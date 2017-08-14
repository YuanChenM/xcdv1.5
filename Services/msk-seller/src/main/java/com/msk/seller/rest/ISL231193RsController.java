package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SoSalesRanking;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.seller.bean.SlProductBean;
import com.msk.seller.logic.ISL231193RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchi on 2016/5/9.
 */
@RestController
public class ISL231193RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231193RsController.class);
    @Autowired
    private ISL231193RsLogic isl231193Logic;
    /**
     * 查询卖家产品编码
     * @param param param
     * @return rs  根据卖家ID和物流区查询卖家产品编码
     */
    @RequestMapping(value = "/sl/slInfo/slPdCode/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISL231193RsResult>> slPdCodeSearch(@RequestBody RsRequest<ISL231193RsParam> param) {
        logger.debug("查询卖家产品编码接口");
        RsResponse<List<ISL231193RsResult>> rs = new RsResponse<List<ISL231193RsResult>>();
        List<ISL231193RsResult>   isl231193Result =  isl231193Logic.querySlProduct(param);
        if (null != isl231193Result && isl231193Result.size() > 0) {
            rs.setResult(isl231193Result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品编码成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家产品编码不存在");
        }
        return rs;
    }

    /**
     * 查询创建时间范围内的卖家用户
     * @param param param
     * @return rs  查询创建时间范围内的卖家用户
     */
    @RequestMapping(value = "/sl/slInfo/soSalesRanking/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231193RsResult> soSalesRankingSearch(@RequestBody RsRequest<ISL231193RsParam> param) {
        logger.debug("查询创建时间范围内的卖家用户接口");
        RsResponse<ISL231193RsResult> rs = new RsResponse<ISL231193RsResult>();
        List<SoSalesRanking>   soSalesRankingList =  isl231193Logic.querySlSellerProduct(param);
        if (null != soSalesRankingList && soSalesRankingList.size() > 0) {
            ISL231193RsResult isl231193RsResult = new ISL231193RsResult();
            isl231193RsResult.setSoSalesRankingList(soSalesRankingList);
            rs.setResult(isl231193RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询创建时间范围内的卖家用户成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询创建时间范围内的卖家用户不存在");
        }
        return rs;
    }

    /**
     * 查询供应商名称
     * @param param param
     * @return rs   根据卖家ID查询供应商名称
     */
    @RequestMapping(value = "/sl/slInfo/slEpName/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231193RsResult> slEpNameSearch(@RequestBody RsRequest<ISL231193RsParam> param) {
        logger.debug("查询供应商名称接口");
        RsResponse<ISL231193RsResult> rs = new RsResponse<ISL231193RsResult>();
        List<ISL231193RsResult> isl231193ResultList = isl231193Logic.querySlEnterprise(param);
        if (null != isl231193ResultList && isl231193ResultList.size() > 0) {
            ISL231193RsResult isl231193RsResult = new ISL231193RsResult();
            isl231193RsResult.setIsl231193RsList(isl231193ResultList);
            rs.setResult(isl231193RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询供应商名称成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询供应商名称不存在");
        }
        return rs;
    }

    /**
     * 查询卖家身份企业信息
     * @param param param
     * @return rs   根据卖家Account查询供应商名称
     */
    @RequestMapping(value = "/sl/slInfo/slEpData/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231193RsResult> slEpDataSearch(@RequestBody RsRequest<ISL231193RsParam> param) {
        logger.debug("查询卖家身份企业信息接口");
        RsResponse<ISL231193RsResult> rs = new RsResponse<ISL231193RsResult>();
        ISL231193RsResult isl231193Result = isl231193Logic.queryslEpData(param);
        if (null != isl231193Result) {
            rs.setResult(isl231193Result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家身份企业信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家身份企业信息不存在");
        }
        return rs;
    }

    /**
     * 批量查询卖家身份企业信息
     * @param param param
     * @return rs   批量查询卖家身份企业信息
     */
    @RequestMapping(value = "/sl/slInfo/slEpDataList/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231193RsResult> slEpDataListSearch(@RequestBody RsRequest<ISL231193RsParam> param) {
        logger.debug("批量查询卖家身份企业信息接口");
        RsResponse<ISL231193RsResult> rs = new RsResponse<ISL231193RsResult>();
        List<ISL231193RsResult> isl231193Result = isl231193Logic.querySlEpDataList(param);
        if (null != isl231193Result && isl231193Result.size() > 0) {
            ISL231193RsResult isl231193RsResult = new ISL231193RsResult();
            isl231193RsResult.setIsl231193RsList(isl231193Result);
            rs.setResult(isl231193RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("批量查询卖家身份企业信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("批量查询卖家身份企业信息不存在");
        }
        return rs;
    }

    /**
     *  查询卖家产品信息
     * @param param param
     * @return rs   根据卖家ID、产品一级分类编码、产品二级分类编码、产品品种编码、产品特征编码、净重编码查询对应的卖家产品信息
     */
    @RequestMapping(value = "/sl/slInfo/slProduct/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231193RsResult> slProductSearch(@RequestBody RsRequest<SlPdArtnoBean> param) {
        logger.debug("查询卖家产品信息接口");
        RsResponse<ISL231193RsResult> rs = new RsResponse<ISL231193RsResult>();
        List<SlProductBean> slProductBean = isl231193Logic.findSlProduct(param);
        if (null != slProductBean && slProductBean.size()>0) {
            ISL231193RsResult isl231193RsResult =  new ISL231193RsResult();
            isl231193RsResult.setSlProductList(slProductBean);
            rs.setResult(isl231193RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家产品信息不存在");
        }
        return rs;
    }

    /**
     * 查询卖家（显示）编码
     * @param param param
     * @return rs  查询卖家（显示）编码
     */
    @RequestMapping(value = "/sl/slInfo/slSellerCode/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231193RsResult> slSellerCodeSearch(@RequestBody RsRequest<ISL231193RsParam> param) {
        logger.debug("查询卖家（显示）编码接口");
        RsResponse<ISL231193RsResult> rs = new RsResponse<ISL231193RsResult>();
        ISL231193RsParam isl231193RsParam = param.getParam();
        List<ISL231193RsResult>   isl231193RsList =  isl231193Logic.findSlSellerCode(isl231193RsParam);
        if (null != isl231193RsList && isl231193RsList.size() > 0) {
            ISL231193RsResult isl231193RsResult = new ISL231193RsResult();
            isl231193RsResult.setIsl231193RsList(isl231193RsList);
            rs.setResult(isl231193RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家（显示）编码成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家（显示）编码不存在");
        }
        return rs;
    }

}
