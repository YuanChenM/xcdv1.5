package com.msk.bs.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;

/**
 * Created by zhu_kai1 on 2016/8/2.
 */
public class BS2102102Param  extends BasePageParam{

    /** 区划(6)+顺序码(4) */
    private java.lang.String slCode;
    /** 用于登录的卖家账号 */
    private java.lang.String houseCode;
    /** 虚拟物流区编码 */
    private java.lang.String vlgcsAreaCode;
    /**物流区名称**/
    private  String lgcsAreaName;
    /** 管家名称 */
    private java.lang.String houseShowName;
    /** 性别 */
    private java.lang.String flag1;
    /** 也可以用于登录（手机号） */
    private java.lang.String houseTel;
    /** 微信号 */
    private java.lang.String wechat;
    /**买手名称**/
    private java.lang.String slShowName;
    /** HOUSE_GREADE */
    private java.lang.String houseGreade;
    /** HOUSE_STAR */
    private java.math.BigDecimal houseStar;
    /**虚拟地区编码**/
    private String vcityCode;
    /**管家一级分类**/
    private String cateCode;
    /**管家二级分类**/
    private String subCode;
    /**管家设置表中的cityCode**/
    private  String houseMangeCityCode;
    /**所属买手**/
    private String slContact;
    /**对应年月*/
    private String validYearMonth;

    public String getVlgcsAreaCode() {
        return vlgcsAreaCode;
    }

    public void setVlgcsAreaCode(String vlgcsAreaCode) {
        this.vlgcsAreaCode = vlgcsAreaCode;
    }

    public String getVcityCode() {
        return vcityCode;
    }

    public void setVcityCode(String vcityCode) {
        this.vcityCode = vcityCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getHouseTel() {
        return houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getHouseGreade() {
        return houseGreade;
    }

    public void setHouseGreade(String houseGreade) {
        this.houseGreade = houseGreade;
    }

    public BigDecimal getHouseStar() {
        return houseStar;
    }

    public void setHouseStar(BigDecimal houseStar) {
        this.houseStar = houseStar;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getHouseMangeCityCode() {
        return houseMangeCityCode;
    }

    public void setHouseMangeCityCode(String houseMangeCityCode) {
        this.houseMangeCityCode = houseMangeCityCode;
    }

    public String getSlContact() {
        return slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getValidYearMonth() {
        return validYearMonth;
    }

    public void setValidYearMonth(String validYearMonth) {
        this.validYearMonth = validYearMonth;
    }
}

