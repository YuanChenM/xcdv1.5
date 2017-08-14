package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.seller.bean.SL24110101;
import com.msk.seller.bean.SL241104Bean;
import com.msk.seller.bean.SL241108Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 卖家产品技术标准档案卡审核
 * 
 * @author yuan_chen
 */
@Controller
@RequestMapping("SL241108")
public class SL241108Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SL241108Controller.class);

    /**
     * 初始化操作
     * 
     * @param sl241104Bean sl241104Bean
     * @param model model
     * @return PD141108
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(SL241104Bean sl241104Bean, Model model) {
        logger.debug("实际产品技术数据录入页面页面初始化");
        BasePageParam pageParam = new BasePageParam();
        pageParam.setPaging(Boolean.FALSE);
        int examineStatus = 0;
        if (StringUtil.isNullOrEmpty(sl241104Bean.getGradeCode())) {
            examineStatus = 1;
        } else {
            if (StringUtil.equals(sl241104Bean.getExamineResult(), "未审核")) {
                examineStatus = 2;
            }
        }
        model.addAttribute("examineStatus", examineStatus);
        model.addAttribute("sl241104Bean", sl241104Bean);
        return "sl/SL241108";
    }

    /**
     * 提交审核结果
     * 
     * @param param param
     * @param model model
     * @return SL241104
     */
    @RequestMapping(value = "examine",
        method = RequestMethod.POST)
    public String examine(SL241108Param param, Model model) {
        return "sl/SL241104";
    }

    /**
     * 提交审核确认结果
     * 
     * @param param param
     * @param model model
     * @return SL241104
     */
    @RequestMapping(value = "confirm",
        method = RequestMethod.POST)
    public String confirm(SL241108Param param, Model model) {
        return "sl/SL241104";
    }
}
