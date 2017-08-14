package com.msk.common.business.constant;

/**
 * Created by dai_youcheng on 2016/6/30.
 * Ds常量定义
 */
public interface BuyersReport {
    /**Config 服务中系统模块名称*/
    String SYSTEM_MODULE_NAME = "BuyersReport";
    /**
     * 需求等级
     */
    interface SettingType{
        String TYPE = "SettingType";
        int REQUIRE_TYPE = 1;
    }

    /**
     * 文件状态
     */
    interface FileStatus{
        String TYPE = "FileStatus";
        int NOT_GENERATED = 0;
        int GENERATED = 1;
    }

}
