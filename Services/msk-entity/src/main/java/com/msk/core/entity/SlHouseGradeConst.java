/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_grade_const对应的SlHouseGradeConst</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseGradeConst extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 等级ID */
    private Long gradeId;
    /** 物流区 */
    private String lgcsAreaCode;
    /** 管家二级分类 */
    private String houseReclassifyCode;
    /** 管家等级 */
    private String gradeCode;
    /** 月销售额上限 */
    private Long saleMax;
    /** 月销售额下限 */
    private Long saleMin;
    /** 销售额单位(0:  万元) */
    private String saleUnit;
    /**
     * <p>默认构造函数</p>
     */
    public SlHouseGradeConst() {

    }

    /**
     * <p>等级ID</p>
     *
     * @return the 等级ID
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * <p>等级ID</p>
     *
     * @param gradeId 等级ID
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * <p>物流区</p>
     *
     * @return the 物流区
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区</p>
     *
     * @param lgcsAreaCode 物流区
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>管家二级分类</p>
     *
     * @return the 管家二级分类
     */
    public String getHouseReclassifyCode() {
        return houseReclassifyCode;
    }

    /**
     * <p>管家二级分类</p>
     *
     * @param houseReclassifyCode 管家二级分类
     */
    public void setHouseReclassifyCode(String houseReclassifyCode) {
        this.houseReclassifyCode = houseReclassifyCode;
    }

    /**
     * <p>管家等级</p>
     *
     * @return the 管家等级
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>管家等级</p>
     *
     * @param gradeCode 管家等级
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>月销售额上限</p>
     *
     * @return the 月销售额上限
     */
    public Long getSaleMax() {
        return saleMax;
    }

    /**
     * <p>月销售额上限</p>
     *
     * @param saleMax 月销售额上限
     */
    public void setSaleMax(Long saleMax) {
        this.saleMax = saleMax;
    }

    /**
     * <p>月销售额下限</p>
     *
     * @return the 月销售额下限
     */
    public Long getSaleMin() {
        return saleMin;
    }

    /**
     * <p>月销售额下限</p>
     *
     * @param saleMin 月销售额下限
     */
    public void setSaleMin(Long saleMin) {
        this.saleMin = saleMin;
    }

    /**
     * <p>销售额单位(0:  万元)</p>
     *
     * @return the 销售额单位(0:  万元)
     */
    public String getSaleUnit() {
        return saleUnit;
    }

    /**
     * <p>销售额单位(0:  万元)</p>
     *
     * @param saleUnit 销售额单位(0:  万元)
     */
    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

}
