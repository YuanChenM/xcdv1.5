package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.MskSlInfoServiceParam;
import com.msk.price.bean.SP171109Bean;
import com.msk.price.bean.SP171196Bean;
import com.msk.price.bean.SP171196Param;
import com.msk.price.logic.SP171109Logic;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 *供应商待报价一览
 */
@Controller
@RequestMapping("SP171109")
public class SP171109Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171109Controller.class);

    @Autowired
    private SP171109Logic SP171109Logic;

    @Autowired
    private SP171196Logic SP171196Logic;
    /**
     * 供应商待报价一览页面加载
     *
     * @param model model
     * @return SP171109
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(BasePageParam pageParam, Model model) {
        logger.debug("供应商待报价一览画面初始化");
        initModel(pageParam, model);
        return "sp/SP171109";
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
    PageResult<SP171109Bean> search(BasePageParam param, Model model) {
        logger.debug("供应商待报价一览检索");
        DbUtils.buildLikeCondition(param, "pdTypeCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "classesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "machining", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "breed", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "feature", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param, "weight", DbUtils.LikeMode.PARTIAL);
        //原有价盘周期信息(不包括下一个报价周期的数据)
        List<SP171109Bean> pricePeriodOriginList = new ArrayList<>();
        String newPricePeriodStr = SP171109Logic.getNewPricePeriod().getPricePeriod();
        param.getFilterMap().put("pricePeriodOriginList", pricePeriodOriginList);
        String pricePeriod = StringUtil.toSafeString(param.getFilterMap().get("pricePeriod"));
        //判断是否为新周期(1为是查询当期价盘，0为往期价盘)
        if (pricePeriod.equals(newPricePeriodStr)) {
            param.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ONE));
        } else {
            param.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));
        }
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


        //String slCode = slInfo.getSlCode();
        //价盘模块的slSode等于卖家模块的SlCodeDis
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        String slCode = slInfo.getSlCode();
        param.getFilterMap().put("slCode", slCode);
        //slInfo.setSlCode(slCode);


        return SP171109Logic.getPageResultByInterface(param, slInfo);
    }

    /**
     * Model处理
     *
     * @param pageParam
     * @param model
     */
    public void initModel(BasePageParam pageParam, Model model) {
        BasePageParam param = new BasePageParam();
        param.getFilterMap().put("delFlg", NumberConst.IntDef.INT_ZERO);
        model.addAttribute("param", pageParam);
        //物流区信息
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());
        //周期信息
        List<SP171109Bean> pricePeriodList = SP171109Logic.priceYmList(pageParam);
        List<SP171109Bean> list = new ArrayList<SP171109Bean>();
        //新价盘周期信息（包括下一个价盘信息）
        SP171109Bean newPricePeriod = SP171109Logic.getNewPricePeriod();
        //判断数据库中价盘信息是否已包括下一个价盘周期
        boolean isContainNewPricePeriod = false;
        for (SP171109Bean pricePeriodInfo : pricePeriodList) {
            if (pricePeriodInfo.getPricePeriod().equals(newPricePeriod.getPricePeriod())) {
                isContainNewPricePeriod = true;
                pricePeriodInfo.setIsNewPrice(String.valueOf(NumberConst.IntDef.INT_ONE));
                break;
            }
        }
        if (!isContainNewPricePeriod) {
            list.add(newPricePeriod);
        }
        for (SP171109Bean pricePeriodInfo : pricePeriodList) {
            list.add(pricePeriodInfo);
        }
        SP171109Bean sb = list.get(NumberConst.IntDef.INT_ZERO);
        model.addAttribute("pricePeriodList", list);
        //price period model
        model.addAttribute("priceStartDate", sb.getPriceStartDate());
        model.addAttribute("priceEndDate", sb.getPriceEndDate());
        //展示信息
        model.addAttribute("demandLimitedDateShow", sb.getDemandLimitedDateShow());
        model.addAttribute("demandYearMonthShow", sb.getDemandYearMonthShow());
        model.addAttribute("isNewPrice", sb.getIsNewPrice());

        //获取供应商是否可以无库存报价权限
        String viewFlg = StringUtil.toString(NumberConst.IntDef.INT_ZERO);
        SP171196Param bp = new SP171196Param();
        bp.setViewKey("PRICE_ABLE");
        List<SP171196Bean> viewList = SP171196Logic.findViewList(bp);
        if (null != viewList && viewList.size() > 0) {
            SP171196Bean view = viewList.get(0);
            if(null!=view.getViewFlg()){
                viewFlg=view.getViewFlg();
            }
        }
        model.addAttribute("viewFlg", viewFlg);
    }
}
