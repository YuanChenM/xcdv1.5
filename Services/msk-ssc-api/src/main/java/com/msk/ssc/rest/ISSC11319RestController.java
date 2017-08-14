package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11319RsBean;
import com.msk.ssc.bean.SSC11319RsParam;
import com.msk.ssc.logic.SSC11319Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yang_yang
 */
@RestController
public class ISSC11319RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11319RestController.class);

    @Autowired
    private SSC11319Logic ssc11319Logic;

    /**
     * 查询资金池列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/searchPayment",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<SSC11319RsBean>> searchPayment(@RequestBody RsRequest<SSC11319RsParam> param) {
        logger.info("查询资金池列表");
        RsResponse<PageResult<SSC11319RsBean>> response = new RsResponse<>();

        PageResult<SSC11319RsBean> res = this.ssc11319Logic.findSSC11319List(param.getParam());
        if(res != null){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(res);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }

}


