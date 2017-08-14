package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.logic.IBR12130401Logic;
import com.msk.br.logic.IBR12130402Logic;
import com.msk.br.logic.IBR12130403Logic;
import com.msk.br.logic.IBR12130404Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.BrOBuyerInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by yuan_zhifei on 2016/7/22.
 */
@RestController
public class IBR121304RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IBR121304RsController.class);

    @Autowired
    private IBR12130401Logic ibr12130401Logic;
    @Autowired
    private IBR12130402Logic ibr12130402Logic;
    @Autowired
    private IBR12130403Logic ibr12130403Logic;
    @Autowired
    private IBR12130404Logic ibr12130404Logic;

    /**
     * 同步订单数据
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/synchronizedOrderData/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateSynchronizedOrderData(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<Integer> response = new RsResponse<>();
        logger.info("更新已同步的订单数据（订单状态或订单明细状态发生变化的数据）");
        if(param!=null){
            //设置共通字段
                setCommonFiled(param);
                this.ibr12130401Logic.modify(param.getParam());
                logger.info("插入新的订单数据");
                int result = this.ibr12130401Logic.save(param.getParam());
                response.setResult(result);
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setMessage("处理成功");

        }
        return response;
    }

    /**
     * 同步买家数据
     */
    @RequestMapping(value = "/br/synchronizedBuyerData/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateSynchronizedBuyerData(@RequestBody RsRequest<BaseParam> param) {
        logger.info("清空买家需求调研数据");
        RsResponse<Integer> response = new RsResponse<>();
        List<BrOBuyerInfo> buyerInfoList = new ArrayList<>();
        //获取买家上线状态对应的值
        Map<String, String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        int employeeCount = this.ibr12130402Logic.getEmployeeCount(param.getParam());
        int deliveryCount = this.ibr12130402Logic.getDeliveryCount(param.getParam());
        int result = 0;
        if (employeeCount < 1 && deliveryCount < 1) {
            buyerInfoList = this.ibr12130402Logic.findList(param.getParam());
            if (!CollectionUtils.isEmpty(buyerInfoList)) {
                    this.ibr12130402Logic.deleteNeedFeaDetail(param.getParam());
                    logger.info("清空买家经营产品类型数据");
                    this.ibr12130402Logic.deleteBuyerPdCla(param.getParam());
                    logger.info("清空买家基本信息数据");
                    this.ibr12130402Logic.deleteBuyerInfo(param.getParam());
                    logger.info("同步买家基本信息");
                for (int i = NumberConst.IntDef.INT_ZERO; i < buyerInfoList.size(); i++) {
                    BrOBuyerInfo buyerInfo = buyerInfoList.get(i);
                    super.setCommonParam(buyerInfoList.get(i));
                    Date currentDate = DateTimeUtil.getCustomerDate();
                    buyerInfoList.get(i).setCrtTime(currentDate);
                    buyerInfoList.get(i).setUpdTime(currentDate);
                    buyerInfoList.get(i).setActTime(currentDate);
                    buyerInfoList.get(i).setCrtId(param.getLoginId());
                    buyerInfoList.get(i).setUpdId(param.getLoginId());
                    buyerInfoList.get(i).setActId(param.getLoginId());
                    if (BuyersConstant.MarketingsStatus.PreRegister.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus.NoMarket.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus.ActivePeriod.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus.StablePeriodCentral.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus.StablePeriodStandard.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus.EarlyWarnPeriod.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus
                                    .SalePublicBuyers.equals(buyerInfoList.get(i).getMarketingsStatus()) ||
                            BuyersConstant.MarketingsStatus.OutBusiness.equals(buyerInfoList.get(i).getMarketingsStatus())) {
                        buyerInfoList.get(i).setMarketingsStatusName(marketStatus.get(buyerInfoList.get(i).getMarketingsStatus()));
                    } else {
                        buyerInfoList.get(i).setMarketingsStatusName("");
                    }
                    this.ibr12130402Logic.save(buyerInfo);
                    result++;
                }
                logger.info("同步买家经营产品类型数据");
                //设置共通字段
                setCommonFiled(param);
                this.ibr12130402Logic.saveBuyerPdCla(param.getParam());
                logger.info("同步买家需求调研数据");
                this.ibr12130402Logic.saveNeedFeaDetail(param.getParam());
            }
        }
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    ;

    /**
     * 同步卖家基本数据
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/synchronizedSlProduct/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateSynchronizedSlProduct(@RequestBody RsRequest<BaseParam> param) {
        logger.info("清除卖家基本数据");
        RsResponse<Integer> response = new RsResponse<>();
        ibr12130403Logic.deleteSlProduct(param.getParam());
        logger.info("同步卖家基本数据");
        if (param!= null){
            if(param.getParam()!=null){
                setCommonFiled(param);
                int result = ibr12130403Logic.addSlProduct(param.getParam());
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setMessage("同步卖家基本数据成功");
                response.setResult(result);
            }
        }
        return response;
    }

    /**
     * 同步产品数据
     *
     * @param rsRequest
     * @return
     */
    @RequestMapping(value = "/br/synchronizedProduct/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateSynchronizedProduct(@RequestBody RsRequest<BaseParam> rsRequest) {
        RsResponse<Integer> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        BaseParam param = new BaseParam();
        if (rsRequest != null && rsRequest.getParam() != null) {
            setCommonFiled(rsRequest);
            param = rsRequest.getParam();
        }
        /*param.getFilterMap().put("nowTime", currentDate);*/

        logger.info("清除产品12级基本数据");
        ibr12130404Logic.deleteMachiningInfo(param);
        logger.info("清除产品34级基本数据");
        ibr12130404Logic.deleteBreedFeaInfo(param);
        logger.info("清除产品56级基本数据");
        ibr12130404Logic.deleteWeiNorInfo(param);
        logger.info("清空物流区产品原始数据");
        ibr12130404Logic.deletePdLgcsArea(param);

        int result = ibr12130404Logic.addMachiningInfo(param);
        result += ibr12130404Logic.addBreedFeaInfo(param);
        result += ibr12130404Logic.addWeiNorInfo(param);
        result += ibr12130404Logic.addPdLgcsArea(param);

        response.setResult(result);
        if (result > NumberConst.IntDef.INT_ZERO) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("同步产品基本数据成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("没有同步到数据");
        }
        return response;
    }


    public void setCommonFiled(RsRequest<BaseParam> param){
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.getParam().setActId(param.getLoginId());
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setCrtId(param.getLoginId());
        param.getParam().setActTime(currentDate);
        param.getParam().setUpdTime(currentDate);
        param.getParam().setCrtTime(currentDate);
    }

}
