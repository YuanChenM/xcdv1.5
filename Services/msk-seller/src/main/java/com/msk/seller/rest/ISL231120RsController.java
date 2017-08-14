package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlSeller;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231120RsResult;
import com.msk.seller.logic.SL241103001Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/15.
 */
@RestController
public class ISL231120RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(ISL231120RsController.class);
    @Autowired
    private SL241103001Logic sL241103001Logic;

    /**
     * 增加卖家基本信息接口
     * @param param
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/slSeller/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231120Validator")
    public RsResponse<ISL231120RsResult> createSlSeller(@RequestBody RsRequest<SlSeller> param){
        logger.debug("增加卖家基本信息接口");
        RsResponse<ISL231120RsResult> rs=new RsResponse<ISL231120RsResult>();
        SlSeller slSeller = param.getParam();
        sL241103001Logic.saveSeller(slSeller);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("增加卖家基本信息成功");
        return rs;
    }
}
