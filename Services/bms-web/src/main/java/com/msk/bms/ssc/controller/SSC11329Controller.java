package com.msk.bms.ssc.controller;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;

import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.bean.ISC182209RsResult;
import com.msk.bms.ssc.bean.seller.ISL231208Param;
import com.msk.bms.ssc.bean.seller.SL241116Bean;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;


import com.msk.ssc.bean.SSC11329Bean;
import com.msk.ssc.bean.SSC11329Param;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;

/**
* Created on 2016/8/3.
*/
@Controller
@RequestMapping("SSC11329")
public class SSC11329Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11329Controller.class);
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SSC11329Param param,Model model) {
        logger.debug("卖家产品信息及状态审核初始化");

        model.addAttribute("ssc11329Param",param);


        return "ssc/SSC11329";
    }

    /**
     *卖家产品信息及状态审核详细
     * @param ssc11329Param
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SL241116Bean> search(SSC11329Param ssc11329Param) {
        logger.info("卖家产品信息及状态详细");
        ISL231208Param param=new ISL231208Param();
        PageResult<SSC11329Bean> result = new PageResult<>();
        param.setPageCount(ssc11329Param.getPageSize());
        param.setPageNo((ssc11329Param.getStartPos() / ssc11329Param.getPageSize()) + 1);
        param.setSlCode(ssc11329Param.getSlCode());

        DbUtils.buildLikeCondition(ssc11329Param, "slPdArtNo", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "pdClassesName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "machiningName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "pdBreedName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "prodEpName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "brandName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "pdFeatureName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11329Param, "weightName", DbUtils.LikeMode.PARTIAL);
        param.setFilterMap(ssc11329Param.getFilterMap());

        RsResponse<ISC182209RsResult> rs = ISSCRestUtil.searchSellerProductInfo(param);
        PageResult<SL241116Bean> been=new PageResult<>();
        if(rs.getResult()!=null){
            been.setData(rs.getResult().getEpPdList());
            been.setRecordsTotal(rs.getResult().getTotalCount());
        }else{
            been.setData(new ArrayList<SL241116Bean>());
            been.setRecordsTotal(0);
        }
        return been;
    }


    @RequestMapping(value = "showImage", method = RequestMethod.POST)
    public String showImage(SL241116Bean sl241116Bean, Model model) {
        model.addAttribute("sl241116Bean", sl241116Bean);
        return "ssc/SSC1133001";
    }
}
