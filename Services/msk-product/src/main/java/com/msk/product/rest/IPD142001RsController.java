package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.product.bean.IPD142001ChildRsResult;
import com.msk.product.bean.IPD142001RsParam;
import com.msk.product.bean.IPD142001RsResult;
import com.msk.product.logic.IPD142001Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * IPD141141RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "共通设定查询RestController", tags = {"IPD142001RsController"})
public class IPD142001RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD142001RsController.class);

    /**
     * 共同设定查询
     *
     * @param param param
     * @return 结果
     */
    @ApiOperation(value = "共通设定", notes = "共通设定查询接口")
    @RequestMapping(value = "/common/master_setting",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD142001Validator")
    public RsResponse<IPD142001RsResult> query(@RequestBody RsRequest<IPD142001RsParam> param) {
        logger.info("查询物流区等级产品信息");
        RsResponse<IPD142001RsResult> rs = new RsResponse<IPD142001RsResult>();
        IPD142001RsResult result = new IPD142001RsResult();
        String constantType = param.getParam().getConstantType();
        Map<String,String> codeMasterMap = CodeMasterManager.findCodeMasterMap(constantType);
        List<IPD142001ChildRsResult> commList = new ArrayList<IPD142001ChildRsResult>();
        if(MapUtils.isNotEmpty(codeMasterMap))
        {
            Iterator<Map.Entry<String, String>> iterator = codeMasterMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                IPD142001ChildRsResult childRsResult = new IPD142001ChildRsResult();
                childRsResult.setConstantName(entry.getKey());
                childRsResult.setConstantValue(entry.getValue());
                commList.add(childRsResult);
            }
        }
        result.setConstantTypeName(constantType);//redis中没有查询Constant_type_name
        result.setCommList(commList);
        if (result.getCommList().size() == NumberConst.IntDef.INT_ZERO) {
            rs.setMessage("共同设定查询失败！");
        } else {
            rs.setMessage("共同设定查询成功！");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(result);
        return rs;
    }
}
