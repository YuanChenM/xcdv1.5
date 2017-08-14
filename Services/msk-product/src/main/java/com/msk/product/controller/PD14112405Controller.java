package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141103Param;
import com.msk.product.bean.PD141124Bean;
import com.msk.product.logic.PD14112405Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 第五级产品分类操作
 *
 * @author xhy
 */
@Controller
@RequestMapping("PD14112405")
public class PD14112405Controller extends BaseController {
    @Autowired
    private PD14112405Logic pd14112405Logic;

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
        //五级分类类目操作
        model.addAttribute("classestree", bean);
        return "pd/PD14112405";
    }


    /**
     * 保存修改五级类别信息
     *
     * @param model
     * @param bean
     * @return 页面
     */
    @RequestMapping(value = "addAndEdit",
            method = RequestMethod.POST)
    public  @ResponseBody String save(Model model, PD141103Param bean) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        //添加操作 产品净重添加操作
        if (StringUtils.isNotBlank(bean.getNormsOut())) {
            int saveClassesTree = this.pd14112405Logic.saveClassesTree(bean,param);
            return saveClassesTree + "";
        }
        return NumberConst.IntDef.INT_ZERO + "";
    }

    /**
     * 六级类别删除操作 xhy
     *
     * @return
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String delete(PD141103Param bean) {
        if (StringUtils.isBlank(bean.getClassestreeCode6())
                ||StringUtils.isBlank(bean.getClassestreeCode5())
                ||StringUtils.isBlank(bean.getClassestreeCode4())
                ||StringUtils.isBlank(bean.getClassestreeCode3())
                ||StringUtils.isBlank(bean.getClassestreeCode2())
                ||StringUtils.isBlank(bean.getClassestreeCode1()))
            throw new BusinessException("数据异常,请联系管理员(五级类别删除)");
        int removeOk =  this.pd14112405Logic.removeNorms(bean);
        return removeOk+"";
    }
}