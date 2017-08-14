package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 发货确认单相关接口
 * Created by liu_yan2 on 2016/9/6.
 */
public class ISSCDeliveryConfirmOrderRestUtil {
    /**logger*/
    private static Logger logger = getLogger(ISSCDeliveryConfirmOrderRestUtil.class);

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
     * 查询发货单确认单列表(一览)
     * @param ssc11314RsParam
     * @return
     */
    public static PageResult<SSC11314RsResult> findDeliveryConfirmInfoList(SSC11314RsParam ssc11314RsParam){
        RsRequest<SSC11314RsParam> request = new RsRequest<>();
        PageResult<SSC11314RsResult> result = new PageResult<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11314RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryConfirmInfoList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryConfirmInfoList";
        }
        RsResponse<SSC11314RsPageResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11314RsPageResult>>() {
        });
        List<SSC11314RsResult> deliveryConfirmList = new ArrayList<>();
        if(rsResponse != null && !SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            SSC11314RsPageResult ssc11314RsPageResult = rsResponse.getResult();
            deliveryConfirmList = ssc11314RsPageResult.getDeliveryConfirmPageResult();
            result.setRecordsTotal(rsResponse.getResult().getTotalCount());
        }
        result.setData(deliveryConfirmList);

        return result;
    }

    /**
     * 根据合同编号查询对应的发货单号
     * @param ssc11314RsParam
     * @return
     */
    public static RsResponse<SSC11314RsPageResult> findChooseDelivery(SSC11314RsParam ssc11314RsParam) {
        RsRequest<SSC11314RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11314RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindChooseDelivery();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findChooseDelivery";
        }
        RsResponse<SSC11314RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11314RsPageResult>>() {
        });
        return response;
    }

    /**
     * 新建发货确认单
     * @param ssc11314RsParam
     * @return
     */
    public static RsResponse<SSC11314RsPageResult> insertDeliveryConfirm(SSC11314RsParam ssc11314RsParam){
        RsRequest<SSC11314RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11314RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getInsertDeliveryConfirm();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/insertDeliveryConfirm";
        }
        RsResponse<SSC11314RsPageResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11314RsPageResult>>() {
        });
        return rsResponse;
    }

    /**
     * 删除发货确认单
     * @param ssc11314RsParam
     * @return
     */
    public static RsResponse<SSC11314RsResult> deleteConfirmBasic(SSC11314RsParam ssc11314RsParam){
        RsRequest<SSC11314RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11314RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getDeleteConfirmBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deleteConfirmBasic";
        }
        RsResponse<SSC11314RsResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11314RsResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }
        return rsResponse;
    }

    /**
     * 查询发货确认单明细
     * @param ssc11315Param
     * @return
     */
    public static RsResponse<SSC11315DeliveryConfirmRsBean> findDeliveryConfirm(SSC11315Param ssc11315Param) {
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryConfirm();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryConfirm";
        }
        RsResponse<SSC11315DeliveryConfirmRsBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11315DeliveryConfirmRsBean>>() {
        });
        return response;
    }

    /**
     * 发货确认产品信息总计
     * @param ssc11315Param
     * @return
     */
    public static RsResponse<SSC11315DeliveryConfirmDetailRsBean> findDeliveryConfirmDetailTotal(SSC11315Param ssc11315Param) {
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryConfirmDetailTotal();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryConfirmDetailTotal";
        }
        RsResponse<SSC11315DeliveryConfirmDetailRsBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11315DeliveryConfirmDetailRsBean>>() {
        });
        return response;
    }

    /**
     * 查询发货单确认单产品信息列表
     * @param ssc11315Param
     * @return
     */
    public static RsResponse<PageResult<SSC11315DeliveryConfirmDetailRsBean>> findDeliveryConfirmDetail(SSC11315Param ssc11315Param){
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryConfirmDetail();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryConfirmDetail";
        }
        RsResponse<PageResult<SSC11315DeliveryConfirmDetailRsBean>> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11315DeliveryConfirmDetailRsBean>>>() {
        });
        return rsResponse;
    }

    /**
     * 更新发货确认单产品信息
     * @param ssc11315Param
     * @return
     */
    public static RsResponse<SSC11315DeliveryConfirmRsBean> modifyDeliveryConfirmDetail(SSC11315Param ssc11315Param){
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyDeliveryConfirmDetail();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyDeliveryConfirmDetail";
        }
        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11315DeliveryConfirmRsBean>>() {
        });
        return rsResponse;
    }

    /**
     * 更新发货确认单基本信息
     * @param ssc11315Param
     * @return
     */
    public static RsResponse<SSC11315DeliveryConfirmRsBean> modifyDeliveryConfirm(SSC11315Param ssc11315Param){
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyDeliveryConfirm();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyDeliveryConfirm";
        }
        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11315DeliveryConfirmRsBean>>() {
        });
        return rsResponse;
    }

    /**
     * check确认单产品是否全部装车，从而判断是否可以再生成预入库单
     * @param ssc11315Param
     * @return
     */
    public static String checkPdPlanBox(SSC11315Param ssc11315Param){
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getCheckPdPlanBox();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/checkPdPlanBox";
        }
        RsResponse<Integer> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        return rsResponse.getStatus();
    }

    /**
     * 获取发货确认单待装车产品列表
     * @param ssc11315Param
     * @return
     */
    public static List<SSC11315DeliveryConfirmDetailRsBean> chooseConfirmPds(SSC11315Param ssc11315Param){
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);
        String url = SystemServerManager.SellerSupplyChainManage.getChooseConfirmPds();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/chooseConfirmPds";
        }
        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11315DeliveryConfirmRsBean>>() {
        });

        List<SSC11315DeliveryConfirmDetailRsBean> pdBeans = new ArrayList<>();
        if (rsResponse != null && !SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            SSC11315DeliveryConfirmRsBean ssc11315PdRsBean = rsResponse.getResult();
            pdBeans = ssc11315PdRsBean.getDeliveryConfirmDetailist();
        }
        return pdBeans;
    }


}
