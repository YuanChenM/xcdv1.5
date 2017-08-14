package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141113Bean;
import com.msk.product.logic.PD141113Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 标准标准档案卡信息
 *
 * @author jiang_nan
 */
@Controller
@RequestMapping("PD141113")
public class PD141113Controller extends BaseController {
    @Autowired
    private PD141113Logic pd141113Logic;

    /**
     * 实例化页面
     *
     * @param pageParam pageParam
     * @param model     model
     * @param classesCode 产品类别编码
     * @param breedCode   产品品种编码
     * @param featureCode  产品特征编码
     * @param yesOrNo   页面跳转标识
     * @return 页面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(BasePageParam pageParam, Model model, @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode, @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "yesOrNo", required = false) String yesOrNo) {
        PD141113Bean pd141113Bean = new PD141113Bean();
        if(breedCode==""||breedCode==null)model.addAttribute("breedCode",null);
        else model.addAttribute("breedCode",breedCode);

        if(classesCode==""||classesCode==null)model.addAttribute("classesCode",null);
        else model.addAttribute("classesCode",classesCode);

        if(featureCode==""||featureCode==null)model.addAttribute("featureCode",null);
        else model.addAttribute("featureCode",featureCode);

        model.addAttribute("yesOrNo", yesOrNo);

        pd141113Bean.setBreedCode((String) pageParam.getFilterMap().get("breedCode"));
        pd141113Bean.setClassesCode((String) pageParam.getFilterMap().get("classesCode"));
        pd141113Bean.setFeatureCode((String) pageParam.getFilterMap().get("featureCode"));

        model.addAttribute("info", pd141113Bean);
        return "pd/PD141113";
    }

    /**
     * 分页查询数据
     *
     * @param pageParam pageParam
     * @return 分页查询数据
     * @author gyh
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PD141113Bean> search(BasePageParam pageParam) {
        DbUtils.buildLikeCondition(pageParam, "classesCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "breedCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "featureCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "classesName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "breedName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "featureName", DbUtils.LikeMode.FRONT);
        return pd141113Logic.findPage(pageParam, PD141113Bean.class);
    }

}
