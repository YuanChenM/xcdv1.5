package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231194RsResult;
import com.msk.seller.bean.ISL231198RsPageResult;
import com.msk.seller.bean.ISL231198RsParam;
import com.msk.seller.bean.ISL231198RsResult;
import com.msk.seller.logic.ISL231194RsLogic;
import com.msk.seller.logic.ISL231198RsLogic;
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
 * 物流区供应商列表查询接口
 *
 * Created by yangchunyan on 2016/6/8.
 */
@RestController
public class ISL231198RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231198RsController.class);
    @Autowired
    private ISL231198RsLogic isl231198Logic;

    /**
     * 查询物流区供应商信息
     * @return rs  物流区供应商信息
     */
    @RequestMapping(value = "/sl/slInfo/lgcsSlInfo/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231198RsPageResult> getLgcsSellerInfoSearch(@RequestBody RsRequest<ISL231198RsParam> param) {
        logger.debug("查询物流区供应商信息");
        ISL231198RsPageResult result = new ISL231198RsPageResult();
        ISL231198RsParam rsParam = param.getParam();
        RsResponse<ISL231198RsPageResult> rs = new RsResponse<ISL231198RsPageResult>();
        List<ISL231198RsResult> results =  isl231198Logic.findLgcsSlPageList(rsParam,result);
        result.setPageResult(results);
        if (CollectionUtils.isNotEmpty(results)) {
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询物流区供应商信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询物流区供应商信息不存在");
        }
        return rs;
    }
}
