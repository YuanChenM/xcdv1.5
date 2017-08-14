package com.msk.common.utils;

import com.hoperun.web.utils.SessionManger;
import com.msk.common.bean.LoginUser;
import com.msk.org.bean.ActionAuthoritie;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangnan on 15/11/26.
 */
public final class AuthoritieUtils {
    /**
     * 获得角色可以访问的页面列表
     *
     * @param request Http 请求的Request
     * @return
     */
    public static List<String> getRolePageIdList(HttpServletRequest request) {
        List<String> rolePageList = new ArrayList<String>();
//        SessionManger sessionManger = SessionManger.getSessionManger(request);
//        LoginUser loginUser = sessionManger.getLoginUser();
//        if (loginUser != null) {
//            List<LoginRole> roleList = loginUser.getRoleList();
//            if (!CollectionUtils.isEmpty(roleList)) {
//                for (LoginRole role : roleList) {
//                    List<LoginRoleAuthority> roleAythorityList = role.getRoleAuthoritieList();
//                    if (!CollectionUtils.isEmpty(roleAythorityList)) {
//                        for (LoginRoleAuthority roleAuthority : roleAythorityList) {
//                            String pageNo = roleAuthority.getPageNo();
//                            String resNo = roleAuthority.getRecNo();
//                            if (!StringUtil.isEmpty(pageNo)) {
//                                rolePageList.add(pageNo);
//                            }
//                            if (!StringUtil.isEmpty(resNo)) {
//                                rolePageList.add(resNo);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        return rolePageList;
    }

    /**
     * 获取按钮权限
     */
    public static Boolean getButtonAuth(HttpServletRequest request, String pageCode, String actionCode) {
        Boolean result = false;
        SessionManger sessionManger = SessionManger.getSessionManger(request);
        LoginUser loginUser = (LoginUser) sessionManger.getValue(SessionManger.USER_SESSION_KEY);;
        if (loginUser != null) {
            List<ActionAuthoritie> list = loginUser.getActionList();
            if (!CollectionUtils.isEmpty(list)) {
                for (ActionAuthoritie bean : list) {
                    if (pageCode.equals(bean.getPageCode()) && actionCode.equals(bean.getActionCode())) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
}

