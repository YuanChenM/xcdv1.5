package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductPageResult;
import com.msk.seller.bean.SL241129Bean;
import com.msk.seller.utils.SLControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 卖家申请产品controller
 *
 * @author pxg
 */
@Controller
@RequestMapping(value = "SL241129")
public class SL241129Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SL241129Controller.class);

    /**
     * 实例化页面
     * @return 界面
     * @author gyh
     */
    @RequestMapping(value = "showNorms",
            method = RequestMethod.POST)
    public String showNorms(Model model,SL241129Bean bean) {
        model.addAttribute("bean",bean);
        return "sl/SL24112901";
    }

    /**
     * 实例化页面
     * @return 界面
     * @author gyh
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model,@RequestParam(required = false)String slCode) {
        model.addAttribute("slCode",slCode);
        return "sl/SL241129";
    }

    /**
     * 卖家申请产品列表。替代旧search()。
     * Created by xia_xiaojie on 2016/6/20.
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<PDInfoResult> search(BasePageParam pageParam) {
        logger.debug("卖家申请产品列表");
        Map<String, Object> filterMap = pageParam.getFilterMap();
        PDInfoParam pdiParam = new PDInfoParam();
        pdiParam.setPaging(true);
        pdiParam.setStartSize(pageParam.getStartPos());
        pdiParam.setEndSize(pageParam.getPageSize());
        pdiParam.setFilterMap(filterMap);

        ProductPageResult pdPageResult = SLControllerUtil.getProviderPackages(pdiParam);
        PageResult<PDInfoResult> pageResult = new PageResult<PDInfoResult>();
        pageResult.setData(pdPageResult.getPdInfo());
        pageResult.setRecordsTotal(pdPageResult.getTotalCount());
        return pageResult;
    }

}
