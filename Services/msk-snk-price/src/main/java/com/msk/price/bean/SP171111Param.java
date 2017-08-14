package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;
/**
 * Created by peng_hao
 */
public class SP171111Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /*产品编码*/
    private String pdCode;
    /*物流区编码*/
    private String lgcsCode;
    /*卖家编码*/
    private String slCode;
    /*卖家编码*/
    private String slId;


    /*申报周期*/
    private String pricePeriod;
    /*登录Id*/
    private String loginId;
    /*分销通道级别*/
    private String wayGradeCode;
    /*分销通道级别对应价格*/
    private String wayGradePrice;
    /*前画面标识（1，价盘一览，0，报价详细）*/
    private String pageType;

    public String getWayGradePrice() {
        return wayGradePrice;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }
    public void setWayGradePrice(String wayGradePrice) {
        this.wayGradePrice = wayGradePrice;
    }

    public String getWayGradeCode() {
        return wayGradeCode;
    }

    public void setWayGradeCode(String wayGradeCode) {
        this.wayGradeCode = wayGradeCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    /**
     * Getter method for property <tt>pageType</tt>.
     *
     * @return property value of pageType
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * Setter method for property <tt>pageType</tt>.
     *
     * @param pageType value to be assigned to property pageType
     */
    public void setPageType(String pageType) {
        this.pageType = pageType;
    }
}
