package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.logic.IBY121209Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerRecTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * IBY121209RsController.
 *
 * @author zhou_yajun
 */
@RestController
public class IBY121209RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121209RsController.class);

    @Autowired
    private IBY121209Logic iby121209Logic;

    /**
     * 买家收货时间段更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/receiveTime/update",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<String> buyerReceiveTimeUpdate(@RequestBody RsRequest<List<ByBuyerRecTime>> param){
        RsResponse<String> rs = new RsResponse<>();
        if(!CollectionUtils.isEmpty(param.getParam())){
            for (int i = NumberConst.IntDef.INT_ZERO;i<param.getParam().size();i++){
                super.setCommonParam(param.getParam().get(i));
                Date currentDate = DateTimeUtil.getCustomerDate();
                param.getParam().get(i).setUpdTime(currentDate);
                param.getParam().get(i).setCrtTime(currentDate);
                param.getParam().get(i).setActTime(currentDate);
                param.getParam().get(i).setCrtId(param.getLoginId());
                param.getParam().get(i).setUpdId(param.getLoginId());
                param.getParam().get(i).setActId(param.getLoginId());
            }
        int deleteResult = iby121209Logic.buyerReceiveTimeModify(param.getParam());
        if (deleteResult > NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家收货时间段更新成功!");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家收货时间段更新失败!");
        }
     }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("参数为空，买家收货时间段更新失败!");
        }
        return rs;
    }
    /**
     * 买家收货时间段查询接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/receiveTime/findList",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<ByBuyerRecTime>> buyerReceiveTimeFind(@RequestBody RsRequest<ByBuyerRecTime> param){
        RsResponse<List<ByBuyerRecTime>> rs = new RsResponse<>();
        List<ByBuyerRecTime> receiveTimeList = iby121209Logic.buyerReceiveTimeFind(param.getParam());
        if(CollectionUtils.isEmpty(receiveTimeList)){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家收货时间信息不存在");
        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家收货时间获取成功");
            rs.setResult(receiveTimeList);
        }
        return rs;
    }
}
