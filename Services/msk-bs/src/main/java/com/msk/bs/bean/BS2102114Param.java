package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.Date;

/**
 * Created by ren_qiang on 2016/8/22.
 */
public class BS2102114Param extends BaseParam {
    private static final long serialVersionUID = 1L;
    /** 分类等级ID */
    private Long gradeId;
    /** 买手ID */
    private String slCode;
    /** 管家ID */
    private String houseCode;
    /** 管家一级分类 */
    private String houseCategoryCode;
    /** 管家二级分类 */
    private String houseReclassifyCode;
    /** 星级 */
    private java.math.BigDecimal starCode;
    /** 对应年月YYYYMM */
    private String validYearMonth;
    /** 有效截止日期 */
    private java.util.Date endTime;
    /** 展期 */
    private java.util.Date extendTime;
    /** 1:正式分类冻品管家，0:预备,9:注销 */
    private String status;
    /** 备注 */
    private String remark;

    /**
     * <p>分类等级ID。</p>
     *
     * @return the 分类等级ID
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * <p>分类等级ID。</p>
     *
     * @param gradeId 分类等级ID。
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * <p>买手ID。</p>
     *
     * @return the 买手ID
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>买手ID。</p>
     *
     * @param slCode 买手ID。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>管家ID。</p>
     *
     * @return the 管家ID
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>管家ID。</p>
     *
     * @param houseCode 管家ID。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>管家一级分类。</p>
     *
     * @return the 管家一级分类
     */
    public String getHouseCategoryCode() {
        return houseCategoryCode;
    }

    /**
     * <p>管家一级分类。</p>
     *
     * @param houseCategoryCode 管家一级分类。
     */
    public void setHouseCategoryCode(String houseCategoryCode) {
        this.houseCategoryCode = houseCategoryCode;
    }

    /**
     * <p>管家二级分类。</p>
     *
     * @return the 管家二级分类
     */
    public String getHouseReclassifyCode() {
        return houseReclassifyCode;
    }

    /**
     * <p>管家二级分类。</p>
     *
     * @param houseReclassifyCode 管家二级分类。
     */
    public void setHouseReclassifyCode(String houseReclassifyCode) {
        this.houseReclassifyCode = houseReclassifyCode;
    }

    /**
     * <p>星级。</p>
     *
     * @return the 星级
     */
    public java.math.BigDecimal getStarCode() {
        return starCode;
    }

    /**
     * <p>星级。</p>
     *
     * @param starCode 星级。
     */
    public void setStarCode(java.math.BigDecimal starCode) {
        this.starCode = starCode;
    }

    /**
     * <p>对应年月YYYYMM。</p>
     *
     * @return the 对应年月YYYYMM
     */
    public String getValidYearMonth() {
        return validYearMonth;
    }

    /**
     * <p>对应年月YYYYMM。</p>
     *
     * @param validYearMonth 对应年月YYYYMM。
     */
    public void setValidYearMonth(String validYearMonth) {
        this.validYearMonth = validYearMonth;
    }

    /**
     * <p>有效截止日期。</p>
     *
     * @return the 有效截止日期
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * <p>有效截止日期。</p>
     *
     * @param endTime 有效截止日期。
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * <p>展期。</p>
     *
     * @return the 展期
     */
    public java.util.Date getExtendTime() {
        return extendTime;
    }

    /**
     * <p>展期。</p>
     *
     * @param extendTime 展期。
     */
    public void setExtendTime(java.util.Date extendTime) {
        this.extendTime = extendTime;
    }

    /**
     * <p>1:正式分类冻品管家，0:预备,9:注销。</p>
     *
     * @return the 1:正式分类冻品管家，0:预备,9:注销
     */
    public String getStatus() {
        return status;
    }

    /**
     * <p>1:正式分类冻品管家，0:预备,9:注销。</p>
     *
     * @param status 1:正式分类冻品管家，0:预备,9:注销。
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


}
