package com.msk.seller.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.*;
import com.msk.core.entity.SlEpHonor;
import com.msk.core.entity.SlEpAgentAuth;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.msk.core.entity.SlAccount;

/**
 * Created by Administrator on 2016/3/16.
 */
/*@JsonIgnoreProperties(value={"crtId","crtTime","updId","updTime","delFlg","actId","actTime"})*/
public class ISL231181Result {

    //卖家产品类别
    private List<SlPdClasses> pdClassesCodeList;
    //企业专业资质
    private List<SlEpCertParam> certInfoList;
    //企业荣誉信息List
    private List<SlEpHonor> slEpHonorList;
    //企业产品品牌
    private List<SL2411030033Bean> slEpBrandList;
    //卖家产品品牌
    private List<SlPdBrand> slPdBrandList;
    //企业车间
    private List<SlEpWorkshop> slEpWorkshopList;
    //生产商
    private List<SlEpAgentAuth> slEpAuthList;
    //企业管理团队
    private List<SlEpManager> slEpManagerList;
    //卖家电商团队
    private List<SlEcTeam> slEcTeamList;
    //卖家账户信息
    private SlAccount slAccount;
    //卖家基本信息
    private ISL231181RegionParam slSeller;
    //企业基本资质
    private SlEnterprise slEnterprise;
    //企业生产能力
    private SlEpCap slEpCap;
    //企业检测设备
    private List<SlEpDdBean> slEpDdList;

    /**
     * Getter method for property <tt>pdClassesCodeList</tt>.
     *
     * @return property value of pdClassesCodeList
     */
    public List<SlPdClasses> getPdClassesCodeList() {
        return pdClassesCodeList;
    }

    /**
     * Setter method for property <tt>pdClassesCodeList</tt>.
     *
     * @param pdClassesCodeList value to be assigned to property pdClassesCodeList
     */
    public void setPdClassesCodeList(List<SlPdClasses> pdClassesCodeList) {
        this.pdClassesCodeList = pdClassesCodeList;
    }

    /**
     * Getter method for property <tt>certInfoList</tt>.
     *
     * @return property value of certInfoList
     */
    public List<SlEpCertParam> getCertInfoList() {
        return certInfoList;
    }

    /**
     * Setter method for property <tt>certInfoList</tt>.
     *
     * @param certInfoList value to be assigned to property certInfoList
     */
    public void setCertInfoList(List<SlEpCertParam> certInfoList) {
        this.certInfoList = certInfoList;
    }

    /**
     * Getter method for property <tt>slEpHonorList</tt>.
     *
     * @return property value of slEpHonorList
     */
    public List<SlEpHonor> getSlEpHonorList() {
        return slEpHonorList;
    }

    /**
     * Setter method for property <tt>slEpHonorList</tt>.
     *
     * @param slEpHonorList value to be assigned to property slEpHonorList
     */
    public void setSlEpHonorList(List<SlEpHonor> slEpHonorList) {
        this.slEpHonorList = slEpHonorList;
    }

    /**
     * Getter method for property <tt>slEpBrandList</tt>.
     *
     * @return property value of slEpBrandList
     */
    public List<SL2411030033Bean> getSlEpBrandList() {
        return slEpBrandList;
    }

    /**
     * Setter method for property <tt>slEpBrandList</tt>.
     *
     * @param slEpBrandList value to be assigned to property slEpBrandList
     */
    public void setSlEpBrandList(List<SL2411030033Bean> slEpBrandList) {
        this.slEpBrandList = slEpBrandList;
    }


    /**
     * Getter method for property <tt>slPdBrandList</tt>.
     *
     * @return property value of slPdBrandList
     */
    public List<SlPdBrand> getSlPdBrandList() {
        return slPdBrandList;
    }

    /**
     * Setter method for property <tt>slPdBrandList</tt>.
     *
     * @param slPdBrandList value to be assigned to property slPdBrandList
     */
    public void setSlPdBrandList(List<SlPdBrand> slPdBrandList) {
        this.slPdBrandList = slPdBrandList;
    }

    /**
     * Getter method for property <tt>slEpWorkshopList</tt>.
     *
     * @return property value of slEpWorkshopList
     */
    public List<SlEpWorkshop> getSlEpWorkshopList() {
        return slEpWorkshopList;
    }

    /**
     * Setter method for property <tt>slEpWorkshopList</tt>.
     *
     * @param slEpWorkshopList value to be assigned to property slEpWorkshopList
     */
    public void setSlEpWorkshopList(List<SlEpWorkshop> slEpWorkshopList) {
        this.slEpWorkshopList = slEpWorkshopList;
    }

    /**
     * Getter method for property <tt>slEpAuthList</tt>.
     *
     * @return property value of slEpAuthList
     */
    public List<SlEpAgentAuth> getSlEpAuthList() {
        return slEpAuthList;
    }

    /**
     * Setter method for property <tt>slEpAuthList</tt>.
     *
     * @param slEpAuthList value to be assigned to property slEpAuthList
     */
    public void setSlEpAuthList(List<SlEpAgentAuth> slEpAuthList) {
        this.slEpAuthList = slEpAuthList;
    }

    /**
     * Getter method for property <tt>slEpManagerList</tt>.
     *
     * @return property value of slEpManagerList
     */
    public List<SlEpManager> getSlEpManagerList() {
        return slEpManagerList;
    }

    /**
     * Setter method for property <tt>slEpManagerList</tt>.
     *
     * @param slEpManagerList value to be assigned to property slEpManagerList
     */
    public void setSlEpManagerList(List<SlEpManager> slEpManagerList) {
        this.slEpManagerList = slEpManagerList;
    }

    /**
     * Getter method for property <tt>slEcTeamList</tt>.
     *
     * @return property value of slEcTeamList
     */
    public List<SlEcTeam> getSlEcTeamList() {
        return slEcTeamList;
    }

    /**
     * Setter method for property <tt>slEcTeamList</tt>.
     *
     * @param slEcTeamList value to be assigned to property slEcTeamList
     */
    public void setSlEcTeamList(List<SlEcTeam> slEcTeamList) {
        this.slEcTeamList = slEcTeamList;
    }


    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode

    public String getSlCode() {
        return slCode;
    }

    *
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    *
     * Getter method for property <tt>slConFlg</tt>.
     *
     * @return property value of slConFlg

    public String getSlConFlg() {
        return slConFlg;
    }

    *
     * Setter method for property <tt>slConFlg</tt>.
     *
     * @param slConFlg value to be assigned to property slConFlg

    public void setSlConFlg(String slConFlg) {
        this.slConFlg = slConFlg;
    }

    *
     * Getter method for property <tt>areaCode</tt>.
     *
     * @return property value of areaCode

    public String getAreaCode() {
        return areaCode;
    }

    *
     * Setter method for property <tt>areaCode</tt>.
     *
     * @param areaCode value to be assigned to property areaCode

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    *
     * Getter method for property <tt>areaName</tt>.
     *
     * @return property value of areaName

    public String getAreaName() {
        return areaName;
    }

    *
     * Setter method for property <tt>areaName</tt>.
     *
     * @param areaName value to be assigned to property areaName

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    *
     * Getter method for property <tt>lgcsAreaCode</tt>.
     *
     * @return property value of lgcsAreaCode

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    *
     * Setter method for property <tt>lgcsAreaCode</tt>.
     *
     * @param lgcsAreaCode value to be assigned to property lgcsAreaCode

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    *
     * Getter method for property <tt>lgcsAreaName</tt>.
     *
     * @return property value of lgcsAreaName

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    *
     * Setter method for property <tt>lgcsAreaName</tt>.
     *
     * @param lgcsAreaName value to be assigned to property lgcsAreaName

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    *
     * Getter method for property <tt>provinceCode</tt>.
     *
     * @return property value of provinceCode

    public String getProvinceCode() {
        return provinceCode;
    }

    *
     * Setter method for property <tt>provinceCode</tt>.
     *
     * @param provinceCode value to be assigned to property provinceCode

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    *
     * Getter method for property <tt>provinceName</tt>.
     *
     * @return property value of provinceName

    public String getProvinceName() {
        return provinceName;
    }

    *
     * Setter method for property <tt>provinceName</tt>.
     *
     * @param provinceName value to be assigned to property provinceName

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    *
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode

    public String getCityCode() {
        return cityCode;
    }

    *
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    *
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName

    public String getCityName() {
        return cityName;
    }

    *
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    *
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode

    public String getDistrictCode() {
        return districtCode;
    }

    *
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    *
     * Getter method for property <tt>districtName</tt>.
     *
     * @return property value of districtName

    public String getDistrictName() {
        return districtName;
    }

    *
     * Setter method for property <tt>districtName</tt>.
     *
     * @param districtName value to be assigned to property districtName

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    *
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId

    public Long getEpId() {
        return epId;
    }

    *
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId

    public void setEpId(Long epId) {
        this.epId = epId;
    }

    *
     * Getter method for property <tt>slMainClass</tt>.
     *
     * @return property value of slMainClass

    public Integer getSlMainClass() {
        return slMainClass;
    }

    *
     * Setter method for property <tt>slMainClass</tt>.
     *
     * @param slMainClass value to be assigned to property slMainClass

    public void setSlMainClass(Integer slMainClass) {
        this.slMainClass = slMainClass;
    }

    *
     * Getter method for property <tt>snkFlg</tt>.
     *
     * @return property value of snkFlg

    public String getSnkFlg() {
        return snkFlg;
    }

    *
     * Setter method for property <tt>snkFlg</tt>.
     *
     * @param snkFlg value to be assigned to property snkFlg

    public void setSnkFlg(String snkFlg) {
        this.snkFlg = snkFlg;
    }

    *
     * Getter method for property <tt>selfFlg</tt>.
     *
     * @return property value of selfFlg

    public String getSelfFlg() {
        return selfFlg;
    }

    *
     * Setter method for property <tt>selfFlg</tt>.
     *
     * @param selfFlg value to be assigned to property selfFlg

    public void setSelfFlg(String selfFlg) {
        this.selfFlg = selfFlg;
    }

    *
     * Getter method for property <tt>agentFlg</tt>.
     *
     * @return property value of agentFlg

    public String getAgentFlg() {
        return agentFlg;
    }

    *
     * Setter method for property <tt>agentFlg</tt>.
     *
     * @param agentFlg value to be assigned to property agentFlg

    public void setAgentFlg(String agentFlg) {
        this.agentFlg = agentFlg;
    }

    *
     * Getter method for property <tt>oemFlg</tt>.
     *
     * @return property value of oemFlg

    public String getOemFlg() {
        return oemFlg;
    }

    *
     * Setter method for property <tt>oemFlg</tt>.
     *
     * @param oemFlg value to be assigned to property oemFlg

    public void setOemFlg(String oemFlg) {
        this.oemFlg = oemFlg;
    }

    *
     * Getter method for property <tt>buyerFlg</tt>.
     *
     * @return property value of buyerFlg

    public String getBuyerFlg() {
        return buyerFlg;
    }

    *
     * Setter method for property <tt>buyerFlg</tt>.
     *
     * @param buyerFlg value to be assigned to property buyerFlg

    public void setBuyerFlg(String buyerFlg) {
        this.buyerFlg = buyerFlg;
    }

    *
     * Getter method for property <tt>sqaStatus</tt>.
     *
     * @return property value of sqaStatus

    public Integer getSqaStatus() {
        return sqaStatus;
    }

    *
     * Setter method for property <tt>sqaStatus</tt>.
     *
     * @param sqaStatus value to be assigned to property sqaStatus

    public void setSqaStatus(Integer sqaStatus) {
        this.sqaStatus = sqaStatus;
    }

    *
     * Getter method for property <tt>distQua</tt>.
     *
     * @return property value of distQua

    public Integer getDistQua() {
        return distQua;
    }

    *
     * Setter method for property <tt>distQua</tt>.
     *
     * @param distQua value to be assigned to property distQua

    public void setDistQua(Integer distQua) {
        this.distQua = distQua;
    }

    *
     * Getter method for property <tt>shopQua</tt>.
     *
     * @return property value of shopQua

    public String getShopQua() {
        return shopQua;
    }

    *
     * Setter method for property <tt>shopQua</tt>.
     *
     * @param shopQua value to be assigned to property shopQua

    public void setShopQua(String shopQua) {
        this.shopQua = shopQua;
    }

    *
     * Getter method for property <tt>memo1</tt>.
     *
     * @return property value of memo1

    public String getMemo1() {
        return memo1;
    }

    *
     * Setter method for property <tt>memo1</tt>.
     *
     * @param memo1 value to be assigned to property memo1

    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    *
     * Getter method for property <tt>memo2</tt>.
     *
     * @return property value of memo2

    public String getMemo2() {
        return memo2;
    }

    *
     * Setter method for property <tt>memo2</tt>.
     *
     * @param memo2 value to be assigned to property memo2

    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    *
     * Getter method for property <tt>memo3</tt>.
     *
     * @return property value of memo3

    public String getMemo3() {
        return memo3;
    }

    *
     * Setter method for property <tt>memo3</tt>.
     *
     * @param memo3 value to be assigned to property memo3

    public void setMemo3(String memo3) {
        this.memo3 = memo3;
    }

    *
     * Getter method for property <tt>memo4</tt>.
     *
     * @return property value of memo4

    public String getMemo4() {
        return memo4;
    }

    *
     * Setter method for property <tt>memo4</tt>.
     *
     * @param memo4 value to be assigned to property memo4

    public void setMemo4(String memo4) {
        this.memo4 = memo4;
    }

    *
     * Getter method for property <tt>memo5</tt>.
     *
     * @return property value of memo5

    public String getMemo5() {
        return memo5;
    }

    *
     * Setter method for property <tt>memo5</tt>.
     *
     * @param memo5 value to be assigned to property memo5

    public void setMemo5(String memo5) {
        this.memo5 = memo5;
    }

    *
     * Getter method for property <tt>memo6</tt>.
     *
     * @return property value of memo6

    public String getMemo6() {
        return memo6;
    }

    *
     * Setter method for property <tt>memo6</tt>.
     *
     * @param memo6 value to be assigned to property memo6

    public void setMemo6(String memo6) {
        this.memo6 = memo6;
    }

    *
     * Getter method for property <tt>memo7</tt>.
     *
     * @return property value of memo7

    public String getMemo7() {
        return memo7;
    }

    *
     * Setter method for property <tt>memo7</tt>.
     *
     * @param memo7 value to be assigned to property memo7

    public void setMemo7(String memo7) {
        this.memo7 = memo7;
    }

    *
     * Getter method for property <tt>memo8</tt>.
     *
     * @return property value of memo8

    public String getMemo8() {
        return memo8;
    }

    *
     * Setter method for property <tt>memo8</tt>.
     *
     * @param memo8 value to be assigned to property memo8

    public void setMemo8(String memo8) {
        this.memo8 = memo8;
    }

    *
     * Getter method for property <tt>memo9</tt>.
     *
     * @return property value of memo9

    public String getMemo9() {
        return memo9;
    }

    *
     * Setter method for property <tt>memo9</tt>.
     *
     * @param memo9 value to be assigned to property memo9

    public void setMemo9(String memo9) {
        this.memo9 = memo9;
    }

    *
     * Getter method for property <tt>memo10</tt>.
     *
     * @return property value of memo10

    public String getMemo10() {
        return memo10;
    }

    *
     * Setter method for property <tt>memo10</tt>.
     *
     * @param memo10 value to be assigned to property memo10

    public void setMemo10(String memo10) {
        this.memo10 = memo10;
    }

    *
     * Getter method for property <tt>memo11</tt>.
     *
     * @return property value of memo11

    public String getMemo11() {
        return memo11;
    }

    *
     * Setter method for property <tt>memo11</tt>.
     *
     * @param memo11 value to be assigned to property memo11

    public void setMemo11(String memo11) {
        this.memo11 = memo11;
    }

    *
     * Getter method for property <tt>memo12</tt>.
     *
     * @return property value of memo12

    public String getMemo12() {
        return memo12;
    }

    *
     * Setter method for property <tt>memo12</tt>.
     *
     * @param memo12 value to be assigned to property memo12

    public void setMemo12(String memo12) {
        this.memo12 = memo12;
    }

    *
     * Getter method for property <tt>memo13</tt>.
     *
     * @return property value of memo13

    public String getMemo13() {
        return memo13;
    }

    *
     * Setter method for property <tt>memo13</tt>.
     *
     * @param memo13 value to be assigned to property memo13

    public void setMemo13(String memo13) {
        this.memo13 = memo13;
    }

    *
     * Getter method for property <tt>memo14</tt>.
     *
     * @return property value of memo14

    public String getMemo14() {
        return memo14;
    }

    *
     * Setter method for property <tt>memo14</tt>.
     *
     * @param memo14 value to be assigned to property memo14

    public void setMemo14(String memo14) {
        this.memo14 = memo14;
    }

    *
     * Getter method for property <tt>memo15</tt>.
     *
     * @return property value of memo15

    public String getMemo15() {
        return memo15;
    }

    *
     * Setter method for property <tt>memo15</tt>.
     *
     * @param memo15 value to be assigned to property memo15

    public void setMemo15(String memo15) {
        this.memo15 = memo15;
    }

    *
     * Getter method for property <tt>memo16</tt>.
     *
     * @return property value of memo16

    public String getMemo16() {
        return memo16;
    }

    *
     * Setter method for property <tt>memo16</tt>.
     *
     * @param memo16 value to be assigned to property memo16

    public void setMemo16(String memo16) {
        this.memo16 = memo16;
    }

    *
     * Getter method for property <tt>memo17</tt>.
     *
     * @return property value of memo17

    public String getMemo17() {
        return memo17;
    }

    *
     * Setter method for property <tt>memo17</tt>.
     *
     * @param memo17 value to be assigned to property memo17

    public void setMemo17(String memo17) {
        this.memo17 = memo17;
    }

    *
     * Getter method for property <tt>memo18</tt>.
     *
     * @return property value of memo18

    public String getMemo18() {
        return memo18;
    }

    *
     * Setter method for property <tt>memo18</tt>.
     *
     * @param memo18 value to be assigned to property memo18

    public void setMemo18(String memo18) {
        this.memo18 = memo18;
    }

    *
     * Getter method for property <tt>memo19</tt>.
     *
     * @return property value of memo19

    public String getMemo19() {
        return memo19;
    }

    *
     * Setter method for property <tt>memo19</tt>.
     *
     * @param memo19 value to be assigned to property memo19

    public void setMemo19(String memo19) {
        this.memo19 = memo19;
    }

    *
     * Getter method for property <tt>memo20</tt>.
     *
     * @return property value of memo20

    public String getMemo20() {
        return memo20;
    }

    *
     * Setter method for property <tt>memo20</tt>.
     *
     * @param memo20 value to be assigned to property memo20

    public void setMemo20(String memo20) {
        this.memo20 = memo20;
    }

    *
     * Getter method for property <tt>epName</tt>.
     *
     * @return property value of epName

    public String getEpName() {
        return epName;
    }

    *
     * Setter method for property <tt>epName</tt>.
     *
     * @param epName value to be assigned to property epName

    public void setEpName(String epName) {
        this.epName = epName;
    }

    *
     * Getter method for property <tt>licType</tt>.
     *
     * @return property value of licType

    public String getLicType() {
        return licType;
    }

    *
     * Setter method for property <tt>licType</tt>.
     *
     * @param licType value to be assigned to property licType

    public void setLicType(String licType) {
        this.licType = licType;
    }

    *
     * Getter method for property <tt>licName</tt>.
     *
     * @return property value of licName

    public String getLicName() {
        return licName;
    }

    *
     * Setter method for property <tt>licName</tt>.
     *
     * @param licName value to be assigned to property licName

    public void setLicName(String licName) {
        this.licName = licName;
    }

    *
     * Getter method for property <tt>licNo</tt>.
     *
     * @return property value of licNo

    public String getLicNo() {
        return licNo;
    }

    *
     * Setter method for property <tt>licNo</tt>.
     *
     * @param licNo value to be assigned to property licNo

    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    *
     * Getter method for property <tt>licAddr</tt>.
     *
     * @return property value of licAddr

    public String getLicAddr() {
        return licAddr;
    }

    *
     * Setter method for property <tt>licAddr</tt>.
     *
     * @param licAddr value to be assigned to property licAddr

    public void setLicAddr(String licAddr) {
        this.licAddr = licAddr;
    }

    *
     * Getter method for property <tt>licBusiType</tt>.
     *
     * @return property value of licBusiType

    public String getLicBusiType() {
        return licBusiType;
    }

    *
     * Setter method for property <tt>licBusiType</tt>.
     *
     * @param licBusiType value to be assigned to property licBusiType

    public void setLicBusiType(String licBusiType) {
        this.licBusiType = licBusiType;
    }

    *
     * Getter method for property <tt>licBusiScope</tt>.
     *
     * @return property value of licBusiScope

    public String getLicBusiScope() {
        return licBusiScope;
    }

    *
     * Setter method for property <tt>licBusiScope</tt>.
     *
     * @param licBusiScope value to be assigned to property licBusiScope

    public void setLicBusiScope(String licBusiScope) {
        this.licBusiScope = licBusiScope;
    }

    *
     * Getter method for property <tt>licLegalPerson</tt>.
     *
     * @return property value of licLegalPerson

    public String getLicLegalPerson() {
        return licLegalPerson;
    }

    *
     * Setter method for property <tt>licLegalPerson</tt>.
     *
     * @param licLegalPerson value to be assigned to property licLegalPerson

    public void setLicLegalPerson(String licLegalPerson) {
        this.licLegalPerson = licLegalPerson;
    }

    *
     * Getter method for property <tt>licRegCapital</tt>.
     *
     * @return property value of licRegCapital

    public BigDecimal getLicRegCapital() {
        return licRegCapital;
    }

    *
     * Setter method for property <tt>licRegCapital</tt>.
     *
     * @param licRegCapital value to be assigned to property licRegCapital

    public void setLicRegCapital(BigDecimal licRegCapital) {
        this.licRegCapital = licRegCapital;
    }

    *
     * Getter method for property <tt>licPaidinCapital</tt>.
     *
     * @return property value of licPaidinCapital

    public BigDecimal getLicPaidinCapital() {
        return licPaidinCapital;
    }

    *
     * Setter method for property <tt>licPaidinCapital</tt>.
     *
     * @param licPaidinCapital value to be assigned to property licPaidinCapital

    public void setLicPaidinCapital(BigDecimal licPaidinCapital) {
        this.licPaidinCapital = licPaidinCapital;
    }

    *
     * Getter method for property <tt>licCrtDate</tt>.
     *
     * @return property value of licCrtDate

    public Date getLicCrtDate() {
        return licCrtDate;
    }

    *
     * Setter method for property <tt>licCrtDate</tt>.
     *
     * @param licCrtDate value to be assigned to property licCrtDate

    public void setLicCrtDate(Date licCrtDate) {
        this.licCrtDate = licCrtDate;
    }

    *
     * Getter method for property <tt>licTermBegin</tt>.
     *
     * @return property value of licTermBegin

    public Date getLicTermBegin() {
        return licTermBegin;
    }

    *
     * Setter method for property <tt>licTermBegin</tt>.
     *
     * @param licTermBegin value to be assigned to property licTermBegin

    public void setLicTermBegin(Date licTermBegin) {
        this.licTermBegin = licTermBegin;
    }

    *
     * Getter method for property <tt>licTermEnd</tt>.
     *
     * @return property value of licTermEnd

    public Date getLicTermEnd() {
        return licTermEnd;
    }

    *
     * Setter method for property <tt>licTermEnd</tt>.
     *
     * @param licTermEnd value to be assigned to property licTermEnd

    public void setLicTermEnd(Date licTermEnd) {
        this.licTermEnd = licTermEnd;
    }

    *
     * Getter method for property <tt>licTermUnliimited</tt>.
     *
     * @return property value of licTermUnliimited

    public String getLicTermUnliimited() {
        return licTermUnliimited;
    }

    *
     * Setter method for property <tt>licTermUnliimited</tt>.
     *
     * @param licTermUnliimited value to be assigned to property licTermUnliimited

    public void setLicTermUnliimited(String licTermUnliimited) {
        this.licTermUnliimited = licTermUnliimited;
    }

    *
     * Getter method for property <tt>taxNo</tt>.
     *
     * @return property value of taxNo

    public String getTaxNo() {
        return taxNo;
    }

    *
     * Setter method for property <tt>taxNo</tt>.
     *
     * @param taxNo value to be assigned to property taxNo

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    *
     * Getter method for property <tt>taxVatNo</tt>.
     *
     * @return property value of taxVatNo

    public String getTaxVatNo() {
        return taxVatNo;
    }

    *
     * Setter method for property <tt>taxVatNo</tt>.
     *
     * @param taxVatNo value to be assigned to property taxVatNo

    public void setTaxVatNo(String taxVatNo) {
        this.taxVatNo = taxVatNo;
    }

    *
     * Getter method for property <tt>orgNo</tt>.
     *
     * @return property value of orgNo

    public String getOrgNo() {
        return orgNo;
    }

    *
     * Setter method for property <tt>orgNo</tt>.
     *
     * @param orgNo value to be assigned to property orgNo

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    *
     * Getter method for property <tt>orgTermBegin</tt>.
     *
     * @return property value of orgTermBegin

    public Date getOrgTermBegin() {
        return orgTermBegin;
    }

    *
     * Setter method for property <tt>orgTermBegin</tt>.
     *
     * @param orgTermBegin value to be assigned to property orgTermBegin

    public void setOrgTermBegin(Date orgTermBegin) {
        this.orgTermBegin = orgTermBegin;
    }

    *
     * Getter method for property <tt>orgTermEnd</tt>.
     *
     * @return property value of orgTermEnd

    public Date getOrgTermEnd() {
        return orgTermEnd;
    }

    *
     * Setter method for property <tt>orgTermEnd</tt>.
     *
     * @param orgTermEnd value to be assigned to property orgTermEnd

    public void setOrgTermEnd(Date orgTermEnd) {
        this.orgTermEnd = orgTermEnd;
    }

    *
     * Getter method for property <tt>balLegalPerson</tt>.
     *
     * @return property value of balLegalPerson

    public String getBalLegalPerson() {
        return balLegalPerson;
    }

    *
     * Setter method for property <tt>balLegalPerson</tt>.
     *
     * @param balLegalPerson value to be assigned to property balLegalPerson

    public void setBalLegalPerson(String balLegalPerson) {
        this.balLegalPerson = balLegalPerson;
    }

    *
     * Getter method for property <tt>balBank</tt>.
     *
     * @return property value of balBank

    public String getBalBank() {
        return balBank;
    }

    *
     * Setter method for property <tt>balBank</tt>.
     *
     * @param balBank value to be assigned to property balBank

    public void setBalBank(String balBank) {
        this.balBank = balBank;
    }

    *
     * Getter method for property <tt>balAccount</tt>.
     *
     * @return property value of balAccount

    public String getBalAccount() {
        return balAccount;
    }

    *
     * Setter method for property <tt>balAccount</tt>.
     *
     * @param balAccount value to be assigned to property balAccount

    public void setBalAccount(String balAccount) {
        this.balAccount = balAccount;
    }

    *
     * Getter method for property <tt>fdlNo</tt>.
     *
     * @return property value of fdlNo

    public String getFdlNo() {
        return fdlNo;
    }

    *
     * Setter method for property <tt>fdlNo</tt>.
     *
     * @param fdlNo value to be assigned to property fdlNo

    public void setFdlNo(String fdlNo) {
        this.fdlNo = fdlNo;
    }

    *
     * Getter method for property <tt>fdlTermBegin</tt>.
     *
     * @return property value of fdlTermBegin

    public Date getFdlTermBegin() {
        return fdlTermBegin;
    }

    *
     * Setter method for property <tt>fdlTermBegin</tt>.
     *
     * @param fdlTermBegin value to be assigned to property fdlTermBegin

    public void setFdlTermBegin(Date fdlTermBegin) {
        this.fdlTermBegin = fdlTermBegin;
    }

    *
     * Getter method for property <tt>fdlTermEnd</tt>.
     *
     * @return property value of fdlTermEnd

    public Date getFdlTermEnd() {
        return fdlTermEnd;
    }

    *
     * Setter method for property <tt>fdlTermEnd</tt>.
     *
     * @param fdlTermEnd value to be assigned to property fdlTermEnd

    public void setFdlTermEnd(Date fdlTermEnd) {
        this.fdlTermEnd = fdlTermEnd;
    }

    *
     * Getter method for property <tt>ftyAsset</tt>.
     *
     * @return property value of ftyAsset

    public BigDecimal getFtyAsset() {
        return ftyAsset;
    }

    *
     * Setter method for property <tt>ftyAsset</tt>.
     *
     * @param ftyAsset value to be assigned to property ftyAsset

    public void setFtyAsset(BigDecimal ftyAsset) {
        this.ftyAsset = ftyAsset;
    }

    *
     * Getter method for property <tt>ftyRegCapital</tt>.
     *
     * @return property value of ftyRegCapital

    public BigDecimal getFtyRegCapital() {
        return ftyRegCapital;
    }

    *
     * Setter method for property <tt>ftyRegCapital</tt>.
     *
     * @param ftyRegCapital value to be assigned to property ftyRegCapital

    public void setFtyRegCapital(BigDecimal ftyRegCapital) {
        this.ftyRegCapital = ftyRegCapital;
    }

    *
     * Getter method for property <tt>ftyLandArea</tt>.
     *
     * @return property value of ftyLandArea

    public BigDecimal getFtyLandArea() {
        return ftyLandArea;
    }

    *
     * Setter method for property <tt>ftyLandArea</tt>.
     *
     * @param ftyLandArea value to be assigned to property ftyLandArea

    public void setFtyLandArea(BigDecimal ftyLandArea) {
        this.ftyLandArea = ftyLandArea;
    }

    *
     * Getter method for property <tt>ftyFloorArea</tt>.
     *
     * @return property value of ftyFloorArea

    public BigDecimal getFtyFloorArea() {
        return ftyFloorArea;
    }

    *
     * Setter method for property <tt>ftyFloorArea</tt>.
     *
     * @param ftyFloorArea value to be assigned to property ftyFloorArea

    public void setFtyFloorArea(BigDecimal ftyFloorArea) {
        this.ftyFloorArea = ftyFloorArea;
    }

    *
     * Getter method for property <tt>ftyEquipment</tt>.
     *
     * @return property value of ftyEquipment

    public String getFtyEquipment() {
        return ftyEquipment;
    }

    *
     * Setter method for property <tt>ftyEquipment</tt>.
     *
     * @param ftyEquipment value to be assigned to property ftyEquipment

    public void setFtyEquipment(String ftyEquipment) {
        this.ftyEquipment = ftyEquipment;
    }

    *
     * Getter method for property <tt>ftyDesignCap</tt>.
     *
     * @return property value of ftyDesignCap

    public String getFtyDesignCap() {
        return ftyDesignCap;
    }

    *
     * Setter method for property <tt>ftyDesignCap</tt>.
     *
     * @param ftyDesignCap value to be assigned to property ftyDesignCap

    public void setFtyDesignCap(String ftyDesignCap) {
        this.ftyDesignCap = ftyDesignCap;
    }

    *
     * Getter method for property <tt>ftyActualCap</tt>.
     *
     * @return property value of ftyActualCap

    public String getFtyActualCap() {
        return ftyActualCap;
    }

    *
     * Setter method for property <tt>ftyActualCap</tt>.
     *
     * @param ftyActualCap value to be assigned to property ftyActualCap

    public void setFtyActualCap(String ftyActualCap) {
        this.ftyActualCap = ftyActualCap;
    }

    *
     * Getter method for property <tt>ftyFtRate</tt>.
     *
     * @return property value of ftyFtRate

    public BigDecimal getFtyFtRate() {
        return ftyFtRate;
    }

    *
     * Setter method for property <tt>ftyFtRate</tt>.
     *
     * @param ftyFtRate value to be assigned to property ftyFtRate

    public void setFtyFtRate(BigDecimal ftyFtRate) {
        this.ftyFtRate = ftyFtRate;
    }

    *
     * Getter method for property <tt>ftyDsRate</tt>.
     *
     * @return property value of ftyDsRate

    public BigDecimal getFtyDsRate() {
        return ftyDsRate;
    }

    *
     * Setter method for property <tt>ftyDsRate</tt>.
     *
     * @param ftyDsRate value to be assigned to property ftyDsRate

    public void setFtyDsRate(BigDecimal ftyDsRate) {
        this.ftyDsRate = ftyDsRate;
    }

    *
     * Getter method for property <tt>ftyAsRate</tt>.
     *
     * @return property value of ftyAsRate

    public BigDecimal getFtyAsRate() {
        return ftyAsRate;
    }

    *
     * Setter method for property <tt>ftyAsRate</tt>.
     *
     * @param ftyAsRate value to be assigned to property ftyAsRate

    public void setFtyAsRate(BigDecimal ftyAsRate) {
        this.ftyAsRate = ftyAsRate;
    }

    *
     * Getter method for property <tt>scapMaterial</tt>.
     *
     * @return property value of scapMaterial

    public BigDecimal getScapMaterial() {
        return scapMaterial;
    }

    *
     * Setter method for property <tt>scapMaterial</tt>.
     *
     * @param scapMaterial value to be assigned to property scapMaterial

    public void setScapMaterial(BigDecimal scapMaterial) {
        this.scapMaterial = scapMaterial;
    }

    *
     * Getter method for property <tt>scapProduct</tt>.
     *
     * @return property value of scapProduct

    public BigDecimal getScapProduct() {
        return scapProduct;
    }

    *
     * Setter method for property <tt>scapProduct</tt>.
     *
     * @param scapProduct value to be assigned to property scapProduct

    public void setScapProduct(BigDecimal scapProduct) {
        this.scapProduct = scapProduct;
    }

    *
     * Getter method for property <tt>labArea</tt>.
     *
     * @return property value of labArea

    public BigDecimal getLabArea() {
        return labArea;
    }

    *
     * Setter method for property <tt>labArea</tt>.
     *
     * @param labArea value to be assigned to property labArea

    public void setLabArea(BigDecimal labArea) {
        this.labArea = labArea;
    }

    *
     * Getter method for property <tt>labFunction</tt>.
     *
     * @return property value of labFunction

    public String getLabFunction() {
        return labFunction;
    }

    *
     * Setter method for property <tt>labFunction</tt>.
     *
     * @param labFunction value to be assigned to property labFunction

    public void setLabFunction(String labFunction) {
        this.labFunction = labFunction;
    }

    *
     * Getter method for property <tt>labInvestment</tt>.
     *
     * @return property value of labInvestment

    public BigDecimal getLabInvestment() {
        return labInvestment;
    }

    *
     * Setter method for property <tt>labInvestment</tt>.
     *
     * @param labInvestment value to be assigned to property labInvestment

    public void setLabInvestment(BigDecimal labInvestment) {
        this.labInvestment = labInvestment;
    }

    *
     * Getter method for property <tt>labMember</tt>.
     *
     * @return property value of labMember

    public Integer getLabMember() {
        return labMember;
    }

    *
     * Setter method for property <tt>labMember</tt>.
     *
     * @param labMember value to be assigned to property labMember

    public void setLabMember(Integer labMember) {
        this.labMember = labMember;
    }

    *
     * Getter method for property <tt>ddEquipment</tt>.
     *
     * @return property value of ddEquipment

    public String getDdEquipment() {
        return ddEquipment;
    }

    *
     * Setter method for property <tt>ddEquipment</tt>.
     *
     * @param ddEquipment value to be assigned to property ddEquipment

    public void setDdEquipment(String ddEquipment) {
        this.ddEquipment = ddEquipment;
    }
*/
    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    public SlAccount getSlAccount() {
        return slAccount;
    }

    /**
     * Setter method for property <tt>slAccount</tt>.
     *
     * @param slAccount value to be assigned to property slAccount
     */
    public void setSlAccount(SlAccount slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * Getter method for property <tt>slSeller</tt>.
     *
     * @return property value of slSeller
     */
    public ISL231181RegionParam getSlSeller() {
        return slSeller;
    }

    /**
     * Setter method for property <tt>slSeller</tt>.
     *
     * @param slSeller value to be assigned to property slSeller
     */
    public void setSlSeller(ISL231181RegionParam slSeller) {
        this.slSeller = slSeller;
    }

    /**
     * Getter method for property <tt>slEnterprise</tt>.
     *
     * @return property value of slEnterprise
     */
    public SlEnterprise getSlEnterprise() {
        return slEnterprise;
    }

    /**
     * Setter method for property <tt>slEnterprise</tt>.
     *
     * @param slEnterprise value to be assigned to property slEnterprise
     */
    public void setSlEnterprise(SlEnterprise slEnterprise) {
        this.slEnterprise = slEnterprise;
    }

    /**
     * Getter method for property <tt>slEpCap</tt>.
     *
     * @return property value of slEpCap
     */
    public SlEpCap getSlEpCap() {
        return slEpCap;
    }

    /**
     * Setter method for property <tt>slEpCap</tt>.
     *
     * @param slEpCap value to be assigned to property slEpCap
     */
    public void setSlEpCap(SlEpCap slEpCap) {
        this.slEpCap = slEpCap;
    }

    /**
     * Getter method for property <tt>slEpDdList</tt>.
     *
     * @return property value of slEpDdList
     */
    public List<SlEpDdBean> getSlEpDdList() {
        return slEpDdList;
    }

    /**
     * Setter method for property <tt>slEpDdList</tt>.
     *
     * @param slEpDdList value to be assigned to property slEpDdList
     */
    public void setSlEpDdList(List<SlEpDdBean> slEpDdList) {
        this.slEpDdList = slEpDdList;
    }
}
