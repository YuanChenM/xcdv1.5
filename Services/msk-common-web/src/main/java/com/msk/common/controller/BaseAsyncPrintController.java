package com.msk.common.controller;

import com.msk.common.bean.AsyncReportTaskInfo;
import com.msk.common.service.bean.AsyncPDFInfoRequestEntity;

import java.io.IOException;

/**
 * 提供异步任务 controller 的基础接口。
 * Created by ye_wenchen on 2016/7/12.
 */
interface BaseAsyncPrintController
{
	/**
	 * 异步任务发起。
	 * @return
	 */
	void start(AsyncPDFInfoRequestEntity p_info) throws IOException;

	/**
	 * 异步任务状态维护。
	 * @return
	 */
	void taskStatusUpdate(AsyncReportTaskInfo p_taskInfo);

	/**
	 * 异步任务状态查询。
	 * @return
	 */
	 AsyncReportTaskInfo taskStatusCheck(String p_taskID) throws Exception;
}