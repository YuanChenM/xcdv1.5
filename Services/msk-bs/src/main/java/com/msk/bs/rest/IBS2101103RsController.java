package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS210110301Bean;
import com.msk.bs.bean.IBS2101103RsParam;
import com.msk.bs.bean.IBS2101103RsResult;
import com.msk.bs.logic.IBS2101102RsLogic;
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
 * Created by cx on 2016/2/29.
 */
@RestController
@Api(description = "查询买手信息RestController", tags = {"IBS2101103RsController"})
public class IBS2101103RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101103RsController.class);
    @Autowired
    private IBS2101102RsLogic ibS2101102RsLogic;

    /**
     * 查询买手店卖家账户
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "买手信息", notes = "查询买手信息接口")
    @RequestMapping(value = "/bs/slInfo/slAccount/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101103RsResult> querySLShopAccount(@RequestBody RsRequest<IBS2101103RsParam> request) {
        logger.debug("查询买手店卖家账户接口");
        RsResponse<IBS2101103RsResult> rs = new RsResponse<IBS2101103RsResult>();
        IBS2101103RsResult result = new IBS2101103RsResult();
        List<IBS210110301Bean> buyershopList = ibS2101102RsLogic.findPageList(request.getParam(), result);

        //接口返回值参数转换
        if (!CollectionUtils.isEmpty(buyershopList) && buyershopList.size() > 0) {
            for (IBS210110301Bean bean : buyershopList) {
                bean.setSlMainClass(4);
                if(StringUtil.isNullOrEmpty(bean.getSnkFlg())){
                    bean.setSnkFlg("1");
                }
                if(StringUtil.isNullOrEmpty(bean.getSelfFlg())){
                    bean.setSelfFlg("0");
                }
                if(StringUtil.isNullOrEmpty(bean.getAgentFlg())){
                    bean.setAgentFlg("0");
                }
                if(StringUtil.isNullOrEmpty(bean.getOemFlg())){
                    bean.setOemFlg("0");
                }
                if(StringUtil.isNullOrEmpty(bean.getBuyerFlg())){
                    bean.setBuyerFlg("1");
                }
            }

            result.setBuyershopList(buyershopList);
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询买手店买手账户成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("暂不存在数据");
        }
        return rs;
    }
}
