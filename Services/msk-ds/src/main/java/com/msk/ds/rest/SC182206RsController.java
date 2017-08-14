package com.msk.ds.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ds.bean.SC182206Bean;
import com.msk.ds.bean.SC182206Param;
import com.msk.ds.logic.SC182206Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * ISC182206RsController.
 * 卖家供应链信息查询
 *
 * @author geng_xingdong
 */
@Api(description = "卖家供应链信息查询接口",tags = {"SC182206RsController"})
@RestController
public class SC182206RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SC182206RsController.class);

    /**
     * SC182206Logic
     */
    @Autowired
    private SC182206Logic  sc182206Logic;



    /**
     * 查询

     * @return rs  返回卖家的供应链信息
     */
    @ApiOperation(value = "查询卖家的供应链信息",notes = "查询卖家的供应链信息")
    @RequestMapping(value = "/sc/queryDsInformation/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SC182206Bean> queryDInformation(@RequestBody RsRequest<SC182206Param> param) {
        logger.debug("查询卖家供应链信息");
        RsResponse<SC182206Bean> rs = new RsResponse<SC182206Bean>();
        List<SC182206Bean> sc182206Beans = sc182206Logic.queryDsInformation(param);
        if (!CollectionUtils.isEmpty(sc182206Beans)) {
            SC182206Bean sc182206Bean = new SC182206Bean();
            sc182206Bean.setSc182206BeanList(sc182206Beans);
            rs.setResult(sc182206Bean);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家供应链信息成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家供应链信息不成功");
        }
        return rs;
    }
}


