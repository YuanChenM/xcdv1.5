package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141151Param;
import com.msk.product.bean.PD141151ParentBean;
import com.msk.product.logic.PD141151Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品种源标准指标controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "pd141151")
public class PD141151Controller extends BaseController {
    @Autowired
    private PD141151Logic pd141151Logic;

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
        PdStandard standBean = this.pd141151Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }
        String standardId = standBean.getStandardId().toString();
        model.addAttribute("standardId", standardId);
        param.setFilter("standardId", standardId);
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName= this.pd141151Logic.findBreedNameAndFea(param);
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

        int size = this.pd141151Logic.getCountNum(param);
        if (size > NumberConst.IntDef.INT_ZERO) { //有数据
        /*查询一级类目*/
            List<PD141151ParentBean> list = this.pd141151Logic.findList(param);
            //有
            for (PD141151ParentBean parentbeans : list) {
                List<PD141151Param> list2 = new ArrayList<PD141151Param>();
                param.setFilter("parentId", parentbeans.getSftStdItemId());
                List<PD141151Param> level2List = this.pd141151Logic.findListLevel2(param);
                for (PD141151Param level2 : level2List) {
                    PD141151Param pd141151Param = new PD141151Param();
                    param.setFilter("sftStdItemId", level2.getSftStdItemId());
                    pd141151Param = this.pd141151Logic.findOneSftStd(param);
                    if(null != pd141151Param) {
                        pd141151Param.setSftStdItemName(level2.getSftStdItemName());
                        list2.add(pd141151Param);
                    }
                }
                parentbeans.setSftList(list2);
            }
            model.addAttribute("list", list);

        } else {
           /* 获取一级类目*/
            List<PD141151ParentBean> list = this.pd141151Logic.findList(param);
            for (PD141151ParentBean parentbeans : list) {
                param.setFilter("parentId", parentbeans.getSftStdItemId());
                List<PD141151Param> level2List = this.pd141151Logic.findListLevel2(param);
                parentbeans.setSftList(level2List);
            }
            model.addAttribute("list", list);
        }
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD141151";
    }

    /**
     * 数据保存修改
     * @param model
     * @param bean
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public String saveAndEdit(Model model, PD141151Param bean, @RequestParam(value = "classesCode", required = false) String classesCode) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        this.pd141151Logic.saveAndEdit(bean,param);
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        return "pd/PD141124";
    }
}