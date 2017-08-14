package com.msk.common.constant.business;

/**
 * Created by dai_youcheng on 2016/8/26.
 * SoStock常量定义
 */
public interface InventoryCodeMasterDef {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "InventoryCodeMasterDef";

    /**
     * 入库类型
     */
    public interface inboundType {
        String TYPE = "inboundType";
        /**采购入库*/
        int IT_PUCHARSE = 11;
        /**调整入库*/
        int IT_ADJUST = 12;
        /**退货入库*/
        int IT_RETURN = 13;
        /**样品入库*/
        int IT_TRYOUT = 14;
    }

    /**
     * 出库类型
     */
    public interface outboundType {
        String TYPE = "outboundType";
        /**销售出库*/
        int OT_SALE = 21;
        /**调整出库*/
        int OT_ADJUST = 22;
        /**退货出库*/
        int OT_RETURN = 23;
        /**样品出库*/
        int OT_TRYOUT = 24;
    }

    /**
     * 货品类型
     */
    public interface goodType {
        String TYPE = "goodType";
        /**良品*/
        int GT_GOOD = 31;
        /**不良品*/
        int GT_REJECT = 32;
        /**问题品*/
        int GT_PROBLEM = 33;
    }

    /**
     * 库存状态
     */
    public interface ivStatus {
        String TYPE = "ivStatus";
        /**采购在途*/
        int IS_IN_TRANSIT = 51;
        /**未收货*/
        int IS_NOT_RECEIVE = 519;
        /**入库中*/
        int IS_INBOUNDING = 52;
        /**在库*/
        int IS_PUTAWAY = 53;
        /**出库中*/
        int IS_OUTBOUNDING = 54;
        /**已发货*/
        int IS_DISPATCHED = 55;
        /**未交付*/
        int IS_NOT_DELIVER = 559;
        /**已交付*/
        int IS_DELIVERED = 56;

    }
}
