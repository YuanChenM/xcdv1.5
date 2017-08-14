package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEpBrand;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231155RsParam;
import com.msk.seller.bean.ISL231155RsResult;
import com.msk.seller.logic.ISL231154RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/18.
 */
@RestController
public class ISL231156RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231156RsController.class);
    @Autowired
    private ISL231154RsLogic iSL231154RsLogic;
    /**
     * 删除企业产品品牌荣誉
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEpBrandHonor/delete",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231156Validator")
    public RsResponse<ISL231155RsResult> deleteSLEpBrandHonor(@RequestBody RsRequest<ISL231155RsParam> param) {
        logger.debug("删除企业产品品牌荣誉接口");
        RsResponse<ISL231155RsResult> rs = new RsResponse<ISL231155RsResult>();
        ISL231155RsParam iSL231155RsParam = param.getParam();
        BaseParam baseParam = new BaseParam();
        SlEpBrand slEpBrand = new SlEpBrand();
        //查询传过来的epId ,brandId 是否存在sl_ep_brand中
        baseParam.setFilter("epId", StringUtil.toSafeString(iSL231155RsParam.getEpId()));
        baseParam.setFilter("brandId", StringUtil.toSafeString(iSL231155RsParam.getBrandId()));
        slEpBrand = iSL231154RsLogic.findSlEpBrand(baseParam);
        if(null == slEpBrand){
            throw new BusinessException("您输入的企业品牌不存在，请输入正确数据");
        }
        int num = iSL231154RsLogic.removeSLEpBrandHonor(param);
        if(num == 0){
            throw new BusinessException("删除失败，请输入正确的参数");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("删除企业产品品牌荣誉成功");
        return rs;
    }
}
