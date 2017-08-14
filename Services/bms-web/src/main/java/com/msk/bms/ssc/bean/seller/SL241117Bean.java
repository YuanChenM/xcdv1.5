package com.msk.bms.ssc.bean.seller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.PdTncStd;

import java.util.Date;
import java.util.List;

/**
 * 
 * SL241117Bean.
 * @author gyh
 */
public class SL241117Bean extends PdTncStd {
    /** 技术标准项目ID */
    private String tncStdItemId;
    /** 技术标准项目名称 */
    private String tncStdItemName;
    /** 1:一级目录,2:二级目录,3:.. */
    private String levelId;
    /** 父节点ID */
    private String parentId;
    /** 0:是目录节点,1:不是目录节点 */
    private String isCatalog;

    /** SL_CODE */
    private String slCode;
    /** SL_PD_ID */
    private Integer slPdId;
    /** STD_ITEM_ID */
    private String stdItemId;
    /** 0:否，1:是 */
    private String agreeFlg;
    /** STD_VAL */
    private String stdVal;
    /**产品加工质量标准子节点*/
    private List<SL241117Bean> pdTncStds;
    //接口用
    private String slTncStdVal;
    private String tncStdItemValue1;
    private String tncStdItemValue2;
    private String tncStdItemValue3;
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
     * Getter method for property <tt>slTncStdVal</tt>.
     *
     * @return property value of slTncStdVal
     */
    public String getSlTncStdVal() {
        return slTncStdVal;
    }

    /**
     * Setter method for property <tt>slTncStdVal</tt>.
     *
     * @param slTncStdVal value to be assigned to property slTncStdVal
     */
    public void setSlTncStdVal(String slTncStdVal) {
        this.slTncStdVal = slTncStdVal;
    }

    /**
     * Getter method for property <tt>tncStdItemValue1</tt>.
     *
     * @return property value of tncStdItemValue1
     */
    public String getTncStdItemValue1() {
        return tncStdItemValue1;
    }

    /**
     * Setter method for property <tt>tncStdItemValue1</tt>.
     *
     * @param tncStdItemValue1 value to be assigned to property tncStdItemValue1
     */
    public void setTncStdItemValue1(String tncStdItemValue1) {
        this.tncStdItemValue1 = tncStdItemValue1;
    }

    /**
     * Getter method for property <tt>tncStdItemValue2</tt>.
     *
     * @return property value of tncStdItemValue2
     */
    public String getTncStdItemValue2() {
        return tncStdItemValue2;
    }

    /**
     * Setter method for property <tt>tncStdItemValue2</tt>.
     *
     * @param tncStdItemValue2 value to be assigned to property tncStdItemValue2
     */
    public void setTncStdItemValue2(String tncStdItemValue2) {
        this.tncStdItemValue2 = tncStdItemValue2;
    }

    /**
     * Getter method for property <tt>tncStdItemValue3</tt>.
     *
     * @return property value of tncStdItemValue3
     */
    public String getTncStdItemValue3() {
        return tncStdItemValue3;
    }

    /**
     * Setter method for property <tt>tncStdItemValue3</tt>.
     *
     * @param tncStdItemValue3 value to be assigned to property tncStdItemValue3
     */
    public void setTncStdItemValue3(String tncStdItemValue3) {
        this.tncStdItemValue3 = tncStdItemValue3;
    }

    /**
     * Getter method for property <tt>pdTncStds</tt>.
     *
     * @return property value of pdTncStds
     */
    public List<SL241117Bean> getPdTncStds() {
        return pdTncStds;
    }

    /**
     * Setter method for property <tt>pdTncStds</tt>.
     *
     * @param pdTncStds value to be assigned to property pdTncStds
     */
    public void setPdTncStds(List<SL241117Bean> pdTncStds) {
        this.pdTncStds = pdTncStds;
    }

    /**
     * Getter method for property <tt>tncStdItemId</tt>.
     *
     * @return property value of tncStdItemId
     */
    @Override
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * Setter method for property <tt>tncStdItemId</tt>.
     *
     * @param tncStdItemId value to be assigned to property tncStdItemId
     */
    @Override
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * Getter method for property <tt>tncStdItemName</tt>.
     *
     * @return property value of tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * Setter method for property <tt>tncStdItemName</tt>.
     *
     * @param tncStdItemName value to be assigned to property tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
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
