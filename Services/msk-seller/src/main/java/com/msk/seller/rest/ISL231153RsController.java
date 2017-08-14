package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlPdBrand;
import com.msk.core.entity.SlProduct;
import com.msk.seller.bean.ISL231153RsParam;
import com.msk.seller.bean.ISL231153RsResult;
import com.msk.seller.logic.ISL231150RsLogic;
import com.msk.seller.logic.ISL231153RsLogic;
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
 * Created by zhang_chi on 2016/4/28.
 */
@RestController
public class ISL231153RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231153RsController.class);
    @Autowired
    private ISL231150RsLogic isl231150RsLogic;
    @Autowired
    private ISL231153RsLogic isl231153RsLogic;
    /**
     * 查询卖家产品品牌
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slPdBrandcTeam/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231153Validator")
    public RsResponse<ISL231153RsResult> querySLPdBrandc(@RequestBody RsRequest<ISL231153RsParam> param) {
        logger.debug("查询卖家产品品牌接口");
        RsResponse<ISL231153RsResult> rs = new RsResponse<ISL231153RsResult>();
        ISL231153RsResult result = new ISL231153RsResult();
        result = isl231150RsLogic.findSLPdBrandcAllList(param);
        if(null ==  result.getSlPdBrandList()){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家产品品牌不存在");
        }else{
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品品牌成功");
        }
        return rs;
    }


    /**
     * 查询卖家产品品牌信息（多个)
     * @return rs  查询卖家产品品牌信息（多个)
     */
    @RequestMapping(value = "/sl/slInfo/slPdBrand/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<SlPdBrand>> slPdBrandSearch(@RequestBody RsRequest<SlProduct> param) {
        logger.debug("查询卖家产品品牌信息接口");
        RsResponse<List<SlPdBrand>> rs = new RsResponse<List<SlPdBrand>>();
        List<SlPdBrand>   slProductList =  isl231153RsLogic.querySlPdBrand(param);
        if (null != slProductList && slProductList.size()>0 ) {
            rs.setResult(slProductList);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品品牌信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家产品品牌信息不存在");
        }
        return rs;
    }
}
