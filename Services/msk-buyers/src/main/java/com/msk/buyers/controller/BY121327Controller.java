package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.BY121303Bean;
import com.msk.buyers.logic.BY121327Logic;
import com.msk.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("BY121327")
public class BY121327Controller extends BaseController {

    @Autowired
    private BY121327Logic  by121327Logic;

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BY121327Controller.class);
    
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model) {
        logger.debug("买家账号信息表列表画面初始化");
        return "buyers/BY121327";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121303Bean> search(BasePageParam basePageParam) {
        logger.info("买家账号信息表列表画面查询");
        DbUtils.buildLikeCondition(basePageParam, "buyerCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "buyerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "accountName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "telNo", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(basePageParam, "buyerAddr", DbUtils.LikeMode.PARTIAL);
        String registerSource = StringUtil.toSafeString(basePageParam.getFilterMap().get("registerSource"));
        String[] registerSources = null;
        if (!StringUtil.isNullOrEmpty(registerSource)){
            registerSources = registerSource.split(",");
        }
        basePageParam.getFilterMap().put("registerSource", registerSources);

        return by121327Logic.findPage(basePageParam,BY121303Bean.class);
    }
    
 
}
