package com.msk.order.rest;

import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.order.entity.SoOrder;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.ISO151502Service;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单详情 接口
 * zhangqiang1
 */
@Api(value = "order-Api", description = "订单详情相关Rest接口", tags = {"ISO151502RestController"})
@RestController
public class ISO151502RestController extends BaseRestController {

    @Autowired
    private SoOrderRepository soOrderRepository;


    @Autowired
    private ISO151502Service iso151502Service;

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151502RestController.class);


    /**
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/order/order/_get/by/orderid", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<SoOrder> queryOrderBuyersByOrderId(@RequestBody RestRequest<Long> param) {
        RestResponse<SoOrder> response = new RestResponse<>();
        Long orderId = param.getParam();
        logger.info("查询主订单信息");
        SoOrder soOrder = null;
        try {
            soOrder = this.iso151502Service.findByOrderIdAndDelFlg(orderId, SystemConstant.DelFlg.ENABLE);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(soOrder);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage("处理失败");
            e.printStackTrace();
        }
        return response;
    }


}


