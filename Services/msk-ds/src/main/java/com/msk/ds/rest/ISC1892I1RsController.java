package com.msk.ds.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.DsDeliveryStock;
import com.msk.ds.bean.ISC1892I1Param;
import com.msk.ds.bean.ISC1892I1RsResult;
import com.msk.ds.consts.BusinessConst;
import com.msk.ds.logic.ISC1892I1Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ISC1892I1RsController.
 * 扫码入库虚拟库存变更API
 *
 * @author xu_wei
 */
@Api(description = "扫码入库虚拟库存变更API",tags = {"ISC1892I1RsController"})
@RestController
public class ISC1892I1RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISC1892I1RsController.class);

    @Autowired
    private ISC1892I1Logic isc1892I1Logic;

    /**
     * 扫码入库虚拟库存变更
     *
     */
    @ApiOperation(value = "扫码入库虚拟库存变更",notes = "扫码入库虚拟库存变更")
    @RequestMapping(value = "/sc/modify",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_XML_VALUE })
    public RsResponse<ISC1892I1RsResult> modify(@RequestBody RsRequest<ISC1892I1Param> request) {
        logger.info("扫码入库虚拟库存变更");
        RsResponse<ISC1892I1RsResult> rs = new RsResponse<ISC1892I1RsResult>();
        ISC1892I1RsResult isc1892I1RsResult = null;
        String message = "";
        DsDeliveryStock dsDeliveryStock = isc1892I1Logic.judgeDelivery(request.getParam());
        if (null != dsDeliveryStock) {
            logger.info("卖家采供链扫码入库变更,处理开始!");
            ISC1892I1Param param = request.getParam();
            isc1892I1Logic.setSscParam(param);
            param.getFilterMap().put("sscDeliveryStockId",dsDeliveryStock.getSscDeliveryStockId());
            isc1892I1RsResult = isc1892I1Logic.modifySscDelivery(param);
            message = "卖家采供链，扫码入库处理成功！";
        } else {
            logger.info("平台供应链扫码入库变更,处理开始!");
            BaseParam baseParam = new BaseParam();
            this.setCommonParam(baseParam);
            request.getParam().setCrtId(baseParam.getCrtId());
            //实际收货箱数大于实际发货箱数时的Message
            StringBuffer outOfSendNumMessage = new StringBuffer("");
            isc1892I1RsResult = isc1892I1Logic.modifyStock(request, outOfSendNumMessage);
            message = "平台供应链,扫码入库处理成功！" + outOfSendNumMessage.toString();
        }
        if (null == isc1892I1RsResult || StringUtil.isEmpty(isc1892I1RsResult.getDeliveryStockStatus())) {
            rs.setStatus(BusinessConst.RsStatus.FAIL);
            rs.setMessage("扫码入库失败");
        } else {
            rs.setResult(isc1892I1RsResult);
            rs.setStatus(BusinessConst.RsStatus.SUCCESS);
            rs.setMessage(message);
        }
        return rs;

    }

}
