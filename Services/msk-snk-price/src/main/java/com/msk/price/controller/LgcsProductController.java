package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171193Result;
import com.msk.price.logic.LgcsProductLogic;
import com.msk.price.logic.SP171193Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhu_kai1 on 2016/6/16.
 */
@Controller
@RequestMapping("lgcsProduct")
public class LgcsProductController extends BaseController {

    @Autowired
    private  LgcsProductLogic lgcsProductLogic;
    @Autowired
    private SP171193Logic sp171193Logic;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(BaseParam param,Model model) throws Exception{
        super.setCommonParam(param);
        lgcsProductLogic.dataResolve(param);
        //通道等级下拉
        BasePageParam basePageParam = new BasePageParam();
        PageResult<SP171193Result> pageResult = sp171193Logic.findWayDetail(basePageParam);
        if (null != pageResult && null != pageResult.getData() && pageResult.getData().size() > 0) {
            model.addAttribute("wayList", pageResult.getData());
        }
        return "sp/SP171191";
    }
}
