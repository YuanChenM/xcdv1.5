package com.msk.price.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.*;
import com.msk.price.utils.CommRestUtil;
import com.msk.price.utils.PriceCycleUtil;
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 供应商：待申报产品一览
 */
@Service
public class SP171109Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171109Logic.class);

    /**
     * SQL Map 中SQL ID定义
     */
    private interface SqlId {
        String FIND_DEMAND_YEARMONTH_LIST = "findYearMonthList";//获取价盘列表
        String GET_PRICE_PERIOD = "getPricePeriod";//获取供应商历史报价
        String GET_PRODUCT_LIST = "getProductList";//根据卖家旗下产品获取产品信息列表
        String GET_PAGE_COUNT_PRODUCT = "getPageCountProduct";//根据卖家旗下产品获取产品信息总数
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取页面list信息
     *
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171109Bean> getPageResultByInterface(BasePageParam pageParam,MskSlInfoServiceParam slInfo) {
        logger.info("供应商待报价产品一览");
        //调用卖家接口，获取卖家产品信息  pdTypeCodeList: {"pdCode", "lgcsAreaCode"}
        PageResult<SP171109Bean> result = new PageResult<SP171109Bean>();
        List<SP171109Bean> list = new ArrayList<SP171109Bean>();
        //login信息
        if (StringUtil.isNullOrEmpty(slInfo.getSlCode())) {
            slInfo.setSlCode(String.valueOf(NumberConst.IntDef.INT_ZERO));
        }
        List<MskSellerServiceParam> pdTypeCodeList = CommRestUtil.getPdResponse(slInfo);
        SP171109Bean bean = null;
        for (MskSellerServiceParam seller : pdTypeCodeList) {
            bean = new SP171109Bean();
            bean.setPdCode(seller.getPdCode() + seller.getSlQltGradeCode());
            bean.setLgcsCode(seller.getLgcsAreaCode());
            list.add(bean);
        }

        //调用库存接口，获取当前库存信息 参数{"pdCode", "lgcsCode", "StockQty"}
        List<Map<String, Object>> pdList = new ArrayList<>();
        Map<String, Object> pd = null;
        for (MskSellerServiceParam data : pdTypeCodeList) {
            if (!StringUtil.isEmpty(data.getPdCode())) {
                pd = new HashMap<>();
                pd.put("supplierCode", slInfo.getSlCode());
                pd.put("lgcsCode", data.getLgcsAreaCode());
                pd.put("pdCode", data.getPdCode() + data.getSlQltGradeCode());
                pdList.add(pd);
            }
        }
        RsResponse<StockResult> response = CommRestUtil.getStockBySellerList(pdList);

        //分页查询结果
        pageParam.getFilterMap().put("pdTypeCodeList", pdTypeCodeList);
        pageParam.getFilterMap().put("list", list);
        pageParam.getFilterMap().put("slCode", slInfo.getSlCode());
        //判断是否查询新价盘数据，调用不同sql
        if (pageParam.getDelFlg().equals(String.valueOf(NumberConst.IntDef.INT_ONE))) {
            if (null != list && list.size() > 0) {//卖家旗下是否有产品
                pageParam.setSystemCode(String.valueOf(NumberConst.IntDef.INT_ONE));
            } else {
                pageParam.setSystemCode(String.valueOf(NumberConst.IntDef.INT_ZERO));
            }
            result = this.getProuductList(pageParam);
        } else {
            result = super.findPage(pageParam, SP171109Bean.class);
            for (SP171109Bean data : result.getData()) {
                if (data.getPdCode().length() == NumberConst.IntDef.INT_TEN) {
                    data.setPdTypeCode(data.getPdCode().substring(0, data.getPdCode().length() - 1));
                } else {
                    data.setPdTypeCode(data.getPdCode());
                }
                for (Stock rs : response.getResult().getPdStockList()) {
                    if (null != rs) {
                        if (data.getLgcsCode().equals(rs.getLgcsCode())
                                && data.getPdCode().equals(rs.getPdCode())
                                && slInfo.getSlCode().equals(rs.getSupplierCode())) {
                            data.setStockQty(rs.getEnabledStockQty().toString());//添加库存信息
                            data.setSellerName(slInfo.getEpName());
                        }
                    }
                }
            }
        }

        //获取并插入价盘和库存信息
        getPriceData(result, response, slInfo, pageParam.getFilterMap().get("pricePeriod").toString());

        return result;
    }

    /**
     * 获取价盘信息
     *
     * @param page
     * @param response
     * @param slInfo
     * @return
     */
    @Transactional(readOnly = true)
    private PageResult<SP171109Bean> getPriceData(PageResult<SP171109Bean> page, RsResponse<StockResult> response, MskSlInfoServiceParam slInfo, String pricePeriod) {
        for (SP171109Bean data : page.getData()) {
            //价盘信息
            BasePageParam pageParam = new BasePageParam();
            pageParam.getFilterMap().put("pdCode", data.getPdCode());
            pageParam.getFilterMap().put("lgcsCode", data.getLgcsCode());
            pageParam.getFilterMap().put("pricePeriod", pricePeriod);
            pageParam.getFilterMap().put("slCode", slInfo.getSlCode());
            List<SP171109Bean> priceInfoList = super.findList(SqlId.GET_PRICE_PERIOD, pageParam);
            //价盘定义
            PriceCycleParam priceCycleParam = new PriceCycleParam();
            priceCycleParam.setPriceCode(pricePeriod);

            PriceCycleResult priceResult = PriceCycleUtil.getPriceCycleByCode(priceCycleParam);//当前价盘对象
            //pricePeriodStart
            data.setPricePeriodStart(priceResult.getStartDateStr());
            //pricePeriodEnd
            data.setPricePeriodEnd(priceResult.getEndDateStr());
            SP171109Bean newPrice = this.getNewPricePeriod();
            if (null != priceInfoList
                    && priceInfoList.size() != 0 && null != priceInfoList.get(0)) {
                data.setPricePeriod(priceInfoList.get(0).getPricePeriod());
                data.setPriceStartDate(priceInfoList.get(0).getPriceStartDate());
                data.setPriceEndDate(priceInfoList.get(0).getPriceEndDate());
                data.setApplyPrice(priceInfoList.get(0).getApplyPrice());
            } else if (data.getIsNewPrice().equals("1")) {
                data.setPricePeriod(newPrice.getPricePeriod());
                data.setPriceStartDate(newPrice.getPriceStartDate());
                data.setPriceEndDate(newPrice.getPriceEndDate());
                data.setApplyPrice("未报价");
            }
            //lastPricePeriod
            priceCycleParam.setCurrentDate(priceResult.getStartDate());
            PriceCycleResult priceCycleResult = PriceCycleUtil.getPrePriceCycle(priceCycleParam);
            data.setLastPricePeriod(priceCycleResult.getCycleCode());
            //setLastPricePeriodTime
            String monthStr = priceCycleResult.getStartDateStr().substring(4, 6);
            data.setLastPricePeriodTime(monthStr + "月"
                    + priceCycleResult.getStartDateStr().substring(6, 8) + "日"
                    + " - " + monthStr + "月"
                    + priceCycleResult.getEndDateStr().substring(6, 8) + "日");
            //卖家名称 sellerName
            data.setSellerName(slInfo.getEpName());
            data.setSellerCode(slInfo.getSlCode());
            //slInfo
            data.setSlId(slInfo.getSlCodeDis());
            data.setSlAccount(slInfo.getSlAccount());
            data.setEpId(slInfo.getEpId());
            data.setEpName(slInfo.getEpName());
            if (data.getPdCode().length() == 10) {
                data.setPdTypeCode(data.getPdCode().substring(0, data.getPdCode().length() - 1));
            }
            //data
            for (Stock rs : response.getResult().getPdStockList()) {
                if (null != rs) {
                    if (data.getLgcsCode().equals(rs.getLgcsCode())
                            && data.getPdCode().equals(rs.getPdCode())
                            && slInfo.getSlCode().equals(rs.getSupplierCode())) {

                        //箱数转成吨数，箱数*净重/1000
                        BigDecimal stockQty = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                        BigDecimal weight = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                        if (!StringUtil.isNullOrEmpty(data.getWeight()) && !data.getWeight().equals(StringConst.MIDDLE_LINE)) {
                            int num = data.getWeight().length();
                            for(int i = 0 ; i < data.getWeight().length() ; i++){ //循环遍历字符串
                                if(Character.isLetter(data.getWeight().charAt(i))){     //用char包装类中的判断数字的方法判断每一个字符
                                    num = i;
                                    break;
                                }
                            }
                            weight = new BigDecimal(data.getWeight().substring(NumberConst.IntDef.INT_ZERO,num));
                            stockQty = DecimalUtil.divide(DecimalUtil.multiply(rs.getEnabledStockQty(), weight),
                                    new BigDecimal(NumberConst.IntDef.INT_THOUSAND));
                        }

                        data.setStockQty(stockQty.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                        data.setSellerName(slInfo.getEpName());
                    }
                }
            }
        }
        return page;
    }

    /**
     * 供应商申报发布年月周期取得
     *
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171109Bean> priceYmList(BasePageParam pageParam) {
        logger.info("价盘年月周期取得");
        List<SP171109Bean> SP171109list = super.findList(SqlId.FIND_DEMAND_YEARMONTH_LIST, pageParam);
        List<SP171109Bean> result = new LinkedList<SP171109Bean>();
        PriceCycleParam priceCycleParam = null;
        for (SP171109Bean SP171109Bean : SP171109list) {
            //判断周期信息是否完整
            if (StringUtil.isNullOrEmpty(SP171109Bean.getPricePeriod()) ||
                    null == SP171109Bean.getPriceStartDate() ||
                    null == SP171109Bean.getPriceEndDate()) {
                continue;
            }
            String pricePeriod = SP171109Bean.getPricePeriod();
            //价盘定义
            priceCycleParam = new PriceCycleParam();
            priceCycleParam.setPriceCode(pricePeriod);
            PriceCycleResult priceResult = PriceCycleUtil.getPriceCycleByCode(priceCycleParam);//当前价盘对象
            String periodNum = pricePeriod.substring(4, 5);
            String priceStartDate = DateTimeUtil.
                    formatDate(DateTimeUtil.FORMAT_YYYYMMDD, SP171109Bean.getPriceStartDate());
            String priceEndDate = DateTimeUtil.
                    formatDate(DateTimeUtil.FORMAT_YYYYMMDD, SP171109Bean.getPriceEndDate());
            SP171109Bean.setPriceStartDate(DateTimeUtil.
                    firstDay(SP171109Bean.getPriceStartDate()));
            SP171109Bean.setPriceEndDate(DateTimeUtil.
                    lastDay(SP171109Bean.getPriceEndDate()));
            SP171109Bean.setDemandYearMonthShow("第" + periodNum + "期 " + priceResult.getCycleName());
            priceCycleParam.setCurrentDate(priceResult.getStartDate());
            PriceCycleResult priceCycleResult = PriceCycleUtil.getPrePriceCycle(priceCycleParam);//上一个价盘
            SP171109Bean.setDemandLimitedDateShow(priceCycleResult.getCycleName());
            result.add(SP171109Bean);
        }
        return result;
    }

    /**
     * 获取下一个价盘周期
     *
     * @return
     */
    public SP171109Bean getNewPricePeriod() {
        PriceCycleParam priceCycleParam = new PriceCycleParam();
        priceCycleParam.setCurrentDate(new Date());
        PriceCycleResult currentPrice = PriceCycleUtil.getPriceCycle(priceCycleParam);//当前所在价盘
        String oldMonth = currentPrice.getStartDateStr().substring(4, 6);
        String oldStartDate = currentPrice.getStartDateStr();
        String oldEndDate = currentPrice.getEndDateStr();
        priceCycleParam.setCurrentDate(this.dayPost5(currentPrice.getEndDate()));//下一个价盘

        currentPrice = PriceCycleUtil.getPriceCycle(priceCycleParam);
        SP171109Bean newPricePeriod = new SP171109Bean();
        newPricePeriod.setPricePeriod(currentPrice.getCycleCode());
        newPricePeriod.setPriceStartDate(currentPrice.getStartDate());
        newPricePeriod.setPriceEndDate(currentPrice.getEndDate());
        //setDemandYearMonthShow
        String periodNum = currentPrice.getDayAmount() + "";
        String priceStartDate = currentPrice.getStartDateStr();
        String priceEndDate = currentPrice.getEndDateStr();
        newPricePeriod.setDemandYearMonthShow("第" + periodNum + "期 " + priceStartDate + "-" + priceEndDate);
        //setDemandLimitedDateShow
        String monthStr = priceStartDate.substring(4, 6);
        newPricePeriod.setDemandLimitedDateShow(oldMonth + "月" + oldStartDate.substring(6, 8) + "日"
                + " - " + oldMonth + "月" + oldEndDate.substring(6, 8) + "日");
        newPricePeriod.setIsNewPrice("1");
        return newPricePeriod;
    }

    /**
     * 根据卖家旗下产品获取产品信息
     *
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171109Bean> getProuductList(BasePageParam pageParam) {
        PageResult<SP171109Bean> pageResult = new PageResult<SP171109Bean>();

        if (pageParam.getSystemCode().equals(String.valueOf(NumberConst.IntDef.INT_ZERO))) {//如果卖家旗下无产品那么返回空数据
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<SP171109Bean>());
        } else {
            pageResult.setRecordsTotal(this.getBaseDao().count(SqlId.GET_PAGE_COUNT_PRODUCT, pageParam));//获取卖家产品总数

            if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
                pageResult.setData(this.getBaseDao().<SP171109Bean>selectList(SqlId.GET_PRODUCT_LIST, pageParam));//获取卖家产品列表
            } else {
                pageResult.setData(new ArrayList<SP171109Bean>());
            }
        }
        return pageResult;
    }

    /**
     * 后五天时间
     *
     * @param date
     * @return
     */
    public Date dayPost5(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, NumberConst.IntDef.INT_FIVE);//后五天
        return calendar.getTime();
    }
}
