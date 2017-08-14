package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by zhou_ling
 */
public class SP171113Bean extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**
     * 价盘ID
     */
    private String priceId;
    /**
     * 通道等级CODE
     */
    private String wayCode;
    /**
     * 通道等级名称
     */
    private String wayName;
    /**
     * 通道等级CODE
     */
    private String wayGateCode;
    /**
     * 通道等级名称
     */
    private String wayGateName;
    /**
     * 通道等级平衡系数
     */
    private BigDecimal wayGradePercent;

    /**
     * 价盘报价下限价
     */
    private double downPrice;

    /**
     * 价盘报价上限价
     */
    private double upPrice;

    /**
     * 半旬标准定价价盘
     */
    private BigDecimal price;

    /**
     * 前半旬标准定价价盘
     */
    private double frontPrice;

    /**
     * 价盘ID集合
     */
    private Integer[] priceIdArray;

    /**
     * 价盘报价集合
     */
    private BigDecimal[] wayGradePriceArray;

    /**
     * 半旬标准定价价盘
     */
    private String pdCode;

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    /**
     * Getter method for property <tt>wayCode</tt>.
     *
     * @return property value of wayCode
     */
    public String getWayCode() {
        return wayCode;
    }

    /**
     * Setter method for property <tt>wayCode</tt>.
     *
     * @param wayCode value to be assigned to property wayCode
     */
    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    /**
     * Getter method for property <tt>wayName</tt>.
     *
     * @return property value of wayName
     */
    public String getWayName() {
        return wayName;
    }

    /**
     * Setter method for property <tt>wayName</tt>.
     *
     * @param wayName value to be assigned to property wayName
     */
    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    /**
     * Getter method for property <tt>wayGateCode</tt>.
     *
     * @return property value of wayGateCode
     */
    public String getWayGateCode() {
        return wayGateCode;
    }

    /**
     * Setter method for property <tt>wayGateCode</tt>.
     *
     * @param wayGateCode value to be assigned to property wayGateCode
     */
    public void setWayGateCode(String wayGateCode) {
        this.wayGateCode = wayGateCode;
    }

    /**
     * Getter method for property <tt>wayGateName</tt>.
     *
     * @return property value of wayGateName
     */
    public String getWayGateName() {
        return wayGateName;
    }

    /**
     * Setter method for property <tt>wayGateName</tt>.
     *
     * @param wayGateName value to be assigned to property wayGateName
     */
    public void setWayGateName(String wayGateName) {
        this.wayGateName = wayGateName;
    }

    /**
     * Getter method for property <tt>wayGradePercent</tt>.
     *
     * @return property value of wayGradePercent
     */
    public BigDecimal getWayGradePercent() {
        return wayGradePercent;
    }

    /**
     * Setter method for property <tt>wayGradePercent</tt>.
     *
     * @param wayGradePercent value to be assigned to property wayGradePercent
     */
    public void setWayGradePercent(BigDecimal wayGradePercent) {
        this.wayGradePercent = wayGradePercent;
    }

    /**
     * Getter method for property <tt>downPrice</tt>.
     *
     * @return property value of downPrice
     */
    public double getDownPrice() {
        return downPrice;
    }

    /**
     * Setter method for property <tt>downPrice</tt>.
     *
     * @param downPrice value to be assigned to property downPrice
     */
    public void setDownPrice(double downPrice) {
        this.downPrice = downPrice;
    }

    /**
     * Getter method for property <tt>upPrice</tt>.
     *
     * @return property value of upPrice
     */
    public double getUpPrice() {
        return upPrice;
    }

    /**
     * Setter method for property <tt>upPrice</tt>.
     *
     * @param upPrice value to be assigned to property upPrice
     */
    public void setUpPrice(double upPrice) {
        this.upPrice = upPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter method for property <tt>frontPrice</tt>.
     *
     * @return property value of frontPrice
     */
    public double getFrontPrice() {
        return frontPrice;
    }

    /**
     * Setter method for property <tt>frontPrice</tt>.
     *
     * @param frontPrice value to be assigned to property frontPrice
     */
    public void setFrontPrice(double frontPrice) {
        this.frontPrice = frontPrice;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public Integer[] getPriceIdArray() {
        return priceIdArray;
    }

    public void setPriceIdArray(Integer[] priceIdArray) {
        this.priceIdArray = priceIdArray;
    }

    public BigDecimal[] getWayGradePriceArray() {
        return wayGradePriceArray;
    }

    public void setWayGradePriceArray(BigDecimal[] wayGradePriceArray) {
        this.wayGradePriceArray = wayGradePriceArray;
    }
}
