package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.SlProductBean;
import com.msk.seller.logic.ISL231197RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangchi on 2016/5/25.
 */
@RestController
public class ISL231197RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231197RsController.class);
    @Autowired
    private ISL231197RsLogic isl231197Logic;

    /**
     * 新增卖家产品状态履历
     *
     * @return rs  新增卖家产品状态履历
     */
    @RequestMapping(value = "/sl/slInfo/slPdStatusHis/new",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SlProductBean> createSlPdStatusHis(@RequestBody RsRequest<SlProductBean> param){
        logger.debug("新增卖家产品状态履历接口");
        RsResponse<SlProductBean> rs=new RsResponse<SlProductBean>();
        SlProductBean slProductBean = param.getParam();
        isl231197Logic.saveSlPdStatusHis(slProductBean);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("新增卖家产品状态履历成功");
        return rs;
    }


}
