/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_grade对应的PdGrade。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdGrade extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品等级编码 */
    private java.lang.String gradeCode;
    /** 产品等级名称 */
    private java.lang.String gradeName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdGrade() {

    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param gradeCode 产品等级编码。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @return the 产品等级名称
     */
    public java.lang.String getGradeName() {
        return gradeName;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @param gradeName 产品等级名称。
     */
    public void setGradeName(java.lang.String gradeName) {
        this.gradeName = gradeName;
    }

}
