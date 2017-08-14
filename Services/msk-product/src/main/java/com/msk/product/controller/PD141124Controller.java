package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdWeight;
import com.msk.product.bean.PD141103Param;
import com.msk.product.bean.PD141124Bean;
import com.msk.product.logic.PD14112404Logic;
import com.msk.product.logic.PD141124Logic;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "PD141124")
public class PD141124Controller extends BaseController {
    @Autowired
    private PD141124Logic pd141124Logic;
    @Autowired
    private PD14112404Logic pd14112404Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {
        BaseParam param = new BaseParam();
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  Start */
        super.setCommonParam(param);
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  End */
        param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_ONE));
        //显示一级类目下拉框
        List<PD141124Bean> list = this.pd141124Logic.findList(param);
        if(!CollectionUtils.isEmpty(list)) model.addAttribute("classesList", list);

        if (StringUtils.isNotBlank(classesCode)) {
            model.addAttribute("classesCode", classesCode);
            param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_TWO));
            param.setFilter("parentCode", classesCode);
            //显示一级类目下拉框
            List<PD141124Bean> machinings = this.pd141124Logic.findList(param);
            if (!CollectionUtils.isEmpty(machinings))
                model.addAttribute("machiningList", machinings);
        }
        if (StringUtils.isNotBlank(machiningCode)) {
            model.addAttribute("machiningCode", machiningCode);
            param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_THREE));
            param.setFilter("parentCode", machiningCode);
            //显示一级类目下拉框
            List<PD141124Bean> breeds = this.pd141124Logic.findList(param);
            if(!CollectionUtils.isEmpty(breeds))
                model.addAttribute("breedList", breeds);
        }
        if (StringUtils.isNotBlank(breedCode)) {
            model.addAttribute("breedCode", breedCode);
            param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_FOUR));
            param.setFilter("parentCode", breedCode);
            //显示一级类目下拉框
            List<PD141124Bean> features = this.pd141124Logic.findList(param);
            if(!CollectionUtils.isEmpty(features))
                model.addAttribute("featureList", features);
        }
        if (StringUtils.isNotBlank(featureCode)) {
            model.addAttribute("featureCode", featureCode);
            param.setFilter("level", String.valueOf(NumberConst.IntDef.INT_FIVE));
            param.setFilter("parentCode", featureCode);
            //显示一级类目下拉框
            List<PD141124Bean> weights = this.pd141124Logic.findList(param);
            if(!CollectionUtils.isEmpty(weights))
                model.addAttribute("weightList", weights);
        }
        if (StringUtils.isNotBlank(weightCode)) {
            model.addAttribute("weightCode", weightCode);
        }
        return "pd/PD141124";
    }

}
