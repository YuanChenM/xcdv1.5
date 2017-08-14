package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.*;
import com.msk.price.logic.SP171112Logic;
import com.msk.price.utils.CommRestUtil;
import com.msk.price.utils.PriceCycleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成价盘一览采购员
 *
 * @author zhou_ling
 */
@Controller
@RequestMapping("SP171112")
public class SP171112Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171112Controller.class);

    @Autowired
    private SP171112Logic sp171112Logic;

    /**
     * 初始化页面
     *
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(@RequestParam(value = "pricePeriod", required = false) String pricePeriod, Model model) {


        // 获取登录信息（登录类型）
        LoginUser loginUser = getLoginUser();
        String userType = loginUser.getUserType();

        // 根据当前系统日期获取当天价盘周期
        PriceCycleParam param = new PriceCycleParam();
        Date dateNow = DateTimeUtil.getCustomerDate();
        param.setCurrentDate(dateNow);
        PriceCycleResult priceCycle = PriceCycleUtil.getPriceCycle(param);
        if (StringUtils.isEmpty(pricePeriod)) {
            pricePeriod = priceCycle.getCycleCode();
        }
        // 根据价盘周期获取价盘周期对应的时间
        param.setPriceCode(pricePeriod);
        priceCycle = PriceCycleUtil.getPriceCycleByCode(param);
        String startDate = priceCycle.getStartDateStr();
        String endDate = priceCycle.getEndDateStr();
        String beforePeriodTime = startDate.substring(0, 4) + "年" + startDate.substring(4, 6) + "月" + startDate.substring(6, 8) + "日" + "-" + endDate.substring(0, 4) + "年" + endDate.substring(4, 6) + "月" + endDate.substring(6, 8) + "日";
        String lastPricePeriod = priceCycle.getCycleCode();
        //下一个价盘周期信息
        PriceCycleResult priceNextCycle = PriceCycleUtil.getNextPriceCycle(param);
        String priceNext = priceNextCycle.getCycleCode();
        // 日历初期显示值
        String priceDate = priceNext.substring(0, 2) + "-" + priceNext.substring(2, 4);
        String pricecycle = priceNext.substring(4, 5);
        //物流区信息
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());
        model.addAttribute("userType", userType);
        model.addAttribute("priceDate", priceDate);
        model.addAttribute("pricecycle", pricecycle);
        model.addAttribute("pricePeriod", priceNext);
        model.addAttribute("beforePeriodTime", beforePeriodTime);
        model.addAttribute("lastPricePeriod", lastPricePeriod);
        model.addAttribute("pricePeriodStart", startDate);
        model.addAttribute("pricePeriodEnd", endDate);
        logger.debug("生成价盘一览");
        return "sp/SP171112";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SP171112Bean> search(BasePageParam param, @RequestParam(value = "pricePeriod", required = false) String pricePeriod, @RequestParam(value = "userType", required = false) String userType) {
        logger.debug("价盘查询");
        //调用pdTypeCode接口
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        // 获得供应商的产品信息
        List<MskSellerServiceParam> pdTypeCodeList = CommRestUtil.getPdResponse(slInfo);
        param.setFilterObject("pdTypeCodeList", pdTypeCodeList);
        // 获得卖家登录类型
        param.setFilter("pricePeriod", pricePeriod);
        param.setFilter("userType", userType);
        DbUtils.buildLikeCondition(param, "pdName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "machiningName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "featureName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "breedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "weightName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "pdCode", DbUtils.LikeMode.PARTIAL);
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
        PageResult<SP171112Bean> result = sp171112Logic.findPage(param, SP171112Bean.class);
        return result;
    }

    /**
     * 根据价盘编号获取填报时间
     *
     * @param pricePeriod
     * @return
     */
    @RequestMapping(value = "getLastDate",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String getLastDate(@RequestParam(value = "pricePeriod", required = false) String pricePeriod) {
        PriceCycleParam param = new PriceCycleParam();
        Date dateNow = DateTimeUtil.getCustomerDate();
        param.setCurrentDate(dateNow);
        PriceCycleResult priceCycle = PriceCycleUtil.getPriceCycle(param);
        if (null != pricePeriod && pricePeriod.length() == 5) {
            param.setPriceCode(pricePeriod);
        } else {
            pricePeriod = priceCycle.getCycleCode();
        }
        // 根据价盘周期获取价盘周期对应的时间
        param.setPriceCode(pricePeriod);
        priceCycle = PriceCycleUtil.getPriceCycleByCode(param);
        // 获取上一个价盘周期的时间段
        param.setCurrentDate(priceCycle.getStartDate());
        priceCycle = PriceCycleUtil.getPrePriceCycle(param);
        String startDate = priceCycle.getStartDateStr();
        String endDate = priceCycle.getEndDateStr();
        String lastTime = startDate.substring(0, 4) + "年" + startDate.substring(4, 6) + "月" + startDate.substring(6, 8) + "日" + "-" + endDate.substring(0, 4) + "年" + endDate.substring(4, 6) + "月" + endDate.substring(6, 8) + "日";
        return lastTime;
    }

    /**
     * 删除价盘
     */
    @RequestMapping(value = "deletePricePeriod", method = RequestMethod.POST)
    @ResponseBody
    public int deletePricePeriod(SP171112Bean sp171112Bean){
        super.setCommonParam(sp171112Bean);
        return  sp171112Logic.removePricePeriod(sp171112Bean);
    }
}
