package com.msk.bs.controller;

import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.BS2101200Param;
import com.msk.bs.bean.BS2101200RsBean;
import com.msk.bs.bean.BS2101200RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsPageParam;
import com.msk.core.entity.BrBuyerPool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhu_kai1 on 2016/8/24.
 */
@Controller
@RequestMapping("BS2101200")
public class BS2101200Controller extends BaseController {

    /**
     * 查询该买家所在的买家池
     * @param bs2101200Param
     * @return
     */
    @RequestMapping(value = "findBuyerPoolListByBuyerId", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<BS2101200RsBean> findBuyerPoolListByBuyerId(BS2101200Param bs2101200Param){
        BS2101200RsParam rsParam = new BS2101200RsParam();
        rsParam.setBuyerId(bs2101200Param.getBuyerId());
        rsParam.setPageCount(bs2101200Param.getPageSize());
        rsParam.setPageNo((bs2101200Param.getStartPos()/bs2101200Param.getPageSize()) +1);
        return CommRestUtil.getBuyerPoolListByBuyerId(rsParam);
    }


    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String buyerPoolListInit(String buyerId,Model model){
        model.addAttribute("buyerId",buyerId);
        return "/bs/BS2101200";
    }
}
