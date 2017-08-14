package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231185RsResult;
import com.msk.seller.logic.ISL231185RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhang_chi on 2016/4/28.
 */
@RestController
public class ISL231185RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231185RsController.class);

    @Autowired
    private ISL231185RsLogic isl231185Logic;

    /**
     * 查询卖家编码和销售区域接口
     *
     * @return rs
     */
    @RequestMapping(value = "/sl/slInfo/slCode/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISL231185RsResult>> querySLCode() {
        logger.debug("查询卖家编码和销售区域接口");
        RsResponse<List<ISL231185RsResult>> rs = new RsResponse<List<ISL231185RsResult>>();
        List<ISL231185RsResult> SLCodeList = isl231185Logic.querySLCode();
        if (null != SLCodeList && SLCodeList.size() > 0) {
            rs.setResult(SLCodeList);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家编码和销售区域成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家编码和销售区域不存在");
        }
        return rs;
    }
}
