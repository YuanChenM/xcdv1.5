package com.msk.common.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态定义
 * 
 * @author jiang_nan
 */
public final class StatusConst {
    public static Map<String, String> ORDER_STATUS_MAP = new HashMap<String, String>();

    static {
        ORDER_STATUS_MAP.put(String.valueOf(OrderStatusDef.NEW), "新建");

    }

    /**
     * Batch 状态定义
     * 
     * @author jiang_nan
     */
    public final static class BatchStatusDef {
        /** Type:BatchStatus */
        public final static String TYPE = "BatchStatus";
        /** NEW:新建 */
        public final static int NEW = 1;
        /** RUN:执行中 */
        public final static int RUN = 2;
        /** EXCETION:异常终止 */
        public final static int EXCETION = 3;
        /** END:正常结束 */
        public final static int END = 4;
    }

    /**
     * 订单状态定义
     * 
     * @author jiang_nan
     */
    public final static class OrderStatusDef {
        /** TYPE:OrderStatus */
        public final static String TYPE = "OrderStatus";
        /** NEW:新建 */
        public final static int NEW = 1;
        /** OBLIGATION:待付款 */
        public final static int OBLIGATION = 2;
        /** PAYMENT:已付款 */
        public final static int PAYMENT = 3;
        /** PENDING_AUDIT:待审核 */
        public final static int PENDING_AUDIT = 4;
        /** HAVE_AUDITED:已审核 */
        public final static int HAVE_AUDITED = 5;
        /** WAIT_DISTRIBUTION:待分销 */
        public final static int WAIT_DISTRIBUTION = 6;
        /** IN_DISTRIBUTION:分销中 */
        public final static int IN_DISTRIBUTION = 7;
        /** CONFIRM:已确认 */
        public final static int CONFIRM = 8;
        /** WAIT_SEND:待发货 */
        public final static int WAIT_SEND = 9;
        /** PARTIAL_SHIPMENT:部分发货 */
        public final static int PARTIAL_SHIPMENT = 10;
        /** PARTIAL_RECEIPT:部分收货 */
        public final static int PARTIAL_RECEIPT = 11;
        /** ALL_SHIPMENT:全部发货 */
        public final static int ALL_SHIPMENT = 12;
        /** DISTRIBUTION_FAIL:分销失败 */
        public final static int DISTRIBUTION_FAIL = 14;
        /** ALL_RECEIPT:全部收货 */
        public final static int ALL_RECEIPT = 13;
        /** CANCEL:取消 */
        public final static int CANCEL = 99;

    }
    
    /**
     * 订单明细状态
     */
    public final static class OrderDetailStatusDef{
        public final static String TYPE = "OrderDetailStatus";
        /** WAIT_DISTRIBUTION:待分销 */
        public final static int WAIT_DISTRIBUTION = 1;
        /** CONFIRM:已确认 */
        public final static int CONFIRM = 2;
        /**WAIT_SEND:待发货*/
        public final static int WAIT_SEND = 3;
        /** PARTIAL_SHIPMENT:部分发货 */
        public final static int PARTIAL_SHIPMENT = 4;
        /** PARTIAL_RECEIPT:部分收货 */
        public final static int PARTIAL_RECEIPT = 5;
        /** ALL_SHIPMENT:全部发货 */
        public final static int ALL_SHIPMENT = 6;
        /** ALL_RECEIPT:全部收货 */
        public final static int ALL_RECEIPT = 7;
        /** CANCEL:取消 */
        public final static int CANCEL = 99;
    }


    /**
     * 退货单状态定义
     */
    public final static class ReturnOrderStatusDef {
        /** 退货单对应表中的状态 */
        public final static String TYPE = "ReturnOrderStatus";
        /** APPLY:申请 */
        public final static int APPLY = 1;
        /** PENDING_AUDIT:待审核 */
        public final static int PENDING_AUDIT = 2;
        /** HAVE_AUDITED:已审核 */
        public final static int HAVE_AUDITED = 3;
        /** WAIT_RECEIPT:待收货 */
        public final static int WAIT_RECEIPT = 4;
        /** PROCESS 处理中*/
        public final static int PROCESS=5;
        /**  RECEIVING  收货中*/
        public final static int RECEIVING =6;
        /** RECEIPT:已收货 */
        public final static int RECEIPT = 7;
        /** STORAGE:入库  完结*/
        public final static int STORAGE = 8;
    }

    /**
     * 订单明细对于的供应商状态
     */
    public final static class OrderDetailAvailabilityStatusDef{
        public final static String TYPE = "OrderDetailAvailability";
        /** CONFIRM:已确认 */
        public final static int CONFIRM = 1;
        /**WAIT_SEND:待发货*/
        public final static int WAIT_SEND = 2;
        /** PARTIAL_SHIPMENT:部分发货 */
        public final static int PARTIAL_SHIPMENT = 3;
        /** PARTIAL_RECEIPT:部分收货 */
        public final static int PARTIAL_RECEIPT = 4;
        /** ALL_SHIPMENT:全部发货 */
        public final static int ALL_SHIPMENT = 5;
        /** ALL_RECEIPT:全部收货 */
        public final static int ALL_RECEIPT = 6;
        /** CANCEL:取消 */
        public final static int CANCEL = 99;
    }
    /**
     * 订单发货单状态
     */
    public final static class OrderShipStatusDef{
        public final static String TYPE = "OrderShipStatus";
        /** WAIT_SEND:待发货 */
        public final static int WAIT_SEND = 1;
        /** PARTIAL_SHIPMENT:部分发货 */
        public final static int PARTIAL_SHIPMENT = 2;
        /** ALL_SHIPMENT:全部发货 */
        public final static int ALL_SHIPMENT = 3;
        /** PARTIAL_RECEIPT:部分收货 */
        public final static int PARTIAL_RECEIPT = 4;
        /** ALL_RECEIPT:全部收货 */
        public final static int ALL_RECEIPT = 5;
        /** ALL_LATE_RECEIPT:全部迟收 */
        public final static int ALL_LATE_RECEIPT = 6;
        /** ALL_RETURN:全部退货 */
        public final static int ALL_RETURN = 7;
        /** WAIT_CANCEL:待取消 */
        public final static int WAIT_CANCEL = 97;
        /** CANCELING:取消中 */
        public final static int CANCELING = 98;
        /** CANCELED:已取消 */
        public final static int CANCELED = 99;
    }

    /**
     * 订单状态定义
     *
     * @author sun_jiaju
     */
    public final static class SoOrderStatusDef {
        /** TYPE:OrderStatus */
        public final static String TYPE = "SoOrderStatus";
        /** PENDING_AUDIT:待审核 */
        public final static int PENDING_AUDIT = 1;
        /** OBLIGATION:待付款 */
        public final static int OBLIGATION = 2;
        /** WAIT_SEND:待发货 */
        public final static int WAIT_SEND = 3;
        /** PARTIAL_SHIPMENT:部分发货 */
        public final static int PARTIAL_SHIPMENT = 4;
        /** ALL_SHIPMENT:发货完成 */
        public final static int ALL_SHIPMENT = 5;
        /** COMPLETED:已完成 */
        public final static int COMPLETED = 6;
        /** ALL_RETURN:全部退货 */
        public final static int ALL_RETURN = 7;
        /** CANCEL:已取消 */
        public final static int CANCEL = 99;
    }

    /**
     * 订单付款状态定义
     *
     * @author sun_jiaju
     */
    public final static class PayStatusDef {
        /** TYPE:PayStatusDef */
        public final static String TYPE = "PayStatusDef";
        /** NON_PAY:未支付 */
        public final static int NON_PAY = 0;
        /** PART_PAY:部分支付 */
        public final static int PART_PAY = 1;
        /** ALL_PAY:已支付 */
        public final static int ALL_PAY = 2;
    }

    /**
     * 退货单状态定义
     */
    public final static class SoReturnStatusDef {
        /** 退货单对应表中的状态 */
        public final static String TYPE = "ReturnStatus";
        /** APPLY:申请 */
        public final static int APPLY = 1;
        /** PENDING_AUDIT:待审核 */
        public final static int PENDING_AUDIT = 2;
        /** HAVE_AUDITED:已审核 */
        public final static int HAVE_AUDITED = 3;
        /** WAIT_PROCESS:待处理 */
        public final static int WAIT_PROCESS = 4;
        /** PROCESS 处理中*/
        public final static int PROCESS = 5;
        /**  RECEIVING  收货中*/
        public final static int RECEIVING =6;
        /** RECEIPT:已收货 */
        public final static int RECEIPT = 7;
        /** STORAGE:入库 */
        public final static int STORAGE = 8;
        /** LATE_RECEIPT:迟收成功 */
        public final static int LATE_RECEIPT = 9;
        /** CANCELED:已取消 */
        public final static int CANCELED = 99;
    }
}
