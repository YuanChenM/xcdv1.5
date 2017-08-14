package com.msk.br.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121415Result;
import com.msk.br.bean.IBR121415RsBean;
import com.msk.br.bean.IBR121415RsParam;
import com.msk.br.logic.IBR121415Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerPdCla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/28.
 */

@RestController
public class IBR121415RsController extends BaseRsController{

    @Autowired
    private IBR121415Logic ibr121415Logic;

    @Autowired
    private CommonLogic commonLogic;

    @RequestMapping(value = "/br/bindingRelationship/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RsResponse<IBR121415Result> updateBindingRelationship(@RequestBody RsRequest<IBR121415RsParam> param) {

        RsResponse<IBR121415Result> rs = new RsResponse<>();
        int buyerBoolrelationshipCount = 0;
        int buyerHkRelationshipCount = 0;
        int produceCount = 0;
        int buyerCount = 0;
        String result;
        int flag = 0;
        List<IBR121415RsParam> list = new ArrayList<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.getParam().setActTime(currentDate);
        param.getParam().setCrtTime(currentDate);
        param.getParam().setUpdTime(currentDate);
        param.getParam().setActId(param.getLoginId());
        param.getParam().setCrtId(param.getLoginId());
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setStartTime(currentDate);

        if(param.getParam().getHouseCode() == null || param.getParam().getHouseCode() == ""){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("冻品管家编码不能为空");
            return rs;
        }
        if(param.getParam().getSlCode() == null || param.getParam().getSlCode() == ""){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买手店ID不能为空");
            return rs;
        }

        for (int i = 0; i < param.getParam().getRelationList().size(); i++) {
            IBR121415RsParam rsParam = new IBR121415RsParam();
            if(param.getParam().getRelationList().get(i).getBuyerId() ==null || param.getParam().getRelationList().get(i).getBuyerId() == ""){
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家ID不能为空");
                return rs;
            }
            if(param.getParam().getRelationList().get(i).getRelationType() ==null || param.getParam().getRelationList().get(i).getRelationType() == ""){
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家管家关系不能为空");
                return rs;
            }
            if(param.getParam().getRelationList().get(i).getRelationType().equals("0")){
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rsParam.setResult("2");
                rsParam.setBuyerId(param.getParam().getRelationList().get(i).getBuyerId());
                rs.setMessage("买家管家关系数据传入错误");
                list.add(rsParam);
            }

            param.getParam().setBuyerId(param.getParam().getRelationList().get(i).getBuyerId());
            param.getParam().setRelationType(param.getParam().getRelationList().get(i).getRelationType());
            buyerBoolrelationshipCount = ibr121415Logic.findBrBuyerBoolrelationship(param.getParam());

            if (buyerBoolrelationshipCount == NumberConst.IntDef.INT_ZERO) {
                RsRequest<IBR121415RsParam> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(param.getParam());
                String url = SystemServerManager.BuyersServerManage.getFindBuyerDetailInfo();
                RsResponse<ByBuyerBasicInfo> byBuyerBasicInfo =
                        RestClientUtil.post(url, request, new TypeReference<RsResponse<ByBuyerBasicInfo>>() {
                        });
                if (byBuyerBasicInfo != null && byBuyerBasicInfo.getResult() != null) {

                    if (byBuyerBasicInfo.getResult().getSuperiorType().equals("01")) {
                        RsRequest<IBR121415RsParam> requestes = new RsRequest<>();
                        requestes.setSiteCode("1");
                        requestes.setAuth("MSK00001");
                        requestes.setLoginId("msk01");
                        requestes.setParam(param.getParam());
//                    String urls ="http://localhost:8480/msk-buyers/api/by/pdClassification/findList";
                        String urles = SystemServerManager.BuyersServerManage.getBuyerPdClassificationFind();
                        RsResponse<ArrayList<ByBuyerPdCla>> pdClassList =
                                RestClientUtil.post(urles, requestes, new TypeReference<RsResponse<ArrayList<ByBuyerPdCla>>>() {
                                });
                        if (pdClassList != null && pdClassList.getResult() != null) {
                            IBR121415RsBean ibr121415RsBean = new IBR121415RsBean();
                            ibr121415RsBean.setBuyerCode(byBuyerBasicInfo.getResult().getBuyerCode());
                            ibr121415RsBean.setBuyerId(param.getParam().getBuyerId());
                            ibr121415RsBean.setSuperiorType(byBuyerBasicInfo.getResult().getSuperiorType());
                            ibr121415RsBean.setLgcsAreaCode(byBuyerBasicInfo.getResult().getLgcsAreaCode());
                            ibr121415RsBean.setCityCode(byBuyerBasicInfo.getResult().getCityCode());
                            ibr121415RsBean.setClassCode(pdClassList.getResult().get(0).getClassCode());
                            ibr121415RsBean.setMachiningCodeU(pdClassList.getResult().get(0).getMachiningCode());
                            RsRequest<IBR121415RsBean> requestess = new RsRequest<>();
                            requestess.setSiteCode("1");
                            requestess.setAuth("MSK00001");
                            requestess.setLoginId("msk01");
                            requestess.setParam(ibr121415RsBean);
                            //        String url = "http://localhost:8380/msk-br-api/api/br/brBuyerPoolRelationship/updateMarketingPeriod";
                            String urless = SystemServerManager.BuyersReportServerManager.getUpdateMarketingAndSalePeriod();
                            RsResponse<Integer> response = RestClientUtil.post(urless, requestess, new TypeReference<RsResponse<Integer>>() {
                            });
                            if (response == null || response.getResult() == null || response.getResult() == NumberConst.IntDef.INT_ZERO) {
                                rs.setMessage("不是分销买家");
                                rsParam.setResult("3");
                                rsParam.setBuyerId(param.getParam().getBuyerId());
                                list.add(rsParam);
                            } else {
                                flag = NumberConst.IntDef.INT_ONE;
                            }

                        } else {
                            rs.setStatus(SystemConst.RsStatus.FAIL);
                            rs.setMessage("该买家没有归属的买家池");
                            rsParam.setResult("2");
                            rsParam.setBuyerId(param.getParam().getBuyerId());
                            list.add(rsParam);
                        }

                    }else {
                        flag = NumberConst.IntDef.INT_ONE;
                    }

                }else {
                    rs.setStatus(SystemConst.RsStatus.FAIL);
                    rsParam.setResult("1");
                    rsParam.setBuyerId(param.getParam().getBuyerId());
                    rs.setMessage("买家不存在");
                    list.add(rsParam);
                }
            }else {
                flag = NumberConst.IntDef.INT_ONE;
            }

            if(flag != NumberConst.IntDef.INT_ZERO){
                ibr121415Logic.updateBrBuyerPoolRelationship(param.getParam());
                buyerHkRelationshipCount = ibr121415Logic.findBrBuyerHkRelationship(param.getParam());
                if (buyerHkRelationshipCount == NumberConst.IntDef.INT_ZERO) {
                    Long maxId = commonLogic.maxId("BR_BUYER_HK_RELATIONSHIP", "ID");
                    param.getParam().setId(maxId);
                    ibr121415Logic.saveBrBuyerHkRelationship(param.getParam());
                } else {
                    IBR121415RsBean ibr121415RsBeans = new IBR121415RsBean();
                    ibr121415RsBeans = ibr121415Logic.findOne(param.getParam());
                    if(ibr121415RsBeans.getSlCode().equals(param.getParam().getSlCode())){
                        ibr121415Logic.updateBrBuyerHkRelationship(param.getParam());
                    }else{
                        rs.setStatus(SystemConst.RsStatus.FAIL);
                        rsParam.setResult("4");
                        rsParam.setBuyerId(param.getParam().getBuyerId());
                        rs.setMessage("一个管家只能属于一个买家");
                        list.add(rsParam);
                    }
                }
            }
        }


//            if(buyerBoolrelationshipCount != NumberConst.IntDef.INT_ZERO){
//                ibr121415Logic.updateBrBuyerPoolRelationship(param.getParam());
//            }
//            buyerHkRelationshipCount = ibr121415Logic.findBrBuyerHkRelationship(param.getParam());
//            if (buyerHkRelationshipCount == NumberConst.IntDef.INT_ZERO) {
//                Long maxId = commonLogic.maxId("BR_BUYER_HK_RELATIONSHIP", "ID");
//                param.getParam().setId(maxId);
//                ibr121415Logic.saveBrBuyerHkRelationship(param.getParam());
//            } else {
//                IBR121415RsBean ibr121415RsBean = new IBR121415RsBean();
//                ibr121415RsBean = ibr121415Logic.findOne(param.getParam());
//                if(ibr121415RsBean.getSlCode().equals(param.getParam().getSlCode())){
//                    ibr121415Logic.updateBrBuyerHkRelationship(param.getParam());
//                }else{
//                    rs.setStatus(SystemConst.RsStatus.FAIL);
//                    rsParam.setResult("1");
//                    rsParam.setBuyerId(param.getParam().getBuyerId());
//                    rs.setMessage("一个管家只能属于一个买手");
//                    list.add(rsParam);
//                }
//            }
//        }

        if(list.size() == NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("更新成功");
            return rs;
        }else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("更新失败");
            IBR121415Result ibr121415Result = new IBR121415Result();
            ibr121415Result.setBuyerIdList(list);
            rs.setResult(ibr121415Result);
            ibr121415Result.setTotalCount(list.size());
            return rs;
        }
    }

}


