package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.logic.IBY121227Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 * Created by tao_zhifa on 2016/8/4.
 */
@RestController
public class IBY121227RsController extends BaseRsController{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121226RsController.class);

    @Autowired
    private IBY121227Logic iby121227Logic;
    /**
     * 保存管控工具和时间
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/saveTOOLToDataBase",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> saveTOOLToDataBase(@RequestBody RsRequest<ByBuyerBasicInfo> param){
        RsResponse<Integer> rsResponse = new RsResponse<>();
        if (param.getParam()!=null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            int count = iby121227Logic.modify(param.getParam());
            rsResponse.setResult(count);
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("查询管控工具和时间成功!");
        }
        return rsResponse;
    }


    /**
     * 通过buyerId 查询管理工具
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/findToolToBuyerId",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ByBuyerBasicInfo> findToolToBuyerId(@RequestBody RsRequest<ByBuyerBasicInfo> param){
        RsResponse<ByBuyerBasicInfo> rsResponse = new RsResponse<>();
        ByBuyerBasicInfo byBuyerBasicInfo = new ByBuyerBasicInfo();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId",param.getParam().getBuyerId());
        byBuyerBasicInfo = iby121227Logic.findToolToBuyerIds(baseParam);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("查询管理工具成功!");
        rsResponse.setResult(byBuyerBasicInfo);
        return rsResponse;
    }

    /**
     * 查询收货时间
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/findRecTimeByBuyerId",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ByBuyerBasicInfo> findRecTimeByBuyerId(@RequestBody RsRequest<ByBuyerBasicInfo> param){
        RsResponse<ByBuyerBasicInfo> rsResponse = new RsResponse<>();
        ByBuyerBasicInfo byBuyerBasicInfo = new ByBuyerBasicInfo();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId",param.getParam().getBuyerId());
        byBuyerBasicInfo = iby121227Logic.findRecTimeByBuyerIds(baseParam);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage("查询收货时间成功!");
        rsResponse.setResult(byBuyerBasicInfo);
        return rsResponse;
    }
}
