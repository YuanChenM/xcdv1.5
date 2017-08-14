package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.*;
import com.msk.price.logic.SP171104Logic;
import com.msk.price.logic.SP171108Logic;
import com.msk.price.logic.SP171196Logic;
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
 * Created by sun_jiaju on 2016/5/9.
 */
@Controller
@RequestMapping("SP171104")
public class SP171104Controller extends BaseController {
    /** logger **/
    private static Logger logger = LoggerFactory.getLogger(SP171104Controller.class);
    @Autowired
    private SP171104Logic sp171104Logic;
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
     *
     * @param param
     * @return 加载初始页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(SP171104Param param,Model model){
        logger.debug("采购员用供应商数量申报详细初始化");
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
        BigDecimal HUNDRED = new BigDecimal("100");
        List<SP171104Bean> constRatioList = sp171104Logic.findConstRatio(param);
        BigDecimal wayRatio1 = null;
        BigDecimal wayRatio2 = null;
        BigDecimal wayRatio3 = null;
        for(SP171104Bean scr : constRatioList){
            if (bulkOrder.equals(scr.getRatioTypeDetail())){
                wayRatio1 = DecimalUtil.divide(scr.getRatioTypeDetailVal(), HUNDRED);
                param.setWayRatio1(DecimalUtil.round(scr.getRatioTypeDetailVal(),NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            } else if (largeOrder.equals(scr.getRatioTypeDetail())){
                wayRatio2 = DecimalUtil.divide(scr.getRatioTypeDetailVal(), HUNDRED);
                param.setWayRatio2(DecimalUtil.round(scr.getRatioTypeDetailVal(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            } else if (standardOrder.equals(scr.getRatioTypeDetail())){
                wayRatio3 = DecimalUtil.divide(scr.getRatioTypeDetailVal(), HUNDRED);
                param.setWayRatio3(DecimalUtil.round(scr.getRatioTypeDetailVal(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
            }
        }

        // 发布总数量和等级占比
        param.setFilterObject("pdTypeCode",param.getPdCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_NINE));
        SP171104Bean demandPublish = sp171104Logic.findDemandPublish(param);
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

        SP171104Bean bean = sp171104Logic.findDemandApply(param);
        param.setPassNum(DecimalUtil.round(bean.getPassNum(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
        param.setApplyNum(DecimalUtil.round(bean.getApplyNum(), NumberConst.IntDef.INT_ZERO, BigDecimal.ROUND_HALF_UP));
        param.setVer(bean.getVer());
        param.setEpName(bean.getEpName());
        param.setIsConfirm(bean.getIsConfirm());
        model.addAttribute("sp171104Param", param);
        return "sp/SP171104";
    }
    /**
     *  SAVE
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(SP171104Param param, Model model){
        logger.debug("采购员分配数量");
        super.setCommonParam(param);
        this.sp171104Logic.updateDemandApply(param);
        return this.init(param, model);
    }

    /**
     * 数量申报历史一览
     * @param basePageParam
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171108Bean> search(BasePageParam basePageParam,String demandId) {
        logger.info("查询数据库");
        basePageParam.getFilterMap().put("type", "type");
        basePageParam.getFilterMap().put("demandId",demandId);
        return SP171108Logic.findSP171108BeansList(basePageParam);
    }

    /**
     * 获取入围卖家的销售量
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "searchSeller",method = RequestMethod.POST)
    public  String searchSeller(SP171104Param param,Model model){
        logger.debug("获取入围卖家的销售量");
        model.addAttribute("sellerList", this.sp171104Logic.searchSeller(param));
        return "sp/SP171115";
    }
}

