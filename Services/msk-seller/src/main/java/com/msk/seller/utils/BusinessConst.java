package com.msk.seller.utils;


import com.hoperun.core.utils.StringUtil;
import com.msk.common.config.ConfigManager;

/**
 * The Const for business.
 *
 */
public interface BusinessConst {

    interface SLPath {
        // 卖家图片的主目录
        // final static String SLPRIMARYPATH = ConfigManager.getFtpHttpUrl();
        // 服务器图片存储路径
       // String SERVICEIMAGEPATH = StringUtil.removeLastFromPath(ConfigManager.getFtpImageRootPath());
        String SERVICEIMAGEPATH = ConfigManager.getFtpImageRootPath();
        // 卖家图片文件路径
        String SLIMAGEPATH = "sl";
        String BASE = "base"; // 卖家图像路径
        String ECTEAM = "ecteam"; // 电商团队成员 //电商团队管理
        String EPBUS = "epbus"; // 营业执照
        String EPTAX = "eptax"; // 税务登记证
        String EPORG = "eporg"; // 组织代码证
        String EPBAN = "epban"; // 银行开户许可证
        String EPTHR = "epthr"; // 三证合一营业执照
        String EPFOO = "epfoo"; // 食品流通许可证
        String EPAGE = "epage"; // 代理及分销授权书
        String EPOEM = "epoem"; // OEM委托授权书
        String CERT = "cert"; // 证照名称
        String BOAMAN = "boaman"; // 公司董事长头像
        String GENMAN = "genman"; // 公司总经理头像
        String VICMAN = "vicman"; // 公司副总经理头像
        String SALMAN = "salman"; // 销售部经理头像
        String QCMAN = "qcman"; // 品控部经理头像
        String FINANCE = "finance"; // 财务部经理头像
        String PDMAN = "pdMan"; // 生产部经理头像
        String SALEMAN = "saleMan"; // 营销负责人头像
        String OPERMAN = "operMan"; // 业务负责人头像
        String BRAND = "brand"; // 品牌证书
        String BRANDPAC = "brandpac"; // 包装图片
        String BRANDHONOR = "brandhonor"; // 品牌荣誉
        String EPHON = "ephon"; // 企业荣誉
        String EPWORKSHOP = "epworkshop"; // 厂房平面图
        String EPWAREHOUSE = "epwarehouse"; // 库容概括
        String EPLABORATORY = "eplaboratory"; // 实验室
        String EPTESTING = "eptesting"; // 检测设备
        String EPQUALITY = "epquality"; // 质量控制系统图
        String EPQCORGANIZE = "epqcorganize"; // 品控组织架构
        String EPWORKSHOPDES = "epworkshopdes"; // 车间概括
        String PD = "pd"; // 卖家产品图片
        String INTRAYFIGURE = "inTrayFigure"; // 产品盘装图片
        String INSIDEOFFIGURE = "insideOfFigure"; // 产品内袋图片
        String OUTSIDEBOXFIGURE = "outsideBoxFigure"; // 产品外箱开箱图片
        String CARTONAPPEARANCEFIGURE = "cartonAppearanceFigure"; // 产品外箱外观图片
        String SLSLDISTREGUCHAP = "slsldistreguchap"; // 分销章程内容

    }

    /**
     * 买手时间周期
     */
    interface BSCycle {
        // 冻品管家卖家关系的时间周期，按天
        int TIMECYCLE = 60;
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
     * 接口返回结果状态
     *
     * @author mabo
     */
    final class RsStatus {
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
    final class MarketingSatus {
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
    final class BuyerRegisterWay {
        /** 后台系统注册 */
        public final static String System = "0";
        /** 冻品APP注册 */
        public final static String Access = "1";
        /** 神农客平台注册 */
        public final static String SNK = "2";
        /** 美侍客平台注册 */
        public final static String MSK = "3";
    }

    /**
     * 支付方式
     */
    final class PaymentMethod {
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
    final class FrozenSaveType {
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
    final class StorageCap {
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
    final class BuyerType {
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
    final class SalesTarget {
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
    final class LicType {
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
    final class LegalLicType {
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
    final class EmployeeType {
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
    final class ResearchStatus {
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
    final class ResearchType {
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
    final class DeliveryStockStatus {
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
    final class StockStatusName {
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
    }

    /**
     * 交易记录角色
     */
    final class TranType {
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

    /**
     * 登录用户区分
     */
    final class LoginUserType {
        public final static String Type = "LoginUserType";
        /** 员工 */
        public final static String EMPL = "1";
        /** 卖家 */
        public final static String SELLER = "2";
        /** 买家 */
        public final static String BUYER = "3";
        /** 员工 */
        public final static String EMPL_NAME = "鲜驰达员工";
        /** 卖家 */
        public final static String SELLER_NAME = "卖家/供应商";
        /** 买家 */
        public final static String BUYER_NAME = "买家";
    }

    final class Auth {
        public final static String Type = "Auth";
        /** 全部 */
        public final static String ALL = "ALL";
    }

}
