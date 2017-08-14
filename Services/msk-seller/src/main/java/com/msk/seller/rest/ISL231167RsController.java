package com.msk.seller.rest;

import com.hoperun.plug.spring.annotation.Validator;
import com.hoperun.core.bean.BaseParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlSeller;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.hoperun.core.consts.SystemConst;
import com.msk.seller.bean.ISL231167RsParam;
import com.msk.seller.bean.ISL231167RsResult;
import com.msk.seller.logic.ISL231166RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cx on 2016/2/18.
 */
@RestController
public class ISL231167RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231167RsController.class);
    @Autowired
    private ISL231166RsLogic iSL231166RsLogic;

    /**
     * 删除卖家产品类别
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPdClasses/delete", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231167Validator")
    public RsResponse<ISL231167RsResult> deleteSLPdClasses(@RequestBody RsRequest<ISL231167RsParam> param) {
        logger.debug("删除卖家产品类别接口");
        RsResponse<ISL231167RsResult> rs = new RsResponse<ISL231167RsResult>();
        ISL231167RsParam iSL231167RsParam = param.getParam();
        //查询传过来的iscode是否存在sl_seller中
        BaseParam baseParam = new BaseParam();
        String isCode = iSL231167RsParam.getSlCode();
        baseParam.setFilter("slCode", StringUtil.toSafeString(iSL231167RsParam.getSlCode()));
        List<SlSeller> slSellerList = iSL231166RsLogic.findIsCode(baseParam);
        if (slSellerList.isEmpty()) {
            throw new BusinessException("传入参数有误请重新输入！");
        }
        int num = iSL231166RsLogic.removeSLPdClasses(param);
        if (num == 0) {
            throw new BusinessException("删除失败，请输入正确的参数");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("删除卖家产品类别成功");
        return rs;
    }
}
