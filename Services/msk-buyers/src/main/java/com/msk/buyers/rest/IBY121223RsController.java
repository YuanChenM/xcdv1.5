package com.msk.buyers.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.IBY121223Param;
import com.msk.buyers.bean.IBY121223Result;
import com.msk.buyers.logic.IBY121223Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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
 * Created by zhu_kai1 on 2016/6/20.
 */
@RestController
public class IBY121223RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBY121223RsController.class);
    @Autowired
    private IBY121223Logic iby121223Logic;

    /**
     * 查询公共买家池买家信息
     * @param request
     * @return
     */
    @RequestMapping(value = "by/buyerInfo/searchBuyer",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBY121223Result>> searSlInfoBuyer(@RequestBody RsRequest<IBY121223Param> request){
        logger.debug("开始调用公共买家池买家信息接口");
        RsResponse<List<IBY121223Result>> response = new RsResponse<List<IBY121223Result>>();
        request.getParam().setBuyerCode(DbUtils.buildLikeCondition(request.getParam().getBuyerCode(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setAccountName(DbUtils.buildLikeCondition(request.getParam().getAccountName(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBuyerAddr(DbUtils.buildLikeCondition(request.getParam().getBuyerAddr(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setTelNo(DbUtils.buildLikeCondition(request.getParam().getTelNo(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBuyerName(DbUtils.buildLikeCondition(request.getParam().getBuyerName(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setSuperiorName(DbUtils.buildLikeCondition(request.getParam().getSuperiorName(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBusiTel(DbUtils.buildLikeCondition(request.getParam().getBusiTel(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setEmployeeName(DbUtils.buildLikeCondition(request.getParam().getEmployeeName(), DbUtils.LikeMode.PARTIAL));

        List<IBY121223Result> buyerHandResultList =  iby121223Logic.getBuyerHand(request.getParam());
        if(!CollectionUtils.isEmpty(buyerHandResultList)){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(buyerHandResultList);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("没有查询到对应的数据");
        }
        logger.debug("调用公共买家池买家信息接口结束");
        return response;
    }
}
