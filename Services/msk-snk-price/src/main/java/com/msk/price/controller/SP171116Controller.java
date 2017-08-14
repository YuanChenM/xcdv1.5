package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import com.msk.price.bean.SP171109Bean;
import com.msk.price.logic.SP171116Logic;
import com.msk.price.utils.CommRestUtil;
import com.msk.price.utils.PriceCycleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 采购员报价一览
 * Created by zhu_kai1 on 2016/6/30.
 */
@Controller
@RequestMapping("SP171116")
public class SP171116Controller extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(SP171116Controller.class);

    @Autowired
    private SP171116Logic sp171116Logic;
    /**
     * 采购员报价一览画面初始化
     * @return
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(@RequestParam(value = "pricePeriod", required = false) String pricePeriod,Model model){
        logger.debug("采购员报价一览画面初始化");
        //物流区信息
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());
         /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  Start */
        PriceCycleResult priceCycle = getPriceCycleResult();
        if (StringUtils.isEmpty(pricePeriod)) {
            pricePeriod = priceCycle.getCycleCode();
        }

        // 根据价盘周期获取价盘周期对应的时间
        PriceCycleParam param = new PriceCycleParam();
        param.setPriceCode(pricePeriod);
        priceCycle = PriceCycleUtil.getPriceCycleByCode(param);
        /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  end */
        // 日历初期显示值
        String priceDate = priceCycle.getCycleCode().substring(0, 2) + "-" + priceCycle.getCycleCode().substring(2, 4);
        String pricecycle = priceCycle.getCycleCode().substring(4, 5);
        model.addAttribute("pricePeriod",priceCycle.getCycleCode());
        model.addAttribute("pricePeriodStart",priceCycle.getStartDateStr());
        model.addAttribute("priceDate", priceDate);
        model.addAttribute("pricecycle", pricecycle);
        model.addAttribute("pricePeriodEnd",priceCycle.getEndDateStr());
        return "sp/SP171116";
    }

    /**
     * 获取当前时间的下一个价盘周期
     * @return
     */
    private PriceCycleResult getPriceCycleResult() {
        // 根据当前系统日期获取下一个价盘周期
        PriceCycleParam priceCycleParam = new PriceCycleParam();
        Date dateNow = DateTimeUtil.getCustomerDate();
        priceCycleParam.setCurrentDate(dateNow);
        return PriceCycleUtil.getNextPriceCycle(priceCycleParam);
    }


    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171109Bean> search(BasePageParam param){
        DbUtils.buildLikeCondition(param, "pdCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "machining", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "breed", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "feature", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "weight", DbUtils.LikeMode.PARTIAL);
        /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  Start */
        String  priceDate=(String)param.getFilterMap().get("priceDate");
        String dateArr[] = priceDate.split("-");
        param.getFilterMap().put("pricePeriod",dateArr[0]+dateArr[1]+param.getFilterMap().get("pricecycle"));
        /** Modfiy:  Bug#2560 : 真实生产过程中会经常新增加产品，但是目前不支持对新产品进行当期报价   2016/9/14   BY  zhukai1  end */
        String lgcsCode = StringUtil.toSafeString(param.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(",");
            param.getFilterMap().put("lgcsCodes", lgcsCodes);
        }
        String gradeCode = StringUtil.toSafeString(param.getFilterMap().get("gradeCode"));
        if (!StringUtil.isNullOrEmpty(gradeCode)) {
            String[] gradeCodes = gradeCode.split(",");
            param.getFilterMap().put("gradeCodes", gradeCodes);
        }
        return sp171116Logic.search(param);
    }
}
