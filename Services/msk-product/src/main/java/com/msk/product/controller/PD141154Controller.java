package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PdTcProviderPackageParam;
import com.msk.product.logic.PD141154Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 正式上线添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141154")
public class PD141154Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PD141154Controller.class);
    @Autowired
    private PD141154Logic pd141154Logic;
    /**
     * 实例化页面
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,BaseParam param) {
        PdTcProviderPackageParam packageParam=new PdTcProviderPackageParam();
        packageParam.setClassestreeCode(StringUtil.toSafeString(param.getFilterMap().get("classesCode")));
        packageParam.setProviderCode(StringUtil.toSafeString(param.getFilterMap().get("providerCode")));
        packageParam.setMachiningCode(StringUtil.toSafeString(param.getFilterMap().get("machiningCode")));
        packageParam.setBreedCode(StringUtil.toSafeString(param.getFilterMap().get("breedCode")));
        packageParam.setFeatureCode(StringUtil.toSafeString(param.getFilterMap().get("featureCode")));
        packageParam.setWeightCode(StringUtil.toSafeString(param.getFilterMap().get("weightCode")));
        model.addAttribute("packageParam",packageParam);
        return "pd/PD141154";
    }

    /**
     * 实例化页面
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<PdTcProviderPackageParam> search(BasePageParam pageParam) {
        logger.debug("产品包装检索");
        DbUtils.buildLikeCondition(pageParam, "providerCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "classesCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "machiningCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "breedCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "featureCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "weightCode", DbUtils.LikeMode.FRONT);
        return pd141154Logic.queryProvider(pageParam);
    }
}
