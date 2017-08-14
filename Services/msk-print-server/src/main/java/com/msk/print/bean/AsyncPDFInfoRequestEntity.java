package com.msk.print.bean;

/**
 * Created by ye_wenchen on 2016/7/18.
 */
public class AsyncPDFInfoRequestEntity
{
	// 任务唯一标识。
	private String taskID;

	// 获取数据源的 service 的名称。
	private String serviceName;

	// 文件服务器上存储的文件唯一标识。
	private String fileCode;

	// PDF 打印模板的唯一标识。
	private String templateCode;

	//模块名
	private String modelName;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
