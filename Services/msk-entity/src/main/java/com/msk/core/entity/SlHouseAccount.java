/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_account对应的SlHouseAccount。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseAccount extends BaseEntity{
    /**
     * 
     */
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
    /** 加密后的值 */
    private String accountPsd;
    /** 0:未认证,1:认证中,2:已认证 */
    private Integer authStatus;
    /** SL_IDCARD */
    private String slIdcard;
    /** 1：国产，2：进口 */
    private String slConFlg;
    /** 大区 */
    private String rareaCode;
    /** 物流区编码 */
    private String rlgcsAreaCode;
    /** 省编码 */
    private String rprovinceCode;
    /** 地区编码 */
    private String rcityCode;
    /** 区编码 */
    private String rdistrictCode;
    /** R_HOUSE_ADDRESS */
    private String rhouseAddress;
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
    /** HOUSE_ADDRESS */
    private String houseAddress;
    /** 大区 */
    private String vareaCode;
    /** 物流区编码 */
    private String vlgcsAreaCode;
    /** 省编码 */
    private String vprovinceCode;
    /** 地区编码 */
    private String vcityCode;
    /** 区编码 */
    private String vdistrictCode;
    /** V_HOUSE_ADDRESS */
    private String vhouseAddress;
    /** LAT */
    private String lat;
    /** LON */
    private String lon;
    /** V_LAT */
    private String vlat;
    /** V_LON */
    private String vlon;
    /** 1:专业冻品管家资格未申请 2:专业冻品管家资格申请中 3:专业冻品管家资格 */
    private String licenses;
    /** 0:没签署，1：签署 */
    private String buyerAsign;
    /** WECHAT */
    private String wechat;
    /** QQ */
    private String qq;
    /** EMAIL */
    private String email;
    /** 1:行业型	2:创业型	3:关系型	4:配送型 */
    private String fixedTel;
    /** FAX */
    private String fax;
    /** FLAG20 */
    private String flag20;
    /** FLAG19 */
    private String flag19;
    /** FLAG18 */
    private String flag18;
    /** FLAG17 */
    private String flag17;
    /** FLAG16 */
    private String flag16;
    /** FLAG15 */
    private String flag15;
    /** FLAG14 */
    private String flag14;
    /** FLAG13 */
    private String flag13;
    /** FLAG12 */
    private String flag12;
    /** FLAG11 */
    private String flag11;
    /** FLAG10 */
    private String flag10;
    /** FLAG9 */
    private String flag9;
    /** FLAG8 */
    private String flag8;
    /** FLAG7 */
    private String flag7;
    /** 备注 */
    private String flag6;
    /** FLAG5 */
    private String flag5;
    /** FLAG4 */
    private String flag4;
    /** FLAG3 */
    private String flag3;
    /** FLAG2 */
    private String flag2;
    /** FLAG1 */
    private String flag1;
    /** HOUSE_INTRODUCE */
    private String houseIntroduce;
    /** 1：店主，2：店员 */
    private String houseClass;
    /** 管家一级分类 */
    private String houseCategory;
    /** 管家二级分类 */
    private String houseCategorySub;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory0;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory1;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory2;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory3;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory4;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory5;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory6;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory7;
    /** 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂 */
    private String houseCategory8;
    /** 等级 */
    private String houseGreade;
    /** 星级 */
    private java.math.BigDecimal houseStar;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseAccount() {

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
     * <p>加密后的值。</p>
     *
     * @return the 加密后的值
     */
    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @param accountPsd 加密后的值。
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @return the 0:未认证,1:认证中,2:已认证
     */
    public Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @param authStatus 0:未认证,1:认证中,2:已认证。
     */
    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * <p>SL_IDCARD。</p>
     *
     * @return the SL_IDCARD
     */
    public String getSlIdcard() {
        return slIdcard;
    }

    /**
     * <p>SL_IDCARD。</p>
     *
     * @param slIdcard SL_IDCARD。
     */
    public void setSlIdcard(String slIdcard) {
        this.slIdcard = slIdcard;
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
    public String getRareaCode() {
        return rareaCode;
    }

    /**
     * <p>大区。</p>
     *
     * @param rareaCode 大区。
     */
    public void setRareaCode(String rareaCode) {
        this.rareaCode = rareaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getRlgcsAreaCode() {
        return rlgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param rlgcsAreaCode 物流区编码。
     */
    public void setRlgcsAreaCode(String rlgcsAreaCode) {
        this.rlgcsAreaCode = rlgcsAreaCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public String getRprovinceCode() {
        return rprovinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param rprovinceCode 省编码。
     */
    public void setRprovinceCode(String rprovinceCode) {
        this.rprovinceCode = rprovinceCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public String getRcityCode() {
        return rcityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param rcityCode 地区编码。
     */
    public void setRcityCode(String rcityCode) {
        this.rcityCode = rcityCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @return the 区编码
     */
    public String getRdistrictCode() {
        return rdistrictCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @param rdistrictCode 区编码。
     */
    public void setRdistrictCode(String rdistrictCode) {
        this.rdistrictCode = rdistrictCode;
    }

    /**
     * <p>R_HOUSE_ADDRESS。</p>
     *
     * @return the R_HOUSE_ADDRESS
     */
    public String getRhouseAddress() {
        return rhouseAddress;
    }

    /**
     * <p>R_HOUSE_ADDRESS。</p>
     *
     * @param rhouseAddress R_HOUSE_ADDRESS。
     */
    public void setRhouseAddress(String rhouseAddress) {
        this.rhouseAddress = rhouseAddress;
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
     * <p>HOUSE_ADDRESS。</p>
     *
     * @return the HOUSE_ADDRESS
     */
    public String getHouseAddress() {
        return houseAddress;
    }

    /**
     * <p>HOUSE_ADDRESS。</p>
     *
     * @param houseAddress HOUSE_ADDRESS。
     */
    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    /**
     * <p>大区。</p>
     *
     * @return the 大区
     */
    public String getVareaCode() {
        return vareaCode;
    }

    /**
     * <p>大区。</p>
     *
     * @param vareaCode 大区。
     */
    public void setVareaCode(String vareaCode) {
        this.vareaCode = vareaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getVlgcsAreaCode() {
        return vlgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param vlgcsAreaCode 物流区编码。
     */
    public void setVlgcsAreaCode(String vlgcsAreaCode) {
        this.vlgcsAreaCode = vlgcsAreaCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @return the 省编码
     */
    public String getVprovinceCode() {
        return vprovinceCode;
    }

    /**
     * <p>省编码。</p>
     *
     * @param vprovinceCode 省编码。
     */
    public void setVprovinceCode(String vprovinceCode) {
        this.vprovinceCode = vprovinceCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @return the 地区编码
     */
    public String getVcityCode() {
        return vcityCode;
    }

    /**
     * <p>地区编码。</p>
     *
     * @param vcityCode 地区编码。
     */
    public void setVcityCode(String vcityCode) {
        this.vcityCode = vcityCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @return the 区编码
     */
    public String getVdistrictCode() {
        return vdistrictCode;
    }

    /**
     * <p>区编码。</p>
     *
     * @param vdistrictCode 区编码。
     */
    public void setVdistrictCode(String vdistrictCode) {
        this.vdistrictCode = vdistrictCode;
    }

    /**
     * <p>V_HOUSE_ADDRESS。</p>
     *
     * @return the V_HOUSE_ADDRESS
     */
    public String getVhouseAddress() {
        return vhouseAddress;
    }

    /**
     * <p>V_HOUSE_ADDRESS。</p>
     *
     * @param vhouseAddress V_HOUSE_ADDRESS。
     */
    public void setVhouseAddress(String vhouseAddress) {
        this.vhouseAddress = vhouseAddress;
    }

    /**
     * <p>LAT。</p>
     *
     * @return the LAT
     */
    public String getLat() {
        return lat;
    }

    /**
     * <p>LAT。</p>
     *
     * @param lat LAT。
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * <p>LON。</p>
     *
     * @return the LON
     */
    public String getLon() {
        return lon;
    }

    /**
     * <p>LON。</p>
     *
     * @param lon LON。
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * <p>V_LAT。</p>
     *
     * @return the V_LAT
     */
    public String getVlat() {
        return vlat;
    }

    /**
     * <p>V_LAT。</p>
     *
     * @param vlat V_LAT。
     */
    public void setVlat(String vlat) {
        this.vlat = vlat;
    }

    /**
     * <p>V_LON。</p>
     *
     * @return the V_LON
     */
    public String getVlon() {
        return vlon;
    }

    /**
     * <p>V_LON。</p>
     *
     * @param vlon V_LON。
     */
    public void setVlon(String vlon) {
        this.vlon = vlon;
    }

    /**
     * <p>1:专业冻品管家资格未申请 2:专业冻品管家资格申请中 3:专业冻品管家资格。</p>
     *
     * @return the 1:专业冻品管家资格未申请 2:专业冻品管家资格申请中 3:专业冻品管家资格
     */
    public String getLicenses() {
        return licenses;
    }

    /**
     * <p>1:专业冻品管家资格未申请 2:专业冻品管家资格申请中 3:专业冻品管家资格。</p>
     *
     * @param licenses 1:专业冻品管家资格未申请 2:专业冻品管家资格申请中 3:专业冻品管家资格。
     */
    public void setLicenses(String licenses) {
        this.licenses = licenses;
    }

    /**
     * <p>0:没签署，1：签署。</p>
     *
     * @return the 0:没签署，1：签署
     */
    public String getBuyerAsign() {
        return buyerAsign;
    }

    /**
     * <p>0:没签署，1：签署。</p>
     *
     * @param buyerAsign 0:没签署，1：签署。
     */
    public void setBuyerAsign(String buyerAsign) {
        this.buyerAsign = buyerAsign;
    }

    /**
     * <p>WECHAT。</p>
     *
     * @return the WECHAT
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * <p>WECHAT。</p>
     *
     * @param wechat WECHAT。
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * <p>QQ。</p>
     *
     * @return the QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * <p>QQ。</p>
     *
     * @param qq QQ。
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * <p>EMAIL。</p>
     *
     * @return the EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * <p>EMAIL。</p>
     *
     * @param email EMAIL。
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @return the 1:行业型	2:创业型	3:关系型	4:配送型
     */
    public String getFixedTel() {
        return fixedTel;
    }

    /**
     * <p>1:行业型	2:创业型	3:关系型	4:配送型。</p>
     *
     * @param fixedTel 1:行业型	2:创业型	3:关系型	4:配送型。
     */
    public void setFixedTel(String fixedTel) {
        this.fixedTel = fixedTel;
    }

    /**
     * <p>FAX。</p>
     *
     * @return the FAX
     */
    public String getFax() {
        return fax;
    }

    /**
     * <p>FAX。</p>
     *
     * @param fax FAX。
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * <p>FLAG20。</p>
     *
     * @return the FLAG20
     */
    public String getFlag20() {
        return flag20;
    }

    /**
     * <p>FLAG20。</p>
     *
     * @param flag20 FLAG20。
     */
    public void setFlag20(String flag20) {
        this.flag20 = flag20;
    }

    /**
     * <p>FLAG19。</p>
     *
     * @return the FLAG19
     */
    public String getFlag19() {
        return flag19;
    }

    /**
     * <p>FLAG19。</p>
     *
     * @param flag19 FLAG19。
     */
    public void setFlag19(String flag19) {
        this.flag19 = flag19;
    }

    /**
     * <p>FLAG18。</p>
     *
     * @return the FLAG18
     */
    public String getFlag18() {
        return flag18;
    }

    /**
     * <p>FLAG18。</p>
     *
     * @param flag18 FLAG18。
     */
    public void setFlag18(String flag18) {
        this.flag18 = flag18;
    }

    /**
     * <p>FLAG17。</p>
     *
     * @return the FLAG17
     */
    public String getFlag17() {
        return flag17;
    }

    /**
     * <p>FLAG17。</p>
     *
     * @param flag17 FLAG17。
     */
    public void setFlag17(String flag17) {
        this.flag17 = flag17;
    }

    /**
     * <p>FLAG16。</p>
     *
     * @return the FLAG16
     */
    public String getFlag16() {
        return flag16;
    }

    /**
     * <p>FLAG16。</p>
     *
     * @param flag16 FLAG16。
     */
    public void setFlag16(String flag16) {
        this.flag16 = flag16;
    }

    /**
     * <p>FLAG15。</p>
     *
     * @return the FLAG15
     */
    public String getFlag15() {
        return flag15;
    }

    /**
     * <p>FLAG15。</p>
     *
     * @param flag15 FLAG15。
     */
    public void setFlag15(String flag15) {
        this.flag15 = flag15;
    }

    /**
     * <p>FLAG14。</p>
     *
     * @return the FLAG14
     */
    public String getFlag14() {
        return flag14;
    }

    /**
     * <p>FLAG14。</p>
     *
     * @param flag14 FLAG14。
     */
    public void setFlag14(String flag14) {
        this.flag14 = flag14;
    }

    /**
     * <p>FLAG13。</p>
     *
     * @return the FLAG13
     */
    public String getFlag13() {
        return flag13;
    }

    /**
     * <p>FLAG13。</p>
     *
     * @param flag13 FLAG13。
     */
    public void setFlag13(String flag13) {
        this.flag13 = flag13;
    }

    /**
     * <p>FLAG12。</p>
     *
     * @return the FLAG12
     */
    public String getFlag12() {
        return flag12;
    }

    /**
     * <p>FLAG12。</p>
     *
     * @param flag12 FLAG12。
     */
    public void setFlag12(String flag12) {
        this.flag12 = flag12;
    }

    /**
     * <p>FLAG11。</p>
     *
     * @return the FLAG11
     */
    public String getFlag11() {
        return flag11;
    }

    /**
     * <p>FLAG11。</p>
     *
     * @param flag11 FLAG11。
     */
    public void setFlag11(String flag11) {
        this.flag11 = flag11;
    }

    /**
     * <p>FLAG10。</p>
     *
     * @return the FLAG10
     */
    public String getFlag10() {
        return flag10;
    }

    /**
     * <p>FLAG10。</p>
     *
     * @param flag10 FLAG10。
     */
    public void setFlag10(String flag10) {
        this.flag10 = flag10;
    }

    /**
     * <p>FLAG9。</p>
     *
     * @return the FLAG9
     */
    public String getFlag9() {
        return flag9;
    }

    /**
     * <p>FLAG9。</p>
     *
     * @param flag9 FLAG9。
     */
    public void setFlag9(String flag9) {
        this.flag9 = flag9;
    }

    /**
     * <p>FLAG8。</p>
     *
     * @return the FLAG8
     */
    public String getFlag8() {
        return flag8;
    }

    /**
     * <p>FLAG8。</p>
     *
     * @param flag8 FLAG8。
     */
    public void setFlag8(String flag8) {
        this.flag8 = flag8;
    }

    /**
     * <p>FLAG7。</p>
     *
     * @return the FLAG7
     */
    public String getFlag7() {
        return flag7;
    }

    /**
     * <p>FLAG7。</p>
     *
     * @param flag7 FLAG7。
     */
    public void setFlag7(String flag7) {
        this.flag7 = flag7;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public String getFlag6() {
        return flag6;
    }

    /**
     * <p>备注。</p>
     *
     * @param flag6 备注。
     */
    public void setFlag6(String flag6) {
        this.flag6 = flag6;
    }

    /**
     * <p>FLAG5。</p>
     *
     * @return the FLAG5
     */
    public String getFlag5() {
        return flag5;
    }

    /**
     * <p>FLAG5。</p>
     *
     * @param flag5 FLAG5。
     */
    public void setFlag5(String flag5) {
        this.flag5 = flag5;
    }

    /**
     * <p>FLAG4。</p>
     *
     * @return the FLAG4
     */
    public String getFlag4() {
        return flag4;
    }

    /**
     * <p>FLAG4。</p>
     *
     * @param flag4 FLAG4。
     */
    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    /**
     * <p>FLAG3。</p>
     *
     * @return the FLAG3
     */
    public String getFlag3() {
        return flag3;
    }

    /**
     * <p>FLAG3。</p>
     *
     * @param flag3 FLAG3。
     */
    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    /**
     * <p>FLAG2。</p>
     *
     * @return the FLAG2
     */
    public String getFlag2() {
        return flag2;
    }

    /**
     * <p>FLAG2。</p>
     *
     * @param flag2 FLAG2。
     */
    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    /**
     * <p>FLAG1。</p>
     *
     * @return the FLAG1
     */
    public String getFlag1() {
        return flag1;
    }

    /**
     * <p>FLAG1。</p>
     *
     * @param flag1 FLAG1。
     */
    public void setFlag1(String flag1) {
        this.flag1 = flag1;
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
     * <p>管家二级分类。</p>
     *
     * @return the 管家二级分类
     */
    public String getHouseCategorySub() {
        return houseCategorySub;
    }

    /**
     * <p>管家二级分类。</p>
     *
     * @param houseCategorySub 管家二级分类。
     */
    public void setHouseCategorySub(String houseCategorySub) {
        this.houseCategorySub = houseCategorySub;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory0() {
        return houseCategory0;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory0 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory0(String houseCategory0) {
        this.houseCategory0 = houseCategory0;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory1() {
        return houseCategory1;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory1 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory1(String houseCategory1) {
        this.houseCategory1 = houseCategory1;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory2() {
        return houseCategory2;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory2 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory2(String houseCategory2) {
        this.houseCategory2 = houseCategory2;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory3() {
        return houseCategory3;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory3 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory3(String houseCategory3) {
        this.houseCategory3 = houseCategory3;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory4() {
        return houseCategory4;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory4 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory4(String houseCategory4) {
        this.houseCategory4 = houseCategory4;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory5() {
        return houseCategory5;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory5 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory5(String houseCategory5) {
        this.houseCategory5 = houseCategory5;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory6() {
        return houseCategory6;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory6 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory6(String houseCategory6) {
        this.houseCategory6 = houseCategory6;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory7() {
        return houseCategory7;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory7 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory7(String houseCategory7) {
        this.houseCategory7 = houseCategory7;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @return the 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂
     */
    public String getHouseCategory8() {
        return houseCategory8;
    }

    /**
     * <p>0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。</p>
     *
     * @param houseCategory8 0基本冻品管家 1分销	2菜场	3团膳	4火锅	5中餐	6西餐	7小吃烧烤	8加工厂。
     */
    public void setHouseCategory8(String houseCategory8) {
        this.houseCategory8 = houseCategory8;
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

}
