package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/29.
 * Ds常量定义
 */
public interface DsConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Ds";
    /**
     * 产品状态
     */
    interface PdStockType{
        String TYPE = "PdStockType";
        /**生产期产品*/
        int PRODUCTION_OF_PRODUCTS = 1;
        /**待运库产品*/
        int SHIPMENT_OF_PRODUCTS = 2;
        /**在途产品*/
        int PRODUCTS_IN_TRANSIT = 3;
        /**入销售库产品*/
        int SALES_BASE_PRODUCTS = 4;
    }

    /**
     * 日期状态
     */
    interface HalfCodeType{
        String TYPE = "HalfCodeType";
        /**21-25日*/
        int TWENTY_TO_TWENTYFIVE = 1;
        /**26-N日*/
        int TWENTYSIX_TO_N = 2;
        /**1-5日*/
        int ONE_TO_FIVE = 3;
        /**6-10日*/
        int SIX_TO_TEN = 4;
        /**11-15日*/
        int ELEVEN_TO_FIFTEEN = 5;
        /**16-20日*/
        int SIXTEEN_TO_TWENTY = 6;
    }

    /**
     * 运输货物状态
     */
    interface DeliveryStockStatus{
        String TYPE = "DeliveryStockStatus";
        /**申请中*/
        int INAPPLY = 1;
        /**申请中*/
        int APPLYING = 2;
        /**已确认*/
        int CONFIRM = 3;
        /**待发货*/
        int WAIT_SEND = 4;
        /**全部发货*/
        int ALL_SHIPMENT = 5;
        /**部分收货*/
        int PARTIAL_RECEIPT = 6;
    }

    /**
     * 品牌类型(卖家供应链)
     */
    public interface BrandType{
        String TYPE = "BrandType";
        /**美侍客*/
        int BRANDTYPE_MSK = 1;
        /**神农客*/
        int BRANDTYPE_SNK = 2;
        /**绿美通*/
        int BRANDTYPE_LMT = 3;
    }

    /*
    * 发货入库单来源标识
    */
    public interface SourceFlg {
        String TYPE = "SourceFlg";
        /**平台供应链*/
        int MSK_DS = 1;
        /**卖家采供链*/
        int MSK_NEWDS = 2;

    }
}
