package com.msk.seller.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlProduct;
import com.msk.seller.bean.ISL231169RsParam;
import com.msk.seller.bean.ISL231171RsResult;
import com.msk.seller.logic.ISL231169RsLogic;
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
 * Created by cx on 2016/2/24.
 */
@RestController
public class ISL231171RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231171RsController.class);

    @Autowired
    private ISL231169RsLogic iSL231169RsLogic;

    /**
     * 删除卖家产品
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slProduct/delete", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231171Validator")
    public RsResponse<ISL231171RsResult> deleteSLProduct(@RequestBody RsRequest<ISL231169RsParam> param) {
        logger.debug("删除卖家产品接口");
        RsResponse<ISL231171RsResult> rs = new RsResponse<ISL231171RsResult>();
        ISL231169RsParam iSL231169RsParam = param.getParam();
        List<SlProduct> list1 = iSL231169RsParam.getSlPdList();
        for (int j = 0; j < list1.size(); j++) {
            SlProduct slProduct = list1.get(j);
            int slPdId = slProduct.getSlPdId();
            String slCode = slProduct.getSlCode();
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("slCode", slCode);
            baseParam.setFilter("slPdId", String.valueOf(slPdId));
            //删除卖家产品包装规格
            int num = iSL231169RsLogic.removeSlPdPkg(baseParam);
            //删除卖家产品加工技术标准
            int num1 = iSL231169RsLogic.removeSLPdMctStdNew(baseParam);
            //删除卖家产品其他标准
            int num2 = iSL231169RsLogic.removeSLPdTncStdOther(baseParam);
            //删除卖家产品加工质量标准
            int num3 = iSL231169RsLogic.removeSLPdTncStdNew(baseParam);
            //删除卖家产品
            int num4 = iSL231169RsLogic.removeSLProduct(baseParam);
            if (num4 == 0) {
                throw new BusinessException("删除失败，请输入正确的参数");
            }
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("删除卖家产品成功");
        return rs;
    }
}
