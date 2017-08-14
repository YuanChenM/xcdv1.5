package com.msk.seller.bean;

import com.msk.core.entity.PdOrgStd;

import java.util.List;

/**
 *
 * SL131122Bean.
 * @author gyh
 */
public class SL241122Bean extends PdOrgStd {
    /** 种源标准项目ID */
    private String orgStdItemId;
    /** 种源标准项目名称 */
    private String orgStdItemName;
    /** 层次ID */
    private String levelId;
    /** 父节点ID */
    private String parentId;
    /** 0:是目录节点,1:不是目录节点 */
    private String isCatalog;

    /** 1.原种种源标准,2.饲养标准,3,通用质量标准,4.安全标准,5.储存运输标准 */
    private Integer stdFlag;
    /** 卖家编码 */
    private String slCode;
    /** 卖家编码内的顺序号 */
    private Integer slPdId;
    /** 技术标准项目ID */
    private String tncStdItemId;
    /** 0:否，1:等级1，2：等级2,3：等级3 */
    private String agreeFlg;
    /**产品种源标准子节点*/
    private List<SL241122Bean> pdOrgStds;

    /**
     * Getter method for property <tt>orgStdItemId</tt>.
     *
     * @return property value of orgStdItemId
     */
    @Override
    public String getOrgStdItemId() {
        return orgStdItemId;
    }

    /**
     * Setter method for property <tt>orgStdItemId</tt>.
     *
     * @param orgStdItemId value to be assigned to property orgStdItemId
     */
    @Override
    public void setOrgStdItemId(String orgStdItemId) {
        this.orgStdItemId = orgStdItemId;
    }

    /**
     * Getter method for property <tt>orgStdItemName</tt>.
     *
     * @return property value of orgStdItemName
     */
    public String getOrgStdItemName() {
        return orgStdItemName;
    }

    /**
     * Setter method for property <tt>orgStdItemName</tt>.
     *
     * @param orgStdItemName value to be assigned to property orgStdItemName
     */
    public void setOrgStdItemName(String orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
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
     * Getter method for property <tt>pdOrgStds</tt>.
     *
     * @return property value of pdOrgStds
     */
    public List<SL241122Bean> getPdOrgStds() {
        return pdOrgStds;
    }

    /**
     * Setter method for property <tt>pdOrgStds</tt>.
     *
     * @param pdOrgStds value to be assigned to property pdOrgStds
     */
    public void setPdOrgStds(List<SL241122Bean> pdOrgStds) {
        this.pdOrgStds = pdOrgStds;
    }
}
