package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.PdMctStd;
import com.msk.core.entity.PdTncStd;

import java.util.Date;
import java.util.List;

/**
 * 
 * SL241117Bean.
 * @author gyh
 */
public class SL241118Bean extends PdMctStd {
    /** 加工技术标准项目ID */
    private String mctStdItemId;
    /** 加工技术标准项目名称 */
    private String mctStdItemName;
    /** 层次ID */
    private String levelId;
    /** 父节点ID */
    private String parentId;
    /** 是否为目录节点 */
    private String isCatalog;

    /** SL_CODE */
    private String slCode;
    /** 卖家编码内的顺序号 */
    private Integer slPdId;
    /** STD_ITEM_ID */
    private String stdItemId;
    /** 0:否，1:是 */
    private String agreeFlg;
    /** STD_VAL */
    private String stdVal;
    /**子节点*/
    private List<SL241118Bean> pdMctStds;
    //标准日期
    private Date stdDate;

    /**
     * Getter method for property <tt>stdDate</tt>.
     *
     * @return property value of stdDate
     */
    public Date getStdDate() {
        return stdDate;
    }

    /**
     * Setter method for property <tt>stdDate</tt>.
     *
     * @param stdDate value to be assigned to property stdDate
     */
    public void setStdDate(Date stdDate) {
        this.stdDate = stdDate;
    }

    /**
     * Getter method for property <tt>pdMctStds</tt>.
     *
     * @return property value of pdMctStds
     */
    public List<SL241118Bean> getPdMctStds() {
        return pdMctStds;
    }

    /**
     * Setter method for property <tt>pdMctStds</tt>.
     *
     * @param pdMctStds value to be assigned to property pdMctStds
     */
    public void setPdMctStds(List<SL241118Bean> pdMctStds) {
        this.pdMctStds = pdMctStds;
    }

    /**
     * Getter method for property <tt>mctStdItemId</tt>.
     *
     * @return property value of mctStdItemId
     */
    @Override
    public String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * Setter method for property <tt>mctStdItemId</tt>.
     *
     * @param mctStdItemId value to be assigned to property mctStdItemId
     */
    @Override
    public void setMctStdItemId(String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
    }

    /**
     * Getter method for property <tt>levelId</tt>.
     *
     * @return property value of levelId
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * Setter method for property <tt>levelId</tt>.
     *
     * @param levelId value to be assigned to property levelId
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * Getter method for property <tt>parentId</tt>.
     *
     * @return property value of parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     *
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter method for property <tt>isCatalog</tt>.
     *
     * @return property value of isCatalog
     */
    public String getIsCatalog() {
        return isCatalog;
    }

    /**
     * Setter method for property <tt>isCatalog</tt>.
     *
     * @param isCatalog value to be assigned to property isCatalog
     */
    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>slPdId</tt>.
     *
     * @return property value of slPdId
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * Setter method for property <tt>slPdId</tt>.
     *
     * @param slPdId value to be assigned to property slPdId
     */
    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * Getter method for property <tt>stdItemId</tt>.
     *
     * @return property value of stdItemId
     */
    public String getStdItemId() {
        return stdItemId;
    }

    /**
     * Setter method for property <tt>stdItemId</tt>.
     *
     * @param stdItemId value to be assigned to property stdItemId
     */
    public void setStdItemId(String stdItemId) {
        this.stdItemId = stdItemId;
    }

    /**
     * Getter method for property <tt>agreeFlg</tt>.
     *
     * @return property value of agreeFlg
     */
    public String getAgreeFlg() {
        return agreeFlg;
    }

    /**
     * Setter method for property <tt>agreeFlg</tt>.
     *
     * @param agreeFlg value to be assigned to property agreeFlg
     */
    public void setAgreeFlg(String agreeFlg) {
        this.agreeFlg = agreeFlg;
    }

    /**
     * Getter method for property <tt>stdVal</tt>.
     *
     * @return property value of stdVal
     */
    public String getStdVal() {
        return stdVal;
    }

    /**
     * Setter method for property <tt>stdVal</tt>.
     *
     * @param stdVal value to be assigned to property stdVal
     */
    public void setStdVal(String stdVal) {
        this.stdVal = stdVal;
    }
}
