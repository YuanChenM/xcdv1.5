/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_o_buyer_pd_cla对应的BrOBuyerPdCla</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrOBuyerPdCla extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** CLASSES_CODE */
    private String classesCode;
    /** CLASSES_NAME */
    private String classesName;
    /** MACHINING_CODE */
    private String machiningCode;
    /** MACHINING_NAME */
    private String machiningName;
    /**
     * <p>默认构造函数</p>
     */
    public BrOBuyerPdCla() {

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
     * <p>CLASSES_CODE</p>
     *
     * @return the CLASSES_CODE
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>CLASSES_CODE</p>
     *
     * @param classesCode CLASSES_CODE
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>CLASSES_NAME</p>
     *
     * @return the CLASSES_NAME
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * <p>CLASSES_NAME</p>
     *
     * @param classesName CLASSES_NAME
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>MACHINING_CODE</p>
     *
     * @return the MACHINING_CODE
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>MACHINING_CODE</p>
     *
     * @param machiningCode MACHINING_CODE
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>MACHINING_NAME</p>
     *
     * @return the MACHINING_NAME
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>MACHINING_NAME</p>
     *
     * @param machiningName MACHINING_NAME
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

}
