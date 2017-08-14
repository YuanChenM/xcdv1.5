package com.msk.common.consts;

/**
 * Created by zhu_kai1 on 2016/5/25.
 */
public interface BuyersConst {

    /**
     * 买家类型
     */
    interface BuyerType {
        /** master表里字段名*/
        String TYPE = "BuyerType";

        //默认买家编码最后两位校验码
        String CheckOutCode = "00";
        //是否菜场买家 1：是菜场买家，0：非菜场买家
        String isMarket = "1";
        //识别码
        String IdentCode = "99";

        /** 分销买家 */
        String Distribution = "01";
        /** 菜场买家 */
        String Market = "02";
        /** 团膳买家 */
        String GroupMeals = "03";
        /** 火锅买家 */
        String HotPot = "04";
        /** 中餐买家 */
        String ChineseFood = "06";
        /** 加工厂买家 */
        String Processing = "05";
        /** 加工厂卤肉熟食买家子编码 */
        String BraiseFood = "01";
    }

    /**
     * 买家图片路径
     */
    interface BYPath {

        // 买家图片文件路径
        String BYIMAGEPATH = "by";
        // 买家图片临时文件路径
        String TEMPIMAGEPATH = "temp";
    }

    /**
     * 买家注册途径
     */
    interface BuyerRegisterWay{
        /** master表里字段名*/
        String TYPE = "BuyerRegisterWay";
        /** 后台系统注册*/
        String SYSTEMREGIST = "0";
        /** 手机通路注册*/
        String ACCESSREGIST = "1";
        /** 云冻品平台注册*/
        String SNKREGIST = "2";
        /** 云冻品B2B平台注册*/
        String MSKREGIST = "3";
        /**买家APP*/
        String BYAPP = "4";
    }

    /**
     * 买家收货时间
     */
    interface ReceivePeriodType{
        /** master表里字段名*/
        String TYPE = "ReceivePeriodType";
    }

    /**
     * 买家销售对象
     */
    interface SalesTarget{
        /** master表里字段名*/
        String TYPE = "SalesTarget";
    }

    /**
     * 买家员工类型
     */
    interface EmployeeType{
        /** master表里字段名*/
        String TYPE ="EmployeeType";
    }

    /**
     * 买家对接接口令牌
     */
    interface ByInterfaceToken{
        String TYPE = "ByInterfaceToken";
    }
    /**
     * 批发市场等级
     */
    interface MarketLevel{
        /** master表里字段名*/
        String TYPE = "MarketLevel";
    }

    /**
     * 菜场类型
     */
    interface MarketType{
        /** master表里字段名*/
        String TYPE = "MarketType";
    }

    /**
     * 菜场地段类型
     */
    interface MarketSectionType{
        String TYPE = "MarketSectionType";
    }

    /**
     * 菜场规模类型
     */
    interface MarketSizeType{
        String TYPE = "MarketSizeType";
    }

    /**
     * 买家注册不选择物流区时,设置默认值
     */
    interface DefaultString{
        /** 城市默认*/
        String CITYCODEDEFAULT = "999";
        /** 省,物流区,区县默认Code*/
        String CODEDEFAULT = "99";
        /** 物流区默认Name*/
        String NAMEDEFAULT = "其他";
    }

    /**
     * 菜场市场调研阶段
     */
    public interface FoodMarketResearchPhase{
        /**网搜阶段*/
        String MARKET_NET = "1";
        /**先期调研阶段*/
        String MARKET_RESEARCH  = "2";
        /**现场稽核阶段*/
        String MARKET_AUDIT = "3";
    }

    public interface FoodMarketResearchPhaseName{
        String MARKET_NET_NAME = "网搜阶段";
        String MARKET_RESEARCH_NAME = "先期调研阶段";
        String MARKET_AUDIT_NAME = "现场稽核阶段";
    }
}
