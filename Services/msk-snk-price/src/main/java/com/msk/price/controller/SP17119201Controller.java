package com.msk.price.controller;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;


import com.msk.price.bean.SP17119201Bean;
import com.msk.price.logic.SP17119201Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by wang_shuai on 2016/5/20.
 */
@Controller
@RequestMapping("SP17119201")
public class SP17119201Controller extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(SP17119201Controller.class);

    @Autowired
    private SP17119201Logic sp17119201Logic;



    /**
     * 初始化页面
     *
     *
     * @return 页面
     */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(String str) {
        logger.debug("价盘通道编辑页面弹出框初始化");
        return "sp/SP17119201";
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(String paramList){
        logger.debug("保存价盘通道标准数据中");
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        sp17119201Logic.save(paramList,baseParam);

        return "sp/SP17119201";
    }


    @RequestMapping(value = "searchMaxCode",method = RequestMethod.POST)
    public String searchMaxCode(Model model){
        logger.debug("获得价盘通道code");
        String maxCode = null;
        model.addAttribute("maxCode",maxCode);

        return "sp/SP17119201";
    }
    @RequestMapping(value = "updateInit",method = RequestMethod.POST)
    public String updateInit(SP17119201Bean sp17119201Bean,Model model) {
        logger.debug("价盘通道编辑页面弹出框初始化");
        model.addAttribute("wayCode", sp17119201Bean.getWayCode());
        model.addAttribute("wayName",sp17119201Bean.getWayName());
        if (sp17119201Bean.getSupOrder() != null && !sp17119201Bean.getSupOrder().equals("")){
            model.addAttribute("startSup",sp17119201Bean.getSupOrder().split("≥")[1]);
        }
        //Modif for Bug#3345 at 2016/10/14 by ni_shaotang Start
        if(sp17119201Bean.getOrder1() != null && !sp17119201Bean.getOrder1().equals("")){
            model.addAttribute("start1",sp17119201Bean.getOrder1().split("-")[0]);
            model.addAttribute("end1",sp17119201Bean.getOrder1().split("-")[1]);
        }
        //Modif for Bug#3345 at 2016/10/14 by ni_shaotang End
        if(sp17119201Bean.getOrder2() != null && !sp17119201Bean.getOrder2().equals("")){
            model.addAttribute("start2",sp17119201Bean.getOrder2().split("-")[0]);
            model.addAttribute("end2",sp17119201Bean.getOrder2().split("-")[1]);
        }
        if(sp17119201Bean.getOrder3() != null && !sp17119201Bean.getOrder3().equals("")){
            model.addAttribute("start3",sp17119201Bean.getOrder3().split("-")[0]);
            model.addAttribute("end3",sp17119201Bean.getOrder3().split("-")[1]);
        }
        if(sp17119201Bean.getOrder4() != null && !sp17119201Bean.getOrder4().equals("")){
            model.addAttribute("start4",sp17119201Bean.getOrder4().split("-")[0]);
            model.addAttribute("end4",sp17119201Bean.getOrder4().split("-")[1]);
        }
        if(sp17119201Bean.getOrder5() != null && !sp17119201Bean.getOrder5().equals("")){
            model.addAttribute("start5",sp17119201Bean.getOrder5().split("-")[0]);
            model.addAttribute("end5",sp17119201Bean.getOrder5().split("-")[1]);
        }
        if(sp17119201Bean.getOrder6() != null && !sp17119201Bean.getOrder6().equals("")){
            model.addAttribute("start6",sp17119201Bean.getOrder6().split("-")[0]);
            model.addAttribute("end6",sp17119201Bean.getOrder6().split("-")[1]);
        }
        if(sp17119201Bean.getOrder7() != null && !sp17119201Bean.getOrder7().equals("")){
            model.addAttribute("start7",sp17119201Bean.getOrder7().split("-")[0]);
            model.addAttribute("end7",sp17119201Bean.getOrder7().split("-")[1]);
        }
        if(sp17119201Bean.getOrder8() != null && !sp17119201Bean.getOrder8().equals("")){
            model.addAttribute("start8",sp17119201Bean.getOrder8().split("-")[0]);
            model.addAttribute("end8",sp17119201Bean.getOrder8().split("-")[1]);
        }
        if(sp17119201Bean.getOrder9() != null && !sp17119201Bean.getOrder9().equals("")){
            model.addAttribute("start9",sp17119201Bean.getOrder9().split("-")[0]);
            model.addAttribute("end9",sp17119201Bean.getOrder9().split("-")[1]);
        }

        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getSupBlance())){
            model.addAttribute("supBlance",sp17119201Bean.getSupBlance());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance1())){
            model.addAttribute("blance1",sp17119201Bean.getBlance1());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance2())){
            model.addAttribute("blance2",sp17119201Bean.getBlance2());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance3())){
            model.addAttribute("blance3",sp17119201Bean.getBlance3());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance4())){
            model.addAttribute("blance4",sp17119201Bean.getBlance4());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance5())){
            model.addAttribute("blance5",sp17119201Bean.getBlance5());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance6())){
            model.addAttribute("blance6",sp17119201Bean.getBlance6());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance7())){
            model.addAttribute("blance7",sp17119201Bean.getBlance7());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance8())){
            model.addAttribute("blance8",sp17119201Bean.getBlance8());
        }
        if(!StringUtil.isNullOrEmpty(sp17119201Bean.getBlance9())){
            model.addAttribute("blance9",sp17119201Bean.getBlance9());
        }
        model.addAttribute("flag","0");
        model.addAttribute("saveOrUpFlag","0");
        return "sp/SP17119201";
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(String paramList){
        logger.debug("保存价盘通道标准数据中");
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        sp17119201Logic.update(paramList,baseParam);

        return "sp/SP17119201";
    }

    /**
     * 校验营销状态名称不能重复
     * @param wayName
     * @param wayCode
     * @return
     */
    @RequestMapping(value = "checkWayInfo",method = RequestMethod.POST)
    @ResponseBody
    public int checkWayInfo(String wayName,String wayCode){
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("wayName", wayName);
        baseParam.getFilterMap().put("wayCode", wayCode);
        return sp17119201Logic.checkWayInfo(baseParam);
    }
}

