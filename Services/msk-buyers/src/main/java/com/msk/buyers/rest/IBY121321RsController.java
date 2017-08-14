package com.msk.buyers.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.br.bean.IBR121412RsBean;
import com.msk.buyers.logic.IBY121321Logic;
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

import java.util.List;

@RestController
public class IBY121321RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121321RsController.class);

    @Autowired
    private IBY121321Logic iby121321Logic;

    /**
     * 批量根据买家ID更新买家上线状态
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/modifyMarketStatusByBuyerId/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> modifyMarketStatusByBuyerId(@RequestBody RsRequest<List<IBR121412RsBean>> param) {
        RsResponse<Integer> response = new RsResponse<>();
        List<IBR121412RsBean> modifyList = param.getParam();
        if(CollectionUtils.isEmpty(modifyList)){
            response.setStatus(SystemConst.RsStatus.FAIL);
            //response.setMessage("没有需要更新的买家");
            return response;
        }
        int count = iby121321Logic.modifyMarketStatusList(modifyList);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setResult(count);
        return response;
    }
}
