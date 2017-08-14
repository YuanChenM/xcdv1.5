package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdNormsStd;
import com.msk.product.logic.PD141111Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品包装信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping("PD141111")
public class PD141111Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PD141111Controller.class);
    @Autowired
    private PD141111Logic pd141111Service;

    /**
     * 进入列表页面*
     *
     * @param model      model
     * @param standardId 产品标准ID
     * @param classesCode 产品类别名称
     * @param breedCode 产品品种编码
     * @param featureCode 产品特征编码
     * @param yesOrNo 页面跳转标识
     * @return "pd/PD141111"页面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(required = false) String standardId,
                       @RequestParam(value = "classesCode",required = false) String classesCode, @RequestParam(value ="breedCode", required = false) String breedCode,@RequestParam(value ="featureCode", required = false) String featureCode,
                       @RequestParam(value = "yesOrNo", required = false) String yesOrNo) {
        logger.debug("查询所有的产品类数据");
        model.addAttribute("standardId", standardId);

        if(breedCode==""||breedCode==null)model.addAttribute("breedCode",null);
        else model.addAttribute("breedCode",breedCode);

        if(classesCode==""||classesCode==null)model.addAttribute("classesCode",null);
        else model.addAttribute("classesCode",classesCode);

        if(featureCode==""||featureCode==null)model.addAttribute("featureCode",null);
        else model.addAttribute("featureCode",featureCode);

        model.addAttribute("yesOrNo",yesOrNo);
        return "pd/PD141111";
    }

    /**
     * 查询列表数据*
     *
     * @param pageParam 分页参数
     * @return PageResult
     * @author pxg
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PdNormsStd> search(BasePageParam pageParam) {
        logger.debug("产品包装检索");
        DbUtils.buildLikeCondition(pageParam, "normsSuttle", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsError", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsNumber", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsSize", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsTexture", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsOut", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsKg", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsOutSize", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsOutTexture", DbUtils.LikeMode.FRONT);
        return pd141111Service.findPdNormsPageList(pageParam);
    }
}
