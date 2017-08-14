package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/7/12.
 * Ssc常量定义
 */
public interface SscConstant {
    /**
     * Config 服务中系统模块名称
     */
    String SYSTEM_MODULE_NAME = "Ssc";

    /**
     * 中标确认书状态
     */
    interface BidStatus {
        String TYPE = "BidStatus";
        /**待确认*/
        int PENDING_CONFIRM = 0;
        /**采购商已确认*/
        int PUR_CONFIRMED = 1;
        /**生产商已确认*/
        int SUPP_CONFIRMED = 2;
        /**已确认*/
        int BID_CONFIRMED = 3;
        /**执行中*/
        int EXECUTING = 4;
        /**已结束*/
        int FINISH = 5;
        /**已删除*/
        int CANCEL = 9;
    }

    /**
     * 订单合同
     */
    interface SscOrderStatus {
        String TYPE = "SscOrderStatus";
        /**新建*/
        int CREATE = 0;
        /**待审核*/
        int PENDING_AUDIT = 1;
        /**采购商已审核*/
        int PUR_AUDIT = 2;
        /**生产商已审核*/
        int SUPP_AUDIT = 3;
        /**已生效*/
        int EFFECTIVE = 4;
        /**部分完成*/
        int PARTIAL_COMP = 5;
        /**全部完成*/
        int COMPLETE = 6;
        /**待核销*/
        int PENDING_VERIF = 7;
        /**已结案*/
        int FINISHED = 8;
        /**废弃*/
        int ABANDON = 9;
    }

    /**
     * 包材提供方式
     */
    interface PacSupplyMode {
        String TYPE = "PacSupplyMode";
        /**甲供*/
        int A_SUPP = 0;
        /**乙供*/
        int B_SUPP = 1;
    }

    /**
     * 包材审核方式
     */
    interface PacAuditMode {
        String TYPE = "PacAuditMode";
        /**寄送样品审核*/
        int SAMPLE_AUD = 0;
        /**到货审核*/
        int ARRIVAL_AUD = 1;
    }

    /**
     * 包材结算方式
     */
    interface PacPaymentMode {
        String TYPE = "PacPaymentMode";
        /**产品价内*/
        int INCLUDE = 0;
        /**产品价外*/
        int NOTINCLUDE = 1;
    }

    /**
     * 发货订单状态
     */
    interface DeliveryOrderStatus {
        String TYPE = "DeliveryOrderStatus";
        /**新建*/
        int NEW = 0;
        /**待确认*/
        int PENDING_CONFIRM = 1;
        /**甲方已审批*/
        int PUR_AUDIT = 2;
        /**乙方已确认*/
        int SUP_CONFIRM = 3;
        /**双方已确认*/
        int CONFIRM = 4;
        /**已删除*/
        int DELETE = 9;
    }

    /**
     * 发货确认单状态
     */
    interface DeliveryConfirmStatus {
        String TYPE = "DeliveryConfirmStatus";
        /**新建*/
        int NEW = 0;
        /**待确认*/
        int PENDING_PUR = 1;
        /**已确认*/
        int CONFIRM = 4;
        /**待发货*/
        int TO_BE_SHIPPED = 5;
        /**部分完成*/
        int PARTIAL_COMP = 6;
        /**已完成*/
        int COMPLETE = 7;
        /**已删除*/
        int CANCEL = 9;
    }

    /**
     * 预入库通知单状态
     */
    interface ProductRecvStatus {
        String TYPE = "ProductRecvStatus";
        /**待发货*/
        int TO_BE_SHIPPED = 0;
        /**在途*/
        int TRANSIT = 1;
        /**全部收货*/
        int RECEPIT = 5;
        /**部分收货*/
        int PAR_RECEPIT = 6;
        /**已取消*/
        int CANCEL = 4;
        /**已删除*/
        int EXCEPTION = 9;
    }

    /**
     * 支付类型
     */
    interface SscPaymentType {
        String TYPE = "SscPaymentType";
        /**预付款*/
        int DOWN_PAYMENT = 0;
        /**进度款*/
        int PROGRESS_PAYMENT = 1;
        /**余款*/
        int BALANCE = 2;
    }

    /**
     * 支付状态
     */
    interface PaymentStatus {
        String TYPE = "PaymentStatus";
        /**待审核*/
        int PENDING_AUDIT = 0;
        /**部分付款*/
        int PAR_PAID = 1;
        /**已付款*/
        int PAID = 2;
        /**全部付款*/
        int AL_PAID = 3;
        /**未付款*/
        int UNPAID = 4;
        /**已删除*/
        int DELETE = 9;
    }

    /**
     * 审批结果
     */
    interface ApproveResult {
        String TYPE = "ApproveResult";
        /**同意*/
        int AGREE = 0;
        /**不同意*/
        int DISAGREE = 1;
    }

    /**
     * 审核结果
     */
    interface AuditResult {
        String TYPE = "AuditResult";
        /**同意*/
        int AGREE = 0;
        /**不同意*/
        int DISAGREE = 1;
    }

    /**
     *
     */
    interface DifferStatus {
        String TYPE = "DifferStatus";
        /**未确认*/
        int PENDING_CONFIRM = 0;
        /**已确认*/
        int CONFIRM  = 1;
    }

    /**
     *核销单处理状态
     */
    interface VerificationStatus {
        String TYPE = "VerificationStatus";
        /**未处理*/
        int UNTREATED = 0;
        /**待确认*/
        int PENDING_CONFIRM   = 1;
        /**确认中*/
        int IN_CONFIRM = 2;
        /**已确认*/
        int CONFIRM = 3;
        /**已结案*/
        int CLOSED = 4;
        /**已删除*/
        int DELETED = 5;
    }

    /**
     *核销处理办法
     */
    interface VerificationType {
        String TYPE = "VerificationType";
        /**退款*/
        int REFUND = 0;
        /**挂账*/
        int BILL = 1;
        /**其他*/
        int OTHER = 2;
    }

    /**
     *发票申请状态
     */
    interface InvoiceStatus {
        String TYPE = "InvoiceStatus";
        /**申请中*/
        int APPL = 0;
        /**已审批*/
        int APPROVED = 1;
        /**已审核*/
        int AUDITED = 2;
        /**已开票*/
        int INVOICED = 3;
        /**已接收发票*/
        int RECEIVED = 4;
        /**审批未通过*/
        int APPROVE_NG = 5;
        /**审核未通过*/
        int AUDIT_NG = 6;
        /**已删除*/
        int DELETE = 9;
    }

    /**
     *审批审核状态
     */
    interface AuditingStatus {
        String TYPE = "AuditingStatus";
        /**未审批*/
        int UNAPPROVED = 0;
        /**未审核*/
        int UNAUDITED = 1;
        /**已审核*/
        int AUDITED = 2;
        /**审批未通过*/
        int APPROVE_NG = 3;
        /**审核未通过*/
        int AUDIT_NG = 4;
    }

    /**
     *核销单审核状态
     */
    interface VerificationAuditStatus {
        String TYPE = "VerificationAuditStatus";
        /**未确认*/
        int PENDING_CONFIRM = 0;
        /**甲方已确认*/
        int PUR_CONFIRM = 1;
        /**乙方已确认*/
        int SUPP_COMFIRM = 2;
        /**双方已确认*/
        int CONFIRMED = 3;
    }

    /**
     *运费结算方式
     */
    interface FreightSettleMethod {
        String TYPE = "FreightSettleMethod";
        /**到岸价*/
        int CIF = 1;
        /**离岸价价内结算*/
        int WITHIN_FOB = 2;
        /**离岸价价外代付结算*/
        int FOB = 3;
        /**独立第三方支付*/
        int  INDEPENDENT_PAYMENT = 4;
    }

    /**
     * 付款科目
     */
    interface Subject{
        String TYPE = "Subject";
        /**货款*/
        int GOODS_PAYMENT = 0;
        /**运费*/
        int FREIGHT = 1;
    }

    /**
     * RelationType
     */
    interface RelationType{
        String TYPE = "RelationType";
        /**正常*/
        int NORMAL   = 0;
        /**已关联*/
        int RELATED = 1;
        /**未关联*/
        int UNRELATE = 2;
    }

    /**
     * SscEpMainClass
     */
    interface SscEpMainClass{
        String TYPE = "SscEpMainClass";
        /**生产商*/
        int SUPPLY = 0;
        /**自产型*/
        int PRODUCT = 1;
        /**代理型*/
        int AGENT = 2;
        /**OEM型*/
        int OEM = 3;
    }

}