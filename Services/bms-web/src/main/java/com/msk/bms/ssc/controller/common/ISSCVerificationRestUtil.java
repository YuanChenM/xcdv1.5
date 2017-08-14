package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/9/5.
 */
public class ISSCVerificationRestUtil {
    private static final String SITECODE = "1";      //平台编号
    private static final String AUTH = "MSK00001";   //平台身份识别码
    private static final String LOGINID = "msk01";   //用户登录ID
    private static final boolean LOCALHOST = false;  //true：启用本地路径，false：停用
    private static final String LOCALHOST_URL = "http://localhost:8081/msk-ssc-api";    //本地路径

    /**
     * 查询核销单
     */
    public static PageResult<SSC11321RsBean> findVerifications(SSC11321RsParam ssc11321RsParam) {
        RsRequest<SSC11321RsParam> reqParam = new RsRequest<SSC11321RsParam>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11321RsParam);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/searchContractVerification";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getSearchContractVerification();
        }
        TypeReference ref = new TypeReference<RsResponse<PageResult<SSC11321RsBean>>>() {};
        RsResponse<PageResult<SSC11321RsBean>> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult.getResult();
    }

    /**
     * 更新核销单审核状态
     */
    public static RsResponse<SSC11321RsResult> auditVerification(SSC11321RsBean ssc11321RsBean) {
        RsRequest<SSC11321RsBean> reqParam = new RsRequest<SSC11321RsBean>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11321RsBean);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/auditVerification";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getAuditVerification();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11321RsResult>>() {};
        RsResponse<SSC11321RsResult> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

    /**
     * 查询核销单明细
     */
    public static SSC11322Result findVerificationDetails(SSC11322Param ssc11322Param) {
        RsRequest<SSC11322Param> reqParam = new RsRequest<SSC11322Param>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11322Param);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/findVerificationDetails";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getFindVerificationDetails();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11322Result>>() {};
        RsResponse<SSC11322Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult.getResult();
    }

    /**
     * 计算货值差异
     */
    public static List<SSC11322ProductValueBean> calculateProductValueDifference(long contractId) {
        SSC11322Param ssc11322Param = new SSC11322Param();
        ssc11322Param.setContractId(contractId);

        RsRequest<SSC11322Param> reqParam = new RsRequest<SSC11322Param>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11322Param);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/hx/calcDelyIntoDiff";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getCalcDelyIntoDiff();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11322Result>>() {};
        RsResponse<SSC11322Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult.getResult().getProductValues();
    }


    /**
     * 计算运费差异
     */
    public static List<SSC11322TransportCostBean> calculateTransportCostDifference(long contractId) {
        SSC11322Param ssc11322Param = new SSC11322Param();
        ssc11322Param.setContractId(contractId);

        RsRequest<SSC11322Param> reqParam = new RsRequest<SSC11322Param>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11322Param);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/hx/calcTranspExpDiff";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getCalcTranspExpDiff();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11322Result>>() {};
        RsResponse<SSC11322Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult.getResult().getTransportCosts();
    }

    /**
     * 新增或更新核销单及其详情
     */
    public static RsResponse<SSC11322Result> saveOrUpdateVerification(SSC11321RsBean ssc11321RsBean) {
        RsRequest<SSC11321RsBean> reqParam = new RsRequest<SSC11321RsBean>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11321RsBean);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/saveOrUpdateVerification";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getSaveOrUpdateVerification();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11322Result>>() {};
        RsResponse<SSC11322Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

    /**
     * 关闭合同和核销单
     */
    public static RsResponse<SSC11304Result> closeContract(SSC11322Bean ssc11322Bean) {
        RsRequest<SSC11322Bean> reqParam = new RsRequest<SSC11322Bean>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11322Bean);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/closeContract";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getCloseContract();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11304Result>>() {};
        RsResponse<SSC11304Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

    /**
     * 删除核销单
     */
    public static RsResponse<SSC11321RsResult> deleteVerification(SSC11321RsBean ssc11321RsBean) {
        RsRequest<SSC11321RsBean> reqParam = new RsRequest<SSC11321RsBean>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(ssc11321RsBean);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/deleteVerification";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getDeleteVerification();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11321RsResult>>() {};
        RsResponse<SSC11321RsResult> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

}
