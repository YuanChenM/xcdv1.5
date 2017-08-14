package com.msk.product.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PD141130Bean;
import com.msk.product.logic.PD141130Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 市场需求审核注册修改添加controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "PD141130")
public class PD141130Controller extends BaseController {
    @Autowired
    private PD141130Logic pd141130Logic;

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
        return "pd/PD141130";
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
    PageResult<PD141130Bean> search(BasePageParam pageParam) {
        /*DbUtils.buildLikeCondition(pageParam, "normsSuttle", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsError", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsNumber", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsSize", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsTexture", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsOut", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsKg", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsOutSize", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "normsOutTexture", DbUtils.LikeMode.FRONT);*/
        return pd141130Logic.queryList();
    }
}
