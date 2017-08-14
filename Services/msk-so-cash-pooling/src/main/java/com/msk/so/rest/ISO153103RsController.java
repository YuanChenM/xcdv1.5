package com.msk.so.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SoCpSelCharging;
import com.msk.cashPooling.bean.BaseCpResult;
import com.msk.so.bean.ISO153103Param;
import com.msk.so.logic.ISO153103Logic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 卖家计费项接口Controller
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@RestController
public class ISO153103RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO153103RsController.class);

    @Autowired
    private ISO153103Logic logic;

    /**
     * 卖家计费项接口调用
     *
     * @param param param
     * @return result
     */
    @RequestMapping(value = "/cp/sellerCharging",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BaseCpResult> sellerCharging(@RequestBody RsRequest<ISO153103Param> param) {
        logger.debug("卖家计费项接口调用 start");
        RsResponse<BaseCpResult> rs = new RsResponse<BaseCpResult>();
        BaseCpResult result = new BaseCpResult();
        logic.createSelCharging(param);

        if (null != param.getParam()) {
            List<SoCpSelCharging> selChargingList = param.getParam().getSoCpSelChargingList();
            if (CollectionUtils.isNotEmpty(selChargingList)) {
                result.setTransCode(selChargingList.get(NumberConst.IntDef.INT_ZERO).getTransCode());
                rs.setResult(result);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("卖家计费项接口调用成功！");
            } else {
                rs.setReturnCode("F000001");
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("error.system.error");
            }
        } else {
            rs.setReturnCode("F000001");
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("error.system.error");
        }

        logger.debug("卖家计费项接口调用 end");
        return rs;
    }
}
