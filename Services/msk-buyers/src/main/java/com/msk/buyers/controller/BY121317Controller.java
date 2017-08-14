package com.msk.buyers.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.buyers.bean.BY121316Bean;
import com.msk.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("BY121317")
public class BY121317Controller extends BaseController {


    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121317Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家商城账号信息");

        model.addAttribute("buyerId", buyerId);
        return "buyers/BY121317";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BY121316Bean> search(BasePageParam basePageParam) {
        logger.info("买家商城账号信息列表");
        PageResult<BY121316Bean> result = new PageResult<BY121316Bean>();
        /*BY121316Bean been1 = new BY121316Bean();
        been1.setTelNo("");
        been1.setAccountName("");
        been1.setAccountPass("");
        been1.setBuyerSingleWechat("");
        been1.setBuyerQq("");
        been1.setBuyerName("");
        BY121316Bean been2 = new BY121316Bean();

        been2.setAccountName("");
        been2.setAccountPass("");
        been2.setTelNo("");
        been2.setBuyerSingleWechat("");
        been2.setBuyerQq("");
        been1.setBuyerName("");
        List<BY121316Bean> list = new ArrayList<>();
        list.add(been1);
        list.add(been2);*/

        List<BY121316Bean> list = new ArrayList<>();
        result.setData(list);
        result.setRecordsTotal(list.size());

        return result;
    }


}
