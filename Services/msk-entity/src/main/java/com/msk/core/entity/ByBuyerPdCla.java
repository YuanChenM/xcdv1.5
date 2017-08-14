/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_pd_cla对应的ByBuyerPdCla</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerPdCla extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** 参考产品类别 */
    private String classCode;
    /** CLASS_NAME */
    private String className;
    /** 产品二级分类编码集 */
    private String machiningCode;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerPdCla() {

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
     * <p>参考产品类别</p>
     *
     * @return the 参考产品类别
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * <p>参考产品类别</p>
     *
     * @param classCode 参考产品类别
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * <p>CLASS_NAME</p>
     *
     * @return the CLASS_NAME
     */
    public String getClassName() {
        return className;
    }

    /**
     * <p>CLASS_NAME</p>
     *
     * @param className CLASS_NAME
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * <p>产品二级分类编码集</p>
     *
     * @return the 产品二级分类编码集
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品二级分类编码集</p>
     *
     * @param machiningCode 产品二级分类编码集
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

}
