/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_pd_tnc_std_not_agree对应的SlPdTncStdNotAgree。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlPdTncStdNotAgree extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 卖家编码 */
    private String slCode;
    /** (卖家编码)内顺序号 */
    private Integer slPdId;
    /** 产品标准ID */
    private Integer standardId;
    /** 技术标准项目ID */
    private String tncStdItemId;
    /** 技术标准项目层次 */
    private String levelId;
    /** 技术标准父节点ID */
    private String parentId;
    /** 技术标准项目名称 */
    private String tncStdItemName;
    /** 神农客技术标准项目值1 */
    private String tncStdItemValue1;
    /** 神农客技术标准项目值2 */
    private String tncStdItemValue2;
    /** 神农客技术标准项目值3 */
    private String tncStdItemValue3;
    /** 标准日期 */
    private java.util.Date stdDate;
    /** 0:否，1:是 */
    private String agreeFlg;
    /** 卖家技术标准 */
    private String slTncStdVal;
    /**
     * <p>默认构造函数。</p>
     */
    public SlPdTncStdNotAgree() {

    }

    /**
     * <p>卖家编码。</p>
     *
     * @return the 卖家编码
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>卖家编码。</p>
     *
     * @param slCode 卖家编码。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>(卖家编码)内顺序号。</p>
     *
     * @return the (卖家编码)内顺序号
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * <p>(卖家编码)内顺序号。</p>
     *
     * @param slPdId (卖家编码)内顺序号。
     */
    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @return the 产品标准ID
     */
    public Integer getStandardId() {
        return standardId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @param standardId 产品标准ID。
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @return the 技术标准项目ID
     */
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @param tncStdItemId 技术标准项目ID。
     */
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * <p>技术标准项目层次。</p>
     *
     * @return the 技术标准项目层次
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * <p>技术标准项目层次。</p>
     *
     * @param levelId 技术标准项目层次。
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * <p>技术标准父节点ID。</p>
     *
     * @return the 技术标准父节点ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * <p>技术标准父节点ID。</p>
     *
     * @param parentId 技术标准父节点ID。
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p>技术标准项目名称。</p>
     *
     * @return the 技术标准项目名称
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * <p>技术标准项目名称。</p>
     *
     * @param tncStdItemName 技术标准项目名称。
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * <p>神农客技术标准项目值1。</p>
     *
     * @return the 神农客技术标准项目值1
     */
    public String getTncStdItemValue1() {
        return tncStdItemValue1;
    }

    /**
     * <p>神农客技术标准项目值1。</p>
     *
     * @param tncStdItemValue1 神农客技术标准项目值1。
     */
    public void setTncStdItemValue1(String tncStdItemValue1) {
        this.tncStdItemValue1 = tncStdItemValue1;
    }

    /**
     * <p>神农客技术标准项目值2。</p>
     *
     * @return the 神农客技术标准项目值2
     */
    public String getTncStdItemValue2() {
        return tncStdItemValue2;
    }

    /**
     * <p>神农客技术标准项目值2。</p>
     *
     * @param tncStdItemValue2 神农客技术标准项目值2。
     */
    public void setTncStdItemValue2(String tncStdItemValue2) {
        this.tncStdItemValue2 = tncStdItemValue2;
    }

    /**
     * <p>神农客技术标准项目值3。</p>
     *
     * @return the 神农客技术标准项目值3
     */
    public String getTncStdItemValue3() {
        return tncStdItemValue3;
    }

    /**
     * <p>神农客技术标准项目值3。</p>
     *
     * @param tncStdItemValue3 神农客技术标准项目值3。
     */
    public void setTncStdItemValue3(String tncStdItemValue3) {
        this.tncStdItemValue3 = tncStdItemValue3;
    }

    /**
     * <p>标准日期。</p>
     *
     * @return the 标准日期
     */
    public java.util.Date getStdDate() {
        return stdDate;
    }

    /**
     * <p>标准日期。</p>
     *
     * @param stdDate 标准日期。
     */
    public void setStdDate(java.util.Date stdDate) {
        this.stdDate = stdDate;
    }

    /**
     * <p>0:否，1:是。</p>
     *
     * @return the 0:否，1:是
     */
    public String getAgreeFlg() {
        return agreeFlg;
    }

    /**
     * <p>0:否，1:是。</p>
     *
     * @param agreeFlg 0:否，1:是。
     */
    public void setAgreeFlg(String agreeFlg) {
        this.agreeFlg = agreeFlg;
    }

    /**
     * <p>卖家技术标准。</p>
     *
     * @return the 卖家技术标准
     */
    public String getSlTncStdVal() {
        return slTncStdVal;
    }

    /**
     * <p>卖家技术标准。</p>
     *
     * @param slTncStdVal 卖家技术标准。
     */
    public void setSlTncStdVal(String slTncStdVal) {
        this.slTncStdVal = slTncStdVal;
    }

}
