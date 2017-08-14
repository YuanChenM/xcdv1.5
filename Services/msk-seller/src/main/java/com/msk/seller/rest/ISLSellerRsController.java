package com.msk.seller.rest;


import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.ISLSellerRsResult;
import com.msk.seller.logic.ISLSellerRsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhang_chi on 2016/8/3.
 */
@RestController
public class ISLSellerRsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISLSellerRsController.class);

    @Autowired
    private ISLSellerRsLogic islSellerRsLogic;

    /**
     * 买手信息维护卖家同步
     * @return
     */
    @RequestMapping(value = "/sl/slSeller/slAccount",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLSellerRsResult> dealSlSellerAccount(@RequestBody RsRequest<ISLSellerRsParam> param){
        RsResponse<ISLSellerRsResult> result = new RsResponse<ISLSellerRsResult>();
        ISLSellerRsResult islSellerRsResult =  islSellerRsLogic.dealSlSellerData(param);
        if (null != islSellerRsResult) {
            result.setResult(islSellerRsResult);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("更新成功");
        } else {
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("更新失败");
        }
        return result;
    }


    /**
     * 根据卖家编码批量查询卖家信息
     * @return
     */
    @RequestMapping(value = "/sl/slSeller/list",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLSellerRsResult> querySellerList(@RequestBody RsRequest<ISLSellerRsParam> param){
        RsResponse<ISLSellerRsResult> result = new RsResponse<ISLSellerRsResult>();
        ISLSellerRsResult islSellerRsResult=  islSellerRsLogic.queryISLSellerRsResult(param.getParam());
        if (null != islSellerRsResult) {
            result.setResult(islSellerRsResult);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("查询成功");
        } else {
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("查询失败");
        }
        return result;
    }


}
