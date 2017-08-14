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
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 供应商待申报产品一览
 *
 * @author ni_shaotang
 */
@Service
public class SP171105Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171105Logic.class);

    /**
     * SQL Map 中SQL ID定义
     */
    private interface SqlId {
        String FIND_DEMAND_YEARMONTH_LIST = "findYearMonthList";//获取已有申报周期
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
    public PageResult<SP171105Bean> getPageResultByInterface(BasePageParam pageParam, MskSlInfoServiceParam slInfo) {
        logger.info("供应商待申报产品一览");
        //调用卖家接口，根据SL Code 获取卖家名下商品的信息
        List<MskSellerServiceParam> pdTypeCodeList = CommRestUtil.getPdResponse(slInfo);

        //分页查询结果
        if(null!=pdTypeCodeList&& pdTypeCodeList.size()>0){
            pageParam.getFilterMap().put("pdTypeCodeList", pdTypeCodeList);
            PageResult<SP171105Bean> result = super.findPage(pageParam, SP171105Bean.class);
            //调用库存接口，获取当前库存信息 参数{"pdCode", "lgcsCode", "StockQty"}
            result = getStockResponse(result, slInfo);
            return result;
        }else{
            PageResult<SP171105Bean> pageResult = new PageResult<SP171105Bean>();
            pageResult.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            pageResult.setData(new ArrayList<SP171105Bean>());
            return pageResult;
        }

    }

    /**
     * 调用库存接口，根据卖家商品信息获取对应商品的现有库存量
     *
     * @param page
     * @param slInfo
     * @return
     */
    private PageResult<SP171105Bean> getStockResponse(PageResult<SP171105Bean> page, MskSlInfoServiceParam slInfo) {
        logger.info("调用库存接口,查询产品现有库存");
        //设置请求参数
        List<Map<String, Object>> pdList = new ArrayList<>();//接口参数集合
        Map<String, Object> pd = null;
        for (SP171105Bean data : page.getData()) {
            if (!StringUtil.isNullOrEmpty(data.getPdCode())) {
                pd = new HashMap<>();
                pd.put("supplierCode", slInfo.getSlCode());
                pd.put("lgcsCode", data.getLgcsCode());
                pd.put("pdCode", data.getPdCode());
                pdList.add(pd);
            }

            //需求预测数量转箱转吨
            String forecastNumStr = data.getForecastNum();//需求预测数量

            if (!StringUtil.isNullOrEmpty(forecastNumStr) && !forecastNumStr.equals(StringConst.MIDDLE_LINE)) {
                BigDecimal forecastNum = new BigDecimal(forecastNumStr);

                //箱数转成吨数，箱数*净重/1000
                if (!StringUtil.isNullOrEmpty(data.getWeight()) && !data.getWeight().equals(StringConst.MIDDLE_LINE)) {
                    int num = data.getWeight().length();
                    for (int i = 0; i < data.getWeight().length(); i++) { //循环遍历字符串
                        if (Character.isLetter(data.getWeight().charAt(i))) {     //用char包装类中的判断数字的方法判断每一个字符
                            num = i;
                            break;
                        }
                    }
                    BigDecimal weight = new BigDecimal(data.getWeight().substring(NumberConst.IntDef.INT_ZERO, num));
                    forecastNumStr = DecimalUtil.divide(DecimalUtil.multiply(forecastNum, weight),
                            new BigDecimal(NumberConst.IntDef.INT_THOUSAND)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                }
                data.setForecastNum(forecastNumStr);
            }
        }
        //调用接口
        /** Modfiy:  优化代码，当pdList为空时，不需要再去调接口，不然接口处会报pdList不能为空   2016/10/11   BY  zhukai1  Start */
        RsResponse<StockResult> response = new RsResponse<>();
        if(!CollectionUtils.isEmpty(pdList)){
             response = CommRestUtil.getStockBySellerList(pdList);
        }
        /** Modfiy:  优化代码，当pdList为空时，不需要再去调接口，不然接口处会报pdList不能为空   2016/10/11   BY  zhukai1  end */
        for (SP171105Bean data : page.getData()) {
            //周期
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  Start */
            String demandMonth = data.getDemandYearMonth().substring(4, 6);
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对  2016/10/11   BY  zhukai1  end */
            String demandStartDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, data.getStartDate());
            String demandEndDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, data.getEndDate());
            data.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);
            //起止日期
            String lastMonth = DateTimeUtil.getLastMonth(data.getDemandYearMonth());
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  Start */
            data.setDemandLimitedDateShow(lastMonth.substring(4, 6) + "月16日 - " + lastMonth.substring(4, 6) + "月20日");
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对  2016/10/11   BY  zhukai1  end */
            //slInfo
            data.setSlId(slInfo.getSlId());
            data.setSlAccount(slInfo.getSlAccount());
            data.setSlCode(slInfo.getSlCode());
            data.setEpId(slInfo.getEpId());
            data.setEpName(slInfo.getEpName());
            //
            if (null != response && null != response.getResult()) {
                for (Stock rs : response.getResult().getPdStockList()) {
                    if (null != rs) {
                        if (data.getLgcsCode().equals(rs.getLgcsCode())
                                && data.getPdCode().equals(rs.getPdCode())
                                && slInfo.getSlCode().equals(rs.getSupplierCode())) {

                            //库存箱数转成吨数，箱数*净重/1000
                            BigDecimal stockQty = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                            BigDecimal weight = new BigDecimal(NumberConst.IntDef.INT_ZERO);
                            if (!StringUtil.isNullOrEmpty(data.getWeight()) && !data.getWeight().equals(StringConst.MIDDLE_LINE)) {
                                int num = data.getWeight().length();
                                for (int i = 0; i < data.getWeight().length(); i++) { //循环遍历字符串
                                    if (Character.isLetter(data.getWeight().charAt(i))) {     //用char包装类中的判断数字的方法判断每一个字符
                                        num = i;
                                        break;
                                    }
                                }
                                weight = new BigDecimal(data.getWeight().substring(NumberConst.IntDef.INT_ZERO, num));
                                stockQty = DecimalUtil.divide(DecimalUtil.multiply(rs.getEnabledStockQty(), weight),
                                        new BigDecimal(NumberConst.IntDef.INT_THOUSAND));
                            }
                            data.setStockQty(stockQty.setScale(2, BigDecimal.ROUND_HALF_UP).toString());//添加库存数据到实体
                        }
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
    public List<SP171105Bean> publishYmList(BasePageParam pageParam) {
        logger.info("OEM供应商申报发布年月周期取得");
        List<SP171105Bean> SP171105list = super.findList(SqlId.FIND_DEMAND_YEARMONTH_LIST, pageParam);
        if (null != SP171105list && SP171105list.size() > 0) {
            for (SP171105Bean SP171105Bean : SP171105list) {
                String demandYearMonth = SP171105Bean.getDemandYearMonth();
                /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  Start */
                String demandMonth = demandYearMonth.substring(4, 6);
                /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  end */
                String demandStartDate = DateTimeUtil.
                        formatDate(DateTimeUtil.FORMAT_YYYYMMDD,
                                DateTimeUtil.firstDay(SP171105Bean.getStartDate()));
                String demandEndDate = DateTimeUtil.
                        formatDate(DateTimeUtil.FORMAT_YYYYMMDD,
                                DateTimeUtil.lastDay(SP171105Bean.getEndDate()));
                SP171105Bean.setStartDate(DateTimeUtil.
                        firstDay(SP171105Bean.getStartDate()));
                SP171105Bean.setEndDate(DateTimeUtil.
                        lastDay(SP171105Bean.getEndDate()));
                SP171105Bean.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);
                String lastMonth = DateTimeUtil.getLastMonth(demandYearMonth);
                /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  Start */
                SP171105Bean.setDemandLimitedDateShow(lastMonth.substring(4, 6) + "月16日 - " + lastMonth.substring(4, 6) + "月20日");
                /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  end */
            }
        } else {//从来没有发布产品数据。添加当前申报周期
            SP171105list = new ArrayList<SP171105Bean>();
            Date nowDate = DateTimeUtil.getCustomerDate();//获取当前时间
            Date firstDate = DateTimeUtil.addMonth(nowDate, 1);//下个月时间
            String yearMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, firstDate);
            Date startDate = DateTimeUtil.getMinMonthDate(firstDate);
            Date endDate = DateTimeUtil.getMaxMonthDate(firstDate);
            SP171105Bean sp171105Bean = new SP171105Bean();
            sp171105Bean.setDemandYearMonth(yearMonth);
            sp171105Bean.setStartDate(startDate);
            sp171105Bean.setEndDate(endDate);
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  Start */
            String demandMonth = yearMonth.substring(4, 6);
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  end */
            String demandStartDate = DateTimeUtil.
                    formatDate(DateTimeUtil.FORMAT_YYYYMMDD,
                            DateTimeUtil.firstDay(sp171105Bean.getStartDate()));
            String demandEndDate = DateTimeUtil.
                    formatDate(DateTimeUtil.FORMAT_YYYYMMDD,
                            DateTimeUtil.lastDay(sp171105Bean.getEndDate()));
            sp171105Bean.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);
            String lastMonth = DateTimeUtil.getLastMonth(yearMonth);
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  Start */
            sp171105Bean.setDemandLimitedDateShow(lastMonth.substring(4, 6) + "月16日 - " + lastMonth.substring(4, 6) + "月20日");
            /** Modfiy:  优化代码，当data.getDemandYearMonth()为201610时，截取5-6位，那么截取结果为0，明显不对   2016/10/11   BY  zhukai1  end */
            SP171105list.add(sp171105Bean);
        }
        return SP171105list;
    }


}
