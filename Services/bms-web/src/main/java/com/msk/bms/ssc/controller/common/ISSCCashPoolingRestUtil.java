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

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by peng_hao on 2016/9/13.
 */
public class ISSCCashPoolingRestUtil {

    /**logger*/
    private static Logger logger = getLogger(ISSCCashPoolingRestUtil.class);

    /** true：启用本地路径，false：停用 */
    private static final boolean LOCALHOST = false;

    /** 调试方便，接口路径可以修改 */
    private static final String LOCALHOST_URL = "http://localhost:8084/msk-ssc-api";

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";

    /**
     * 调用接口 查询资金池列表
     * @param ssc11319RsParam
     * @return
     */
    public static PageResult<SSC11319RsBean> findSscCashPoolingInfoList(SSC11319RsParam ssc11319RsParam) {
        RsRequest<SSC11319RsParam> request = new RsRequest<SSC11319RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11319RsParam);
        String url = SystemServerManager.SellerSupplyChainManage.getSearchPayment();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/searchPayment";
        }
        RsResponse<PageResult<SSC11319RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11319RsBean>>>() {

        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }
    /**
     * 调用接口 根据付款id查询资金池详细
     * @param param
     * @return
     */
    public static SSC11320RsBean findSscCashPoolingDetail(SSC11320RsParam param) {
        RsRequest<SSC11320RsParam> request = new RsRequest<SSC11320RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindSscCashPoolingDetail();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findSscCashPoolingDetail";
        }
        RsResponse<SSC11320RsBean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11320RsBean>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }


}
