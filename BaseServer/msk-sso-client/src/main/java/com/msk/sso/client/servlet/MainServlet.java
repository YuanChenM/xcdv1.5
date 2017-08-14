package com.msk.sso.client.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msk.org.bean.param.LoginUserMenuParam;
import com.msk.org.bean.param.LoginUserParam;
import com.msk.org.entity.OrgSystem;
import com.msk.sso.client.bean.LoginUser;
import com.msk.sso.client.utils.AssertionUtils;
import com.msk.sso.client.utils.CookieUtils;
import com.msk.sso.client.utils.RestClientUtil;
import com.msk.sso.client.utils.UserSessionManger;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.TypeReference;
import com.msk.sso.client.bean.RestRequest;
import com.msk.sso.client.bean.RestResponse;
import com.msk.sso.client.bean.result.OrgSystemResult;
import com.msk.sso.client.constant.SSORelevanceDefine;




public class MainServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sysCode = req.getParameter("sysCode");
        String sysModel = req.getParameter("sysModel");
        String sysName = req.getParameter("sysName");
        if(StringUtils.isEmpty(sysCode)) {
            sysCode = CookieUtils.getCookieValue(req, "sysCode");
        }
        if(StringUtils.isEmpty(sysModel)) {
            sysModel = CookieUtils.getCookieValue(req,"sysModel");
        }
        if(StringUtils.isEmpty(sysName)) {
            sysName = CookieUtils.getCookieValue(req,"sysName");
        }
        if(StringUtils.isEmpty(sysCode)||StringUtils.isEmpty(sysModel)){
            req.getRequestDispatcher("/switch/system").forward(req,resp);
            return;
        }
        UserSessionManger sessionManger = new UserSessionManger();
        LoginUser loginUser = sessionManger.getLoginUserSession(req);
        LoginUserMenuParam param = new LoginUserMenuParam();
        param.setUserName(loginUser.getEmplNo());
        param.setUserType(loginUser.getUserType());

        RestRequest<LoginUserMenuParam> requestBody = new RestRequest<>();
        requestBody.setParam(param);
        String url = SSORelevanceDefine.getSearchMenuServerUrl();
        RestResponse<ArrayList<OrgSystem>> responseBody = RestClientUtil.post(url, requestBody, new TypeReference<RestResponse<ArrayList<OrgSystem>>>() {
        });
        List<OrgSystem> menuList = responseBody.getResult();
        List<OrgSystem> systemMenu = new ArrayList<>();

        Iterator<OrgSystem> iterator = menuList.iterator();
        while (iterator.hasNext()){
            OrgSystem orgSystem = iterator.next();
            String userSystemCode = orgSystem.getSysCode();
            if(!userSystemCode.startsWith(sysCode)){
                iterator.remove();
                continue;
            }
            systemMenu.add(orgSystem);
        }

        req.setAttribute("sysModel",sysModel);
        req.setAttribute("sysCode",sysCode);
        req.setAttribute("sysName",sysName);
        req.setAttribute("systemVersion",SSORelevanceDefine.getVersion());
        req.setAttribute("menuList",systemMenu);
        sessionManger.getLoginUserSession(req);
        req.setAttribute("loginId",sessionManger.getLoginUserSession(req).getEmplId());
        req.getRequestDispatcher(SSORelevanceDefine.getMainJspPatch()).forward(req,resp);
        this.init(req);
    }

    private void init(HttpServletRequest request){
        Map<String, String> ssoUerInfoMap = AssertionUtils.getSsoUserInfo(request);
        String userType = ssoUerInfoMap.get(AssertionUtils.SSO_USER_INFO_MAP_USER_TYPE_KEY);
        String userName = ssoUerInfoMap.get(AssertionUtils.SSO_USER_INFO_MAP_USER_NAME_KEY);
        UserSessionManger userSessionManger = new UserSessionManger();
        com.msk.org.bean.LoginUser loginUser = this.getEmploy(userName,userType);
        userSessionManger.setUserRoleAuth(loginUser.getUserRoleList(),request);

    }
    private com.msk.org.bean.LoginUser getEmploy(String employCode, String userType) {
        String url = SSORelevanceDefine.getLoginUserInfoServerUrl();
        LoginUserParam loginUserParam = new LoginUserParam();
        loginUserParam.setUserName(employCode);
        loginUserParam.setUserType(userType);
        RestRequest<LoginUserParam> requestBody = new RestRequest<>();
        requestBody.setParam(loginUserParam);
        RestResponse<com.msk.org.bean.LoginUser> responseBody = RestClientUtil.post(url, requestBody,
                new TypeReference<RestResponse<com.msk.org.bean.LoginUser>>() {});
        return responseBody.getResult();
    }




}
