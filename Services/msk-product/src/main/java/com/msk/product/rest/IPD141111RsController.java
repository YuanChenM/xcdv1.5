package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141111RsParam;
import com.msk.product.bean.IPD141111RsResult;
import com.msk.product.logic.IPD141111Logic;
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
 * 产品当前价盘周期价盘查询
 * IPD141111RsController.
 *
 * @author zhou_ling
 */
@RestController
public class IPD141111RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141111RsController.class);

    @Autowired
    private IPD141111Logic ipd141111Logic;

    /**
     * 产品查询价盘接口
     *
     * @param param param
     * @return 结果
     * @author zhou_Ling
     */
    @RequestMapping(value = "/pd/pd_pricecycle",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141111Validator")
    public RsResponse<List<IPD141111RsResult>> createClasses(@RequestBody RsRequest<IPD141111RsParam> param) {
        // 查询所有产品类别信息
        RsResponse<List<IPD141111RsResult>> rs = new RsResponse<List<IPD141111RsResult>>();
        List<IPD141111RsResult> result = ipd141111Logic.findPriceCycle(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据查询成功!");
            rs.setResult(result);
            logger.info("数据查询成功!");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        logger.info("没有查询到数据！");
        rs.setMessage("没有查询到数据！");
        return rs;
    }

}
