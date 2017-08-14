package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

/**
 * SL241101Bean
 *
 * @author gyh
 * @version 1.0
 **/
public class SL241101Bean extends BaseBean {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String slAccount;
    private String slTel;
    private String slShowName;
    private String slContact;
    private String accountPsd;
    private String accountImg;
    /** 注册来源 */
    private String fromFlg;
    /**
     * 认证状态 '0:未认证,1:认证中,2:已认证',
     */
    private Integer authStatus;
    private String slAreaCode;
    private String slCode;
    /**
     * 卖家编码显示用
     */
    private String slCodeDis;
    private String slCodeManufacture;//卖家生产商编码
    private Integer epId;
    private String snkFlg;
    private String selfFlg;
    private String proxyFlg;
    private String oemFlg;
    private String slMainClass;
    private Integer sqaStaturs;
    private String distQua;
    private String shopQua;
    private String salType;
    private String ddjsh;//待定级审核有无
    private String ddjkr;//待定监控人有无
    private String cityName;//城市中文名
    private String lgcsAreaName;//物流区中文名
    private String slConFlg;//生产国籍
    /**
     * 开户银行
     */
    private String balBank;
    /**
     * 开户账号
     */
    private String balAccount;
    /**
     * 开户人
     */
    private String balLegalPerson;

    /** 生产商企业ID */
    private java.lang.Integer prodEpId;
    /** 品牌商企业ID */
    private java.lang.Integer brandEpId;
    /** 产品品牌ID */
    private java.lang.Integer brandId;
    /** 产品类别 */
    private java.lang.String pdClassesCode;
    /** 产品二级分类编码 */
    private java.lang.String machiningCode;
    /** 产品品种 */
    private java.lang.String pdBreedCode;

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
     * Getter method for property <tt>slCodeManufacture</tt>.
     *
     * @return property value of slCodeManufacture
     */
    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    /**
     * Setter method for property <tt>slCodeManufacture</tt>.
     *
     * @param slCodeManufacture value to be assigned to property slCodeManufacture
     */
    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
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
     * Getter method for property <tt>prodEpId</tt>.
     *
     * @return property value of prodEpId
     */
    public Integer getProdEpId() {
        return prodEpId;
    }

    /**
     * Setter method for property <tt>prodEpId</tt>.
     *
     * @param prodEpId value to be assigned to property prodEpId
     */
    public void setProdEpId(Integer prodEpId) {
        this.prodEpId = prodEpId;
    }

    /**
     * Getter method for property <tt>brandEpId</tt>.
     *
     * @return property value of brandEpId
     */
    public Integer getBrandEpId() {
        return brandEpId;
    }

    /**
     * Setter method for property <tt>brandEpId</tt>.
     *
     * @param brandEpId value to be assigned to property brandEpId
     */
    public void setBrandEpId(Integer brandEpId) {
        this.brandEpId = brandEpId;
    }

    /**
     * Getter method for property <tt>brandId</tt>.
     *
     * @return property value of brandId
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * Setter method for property <tt>brandId</tt>.
     *
     * @param brandId value to be assigned to property brandId
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * Getter method for property <tt>pdClassesCode</tt>.
     *
     * @return property value of pdClassesCode
     */
    public String getPdClassesCode() {
        return pdClassesCode;
    }

    /**
     * Setter method for property <tt>pdClassesCode</tt>.
     *
     * @param pdClassesCode value to be assigned to property pdClassesCode
     */
    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Getter method for property <tt>pdBreedCode</tt>.
     *
     * @return property value of pdBreedCode
     */
    public String getPdBreedCode() {
        return pdBreedCode;
    }

    /**
     * Setter method for property <tt>pdBreedCode</tt>.
     *
     * @param pdBreedCode value to be assigned to property pdBreedCode
     */
    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
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
     * Getter method for property <tt>slMainClass</tt>.
     *
     * @return property value of slMainClass
     */
    public String getSlMainClass() {
        return slMainClass;
    }

    /**
     * Setter method for property <tt>slMainClass</tt>.
     *
     * @param slMainClass value to be assigned to property slMainClass
     */
    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    /**
     * Getter method for property <tt>serialVersionUID</tt>.
     *
     * @return property value of serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * Getter method for property <tt>slAreaCode</tt>.
     *
     * @return property value of slAreaCode
     */
    public String getSlAreaCode() {
        return slAreaCode;
    }

    /**
     * Setter method for property <tt>slAreaCode</tt>.
     *
     * @param slAreaCode value to be assigned to property slAreaCode
     */
    public void setSlAreaCode(String slAreaCode) {
        this.slAreaCode = slAreaCode;
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
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public Integer getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
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
     * Getter method for property <tt>proxyFlg</tt>.
     *
     * @return property value of proxyFlg
     */
    public String getProxyFlg() {
        return proxyFlg;
    }

    /**
     * Setter method for property <tt>proxyFlg</tt>.
     *
     * @param proxyFlg value to be assigned to property proxyFlg
     */
    public void setProxyFlg(String proxyFlg) {
        this.proxyFlg = proxyFlg;
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
     * Getter method for property <tt>sqaStaturs</tt>.
     *
     * @return property value of sqaStaturs
     */
    public Integer getSqaStaturs() {
        return sqaStaturs;
    }

    /**
     * Setter method for property <tt>sqaStaturs</tt>.
     *
     * @param sqaStaturs value to be assigned to property sqaStaturs
     */
    public void setSqaStaturs(Integer sqaStaturs) {
        this.sqaStaturs = sqaStaturs;
    }

    /**
     * Getter method for property <tt>distQua</tt>.
     *
     * @return property value of distQua
     */
    public String getDistQua() {
        return distQua;
    }

    /**
     * Setter method for property <tt>distQua</tt>.
     *
     * @param distQua value to be assigned to property distQua
     */
    public void setDistQua(String distQua) {
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
     * Getter method for property <tt>salType</tt>.
     *
     * @return property value of salType
     */
    public String getSalType() {
        return salType;
    }

    /**
     * Setter method for property <tt>salType</tt>.
     *
     * @param salType value to be assigned to property salType
     */
    public void setSalType(String salType) {
        this.salType = salType;
    }

    /**
     * Getter method for property <tt>ddjsh</tt>.
     *
     * @return property value of ddjsh
     */
    public String getDdjsh() {
        return ddjsh;
    }

    /**
     * Setter method for property <tt>ddjsh</tt>.
     *
     * @param ddjsh value to be assigned to property ddjsh
     */
    public void setDdjsh(String ddjsh) {
        this.ddjsh = ddjsh;
    }

    /**
     * Getter method for property <tt>ddjkr</tt>.
     *
     * @return property value of ddjkr
     */
    public String getDdjkr() {
        return ddjkr;
    }

    /**
     * Setter method for property <tt>ddjkr</tt>.
     *
     * @param ddjkr value to be assigned to property ddjkr
     */
    public void setDdjkr(String ddjkr) {
        this.ddjkr = ddjkr;
    }

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

    public String getBalLegalPerson() {
        return balLegalPerson;
    }

    public void setBalLegalPerson(String balLegalPerson) {
        this.balLegalPerson = balLegalPerson;
    }

    public String getBalAccount() {
        return balAccount;
    }

    public void setBalAccount(String balAccount) {
        this.balAccount = balAccount;
    }

    public String getBalBank() {
        return balBank;
    }

    public void setBalBank(String balBank) {
        this.balBank = balBank;
    }
}
