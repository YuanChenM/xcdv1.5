package com.msk.bms.ssc.controller.common;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.web.utils.SessionManger;
import com.msk.common.bean.LoginUser;
import com.msk.sso.client.utils.UserSessionManger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liu_yan2 on 2016/8/30.
 */
public class ISSCCommonUtil {

    /** 继承 BaseUploadController 的controller 中无法获得登录信息
     * BaseController中没有该判断 request是根据控制层获取*/

     /**
     * 获得登陆用户信息
     *
     * @return 登陆信息
     */
    public static LoginUser getLoginUser(HttpServletRequest request) {
        /*LoginUser loginUser = (LoginUser) SessionManger.getSessionManger(request)
                .getValue(SessionManger.USER_SESSION_KEY);
        if (loginUser == null) {
            throw new BusinessException("登录信息失效，请重新登录！");
        }*/
        UserSessionManger sessionManger = new UserSessionManger();
        com.msk.sso.client.bean.LoginUser ssoLoginUser = sessionManger.getLoginUserSession(request);
        LoginUser loginUser = new LoginUser();
        loginUser.setEmplName(ssoLoginUser.getEmplName());
        loginUser.setUserType(ssoLoginUser.getUserType());
        loginUser.setEmplId(ssoLoginUser.getEmplId());
        return loginUser;
    }

    /**
     * 设置共通字段
     *
     * @param param Param
     */
    public static void setCommonParam(BaseParam param,HttpServletRequest request) {
        LoginUser loginUser = getLoginUser(request);
        param.setCrtId(loginUser.getEmplId());
        param.setUpdId(loginUser.getEmplId());
        param.setActId(loginUser.getEmplId());
        param.setUserType(loginUser.getUserType());
    }
}
