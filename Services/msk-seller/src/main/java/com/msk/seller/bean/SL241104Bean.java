package com.msk.seller.bean;

/**
 * SL241104Bean
 *
 * @author yuan_chen
 * @version 1.0
 **/
public class SL241104Bean extends BaseSellerProductBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** 产品等级code */
    private String gradeCode;
    /** 产品等级名 */
    private String gradeName;
    /** 是否参与分销 */
    private String isDistribution;
    /** 质控人审核 */
    private String examineResult;

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
     * Get the gradeName.
     *
     * @return gradeName
     *
     * @author Marshall
     */
    public String getGradeName() {
        return this.gradeName;
    }

    /**
     * Set the gradeName.
     *
     * @param gradeName gradeName
     *
     * @author Marshall
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * Get the isDistribution.
     *
     * @return isDistribution
     *
     * @author Marshall
     */
    public String getIsDistribution() {
        return this.isDistribution;
    }

    /**
     * Set the isDistribution.
     *
     * @param isDistribution isDistribution
     *
     * @author Marshall
     */
    public void setIsDistribution(String isDistribution) {
        this.isDistribution = isDistribution;
    }

    /**
     * Get the checkResult.
     *
     * @return checkResult
     *
     * @author Marshall
     */
    public String getExamineResult() {
        return this.examineResult;
    }

    /**
     * Set the checkResult.
     *
     * @param checkResult checkResult
     *
     * @author Marshall
     */
    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }
}
