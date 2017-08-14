package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101106Bean;
import com.msk.bs.bean.IBS2101106RsResult;
import com.msk.bs.logic.IBS2101106RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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

import java.util.Date;

/**
 * Created by cx on 2016/3/2.
 */
@RestController
@Api(description = "编辑买手冻品管家的买家信息RestController", tags = {"IBS2101106RsController"})
public class IBS2101106RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101106RsController.class);
    @Autowired
    private IBS2101106RsLogic iBS2101106RsLogic;

    /**
     * 编辑买手冻品管家的买家信息接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "买家信息", notes = "编辑买手冻品管家的买家信息接口")
    @RequestMapping(value = "/bs/slInfo/slExclusive/newOrUpdate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101106Validator")
    public RsResponse<IBS2101106RsResult> editBSExclusive(@RequestBody RsRequest<IBS2101106Bean> param) {
        logger.debug("编辑买手冻品管家的买家信息接口");
        RsResponse<IBS2101106RsResult> rs = new RsResponse<IBS2101106RsResult>();
        IBS2101106Bean iBS2101106Bean = param.getParam();
        if(null == iBS2101106Bean){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("缺少参数");
            return rs;
        }
        /**Add: Bug #3404 2016/10/19   BY  ni_shaotang  Start */
        iBS2101106Bean.setCrtId(param.getLoginId());
        iBS2101106Bean.setUpdId(param.getLoginId());
        iBS2101106Bean.setActId(param.getLoginId());
        Date date = DateTimeUtil.getCustomerDate();
        iBS2101106Bean.setCrtTime(date);
        iBS2101106Bean.setUpdTime(date);
        iBS2101106Bean.setActTime(date);
        /**Add: Bug #3404 2016/10/19   BY  ni_shaotang  End */
        iBS2101106RsLogic.editExclusive(iBS2101106Bean);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("更新买手冻品管家的买家信息成功");
        return rs;
    }
}
