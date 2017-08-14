/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_hk_info对应的BrHkInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrHkInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** HK_GROUP_ID */
    private Long hkGroupId;
    /** 冻品管家主键 */
    private String houseCode;
    /** 买手店主键 */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCodeDis;
    /** 也可以用于登录 */
    private String houseTel;
    /** HOUSE_SHOW_NAME */
    private String houseShowName;
    /** HOUSE_CONTACT */
    private String houseContact;
    /** 插入时间，管家加入管家组的时间 */
    private java.util.Date joinTime;
    /** 删除时间，管家离开管家组的时间 */
    private java.util.Date leaveTime;
    /**
     * <p>默认构造函数</p>
     */
    public BrHkInfo() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>HK_GROUP_ID</p>
     *
     * @return the HK_GROUP_ID
     */
    public Long getHkGroupId() {
        return hkGroupId;
    }

    /**
     * <p>HK_GROUP_ID</p>
     *
     * @param hkGroupId HK_GROUP_ID
     */
    public void setHkGroupId(Long hkGroupId) {
        this.hkGroupId = hkGroupId;
    }

    /**
     * <p>冻品管家主键</p>
     *
     * @return the 冻品管家主键
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>冻品管家主键</p>
     *
     * @param houseCode 冻品管家主键
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>买手店主键</p>
     *
     * @return the 买手店主键
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>买手店主键</p>
     *
     * @param slCode 买手店主键
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCodeDis() {
        return houseCodeDis;
    }

    /**
     * <p>用于登录的卖家账号</p>
     *
     * @param houseCodeDis 用于登录的卖家账号
     */
    public void setHouseCodeDis(String houseCodeDis) {
        this.houseCodeDis = houseCodeDis;
    }

    /**
     * <p>也可以用于登录</p>
     *
     * @return the 也可以用于登录
     */
    public String getHouseTel() {
        return houseTel;
    }

    /**
     * <p>也可以用于登录</p>
     *
     * @param houseTel 也可以用于登录
     */
    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    /**
     * <p>HOUSE_SHOW_NAME</p>
     *
     * @return the HOUSE_SHOW_NAME
     */
    public String getHouseShowName() {
        return houseShowName;
    }

    /**
     * <p>HOUSE_SHOW_NAME</p>
     *
     * @param houseShowName HOUSE_SHOW_NAME
     */
    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    /**
     * <p>HOUSE_CONTACT</p>
     *
     * @return the HOUSE_CONTACT
     */
    public String getHouseContact() {
        return houseContact;
    }

    /**
     * <p>HOUSE_CONTACT</p>
     *
     * @param houseContact HOUSE_CONTACT
     */
    public void setHouseContact(String houseContact) {
        this.houseContact = houseContact;
    }

    /**
     * <p>插入时间，管家加入管家组的时间</p>
     *
     * @return the 插入时间，管家加入管家组的时间
     */
    public java.util.Date getJoinTime() {
        return joinTime;
    }

    /**
     * <p>插入时间，管家加入管家组的时间</p>
     *
     * @param joinTime 插入时间，管家加入管家组的时间
     */
    public void setJoinTime(java.util.Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * <p>删除时间，管家离开管家组的时间</p>
     *
     * @return the 删除时间，管家离开管家组的时间
     */
    public java.util.Date getLeaveTime() {
        return leaveTime;
    }

    /**
     * <p>删除时间，管家离开管家组的时间</p>
     *
     * @param leaveTime 删除时间，管家离开管家组的时间
     */
    public void setLeaveTime(java.util.Date leaveTime) {
        this.leaveTime = leaveTime;
    }

}
