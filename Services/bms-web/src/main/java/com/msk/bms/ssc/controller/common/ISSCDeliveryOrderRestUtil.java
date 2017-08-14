package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 发货订单相关接口
 * Created by liu_yan2 on 2016/9/6.
 */
public class ISSCDeliveryOrderRestUtil {
    /**logger*/
    private static Logger logger = getLogger(ISSCDeliveryOrderRestUtil.class);

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
     * 查询发货订单列表
     * @param ssc11305RsParam
     * @return
     */
    public static RsResponse<SSC11305RsPageResult> findOrderBasicList(SSC11305RsParam ssc11305RsParam){
        RsRequest<SSC11305RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11305RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindSscDeliveryOrderBasicList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findSscDeliveryOrderBasicList";
        }
        RsResponse<SSC11305RsPageResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11305RsPageResult>>() {
        });
        return rsResponse;
    }

    /**
     * 查询发货订单基本信息
     * @param ssc11306RsParam
     * @return
     */
    public static SSC11306RsBean findOrderBasic(SSC11306RsParam ssc11306RsParam){
        RsRequest<SSC11306RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11306RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindOrderBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findOrderBasic";
        }
        RsResponse<SSC11306RsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11306RsBean>>() {
        });
        SSC11306RsBean orderBasic = new SSC11306RsBean();
        if(rsResponse != null){
            orderBasic = rsResponse.getResult();
        }
        return orderBasic;
    }

    /**
     * 查询发货订单产品信息列表
     * @param ssc11306RsParam
     * @return
     */
    public static PageResult<SSC1130601RsBean> searchOrderPd(SSC11306RsParam ssc11306RsParam){
        RsRequest<SSC11306RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11306RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getSearchOrderPd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/searchOrderPd";
        }
        RsResponse<PageResult<SSC1130601RsBean>> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC1130601RsBean>>>() {
        });
        return rsResponse.getResult();
    }

    /**
     * 查询发货订单产品信息
     * @param ssc11306RsParam
     * @return
     */
    public static SSC1130601RsBean findOrderPd(SSC11306RsParam ssc11306RsParam){
        RsRequest<SSC11306RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11306RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindOrderPd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findOrderPd";
        }
        RsResponse<SSC1130601RsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC1130601RsBean>>() {
        });
        return rsResponse.getResult();
    }

    /**
     * 修改发货订单基本信息
     * @param ssc11306RsParam
     * @return
     */
    public static RsResponse<SSC11306RsBean> modifyOrderBasic(SSC11306RsParam ssc11306RsParam){
        RsRequest<SSC11306RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11306RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyOrderBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyOrderBasic";
        }
        RsResponse<SSC11306RsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11306RsBean>>() {
        });
        return rsResponse;
    }

    /**
     * 修改发货订单产品信息
     * @param ssc1130601RsBean
     * @return
     */
    public static RsResponse<SSC1130601RsBean> modifyOrderPd(SSC1130601RsBean ssc1130601RsBean){
        RsRequest<SSC1130601RsBean> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc1130601RsBean);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyOrderPd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyOrderPd";
        }
        RsResponse<SSC1130601RsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC1130601RsBean>>() {
        });
        return rsResponse;
    }

    /**
     * 新增发货订单基本信息
     * @param ssc11306RsParam
     * @return
     */
    public static RsResponse<SSC11306RsBean> saveOrderBasic(SSC11306RsParam ssc11306RsParam){
        RsRequest<SSC11306RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11306RsParam);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveOrderBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveOrderBasic";
        }
        RsResponse<SSC11306RsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11306RsBean>>() {
        });
        return rsResponse;
    }

    /**
     * 新增发货订单产品信息
     * @param ssc1130601RsBean
     * @return
     */
    public static RsResponse<SSC11306RsBean> saveOrderPd(SSC1130601RsBean ssc1130601RsBean){
        RsRequest<SSC1130601RsBean> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc1130601RsBean);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveOrderPd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveOrderPd";
        }
        RsResponse<SSC11306RsBean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11306RsBean>>() {
        });
        return rsResponse;
    }

}
