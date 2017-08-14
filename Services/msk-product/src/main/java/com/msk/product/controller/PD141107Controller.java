package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141107Bean;
import com.msk.product.bean.PD141107Param;
import com.msk.product.logic.PD141107Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * PD141107Controller
 * @author yuan_chen
 *
 */
@Controller
@RequestMapping("PD141107")
public class PD141107Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PD141107Controller.class);

    /**
     * 注入PD141107Logic代理类
     */
    @Autowired
    private PD141107Logic pd141107Logic;

    private String classesCodet = "";
    private String breedCodet = "";
    private String featureCodet = "";

   /**
    * 
    * 初始化技术标准设置
    * @param pd141107Param pd141107Param
    * @param classesCode 参数
    * @param breedCode 参数
    * @param featureCode 参数
    * @param model model
    * @return 技术标准
    * @author gyh
    */
    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(PD141107Param pd141107Param, Model model, @RequestParam(value = "classesCode",required = false) String classesCode,@RequestParam(value = "breedCode",required = false) String breedCode,@RequestParam(value = "featureCode",required = false) String featureCode,
                       @RequestParam(value = "yesOrNo", required = false) String yesOrNo) {
        logger.debug("技术标准页面初始化");
        BasePageParam pageParam = new BasePageParam();
        pageParam.setFilter("standardId", StringUtil.toString(pd141107Param.getPdStdId()));
        List<PD141107Bean> pd141107Beans;
        if(isDebug){
            pd141107Beans=pd141107Logic.getTncStdTestData();
        }
        classesCodet = classesCode;
        breedCodet = breedCode;
        featureCodet = featureCode;
        model.addAttribute("breedCode", breedCodet);
        model.addAttribute("classesCode", classesCodet);
        model.addAttribute("featureCodet", featureCodet);
        model.addAttribute("yesOrNo",yesOrNo);
        pd141107Beans=pd141107Logic.getTncStd(pageParam);
        model.addAttribute("pd141107Beans", pd141107Beans);
        model.addAttribute("pdStd", pd141107Param);
        return "pd/PD141107";
    }

    /**
     * 
     * 保存标准值数据
     * @param param param
     * @param model model
     * @return 实例化
     * @author gyh
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(PD141107Param param, Model model) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(param);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */

        this.pd141107Logic.modifyTncStdData(param);
        return this.init(param, model,classesCodet,breedCodet,featureCodet,null);
    }

}
