/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_o_wei_nor_info对应的BrOWeiNorInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrOWeiNorInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** WEIGHT_CODE */
    private String weightCode;
    /** WEIGHT_NAME */
    private String weightName;
    /** WEIGHT_VAL */
    private java.math.BigDecimal weightVal;
    /** NORMS_CODE */
    private String normsCode;
    /** NORMS_NAME */
    private String normsName;
    /**
     * <p>默认构造函数</p>
     */
    public BrOWeiNorInfo() {

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
     * <p>WEIGHT_CODE</p>
     *
     * @return the WEIGHT_CODE
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>WEIGHT_CODE</p>
     *
     * @param weightCode WEIGHT_CODE
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>WEIGHT_NAME</p>
     *
     * @return the WEIGHT_NAME
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * <p>WEIGHT_NAME</p>
     *
     * @param weightName WEIGHT_NAME
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    /**
     * <p>WEIGHT_VAL</p>
     *
     * @return the WEIGHT_VAL
     */
    public java.math.BigDecimal getWeightVal() {
        return weightVal;
    }

    /**
     * <p>WEIGHT_VAL</p>
     *
     * @param weightVal WEIGHT_VAL
     */
    public void setWeightVal(java.math.BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * <p>NORMS_CODE</p>
     *
     * @return the NORMS_CODE
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>NORMS_CODE</p>
     *
     * @param normsCode NORMS_CODE
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>NORMS_NAME</p>
     *
     * @return the NORMS_NAME
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * <p>NORMS_NAME</p>
     *
     * @param normsName NORMS_NAME
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

}
