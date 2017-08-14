package com.msk.br.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121311RsParam;
import com.msk.br.bean.IBR121311RsResult;
import com.msk.br.logic.IBR121311Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrHkGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/9.
 */
@RestController
public class IBR121311RsController extends BaseRsController {

    @Autowired
    private IBR121311Logic ibr121311Logic;

    @RequestMapping(value = "/br/houseKeeperOfHkGroup/query",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RsResponse<IBR121311RsResult> queryHouseKeeperOfHkGroup(@RequestBody RsRequest<IBR121311RsParam> paramRsRequest){
        Date currentDate =  DateTimeUtil.getCustomerDate();
        RsResponse<IBR121311RsResult> rs = new RsResponse<>();
        IBR121311RsResult ibr121311RsResult = new IBR121311RsResult();
        ibr121311RsResult.setCreateTime(currentDate);
        List<BrHkGroup> brHkGroupList= new ArrayList<>();
        brHkGroupList = ibr121311Logic.findList(paramRsRequest.getParam());
        ibr121311RsResult.setHkGroupList(brHkGroupList);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("处理成功");
        rs.setResult(ibr121311RsResult);
        return  rs;

    }
}
