/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_research_category对应的ByResearchCategory</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByResearchCategory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** CATEGORY_ID */
    private Long categoryId;
    /** BUYER_ID */
    private String buyerId;
    /** INPUT_NUM */
    private Integer inputNum;
    /** INPUT_NAME */
    private String inputName;
    /** 产品类别 */
    private String classesCode;
    /** 产品加工类型 */
    private String machiningCode;
    /** 产品品种 */
    private String breedCode;
    /** 1:是 */
    private String isStandard;
    /** SALE_NAME */
    private String saleName;
    /** SCIENTIFIC_NAME */
    private String scientificName;
    /** POPULAR_NAME */
    private String popularName;
    /**
     * <p>默认构造函数</p>
     */
    public ByResearchCategory() {

    }

    /**
     * <p>CATEGORY_ID</p>
     *
     * @return the CATEGORY_ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * <p>CATEGORY_ID</p>
     *
     * @param categoryId CATEGORY_ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
     * <p>INPUT_NUM</p>
     *
     * @return the INPUT_NUM
     */
    public Integer getInputNum() {
        return inputNum;
    }

    /**
     * <p>INPUT_NUM</p>
     *
     * @param inputNum INPUT_NUM
     */
    public void setInputNum(Integer inputNum) {
        this.inputNum = inputNum;
    }

    /**
     * <p>INPUT_NAME</p>
     *
     * @return the INPUT_NAME
     */
    public String getInputName() {
        return inputName;
    }

    /**
     * <p>INPUT_NAME</p>
     *
     * @param inputName INPUT_NAME
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
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
     * <p>1:是</p>
     *
     * @return the 1:是
     */
    public String getIsStandard() {
        return isStandard;
    }

    /**
     * <p>1:是</p>
     *
     * @param isStandard 1:是
     */
    public void setIsStandard(String isStandard) {
        this.isStandard = isStandard;
    }

    /**
     * <p>SALE_NAME</p>
     *
     * @return the SALE_NAME
     */
    public String getSaleName() {
        return saleName;
    }

    /**
     * <p>SALE_NAME</p>
     *
     * @param saleName SALE_NAME
     */
    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    /**
     * <p>SCIENTIFIC_NAME</p>
     *
     * @return the SCIENTIFIC_NAME
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * <p>SCIENTIFIC_NAME</p>
     *
     * @param scientificName SCIENTIFIC_NAME
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * <p>POPULAR_NAME</p>
     *
     * @return the POPULAR_NAME
     */
    public String getPopularName() {
        return popularName;
    }

    /**
     * <p>POPULAR_NAME</p>
     *
     * @param popularName POPULAR_NAME
     */
    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

}
