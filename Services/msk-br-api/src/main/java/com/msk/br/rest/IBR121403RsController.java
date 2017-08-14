package com.msk.br.rest;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.br.bean.BR121403RsPageResult;
import com.msk.br.logic.BR121403Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrOBuyerInfo;
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
public class IBR121403RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121403RsController.class);

    @Autowired
    private BR121403Logic br121403Logic;

    /*
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/findBrOBuyerInfoList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121403RsPageResult> findBrOBuyerInfoList(@RequestBody RsRequest<BasePageParam> param) {
        logger.info("-----单一买家池列表查询开始-----");
        RsResponse<BR121403RsPageResult> response = new RsResponse<>();
        BR121403RsPageResult br121403RsPageResult = new BR121403RsPageResult();
        DbUtils.buildLikeCondition(param.getParam(), "buyerName", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param.getParam(), "buyerCode", DbUtils.LikeMode.PARTIAL);
        DbUtils.buildLikeCondition(param.getParam(), "marketName", DbUtils.LikeMode.PARTIAL);
        int count = this.br121403Logic.getPageCount(param.getParam());
        List<BrOBuyerInfo> res = br121403Logic.findPageList(param.getParam(), BrOBuyerInfo.class);
        br121403RsPageResult.setBrOBuyerInfoList(res);
        br121403RsPageResult.setTotalCount(count);
        response.setResult(br121403RsPageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }
}


