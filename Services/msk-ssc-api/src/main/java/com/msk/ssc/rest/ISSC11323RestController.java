package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11323Bean;
import com.msk.ssc.bean.SSC11323Param;
import com.msk.ssc.logic.SSC11323Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2016/8/3.
 */
@RestController
public class ISSC11323RestController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(ISSC11323RestController.class);
    /**
     * logger
     */
    @Autowired
    private SSC11323Logic ssc11323Logic;

    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findSscinvoiceRequestList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<SSC11323Bean>> findSscBidBasicInfoList(@RequestBody RsRequest<SSC11323Param> param) {
        logger.info("-----发票申请一览-----");
        RsResponse<PageResult<SSC11323Bean>> response = new RsResponse<>();
//        PageResult<SSC11323Bean> result = new PageResult<>();
//        int count = this.ssc11323Logic.getPageCount(param.getParam());
//        List<SSC11323Bean> res = null;
     /*   if (count != NumberConst.IntDef.INT_ZERO) {*/
//        res = ssc11323Logic.findPageList(param.getParam(), SSC11323Bean.class);
//        result.setRecordsTotal(count);

//        result.setData(res);
//        response.setResult(result);
//        response.setStatus(SystemConst.RsStatus.SUCCESS);
//        response.setMessage("处理成功");
     /*   } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }*/

        PageResult<SSC11323Bean> res = ssc11323Logic.findPage(param.getParam(),SSC11323Bean.class);
        if(res!=null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }
    @RequestMapping(value = "/ssc/modifyInvoiceRequest",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RsResponse<SSC11323Bean> modifyInvoiceRequest(@RequestBody RsRequest<SSC11323Param> param){
        logger.info("删除预入库通知单");
        RsResponse<SSC11323Bean> resultRsResponse = new RsResponse<>();

        String flag = this.ssc11323Logic.deletePreInto(param.getParam());
        if(flag.equals(SystemConst.RsStatus.SUCCESS)){
            resultRsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            resultRsResponse.setMessage("删除成功");
        }else {
            resultRsResponse.setStatus(SystemConst.RsStatus.FAIL);
            resultRsResponse.setMessage("删除失败");
        }
        return resultRsResponse;
    }


}
