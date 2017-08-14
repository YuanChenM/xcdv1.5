/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_research_std_fed对应的ByResearchStdFed</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByResearchStdFed extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** 产品类别 */
    private String classesCode;
    /** 产品加工类型 */
    private String machiningCode;
    /** 产品品种 */
    private String breedCode;
    /** STANDARD_ID */
    private Long standardId;
    /** FED_STD_ITEM_ID */
    private String fedStdItemId;
    /** FED_STD_ITEM_NAME */
    private String fedStdItemName;
    /** GOOD_VAL */
    private String goodVal;
    /** 1:是 */
    private String goodValAgree;
    /** NORMAL_VAL */
    private String normalVal;
    /** 1:是 */
    private String normalValAgree;
    /** BAD_VAL */
    private String badVal;
    /** 1:是 */
    private String badValAgree;
    /** DESCRIPTION */
    private String description;
    /** REMARK */
    private String remark;
    /** 1:是 */
    private String isResearch;
    /**
     * <p>默认构造函数</p>
     */
    public ByResearchStdFed() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>产品类别</p>
     *
     * @return the 产品类别
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别</p>
     *
     * @param classesCode 产品类别
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品加工类型</p>
     *
     * @return the 产品加工类型
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品加工类型</p>
     *
     * @param machiningCode 产品加工类型
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品品种</p>
     *
     * @return the 产品品种
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种</p>
     *
     * @param breedCode 产品品种
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>STANDARD_ID</p>
     *
     * @return the STANDARD_ID
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * <p>STANDARD_ID</p>
     *
     * @param standardId STANDARD_ID
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /**
     * <p>FED_STD_ITEM_ID</p>
     *
     * @return the FED_STD_ITEM_ID
     */
    public String getFedStdItemId() {
        return fedStdItemId;
    }

    /**
     * <p>FED_STD_ITEM_ID</p>
     *
     * @param fedStdItemId FED_STD_ITEM_ID
     */
    public void setFedStdItemId(String fedStdItemId) {
        this.fedStdItemId = fedStdItemId;
    }

    /**
     * <p>FED_STD_ITEM_NAME</p>
     *
     * @return the FED_STD_ITEM_NAME
     */
    public String getFedStdItemName() {
        return fedStdItemName;
    }

    /**
     * <p>FED_STD_ITEM_NAME</p>
     *
     * @param fedStdItemName FED_STD_ITEM_NAME
     */
    public void setFedStdItemName(String fedStdItemName) {
        this.fedStdItemName = fedStdItemName;
    }

    /**
     * <p>GOOD_VAL</p>
     *
     * @return the GOOD_VAL
     */
    public String getGoodVal() {
        return goodVal;
    }

    /**
     * <p>GOOD_VAL</p>
     *
     * @param goodVal GOOD_VAL
     */
    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    /**
     * <p>1:是</p>
     *
     * @return the 1:是
     */
    public String getGoodValAgree() {
        return goodValAgree;
    }

    /**
     * <p>1:是</p>
     *
     * @param goodValAgree 1:是
     */
    public void setGoodValAgree(String goodValAgree) {
        this.goodValAgree = goodValAgree;
    }

    /**
     * <p>NORMAL_VAL</p>
     *
     * @return the NORMAL_VAL
     */
    public String getNormalVal() {
        return normalVal;
    }

    /**
     * <p>NORMAL_VAL</p>
     *
     * @param normalVal NORMAL_VAL
     */
    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    /**
     * <p>1:是</p>
     *
     * @return the 1:是
     */
    public String getNormalValAgree() {
        return normalValAgree;
    }

    /**
     * <p>1:是</p>
     *
     * @param normalValAgree 1:是
     */
    public void setNormalValAgree(String normalValAgree) {
        this.normalValAgree = normalValAgree;
    }

    /**
     * <p>BAD_VAL</p>
     *
     * @return the BAD_VAL
     */
    public String getBadVal() {
        return badVal;
    }

    /**
     * <p>BAD_VAL</p>
     *
     * @param badVal BAD_VAL
     */
    public void setBadVal(String badVal) {
        this.badVal = badVal;
    }

    /**
     * <p>1:是</p>
     *
     * @return the 1:是
     */
    public String getBadValAgree() {
        return badValAgree;
    }

    /**
     * <p>1:是</p>
     *
     * @param badValAgree 1:是
     */
    public void setBadValAgree(String badValAgree) {
        this.badValAgree = badValAgree;
    }

    /**
     * <p>DESCRIPTION</p>
     *
     * @return the DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>DESCRIPTION</p>
     *
     * @param description DESCRIPTION
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>REMARK</p>
     *
     * @return the REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>REMARK</p>
     *
     * @param remark REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>1:是</p>
     *
     * @return the 1:是
     */
    public String getIsResearch() {
        return isResearch;
    }

    /**
     * <p>1:是</p>
     *
     * @param isResearch 1:是
     */
    public void setIsResearch(String isResearch) {
        this.isResearch = isResearch;
    }

}
