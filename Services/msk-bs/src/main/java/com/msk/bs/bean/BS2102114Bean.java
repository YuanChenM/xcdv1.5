package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * Created by ren_qiang on 2016/8/22.
 */
public class BS2102114Bean extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseAccount;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 用于登录的卖家账号 */
    private String houseCodeDis;
    /** 也可以用于登录 */
    private String houseTel;
    /** HOUSE_SHOW_NAME */
    private String houseShowName;
    /** HOUSE_CONTACT */
    private String houseContact;
    /** HOUSE_INTRODUCE */
    private String houseIntroduce;
    /** 1：店主，2：店员 */
    private String houseClass;
    /** 管家一级分类 */
    private String houseCategory;
    /** 管家二级分类 */
    /** 等级 */
    private String houseGreade;
    /** 星级 */
    private java.math.BigDecimal houseStar;
    /**对应年月*/
    private String validYearMonth;
    /**有效截止日期*/
    private Date endTime;
    /**展期*/
    private Date extendTime;
    /**状态：1:正式分类冻品管家，0:预备,9:注销*/
    private String status;
    /**备注*/
    private String remark;

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
    public String getHouseAccount() {
        return houseAccount;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseAccount 用于登录的卖家账号。
     */
    public void setHouseAccount(String houseAccount) {
        this.houseAccount = houseAccount;
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
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCodeDis() {
        return houseCodeDis;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCodeDis 用于登录的卖家账号。
     */
    public void setHouseCodeDis(String houseCodeDis) {
        this.houseCodeDis = houseCodeDis;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @return the 也可以用于登录
     */
    public String getHouseTel() {
        return houseTel;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @param houseTel 也可以用于登录。
     */
    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    /**
     * <p>HOUSE_SHOW_NAME。</p>
     *
     * @return the HOUSE_SHOW_NAME
     */
    public String getHouseShowName() {
        return houseShowName;
    }

    /**
     * <p>HOUSE_SHOW_NAME。</p>
     *
     * @param houseShowName HOUSE_SHOW_NAME。
     */
    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    /**
     * <p>HOUSE_CONTACT。</p>
     *
     * @return the HOUSE_CONTACT
     */
    public String getHouseContact() {
        return houseContact;
    }

    /**
     * <p>HOUSE_CONTACT。</p>
     *
     * @param houseContact HOUSE_CONTACT。
     */
    public void setHouseContact(String houseContact) {
        this.houseContact = houseContact;
    }

    /**
     * <p>HOUSE_INTRODUCE。</p>
     *
     * @return the HOUSE_INTRODUCE
     */
    public String getHouseIntroduce() {
        return houseIntroduce;
    }

    /**
     * <p>HOUSE_INTRODUCE。</p>
     *
     * @param houseIntroduce HOUSE_INTRODUCE。
     */
    public void setHouseIntroduce(String houseIntroduce) {
        this.houseIntroduce = houseIntroduce;
    }

    /**
     * <p>1：店主，2：店员。</p>
     *
     * @return the 1：店主，2：店员
     */
    public String getHouseClass() {
        return houseClass;
    }

    /**
     * <p>1：店主，2：店员。</p>
     *
     * @param houseClass 1：店主，2：店员。
     */
    public void setHouseClass(String houseClass) {
        this.houseClass = houseClass;
    }

    /**
     * <p>管家一级分类。</p>
     *
     * @return the 管家一级分类
     */
    public String getHouseCategory() {
        return houseCategory;
    }

    /**
     * <p>管家一级分类。</p>
     *
     * @param houseCategory 管家一级分类。
     */
    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory;
    }

    /**
     * <p>等级。</p>
     *
     * @return the 等级
     */
    public String getHouseGreade() {
        return houseGreade;
    }

    /**
     * <p>等级。</p>
     *
     * @param houseGreade 等级。
     */
    public void setHouseGreade(String houseGreade) {
        this.houseGreade = houseGreade;
    }

    /**
     * <p>星级。</p>
     *
     * @return the 星级
     */
    public java.math.BigDecimal getHouseStar() {
        return houseStar;
    }

    /**
     * <p>星级。</p>
     *
     * @param houseStar 星级。
     */
    public void setHouseStar(java.math.BigDecimal houseStar) {
        this.houseStar = houseStar;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getValidYearMonth() {
        return validYearMonth;
    }

    public void setValidYearMonth(String validYearMonth) {
        this.validYearMonth = validYearMonth;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getExtendTime() {
        return extendTime;
    }

    public void setExtendTime(Date extendTime) {
        this.extendTime = extendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
