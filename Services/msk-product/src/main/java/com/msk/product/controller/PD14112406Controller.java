package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141103Param;
import com.msk.product.bean.PD14112406Param;
import com.msk.product.bean.PD141124Bean;
import com.msk.product.logic.PD14112406Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "PD14112406")
public class PD14112406Controller extends BaseController {
    @Autowired
    private PD14112406Logic pd14112406Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PD14112406Param pd14112406Param) {
        BaseParam param = new BaseParam();
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  Start */
        super.setCommonParam(param);
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  End */
        String classesCode = pd14112406Param.getClassesCode();
        String machiningCode = pd14112406Param.getMachiningCode();
        String breedCode = pd14112406Param.getBreedCode();
        String featureCode = pd14112406Param.getFeatureCode();
        String weightCode = pd14112406Param.getWeightCode();
        String classestreeCode = "";
        if (StringUtils.isNotBlank(classesCode)) {
            classestreeCode += classesCode;
            model.addAttribute("classesCode", classesCode);
            model.addAttribute("classesName", pd14112406Param.getClassesName());
            if (StringUtils.isNotBlank(machiningCode)) {
                param.setFilter("machiningCode",machiningCode);
                model.addAttribute("machiningCode", machiningCode);
                model.addAttribute("machiningName", pd14112406Param.getMachiningName());
                if (StringUtils.isNotBlank(breedCode)) {
                    param.setFilter("breedCode",breedCode);
                    model.addAttribute("breedCode", breedCode);
                    model.addAttribute("breedName", pd14112406Param.getBreedName());
                    if (StringUtils.isNotBlank(featureCode)) {
                        param.setFilter("featureCode",featureCode);
                        model.addAttribute("featureCode", featureCode);
                        model.addAttribute("featureName", pd14112406Param.getFeatureName());
                        if (StringUtils.isNotBlank(weightCode)) {
                            param.setFilter("weightCode",weightCode);
                            model.addAttribute("weightCode", weightCode);
                            model.addAttribute("weightName", pd14112406Param.getWeightName());
                        }
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(classestreeCode)) {
            param.setFilter("classestreeCode", classestreeCode);
            List<PD141124Bean> listTree = this.pd14112406Logic.findClassesList(param);
            if (listTree != null) model.addAttribute("listTree", listTree);
        }
        return "pd/PD14112406";
    }

    /**
     * 一级类别删除操作 xhy
     *
     * @return
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String delete(PD141103Param bean) {
        if (StringUtils.isBlank(bean.getClassestreeCode1())) throw new BusinessException("数据异常,请联系管理员(一级类别删除)");
        int removeOk = this.pd14112406Logic.removeClassesCode(bean);
        return removeOk + "";
    }

}
