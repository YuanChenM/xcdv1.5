package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141114RsResult;
import com.msk.product.logic.IPD141114Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 查询物流区信息
 * Created by Administrator on 2016/1/26.
 */
@RestController
public class IPD141114RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141114RsController.class);
    @Autowired
    private IPD141114Logic ipd141114Logic;

    /**
     * 物流区信息查询所有数据
     *
     * @return RsResponse<IPD141114RsResult> 返回json数据给前端
     */
    @RequestMapping(value = "/pd/logiArea",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141114RsResult> findLogiAreaList() {
        RsResponse<IPD141114RsResult> rs = new RsResponse<IPD141114RsResult>();
        IPD141114RsResult ipd141114RsResult = this.ipd141114Logic.findAllRecordList();
        if(ipd141114RsResult!=null) {
            rs.setResult(ipd141114RsResult);
            rs.setMessage("处理成功");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        logger.info("没有查询到数据！");
        rs.setMessage("没有查询到数据！");
        return rs;
    }

}
