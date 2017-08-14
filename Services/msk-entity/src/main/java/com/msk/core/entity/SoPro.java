/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_pro对应的SoPro。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoPro extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 意愿ID */
    private Long proId;
    /** 意愿编码 */
    private String proCode;
    /** 需求状态 */
    private Integer proStatus;
    /** 卖家ID */
    private String buyerId;
    /** 买家编码 */
    private String buyerCode;
    /** 买家名称 */
    private String buyerName;
    /** 买家类别-CodeMaster
            1:分销买家,
            2:菜场买家,
            3:团膳买家,
            4:火锅买家,
            5:中餐买家,
            6:西餐买家,
            7:小吃烧烤买家,
            8:加工厂买家 */
    private Integer buyerType;
    /** 卖家编号 */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
    /** 订单来源 */
    private Integer orderSource;
    /** 订单所属物流区域 */
    private String districtCode;
    /** 购物意愿创建时间 */
    private java.util.Date proTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SoPro() {

    }

    /**
     * <p>意愿ID。</p>
     *
     * @return the 意愿ID
     */
    public Long getProId() {
        return proId;
    }

    /**
     * <p>意愿ID。</p>
     *
     * @param proId 意愿ID。
     */
    public void setProId(Long proId) {
        this.proId = proId;
    }

    /**
     * <p>意愿编码。</p>
     *
     * @return the 意愿编码
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p>意愿编码。</p>
     *
     * @param proCode 意愿编码。
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p>需求状态。</p>
     *
     * @return the 需求状态
     */
    public Integer getProStatus() {
        return proStatus;
    }

    /**
     * <p>需求状态。</p>
     *
     * @param proStatus 需求状态。
     */
    public void setProStatus(Integer proStatus) {
        this.proStatus = proStatus;
    }

    /**
     * <p>卖家ID。</p>
     *
     * @return the 卖家ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>卖家ID。</p>
     *
     * @param buyerId 卖家ID。
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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
     * <p>买家类别-CodeMaster
            1:分销买家,
            2:菜场买家,
            3:团膳买家,
            4:火锅买家,
            5:中餐买家,
            6:西餐买家,
            7:小吃烧烤买家,
            8:加工厂买家。</p>
     *
     * @return the 买家类别-CodeMaster
            1:分销买家,
            2:菜场买家,
            3:团膳买家,
            4:火锅买家,
            5:中餐买家,
            6:西餐买家,
            7:小吃烧烤买家,
            8:加工厂买家
     */
    public Integer getBuyerType() {
        return buyerType;
    }

    /**
     * <p>买家类别-CodeMaster
            1:分销买家,
            2:菜场买家,
            3:团膳买家,
            4:火锅买家,
            5:中餐买家,
            6:西餐买家,
            7:小吃烧烤买家,
            8:加工厂买家。</p>
     *
     * @param buyerType 买家类别-CodeMaster
            1:分销买家,
            2:菜场买家,
            3:团膳买家,
            4:火锅买家,
            5:中餐买家,
            6:西餐买家,
            7:小吃烧烤买家,
            8:加工厂买家。
     */
    public void setBuyerType(Integer buyerType) {
        this.buyerType = buyerType;
    }

    /**
     * <p>卖家编号。</p>
     *
     * @return the 卖家编号
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * <p>卖家编号。</p>
     *
     * @param sellerCode 卖家编号。
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * <p>卖家名称。</p>
     *
     * @return the 卖家名称
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * <p>卖家名称。</p>
     *
     * @param sellerName 卖家名称。
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * <p>订单来源。</p>
     *
     * @return the 订单来源
     */
    public Integer getOrderSource() {
        return orderSource;
    }

    /**
     * <p>订单来源。</p>
     *
     * @param orderSource 订单来源。
     */
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * <p>订单所属物流区域。</p>
     *
     * @return the 订单所属物流区域
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * <p>订单所属物流区域。</p>
     *
     * @param districtCode 订单所属物流区域。
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * <p>购物意愿创建时间。</p>
     *
     * @return the 购物意愿创建时间
     */
    public java.util.Date getProTime() {
        return proTime;
    }

    /**
     * <p>购物意愿创建时间。</p>
     *
     * @param proTime 购物意愿创建时间。
     */
    public void setProTime(java.util.Date proTime) {
        this.proTime = proTime;
    }

}
