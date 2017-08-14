package com.msk.common.controller;

import com.msk.common.base.BaseController;
import com.msk.common.bean.FavoritesBean;
import com.msk.common.bean.LoginUser;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.common.dao.RedisExtendsUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by rong_ting on 2016/8/24.
 */
@Controller
@RequestMapping("favorites")
public class FavoritesController extends BaseController {

    /** logger */
    private static Logger logger = getLogger(FavoritesController.class);

    /** 调用redis */
    @Autowired
    RedisExtendsUtils redisExtendsUtils;

    /**
     * 收藏夹功能
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(FavoritesBean bean, String sysCode){
        LoginUser loginUser = this.getLoginUser();
        String loginId = loginUser.getEmplId();

        // redis DB
        redisExtendsUtils.setDatabase(RedisDataBaseDef.FAVORITES_DB);

        if(redisExtendsUtils.exist(loginId + sysCode)) {
            Map<String,String> mapGet = redisExtendsUtils.getRedisMapValue(loginId + sysCode);
            if(!mapGet.containsKey(bean.getPageUrl())) {
                redisExtendsUtils.saveRedisMapOne(loginId + sysCode,bean.getPageUrl(),bean.getPageName());
            } else {
                return "HAS";
            }
        } else {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(bean.getPageUrl(),bean.getPageName());
            redisExtendsUtils.saveRedisMap(loginId + sysCode, map);
        }

        return "SUCCESS";
    }

    /**
     * 收藏夹删除
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String pageUrl, String sysCode){
        LoginUser loginUser = this.getLoginUser();
        String loginId = loginUser.getEmplId();

        // redis DB
        redisExtendsUtils.setDatabase(RedisDataBaseDef.FAVORITES_DB);

        if(redisExtendsUtils.exist(loginId + sysCode)) {
            redisExtendsUtils.removeRedisMapItems(loginId + sysCode, pageUrl);
        }

        return "SUCCESS";
    }
}
