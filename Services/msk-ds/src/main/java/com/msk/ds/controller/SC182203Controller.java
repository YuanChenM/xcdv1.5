package com.msk.ds.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseController;
import com.msk.ds.bean.SC182203Bean;
import com.msk.ds.bean.SC182203Param;
import com.msk.ds.logic.SC182203Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SC182203Controller
 *
 * @author yi_qixiang
 *
 */
@Controller
@RequestMapping("SC182203")
public class SC182203Controller extends BaseController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SC182203Controller.class);

    /** sc182203Logic */
    @Autowired
    private SC182203Logic sc182203Logic;

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(SC182203Param sc182203Param,Model model) {
//        String url1 = ConfigManager.getMskProductService() + ConfigManager.getPdInfoSearchService();
//        System.out.println("产品信息"+url1);
//        String s = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlPdArtnoSearchServices();
//        System.out.println("产品货号"+s);
//        String ur = ConfigManager.getMskProductService() + ConfigManager.getPdPackageInfoSearchServices();
//        System.out.println("产品包装信息"+ur);
//        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlPdBrandSearchServices();
//        System.out.println("产品品牌"+url);
//        String url6 = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlEpDataListSearchServices();
//        System.out.println("批量查询卖家企业信息"+url6);
//
//        String url2 = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlPropSearchServices();
//        System.out.println("产品生产商"+url2);
//        String url3 = ConfigManager.getMskSellerService() + ConfigManager.getStockOfSupplierListSaveServices();
//        System.out.println("库存"+url3);


        logger.debug("产品批次查询");
        return "ds/SC182203";
    }

    /**
     * 分页查询数据
     *
     * @param  pageParam
     * @return 分页查询数据
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public @ResponseBody
    PageResult<SC182203Bean> search(BasePageParam pageParam) {
        logger.debug("产品批次查询");
        this.setCommonParam(pageParam);

        DbUtils.buildLikeCondition(pageParam, "halfPeriod", DbUtils.LikeMode.FRONT);
//        DbUtils.buildLikeCondition(pageParam, "lgcsName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "proLotNum", DbUtils.LikeMode.FRONT);

        String lgcsName = StringUtil.toSafeString(pageParam.getFilterMap().get("lgcsName"));
        if (!StringUtil.isNullOrEmpty(lgcsName)) {
            String[] lgcsNames = lgcsName.split(",");
            pageParam.getFilterMap().put("lgcsNames", lgcsNames);
        }
        PageResult<SC182203Bean> list = sc182203Logic.findPage(pageParam, SC182203Bean.class);

        return list;

    }

}
