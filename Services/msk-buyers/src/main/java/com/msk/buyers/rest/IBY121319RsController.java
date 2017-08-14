package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.IBY121319RsBean;
import com.msk.buyers.bean.IBY121319RsParam;
import com.msk.buyers.logic.IBY121319Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerDelivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IBY121319RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121319RsController.class);

    @Autowired
    private IBY121319Logic iby121319Logic;

    /**
     * 查询买家编码
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/buyerCodeWithRingCode/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121319RsBean> queryBuyerCodeWithRingCode(@RequestBody RsRequest<IBY121319RsParam> param) {
        RsResponse<IBY121319RsBean> response = new RsResponse<>();
        IBY121319RsBean iby121319RsBean = new IBY121319RsBean();
        String shOdDeliveryArea = param.getParam().getShOdDeliveryArea();
        String buyerCode = "";
        String message = "";
        if (!StringUtil.isNullOrEmpty(shOdDeliveryArea) && param.getParam().getCityName().equals("上海")) {
            //传环标码并且配送地址所在城市为上海
            buyerCode = this.changeBuyerCode(param.getParam());
            if(StringUtil.isNullOrEmpty(buyerCode)){
                message = "该买家不存在！";
            }else {
                message = "传环标码并且配送地址所在城市为上海！";
            }
        } else if(param.getParam().getCityName().equals("上海")) {
            //不传环标码配送地址所在城市为上海
            ByBuyerDelivery buyerDelivery = this.iby121319Logic.findShOdDeliveryArea(param.getParam());
            if (buyerDelivery != null) {
                shOdDeliveryArea = buyerDelivery.getShOdDeliveryArea();
                if (!StringUtil.isNullOrEmpty(shOdDeliveryArea)) {
                    //配送地址所在城市为上海且有环标码
                    param.getParam().setShOdDeliveryArea(shOdDeliveryArea);
                    buyerCode = this.changeBuyerCode(param.getParam());
                    if (!StringUtil.isNullOrEmpty(buyerCode)) {
                        message = "配送地址存在所在城市为上海且有环标码！";
                    } else {
                        message = "该买家不存在！";
                    }
                } else {
                    //配送地址所在城市为上海无环标码
                    param.getParam().setShOdDeliveryArea(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                    buyerCode = this.changeBuyerCode(param.getParam());
                    if (!StringUtil.isNullOrEmpty(buyerCode)) {
                        message = "配送地址所在城市为上海无环标码，环标码为0！";
                    } else {
                        message = "该买家不存在！";
                    }
                }
            } else {
                //无配送地址城市为上海
                param.getParam().setShOdDeliveryArea(StringUtil.toString(NumberConst.IntDef.INT_ZERO));
                buyerCode = this.changeBuyerCode(param.getParam());
                if (!StringUtil.isNullOrEmpty(buyerCode)) {
                    message = "无配送地址城市为上海，环标码0！";
                } else {
                    message = "该买家不存在！";
                }
            }
        }else {
            //配送地址所在城市不是上海的
            ByBuyerBasicInfo buyerBasicInfo = this.iby121319Logic.findBuyerCode(param.getParam());
            if(buyerBasicInfo != null){
                buyerCode = buyerBasicInfo.getBuyerCode();
                if(!StringUtil.isNullOrEmpty(buyerCode)){
                    message = "配送地址所在城市不是上海,买家编码不配环标码！";
                }else {
                    message = "该买家不存在！";
                }
            }else {
                message = "该买家不存在！";
            }
        }
        iby121319RsBean.setBuyerCode(buyerCode);
        response.setResult(iby121319RsBean);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage(message);
        return response;
    }

    //变更买家编码
    private String changeBuyerCode(IBY121319RsParam param) {
        String buyerCode;
        String[] buyerCodes;
        String newBuyerCode = "";
        String shOdDeliveryArea = param.getShOdDeliveryArea();
        ByBuyerBasicInfo buyerBasicInfo = this.iby121319Logic.findBuyerCode(param);
        if (buyerBasicInfo != null) {
            buyerCode = buyerBasicInfo.getBuyerCode();
            buyerCodes = buyerCode.split("-");
            if (buyerCodes.length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < buyerCodes.length; i++) {
                    if (i == NumberConst.IntDef.INT_ZERO) {
                        newBuyerCode += buyerCodes[i];
                    } else {
                        newBuyerCode += "-" + shOdDeliveryArea + "-" + buyerCodes[i];
                    }
                }
            }
        }
        return newBuyerCode;
    }
}
