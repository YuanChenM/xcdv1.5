package com.msk.order.rest;

import com.msk.common.base.BaseRestController;
import com.msk.order.service.ISO151510Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.order.bean.param.ISO151510RestParam;
import com.msk.order.bean.result.ISO151510RestBean;
import com.msk.order.bean.result.ISO151510RestBeanList;


/**
 * ISO151510_发货单取消后台接口
 * Created by wang_jianzhou on 2016/8/4.
 */
@RestController
public class ISO151510RestController extends BaseRestController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151510RestController.class);

    @Autowired
    private ISO151510Service service;
    /**
     * 查询发货单信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/deliver/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151510RestBeanList> deliverSearch(@RequestBody RestRequest<ISO151510RestParam> param) {
        logger.debug("查询发货单");
        ISO151510RestParam so151410RsParam = param.getParam();
        ISO151510RestBeanList iso151510Beans = this.service.findOrderShipInfo(so151410RsParam);
        RestResponse<ISO151510RestBeanList> response = new RestResponse<ISO151510RestBeanList>();
        response.setResult(iso151510Beans);
        logger.debug("查询发货单结束");
        return response;
    }

    /**
     * 发货单取消
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/deliver/_cancel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151510RestBean> deliverCancel(@RequestBody RestRequest<ISO151510RestParam> param) {
        logger.debug("取消发货单");
        ISO151510RestParam so151410RsParam = param.getParam();
        this.service.cancelOrderShip(so151410RsParam);
        RestResponse<ISO151510RestBean> response = new RestResponse<ISO151510RestBean>();
        response.setStatus("S");
        logger.debug("取消发货单结束");
        return response;
    }
}
