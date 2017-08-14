package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.*;
import com.msk.price.logic.SP171106Logic;
import com.msk.price.logic.SP171108Logic;
import com.msk.price.logic.SP171196Logic;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 供应商数量申报详细
 * Created by sun_jiaju on 2016/5/9.
 */
@Controller
@RequestMapping("SP171106")
public class SP171106Controller extends BaseController {
    /**
     * logger *
     */
    private static Logger logger = LoggerFactory.getLogger(SP171106Controller.class);
    @Autowired
    private SP171106Logic sp171106Logic;
    @Autowired
    private SP171108Logic SP171108Logic;
    @Autowired
    private SP171196Logic sP171196Logic;
    // 大宗订单通道平衡系数
    private static String bulkOrder = "1";
    // 大额订单通道平衡系数
    private static String largeOrder = "2";
    // 标准订单通道平衡系数
    private static String standardOrder = "3";

    /**
     * 加载初始页面
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SP171106Param param, Model model) throws Exception {
        logger.debug("供应商数量申报详细初始化");
        //获取卖家slcode
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        if (null != slInfo) {
            String slCode = slInfo.getSlCode();
            param.setSlCode(slCode);
            param.setSlAccount(slInfo.getSlAccount());
            param.setEpId(slInfo.getEpId());
            param.setEpName(slInfo.getEpName());
        }
        if (param.getDemandId() != 0) {
            sp171106Logic.queryDemandInfo(param);
        }else {
            sp171106Logic.queryPdInfo(param);
        }
        //周期
        String demandMonth = param.getDemandYearMonth().substring(5, 6);
        Date demandTime = DateTimeUtil.parseDate(param.getDemandYearMonth(), DateTimeUtil.FORMAT_YEAR_MONTH);
        Date startTime = DateTimeUtil.firstDay(demandTime);
        Date endTime = DateTimeUtil.lastDay(demandTime);
        String demandStartDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, startTime);
        String demandEndDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,endTime);
        param.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);
        // 处理参数
        Boolean canSetFlg = true;
        String lastMonth = DateTimeUtil.getLastMonth(param.getDemandYearMonth());
        Date nowDate = DateTimeUtil.getCustomerDate();
        Date lastDate = DateTimeUtil.lastDay(nowDate);
        int lastDateStr = StringUtil.toInteger(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DD, lastDate));
        //获取可申报时间
        SP171196Param pm = new SP171196Param();
        SP171196Bean rbean = sP171196Logic.findConstRatio(pm);
        String startDay = StringUtil.toString(rbean.getStartTime() > lastDateStr ? lastDateStr : rbean.getStartTime());
        String endDay = StringUtil.toString(rbean.getEndTime() > lastDateStr ? lastDateStr : rbean.getEndTime());
        String startDate = lastMonth + startDay;
        String endDate = lastMonth + endDay;
        lastMonth = lastMonth.substring(0,4)+"年"+lastMonth.substring(5);
        param.setDemandLimitedDateShow(lastMonth + "月" + startDay + "日 - " + lastMonth + "月" + endDay + "日");

        Date setStartDate = DateTimeUtil.parseDate(startDate, DateTimeUtil.FORMAT_YYYYMMDD);
        Date setEndDate = DateTimeUtil.parseDate(endDate, DateTimeUtil.FORMAT_YYYYMMDD);
        Date setEndDateAdd = DateTimeUtil.addDay(setEndDate, 1);//因为结束时间只到天，所以加一天再判断
        //判断是否可申报
        if (nowDate.compareTo(setStartDate) < NumberConst.IntDef.INT_ZERO || nowDate.compareTo(setEndDateAdd) > NumberConst.IntDef.INT_ZERO) {
            canSetFlg = false;
        }
        param.setCanSetFlg(canSetFlg);

        // listtable
        // 分配平衡管理系数
        BigDecimal HUNDRED = new BigDecimal(NumberConst.IntDef.INT_HUNDRED);
        List<SP171106Bean> constRatioList = sp171106Logic.findConstRatio(param);
        BigDecimal wayRatio1 = null;
        BigDecimal wayRatio2 = null;
        BigDecimal wayRatio3 = null;
        for (SP171106Bean scr : constRatioList) {
            if (bulkOrder.equals(scr.getRatioTypeDetail())) {
                wayRatio1 = DecimalUtil.divide(scr.getRatioTypeDetailVal(), HUNDRED);
                param.setWayRatio1(DecimalUtil.round(scr.getRatioTypeDetailVal(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            } else if (largeOrder.equals(scr.getRatioTypeDetail())) {
                wayRatio2 = DecimalUtil.divide(scr.getRatioTypeDetailVal(), HUNDRED);
                param.setWayRatio2(DecimalUtil.round(scr.getRatioTypeDetailVal(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            } else if (standardOrder.equals(scr.getRatioTypeDetail())) {
                wayRatio3 = DecimalUtil.divide(scr.getRatioTypeDetailVal(), HUNDRED);
                param.setWayRatio3(DecimalUtil.round(scr.getRatioTypeDetailVal(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            }
        }

        //发布总数量和等级占比
        SP171106Bean demandPublish = sp171106Logic.findDemandPublish(param);
        BigDecimal publishTotalNum = demandPublish.getPublishTotalNum();
        BigDecimal gradeRatio1 = DecimalUtil.divide(demandPublish.getGradeRatio1(), HUNDRED);
        BigDecimal gradeRatio2 = DecimalUtil.divide(demandPublish.getGradeRatio2(), HUNDRED);
        BigDecimal gradeRatio3 = DecimalUtil.divide(demandPublish.getGradeRatio3(), HUNDRED);

        param.setGradeRatio1(DecimalUtil.round(demandPublish.getGradeRatio1(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
        param.setGradeRatio2(DecimalUtil.round(demandPublish.getGradeRatio2(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
        param.setGradeRatio3(DecimalUtil.round(demandPublish.getGradeRatio3(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
        param.setPublishTotalNum(DecimalUtil.round(publishTotalNum, NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));

        param.setA1Way3Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio1), wayRatio3, NumberConst.IntDef.INT_ZERO));
        param.setA1Way2Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio1), wayRatio2, NumberConst.IntDef.INT_ZERO));
        param.setA1Way1Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio1), wayRatio1, NumberConst.IntDef.INT_ZERO));

        param.setA2Way3Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio2), wayRatio3, NumberConst.IntDef.INT_ZERO));
        param.setA2Way2Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio2), wayRatio2, NumberConst.IntDef.INT_ZERO));
        param.setA2Way1Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio2), wayRatio1, NumberConst.IntDef.INT_ZERO));

        param.setA3Way3Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio3), wayRatio3, NumberConst.IntDef.INT_ZERO));
        param.setA3Way2Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio3), wayRatio2, NumberConst.IntDef.INT_ZERO));
        param.setA3Way1Num(DecimalUtil.multiplyWithNScale(DecimalUtil.multiply(publishTotalNum, gradeRatio3), wayRatio1, NumberConst.IntDef.INT_ZERO));

        param.setA1Num(DecimalUtil.multiplyWithNScale(publishTotalNum, gradeRatio1, NumberConst.IntDef.INT_ZERO));
        param.setA2Num(DecimalUtil.multiplyWithNScale(publishTotalNum, gradeRatio2, NumberConst.IntDef.INT_ZERO));
        param.setA3Num(DecimalUtil.multiplyWithNScale(publishTotalNum, gradeRatio3, NumberConst.IntDef.INT_ZERO));

        param.setWay1Num(DecimalUtil.multiplyWithNScale(publishTotalNum, wayRatio1, NumberConst.IntDef.INT_ZERO));
        param.setWay2Num(DecimalUtil.multiplyWithNScale(publishTotalNum, wayRatio2, NumberConst.IntDef.INT_ZERO));
        param.setWay3Num(DecimalUtil.multiplyWithNScale(publishTotalNum, wayRatio3, NumberConst.IntDef.INT_ZERO));

        if (param.getDemandId() != NumberConst.IntDef.INT_ZERO) {
            SP171106Bean bean = sp171106Logic.findDemandApply(param);
            param.setPassNum(DecimalUtil.round(bean.getPassNum(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            param.setApplyNum(DecimalUtil.round(bean.getApplyNum(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            param.setIsConfirm(bean.getIsConfirm());
            param.setVer(bean.getVer());
        }
        model.addAttribute("sp171106Param", param);
        return "sp/SP171106";
    }

    /**
     * SAVE
     *
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(SP171106Param param, Model model) throws Exception {
        logger.debug("供应商申报数量");
        super.setCommonParam(param);
        //获取卖家slcode
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        if (null != slInfo) {
            String slCodeDis = slInfo.getSlCodeDis();
            param.setSlId(slCodeDis);
        }
        this.sp171106Logic.update(param);
        return this.init(param, model);
    }

    /**
     * 数量申报历史一览
     *
     * @param basePageParam
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171108Bean> search(BasePageParam basePageParam, String demandId) {
        logger.info("查询数据库");
        basePageParam.getFilterMap().put("type", "type");
        basePageParam.getFilterMap().put("demandId", demandId);
        return SP171108Logic.findSP171108BeansList(basePageParam);
    }
}
