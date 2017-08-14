package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/8/18.
 * Bs常量定义
 */
public interface BsConstant {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "Bs";

    /**
     * 冻品管家分类级别
     */
    interface HkGradeCode {
        String Type = "HkGradeCode";
        /**特级*/
        int Super = 0;
        /**高级*/
        int senior = 1;
        /**中级*/
        int middle = 2;
        /**见习*/
        int TRAINEE = 3;
    }

    /**
     * 冻品管家分类级别
     */
    interface ApplySide {
        String Type = "ApplySide";
        /**冻品管家发展专属会员买家*/
        String FROZEN_GOODS_MANAGER_DEVELOP_OWN_BUYERS = "A";
        /**买家选择专属冻品管家*/
        String BUYER_CHOOSE_OWN_FROZEN_GOODS_MANAGER = "B";
    }
}
