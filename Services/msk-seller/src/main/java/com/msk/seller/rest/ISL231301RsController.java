package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.param.ISLMultiSellerParam;
import com.msk.seller.bean.result.ISLMultiSellerResult;
import com.msk.seller.logic.comm.ISLMultiSellerLogic;
import org.apache.commons.collections.CollectionUtils;
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
 * Created by zhang_chi on 2016/9/13.
 */
@RestController
public class ISL231301RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(ISL231207RsController.class);

    @Autowired
    private ISLMultiSellerLogic islMultiSellerLogic;


    /**
     * 查询供应商对应的分销资格
     * @param param param
     * @return 产品信息
     */
    @RequestMapping(value = "/sl/seller/disQua",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLMultiSellerResult> querySlSellerDisQua(@RequestBody RsRequest<ISLMultiSellerParam> param){
        logger.debug("查询供应商对应的分销资格");
        RsResponse<ISLMultiSellerResult> rs=new RsResponse<ISLMultiSellerResult>();
        //校验参数的准确性
        if(null == param || null == param.getParam() ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("输入参数错误");
            return rs;
        }
        List<ISLMultiSellerResult>  islMultiSellerResultList =  islMultiSellerLogic.querySlSellerDisQua(param.getParam());
        if (CollectionUtils.isNotEmpty(islMultiSellerResultList)) {
            ISLMultiSellerResult  islMultiSellerResult  = new ISLMultiSellerResult();
            islMultiSellerResult.setSellers(islMultiSellerResultList);
            rs.setResult(islMultiSellerResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询信息不存在");
        }
        return rs;
    }

    /**
     * 查询新增卖家对应产品信息
     * @param param param
     * @return 产品信息
     */
    @RequestMapping(value = "/sl/seller/product",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISLMultiSellerResult> querySlSellerProduct(@RequestBody RsRequest<ISLMultiSellerParam> param){
        logger.debug("查询新增卖家对应产品信息");
        RsResponse<ISLMultiSellerResult> rs=new RsResponse<ISLMultiSellerResult>();
        //校验参数的准确性
        if(null == param || null == param.getParam() ){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("输入参数错误");
            return rs;
        }
        List<ISLMultiSellerResult>  islMultiSellerResultList =  islMultiSellerLogic.querySlSellerProduct(param.getParam());
        if (CollectionUtils.isNotEmpty(islMultiSellerResultList)) {
            ISLMultiSellerResult  islMultiSellerResult  = new ISLMultiSellerResult();
            islMultiSellerResult.setSellers(islMultiSellerResultList);
            rs.setResult(islMultiSellerResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询信息不存在");
        }
        return rs;
    }

}
