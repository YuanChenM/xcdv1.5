package com.msk.common.controller;

import com.msk.common.bean.AsyncReportTaskInfo;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;

import java.io.IOException;

/**
 * 提供异步任务 controller 的基础接口。
 * Created by ye_wenchen on 2016/7/12.
 */
interface BaseAsyncPrintFasterController {
    /**
     * 异步任务发起。
     *
     * @return
     */
    void startFaster(AsyncPDFInfoRequestEntity p_info) throws IOException;

    /**
     * 异步任务状态维护。
     *
     * @return
     */
    void taskStatusUpdateFaster(AsyncReportTaskInfo p_taskInfo);

    /**
     * 异步任务状态查询。
     *
     * @return
     */
    AsyncReportTaskInfo taskStatusCheckFaster(String p_taskID) throws Exception;
}