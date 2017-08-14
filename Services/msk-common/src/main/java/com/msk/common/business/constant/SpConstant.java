package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/29.
 * Sp常量定义
 */
public interface SpConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Sp";
    /**
     * 投标状态
     */
    public interface DemandApplyStatus{
        String TYPE = "DemandApplyStatus";
        /**未投标*/
        int no_tender = 0;
        /**未中标*/
        int NO_WINNIG = 1;
        /**驳回*/
        int REJECT = 2;
        /**已中标*/
        int TENDER = 3;
    }

    /**
     * 是否确认
     */
    interface IsConfirm{
        String TYPE = "isConfirm";
        /**未确认*/
        int NOCONFIRM = 1;
        /**驳回*/
        int REJECT = 2;
        /**已确认*/
        int CONFIRM = 3;
    }

    /**
     * 角色
     */
    interface Role{
        String TYPE = "Role";
        /**采购员*/
        int BUYER = 1;
        /**供应商*/
        int SUPPLIER = 2;
    }

    /**
     * 单位
     */
    public interface Untis{
        String TYPE = "Untis";
        /*箱*/
        String BOX = "1";
        /*吨*/
        String TON = "2";
    }

    /**
     * 营销状态
     */
    public interface MarketingStatus{
        String TYPE = "MarketingStatus";
        /*大宗惠*/
        String BULK_BENEFIT = "0";
        /*新品惠*/
        String NEW_BENEFITS = "1";
        /*秒杀*/
        String SECKILL = "2";

    }
}
