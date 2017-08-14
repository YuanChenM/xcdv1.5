package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlAccount;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231102RsResult;
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
 * Created by cx on 2016/2/14.
 */
@RestController
public class IBYS2101102RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBYS2101102RsController.class);
    @Autowired
    private SL241103001Logic sL241103001Logic;
    /**
     * 增加买手店卖家账户接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/bys/slInfo/slAccount/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231102Validator")
    public RsResponse<ISL231102RsResult> createSLAccountByBuyer(@RequestBody RsRequest<SlAccount> param){
        logger.debug("增加买手店卖家账户接口");
        RsResponse<ISL231102RsResult> rs=new RsResponse<ISL231102RsResult>();
        SlAccount slAccount = param.getParam();
        sL241103001Logic.saveAccount(slAccount);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("增加买手店卖家账户成功");
        return rs;
    }
}
