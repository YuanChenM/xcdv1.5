package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscDifferBasic;
import com.msk.core.entity.SscDifferDetail;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11306Logic;
import com.msk.ssc.logic.SSC11311Logic;
import com.msk.ssc.logic.SSC11312Logic;
import com.msk.ssc.logic.SSC11317Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务控制器：管理生产商入库差异单
 * Created by xia_xiaojie on 2016/7/4.
 */
@RestController
public class ISSC11311RestController extends BaseRsController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ISSC11311RestController.class);

    @Autowired
    private SSC11311Logic ssc11311Logic;


    /**
     * 查询生产商入库差异单
     * 接口URL：SystemServerManager.SellerSupplyChainManage.getQueryDifferBasics()
     */
    @RequestMapping(value = "/ssc/differ/query/basics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11311Result> queryDifferBasics(@RequestBody RsRequest<SSC11311Param> reqParam) {
        SSC11311Param queryParam = reqParam.getParam();
        boolean paging = queryParam.isPaging();

        SSC11311Result ssc11311Result = new SSC11311Result();
        int count = NumberConst.IntDef.INT_ZERO;
        if (paging) {
            count = ssc11311Logic.getPageCount(queryParam);
            ssc11311Result.setTotalCount(count);
        }

        List<SSC11311Bean> sscDifferBasics = new ArrayList<SSC11311Bean>();
        if (!paging || count > NumberConst.IntDef.INT_ZERO) {
            sscDifferBasics = ssc11311Logic.findPageList(queryParam, SSC11311Bean.class);
        }
        ssc11311Result.setSscDifferBasics(sscDifferBasics);

        RsResponse<SSC11311Result> respResult = new RsResponse<SSC11311Result>();
        respResult.setResult(ssc11311Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

    /**
     * 将差异单状态更新为已确认
     */
    @RequestMapping(value = "/ssc/differ/confirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11311Result> confirmDifferBasic(@RequestBody RsRequest<SSC11311Bean> reqParam) {
        int row = ssc11311Logic.confirm(reqParam.getParam());
        RsResponse<SSC11311Result> respResult = new RsResponse<SSC11311Result>();
        if (NumberConst.IntDef.INT_ZERO == row) {
            respResult.setStatus(SystemConst.RsStatus.FAIL);
        }
        else {
            respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        }
        return respResult;
    }

}
