package com.msk.seller.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlSeller;
import com.msk.seller.bean.ISL231204RsResult;
import com.msk.seller.logic.ISL231204RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhou_yajun on 2016/5/27.
 */
@RestController
public class ISL231204RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISL231204RsController.class);
    @Autowired
    private ISL231204RsLogic isl231204RsLogic;

    /**
     * 获取生产商和品牌ID
     *
     * @return rs  新增卖家产品状态履历
     */
    @RequestMapping(value = "/sl/slInfo/getManufactureAndBrand",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231204RsResult> findManuAndBrand(@RequestBody RsRequest<SlSeller> param){

        SlSeller slSeller = param.getParam();

        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode",slSeller.getSlCode());
        List<ISL231204RsResult> sellerResult = isl231204RsLogic.findList(baseParam);
        ISL231204RsResult isl231204RsResult = new ISL231204RsResult();
        isl231204RsResult.setIsl231201List(sellerResult);
        RsResponse<ISL231204RsResult> rs = new RsResponse<>();
        rs.setMessage("查询成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(isl231204RsResult);
        return rs;
    }
}
