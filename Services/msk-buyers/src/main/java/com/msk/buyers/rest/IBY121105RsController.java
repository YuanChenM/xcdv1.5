package com.msk.buyers.rest;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.buyers.bean.IBY121105Bean;
import com.msk.buyers.bean.IBY121105Param;
import com.msk.buyers.bean.IBY121105RsResult;
import com.msk.buyers.logic.IBY121105Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guan_zhongheng on 2016/7/29.
 */
@RestController
public class IBY121105RsController extends BaseRsController {

    @Autowired
    private IBY121105Logic iby121105Logic;

    /**
     * 根据时间和来源查询买家用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/buyerAccount/search",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.buyers.validator.IBY121105Validator")
    public RsResponse<IBY121105RsResult> queryBuyerAccounts(@RequestBody RsRequest<IBY121105Param> request){
        RsResponse<IBY121105RsResult> rs = new RsResponse<>();
        if(StringUtil.isNullOrEmpty(request.getParam().getRegisterSource()) || StringUtil.isNullOrEmpty(request.getParam().getCrtTimeInfo())){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("参数不正确！");
        }else{
            List<IBY121105Bean> list = iby121105Logic.findPageList(request.getParam(), IBY121105Bean.class);
            int totalCount = iby121105Logic.getPageCount(request.getParam());
            if (list != null && list.size() > NumberConst.IntDef.INT_ZERO) {
                IBY121105RsResult rsPageResult = new IBY121105RsResult();
                rsPageResult.setByAccountInfoList(list);
                rsPageResult.setTotalCount(totalCount);
                rsPageResult.setTotalPage(totalCount,request.getParam().getPageCount());
                rsPageResult.setPageNo(request.getParam().getPageNo());
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
                rs.setMessage("用户信息查询正常");
                rs.setResult(rsPageResult);
            }else{
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("没有查询到数据！");
            }
        }
        return rs;
    }
}
