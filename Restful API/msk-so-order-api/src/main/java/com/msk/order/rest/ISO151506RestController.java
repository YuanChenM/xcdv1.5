package com.msk.order.rest;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.entity.SoReturn;
import com.msk.order.service.ISO151506Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * ISO151506_退货单列表
 * Created by wang_shuai on 2016/8/11.
 */
@RestController
public class ISO151506RestController extends BaseRestController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151506RestController.class);

    @Autowired
    private ISO151506Service iso151506Service;


    @RequestMapping(value = "/order/findReturnList",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RestResponse<PageResult<SoReturn>> findReturnList(@RequestBody RestRequest<BasePageParam> param){
        logger.info("查询退货单列表信息");
        RestResponse<PageResult<SoReturn>> response = new RestResponse<>();
        PageResult<SoReturn> returnOrders= this.iso151506Service.findPageList(param.getParam());
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(returnOrders);
        return response;
    }
}
