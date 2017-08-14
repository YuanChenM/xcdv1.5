package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.CommCodeMasterConst;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151416OrderSearchParam;
import com.msk.order.bean.param.ISO151416SoldProductParam;
import com.msk.order.bean.result.ISO151414ByBuyerInfoResult;
import com.msk.order.bean.result.ISO151416OrderSearchResult;
import com.msk.order.bean.result.ISO151416SoldProductResult;
import com.msk.order.service.ISO151416Service;
import com.msk.order.util.SoRestUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liu_tao2 on 2016/8/17.
 */
@RestController
@Api(value = "Api", description = "订单查询接口Api", tags = {"ISO151416RestController"})
public class ISO151416RestController extends BaseRestController {

    @Autowired
    private ISO151416Service iso151416Service;

    /**
     * 订单列表查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151416OrderSearchResult> searchDetailList(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderDetailList(param);
            response.setMessage("订单列表查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 订单明细查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/detail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151416OrderSearchResult> searchDetail(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderDetail(param);
            response.setMessage("订单明细查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 买家订单列表查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/by/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151416OrderSearchResult> searchBuyerDetailList(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            if(request.getSiteCode().equals(CommCodeMasterConst.SystemCode.SYSTEM_CODE_WSC)){
                ISO151414ByBuyerInfoResult result = SoRestUtil.getBuyerInfo(param.getBuyersCode());
                if(null == result || StringUtil.isEmpty(result.getBuyerId())){
                    throw new BusinessException("通过电话没有找到该买家");
                }
                param.setBuyersCode(null);
                param.setBuyersId(result.getBuyerId());
            }else {
                if(StringUtil.isEmpty(param.getBuyersCode())){
                    throw new BusinessException("查询买家订单列买家编码不能为空");
                }
                if(StringUtil.isEmpty(param.getBuyersId())){
                    throw new BusinessException("查询买家订单列买家ID不能为空");
                }
                iso151416Service.getBuyerIdByBuyerCode(param);
            }
            ISO151416OrderSearchResult result = iso151416Service.findOrderDetailList(param);
            response.setMessage("买家订单列表查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 买家订单明细查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/by/detail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchBuyerDetailValidator")
    public RestResponse<ISO151416OrderSearchResult> searchBuyerDetail(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderDetail(param);
            response.setMessage("买家订单明细查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 买手囤货订单列表查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/bssg/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchBuyerStoreDetailListValidator")
    public RestResponse<ISO151416OrderSearchResult> searchBuyerStoreDetailList(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            param.setOrderType(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER + "," + OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER);
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderDetailList(param);
            response.setMessage("买手囤货订单列表查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 买手囤货订单明细查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/bssg/detail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchBuyerStoreDetailValidator")
    public RestResponse<ISO151416OrderSearchResult> searchBuyerStoreDetail(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            param.setOrderType(OrderCodeMasterDef.OrderType.BUYER_STOCKPILING_ORDER + "," + OrderCodeMasterDef.OrderType.THIRD_BUYER_ORDER);
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderDetail(param);
            response.setMessage("买手囤货订单明细查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 买手销售订单列表查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/bss/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchBuyerSaleDetailListValidator")
    public RestResponse<ISO151416OrderSearchResult> searchBuyerSaleDetailList(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            param.setOrderType(OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER + "," + OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER);
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderSaleDetailList(param);
            response.setMessage("买手销售订单列表查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 买手销售订单明细查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/bss/detail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchBuyerSaleDetailValidator")
    public RestResponse<ISO151416OrderSearchResult> searchBuyerSaleDetail(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            param.setOrderType(OrderCodeMasterDef.OrderType.BUYER_SALE_ORDER + "," + OrderCodeMasterDef.OrderType.THIRD_BUYER_SALE_ORDER);
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderSaleDetail(param);
            response.setMessage("买手销售订单明细查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 卖家订单列表查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/sl/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchSellerDetailListValidator")
    public RestResponse<ISO151416OrderSearchResult> searchSellerDetailList(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderSaleDetailList(param);
            response.setMessage("卖家订单列表查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 卖家订单明细接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/sl/detail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchSellerDetailValidator")
    public RestResponse<ISO151416OrderSearchResult> searchSellerDetail(@RequestBody RestRequest<ISO151416OrderSearchParam> request) throws Exception {
        ISO151416OrderSearchParam param = request.getParam();
        RestResponse<ISO151416OrderSearchResult> response = new RestResponse<ISO151416OrderSearchResult>();
        try {
            iso151416Service.getBuyerIdByBuyerCode(param);
            ISO151416OrderSearchResult result = iso151416Service.findOrderSaleDetail(param);
            response.setMessage("卖家订单明细接口查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 卖家已卖出商品查询
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/so/slProduct/list",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151416SearchSoldProductListValidator")
    public RestResponse<ISO151416SoldProductResult> searchSoldProductList(@RequestBody RestRequest<ISO151416SoldProductParam> request) throws Exception {
        ISO151416SoldProductParam param = request.getParam();
        RestResponse<ISO151416SoldProductResult> response = new RestResponse<ISO151416SoldProductResult>();
        try {
            ISO151416SoldProductResult result = iso151416Service.searchSoldProductList(param);
            response.setMessage("卖家已卖出商品查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
