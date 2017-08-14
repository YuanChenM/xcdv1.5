package com.msk.seller.rest;

import com.hoperun.core.consts.NumberConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231119RsParam;
import com.msk.seller.bean.ISL231119RsResult;
import com.msk.seller.logic.ISL231119Logic;

/**
 * ISL231119RsController.
 *
 * @author yuan_chen
 */
@RestController
public class ISL231119RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ISL231119RsController.class);

    /** ISL231119Logic */
    @Autowired
    private ISL231119Logic isl231119Logic;

    /**
     * 查询物流区卖家产品信息
     *
     * @param param param
     * @return 结果
     */
    @RequestMapping(value = "/sl/slBidLogiArea",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ISL231119RsResult> query(@RequestBody RsRequest<ISL231119RsParam> param) {
        logger.info("查询物流区卖家产品信息");
        if (param == null){
            param = new RsRequest<ISL231119RsParam>();
        }
        RsResponse<ISL231119RsResult> rs = new RsResponse<ISL231119RsResult>();
        ISL231119RsResult result = isl231119Logic.findRsResult(param.getParam());
        if (result.getTotalCount() == NumberConst.IntDef.INT_ZERO){
            rs.setMessage("未查询到物流区卖家产品信息！");
        }else{
            rs.setMessage("查询物流区卖家产品信息成功！");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(result);
        return rs;
    }
}
