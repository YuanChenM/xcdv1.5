package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBA2141102Param;
import com.msk.bs.logic.IBA2141103Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlHouseAccount;
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
 * 冻品管家app登录接口
 * Created by ni_shaotang on 2016/7/14.
 */
@RestController
@Api(description = "冻品管家登录接口RestController", tags = {"IBA2141103RsController"})
public class IBA2141103RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBA2141103RsController.class);

    @Autowired
    private IBA2141103Logic iba2141103Logic;

    /**
     * 冻品管家app登录接口
     *
     * @param params
     * @return 结果
     * @author yuan_chen
     */
    @ApiOperation(value = "冻品管家登录", notes = "冻品管家登录接口")
    @RequestMapping(value = "/ba/houseAccount/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SlHouseAccount> accessLogin(@RequestBody RsRequest<IBA2141102Param> params) {
        RsResponse<SlHouseAccount> rs = new RsResponse<SlHouseAccount>();
        IBA2141102Param param = params.getParam();
        if (StringUtil.isNullOrEmpty(param.getAccountPsd())) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("密码不能为空,请重新输入");
        } else {
            SlHouseAccount slHouseAccount = iba2141103Logic.getHouseAccount(param);
            if (null == slHouseAccount) {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("用户名或密码错误,请重新输入");
            } else {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setResult(slHouseAccount);
                rs.setMessage("登录成功");
            }
        }
        return rs;
    }
}
