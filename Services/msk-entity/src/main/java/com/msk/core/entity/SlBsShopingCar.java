/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_shoping_car对应的SlBsShopingCar。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsShopingCar extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 车牌 */
    private Long carId;
    /** 买家ID */
    private String buyersId;
    /** 买家类型(1:买家 2：管家 3：买手) */
    private Integer buyersType;
    /** 卖家编号 */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
    /** 订单来源 */
    private Integer orderSource;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsShopingCar() {

    }

    /**
     * <p>车牌。</p>
     *
     * @return the 车牌
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * <p>车牌。</p>
     *
     * @param carId 车牌。
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * <p>买家ID。</p>
     *
     * @return the 买家ID
     */
    public String getBuyersId() {
        return buyersId;
    }

    /**
     * <p>买家ID。</p>
     *
     * @param buyersId 买家ID。
     */
    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    /**
     * <p>买家类型(1:买家 2：管家 3：买手)。</p>
     *
     * @return the 买家类型(1:买家 2：管家 3：买手)
     */
    public Integer getBuyersType() {
        return buyersType;
    }

    /**
     * <p>买家类型(1:买家 2：管家 3：买手)。</p>
     *
     * @param buyersType 买家类型(1:买家 2：管家 3：买手)。
     */
    public void setBuyersType(Integer buyersType) {
        this.buyersType = buyersType;
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

}
