package com.msk.print.bean;

/**
 * Created by ye_wenchen on 2016/7/18.
 */
public class AsyncReportTaskInfo
{
	private String taskID;
	private String status;
	private String info;

	public AsyncReportTaskInfo(){}

	public AsyncReportTaskInfo(String taskID, String status, String info)
	{
		super();
		this.taskID = taskID;
		this.status = status;
		this.info = info;
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
