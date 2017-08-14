/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_seller_his对应的SlSellerHis。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlSellerHis extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 履历ID */
    private Long hisId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 卖家账号 */
    private String slAccount;
    /** 区划(6)+顺序码(4) */
    private String slCodeDis;
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
    /** 1.自产型,2:代理型,3:OEM型 */
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
    /** 备用字段1 */
    private String memo1;
    /** 备用字段2 */
    private String memo2;
    /** 备用字段3 */
    private String memo3;
    /** 备用字段4 */
    private String memo4;
    /** 备用字段5 */
    private String memo5;
    /** 备用字段6 */
    private String memo6;
    /** 备用字段7 */
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
     * <p>默认构造函数。</p>
     */
    public SlSellerHis() {

    }

    /**
     * <p>履历ID。</p>
     *
     * @return the 履历ID
     */
    public Long getHisId() {
        return hisId;
    }

    /**
     * <p>履历ID。</p>
     *
     * @param hisId 履历ID。
     */
    public void setHisId(Long hisId) {
        this.hisId = hisId;
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
     * <p>1.自产型,2:代理型,3:OEM型。</p>
     *
     * @return the 1.自产型,2:代理型,3:OEM型
     */
    public Integer getSlMainClass() {
        return slMainClass;
    }

    /**
     * <p>1.自产型,2:代理型,3:OEM型。</p>
     *
     * @param slMainClass 1.自产型,2:代理型,3:OEM型。
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

}
