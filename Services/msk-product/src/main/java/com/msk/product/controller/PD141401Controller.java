package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141401Bean;
import com.msk.product.bean.PD141402Bean;
import com.msk.product.logic.PD141401Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * controller 卖家一览列表查询
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "PD141401")
public class PD141401Controller extends BaseController {
    @Autowired
    private PD141401Logic pd141401Logic;

    /**
     * 实例化页面
     *
     * @return 卖家一览画面显示
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init() {
        return "/pd/PD141401";
    }

    /**
     * 查询审批审核列表
     *
     * @param basePageParam basePageParam
     * @return 信息
     * @author gyh
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PD141401Bean> search(BasePageParam basePageParam) {
        DbUtils.buildLikeCondition(basePageParam, "slCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slShowName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slTel", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slContact", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "cityName", DbUtils.LikeMode.FRONT);
        String slMainClass = StringUtil.toSafeString(basePageParam.getFilterMap().get("slMainClass"));
        if (!StringUtil.isNullOrEmpty(slMainClass)) {
            String[] slMainClasss = slMainClass.split(",");
            basePageParam.getFilterMap().put("slMainClasss", slMainClasss);
        }
        basePageParam.setFilter("delFlg", "0");
        PageResult<PD141401Bean> beans =   this.pd141401Logic.findPage(basePageParam, PD141401Bean.class);
        if(beans.getData().size()<= NumberConst.IntDef.INT_ZERO){
            PageResult result = new PageResult();
            List<PD141402Bean> list = new ArrayList<PD141402Bean>();
            result.setData(list);
            return result;
        }
        return beans;
    }
}