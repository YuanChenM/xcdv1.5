/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ms_cardmanager对应的MsCardmanager。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class MsCardmanager extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 会员卡ID */
    private Long msId;
    /** 会员卡编码 */
    private String msCardno;
    /** 加密后密文 */
    private String msInitpw;
    /** 会员卡有效期 */
    private java.util.Date expireDate;
    /** 采购批次 */
    private String purchaseLot;
    /** 1:是 */
    private String isActivate;
    /** 会员卡前次密码 */
    private String msPrevpw;
    /** 会员卡当前密码 */
    private String msNowpw;
    /** 买家编码 */
    private String buyerCode;
    /** 买家名称 */
    private String buyerName;
    /** 会员编码 */
    private String msCode;
    /** 1:是 */
    private String isProvide;
    /** 发放日期 */
    private java.util.Date provideDate;
    /**
     * <p>默认构造函数。</p>
     */
    public MsCardmanager() {

    }

    /**
     * <p>会员卡ID。</p>
     *
     * @return the 会员卡ID
     */
    public Long getMsId() {
        return msId;
    }

    /**
     * <p>会员卡ID。</p>
     *
     * @param msId 会员卡ID。
     */
    public void setMsId(Long msId) {
        this.msId = msId;
    }

    /**
     * <p>会员卡编码。</p>
     *
     * @return the 会员卡编码
     */
    public String getMsCardno() {
        return msCardno;
    }

    /**
     * <p>会员卡编码。</p>
     *
     * @param msCardno 会员卡编码。
     */
    public void setMsCardno(String msCardno) {
        this.msCardno = msCardno;
    }

    /**
     * <p>加密后密文。</p>
     *
     * @return the 加密后密文
     */
    public String getMsInitpw() {
        return msInitpw;
    }

    /**
     * <p>加密后密文。</p>
     *
     * @param msInitpw 加密后密文。
     */
    public void setMsInitpw(String msInitpw) {
        this.msInitpw = msInitpw;
    }

    /**
     * <p>会员卡有效期。</p>
     *
     * @return the 会员卡有效期
     */
    public java.util.Date getExpireDate() {
        return expireDate;
    }

    /**
     * <p>会员卡有效期。</p>
     *
     * @param expireDate 会员卡有效期。
     */
    public void setExpireDate(java.util.Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * <p>采购批次。</p>
     *
     * @return the 采购批次
     */
    public String getPurchaseLot() {
        return purchaseLot;
    }

    /**
     * <p>采购批次。</p>
     *
     * @param purchaseLot 采购批次。
     */
    public void setPurchaseLot(String purchaseLot) {
        this.purchaseLot = purchaseLot;
    }

    /**
     * <p>1:是。</p>
     *
     * @return the 1:是
     */
    public String getIsActivate() {
        return isActivate;
    }

    /**
     * <p>1:是。</p>
     *
     * @param isActivate 1:是。
     */
    public void setIsActivate(String isActivate) {
        this.isActivate = isActivate;
    }

    /**
     * <p>会员卡前次密码。</p>
     *
     * @return the 会员卡前次密码
     */
    public String getMsPrevpw() {
        return msPrevpw;
    }

    /**
     * <p>会员卡前次密码。</p>
     *
     * @param msPrevpw 会员卡前次密码。
     */
    public void setMsPrevpw(String msPrevpw) {
        this.msPrevpw = msPrevpw;
    }

    /**
     * <p>会员卡当前密码。</p>
     *
     * @return the 会员卡当前密码
     */
    public String getMsNowpw() {
        return msNowpw;
    }

    /**
     * <p>会员卡当前密码。</p>
     *
     * @param msNowpw 会员卡当前密码。
     */
    public void setMsNowpw(String msNowpw) {
        this.msNowpw = msNowpw;
    }

    /**
     * <p>买家编码。</p>
     *
     * @return the 买家编码
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>买家编码。</p>
     *
     * @param buyerCode 买家编码。
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>买家名称。</p>
     *
     * @return the 买家名称
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>买家名称。</p>
     *
     * @param buyerName 买家名称。
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>会员编码。</p>
     *
     * @return the 会员编码
     */
    public String getMsCode() {
        return msCode;
    }

    /**
     * <p>会员编码。</p>
     *
     * @param msCode 会员编码。
     */
    public void setMsCode(String msCode) {
        this.msCode = msCode;
    }

    /**
     * <p>1:是。</p>
     *
     * @return the 1:是
     */
    public String getIsProvide() {
        return isProvide;
    }

    /**
     * <p>1:是。</p>
     *
     * @param isProvide 1:是。
     */
    public void setIsProvide(String isProvide) {
        this.isProvide = isProvide;
    }

    /**
     * <p>发放日期。</p>
     *
     * @return the 发放日期
     */
    public java.util.Date getProvideDate() {
        return provideDate;
    }

    /**
     * <p>发放日期。</p>
     *
     * @param provideDate 发放日期。
     */
    public void setProvideDate(java.util.Date provideDate) {
        this.provideDate = provideDate;
    }

}
