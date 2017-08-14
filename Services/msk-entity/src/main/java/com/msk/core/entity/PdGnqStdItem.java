/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_gnq_std_item对应的PdGnqStdItem。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdGnqStdItem extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 通用质量标准项目ID */
    private java.lang.String gnqStdItemId;
    /** 通用质量标准项目名称 */
    private java.lang.String gnqStdItemName;
    /** 层次ID */
    private java.lang.String levelId;
    /** 父节点ID */
    private java.lang.String parentId;
    /** 是否为目录节点 */
    private java.lang.String isCatalog;
    /**
     * <p>默认构造函数。</p>
     */
    public PdGnqStdItem() {

    }

    /**
     * <p>通用质量标准项目ID。</p>
     *
     * @return the 通用质量标准项目ID
     */
    public java.lang.String getGnqStdItemId() {
        return gnqStdItemId;
    }

    /**
     * <p>通用质量标准项目ID。</p>
     *
     * @param gnqStdItemId 通用质量标准项目ID。
     */
    public void setGnqStdItemId(java.lang.String gnqStdItemId) {
        this.gnqStdItemId = gnqStdItemId;
    }

    /**
     * <p>通用质量标准项目名称。</p>
     *
     * @return the 通用质量标准项目名称
     */
    public java.lang.String getGnqStdItemName() {
        return gnqStdItemName;
    }

    /**
     * <p>通用质量标准项目名称。</p>
     *
     * @param gnqStdItemName 通用质量标准项目名称。
     */
    public void setGnqStdItemName(java.lang.String gnqStdItemName) {
        this.gnqStdItemName = gnqStdItemName;
    }

    /**
     * <p>层次ID。</p>
     *
     * @return the 层次ID
     */
    public java.lang.String getLevelId() {
        return levelId;
    }

    /**
     * <p>层次ID。</p>
     *
     * @param levelId 层次ID。
     */
    public void setLevelId(java.lang.String levelId) {
        this.levelId = levelId;
    }

    /**
     * <p>父节点ID。</p>
     *
     * @return the 父节点ID
     */
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * <p>父节点ID。</p>
     *
     * @param parentId 父节点ID。
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p>是否为目录节点。</p>
     *
     * @return the 是否为目录节点
     */
    public java.lang.String getIsCatalog() {
        return isCatalog;
    }

    /**
     * <p>是否为目录节点。</p>
     *
     * @param isCatalog 是否为目录节点。
     */
    public void setIsCatalog(java.lang.String isCatalog) {
        this.isCatalog = isCatalog;
    }

}
