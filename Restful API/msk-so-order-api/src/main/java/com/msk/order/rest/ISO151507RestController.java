package com.msk.order.rest;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.param.BasePageParam;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.result.ISO151507BaseReturnResult;
import com.msk.order.bean.result.SO15150701BeanResult;
import com.msk.order.bean.result.SO15150701RestResultList;
import com.msk.order.service.ISO151507Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * ISO151506_退货单详细
 * Created by wang_shuai on 2016/8/11.
 */
@RestController
public class ISO151507RestController extends BaseRestController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ISO151507RestController.class);
    @Autowired
    private ISO151507Service iso151507Service;

    @RequestMapping(value = "/order/findReturnDetail",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RestResponse<SO15150701RestResultList> findReturnList(@RequestBody RestRequest<BasePageParam> param){
        logger.info("查询退货单详细列表信息");
        RestResponse<SO15150701RestResultList> resultRsResponse = new RestResponse<SO15150701RestResultList>();
        List<SO15150701BeanResult> returnList = null;
        try {
            returnList = this.iso151507Service.findReturnDetailList(param.getParam());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SO15150701RestResultList so15150701RestResultList = new SO15150701RestResultList();
        so15150701RestResultList.setSo15150701BeanResultList(returnList);
        if(returnList.size() != NumberConstant.IntDef.INT_ZERO){
            resultRsResponse.setResult(so15150701RestResultList);
            resultRsResponse.setStatus(SystemConstant.RsStatus.SUCCESS);
            resultRsResponse.setMessage("查询成功");
        }else {
            resultRsResponse.setStatus(SystemConstant.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;
    }

    @RequestMapping(value = "/order/findBaseReturn",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RestResponse<ISO151507BaseReturnResult> findBaseReturn(@RequestBody RestRequest<BasePageParam> param){
        logger.info("查询退货单基本信息");
        RestResponse<ISO151507BaseReturnResult> resultRsResponse = new RestResponse<ISO151507BaseReturnResult>();
        ISO151507BaseReturnResult ISO151507BaseReturnResult = new ISO151507BaseReturnResult();
        try {
            ISO151507BaseReturnResult = this.iso151507Service.findBaseReturnInfo(param.getParam());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ISO151507BaseReturnResult != null){
            resultRsResponse.setResult(ISO151507BaseReturnResult);
            resultRsResponse.setStatus(SystemConstant.RsStatus.SUCCESS);
            resultRsResponse.setMessage("查询成功");
        }else {
            resultRsResponse.setStatus(SystemConstant.RsStatus.FAIL);
            resultRsResponse.setMessage("数据不存在");
        }
        return resultRsResponse;
    }
}
