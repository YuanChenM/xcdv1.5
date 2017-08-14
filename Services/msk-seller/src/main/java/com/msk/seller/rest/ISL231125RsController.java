package com.msk.seller.rest;


import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlEnterprise;
import com.msk.seller.bean.ISL231125RsParam;
import com.msk.seller.bean.ISL231125RsResult;
import com.msk.seller.logic.ISL231125RsLogic;
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
public class ISL231125RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231125RsController.class);
    @Autowired
    private ISL231125RsLogic iSL231125RsLogic;

    /**
     * 查询企业基本资质接口
     *
     * @param param param
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slEnterprise/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231125RsResult> querySlEnterpriseInfo(@RequestBody RsRequest<ISL231125RsParam> param) {
        logger.debug("查询企业基本资质接口");
        RsResponse<ISL231125RsResult> rs = new RsResponse<ISL231125RsResult>();
        ISL231125RsResult result = new ISL231125RsResult();
        List<SlEnterprise> slEnterpriseList = iSL231125RsLogic.findAllList(param);
        if (null != slEnterpriseList && slEnterpriseList.size() > 0) {
            result.setEpInfoList(slEnterpriseList);
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询企业基本资质成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("企业基本资质不存在");
        }
        return rs;
    }
}
