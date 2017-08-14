package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.br.bean.BY121308RsPageResult;
import com.msk.br.bean.IBY121308RsBean;
import com.msk.br.bean.IBY121308RsParam;
import com.msk.br.logic.IBR121301Logic;
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

import java.util.List;

/**
 * 产品共通接口
 * Created by yuan_zhifei on 2016/6/29.
 */
@RestController
public class IBR121301RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121301RsController.class);
    @Autowired
    private IBR121301Logic ibr121301Logic;

    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/findOrderInfoList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BY121308RsPageResult> findOrderInfoList(@RequestBody RsRequest<IBY121308RsParam> param) {
        logger.info("-----订单汇总信息-----");
        RsResponse<BY121308RsPageResult> response = new RsResponse<>();
        BY121308RsPageResult by121308RsPageResult=new BY121308RsPageResult();
        int count=this.ibr121301Logic.getPageCount(param.getParam());
        List<IBY121308RsBean> res = ibr121301Logic.findPageList(param.getParam(), IBY121308RsBean.class);
        by121308RsPageResult.setIby121308RsBeanList(res);
        by121308RsPageResult.setTotalCount(count);
        response.setResult(by121308RsPageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }


}


