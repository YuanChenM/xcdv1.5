package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231199RsBean;
import com.msk.seller.bean.ISL231199RsPageBean;
import com.msk.seller.bean.ISL231199RsParam;
import com.msk.seller.logic.ISL231199RsLogic;
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
 * Created by geng_xingdong on 2016/6/16.
 */
@RestController
public class ISL231199RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231199RsController.class);
    @Autowired
    private ISL231199RsLogic isl231199Logic;

    /**
     * 查询卖家区域code和name
     *
     * @return rs  返回卖家code和name
     */
    @RequestMapping(value = "/sl/slInfo/slCodeName/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231199RsBean> slPdCodeSearch() {
        logger.debug("查询卖家区域code和name");
        RsResponse<ISL231199RsBean> rs = new RsResponse<ISL231199RsBean>();
        ISL231199RsBean isl231199RsBean = new ISL231199RsBean();
        List<ISL231199RsBean> isl231199Result = isl231199Logic.queryCodeName();
        if (null != isl231199Result && isl231199Result.size() > 0) {
            isl231199RsBean.setIsl231199RsBeanList(isl231199Result);
            rs.setResult(isl231199RsBean);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家区域code和name成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家区域code和name不存在");
        }
        return rs;
    }


    /**
     * 查询卖家name
     *
     * @return rs  返回卖家name
     */
    @RequestMapping(value = "/sl/slInfo/slCodeShowName/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231199RsBean> slPdCodeSearch1(@RequestBody RsRequest<ISL231199RsParam> param) {
        logger.debug("查询卖家的名称");
        RsResponse<ISL231199RsBean> rs = new RsResponse<ISL231199RsBean>();
        ISL231199RsBean isl231199RsBean = new ISL231199RsBean();
        List<ISL231199RsBean> isl231199Result = isl231199Logic.queryCodeShowName(param);
        if (null != isl231199Result && isl231199Result.size() > 0) {
            isl231199RsBean.setIsl231199RsBeanList(isl231199Result);
            rs.setResult(isl231199RsBean);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家的名称成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家区域code和name不存在");
        }
        return rs;
    }


    /**
     * 查询卖家产品分页信息
     *
     * @return rs  查询卖家产品分页信息
     */
    @RequestMapping(value = "/sl/slInfo/slSellerProduct/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231199RsPageBean> getLgcsSellerInfoSearch(@RequestBody RsRequest<ISL231199RsParam> param) {
        logger.debug("查询卖家产品分页信息");
        ISL231199RsPageBean result = new ISL231199RsPageBean();
        ISL231199RsParam rsParam = param.getParam();
        RsResponse<ISL231199RsPageBean> rs = new RsResponse<ISL231199RsPageBean>();
        List<ISL231199RsBean> results = isl231199Logic.findLgcsSlPageList(rsParam, result);
        result.setPageResult(results);
        if (CollectionUtils.isNotEmpty(results)) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家产品分页信息成功");
        } else {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家产品分页信息不存在");
        }
        return rs;
    }


}
