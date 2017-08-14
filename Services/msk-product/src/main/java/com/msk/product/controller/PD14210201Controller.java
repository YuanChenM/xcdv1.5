package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD14210201Bean;
import com.msk.product.logic.PD14210201Logic;
import com.msk.product.logic.ProductLogic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 品种产品目录报表
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "PD14210201")
public class PD14210201Controller extends BaseController {
    @Autowired
    private PD14210201Logic pd14210201Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classesCode", defaultValue = "01") String classesCode) {
        model.addAttribute("classesCode", classesCode);
        BaseParam param = new BaseParam();
        param.setFilter("classesCode", classesCode);
        model.addAttribute("machiningInfo", this.productLogic.findPdMachining(param));
        return "pd/PD14210201";
    }

    /**
     * 查询页面显示数据
     *
     * @return
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PD14210201Bean> search(BasePageParam pageParam, @RequestParam(value = "classesCode", required = false) String classesCode) {
        if (StringUtils.isNotBlank(classesCode)) {
            pageParam.setFilter("classesCode", classesCode);
            DbUtils.buildLikeCondition(pageParam, "breedName", DbUtils.LikeMode.FRONT);
            DbUtils.buildLikeCondition(pageParam, "gradeName", DbUtils.LikeMode.FRONT);
            return this.pd14210201Logic.findPage(pageParam, PD14210201Bean.class);
        }
        PageResult<PD14210201Bean> bean = new PageResult<PD14210201Bean>();
        bean.setData(new ArrayList<PD14210201Bean>());
        return bean;
    }
}
