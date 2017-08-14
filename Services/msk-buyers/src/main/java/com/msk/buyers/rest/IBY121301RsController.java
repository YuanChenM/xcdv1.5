package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.msk.buyers.bean.IBY121301RsBean;
import com.msk.buyers.logic.BuyerTypeLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/15.
 */
@RestController
public class IBY121301RsController extends BaseRsController {

    @Autowired
    private BuyerTypeLogic buyerTypeLogic;
    /**
     * 获取买家类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/findBuyerTypesList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBY121301RsBean>> findBuyerTypesList(@RequestBody RsRequest<BaseParam> request){
        RsResponse<List<IBY121301RsBean>> response = new RsResponse<List<IBY121301RsBean>>();
        // 买家类型
        List<IBY121301RsBean> iby121301RsBeanList=buyerTypeLogic.findList(request.getParam());
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(iby121301RsBeanList);
        return response;
    }
}
