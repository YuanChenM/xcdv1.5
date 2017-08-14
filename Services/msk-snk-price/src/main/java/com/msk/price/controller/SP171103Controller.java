package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171103Bean;
import com.msk.price.logic.SP171103Logic;
import com.msk.price.utils.CommRestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 供应商申报情况一览控制层
 * Created by xu_wei on
 */
@Controller
@RequestMapping("SP171103")
public class SP171103Controller extends BaseController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SP171103Controller.class);

    @Autowired
    private SP171103Logic SP171103Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model) {

        logger.debug("供应商申报情况一览初始化");
        BasePageParam pageParam = new BasePageParam();

        //物流下拉框
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());
        List<SP171103Bean> demandYearMonthlist = SP171103Logic.publishYmList(pageParam);
        if(CollectionUtils.isNotEmpty(demandYearMonthlist)){
            model.addAttribute("demandStartDate", demandYearMonthlist.get(NumberConst.IntDef.INT_ZERO).getDemandStartDate());
            model.addAttribute("demandEndDate", demandYearMonthlist.get(NumberConst.IntDef.INT_ZERO).getDemandEndDate());
            model.addAttribute("demandYearMonthlist", demandYearMonthlist);
            model.addAttribute("demandLimitedDateShow", demandYearMonthlist.get(NumberConst.IntDef.INT_ZERO).getDemandLimitedDateShow());
            model.addAttribute("demandYearMonthShow", demandYearMonthlist.get(NumberConst.IntDef.INT_ZERO).getDemandYearMonthShow());
            model.addAttribute("nowMonth", DateTimeUtil.getLastMonth(demandYearMonthlist.get(NumberConst.IntDef.INT_ZERO).getDemandYearMonth())) ;
        }
        return "sp/SP171103";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171103Bean> search(BasePageParam pageParam) {
        logger.debug("供应商申报情况一览页面初始化");

        DbUtils.buildLikeCondition(pageParam, "pdName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "scientificName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "localName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "machiningName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "breedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "pdCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "slName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "featureName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(pageParam, "weightName", DbUtils.LikeMode.PARTIAL);

        String lgcsCode = StringUtil.toSafeString(pageParam.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(StringConst.COMMA);
            pageParam.getFilterMap().put("lgcsCodes", lgcsCodes);
        }

        String gradeCode = StringUtil.toSafeString(pageParam.getFilterMap().get("gradeCode"));
        if (!StringUtil.isNullOrEmpty(gradeCode)) {
            String[] gradeCodes = gradeCode.split(StringConst.COMMA);
            pageParam.getFilterMap().put("gradeCodes", gradeCodes);
        }

        String isConfirm = StringUtil.toSafeString(pageParam.getFilterMap().get("isConfirm"));

        if (!StringUtil.isNullOrEmpty(isConfirm)) {

            if(StringUtil.contains(isConfirm, SystemConst.DelFlg.ENABLE)){
                if(StringUtil.equals(SystemConst.DelFlg.ENABLE,isConfirm)){
                    pageParam.getFilterMap().put("type", NumberConst.IntDef.INT_ONE);
                }else{
                    pageParam.getFilterMap().put("type", NumberConst.IntDef.INT_THREE);
                }
            } else {
                pageParam.getFilterMap().put("type", NumberConst.IntDef.INT_TWO);
            }
            String[] isConfirms = isConfirm.split(StringConst.COMMA);
            pageParam.getFilterMap().put("isConfirms", isConfirms);
        }
        return SP171103Logic.findSP171103BeansList(pageParam);
    }
}
