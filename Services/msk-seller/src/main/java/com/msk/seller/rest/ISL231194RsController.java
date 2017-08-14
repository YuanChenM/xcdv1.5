package com.msk.seller.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.seller.bean.ISL231194RsResult;
import com.msk.seller.logic.ISL231194RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangchi on 2016/5/9.
 */
@RestController
public class ISL231194RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231194RsController.class);
    @Autowired
    private ISL231194RsLogic isl231194Logic;

    /**
     * 查询卖家银行卡信息
     * @return rs  查询全部卖家基本信息和卖家名下所有银行卡信息
     */
    @RequestMapping(value = "/sl/slInfo/slBaseInfo/search",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ISL231194RsResult>> slPdCodeSearch() {
        logger.debug("查询卖家银行卡信息");
        RsResponse<List<ISL231194RsResult>> rs = new RsResponse<List<ISL231194RsResult>>();
        List<ISL231194RsResult>   isl231194Result =  isl231194Logic.querySlBank();
        if (null != isl231194Result && isl231194Result.size() > 0) {
            rs.setResult(isl231194Result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询卖家银行卡信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询卖家银行卡信息不存在");
        }
        return rs;
    }


}
