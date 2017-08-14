package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/29.
 * Seller常量定义
 */
public interface SellerConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Seller";
    /**
     * 卖家主分类
     */
    interface SellerType{
        String TYPE = "SellerType";
        /**自产型*/
        int PRODUCT = 1;
        /**代理型*/
        int AGENT = 2;
        /**OEM型*/
        int OEM = 3;
        /**买手*/
        int BUYER = 4;
        /**生产商*/
        int SUPPLY = 0;
    }

    /**
     * 卖家分销资格
     */
    interface SellerQualification{
        String TYPE = "SellerQualification";
        /**无资格*/
        int NO_QUALIFICATION = 0;
        /**标准分销卖家*/
        int STANDARD_DISTRIBUTIOR = 1;
        /**优良分销卖家*/
        int EXCELLENT_DISTRIBUTOR = 2;
        /**重点分销卖家*/
        int IMPORTANT_DISTRIBUTOR = 3;
    }

    /**
     * 卖家分销资格
     */
    interface IsMust{
        String TYPE = "IsMust";
        /**不必须*/
        int UNMUST = 0;
        /**必须*/
        int MUST = 1;
        /**按产品类别*/
        int BY_PRODUCT_CATEGORY = 2;
    }

    /**
     * 品牌名称
     */
    interface BrandName{
        String TYPE = "BrandName";
        /**品牌证书*/
        int BRAND_CERTIFICATE = 0;
        /**品牌包装*/
        int BRAND_PACKAGING = 1;
        /**品牌荣誉*/
        int BRAND_HONOR = 2;
    }

    /**
     * 产品技术标准定级
     *
     *
     */
     interface TechnicalGradeCode {
        String TYPE = "TechnicalGradeCode";
        /** 等级:A1 */
        int A1 = 1;
        /** 等级:A2 */
        int A2 = 2;
        /** 等级:A3 */
        int A3 = 3;
        /**不通过*/
        int NONE = 4;
    }

    /**
     * 产品技术标准定级监控人审核意见
     *
     *
     */
    interface ProductTechnicalOption{
        String TYPE = "ProductTechnicalOption";
        /**待审核*/
        int PENDING_AUDIT = 0;
        /**同意*/
        int AGREE = 1;
        /**不同意*/
        int NOAGREE = 2;
    }

    /**
     * 产品质量标准定级
     *
     *
     */
    interface qualityGradeCode{
        String TYPE = "qualityGradeCode";
        /**优良*/
        int EXCELLENT = 1;
        /**合格*/
        int QUALIFIED = 2;
        /**不合格*/
        int UNQUALIFIED = 3;
    }

    /**
     * 审核结果
     *
     *
     */
    interface AuditResults{
        String TYPE = "AuditResults";
        /**同意*/
        int AGREE = 1;
        /**增加标准包装规格*/
        int INCREASE_STANDARD_PACKAGE = 2;
        /**要求执行平台标准*/
        int REQUEST_EXECUTION_PLATFORM_STANDARD = 3;
    }

    /**
     * 品牌类型
     *
     *
     */
    interface BrandType{
        String TYPE = "BrandType";
        /**自有品牌*/
        int OWN_BRAND = 1;
        /**代理品牌*/
        int AGENCY_BRAND = 2;
    }

    interface ConsentMark{
        String TYPE = "ConsentMark";
        /**未录入*/
        int WITHOUT_INPUT =0;
        /**同意*/
        int AGREE = 1;
        /**不同意*/
        int DISAGREE = 2;
    }


    /**
     * 团队职务
     */
    interface TeamPosition{
        String TYPE = "TeamPosition";
        /**公司董事长*/
        String CHAI_RMAN = "1";

        /**公司总经理*/
        String ALL_MANAGER = "2";

        /**公司副总经理*/
        String GENERAL_MANAGER = "3";

       /* *销售部经理*/
        String SALES_MANAGER = "4";

       /**品控部经理*/
        String QUALITY_MANAGER = "5";

        /**财务部经理*/
        String FINANCE_MANAGER = "6";
    }

    /**
     * 品牌类型(卖家供应链)
     */
    interface SaleStatus{
        String TYPE = "SaleStatus";
        /**申请中*/
        String APPLYING = "1";
        /**论证中*/
        String ARGUMENTING = "2";
        /**禁止准入*/
        String REFUSE = "3";
        /**试销*/
        String TRYING = "4";
        /**正式上线*/
        String LAUNCHED = "5";
        /**下线*/
        String OFFLINE = "6";
        /**黑名单*/
        String BLACK_LIST = "7";
        /**缺货中*/
        String OUT_OF_STOCK = "8";
    }

    /**
     * 卖家主分类
     */
    interface SlMainClass{
        String TYPE = "SlMainClass";
        /**自产型*/
        int PRODUCT = 1;
        /**代理型*/
        int AGENT = 2;
        /**OEM型*/
        int OEM = 3;
        /**买手*/
        int BUYER = 4;
        /**生产商*/
        int SUPPLY = 0;
    }

    /**
     * 产品技术标准定级(加工质量标准)
     *
     *
     */
    interface SlTncGradeCode {
        String TYPE = "SlTncGradeCode";
        /** 等级:A1 */
        int A1 = 1;
        /** 等级:A2 */
        int A2 = 2;
        /** 等级:A3 */
        int A3 = 3;
        /**不通过*/
        int NONE = 4;
        /**讨论中*/
        int CHATING = 5;
    }

    /**
     * 产品质量标准定级
     *
     *
     */
    interface SlQltGradeCode {
        String TYPE = "SlQltGradeCode";
        /**优良*/
        int EXCELLENT = 1;
        /**合格*/
        int QUALIFIED = 2;
        /**不合格*/
        int UNQUALIFIED = 3;
        /**论证中*/
        int COFIRMING = 4;
    }

    /**
     *同意标志
     */
    interface AgreeFlg{
        String TYPE = "AgreeFlg";
        /**未录入*/
        int WITHOUT_INPUT =0;
        /**同意*/
        int AGREE = 1;
        /**不同意*/
        int DISAGREE = 2;
    }

    /**
     *品牌分类
     */
    interface BrandClass {
        String TYPE = "BrandClass";
        /**卖家独立品牌*/
        int SELLER_INDEPENDENT_BRAND =0;
        /**神农先生联合*/
        int SNK_SIR_UNION = 1;
        /**神农客联合*/
        int SNK_UNION = 2;
        /**神农人家联合*/
        int SNK_FAMILY_UNION = 3;
    }

    /**
     *是否负责人
     */
    interface LeaderFlg {
        String TYPE = "LeaderFlg";
        /**团队负责人*/
        int TEAM_RESPONSIBLE = 1;
        /**团队成员*/
        int TEAM_MEMBER = 0;
    }

    /**
     *国内国外
     */
    interface PdCountry {
        String TYPE = "PdCountry";
        /**国内*/
        int INT_COUNTRY =0;
        /**国外*/
        int OUT_COUNTRY = 1;
    }

    /**
     *是,否
     */
    interface YESNO{
        String TYPE = "YESNO";
        /**是*/
        int YES = 1;
        /**否*/
        int NO = 0;
    }

    /**
     * 供应商分类
     */
    public interface SalePlatform{
        String TYPE = "SupplyPlatform";
        /**美侍客*/
        int MSK = 1;
        /**神农客*/
        int SNK = 2;
    }
}
