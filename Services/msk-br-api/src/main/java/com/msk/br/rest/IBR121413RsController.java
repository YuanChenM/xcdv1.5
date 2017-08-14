package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.msk.br.bean.IBR121413RsBean;
import com.msk.br.bean.IBR121413RsResult;
import com.msk.br.logic.IBR121413Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class IBR121413RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121413RsController.class);

    @Autowired
    private IBR121413Logic ibr121413Logic;

    @RequestMapping(value = "/br/synBuyerPdClasses/query",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121413RsResult> synBuyerPdClasses(@RequestBody RsRequest<BaseParam> param) {
        logger.info("根据订单数据整理买家池");
        RsResponse<IBR121413RsResult> response = new RsResponse<>();
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        IBR121413RsResult buyerPdClasses = new IBR121413RsResult();
        List<IBR121413RsBean> buyerPdClassesList = ibr121413Logic.synBuyerPdClasses(param.getParam());
        buyerPdClasses.setBuyerPdClassesList(buyerPdClassesList);
        response.setResult(buyerPdClasses);
        return response;
    }
}


