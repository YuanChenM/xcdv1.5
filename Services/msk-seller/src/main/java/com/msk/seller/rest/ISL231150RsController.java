package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231150RsResult;
import com.msk.seller.bean.ISlPdBrand;
import com.msk.seller.logic.ISL231150RsLogic;
import com.msk.seller.logic.SL241103009Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cx on 2016/2/17.
 */
@RestController
public class ISL231150RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231150RsController.class);
    @Autowired
    private ISL231150RsLogic iSL231150RsLogic;
    @Autowired
    private SL241103009Logic sL241103009Logic;

    /**
     * 增加卖家产品品牌接口
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPdBrandcTeam/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231150Validator")
    public RsResponse<ISL231150RsResult> createSLPdBrand(@RequestBody RsRequest<ISlPdBrand> param) {
        logger.debug("增加卖家产品品牌接口");
        RsResponse<ISL231150RsResult> rs = new RsResponse<ISL231150RsResult>();
        ISL231150RsResult result = new ISL231150RsResult();
        ISlPdBrand islPdBrand = param.getParam();
        //查询传过来的slCode是否存在sl_seller中
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode", StringUtil.toSafeString(islPdBrand.getSlCode()));
        //查询传过来的brandEpId和brandId是否存在sl_ep_brand中
        baseParam.setFilter("brandEpId", StringUtil.toSafeString(islPdBrand.getBrandEpId()));
        baseParam.setFilter("brandId", StringUtil.toSafeString(islPdBrand.getBrandId()));
        SlSeller slSeller = sL241103009Logic.findSLCode(baseParam);
        if(null == slSeller){
            throw new BusinessException("您输入的卖家产品品牌没有对应的slcode，请输入正确的slCode");
        }
        SlEpBrand slEpBrand = iSL231150RsLogic.findSlEpBrandYesOrON(baseParam);
        if(null == slEpBrand){
            throw new BusinessException("您输入的卖家产品品牌没有对应的brandEpId或brandId，请输入正确的brandEpId或brandId");
        }
        iSL231150RsLogic.saveSLPdBrand(islPdBrand);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("增加卖家产品品牌成功");
        return rs;
    }
}
