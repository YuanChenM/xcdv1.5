package com.msk.common.consts;

/**
 * 资金池常量定义
 * *@author jiang_nan
 * *@version 1.0
 **/
public interface CapitalPoolConst {

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
     * 费用调整类型: 0：退货退款 1：拒收退款 2：全部取消 3：减免 4：部分取消
     */
    public interface RefundType{
        String TYPE = "RefundType";
        // 退货退款
        int GOODSRETURN = 0;
        // 拒收退款
        int REJECT = 1;
        // 全部取消
        int CANCELORDER = 2;
        // 减免
        int RELIEF = 3;
        // 部分取消
        int PARTCANCEL = 4;
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
     * 结算标志： 1：借 2：贷 3：平
     */
    public interface SettlementStatus{
        String TYPE = "SettlementStatus";
        // 借
        int FOR_PAY = 1;
        // 贷
        int FOR_REFUND = 2;
        // 平
        int BALANCED = 3;
    }

    /**
     * 金额类型： 0：付款 1：退款 2:混合 3期初
     */
    public interface AmountType{
        String TYPE = "AmountType";
        // 付款
        int PAID = 0;
        // 退款
        int REFUND = 1;
        // 混合
        int BOTH = 2;
        // 期初
        int INITIAL = 3;
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
     * 其他常量
     */
    public interface OtherConst {
        // 神农客平台用编码
        String SNK_CODE = "SNK";
        // 神农客平台用ID
        String SNK_ID = "0000000";
        // 美侍客平台用编码
        String MSK_CODE = "MSK";
        // 美侍客平台用ID
        String MSK_ID = "AAAAAAA";
    }

    /**
     * 查询类型输入项
     */
    public interface queryTypeFlag{
        // 查询卖家结算列表分页
        int QUERY_PAGE = 1;
        // 查询配送单列表，退货单列表
        int QUERY_DETAIL = 2;
    }

    /**
     * 收款方，付款方角色信息
     */
    public interface RoleType{
        String TYPE = "RoleType";
        //平台
        String ROLE_PLATFORM = "1";
        //买家
        String ROLE_BIDDER = "2";
        //卖家
        String ROLE_SELLER = "3";
        //买手
        String ROLE_BUYERE = "4";
    }

}
