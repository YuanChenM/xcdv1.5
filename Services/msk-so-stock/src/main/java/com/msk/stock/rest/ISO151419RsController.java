package com.msk.stock.rest;

import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.stock.bean.ISO151419RsParam;
import com.msk.stock.bean.ISO151419RsResult;

import com.msk.stock.logic.ISO151419Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ISO151419RsController
 *
 * @author jiang_nan
 * @version 1.0
 **/
@RestController
public class ISO151419RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151419RsController.class);

    @Autowired
    private ISO151419Logic iSO151419Logic;

    /**
     * 库存统计查询
     * @param type 库存统计类型
     * @param param 查询参数
     * @return 库存情况
     */
    @RequestMapping(value = "/so/sds/{type}/list",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ISO151419RsResult> search(@PathVariable("type")String type, @RequestBody RsRequest<ISO151419RsParam> param){
        logger.debug("库存统计查询");
        RsResponse<ISO151419RsResult> response = new RsResponse<ISO151419RsResult>();
        response.setStatus("S");
        response.setMessage("库存统计查询");
        ISO151419RsResult result = this.iSO151419Logic.findAllList(param);
        response.setResult(result);
        return response;
    }
}
