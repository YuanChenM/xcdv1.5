package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.SlEnterprise;
import com.msk.core.entity.SlPdBrand;
import com.msk.seller.bean.SL241101Bean;
import com.msk.seller.bean.SL241116Bean;
import com.msk.seller.logic.ProductLogic;
import com.msk.seller.logic.Sl241101Logic;
import com.msk.seller.logic.Sl241116Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 卖家审批审核列表Controller.
 *
 * @author gyh
 */
@Controller
@RequestMapping(value = "SL241127")
public class SL241127Controller extends BaseUploadController {
    @Autowired
    private Sl241116Logic sl241116Logic;
    @Autowired
    private Sl241101Logic sl241101Logic;
    @Autowired
    private ProductLogic productLogic;

    /**
     * 实例化页面
     *
     * @return 卖家产品信息页面
     */
    @RequestMapping(value = "init/{slCode}",
            method = RequestMethod.POST)
    private String init(Model model, @PathVariable("slCode") String slCode) {
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPaging(false);
        basePageParam.setFilter("slCode", slCode);
        List<SL241101Bean> sl241101Beans = this.sl241101Logic.findPageResultList(basePageParam);
        if (!CollectionUtils.isEmpty(sl241101Beans)) {
            if (sl241101Beans.size() > 0) {
                model.addAttribute("slShowName", sl241101Beans.get(0).getSlShowName());
            }
        }
        BaseParam param = new BaseParam();
        param.setFilter("slCode", slCode);
        //调用接口产品类别信息
        List<PdClasses> pdClassess = productLogic.findPdClasses(param);
        model.addAttribute("pdClasses", pdClassess);
        List<SlEnterprise> slEnterprises = this.sl241116Logic.findEpInfo(param);
        model.addAttribute("slEnterprise", slEnterprises);
        List<SlPdBrand> slPdBrands = this.sl241116Logic.findSlPdBrand(param);
        model.addAttribute("slPdBrand", slPdBrands);
        model.addAttribute("slCode", slCode);
        model.addAttribute("slProduct", new SL241116Bean());
        return "sl/SL241127";
    }
}
