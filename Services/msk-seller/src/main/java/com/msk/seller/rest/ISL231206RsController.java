package com.msk.seller.rest;


import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231206RsBean;
import com.msk.seller.bean.ISL231206RsParam;
import com.msk.seller.logic.ISL231206RsLogic;
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
 * Created by liu_tao2 on 2016/5/27.
 */
@RestController
public class ISL231206RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231206RsController.class);

    @Autowired
    private ISL231206RsLogic isl231206RsLogic;

    /**
     * 根据卖家产品查询产品生产商
     * @param param
     * @return
     */
    @RequestMapping(value = "/sl/slInfo/SlProp/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISL231206RsBean>> searchSlEp(@RequestBody RsRequest<ISL231206RsParam> param){
        RsResponse<List<ISL231206RsBean>> result = new RsResponse<List<ISL231206RsBean>>();

        ISL231206RsParam isl231206RsParam = param.getParam();

        List<ISL231206RsBean> isl231206RsBeans = isl231206RsLogic.getSlPropInfo(isl231206RsParam);
        if (!CollectionUtils.isEmpty(isl231206RsBeans)) {
            result.setResult(isl231206RsBeans);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("查询生产商信息成功");
        } else {
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("查询生产商信息不存在");
        }

        return result;
    }
}
