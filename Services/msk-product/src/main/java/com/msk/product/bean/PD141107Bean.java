package com.msk.product.bean;

import com.msk.core.entity.PdTncStd;

import java.util.List;

/**
 * 
 * PD141107Bean.
 * @author gyh
 */
public class PD141107Bean extends PdTncStd {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /*技术标准项目ID*/
    private String tncStdItemId;
    /*技术标准项目名称*/
    private String tncStdItemName;
    /*层次ID*/
    private String levelId;
    /*父节点ID*/
    private String parentId;
    /*是否为目录节点*/
    private String isCatalog;
    /*备注*/
    private String remark;
    // 是否该品种技术标准项目
    private String isCheck;
    /*产品技术标准信息*/
    private List<PD141107Bean> pdTncStdList;

    /**
     * Get the tncStdItemId.
     *
     * @return tncStdItemId
     *
     * @author silent
     */
    public String getTncStdItemId() {
        return this.tncStdItemId;
    }

    /**
     * Set the tncStdItemId.
     *
     * @param tncStdItemId tncStdItemId
     *
     * @author silent
     */
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * Get the tncStdItemName.
     *
     * @return tncStdItemName
     *
     * @author silent
     */
    public String getTncStdItemName() {
        return this.tncStdItemName;
    }

    /**
     * Set the tncStdItemName.
     *
     * @param tncStdItemName tncStdItemName
     *
     * @author silent
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * Get the levelId.
     *
     * @return levelId
     *
     * @author silent
     */
    public String getLevelId() {
        return this.levelId;
    }

    /**
     * Set the levelId.
     *
     * @param levelId levelId
     *
     * @author silent
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * Get the parentId.
     *
     * @return parentId
     *
     * @author silent
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * Set the parentId.
     *
     * @param parentId parentId
     *
     * @author silent
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Get the isCatalog.
     *
     * @return isCatalog
     *
     * @author silent
     */
    public String getIsCatalog() {
        return this.isCatalog;
    }

    /**
     * Set the isCatalog.
     *
     * @param isCatalog isCatalog
     *
     * @author silent
     */
    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    /**
     * Get the remark.
     *
     * @return remark
     *
     * @author silent
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * Set the remark.
     *
     * @param remark remark
     *
     * @author silent
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Get the pdTncStdList.
     *
     * @return pdTncStdList
     *
     * @author silent
     */
    public List<PD141107Bean> getPdTncStdList() {
        return this.pdTncStdList;
    }

    /**
     * Set the pdTncStdList.
     *
     * @param pdTncStdList pdTncStdList
     *
     * @author silent
     */
    public void setPdTncStdList(List<PD141107Bean> pdTncStdList) {
        this.pdTncStdList = pdTncStdList;
    }

    /**
     * Get the isCheck.
     *
     * @return isCheck
     *
     * @author silent
     */
    public String getIsCheck() {
        return this.isCheck;
    }

    /**
     * Set the isCheck.
     *
     * @param isCheck isCheck
     *
     * @author silent
     */
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
