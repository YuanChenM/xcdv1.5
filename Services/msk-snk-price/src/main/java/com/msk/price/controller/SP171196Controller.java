package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import com.msk.price.bean.SP171196Bean;
import com.msk.price.bean.SP171196Param;
import com.msk.price.logic.SP171196Logic;
import com.msk.price.utils.CommRestUtil;
import com.msk.price.utils.PriceCycleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 供应商显示项目设置
 *
 * @author ni_shaotang
 */

@Controller
@RequestMapping("SP171196")
public class SP171196Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171196Controller.class);

    @Autowired
    private SP171196Logic SP171196Logic;

    /**
     * 初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "init")
    public String init(Model model, SP171196Bean bean) {
        logger.info("供应商显示项目设置显示设置初始化");
        //物流区信息
        model.addAttribute("lgcsinfo", CommRestUtil.getLogiticsAreaList());

        String viewFlg = StringUtil.toString(NumberConst.IntDef.INT_ZERO);
        SP171196Param bp = new SP171196Param();
        bp.setViewKey("PRICE_ABLE");
        List<SP171196Bean> list = SP171196Logic.findViewList(bp);
        if (null != list && list.size() > 0) {
            SP171196Bean view = list.get(0);
            if (null != view.getViewFlg()) {
                viewFlg = view.getViewFlg();
            }
        }
        model.addAttribute("viewFlg", viewFlg);
        //供应商申报时间段
        SP171196Bean declareBean = SP171196Logic.findConstRatio(bp);
        List<String> timeList = new ArrayList<String>();
        for (int i = 1; i < 32; i++) {
            timeList.add(i + "");
        }
        //价盘定义
        Date newDate = DateTimeUtil.getCustomerDate();
        PriceCycleParam priceCycleParam = new PriceCycleParam();
        priceCycleParam.setCurrentDate(newDate);

        PriceCycleResult priceResult = PriceCycleUtil.getPriceCycle(priceCycleParam);//当前价盘周期

        PriceCycleResult lastPriceResult = PriceCycleUtil.getPrePriceCycle(priceCycleParam);//上一个价盘周期
        bp.setPricePeriod(lastPriceResult.getCycleCode());
        //查询价盘列表
        bp.setActTime(newDate);
        List<SP171196Bean> priceList = SP171196Logic.getPriceList(bp);
        //获取当前有效价盘数据
        List<SP171196Bean> newPrice = SP171196Logic.getPriceCycleCode();

        if (null != newPrice && newPrice.size() > 0) {

            SP171196Bean price = newPrice.get(0);

            String startDateStr = DateTimeUtil.formatDate("yyyy-MM-dd HH:mm", price.getStartDate());//有效价盘开始时间
            String endDateStr = DateTimeUtil.formatDate("yyyy-MM-dd HH:mm", price.getEndDate());//有效价盘结束时间
            String pricePeriod = price.getPricePeriod();//有效价盘周期

            for (int i = 0; i < priceList.size(); i++) {
                SP171196Bean priceBean = priceList.get(i);
                if (priceBean.getPricePeriod().equals(pricePeriod)) {
                    priceBean.setStartDateStr(startDateStr);
                    priceBean.setEndDateStr(endDateStr);
                    model.addAttribute("startDate", priceBean.getStartDateStr());
                    model.addAttribute("endDate", priceBean.getEndDateStr());
                    model.addAttribute("pricePeriod", priceBean.getPricePeriod());
                    break;
                }
            }
        }

        model.addAttribute("declareBean", declareBean);
        model.addAttribute("timeList", timeList);
        model.addAttribute("priceList", priceList);
        return "sp/SP171196";
    }

    /**
     * 分页查询数据
     *
     * @param param pageParam
     * @return 分页查询数据
     */

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SP171196Bean> search(BasePageParam param, Model model) {
        logger.info("供应商显示项目设置检索");
        //多地区条件设置
        String lgcsCode = StringUtil.toSafeString(param.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsCode)) {
            String[] lgcsCodes = lgcsCode.split(",");
            param.getFilterMap().put("lgcsCode", lgcsCodes);
        }
        PageResult<SP171196Bean> result = SP171196Logic.getPageViewList(param);
        return result;
    }

    /**
     * 保存供应商权限
     *
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(SP171196Bean param, Model model) {
        logger.info("供应商申报数量");
        super.setCommonParam(param);
        param.setSystemType("1");//暂时默认是1
        SP171196Logic.saveView(param);
        return this.init(model, param);
    }

    /**
     * 供应商无库存是否可以报价
     *
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "updateDeclare/{declareFlg}", method = RequestMethod.POST)
    public String updateDeclare(@PathVariable("declareFlg") String declareFlg, SP171196Bean param, Model model) {
        logger.info("供应商申报数量");
        super.setCommonParam(param);
        param.setSystemType("1");//暂时默认是1
        if (!StringUtil.isNullOrEmpty(declareFlg)) {
            param.setViewKey("PRICE_ABLE");
            if (declareFlg.equals("1")) {
                param.setViewFlg("1");
            } else {
                param.setViewFlg("0");
            }
            SP171196Logic.saveViewInfo(param);
        }
        return this.init(model, param);
    }

    /**
     * 供应商无库存是否可以报价
     *
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "saveDeclare", method = RequestMethod.POST)
    public String saveDeclare(SP171196Bean param, Model model) {
        logger.info("供应商申报数量");
        super.setCommonParam(param);
        SP171196Logic.saveDeclare(param);
        return this.init(model, param);
    }

    /**
     * 价盘有效期设置
     *
     * @param param
     * @param model
     * @return
     */
    @RequestMapping(value = "savePrice", method = RequestMethod.POST)
    public String savePrice(SP171196Param param, Model model) {
        logger.info("价盘有效期设置");
        super.setCommonParam(param);
        param.setSystemType("1");
        SP171196Logic.savePrice(param);
        return this.init(model, new SP171196Bean());
    }
}
