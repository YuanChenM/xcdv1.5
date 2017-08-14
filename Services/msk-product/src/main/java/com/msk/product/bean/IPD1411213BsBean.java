package com.msk.product.bean;

import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gao_min on 2016/10/17.
 */
@ApiModel(value = "IPD1411213BsBean",description = "bean")
public class IPD1411213BsBean extends BaseBean {
    //SlSeller
    @ApiModelProperty(value = "区划(6)+顺序码(4)")
    private java.lang.String slCode;
    @ApiModelProperty(value = "卖家账号")
    private java.lang.String slAccount;
    @ApiModelProperty(value = "区划(6)+顺序码(4)")
    private java.lang.String slCodeDis;
    @ApiModelProperty(value = "1：国产，2：进口")
    private java.lang.String slConFlg;
    @ApiModelProperty(value = "大区")
    private java.lang.String areaCode;
    @ApiModelProperty(value = "物流区编码")
    private java.lang.String lgcsAreaCode;
    @ApiModelProperty(value = "省编码")
    private java.lang.String provinceCode;
    @ApiModelProperty(value = "地区编码")
    private java.lang.String cityCode;
    @ApiModelProperty(value = "区编码")
    private java.lang.String districtCode;
    @ApiModelProperty(value = "企业ID")
    private java.lang.Long epId;
    @ApiModelProperty(value = "0.生产商1.自产型,2:代理型,3:OEM型")
    private java.lang.Integer slMainClass;
    @ApiModelProperty(value = "1.是")
    private java.lang.String snkFlg;
    @ApiModelProperty(value = "1.是")
    private java.lang.String selfFlg;
    @ApiModelProperty(value = "1.是")
    private java.lang.String agentFlg;
    @ApiModelProperty(value = "1.是")
    private java.lang.String oemFlg;
    @ApiModelProperty(value = "1.是")
    private java.lang.String buyerFlg;
    @ApiModelProperty(value = "0:未审核,1:审核中,2:审核通过,3:审核未通过")
    private java.lang.Integer sqaStatus;
    @ApiModelProperty(value = "0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)")
    private java.lang.Integer distQua;
    @ApiModelProperty(value = "0:无资格,1:有资格")
    private java.lang.String shopQua;
    @ApiModelProperty(value = "备用字段1")
    private java.lang.String memo1;
    @ApiModelProperty(value = "备用字段2")
    private java.lang.String memo2;
    @ApiModelProperty(value = "备用字段3")
    private java.lang.String memo3;
    @ApiModelProperty(value = "备用字段4")
    private java.lang.String memo4;
    @ApiModelProperty(value = "备用字段5")
    private java.lang.String memo5;
    @ApiModelProperty(value = "备用字段6")
    private java.lang.String memo6;
    @ApiModelProperty(value = "备用字段7")
    private java.lang.String memo7;
    @ApiModelProperty(value = "备用字段8")
    private java.lang.String memo8;
    @ApiModelProperty(value = "备用字段9")
    private java.lang.String memo9;
    @ApiModelProperty(value = "备用字段10")
    private java.lang.String memo10;
    @ApiModelProperty(value = "备用字段11")
    private java.lang.String memo11;
    @ApiModelProperty(value = "备用字段12")
    private java.lang.String memo12;
    @ApiModelProperty(value = "备用字段13")
    private java.lang.String memo13;
    @ApiModelProperty(value = "备用字段14")
    private java.lang.String memo14;
    @ApiModelProperty(value = "备用字段15")
    private java.lang.String memo15;
    @ApiModelProperty(value = "备用字段16")
    private java.lang.String memo16;
    @ApiModelProperty(value = "备用字段17")
    private java.lang.String memo17;
    @ApiModelProperty(value = "备用字段18")
    private java.lang.String memo18;
    @ApiModelProperty(value = "备用字段19")
    private java.lang.String memo19;
    @ApiModelProperty(value = "备用字段20")
    private java.lang.String memo20;
    //slAccount
    @ApiModelProperty(value = "也可以用于登录")
    private java.lang.String slTel;
    @ApiModelProperty(value = "卖家显示名称")
    private java.lang.String slShowName;
    @ApiModelProperty(value = "联系人姓名")
    private java.lang.String slContact;
    @ApiModelProperty(value = "加密后的值")
    private java.lang.String accountPsd;
    @ApiModelProperty(value = "图片路径")
    private java.lang.String accountImg;
    @ApiModelProperty(value = "0:未认证,1:认证中,2:已认证")
    private java.lang.Integer authStatus;
    @ApiModelProperty(value = "注册来源")
    private java.lang.String fromFlg;
    //SlBuyershop
    @ApiModelProperty(value = "买手身份证号")
    private java.lang.String slIdcard;
    @ApiModelProperty(value = "合营优先顺方式")
    private java.lang.Integer slSort;
    @ApiModelProperty(value = "买手地址")
    private java.lang.String slAddress;
    @ApiModelProperty(value = "维度")
    private java.lang.String lat;
    @ApiModelProperty(value = "精度")
    private java.lang.String lon;
    @ApiModelProperty(value = "备用10")
    private java.lang.String flag10;
    @ApiModelProperty(value = "备用9")
    private java.lang.String flag9;
    @ApiModelProperty(value = "备用8")
    private java.lang.String flag8;
    @ApiModelProperty(value = "备用7")
    private java.lang.String flag7;
    @ApiModelProperty(value = "备用6")
    private java.lang.String flag6;
    @ApiModelProperty(value = "备用5")
    private java.lang.String flag5;
    @ApiModelProperty(value = "备用4")
    private java.lang.String flag4;
    @ApiModelProperty(value = "备用3")
    private java.lang.String flag3;
    @ApiModelProperty(value = "备用2")
    private java.lang.String flag2;
    @ApiModelProperty(value = "1：男 2：女")
    private java.lang.String flag1;
    //SlShopInfo
    @ApiModelProperty(value = "店铺ID")
    private java.lang.Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private java.lang.String shopName;
    @ApiModelProperty(value = "店铺Logo")
    private java.lang.String shopLogo;
    @ApiModelProperty(value = "经营特色10")
    private java.lang.String managingCharact10;
    @ApiModelProperty(value = "经营特色9")
    private java.lang.String managingCharact9;
    @ApiModelProperty(value = "经营特色8")
    private java.lang.String managingCharact8;
    @ApiModelProperty(value = "经营特色7")
    private java.lang.String managingCharact7;
    @ApiModelProperty(value = "经营特色6")
    private java.lang.String managingCharact6;
    @ApiModelProperty(value = "经营特色5")
    private java.lang.String managingCharact5;
    @ApiModelProperty(value = "经营特色4")
    private java.lang.String managingCharact4;
    @ApiModelProperty(value = "经营特色3")
    private java.lang.String managingCharact3;
    @ApiModelProperty(value = "经营特色2")
    private java.lang.String managingCharact2;
    @ApiModelProperty(value = "经营特色1")
    private java.lang.String managingCharact1;
    @ApiModelProperty(value = "备用30")
    private java.lang.String shopFlag30;
    @ApiModelProperty(value = "备用29")
    private java.lang.String shopFlag29;
    @ApiModelProperty(value = "备用28")
    private java.lang.String shopFlag28;
    @ApiModelProperty(value = "备用27")
    private java.lang.String shopFlag27;
    @ApiModelProperty(value = "备用26")
    private java.lang.String shopFlag26;
    @ApiModelProperty(value = "备用25")
    private java.lang.String shopFlag25;
    @ApiModelProperty(value = "备用24")
    private java.lang.String shopFlag24;
    @ApiModelProperty(value = "备用23")
    private java.lang.String shopFlag23;
    @ApiModelProperty(value = "备用22")
    private java.lang.String shopFlag22;
    @ApiModelProperty(value = "备用21")
    private java.lang.String shopFlag21;
    @ApiModelProperty(value = "备用20")
    private java.lang.String shopFlag20;
    @ApiModelProperty(value = "备用19")
    private java.lang.String shopFlag19;
    @ApiModelProperty(value = "备用18")
    private java.lang.String shopFlag18;
    @ApiModelProperty(value = "备用17")
    private java.lang.String shopFlag17;
    @ApiModelProperty(value = "备用16")
    private java.lang.String shopFlag16;
    @ApiModelProperty(value = "备用15")
    private java.lang.String shopFlag15;
    @ApiModelProperty(value = "备用14")
    private java.lang.String shopFlag14;
    @ApiModelProperty(value = "备用13")
    private java.lang.String shopFlag13;
    @ApiModelProperty(value = "备用12")
    private java.lang.String shopFlag12;
    @ApiModelProperty(value = "备用11")
    private java.lang.String shopFlag11;
    @ApiModelProperty(value = "备用10")
    private java.lang.String shopFlag10;
    @ApiModelProperty(value = "备用9")
    private java.lang.String shopFlag9;
    @ApiModelProperty(value = "备用8")
    private java.lang.String shopFlag8;
    @ApiModelProperty(value = "备用7")
    private java.lang.String shopFlag7;
    @ApiModelProperty(value = "备用6")
    private java.lang.String shopFlag6;
    @ApiModelProperty(value = "备用5")
    private java.lang.String shopFlag5;
    @ApiModelProperty(value = "备用4")
    private java.lang.String shopFlag4;
    @ApiModelProperty(value = "备用3")
    private java.lang.String shopFlag3;
    @ApiModelProperty(value = "备用2")
    private java.lang.String shopFlag2;
    @ApiModelProperty(value = "备用1")
    private java.lang.String shopFlag1;
    //BS2101101Bean
    @ApiModelProperty(value = "行政区划")
    private String cityName;
    @ApiModelProperty(value = "物流区划")
    private String lgcsAreaName;
    @ApiModelProperty(value = "当前管家数")
    private  String stewardNum;
    @ApiModelProperty(value = "当前专属买家")
    private  String buyerNum;

    /**
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter method for property <tt>lgcsAreaName</tt>.
     *
     * @return property value of lgcsAreaName
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * Setter method for property <tt>lgcsAreaName</tt>.
     *
     * @param lgcsAreaName value to be assigned to property lgcsAreaName
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * Getter method for property <tt>stewardNum</tt>.
     *
     * @return property value of stewardNum
     */
    public String getStewardNum() {
        return stewardNum;
    }

    /**
     * Setter method for property <tt>stewardNum</tt>.
     *
     * @param stewardNum value to be assigned to property stewardNum
     */
    public void setStewardNum(String stewardNum) {
        this.stewardNum = stewardNum;
    }

    /**
     * Getter method for property <tt>buyerNum</tt>.
     *
     * @return property value of buyerNum
     */
    public String getBuyerNum() {
        return buyerNum;
    }

    /**
     * Setter method for property <tt>buyerNum</tt>.
     *
     * @param buyerNum value to be assigned to property buyerNum
     */
    public void setBuyerNum(String buyerNum) {
        this.buyerNum = buyerNum;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * Setter method for property <tt>slAccount</tt>.
     *
     * @param slAccount value to be assigned to property slAccount
     */
    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * Getter method for property <tt>slCodeDis</tt>.
     *
     * @return property value of slCodeDis
     */
    public String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * Setter method for property <tt>slCodeDis</tt>.
     *
     * @param slCodeDis value to be assigned to property slCodeDis
     */
    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * Getter method for property <tt>slConFlg</tt>.
     *
     * @return property value of slConFlg
     */
    public String getSlConFlg() {
        return slConFlg;
    }

    /**
     * Setter method for property <tt>slConFlg</tt>.
     *
     * @param slConFlg value to be assigned to property slConFlg
     */
    public void setSlConFlg(String slConFlg) {
        this.slConFlg = slConFlg;
    }

    /**
     * Getter method for property <tt>areaCode</tt>.
     *
     * @return property value of areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Setter method for property <tt>areaCode</tt>.
     *
     * @param areaCode value to be assigned to property areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * Getter method for property <tt>lgcsAreaCode</tt>.
     *
     * @return property value of lgcsAreaCode
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * Setter method for property <tt>lgcsAreaCode</tt>.
     *
     * @param lgcsAreaCode value to be assigned to property lgcsAreaCode
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * Getter method for property <tt>provinceCode</tt>.
     *
     * @return property value of provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Setter method for property <tt>provinceCode</tt>.
     *
     * @param provinceCode value to be assigned to property provinceCode
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * Getter method for property <tt>slMainClass</tt>.
     *
     * @return property value of slMainClass
     */
    public Integer getSlMainClass() {
        return slMainClass;
    }

    /**
     * Setter method for property <tt>slMainClass</tt>.
     *
     * @param slMainClass value to be assigned to property slMainClass
     */
    public void setSlMainClass(Integer slMainClass) {
        this.slMainClass = slMainClass;
    }

    /**
     * Getter method for property <tt>snkFlg</tt>.
     *
     * @return property value of snkFlg
     */
    public String getSnkFlg() {
        return snkFlg;
    }

    /**
     * Setter method for property <tt>snkFlg</tt>.
     *
     * @param snkFlg value to be assigned to property snkFlg
     */
    public void setSnkFlg(String snkFlg) {
        this.snkFlg = snkFlg;
    }

    /**
     * Getter method for property <tt>selfFlg</tt>.
     *
     * @return property value of selfFlg
     */
    public String getSelfFlg() {
        return selfFlg;
    }

    /**
     * Setter method for property <tt>selfFlg</tt>.
     *
     * @param selfFlg value to be assigned to property selfFlg
     */
    public void setSelfFlg(String selfFlg) {
        this.selfFlg = selfFlg;
    }

    /**
     * Getter method for property <tt>agentFlg</tt>.
     *
     * @return property value of agentFlg
     */
    public String getAgentFlg() {
        return agentFlg;
    }

    /**
     * Setter method for property <tt>agentFlg</tt>.
     *
     * @param agentFlg value to be assigned to property agentFlg
     */
    public void setAgentFlg(String agentFlg) {
        this.agentFlg = agentFlg;
    }

    /**
     * Getter method for property <tt>oemFlg</tt>.
     *
     * @return property value of oemFlg
     */
    public String getOemFlg() {
        return oemFlg;
    }

    /**
     * Setter method for property <tt>oemFlg</tt>.
     *
     * @param oemFlg value to be assigned to property oemFlg
     */
    public void setOemFlg(String oemFlg) {
        this.oemFlg = oemFlg;
    }

    /**
     * Getter method for property <tt>buyerFlg</tt>.
     *
     * @return property value of buyerFlg
     */
    public String getBuyerFlg() {
        return buyerFlg;
    }

    /**
     * Setter method for property <tt>buyerFlg</tt>.
     *
     * @param buyerFlg value to be assigned to property buyerFlg
     */
    public void setBuyerFlg(String buyerFlg) {
        this.buyerFlg = buyerFlg;
    }

    /**
     * Getter method for property <tt>sqaStatus</tt>.
     *
     * @return property value of sqaStatus
     */
    public Integer getSqaStatus() {
        return sqaStatus;
    }

    /**
     * Setter method for property <tt>sqaStatus</tt>.
     *
     * @param sqaStatus value to be assigned to property sqaStatus
     */
    public void setSqaStatus(Integer sqaStatus) {
        this.sqaStatus = sqaStatus;
    }

    /**
     * Getter method for property <tt>distQua</tt>.
     *
     * @return property value of distQua
     */
    public Integer getDistQua() {
        return distQua;
    }

    /**
     * Setter method for property <tt>distQua</tt>.
     *
     * @param distQua value to be assigned to property distQua
     */
    public void setDistQua(Integer distQua) {
        this.distQua = distQua;
    }

    /**
     * Getter method for property <tt>shopQua</tt>.
     *
     * @return property value of shopQua
     */
    public String getShopQua() {
        return shopQua;
    }

    /**
     * Setter method for property <tt>shopQua</tt>.
     *
     * @param shopQua value to be assigned to property shopQua
     */
    public void setShopQua(String shopQua) {
        this.shopQua = shopQua;
    }

    /**
     * Getter method for property <tt>memo1</tt>.
     *
     * @return property value of memo1
     */
    public String getMemo1() {
        return memo1;
    }

    /**
     * Setter method for property <tt>memo1</tt>.
     *
     * @param memo1 value to be assigned to property memo1
     */
    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    /**
     * Getter method for property <tt>memo2</tt>.
     *
     * @return property value of memo2
     */
    public String getMemo2() {
        return memo2;
    }

    /**
     * Setter method for property <tt>memo2</tt>.
     *
     * @param memo2 value to be assigned to property memo2
     */
    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    /**
     * Getter method for property <tt>memo3</tt>.
     *
     * @return property value of memo3
     */
    public String getMemo3() {
        return memo3;
    }

    /**
     * Setter method for property <tt>memo3</tt>.
     *
     * @param memo3 value to be assigned to property memo3
     */
    public void setMemo3(String memo3) {
        this.memo3 = memo3;
    }

    /**
     * Getter method for property <tt>memo4</tt>.
     *
     * @return property value of memo4
     */
    public String getMemo4() {
        return memo4;
    }

    /**
     * Setter method for property <tt>memo4</tt>.
     *
     * @param memo4 value to be assigned to property memo4
     */
    public void setMemo4(String memo4) {
        this.memo4 = memo4;
    }

    /**
     * Getter method for property <tt>memo5</tt>.
     *
     * @return property value of memo5
     */
    public String getMemo5() {
        return memo5;
    }

    /**
     * Setter method for property <tt>memo5</tt>.
     *
     * @param memo5 value to be assigned to property memo5
     */
    public void setMemo5(String memo5) {
        this.memo5 = memo5;
    }

    /**
     * Getter method for property <tt>memo6</tt>.
     *
     * @return property value of memo6
     */
    public String getMemo6() {
        return memo6;
    }

    /**
     * Setter method for property <tt>memo6</tt>.
     *
     * @param memo6 value to be assigned to property memo6
     */
    public void setMemo6(String memo6) {
        this.memo6 = memo6;
    }

    /**
     * Getter method for property <tt>memo7</tt>.
     *
     * @return property value of memo7
     */
    public String getMemo7() {
        return memo7;
    }

    /**
     * Setter method for property <tt>memo7</tt>.
     *
     * @param memo7 value to be assigned to property memo7
     */
    public void setMemo7(String memo7) {
        this.memo7 = memo7;
    }

    /**
     * Getter method for property <tt>memo8</tt>.
     *
     * @return property value of memo8
     */
    public String getMemo8() {
        return memo8;
    }

    /**
     * Setter method for property <tt>memo8</tt>.
     *
     * @param memo8 value to be assigned to property memo8
     */
    public void setMemo8(String memo8) {
        this.memo8 = memo8;
    }

    /**
     * Getter method for property <tt>memo9</tt>.
     *
     * @return property value of memo9
     */
    public String getMemo9() {
        return memo9;
    }

    /**
     * Setter method for property <tt>memo9</tt>.
     *
     * @param memo9 value to be assigned to property memo9
     */
    public void setMemo9(String memo9) {
        this.memo9 = memo9;
    }

    /**
     * Getter method for property <tt>memo10</tt>.
     *
     * @return property value of memo10
     */
    public String getMemo10() {
        return memo10;
    }

    /**
     * Setter method for property <tt>memo10</tt>.
     *
     * @param memo10 value to be assigned to property memo10
     */
    public void setMemo10(String memo10) {
        this.memo10 = memo10;
    }

    /**
     * Getter method for property <tt>memo11</tt>.
     *
     * @return property value of memo11
     */
    public String getMemo11() {
        return memo11;
    }

    /**
     * Setter method for property <tt>memo11</tt>.
     *
     * @param memo11 value to be assigned to property memo11
     */
    public void setMemo11(String memo11) {
        this.memo11 = memo11;
    }

    /**
     * Getter method for property <tt>memo12</tt>.
     *
     * @return property value of memo12
     */
    public String getMemo12() {
        return memo12;
    }

    /**
     * Setter method for property <tt>memo12</tt>.
     *
     * @param memo12 value to be assigned to property memo12
     */
    public void setMemo12(String memo12) {
        this.memo12 = memo12;
    }

    /**
     * Getter method for property <tt>memo13</tt>.
     *
     * @return property value of memo13
     */
    public String getMemo13() {
        return memo13;
    }

    /**
     * Setter method for property <tt>memo13</tt>.
     *
     * @param memo13 value to be assigned to property memo13
     */
    public void setMemo13(String memo13) {
        this.memo13 = memo13;
    }

    /**
     * Getter method for property <tt>memo14</tt>.
     *
     * @return property value of memo14
     */
    public String getMemo14() {
        return memo14;
    }

    /**
     * Setter method for property <tt>memo14</tt>.
     *
     * @param memo14 value to be assigned to property memo14
     */
    public void setMemo14(String memo14) {
        this.memo14 = memo14;
    }

    /**
     * Getter method for property <tt>memo15</tt>.
     *
     * @return property value of memo15
     */
    public String getMemo15() {
        return memo15;
    }

    /**
     * Setter method for property <tt>memo15</tt>.
     *
     * @param memo15 value to be assigned to property memo15
     */
    public void setMemo15(String memo15) {
        this.memo15 = memo15;
    }

    /**
     * Getter method for property <tt>memo16</tt>.
     *
     * @return property value of memo16
     */
    public String getMemo16() {
        return memo16;
    }

    /**
     * Setter method for property <tt>memo16</tt>.
     *
     * @param memo16 value to be assigned to property memo16
     */
    public void setMemo16(String memo16) {
        this.memo16 = memo16;
    }

    /**
     * Getter method for property <tt>memo17</tt>.
     *
     * @return property value of memo17
     */
    public String getMemo17() {
        return memo17;
    }

    /**
     * Setter method for property <tt>memo17</tt>.
     *
     * @param memo17 value to be assigned to property memo17
     */
    public void setMemo17(String memo17) {
        this.memo17 = memo17;
    }

    /**
     * Getter method for property <tt>memo18</tt>.
     *
     * @return property value of memo18
     */
    public String getMemo18() {
        return memo18;
    }

    /**
     * Setter method for property <tt>memo18</tt>.
     *
     * @param memo18 value to be assigned to property memo18
     */
    public void setMemo18(String memo18) {
        this.memo18 = memo18;
    }

    /**
     * Getter method for property <tt>memo19</tt>.
     *
     * @return property value of memo19
     */
    public String getMemo19() {
        return memo19;
    }

    /**
     * Setter method for property <tt>memo19</tt>.
     *
     * @param memo19 value to be assigned to property memo19
     */
    public void setMemo19(String memo19) {
        this.memo19 = memo19;
    }

    /**
     * Getter method for property <tt>memo20</tt>.
     *
     * @return property value of memo20
     */
    public String getMemo20() {
        return memo20;
    }

    /**
     * Setter method for property <tt>memo20</tt>.
     *
     * @param memo20 value to be assigned to property memo20
     */
    public void setMemo20(String memo20) {
        this.memo20 = memo20;
    }

    /**
     * Getter method for property <tt>slTel</tt>.
     *
     * @return property value of slTel
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * Setter method for property <tt>slTel</tt>.
     *
     * @param slTel value to be assigned to property slTel
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * Getter method for property <tt>slShowName</tt>.
     *
     * @return property value of slShowName
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * Setter method for property <tt>slShowName</tt>.
     *
     * @param slShowName value to be assigned to property slShowName
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * Getter method for property <tt>slContact</tt>.
     *
     * @return property value of slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * Setter method for property <tt>slContact</tt>.
     *
     * @param slContact value to be assigned to property slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * Getter method for property <tt>accountPsd</tt>.
     *
     * @return property value of accountPsd
     */
    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * Setter method for property <tt>accountPsd</tt>.
     *
     * @param accountPsd value to be assigned to property accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * Getter method for property <tt>accountImg</tt>.
     *
     * @return property value of accountImg
     */
    public String getAccountImg() {
        return accountImg;
    }

    /**
     * Setter method for property <tt>accountImg</tt>.
     *
     * @param accountImg value to be assigned to property accountImg
     */
    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    /**
     * Getter method for property <tt>authStatus</tt>.
     *
     * @return property value of authStatus
     */
    public Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * Setter method for property <tt>authStatus</tt>.
     *
     * @param authStatus value to be assigned to property authStatus
     */
    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * Getter method for property <tt>fromFlg</tt>.
     *
     * @return property value of fromFlg
     */
    public String getFromFlg() {
        return fromFlg;
    }

    /**
     * Setter method for property <tt>fromFlg</tt>.
     *
     * @param fromFlg value to be assigned to property fromFlg
     */
    public void setFromFlg(String fromFlg) {
        this.fromFlg = fromFlg;
    }

    /**
     * Getter method for property <tt>slIdcard</tt>.
     *
     * @return property value of slIdcard
     */
    public String getSlIdcard() {
        return slIdcard;
    }

    /**
     * Setter method for property <tt>slIdcard</tt>.
     *
     * @param slIdcard value to be assigned to property slIdcard
     */
    public void setSlIdcard(String slIdcard) {
        this.slIdcard = slIdcard;
    }

    /**
     * Getter method for property <tt>slSort</tt>.
     *
     * @return property value of slSort
     */
    public Integer getSlSort() {
        return slSort;
    }

    /**
     * Setter method for property <tt>slSort</tt>.
     *
     * @param slSort value to be assigned to property slSort
     */
    public void setSlSort(Integer slSort) {
        this.slSort = slSort;
    }

    /**
     * Getter method for property <tt>slAddress</tt>.
     *
     * @return property value of slAddress
     */
    public String getSlAddress() {
        return slAddress;
    }

    /**
     * Setter method for property <tt>slAddress</tt>.
     *
     * @param slAddress value to be assigned to property slAddress
     */
    public void setSlAddress(String slAddress) {
        this.slAddress = slAddress;
    }

    /**
     * Getter method for property <tt>lat</tt>.
     *
     * @return property value of lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * Setter method for property <tt>lat</tt>.
     *
     * @param lat value to be assigned to property lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Getter method for property <tt>lon</tt>.
     *
     * @return property value of lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * Setter method for property <tt>lon</tt>.
     *
     * @param lon value to be assigned to property lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * Getter method for property <tt>flag10</tt>.
     *
     * @return property value of flag10
     */
    public String getFlag10() {
        return flag10;
    }

    /**
     * Setter method for property <tt>flag10</tt>.
     *
     * @param flag10 value to be assigned to property flag10
     */
    public void setFlag10(String flag10) {
        this.flag10 = flag10;
    }

    /**
     * Getter method for property <tt>flag9</tt>.
     *
     * @return property value of flag9
     */
    public String getFlag9() {
        return flag9;
    }

    /**
     * Setter method for property <tt>flag9</tt>.
     *
     * @param flag9 value to be assigned to property flag9
     */
    public void setFlag9(String flag9) {
        this.flag9 = flag9;
    }

    /**
     * Getter method for property <tt>flag8</tt>.
     *
     * @return property value of flag8
     */
    public String getFlag8() {
        return flag8;
    }

    /**
     * Setter method for property <tt>flag8</tt>.
     *
     * @param flag8 value to be assigned to property flag8
     */
    public void setFlag8(String flag8) {
        this.flag8 = flag8;
    }

    /**
     * Getter method for property <tt>flag7</tt>.
     *
     * @return property value of flag7
     */
    public String getFlag7() {
        return flag7;
    }

    /**
     * Setter method for property <tt>flag7</tt>.
     *
     * @param flag7 value to be assigned to property flag7
     */
    public void setFlag7(String flag7) {
        this.flag7 = flag7;
    }

    /**
     * Getter method for property <tt>flag6</tt>.
     *
     * @return property value of flag6
     */
    public String getFlag6() {
        return flag6;
    }

    /**
     * Setter method for property <tt>flag6</tt>.
     *
     * @param flag6 value to be assigned to property flag6
     */
    public void setFlag6(String flag6) {
        this.flag6 = flag6;
    }

    /**
     * Getter method for property <tt>flag5</tt>.
     *
     * @return property value of flag5
     */
    public String getFlag5() {
        return flag5;
    }

    /**
     * Setter method for property <tt>flag5</tt>.
     *
     * @param flag5 value to be assigned to property flag5
     */
    public void setFlag5(String flag5) {
        this.flag5 = flag5;
    }

    /**
     * Getter method for property <tt>flag4</tt>.
     *
     * @return property value of flag4
     */
    public String getFlag4() {
        return flag4;
    }

    /**
     * Setter method for property <tt>flag4</tt>.
     *
     * @param flag4 value to be assigned to property flag4
     */
    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    /**
     * Getter method for property <tt>flag3</tt>.
     *
     * @return property value of flag3
     */
    public String getFlag3() {
        return flag3;
    }

    /**
     * Setter method for property <tt>flag3</tt>.
     *
     * @param flag3 value to be assigned to property flag3
     */
    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    /**
     * Getter method for property <tt>flag2</tt>.
     *
     * @return property value of flag2
     */
    public String getFlag2() {
        return flag2;
    }

    /**
     * Setter method for property <tt>flag2</tt>.
     *
     * @param flag2 value to be assigned to property flag2
     */
    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    /**
     * Getter method for property <tt>flag1</tt>.
     *
     * @return property value of flag1
     */
    public String getFlag1() {
        return flag1;
    }

    /**
     * Setter method for property <tt>flag1</tt>.
     *
     * @param flag1 value to be assigned to property flag1
     */
    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    /**
     * Getter method for property <tt>shopId</tt>.
     *
     * @return property value of shopId
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * Setter method for property <tt>shopId</tt>.
     *
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property <tt>shopName</tt>.
     *
     * @return property value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Setter method for property <tt>shopName</tt>.
     *
     * @param shopName value to be assigned to property shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Getter method for property <tt>shopLogo</tt>.
     *
     * @return property value of shopLogo
     */
    public String getShopLogo() {
        return shopLogo;
    }

    /**
     * Setter method for property <tt>shopLogo</tt>.
     *
     * @param shopLogo value to be assigned to property shopLogo
     */
    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    /**
     * Getter method for property <tt>managingCharact10</tt>.
     *
     * @return property value of managingCharact10
     */
    public String getManagingCharact10() {
        return managingCharact10;
    }

    /**
     * Setter method for property <tt>managingCharact10</tt>.
     *
     * @param managingCharact10 value to be assigned to property managingCharact10
     */
    public void setManagingCharact10(String managingCharact10) {
        this.managingCharact10 = managingCharact10;
    }

    /**
     * Getter method for property <tt>managingCharact9</tt>.
     *
     * @return property value of managingCharact9
     */
    public String getManagingCharact9() {
        return managingCharact9;
    }

    /**
     * Setter method for property <tt>managingCharact9</tt>.
     *
     * @param managingCharact9 value to be assigned to property managingCharact9
     */
    public void setManagingCharact9(String managingCharact9) {
        this.managingCharact9 = managingCharact9;
    }

    /**
     * Getter method for property <tt>managingCharact8</tt>.
     *
     * @return property value of managingCharact8
     */
    public String getManagingCharact8() {
        return managingCharact8;
    }

    /**
     * Setter method for property <tt>managingCharact8</tt>.
     *
     * @param managingCharact8 value to be assigned to property managingCharact8
     */
    public void setManagingCharact8(String managingCharact8) {
        this.managingCharact8 = managingCharact8;
    }

    /**
     * Getter method for property <tt>managingCharact7</tt>.
     *
     * @return property value of managingCharact7
     */
    public String getManagingCharact7() {
        return managingCharact7;
    }

    /**
     * Setter method for property <tt>managingCharact7</tt>.
     *
     * @param managingCharact7 value to be assigned to property managingCharact7
     */
    public void setManagingCharact7(String managingCharact7) {
        this.managingCharact7 = managingCharact7;
    }

    /**
     * Getter method for property <tt>managingCharact6</tt>.
     *
     * @return property value of managingCharact6
     */
    public String getManagingCharact6() {
        return managingCharact6;
    }

    /**
     * Setter method for property <tt>managingCharact6</tt>.
     *
     * @param managingCharact6 value to be assigned to property managingCharact6
     */
    public void setManagingCharact6(String managingCharact6) {
        this.managingCharact6 = managingCharact6;
    }

    /**
     * Getter method for property <tt>managingCharact5</tt>.
     *
     * @return property value of managingCharact5
     */
    public String getManagingCharact5() {
        return managingCharact5;
    }

    /**
     * Setter method for property <tt>managingCharact5</tt>.
     *
     * @param managingCharact5 value to be assigned to property managingCharact5
     */
    public void setManagingCharact5(String managingCharact5) {
        this.managingCharact5 = managingCharact5;
    }

    /**
     * Getter method for property <tt>managingCharact4</tt>.
     *
     * @return property value of managingCharact4
     */
    public String getManagingCharact4() {
        return managingCharact4;
    }

    /**
     * Setter method for property <tt>managingCharact4</tt>.
     *
     * @param managingCharact4 value to be assigned to property managingCharact4
     */
    public void setManagingCharact4(String managingCharact4) {
        this.managingCharact4 = managingCharact4;
    }

    /**
     * Getter method for property <tt>managingCharact3</tt>.
     *
     * @return property value of managingCharact3
     */
    public String getManagingCharact3() {
        return managingCharact3;
    }

    /**
     * Setter method for property <tt>managingCharact3</tt>.
     *
     * @param managingCharact3 value to be assigned to property managingCharact3
     */
    public void setManagingCharact3(String managingCharact3) {
        this.managingCharact3 = managingCharact3;
    }

    /**
     * Getter method for property <tt>managingCharact2</tt>.
     *
     * @return property value of managingCharact2
     */
    public String getManagingCharact2() {
        return managingCharact2;
    }

    /**
     * Setter method for property <tt>managingCharact2</tt>.
     *
     * @param managingCharact2 value to be assigned to property managingCharact2
     */
    public void setManagingCharact2(String managingCharact2) {
        this.managingCharact2 = managingCharact2;
    }

    /**
     * Getter method for property <tt>managingCharact1</tt>.
     *
     * @return property value of managingCharact1
     */
    public String getManagingCharact1() {
        return managingCharact1;
    }

    /**
     * Setter method for property <tt>managingCharact1</tt>.
     *
     * @param managingCharact1 value to be assigned to property managingCharact1
     */
    public void setManagingCharact1(String managingCharact1) {
        this.managingCharact1 = managingCharact1;
    }

    /**
     * Getter method for property <tt>shopFlag30</tt>.
     *
     * @return property value of shopFlag30
     */
    public String getShopFlag30() {
        return shopFlag30;
    }

    /**
     * Setter method for property <tt>shopFlag30</tt>.
     *
     * @param shopFlag30 value to be assigned to property shopFlag30
     */
    public void setShopFlag30(String shopFlag30) {
        this.shopFlag30 = shopFlag30;
    }

    /**
     * Getter method for property <tt>shopFlag29</tt>.
     *
     * @return property value of shopFlag29
     */
    public String getShopFlag29() {
        return shopFlag29;
    }

    /**
     * Setter method for property <tt>shopFlag29</tt>.
     *
     * @param shopFlag29 value to be assigned to property shopFlag29
     */
    public void setShopFlag29(String shopFlag29) {
        this.shopFlag29 = shopFlag29;
    }

    /**
     * Getter method for property <tt>shopFlag28</tt>.
     *
     * @return property value of shopFlag28
     */
    public String getShopFlag28() {
        return shopFlag28;
    }

    /**
     * Setter method for property <tt>shopFlag28</tt>.
     *
     * @param shopFlag28 value to be assigned to property shopFlag28
     */
    public void setShopFlag28(String shopFlag28) {
        this.shopFlag28 = shopFlag28;
    }

    /**
     * Getter method for property <tt>shopFlag27</tt>.
     *
     * @return property value of shopFlag27
     */
    public String getShopFlag27() {
        return shopFlag27;
    }

    /**
     * Setter method for property <tt>shopFlag27</tt>.
     *
     * @param shopFlag27 value to be assigned to property shopFlag27
     */
    public void setShopFlag27(String shopFlag27) {
        this.shopFlag27 = shopFlag27;
    }

    /**
     * Getter method for property <tt>shopFlag26</tt>.
     *
     * @return property value of shopFlag26
     */
    public String getShopFlag26() {
        return shopFlag26;
    }

    /**
     * Setter method for property <tt>shopFlag26</tt>.
     *
     * @param shopFlag26 value to be assigned to property shopFlag26
     */
    public void setShopFlag26(String shopFlag26) {
        this.shopFlag26 = shopFlag26;
    }

    /**
     * Getter method for property <tt>shopFlag25</tt>.
     *
     * @return property value of shopFlag25
     */
    public String getShopFlag25() {
        return shopFlag25;
    }

    /**
     * Setter method for property <tt>shopFlag25</tt>.
     *
     * @param shopFlag25 value to be assigned to property shopFlag25
     */
    public void setShopFlag25(String shopFlag25) {
        this.shopFlag25 = shopFlag25;
    }

    /**
     * Getter method for property <tt>shopFlag24</tt>.
     *
     * @return property value of shopFlag24
     */
    public String getShopFlag24() {
        return shopFlag24;
    }

    /**
     * Setter method for property <tt>shopFlag24</tt>.
     *
     * @param shopFlag24 value to be assigned to property shopFlag24
     */
    public void setShopFlag24(String shopFlag24) {
        this.shopFlag24 = shopFlag24;
    }

    /**
     * Getter method for property <tt>shopFlag23</tt>.
     *
     * @return property value of shopFlag23
     */
    public String getShopFlag23() {
        return shopFlag23;
    }

    /**
     * Setter method for property <tt>shopFlag23</tt>.
     *
     * @param shopFlag23 value to be assigned to property shopFlag23
     */
    public void setShopFlag23(String shopFlag23) {
        this.shopFlag23 = shopFlag23;
    }

    /**
     * Getter method for property <tt>shopFlag22</tt>.
     *
     * @return property value of shopFlag22
     */
    public String getShopFlag22() {
        return shopFlag22;
    }

    /**
     * Setter method for property <tt>shopFlag22</tt>.
     *
     * @param shopFlag22 value to be assigned to property shopFlag22
     */
    public void setShopFlag22(String shopFlag22) {
        this.shopFlag22 = shopFlag22;
    }

    /**
     * Getter method for property <tt>shopFlag21</tt>.
     *
     * @return property value of shopFlag21
     */
    public String getShopFlag21() {
        return shopFlag21;
    }

    /**
     * Setter method for property <tt>shopFlag21</tt>.
     *
     * @param shopFlag21 value to be assigned to property shopFlag21
     */
    public void setShopFlag21(String shopFlag21) {
        this.shopFlag21 = shopFlag21;
    }

    /**
     * Getter method for property <tt>shopFlag20</tt>.
     *
     * @return property value of shopFlag20
     */
    public String getShopFlag20() {
        return shopFlag20;
    }

    /**
     * Setter method for property <tt>shopFlag20</tt>.
     *
     * @param shopFlag20 value to be assigned to property shopFlag20
     */
    public void setShopFlag20(String shopFlag20) {
        this.shopFlag20 = shopFlag20;
    }

    /**
     * Getter method for property <tt>shopFlag19</tt>.
     *
     * @return property value of shopFlag19
     */
    public String getShopFlag19() {
        return shopFlag19;
    }

    /**
     * Setter method for property <tt>shopFlag19</tt>.
     *
     * @param shopFlag19 value to be assigned to property shopFlag19
     */
    public void setShopFlag19(String shopFlag19) {
        this.shopFlag19 = shopFlag19;
    }

    /**
     * Getter method for property <tt>shopFlag18</tt>.
     *
     * @return property value of shopFlag18
     */
    public String getShopFlag18() {
        return shopFlag18;
    }

    /**
     * Setter method for property <tt>shopFlag18</tt>.
     *
     * @param shopFlag18 value to be assigned to property shopFlag18
     */
    public void setShopFlag18(String shopFlag18) {
        this.shopFlag18 = shopFlag18;
    }

    /**
     * Getter method for property <tt>shopFlag17</tt>.
     *
     * @return property value of shopFlag17
     */
    public String getShopFlag17() {
        return shopFlag17;
    }

    /**
     * Setter method for property <tt>shopFlag17</tt>.
     *
     * @param shopFlag17 value to be assigned to property shopFlag17
     */
    public void setShopFlag17(String shopFlag17) {
        this.shopFlag17 = shopFlag17;
    }

    /**
     * Getter method for property <tt>shopFlag16</tt>.
     *
     * @return property value of shopFlag16
     */
    public String getShopFlag16() {
        return shopFlag16;
    }

    /**
     * Setter method for property <tt>shopFlag16</tt>.
     *
     * @param shopFlag16 value to be assigned to property shopFlag16
     */
    public void setShopFlag16(String shopFlag16) {
        this.shopFlag16 = shopFlag16;
    }

    /**
     * Getter method for property <tt>shopFlag15</tt>.
     *
     * @return property value of shopFlag15
     */
    public String getShopFlag15() {
        return shopFlag15;
    }

    /**
     * Setter method for property <tt>shopFlag15</tt>.
     *
     * @param shopFlag15 value to be assigned to property shopFlag15
     */
    public void setShopFlag15(String shopFlag15) {
        this.shopFlag15 = shopFlag15;
    }

    /**
     * Getter method for property <tt>shopFlag14</tt>.
     *
     * @return property value of shopFlag14
     */
    public String getShopFlag14() {
        return shopFlag14;
    }

    /**
     * Setter method for property <tt>shopFlag14</tt>.
     *
     * @param shopFlag14 value to be assigned to property shopFlag14
     */
    public void setShopFlag14(String shopFlag14) {
        this.shopFlag14 = shopFlag14;
    }

    /**
     * Getter method for property <tt>shopFlag13</tt>.
     *
     * @return property value of shopFlag13
     */
    public String getShopFlag13() {
        return shopFlag13;
    }

    /**
     * Setter method for property <tt>shopFlag13</tt>.
     *
     * @param shopFlag13 value to be assigned to property shopFlag13
     */
    public void setShopFlag13(String shopFlag13) {
        this.shopFlag13 = shopFlag13;
    }

    /**
     * Getter method for property <tt>shopFlag12</tt>.
     *
     * @return property value of shopFlag12
     */
    public String getShopFlag12() {
        return shopFlag12;
    }

    /**
     * Setter method for property <tt>shopFlag12</tt>.
     *
     * @param shopFlag12 value to be assigned to property shopFlag12
     */
    public void setShopFlag12(String shopFlag12) {
        this.shopFlag12 = shopFlag12;
    }

    /**
     * Getter method for property <tt>shopFlag11</tt>.
     *
     * @return property value of shopFlag11
     */
    public String getShopFlag11() {
        return shopFlag11;
    }

    /**
     * Setter method for property <tt>shopFlag11</tt>.
     *
     * @param shopFlag11 value to be assigned to property shopFlag11
     */
    public void setShopFlag11(String shopFlag11) {
        this.shopFlag11 = shopFlag11;
    }

    /**
     * Getter method for property <tt>shopFlag10</tt>.
     *
     * @return property value of shopFlag10
     */
    public String getShopFlag10() {
        return shopFlag10;
    }

    /**
     * Setter method for property <tt>shopFlag10</tt>.
     *
     * @param shopFlag10 value to be assigned to property shopFlag10
     */
    public void setShopFlag10(String shopFlag10) {
        this.shopFlag10 = shopFlag10;
    }

    /**
     * Getter method for property <tt>shopFlag9</tt>.
     *
     * @return property value of shopFlag9
     */
    public String getShopFlag9() {
        return shopFlag9;
    }

    /**
     * Setter method for property <tt>shopFlag9</tt>.
     *
     * @param shopFlag9 value to be assigned to property shopFlag9
     */
    public void setShopFlag9(String shopFlag9) {
        this.shopFlag9 = shopFlag9;
    }

    /**
     * Getter method for property <tt>shopFlag8</tt>.
     *
     * @return property value of shopFlag8
     */
    public String getShopFlag8() {
        return shopFlag8;
    }

    /**
     * Setter method for property <tt>shopFlag8</tt>.
     *
     * @param shopFlag8 value to be assigned to property shopFlag8
     */
    public void setShopFlag8(String shopFlag8) {
        this.shopFlag8 = shopFlag8;
    }

    /**
     * Getter method for property <tt>shopFlag7</tt>.
     *
     * @return property value of shopFlag7
     */
    public String getShopFlag7() {
        return shopFlag7;
    }

    /**
     * Setter method for property <tt>shopFlag7</tt>.
     *
     * @param shopFlag7 value to be assigned to property shopFlag7
     */
    public void setShopFlag7(String shopFlag7) {
        this.shopFlag7 = shopFlag7;
    }

    /**
     * Getter method for property <tt>shopFlag6</tt>.
     *
     * @return property value of shopFlag6
     */
    public String getShopFlag6() {
        return shopFlag6;
    }

    /**
     * Setter method for property <tt>shopFlag6</tt>.
     *
     * @param shopFlag6 value to be assigned to property shopFlag6
     */
    public void setShopFlag6(String shopFlag6) {
        this.shopFlag6 = shopFlag6;
    }

    /**
     * Getter method for property <tt>shopFlag5</tt>.
     *
     * @return property value of shopFlag5
     */
    public String getShopFlag5() {
        return shopFlag5;
    }

    /**
     * Setter method for property <tt>shopFlag5</tt>.
     *
     * @param shopFlag5 value to be assigned to property shopFlag5
     */
    public void setShopFlag5(String shopFlag5) {
        this.shopFlag5 = shopFlag5;
    }

    /**
     * Getter method for property <tt>shopFlag4</tt>.
     *
     * @return property value of shopFlag4
     */
    public String getShopFlag4() {
        return shopFlag4;
    }

    /**
     * Setter method for property <tt>shopFlag4</tt>.
     *
     * @param shopFlag4 value to be assigned to property shopFlag4
     */
    public void setShopFlag4(String shopFlag4) {
        this.shopFlag4 = shopFlag4;
    }

    /**
     * Getter method for property <tt>shopFlag3</tt>.
     *
     * @return property value of shopFlag3
     */
    public String getShopFlag3() {
        return shopFlag3;
    }

    /**
     * Setter method for property <tt>shopFlag3</tt>.
     *
     * @param shopFlag3 value to be assigned to property shopFlag3
     */
    public void setShopFlag3(String shopFlag3) {
        this.shopFlag3 = shopFlag3;
    }

    /**
     * Getter method for property <tt>shopFlag2</tt>.
     *
     * @return property value of shopFlag2
     */
    public String getShopFlag2() {
        return shopFlag2;
    }

    /**
     * Setter method for property <tt>shopFlag2</tt>.
     *
     * @param shopFlag2 value to be assigned to property shopFlag2
     */
    public void setShopFlag2(String shopFlag2) {
        this.shopFlag2 = shopFlag2;
    }

    /**
     * Getter method for property <tt>shopFlag1</tt>.
     *
     * @return property value of shopFlag1
     */
    public String getShopFlag1() {
        return shopFlag1;
    }

    /**
     * Setter method for property <tt>shopFlag1</tt>.
     *
     * @param shopFlag1 value to be assigned to property shopFlag1
     */
    public void setShopFlag1(String shopFlag1) {
        this.shopFlag1 = shopFlag1;
    }

}
