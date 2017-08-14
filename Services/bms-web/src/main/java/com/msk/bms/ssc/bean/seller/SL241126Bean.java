package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.PdSftStd;

import java.util.List;

/**
 * SL131126Bean.
 *
 * @author gyh
 */
public class SL241126Bean extends PdSftStd {
    /** 产品安全标准项目ID */
    private String sftStdItemId;
    /** 产品安全标准项目名称 */
    private String sftStdItemName;
    /** 层次ID */
    private String levelId;
    /** 父节点ID */
    private String parentId;
    /** 是否为目录节点 */
    private String isCatalog;

    /** 1.原种种源标准,2.饲养标准,3,通用质量标准,4.安全标准,5.储存运输标准 */
    private Integer stdFlag;
    /** 卖家编码 */
    private String slCode;
    /** 卖家编码内的顺序号 */
    private Integer slPdId;
    /** 技术标准项目ID */
    private String tncStdItemId;
    /** 0:否，1:是 */
    private String agreeFlg;
    /**产品种源标准子节点*/
    private List<SL241126Bean> pdSftStds;

    /**
     * Getter method for property <tt>sftStdItemId</tt>.
     *
     * @return property value of sftStdItemId
     */
    @Override
    public String getSftStdItemId() {
        return sftStdItemId;
    }

    /**
     * Setter method for property <tt>sftStdItemId</tt>.
     *
     * @param sftStdItemId value to be assigned to property sftStdItemId
     */
    @Override
    public void setSftStdItemId(String sftStdItemId) {
        this.sftStdItemId = sftStdItemId;
    }

    /**
     * Getter method for property <tt>sftStdItemName</tt>.
     *
     * @return property value of sftStdItemName
     */
    public String getSftStdItemName() {
        return sftStdItemName;
    }

    /**
     * Setter method for property <tt>sftStdItemName</tt>.
     *
     * @param sftStdItemName value to be assigned to property sftStdItemName
     */
    public void setSftStdItemName(String sftStdItemName) {
        this.sftStdItemName = sftStdItemName;
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
     * Getter method for property <tt>stdFlag</tt>.
     *
     * @return property value of stdFlag
     */
    public Integer getStdFlag() {
        return stdFlag;
    }

    /**
     * Setter method for property <tt>stdFlag</tt>.
     *
     * @param stdFlag value to be assigned to property stdFlag
     */
    public void setStdFlag(Integer stdFlag) {
        this.stdFlag = stdFlag;
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
     * Getter method for property <tt>tncStdItemId</tt>.
     *
     * @return property value of tncStdItemId
     */
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * Setter method for property <tt>tncStdItemId</tt>.
     *
     * @param tncStdItemId value to be assigned to property tncStdItemId
     */
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
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
     * Getter method for property <tt>pdSftStds</tt>.
     *
     * @return property value of pdSftStds
     */
    public List<SL241126Bean> getPdSftStds() {
        return pdSftStds;
    }

    /**
     * Setter method for property <tt>pdSftStds</tt>.
     *
     * @param pdSftStds value to be assigned to property pdSftStds
     */
    public void setPdSftStds(List<SL241126Bean> pdSftStds) {
        this.pdSftStds = pdSftStds;
    }
}
