package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.core.entity.PdClassestree;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141136Bean;
import com.msk.product.logic.PD141136Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 市场需求审核注册修改添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD14113601")
public class PD14113601Controller extends BaseController {
    @Autowired
    private PD141136Logic pd141136Logic;

    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author pxg
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, PdStandard bean) {
        BaseParam param = new BaseParam();
        param.setFilter("treeLevel", StringUtil.toSafeString(NumberConst.IntDef.INT_ONE));
        List<PdClassestree> classesList = this.pd141136Logic.findListClasses(param);
        model.addAttribute("classesList", classesList);
        model.addAttribute("bean", bean);
        return "pd/PD14113601";
    }

    /**
     * 查询列表数据*
     *
     * @param pageParam 分页参数
     * @return PageResult
     * @author pxg
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<PD141136Bean> search(BasePageParam pageParam) {
        return this.pd141136Logic.findListSl(pageParam);
    }
}
