/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_shoping_car_detail对应的SlBsShopingCarDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsShopingCarDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 车牌 */
    private Long carId;
    /** 明细INDEX */
    private Long carDetailId;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 数量 */
    private String pdNum;
    /** 加入购物车时的价格 */
    private java.math.BigDecimal oldPrice;
    /** 卖家编号 */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
    /** 0：加入购物车 1：选中 9：失效 */
    private Integer status;
    /** 购物来源 （云冻品商城 微商城 买手APP） */
    private String carSource;
    /** 移除原因(1: 购买 2：编辑删除  3：产品失效 ) */
    private Integer removeReason;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsShopingCarDetail() {

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
     * <p>明细INDEX。</p>
     *
     * @return the 明细INDEX
     */
    public Long getCarDetailId() {
        return carDetailId;
    }

    /**
     * <p>明细INDEX。</p>
     *
     * @param carDetailId 明细INDEX。
     */
    public void setCarDetailId(Long carDetailId) {
        this.carDetailId = carDetailId;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>数量。</p>
     *
     * @return the 数量
     */
    public String getPdNum() {
        return pdNum;
    }

    /**
     * <p>数量。</p>
     *
     * @param pdNum 数量。
     */
    public void setPdNum(String pdNum) {
        this.pdNum = pdNum;
    }

    /**
     * <p>加入购物车时的价格。</p>
     *
     * @return the 加入购物车时的价格
     */
    public java.math.BigDecimal getOldPrice() {
        return oldPrice;
    }

    /**
     * <p>加入购物车时的价格。</p>
     *
     * @param oldPrice 加入购物车时的价格。
     */
    public void setOldPrice(java.math.BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
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
     * <p>0：加入购物车 1：选中 9：失效。</p>
     *
     * @return the 0：加入购物车 1：选中 9：失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>0：加入购物车 1：选中 9：失效。</p>
     *
     * @param status 0：加入购物车 1：选中 9：失效。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <p>购物来源 （云冻品商城 微商城 买手APP）。</p>
     *
     * @return the 购物来源 （云冻品商城 微商城 买手APP）
     */
    public String getCarSource() {
        return carSource;
    }

    /**
     * <p>购物来源 （云冻品商城 微商城 买手APP）。</p>
     *
     * @param carSource 购物来源 （云冻品商城 微商城 买手APP）。
     */
    public void setCarSource(String carSource) {
        this.carSource = carSource;
    }

    /**
     * <p>移除原因(1: 购买 2：编辑删除  3：产品失效 )。</p>
     *
     * @return the 移除原因(1: 购买 2：编辑删除  3：产品失效 )
     */
    public Integer getRemoveReason() {
        return removeReason;
    }

    /**
     * <p>移除原因(1: 购买 2：编辑删除  3：产品失效 )。</p>
     *
     * @param removeReason 移除原因(1: 购买 2：编辑删除  3：产品失效 )。
     */
    public void setRemoveReason(Integer removeReason) {
        this.removeReason = removeReason;
    }

}
