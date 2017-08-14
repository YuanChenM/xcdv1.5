package com.msk.log.common;

public class Common {
	//查询日志级别
public interface LOG_LEVEL{
	public String ERROR_LEVEL="ERROR";
	public String INFO_LEVEL="INFO";
	public String ALL_LEVEL="*";
}
//时间差级别
public interface TIME_LEVEL{
	public String DAY_LEVEL="DAY";
	public String HOUR_LEVEL="HOUR";
}
//时间差级别
public interface REQUEST_LEVEL{
	public String ACCOUNT="ACCOUNT";
	public String DETAIL="DETAIL";
}
}
