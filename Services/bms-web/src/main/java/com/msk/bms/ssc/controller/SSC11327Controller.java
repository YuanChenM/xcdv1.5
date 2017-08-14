package com.msk.bms.ssc.controller;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.bean.SSC11327RsBean;
import com.msk.bms.ssc.bean.SSC11327RsParam;
import com.msk.bms.ssc.bean.SSC11327RsPageResult;
import com.msk.bms.ssc.bean.seller.ISL231207Param;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 企业信息列表画面
 * Created by peng_hao on 2016/9/1.
 */
@Controller
@RequestMapping("SSC11327")
public class SSC11327Controller extends BaseController {

    private static Logger logger = getLogger(SSC11327Controller.class);

    /**
     * 初始化页面
     *
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init() {
        logger.debug("企业信息列表页面初始化");
        return "ssc/SSC11327";
    }

    /**
     * 企业信息列表
     *
     * @return 信息
     * @author ph
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    @ResponseBody
    public  PageResult<SSC11327RsBean> search(SSC11327RsParam ssc11327RsParam) {

        PageResult<SSC11327RsBean> result = new PageResult();
        ISL231207Param isl231207Param = new ISL231207Param();

        DbUtils.buildLikeCondition(ssc11327RsParam, "slAccount", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11327RsParam, "epName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11327RsParam, "slTel", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11327RsParam, "cityName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11327RsParam, "lgcsAreaName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(ssc11327RsParam, "slContact", DbUtils.LikeMode.PARTIAL);
        isl231207Param.setFilterMap(ssc11327RsParam.getFilterMap());
        isl231207Param.setPageCount(ssc11327RsParam.getPageSize());
        isl231207Param.setPageNo((ssc11327RsParam.getStartPos() / ssc11327RsParam.getPageSize()) + 1);


        RsResponse<SSC11327RsPageResult> rsResponse = ISSCRestUtil.findEpPageList(isl231207Param);

        List<SSC11327RsBean> epList=null;
        if(rsResponse == null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            epList = new ArrayList<>();
            result.setData(epList);
            result.setRecordsTotal(0);
        }else{
            result.setData(rsResponse.getResult().getEpList());
            result.setRecordsTotal(rsResponse.getResult().getTotalCount());
        }
        return result;
    }

}
