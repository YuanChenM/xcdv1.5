package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.logic.BY121402Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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
 * @author yuan_zhifei
 */
@RestController
public class IBY121402RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121402RsController.class);

    @Autowired
    private BY121402Logic by121402Logic;

    /**
     * 删除批发市场文件信息
     *
     * @param param param
     * @return 结果
     * @author yuan_zhifei
     */
    @RequestMapping(value = "/by/marketTerminalFileInfo/_update",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> updateMarketTerminalFileInfo(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<Integer> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        String message = "";
        param.getParam().setActId(param.getLoginId());
        param.getParam().setActTime(currentDate);
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setUpdTime(currentDate);
        param.getParam().setCrtId(param.getLoginId());
        param.getParam().setCrtTime(currentDate);
        int result = this.by121402Logic.modify(param.getParam());
        if (result > NumberConst.IntDef.INT_ZERO) {
            message = "删除成功！";
        } else {
            message = "删除失败！";
        }
        response.setResult(result);
        response.setMessage(message);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        return response;
    }
}
