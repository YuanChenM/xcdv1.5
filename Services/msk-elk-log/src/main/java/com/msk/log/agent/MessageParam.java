package com.msk.log.agent;

public class MessageParam {
    /**请求uri*/
	private String requestUri;
	/**请求参数*/
	private String requestParam;
	/**请求花费时间*/
	private String requestTime;
	/**返回参数*/
	private String  responseTime;
	/**返回结果*/
	private boolean result;
	/**返回结果*/
	private String responseParam;
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getResponseParam() {
		return responseParam;
	}
	public void setResponseParam(String responseParam) {
		this.responseParam = responseParam;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getRequestParam() {
		return requestParam;
	}
	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
