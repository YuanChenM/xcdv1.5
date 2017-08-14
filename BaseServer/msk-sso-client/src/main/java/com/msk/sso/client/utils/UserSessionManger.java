package com.msk.sso.client.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.msk.org.bean.LoginUserType;
import com.msk.org.bean.SessionKey;
import com.msk.org.entity.OrgRoleAuth;
import com.msk.sso.client.bean.LoginUser;

/**
 * Session Manager
 */
public final class UserSessionManger {
    public static String USER_TYPE_BUYER = "3";
    public static String USER_TYPE_SELLER = "2";
    public static String USER_TYPE_EMPLOY = "1";
    public static String USER_TYPE_BANK = "99";
    /** 用户Session Key */
    public static String USER_SESSION_KEY = "loginUser";
    public LoginUser getLoginUserSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser)session.getAttribute(USER_SESSION_KEY);
        if(loginUser == null){
            loginUser = this.createLoginUserSession(request);
            session.setAttribute(USER_SESSION_KEY,loginUser);
        }
        return loginUser;
    }

    public void setUserRoleAuth(List<OrgRoleAuth> roleAuthList, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(SessionKey.getUserRoleAuth(),roleAuthList);
    }

    public List<OrgRoleAuth> getUserRoleAuth(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (List<OrgRoleAuth>)session.getAttribute(SessionKey.getUserRoleAuth());
    }


    public LoginUser createLoginUserSession(HttpServletRequest request){
        Map<String,String> ssoUerInfoMap = AssertionUtils.getSsoUserInfo(request);
        String userType = ssoUerInfoMap.get(AssertionUtils.SSO_USER_INFO_MAP_USER_TYPE_KEY);
        Map<String,Object> loginUserInfoMap = AssertionUtils.getLoginUserInfo(ssoUerInfoMap);
        LoginUser loginUser = new LoginUser();
        String employCode = "";
        String employName = "";
        String employId = "";
        if(LoginUserType.getUserTypeEmploy().equals(userType)) {
            employCode = (String) loginUserInfoMap.get("employCode");
            employName = (String)loginUserInfoMap.get("employName");
            employId = String.valueOf(loginUserInfoMap.get("employId"));
        } else if(LoginUserType.getUserTypeSeller().equals(userType)) {
            employCode = (String) loginUserInfoMap.get("slAccount");
            employName = (String)loginUserInfoMap.get("slContact");
            employId = String.valueOf(loginUserInfoMap.get("slAccount"));
        } else if(LoginUserType.getUserTypeBank().equals(userType)) {
            employCode = (String) loginUserInfoMap.get("employCode");
            employName = (String)loginUserInfoMap.get("employName");
            employId = String.valueOf(loginUserInfoMap.get("employId"));

        }
        loginUser.setEmplName(employName);
        loginUser.setEmplId(employId);
        loginUser.setEmplNo(employCode);
        loginUser.setUserType(userType);
        return loginUser;
    }


}