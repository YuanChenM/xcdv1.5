/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * <p>表so_sub_order对应的SoSubOrder。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoSubOrder extends BaseEntity {
    /**
     * 
     */
    @OneToMany
    @JoinColumn(name = "subOrderId")
    private List<SoSubOrderDetail> soSubOrderDetailList;

    @OneToMany
    @JoinColumn(name = "subOrderId")
    private List<SoDeliver> soDeliverList;


    private static final long serialVersionUID = 1L;
    /** 分批订单ID */
    @Id
    private Long subOrderId;
    /** 分批订单编码 */
    private String subOrderCode;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 卖家编码-实际的销售方
            云冻品为平台，
            B2B第三方就是第三方卖家，
            若为买手销售订单则为买手ID */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
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
    /** 订单类型-CodeMaster
            初始只有4种
            1-标准分销订单（V）
            2-第三方订单（V）
            3-大促会订单
            4-买手囤货订单（V）
            5-买手销售订单
            6-第三方买手销售订单
            7-第三方买手囤货订单（V）
             */
    private Integer orderType;
    /** 分批订单状态 */
    private Integer subOrderStatus;
    /** 订单付款状态-CodeMaster
            0-未支付
            1-部分支付
            2-已支付 */
    private Integer subPayStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoSubOrder() {

    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public Long getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @return the 分批订单编码
     */
    public String getSubOrderCode() {
        return subOrderCode;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @param subOrderCode 分批订单编码。
     */
    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>卖家编码-实际的销售方
            云冻品为平台，
            B2B第三方就是第三方卖家，
            若为买手销售订单则为买手ID。</p>
     *
     * @return the 卖家编码-实际的销售方
            云冻品为平台，
            B2B第三方就是第三方卖家，
            若为买手销售订单则为买手ID
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * <p>卖家编码-实际的销售方
            云冻品为平台，
            B2B第三方就是第三方卖家，
            若为买手销售订单则为买手ID。</p>
     *
     * @param sellerCode 卖家编码-实际的销售方
            云冻品为平台，
            B2B第三方就是第三方卖家，
            若为买手销售订单则为买手ID。
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
     * <p>订单类型-CodeMaster
            初始只有4种
            1-标准分销订单（V）
            2-第三方订单（V）
            3-大促会订单
            4-买手囤货订单（V）
            5-买手销售订单
            6-第三方买手销售订单
            7-第三方买手囤货订单（V）
            。</p>
     *
     * @return the 订单类型-CodeMaster
            初始只有4种
            1-标准分销订单（V）
            2-第三方订单（V）
            3-大促会订单
            4-买手囤货订单（V）
            5-买手销售订单
            6-第三方买手销售订单
            7-第三方买手囤货订单（V）
            
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * <p>订单类型-CodeMaster
            初始只有4种
            1-标准分销订单（V）
            2-第三方订单（V）
            3-大促会订单
            4-买手囤货订单（V）
            5-买手销售订单
            6-第三方买手销售订单
            7-第三方买手囤货订单（V）
            。</p>
     *
     * @param orderType 订单类型-CodeMaster
            初始只有4种
            1-标准分销订单（V）
            2-第三方订单（V）
            3-大促会订单
            4-买手囤货订单（V）
            5-买手销售订单
            6-第三方买手销售订单
            7-第三方买手囤货订单（V）
            。
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * <p>分批订单状态。</p>
     *
     * @return the 分批订单状态
     */
    public Integer getSubOrderStatus() {
        return subOrderStatus;
    }

    /**
     * <p>分批订单状态。</p>
     *
     * @param subOrderStatus 分批订单状态。
     */
    public void setSubOrderStatus(Integer subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    /**
     * <p>订单付款状态-CodeMaster
            0-未支付
            1-部分支付
            2-已支付。</p>
     *
     * @return the 订单付款状态-CodeMaster
            0-未支付
            1-部分支付
            2-已支付
     */
    public Integer getSubPayStatus() {
        return subPayStatus;
    }

    /**
     * <p>订单付款状态-CodeMaster
            0-未支付
            1-部分支付
            2-已支付。</p>
     *
     * @param subPayStatus 订单付款状态-CodeMaster
            0-未支付
            1-部分支付
            2-已支付。
     */
    public void setSubPayStatus(Integer subPayStatus) {
        this.subPayStatus = subPayStatus;
    }


    public List<SoSubOrderDetail> getSoSubOrderDetailList() {
        return soSubOrderDetailList;
    }

    public void setSoSubOrderDetailList(List<SoSubOrderDetail> soSubOrderDetailList) {
        this.soSubOrderDetailList = soSubOrderDetailList;
    }

    public List<SoDeliver> getSoDeliverList() {
        return soDeliverList;
    }

    public void setSoDeliverList(List<SoDeliver> soDeliverList) {
        this.soDeliverList = soDeliverList;
    }
}
