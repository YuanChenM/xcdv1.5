package com.msk.product.controller;

import com.hoperun.core.consts.StringConst;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdStandard;
import com.msk.product.logic.StandardLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("realitymeasure")
public class RealityMeasureControler extends BaseController {
    @Autowired
    private StandardLogic standardLogic;
    @RequestMapping(value="check",method=RequestMethod.POST)
    public @ResponseBody String checkStandard(String classesCode,String breedCode){
        PdStandard standard = standardLogic.findStandard(classesCode, breedCode, StringConst.EMPTY);
        String flag = String.valueOf((standard==null));
        return flag;
    }
}
