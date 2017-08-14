package com.msk.seller.bean;

import com.msk.core.entity.PdTncStd;

import java.util.List;

/**
 *
 * PD141107Bean.
 * @author gyh
 */
public class SL24110101 extends PdTncStd {
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

    /**
     * 获得pdTncStdList
     */
    public List<SL24110101> getPdTncStdList() {
        return pdTncStdList;
    }

    /**
     * 设置pdTncStdList
     */
    public void setPdTncStdList(List<SL24110101> pdTncStdList) {
        this.pdTncStdList = pdTncStdList;
    }

    /**
     * 获得serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 获得tncStdItemId
     */
    @Override
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * 设置tncStdItemId
     */
    @Override
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * 获得tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * 设置tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * 获得levelId
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * 设置levelId
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * 获得parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获得isCatalog
     */
    public String getIsCatalog() {
        return isCatalog;
    }

    /**
     * 设置isCatalog
     */
    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    /**
     * 获得remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获得isCheck
     */
    public String getIsCheck() {
        return isCheck;
    }

    /**
     * 设置isCheck
     */
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    private List<SL24110101> pdTncStdList;

}
