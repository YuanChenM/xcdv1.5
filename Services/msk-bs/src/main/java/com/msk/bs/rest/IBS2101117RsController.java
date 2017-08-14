package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bs.bean.IBS2101117Param;
import com.msk.bs.logic.IBS2101117RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 根据订单时间更新买家和管家关系有效期时间
 * Created by ni_shaotang on 2016/8/10.
 */
@RestController
@Api(description = "更新买家和冻品管家关系有效期RestController", tags = {"IBS2101117RsController"})
public class IBS2101117RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101114RsController.class);

    @Autowired
    private IBS2101117RsLogic ibs2101117RsLogic;

    private static Integer days = 60;//有效期时间默认为60天

    @ApiOperation(value = "更新有效期 ", notes = "买家提交订单后，刷新买家和冻品管家关系有效期时间")
    @RequestMapping(value = "/bs/updateBuyerValidDate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<T> updateBuyerValidDate(@RequestBody RsRequest<IBS2101117Param> request){
        if(null != request.getParam()) {
            IBS2101117Param param = request.getParam();
            //有效时间为空添加默认时间
            if (null == param.getDays()) {
                param.setDays(days);
            }
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
            param.setUpdId(request.getLoginId());
            param.setUpdTime(DateTimeUtil.getCustomerDate());
            /**Add: 横展开添加共通设置 2016/09/23   BY  任强  End */
            return ibs2101117RsLogic.updateBuyer(param);
        }else {
            RsResponse<T> response = new RsResponse<T>();
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("参数不全。请检查参数");
            return response;
        }
    }
}
