package com.msk.price.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.*;
import com.msk.price.logic.SP171110Logic;
import com.msk.price.logic.SP171196Logic;
import com.msk.price.utils.CommRestUtil;
import com.msk.price.utils.PriceCycleUtil;
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
 * 供应商价格申报详细
 *
 * @author qi_dianyong
 */

@Controller
@RequestMapping("SP171110")
public class SP171110Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171110Controller.class);

    @Autowired
    private SP171110Logic sp171110Logic;
    @Autowired
    private SP171196Logic SP171196Logic;

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model, SP171110Param sp171110Param) {

        logger.info("供应商价格申报详细初始化开始");

        String defaultWayGrade = "5";//默认可编辑通道
        // 获取登录信息（登录类型）
        LoginUser loginUser = getLoginUser();
        String userType = loginUser.getUserType();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        sp171110Param.setSellerCode(slInfo.getSlCode());
        sp171110Param.setSlId(slInfo.getSlId());
        sp171110Param.setSellerName(slInfo.getEpName());
        sp171110Logic.queryPdInfo(sp171110Param);
        //价盘定义
        PriceCycleParam priceCycleParam = new PriceCycleParam();
        priceCycleParam.setPriceCode(sp171110Param.getPricePeriod());
        //当期价盘信息
        PriceCycleResult priceResult = PriceCycleUtil.getPriceCycleByCode(priceCycleParam);//当前价盘对象
        sp171110Param.setPricePeriodStart(priceResult.getStartDateStr());
        sp171110Param.setPricePeriodEnd(priceResult.getEndDateStr());
        //上期价盘信息
        priceCycleParam = new PriceCycleParam();
        priceCycleParam.setCurrentDate(priceResult.getStartDate());
        PriceCycleResult lastPrice = PriceCycleUtil.getPrePriceCycle(priceCycleParam);//上一期价盘
        sp171110Param.setLastPricePeriod(lastPrice.getCycleCode());
        String lastStartTime = lastPrice.getStartDateStr();
        String lastEndTime = lastPrice.getEndDateStr();
        sp171110Param.setLastPricePeriodTime(lastStartTime.substring(4, 6) + "月" + lastStartTime.substring(6) + "日 - " + lastEndTime.substring(4, 6) + "月" + lastEndTime.substring(6) + "日");
        // 查询供应商价格申报详细
        List<SP171110Bean> SP171110BeanList = sp171110Logic.getSellerPriceList(sp171110Param);
        for (SP171110Bean bean : SP171110BeanList) {
            if (bean.getWayGradePercent().compareTo(new BigDecimal(NumberConst.IntDef.INT_HUNDRED)) == 0) {
                defaultWayGrade = bean.getWayGradeCode();
            }
        }
        //判断价盘周期是否过期
        String overDataFlag = "0";
        String date = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDDHHMM, DateTimeUtil.getCustomerDate());
        Date nowDate = DateTimeUtil.parseDate(date, DateTimeUtil.FORMAT_YYMMDDHHMM);

        //价盘定义
        priceCycleParam = new PriceCycleParam();
        priceCycleParam.setCurrentDate(nowDate);
        PriceCycleResult nextPriceCycle = PriceCycleUtil.getNextPriceCycle(priceCycleParam);//当前时间填报的价盘
        if (!nextPriceCycle.getCycleCode().equals(sp171110Param.getPricePeriod())) {//判断是否可填报价盘
            overDataFlag = "1";
        }

        model.addAttribute("defaultWayGrade", defaultWayGrade);
        model.addAttribute("overDataFlag", overDataFlag);
        model.addAttribute("userType", userType);
        model.addAttribute("sp171110Param", sp171110Param);
        model.addAttribute("SP171110BeanList", SP171110BeanList);
        //获取供应商是否可以无库存报价权限
        String viewFlg = StringUtil.toString(NumberConst.IntDef.INT_ZERO);
        SP171196Param bp = new SP171196Param();
        bp.setViewKey("PRICE_ABLE");
        List<SP171196Bean> viewList = SP171196Logic.findViewList(bp);
        if (null != viewList && viewList.size() > 0) {
            SP171196Bean view = viewList.get(0);
            if (null != view.getViewFlg()) {
                viewFlg = view.getViewFlg();
            }
        }
        model.addAttribute("viewFlg", viewFlg);
        logger.info("供应商价格申报详细初始化开始结束");
        return "sp/SP171110";
    }

    /**
     * 供应商价格申报详细保存
     *
     * @author qi_dianyong
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(SP171110Bean param, Model model, SP171110Param sp171110Param) {

        logger.debug("供应商价格申报保存");
        Date newDate = DateTimeUtil.getCustomerDate();
        // 设置共通字段
        super.setCommonParam(param);
        super.setCommonParam(sp171110Param);
        //Add for 保存时间sysdate横展开 at 2016/09/06 by ni_shaotang Start
        param.setCrtTime(newDate);
        param.setUpdTime(newDate);
        param.setActTime(newDate);

        sp171110Param.setCrtTime(newDate);
        sp171110Param.setUpdTime(newDate);
        sp171110Param.setActTime(newDate);
        //Add for 保存时间sysdate横展开 at 2016/09/06 by ni_shaotang Start
        //获取卖家slcode
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        if (null != slInfo) {
            String slCodeDis = slInfo.getSlCodeDis();
            sp171110Param.setSlId(slCodeDis);
        }
        this.sp171110Logic.modifyPrice(param, sp171110Param);
        return "1";
    }
}

