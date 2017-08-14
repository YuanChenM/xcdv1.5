package com.msk.price.bean;

import com.msk.core.entity.PdPriceprdLogiarea;

import java.util.Date;

/**
 * Created by zhu_kai1 on 2016/6/23.
 */
public class SP171170Bean extends PdPriceprdLogiarea {

    /**订单数量**/
    private String orderCount;
    /**通道等级名称**/
    private  String wayGradeName;
    private String wayCode;
    private String marketingName;
    /**营销状态**/
    private String wayName;
    /**等级通道Code**/
    private int wayGradeCode;
    /**产品等级名称**/
    private String gradeName;
    /**产品code**/
    private String pdCode;
    /**产品净重名称**/
    private  String weightName;
    /**超级订单公斤价**/
    private String supPriceofkg;
    /**超级订单箱价**/
    private String supPriceofbox;

    private String supGradeCode;

    private String supDelFlg;
    /**大宗订单1级公斤价**/
    private String onePriceofkg;

    private String oneGradeCode;
    /**大宗订单1级箱价**/
    private String onepriceofbox;

    private String oneDelFlg;
    /**大宗订单2级公斤价**/
    private String twoPriceofkg;
    /**大宗订单2级箱价**/
    private String twoPriceofbox;

    private String twoGradeCode;

    private String twoDelFlg;
    /**大额订单3级公斤价**/
    private String threePriceofkg;
    /**大额订单3级箱价**/
    private String threepriceofbox;

    private String threeGradeCode;

    private String threeDelFlg;
    /**大额订单4级公斤价**/
    private String fourPriceofkg;
    /**大额订单4级箱价**/
    private String fourPriceofbox;

    private String fourGradeCode;

    private String fourDelFlg;
    /**大额订单5级公斤价**/
    private String fivePriceofkg;
    /**大额订单5级箱价**/
    private String fivepriceofbox;

    private String fiveGradeCode;

    private String fiveDelFlg;
    /**标准订单6级公斤价**/
    private String sixPriceofkg;
    /**标准订单6级箱价**/
    private String sixPriceofbox;

    private String sixGradeCode;

    private String sixDelFlg;
    /**标准订单7级公斤价**/
    private String sevenPriceofkg;
    /**标准订单7级箱价**/
    private String sevenpriceofbox;

    private String sevenGradeCode;

    private String sevenDelFlg;
    /**标准订单8级公斤价**/
    private String eightPriceofkg;
    /**标准订单8级箱价**/
    private String eightPriceofbox;

    private String eightGradeCode;

    private String eightDelFlg;
    /**标准订单9级公斤价**/
    private String ninePriceofkg;
    /**标准订单9级箱价**/
    private String ninepriceofbox;

    private String nineGradeCode;

    private String nineDelFlg;
    /**最大更新时间**/
    private String maxUpdTime;
    /**execl导入失败与否**/
    private String message;
    /**excel导入成功条数**/
    private int result;
    /**价盘开始时间**/
    private Date pricePeriodStart;
    /**价盘结束时间**/
    private Date pricePeriodEnd;

    // 以下为通道箱数区间
    private String supOrder;
    private String order1;
    private String order2;
    private String order3;
    private String order4;
    private String order5;
    private String order6;
    private String order7;
    private String order8;
    private String order9;
    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getWayGradeName() {
        return wayGradeName;
    }

    public void setWayGradeName(String wayGradeName) {
        this.wayGradeName = wayGradeName;
    }

    public int getWayGradeCode() {
        return wayGradeCode;
    }

    public void setWayGradeCode(int wayGradeCode) {
        this.wayGradeCode = wayGradeCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getSupPriceofkg() {
        return supPriceofkg;
    }

    public void setSupPriceofkg(String supPriceofkg) {
        this.supPriceofkg = supPriceofkg;
    }

    public String getSupPriceofbox() {
        return supPriceofbox;
    }

    public void setSupPriceofbox(String supPriceofbox) {
        this.supPriceofbox = supPriceofbox;
    }

    public String getOnePriceofkg() {
        return onePriceofkg;
    }

    public void setOnePriceofkg(String onePriceofkg) {
        this.onePriceofkg = onePriceofkg;
    }

    public String getOnepriceofbox() {
        return onepriceofbox;
    }

    public void setOnepriceofbox(String onepriceofbox) {
        this.onepriceofbox = onepriceofbox;
    }

    public String getTwoPriceofkg() {
        return twoPriceofkg;
    }

    public void setTwoPriceofkg(String twoPriceofkg) {
        this.twoPriceofkg = twoPriceofkg;
    }

    public String getTwoPriceofbox() {
        return twoPriceofbox;
    }

    public void setTwoPriceofbox(String twoPriceofbox) {
        this.twoPriceofbox = twoPriceofbox;
    }

    public String getThreePriceofkg() {
        return threePriceofkg;
    }

    public void setThreePriceofkg(String threePriceofkg) {
        this.threePriceofkg = threePriceofkg;
    }

    public String getThreepriceofbox() {
        return threepriceofbox;
    }

    public void setThreepriceofbox(String threepriceofbox) {
        this.threepriceofbox = threepriceofbox;
    }

    public String getFourPriceofkg() {
        return fourPriceofkg;
    }

    public void setFourPriceofkg(String fourPriceofkg) {
        this.fourPriceofkg = fourPriceofkg;
    }

    public String getFourPriceofbox() {
        return fourPriceofbox;
    }

    public void setFourPriceofbox(String fourPriceofbox) {
        this.fourPriceofbox = fourPriceofbox;
    }

    public String getFivePriceofkg() {
        return fivePriceofkg;
    }

    public void setFivePriceofkg(String fivePriceofkg) {
        this.fivePriceofkg = fivePriceofkg;
    }

    public String getFivepriceofbox() {
        return fivepriceofbox;
    }

    public void setFivepriceofbox(String fivepriceofbox) {
        this.fivepriceofbox = fivepriceofbox;
    }

    public String getSixPriceofkg() {
        return sixPriceofkg;
    }

    public void setSixPriceofkg(String sixPriceofkg) {
        this.sixPriceofkg = sixPriceofkg;
    }

    public String getSixPriceofbox() {
        return sixPriceofbox;
    }

    public void setSixPriceofbox(String sixPriceofbox) {
        this.sixPriceofbox = sixPriceofbox;
    }

    public String getSevenPriceofkg() {
        return sevenPriceofkg;
    }

    public void setSevenPriceofkg(String sevenPriceofkg) {
        this.sevenPriceofkg = sevenPriceofkg;
    }

    public String getSevenpriceofbox() {
        return sevenpriceofbox;
    }

    public void setSevenpriceofbox(String sevenpriceofbox) {
        this.sevenpriceofbox = sevenpriceofbox;
    }

    public String getEightPriceofkg() {
        return eightPriceofkg;
    }

    public void setEightPriceofkg(String eightPriceofkg) {
        this.eightPriceofkg = eightPriceofkg;
    }

    public String getEightPriceofbox() {
        return eightPriceofbox;
    }

    public void setEightPriceofbox(String eightPriceofbox) {
        this.eightPriceofbox = eightPriceofbox;
    }

    public String getNinePriceofkg() {
        return ninePriceofkg;
    }

    public void setNinePriceofkg(String ninePriceofkg) {
        this.ninePriceofkg = ninePriceofkg;
    }

    public String getNinepriceofbox() {
        return ninepriceofbox;
    }

    public void setNinepriceofbox(String ninepriceofbox) {
        this.ninepriceofbox = ninepriceofbox;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSupGradeCode() {
        return supGradeCode;
    }

    public void setSupGradeCode(String supGradeCode) {
        this.supGradeCode = supGradeCode;
    }

    public String getOneGradeCode() {
        return oneGradeCode;
    }

    public void setOneGradeCode(String oneGradeCode) {
        this.oneGradeCode = oneGradeCode;
    }

    public String getTwoGradeCode() {
        return twoGradeCode;
    }

    public void setTwoGradeCode(String twoGradeCode) {
        this.twoGradeCode = twoGradeCode;
    }

    public String getThreeGradeCode() {
        return threeGradeCode;
    }

    public void setThreeGradeCode(String threeGradeCode) {
        this.threeGradeCode = threeGradeCode;
    }

    public String getFourGradeCode() {
        return fourGradeCode;
    }

    public void setFourGradeCode(String fourGradeCode) {
        this.fourGradeCode = fourGradeCode;
    }

    public String getFiveGradeCode() {
        return fiveGradeCode;
    }

    public void setFiveGradeCode(String fiveGradeCode) {
        this.fiveGradeCode = fiveGradeCode;
    }

    public String getSixGradeCode() {
        return sixGradeCode;
    }

    public void setSixGradeCode(String sixGradeCode) {
        this.sixGradeCode = sixGradeCode;
    }

    public String getSevenGradeCode() {
        return sevenGradeCode;
    }

    public void setSevenGradeCode(String sevenGradeCode) {
        this.sevenGradeCode = sevenGradeCode;
    }

    public String getEightGradeCode() {
        return eightGradeCode;
    }

    public void setEightGradeCode(String eightGradeCode) {
        this.eightGradeCode = eightGradeCode;
    }

    public String getNineGradeCode() {
        return nineGradeCode;
    }

    public void setNineGradeCode(String nineGradeCode) {
        this.nineGradeCode = nineGradeCode;
    }

    public String getSupDelFlg() {
        return supDelFlg;
    }

    public void setSupDelFlg(String supDelFlg) {
        this.supDelFlg = supDelFlg;
    }

    public String getOneDelFlg() {
        return oneDelFlg;
    }

    public void setOneDelFlg(String oneDelFlg) {
        this.oneDelFlg = oneDelFlg;
    }

    public String getTwoDelFlg() {
        return twoDelFlg;
    }

    public void setTwoDelFlg(String twoDelFlg) {
        this.twoDelFlg = twoDelFlg;
    }

    public String getThreeDelFlg() {
        return threeDelFlg;
    }

    public void setThreeDelFlg(String threeDelFlg) {
        this.threeDelFlg = threeDelFlg;
    }

    public String getFourDelFlg() {
        return fourDelFlg;
    }

    public void setFourDelFlg(String fourDelFlg) {
        this.fourDelFlg = fourDelFlg;
    }

    public String getFiveDelFlg() {
        return fiveDelFlg;
    }

    public void setFiveDelFlg(String fiveDelFlg) {
        this.fiveDelFlg = fiveDelFlg;
    }

    public String getSixDelFlg() {
        return sixDelFlg;
    }

    public void setSixDelFlg(String sixDelFlg) {
        this.sixDelFlg = sixDelFlg;
    }

    public String getSevenDelFlg() {
        return sevenDelFlg;
    }

    public void setSevenDelFlg(String sevenDelFlg) {
        this.sevenDelFlg = sevenDelFlg;
    }

    public String getEightDelFlg() {
        return eightDelFlg;
    }

    public void setEightDelFlg(String eightDelFlg) {
        this.eightDelFlg = eightDelFlg;
    }

    public String getNineDelFlg() {
        return nineDelFlg;
    }

    public void setNineDelFlg(String nineDelFlg) {
        this.nineDelFlg = nineDelFlg;
    }

    public String getMaxUpdTime() {
        return maxUpdTime;
    }

    public void setMaxUpdTime(String maxUpdTime) {
        this.maxUpdTime = maxUpdTime;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getPricePeriodStart() {
        return pricePeriodStart;
    }

    public void setPricePeriodStart(Date pricePeriodStart) {
        this.pricePeriodStart = pricePeriodStart;
    }

    public Date getPricePeriodEnd() {
        return pricePeriodEnd;
    }

    public void setPricePeriodEnd(Date pricePeriodEnd) {
        this.pricePeriodEnd = pricePeriodEnd;
    }

    public String getSupOrder() {
        return supOrder;
    }

    public void setSupOrder(String supOrder) {
        this.supOrder = supOrder;
    }

    public String getOrder1() {
        return order1;
    }

    public void setOrder1(String order1) {
        this.order1 = order1;
    }

    public String getOrder2() {
        return order2;
    }

    public void setOrder2(String order2) {
        this.order2 = order2;
    }

    public String getOrder3() {
        return order3;
    }

    public void setOrder3(String order3) {
        this.order3 = order3;
    }

    public String getOrder4() {
        return order4;
    }

    public void setOrder4(String order4) {
        this.order4 = order4;
    }

    public String getOrder5() {
        return order5;
    }

    public void setOrder5(String order5) {
        this.order5 = order5;
    }

    public String getOrder6() {
        return order6;
    }

    public void setOrder6(String order6) {
        this.order6 = order6;
    }

    public String getOrder7() {
        return order7;
    }

    public void setOrder7(String order7) {
        this.order7 = order7;
    }

    public String getOrder8() {
        return order8;
    }

    public void setOrder8(String order8) {
        this.order8 = order8;
    }

    public String getOrder9() {
        return order9;
    }

    public void setOrder9(String order9) {
        this.order9 = order9;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }
}
