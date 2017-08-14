package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by wu_honglei on 2016/9/14.
 * 生产商计划
 */
public class ISSCProducePlanRestUtil {

    /**logger*/
    private static Logger logger = getLogger(ISSCPaymentRestUtil.class);

    /** true：启用本地路径，false：停用 */
    private static final boolean LOCALHOST = false;

    /** 调试方便，接口路径可以修改 */
    private static final String LOCALHOST_URL = "http://localhost:8080/msk-ssc-api";

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";


    /**
     * 查询合同基本信息、生效日及交货期中的最后交货日
     *
     * @param param
     * @return 分页查询数据
     */
    public static SSC11326RsBean findContractPlanInfo(SSC11326RsParam param){

        SSC11326RsBean bean = null;
        RsRequest<SSC11326RsParam> request = new RsRequest<SSC11326RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindContractPlanInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findContractPlanInfo";
        }
        RsResponse<SSC11326RsBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11326RsBean>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            bean = response.getResult();
        } else {
            throw new BusinessException(response.getMessage());
        }
        return bean;
    }



    /**
     * 调用接口更新生产商生产开始日期及生产商生产结束日期
     * @param param
     * @return
     */
    public static String updateContractBasic(SscContractBasic param) {
        RsRequest<SscContractBasic> request = new RsRequest<SscContractBasic>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getUpdateContractBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/updateContractBasic";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getStatus();
        } else {
            throw new BusinessException(response.getMessage());
        }
    }



    /**
     * 调用接口 批量保存或更新生产商生产计划
     * @param param
     * @return
     */
    public static int batchSaveOrUpdateProducePlan(SSC11326RsParam param) {
        RsRequest<SSC11326RsParam> request = new RsRequest<SSC11326RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url =  SystemServerManager.SellerSupplyChainManage.getBatchSaveOrUpdateProducePlan();
        if(LOCALHOST){
            url = LOCALHOST_URL + "/api/ssc/batchSaveOrUpdateProducePlan";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        } else {
            throw new BusinessException(response.getMessage());
        }
    }


    /**
     * 调用接口 批批量保存或更新生产商生产计划
     * @param param
     * @return
     */
    public static  int batchSaveOrUpdatePdControl(SSC11326RsParam param) {
        RsRequest<SSC11326RsParam> request = new RsRequest<SSC11326RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url =  SystemServerManager.SellerSupplyChainManage.getBatchSaveOrUpdatePdControl();
        if(LOCALHOST){
            url = LOCALHOST_URL + "/api/ssc/batchSaveOrUpdatePdControl";
        }

        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        } else {
            throw new BusinessException(response.getMessage());
        }
    }


    /**
     * 调用接口 查询运输期产品管控
     * @param param
     * @return
     */
    public static  SSC11326RsResult findDeliveryPdControl(SSC11326RsParam param) {

        RsRequest<SSC11326RsParam> request = new RsRequest<SSC11326RsParam>();

        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryPdControl();
        if(LOCALHOST){
            url = LOCALHOST_URL + "/api/ssc/findDeliveryPdControl";
        }

        RsResponse<SSC11326RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11326RsResult>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }
        return new SSC11326RsResult();
    }




    /**
     * 调用接口获取入库产品管控
     * @param param
     * @return
     */
    public static List<SSC11317PrePdBean> findStockProductDetail(SSC11326RsParam param) {
        List<SSC11317PrePdBean> ssc11317PrePdBeanList = new ArrayList<>();
        RsRequest<SSC11326RsParam> request = new RsRequest<SSC11326RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindStockProductDetail();
        if(LOCALHOST){
            url = LOCALHOST_URL + "/api/ssc/findStockProductDetail";
        }

        RsResponse<SSC11317PreIntoBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11317PreIntoBean>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            ssc11317PrePdBeanList = response.getResult().getSsc11317PrePdBeanList();
        }
        return ssc11317PrePdBeanList;
    }


    /**
     * 调用接口获取生产期/待运期管控
     * @param param
     * @return
     */
    public static SSC11326RsResult findProducePdControl(SSC11326RsParam param) {
        RsRequest<SSC11326RsParam> request = new RsRequest<SSC11326RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);


        String url = SystemServerManager.SellerSupplyChainManage.getFindProducePdControl();
        if(LOCALHOST){
            url = LOCALHOST_URL + "/api/ssc/findProducePdControl";
        }

        RsResponse<SSC11326RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11326RsResult>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }
        return new SSC11326RsResult();
    }




}
