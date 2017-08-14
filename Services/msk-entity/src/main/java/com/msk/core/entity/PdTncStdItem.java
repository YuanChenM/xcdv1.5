/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tnc_std_item对应的PdTncStdItem。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTncStdItem extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 技术标准项目ID */
    private java.lang.String tncStdItemId;
    /** 技术标准项目名称 */
    private java.lang.String tncStdItemName;
    /** 1:一级目录,2:二级目录,3:.. */
    private java.lang.String levelId;
    /** 父节点ID */
    private java.lang.String parentId;
    /** 0:是目录节点,1:不是目录节点 */
    private java.lang.String isCatalog;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTncStdItem() {

    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @return the 技术标准项目ID
     */
    public java.lang.String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @param tncStdItemId 技术标准项目ID。
     */
    public void setTncStdItemId(java.lang.String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * <p>技术标准项目名称。</p>
     *
     * @return the 技术标准项目名称
     */
    public java.lang.String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * <p>技术标准项目名称。</p>
     *
     * @param tncStdItemName 技术标准项目名称。
     */
    public void setTncStdItemName(java.lang.String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * <p>1:一级目录,2:二级目录,3:..。</p>
     *
     * @return the 1:一级目录,2:二级目录,3:..
     */
    public java.lang.String getLevelId() {
        return levelId;
    }

    /**
     * <p>1:一级目录,2:二级目录,3:..。</p>
     *
     * @param levelId 1:一级目录,2:二级目录,3:..。
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
     * <p>0:是目录节点,1:不是目录节点。</p>
     *
     * @return the 0:是目录节点,1:不是目录节点
     */
    public java.lang.String getIsCatalog() {
        return isCatalog;
    }

    /**
     * <p>0:是目录节点,1:不是目录节点。</p>
     *
     * @param isCatalog 0:是目录节点,1:不是目录节点。
     */
    public void setIsCatalog(java.lang.String isCatalog) {
        this.isCatalog = isCatalog;
    }

}
