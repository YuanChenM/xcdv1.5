package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121412RsBean;
import com.msk.br.bean.IBR121412RsResult;
import com.msk.br.logic.IBR121412Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
public class IBR121412RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121412RsController.class);

    @Autowired
    private IBR121412Logic ibr121412Logic;

    @RequestMapping(value = "/br/synMarketingStatusBuyer/query",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121412RsResult> synMarketingStatusByOrder(@RequestBody RsRequest<BaseParam> param) {
        logger.info("根据订单数据同步买家上线状态");
        RsResponse<IBR121412RsResult> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        if(param!= null){
            param.getParam().setActId(param.getLoginId());
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setActTime(currentDate);
            param.getParam().setUpdTime(currentDate);
            param.getParam().setCrtTime(currentDate);

        }
        List<IBR121412RsBean> modifyBuyerList = ibr121412Logic.synMarketingStatusByOrder(param.getParam());
        if(!CollectionUtils.isEmpty(modifyBuyerList)){
            //更新买家池买家上线状态
            ibr121412Logic.modifyMarketStatusList(modifyBuyerList);
            //更新买家买家池编码
            ibr121412Logic.modifyBuyerPoolCode(modifyBuyerList);
        }

        // 根据状态修改计划 修改买家上线状态
        ibr121412Logic.updateBuyerMarketingStatus();

        response.setStatus(SystemConst.RsStatus.SUCCESS);
        IBR121412RsResult modifyResult = new IBR121412RsResult();
        modifyResult.setModifyBuyerList(modifyBuyerList);
        response.setResult(modifyResult);

        return response;
    }
}


