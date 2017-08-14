/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.List;

/**
 * <p>表so_order对应的SoOrder。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoOrder extends BaseEntity {
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<SoOrderDetail> soOrderDetailList;

    @OneToMany
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "orderId")
    private List<SoOrderShip> soOrderShipList;

    @OneToMany(fetch = FetchType.LAZY)
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "orderId")
    private List<SoOrderShipDetail> soOrderShipDetailList;

    @OneToMany
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "orderId")
    private List<SoSubOrder> soSubOrders;

    @OneToMany
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "orderId")
    private List<SoSubOrderDetail> soSubOrderDetailList;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<SoOrderStatus> soOrderStatusList;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<SoSubOrderStatus> soSubOrderStatusList;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", unique = true, nullable = false, insertable = false, updatable = false)
    private SoOrderReceiveDemand soOrderReceiveDemand;

    @OneToMany
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "orderId")
    private List<SoPayment> soPaymentList;

    private static final long serialVersionUID = 1L;
    /**
     * 订单ID
     */
    @Id
    private Long orderId;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 订单辅码（时间编码)
     */
    private String orderViceCode;
    /**
     * 购物意愿ID(暂留)
     */
    private Long proId;
    /**
     * 购物意愿编码(暂留)
     */
    private String proCode;
    /**
     * 卖家编码
     * 云冻品为平台，
     * B2B第三方就是第三方卖家
     */
    private String sellerCode;
    /**
     * 卖家名称
     */
    private String sellerName;
    /**
     * 卖家ID
     */
    private String buyerId;
    /**
     * 买家编码
     */
    private String buyerCode;
    /**
     * 买家名称
     */
    private String buyerName;
    /**
     * 买家类别-CodeMaster
     * 1:分销买家,
     * 2:菜场买家,
     * 3:团膳买家,
     * 4:火锅买家,
     * 5:中餐买家,
     * 6:西餐买家,
     * 7:小吃烧烤买家,
     * 8:加工厂买家
     */
    private Integer buyerType;
    /**
     * 买家所属菜场、市场名称
     */
    private String buyerMarketName;
    /**
     * 是否开票：1:是；0-否(暂留)
     */
    private String needInvoice;
    /**
     * 订单所属物流区域
     */
    private String districtCode;
    /**
     * 订单所属物流区域名称
     */
    private String districtName;
    /**
     * 订单来源，CodeMaster，是将系统模块Code转为1，2，3
     */
    private Integer orderSource;
    /**
     * 销售平台，CodeMaster，1-云冻品；2-云冻品B2B
     */
    private String salePlatform;
    /**
     * 订单类型-CodeMaster
     * 初始只有4种
     * 1-标准分销订单（V）
     * 2-第三方订单（V）
     * 3-大促会订单
     * 4-买手囤货订单（V）
     * 5-买手销售订单
     * 6-第三方买手销售订单
     * 7-第三方买手囤货订单（V）
     */
    private Integer orderType;
    /**
     * 订单创建时间
     */
    private java.util.Date orderTime;
    /**
     * 订单总金额
     */
    private java.math.BigDecimal orderAmount;
    /**
     * 付款方式：1:在线支付,2:线下支付
     */
    private Integer paymentType;
    /**
     * 直销员-冻品管家
     */
    private String saId;
    /**
     * 冻品管家名称
     */
    private String saName;
    /**
     * 订单员，下单的人员
     */
    private String orderTaker;
    /**
     * 订单员类型，CodeMaster
     */
    private Integer orderTakerType;
    /**
     * 是否自配送1:是;0-否(暂留)
     */
    private String selfDeliveryFlg;
    /**
     * 分批发货确认(暂留)
     * 0:不分批,1:发生分批,2:买家同意分批,3:买家确认取消
     */
    private String splitDeliveryFlg;
    /**
     * 1:是,0-否(暂留)
     */
    private String dustbinFlg;
    /**
     * 1:是,0-否(暂留)
     */
    private String returnFlg;
    /**
     * 订单发货时间，记录首次发货时间
     */
    private java.util.Date orderSendTime;
    /**
     * 订单收货时间，记录最后收货时间
     */
    private java.util.Date orderReceiveTime;
    /**
     * 取消类型(暂留)-CodeMaster:OrderCancleType
     * 1.买家取消 2.不同意拼货的取消 3.不同意分批的取消
     */
    private Integer cancelType;
    /**
     * 取消原因(暂留)
     */
    private String cancelReason;
    /**
     * 订单主状态-CodeMaster
     */
    private Integer orderStatus;
    /**
     * 订单付款状态-CodeMaster
     * 0-未支付
     * 1-部分支付
     * 2-已支付
     */
    private Integer payStatus;
    /**
     * 已支付金额
     */
    private java.math.BigDecimal payAmount;
    /**
     * 分销模式-CodeMaster
     * 1-整单分销锁货
     * 2-分批分销锁货
     * 根据客户付款情况可能采用不同的分销模式，
     * 若已全部付款，采取整单分销锁货模式
     * 若采用货到付款，采取分批分销锁货模式
     */
    private Integer distributorMode;

    public List<SoSubOrder> getSoSubOrders() {
        return soSubOrders;
    }

    public void setSoSubOrders(List<SoSubOrder> soSubOrders) {
        this.soSubOrders = soSubOrders;
    }

    /**
     * <p>默认构造函数。</p>
     */
    public SoOrder() {

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
     * <p>订单辅码（时间编码)。</p>
     *
     * @return the 订单辅码（时间编码)
     */
    public String getOrderViceCode() {
        return orderViceCode;
    }

    /**
     * <p>订单辅码（时间编码)。</p>
     *
     * @param orderViceCode 订单辅码（时间编码)。
     */
    public void setOrderViceCode(String orderViceCode) {
        this.orderViceCode = orderViceCode;
    }

    /**
     * <p>购物意愿ID(暂留)。</p>
     *
     * @return the 购物意愿ID(暂留)
     */
    public Long getProId() {
        return proId;
    }

    /**
     * <p>购物意愿ID(暂留)。</p>
     *
     * @param proId 购物意愿ID(暂留)。
     */
    public void setProId(Long proId) {
        this.proId = proId;
    }

    /**
     * <p>购物意愿编码(暂留)。</p>
     *
     * @return the 购物意愿编码(暂留)
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p>购物意愿编码(暂留)。</p>
     *
     * @param proCode 购物意愿编码(暂留)。
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p>卖家编码
     * 云冻品为平台，
     * B2B第三方就是第三方卖家。</p>
     *
     * @return the 卖家编码
     * 云冻品为平台，
     * B2B第三方就是第三方卖家
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * <p>卖家编码
     * 云冻品为平台，
     * B2B第三方就是第三方卖家。</p>
     *
     * @param sellerCode 卖家编码
     *                   云冻品为平台，
     *                   B2B第三方就是第三方卖家。
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
     * 1:分销买家,
     * 2:菜场买家,
     * 3:团膳买家,
     * 4:火锅买家,
     * 5:中餐买家,
     * 6:西餐买家,
     * 7:小吃烧烤买家,
     * 8:加工厂买家。</p>
     *
     * @return the 买家类别-CodeMaster
     * 1:分销买家,
     * 2:菜场买家,
     * 3:团膳买家,
     * 4:火锅买家,
     * 5:中餐买家,
     * 6:西餐买家,
     * 7:小吃烧烤买家,
     * 8:加工厂买家
     */
    public Integer getBuyerType() {
        return buyerType;
    }

    /**
     * <p>买家类别-CodeMaster
     * 1:分销买家,
     * 2:菜场买家,
     * 3:团膳买家,
     * 4:火锅买家,
     * 5:中餐买家,
     * 6:西餐买家,
     * 7:小吃烧烤买家,
     * 8:加工厂买家。</p>
     *
     * @param buyerType 买家类别-CodeMaster
     *                  1:分销买家,
     *                  2:菜场买家,
     *                  3:团膳买家,
     *                  4:火锅买家,
     *                  5:中餐买家,
     *                  6:西餐买家,
     *                  7:小吃烧烤买家,
     *                  8:加工厂买家。
     */
    public void setBuyerType(Integer buyerType) {
        this.buyerType = buyerType;
    }


    public String getBuyerMarketName() {
        return buyerMarketName;
    }

    public void setBuyerMarketName(String buyerMarketName) {
        this.buyerMarketName = buyerMarketName;
    }

    /**
     * <p>是否开票：1:是；0-否(暂留)。</p>
     *
     * @return the 是否开票：1:是；0-否(暂留)
     */
    public String getNeedInvoice() {
        return needInvoice;
    }

    /**
     * <p>是否开票：1:是；0-否(暂留)。</p>
     *
     * @param needInvoice 是否开票：1:是；0-否(暂留)。
     */
    public void setNeedInvoice(String needInvoice) {
        this.needInvoice = needInvoice;
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
     * <p>订单所属物流区域名称。</p>
     *
     * @return the 订单所属物流区域名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * <p>订单所属物流区域名称。</p>
     *
     * @param districtName 订单所属物流区域名称。
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * <p>订单来源，CodeMaster，是将系统模块Code转为1，2，3。</p>
     *
     * @return the 订单来源，CodeMaster，是将系统模块Code转为1，2，3
     */
    public Integer getOrderSource() {
        return orderSource;
    }

    /**
     * <p>订单来源，CodeMaster，是将系统模块Code转为1，2，3。</p>
     *
     * @param orderSource 订单来源，CodeMaster，是将系统模块Code转为1，2，3。
     */
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * <p>销售平台，CodeMaster，1-云冻品；2-云冻品B2B。</p>
     *
     * @return the 销售平台，CodeMaster，1-云冻品；2-云冻品B2B
     */
    public String getSalePlatform() {
        return salePlatform;
    }

    /**
     * <p>销售平台，CodeMaster，1-云冻品；2-云冻品B2B。</p>
     *
     * @param salePlatform 销售平台，CodeMaster，1-云冻品；2-云冻品B2B。
     */
    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    /**
     * <p>订单类型-CodeMaster
     * 初始只有4种
     * 1-标准分销订单（V）
     * 2-第三方订单（V）
     * 3-大促会订单
     * 4-买手囤货订单（V）
     * 5-买手销售订单
     * 6-第三方买手销售订单
     * 7-第三方买手囤货订单（V）
     * 。</p>
     *
     * @return the 订单类型-CodeMaster
     * 初始只有4种
     * 1-标准分销订单（V）
     * 2-第三方订单（V）
     * 3-大促会订单
     * 4-买手囤货订单（V）
     * 5-买手销售订单
     * 6-第三方买手销售订单
     * 7-第三方买手囤货订单（V）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * <p>订单类型-CodeMaster
     * 初始只有4种
     * 1-标准分销订单（V）
     * 2-第三方订单（V）
     * 3-大促会订单
     * 4-买手囤货订单（V）
     * 5-买手销售订单
     * 6-第三方买手销售订单
     * 7-第三方买手囤货订单（V）
     * 。</p>
     *
     * @param orderType 订单类型-CodeMaster
     *                  初始只有4种
     *                  1-标准分销订单（V）
     *                  2-第三方订单（V）
     *                  3-大促会订单
     *                  4-买手囤货订单（V）
     *                  5-买手销售订单
     *                  6-第三方买手销售订单
     *                  7-第三方买手囤货订单（V）
     *                  。
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * <p>订单创建时间。</p>
     *
     * @return the 订单创建时间
     */
    public java.util.Date getOrderTime() {
        return orderTime;
    }

    /**
     * <p>订单创建时间。</p>
     *
     * @param orderTime 订单创建时间。
     */
    public void setOrderTime(java.util.Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * <p>订单总金额。</p>
     *
     * @return the 订单总金额
     */
    public java.math.BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * <p>订单总金额。</p>
     *
     * @param orderAmount 订单总金额。
     */
    public void setOrderAmount(java.math.BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * <p>付款方式：1:在线支付,2:线下支付。</p>
     *
     * @return the 付款方式：1:在线支付,2:线下支付
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * <p>付款方式：1:在线支付,2:线下支付。</p>
     *
     * @param paymentType 付款方式：1:在线支付,2:线下支付。
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * <p>直销员-冻品管家。</p>
     *
     * @return the 直销员-冻品管家
     */
    public String getSaId() {
        return saId;
    }

    /**
     * <p>直销员-冻品管家。</p>
     *
     * @param saId 直销员-冻品管家。
     */
    public void setSaId(String saId) {
        this.saId = saId;
    }

    /**
     * <p>冻品管家名称。</p>
     *
     * @return the 冻品管家名称
     */
    public String getSaName() {
        return saName;
    }

    /**
     * <p>冻品管家名称。</p>
     *
     * @param saName 冻品管家名称。
     */
    public void setSaName(String saName) {
        this.saName = saName;
    }

    /**
     * <p>订单员，下单的人员。</p>
     *
     * @return the 订单员，下单的人员
     */
    public String getOrderTaker() {
        return orderTaker;
    }

    /**
     * <p>订单员，下单的人员。</p>
     *
     * @param orderTaker 订单员，下单的人员。
     */
    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    /**
     * <p>订单员类型，CodeMaster。</p>
     *
     * @return the 订单员类型，CodeMaster
     */
    public Integer getOrderTakerType() {
        return orderTakerType;
    }

    /**
     * <p>订单员类型，CodeMaster。</p>
     *
     * @param orderTakerType 订单员类型，CodeMaster。
     */
    public void setOrderTakerType(Integer orderTakerType) {
        this.orderTakerType = orderTakerType;
    }

    /**
     * <p>是否自配送1:是;0-否(暂留)。</p>
     *
     * @return the 是否自配送1:是;0-否(暂留)
     */
    public String getSelfDeliveryFlg() {
        return selfDeliveryFlg;
    }

    /**
     * <p>是否自配送1:是;0-否(暂留)。</p>
     *
     * @param selfDeliveryFlg 是否自配送1:是;0-否(暂留)。
     */
    public void setSelfDeliveryFlg(String selfDeliveryFlg) {
        this.selfDeliveryFlg = selfDeliveryFlg;
    }

    /**
     * <p>分批发货确认(暂留)
     * 0:不分批,1:发生分批,2:买家同意分批,3:买家确认取消。</p>
     *
     * @return the 分批发货确认(暂留)
     * 0:不分批,1:发生分批,2:买家同意分批,3:买家确认取消
     */
    public String getSplitDeliveryFlg() {
        return splitDeliveryFlg;
    }

    /**
     * <p>分批发货确认(暂留)
     * 0:不分批,1:发生分批,2:买家同意分批,3:买家确认取消。</p>
     *
     * @param splitDeliveryFlg 分批发货确认(暂留)
     *                         0:不分批,1:发生分批,2:买家同意分批,3:买家确认取消。
     */
    public void setSplitDeliveryFlg(String splitDeliveryFlg) {
        this.splitDeliveryFlg = splitDeliveryFlg;
    }

    /**
     * <p>1:是,0-否(暂留)。</p>
     *
     * @return the 1:是,0-否(暂留)
     */
    public String getDustbinFlg() {
        return dustbinFlg;
    }

    /**
     * <p>1:是,0-否(暂留)。</p>
     *
     * @param dustbinFlg 1:是,0-否(暂留)。
     */
    public void setDustbinFlg(String dustbinFlg) {
        this.dustbinFlg = dustbinFlg;
    }

    /**
     * <p>1:是,0-否(暂留)。</p>
     *
     * @return the 1:是,0-否(暂留)
     */
    public String getReturnFlg() {
        return returnFlg;
    }

    /**
     * <p>1:是,0-否(暂留)。</p>
     *
     * @param returnFlg 1:是,0-否(暂留)。
     */
    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    /**
     * <p>订单发货时间，记录首次发货时间。</p>
     *
     * @return the 订单发货时间，记录首次发货时间
     */
    public java.util.Date getOrderSendTime() {
        return orderSendTime;
    }

    /**
     * <p>订单发货时间，记录首次发货时间。</p>
     *
     * @param orderSendTime 订单发货时间，记录首次发货时间。
     */
    public void setOrderSendTime(java.util.Date orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    /**
     * <p>订单收货时间，记录最后收货时间。</p>
     *
     * @return the 订单收货时间，记录最后收货时间
     */
    public java.util.Date getOrderReceiveTime() {
        return orderReceiveTime;
    }

    /**
     * <p>订单收货时间，记录最后收货时间。</p>
     *
     * @param orderReceiveTime 订单收货时间，记录最后收货时间。
     */
    public void setOrderReceiveTime(java.util.Date orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    /**
     * <p>取消类型(暂留)-CodeMaster:OrderCancleType
     * 1.买家取消 2.不同意拼货的取消 3.不同意分批的取消。</p>
     *
     * @return the 取消类型(暂留)-CodeMaster:OrderCancleType
     * 1.买家取消 2.不同意拼货的取消 3.不同意分批的取消
     */
    public Integer getCancelType() {
        return cancelType;
    }

    /**
     * <p>取消类型(暂留)-CodeMaster:OrderCancleType
     * 1.买家取消 2.不同意拼货的取消 3.不同意分批的取消。</p>
     *
     * @param cancelType 取消类型(暂留)-CodeMaster:OrderCancleType
     *                   1.买家取消 2.不同意拼货的取消 3.不同意分批的取消。
     */
    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    /**
     * <p>取消原因(暂留)。</p>
     *
     * @return the 取消原因(暂留)
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * <p>取消原因(暂留)。</p>
     *
     * @param cancelReason 取消原因(暂留)。
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    /**
     * <p>订单主状态-CodeMaster。</p>
     *
     * @return the 订单主状态-CodeMaster
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * <p>订单主状态-CodeMaster。</p>
     *
     * @param orderStatus 订单主状态-CodeMaster。
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * <p>订单付款状态-CodeMaster
     * 0-未支付
     * 1-部分支付
     * 2-已支付。</p>
     *
     * @return the 订单付款状态-CodeMaster
     * 0-未支付
     * 1-部分支付
     * 2-已支付
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * <p>订单付款状态-CodeMaster
     * 0-未支付
     * 1-部分支付
     * 2-已支付。</p>
     *
     * @param payStatus 订单付款状态-CodeMaster
     *                  0-未支付
     *                  1-部分支付
     *                  2-已支付。
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * <p>已支付金额。</p>
     *
     * @return the 已支付金额
     */
    public java.math.BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * <p>已支付金额。</p>
     *
     * @param payAmount 已支付金额。
     */
    public void setPayAmount(java.math.BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * <p>分销模式-CodeMaster
     * 1-整单分销锁货
     * 2-分批分销锁货
     * 根据客户付款情况可能采用不同的分销模式，
     * 若已全部付款，采取整单分销锁货模式
     * 若采用货到付款，采取分批分销锁货模式。</p>
     *
     * @return the 分销模式-CodeMaster
     * 1-整单分销锁货
     * 2-分批分销锁货
     * 根据客户付款情况可能采用不同的分销模式，
     * 若已全部付款，采取整单分销锁货模式
     * 若采用货到付款，采取分批分销锁货模式
     */
    public Integer getDistributorMode() {
        return distributorMode;
    }

    /**
     * <p>分销模式-CodeMaster
     * 1-整单分销锁货
     * 2-分批分销锁货
     * 根据客户付款情况可能采用不同的分销模式，
     * 若已全部付款，采取整单分销锁货模式
     * 若采用货到付款，采取分批分销锁货模式。</p>
     *
     * @param distributorMode 分销模式-CodeMaster
     *                        1-整单分销锁货
     *                        2-分批分销锁货
     *                        根据客户付款情况可能采用不同的分销模式，
     *                        若已全部付款，采取整单分销锁货模式
     *                        若采用货到付款，采取分批分销锁货模式。
     */
    public void setDistributorMode(Integer distributorMode) {
        this.distributorMode = distributorMode;
    }


    public List<SoOrderDetail> getSoOrderDetailList() {
        return soOrderDetailList;
    }

    public void setSoOrderDetailList(List<SoOrderDetail> soOrderDetailList) {
        this.soOrderDetailList = soOrderDetailList;
    }

    public List<SoOrderShipDetail> getSoOrderShipDetailList() {
        return soOrderShipDetailList;
    }

    public void setSoOrderShipDetailList(List<SoOrderShipDetail> soOrderShipDetailList) {
        this.soOrderShipDetailList = soOrderShipDetailList;
    }


    public SoOrderReceiveDemand getSoOrderReceiveDemand() {
        return soOrderReceiveDemand;
    }

    public void setSoOrderReceiveDemand(SoOrderReceiveDemand soOrderReceiveDemand) {
        this.soOrderReceiveDemand = soOrderReceiveDemand;
    }

    public List<SoSubOrderDetail> getSoSubOrderDetailList() {
        return soSubOrderDetailList;
    }

    public void setSoSubOrderDetailList(List<SoSubOrderDetail> soSubOrderDetailList) {
        this.soSubOrderDetailList = soSubOrderDetailList;
    }

    public List<SoSubOrderStatus> getSoSubOrderStatusList() {
        return soSubOrderStatusList;
    }

    public void setSoSubOrderStatusList(List<SoSubOrderStatus> soSubOrderStatusList) {
        this.soSubOrderStatusList = soSubOrderStatusList;
    }


    public List<SoOrderStatus> getSoOrderStatusList() {
        return soOrderStatusList;
    }

    public void setSoOrderStatusList(List<SoOrderStatus> soOrderStatusList) {
        this.soOrderStatusList = soOrderStatusList;
    }

    public List<SoOrderShip> getSoOrderShipList() {
        return soOrderShipList;
    }

    public void setSoOrderShipList(List<SoOrderShip> soOrderShipList) {
        this.soOrderShipList = soOrderShipList;
    }

    public List<SoPayment> getSoPaymentList() {
        return soPaymentList;
    }

    public void setSoPaymentList(List<SoPayment> soPaymentList) {
        this.soPaymentList = soPaymentList;
    }
}
