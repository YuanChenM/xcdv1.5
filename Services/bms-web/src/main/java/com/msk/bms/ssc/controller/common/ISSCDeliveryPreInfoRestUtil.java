package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ds.bean.ISC1892I1Param;
import com.msk.ds.bean.ISC1892I1RsResult;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 预入库相关接口
 * Created by liu_yan2 on 2016/9/6.
 */
public class ISSCDeliveryPreInfoRestUtil {
    /**logger*/
    private static Logger logger = getLogger(ISSCDeliveryPreInfoRestUtil.class);

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
     * 根据预入库ID或Code查询预入库基本信息
     * @param param
     * @return
     */
    public static SSC11317PreIntoBean getFindDeliveryPreInfo(SSC11317RsParam param) {
        RsRequest<SSC11317RsParam> request = new RsRequest<SSC11317RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryPreInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryPreInfo";
        }
        RsResponse<SSC11317PreIntoBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11317PreIntoBean>>() {
        });
        return response.getResult();
    }

    /**
     * 查询预入库通知单列表(一览)
     * @param ssc11316Param
     * @return
     */
    public static RsResponse<SSC11316Result> findPreIntoBasicList(SSC11316Param ssc11316Param){
        RsRequest<SSC11316Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11316Param);

        String url = SystemServerManager.SellerSupplyChainManage.getQueryPreintoBasics();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/preinto/query/basics";
        }
        RsResponse<SSC11316Result> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11316Result>>() {
        });
        return rsResponse;
    }

    /**
     * 查询预入库通知单基本信息列表
     * @param ssc11316Param
     * @return
     */
    public static List<SSC11316Bean> findPreInto(SSC11316Param ssc11316Param){
        RsRequest<SSC11316Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11316Param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPreInto();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPreInto";
        }
        RsResponse<SSC11316Bean[]> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11316Bean[]>>() {
        });
        List<SSC11316Bean> list = new ArrayList<>();

        if(null != rsResponse.getResult() ){
            list = Arrays.asList(rsResponse.getResult());
        }
        return list;
    }

    /**
     * 新建发货预入库单
     * @param ssc11315Param
     * @return
     */
    public static RsResponse<SSC11315DeliveryConfirmRsBean> insertDeliveryPreInto(SSC11315Param ssc11315Param){
        RsRequest<SSC11315Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11315Param);

        String url = SystemServerManager.SellerSupplyChainManage.getInsertDeliveryPreInto();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/insertDeliveryPreInto";
        }
        RsResponse<SSC11315DeliveryConfirmRsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11315DeliveryConfirmRsBean>>() {
        });
        return rsResponse;
    }

    /**
     * 删除预入库通知单
     * @param ssc11316Param
     * @return
     */
    public static RsResponse<SSC11316Bean> deletePreInto(SSC11316Param ssc11316Param){
        RsRequest<SSC11316Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11316Param);

        String url = SystemServerManager.SellerSupplyChainManage.getDeletePreInto();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deletePreInto";
        }
        RsResponse<SSC11316Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11316Bean>>() {
        });
        return rsResponse;
    }

    /**
     * 查询预入库通知单产品明细
     * @param param
     * @return
     */
    public static RsResponse<PageResult<SSC11317PrePdBean>> findDeliveryPrePd(SSC11317RsParam param) {
        RsRequest<SSC11317RsParam> request = new RsRequest<SSC11317RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryPrePd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryPrePd";
        }
        RsResponse<PageResult<SSC11317PrePdBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11317PrePdBean>>>() {
        });
        return response;
    }

    /**
     * 更新预入库通知单基本信息
     * @param ssc11317RsParam
     * @return
     */
    public static RsResponse<SSC11317PreIntoBean> modifyDeliveryPreInto(SSC11317RsParam ssc11317RsParam){
        RsRequest<SSC11317RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11317RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyDeliveryIntoInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyDeliveryIntoInfo";
        }
        RsResponse<SSC11317PreIntoBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11317PreIntoBean>>() {
        });
        return rsResponse;
    }

    /**
     * 修改预入库实际入库数据 以及更新销售库存
     * @param isc1892I1Param
     * @return
     */
    public static RsResponse<ISC1892I1RsResult> updatePreInto(ISC1892I1Param isc1892I1Param){
        RsRequest<ISC1892I1Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(isc1892I1Param);

        String url = SystemServerManager.SellerSupplyChainManage.getUpdatePreInto();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/updatePreInto";
        }
        RsResponse<ISC1892I1RsResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISC1892I1RsResult>>() {
        });
        return rsResponse;
    }

    /**
     * 插入预入库文件信息
     * @param ssc11317PreIntoBean
     * @return
     */
    public static RsResponse<SSC11317PreIntoBean> savePreIntoFile(SSC11317PreIntoBean ssc11317PreIntoBean){
        RsRequest<SSC11317PreIntoBean> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11317PreIntoBean);

        String url = SystemServerManager.SellerSupplyChainManage.getSavePreIntoFile();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/savePreIntoFile";
        }
        RsResponse<SSC11317PreIntoBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11317PreIntoBean>>() {
        });
        return rsResponse;
    }

    /**
     * 获取发货订单关联的预入库通知单信息
     * @param ssc11317RsParam
     * @return
     */
    public static List<SSC11317PreIntoBean> findPreIntoListByDeliveryId(SSC11317RsParam ssc11317RsParam){
        RsRequest<SSC11317RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11317RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPreIntoListByDeliveryId();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPreIntoListByDeliveryId";
        }
        RsResponse<SSC11317PreIntoBean[]> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11317PreIntoBean[]>>() {
        });
        List<SSC11317PreIntoBean> list = new ArrayList<>();

        if(null != rsResponse.getResult()){
            list = Arrays.asList(rsResponse.getResult());
        }
        return list;
    }


}
