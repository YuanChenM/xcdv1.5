package com.msk.sso.client.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.TypeReference;
import com.msk.org.bean.DepartmentDef;
import com.msk.org.bean.LoginUser;
import com.msk.org.bean.LoginUserType;
import com.msk.org.bean.param.LoginUserParam;
import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.entity.OrgSystemModule;
import com.msk.sso.client.bean.RestRequest;
import com.msk.sso.client.bean.RestResponse;
import com.msk.sso.client.bean.SystemModule;
import com.msk.sso.client.constant.SSORelevanceDefine;
import com.msk.sso.client.utils.AssertionUtils;
import com.msk.sso.client.utils.RestClientUtil;
import com.msk.sso.client.utils.UserSessionManger;

public class SwitchSystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> ssoUerInfoMap = AssertionUtils.getSsoUserInfo(req);
        String userType = ssoUerInfoMap.get(AssertionUtils.SSO_USER_INFO_MAP_USER_TYPE_KEY);
        String userName = ssoUerInfoMap.get(AssertionUtils.SSO_USER_INFO_MAP_USER_NAME_KEY);
        this.initModuleList(req, userType, userName);
        req.getRequestDispatcher("/jsp/switch.jsp").forward(req, resp);
    }

    private void initModuleList(HttpServletRequest request, String userType, String userName) {
        RestRequest<String> requestBody = new RestRequest<>();
        requestBody.setParam(userType);
        String systemModuleServerUrl = SSORelevanceDefine.getSystemModuleServerUrl();
        RestResponse<List<SystemModule>> responseBody = RestClientUtil.post(systemModuleServerUrl, requestBody,
            new TypeReference<RestResponse<List<SystemModule>>>() {});
        List<SystemModule> result = responseBody.getResult();
        UserSessionManger userSessionManger = new UserSessionManger();
        if(LoginUserType.getUserTypeSeller().equals(userType)){
            request.setAttribute("moduleList", result);
            userSessionManger.setUserRoleAuth(getSellerRole(),request);
            return;
        }
        LoginUser loginUser = this.getEmploy(userName, userType);
        List<OrgSystemModule> systemModuleList = loginUser.getSystemModules();
        this.initSystemModule(result,systemModuleList);
        request.setAttribute("moduleList", result);
        userSessionManger.setUserRoleAuth(loginUser.getUserRoleList(),request);
    }

    private List<OrgRoleAuth> getSellerRole(){
        RestRequest<String> requestBody = new RestRequest<>();
        requestBody.setParam(DepartmentDef.getDepartmentSeller());
        String url = SSORelevanceDefine.getDepartmentRole();
        RestResponse<List<OrgRoleAuth>> responseBody = RestClientUtil.post(url, requestBody,
                new TypeReference<RestResponse<List<OrgRoleAuth>>>() {});;
        return responseBody.getResult();
    }

    private void initSystemModule(List<SystemModule> result, List<OrgSystemModule> systemModuleList) {
        for (SystemModule systemModule : result) {
            boolean flg = false;
            for (OrgSystemModule userSystemModule : systemModuleList) {
                String systemModuleSystemCode = systemModule.getSysCode();
                String userSystemSystemCode = userSystemModule.getSysCode();
                if (systemModuleSystemCode.equals(userSystemSystemCode)||"ALL".equals(userSystemSystemCode)) {
                    flg = true;
                    break;
                }
            }
            if (!flg) {
                systemModule.setSysMainUrl(null);
            }
        }
    }

    private LoginUser getEmploy(String employCode, String userType) {
        String url = SSORelevanceDefine.getLoginUserInfoServerUrl();
        LoginUserParam loginUserParam = new LoginUserParam();
        loginUserParam.setUserName(employCode);
        loginUserParam.setUserType(userType);
        RestRequest<LoginUserParam> requestBody = new RestRequest<>();
        requestBody.setParam(loginUserParam);
        RestResponse<LoginUser> responseBody = RestClientUtil.post(url, requestBody,
            new TypeReference<RestResponse<LoginUser>>() {});
        return responseBody.getResult();
    }

}
