package com.msk.comm.bean;

/**
 * 表示报表异步导出任务的信息实体类。
 * Created by ye_wenchen on 2016/7/14.
 */
public class AsyncReportTaskInfo
{
	// 任务唯一标识。
	private String taskID;

	// 任务状态。（0 为未完成， 1 为完成， 2为任务异常已中断）
	private String status;

	// 任务状态信息。（status 为未完成时，记录任务阶段，status 为完成时 记录存储在文件服务器中的文件唯一标识，status为任务异常时 记录异常信息）
	private String info;

	public AsyncReportTaskInfo(String p_taskID)
	{
		this.taskID = p_taskID;
	}

	public AsyncReportTaskInfo(String p_taskID, String p_status, String p_info)
	{
		this.taskID = p_taskID;
		this.status = p_status;
		this.info = p_info;
	}

	public AsyncReportTaskInfo(){
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}