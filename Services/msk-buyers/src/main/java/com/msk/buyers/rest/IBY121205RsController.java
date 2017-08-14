package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.BuyerLicenceBean;
import com.msk.buyers.logic.IBY121205Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.ByBuyerLicence;
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
 * IBY121205RsController.
 *
 * @author zhou_yajun
 */
@RestController
public class IBY121205RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121205RsController.class);

    @Autowired
    private IBY121205Logic iby121205Logic;

    /**
     * 买家证照信息更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/licence/update",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Object> buyerLicenceUpdate(@RequestBody RsRequest<BuyerLicenceBean> param){
        RsResponse<Object> rs = new RsResponse<>();
        if(param.getParam()!=null){
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.getParam().setCrtId(param.getLoginId());
            param.getParam().setCrtTime(currentDate);
            param.getParam().setUpdId(param.getLoginId());
            param.getParam().setUpdTime(currentDate);
            param.getParam().setActId(param.getLoginId());
            param.getParam().setActTime(currentDate);

        int resultCount = iby121205Logic.buyerLicenceModify(param.getParam());
        if(resultCount == NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家证照信息更新失败！");
        }else {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家证照信息更新成功");
        }

        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("參數為空，买家证照信息更新成功");
        }
        return rs;
    }
    /**
     * 买家证照信息查询接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/licence/find",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ByBuyerLicence> buyerLicenceFind(@RequestBody RsRequest<BuyerLicenceBean> param){
        RsResponse<ByBuyerLicence> rs = new RsResponse<>();
        ByBuyerLicence licence = iby121205Logic.buyerLicenceFind(param.getParam());
        if(null == licence){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家证照信息不存在");
        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家证照信息获取成功");
            rs.setResult(licence);
        }
        return rs;
    }
}
