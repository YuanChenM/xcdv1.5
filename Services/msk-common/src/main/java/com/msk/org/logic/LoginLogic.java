package com.msk.org.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.Md5Digest;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.LoginUser;
import com.msk.common.config.ConfigManager;
import com.msk.common.consts.CodeMasterConst;
import com.msk.core.entity.OrgPage;
import com.msk.org.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 登录操作Logic
 * 
 * @author jiang_nan
 * @version 1.0
 */
@Service
public class LoginLogic extends BaseLogic {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(LoginLogic.class);

    /**
     * SQL ID定义
     * 
     * @author jiang_nan
     */
    private interface SqlId {
        String LOGIN_SQL_ID = "login";
        String FIND_ROLE_LIST_SQL_ID = "findRoleList";
        String FIND_ROLE_AUTHORITY_SQL_ID = "findRoleAuthorityList";
        String SQL_ID_GET_SYSTEM_NAME = "findSystemList";
        String SQL_ID_FIND_PAGE_LIST = "findPageList";
    }
    private interface MapKey{
        String MAP_KEY_ACTION = "ACTION";
        String MAP_KEY_MENU = "MENU";
        String MAP_KEY_PAGE = "PAGE";
    }

    @Transactional(readOnly = true)
    public List<OrgPage> findPageList(String systemCode){
        BaseParam param = new BaseParam();
        param.setFilter("systemCode",systemCode);
        List<OrgPage> pageList = super.findList(SqlId.SQL_ID_FIND_PAGE_LIST,param);
        return pageList;
    }


    @Transactional(readOnly = true)
    public List<SystemModel> findSystemList(String systemCode){
        BaseParam param = new BaseParam();
        param.setFilter("systemCode",systemCode);
        DbUtils.buildLikeCondition(param,"systemCode", DbUtils.LikeMode.FRONT);
        List<SystemModel> systemModelList = super.findList(SqlId.SQL_ID_GET_SYSTEM_NAME,param);
        if(!CollectionUtils.isEmpty(systemModelList)){
            for(SystemModel systemModel : systemModelList){
                List<OrgPage> pageList = this.findPageList(systemModel.getSysCode());
                systemModel.setPageList(pageList);
            }
        }
        return systemModelList;
    }
    /**
     * 登录用户名密码验证
     * 
     * @param loginId 登录ID
     * @param loginPwd 登陆密码
     * @return 登陆用户信息
     */
    @Transactional
    public LoginUser login(String loginId, String loginPwd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loginId", loginId);
        paramMap.put("status", CodeMasterConst.LoginStatus.AVAILABLE);
        String md5Pwd = Md5Digest.digest(loginPwd);
        //登陆验证用户名和密码
        LoginUser loginUser = super.findOne(SqlId.LOGIN_SQL_ID, paramMap);
        if(null == loginUser){
            return null;
        }
        if (!md5Pwd.equals(loginUser.getLoginPwd())) {
            return null;
        }

        //设置员工编码
        paramMap.put("emplId", loginUser.getEmplId());
        //根据员工编号获得角色列表
        List<LoginRole> roleList = this.findList(paramMap, SqlId.FIND_ROLE_LIST_SQL_ID);

        if (!CollectionUtils.isEmpty(roleList)) {
            for (LoginRole loginRole : roleList) {
                //角色编码
                paramMap.put("roleNo", loginRole.getRoleNo());
                //是否有效
                paramMap.put("isAvailable", CodeMasterConst.IsAvailable.AVAILABLE);
                //根据角色获得可以访问的页面和资源信息
                List<LoginRoleAuthority> roleAuthoritieList = this.findList(paramMap, SqlId.FIND_ROLE_AUTHORITY_SQL_ID);

                Map<String,List<?>> roleAuthoritieMap = this.createRoleAuthority(roleAuthoritieList);
                //设置Action
                List<ActionAuthoritie> actionAuthoritieList = loginUser.getActionList();
                if(null == actionAuthoritieList){
                    actionAuthoritieList = new ArrayList<ActionAuthoritie>();
                }
                actionAuthoritieList.addAll((List<ActionAuthoritie>)roleAuthoritieMap.get(MapKey.MAP_KEY_ACTION));

                //设置菜单
                List<PageAuthoritie> menuPageAuthoritieList = loginUser.getPageList();
                if(null == menuPageAuthoritieList){
                    menuPageAuthoritieList = new ArrayList<PageAuthoritie>();
                }
                menuPageAuthoritieList.addAll((List<PageAuthoritie>)roleAuthoritieMap.get(MapKey.MAP_KEY_MENU));

                //设置页面
                List<PageAuthoritie> pageAuthoritieList = loginUser.getMenuPageList();
                if(null == pageAuthoritieList){
                    pageAuthoritieList = new ArrayList<PageAuthoritie>();
                }
                pageAuthoritieList.addAll((List<PageAuthoritie>)roleAuthoritieMap.get(MapKey.MAP_KEY_PAGE));
            }
        }
        return loginUser;
    }
    /**
     * 登录用户名密码验证(供应商用)
     *
     * @param loginId 登录ID
     * @param loginUser 登录用户
     * @return 登陆用户信息
     */
    @Transactional
    public LoginUser loginForSupplier(String loginId, LoginUser loginUser) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loginId", loginId);
        paramMap.put("status", '2');
        //设置员工编码
        paramMap.put("emplId", loginUser.getEmplId());
        //根据员工编号获得角色列表
        List<LoginRole> roleList = this.findList(paramMap, SqlId.FIND_ROLE_LIST_SQL_ID);

        if (!CollectionUtils.isEmpty(roleList)) {
            for (LoginRole loginRole : roleList) {
                //角色编码
                paramMap.put("roleNo", ConfigManager.getSupplierRoleId());
                //是否有效
                paramMap.put("isAvailable", CodeMasterConst.IsAvailable.AVAILABLE);
                //根据角色获得可以访问的页面和资源信息
                List<LoginRoleAuthority> roleAuthoritieList = this.findList(paramMap, SqlId.FIND_ROLE_AUTHORITY_SQL_ID);

                Map<String,List<?>> roleAuthoritieMap = this.createRoleAuthority(roleAuthoritieList);
                //设置Action
                List<ActionAuthoritie> actionAuthoritieList = loginUser.getActionList();
                if(null == actionAuthoritieList){
                    actionAuthoritieList = new ArrayList<ActionAuthoritie>();
                }
                actionAuthoritieList.addAll((List<ActionAuthoritie>)roleAuthoritieMap.get(MapKey.MAP_KEY_ACTION));

                //设置菜单
                List<PageAuthoritie> menuPageAuthoritieList = loginUser.getPageList();
                if(null == menuPageAuthoritieList){
                    menuPageAuthoritieList = new ArrayList<PageAuthoritie>();
                }
                menuPageAuthoritieList.addAll((List<PageAuthoritie>)roleAuthoritieMap.get(MapKey.MAP_KEY_MENU));

                //设置页面
                List<PageAuthoritie> pageAuthoritieList = loginUser.getMenuPageList();
                if(null == pageAuthoritieList){
                    pageAuthoritieList = new ArrayList<PageAuthoritie>();
                }
                pageAuthoritieList.addAll((List<PageAuthoritie>)roleAuthoritieMap.get(MapKey.MAP_KEY_PAGE));
            }
        }
        return loginUser;
    }

    /**
     * 设置操作和页面权限
     * @param roleAuthorityList 角色权限列表
     * @return Map操作
     */
    private Map<String,List<?>> createRoleAuthority(List<LoginRoleAuthority> roleAuthorityList){
        Map<String,List<?>> retMap = new HashMap<String,List<?>>();
        List<ActionAuthoritie> actionAuthoritieList = new ArrayList<ActionAuthoritie>();
        List<PageAuthoritie> menuPageAuthoritieList = new ArrayList<PageAuthoritie>();
        List<PageAuthoritie> pageAuthoritieList = new ArrayList<PageAuthoritie>();
        retMap.put(MapKey.MAP_KEY_ACTION,actionAuthoritieList);
        retMap.put(MapKey.MAP_KEY_MENU,menuPageAuthoritieList);
        retMap.put(MapKey.MAP_KEY_PAGE,pageAuthoritieList);
        for (LoginRoleAuthority roleAuthority : roleAuthorityList){
            if(!StringUtil.isEmpty(roleAuthority.getActionCode())){
                ActionAuthoritie actionAuthoritie = new ActionAuthoritie();
                actionAuthoritie.setActionCode(roleAuthority.getActionCode());
                actionAuthoritie.setActionName(roleAuthority.getActionName());
                actionAuthoritie.setActionType(roleAuthority.getActionType());
                actionAuthoritie.setPageCode(roleAuthority.getActionPageCode());
                actionAuthoritieList.add(actionAuthoritie);
                continue;
            }
            String pageCode = roleAuthority.getPageCode();
            String pageName = roleAuthority.getPageName();
            String pageUrl = roleAuthority.getPageUrl();
            PageAuthoritie pageAuthoritie = new PageAuthoritie();
            pageAuthoritie.setPageCode(pageCode);
            pageAuthoritie.setPageName(pageName);
            pageAuthoritie.setPageUrl(pageUrl);
            if(!StringUtil.isEmpty(roleAuthority.getPageCode())&&"0".equals(roleAuthority.getIsMenu())){
                menuPageAuthoritieList.add(pageAuthoritie);
            }else{
                pageAuthoritieList.add(pageAuthoritie);
            }
        }
        return retMap;

    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
