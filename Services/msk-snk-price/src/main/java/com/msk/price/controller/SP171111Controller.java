package com.msk.price.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.price.bean.MskSlInfoServiceParam;
import com.msk.price.bean.SP171111Bean;
import com.msk.price.bean.SP171111Param;
import com.msk.price.bean.SP171196Bean;
import com.msk.price.logic.SP171111Logic;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 其他供应商申报价格一览Controller
 * Created by 彭浩
 */
@Controller
@RequestMapping("SP171111")
public class SP171111Controller extends BaseController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SP171111Controller.class);

    @Autowired
    private SP171111Logic sp171111Logic;
    @Autowired
    private com.msk.price.logic.SP171196Logic SP171196Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SP171111Param pageParam, Model model) {
        logger.debug("其他供应商申报价格一览初始化");
        model.addAttribute("pricePeriod", pageParam.getPricePeriod());
        model.addAttribute("pdCode", pageParam.getPdCode());
        model.addAttribute("lgcsCode", pageParam.getLgcsCode());
        model.addAttribute("pageType", pageParam.getPageType());
        //获取卖家slcode
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        String slCode = slInfo.getSlCode();
        model.addAttribute("slId", slCode);
        //获取供应商显示项目
        SP171196Bean bean = new SP171196Bean();
        bean.setLgcsCode(pageParam.getLgcsCode());
        bean.setSlCode(pageParam.getSlId());
        SP171196Bean sb = SP171196Logic.getSellerView(bean);
        if (null != sb) {
            model.addAttribute("isSupply", sb.getIsSupply());
            model.addAttribute("isPrice", sb.getIsPrice());
        }else {
            model.addAttribute("isSupply", "否");
            model.addAttribute("isPrice", "否");
        }

        return "sp/SP171111";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SP171111Bean> search(BasePageParam pageParam) {
        logger.debug("其他供应商申报价格一览页面初始化");
        //获取卖家slcode
        LoginUser loginUser = this.getLoginUser();
        MskSlInfoServiceParam slInfo = CommRestUtil.getSlInfo(loginUser.getEmplId());
        String slCode = slInfo.getSlCode();
        SP171196Bean bean = new SP171196Bean();
        bean.setLgcsCode(StringUtil.toString(pageParam.getFilterMap().get("lgcsCode")));
        bean.setSlCode(slCode);
        pageParam.getFilterMap().put("slCode", slCode);

        SP171196Bean sb = SP171196Logic.getSellerView(bean);
        System.out.println("物流区:" + bean.getLgcsCode()+"slCode:"+slCode);
        if (null != sb) {
            pageParam.getFilterMap().put("isSupply",sb.getIsSupply());
            pageParam.getFilterMap().put("isPrice",sb.getIsPrice());
            System.out.println("isSupply:" + sb.getIsSupply() + "isPrice:" + sb.getIsPrice());
        }else {
            pageParam.getFilterMap().put("isSupply","否");
            pageParam.getFilterMap().put("isPrice","否");
        }
        return sp171111Logic.findSP171111List(pageParam);
    }
}
