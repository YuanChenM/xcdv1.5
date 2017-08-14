package com.msk.common.consts;

/**
 * Created by dai_youcheng on 2016/6/30.
 * Common常量定义
 */
interface CommonConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Common";
    /**
    *YESNO
    */
    interface IsYesNo{
        String TYPE = "IsYesNo";
        /**大区*/
        int NO = 0;
        /**大区*/
        int YES = 1;
    }

    /**
     * 行政区域划分级别
     *
     */
    interface DivisionLevel {
        String Type = "DivisionLevel";
        /**大区*/
        int Area = 1;
        /**省或物流区*/
        int PrLgcs = 2;
        /**城市(区域)*/
        int City = 3;
        /**区(县)*/
        int District = 4;
    }

    /**
     *通知查看状态
     */
    interface ReaderStatus{
        String TYPE = "ReaderStatus";
        /**未读*/
        int UNREAD = 0;
        /**已读*/
        int READ = 1;
    }

    /**
     *通知类型
     */
    interface NoticeType{
        String TYPE = "NoticeType";
        /**系统通知*/
        int SYSTEM_NOTIFICATION = 1;
    }
}
