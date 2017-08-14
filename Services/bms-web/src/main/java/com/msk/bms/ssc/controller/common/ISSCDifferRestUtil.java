package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 差异单相关接口
 * Created by liu_yan2 on 2016/9/6.
 */
public class ISSCDifferRestUtil {
    /**logger*/
    private static Logger logger = getLogger(ISSCDifferRestUtil.class);
    /** true：启用本地路径，false：停用 */
    private static final boolean LOCALHOST = false;

    /** 调试方便，接口路径可以修改 */
    private static final String LOCALHOST_URL = "http://localhost:8081/msk-ssc-api";

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";

    /**
     * 差异单确认
     * @param ssc11311Bean
     * @return
     */
    public static RsResponse<SSC11311Result> confirmDifferBasic(SSC11311Bean ssc11311Bean){
        RsRequest<SSC11311Bean> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11311Bean);

        String url = SystemServerManager.SellerSupplyChainManage.getConfirmDifferBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/differ/confirm";
        }
        RsResponse<SSC11311Result> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11311Result>>() {
        });
        return rsResponse;
    }


    /**
     * 查询生产商入库差异单
     */
    public static RsResponse<SSC11311Result> queryDifferBasics(SSC11311Param queryParam) {
        RsRequest<SSC11311Param> reqParam = new RsRequest<SSC11311Param>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(queryParam);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/differ/query/basics";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getQueryDifferBasics();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11311Result>>() {};
        RsResponse<SSC11311Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

    /**
     * 查询生产商入库差异单详情
     */
    public static RsResponse<SSC11312Result> queryDifferDetails(SSC11312Param queryParam) {
        RsRequest<SSC11312Param> reqParam = new RsRequest<SSC11312Param>();
        reqParam.setSiteCode(SITECODE);
        reqParam.setAuth(AUTH);
        reqParam.setLoginId(LOGINID);
        reqParam.setParam(queryParam);

        String restUrl = null;
        if (LOCALHOST) {
            restUrl = LOCALHOST_URL + "/api/ssc/differ/query/details";
        }
        else {
            restUrl = SystemServerManager.SellerSupplyChainManage.getQueryDifferDetails();
        }
        TypeReference ref = new TypeReference<RsResponse<SSC11312Result>>() {};
        RsResponse<SSC11312Result> respResult = RestClientUtil.post(restUrl, reqParam, ref);
        return respResult;
    }

}
