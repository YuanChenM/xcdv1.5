package com.msk.batch.br;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.br.bean.IBR121412RsBean;
import com.msk.br.bean.IBR121412RsResult;
import com.msk.bs.bean.IBS2101132RsParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SlBsBuyer;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhou_yajun on 2016/8/30.
 * Update by zhang_jian4 on 2016/10/10
 */
@Component("BBR121404")
public class BBR121404Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121404Batch.class);

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        BaseParam param = new BaseParam();
        //设置共通参数
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId("admin");
        param.setCrtTime(currentDate);
        param.setActId("admin");
        param.setActTime(currentDate);
        param.setUpdId("admin");
        param.setUpdTime(currentDate);
        return param;
    }

    /**
     * 业务处理
     *
     * @param param
     */
    @Override
    public void doOperate(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("Batch");
        request.setParam(param);

        //String brUrl = "http://localhost:8082/api/br/synMarketingStatusBuyer/query";
        String brUrl = SystemServerManager.BuyersReportServerManager.getSynMarketingStatusByOrder();
        RsResponse<IBR121412RsResult> brBuyerResult =
                RestClientUtil.post(brUrl, request, new TypeReference<RsResponse<IBR121412RsResult>>() {
                });
        if (null == brBuyerResult.getResult()) {
            throw new BusinessException("接口调用失败！");
        } else {
            List<IBR121412RsBean> modifyBuyerList = brBuyerResult.getResult().getModifyBuyerList();
            if(!CollectionUtils.isEmpty(modifyBuyerList)){
                RsRequest<List<IBR121412RsBean>> modifyRequest = new RsRequest<>();
                modifyRequest.setSiteCode("1");
                modifyRequest.setAuth("MSK00001");
                modifyRequest.setLoginId("msk01");
                modifyRequest.setParam(modifyBuyerList);
//                String byUrl = "http://localhost:8080/msk-buyers/api/by/modifyMarketStatusByBuyerId/update";
                String byUrl = SystemServerManager.BuyersServerManage.getModifyMarketStatusByBuyerId();
                RsResponse<Integer> modifyBuyerResult =
                        RestClientUtil.post(byUrl, modifyRequest, new TypeReference<RsResponse<Integer>>() {
                        });
            }

        }
    }

}
