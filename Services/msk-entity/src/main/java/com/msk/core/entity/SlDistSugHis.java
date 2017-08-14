/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_dist_sug_his对应的SlDistSugHis。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlDistSugHis extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 卖家意见履历ID */
    private Long slSugHisId;
    /** 卖家编码 */
    private String slCode;
    /** 章节ID */
    private Integer chapId;
    /** 类别 */
    private String chapClass;
    /** 0:未录入,1:同意,2:不同意 */
    private Integer agreeFlg;
    /** 卖家意见 */
    private String slSug;
    /**
     * <p>默认构造函数。</p>
     */
    public SlDistSugHis() {

    }

    /**
     * <p>卖家意见履历ID。</p>
     *
     * @return the 卖家意见履历ID
     */
    public Long getSlSugHisId() {
        return slSugHisId;
    }

    /**
     * <p>卖家意见履历ID。</p>
     *
     * @param slSugHisId 卖家意见履历ID。
     */
    public void setSlSugHisId(Long slSugHisId) {
        this.slSugHisId = slSugHisId;
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
     * <p>章节ID。</p>
     *
     * @return the 章节ID
     */
    public Integer getChapId() {
        return chapId;
    }

    /**
     * <p>章节ID。</p>
     *
     * @param chapId 章节ID。
     */
    public void setChapId(Integer chapId) {
        this.chapId = chapId;
    }

    /**
     * <p>类别。</p>
     *
     * @return the 类别
     */
    public String getChapClass() {
        return chapClass;
    }

    /**
     * <p>类别。</p>
     *
     * @param chapClass 类别。
     */
    public void setChapClass(String chapClass) {
        this.chapClass = chapClass;
    }

    /**
     * <p>0:未录入,1:同意,2:不同意。</p>
     *
     * @return the 0:未录入,1:同意,2:不同意
     */
    public Integer getAgreeFlg() {
        return agreeFlg;
    }

    /**
     * <p>0:未录入,1:同意,2:不同意。</p>
     *
     * @param agreeFlg 0:未录入,1:同意,2:不同意。
     */
    public void setAgreeFlg(Integer agreeFlg) {
        this.agreeFlg = agreeFlg;
    }

    /**
     * <p>卖家意见。</p>
     *
     * @return the 卖家意见
     */
    public String getSlSug() {
        return slSug;
    }

    /**
     * <p>卖家意见。</p>
     *
     * @param slSug 卖家意见。
     */
    public void setSlSug(String slSug) {
        this.slSug = slSug;
    }

}
