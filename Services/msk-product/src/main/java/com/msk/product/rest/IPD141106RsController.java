package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141106RsParam;
import com.msk.product.logic.IPD141106Logic;
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
 * 产品标准质量档案卡查询接口
 * IPD141106RsController.
 * 
 * @author xhy
 */
@RestController
public class IPD141106RsController extends BaseRsController {

    /** logger 日志 */
    private static Logger logger = LoggerFactory.getLogger(IPD141106RsController.class);

    @Autowired
    private IPD141106Logic ipd141106Logic;

    /**
     * 产品标准质量档案卡查询接口
     * 
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/pd/pd_qlt_std",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.product.validator.IPD141106Validator")
    public RsResponse<List<IPD141106RsParam>> findPdQltStd(@RequestBody RsRequest<IPD141106RsParam> param) {
        logger.info("产品标准质量档案卡查询");
        RsResponse<List<IPD141106RsParam>> rs = new RsResponse<List<IPD141106RsParam>>();
        List<IPD141106RsParam> res = this.ipd141106Logic.findListPdQltStd(param);
        if (res != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品标准质量档案卡查询接口异常!");
        return rs;
    }
}
