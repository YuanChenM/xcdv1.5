package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEpBrand;
import com.msk.core.entity.SlPdBrand;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231152RsResult;
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
 * Created by cx on 2016/2/18.
 */
@RestController
public class ISL231152RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231152RsController.class);
    @Autowired
    private ISL231150RsLogic iSL231150RsLogic;
    @Autowired
    private SL241103009Logic sL241103009Logic;
    /**
     * 删除卖家产品品牌
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPdBrandcTeam/delete",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231148Validator")
    public RsResponse<ISL231152RsResult> deleteSLPdBrandc(@RequestBody RsRequest<SlPdBrand> param) {
        logger.debug("删除卖家产品品牌接口");
        RsResponse<ISL231152RsResult> rs = new RsResponse<ISL231152RsResult>();
        SlPdBrand slPdBrand = param.getParam();
        BaseParam baseParam = new BaseParam();
        //查询传过来的slCode是否存在sl_seller中
        baseParam.setFilter("slCode", StringUtil.toSafeString(slPdBrand.getSlCode()));
        //查询传过来的brandEpId和brandId是否存在sl_ep_brand中
        baseParam.setFilter("brandEpId", StringUtil.toSafeString(slPdBrand.getBrandEpId()));
        baseParam.setFilter("brandId", StringUtil.toSafeString(slPdBrand.getBrandId()));
        SlSeller slSeller = sL241103009Logic.findSLCode(baseParam);
        SlEpBrand slEpBrand = iSL231150RsLogic.findSlEpBrandYesOrON(baseParam);
        if(null == slSeller || null == slEpBrand){
            throw new BusinessException("您要删除的数据不存在，请输入正确参数");
        }
        int num = iSL231150RsLogic.removeSLPdBrandc(slPdBrand);
        if(num == 0){
            throw new BusinessException("删除失败，请输入正确的参数");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("删除卖家产品品牌成功");
        return rs;
    }
}
