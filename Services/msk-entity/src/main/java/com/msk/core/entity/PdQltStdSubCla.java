/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_qlt_std_sub_cla对应的PdQltStdSubCla。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdQltStdSubCla extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 质量标准分类ID */
    private String qltStdClaId;
    /** 质量标准子分类ID */
    private String qltStdSubId;
    /** 质量标准子分类名称 */
    private String qltStdSubName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdQltStdSubCla() {

    }

    /**
     * <p>质量标准分类ID。</p>
     *
     * @return the 质量标准分类ID
     */
    public String getQltStdClaId() {
        return qltStdClaId;
    }

    /**
     * <p>质量标准分类ID。</p>
     *
     * @param qltStdClaId 质量标准分类ID。
     */
    public void setQltStdClaId(String qltStdClaId) {
        this.qltStdClaId = qltStdClaId;
    }

    /**
     * <p>质量标准子分类ID。</p>
     *
     * @return the 质量标准子分类ID
     */
    public String getQltStdSubId() {
        return qltStdSubId;
    }

    /**
     * <p>质量标准子分类ID。</p>
     *
     * @param qltStdSubId 质量标准子分类ID。
     */
    public void setQltStdSubId(String qltStdSubId) {
        this.qltStdSubId = qltStdSubId;
    }

    /**
     * <p>质量标准子分类名称。</p>
     *
     * @return the 质量标准子分类名称
     */
    public String getQltStdSubName() {
        return qltStdSubName;
    }

    /**
     * <p>质量标准子分类名称。</p>
     *
     * @param qltStdSubName 质量标准子分类名称。
     */
    public void setQltStdSubName(String qltStdSubName) {
        this.qltStdSubName = qltStdSubName;
    }

}
