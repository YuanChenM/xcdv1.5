/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_seller对应的SlSeller。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlSeller extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 卖家账号 */
    private String slAccount;
    /** 区划(6)+顺序码(4) */
    private String slCodeDis;
    /** SL_CODE_MANUFACTURE */
    private String slCodeManufacture;
    /** SL_CODE_SELF */
    private String slCodeSelf;
    /** SL_CODE_AGENT */
    private String slCodeAgent;
    /** SL_CODE_OEM */
    private String slCodeOem;
    /** 1：国产，2：进口 */
    private String slConFlg;
    /** 大区 */
    private String areaCode;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 省编码 */
    private String provinceCode;
    /** 地区编码 */
    private String cityCode;
    /** 区编码 */
    private String districtCode;
    /** 企业ID */
    private Long epId;
    /** 0.生产商1.自产型,2:代理型,3:OEM型 */
    private Integer slMainClass;
    /** 1.是 */
    private String snkFlg;
    /** 1.是 */
    private String selfFlg;
    /** 1.是 */
    private String agentFlg;
    /** 1.是 */
    private String oemFlg;
    /** 1.是 */
    private String buyerFlg;
    /** 0:未审核,1:审核中,2:审核通过,3:审核未通过 */
    private Integer sqaStatus;
    /** 0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家) */
    private Integer distQua;
    /** 0:无资格,1:有资格 */
    private String shopQua;
    /** 微信号码 */
    private String memo1;
    /** QQ号码 */
    private String memo2;
    /** 邮箱 */
    private String memo3;
    /** 1:行业型	2:创业型	3:关系型	4:配送型 */
    private String memo4;
    /** 传真号 */
    private String memo5;
    /** 1:行业型	2:创业型	3:关系型	4:配送型 */
    private String memo6;
    /** 身份证号 */
    private String memo7;
    /** 备用字段8 */
    private String memo8;
    /** 备用字段9 */
    private String memo9;
    /** 备用字段10 */
    private String memo10;
    /** 备用字段11 */
    private String memo11;
    /** 备用字段12 */
    private String memo12;
    /** 备用字段13 */
    private String memo13;
    /** 备用字段14 */
    private String memo14;
    /** 备用字段15 */
    private String memo15;
    /** 备用字段16 */
    private String memo16;
    /** 备用字段17 */
    private String memo17;
    /** 备用字段18 */
    private String memo18;
    /** 备用字段19 */
    private String memo19;
    /** 备用字段20 */
    private String memo20;
    /** 大区名称 */
    private String areaName;
    /** 物流区名称 */
    private String lgcsAreaName;
    /** 省名称 */
    private String provinceName;
    /** 地区名称 */
    private String cityName;
    /** 区名称 */
    private String districtName;
    /**
     * <p>默认构造函数。</p>
     */
    public SlSeller() {

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
     * <p>卖家账号。</p>
     *
     * @return the 卖家账号
     */
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * <p>卖家账号。</p>
     *
     * @param slAccount 卖家账号。
     */
    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCodeDis 区划(6)+顺序码(4)。
     */
    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * <p>SL_CODE_MANUFACTURE。</p>
     *
     * @return the SL_CODE_MANUFACTURE
     */
    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    /**
     * <p>SL_CODE_MANUFACTURE。</p>
     *
     * @param slCodeManufacture SL_CODE_MANUFACTURE。
     */
    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }

    /**
     * <p>SL_CODE_SELF。</p>
     *
     * @return the SL_CODE_SELF
     */
    public String getSlCodeSelf() {
        return slCodeSelf;
    }

    /**
     * <p>SL_CODE_SELF。</p>
     *
     * @param slCodeSelf SL_CODE_SELF。
     */
    public void setSlCodeSelf(String slCodeSelf) {
        this.slCodeSelf = slCodeSelf;
    }

    /**
     * <p>SL_CODE_AGENT。</p>
     *
     * @return the SL_CODE_AGENT
     */
    public String getSlCodeAgent() {
        return slCodeAgent;
    }

    /**
     * <p>SL_CODE_AGENT。</p>
     *
     * @param slCodeAgent SL_CODE_AGENT。
     */
    public void setSlCodeAgent(String slCodeAgent) {
        this.slCodeAgent = slCodeAgent;
    }

    /**
     * <p>SL_CODE_OEM。</p>
     *
     * @return the SL_CODE_OEM
     */
    public String getSlCodeOem() {
        return slCodeOem;
    }

    /**
     * <p>SL_CODE_OEM。</p>
     *
     * @param slCodeOem SL_CODE_OEM。
     */
    public void setSlCodeOem(String slCodeOem) {
        this.slCodeOem = slCodeOem;
    }

    /**
     * <p>1：国产，2：进口。</p>
     *
     * @return the 1：国产，2：进口
     */
    public String getSlConFlg() {
        return slConFlg;
    }

    /**
     * <p>1：国产，2：进口。</p>
     *
     * @param slConFlg 1：国产，2：进口。
     */
    public void setSlConFlg(String slConFlg) {
        this.slConFlg = slConFlg;
    }

    /**
     * <p>大区。</p>
     *
     * @return the 大区
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * <p>大区。</p>
     *
     * @param areaCode 大区。
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param lgcsAreaCode 物流区编码。
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param provinceCode 省编码。
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param cityCode 地区编码。
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @return the 区编码
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @param districtCode 区编码。
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>企业ID。</p>
     *
     * @return the 企业ID
     */
    public Long getEpId() {
        return epId;
    }

    /**
     * <p>企业ID。</p>
     *
     * @param epId 企业ID。
     */
    public void setEpId(Long epId) {
        this.epId = epId;
    }

    /**
     * <p>0.生产商1.自产型,2:代理型,3:OEM型。</p>
     *
     * @return the 0.生产商1.自产型,2:代理型,3:OEM型
     */
    public Integer getSlMainClass() {
        return slMainClass;
    }

    /**
     * <p>0.生产商1.自产型,2:代理型,3:OEM型。</p>
     *
     * @param slMainClass 0.生产商1.自产型,2:代理型,3:OEM型。
     */
    public void setSlMainClass(Integer slMainClass) {
        this.slMainClass = slMainClass;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public String getSnkFlg() {
        return snkFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param snkFlg 1.是。
     */
    public void setSnkFlg(String snkFlg) {
        this.snkFlg = snkFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public String getSelfFlg() {
        return selfFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param selfFlg 1.是。
     */
    public void setSelfFlg(String selfFlg) {
        this.selfFlg = selfFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public String getAgentFlg() {
        return agentFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param agentFlg 1.是。
     */
    public void setAgentFlg(String agentFlg) {
        this.agentFlg = agentFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public String getOemFlg() {
        return oemFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param oemFlg 1.是。
     */
    public void setOemFlg(String oemFlg) {
        this.oemFlg = oemFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @return the 1.是
     */
    public String getBuyerFlg() {
        return buyerFlg;
    }

    /**
     * <p>1.是。</p>
     *
     * @param buyerFlg 1.是。
     */
    public void setBuyerFlg(String buyerFlg) {
        this.buyerFlg = buyerFlg;
    }

    /**
     * <p>0:未审核,1:审核中,2:审核通过,3:审核未通过。</p>
     *
     * @return the 0:未审核,1:审核中,2:审核通过,3:审核未通过
     */
    public Integer getSqaStatus() {
        return sqaStatus;
    }

    /**
     * <p>0:未审核,1:审核中,2:审核通过,3:审核未通过。</p>
     *
     * @param sqaStatus 0:未审核,1:审核中,2:审核通过,3:审核未通过。
     */
    public void setSqaStatus(Integer sqaStatus) {
        this.sqaStatus = sqaStatus;
    }

    /**
     * <p>0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)。</p>
     *
     * @return the 0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)
     */
    public Integer getDistQua() {
        return distQua;
    }

    /**
     * <p>0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)。</p>
     *
     * @param distQua 0:无资格,1:标准分销卖家,2:优良分销卖家,3:重点分销卖家(同意分销章程后变为标准分销卖家)。
     */
    public void setDistQua(Integer distQua) {
        this.distQua = distQua;
    }

    /**
     * <p>0:无资格,1:有资格。</p>
     *
     * @return the 0:无资格,1:有资格
     */
    public String getShopQua() {
        return shopQua;
    }

    /**
     * <p>0:无资格,1:有资格。</p>
     *
     * @param shopQua 0:无资格,1:有资格。
     */
    public void setShopQua(String shopQua) {
        this.shopQua = shopQua;
    }

    /**
     * <p>微信号码。</p>
     *
     * @return the 微信号码
     */
    public String getMemo1() {
        return memo1;
    }

    /**
     * <p>微信号码。</p>
     *
     * @param memo1 微信号码。
     */
    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    /**
     * <p>QQ号码。</p>
     *
     * @return the QQ号码
     */
    public String getMemo2() {
        return memo2;
    }

    /**
     * <p>QQ号码。</p>
     *
     * @param memo2 QQ号码。
     */
    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    /**
     * <p>邮箱。</p>
     *
     * @return the 邮箱
     */
    public String getMemo3() {
        return memo3;
    }

    /**
     * <p>邮箱。</p>
     *
     * @param memo3 邮箱。
     */
    public void setMemo3(String memo3) {
        this.memo3 = memo3;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @return the 1:行业型	2:创业型	3:关系型	4:配送型
     */
    public String getMemo4() {
        return memo4;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @param memo4 1:行业型	2:创业型	3:关系型	4:配送型。
     */
    public void setMemo4(String memo4) {
        this.memo4 = memo4;
    }

    /**
     * <p>传真号。</p>
     *
     * @return the 传真号
     */
    public String getMemo5() {
        return memo5;
    }

    /**
     * <p>传真号。</p>
     *
     * @param memo5 传真号。
     */
    public void setMemo5(String memo5) {
        this.memo5 = memo5;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @return the 1:行业型	2:创业型	3:关系型	4:配送型
     */
    public String getMemo6() {
        return memo6;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @param memo6 1:行业型	2:创业型	3:关系型	4:配送型。
     */
    public void setMemo6(String memo6) {
        this.memo6 = memo6;
    }

    /**
     * <p>身份证号。</p>
     *
     * @return the 身份证号
     */
    public String getMemo7() {
        return memo7;
    }

    /**
     * <p>身份证号。</p>
     *
     * @param memo7 身份证号。
     */
    public void setMemo7(String memo7) {
        this.memo7 = memo7;
    }

    /**
     * <p>备用字段8。</p>
     *
     * @return the 备用字段8
     */
    public String getMemo8() {
        return memo8;
    }

    /**
     * <p>备用字段8。</p>
     *
     * @param memo8 备用字段8。
     */
    public void setMemo8(String memo8) {
        this.memo8 = memo8;
    }

    /**
     * <p>备用字段9。</p>
     *
     * @return the 备用字段9
     */
    public String getMemo9() {
        return memo9;
    }

    /**
     * <p>备用字段9。</p>
     *
     * @param memo9 备用字段9。
     */
    public void setMemo9(String memo9) {
        this.memo9 = memo9;
    }

    /**
     * <p>备用字段10。</p>
     *
     * @return the 备用字段10
     */
    public String getMemo10() {
        return memo10;
    }

    /**
     * <p>备用字段10。</p>
     *
     * @param memo10 备用字段10。
     */
    public void setMemo10(String memo10) {
        this.memo10 = memo10;
    }

    /**
     * <p>备用字段11。</p>
     *
     * @return the 备用字段11
     */
    public String getMemo11() {
        return memo11;
    }

    /**
     * <p>备用字段11。</p>
     *
     * @param memo11 备用字段11。
     */
    public void setMemo11(String memo11) {
        this.memo11 = memo11;
    }

    /**
     * <p>备用字段12。</p>
     *
     * @return the 备用字段12
     */
    public String getMemo12() {
        return memo12;
    }

    /**
     * <p>备用字段12。</p>
     *
     * @param memo12 备用字段12。
     */
    public void setMemo12(String memo12) {
        this.memo12 = memo12;
    }

    /**
     * <p>备用字段13。</p>
     *
     * @return the 备用字段13
     */
    public String getMemo13() {
        return memo13;
    }

    /**
     * <p>备用字段13。</p>
     *
     * @param memo13 备用字段13。
     */
    public void setMemo13(String memo13) {
        this.memo13 = memo13;
    }

    /**
     * <p>备用字段14。</p>
     *
     * @return the 备用字段14
     */
    public String getMemo14() {
        return memo14;
    }

    /**
     * <p>备用字段14。</p>
     *
     * @param memo14 备用字段14。
     */
    public void setMemo14(String memo14) {
        this.memo14 = memo14;
    }

    /**
     * <p>备用字段15。</p>
     *
     * @return the 备用字段15
     */
    public String getMemo15() {
        return memo15;
    }

    /**
     * <p>备用字段15。</p>
     *
     * @param memo15 备用字段15。
     */
    public void setMemo15(String memo15) {
        this.memo15 = memo15;
    }

    /**
     * <p>备用字段16。</p>
     *
     * @return the 备用字段16
     */
    public String getMemo16() {
        return memo16;
    }

    /**
     * <p>备用字段16。</p>
     *
     * @param memo16 备用字段16。
     */
    public void setMemo16(String memo16) {
        this.memo16 = memo16;
    }

    /**
     * <p>备用字段17。</p>
     *
     * @return the 备用字段17
     */
    public String getMemo17() {
        return memo17;
    }

    /**
     * <p>备用字段17。</p>
     *
     * @param memo17 备用字段17。
     */
    public void setMemo17(String memo17) {
        this.memo17 = memo17;
    }

    /**
     * <p>备用字段18。</p>
     *
     * @return the 备用字段18
     */
    public String getMemo18() {
        return memo18;
    }

    /**
     * <p>备用字段18。</p>
     *
     * @param memo18 备用字段18。
     */
    public void setMemo18(String memo18) {
        this.memo18 = memo18;
    }

    /**
     * <p>备用字段19。</p>
     *
     * @return the 备用字段19
     */
    public String getMemo19() {
        return memo19;
    }

    /**
     * <p>备用字段19。</p>
     *
     * @param memo19 备用字段19。
     */
    public void setMemo19(String memo19) {
        this.memo19 = memo19;
    }

    /**
     * <p>备用字段20。</p>
     *
     * @return the 备用字段20
     */
    public String getMemo20() {
        return memo20;
    }

    /**
     * <p>备用字段20。</p>
     *
     * @param memo20 备用字段20。
     */
    public void setMemo20(String memo20) {
        this.memo20 = memo20;
    }

    /**
     * <p>大区名称。</p>
     *
     * @return the 大区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * <p>大区名称。</p>
     *
     * @param areaName 大区名称。
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @param lgcsAreaName 物流区名称。
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * <p>省名称。</p>
     *
     * @return the 省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * <p>省名称。</p>
     *
     * @param provinceName 省名称。
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * <p>地区名称。</p>
     *
     * @return the 地区名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * <p>地区名称。</p>
     *
     * @param cityName 地区名称。
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>区名称。</p>
     *
     * @return the 区名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * <p>区名称。</p>
     *
     * @param districtName 区名称。
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
