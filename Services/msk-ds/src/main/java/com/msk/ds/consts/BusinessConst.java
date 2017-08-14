package com.msk.ds.consts;

/**
 * The Const for business.
 *
 */
public interface BusinessConst {

    interface SLPath {
        // 卖家图片的主目录
        // final static String SLPRIMARYPATH = ConfigManager.getFtpHttpUrl();
        // 服务器图片存储路径
        final static String SERVICEIMAGEPATH = "msk-image";
        // 卖家图片文件路径
        final static String SLIMAGEPATH = "sl";
        final static String BASE = "base"; // 卖家图像路径
        final static String ECTEAM = "ecteam"; // 电商团队成员 //电商团队管理
        final static String EPBUS = "epbus"; // 营业执照
        final static String EPTAX = "eptax"; // 税务登记证
        final static String EPORG = "eporg"; // 组织代码证
        final static String EPBAN = "epban"; // 银行开户许可证
        final static String EPTHR = "epthr"; // 三证合一营业执照
        final static String EPFOO = "epfoo"; // 食品流通许可证
        final static String EPAGE = "epage"; // 代理及分销授权书
        final static String EPOEM = "epoem"; // OEM委托授权书
        final static String CERT = "cert"; // 证照名称
        final static String BOAMAN = "boaman"; // 公司董事长头像
        final static String GENMAN = "genman"; // 公司总经理头像
        final static String VICMAN = "vicman"; // 公司副总经理头像
        final static String SALMAN = "salman"; // 销售部经理头像
        final static String QCMAN = "qcman"; // 品控部经理头像
        final static String FINANCE = "finance"; // 财务部经理头像
        final static String BRAND = "brand"; // 品牌证书
        final static String BRANDPAC = "brandpac"; // 包装图片
        final static String BRANDHONOR = "brandhonor"; // 品牌荣誉
        final static String EPHON = "ephon"; // 企业荣誉
        final static String EPWORKSHOP = "epworkshop"; // 厂房平面图
        final static String EPWAREHOUSE = "epwarehouse"; // 库容概括
        final static String EPLABORATORY = "eplaboratory"; // 实验室
        final static String EPTESTING = "eptesting"; // 检测设备
        final static String EPQUALITY = "epquality"; // 质量控制系统图
        final static String EPQCORGANIZE = "epqcorganize"; // 品控组织架构
        final static String EPWORKSHOPDES = "epworkshopdes"; // 车间概括
        final static String PD = "pd"; // 卖家产品图片
        final static String INTRAYFIGURE = "inTrayFigure"; // 产品盘装图片
        final static String INSIDEOFFIGURE = "insideOfFigure"; // 产品内袋图片
        final static String OUTSIDEBOXFIGURE = "outsideBoxFigure"; // 产品外箱开箱图片
        final static String CARTONAPPEARANCEFIGURE = "cartonAppearanceFigure"; // 产品外箱外观图片
        final static String SLSLDISTREGUCHAP = "slsldistreguchap"; // 分销章程内容

    }

    /**
     * 买手时间周期
     */
    interface BSCycle {
        // 冻品管家卖家关系的时间周期，按天
        final static int TIMECYCLE = 60;
    }

    /**
     * 买家图片路径
     */
    interface BYPath {

        // 买家图片文件路径
        final static String BYIMAGEPATH = "by";
        // 买家图片临时文件路径
        final static String TEMPIMAGEPATH = "temp";
    }

    /**
     * 接口返回结果状态
     *
     * @author mabo
     */
    public final static class RsStatus {
        /**
         * 处理成功
         */
        public final static String SUCCESS = "S";
        /**
         * 处理失败
         */
        public final static String FAIL = "F";
    }

    /**
     * 买家上线状态
     */
    public final static class MarketingSatus {
        public final static String Type = "MarketingSatus";

        /** 未营销 */
        public final static String NotMarketing = "1";
        /** 未打通电话 */
        public final static String NotCall = "2";
        /** 未注册 */
        public final static String New = "3";
        /** 未注册停业 */
        public final static String InfoError = "4";
        /** 未注册拒绝沟通 */
        public final static String Stop = "5";
        /** 未注册信息错误 */
        public final static String Refuse = "6";
        /** 预注册 */
        public final static String Standard = "7";
        /** 预注册标准营销难度买家 */
        public final static String Hard = "8";
        /** 预注册高营销难度买家 */
        public final static String Activation = "9";
        /** 激活期 */
        public final static String Maintenance = "10";
        /** 稳定期 */
        public final static String Stable = "11";
        /** 休眠期 */
        public final static String Dormant = "12";
        /** 预警期 */
        public final static String WARNING = "13";
    }

    /**
     * 买家注册途径
     */
    public final static class BuyerRegisterWay{
        /** 后台系统注册*/
        public final static String System = "0";
        /** 冻品APP注册*/
        public final static String Access = "1";
        /** 神农客平台注册*/
        public final static String SNK = "2";
        /** 美侍客平台注册*/
        public final static String MSK = "3";
    }
    /**
     * 支付方式
     */
    public final static class PaymentMethod {
        public final static String Type = "PaymentMethod";

        /** 网银 */
        public final static String OnlineBanking = "1";
        /** 会员卡 */
        public final static String VIPCard = "2";
        /** POS机 */
        public final static String POS = "3";
        /** 现金 */
        public final static String Cash = "4";
    }

    /**
     * 冻品存放方式
     */
    public final static class FrozenSaveType {
        public final static String Type = "FrozenSaveType";

        /** 第三方冷库 */
        public final static String OtherStorage = "1";
        /** 自有冷库 */
        public final static String OwnStorage = "2";
        /** 冰柜 */
        public final static String Freezer = "3";
    }

    /**
     * 存放能力
     */
    public final static class StorageCap {
        public final static String Type = "StorageCap";

        /** 1吨以下 */
        public final static String LessOne = "1";
        /** 1-5吨 */
        public final static String BetweenOneAndFive = "2";
        /** 5-10吨 */
        public final static String BetweenFiveAndTen = "3";
        /** 10吨以上 */
        public final static String MoreThanTen = "4";
    }

    /**
     * 买家类型
     */
    public final static class BuyerType {
        public final static String Type = "BuyerType";

        /** 分销买家 */
        public final static String Distribution = "1";
        /** 菜场买家 */
        public final static String Market = "2";
        /** 团膳买家 */
        public final static String GroupMeals = "3";
        /** 火锅买家 */
        public final static String HotPot = "4";
        /** 中餐买家 */
        public final static String ChineseFood = "5";
        /** 西餐买家 */
        public final static String WestFood = "6";
        /** 小吃烧烤买家 */
        public final static String SnackGrill = "7";
        /** 加工厂买家 */
        public final static String Processing = "8";
    }

    /**
     * 产品销售对象
     */
    public final static class SalesTarget {
        public final static String Type = "SalesTarget";

        /** 菜场(含KA) */
        public final static String MarketKA = "1";
        /** 团膳 */
        public final static String GroupMeals = "2";
        /** 火锅 */
        public final static String HotPot = "3";
        /** 中餐 */
        public final static String ChineseFood = "4";
        /** 西餐 */
        public final static String WesternFood = "5";
        /** 小吃烧烤 */
        public final static String BarbecueSnacks = "6";
        /** 加工厂 */
        public final static String ProcessFactory = "7";
    }

    /**
     * 企业证照类型
     */
    public final static class LicType {
        public final static String Type = "LicType";

        /** 个体工商户营业执照 */
        public final static String IndiviLic = "1";
        /** 个人独资企业营业执照 */
        public final static String IndiviEnterpriseLic = "2";
        /** 合伙企业营业执照 */
        public final static String PartnerEnterpriseLic = "3";
        /** 企业法人营业执照 */
        public final static String EnterpriseLegalLic = "4";
        /** 农民专业合作社营业执照 */
        public final static String FarmersCoopeLic = "5";
    }

    /**
     * 个人证照类型
     */
    public final static class LegalLicType {
        public final static String Type = "LegalLicType";

        /** 身份证 */
        public final static String IdCard = "1";
        /** 护照 */
        public final static String Passport = "2";
        /** 其他 */
        public final static String Other = "3";
    }

    /**
     * 雇员类型
     */
    public final static class EmployeeType {
        public final static String Type = "EmployeeType";

        /** 老板 */
        public final static String Boss = "1";
        /** 经理/店长 */
        public final static String Manager = "2";
        /** 员工 */
        public final static String Employee = "3";
    }

    /**
     * 调研状态
     */
    public final static class ResearchStatus {
        public final static String Type = "ResearchStatus";

        /** 未调研 */
        public final static String NotResearch = "0";
        /** 已调研 */
        public final static String AlreadyResearch = "1";
        /** 调研中 */
        public final static String Researching = "2";
        /** 不调研 */
        public final static String UnResearch = "3";
    }

    /**
     * 调研类型
     */
    public final static class ResearchType {
        public final static String Type = "ResearchType";

        /** 产品品类需求调研 */
        public final static String CAT = "1";
        /** 原种种源标准调研 */
        public final static String ORG = "2";
        /** 加工技术标准调研 */
        public final static String MCT = "3";
        /** 加工质量标准调研 */
        public final static String TNC = "4";
        /** 产品饲养标准调研 */
        public final static String FED = "5";
        /** 通用质量标准调研 */
        public final static String GNQ = "6";
        /** 品种安全标准调研 */
        public final static String SFT = "7";
        /** 运输存储标准调研 */
        public final static String TSP = "8";
        /** 产品包装标准调研 */
        public final static String NOR = "9";
    }

    /**
     * 分销物流状态
     */
    public final static class DeliveryStockStatus {
        public final static String Type = "DeliveryStockStatus";

        /** 未申请 */
        public final static String NoApply = "1";
        /** 申请中 */
        public final static String IsApply = "2";
        /** 已确认 */
        public final static String IsSure = "3";
        /** 待收货 */
        public final static String NoDelivery = "4";
        /** 已收货 */
        public final static String Delivery = "5";
        /**
         * 部分收货
         */
        public final static String PartialDelivery = "6";
    }

    /**
     * 分销物流状态名称
     */
    public final static class StockStatusName {
        public final static String Type = "StockStatusName";

        /** 未申请 */
        public final static String NoApply = "未申请";
        /** 申请中 */
        public final static String IsApply = "申请中";
        /** 已确认 */
        public final static String IsSure = "已确认";
        /** 待收货 */
        public final static String NoDelivery = "待收货";
        /** 已收货 */
        public final static String Delivery = "已收货";
        /** 部分收货 */
        public final static String PartialDelivery = "部分收货";
    }

    /**
     * 交易记录角色
     */
    public final static class TranType {
        public final static String Type = "TranType";

        /** 平台 */
        public final static int Platform = 1;
        /** 买家 */
        public final static int Buyer = 2;
        /** 卖家 */
        public final static int Seller = 3;
        /** 买手 */
        public final static int BuyMan = 4;
    }

}
