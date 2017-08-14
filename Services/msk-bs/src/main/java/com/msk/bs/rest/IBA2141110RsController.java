package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.IBA2141110Param;
import com.msk.bs.bean.IBA2141110Result;
import com.msk.bs.logic.IBA2141110RsLogic;
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

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/7/19.
 */
@RestController
@Api(description = "查询购物车详细、更新订单信息等RestController", tags = {"IBA2141110RsController"})
public class IBA2141110RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141110RsController.class);

    @Autowired
    private IBA2141110RsLogic iba2141110RsLogic;

    /**
     * 根据管家ID查询购物车详细
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "购物车详细", notes = "根据管家ID查询购物车详细接口")
    @RequestMapping(value = "/bs/find/orderDetail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141110Result>> findOrderDetail(@RequestBody RsRequest<IBA2141110Param> request) {
        logger.info("开始查询订单列表信息");
        RsResponse<List<IBA2141110Result>> response = new RsResponse<List<IBA2141110Result>>();
        IBA2141110Param param = request.getParam();
        List<IBA2141110Result> iba2141112Result = iba2141110RsLogic.findOrderDetail(param);
        if (null != iba2141112Result) {
            response.setResult(iba2141112Result);
            response.setMessage("处理成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("查询订单列表信息结束");
        return response;
    }

    /**
     * 根据购物车ID与购物车明细ID查询对应的产品信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询对应的产品信息", notes = "根据购物车ID与购物车明细ID查询对应的产品信息接口")
    @RequestMapping(value = "/bs/get/orderDetailInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141110Result>> getOrderDetailInfoByCarID(@RequestBody RsRequest<IBA2141110Param> request) {
        logger.info("开始查询订单列表信息");
        RsResponse<List<IBA2141110Result>> response = new RsResponse<List<IBA2141110Result>>();
        IBA2141110Param param = request.getParam();
        List<IBA2141110Result> iba2141112Result = iba2141110RsLogic.getOrderDetailInfo(param);
        if (null != iba2141112Result) {
            response.setResult(iba2141112Result);
            response.setMessage("处理成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("查询订单列表信息结束");
        return response;
    }


    /**
     * 产品列表购买商品
     *
     * @param request
     * @returna
     */
    @ApiOperation(value = "查询产品列表购买商品", notes = "查询订单列表商品信息，通过产品列表购买商品")
    @RequestMapping(value = "/ba/buyGoodsInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBA2141110Result> buyGoodsInfo(@RequestBody RsRequest<IBA2141110Param> request) {
        logger.info("开始查询产品列表购买商品信息");
        RsResponse<IBA2141110Result> response = new RsResponse<IBA2141110Result>();
        IBA2141110Param param = request.getParam();
        IBA2141110Result iba2141112Result = iba2141110RsLogic.getGoodsInfo(param);
        if (null != iba2141112Result) {
            response.setResult(iba2141112Result);
            response.setMessage("处理成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("处理失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("查询产品列表购买商品结束");
        return response;
    }

    /**
     * 更新订单信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "更新订单信息", notes = "更新订单信息接口")
    @RequestMapping(value = "/bs/updateOrder", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateOrder(@RequestBody RsRequest<IBA2141110Param> request) {
        logger.info("开始更新购物车明细信息");
        RsResponse<Integer> response = new RsResponse<Integer>();
        IBA2141110Param param = request.getParam();
        int i = iba2141110RsLogic.modifyShopingCardDetail(param);
        if (i > 0) {
            response.setResult(i);
            response.setMessage("更新成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        } else {
            response.setMessage("更新失败");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        logger.info("更新购物车明细结束");
        return response;
    }
}
