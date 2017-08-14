package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171108Bean;
import com.msk.price.bean.SP171108Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.msk.price.logic.SP171108Logic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数量申报历史控制层
 * Created by XU_WEI
 */
@Controller
@RequestMapping(value = "SP171108")
public class SP171108Controller extends BaseController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SP171108Controller.class);

    @Autowired
    private SP171108Logic SP171108Logic;

    /**
     * 初始化
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "init")
    public String init(SP171108Param param,Model model){
        logger.info("数量申报历史页面初始化");
        BaseParam param1 = new BaseParam();
        super.setCommonParam(param1);
        String loginId = param1.getActId();
        //TODO
        //地区名称
        model.addAttribute("lgcsName",param.getLgcsName());
        //品名
        model.addAttribute("pdName",param.getPdName());
        //一级分类
        model.addAttribute("classesName",param.getClassesName());
        //二级分类
        model.addAttribute("machiningName",param.getMachiningName());
        //产品编码
        model.addAttribute("pdCode",param.getPdCode());
        //产品等级
        model.addAttribute("gradeName",param.getGradeName());
        //产品等级
        model.addAttribute("featureName",param.getFeatureName());
        //供应商
        model.addAttribute("slName",param.getSlName());
        //demandId
        model.addAttribute("gradeCode",param.getGradeCode());
        //demandId
        model.addAttribute("demandId",param.getDemandId());
        //type
        model.addAttribute("type",param.getType());
        model.addAttribute("demandYearmonth", param.getDemandYearmonth());
        model.addAttribute("fillTime", param.getFillTime());
        model.addAttribute("loginId", loginId);

        return "sp/SP171108";
    }

    /**
     * 数量申报历史一览
     * @param basePageParam
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171108Bean> search(BasePageParam basePageParam) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        String loginId = param.getActId();
        basePageParam.getFilterMap().put("loginId", loginId);
        logger.info("查询数据库");
        return SP171108Logic.findSP171108BeansList(basePageParam);
    }

}
