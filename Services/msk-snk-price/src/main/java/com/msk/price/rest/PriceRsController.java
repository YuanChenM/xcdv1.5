package com.msk.price.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.PriceCycleParam;
import com.msk.price.bean.PriceCycleResult;
import com.msk.price.utils.PriceCycleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by sun_jiaju on 2016/5/12.
 */
@RestController
public class PriceRsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PriceRsController.class);

    @RequestMapping(value = "/priceCycle/{interface}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PriceCycleResult> getPriceCycle (@RequestBody RsRequest<PriceCycleParam> request, @PathVariable("interface") String type)
    {
        PriceCycleParam param = request.getParam();
        PriceCycleResult result = new PriceCycleResult();
        switch (type){
            case "now":
                // 查询当前价盘周期
                result = PriceCycleUtil.getPriceCycle(param);
                return getSuccessRsResponse(result);
            case "next":
                // 查询下一个价盘周期
                result = PriceCycleUtil.getNextPriceCycle(param);
                return getSuccessRsResponse(result);
            default:
                throw new BusinessException("接口参数不正确");
        }
    }

    private RsResponse<PriceCycleResult> getSuccessRsResponse(PriceCycleResult result) {
        RsResponse<PriceCycleResult> response = new RsResponse<PriceCycleResult>();
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }
}
