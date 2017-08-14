/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_work对应的SlHouseWork。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseWork extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 培训ID */
    private Long workId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 工作开始时间 */
    private java.util.Date workStart;
    /** 工作结束时间 */
    private java.util.Date workEnd;
    /** 工作单位 */
    private String workComp;
    /** 工作职位 */
    private String workPosition;
    /** 工作岗位 */
    private String workStation;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseWork() {

    }

    /**
     * <p>培训ID。</p>
     *
     * @return the 培训ID
     */
    public Long getWorkId() {
        return workId;
    }

    /**
     * <p>培训ID。</p>
     *
     * @param workId 培训ID。
     */
    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>工作开始时间。</p>
     *
     * @return the 工作开始时间
     */
    public java.util.Date getWorkStart() {
        return workStart;
    }

    /**
     * <p>工作开始时间。</p>
     *
     * @param workStart 工作开始时间。
     */
    public void setWorkStart(java.util.Date workStart) {
        this.workStart = workStart;
    }

    /**
     * <p>工作结束时间。</p>
     *
     * @return the 工作结束时间
     */
    public java.util.Date getWorkEnd() {
        return workEnd;
    }

    /**
     * <p>工作结束时间。</p>
     *
     * @param workEnd 工作结束时间。
     */
    public void setWorkEnd(java.util.Date workEnd) {
        this.workEnd = workEnd;
    }

    /**
     * <p>工作单位。</p>
     *
     * @return the 工作单位
     */
    public String getWorkComp() {
        return workComp;
    }

    /**
     * <p>工作单位。</p>
     *
     * @param workComp 工作单位。
     */
    public void setWorkComp(String workComp) {
        this.workComp = workComp;
    }

    /**
     * <p>工作职位。</p>
     *
     * @return the 工作职位
     */
    public String getWorkPosition() {
        return workPosition;
    }

    /**
     * <p>工作职位。</p>
     *
     * @param workPosition 工作职位。
     */
    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    /**
     * <p>工作岗位。</p>
     *
     * @return the 工作岗位
     */
    public String getWorkStation() {
        return workStation;
    }

    /**
     * <p>工作岗位。</p>
     *
     * @param workStation 工作岗位。
     */
    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

}
