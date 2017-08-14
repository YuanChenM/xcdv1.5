package com.msk.ds.rest;

import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ds.bean.ISC1891I1RsParam;
import com.msk.ds.bean.ISC1891I1RsResult;
import com.msk.ds.consts.BusinessConst;
import com.msk.ds.logic.ISC1891I1Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * ISC1891I1RsController.
 * 查询供应计划尚未入库的供应量
 *
 * @author yuan_chen
 */
@Api(description = "查询供应计划尚未入库的供应量",tags = {"ISC1891I1RsController"})
@RestController
public class ISC1891I1RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISC1891I1RsController.class);

    /** ISC1891I1Logic */
    @Autowired
    private ISC1891I1Logic iSC1891I1Logic;

    /**
     * 查询供应计划尚未入库的供应量
     *
     * @param param param
     * @return 结果
     */
    @ApiOperation(value = "查询供应计划尚未入库的供应量",notes = "查询供应计划尚未入库的供应量")
    @RequestMapping(value = "/sc/undonum",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ISC1891I1RsResult> queryProductLotInfo(@RequestBody RsRequest<ISC1891I1RsParam> param) {
        logger.info("查询供应计划尚未入库的供应量");
        RsResponse<ISC1891I1RsResult> rs = new RsResponse<ISC1891I1RsResult>();
        ISC1891I1RsResult isc1891I1RsResult = iSC1891I1Logic.findAllList(param);
        rs.setStatus(BusinessConst.RsStatus.SUCCESS);
        rs.setMessage("查询供应计划尚未入库的供应量信息成功！");
        rs.setResult(isc1891I1RsResult);
        return rs;
    }
}
