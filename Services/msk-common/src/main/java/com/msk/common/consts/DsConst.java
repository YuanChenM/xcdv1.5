package com.msk.common.consts;

/**
 * 订单模块常量定义
 *
 * @author likai
 * @version 1.0
 **/
public interface DsConst {
    /**
     * 发货入库单来源标识
     */
    interface DeliverySourceFlg {
        String TYPE = "DeliverySourceFlg";
        /** 平台供应链 */
        String DELIVERY_FROM_DS = "1";
        /** 卖家采供链 */
        String DELIVERY_FROM_SSC = "2";
    }

}
