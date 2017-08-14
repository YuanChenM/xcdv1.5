package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlEnterprise;
import com.msk.seller.bean.SL24110301001Bean;
import com.msk.seller.logic.SL24110301001Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL24110301001")
public class SL24110301001Controller extends BaseUploadController{

    @Autowired
    private SL24110301001Logic sl24110301001Logic;

    /**
     * 初始化页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(String flg,Model mav) {
        mav.addAttribute("flg",flg);
        return "sl/SL24110301001";
    }

    /**
     * 检索页面数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody PageResult<SL24110301001Bean> search(BasePageParam pageParam){
        DbUtils.buildLikeCondition(pageParam, "epName", DbUtils.LikeMode.FRONT);
        return sl24110301001Logic.queryData(pageParam);
    }
}
