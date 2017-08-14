package com.msk.price.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.*;
import com.msk.price.utils.PriceCycleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/6/30.
 */
@Service
public class SP171117Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SP171117Logic.class);
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SP171196Logic sP171196Logic;
    //上限价格系数
    private BigDecimal upPrice = new BigDecimal("1.03");
    //下显价格系数
    private BigDecimal downPrice = new BigDecimal("0.97");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQL_ID_GET_LAST_PRICE_INFO = "getLastPriceInfo";
        static final String SQL_GET_NOW_PRICE_INFO = "getNowPriceInfo";
        static final String SQL_INSERT_PRICEPLATE = "insertPricePlate";
        static final String SQL_UPDATE_PRICEPLATE = "updatePricePlate";
        static final String SQL_COUNT_PRICEPLATE = "countSellerPdPrice";
        static final String SQL_GET_EFFECTIVE_PRICE = "getEffectivePrice";

    }

    /**
     * 查询采购员待报价产品信息
     *
     * @param param
     * @return
     */
    @Transactional
    public List<SP171110Bean> search(SP171110Param param) {
        //获取
        BigDecimal viewFlg = BigDecimal.valueOf(NumberConst.IntDef.INT_FIVE);
        SP171196Param bp = new SP171196Param();
        bp.setViewKey("PRICE_THRESHOLD");
        List<SP171196Bean> viewList = sP171196Logic.findViewList(bp);
        if (null != viewList && viewList.size() > 0) {
            SP171196Bean view = viewList.get(0);
            if (null != view.getViewFlg()) {
                viewFlg = new BigDecimal(view.getViewFlg());
            }
        } else {
            SP171196Bean bean = new SP171196Bean();
            bean.setViewFlg(NumberConst.IntDef.INT_FIVE + "");
            bean.setSystemType("1");//暂时默认是1
            bean.setViewKey("PRICE_THRESHOLD");
            bean.setCrtId(param.getCrtId());
            bean.setUpdId(param.getUpdId());
            bean.setActId(param.getActId());
            sP171196Logic.saveViewInfo(bean);
        }
        BigDecimal threshold = DecimalUtil.divide(viewFlg, BigDecimal.valueOf(NumberConst.IntDef.INT_HUNDRED));
        upPrice = DecimalUtil.add(BigDecimal.valueOf(NumberConst.IntDef.INT_ONE), threshold);
        downPrice = DecimalUtil.subtract(BigDecimal.valueOf(NumberConst.IntDef.INT_ONE), threshold);

        // 查询价盘通道等级详细
        List<SP171110Bean> sellerPriceDetailList = this.findList(param);

        int count = this.getCount(SqlId.SQL_COUNT_PRICEPLATE, param);
        List<SP171110Bean> priceInfoList = this.findList(SqlId.SQL_GET_NOW_PRICE_INFO, param);
        for (SP171110Bean sp171110Bean : sellerPriceDetailList) {
            if (NumberConst.IntDef.INT_ZERO == count) {
                // 设置默认被选中
                sp171110Bean.setIsValid(String.valueOf(NumberConst.IntDef.INT_ONE));
            } else {
                for (SP171110Bean sp : priceInfoList) {
                    if (sp.getDelFlg().equals("0") && sp171110Bean.getWayGradeCode().equals(sp.getWayGradeCode())) {
                        // 设置其被选中
                        sp171110Bean.setIsValid(String.valueOf(NumberConst.IntDef.INT_ONE));
                    }
                    if (sp171110Bean.getWayGradeCode().equals(sp.getWayGradeCode())) {
                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                        sp171110Bean.setWayGradePrice(new BigDecimal(decimalFormat.format(sp.getWayGradePrice())));
                        sp171110Bean.setWayGradeTempPrice(sp.getWayGradeTempPrice());
                    }
                }
            }
        }
        //查询最新的历史价盘周期
        SP171110Bean price = this.findOne(SqlId.SQL_GET_EFFECTIVE_PRICE, param);
        if (null != price && !StringUtil.isEmpty(price.getWayCode())) {
            param.getFilterMap().put("lastPricePeriod", price.getWayCode());
            // 查询前次的价盘周期和加盘通道等级
            List<SP171110Bean> lastPriceInfoList = this.findList(SqlId.SQL_ID_GET_LAST_PRICE_INFO, param);
            for (SP171110Bean sp171110Bean : sellerPriceDetailList) {
                for (SP171110Bean bean : lastPriceInfoList) {
                    if (sp171110Bean.getWayGradeCode().equals(bean.getWayGradeCode())) {
                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                        sp171110Bean.setUpPrice(new BigDecimal(decimalFormat.format(bean.getWayGradePrice().multiply(upPrice))));
                        sp171110Bean.setDownPrice(new BigDecimal(decimalFormat.format(bean.getWayGradePrice().multiply(downPrice))));
                        sp171110Bean.setLastWayGradePrice(new BigDecimal(decimalFormat.format(bean.getWayGradePrice())));
                    }
                }
            }
        }

        return sellerPriceDetailList;
    }


    @Transactional
    public void modifyPrice(SP171110Bean param, SP171110Param sp171110Param) {
        Date nowDate = DateTimeUtil.getCustomerDate();
        param.setCrtTime(nowDate);
        param.setUpdTime(nowDate);
        param.setActTime(nowDate);
        String[] isValidArray = param.getIsValidArray();
        String[] wayCodeArray = param.getWayCodeArray();
        String[] marketingNameArray = param.getMarketingNameArray();
        String[] wayGradeCodeArray = param.getWayGradeCodeArray();
        String[] wayGradeNameArray = param.getWayGradeNameArray();
        BigDecimal[] wayGradePriceArray = param.getWayGradePriceArray();
        BigDecimal[] wayGradeTempPriceArray = param.getWayGradeTempPriceArray();
        isValidArray = formatIsValidArray(isValidArray);//判断哪些通道没有被勾选，1-代表参与分销，0-代表没有参与分销
        String weights = sp171110Param.getWeight().substring(0, sp171110Param.getWeight().length() - 2);
        int count = this.getCount(SqlId.SQL_COUNT_PRICEPLATE, sp171110Param);
        /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  Start */
        // 根据价盘周期获取价盘周期对应的时间
        PriceCycleParam priceCycleParam = new PriceCycleParam();
        priceCycleParam.setPriceCode(param.getPricePeriod());
        PriceCycleResult priceCycle = PriceCycleUtil.getPriceCycleByCode(priceCycleParam);
        sp171110Param.setPricePeriodStart(priceCycle.getStartDateStr());
        sp171110Param.setPricePeriodEnd(priceCycle.getEndDateStr());
        /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  end */
        if (NumberConst.IntDef.INT_ZERO == count) {
            for (int i = 0; i < wayGradeCodeArray.length; i++) {
                long priceId = commonLogic.maxId("SP_SELLER_PD_PRICEPLATE", "PRICE_ID");
                param.setPriceId(priceId);
                param.setLgcsCode(sp171110Param.getLgcsCode());
                param.setPricePeriodStart(simpleDateFormat.format(DateTimeUtil.parseDate(sp171110Param.getPricePeriodStart(), DateTimeUtil.FORMAT_YYYYMMDD)));
                param.setPricePeriodEnd(simpleDateFormat.format(DateTimeUtil.parseDate(sp171110Param.getPricePeriodEnd(), DateTimeUtil.FORMAT_YYYYMMDD)));
                param.setPdCode(sp171110Param.getPdCode());
                param.setPricePeriod(sp171110Param.getPricePeriod());
                param.setWayCode(wayCodeArray[i]);
                param.setMarketingName(marketingNameArray[i]);
                param.setWayGradeCode(wayGradeCodeArray[i]);
                param.setWayGradeName(wayGradeNameArray[i]);
                param.setWayGradePrice(wayGradeTempPriceArray[i]);
                BigDecimal pricefBox = new BigDecimal(wayGradeTempPriceArray[i].toString()).multiply(new BigDecimal(weights));
                param.setWayGradePricefBox(pricefBox);
                // 0-代表没有参与分销
                if (isValidArray[i].equals("0")) {
                    // 没有参与分销
                    param.setDelFlg("1");
                } else {
                    param.setDelFlg("0");
                }
                this.save(SqlId.SQL_INSERT_PRICEPLATE, param);
            }
        } else {
            for (int i = 0; i < wayGradeCodeArray.length; i++) {
                param.setPricePeriod(sp171110Param.getPricePeriod());
                param.setWayGradeCode(wayGradeCodeArray[i]);
                param.setWayGradePrice(wayGradeTempPriceArray[i]);
                param.setWayCode(wayCodeArray[i]);
                param.setMarketingName(marketingNameArray[i]);
                BigDecimal pricefBox = new BigDecimal(wayGradeTempPriceArray[i].toString()).multiply(new BigDecimal(weights));
                param.setWayGradePricefBox(pricefBox);
                if (!isValidArray[i].equals("0")) {
                    param.setDelFlg("0");
                } else {
                    param.setDelFlg("1");
                }
                this.modify(SqlId.SQL_UPDATE_PRICEPLATE, param);
            }
        }
    }

    /**
     * 组装checkbox的array
     *
     * @param isValidArray
     */
    private String[] formatIsValidArray(String[] isValidArray) {
        String[] checkBoxArray = new String[10];
        if (isValidArray != null && isValidArray.length != 0) {
            for (int i = 0; i < isValidArray.length; i++) {
                if ("0".equals(isValidArray[i])) {
                    checkBoxArray[0] = "1";
                }
                if ("1".equals(isValidArray[i])) {
                    checkBoxArray[1] = checkBoxArray[2] = checkBoxArray[3] = "1";
                }
                if ("4".equals(isValidArray[i])) {
                    checkBoxArray[4] = checkBoxArray[5] = checkBoxArray[6] = "1";
                }
                if ("7".equals(isValidArray[i])) {
                    checkBoxArray[7] = checkBoxArray[8] = checkBoxArray[9] = "1";
                }
            }
        }
        for (int j = 0; j < 10; j++) {
            if (StringUtil.isEmpty(checkBoxArray[j])) {
                checkBoxArray[j] = "0";
            }
        }
        return checkBoxArray;
    }

}
