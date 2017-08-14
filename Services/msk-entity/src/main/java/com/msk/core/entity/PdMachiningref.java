/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_machiningref对应的PdMachiningref。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdMachiningref extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品加工程度对照ID */
    private java.lang.Long machiningrefId;
    /** 类别编码 */
    private java.lang.String classesCode;
    /** 产品加工程度编码 */
    private java.lang.String machiningCode;
    /** 产品加工程度名称 */
    private java.lang.String machiningName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdMachiningref() {

    }

    /**
     * <p>产品加工程度对照ID。</p>
     *
     * @return the 产品加工程度对照ID
     */
    public java.lang.Long getMachiningrefId() {
        return machiningrefId;
    }

    /**
     * <p>产品加工程度对照ID。</p>
     *
     * @param machiningrefId 产品加工程度对照ID。
     */
    public void setMachiningrefId(java.lang.Long machiningrefId) {
        this.machiningrefId = machiningrefId;
    }

    /**
     * <p>类别编码。</p>
     *
     * @return the 类别编码
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @param classesCode 类别编码。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品加工程度编码。</p>
     *
     * @return the 产品加工程度编码
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品加工程度编码。</p>
     *
     * @param machiningCode 产品加工程度编码。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品加工程度名称。</p>
     *
     * @return the 产品加工程度名称
     */
    public java.lang.String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>产品加工程度名称。</p>
     *
     * @param machiningName 产品加工程度名称。
     */
    public void setMachiningName(java.lang.String machiningName) {
        this.machiningName = machiningName;
    }

}
