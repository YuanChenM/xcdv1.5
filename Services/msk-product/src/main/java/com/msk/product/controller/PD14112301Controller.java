package com.msk.product.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141123Bean;
import com.msk.product.logic.PD14112301Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品加工类型维护
 *
 * @author xuhongayng
 */
@Controller
@RequestMapping("PD14112301")
public class PD14112301Controller extends BaseController {

    @Autowired
    private PD14112301Logic pd14112301Logic;


    /**
     * 实例化页面
     *
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PD141123Bean bean) {
        if (StringUtils.isBlank(bean.getClassesCode())) throw new BusinessException("数据异常!请联系管理员");
        model.addAttribute("bean", bean);
        return "pd/PD14112301";
    }

    /**
     * 加工程度添加修改操作
     *
     * @param bean 参数
     * @param mv   参数
     * @return String
     */

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveAndEdit(Model mv, PD141123Bean bean) {
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  Start */
        super.setCommonParam(bean);
        /**Add: 横展开添加共通设置 2016/09/09   BY  任强  End */
        if (StringUtils.isBlank(bean.getClassesCode())) {
            throw new BusinessException("数据异常!请联系管理员");
        }
        if (!StringUtils.isNotEmpty(bean.getMachiningCode())) {
            //添加操作
            int saveYes = this.pd14112301Logic.saveMacANDMacRef(bean);
            if (saveYes <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("数据插入失败!请联系管理员");
            return "{flag:'1'}";
        } else {
            //修改操作
            int modify = this.pd14112301Logic.modifyMacRef(bean);
            if (modify <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("数据插入失败!请联系管理员");
            return "{flag:'1'}";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteMacRef(Model mv, PD141123Bean bean) {
        int deletaMac = this.pd14112301Logic.remove(bean);
        if (deletaMac <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("数据插入失败!请联系管理员");
        return "{flag:'1'}";
    }
}
