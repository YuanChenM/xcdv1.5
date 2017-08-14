/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_market_terminal_br_search对应的ByMarketTerminalBrSearch</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByMarketTerminalBrSearch extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** STORE_ID */
    private Long storeId;
    /** 买家店铺号 */
    private String buyerStoreNo;
    /** 批发市场ID */
    private String marketId;
    /** 是否目标买家 */
    private String isTargetMerchant;
    /** 买家类型 */
    private String merchantType;
    /** 备注 */
    private String remark;
    /**
     * <p>默认构造函数</p>
     */
    public ByMarketTerminalBrSearch() {

    }

    /**
     * <p>STORE_ID</p>
     *
     * @return the STORE_ID
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * <p>STORE_ID</p>
     *
     * @param storeId STORE_ID
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * <p>买家店铺号</p>
     *
     * @return the 买家店铺号
     */
    public String getBuyerStoreNo() {
        return buyerStoreNo;
    }

    /**
     * <p>买家店铺号</p>
     *
     * @param buyerStoreNo 买家店铺号
     */
    public void setBuyerStoreNo(String buyerStoreNo) {
        this.buyerStoreNo = buyerStoreNo;
    }

    /**
     * <p>批发市场ID</p>
     *
     * @return the 批发市场ID
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * <p>批发市场ID</p>
     *
     * @param marketId 批发市场ID
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * <p>是否目标买家</p>
     *
     * @return the 是否目标买家
     */
    public String getIsTargetMerchant() {
        return isTargetMerchant;
    }

    /**
     * <p>是否目标买家</p>
     *
     * @param isTargetMerchant 是否目标买家
     */
    public void setIsTargetMerchant(String isTargetMerchant) {
        this.isTargetMerchant = isTargetMerchant;
    }

    /**
     * <p>买家类型</p>
     *
     * @return the 买家类型
     */
    public String getMerchantType() {
        return merchantType;
    }

    /**
     * <p>买家类型</p>
     *
     * @param merchantType 买家类型
     */
    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * <p>备注</p>
     *
     * @return the 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>备注</p>
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
