package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/29.
 * Buyers常量定义
 */
public interface BuyersConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Buyers";
    /**
     * 买家上线状态
    */
    interface MarketingsStatus {
        String Type = "MarketingsStatus";
        /**预注册买家*/
        String PreRegister = "01";
        /**未营销成功买家*/
        String NoMarket = "02";
        /**激活期会员*/
        String ActivePeriod = "11";
        /**稳定期会员(核心会员)*/
        String StablePeriodCentral = "21";
        /**稳定期会员(标准会员)*/
        String StablePeriodStandard = "22";
        /**预警期会员*/
        String EarlyWarnPeriod = "13";
        /**销售期公众买家*/
        String SalePublicBuyers = "14";
        /**停业买家*/
        String OutBusiness = "31";
        /**信息错误买家*/
        String InfoError = "32";
    }

    /**
     * 支付方式
  */
    interface PaymentMethod {
       String Type = "PaymentMethod";
        /**网银*/
       int ONLINEBANKING = 1;
        /**会员卡*/
       int VIPCARD = 2;
        /**POS机*/
       int POS = 3;
    }

    /**
     * 冻品存放方式
  */
    interface FrozenSaveType {
        String Type = "FrozenSaveType";
        /**第三方冷库*/
        int OTHER_STORAGE = 1;
        /**自有冷库*/
        int OWN_STORAGE = 2;
        /**冰柜*/
        int FREEZER = 3;
    }

    /**
     * 存放能力
   */
    interface  StorageCap {
        String Type = "StorageCap";
        /**1吨以下*/
        int LESS_ONE = 1;
        /**1-5吨*/
        int BETWEEN_ONE_AND_FIVE = 2;
        /**5-10吨*/
        int BETWEEN_FIVE_AND_TEN = 3;
        /**10吨以上*/
        int MORE_THAN_TEN = 4;
    }

    /**
     * 买家类型
   */
    interface  BuyerType {
        String Type = "BuyerType";
        /**分销买家*/
        int DISTRIBUTION = 1;
        /**菜场买家*/
        int MARKET = 2;
        /**团膳买家*/
        int GROUPMEALS = 3;
        /**火锅买家*/
        int HOTPOT = 4;
        /**中餐买家*/
        int CHINESEFOOD = 5;
        /**西餐买家*/
        int WESTFOOD = 6;
        /**小吃烧烤买家*/
        int SNACKGRILL = 7;
        /**加工厂买家*/
        int PROCESSING = 8;
        /*买手*/
        int BUYER_SHOP = 9;
        /*第三方买手*/
        int THIRD_BUYER = 10;
    }

    /**
     * 产品销售对象
   */
    interface SalesTarget {
       String Type = "SalesTarget";

        /**菜场(含KA)*/
        int MARKET_KA = 1;
        /**团膳*/
        int GROUP_MEALS = 2;
        /**火锅*/
        int HOTPOT = 3;
        /**中餐*/
        int CHINESE_FOOD = 4;
        /**西餐*/
        int WESTERN_FOOD = 5;
        /**小吃烧烤*/
        int BARBECUE_SNACKS = 6;
        /**加工厂*/
        int PROCESS_FACTORY = 7;
    }

    /**
     * 企业证照类型
   */
    interface LicType {
        String Type = "LicType";
        /**个体工商户营业执照*/
        int INDIVI_LIC = 1;
        /**个人独资企业营业执照*/
        int INDIVI_ENTERPRISE_LIC = 2;
        /**合伙企业营业执照*/
        int PARTNER_ENTERPRISE_LIC = 3;
        /**企业法人营业执照*/
        int ENTERPRISE_LEGAL_LIC = 4;
        /**农民专业合作社营业执照*/
        int FARMERS_COOPE_LIC = 5;
    }

    /**
     * 个人证照类型
   */
    interface LegalLicType {
        String Type = "LegalLicType";
        /**身份证*/
        int ID_CARD = 1;
        /**护照*/
        int PASSPORT = 2;
        /**其他*/
        int OTHER = 3;
    }

    /**
     * 买家员工类型
   */
    interface EmployeeType{
        String TYPE ="EmployeeType";
        /**老板*/
        int BOSS = 1;
        /**经理/店长*/
        int MANAGE = 2;
        /**员工*/
        int EMPLOYEE = 3;
    }

    /**
     * 买家注册途径
   */
    interface BuyerRegisterWay{
        String TYPE = "BuyerRegisterWay";
        /**后台系统注册*/
        int SYSTEMREGIST = 0;
        /**通路注册*/
        int ACCESSREGIST = 1;
        /**云冻品平台注册*/
        int SNKREGIST = 2;
        /**云冻品B2B平台注册*/
        int MSKREGIST = 3;
    }

    /**
     * 买家收货时间
   */
    interface ReceivePeriodType{
        String TYPE = "ReceivePeriodType";
        /**00:00-03:00*/
        int ZERO_TO_THREE = 1;
        /**03:00-06:00*/
        int THREE_TO_SIX = 2;
        /**06:00-09:00*/
        int SIX_TO_NINE = 3;
        /**09:00-12:00*/
        int NINE_TO_TWELVE = 4;
        /**12:00-15:00*/
        int TWELVE_TO_FIFTEEN = 5;
        /**15:00-18:00*/
        int FIFTEEN_TO_EIGHTEEN = 6;
        /**18:00-21:00*/
        int EIGHTEEN_TO_TWENTYONE = 7;
        /**21:00-24:00*/
        int TWENTYONE_TO_TWENTYFOUR = 8;
    }

    /**
     * 菜场类型
   */
    interface MarketType{
        String TYPE = "MarketType";
        /**菜场*/
        int MARKET = 1;
        /**农贸市场*/
        int agricultural_market = 2;
    }

    /**
     * 菜场地段类型
   */
    interface MarketSectionType{
        String TYPE = "MarketSectionType";
        /**城区*/
        int CITY = 1;
        /**近郊*/
        int SUBURBS = 2;
        /**远郊*/
        int SUBURBAN = 3;
        /**乡镇*/
        int COUNTRY = 4;
    }

    /**
     * 菜场规模类型
   */
    interface MarketSizeType{
        String TYPE = "MarketSizeType";
        /**大型菜场*/
        int BIG_MARKET = 1;
        /**中型菜场*/
        int MIDDLE_MARKET = 2;
        /**小型菜场*/
        int SMALL_MARKET = 3;
    }

    /**
     * 批发市场等级
   */
    interface MarketLevel{
        String TYPE = "MarketLevel";
        /**未分级*/
        int NO_LEVEL_MARKET = 0;
        /**一级批发市场*/
        int FIRST_LEVEL_MARKET = 1;
        /**二级批发市场*/
        int SECOND_LEVEL_MARKET = 2;
        /**三级批发市场*/
        int THIRD_LEVEL_MARKET = 3;
    }

    /**
     * 调研状态
   */
    interface ResearchStatus {
        String Type = "ResearchStatus";
        /**未调研*/
        int NOT_RESEARCH = 0;
        /**已调研*/
        int Already_Research = 1;
        /**调研中*/
        int researching = 2;
        /**不调研*/
        int UNRESEARCH = 3;
    }

    /**
     * 调研类型
   */
    interface ResearchType {
        String Type = "ResearchType";

        /**产品品类需求调研*/
        int CAT = 1;
        /**原种种源标准调研*/
        int ORG = 2;
        /**加工技术标准调研*/
        int MCT = 3;
        /**加工质量标准调研*/
        int TNC = 4;
        /**产品饲养标准调研*/
        int FED = 5;
        /**通用质量标准调研*/
        int GNQ = 6;
        /**品种安全标准调研*/
        int SFT = 7;
        /**运输存储标准调研*/
        int TSP = 8;
        /**产品包装标准调研*/
        int NOR = 9;
    }

    /**
     * 买家对接接口令牌
     */
    interface ByInterfaceToken{
        String TYPE = "ByInterfaceToken";
        int ACCESSREGIST = 123456;
    }

    /**
     * 买家类型
     */
    public interface OrderBuyerType{
        String TYPE = "OrderBuyerType";
        /**分销买家*/
        int DISTRIBUTION = 1;
        /**菜场买家*/
        int MARKET = 2;
        /**团膳买家*/
        int GROUP_MEAL = 3;
        /**火锅买家*/
        int HOT_POT = 4;
        /**中餐买家*/
        int CHINESE_FOOD = 5;
        /**西餐买家*/
        int WEST_FOOD = 6;
        /**小吃烧烤买家*/
        int SNACK_GRILL = 7;
        /**加工厂买家*/
        int PROCESSING = 8;
        /**买手*/
        int BUYER_SHOP = 9;
        /**第三方买手*/
        int THIRD_BUYER = 10;
    }

    /**
     * 辐射范围
     */
    public interface RadiationRange{
        String TYPE = "RadiationRange";
        /**全物流区*/
        int ALL_LOGIS_AREA = 1;
        /**本地区(城市)*/
        int LOCAL_CITY = 2;
        /**本地区(县)*/
        int LOCAL_COUNTRY = 3;
    }

    /**
     * 面向买家类型
     */
    public interface FaceBuyerType{
        String TYPE = "FaceBuyerType";
        /**二批、三批分销商*/
        int MARKET_DISTRIBUTOR = 1;
        /**综合农贸市场买家*/
        int CHA_MARKET_BUYERS  = 2;
        /**用户*/
        int MARKET_USER = 3;
    }

    /**
     * 市场调研阶段
     */
    public interface MarketResearchPhase{
        String TYPE = "MarketResearchPhase";
        /**网搜阶段*//*
        int MARKET_NET = 1;*/
        /**先期调研阶段*/
        int MARKET_RESEARCH  = 1;
        /**现场稽核阶段*/
        int MARKET_AUDIT = 2;
    }

    /**
     * 商户类型
     */
    public interface MerchantType{
        String TYPE = "MerchantType";
        /**单一产品买家*/
        int Single_PdBuyers = 1;
        /**组合产品买家*/
        int Portfolio_PdBuyers  = 2;
        /**综合产品买家*/
        int Integrate_PdBuyers = 3;
    }

    /**
     * 批发市场性质
     */
    public interface TerminalMarketNature{
        String TYPE = "TerminalMarketNature";
        /**综合（冻品类)*/
        int INTEGRATE_FROZEN = 1;
        /**非综合（冻品类）*/
        int UINTEGRATE_FROZEN  = 2;
        /**综合（常温产品类)*/
        int INTEGRATE_NORMALTEM = 3;
        /**非综合（常温产品类）*/
        int UINTEGRATE_NORMALTEM = 4;
    }

    /**
     * 市场审批状态
     */
    public interface MarketApproveStatus{
        String TYPE = "MarketApproveStatus";
        /**未审批*/
        int MarketNotApprove = 0;
        /**已审批*/
        int MarketApproved  = 1;
    }

    /**
     * 是否目标买家
     */
    public interface TargetMarketBuyer{
        String TYPE = "TargetMarketBuyer";
        /**目标买家*/
        int TargetBuyer  = 1;
        /**非目标买家*/
        int NTargetBuyer  = 0;
    }

    /**
     * 菜场辐射范围
     */
    public interface FoodRadiationRange {
        String TYPE = "FoodRadiationRange";
        /**周边居民*/
        int SURROUND = 1;
        /**本区(县)1/4或以上区域*/
        int FOOD_COUNTY = 2;
    }

    /**
     * 市场买家定价平均水平
     */
    public interface AverageMarketPrice {
        String TYPE = "AverageMarketPrice";
        /**本区(县)平均水平*/
        int DISTRICT_AVERAGE = 1;
        /**较本区(县)平均水平低2%-3%*/
        int LOWDISTRICT_AVERAGE = 2;

    }

    /**
     * 习惯收获时间段
     */
    public interface HabitReceivePeriodType {
        String TYPE = "HabitReceivePeriodType";
        /**06:00-08:00*/
        String SIX_TO_EIGHT = "1";
        /**08:00-10:00*/
        String EIGHT_TO_TEN = "2";
        /**10:00-12:00*/
        String TEN_TO_TWELVE = "3";
        /**12:00-14:00*/
        String TWELVE_TO_FOURTEEN = "4";
        /**14:00-16:00*/
        String FOURTEEN_TO_SIXTEEN = "5";
        /**16:00-18:00*/
        String SIXTEEN_TO_EIGHTEEN = "6";
        /**18:00-20:00*/
        String EIGHTEEN_TO_TWENTY = "7";
        /**20:00-22:00*/
        String TWENTY_TO_TWENTY_TWO = "8";
        /**22:00-24:00*/
        String TWENTY_TWO_TO_TWENTY_FOUR = "9";
        /**00:00-02:00*/
        String ZERO_TO_TWO = "10";
        /**02:00-04:00*/
        String TWO_TO_FOUR = "11";
        /**04:00-06:00*/
        String FOUR_TO_SIX = "12";



    }

    /**
     * 非目标买家销售产品分类
     */
    public interface NTargetPdClasses {
        String TYPE = "NTargetPdClasses";
        /**鲜肉产品*/
        String MEAT_PRODUCT = "1";
        /**鲜鱼产品*/
        String FISH_PRODUCT = "2";
        /**活禽产品*/
        String LIVE_POULTRY_PRODUCT = "3";
        /**禽蛋产品*/
        String EGG_PRODUCT = "4";
        /**果蔬产品*/
        String FRUIT_PRODUCT = "5";
        /**豆制品*/
        String BEAN_PRODUCT = "6";
        /**其它食品*/
        String OTHER_FOOD = "7";
        /**非食品*/
        String NOT_FOOD = "8";

    }

    /**
     * 文件状态
     */
    public interface FileStatus {
        String TYPE = "FileStatus";
        /**未生成*/
        String NotGenerated = "0";
        /**已生成*/
        String Generated = "1";
        /** 生成中*/
        String Generating = "2";
        /** 生成失败*/
        String  Generate_fail = "3";
    }

    /**
     *
     */
    public interface CurrentPeriod {
        String TYPE = "CurrentPeriod";
        /**本半旬*/
        String CURRENTHALF = "1";
        /**本旬*/
        String CURRENTTEN = "2";
        /**本月*/
        String CURRENTMONTH = "3";

    }

    /**
     *上海订单配送区域
     */
    public interface ShOrderDeliveryArea {
        String TYPE = "ShOrderDeliveryArea";
        /**内环以内*/
        String INSQUARE = "1";
        /**内环与中环间*/
        String INSQUARE_TO_MIDDLESQUARE = "2";
        /**中环与外环间*/
        String MIDDLESQUARE_TO_OUTSQUARE = "3";
        /**外环以外*/
        String OUtSQUARE = "4";

    }

    public interface MarketingTool{
        String TYPE = "MarketingTool";
        /** 电话*/
        String BYPHONE = "1";
        /**微信*/
        String BYWECHAT = "2";
        /**QQ*/
        String BYQQ = "3";
    }

}
