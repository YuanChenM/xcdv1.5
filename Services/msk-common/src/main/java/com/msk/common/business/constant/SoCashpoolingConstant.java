package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/7/21.
 * SoCashpooling常量定义
 */
public interface SoCashpoolingConstant {
    /**
     * 金额类型
     */
    interface AmountType{
        String TYPE = "AmountType";
        /**付款*/
        int PAYMENT = 0;
        /**退款*/
        int REFUND = 1;
        // 混合
        int BOTH = 2;
        // 期始
        int PERIOD = 3;
    }

    /**
     * 退回费用类型
     */
    interface RefundType{
        String TYPE = "RefundType";
        /**退货退款*/
        int RETURN_REFUND = 0;
        /**拒收退款*/
        int REFUSE_REFUND = 1;
        /**全部取消*/
        int ALL_CANCEL = 2;
        /**减免*/
        int DERATE = 3;
        /**部分取消*/
        int PART_CANCEL = 4;
    }

    /**
     * 支付方式: 1：现金 2：转账 3：支票 4：冲抵核销 5:POS刷卡
     */
    public interface PaidType{
        String TYPE = "PaidType";
        /**现金*/
        int CASH = 1;
        /**转账*/
        int TRANSFER = 2;
        /**支票*/
        int CHEQUE = 3;
        /**冲抵核销*/
        int VER = 4;
        /**POS刷卡*/
        int POSBUSHCARD = 5;
        /**微信支付*/
        int WECHAT = 6;
        /**支付宝支付*/
        int ALIPAY = 7;
    }

    /**
     * 结算标志： 1：付 2：退 3：平
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
}
