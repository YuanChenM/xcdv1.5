package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141149Bean;
import com.msk.product.bean.PD141149Param;
import com.msk.product.logic.PD141149Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品质量标准指标controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "pd141149")
public class PD141149Controller extends BaseController {
    @Autowired
    private PD141149Logic pd141149Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author xhy
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode,
                       @RequestParam(value = "breedName", required = false) String breedName,
                       @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {

        BaseParam param = new BaseParam();
        param.setFilter("classestreeCode", classestreeCode);
        PdStandard standBean = this.pd141149Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }
        String standardId = standBean.getStandardId().toString();
        model.addAttribute("standardId", standardId);
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName= this.pd141149Logic.findBreedNameAndFea(param);
        if(showName.size()> NumberConst.IntDef.INT_ZERO){
            PD141124showNameBean names = new PD141124showNameBean();
            String feaNames  = "";
            for(PD141124showNameBean beans:showName){
                feaNames += beans.getFeatureNames()+"/";
            }
            names.setFeatureNames(feaNames.substring(NumberConst.IntDef.INT_ZERO, feaNames.length() - NumberConst.IntDef.INT_ONE));
            model.addAttribute("feaNames",names.getFeatureNames());
        }
        model.addAttribute("breedName", breedName);
        //end
        List<PD141149Bean> list = this.pd141149Logic.findInitList(standardId);
        if (list!=null&&list.size()> NumberConst.IntDef.INT_ZERO)  model.addAttribute("pd141107Beans", list);
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD141149";
    }

    //保存修改数据

    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public @ResponseBody String saveAndEdit(Model model, PD141149Param bean, @RequestParam(value = "classesCode", required = false) String classesCode) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        this.pd141149Logic.SaveAndEditTnc(bean,param);
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        return "flag";
    }
}