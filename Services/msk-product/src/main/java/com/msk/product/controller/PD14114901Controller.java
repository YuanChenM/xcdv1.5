package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PDtncMarkeyBean;
import com.msk.product.bean.PDtncMarkeyJinBean;
import com.msk.product.logic.PD14114901Logic;
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
@RequestMapping("pd14114901")
public class PD14114901Controller extends BaseController {
    /**
     * logger
     */
    @Autowired
    private PD14114901Logic pd14114901Logic;


    /**
     * 进入列表页面*
     *
     * @param model model
     * @param bean
     * @return "pd/PD14114901"页面
     * @author xhy
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PDtncMarkeyBean bean, @RequestParam(required = false) String fixDateShow,
                       @RequestParam(value = "raiseDateShow", required = false) String raiseDateShow
                       ) {
        model.addAttribute("bean", bean);
        model.addAttribute("fixDateShow", fixDateShow);
        model.addAttribute("raiseDateShow", raiseDateShow);
        return "pd/PD14114901";
    }


    /**
     * 保存修改数据
     */
    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PDtncMarkeyBean> saveMar(Model model, PDtncMarkeyBean bean, @RequestParam(value = "raiseDateString", required = false) String raiseDateString,
                                  @RequestParam(value = "fixDateString", required = false) String fixDateString) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
       return this.pd14114901Logic.saveAndEdit(bean, raiseDateString, fixDateString,param);
    }

    //设为禁用数据
    @RequestMapping(value = "forbid",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PDtncMarkeyJinBean forbidMar(PDtncMarkeyBean bean) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        bean.setDiscussStatus(NumberConst.IntDef.INT_TWO);//结案
        bean.setFixDate(new Date());
        bean.setUpdId(param.getUpdId());
        int modifoyJan = this.pd14114901Logic.modify(bean);
        if (modifoyJan <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("设置市场需求结案日失败!");


        param.setFilter("standardId",bean.getStandardId().toString());
        param.setFilter("tncStdItemId",bean.getTncStdItemId());
        param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));

        List<PDtncMarkeyBean> list = this.pd14114901Logic.queryListMar(param,bean);
        //更新禁止日
        List<PDtncMarkeyBean> listMarJin = this.pd14114901Logic.queryListMarJin(param, bean);

        PDtncMarkeyJinBean beans = new PDtncMarkeyJinBean();
        beans.setListMar(list);
        beans.setListMarJin(listMarJin);
        return beans;
    }
}
