package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBA2141102Param;
import com.msk.bs.bean.IBA2141102RsResult;
import com.msk.bs.logic.IBA2141102Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BsAccount;
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
 * 买手app登录接口
 * Created by ni_shaotang on 2016/7/14.
 */
@RestController
@Api(description = "买手app登录接口RestController", tags = {"IBA2141102RsController"})
public class IBA2141102RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBA2141102RsController.class);

    @Autowired
    private IBA2141102Logic iba2141102Logic;

    /**
     * 买手app登录接口
     *
     * @param request
     * @return 结果
     * @author yuan_chen
     */
    @ApiOperation(value = "买手app登录", notes = "买手app登录接口")
    @RequestMapping(value = "/ba/account/login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BsAccount> accessLogin(@RequestBody RsRequest<IBA2141102Param> request) {
        RsResponse<BsAccount> rs = new RsResponse<BsAccount>();
        IBA2141102Param param = request.getParam();
        if (StringUtil.isNullOrEmpty(param.getAccountPsd())) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("密码不能为空,请重新输入");
        } else {
            IBA2141102RsResult bsAccount = iba2141102Logic.getBsAccount(param);
            if (null == bsAccount) {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("用户名或密码错误,请重新输入");
            } else {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setResult(bsAccount);
                rs.setMessage("登录成功");
            }
        }
        return rs;
    }
}
