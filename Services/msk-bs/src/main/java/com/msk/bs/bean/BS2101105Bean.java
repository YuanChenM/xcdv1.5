package com.msk.bs.bean;

import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlSeller;

/**
 * Created by Administrator on 2016/4/7.
 */
public class BS2101105Bean extends BsBasicInfo {
    /** 用于登录的卖家账号 */
    private String slAccount;
    /** 也可以用于登录 */
    private String slTel;
    /** 卖家显示名称 */
    private String slShowName;
    /** 联系人姓名 */
    private String slContact;
    /** 加密后的值 */
    private String accountPsd;
    /** 图片路径 */
    private String accountImg;
    /** 0:未认证,1:认证中,2:已认证 */
    private Integer authStatus;
    /** 注册来源 */
    private String fromFlg;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** SHOP_ID */
    private Long shopId;
    /** 店铺名称 */
    private String shopName;
    /** 店铺Logo */
    private String shopLogo;
    /** 经营特色10 */
    private String managingCharact10;
    /** 经营特色9 */
    private String managingCharact9;
    /** 经营特色8 */
    private String managingCharact8;
    /** 经营特色7 */
    private String managingCharact7;
    /** 经营特色6 */
    private String managingCharact6;
    /** 经营特色5 */
    private String managingCharact5;
    /** 经营特色4 */
    private String managingCharact4;
    /** 经营特色3 */
    private String managingCharact3;
    /** 经营特色2 */
    private String managingCharact2;
    /** 经营特色1 */
    private String managingCharact1;
    /** 备用30 */
    private String shopFlag30;
    /** 备用29 */
    private String shopFlag29;
    /** 备用28 */
    private String shopFlag28;
    /** 备用27 */
    private String shopFlag27;
    /** 备用26 */
    private String shopFlag26;
    /** 备用25 */
    private String shopFlag25;
    /** 备用24 */
    private String shopFlag24;
    /** 备用23 */
    private String shopFlag23;
    /** 备用22 */
    private String shopFlag22;
    /** 备用21 */
    private String shopFlag21;
    /** 备用20 */
    private String shopFlag20;
    /** 备用19 */
    private String shopFlag19;
    /** 备用18 */
    private String shopFlag18;
    /** 备用17 */
    private String shopFlag17;
    /** 备用16 */
    private String shopFlag16;
    /** 备用15 */
    private String shopFlag15;
    /** 备用14 */
    private String shopFlag14;
    /** 备用13 */
    private String shopFlag13;
    /** 备用12 */
    private String shopFlag12;
    /** 备用11 */
    private String shopFlag11;
    /** 备用10 */
    private String shopFlag10;
    /** 备用9 */
    private String shopFlag9;
    /** 备用8 */
    private String shopFlag8;
    /** 备用7 */
    private String shopFlag7;
    /** 备用6 */
    private String shopFlag6;
    /** 备用5 */
    private String shopFlag5;
    /** 备用4 */
    private String shopFlag4;
    /** 备用3 */
    private String shopFlag3;
    /** 备用2 */
    private String shopFlag2;
    /** 备用1 */
    private String shopFlag1;
    /** 买手身份证号 */
    private String slIdcard;
    /** 合营优先顺方式 */
    private Integer slSort;
    /** 买手地址 */
    private String slAddress;
    /** 维度 */
    private String lat;
    /** 精度 */
    private String lon;
    /** 备用10 */
    private String flag10;
    /** 备用9 */
    private String flag9;
    /** 备用8 */
    private String flag8;
    /** 备用7 */
    private String flag7;
    /** 备用6 */
    private String flag6;
    /** 备用5 */
    private String flag5;
    /** 备用4 */
    private String flag4;
    /** 备用3 */
    private String flag3;
    /** 备用2 */
    private String flag2;
    /** 1：男 2：女 */
    private String flag1;
    //新增或者修改标志 1新增 2修改
    private String flagNum;

    /**
     * Getter method for property <tt>flagNum</tt>.
     *
     * @return property value of flagNum
     */
    public String getFlagNum() {
        return flagNum;
    }

    /**
     * Setter method for property <tt>flagNum</tt>.
     *
     * @param flagNum value to be assigned to property flagNum
     */
    public void setFlagNum(String flagNum) {
        this.flagNum = flagNum;
    }

    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    @Override
    public String getSlAccount() {
        return slAccount;
    }

    /**
     * Setter method for property <tt>slAccount</tt>.
     *
     * @param slAccount value to be assigned to property slAccount
     */
    @Override
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
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    @Override
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    @Override
    public void setSlCode(String slCode) {
        this.slCode = slCode;
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

}
