package com.msk.bs.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bs.bean.IBA2141101Bean;
import com.msk.bs.logic.IBA2141101Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by ni_shaotang on 2016/9/29.
 */
@RestController
@Api(description = "买手app启动接口RestController", tags = {"IBA2141101RsController"})
public class IBA2141101RsController extends BaseRsController {

    @Autowired
    private IBA2141101Logic iba2141101Logic;


    @ApiOperation(value = "买手app启动接口", notes = "买手app启动接口")
    @RequestMapping(value = "/ba/checkLoginType",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBA2141101Bean> checkLoginType(@RequestBody RsRequest<String> request) {
        RsResponse<IBA2141101Bean> response = new RsResponse<IBA2141101Bean>();
        BaseParam param = new BaseParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        List<IBA2141101Bean> list = iba2141101Logic.getVersions(param);
        if (null != list && list.size() > 0) {
            IBA2141101Bean bean = list.get(0);
            if (null != bean.getEndTime() && bean.getEndTime().getTime() < newDate.getTime()) {
                response.setReturnCode(SystemConst.RsStatus.FAIL);
                response.setMessage("当前时间系统不可用");
            } else {
                response.setReturnCode(SystemConst.RsStatus.SUCCESS);
                response.setMessage("当前时间系统可用");
            }
            response.setResult(bean);
        } else {
            response.setReturnCode(SystemConst.RsStatus.FAIL);
            response.setMessage("版本信息不存在，请联系管理员");
        }
        return response;
    }
}
