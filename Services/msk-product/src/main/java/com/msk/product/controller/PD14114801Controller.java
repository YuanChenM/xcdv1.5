package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141148MctProBean;
import com.msk.product.logic.PD14114801Logic;
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
 * 产品包装信息controller
 *
 * @author pxg
 */
@Controller
@RequestMapping("pd14114801")
public class PD14114801Controller extends BaseController {
    /**
     * logger
     */
    @Autowired
    private PD14114801Logic pd14114801Logic;


    /**
     * 进入列表页面*
     *
     * @param model model
     * @param bean
     * @return "pd/PD14114801"页面
     * @author xhy
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PD141148MctProBean bean) {
        model.addAttribute("bean", bean);
        return "pd/PD14114801";
    }


    /**
     * 保存修改数据
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PD141148MctProBean> saveMar(PD141148MctProBean bean,
                                  @RequestParam(value = "fixDateString", required = false) String fixDateString) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        return this.pd14114801Logic.saveAndEdit(bean,fixDateString,param);
    }

    //设为禁用数据
    @RequestMapping(value = "forbid",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PD141148MctProBean> forbidMar(PD141148MctProBean bean) {
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(bean);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        bean.setDiscussStatus(NumberConst.IntDef.INT_TWO);//结案
        bean.setFixDate(new Date());
        int modifoyJan = this.pd14114801Logic.modify(bean);
        if (modifoyJan <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("设置市场需求结案日失败!");

        BaseParam param = new BaseParam();
        param.setFilter("standardId",bean.getStandardId().toString());
        param.setFilter("mctStdItemId",bean.getMctStdItemId());
        param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));
        super.setCommonParam(param);
        List<PD141148MctProBean> list = this.pd14114801Logic.queryListMctProJin(param, bean);
        return list;
    }
}
