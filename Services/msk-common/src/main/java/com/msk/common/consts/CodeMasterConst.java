package com.msk.common.consts;

/**
 * Code Master Def
 *
 * @author jiang_nan
 */
public interface CodeMasterConst {
    /**
     * 组织级别
     *
     * @author jiang_nan
     */
    public interface OrgLevel {
        /** Code Master Model Name定义:OrgLevel */
        public String MODEL_NAME = "OrgLevel";
        /** ONE定义:1(级别1) */
        public String ONE = "1";
        /** TOW定义:2(级别2) */
        public String TOW = "2";
        /** THREE定义:3(级别3) */
        public String THREE = "3";
    }

    /**
     * 系统级别
     *
     * @author jiang_nan
     */
    public interface SysLevel {
        /** Code Master Model Name定义:systemLevel */
        public String MODEL_NAME = "systemLevel";
        /** SYSTEM:1(系统模块) */
        public String SYSTEM = "1";
        /** SUBSYSTEM:2(子系统) */
        public String SUBSYSTEM = "2";
        /** MODUL:3模块 */
        public String MODUL = "3";

    }

    /**
     * 登录表状态Code Master定义
     *
     * @author jian_gnan
     */
    public interface LoginStatus {
        /** Code Master Model Name定义:LoginStatus */
        public String MODUL_NAME = "LoginStatus";
        /** AVAILABLE:有效 */
        public String AVAILABLE = "0";
        /** FREEZE:冻结 */
        public String FREEZE = "1";
    }

    /**
     * 是否可用
     *
     * @author jian_gnan
     */
    public interface IsAvailable {
        /** Code Master Model Name定义:IsAvailable */
        public String MODUL_NAME = "IsAvailable";
        /** AVAILABLE:可用 */
        public String AVAILABLE = "0";
        /** NOAVAILABLE:不可用 */
        public String NOAVAILABLE = "1";
    }

    /**
     * 产品等级
     *
     * @author yuan_chen
     */
    public interface GradeCode {
        /** Code Master Model Name定义:GradeCode */
        public String NONE = "0";
        /** 等级:A1 */
        public String A1 = "1";
        /** 等级:A2 */
        public String A2 = "2";
        /** 等级:A3 */
        public String A3 = "3";
    }

    /**
     * 行政区域划分级别
     *
     * @author yuan_chen
     */
    public interface DivisionLevel {
        public String Type = "DivisionLevel";

        /** 大区 */
        public String Area = "1";
        /** 省或物流区 */
        public String PrLgcs = "2";
        /** 城市(区域) */
        public String City = "3";
        /** 区(县) */
        public String District = "4";
    }
    /**
     * 冻品管家与买家的关系：申请状态
     * 1：申请中 2：专属会员
     * @author chen_xin
     */
    public interface SlApplyStatus {
        public String Type = "SlApplyStatus";
        /** 申请中 */
        public String Apply = "1";
        /** 专属会员 */
        public String Exclusive = "2";
    }

    public interface OrderDetailType{
        public String TYPE = "OrderDetailType";
        public int NORMAL = 1;
    }

    interface OrderDetailLevel{
        String TYPE = "OrderDetailLevel";
        int NORMAL = 1;
    }

    /**
     * 产品状态
     * @author gyh
     */
    public interface slProductStatus{
        /**申请中*/
        String SQZ="1";
        /**论证中*/
        String LZZ="2";
        /**禁止准入*/
        String JZZR="3";
        /**试销*/
        String SX="4";
        /**正式上线*/
        String ZSSX="5";
        /**下线*/
        String XX="6";
        /**黑名单*/
        String HMD="7";
        /**断货*/
        String DH="8";
    }


    /**
     * 是否为目录产品
     *
     * @author yuan_chen
     */
    public interface IsStandard {
        //不是目录产品(新产品)
        static final String NO = "0";
        //是目录产品(已注册产品)
        static final String YES = "1";
    }
    /**
     * YES NO
     */
    public interface YESNO{
        String TYPE = "YESNO";
        /**NO:0*/
        int NO = 0;
        /**YES:1*/
        int YES = 1;
    }

    /**
     * 登录用户类型
     */
    public interface LoginUserType{
        String TYPE = "LoginUserType";
        /**系统用户*/
        String SYSTEM_USER_TYPE = "1";
        /**供应商用户*/
        String SUPPLIER_USER_TYPE = "2";
    }

    /**
     * 系统Code定义
     */
    public interface SystemCode {
        String SYSTEM_CODE_BY = "302";
        String SYSTEM_CODE_PD = "304";
        String SYSTEM_CODE_SL = "303";
        String SYSTEM_CODE_SO = "305";
        String SYSTEM_CODE_MS = "301";
        String SYSTEM_CODE_DS = "307";
        String SYSTEM_CODE_MD = "399";
        String SYSTEM_CODE_BS = "210";
        String SYSTEM_CODE_SNK = "101";
        String SYSTEM_CODE_MSK = "102";
    }
    /**
     * 查询类型： 0：订单 1：管理费
     */
    public interface SearchType{
        String TYPE = "SearchType";
        // 订单
        String ORDER = "0";
        // 管理费
        String MANAGEMENT_COSTS = "1";
    }

    /**
     * 金额类型： 0：付款 1：退款 2:混合
     */
    public interface AmountType{
        String TYPE = "AmountType";
        // 付款
        int PAID = 0;
        // 退款
        int REFUND = 1;
        // 混合
        int BOTH = 2;
    }

    /**
     * 收款方，付款方角色信息
     */
    public interface RoleType{
        String TYPE = "RoleType";
        //平台
        int ROLE_PLATFORM = 1;
        //买家
        int ROLE_BIDDER = 2;
        //卖家
        int ROLE_SELLER = 3;
        //买手
        int ROLE_BUYERE = 4;
    }

    /**
     * 平台类型： 0：神农客 1：美侍客
     */
    public interface SupplyPlatform{
        String TYPE = "SupplyPlatform";
        // 神农客
        int SNK = 1;
        // 美侍客
        int MSK = 2;
        // 大促会
        int DCH = 3;
    }

    /**
     * 结算标志： 1：付 2：退 3：平
     */
    public interface SettlementStatus{
        String TYPE = "SettlementStatus";
        // 付
        int FOR_PAY = 1;
        // 退
        int FOR_REFUND = 2;
        // 平
        int BALANCED = 3;
    }

    /**
     * 参考明细来源： 1：买家 2：卖家
     */
    public interface BackType{
        String TYPE = "BackType";
        // 买家
        int BUYER = 1;
        // 卖家
        int SELLER = 2;
    }

    /**
     * 退款标志： 0：无退款 1：有退款
     */
    public interface RefundFlg{
        String TYPE = "RefundFlg";
        // 无退款
        String NOREFUND = "0";
        // 有退款
        String WITHREFUND = "1";
    }

    /**
     * 是否新增标识： 0：否 1：是
     */
    public interface InsertFlg{
        String TYPE = "InsertFlg";
        // 否
        String NO = "0";
        // 是
        String YES = "1";
    }

    /**
     * 交易标志： :0：正常 1：交易关闭
     */
    public interface TransFlg{
        String TYPE = "TransFlg";
        // 正常
        String NORMAL = "0";
        // 交易关闭
        String CLOSED = "1";
    }

    /**
     * 支付类型 1:在线支付,2:线下支付
     */
    public interface PaymentType{
        String TYPE = "PaymentType";
        // 在线支付
        int ONLINE = 1;
        // 线下支付
        int OFFLINE = 2;
    }

    /**
     * 退回费用类型: 0：退货退款 1：拒收退款 2：关闭订单
     */
    public interface RefundType{
        String TYPE = "RefundType";
        // 退货退款
        int GOODSRETURN = 0;
        // 拒收退款
        int REJECT = 1;
        // 关闭订单
        int CANCELORDER = 2;
    }

    /**
     * 支付方式: 1：现金 2：转账 3：支票 4：冲抵核销
     */
    public interface PaidType{
        String TYPE = "PaidType";
        // 现金
        int CASH = 1;
        // 转账
        int TRANSFER = 2;
        // 支票
        int CHEQUE = 3;
        // 冲抵核销
        int VER = 4;
    }

    /**
     * 计费类型: 1：卖家交易计费 2：平台管理费
     */
    public interface BillType{
        String TYPE = "BillType";
        // 卖家交易计费
        int TRANSBILL = 1;
        // 平台管理费
        int MANAGEBILL = 2;
    }

    /**
     * 支付状态: 0：未支付 1：部分支付 2：已支付
     */
    public interface PaidStatus {
        String TYPE = "PaidStatus";
        // 未支付
        int UNPAID = 0;
        // 部分支付
        int PARTPAID = 1;
        // 已支付
        int ALLPAID = 2;
    }

    /**
     * 交易类型: 0:主订单 1:管理费用清单
     */
    public interface TransType {
        String TYPE = "TransType";
        // 主订单
        int MAINORDER = 0;
        // 管理费用清单
        int MANAGERFEE = 1;
    }

    /**
     * 计费标志: 0：未计费 1：已计费
     */
    public interface ChargeFlg{
        String TYPE = "ChargeFlg";
        // 未计费
        String UNCHARGED = "0";
        // 已计费
        String CHARGED = "1";
    }

    /**
     * 冲抵核销标志: 0：正常收支 1：冲抵核销
     */
    public interface MatchVerFlg{
        String TYPE = "MatchVerFlg";
        // 正常收支
        String COMMON = "0";
        // 冲抵核销
        String MATCHVER = "1";
    }

    /**
     * 对账标志: 0：可对账 1：已对账 2：挂起
     */
    public interface StatementFlg{
        String TYPE = "StatementFlg";
        // 可对账
        String FORCHECKING = "0";
        // 已对账
        String CHECKED = "1";
        // 挂起
        String HANGUP = "2";
    }

    /**
     * 重新发货标志: 0：不重新发货 1：重新发货
     */
    public interface ReShipFlg{
        String TYPE = "ReShipFlg";
        // 不重新发货
        String RESHIP = "0";
        // 重新发货
        String UNRESHIP = "1";
    }

    /**
     * 买家类型
     */
    public interface OrderBuyerType{
        String TYPE = "OrderBuyerType";
        // 分销买家
        String DISTRIBUTION = "1";
        // 菜场买家
        String MARKET = "2";

        // 团膳买家
        String GROUP_MEAL = "3";
        // 火锅买家
        String HOT_POT = "4";

        // 中餐买家
        String CHINESE_FOOD = "5";
        // 西餐买家
        String WEST_FOOD = "6";

        // 小吃烧烤买家
        String SNACK_GRILL = "7";
        // 加工厂买家
        String PROCESSING = "8";

        // 买手
        String BUYER_SHOP = "9";
        // 第三方买手
        String THIRD_BUYER = "10";

    }
    /**
     * 单位
     */
    public interface Untis{
        String TYPE = "Untis";
        // 箱
        String BOX = "1";
        // 吨
        String TON = "2";
    }

    /**
     * 团队职务
     */
    public interface TeamPosition{

        String TYPE = "TeamPosition";
        // 公司董事长
        String CHAI_RMAN = "1";

        // 公司总经理
        String ALL_MANAGER = "2";

        // 公司副总经理
        String GENERAL_MANAGER = "3";

        // 销售部经理
        String SALES_MANAGER = "4";

        // 品控部经理
        String QUALITY_MANAGER = "5";

        // 财务部经理
        String FINANCE_MANAGER = "6";
    }

    /**
     * 品牌类型(卖家供应链)
     */
    public interface BrandType{

        String TYPE = "BrandType";
        // 美侍客
        String BRANDTYPE_MSK = "1";

        // 神农客
        String BRANDTYPE_SNK = "2";

        // 绿美通
        String BRANDTYPE_LMT = "3";
    }

    /**
     * 品牌类型(卖家供应链)
     */
    public interface SaleStatus{

        String TYPE = "SaleStatus";
        //申请中
        String APPLYING = "1";
        //        论证中
        String ARGUMENTING = "2";
        //禁止准入
        String REFUSE = "3";
        //        试销
        String TRYING = "4";
        //正式上线
        String LAUNCHED = "5";
        //        下线
        String OFFLINE = "6";
        //黑名单
        String BLACK_LIST = "7";
        //        缺货中
        String OUT_OF_STOCK = "8";
    }


    /**
     * 核销单审核状态
     */
    public interface VerificationAuditStatus{

        String TYPE = "VerificationAuditStatus";
        //未确认
        String PENDING_CONFIRM = "0";
        //甲方已确认
        String PUR_CONFIRM = "1";
        //乙方已确认
        String SUPP_COMFIRM = "2";
        //双方已确认
        String CONFIRMED = "3";
    }
}
