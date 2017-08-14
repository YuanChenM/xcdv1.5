package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11311Bean;
import com.msk.ssc.bean.SSC1131502Bean;
import com.msk.ssc.bean.SSC1131502Param;
import com.msk.ssc.bean.SSC1131502Result;
import com.msk.ssc.logic.SSC1131502Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/22.
 */
@RestController
public class ISSC1131502RestController extends BaseRsController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ISSC11312RestController.class);

    @Autowired
    private SSC1131502Logic ssc1131502Logic;

    @RequestMapping(value = "/ssc/findProductHistories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC1131502Result> findProductHistories(@RequestBody RsRequest<SSC1131502Param> reqParam) {
        SSC1131502Param ssc1131502Param = reqParam.getParam();
        boolean paging = ssc1131502Param.isPaging();

        SSC1131502Result ssc1131502Result = new SSC1131502Result();
        int count = NumberConst.IntDef.INT_ZERO;
        if (paging) {
            count = ssc1131502Logic.getPageCount(ssc1131502Param);
            ssc1131502Result.setTotalCount(count);
        }

        List<SSC1131502Bean> ssc1131502Beans = new ArrayList<SSC1131502Bean>();
        if (!paging || count > NumberConst.IntDef.INT_ZERO) {
            ssc1131502Beans = ssc1131502Logic.findProductHistories(ssc1131502Param);
        }
        ssc1131502Result.setProductHistories(ssc1131502Beans);

        RsResponse<SSC1131502Result> respResult = new RsResponse<SSC1131502Result>();
        respResult.setResult(ssc1131502Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

}
