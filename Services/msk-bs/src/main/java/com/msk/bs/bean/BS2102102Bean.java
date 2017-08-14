package com.msk.bs.bean;

import com.msk.core.entity.SlHouseAccount;

import java.math.BigDecimal;

/**
 * Created by zhu_kai1 on 2016/8/2.
 */
public class BS2102102Bean extends SlHouseAccount {

    /**虚拟物流区名称**/
    private  String vlgcsAreaName;
    /**虚拟地区名称**/
    private String vcityName;
    /**买手名称**/
    private java.lang.String slShowName;
    /**备注**/
    private String remark;
    /**买手编码显示用**/
    private String slCodeDis;
    /**联系人姓名**/
    private  String slContact;
    /**性别**/
    private String sex;
    /**管家一级分类名称**/
    private String cateName;
    /**管家二级分类名称**/
    private String subName;
    /**管家一级分类编码**/
    private String  cateCode;
    /**管家二级分类编码**/
    private String subCode;
    /**管家设置表中的cityCode**/
    private  String houseMangeCityCode;
    /**管家设置表中的cityName**/
    private  String houseMangeCityName;

    /** HOUSE_STAR */
    private java.math.BigDecimal houseStar;
    /**String类型的星级*/
    private String houseStar1;

    /**对应年月*/
    private String validYearMonth;

    public String getVlgcsAreaName() {
        return vlgcsAreaName;
    }

    public void setVlgcsAreaName(String vlgcsAreaName) {
        this.vlgcsAreaName = vlgcsAreaName;
    }

    public String getVcityName() {
        return vcityName;
    }

    public void setVcityName(String vcityName) {
        this.vcityName = vcityName;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlContact() {
        return slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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

    public String getHouseMangeCityName() {
        return houseMangeCityName;
    }

    public void setHouseMangeCityName(String houseMangeCityName) {
        this.houseMangeCityName = houseMangeCityName;
    }

    @Override
    public BigDecimal getHouseStar() {
        return houseStar;
    }

    @Override
    public void setHouseStar(BigDecimal houseStar) {
        this.houseStar = houseStar;
    }

    public String getHouseStar1() {
        return houseStar1;
    }

    public void setHouseStar1(String houseStar1) {
        this.houseStar1 = houseStar1;
    }

    public String getValidYearMonth() {
        return validYearMonth;
    }

    public void setValidYearMonth(String validYearMonth) {
        this.validYearMonth = validYearMonth;
    }
}
