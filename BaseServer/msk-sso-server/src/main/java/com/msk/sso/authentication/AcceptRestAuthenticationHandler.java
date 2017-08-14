package com.msk.sso.authentication;

import java.security.GeneralSecurityException;
import java.util.HashMap;

import javax.security.auth.login.FailedLoginException;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;

import com.alibaba.fastjson.TypeReference;
import com.msk.comm.bean.ConfigParam;
import com.msk.comm.bean.LoginParam;
import com.msk.comm.bean.RestRequest;
import com.msk.comm.bean.RestResponse;
import com.msk.comm.utils.ConfigInfo;
import com.msk.comm.utils.RestClientUtil;

/**
 * Created by jackjiang on 16/8/5.
 */
public class AcceptRestAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential) throws GeneralSecurityException, PreventedException {
        RestLoginCredential restLoginCredential = (RestLoginCredential)credential;
        final String username = credential.getUsername();
        final String userType = restLoginCredential.getUserType();
        final String password = credential.getPassword();
        ConfigInfo configInfo = ConfigInfo.newConfigInfo();
        System.out.println(configInfo.getEnvironment());
        System.out.println("用户类型:"+userType);
        String loginRestServerUrl = this.getLoginRestfulServerUrl();
        System.out.println("Login Rest Server Url:"+loginRestServerUrl);
        LoginParam loginParam = new LoginParam();
        loginParam.setUserName(username);
        loginParam.setPassword(password);
        loginParam.setUserType(userType);
        RestRequest<LoginParam> requestParam = new RestRequest<LoginParam>();
        requestParam.setParam(loginParam);
        RestResponse<Boolean> loginUserResponse = RestClientUtil.post(loginRestServerUrl,requestParam,new TypeReference<RestResponse<Boolean>>(){});
        Boolean result = loginUserResponse.getResult();
        if(!result){
            throw new FailedLoginException();
        }
        return createHandlerResult(restLoginCredential, new SimplePrincipal(username), null);
    }
    private String getLoginRestfulServerUrl(){
        ConfigInfo configInfo = ConfigInfo.newConfigInfo();
        String loginModuleName = configInfo.getLoginModuleName();
        String loginServerName = configInfo.getLoginServerName();
        ConfigParam param = new ConfigParam();
        param.setEnvironment(configInfo.getEnvironment());
        param.setType("ConfigConstant");
        param.setModelName(loginModuleName);
        param.setKey(loginServerName);

        RestRequest<ConfigParam> requestBody = new RestRequest<>();
        requestBody.setParam(param);
        String configConstantUrl = configInfo.getConfigServerUrl() + configInfo.getConfigLoadConfigConstant();
        RestResponse<HashMap<String,Object>> responseBody =  RestClientUtil.post(configConstantUrl,requestBody,new TypeReference<RestResponse<HashMap<String,Object>>>(){});
        HashMap<String,Object> result = responseBody.getResult();
        String baseUrl = (String) result.get(configInfo.getEnvironment());
        String moduleUrl = (String) result.get(loginModuleName);
        String apiUrl = (String) result.get(loginServerName);
        return baseUrl + moduleUrl + apiUrl;
    }



}
