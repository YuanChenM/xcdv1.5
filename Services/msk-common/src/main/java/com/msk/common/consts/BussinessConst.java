package com.msk.common.consts;

public interface BussinessConst {

    interface SlMainClass{
        String TYPE = "slMainClass";
        /**生产商*/
        int SUPP = 0;
        /**自产型*/
        int PRODUCT = 1;
        /**代理型*/
        int AGENT = 2;
        /**OEM型*/
        int OEM = 3;
        /**买手*/
        int BS = 4;
    }

    /**
     * 分销通道
     */
    interface WayType{
        String TYPE = "wayType";
        /**超级大宗订单*/
        int SUPERORDER = 1;
        /**大宗订单*/
        int LARGEORDER = 2;
        /**大额订单*/
        int BIGORDER = 3;
        /**标准订单*/
        int STANDARDORDER = 4;

    }

    /**
     * 是否确认
     */
    interface ISCONFIRM{
        String TYPE = "isConfirm";
        /**未确认*/
        int NOCONFIRM = 1;
        /**驳回*/
        int REJECT = 2;
        /**已确认*/
        int CONFIRM = 3;

    }

    /**
     * 是否同意档案卡
     */
    interface ISAGREE{
        String TYPE = "isAgree";
        /**同意*/
        int AGREE = 1;
        /**不同意*/
        int NOAGREE = 0;

    }

}
