package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11309RsBean;
import com.msk.ssc.bean.SSC11309RsParam;
import com.msk.ssc.logic.SSC11309Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询生产商入库列表
 * Created by liu_yan2 on 2016/6/30.
 */
@RestController
public class ISSC11309RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11309RestController.class);


    @Autowired
    private SSC11309Logic ssc11309Logic;

    /**
     * 查询生产商入库列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findSscIntoBasic",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public RsResponse<PageResult<SSC11309RsBean>> findSscIntoBasic(@RequestBody RsRequest<SSC11309RsParam> param) {
        logger.info("查询生产商入库列表");
        RsResponse<PageResult<SSC11309RsBean>> response=new RsResponse<>();

        PageResult<SSC11309RsBean> res =this.ssc11309Logic.findPage(param.getParam(), SSC11309RsBean.class );
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



//    /**
//     * 批量保存生产商入库单列表以及生产商入库单详细信息
//     *
//     * @param param
//     * @return
//     */
//    @RequestMapping(value = "/ssc/batchSaveSscIntoBasic",
//            method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//
//    public RsResponse<Integer> batchSaveSscIntoBasic(@RequestBody RsRequest<SSC11309RsParam> param) {
//        logger.info("批量保存生产商入库单列表以及生产商入库单详细信息");
//        RsResponse<Integer> response=new RsResponse<>();
//        int result = ssc11309Logic.batchSaveSscIntoBasic(param.getParam());
//        response.setStatus(SystemConst.RsStatus.SUCCESS);
//        response.setMessage("处理成功");
//        response.setResult(result);
//        return response;
//    }
}


