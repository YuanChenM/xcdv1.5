package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;

/**
 * 具体质量指标
 * IPD141106RsParam.
 *
 * @author xhy
 */
@JsonIgnoreProperties(
    value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime", "standardId" })
public class IPD141106ItemRsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 产品标准ID */
    private Integer standardId;
    /** 质量标准项目ID */
    private String qltStdItemId;
    /** 标准值名称 */
    private String qltStdItemName;
    /** 质量标准优良值 */
    private String qltStdExcVal;
    /** 质量标准合格值 */
    private String qltStdSuitVal;
    /** 质量标准不合格值 */
    private String qltStdUnqualVal;
    /** 标准值单位 */
    private String qltStdValUnit;
  

    /**
     * Get the standardId.
     *
     * @return standardId
     *
     * @author xhy
     */
    public Integer getStandardId() {
        return this.standardId;
    }

    /**
     * Set the standardId.
     *
     * @param standardId standardId
     *
     * @author xhy
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * Get the qltStdItemId.
     *
     * @return qltStdItemId
     *
     * @author xhy
     */
    public String getQltStdItemId() {
        return this.qltStdItemId;
    }

    /**
     * Set the qltStdItemId.
     *
     * @param qltStdItemId qltStdItemId
     *
     * @author xhy
     */
    public void setQltStdItemId(String qltStdItemId) {
        this.qltStdItemId = qltStdItemId;
    }

    /**
     * Get the qltStdExcVal.
     *
     * @return qltStdExcVal
     *
     * @author xhy
     */
    public String getQltStdExcVal() {
        return this.qltStdExcVal;
    }

    /**
     * Set the qltStdExcVal.
     *
     * @param qltStdExcVal qltStdExcVal
     *
     * @author xhy
     */
    public void setQltStdExcVal(String qltStdExcVal) {
        this.qltStdExcVal = qltStdExcVal;
    }

    /**
     * Get the qltStdSuitVal.
     *
     * @return qltStdSuitVal
     *
     * @author xhy
     */
    public String getQltStdSuitVal() {
        return this.qltStdSuitVal;
    }

    /**
     * Set the qltStdSuitVal.
     *
     * @param qltStdSuitVal qltStdSuitVal
     *
     * @author xhy
     */
    public void setQltStdSuitVal(String qltStdSuitVal) {
        this.qltStdSuitVal = qltStdSuitVal;
    }

    /**
     * Get the qltStdUnqualVal.
     *
     * @return qltStdUnqualVal
     *
     * @author xhy
     */
    public String getQltStdUnqualVal() {
        return this.qltStdUnqualVal;
    }

    /**
     * Set the qltStdUnqualVal.
     *
     * @param qltStdUnqualVal qltStdUnqualVal
     *
     * @author xhy
     */
    public void setQltStdUnqualVal(String qltStdUnqualVal) {
        this.qltStdUnqualVal = qltStdUnqualVal;
    }

    /**
     * Get the qltStdValUnit.
     *
     * @return qltStdValUnit
     *
     * @author xhy
     */
    public String getQltStdValUnit() {
        return this.qltStdValUnit;
    }

    /**
     * Set the qltStdValUnit.
     *
     * @param qltStdValUnit qltStdValUnit
     *
     * @author xhy
     */
    public void setQltStdValUnit(String qltStdValUnit) {
        this.qltStdValUnit = qltStdValUnit;
    }

    /**
     * Get the qltStdItemName.
     *
     * @return qltStdItemName
     *
     * @author Administrator
     */
    public String getQltStdItemName() {
        return this.qltStdItemName;
    }

    /**
     * Set the qltStdItemName.
     *
     * @param qltStdItemName qltStdItemName
     *
     * @author Administrator
     */
    public void setQltStdItemName(String qltStdItemName) {
        this.qltStdItemName = qltStdItemName;
    }

}