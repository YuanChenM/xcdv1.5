package com.msk.common.base;

import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.LoginUser;
import com.msk.core.entity.BaseEntity;
import com.msk.sso.client.utils.UserSessionManger;

/**
 * Created by jackjiang on 16/6/14.
 */
public class BaseUploadController extends com.hoperun.plug.spring.web.base.BaseUploadController{
    /**
     * 获得登陆用户信息
     *
     * @return 登陆信息
     */
    protected LoginUser getLoginUser() {
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
    protected void setCommonParam(BaseParam param) {
        LoginUser loginUser = this.getLoginUser();
        param.setCrtId(loginUser.getEmplId());
        param.setUpdId(loginUser.getEmplId());
        param.setActId(loginUser.getEmplId());
        param.setUserType(loginUser.getUserType());
    }

    /**
     * 设置共通字段
     *
     * @param param Param
     */
    protected void setCommonParam(BaseEntity param) {
        LoginUser loginUser = this.getLoginUser();
        param.setCrtId(loginUser.getEmplId());
        param.setUpdId(loginUser.getEmplId());
        param.setActId(loginUser.getEmplId());
    }
}
