/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_alliance对应的SlBsAlliance。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsAlliance extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private java.lang.String slCode;
    /** 区划(6)+顺序码(4) */
    private java.lang.String slCodeAlliance;
    /** SORT */
    private java.util.Date sort;
    /** START_TIME */
    private java.util.Date startTime;
    /** END_TIME */
    private java.util.Date endTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsAlliance() {

    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public java.lang.String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(java.lang.String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public java.lang.String getSlCodeAlliance() {
        return slCodeAlliance;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCodeAlliance 区划(6)+顺序码(4)。
     */
    public void setSlCodeAlliance(java.lang.String slCodeAlliance) {
        this.slCodeAlliance = slCodeAlliance;
    }

    /**
     * <p>SORT。</p>
     *
     * @return the SORT
     */
    public java.util.Date getSort() {
        return sort;
    }

    /**
     * <p>SORT。</p>
     *
     * @param sort SORT。
     */
    public void setSort(java.util.Date sort) {
        this.sort = sort;
    }

    /**
     * <p>START_TIME。</p>
     *
     * @return the START_TIME
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * <p>START_TIME。</p>
     *
     * @param startTime START_TIME。
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * <p>END_TIME。</p>
     *
     * @return the END_TIME
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * <p>END_TIME。</p>
     *
     * @param endTime END_TIME。
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

}
