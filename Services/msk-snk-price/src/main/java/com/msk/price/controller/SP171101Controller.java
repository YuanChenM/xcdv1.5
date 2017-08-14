package com.msk.price.controller;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171101Bean;
import com.msk.price.bean.SP171101Param;
import com.msk.price.logic.SP171101Logic;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * SP171103Controller
 *
 * @author zhao_chen1
 *
 */
@Controller
@RequestMapping("SP171101")
public class SP171101Controller extends BaseController {
    /** logger */
    private static Logger logger = getLogger(SP171101Controller.class);

    @Autowired
    private SP171101Logic sp171101Logic;

  /*  @Autowired
    private SP171103Logic sp171103Logic;*/

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init( SP171101Param param,Model model) {
        logger.debug("供应商需求发布页面初始化");
        //物流下拉框
        model.addAttribute("lgcsinfo",CommRestUtil.getLogiticsAreaList());
        //周期
        model.addAttribute("dateList",sp171101Logic.publishYmList(param));
        //填报时间
        model.addAttribute("publicDate",sp171101Logic.publishYmList(param).get(NumberConst.IntDef.INT_ZERO).getLimitWriteDate());

        logger.debug("供应商需求发布页面初始化结束");
        return "sp/SP171101";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SP171101Bean> search(SP171101Param sp171101Param)  {
        logger.debug("供应商需求发布表");
        DbUtils.buildLikeCondition(sp171101Param, "pdTypeCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(sp171101Param, "classes", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(sp171101Param, "machining", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(sp171101Param, "breed", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(sp171101Param, "feature", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(sp171101Param, "weight", DbUtils.LikeMode.PARTIAL);
        String lgcsCode = StringUtil.toSafeString(sp171101Param.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(StringConst.COMMA);
            sp171101Param.getFilterMap().put("lgcsCodes", lgcsCodes);
        }
        return sp171101Logic.findSP171101List(sp171101Param);

    }

}
