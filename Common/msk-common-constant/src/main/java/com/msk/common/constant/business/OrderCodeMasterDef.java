package com.msk.common.constant.business;

/**
 * 订单模块常量定义
 *
 * @author jiang_nan
 * @version 1.0
 */
public interface OrderCodeMasterDef {
    /**
     * 付款方式
     */
    interface PaymentType {
        String TYPE = "PaymentType";
        /**
         * 线上支付
         */
        int PAYING_ONLINE = 1;
        /**
         * 货到付款
         */
        int CASH_ON_DELIVERY = 2;
    }

    interface OrderType {
        String TYPE = "OrderType";
        /**
         * 分销订单
         */
        int DISTRIBUTION_ORDER = 1;
        /**
         * 第三方订单
         */
        int THIRD_PARTY_ORDER = 2;
        /**
         * 大促会订单
         */
        int BIG_PROMOTION_ORDER = 3;
        /**
         * 买手囤货订单
         */
        int BUYER_STOCKPILING_ORDER = 4;
        /**
         * 买手销售订单
         */
        int BUYER_SALE_ORDER = 5;
        /**
         * 第三方买手销售订单
         */
        int THIRD_BUYER_SALE_ORDER = 6;
        /**
         * 第三方买手囤货订单
         */
        int THIRD_BUYER_ORDER = 7;
    }

    /**
     * 订单来源
     */
    interface OrderSource {
        String TYPE = "OrderSource";
        /**
         * 云冻品平台网站
         */
        int SNK = 1;
        /**
         * 云冻品B2B平台网站
         */
        int MSK = 2;
        /**
         * 微商城网站
         */
        int WSC = 3;
        /**
         * 买手APP
         */
        int BYAPP = 4;
    }

    /**
     * 销售平台
     */
    interface SalePlatform {
        String TYPE = "SalePlatform";
        /**
         * 云冻品
         */
        String YDP = "1";
        /**
         * 云冻品B2B
         */
        String YDPB2B = "2";
    }

    interface OrderBuyerType {
        /**
         * master表里字段名
         */
        String TYPE = "BuyerType";
        /**
         * 分销买家
         */
        String Distribution = "1";
        /**
         * 菜场买家
         */
        String Market = "2";
        /**
         * 团膳买家
         */
        String GroupMeals = "3";
        /**
         * 火锅买家
         */
        String HotPot = "4";
        /**
         * 中餐买家
         */
        String ChineseFood = "5";
        /**
         * 西餐买家
         */
        String WestFood = "6";
        /**
         * 小吃烧烤买家
         */
        String SnackGrill = "7";
        /**
         * 加工厂买家
         */
        String Processing = "8";
        // 买手
        String BuyerShop = "9";
        // 第三方买手
        String ThirdBuyer = "10";
    }

    /**
     * 退货单类型
     */
    interface ReturnType {
        String TYPE = "ReturnType";
        // 迟收退货
        String LATER = "1";
        // 现场拒收
        String REJECT = "2";
        // 平台退货
        String RETURNED = "3";
    }

    /**
     * 退货方式
     */
    interface ReturnMode {
        String TYPE = "ReturnMode";
        // 1：全部
        int ALL = 1;
        // 2：部分
        int APART = 2;
    }

    /**
     * 卖家类型
     */
    interface SellerType {
        String TYPE = "SellerType";
        // 1 神农客
        int SLK = 1;
        // 2 美侍客
        int MSK = 2;
        // 3 买手
        int SALE = 3;
    }

    /**
     * 订单细节类型
     */
    interface OrderDetailType {
        String TYPE = "OrderDetailType";
        /**
         * 正常订单
         */
        int NORMAL = 1;
        /**
         * 非正常订单
         */
        int UNNORMAL = 2;
        /**
         * 促销订单
         */
        int push = 3;
    }

    /**
     * 订单等级
     */
    interface OrderLevel {
        String TYPE = "OrderLevel";
        /**
         * 超级大宗订单
         */
        int SUPER_ORDER = 4;
        /**
         * 大宗订单
         */
        int LARGE_ORDER = 3;
        /**
         * 大额订单
         */
        int BIG_ORDER = 2;
        /**
         * 标准订单
         */
        int STANDARD_ORDER = 1;
    }

    /**
     * 发票类型
     */
    interface InvoiceType {
        String TYPE = "InvoiceType";
        /**
         * 普通发票
         */
        int PLAIN_INVOICE = 1;
        /**
         * 增值税普通发票
         */
        int VAT_INVOICE = 2;
        /**
         * 增值税专用发票
         */
        int VATS_PECIAL_INVOICE = 3;
    }

    /**
     * 取消类型
     */
    interface OrderCancleType {
        String TYPE = "OrderCancleType";
        /**
         * 买家取消
         */
        int BUYERS_CANCEL = 1;
        /**
         * 不同意拼货取消
         */
        int DISAGREE_HARD_GOOD_SCANCLE = 2;
        /**
         * 不同意分批取消
         */
        int DISAGREE_BATCH_GOODS_CANCLE = 3;
    }

    /*
    *分批发货确认
    */
    interface SplitDeliveryFlg {
        String TYPE = "SplitDeliveryFlg";
        /**
         * 不分批
         */
        int NO_BATCH = 0;
        /**
         * 发生分批
         */
        int BATCH = 1;
        /**
         * 买家同意分批
         */
        int BUYER_AGREE_BATCH = 2;
        /**
         * 买家确认取消
         */
        int BUYER_CONFIRM_CANCEL = 3;
    }

    /*
    *拣货差异状态
    */
    interface PickDiff {
        String TYPE = "PickDiff";
        /**
         * 无差异
         */
        int NO_DIFFENCE = 0;
        /**
         * 发生差异
         */
        int DIFFENCE = 1;
        /**
         * 买家同意差异
         */
        int BUYER_AGREE_DIFFENCE = 2;
        /**
         * 买家确认取消
         */
        int BUYER_CONFIRM_CANCEL = 3;
    }

    /**
     * 配送类型
     */
    interface DeliveryType {
        String TYPE = "DeliveryType";
        /**
         * 汽车配送
         */
        int CAR_DISTRIBUTION = 1;
        /**
         * 空运
         */
        int PLANE_DISTRIBUTION = 2;
    }

    /**
     * 库存类型
     */
    interface StockType {
        String TYPE = "StockType";
        /**
         * 正常库存
         */
        int NORMAL_STOCK = 1;
        /**
         * 非正常库存
         */
        int UNNORMAL_STOCK = 2;
        /**
         * 促销库存
         */
        int PUSH_STOCK = 3;
    }

    /**
     * 供货平台
     */
    interface SupplyPlatform {
        String TYPE = "SupplyPlatform";
        /**
         * 神农客
         */
        int SNKREGIST = 1;
        /**
         * 美食客
         */
        int MSKREGIST = 2;
        /**
         * 大促会
         */
        int micro_mall_platform = 3;

    }


    /**
     * 发货取消原因
     */
    interface ShipCancleReason {
        String TYPE = "ShipCancleReason";
        /**
         * 客户取消
         */
        int CUM_CANCEL = 1;
        /**
         * 库存不足
         */
        int UNENOUGH = 2;
        /**
         * 其他原因
         */
        int OTHERS = 99;
    }

    /**
     * 订单付款状态定义
     */
    interface PayStatus {
        /**
         * TYPE:PayStatus
         */
        String TYPE = "PayStatus";
        /**
         * NON_PAY:未支付
         */
        int NON_PAY = 0;
        /**
         * PART_PAY:部分支付
         */
        int PART_PAY = 1;
        /**
         * ALL_PAY:全部支付
         */
        int ALL_PAY = 2;
        /**
         * PAYING:付款中
         */
        int PAYING = 3;
    }

    /**
     * 退货单状态定义
     */
    interface ReturnOrderStatus {
        /**
         * 退货单对应表中的状态
         */
        String TYPE = "ReturnOrderStatus";
        /**
         * 申请
         */
        int APPLY = 1;
        /**
         * 待审核
         */
        int PENDING_AUDIT = 2;
        /**
         * 已审核
         */
        int HAVE_AUDITED = 3;
        /**
         * 待处理
         */
        int WAIT_SALVE = 4;
        /**
         * 处理中
         */
        int PROCESS = 5;
        /**
         * 收货中
         */
        int RECEIVING = 6;
        /**
         * 已收货
         */
        int RECEIPT = 7;
        /**
         * 已入库
         */
        int STORAGE = 8;
        /**
         * 迟收成功
         */
        int EARN_LATER = 9;
        /**
         * 已取消
         */
        int CANCEL = 99;
    }

    /**
     * 订单明细对于的供应商状态
     */
    interface OrderDetailAvailabilityStatus {
        String TYPE = "OrderDetailAvailabilityStatus";
        /**
         * 已确认
         */
        int CONFIRM = 1;
        /**
         * 待发货
         */
        int WAIT_SEND = 2;
        /**
         * 部分发货
         */
        int PARTIAL_SHIPMENT = 3;
        /**
         * 部分收货
         */
        int PARTIAL_RECEIPT = 4;
        /**
         * 全部发货
         */
        int ALL_SHIPMENT = 5;
        /**
         * 全部收货
         */
        int ALL_RECEIPT = 6;
        /**
         * 全部退货
         */
        int ALL_RETURN = 7;
        /**
         * 分销失败
         */
        int DISTRIBUTION_FAIL = 8;
        /**
         * 取消
         */
        int CANCEL = 99;
    }

    /**
     * 订单发货单状态
     */
    interface ShipStatus {
        String TYPE = "ShipStatus";
        /**
         * WAIT_SEND:待发货
         */
        int WAIT_SEND = 1;
        /**
         * PARTIAL_SHIPMENT:部分发货
         */
        int PARTIAL_SHIPMENT = 2;
        /**
         * ALL_SHIPMENT:全部发货
         */
        int ALL_SHIPMENT = 3;
        /**
         * PARTIAL_RECEIPT:部分收货
         */
        int PARTIAL_RECEIPT = 4;
        /**
         * ALL_RECEIPT:全部收货
         */
        int ALL_RECEIPT = 5;
        /**
         * ALL_LATE_RECEIPT:全部迟收
         */
        int ALL_LATE_RECEIPT = 6;
        /**
         * ALL_RETURN:全部退货
         */
        int ALL_RETURN = 7;
        /**
         * SENDING:发货中
         */
        int SENDING = 8;
        /**
         * RECIPETING:收款中
         */
        int RECIPETING = 9;
        /**
         * WAIT_CANCEL:待取消
         */
        int WAIT_CANCEL = 97;
        /**
         * CANCELING:取消中
         */
        int CANCELING = 98;
        /**
         * CANCELED:已取消
         */
        int CANCELED = 99;
    }

    /**
     * 分批发货明细状态
     */
    interface SubOrderDetailStatus {
        /**
         * TYPE:SubOrderDetailStatus
         */
        String TYPE = "SubOrderDetailStatus";
        /**
         * WAIT_DISTRIBUTION:待分销
         */
        int WAIT_DISTRIBUTION = 1;
        /* * CONFIRM:已确认 */
        int CONFIRM = 2;
        /**
         * WAIT_SEND:待发货
         */
        int WAIT_SEND = 3;
        /**
         * PARTIAL_SHIPMENT:部分发货
         */
        int PARTIAL_SHIPMENT = 4;
        /**
         * PARTIAL_RECEIPT:部分收货
         */
        int PARTIAL_RECEIPT = 5;
        /**
         * ALL_SHIPMENT:全部发货
         */
        int ALL_SHIPMENT = 6;
        /**
         * ALL_RECEIPT:全部收货
         */
        int ALL_RECEIPT = 7;
        /**
         * ALL_RECEIPT:全部退货
         */
        int ALL_RETURN = 8;
        /**
         * CANCEL:取消
         */
        int CANCEL = 99;

    }

    /**
     * 分批订单状态
     */
    interface SubOrderStatus {
        /**
         * TYPE:OrderStatus
         */
        String TYPE = "SubOrderStatus";
        /**
         * NEW:新建
         */
        int NEW = 1;
        /**
         * OBLIGATION:待付款
         */
        int OBLIGATION = 2;
        /**
         * PAYMENT:已付款
         */
        int PAYMENT = 3;
        /**
         * PENDING_AUDIT:待审核
         */
        int PENDING_AUDIT = 4;
        /**
         * HAVE_AUDITED:已审核
         */
        int HAVE_AUDITED = 5;
        /**
         * WAIT_DISTRIBUTION:待分销
         */
        int WAIT_DISTRIBUTION = 6;
        /**
         * IN_DISTRIBUTION:分销中
         */
        int IN_DISTRIBUTION = 7;
        /**
         * CONFIRM:已确认
         */
        int CONFIRM = 8;
        /**
         * WAIT_SEND:待发货
         */
        int WAIT_SEND = 9;
        /**
         * PARTIAL_SHIPMENT:部分发货
         */
        int PARTIAL_SHIPMENT = 10;
        /**
         * PARTIAL_RECEIPT:部分收货
         */
        int PARTIAL_RECEIPT = 11;
        /**
         * ALL_SHIPMENT:全部发货
         */
        int ALL_SHIPMENT = 12;
        /**
         * ALL_RECEIPT:全部收货
         */
        int ALL_RECEIPT = 13;
        /**
         * DISTRIBUTION_FAIL:分销失败
         */
        int DISTRIBUTION_FAIL = 14;
        /**
         * PARTIAL_DISTRIBUTION:部分分销
         */
        int PARTIAL_DISTRIBUTION = 15;
        /**
         * ALL_RETURN:全部退货  当整单退货时，才会存在改状态
         */
        int ALL_RETURN = 16;
        /**
         * CANCEL:取消
         */
        int CANCEL = 99;

    }

    /**
     * 订单明细状态
     */
    interface OrderDetailStatus {
        /**
         * TYPE:OrderDetailStatus
         */
        String TYPE = "OrderDetailStatus";
        /**
         * WAIT_DISTRIBUTION:待分销
         */
        int WAIT_DISTRIBUTION = 1;
        /* * CONFIRM:已确认 */
        int CONFIRM = 2;
        /**
         * WAIT_SEND:待发货
         */
        int WAIT_SEND = 3;
        /**
         * PARTIAL_SHIPMENT:部分发货
         */
        int PARTIAL_SHIPMENT = 4;
        /**
         * PARTIAL_RECEIPT:部分收货
         */
        int PARTIAL_RECEIPT = 5;
        /**
         * ALL_SHIPMENT:全部发货
         */
        int ALL_SHIPMENT = 6;
        /**
         * ALL_RECEIPT:全部收货
         */
        int ALL_RECEIPT = 7;
        /**
         * ALL_RETURN:全部退货
         */
        int ALL_RETURN = 8;
        /**
         * CANCEL:取消
         */
        int CANCEL = 99;

    }

    /**
     * 订单状态定义
     */
    interface OrderStatus {
        String TYPE = "OrderStatus";
        /**
         * 新建
         */
        int NEW = 1;
        /**
         * 待付款
         */
        int OBLIGATION = 2;
        /**
         * 已付款
         */
            int PAYMENT = 3;
        /**
         * 待审核
         */
        int PENDING_AUDIT = 4;
        /**
         * 已审核
         */
        int HAVE_AUDITED = 5;
        /**
         * 待分销
         */
        int WAIT_DISTRIBUTION = 6;
        /**
         * 分销中
         */
        int IN_DISTRIBUTION = 7;
        /**
         * 已确认
         */
        int CONFIRM = 8;
        /**
         * 分销失败
         */
        int DISTRIBUTION_FAIL = 14;
        /**
         * 部分分销
         */
        int DISTRIBUTION_PART = 15;
        /**
         * 待发货
         */
        int WAIT_SEND = 9;
        /**
         * 部分发货
         */
        int PARTIAL_SHIPMENT = 10;
        /**
         * 部分收货
         */
        int PARTIAL_RECEIPT = 11;
        /**
         * 全部发货
         */
        int ALL_SHIPMENT = 12;

        /**
         * 全部收货
         */
        int ALL_RECEIPT = 13;

        /**
         * 全部退货
         */
        int RETURN_ALL = 16;
        /**
         * 取消中
         */
        int CANCELING = 98;
        /**
         * 已取消
         */
        int CANCEL = 99;
    }

    /*
     *产品等级
     */
    interface PdGrade {
        String TYPE = "PdGrade";
        /*A1*/
        int A1 = 1;
        /*A2*/
        int A2 = 2;
        /*A3*/
        int A3 = 3;
    }

    /*
    *是否已付款
    */
    interface IsPaid {
        String TYPE = "IsPaid";
        /*是*/
        int YER = 1;
        /*否*/
        int NO = 2;
    }

    /*
    *是否已开票
    */
    interface IsInvoice {
        String TYPE = "IsInvoice";
        /*是*/
        int YER = 1;
        /*否*/
        int NO = 2;
    }

    interface ReturnSource {
        String TYPE = "ReturnSource";
        /**
         * 云冻品平台网站
         */
        int SNK = 1;
        /**
         * 云冻品B2B平台网站
         */
        int MSK = 2;
        /**
         * 微商城网站
         */
        int Micro_mall_platform = 3;
        /**
         * 买手APP
         */
        int BUYER_APP = 4;
        /**
         * 司机PDA
         */
        int DRIVER_PDA = 99;
    }

    interface OrderTakerType {
        String TYPE = "OrderTakerType";
        /**
         * 卖家
         */
        int buyers = 1;
        /**
         * 管家
         */
        int manager = 2;
    }
}
