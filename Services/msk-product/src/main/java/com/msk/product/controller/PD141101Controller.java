package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.common.consts.CodeMasterConst;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdFeature;
import com.msk.product.bean.ProductClasses;
import com.msk.product.logic.PD141101Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 产品类别信息controller
 *
 * @author gyh
 */
@Controller
@RequestMapping(value = "PD141101")
public class PD141101Controller extends BaseController {
    @Autowired
    private PD141101Logic pd141101Logic;

    /**
     * 实例化页面
     *
     * @param pdFeature pdFeature
     * @param model model
     * @return 界面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(PdFeature pdFeature, Model model,@RequestParam(value = "classesCode",required = false)String classesCode,
                       @RequestParam(value = "breedCode",required = false)String breedCode,@RequestParam(value = "featureCode",required = false)String featureCode) {
        BaseParam param = new BaseParam();

        if(breedCode=="")model.addAttribute("breedCode",null);
        else model.addAttribute("breedCode",breedCode);

        if(classesCode=="")model.addAttribute("classesCode",null);
        else model.addAttribute("classesCode",classesCode);

        if(featureCode=="")model.addAttribute("featureCode",null);
        else model.addAttribute("featureCode",featureCode);

        param.setFilter("classesCode", DbUtils.buildLikeCondition(pdFeature.getClassesCode(), DbUtils.LikeMode.FRONT));
        param.setFilter("breedCode", DbUtils.buildLikeCondition(pdFeature.getBreedCode(), DbUtils.LikeMode.FRONT));
        param.setFilter("featureCode", DbUtils.buildLikeCondition(pdFeature.getFeatureCode(), DbUtils.LikeMode.FRONT));
        List<ProductClasses> productClasses = null;
        if (isDebug) {
            //productClasses = this.pd141101Logic.getProductClassesInfo();
        } else {
            productClasses = this.pd141101Logic.findPageList(param, ProductClasses.class);
        }
        model.addAttribute("productClasses", productClasses);
        model.addAttribute("pdFeature", pdFeature);
        return "pd/PD141101";
    }

    /**
     *
     * 删除或废除产品类别信息
     *
     * @param editType editType
     * @param pdClasses pdClasses
     * @param model model
     * @return 页面
     * @author gyh
     */
    @RequestMapping(value = "init/{editType}",
            method = RequestMethod.POST)
    public String init(@PathVariable("editType") String editType, PdClasses pdClasses, Model model) {
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", pdClasses.getClassesCode());
        param.setFilter("delflg", CodeMasterConst.IsAvailable.NOAVAILABLE);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(param);
        Date date = DateTimeUtil.getCustomerDate();
        param.setActTime(date);
        param.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if ("close".equals(editType)) {
            int rs = this.pd141101Logic.modify(param);
            if (rs <= 0) {
                throw new BusinessException("废除失败，请重新操作！");
            }
        } else if ("delete".equals(editType)) {
            int rs = this.pd141101Logic.deleteClasses(param);
            if (rs <= 0) {
                throw new BusinessException("删除失败，请重新操作！");
            }
        }
        model.addAttribute("pdFeature", new PdFeature());
        return "pd/PD141101";
    }
}
