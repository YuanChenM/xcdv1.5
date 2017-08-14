package com.msk.bs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.BS2102111Bean;
import com.msk.bs.bean.BS2102111Param;
import com.msk.bs.logic.BS2102111Logic;
import com.msk.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定级管理
 * Created by ren_qiang on 2016/8/18.
 */
@Controller
@RequestMapping(value = "BS2102111")
public class BS2102111Controller extends BaseController {

    @Autowired
    private BS2102111Logic bs2102111Logic;

    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model,String slCode,String houseCode){
        model.addAttribute("slCode",slCode);
        model.addAttribute("houseCode",houseCode);
        /**初始定级数据--begin 2016/10/10 whc */
        if(!StringUtils.isEmpty(slCode) && !StringUtils.isEmpty(houseCode)){
            BaseParam param = new BaseParam();
            super.setCommonParam(param);
            param.setFilter("slCode",slCode);
            param.setFilter("houseCode",houseCode);
            bs2102111Logic.findHouseManageAndGrade(param);
        }
        /**初始定级数据--end 2016/10/10 whc */

        return "bs/BS2102111";
    }


    @RequestMapping(value = "search",method = RequestMethod.POST)
    public  @ResponseBody PageResult<BS2102111Bean> search(BS2102111Param param){

        return  bs2102111Logic.findHousePageList(param);
    }

    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public @ResponseBody String save( String jsonStr){
        Map<String,BS2102111Bean> map = null;
        if(StringUtils.hasLength(jsonStr)){
            map = JSON.parseObject(jsonStr, new TypeReference<Map<String, BS2102111Bean>>() {
            });

        BS2102111Param param = new BS2102111Param();
        super.setCommonParam(param);
        Date date =  DateTimeUtil.getCustomerDate();
        param.setCrtTime(date);
        param.setUpdTime(date);
        param.setActTime(date);
        Integer cot =  bs2102111Logic.insertHouseGrade(map.values(),param);
         if(cot  != null &&cot != 0) {
            return "S";
         }
            else{
             return "F";
         }
        }
        return "F";
    }
}
