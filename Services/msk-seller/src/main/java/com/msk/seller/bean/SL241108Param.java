package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * PD141108 Controller Param
 * 
 * @author yuan_chen
 *
 */
public class SL241108Param extends BaseParam {

    private static final long serialVersionUID = 1L;

    /** 卖家编码 */
    private String slCode;
    /** 卖家产品ID */
    private String slPdId;
    /** 产品等级code */
    private String gradeCode;
    /** 区分 */
    private String flag;
    /** 不通过理由 */
    private String unExamineReason;
    /** 不同意理由 */
    private String unConfirmReason;

    /**
     * Get the slCode.
     *
     * @return slCode
     *
     * @author Marshall
     */
    public String getSlCode() {
        return this.slCode;
    }

    /**
     * Set the slCode.
     *
     * @param slCode slCode
     *
     * @author Marshall
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Get the slPdId.
     *
     * @return slPdId
     *
     * @author Marshall
     */
    public String getSlPdId() {
        return this.slPdId;
    }

    /**
     * Set the slPdId.
     *
     * @param slPdId slPdId
     *
     * @author Marshall
     */
    public void setSlPdId(String slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * Get the gradeCode.
     *
     * @return gradeCode
     *
     * @author Marshall
     */
    public String getGradeCode() {
        return this.gradeCode;
    }

    /**
     * Set the gradeCode.
     *
     * @param gradeCode gradeCode
     *
     * @author Marshall
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * Get the flag.
     *
     * @return flag
     *
     * @author Marshall
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * Set the flag.
     *
     * @param flag flag
     *
     * @author Marshall
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * Get the unExamineReason.
     *
     * @return unExamineReason
     *
     * @author Marshall
     */
    public String getUnExamineReason() {
        return this.unExamineReason;
    }

    /**
     * Set the unExamineReason.
     *
     * @param unExamineReason unExamineReason
     *
     * @author Marshall
     */
    public void setUnExamineReason(String unExamineReason) {
        this.unExamineReason = unExamineReason;
    }

    /**
     * Get the unConfirmReason.
     *
     * @return unConfirmReason
     *
     * @author Marshall
     */
    public String getUnConfirmReason() {
        return this.unConfirmReason;
    }

    /**
     * Set the unConfirmReason.
     *
     * @param unConfirmReason unConfirmReason
     *
     * @author Marshall
     */
    public void setUnConfirmReason(String unConfirmReason) {
        this.unConfirmReason = unConfirmReason;
    }

}
