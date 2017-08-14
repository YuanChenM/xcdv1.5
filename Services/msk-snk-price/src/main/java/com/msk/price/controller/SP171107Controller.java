package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.price.bean.SP171107Bean;
import com.msk.price.bean.SP171107Param;
import com.msk.price.bean.SP171196Bean;
import com.msk.price.logic.SP171107Logic;
import com.msk.price.logic.SP171196Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 其他供应商申报数量一览控制层
 * Created by xu_wei on
 */
@Controller
@RequestMapping("SP171107")
public class SP171107Controller extends BaseController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SP171107Controller.class);

    @Autowired
    private SP171107Logic SP171107Logic;

    @Autowired
    private SP171196Logic SP171196Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SP171107Param pageParam, Model model) {
        logger.debug("其他供应商申报数量一览初始化");
        model.addAttribute("demandYearmonth", pageParam.getDemandYearmonth());
        model.addAttribute("pdCode", pageParam.getPdCode());
        model.addAttribute("lgcsCode", pageParam.getLgcsCode());
        model.addAttribute("slCode", pageParam.getSlCode());
        model.addAttribute("demandId", pageParam.getDemandId());
        //获取供应商显示项目
        SP171196Bean bean = new SP171196Bean();
        bean.setLgcsCode(pageParam.getLgcsCode());
        bean.setSlCode(pageParam.getSlCode());
        SP171196Bean sb = SP171196Logic.getSellerView(bean);
        if (null != sb) {
            model.addAttribute("isSupply", sb.getIsSupply());
            model.addAttribute("isNum", sb.getIsNum());
        }else {
            model.addAttribute("isSupply", "否");
            model.addAttribute("isNum", "否");
        }
        return "sp/SP171107";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171107Bean> search(BasePageParam pageParam) {
        logger.debug("其他供应商申报数量一览页面初始化");
        return SP171107Logic.findSP171107List(pageParam);
    }
}
