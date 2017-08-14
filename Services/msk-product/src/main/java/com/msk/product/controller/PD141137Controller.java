package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.PD141136Bean;
import com.msk.product.bean.PD141137Bean;
import com.msk.product.logic.PD141137Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 产品分类加工信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141137")
public class PD141137Controller extends BaseController {
    @Autowired
    private PD141137Logic pd141137Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PD141136Bean bean) {
        BaseParam param = new BaseParam();
        if (StringUtils.isBlank(bean.getClassesCode()) || StringUtils.isBlank(bean.getSlCode())) {
            throw new BusinessException("参数存在异常,请联系管理员!");
        }

        //查询卖家类型 start
        param.setFilter("slCode", bean.getSlCode());
        PD141136Bean slName = this.pd141137Logic.findSlType(param);
        bean.setSlMainclass(slName.getSlMainclass());
        bean.setSlConFlg(slName.getSlConFlg());
        //获取classestreeCode   start
        if (StringUtils.isNotBlank(bean.getClassesCode())) {
            param.setFilter("classestreeCode", bean.getClassesCode());
            if (StringUtils.isNotBlank(bean.getMachiningCode())) {
                param.setFilter("classestreeCode", bean.getClassesCode() + bean.getMachiningCode());
                if (StringUtils.isNotBlank(bean.getBreedCode())) {
                    param.setFilter("classestreeCode", bean.getClassesCode() + bean.getMachiningCode() + bean.getBreedCode());
                }
            }
        }
        param.setFilter("delFlg", String.valueOf(NumberConst.IntDef.INT_ZERO));
        //获取列表头信息
        PdClassestreeMat pdClassestreeMat = this.pd141137Logic.queryClassMatData(param);
        if (pdClassestreeMat == null) {
            throw new BusinessException("没有查询到数据!");
        }
        model.addAttribute("pdClassestreeMat", pdClassestreeMat);
        //end
        param.setFilter("classesCode", bean.getClassesCode());
        param.setFilter("machiningCode", bean.getMachiningCode());
        param.setFilter("breedCode", bean.getBreedCode());
        param.setFilter("slCode",bean.getSlCode());
        List<PD141137Bean> listAll = this.pd141137Logic.findListAll(param);
        model.addAttribute("listAll", listAll);
        model.addAttribute("bean", bean);
        return "pd/PD141137";
    }

}
