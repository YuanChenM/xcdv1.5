/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_pd_tnc_std_new对应的SlPdTncStdNew。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlPdTncStdNew extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 卖家编码 */
    private String slCode;
    /** 卖家产品ID */
    private Integer slPdId;
    /** 产品标准ID */
    private Integer standardId;
    /** 标准项目ID */
    private String stdItemId;
    /** 0:否，1:是 */
    private String agreeFlg;
    /** 卖家标准 */
    private String stdVal;
    /**
     * <p>默认构造函数。</p>
     */
    public SlPdTncStdNew() {

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
     * <p>卖家产品ID。</p>
     *
     * @return the 卖家产品ID
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * <p>卖家产品ID。</p>
     *
     * @param slPdId 卖家产品ID。
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
     * <p>标准项目ID。</p>
     *
     * @return the 标准项目ID
     */
    public String getStdItemId() {
        return stdItemId;
    }

    /**
     * <p>标准项目ID。</p>
     *
     * @param stdItemId 标准项目ID。
     */
    public void setStdItemId(String stdItemId) {
        this.stdItemId = stdItemId;
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
     * <p>卖家标准。</p>
     *
     * @return the 卖家标准
     */
    public String getStdVal() {
        return stdVal;
    }

    /**
     * <p>卖家标准。</p>
     *
     * @param stdVal 卖家标准。
     */
    public void setStdVal(String stdVal) {
        this.stdVal = stdVal;
    }

}
