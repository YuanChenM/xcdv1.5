package com.msk.bs.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.BS2102116Bean;
import com.msk.bs.bean.BS2102116Param;
import com.msk.bs.bean.BS2102117Bean;
import com.msk.bs.bean.BS2102118Bean;
import com.msk.bs.logic.BS2102115Logic;
import com.msk.bs.logic.BS2102116Logic;
import com.msk.bs.logic.BS2102117Logic;
import com.msk.bs.logic.BS2102118Logic;
import com.msk.core.entity.SlHouseEducationHis;
import com.msk.core.entity.SlHouseTrainingHis;
import com.msk.core.entity.SlHouseWorkHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.msk.common.base.BaseController;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 更新个人简历履历
 * Created by wang_haichun on 2016/8/18.
 */
@Controller
@RequestMapping(value = "BS2102117")
public class BS2102117Controller extends BaseController {

    @Autowired
    private BS2102118Logic bs2102118Logic;

    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "slCode",required = true) String slCode,
                       @RequestParam(value = "houseCode",required = true) String houseCode) {
        //查询履历
        //工作经验
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode",slCode);
        baseParam.setFilter("houseCode",houseCode);
        List<SlHouseWorkHis> workHisList = bs2102118Logic.findWorkHis(baseParam);
        model.addAttribute("workHisList",workHisList);
        //教育经历
        List<SlHouseEducationHis> eduHisList = bs2102118Logic.findEduHis(baseParam);
        model.addAttribute("eduHisList",eduHisList);
        //培训经历
        List<SlHouseTrainingHis> trainHisList = bs2102118Logic.findTrainHis(baseParam);
        model.addAttribute("trainHisList",trainHisList);
        model.addAttribute("slCode",slCode);
        model.addAttribute("houseCode",houseCode);
        return "bs/BS2102122";
    }
}
