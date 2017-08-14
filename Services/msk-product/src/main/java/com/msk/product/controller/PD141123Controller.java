package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClasses;
import com.msk.product.bean.PD141120Bean;
import com.msk.product.bean.PD141123Bean;
import com.msk.product.logic.PD141123Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品加工类型维护
 *
 * @author xuhongayng
 */
@Controller
@RequestMapping("PD141123")
public class PD141123Controller extends BaseController {

    @Autowired
    private PD141123Logic pd141123Logic;

    /**
     * 实例化页面
     *
     * @param model 参数
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classesCode", required = false) String classesCode) {
        BaseParam param = new BaseParam();
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  Start */
        super.setCommonParam(param);
        /** Modfiy: Bug #2442 : 系统长时间未操作后点击菜单报错系统异常   20160905   BY  杨春艳  End */
        List<PdClasses> classesList = this.pd141123Logic.findList(param);
        model.addAttribute("classesList", classesList);
        model.addAttribute("pdMachining", new PD141120Bean());
        return "pd/PD141123";
    }

    /**
     * 查询对应的加工表
     *
     * @param basePageParam
     * @return PageResult
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PD141123Bean> search(BasePageParam basePageParam, @RequestParam(value = "classesCode", required = false) String classesCode) {
        if (classesCode != null && classesCode != "") basePageParam.setFilter("classesCode", classesCode);
        return this.pd141123Logic.findPage(basePageParam, PD141123Bean.class);
    }
}
