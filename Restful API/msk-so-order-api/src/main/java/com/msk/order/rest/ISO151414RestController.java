package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.annotation.valid.Validation;
import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.utils.DateTimeUtil;
import com.msk.order.bean.param.ISO151414BaseOrderParam;
import com.msk.order.bean.result.ISO151414OrderResult;
import com.msk.order.service.ISO151414Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建订单
 * Created by liu_tao2 on 2016/8/1.
 */
@RestController
@Api(value = "Order-Api", description = "创建订单接口Api", tags = {"ISO151414RestController"})
public class ISO151414RestController extends BaseRestController {

    @Autowired
    private ISO151414Service iso151414Service;

    /**
     * 创建分销订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/distribution/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151414CreateDistributionOrderValidator")
    public RestResponse<ISO151414OrderResult> createDistributionOrder(@RequestBody RestRequest<ISO151414BaseOrderParam> request) {
        RestResponse<ISO151414OrderResult> response = new RestResponse<ISO151414OrderResult>();
        try {
            ISO151414BaseOrderParam param = request.getParam();
            param.setOrderType(OrderCodeMasterDef.OrderType.DISTRIBUTION_ORDER);
            iso151414Service.dealOrderSourceAndSalePlatform(request.getSiteCode(), param);
            iso151414Service.getBuyerInfoByBuyerId(param);
            param.setCrtId(request.getLoginId());
            param.setCrtTime(DateTimeUtil.getCustomerDate());
            ISO151414OrderResult result = iso151414Service.createDistributionOrder(param);
            response.setMessage("分销订单创建成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 创建买手囤货订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/buyer/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151414CreateBuyerOrderValidator")
    public RestResponse<ISO151414OrderResult> createBuyerOrder(@RequestBody RestRequest<ISO151414BaseOrderParam> request) {
        RestResponse<ISO151414OrderResult> response = new RestResponse<ISO151414OrderResult>();
        try {
            ISO151414BaseOrderParam param = request.getParam();
            param.setOrderType(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER);
            iso151414Service.dealOrderSourceAndSalePlatform(request.getSiteCode(), param);
            param.setCrtId(request.getLoginId());
            param.setCrtTime(DateTimeUtil.getCustomerDate());
            ISO151414OrderResult result = iso151414Service.createBuyerOrder(param);
            response.setMessage("买手囤货订单创建成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 创建第三方订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/thirdparty/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151414CreateThirdPartyOrderValidator")
    public RestResponse<ISO151414OrderResult> createThirdPartyOrder(@RequestBody RestRequest<ISO151414BaseOrderParam> request) {
        RestResponse<ISO151414OrderResult> response = new RestResponse<>();
        try {
            ISO151414BaseOrderParam param = request.getParam();
            param.setOrderType(OrderCodeMasterDef.OrderType.THIRD_PARTY_ORDER);
            iso151414Service.dealOrderSourceAndSalePlatform(request.getSiteCode(), param);
            iso151414Service.getBuyerInfoByBuyerId(param);
            param.setCrtId(request.getLoginId());
            param.setCrtTime(DateTimeUtil.getCustomerDate());
            ISO151414OrderResult result = iso151414Service.createThirdPartyOrder(param);

            response.setMessage("第三方订单创建成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 创建第三方买手囤货订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/thirdbuyer/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151414CreateThirdBuyerOrderValidator")
    public RestResponse<ISO151414OrderResult> createThirdBuyerOrder(@RequestBody RestRequest<ISO151414BaseOrderParam> request) {
        RestResponse<ISO151414OrderResult> response = new RestResponse<ISO151414OrderResult>();
        try {
            ISO151414BaseOrderParam param = request.getParam();
            param.setOrderType(OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER);
            iso151414Service.dealOrderSourceAndSalePlatform(request.getSiteCode(), param);
            param.setCrtId(request.getLoginId());
            param.setCrtTime(DateTimeUtil.getCustomerDate());
            ISO151414OrderResult result = iso151414Service.createThirdBuyerOrder(param);
            response.setMessage("第三方买手囤货订单创建成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
