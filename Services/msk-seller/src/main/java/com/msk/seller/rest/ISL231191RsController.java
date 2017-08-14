package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231191Result;
import com.msk.seller.bean.ISL231191RsParam;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.seller.logic.ISL231191RsLogic;
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
 * Created by pxg on 2016/4/26.
 */
@RestController
public class ISL231191RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231191RsController.class);
    @Autowired
    private ISL231191RsLogic isl231191Logic;

    /**
     * 查询卖家产品货号信息
     * 
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slArtNo/search",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.seller.validator.ISL231191Validator")
    public RsResponse<ISL231191Result> searchSlArtNo(@RequestBody RsRequest<ISL231191RsParam> param) {
        logger.debug("查询卖家产品货号接口");
        RsResponse<ISL231191Result> rs = new RsResponse<ISL231191Result>();
        rs.setResult(isl231191Logic.queryData(param));
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("查询卖家产品货号信息成功");
        return rs;
    }

    /**
     * 查询卖家产品货号信息
     * 
     * @return rs 查询卖家产品货号信息
     */
    @RequestMapping(value = "/sl/slInfo/slPdArtno/search",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<SlPdArtnoBean> slPdArtnoSearch(@RequestBody RsRequest<SlPdArtnoBean> param) {
        logger.debug("查询卖家产品货号信息");
        RsResponse<SlPdArtnoBean> rs = new RsResponse<SlPdArtnoBean>();
        SlPdArtnoBean ISL231196RsResult = isl231191Logic.querySlPdArtno(param);
        if (null != ISL231196RsResult) {
            rs.setResult(ISL231196RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品货号信息成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家产品货号信息不存在");
        }
        return rs;
    }

    /**
     * 根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息
     *
     * @return rs 根据卖家、销售平台、物流区域、产品查询产品对应的SKU信息
     */
    @RequestMapping(value = "/sl/slInfo/skuCode/search",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<SlPdArtnoBean> skuCodeSearch(@RequestBody RsRequest<SlPdArtnoBean> param) {
        logger.debug("查询卖家产品SKU信息");
        RsResponse<SlPdArtnoBean> rs = new RsResponse<SlPdArtnoBean>();
        List<SlPdArtnoBean> resultList = isl231191Logic.querySkuCode(param);
        SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
        slPdArtnoBean.setProducts(resultList);
        // slCodeDis、slPdArtno都有值时status为true
        boolean dataStatus = true;
        // 数据库内数据异常判断
        for (SlPdArtnoBean bean : resultList) {
            if (StringUtil.isNullOrEmpty(bean.getSlPdArtno()) || StringUtil.isNullOrEmpty(bean.getSlCodeDis())) {
                // rs.setStatus(SystemConst.RsStatus.FAIL);
                // rs.setMessage("数据异常");
                dataStatus = false;
                break;
            }
        }
        if (dataStatus == false) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("数据异常");
        } else if (null != resultList) {
            rs.setResult(slPdArtnoBean);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品SKU信息成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("未输入的产品信息，查询失败");
        }

        return rs;
    }
}
