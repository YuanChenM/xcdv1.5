package com.msk.org.logic;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.param.ConfigParam;
import com.msk.common.utils.RestClientUtil;
import com.msk.org.bean.param.SellerLoginParam;

@Service
public class SellerLoginLogic {
    @Resource
    private Environment environment;

    public Boolean sellerLogin(String userName,String password){
        SellerLoginParam param = new SellerLoginParam();
        param.setSlAccount(userName);
        param.setAccountPsd(password);
        String url = this.getSellerLoginServerUrl();
        RestRequest<SellerLoginParam> requestBody = new RestRequest<>();
        requestBody.setParam(param);
        requestBody.setAuth("ORG");
        requestBody.setLoginId("ORG");
        RestResponse<HashMap<String,Object>> responseBody = RestClientUtil.post(url,requestBody,new TypeReference<RestResponse<HashMap<String, Object>>>(){});
        HashMap<String,Object> result = responseBody.getResult();
        String status = responseBody.getStatus();
        if(StringUtils.isEmpty(status)||status.equalsIgnoreCase("F")){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private String getSellerLoginServerUrl(){
        String environment = this.environment.getProperty("config.server.environment");
        String configServerUrl = this.environment.getProperty("config.server.url");
        String configConstantUrl = this.environment.getProperty("config.server.configConstantUrl");
        String moduleName = this.environment.getProperty("config.server.sellerModuleName");
        String key = this.environment.getProperty("config.server.sellerLoginServerName");
        ConfigParam param = new ConfigParam();
        param.setEnvironment(environment);
        param.setModelName(moduleName);
        param.setKey(key);
        param.setType("ConfigConstant");
        RestRequest<ConfigParam> requestBody = new RestRequest<>();
        requestBody.setParam(param);
        String url = configServerUrl + configConstantUrl;
        RestResponse<HashMap<String,Object>> responseBody = RestClientUtil.post(url,requestBody,new TypeReference<RestResponse<HashMap<String,Object>>>(){});
        HashMap<String,Object> result = responseBody.getResult();
        return  result.get(environment).toString() + result.get(moduleName).toString() + result.get(key).toString();
    }


}
