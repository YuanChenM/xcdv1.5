package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141402Bean;
import com.msk.product.logic.PD141402Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * controller 卖家产品一览列表查询
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "PD141402")
public class PD141402Controller extends BaseController {
    @Autowired
    private PD141402Logic pd141402Logic;

    /**
     * 实例化页面
     *
     * @return 卖家产品信息页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    private String init(Model model, @RequestParam(value = "slCode", required = false) String slCode,
                        @RequestParam(value = "slCodeDis", required = false) String slCodeDis) {
        if (StringUtils.isNotBlank(slCode)) model.addAttribute("slCode", slCode);
        if (StringUtils.isNotBlank(slCodeDis)) model.addAttribute("slCodeDis", slCodeDis);
        return "pd/PD141402";
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
    PageResult<PD141402Bean> search(BasePageParam basePageParam, @RequestParam(value = "slCodeDis", required = false)String slCodeDis) {
        DbUtils.buildLikeCondition(basePageParam, "prodEpName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "brandEpName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdClassesName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "machiningName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdBreedName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdFeatureName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "weightName", DbUtils.LikeMode.FRONT);
        basePageParam.setFilter("delFlg", "0");
        PageResult<PD141402Bean> pageResult = this.pd141402Logic.findPage(basePageParam, PD141402Bean.class);
        for(PD141402Bean beans:pageResult.getData()){
            beans.setSlCodeDis(slCodeDis);
        }
        if(pageResult.getData().size()<= NumberConst.IntDef.INT_ZERO){
            PageResult result = new PageResult();
            List<PD141402Bean> list = new ArrayList<PD141402Bean>();
            result.setData(list);
            return result;
        }
        return pageResult;
    }
}