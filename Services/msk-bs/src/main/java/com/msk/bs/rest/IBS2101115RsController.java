package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS2101115Param;
import com.msk.bs.logic.IBS2101115RsLogic;
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
 * 同步卖家模块买手数据
 * Created by ni_shaotang on 2016/7/29.
 */
@RestController
@Api(description = "同步卖家模块买手信息RestController", tags = {"IBS2101115RsController"})
public class IBS2101115RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IBS2101114RsController.class);
    @Autowired
    private IBS2101115RsLogic ibs2101115RsLogic;

    /**
     * 同步卖家模块买手数据
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "同步卖家模块买手信息 ", notes = "同步卖家模块买手数据")
    @RequestMapping(value = "/bs/slInfo/syncBuyerInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> syncBuyerInfo(@RequestBody RsRequest<IBS2101115Param> request) {
        RsResponse<String> response = new RsResponse<String>();
        logger.info("开始同步卖家模块买手数据");
        response.setStatus(SystemConst.RsStatus.FAIL);
        if(null != request) {
            IBS2101115Param param = request.getParam();
            Date newDate = DateTimeUtil.getCustomerDate();
            int num = 0;
            response.setStatus(SystemConst.RsStatus.FAIL);
            if (null != param) {
                param.setActId(request.getAuth());
                param.setActTime(newDate);
                param.setUpdId(request.getAuth());
                param.setUpdTime(newDate);
                param.setCrtId(request.getAuth());
                param.setCrtTime(newDate);
                if (null != param.getBsBasicInfo()) {//买手基本信息验证
                    if (StringUtil.isNullOrEmpty(param.getBsBasicInfo().getSlCode())) {
                        response.setMessage("买手基本信息主键不能为空");
                    } else if (StringUtil.isNullOrEmpty(param.getBsBasicInfo().getSlAccount())) {
                        response.setMessage("买手基本信息的买手id不能为空");
                    }
                }
                if (StringUtil.isNullOrEmpty(response.getMessage())) {//参数验证通过。保存买手信息
                    num = ibs2101115RsLogic.saveBuyerInfo(param);
                }
                if (num > 0) {
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("保存买手信息成功");
                } else if (StringUtil.isNullOrEmpty(response.getMessage())) {
                    if (num < 0) {
                        if(num == -1) {
                            response.setMessage("保存买手基本信息失败,买手账户信息不存在。");
                        }else if(num == -2){
                            response.setMessage("修改买手账户信息失败,买手账户信息不存在。");
                        }else if(num == -3){
                            response.setMessage("保存买手账户信息失败,买手手机号码不能为空。");
                        }else if(num == -4){
                            response.setMessage("保存买手账户信息失败,认证状态不能为空。");
                        }
                    } else {
                        response.setMessage("保存买手信息失败,未保存任何信息");
                    }
                }
            } else {
                response.setMessage("参数不能为空");
            }
        }else {
            response.setMessage("参数不能为空");
        }
        logger.info("完成同步卖家模块买手数据");
        return response;
    }
}
