package com.msk.price.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.*;
import com.msk.price.logic.SP171113Logic;
import com.msk.price.utils.PriceCycleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 价盘详情
 *
 * @author zhou_ling
 */
@Controller
@RequestMapping("SP171113")
public class SP171113Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171113Controller.class);

    @Autowired
    private SP171113Logic sp171113Logic;

    private static double upPrice = 1.03;
    private static double downPrice = 0.97;
    private static  DecimalFormat df = new DecimalFormat("#.00");
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(@RequestParam(value = "lgcdCode", required = false) String lgcdCode, @RequestParam(value = "pdCode", required = true) String pdCode, @RequestParam(value = "pricePeriod", required = true) String pricePeriod,
                       @RequestParam(value = "lgcsName", required = true) String lgcsName, @RequestParam(value = "pdName", required = true) String pdName, @RequestParam(value = "wayCode", required = true) String wayCode, Model model) {
        logger.debug("价盘详情");

        // 获取登录信息（登录类型）
        LoginUser loginUser = getLoginUser();
        String userType = loginUser.getUserType();

        // 根据价盘周期获取价盘周期对应的时间
        PriceCycleParam priceCycleParam = new PriceCycleParam();
        priceCycleParam.setPriceCode(pricePeriod);
        PriceCycleResult priceCycle = PriceCycleUtil.getPriceCycleByCode(priceCycleParam);

        String startDate = priceCycle.getStartDateStr();
        String endDate = priceCycle.getEndDateStr();
        String beforePeriodTime = startDate.substring(0, 4) + "年" + startDate.substring(4, 6) + "月" +
                startDate.substring(6, 8) + "日" + "-" + endDate.substring(0, 4) + "年" + endDate.substring(4, 6) + "月"
                + endDate.substring(6, 8) + "日";

        // 获取上一个价盘周期的时间段和价盘周期
        priceCycleParam.setCurrentDate(priceCycle.getStartDate());
        priceCycle = PriceCycleUtil.getPrePriceCycle(priceCycleParam);
        String beforePricePeriod = priceCycle.getCycleCode();

        // 查询价盘详情
        BaseParam param = new BaseParam();
        param.setFilter("lgcdCode", lgcdCode);
        param.setFilter("frontPricePeriod", beforePricePeriod);
        param.setFilter("pricePeriod", pricePeriod);
        param.setFilter("pdCode", pdCode);
        param.setFilter("wayCode", wayCode);
        // 查询价盘等级系数（价盘通道等级详细SP_WAYG_DETAIL）
        List<SP171113Bean> priceDetailList = this.sp171113Logic.findPricePercent(param);
        //查询上个价盘周期（卖家供应商投标价盘表SP_SELLER_PD_PRICEPLATE）
        List<SP171113Bean> beforePriceList = this.sp171113Logic.findBeforePrice(param);
        // 查询本次价盘周期价格（卖家供应商投标价盘表SP_SELLER_PD_PRICEPLATE）
        List<SP171113Bean> priceList = this.sp171113Logic.findPrice(param);

        String defaultWayGrade = "5";//默认可编辑通道

        // 十级价盘不足
        if (priceDetailList != null && priceDetailList.size() != 0) {
            for (int j = priceDetailList.size() + 1; j <= NumberConst.IntDef.INT_TEN; j++) {
                SP171113Bean priceInfo = new SP171113Bean();
                priceInfo.setWayGateName(j + "级");
                priceDetailList.add(priceInfo);
            }

            for (int i = 0; i < priceDetailList.size(); i++) {
                if (beforePriceList.size() > i) {
                    double beforePrice = beforePriceList.get(i).getFrontPrice();
                    priceDetailList.get(i).setUpPrice(beforePrice * upPrice);
                    priceDetailList.get(i).setDownPrice(beforePrice * downPrice);
                }
                //默认设置价格为0
                priceDetailList.get(i).setPrice(new BigDecimal("0.00"));
                //定义价格
                for (SP171113Bean bean : priceList) {
                    if(bean.getWayGateCode().equals(priceDetailList.get(i).getWayGateCode())){
                        priceDetailList.get(i).setPrice(new BigDecimal(df.format(bean.getPrice())));
                        continue;
                    }
                }
                //添加价盘id
                priceDetailList.get(i).setPriceId(priceList.get(i).getPriceId());

                    /*if(priceDetailList.size() > priceList.size()){
                        priceDetailList.get(i).setPrice(NumberConst.IntDef.INT_ZERO);
                    }else{
                        priceDetailList.get(i).setPriceId(priceList.get(i).getPriceId());
                        //不参与分销的价格delFlg为1，价格设为0
                        if(String.valueOf(NumberConst.IntDef.INT_ONE).equals(priceList.get(i).getDelFlg())){
                            priceDetailList.get(i).setPrice(NumberConst.IntDef.INT_ZERO);
                        }else{
                            priceDetailList.get(i).setPrice(priceList.get(i).getPrice());
                        }
                    }*/

                if (null !=priceDetailList.get(i).getWayGradePercent() && priceDetailList.get(i).getWayGradePercent().compareTo(new BigDecimal(NumberConst.IntDef.INT_HUNDRED)) == 0) {
                    defaultWayGrade = priceDetailList.get(i).getWayGateCode();
                }
            }
        }
        //判断价盘周期是否过期  0:在显示价盘周期内  1：不在
        String overDataFlag = "0";

        priceCycleParam.setCurrentDate(DateTimeUtil.getCustomerDate());
        PriceCycleResult curPriceCycle = PriceCycleUtil.getPriceCycle(priceCycleParam);//当前价盘
        if (!curPriceCycle.getCycleCode().equals(pricePeriod)) {//判断是否可填报价盘
            overDataFlag = "1";
        }

        //0：供应商  1：采购员
        model.addAttribute("userType", userType);
        model.addAttribute("overDataFlag", overDataFlag);
        model.addAttribute("defaultWayGrade", defaultWayGrade);
        model.addAttribute("priceDetailList", priceDetailList);
        model.addAttribute("beforePeriodTime", beforePeriodTime);
        model.addAttribute("pricePeriod", pricePeriod);
        model.addAttribute("lgcsName", lgcsName);
        model.addAttribute("pdName", pdName);

        return "sp/SP171113";
    }

    /**
     * 价盘详情保存
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    void save(SP171113Bean param) {
        logger.debug("价盘详情保存");
        // 设置共通字段
        super.setCommonParam(param);
        this.sp171113Logic.modifyPrice(param);
    }
}
