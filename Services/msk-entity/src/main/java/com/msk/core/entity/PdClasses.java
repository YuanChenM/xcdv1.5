/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_classes对应的PdClasses。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdClasses extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 类别编码 */
    private java.lang.String classesCode;
    /** 类别名称 */
    private java.lang.String classesName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdClasses() {

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
     * <p>类别名称。</p>
     *
     * @return the 类别名称
     */
    public java.lang.String getClassesName() {
        return classesName;
    }

    /**
     * <p>类别名称。</p>
     *
     * @param classesName 类别名称。
     */
    public void setClassesName(java.lang.String classesName) {
        this.classesName = classesName;
    }

}
