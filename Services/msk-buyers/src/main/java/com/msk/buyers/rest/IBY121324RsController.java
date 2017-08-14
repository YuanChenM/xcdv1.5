package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.IBY121324RsBean;
import com.msk.buyers.bean.IBY121324RsParam;
import com.msk.buyers.bean.IBY121324RsResult;
import com.msk.buyers.logic.IBY121324Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/10/11.
 */

@RestController
public class IBY121324RsController extends BaseRsController{

    @Autowired
    private IBY121324Logic iby121324Logic;
    @Autowired
    private CommonLogic commonLogic;

    @RequestMapping(value = "/by/invoice/add" ,method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RsResponse<Integer> addInvoice(@RequestBody RsRequest<IBY121324RsParam> param){
        Date currentDate = DateTimeUtil.getCustomerDate();
        RsResponse<Integer> rs = new RsResponse<>();
        int result = 0;

        if(param.getParam().getBuyerId() == null || param.getParam().getBuyerId() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写买家ID");
            return rs;
        }
        if(param.getParam().getVatInvCom() == null || param.getParam().getVatInvCom() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写增票单位名称");
            return rs;
        }
        if(param.getParam().getVatTaxpayer() == null || param.getParam().getVatTaxpayer() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写增票纳税人识别码");
            return rs;
        }
        if(param.getParam().getVatAddr() == null || param.getParam().getVatAddr() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写增票注册地址");
            return rs;
        }
        if(param.getParam().getVatTel() == null || param.getParam().getVatTel() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写增票注册电话");
            return rs;
        }
        if(param.getParam().getVatBank() == null || param.getParam().getVatBank() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写增票开户银行");
            return rs;
        }
        if(param.getParam().getVatAccount() == null || param.getParam().getVatAccount() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写增票银行账号");
            return rs;
        }
        if(param.getParam().getInvReceiverName() == null || param.getParam().getInvReceiverName() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写收发票人姓名");
            return rs;
        }
        if(param.getParam().getInvReceiverTel() == null || param.getParam().getInvReceiverTel() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写收发票人手机号");
            return rs;
        }
        if(param.getParam().getInvReceiverProvince() == null || param.getParam().getInvReceiverProvince() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写收发票人省");
            return rs;
        }
        if(param.getParam().getInvReceiverCity() == null || param.getParam().getInvReceiverCity() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写收发票人市");
            return rs;
        }
        if(param.getParam().getInvReceiverDistrict() == null || param.getParam().getInvReceiverDistrict() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写收发票人区");
            return rs;
        }
        if(param.getParam().getInvReceiverAddr() == null || param.getParam().getInvReceiverAddr() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写收发票人地址");
            return rs;
        }


        param.getParam().setActId(param.getLoginId());
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setCrtId(param.getLoginId());
        param.getParam().setUpdTime(currentDate);
        param.getParam().setCrtTime(currentDate);
        param.getParam().setActTime(currentDate);
        Long maxId = commonLogic.maxId("by_buyer_invoice", "INVOICE_ID");
        param.getParam().setInvoiceId(maxId);
        result = iby121324Logic.save(param.getParam());

        if(result > NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("处理成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败");
        }
        rs.setResult(result);

        return rs;
    }

    @RequestMapping(value = "/by/invoice/delete" ,method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RsResponse<Integer> deleteInvoice(@RequestBody RsRequest<IBY121324RsParam> param){
        RsResponse<Integer> rs = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        int result = 0;
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setUpdTime(currentDate);
        result = iby121324Logic.updateDelete(param.getParam());

        if(result > NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("处理成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败");
        }
        rs.setResult(result);

        return rs;
    }

    @RequestMapping(value = "/by/invoice/update" ,method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RsResponse<Integer> updateInvoice(@RequestBody RsRequest<IBY121324RsParam> param){
        RsResponse<Integer> rs = new RsResponse<>();
        int result = 0;
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setUpdTime(currentDate);
        if(param.getParam().getBuyerId() == null || param.getParam().getBuyerId() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写买家ID");
            return rs;
        }
        if(param.getParam().getInvoiceId() == null){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写发票基础信息ID");
            return rs;
        }
        result = iby121324Logic.updateInvoice(param.getParam());
        if(result > NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("处理成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败");
        }
        rs.setResult(result);

        return rs;
    }


        @RequestMapping(value = "/by/invoice/search" ,method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RsResponse<IBY121324RsResult> searchInvoice(@RequestBody RsRequest<IBY121324RsParam> param){
        RsResponse<IBY121324RsResult> rs = new RsResponse<>();
        List<IBY121324RsBean> iby121324RsBeans  = new ArrayList<>();
        int count = 0;
        if(param.getParam().getBuyerId() == null || param.getParam().getBuyerId() == "" ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("处理失败请填写买家ID");
            return rs;
        }
        iby121324RsBeans = iby121324Logic.findList(param.getParam());
        count = iby121324Logic.getCount(param.getParam());
        IBY121324RsResult result = new IBY121324RsResult();
        result.setInvoiceList(iby121324RsBeans);
        result.setTotalCount(count);
        rs.setResult(result);
            if(count == NumberConst.IntDef.INT_ZERO){
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("没有查询到结果 !");
            }else {
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("处理成功");
            }
        return rs;
    }
}
