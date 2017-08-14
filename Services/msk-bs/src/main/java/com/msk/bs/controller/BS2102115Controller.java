package com.msk.bs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ren_qiang on 2016/8/18.
 */
@Controller
@RequestMapping(value = "BS2102115")
public class BS2102115Controller extends BaseController {


    @RequestMapping(value = "init" ,method = RequestMethod.POST)
    public String init(Model model){
        return "bs/BS2102115";
    }





}
