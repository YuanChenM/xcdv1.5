package com.msk.batch.br;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.br.logic.BBR121405Logic;
import com.msk.br.bean.IBR121413RsBean;
import com.msk.br.bean.IBR121413RsResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by zhou_yajun on 2016/8/30.
 */
@Component("BBR121405")
public class BBR121405Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121405Batch.class);

    @Autowired
    private BBR121405Logic bbr121405Logic;
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
        RsRequest<BaseParam> request=new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        //modify for 使用配置接口 at 2016/09/06 by zhou_yajun
        //String brUrl = "http://localhost:8080/api/br/synBuyerPdClasses/query";
        String brUrl = SystemServerManager.BuyersReportServerManager.getSynBuyerPdClasses();
        //modify for 使用配置接口 at 2016/09/06 by zhou_yajun
        RsResponse<IBR121413RsResult> brBuyerResult =
                RestClientUtil.post(brUrl, request, new TypeReference<RsResponse<IBR121413RsResult>>() {
                });
        if(null == brBuyerResult.getResult()){
            throw new BusinessException("接口调用失败！");
        }else{
            List<IBR121413RsBean> buyerPdClassesList = brBuyerResult.getResult().getBuyerPdClassesList();
            if(!CollectionUtils.isEmpty(buyerPdClassesList)){
                //更新买家池买家关系
                bbr121405Logic.synBuyerPdClasses(buyerPdClassesList);
                //更新买家销售产品表
                RsRequest<List<IBR121413RsBean>> updateByPdClaRequest = new RsRequest<>();
                updateByPdClaRequest.setSiteCode("1");
                updateByPdClaRequest.setAuth("MSK00001");
                updateByPdClaRequest.setLoginId("msk01");
                updateByPdClaRequest.setParam(buyerPdClassesList);
                String updateByPdClaUrl = SystemServerManager.BuyersServerManage.getUpdateSalePeriod();
                RsResponse<String> updateByPdClaResult =
                        RestClientUtil.post(updateByPdClaUrl, updateByPdClaRequest, new TypeReference<RsResponse<String>>() {
                        });
            }
        }
    }
}
