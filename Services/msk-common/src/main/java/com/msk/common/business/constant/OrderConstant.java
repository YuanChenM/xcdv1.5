package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/29.
 * Order常量定义
 */
public interface OrderConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Order";
    /**
     * 订单类型
     */
    interface OrderType{
        String TYPE = "OrderType";
        /**分销订单*/
        int DISTRIBUTION_ORDER = 1;
        /**第三方订单*/
        int THIRD_PARTY_ORDER = 2;
        /**大促会订单*/
        int BIG_PROMOTION_ORDER = 3;
        /**买手囤货订单*/
        int BUYER_STOCKPILING_ORDER=4;
        /**买手销售订单*/
        int BUYER_SALE_ORDER=5;
        /**第三方买手销售订单*/
        int THIRD_BUYER_SALE_ORDER = 6;
        /**第三方买手囤货订单*/
        int THIRD_BUYER_ORDER = 7;
    }

    /**
     * 付款方式
     */
    interface PaymentType {
        String TYPE = "PaymentType";
        /**线上支付*/
        int PAYING_ONLINE = 1;
        /**货到付款（线下支付）*/
        int CASH_ON_DELIVERY = 2;
    }

    /**
     * 订单细节类型
     */
    interface OrderDetailType{
        String TYPE = "OrderDetailType";
        /**正常订单*/
        int NORMAL = 1;
        /**非正常订单*/
        int UNNORMAL = 2;
        /**促销订单*/
        int push = 3;
    }

    /**
     * 订单等级
     */
    interface OrderLevel{
        String TYPE = "OrderLevel";
        /**超级大宗订单*/
        int SUPER_ORDER = 4;
        /**大宗订单*/
        int LARGE_ORDER = 3;
        /**大额订单*/
        int BIG_ORDER = 2;
        /**标准订单*/
        int STANDARD_ORDER = 1;
    }

    /**
     * 发票类型
     */
    interface InvoiceType{
        String TYPE ="InvoiceType";
        /**普通发票*/
        int PLAIN_INVOICE = 1;
        /**增值税普通发票*/
        int VAT_INVOICE = 2;
        /**增值税专用发票*/
        int VATS_PECIAL_INVOICE = 3;
    }

    /**
    * 退货类型
    */
    interface ReturnType{
        String TYPE = "ReturnType";
        /**迟收退货*/
        int DISTRIBUTION_RETURN = 1;
        /**现场拒收*/
        int THIRDPARTY_RETURN = 2;
        /**平台退货*/
        int DCH_RETURN = 3;
    }

    /**
     * 退货
     */
    interface ReturnMode{
        String TYPE = "ReturnMode";
        /**全退*/
        int WHOLE_RETURN = 1;
        /**部分退*/
        int PART_RETURN = 2;
    }

    /**
     *取消类型
     */
    interface OrderCancleType{
        String TYPE = "OrderCancleType";
        /**买家取消*/
        int BUYERS_CANCEL = 1;
        /**不同意拼货取消*/
        int DISAGREE_HARD_GOOD_SCANCLE = 2;
        /**不同意分批取消*/
        int DISAGREE_BATCH_GOODS_CANCLE =3;
    }


    /**
     *分批发货确认
     */
    interface BatchDeliveryConfirmation{
        String TYPE = "BatchDeliveryConfirmation";
        /**不分批*/
        int NO_BATCH = 0;
        /**发生分批*/
        int BATCH = 1;
        /**买家同意分批*/
        int BUYER_AGREE_BATCH = 2;
        /**买家确认取消*/
        int BUYER_CONFIRM_CANCEL = 3;
    }

    /**
     *分批发货确认
     */
    interface  PickingStateDifference{
        String TYPE = "PickingStateDifference";
        /**无差异*/
        int NO_DIFFENCE = 0;
        /**发生差异*/
        int DIFFENCE = 1;
        /**买家同意差异*/
        int BUYER_AGREE_DIFFENCE = 2;
        /**买家确认取消*/
        int BUYER_CONFIRM_CANCEL = 3;
    }


    /**
     *配送类型
     */
    interface DeliveryType{
        String TYPE = "DeliveryType";
        /**汽车配送*/
        int CAR_DISTRIBUTION = 1;
        /**空运*/
        int PLANE_DISTRIBUTION = 2;
    }

    /**
     *库存类型
     */
    interface StockType{
        String TYPE = "StockType";
        /**正常库存*/
        int NORMAL_STOCK = 1;
        /**非正常库存*/
        int UNNORMAL_STOCK = 2;
        /**促销库存*/
        int PUSH_STOCK = 3;
    }

    /**
     * 供货平台
     *
     */
    interface SupplyPlatform{
        String TYPE = "SupplyPlatform";
        /**神农客*/
        int SNKREGIST = 1;
        /**美食客*/
        int MSKREGIST = 2;
        /**大促会*/
        int micro_mall_platform = 3;

    }

    /**
     * 需求状态
     *
     */
    interface DemandStatus {
        /**待审核*/
        int PENDING_AUDIT = 1;
        /**已审核*/
        int HAVE_AUDITED = 2;
    }

    /**
     * 订单状态定义
     *
     */
    interface OrderStatus {
        String TYPE = "OrderStatus";
        /**新建*/
        int NEW = 1;
        /**待付款*/
        int OBLIGATION = 2;
        /**已付款*/
        int PAYMENT = 3;
        /**待审核*/
        int PENDING_AUDIT = 4;
        /**已审核*/
        int HAVE_AUDITED = 5;
        /**待分销*/
        int WAIT_DISTRIBUTION = 6;
        /**分销中*/
        int IN_DISTRIBUTION = 7;
        /**已确认*/
        int CONFIRM = 8;
        /**待发货*/
        int WAIT_SEND = 9;
        /**部分发货*/
        int PARTIAL_SHIPMENT = 10;
        /**部分收货*/
        int PARTIAL_RECEIPT = 11;
        /**全部发货*/
        int ALL_SHIPMENT = 12;
        /**分销失败*/
        int DISTRIBUTION_FAIL = 14;
        /**全部收货*/
        int ALL_RECEIPT = 13;
        /**取消*/
        int CANCEL = 99;
    }

    /**
     * 订单明细状态
     */
    interface OrderDetailStatus{
         String TYPE = "OrderDetailStatus";
        /**待分销*/
         int WAIT_DISTRIBUTION = 1;
        /**已确认*/
         int CONFIRM = 2;
        /**待发货*/
         int WAIT_SEND = 3;
        /**部分发货 */
         int PARTIAL_SHIPMENT = 4;
        /**部分收货*/
         int PARTIAL_RECEIPT = 5;
        /**全部发货*/
         int ALL_SHIPMENT = 6;
        /**全部收货*/
         int ALL_RECEIPT = 7;
        /**取消*/
         int CANCEL = 99;
    }

    /**
     * 订单明细对于的供应商状态
     */
    interface OrderDetailAvailabilityStatus{
         String TYPE = "OrderDetailAvailabilityStatus";
        /**已确认*/
         int CONFIRM = 1;
        /**待发货*/
         int WAIT_SEND = 2;
        /**部分发货*/
         int PARTIAL_SHIPMENT = 3;
        /**部分收货*/
         int PARTIAL_RECEIPT = 4;
        /**全部发货*/
         int ALL_SHIPMENT = 5;
        /**全部收货*/
         int ALL_RECEIPT = 6;
        /**取消*/
         int CANCEL = 99;
    }

    /**
     * 退货单状态定义
     */
    interface ReturnOrderStatus{
        /**退货单对应表中的状态 */
         String TYPE = "ReturnOrderStatus";
        /**申请*/
         int APPLY = 1;
        /**待审核*/
         int PENDING_AUDIT = 2;
        /**已审核*/
         int HAVE_AUDITED = 3;
        /**待收货*/
         int WAIT_RECEIPT = 4;
        /**处理中*/
         int PROCESS=5;
        /**收货中*/
         int RECEIVING =6;
        /**已收货*/
         int RECEIPT = 7;
        /**入库*/
         int STORAGE = 8;
        /**取消*/
         int CANCEL = 99;
    }

    /**
     * 产品订单状态定义
     */
    interface ProOrderStatus{
        String TYPE = "ProOrderStatus";
        /**待审批*/
        int PENDING_AUDIT =  1;
        /**已审批*/
        int HAVE_AUDITED = 2;
    }

    /**
     * 金额类型： 0：付款 1：退款 2:混合
     */
    interface AmountType{
        String TYPE = "AmountType";
        /**付款*/
        int PAID = 0;
        /**退款*/
        int REFUND = 1;
        /**混合*/
        int BOTH = 2;
    }

    /**
     * 收款方，付款方角色信息
     */
    interface RoleType{
        String TYPE = "RoleType";
        /**平台*/
        int ROLE_PLATFORM = 1;
        /**买家*/
        int ROLE_BIDDER = 2;
        /**卖家*/
        int ROLE_SELLER = 3;
        /**买手*/
        int ROLE_BUYERE = 4;
    }

    /**
     * 结算标志： 1：付 2：退 3：平
     */
    interface SettlementStatus{
        String TYPE = "SettlementStatus";
        /**付*/
        int FOR_PAY = 1;
        /**退*/
        int FOR_REFUND = 2;
        /**平*/
        int BALANCED = 3;
    }

    /**
     * 交易标志： :0：正常 1：交易关闭
     */
    interface TransFlg{
        String TYPE = "TransFlg";
        /**正常*/
        String NORMAL = "0";
        /**交易关闭*/
        String CLOSED = "1";
    }

    /**
     * 退回费用类型: 0：退货退款 1：拒收退款 2：关闭订单
     */
    public interface RefundType{
        String TYPE = "RefundType";
        /**退货退款*/
        int GOODSRETURN = 0;
        /**拒收退款*/
        int REJECT = 1;
        /**关闭订单*/
        int CANCELORDER = 2;
    }

    /**
     * 支付方式: 1：现金 2：转账 3：支票 4：冲抵核销
     */
    public interface PaidType{
        String TYPE = "PaidType";
        /**现金*/
        int CASH = 1;
        /**转账*/
        int TRANSFER = 2;
        /**支票*/
        int CHEQUE = 3;
        /**冲抵核销*/
        int VER = 4;
    }

    /**
     * 交易类型: 0:主订单 1:管理费用清单
     */
    public interface TransType {
        String TYPE = "TransType";
        /**主订单*/
        int MAINORDER = 0;
        /**管理费用清单*/
        int MANAGERFEE = 1;
    }

    /**
     * 计费标志: 0：未计费 1：已计费
     */
    public interface ChargeFlg{
        String TYPE = "ChargeFlg";
        /*未计费*/
        String UNCHARGED = "0";
        /**已计费*/
        String CHARGED = "1";
    }

    /**
     * 冲抵核销标志: 0：正常收支 1：冲抵核销
     */
    public interface MatchVerFlg{
        String TYPE = "MatchVerFlg";
        /**正常收支*/
        String COMMON = "0";
        /**冲抵核销*/
        String MATCHVER = "1";
    }

    /**
     * 对账标志: 0：可对账 1：已对账 2：挂起
     */
    public interface StatementFlg{
        String TYPE = "StatementFlg";
        /**可对账*/
        String FORCHECKING = "0";
        /**已对账*/
        String CHECKED = "1";
        /**挂起*/
        String HANGUP = "2";
    }

    /**
     * 重新发货标志: 0：不重新发货 1：重新发货
     */
    public interface ReShipFlg{
        String TYPE = "ReShipFlg";
        /**不重新发货*/
        String RESHIP = "0";
        /**重新合作发货*/
        String UNRESHIP = "1";
    }

    /**
     * 平台分类
     */
    interface OrderSource{
        String TYPE = "OrderSource";
        /**云冻品平台网站*/
        int SNK = 1;
        /**云冻品B2B平台网站*/
        int MSK = 2;
        /**微商城网站*/
        int Micro_mall_platform = 3;
        /**买手APP*/
        int BUYER_APP = 4;
    }

    /**
     * 结算状态
     */
    interface SettlementFlg{
        String TYPE = "SettlementFlg";
        /**未结算*/
        int UNSETTLED = 1;
        /**处理中*/
        int TREATMENT = 2;
        /**已结算*/
        int SETTLED = 3;
    }

    interface SplitDeliveryFlg{
        String TYPE = "SplitDeliveryFlg";
        /**不分批*/
        int NO_BATCH = 0;
        /**发生分批*/
        int BATCH = 1;
        /**买家同意分批*/
        int BUYER_AGREE_BATCH = 2;
        /**买家确认取消*/
        int BUYER_CONFIRM_CANCEL = 3;
    }

    interface PickDiff{
        String TYPE = "PickDiff";
        /**无差异*/
        int NO_DIFFENCE = 0;
        /**发生差异*/
        int DIFFENCE = 1;
        /**买家同意差异*/
        int BUYER_AGREE_DIFFENCE = 2;
        /**买家确认取消*/
        int BUYER_CONFIRM_CANCEL = 3;
    }

    interface ShipCancleReason{
        String TYPE = "ShipCancleReason";
        /**客户取消*/
        int CUM_CANCEL = 1;
        /**库存不足*/
        int UNENOUGH = 2;
        /**其他原因*/
        int OTHERS = 99;
    }

    interface OrderTakerType{
        String TYPE = "OrderTakerType";
        /**卖家*/
        int buyers = 1;
        /**管家*/
        int manager = 2;
    }

    interface SalePlatform{
        String TYPE = "SalePlatform";
        /**云冻品平台网站*/
        int SNK = 1;
        /**云冻品B2B平台网站*/
        int MSK = 2;
    }

    interface ReturnSource {
        String TYEP = "ReturnSource";
        /**云冻品平台网站*/
        int SNK = 1;
        /**云冻品B2B平台网站*/
        int MSK = 2;
        /**微商城网站*/
        int Micro_mall_platform = 3;
        /**买手APP*/
        int BUYER_APP = 4;
        /**司机PDA*/
        int DRIVER_PDA = 99;
    }
}
