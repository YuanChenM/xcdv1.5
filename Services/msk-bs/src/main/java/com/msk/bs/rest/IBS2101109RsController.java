package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101109RsParam;
import com.msk.bs.bean.IBS2101109RsResult;
import com.msk.bs.logic.IBS2101109RsLogic;
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

/**
 * Created by cx on 2016/3/30.
 */
@RestController
@Api(description = "冻品管家账号密码修改RestController", tags = {"IBS2101109RsController"})
public class IBS2101109RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101109RsController.class);

    @Autowired
    private IBS2101109RsLogic ibS2101109RsLogic;

    /**
     * 冻品管家账号密码修改
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "密码修改", notes = "冻品管家账号密码修改接口")
    @RequestMapping(value = "/bs/slInfo/updatePsd",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101109Validator")
    public RsResponse<IBS2101109RsResult> updatePsd(@RequestBody RsRequest<IBS2101109RsParam> param) {
        logger.debug("冻品管家账号密码修改接口");
        RsResponse<IBS2101109RsResult> rs = new RsResponse<IBS2101109RsResult>();
        IBS2101109RsParam param1 =  param.getParam();
        /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
        param1.setUpdId(param.getLoginId());
        param1.setUpdTime(DateTimeUtil.getCustomerDate());
        /**Add: 横展开添加共通设置 2016/09/23   BY  任强  End */
        ibS2101109RsLogic.updatePsd(param1);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("冻品管家账号密码修改成功");
        return rs;
    }

}
