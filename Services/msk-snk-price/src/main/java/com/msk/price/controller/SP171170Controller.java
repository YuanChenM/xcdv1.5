package com.msk.price.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseDownloadController;
import com.msk.price.bean.SP171170Bean;
import com.msk.price.bean.SP171170Param;
import com.msk.price.logic.SP171170Logic;
import com.msk.price.utils.CommRestUtil;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/6/23.
 */
@Controller
@RequestMapping("SP171170")
public class SP171170Controller extends BaseDownloadController {

    private  static Logger logger = LoggerFactory.getLogger(SP171170Controller.class);

    @Autowired
    private SP171170Logic sp171170Logic;

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(SP171170Param param,Model model){
        // 价盘年月
        String priceDate="";
        // 价盘周期
        String pricecycle= "";
        if(!StringUtil.isNullOrEmpty(param.getPriceDate()) && !StringUtil.isNullOrEmpty(param.getPricecycle())){
            // 为了防止点击编辑按钮后将查询条件的价盘周期，重新置为最新价盘
            priceDate = param.getPriceDate();
            pricecycle = param.getPricecycle();
        }else{
            // 默认显示价盘表里已有的最新价盘的数据。
            String pricePlate =  sp171170Logic.findNewestPriceplate(param);
            if(!StringUtil.isNullOrEmpty(pricePlate)){
                priceDate =  pricePlate.substring(0,2)+"-"+pricePlate.substring(2,4);
                pricecycle = pricePlate.substring(4,5);
            }
        }
        model.addAttribute("priceDate", priceDate);
        model.addAttribute("pricecycle", pricecycle);
        // 获取物流区信息
        model.addAttribute("logisticsAreas", CommRestUtil.getLogiticsAreaList());
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        // 调用产品接口（获取一级分类的名称）
        model.addAttribute("pdClasses",CommRestUtil.getPdList(pdInfoParam));
        // 页面用来判断是否能够编辑
        model.addAttribute("flag",param.getFlag());
        model.addAttribute("logcAreaCode",param.getLgcsAreaCode());
        model.addAttribute("classesCode",param.getClassesCode());
        model.addAttribute("machiningCode",param.getMachiningCode());
        model.addAttribute("breedName",param.getBreedName());

        return "sp/SP171170";
    }


    @RequestMapping(value = "search",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171170Bean> search(SP171170Param param){
        String priceDate="";
        if(!StringUtil.isNullOrEmpty(param.getPriceDate())){
             priceDate=  param.getPriceDate().substring(0, 2) + param.getPriceDate().substring(3, 5);
        }
        param.setPricecyclePeriod(priceDate+param.getPricecycle());
       return sp171170Logic.searchPricePdInfo(param);
    }


    /**
     *查询产品二级分类
     * @param param
     * @return
     */
    @RequestMapping(value = "findMaching",method = RequestMethod.POST)
    @ResponseBody
    public List<PDInfoResult> findMachingList(SP171170Param param){
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setClassesCode(param.getClassesCode());
        List<PDInfoParam> pdInfoParams = new ArrayList<>();
        pdInfoParams.add(pdInfoParam);
        PDInfoParam  pdParam = new PDInfoParam();
        pdParam.setMachiningCodeList(pdInfoParams);
        return  CommRestUtil.getMachingList(pdParam);
    }


    /**
     * 更新价盘公斤价及箱价
     * @param model
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "modify" ,method = RequestMethod.POST)
    public String modify(Model model,String jsonStr,
                         @RequestParam(value = "priceDate", required = false) String priceDate,
                         @RequestParam(value = "pricecycle", required = false) String pricecycle){
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        Map<String, SP171170Bean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SP171170Bean>>() {
        });
        sp171170Logic.modify(map.values(),baseParam);
        SP171170Param param = new SP171170Param();
        param.setPriceDate(priceDate);
        param.setPricecycle(pricecycle);
        return this.init(param,model);
    }

    @Override
    public InputStream getInputStream() {
        return SP171170Controller.class.getClassLoader().getResourceAsStream("temp/priceTemp.xlsx");
    }
}
