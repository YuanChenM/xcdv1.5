package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.MskSlInfoServiceParam;
import com.msk.price.bean.SP171105Bean;
import com.msk.price.logic.SP171105Logic;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * 供应商待申报产品一览
 *
 * @author ni_shaotang
 */
@Controller
@RequestMapping("SP171105")
public class SP171105Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171105Controller.class);

    @Autowired
    private SP171105Logic SP171105Logic;

    /**
     * 供应商待申报产品一览初始化
     *
     * @param model model
     * @return SP171105
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(BasePageParam pageParam, Model model) {
        logger.debug("供应商待申报产品一览画面初始化");

        model.addAttribute("param", pageParam);

        //物流区信息
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());

        //周期信息
        List<SP171105Bean> demandYearMonthList = SP171105Logic.publishYmList(pageParam);
        model.addAttribute("demandYearMonthList", demandYearMonthList);
        if (null != demandYearMonthList && demandYearMonthList.size() > 0) {//添加默认显示周期
            SP171105Bean bean = demandYearMonthList.get(NumberConst.IntDef.INT_ZERO);

            model.addAttribute("demandStartDate", bean.getStartDate());
            model.addAttribute("demandEndDate", bean.getEndDate());
            model.addAttribute("demandLimitedDateShow", bean.getDemandLimitedDateShow());
            model.addAttribute("demandYearMonthShow", bean.getDemandYearMonthShow());
        }


        return "sp/SP171105";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SP171105Bean> search(BasePageParam param, Model model) {
        logger.debug("供应商待申报产品一览检索");
        DbUtils.buildLikeCondition(param, "pdTypeCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "pdName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "machiningName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "breedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "featureName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "weight", DbUtils.LikeMode.PARTIAL);
        //多地区条件设置
        String lgcsCode = StringUtil.toSafeString(param.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(",");
            param.getFilterMap().put("lgcsCodes", lgcsCodes);
        }
        //多等级条件设计
        String gradeCode = StringUtil.toSafeString(param.getFilterMap().get("gradeCode"));
        if (!StringUtil.isNullOrEmpty(gradeCode)) {
            String[] gradeCodes = gradeCode.split(",");
            param.getFilterMap().put("gradeCodes", gradeCodes);
        }
        //申报状态条件设置
        String isConfirm = StringUtil.toSafeString(param.getFilterMap().get("isConfirm"));
        if (!StringUtil.isNullOrEmpty(isConfirm)) {
            String[] isConfirms = isConfirm.split(",");
            param.getFilterMap().put("isConfirms", isConfirms);
        }
        //获取卖家slcode
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        if (null != slInfo) {
            String slCode = slInfo.getSlCode();
            param.getFilterMap().put("slCode", slCode);
        }
        return SP171105Logic.getPageResultByInterface(param, slInfo);
    }

}
