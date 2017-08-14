/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_qlt_std_cla对应的PdQltStdCla。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdQltStdCla extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 质量标准分类ID */
    private String qltStdClaId;
    /** 质量标准分类名称 */
    private String qltStdClaName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdQltStdCla() {

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
     * <p>质量标准分类名称。</p>
     *
     * @return the 质量标准分类名称
     */
    public String getQltStdClaName() {
        return qltStdClaName;
    }

    /**
     * <p>质量标准分类名称。</p>
     *
     * @param qltStdClaName 质量标准分类名称。
     */
    public void setQltStdClaName(String qltStdClaName) {
        this.qltStdClaName = qltStdClaName;
    }

}
