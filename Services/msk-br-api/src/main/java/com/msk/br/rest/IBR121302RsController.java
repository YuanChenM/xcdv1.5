package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.logic.IBR121302Logic;
import com.msk.buyers.bean.BY121309Bean;
import com.msk.buyers.bean.BY121309Param;
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


/**
 * 买家买家池已购产品目录 接口
 * Created by tao_zhifa on 2016/7/5.
 */
@RestController
public class IBR121302RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBR121302RsController.class);

    @Autowired
    private IBR121302Logic ibr121302Logic;

    @RequestMapping(value = "/br/findOrderInfoProductCatalog", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<BY121309Bean>> findOrderInfoProductCatalog(@RequestBody RsRequest<BY121309Param> param) {

        logger.info("买家买家池已购产品目录");
        RsResponse<PageResult<BY121309Bean>> response = new RsResponse<PageResult<BY121309Bean>>();
        PageResult<BY121309Bean> res = this.ibr121302Logic.findPage(param.getParam(), BY121309Bean.class);
        if (res != null) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(res);
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }
        return response;
    }
}
