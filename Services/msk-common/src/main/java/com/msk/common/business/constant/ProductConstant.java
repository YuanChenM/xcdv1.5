package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/29.
 * Product常量定义
 */
public interface ProductConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Product";
    /**
     * 产品加工程度
     *
     */
    interface ProductProcessingDegree{
        String TYPE = "ProductProcessingDegree";
        /**白条*/
        int WHITE = 1;
        /**分割品*/
        int SPLIT_PRODUCT = 2;
        /**副产品*/
        int DEPUTY_PRODUCT = 3;
        /**净料*/
        int Net_material = 4;
        /**调理品(生)*/
        int Conditioning_products_born = 5;
        /**调理品(熟)*/
        int Conditioning_products_cooked = 6;
    }
    interface ProductMarketType {
        String TYPE = "ProductMarketType";
        /**主力产品*/
        int MAIN_PRODUCTS = 0;
        /**标准产品*/
        int STANDARD_PRODUCTS = 1;
        /**量少产品*/
        int LITTLE_PRODUCTS = 2;
    }

}
