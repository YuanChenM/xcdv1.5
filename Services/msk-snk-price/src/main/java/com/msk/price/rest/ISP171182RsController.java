package com.msk.price.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.PricePlateInfoParam;
import com.msk.price.bean.PricePlateInfoResult;
import com.msk.price.logic.ISP171182Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * 查询价盘箱价接口Controller
 *
 * @author peng_hao
 * @version 1.0
 */
@RestController
public class ISP171182RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171182RsController.class);

    @Autowired
    private ISP171182Logic iSP171182Logic;

    /**
     * 查询价盘箱价接口
     *
     * @param param
     * @return result
     */
    @RequestMapping(value = "/sp/getPriceBox",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.price.validator.ISP171182Validator")
    public RsResponse<PricePlateInfoResult> search(@RequestBody RsRequest<PricePlateInfoParam> param){
        logger.debug("价盘箱价查询");
        RsResponse<PricePlateInfoResult> response = new RsResponse<PricePlateInfoResult>();
        PricePlateInfoResult result=new PricePlateInfoResult();
         //判断参数是否为空
            result = this.iSP171182Logic.findAllList(param);
            if(!CollectionUtils.isEmpty(result.getSearchList())){
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setMessage("价盘箱价查询成功");
            }else{
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("未查询到数据");
                 }

        response.setResult(result);
        return response;
    }
}
