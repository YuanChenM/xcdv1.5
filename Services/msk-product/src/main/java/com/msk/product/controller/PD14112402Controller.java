package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141103Param;
import com.msk.product.logic.PD14112402Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 三级产品分类操作
 *
 * @author xhy
 */
@Controller
@RequestMapping("PD14112402")
public class PD14112402Controller extends BaseController {
    @Autowired
    private PD14112402Logic pd14112402Logic;

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
        //三级分类类目操作
        model.addAttribute("classestree", bean);
        return "pd/PD14112402";
    }

    /**
     * 保存修改三级类别信息
     *
     * @param bean
     * @return 页面
     */
    @RequestMapping(value = "addAndEdit",
            method = RequestMethod.POST)
    public @ResponseBody String save(PD141103Param bean) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        //添加操作 BREED添加
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  Start */
        if (StringUtils.isBlank(bean.getClassestreeCode3())) {
            int saveClassesTree = this.pd14112402Logic.saveClassesTree(bean,param);
            return saveClassesTree + "";
        } else {
           /* 修改操作*/
            int updataClassesTree = this.pd14112402Logic.modifyClassesTreeLevel2(bean,param);
            return updataClassesTree + "";
        }
        /** Modfiy: Bug #2429 : 新产品系统无法新增产品二级分类三级分类等数据   20160905   BY  杨春艳  End */
    }

    /**
     * 三级类别删除操作 xhy
     *
     * @return
     */
    @RequestMapping(value = "delete",
            method = RequestMethod.POST)
    public
    @ResponseBody
    String delete(PD141103Param bean) {
        if (StringUtils.isBlank(bean.getClassestreeCode3())||StringUtils.isBlank(bean.getClassestreeCode2())||StringUtils.isBlank(bean.getClassestreeCode1()))
            throw new BusinessException("数据异常,请联系管理员(三级级类别删除)");
        int removeOk =  this.pd14112402Logic.removeBreed(bean);
        return removeOk+"";
    }

}
