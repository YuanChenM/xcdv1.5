package com.msk.comm.utils;

import com.alibaba.fastjson.TypeReference;
import com.msk.comm.bean.ConfigParam;
import com.msk.print.bean.RsRequest;
import com.msk.print.bean.RsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by ye_wenchen on 2016/7/18.
 */
@Component("com.msk.comm.utils.ConfigManager")
public class ConfigManager {
	private static   Environment environment;

	private static final String COMM = "COMM";
	@Autowired
	public void setConfigInfo(final Environment environment) {
		ConfigManager.environment = environment;
	}

	public static String getFileServerUpload()
	{
		return getServiceByKey("FlieServerUpload", COMM);
	}

	public static String getFileServerUploadServices()
	{
		return getServiceByKey("FlieUploadServices", COMM);
	}


	public static String getMskFileDownLoadServices()
	{
		return getServiceByKey("MskFlieDownLoad", COMM);
	}

	private static String getServiceByKey(String key,String modelName)
	{
		ConfigParam param = new ConfigParam();
		param.setEnvironment(getConfigEnvironment());
		param.setModelName(modelName);
		param.setType("ConfigConstant");
		param.setKey(key);
		RsRequest<ConfigParam> requestBody = new RsRequest<>();
		requestBody.setParam(param);
		String url = getConfigConstantUrl();
		RsResponse<HashMap<String,Object>> response = RestClientUtil.post(url,requestBody,new TypeReference<RsResponse<HashMap<String, Object>>>(){});
		HashMap<String,Object> resultMap = response.getResult();
		return String.valueOf(resultMap.get(key));
	}



	public static String getConfigEnvironment(){
		return environment.getProperty("config.environment");
	}
	private static String getConfigBaseUrl(){
		return environment.getProperty("config.server.baseUrl");
	}
	public static String getConfigConstantUrl(){
		return getConfigBaseUrl() + environment.getProperty("config.server.constant");
	}
}
