package com.msk.common.listener;

import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.sso.client.constant.PasswordConfigServer;
import com.msk.sso.client.constant.SSORelevanceDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jackjiang on 16/8/5.
 */
public class InitializerListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(InitializerListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("serverName", SSORelevanceDefine.getServerName());
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

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
