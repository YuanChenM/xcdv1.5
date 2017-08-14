/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tsp_std_item对应的PdTspStdItem。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTspStdItem extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 储存运输标准项目ID */
    private java.lang.String tspStdItemId;
    /** 储存运输标准项目名称 */
    private java.lang.String tspStdItemName;
    /** 层次ID */
    private java.lang.String levelId;
    /** 父节点ID */
    private java.lang.String parentId;
    /** 是否为目录节点 */
    private java.lang.String isCatalog;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTspStdItem() {

    }

    /**
     * <p>储存运输标准项目ID。</p>
     *
     * @return the 储存运输标准项目ID
     */
    public java.lang.String getTspStdItemId() {
        return tspStdItemId;
    }

    /**
     * <p>储存运输标准项目ID。</p>
     *
     * @param tspStdItemId 储存运输标准项目ID。
     */
    public void setTspStdItemId(java.lang.String tspStdItemId) {
        this.tspStdItemId = tspStdItemId;
    }

    /**
     * <p>储存运输标准项目名称。</p>
     *
     * @return the 储存运输标准项目名称
     */
    public java.lang.String getTspStdItemName() {
        return tspStdItemName;
    }

    /**
     * <p>储存运输标准项目名称。</p>
     *
     * @param tspStdItemName 储存运输标准项目名称。
     */
    public void setTspStdItemName(java.lang.String tspStdItemName) {
        this.tspStdItemName = tspStdItemName;
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
