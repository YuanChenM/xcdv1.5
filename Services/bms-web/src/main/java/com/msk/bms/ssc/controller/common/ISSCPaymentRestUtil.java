package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.ui.Model;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by wu_honglei on 2016/9/7.
 */
public class ISSCPaymentRestUtil {

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
     * 根据主键查询支付信息
     * @param ssc11308RsParam
     * @return
     */
    public static SSC11308RsBean findPaymentRequestOne(SSC11308RsParam  ssc11308RsParam){

        RsResponse<PageResult<SSC11308RsBean>> response =   findPaymentRequestList(ssc11308RsParam);

        if(response.getResult().getData()!=null
                && response.getResult().getData().size()> NumberConst.IntDef.INT_ZERO){
            return response.getResult().getData().get(0);
        }
        return new SSC11308RsBean();
    }



    /**
     * 根据主键查询支付信息
     * @param ssc11308RsParam
     * @return
     */
    public static  RsResponse<PageResult<SSC11308RsBean>> findPaymentRequestList(SSC11308RsParam  ssc11308RsParam){

        RsRequest<SSC11308RsParam> request = new RsRequest<SSC11308RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11308RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPaymentRequest();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPaymentRequest";
        }
        RsResponse<PageResult<SSC11308RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11308RsBean>>>() {});

        return response;
    }




    /**
     * 分页查询付款申请列表
     *
     * @param ssc11307RsParam
     * @return 分页查询数据
     */
    public static  RsResponse<PageResult<SSC11307RsBean>> findSscPaymentRequest(SSC11307RsParam ssc11307RsParam){
        RsRequest<SSC11307RsParam> request = new RsRequest<SSC11307RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11307RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindSscPaymentRequest();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findSscPaymentRequest";
        }

        RsResponse<PageResult<SSC11307RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11307RsBean>>>() {
        });
        return response;
    }


    /**
     * 更新或修改
     * @param ssc11308RsBean
     * @return
     */
    public static  RsResponse<SSC11308RsBean>saveOrModifyPaymentRequest(SSC11308RsBean ssc11308RsBean){
        RsRequest<SSC11308RsBean> request = new RsRequest<SSC11308RsBean>();
        request.setSiteCode(SITECODE);
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setParam(ssc11308RsBean);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveOrModifyPaymentRequest();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveOrModifyPaymentRequest";
        }
        RsResponse<SSC11308RsBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11308RsBean>>() {
        });
        return response;
    }


    /**
     * 删除付款申请信息
     * @param param
     * @return
     */
    public  static RsResponse<Integer>  deletePaymentRequest(SSC11307RsParam param){
        RsRequest<SSC11307RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getDeleteSscPaymentRequest();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deleteSscPaymentRequest";
        }

        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() { });
        return  response;
    }

    /**
     * 分页查询支付申请
     * @param ssc1130801RsParam
     * @return
     */
    public static RsResponse<PageResult<SSC1130801RsBean>> searchPaymentRequestList(SSC1130801RsParam ssc1130801RsParam) {
        RsRequest<SSC1130801RsParam> request = new RsRequest<SSC1130801RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc1130801RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPaymentInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPaymentInfo";
        }

        RsResponse<PageResult<SSC1130801RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC1130801RsBean>>>() {});
        return response;
    }

    /**
     * 检查付款单是否存在
     * @param ssc11308RsParam
     * @return
     */
    public static   RsResponse<PageResult<SSC11308RsBean>> checkExistPaymentRequest(SSC11308RsParam  ssc11308RsParam){
        RsRequest<SSC11308RsParam> request = new RsRequest<SSC11308RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11308RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPaymentRequest();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPaymentRequest";
        }

        RsResponse<PageResult<SSC11308RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11308RsBean>>>() {});

        return response;
    }


    /**
     * 预付款按比例已支付金额
     * @param param
     * @return
     */
    public static  RsResponse<String> findDeliveryPDList(SSC1130802RsParam param){
        param.setContractRelationType(SscConstant.RelationType.NORMAL);
        RsRequest<SSC1130802RsParam> request = new RsRequest<SSC1130802RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryPDList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryPDList";
        }

        RsResponse<String> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<String>>() {});

        return response;
    }

    /**
     * 新增or修改支付管控记录
     * @param ssc1130801RsBean
     * @return
     */
    public static  RsResponse<Integer> saveOrModifyPaymentInfo(SSC1130801RsBean ssc1130801RsBean){
        RsRequest<SSC1130801RsBean> request = new RsRequest<SSC1130801RsBean>();
        request.setSiteCode(SITECODE);
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setParam(ssc1130801RsBean);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveOrModifyPaymentInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveOrModifyPaymentInfo";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });

        return response;
    }

    /**
     * 查询支付管控列表
     * @param ssc1130801RsParam
     * @return
     */
    public static  RsResponse<PageResult<SSC1130801RsBean>> findPaymentInfoList(SSC1130801RsParam ssc1130801RsParam){
        RsRequest<SSC1130801RsParam> request = new RsRequest<SSC1130801RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc1130801RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPaymentInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPaymentInfo";
        }

        RsResponse<PageResult<SSC1130801RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC1130801RsBean>>>() {});

        return response;
    }

    /**
     * 建议修改
     * 查询核销单列表
     * @param ssc11321RsParam
     * @return
     */
    public static  RsResponse<PageResult<SSC11321RsBean>> findVerificationList(SSC11321RsParam ssc11321RsParam){

        RsRequest<SSC11321RsParam> request = new RsRequest<SSC11321RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11321RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getSearchContractVerification();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/searchContractVerification";
        }

        RsResponse<PageResult<SSC11321RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11321RsBean>>>() {});

        return response;
    }
}
