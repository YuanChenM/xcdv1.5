package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdOrgStdItem;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD141146Bean;
import com.msk.product.logic.PD141146Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "pd141146")
public class PD141146Controller extends BaseController {
    @Autowired
    private PD141146Logic pd141146Logic;

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
        super.setCommonParam(param);
        param.setFilter("classestreeCode", classestreeCode);
        PdStandard standBean = this.pd141146Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }
        String standardId = standBean.getStandardId().toString();
        model.addAttribute("standardId", standardId);
        param.setFilter("standardId", standardId);
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName = this.pd141146Logic.findBreedNameAndFea(param);
        if (showName.size() > NumberConst.IntDef.INT_ZERO) {
            PD141124showNameBean names = new PD141124showNameBean();
            String feaNames = "";
            for (PD141124showNameBean beans : showName) {
                feaNames += beans.getFeatureNames() + "/";
            }
            names.setFeatureNames(feaNames.substring(NumberConst.IntDef.INT_ZERO, feaNames.length() - NumberConst.IntDef.INT_ONE));
            model.addAttribute("feaNames", names.getFeatureNames());
        }
        model.addAttribute("breedName", breedName);
        //end

        List<PD141146Bean> list = this.pd141146Logic.findList(param);
        if (list.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("list", list);
        } else {
            List<PdOrgStdItem> itemList = this.pd141146Logic.findListPdOrgStd(param);
            if (itemList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PdOrgStdItem beans : itemList) {
                    PD141146Bean stdNull = new PD141146Bean();
                    stdNull.setStandardId(standBean.getStandardId());
                    stdNull.setOrgStdItemId(beans.getOrgStdItemId());
                    stdNull.setOrgStdItemName(beans.getOrgStdItemName());
                    list.add(stdNull);
                }
                model.addAttribute("list", list);
            }
        }
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD141146";
    }

    /**
     * 保存修改数据
     * @param model
     * @param bean
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public String saveAndEdit(Model model, PD141146Bean bean,@RequestParam(value = "classesCode", required = false) String classesCode) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        this.pd141146Logic.saveAndEdit(bean,param);
        if(StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        return "pd/PD141146";
    }
}