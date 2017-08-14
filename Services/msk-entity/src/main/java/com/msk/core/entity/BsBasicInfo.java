/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表bs_basic_info对应的BsBasicInfo。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BsBasicInfo extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private java.lang.String slCode;
    /** SL_ACCOUNT */
    private java.lang.String slAccount;
    /** 区划(6)+顺序码(4) */
    private java.lang.String slCodeDis;
    /** 区划(3)+顺序码(3)，共6位 */
    private java.lang.String slCodeManufacture;
    /** SL_CODE_SELF */
    private java.lang.String slCodeSelf;
    /** SL_CODE_AGENT */
    private java.lang.String slCodeAgent;
    /** SL_CODE_OEM */
    private java.lang.String slCodeOem;
    /** 1：国产，2：进口 */
    private java.lang.String slConFlg;
    /** 大区 */
    private java.lang.String areaCode;
    /** AREA_NAME */
    private java.lang.String areaName;
    /** 物流区编码 */
    private java.lang.String lgcsAreaCode;
    /** LGCS_AREA_NAME */
    private java.lang.String lgcsAreaName;
    /** 省编码 */
    private java.lang.String provinceCode;
    /** PROVINCE_NAME */
    private java.lang.String provinceName;
    /** 地区编码 */
    private java.lang.String cityCode;
    /** CITY_NAME */
    private java.lang.String cityName;
    /** 区编码 */
    private java.lang.String districtCode;
    /** DISTRICT_NAME */
    private java.lang.String districtName;
    /** EP_ID */
    private java.lang.Long epId;
    /** 0.生产商1.自产型,2:代理型,3:OEM型 */
    private java.lang.Integer slMainClass;
    /** 1.是 */
    private java.lang.String snkFlg;
    /** 1.是 */
    private java.lang.String selfFlg;
    /** 1.是 */
    private java.lang.String agentFlg;
    /** 1.是 */
    private java.lang.String oemFlg;
    /** 1.是 */
    private java.lang.String buyerFlg;
    /** 0:未审核,1:审核中,2:审核通过,3:审核未通过 */
    private java.lang.Integer sqaStatus;
    /** 0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家) */
    private java.lang.Integer distQua;
    /** 0:无资格,1:有资格 */
    private java.lang.String shopQua;
    private java.lang.String agentType;
    private java.lang.String distribution;
    private java.lang.String demesne;
    private java.lang.String registerSource;
    /** HK_SEQ */
    private java.lang.String hkSeq;
    /** MEMO1 */
    private java.lang.String memo1;
    /** MEMO2 */
    private java.lang.String memo2;
    /** MEMO3 */
    private java.lang.String memo3;
    /** MEMO4 */
    private java.lang.String memo4;
    /** MEMO5 */
    private java.lang.String memo5;
    /** 1:行业型	2:创业型	3:关系型	4:配送型 */
    private java.lang.String memo6;
    /** MEMO7 */
    private java.lang.String memo7;
    /** 买手二级分类:职业&职务 */
    private java.lang.String memo8;
    /** 买手顺序码 */
    private java.lang.String memo9;
    /** MEMO10 */
    private java.lang.String memo10;
    /** MEMO11 */
    private java.lang.String memo11;
    /** MEMO12 */
    private java.lang.String memo12;
    /** MEMO13 */
    private java.lang.String memo13;
    /** MEMO14 */
    private java.lang.String memo14;
    /** MEMO15 */
    private java.lang.String memo15;
    /** MEMO16 */
    private java.lang.String memo16;
    /** MEMO17 */
    private java.lang.String memo17;
    /** MEMO18 */
    private java.lang.String memo18;
    /** MEMO19 */
    private java.lang.String memo19;
    /** MEMO20 */
    private java.lang.String memo20;
    /**
     * <p>默认构造函数。</p>
     */
    public BsBasicInfo() {

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
     * <p>SL_ACCOUNT。</p>
     *
     * @return the SL_ACCOUNT
     */
    public java.lang.String getSlAccount() {
        return slAccount;
    }

    /**
     * <p>SL_ACCOUNT。</p>
     *
     * @param slAccount SL_ACCOUNT。
     */
    public void setSlAccount(java.lang.String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public java.lang.String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCodeDis 区划(6)+顺序码(4)。
     */
    public void setSlCodeDis(java.lang.String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * <p>区划(3)+顺序码(3)，共6位。</p>
     *
     * @return the 区划(3)+顺序码(3)，共6位
     */
    public java.lang.String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    /**
     * <p>区划(3)+顺序码(3)，共6位。</p>
     *
     * @param slCodeManufacture 区划(3)+顺序码(3)，共6位。
     */
    public void setSlCodeManufacture(java.lang.String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    /**
     * <p>SL_CODE_SELF。</p>
     *
     * @return the SL_CODE_SELF
     */
    public java.lang.String getSlCodeSelf() {
        return slCodeSelf;
    }

    /**
     * <p>SL_CODE_SELF。</p>
     *
     * @param slCodeSelf SL_CODE_SELF。
     */
    public void setSlCodeSelf(java.lang.String slCodeSelf) {
        this.slCodeSelf = slCodeSelf;
    }

    /**
     * <p>SL_CODE_AGENT。</p>
     *
     * @return the SL_CODE_AGENT
     */
    public java.lang.String getSlCodeAgent() {
        return slCodeAgent;
    }

    /**
     * <p>SL_CODE_AGENT。</p>
     *
     * @param slCodeAgent SL_CODE_AGENT。
     */
    public void setSlCodeAgent(java.lang.String slCodeAgent) {
        this.slCodeAgent = slCodeAgent;
    }

    /**
     * <p>SL_CODE_OEM。</p>
     *
     * @return the SL_CODE_OEM
     */
    public java.lang.String getSlCodeOem() {
        return slCodeOem;
    }

    /**
     * <p>SL_CODE_OEM。</p>
     *
     * @param slCodeOem SL_CODE_OEM。
     */
    public void setSlCodeOem(java.lang.String slCodeOem) {
        this.slCodeOem = slCodeOem;
    }

    /**
     * <p>1：国产，2：进口。</p>
     *
     * @return the 1：国产，2：进口
     */
    public java.lang.String getSlConFlg() {
        return slConFlg;
    }

    /**
     * <p>1：国产，2：进口。</p>
     *
     * @param slConFlg 1：国产，2：进口。
     */
    public void setSlConFlg(java.lang.String slConFlg) {
        this.slConFlg = slConFlg;
    }

    /**
     * <p>大区。</p>
     *
     * @return the 大区
     */
    public java.lang.String getAreaCode() {
        return areaCode;
    }

    /**
     * <p>大区。</p>
     *
     * @param areaCode 大区。
     */
    public void setAreaCode(java.lang.String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * <p>AREA_NAME。</p>
     *
     * @return the AREA_NAME
     */
    public java.lang.String getAreaName() {
        return areaName;
    }

    /**
     * <p>AREA_NAME。</p>
     *
     * @param areaName AREA_NAME。
     */
    public void setAreaName(java.lang.String areaName) {
        this.areaName = areaName;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public java.lang.String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param lgcsAreaCode 物流区编码。
     */
    public void setLgcsAreaCode(java.lang.String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_NAME。</p>
     *
     * @return the LGCS_AREA_NAME
     */
    public java.lang.String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>LGCS_AREA_NAME。</p>
     *
     * @param lgcsAreaName LGCS_AREA_NAME。
     */
    public void setLgcsAreaName(java.lang.String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public java.lang.String getProvinceCode() {
        return provinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param provinceCode 省编码。
     */
    public void setProvinceCode(java.lang.String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * <p>PROVINCE_NAME。</p>
     *
     * @return the PROVINCE_NAME
     */
    public java.lang.String getProvinceName() {
        return provinceName;
    }

    /**
     * <p>PROVINCE_NAME。</p>
     *
     * @param provinceName PROVINCE_NAME。
     */
    public void setProvinceName(java.lang.String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public java.lang.String getCityCode() {
        return cityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param cityCode 地区编码。
     */
    public void setCityCode(java.lang.String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>CITY_NAME。</p>
     *
     * @return the CITY_NAME
     */
    public java.lang.String getCityName() {
        return cityName;
    }

    /**
     * <p>CITY_NAME。</p>
     *
     * @param cityName CITY_NAME。
     */
    public void setCityName(java.lang.String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>区编码。</p>
     *
     * @return the 区编码
     */
    public java.lang.String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @param districtCode 区编码。
     */
    public void setDistrictCode(java.lang.String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>DISTRICT_NAME。</p>
     *
     * @return the DISTRICT_NAME
     */
    public java.lang.String getDistrictName() {
        return districtName;
    }

    /**
     * <p>DISTRICT_NAME。</p>
     *
     * @param districtName DISTRICT_NAME。
     */
    public void setDistrictName(java.lang.String districtName) {
        this.districtName = districtName;
    }

    /**
     * <p>EP_ID。</p>
     *
     * @return the EP_ID
     */
    public java.lang.Long getEpId() {
        return epId;
    }

    /**
     * <p>EP_ID。</p>
     *
     * @param epId EP_ID。
     */
    public void setEpId(java.lang.Long epId) {
        this.epId = epId;
    }

    /**
     * <p>0.生产商1.自产型,2:代理型,3:OEM型。</p>
     *
     * @return the 0.生产商1.自产型,2:代理型,3:OEM型
     */
    public java.lang.Integer getSlMainClass() {
        return slMainClass;
    }

    /**
     * <p>0.生产商1.自产型,2:代理型,3:OEM型。</p>
     *
     * @param slMainClass 0.生产商1.自产型,2:代理型,3:OEM型。
     */
    public void setSlMainClass(java.lang.Integer slMainClass) {
        this.slMainClass = slMainClass;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public java.lang.String getSnkFlg() {
        return snkFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param snkFlg 1.是。
     */
    public void setSnkFlg(java.lang.String snkFlg) {
        this.snkFlg = snkFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public java.lang.String getSelfFlg() {
        return selfFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param selfFlg 1.是。
     */
    public void setSelfFlg(java.lang.String selfFlg) {
        this.selfFlg = selfFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public java.lang.String getAgentFlg() {
        return agentFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param agentFlg 1.是。
     */
    public void setAgentFlg(java.lang.String agentFlg) {
        this.agentFlg = agentFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public java.lang.String getOemFlg() {
        return oemFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param oemFlg 1.是。
     */
    public void setOemFlg(java.lang.String oemFlg) {
        this.oemFlg = oemFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public java.lang.String getBuyerFlg() {
        return buyerFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param buyerFlg 1.是。
     */
    public void setBuyerFlg(java.lang.String buyerFlg) {
        this.buyerFlg = buyerFlg;
    }

    /**
     * <p>0:未审核,1:审核中,2:审核通过,3:审核未通过。</p>
     *
     * @return the 0:未审核,1:审核中,2:审核通过,3:审核未通过
     */
    public java.lang.Integer getSqaStatus() {
        return sqaStatus;
    }

    /**
     * <p>0:未审核,1:审核中,2:审核通过,3:审核未通过。</p>
     *
     * @param sqaStatus 0:未审核,1:审核中,2:审核通过,3:审核未通过。
     */
    public void setSqaStatus(java.lang.Integer sqaStatus) {
        this.sqaStatus = sqaStatus;
    }

    /**
     * <p>0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)。</p>
     *
     * @return the 0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)
     */
    public java.lang.Integer getDistQua() {
        return distQua;
    }

    /**
     * <p>0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)。</p>
     *
     * @param distQua 0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)。
     */
    public void setDistQua(java.lang.Integer distQua) {
        this.distQua = distQua;
    }

    /**
     * <p>0:无资格,1:有资格。</p>
     *
     * @return the 0:无资格,1:有资格
     */
    public java.lang.String getShopQua() {
        return shopQua;
    }

    /**
     * <p>0:无资格,1:有资格。</p>
     *
     * @param shopQua 0:无资格,1:有资格。
     */
    public void setShopQua(java.lang.String shopQua) {
        this.shopQua = shopQua;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getDemesne() {
        return demesne;
    }

    public void setDemesne(String demesne) {
        this.demesne = demesne;
    }

    public String getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }

    /**
     * <p>HK_SEQ。</p>
     *
     * @return the HK_SEQ
     */
    public java.lang.String getHkSeq() {
        return hkSeq;
    }

    /**
     * <p>HK_SEQ。</p>
     *
     * @param hkSeq HK_SEQ。
     */
    public void setHkSeq(java.lang.String hkSeq) {
        this.hkSeq = hkSeq;
    }

    /**
     * <p>MEMO1。</p>
     *
     * @return the MEMO1
     */
    public java.lang.String getMemo1() {
        return memo1;
    }

    /**
     * <p>MEMO1。</p>
     *
     * @param memo1 MEMO1。
     */
    public void setMemo1(java.lang.String memo1) {
        this.memo1 = memo1;
    }

    /**
     * <p>MEMO2。</p>
     *
     * @return the MEMO2
     */
    public java.lang.String getMemo2() {
        return memo2;
    }

    /**
     * <p>MEMO2。</p>
     *
     * @param memo2 MEMO2。
     */
    public void setMemo2(java.lang.String memo2) {
        this.memo2 = memo2;
    }

    /**
     * <p>MEMO3。</p>
     *
     * @return the MEMO3
     */
    public java.lang.String getMemo3() {
        return memo3;
    }

    /**
     * <p>MEMO3。</p>
     *
     * @param memo3 MEMO3。
     */
    public void setMemo3(java.lang.String memo3) {
        this.memo3 = memo3;
    }

    /**
     * <p>MEMO4。</p>
     *
     * @return the MEMO4
     */
    public java.lang.String getMemo4() {
        return memo4;
    }

    /**
     * <p>MEMO4。</p>
     *
     * @param memo4 MEMO4。
     */
    public void setMemo4(java.lang.String memo4) {
        this.memo4 = memo4;
    }

    /**
     * <p>MEMO5。</p>
     *
     * @return the MEMO5
     */
    public java.lang.String getMemo5() {
        return memo5;
    }

    /**
     * <p>MEMO5。</p>
     *
     * @param memo5 MEMO5。
     */
    public void setMemo5(java.lang.String memo5) {
        this.memo5 = memo5;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @return the 1:行业型	2:创业型	3:关系型	4:配送型
     */
    public java.lang.String getMemo6() {
        return memo6;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @param memo6 1:行业型	2:创业型	3:关系型	4:配送型。
     */
    public void setMemo6(java.lang.String memo6) {
        this.memo6 = memo6;
    }

    /**
     * <p>MEMO7。</p>
     *
     * @return the MEMO7
     */
    public java.lang.String getMemo7() {
        return memo7;
    }

    /**
     * <p>MEMO7。</p>
     *
     * @param memo7 MEMO7。
     */
    public void setMemo7(java.lang.String memo7) {
        this.memo7 = memo7;
    }

    /**
     * <p>买手二级分类:职业&职务。</p>
     *
     * @return the 买手二级分类:职业&职务
     */
    public java.lang.String getMemo8() {
        return memo8;
    }

    /**
     * <p>买手二级分类:职业&职务。</p>
     *
     * @param memo8 买手二级分类:职业&职务。
     */
    public void setMemo8(java.lang.String memo8) {
        this.memo8 = memo8;
    }

    /**
     * <p>买手顺序码。</p>
     *
     * @return the 买手顺序码
     */
    public java.lang.String getMemo9() {
        return memo9;
    }

    /**
     * <p>买手顺序码。</p>
     *
     * @param memo9 买手顺序码。
     */
    public void setMemo9(java.lang.String memo9) {
        this.memo9 = memo9;
    }

    /**
     * <p>MEMO10。</p>
     *
     * @return the MEMO10
     */
    public java.lang.String getMemo10() {
        return memo10;
    }

    /**
     * <p>MEMO10。</p>
     *
     * @param memo10 MEMO10。
     */
    public void setMemo10(java.lang.String memo10) {
        this.memo10 = memo10;
    }

    /**
     * <p>MEMO11。</p>
     *
     * @return the MEMO11
     */
    public java.lang.String getMemo11() {
        return memo11;
    }

    /**
     * <p>MEMO11。</p>
     *
     * @param memo11 MEMO11。
     */
    public void setMemo11(java.lang.String memo11) {
        this.memo11 = memo11;
    }

    /**
     * <p>MEMO12。</p>
     *
     * @return the MEMO12
     */
    public java.lang.String getMemo12() {
        return memo12;
    }

    /**
     * <p>MEMO12。</p>
     *
     * @param memo12 MEMO12。
     */
    public void setMemo12(java.lang.String memo12) {
        this.memo12 = memo12;
    }

    /**
     * <p>MEMO13。</p>
     *
     * @return the MEMO13
     */
    public java.lang.String getMemo13() {
        return memo13;
    }

    /**
     * <p>MEMO13。</p>
     *
     * @param memo13 MEMO13。
     */
    public void setMemo13(java.lang.String memo13) {
        this.memo13 = memo13;
    }

    /**
     * <p>MEMO14。</p>
     *
     * @return the MEMO14
     */
    public java.lang.String getMemo14() {
        return memo14;
    }

    /**
     * <p>MEMO14。</p>
     *
     * @param memo14 MEMO14。
     */
    public void setMemo14(java.lang.String memo14) {
        this.memo14 = memo14;
    }

    /**
     * <p>MEMO15。</p>
     *
     * @return the MEMO15
     */
    public java.lang.String getMemo15() {
        return memo15;
    }

    /**
     * <p>MEMO15。</p>
     *
     * @param memo15 MEMO15。
     */
    public void setMemo15(java.lang.String memo15) {
        this.memo15 = memo15;
    }

    /**
     * <p>MEMO16。</p>
     *
     * @return the MEMO16
     */
    public java.lang.String getMemo16() {
        return memo16;
    }

    /**
     * <p>MEMO16。</p>
     *
     * @param memo16 MEMO16。
     */
    public void setMemo16(java.lang.String memo16) {
        this.memo16 = memo16;
    }

    /**
     * <p>MEMO17。</p>
     *
     * @return the MEMO17
     */
    public java.lang.String getMemo17() {
        return memo17;
    }

    /**
     * <p>MEMO17。</p>
     *
     * @param memo17 MEMO17。
     */
    public void setMemo17(java.lang.String memo17) {
        this.memo17 = memo17;
    }

    /**
     * <p>MEMO18。</p>
     *
     * @return the MEMO18
     */
    public java.lang.String getMemo18() {
        return memo18;
    }

    /**
     * <p>MEMO18。</p>
     *
     * @param memo18 MEMO18。
     */
    public void setMemo18(java.lang.String memo18) {
        this.memo18 = memo18;
    }

    /**
     * <p>MEMO19。</p>
     *
     * @return the MEMO19
     */
    public java.lang.String getMemo19() {
        return memo19;
    }

    /**
     * <p>MEMO19。</p>
     *
     * @param memo19 MEMO19。
     */
    public void setMemo19(java.lang.String memo19) {
        this.memo19 = memo19;
    }

    /**
     * <p>MEMO20。</p>
     *
     * @return the MEMO20
     */
    public java.lang.String getMemo20() {
        return memo20;
    }

    /**
     * <p>MEMO20。</p>
     *
     * @param memo20 MEMO20。
     */
    public void setMemo20(java.lang.String memo20) {
        this.memo20 = memo20;
    }

}
