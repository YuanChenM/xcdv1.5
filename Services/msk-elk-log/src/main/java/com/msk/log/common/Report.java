package com.msk.log.common;

import java.util.Date;

public class Report {

	private int successAccount;
	private int errorAccount;
	private String avgTime;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public int getSuccessAccount() {
		return successAccount;
	}

	public void setSuccessAccount(int successAccount) {
		this.successAccount = successAccount;
	}

	public int getErrorAccount() {
		return errorAccount;
	}

	public void setErrorAccount(int errorAccount) {
		this.errorAccount = errorAccount;
	}

	public String getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}


}
