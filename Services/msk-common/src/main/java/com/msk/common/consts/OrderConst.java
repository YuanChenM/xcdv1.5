package com.msk.common.consts;

/**
 * 订单模块常量定义
 *
 * @author jiang_nan
 * @version 1.0
 **/
public interface OrderConst {
    /**
     * 付款方式
     */
    interface PaymentType {
        String TYPE = "PaymentType";
        /** 线上支付 */
        int PAYING_ONLINE = 1;
        /** 货到付款 */
        int CASH_ON_DELIVERY = 2;
    }
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
     * 订单来源
     */
    interface OrderSource{
        /** 云冻品平台网站 */
        int SNK = 1;
        /** 云冻品B2B平台网站 */
        int MSK = 2;
        /** 微商城网站 */
        int WSC = 3;
        /** 买手APP */
        int BYAPP = 4;
    }

    /**
     * 销售平台
     */
    interface SalePlatform{
        /** 云冻品 */
        String YDP = "1";
        /** 云冻品B2B */
        String YDPB2B = "2";
    }

    interface OrderBuyerType{
        /** master表里字段名*/
        String TYPE = "BuyerType";
        /** 分销买家 */
        String Distribution = "1";
        /** 菜场买家 */
        String Market = "2";
        /** 团膳买家 */
        String GroupMeals = "3";
        /** 火锅买家 */
        String HotPot = "4";
        /** 中餐买家 */
        String ChineseFood = "5";
        /** 西餐买家 */
        String WestFood = "6";
        /** 小吃烧烤买家 */
        String SnackGrill = "7";
        /** 加工厂买家 */
        String Processing = "8";
        // 买手
        String BuyerShop = "9";
        // 第三方买手
        String ThirdBuyer ="10";
    }

}
