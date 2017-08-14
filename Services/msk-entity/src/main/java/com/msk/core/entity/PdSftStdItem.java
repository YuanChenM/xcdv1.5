/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_sft_std_item对应的PdSftStdItem。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdSftStdItem extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品安全标准项目ID */
    private java.lang.String sftStdItemId;
    /** 产品安全标准项目名称 */
    private java.lang.String sftStdItemName;
    /** 层次ID */
    private java.lang.String levelId;
    /** 父节点ID */
    private java.lang.String parentId;
    /** 是否为目录节点 */
    private java.lang.String isCatalog;
    /**
     * <p>默认构造函数。</p>
     */
    public PdSftStdItem() {

    }

    /**
     * <p>产品安全标准项目ID。</p>
     *
     * @return the 产品安全标准项目ID
     */
    public java.lang.String getSftStdItemId() {
        return sftStdItemId;
    }

    /**
     * <p>产品安全标准项目ID。</p>
     *
     * @param sftStdItemId 产品安全标准项目ID。
     */
    public void setSftStdItemId(java.lang.String sftStdItemId) {
        this.sftStdItemId = sftStdItemId;
    }

    /**
     * <p>产品安全标准项目名称。</p>
     *
     * @return the 产品安全标准项目名称
     */
    public java.lang.String getSftStdItemName() {
        return sftStdItemName;
    }

    /**
     * <p>产品安全标准项目名称。</p>
     *
     * @param sftStdItemName 产品安全标准项目名称。
     */
    public void setSftStdItemName(java.lang.String sftStdItemName) {
        this.sftStdItemName = sftStdItemName;
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
