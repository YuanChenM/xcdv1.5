package com.msk.product.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141105Param;
import com.msk.product.logic.PD141105Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 质量标准设置
 * 
 * @author jiang_nan
 *
 */
@Controller
@RequestMapping("PD141105")
public class PD141105Controller extends BaseController {
    @Autowired
    private PD141105Logic logic;

    /**
     * 初始化质量标准设置
     * 
     * @param standardId 产品标准ID
     * @param classesName 产品类名称
     * @param breedName 产品品种名称
     * @param classesCode 产品类编码
     * @param breedCode 产品品种编码
     * @param featureCode 产品特征编码
     * @param model model
     * @return PD141105页面
     */
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(@RequestParam(required = false) String standardId,
        @RequestParam(required = false) String classesName, @RequestParam(required = false) String breedName,
        @RequestParam(required = false) String classesCode,@RequestParam(required = false) String breedCode,
                       @RequestParam(required = false) String featureCode,@RequestParam(value = "yesOrNo", required = false) String yesOrNo,
        Model model) {
        if (isDebug) {
            model.addAttribute("standardId", "1");
            model.addAttribute("classesName", "鸡产品");
            model.addAttribute("breedName", "鸡大腿");
            model.addAttribute("qualityStandardList", this.logic.findListByStandardId());
        } else {
            model.addAttribute("qualityStandardList", this.logic.findListByStandardId(standardId));
            model.addAttribute("standardId", standardId);
            model.addAttribute("classesName", classesName);
            model.addAttribute("breedName", breedName);
            if(breedCode==""||breedCode==null)model.addAttribute("breedCode",null);
            else model.addAttribute("breedCode",breedCode);

            if(classesCode==""||classesCode==null)model.addAttribute("classesCode",null);
            else model.addAttribute("classesCode",classesCode);

            if(featureCode==""||featureCode==null)model.addAttribute("featureCode",null);
            else model.addAttribute("featureCode",featureCode);

            model.addAttribute("yesOrNo",yesOrNo);
        }
        return "pd/PD141105";
    }

    /**
     * 保存标准值数据
     * 
     * @param param 保存参数
     * @param model Model
     * @return List 页面
     */
    @RequestMapping(value = "save",
        method = RequestMethod.POST)
    public String save(PD141105Param param, Model model) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(param);
        Date date = DateTimeUtil.getCustomerDate();
        param.setActTime(date);
        param.setUpdTime(date);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        this.logic.modify(param);
        return "pd/PD141105";
    }
}
