package com.msk.product.bean;

import com.msk.core.entity.PdTncStd;

import java.util.List;

/**
 * 
 * PD141107Bean.
 * @author gyh
 */
public class PD141149Bean extends PdTncStd {
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
    private List<PD141149Bean> pdTncStdList;

    /*市场需求标准*/
    private List<PDtncMarkeyBean> pdTncMarkeyList;
    /*供应商习惯性标准*/
    private List<PDtncProviderBean> pdTncProviderList;

    private List<PDtncMarkeyBean> pdTncMarkeyNoList; //市场禁止日

    private List<PDtncProviderBean> pdTncProviderNoList; //供应商禁止日


    /**
     * Getter method for property <tt>pdTncMarkeyNoList</tt>.
     *
     * @return property value of pdTncMarkeyNoList
     */
    public List<PDtncMarkeyBean> getPdTncMarkeyNoList() {
        return pdTncMarkeyNoList;
    }

    /**
     * Setter method for property <tt>pdTncMarkeyNoList</tt>.
     *
     * @param pdTncMarkeyNoList value to be assigned to property pdTncMarkeyNoList
     */
    public void setPdTncMarkeyNoList(List<PDtncMarkeyBean> pdTncMarkeyNoList) {
        this.pdTncMarkeyNoList = pdTncMarkeyNoList;
    }

    /**
     * Getter method for property <tt>pdTncProviderNoList</tt>.
     *
     * @return property value of pdTncProviderNoList
     */
    public List<PDtncProviderBean> getPdTncProviderNoList() {
        return pdTncProviderNoList;
    }

    /**
     * Setter method for property <tt>pdTncProviderNoList</tt>.
     *
     * @param pdTncProviderNoList value to be assigned to property pdTncProviderNoList
     */
    public void setPdTncProviderNoList(List<PDtncProviderBean> pdTncProviderNoList) {
        this.pdTncProviderNoList = pdTncProviderNoList;
    }

    /**
     * Getter method for property <tt>pdTncMarkeyList</tt>.
     *
     * @return property value of pdTncMarkeyList
     */
    public List<PDtncMarkeyBean> getPdTncMarkeyList() {
        return pdTncMarkeyList;
    }

    /**
     * Setter method for property <tt>pdTncMarkeyList</tt>.
     *
     * @param pdTncMarkeyList value to be assigned to property pdTncMarkeyList
     */
    public void setPdTncMarkeyList(List<PDtncMarkeyBean> pdTncMarkeyList) {
        this.pdTncMarkeyList = pdTncMarkeyList;
    }

    /**
     * Getter method for property <tt>pdTncProviderList</tt>.
     *
     * @return property value of pdTncProviderList
     */
    public List<PDtncProviderBean> getPdTncProviderList() {
        return pdTncProviderList;
    }

    /**
     * Setter method for property <tt>pdTncProviderList</tt>.
     *
     * @param pdTncProviderList value to be assigned to property pdTncProviderList
     */
    public void setPdTncProviderList(List<PDtncProviderBean> pdTncProviderList) {
        this.pdTncProviderList = pdTncProviderList;
    }

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
    public List<PD141149Bean> getPdTncStdList() {
        return this.pdTncStdList;
    }

    /**
     * Set the pdTncStdList.
     *
     * @param pdTncStdList pdTncStdList
     *
     * @author silent
     */
    public void setPdTncStdList(List<PD141149Bean> pdTncStdList) {
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
