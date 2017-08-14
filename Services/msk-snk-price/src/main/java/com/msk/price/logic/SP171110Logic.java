package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import com.msk.price.bean.SP171110Bean;
import com.msk.price.bean.SP171110Param;
import com.msk.price.utils.PriceCycleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商价格申报详细
 * <p/>
 * Created by qi_dianyong.
 */
@Service
public class SP171110Logic extends BaseLogic {
    //上限价格系数
    private BigDecimal upPrice = new BigDecimal("1.03");
    //下显价格系数
    private BigDecimal downPrice = new BigDecimal("0.97");
    //价格保留两位小数格式化
    DecimalFormat myFormat = new DecimalFormat("#.00");

    private static Logger logger = LoggerFactory.getLogger(SP171110Logic.class);
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        //卖家供应商报价表是否有数据
        String SQL_ID_COUNT_SELLER_PD_PRICE_ID = "countSellerPdPrice";

        // 查询价盘通道等级详细
        String SQL_ID_GET_WAY_DETAIL_INFO = "getWayDetailInfo";

        // 前次加盘信息个价盘等级
        String SQL_ID_GET_LAST_PRICE_INFO = "gerLastPriceInfo";

        // 获取价格
        String SQL_ID_GET_SELLER_PRICE = "getSellerPrice";

        //卖家供应商报价详细表插入
        String SQL_ID_INSERT_SELLER_DETAIL_PRICE = "insertSellerDetailPrice";

        //卖家供应商报价详细表更新
        String SQL_ID_UPDATE_SELLER_DETAIL_PRICE = "updateSellerDetailPrice";
        //获取报价截止时间时分秒
        String SQL_ID_GET_PRICE_END_TIME = "getPriceEndTime";
    }

    /**
     * 查询供应商价格申报详细
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171110Bean> getSellerPriceList(SP171110Param param) {

        // 获取价格
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("pricePeriod", param.getPricePeriod());
        baseParam.setFilter("lastPricePeriod", param.getLastPricePeriod());
        baseParam.setFilter("lgcsCode", param.getLgcsCode());
        baseParam.setFilter("pdCode", param.getPdCode());
        baseParam.setFilter("slCode", param.getSellerCode());
        baseParam.setFilter("lastPricePeriodTime", param.getLastPricePeriodTime());
        // 卖家供应商报价表是否有数据
        int count = this.getCount(SqlId.SQL_ID_COUNT_SELLER_PD_PRICE_ID, baseParam);
        // 查询价盘通道等级详细
        List<SP171110Bean> sellerPriceDetailList = this.findList(SqlId.SQL_ID_GET_WAY_DETAIL_INFO, baseParam);
        // 查询前次的价盘周期和加盘通道等级
        List<SP171110Bean> lastPriceInfoList = this.findList(SqlId.SQL_ID_GET_LAST_PRICE_INFO, baseParam);

        for (int i = 0; i < sellerPriceDetailList.size(); i++) {
            if (lastPriceInfoList.size() > i) {
                sellerPriceDetailList.get(i).setUpPrice(new BigDecimal(myFormat.format(lastPriceInfoList.get(i).getWayGradePrice().multiply(upPrice).doubleValue())));
                sellerPriceDetailList.get(i).setDownPrice((new BigDecimal(myFormat.format(lastPriceInfoList.get(i).getWayGradePrice().multiply(downPrice)))));
                sellerPriceDetailList.get(i).setLastWayGradePrice((new BigDecimal(myFormat.format(lastPriceInfoList.get(i).getWayGradePrice()))));
            }
            if (NumberConst.IntDef.INT_ZERO == count) {
                // 卖家供应商报价没有数据
                sellerPriceDetailList.get(i).setWayGradePrice(new BigDecimal(NumberConst.IntDef.INT_ZERO));
                sellerPriceDetailList.get(i).setIsValid(String.valueOf(NumberConst.IntDef.INT_ZERO));
            } else {
                // 查询卖家供应商报价详细表
                List<SP171110Bean> priceList = this.findList(SqlId.SQL_ID_GET_SELLER_PRICE, baseParam);
                // 获取报价ID页面更新用
                param.setPriceId(priceList.get(0).getPriceId());
                if (priceList.size() > i) {
                    sellerPriceDetailList.get(i).setWayGradePrice(priceList.get(i).getWayGradePrice());
                    sellerPriceDetailList.get(i).setIsValid(priceList.get(i).getIsValid());
                }
            }
        }
        return sellerPriceDetailList;
    }

    /**
     * 更新/插入卖家供应商投标数量表，插入卖家供应商投标数量履历明细表
     *
     * @param param
     */
    @Transactional
    public void modifyPrice(SP171110Bean param, SP171110Param sp171110Param) {

        String[] isValidArray = param.getIsValidArray();
        String[] wayCodeArray = param.getWayCodeArray();
        String[] wayGradeCodeArray = param.getWayGradeCodeArray();
        String[] wayGradeNameArray = param.getWayGradeNameArray();
        BigDecimal[] wayGradePriceArray = param.getWayGradePriceArray();
        BigDecimal[] downPriceArray = param.getDownPriceArray();
        BigDecimal[] upPriceArray = param.getUpPriceArray();
        BigDecimal[] wayGradePercentArray = param.getWayGradePercentArray();
        isValidArray = formatIsValidArray(isValidArray);
        // 卖家供应商报价表是否有数据
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("pricePeriod", sp171110Param.getPricePeriod());
        baseParam.setFilter("lgcsCode", sp171110Param.getLgcsCode());
        baseParam.setFilter("pdCode", sp171110Param.getPdCode());
        //TODO:
        baseParam.setFilter("slId", sp171110Param.getSlId());
        baseParam.setFilter("slCode", sp171110Param.getSellerCode());
        int count = this.getCount(SqlId.SQL_ID_COUNT_SELLER_PD_PRICE_ID, baseParam);
        if (NumberConst.IntDef.INT_ZERO == count) {
            // 卖家供应商报价表
            long priceId = commonLogic.maxId("SP_SELLER_PD_PRICE", "PRICE_ID");
            sp171110Param.setPriceId(priceId);
            this.save(sp171110Param);
            // 卖家供应商报价详细表
            for (int i = 0; i < wayGradeCodeArray.length; i++) {
                //主键
                long priceDetailId = commonLogic.maxId("SP_SELLER_PD_PRICE_DETAIL", "PRICE_DETAIL_ID");
                param.setPriceDetailId(priceDetailId);
                param.setPriceId(priceId);
                param.setWayCode(wayCodeArray[i]);
                param.setWayGradeCode(wayGradeCodeArray[i]);
                param.setWayGradeName(wayGradeNameArray[i]);
                param.setWayGradePrice(wayGradePriceArray[i]);
                param.setDownPrice(downPriceArray[i]);
                param.setUpPrice(upPriceArray[i]);
                param.setIsValid(isValidArray[i]);
                param.setWayGradePercent(wayGradePercentArray[i]);
                this.save(SqlId.SQL_ID_INSERT_SELLER_DETAIL_PRICE, param);
            }
        } else {
            for (int i = 0; i < wayGradeCodeArray.length; i++) {

                param.setWayCode(wayCodeArray[i]);
                param.setWayGradeCode(wayGradeCodeArray[i]);
                param.setWayGradePrice(wayGradePriceArray[i]);
                param.setIsValid(isValidArray[i]);
                param.setPriceId(sp171110Param.getPriceId());
                // 更新卖家供应商报价详细表
                this.save(SqlId.SQL_ID_UPDATE_SELLER_DETAIL_PRICE, param);
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


    /**
     * 获得报价截止日期时分秒
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String getPriceEndTime() {
        BaseParam param = new BaseParam();
        String priceEndTime = String.valueOf(super.findObject(SqlId.SQL_ID_GET_PRICE_END_TIME, param));
        return priceEndTime;
    }
    /**
     *根据产品code获取产品信息
     *
     * @return
     */
    @Transactional(readOnly = true)
    public void queryPdInfo(SP171110Param param) {
        SP171110Bean bean = this.findOne(param);
        param.setLgcsCode(bean.getLgcsCode());
        param.setLgcsName(bean.getLgcsName());
        param.setPdCode(bean.getPdCode());
        param.setPdName(bean.getPdName());
        param.setClassesCode(bean.getClassesCode());
        param.setClassesName(bean.getClassesName());
        param.setMachiningCode(bean.getMachiningCode());
        param.setMachining(bean.getMachining());
        param.setBreedCode(bean.getBreedCode());
        param.setBreed(bean.getBreed());
        param.setFeatureCode(bean.getFeatureCode());
        param.setFeature(bean.getFeature());
        param.setWeightCode(bean.getWeightCode());
        param.setWeight(bean.getWeight());
        param.setGradeCode(bean.getGradeCode());
        param.setGrade(bean.getGrade());
    }
}
