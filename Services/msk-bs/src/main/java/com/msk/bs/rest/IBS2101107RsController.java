package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.bs.bean.IBS2101107RsParam;
import com.msk.bs.bean.IBS2101107RsResult;
import com.msk.bs.logic.IBS2101107RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cx on 2016/3/18.
 */
@RestController
@Api(description = "查询买手冻品管家的买家信息RestController", tags = {"IBS2101107RsController"})
public class IBS2101107RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101107RsController.class);

    @Autowired
    private IBS2101107RsLogic ibS2101107RsLogic;

    /**
     * 查询买手冻品管家的买家信息
     * @param request request
     * @return rs
     */
    @ApiOperation(value = "买家信息", notes = "查询买手冻品管家的买家信息接口")
    @RequestMapping(value = "/bs/slInfo/slExclusive/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101107Validator")
    public RsResponse<IBS2101107RsResult> querySlExclusive(@RequestBody RsRequest<IBS2101107RsParam> request) {
        logger.debug("查询买手冻品管家的买家信息接口");
        RsResponse<IBS2101107RsResult> rs = new RsResponse<IBS2101107RsResult>();
        /**modify 改善 #3519 查询买手冻品管家的买家信息接口，查询条件不起作用 zhukai start**/
        IBS2101107RsResult result = ibS2101107RsLogic.findBuyersInfo(request.getParam());
        /**modify 改善 #3519 查询买手冻品管家的买家信息接口，查询条件不起作用 zhukai end**/
        if(null !=result && !CollectionUtils.isEmpty(result.getSlBuyerList())){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(result);
            rs.setMessage("查询买手冻品管家的买家信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("暂不存在数据");
        }
        return rs;
    }

}
