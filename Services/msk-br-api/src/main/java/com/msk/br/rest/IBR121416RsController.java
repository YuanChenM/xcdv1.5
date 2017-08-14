package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121415Result;
import com.msk.br.bean.IBR121415RsParam;
import com.msk.br.logic.IBR121415Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/28.
 */

@RestController
public class IBR121416RsController extends BaseRsController {

    @Autowired
    private IBR121415Logic ibr121415Logic;

    @Autowired
    private CommonLogic commonLogic;

    @RequestMapping(value = "/br/unBindingRelationship/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<IBR121415Result> updateUnBindingRelationship(@RequestBody RsRequest<IBR121415RsParam> param) {

        RsResponse<IBR121415Result> rs = new RsResponse<>();
        int buyerBoolrelationshipCount = 0;
        int buyerHkRelationshipCount = 0;
        List<IBR121415RsParam> list = new ArrayList<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.getParam().setActTime(currentDate);
        param.getParam().setCrtTime(currentDate);
        param.getParam().setUpdTime(currentDate);
        param.getParam().setActId(param.getLoginId());
        param.getParam().setCrtId(param.getLoginId());
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setEndTime(currentDate);


        if (param.getParam().getHouseCode() == null || param.getParam().getHouseCode() == "") {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("冻品管家编码不能为空");
            return rs;
        }

        for (int i = 0; i < param.getParam().getRelationList().size(); i++) {
            IBR121415RsParam rsParam = new IBR121415RsParam();
            if (param.getParam().getRelationList().get(i).getBuyerId() == null || param.getParam().getRelationList().get(i).getBuyerId() == "") {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家ID不能为空");
                return rs;
            }
            if (param.getParam().getRelationList().get(i).getRelationType() == null || param.getParam().getRelationList().get(i).getRelationType() == "") {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家管家关系不能为空");
                return rs;
            }
            if(param.getParam().getRelationList().get(i).getRelationType().equals("0")){
                param.getParam().setBuyerId(param.getParam().getRelationList().get(i).getBuyerId());
                param.getParam().setRelationType(param.getParam().getRelationList().get(i).getRelationType());
                buyerBoolrelationshipCount = ibr121415Logic.findBrBuyerBoolrelationship(param.getParam());
                if(buyerBoolrelationshipCount != NumberConst.IntDef.INT_ZERO){
                    ibr121415Logic.updateBrBuyerPoolRelationship(param.getParam());
                }
                buyerHkRelationshipCount = ibr121415Logic.updateBrBuyerHkRelationship(param.getParam());
                if (buyerHkRelationshipCount == 0 && buyerBoolrelationshipCount != 0) {
                    rs.setStatus(SystemConst.RsStatus.FAIL);
                    rs.setMessage("买家管家关系表更新失败");
                    rsParam.setBuyerId(param.getParam().getBuyerId());
                    list.add(rsParam);
                }
            }else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家管家关系传入数据错误");
                rsParam.setBuyerId(param.getParam().getRelationList().get(i).getBuyerId());
                list.add(rsParam);
            }


        }
        if (list.size() == NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("更新成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("更新失败");
            IBR121415Result ibr121415Result = new IBR121415Result();
            ibr121415Result.setBuyerIdList(list);
            ibr121415Result.setTotalCount(list.size());
            rs.setResult(ibr121415Result);
        }
        return rs;
    }
}