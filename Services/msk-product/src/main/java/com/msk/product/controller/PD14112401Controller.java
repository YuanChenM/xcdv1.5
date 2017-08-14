package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141103Param;
import com.msk.product.logic.PD14112401Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 二级加工分类操作
 *
 * @author xhy
 */
@Controller
@RequestMapping("PD14112401")
public class PD14112401Controller extends BaseController {
    @Autowired
    private PD14112401Logic pd14112401Logic;

    /**
     * 实例化页面
     *
     * @param bean  参数
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(
            Model model, PD141103Param bean) {
        model.addAttribute("classestree", bean);
        return "pd/PD14112401";
    }

    /**
     * 保存二级类别信息  mac
     *
     * @param bean  参数
     * @return 页面
     */
    @RequestMapping(value = "addAndEdit",
            method = RequestMethod.POST)
    public @ResponseBody String save(PD141103Param bean) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        //添加操作 同时添加machining表数据
        if (StringUtils.isBlank(bean.getClassestreeCode2())) {
            int saveClassesTree = this.pd14112401Logic.saveClassesTree(bean,param);
            return saveClassesTree + "";
        } else {
           /* 修改操作*/
            int updataClassesTree = this.pd14112401Logic.modifyClassesTreeLevel2(bean,param);
            return updataClassesTree + "";
        }
    }
    /**
     * 二级类别删除操作 xhy
     *
     * @return
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String delete(PD141103Param bean) {
        if (StringUtils.isBlank(bean.getClassestreeCode2())||StringUtils.isBlank(bean.getClassestreeCode1()))
            throw new BusinessException("数据异常,请联系管理员(二级类别删除)");
        int removeOk =  this.pd14112401Logic.removeMachining(bean);
        return removeOk+"";
    }

}
