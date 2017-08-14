package com.msk.br.utils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.msk.br.bean.IBR121309RsParam;
import com.msk.br.bean.IBR121309RsResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;

/**
 * Created by guan_zhongheng on 2016/8/23.
 */
public class RestCommUtil {

    private static String STATE_FLAG = "F";
    /**
     * 获取营销\销售管控信息
     * @param param
     * @return
     */
    public static RsResponse<IBR121309RsResult> getSaleMarketControlInfo(IBR121309RsParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = SystemServerManager.BsServerManage.getGetSaleByBuyerId();
        RsResponse<IBR121309RsResult> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121309RsResult>>() {
        });
        if(STATE_FLAG.equals(responce.getStatus())){
            throw new BusinessException(responce.getMessage());
        }
        return responce;
    }
}
