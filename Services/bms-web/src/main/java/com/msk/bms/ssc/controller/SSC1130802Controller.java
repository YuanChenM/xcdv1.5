package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDeliveryOrderRestUtil;
import com.msk.bms.ssc.controller.common.ISSCPaymentRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.ssc.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * 选择合同/发货订单
 * @author wu_honglei
 */
@Controller
@RequestMapping("SSC1130802")
public class SSC1130802Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC1130802Controller.class);


    /**
     * 选择合同/发货订单
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(Model model){
        logger.info("选择合同/发货订单/核销单");

      /*  List<SSC11308RsBean> paymentRequestList = new ArrayList<>();
        SSC11308RsParam ssc11308RsParam = new SSC11308RsParam();
        ssc11308RsParam.setPaging(false);
        RsResponse<PageResult<SSC11308RsBean>> response = ISSCPaymentRestUtil.checkExistPaymentRequest(ssc11308RsParam);

        if(response.getResult().getData()!=null
                && response.getResult().getData().size()>NumberConst.IntDef.INT_ZERO){
            paymentRequestList = response.getResult().getData();
        }



        //查询合同列表(已生效合同)
        SSC11303RsParam ssc11303RsParam = new SSC11303RsParam();
        String [] contractStatusArr = new String[]{String.valueOf(SscConstant.SscOrderStatus.EFFECTIVE)};
        ssc11303RsParam.setContractStatusArr(contractStatusArr);
        List<SSC11303RsBean> contractList = getContractList(ssc11303RsParam);
        ssc11308RsBean.setContractList(processContractList(contractList,paymentRequestList));


        //查询发货订单列表(双方已确认)
        SSC11305RsParam ssc11305RsParam = new SSC11305RsParam();
        String [] statusArr = new String[]{String.valueOf(SscConstant.DeliveryOrderStatus.CONFIRM)};
        ssc11305RsParam.setStatusArr(statusArr);
        List<SSC11305RsBean> deliverList = getDeliverList(ssc11305RsParam);
        ssc11308RsBean.setDeliverList(processDeliveryList(deliverList,paymentRequestList));

        //查询核销单列表(已确认)
        SSC11321RsParam ssc11321RsParam = new SSC11321RsParam();
        ssc11321RsParam.setStatus(String.valueOf(SscConstant.VerificationStatus.CONFIRM));
        List<SSC11321RsBean> verificationList = getVerificationList(ssc11321RsParam);
        ssc11308RsBean.setVerificationList(processVerificationList(verificationList,paymentRequestList));
        */

        //默认为  预付款
        SSC11308RsBean ssc11308RsBean = new SSC11308RsBean();
        ssc11308RsBean.setPaymentType(SscConstant.SscPaymentType.DOWN_PAYMENT);

        model.addAttribute("ssc1130802RsBean", ssc11308RsBean);
        return "ssc/SSC1130802";
    }



    /**
     * 查询合同列表
     * @param ssc11303RsParam
     * @return
     */
    @RequestMapping(value = "getContractList", method = RequestMethod.POST)
    @ResponseBody
    public List<SSC11303RsBean> getContractList(SSC11303RsParam ssc11303RsParam) {

        RsResponse<PageResult<SSC11303RsBean>> response = ISSCContractRestUtil.findContractList(ssc11303RsParam);

        if(response.getResult()!=null && CollectionUtils.isNotEmpty(response.getResult().getData())){
            return response.getResult().getData();
        }
        return null;
    }


    /**
     * 查询发货单列表
     * @param ssc11305RsParam
     * @return
     */
    public static List<SSC11305RsBean> getDeliverList(SSC11305RsParam ssc11305RsParam) {

        ssc11305RsParam.setDelFlg(String.valueOf(NumberConst.IntDef.INT_ZERO));

        RsResponse<SSC11305RsPageResult> rsResponse = ISSCDeliveryOrderRestUtil.findOrderBasicList(ssc11305RsParam);

        if(rsResponse.getResult()!=null &&
                CollectionUtils.isNotEmpty(rsResponse.getResult().getSSC11305RsBeanPageResult())
                ){
            return rsResponse.getResult().getSSC11305RsBeanPageResult();

        }
        return null;
    }


    /**
     * 查询核销单列表
     * @param ssc11321RsParam
     * @return
     */
    public static List<SSC11321RsBean> getVerificationList(SSC11321RsParam ssc11321RsParam){

        RsResponse<PageResult<SSC11321RsBean>> response = ISSCPaymentRestUtil.findVerificationList(ssc11321RsParam);

        PageResult<SSC11321RsBean> result = response.getResult();

        return result.getData();
    }


    /**
     * 过滤 已经生成过付款申请的合同信息
     * @param contractList
     * @param paymentRequestList
     * @return
     */
    private  List<SSC11303RsBean> processContractList(List<SSC11303RsBean> contractList,List<SSC11308RsBean> paymentRequestList){
        List<SSC11303RsBean> resultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(contractList)
                && CollectionUtils.isNotEmpty(paymentRequestList)){

            Map<Long,SSC11303RsBean> contractMap = new LinkedHashMap<>();
            //将 list 转换为 map,contractId为key
            for (SSC11303RsBean contract:contractList){
                Long contractId = contract.getContractId();
                contractMap.put(contractId,contract);
            }

            //过滤paymentRequestList
            for (SSC11308RsBean paymentRequest:paymentRequestList){
                if(paymentRequest.getPaymentType()==SscConstant.SscPaymentType.DOWN_PAYMENT){
                    Long pContractId = paymentRequest.getContractId();
                    contractMap.remove(pContractId);
                }
            }

            //反转
            Set<Long> keySet = contractMap.keySet();
            for(Iterator iterator = keySet.iterator();iterator.hasNext();){
                Long key = (Long)iterator.next();
                resultList.add(contractMap.get(key));
            }
        }
        return resultList;
    }



    /**
     * 过滤 已经生成过付款申请的发货订单信息
     * @param deliveryList
     * @param paymentRequestList
     * @return
     */
    private  List<SSC11305RsBean> processDeliveryList(List<SSC11305RsBean> deliveryList,List<SSC11308RsBean> paymentRequestList){
        List<SSC11305RsBean> resultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(deliveryList)
                && CollectionUtils.isNotEmpty(paymentRequestList)){

            Map<Long,SSC11305RsBean> deliveryMap = new LinkedHashMap<>();

            //将 list 转换为 map,contractId为key
            for (SSC11305RsBean delivery : deliveryList){
                Long deliveryId = delivery.getDeliveryId();
                deliveryMap.put(deliveryId,delivery);
            }

            //过滤paymentRequestList
            for (SSC11308RsBean paymentRequest:paymentRequestList){
                if(paymentRequest.getPaymentType()==SscConstant.SscPaymentType.PROGRESS_PAYMENT){
                    Long pDeliveryId = paymentRequest.getDeliveryId();
                    deliveryMap.remove(pDeliveryId);
                }
            }

            //反转
            Set<Long> keySet = deliveryMap.keySet();
            for(Iterator iterator = keySet.iterator();iterator.hasNext();){
                Long key = (Long)iterator.next();
                resultList.add(deliveryMap.get(key));
            }
        }
        return resultList;
    }



    /**
     * 过滤 已经生成过付款申请的核销单信息
     * @param verificationList
     * @param paymentRequestList
     * @return
     */
    private  List<SSC11321RsBean> processVerificationList(List<SSC11321RsBean> verificationList,List<SSC11308RsBean> paymentRequestList){
        List<SSC11321RsBean> resultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(verificationList)
                && CollectionUtils.isNotEmpty(paymentRequestList)){

            Map<Long,SSC11321RsBean> verificationMap = new LinkedHashMap<>();

            //将 list 转换为 map,contractId为key
            for (SSC11321RsBean verification : verificationList){
                Long verificationId = verification.getVerificationId();
                verificationMap.put(verificationId,verification);
            }

            //过滤paymentRequestList
            for (SSC11308RsBean paymentRequest:paymentRequestList){
                if(paymentRequest.getPaymentType()==SscConstant.SscPaymentType.BALANCE){
                    Long pVerificationId = paymentRequest.getVerificationId();
                    verificationMap.remove(pVerificationId);
                }
            }

            //反转
            Set<Long> keySet = verificationMap.keySet();
            for(Iterator iterator = keySet.iterator();iterator.hasNext();){
                Long key = (Long)iterator.next();
                resultList.add(verificationMap.get(key));
            }
        }

        return resultList;
    }


}
