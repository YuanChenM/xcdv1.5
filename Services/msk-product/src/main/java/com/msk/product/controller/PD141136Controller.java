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
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "PD141136")
public class PD141136Controller extends BaseController {
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
    public String init(Model model) {
        BaseParam param = new BaseParam();
        param.setFilter("treeLevel", StringUtil.toSafeString(NumberConst.IntDef.INT_ONE));
        List<PdClassestree> classesList = this.pd141136Logic.findListClasses(param);
        model.addAttribute("classesList",classesList);
        return "pd/PD141136";
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
    public @ResponseBody
    PageResult<PD141136Bean> search(BasePageParam pageParam) {
        return this.pd141136Logic.findListSl(pageParam);

    }


    /**
     * 查询列表数据*
     *
     * @param bean
     * @return PageResult
     * @author pxg
     */
    @RequestMapping(value = "query",
            method = RequestMethod.POST)
    public @ResponseBody List<PdClassestree> search(PdStandard bean) {
        BaseParam param = new BaseParam();
        if(StringUtils.isNotBlank(bean.getClassesCode())){
            param.setFilter("parentCode",bean.getClassesCode());
            if(StringUtils.isNotBlank(bean.getMachiningCode())){
                param.setFilter("parentCode",bean.getClassesCode()+bean.getMachiningCode());
                if (StringUtils.isNotBlank(bean.getBreedCode())){
                    param.setFilter("parentCode",bean.getClassesCode()+bean.getMachiningCode()+bean.getBreedCode());
                }
            }
        }
        return this.pd141136Logic.findListClasses(param);
    }
}
