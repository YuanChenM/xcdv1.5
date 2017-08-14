package com.msk.common.service;

import com.msk.common.bean.AsyncReportTaskInfo;
import com.msk.common.dao.RedisExtendsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供对异步打印任务操作的 Service。
 * Created by ye_wenchen on 2016/7/15.
 */
@Service("AsyncPrintTaskService")
public class AsyncPrintTaskService
{
	@Autowired
	RedisExtendsUtils redisExtendsUtils;

	// 指定存储异步方式任务信息的 redis 操作库索引编号。
	private final static Integer REDIS_DATABASE_INDEX = 10;

	// 任务未完成状态。
	private final static String TASK_STATUS_RUNNING = "0";
	// 任务完成状态。
	private final static String TASK_STATUS_COMPLETED = "1";
	// 任务报错中断状态。
	private final static String TASK_STATUS_ERROR = "2";
	/**
	 * 更新任务状态。
	 * @param p_taskInfo 指定的任务状态信息。
	 */
	public void updateTaskStatus(AsyncReportTaskInfo p_taskInfo)
	{
		redisExtendsUtils.setDatabase(REDIS_DATABASE_INDEX);
		Map<String, Object> map = new HashMap<>();
		map.put("status",p_taskInfo.getStatus());
		map.put("info",p_taskInfo.getInfo());
		redisExtendsUtils.saveRedisMap(p_taskInfo.getTaskID(), map);
	}

	public void updateTaskStatus(String taskID, String status, String info)
	{
		AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(taskID, status, info);
		updateTaskStatus(taskInfo);
	}

	/**
	 * 获取任务状态信息。
	 * @param p_taskID 指定的任务唯一标识。
	 * @return
	 */
	public AsyncReportTaskInfo checkTaskStatus(String p_taskID) throws Exception
	{
		redisExtendsUtils.setDatabase(REDIS_DATABASE_INDEX);
		if(redisExtendsUtils.exist(p_taskID))
		{
			Map<String, String> taskInfoMap = redisExtendsUtils.getRedisMapValue(p_taskID);
			AsyncReportTaskInfo taskInfo = new AsyncReportTaskInfo(p_taskID);
			taskInfo.setStatus(taskInfoMap.get("status"));
			taskInfo.setInfo(taskInfoMap.get("info"));

			return taskInfo;
		}
		else
		{
			throw new Exception("获取任务状态信息失败，指定的任务唯一标识 " + p_taskID + " 不存在。");
		}
	}
}