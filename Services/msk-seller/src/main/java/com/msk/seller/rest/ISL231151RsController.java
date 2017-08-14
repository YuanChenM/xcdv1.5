package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlPdBrand;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231151RsResult;
import com.msk.seller.logic.ISL231150RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/19.
 */
@RestController
public class ISL231151RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231151RsController.class);
    @Autowired
    private ISL231150RsLogic iSL231150RsLogic;
    /**
     * 修改卖家产品品牌接口
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPdBrandcTeam/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231151Validator")
    public RsResponse<ISL231151RsResult> updateSlPdBrandcTeam(@RequestBody RsRequest<SlPdBrand> param) {
        logger.debug("修改卖家产品品牌接口");
        RsResponse<ISL231151RsResult> rs = new RsResponse<ISL231151RsResult>();
        SlPdBrand slPdBrand = param.getParam();
        int num = iSL231150RsLogic.updateSlPdBrand(slPdBrand);
        if(num==0){
            throw new BusinessException("更新失败，请输入正确的参数");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("修改卖家产品品牌成功");
        return rs;
    }
}
