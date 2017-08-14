/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_pd_qlt_std_not_agree对应的SlPdQltStdNotAgree。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlPdQltStdNotAgree extends BaseEntity{
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
    /** 质量标准项目ID */
    private String qltStdItemId;
    /** 质量标准分类名称 */
    private String qltStdClaName;
    /** 质量标准子分类名称 */
    private String qltStdSubName;
    /** 质量标准项目名 */
    private String qltStdItemName;
    /** 神农客质量标准优良值 */
    private String qltStdVal1;
    /** 神农客质量标准合格值 */
    private String qltStdVal2;
    /** 神农客质量标准不合格值 */
    private String qltStdVal3;
    /** 标准日期 */
    private java.util.Date stdDate;
    /** 0:否，1:是 */
    private String agreeFlg;
    /** 卖家质量标准 */
    private String slQltStdVal;
    /**
     * <p>默认构造函数。</p>
     */
    public SlPdQltStdNotAgree() {

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
     * <p>质量标准项目ID。</p>
     *
     * @return the 质量标准项目ID
     */
    public String getQltStdItemId() {
        return qltStdItemId;
    }

    /**
     * <p>质量标准项目ID。</p>
     *
     * @param qltStdItemId 质量标准项目ID。
     */
    public void setQltStdItemId(String qltStdItemId) {
        this.qltStdItemId = qltStdItemId;
    }

    /**
     * <p>质量标准分类名称。</p>
     *
     * @return the 质量标准分类名称
     */
    public String getQltStdClaName() {
        return qltStdClaName;
    }

    /**
     * <p>质量标准分类名称。</p>
     *
     * @param qltStdClaName 质量标准分类名称。
     */
    public void setQltStdClaName(String qltStdClaName) {
        this.qltStdClaName = qltStdClaName;
    }

    /**
     * <p>质量标准子分类名称。</p>
     *
     * @return the 质量标准子分类名称
     */
    public String getQltStdSubName() {
        return qltStdSubName;
    }

    /**
     * <p>质量标准子分类名称。</p>
     *
     * @param qltStdSubName 质量标准子分类名称。
     */
    public void setQltStdSubName(String qltStdSubName) {
        this.qltStdSubName = qltStdSubName;
    }

    /**
     * <p>质量标准项目名。</p>
     *
     * @return the 质量标准项目名
     */
    public String getQltStdItemName() {
        return qltStdItemName;
    }

    /**
     * <p>质量标准项目名。</p>
     *
     * @param qltStdItemName 质量标准项目名。
     */
    public void setQltStdItemName(String qltStdItemName) {
        this.qltStdItemName = qltStdItemName;
    }

    /**
     * <p>神农客质量标准优良值。</p>
     *
     * @return the 神农客质量标准优良值
     */
    public String getQltStdVal1() {
        return qltStdVal1;
    }

    /**
     * <p>神农客质量标准优良值。</p>
     *
     * @param qltStdVal1 神农客质量标准优良值。
     */
    public void setQltStdVal1(String qltStdVal1) {
        this.qltStdVal1 = qltStdVal1;
    }

    /**
     * <p>神农客质量标准合格值。</p>
     *
     * @return the 神农客质量标准合格值
     */
    public String getQltStdVal2() {
        return qltStdVal2;
    }

    /**
     * <p>神农客质量标准合格值。</p>
     *
     * @param qltStdVal2 神农客质量标准合格值。
     */
    public void setQltStdVal2(String qltStdVal2) {
        this.qltStdVal2 = qltStdVal2;
    }

    /**
     * <p>神农客质量标准不合格值。</p>
     *
     * @return the 神农客质量标准不合格值
     */
    public String getQltStdVal3() {
        return qltStdVal3;
    }

    /**
     * <p>神农客质量标准不合格值。</p>
     *
     * @param qltStdVal3 神农客质量标准不合格值。
     */
    public void setQltStdVal3(String qltStdVal3) {
        this.qltStdVal3 = qltStdVal3;
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
     * <p>卖家质量标准。</p>
     *
     * @return the 卖家质量标准
     */
    public String getSlQltStdVal() {
        return slQltStdVal;
    }

    /**
     * <p>卖家质量标准。</p>
     *
     * @param slQltStdVal 卖家质量标准。
     */
    public void setSlQltStdVal(String slQltStdVal) {
        this.slQltStdVal = slQltStdVal;
    }

}
