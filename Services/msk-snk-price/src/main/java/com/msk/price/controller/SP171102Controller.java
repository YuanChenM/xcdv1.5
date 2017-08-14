package com.msk.price.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.base.BaseController;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.SP171102Bean;
import com.msk.price.bean.SP171102Param;
import com.msk.price.bean.SP171196Bean;
import com.msk.price.bean.SP171196Param;
import com.msk.price.logic.SP171102Logic;
import com.msk.price.logic.SP171196Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.*;

/**
 * SP171124Controller
 *
 * @author peng_hao
 */
@Controller
@RequestMapping("SP171102")
public class SP171102Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171102Controller.class);

    @Autowired
    private SP171102Logic sp171102Logic;
    @Autowired
    private SP171196Logic sP171196Logic;
    @Autowired
    private CommonLogic commonLogic;
    // 大宗订单通道平衡系数
    private static String bulkOrder = "1";
    // 大额订单通道平衡系数
    private static String largeOrder = "2";
    // 标准订单通道平衡系数
    private static String standardOrder = "3";

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, SP171102Param sp171102Param) {
        logger.info("供应商需求发布详细查看画面初始化");
        SP171102Bean sp171102Bean = new SP171102Bean();
        //判断价盘周期Flag
        String canSetFlg = "Y";
        sp171102Bean.setPublishId(sp171102Param.getPublishId());
        sp171102Bean.setPublishYm(sp171102Param.getPublishYm());

        //获得publishYm 转换成用于页面显示的demandYearMonthShow
        String publishYm = sp171102Param.getPublishYm();
        sp171102Param.setPublishYm(publishYm);
        Date date = DateTimeUtil.parseDate(publishYm, DateTimeUtil.FORMAT_YEAR_MONTH);
        String demandMonth = publishYm.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX);
        String demandStartDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.firstDay(date));
        String demandEndDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD, DateTimeUtil.lastDay(date));
        sp171102Bean.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);

        //判断价盘周期是否过期,如果过期将canSet置为"N"
        String lastMonth = DateTimeUtil.getLastMonth(publishYm);
        Date nowDate = DateTimeUtil.getCustomerDate();
        Date lastDate = DateTimeUtil.lastDay(nowDate);
        int lastDateStr = StringUtil.toInteger(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DD, lastDate));
        SP171196Param pm = new SP171196Param();
        SP171196Bean rbean = sP171196Logic.findConstRatio(pm);
        String startDate = lastMonth + (rbean.getStartTime() > lastDateStr ? lastDateStr : rbean.getStartTime());
        String endDate = lastMonth + (rbean.getEndTime() > lastDateStr ? lastDateStr : rbean.getEndTime());
        Date setStartDate = DateTimeUtil.parseDate(startDate, DateTimeUtil.FORMAT_YYYYMMDD);
        Date setEndDate = DateTimeUtil.parseDate(endDate, DateTimeUtil.FORMAT_YYYYMMDD);
        Date setEndDateAdd = DateTimeUtil.addDay(setEndDate,1);//因为结束时间只到天，所以加一天再判断
        if (nowDate.compareTo(setStartDate) < 0 || nowDate.compareTo(setEndDateAdd) > 0) {
            canSetFlg = "N";
        }
        sp171102Bean.setCanSetFlg(canSetFlg);

        //获得登陆ID
        BaseParam param1 = new BaseParam();
        super.setCommonParam(param1);
        String loginId = param1.getActId();

        sp171102Bean.setDemandStartDate(sp171102Param.getDemandStartDate());
        sp171102Bean.setDemandEndDate(sp171102Param.getDemandEndDate());
        sp171102Bean.setLgcsName(sp171102Param.getLgcsName());
        sp171102Bean.setLgcsCode(sp171102Param.getLgcsCode());
        sp171102Bean.setScientificName(sp171102Param.getScientificName());
        sp171102Bean.setLocalName(sp171102Param.getLocalName());
        sp171102Bean.setSalesName(sp171102Param.getSalesName());
        sp171102Bean.setPdTypeCode(sp171102Param.getPdTypeCode());
        sp171102Bean.setPdName(sp171102Param.getPdName());
        sp171102Bean.setMachiningCode(sp171102Param.getMachiningCode());
        sp171102Bean.setMachining(sp171102Param.getMachining());
        sp171102Bean.setFeatureCode(sp171102Param.getFeatureCode());
        sp171102Bean.setFeature(sp171102Param.getFeature());
        sp171102Bean.setClassesCode(sp171102Param.getClassesCode());
        sp171102Bean.setClasses(sp171102Param.getClasses());
        sp171102Bean.setBreedCode(sp171102Param.getBreedCode());
        sp171102Bean.setBreed(sp171102Param.getBreed());
        sp171102Bean.setWeightCode(sp171102Param.getWeightCode());
        sp171102Bean.setWeight(sp171102Param.getWeight());
        sp171102Bean.setLoginId(loginId);
        if (null != sp171102Param.getPublishId()) {
            SP171102Bean sp171102BeanTemp = sp171102Logic.findDemandPublish(sp171102Bean);
            sp171102Bean.setPublishTotalNum(sp171102BeanTemp.getPublishTotalNum());
            sp171102Bean.setGradeRatio1(sp171102BeanTemp.getGradeRatio1());
            sp171102Bean.setGradeRatio2(sp171102BeanTemp.getGradeRatio2());
            sp171102Bean.setGradeRatio3(sp171102BeanTemp.getGradeRatio3());
        }

        // 分配平衡管理系数
        List<SP171102Bean> constRatioList = sp171102Logic.getBalanceRatio();
        for (SP171102Bean scr : constRatioList) {
            if (bulkOrder.equals(scr.getRatioTypeDetail())) {
                sp171102Bean.setRatioTypeDetailVal1(scr.getRatioTypeDetailVal());
            } else if (largeOrder.equals(scr.getRatioTypeDetail())) {
                sp171102Bean.setRatioTypeDetailVal2(scr.getRatioTypeDetailVal());
            } else if (standardOrder.equals(scr.getRatioTypeDetail())) {
                sp171102Bean.setRatioTypeDetailVal3(scr.getRatioTypeDetailVal());
            }
        }
        sp171102Bean.setCanSetFlg(canSetFlg);
        model.addAttribute("sp171102Bean", sp171102Bean);
        logger.info("供应商需求发布详细查看画面初始化结束");
        return "sp/SP171102";
    }

    /**
     * save
     *
     * @return 页面
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, SP171102Param sp171102Param) {
        logger.info("保存数据");
        Date newDate = DateTimeUtil.getCustomerDate();
        //修改供应商需求发布表
        SP171102Bean sp171102Bean = new SP171102Bean();
        //Add for 保存时间sysdate横展开 at 2016/09/06 by ni_shaotang Start
        this.setCommonParam(sp171102Bean);
        sp171102Bean.setCrtTime(newDate);
        sp171102Bean.setUpdTime(newDate);
        sp171102Bean.setActTime(newDate);
        //Add for 保存时间sysdate横展开 at 2016/09/06 by ni_shaotang Start
        sp171102Bean.setPublishTotalNum(sp171102Param.getPublishTotalNum());
        sp171102Bean.setGradeRatio1(sp171102Param.getGradeRatio1());
        sp171102Bean.setGradeRatio2(sp171102Param.getGradeRatio2());
        sp171102Bean.setGradeRatio3(sp171102Param.getGradeRatio3());
        sp171102Bean.setDemandYearMonthShow(sp171102Param.getDemandYearMonthShow());
        sp171102Bean.setPublishYm(sp171102Param.getPublishYm());
        String publishYm = sp171102Param.getPublishYm();
        Date date = DateTimeUtil.parseDate(publishYm, DateTimeUtil.FORMAT_YEAR_MONTH);
        Date startDate = DateTimeUtil.getMinMonthDate(date);
        Date endDate = DateTimeUtil.getMaxMonthDate(date);
        String start = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, startDate);
        String end = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, endDate);
        sp171102Bean.setDemandStartDate(start);
        sp171102Bean.setDemandEndDate(end);
        sp171102Bean.setLgcsCode(sp171102Param.getLgcsCode());
        sp171102Bean.setPdTypeCode(sp171102Param.getPdTypeCode());
        sp171102Bean.setRatioTypeDetailVal1(sp171102Param.getRatioTypeDetailVal1());
        sp171102Bean.setRatioTypeDetailVal2(sp171102Param.getRatioTypeDetailVal2());
        sp171102Bean.setRatioTypeDetailVal3(sp171102Param.getRatioTypeDetailVal3());
        sp171102Bean.setCanSetFlg(sp171102Param.getCanSetFlg());
        sp171102Bean.setLoginId(sp171102Param.getLoginId());
        //判断页面传递的pubLishId 是否为空，为null进行新增操作，不为null进行修改操作
        if (sp171102Param.getPublishId() != null && !sp171102Param.getPublishId().equals("")) {
            //修改供应商需求发布
            sp171102Bean.setPublishId(sp171102Param.getPublishId());
            sp171102Logic.modifyDemandPublish(sp171102Bean);
            //修改供应商需求发布详细
            List<SP171102Bean> sp171102Beans = sp171102Logic.findDemandPublishDetial(sp171102Param);
            for (int i = 0; i < sp171102Beans.size(); i++) {
                if (sp171102Beans.get(i).getGradeCode().equals(String.valueOf(NumberConst.IntDef.INT_ONE))) {
                    sp171102Bean.setGradeCode(sp171102Beans.get(i).getGradeCode());
                    BigDecimal publishNum1 = sp171102Param.getGradeRatio1().divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
                    sp171102Bean.setPublishNum(sp171102Param.getPublishTotalNum().multiply(publishNum1));
                }
                if (sp171102Beans.get(i).getGradeCode().equals(String.valueOf(NumberConst.IntDef.INT_TWO))) {
                    sp171102Bean.setGradeCode(sp171102Beans.get(i).getGradeCode());
                    BigDecimal publishNum2 = sp171102Param.getGradeRatio2().divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
                    sp171102Bean.setPublishNum(sp171102Param.getPublishTotalNum().multiply(publishNum2));
                }
                if (sp171102Beans.get(i).getGradeCode().equals(String.valueOf(NumberConst.IntDef.INT_THREE))) {
                    sp171102Bean.setGradeCode(sp171102Beans.get(i).getGradeCode());
                    BigDecimal publishNum3 = sp171102Param.getGradeRatio3().divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
                    sp171102Bean.setPublishNum(sp171102Param.getPublishTotalNum().multiply(publishNum3));
                }
                sp171102Logic.modifyDemandPublishDetail(sp171102Bean);
            }
        } else {
            //新增供应商需求发布
            sp171102Bean.setPublishId(createSeqNo());
            sp171102Logic.insertDemandPublish(sp171102Bean);
            Integer greadSize = Integer.valueOf((String) sp171102Param.getFilterMap().get("greadSize"));
            //新增供应商需求发布详细
            for (int i = 1; i < greadSize + 1; i++) {
                sp171102Bean.setPublishDetailId(createDetailSeqNo());
                sp171102Bean.setPublishId(sp171102Bean.getPublishId());
                sp171102Bean.setGradeCode(String.valueOf(i));
                // i-代表等级
                sp171102Bean.setPdCode(sp171102Param.getPdTypeCode() + sp171102Bean.getGradeCode());
                sp171102Bean.setForecastId(sp171102Param.getForecastId());
                if (sp171102Bean.getGradeCode().equals(String.valueOf(NumberConst.IntDef.INT_ONE))) {
                    BigDecimal publishNum1 = sp171102Param.getGradeRatio1().divide(new BigDecimal(NumberConst.IntDef.INT_HUNDRED), NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
                    sp171102Bean.setPublishNum(sp171102Param.getPublishTotalNum().multiply(publishNum1));
                }
                if (sp171102Bean.getGradeCode().equals(String.valueOf(NumberConst.IntDef.INT_TWO))) {
                    BigDecimal publishNum2 = sp171102Param.getGradeRatio2().divide(new BigDecimal(NumberConst.IntDef.INT_HUNDRED), NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
                    sp171102Bean.setPublishNum(sp171102Param.getPublishTotalNum().multiply(publishNum2));
                }

                if (sp171102Bean.getGradeCode().equals(String.valueOf(NumberConst.IntDef.INT_THREE))) {
                    BigDecimal publishNum3 = sp171102Param.getGradeRatio3().divide(new BigDecimal(NumberConst.IntDef.INT_HUNDRED), NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
                    sp171102Bean.setPublishNum(sp171102Param.getPublishTotalNum().multiply(publishNum3));
                }
                sp171102Logic.insertDemandPublishDetail(sp171102Bean);
            }
        }
        model.addAttribute("sp171102Bean", sp171102Bean);
        sp171102Param.setPublishId(sp171102Bean.getPublishId());
        logger.info("供应商需求发布详细保存操作结束");
        return init(model, sp171102Param);
    }

    /**
     * 创建招标数量发布明细表SEQ_NO
     *
     * @return PUBLISH_DETAIL_ID
     */
    private long createDetailSeqNo() {
        return commonLogic.maxId("SP_PD_DEMAND_PUBLISH_DETAIL", "PUBLISH_DETAIL_ID");
    }

    /**
     * 创建招标数量发布明细表SEQ_NO
     *
     * @return PUBLISH_DETAIL_ID
     */
    private long createSeqNo() {
        return commonLogic.maxId("SP_PD_DEMAND_PUBLISH", "PUBLISH_ID");
    }


}
