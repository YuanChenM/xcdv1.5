package com.msk.common.listener;

import com.hoperun.web.listener.ApplicationListenerService;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.sso.client.constant.PasswordConfigServer;
import com.msk.sso.client.constant.SSORelevanceDefine;

import javax.servlet.ServletContext;

/**
 * CodeMasterLoadApplicationListenerService
 * @author jiang_nan
 * @version 1.0
 **/
public class CodeMasterLoadApplicationListenerService implements ApplicationListenerService {

    public void handle(ServletContext application) {
        application.setAttribute("serverName",SSORelevanceDefine.getServerName());
        SSORelevanceDefine.setLoginEmployInfoServerUrl(SystemServerManager.OrgServerManager.getLoginEmployInfo());
        SSORelevanceDefine.setLoginSellerInfoServerUrl(SystemServerManager.SellerServerManage.getQueryAccount());
        SSORelevanceDefine.setSearchMenuServerUrl(SystemServerManager.OrgServerManager.getSearchMenuList());
        SSORelevanceDefine.setCasServerLoginUrl(SystemServerManager.SsoServerManager.getCasServerLoginUrl());
        SSORelevanceDefine.setCasServerLogoutUrl(SystemServerManager.SsoServerManager.getCasServerLogoutUrl());
        SSORelevanceDefine.setCasServerUrlPrefix(SystemServerManager.SsoServerManager.getCasServerUrlPrefix());
        SSORelevanceDefine.setSystemModuleServerUrl(SystemServerManager.OrgServerManager.getSearchSystemModule());
        SSORelevanceDefine.setLoginUserInfoServerUrl(SystemServerManager.OrgServerManager.getLoginUserInfo());
        SSORelevanceDefine.setVersion(ConfigManager.getSystemVersion());
        SSORelevanceDefine.setDepartmentRole(SystemServerManager.OrgServerManager.getDepartmentRole());
        //密码相关常量
        SSORelevanceDefine.setModifyPassword(SystemServerManager.OrgServerManager.getModifyPassword());
        PasswordConfigServer.setIsForceModifyPassword(Boolean.valueOf(SystemServerManager.CommonServerManager.getIsForceModifyPassword().trim()));
        PasswordConfigServer.setIsInitPassword(Boolean.valueOf(SystemServerManager.CommonServerManager.getIsInitPassword().trim()));
        PasswordConfigServer.setIsCheckPasswordTimeSeries(Boolean.valueOf(SystemServerManager.CommonServerManager.getIsCheckPasswordTimeSeries().trim()));
    }
}
