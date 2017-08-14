package com.msk.price.controller;

import com.hoperun.core.consts.NumberConst;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171110Bean;
import com.msk.price.bean.SP171110Param;
import com.msk.price.logic.SP171117Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/6/30.
 */
@Controller
@RequestMapping("SP171117")
public class SP171117Controller extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(SP171117Controller.class);

    @Autowired
    private SP171117Logic sp171117Logic;

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(Model model,SP171110Param sp171110Param){
        String defaultWayGrade = "5";//默认可编辑通道
        logger.info("采购员价格详细初始化开始");
        super.setCommonParam(sp171110Param);
        List<SP171110Bean> SP171110BeanList =  sp171117Logic.search(sp171110Param);
        for (SP171110Bean sp171110Bean :SP171110BeanList){
            if (sp171110Bean.getWayGradePercent().compareTo(new BigDecimal(NumberConst.IntDef.INT_HUNDRED)) == 0) {
                defaultWayGrade = sp171110Bean.getWayGradeCode();
            }
        }
        model.addAttribute("defaultWayGrade", defaultWayGrade);
        model.addAttribute("sp171110Param",sp171110Param);
        model.addAttribute("SP171110BeanList",SP171110BeanList);
        model.addAttribute("overDataFlag",sp171110Param.getFilterMap().get("overDataFlag"));
        return "sp/SP171117";
    }

    /**
     * 保存采购员价格申报
     * @param param
     * @param sp171110Param
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public @ResponseBody void save(SP171110Bean param,SP171110Param sp171110Param) {
        logger.debug("采购员价格申报保存");
        // 设置共通字段
        super.setCommonParam(param);
        this.sp171117Logic.modifyPrice(param, sp171110Param);
    }
}
