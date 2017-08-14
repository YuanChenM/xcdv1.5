package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.IBY121322RsParam;
import com.msk.buyers.logic.IBY121322Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/2.
 */
@RestController
public class IBY121322RsController extends BaseRsController{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121322RsController.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private IBY121322Logic iby121322Logic;

    @RequestMapping(value = "by/byBuyerPdCla/updateSalePeriod",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    void updateSalePeriod(@RequestBody RsRequest<List<IBY121322RsParam>> param){
        Date currentDate = DateTimeUtil.getCustomerDate();
        RsResponse<Integer> resultResponse = new RsResponse<>();
        int result = NumberConst.IntDef.INT_ZERO;
        int count ;
        List<IBY121322RsParam> saveList = new ArrayList<>();
        for(int i=0;i<param.getParam().size();i++){
             count = iby121322Logic.getCount(param.getParam().get(i));
            if(count == NumberConst.IntDef.INT_ZERO){
                IBY121322RsParam iby121322RsParam = new IBY121322RsParam();
                Long maxId = commonLogic.maxId("BR_BUYER_POOL_RELATIONSHIP", "ID");
                iby121322RsParam.setBuyerId(param.getParam().get(i).getBuyerId());
                iby121322RsParam.setMachiningCode(param.getParam().get(i).getMachiningCode());
                iby121322RsParam.setClassesCode(param.getParam().get(i).getClassesCode());
                iby121322RsParam.setClassesName(param.getParam().get(i).getClassesName());
                iby121322RsParam.setId(maxId);
                iby121322RsParam.setCrtTime(currentDate);
                iby121322RsParam.setCrtId("admin");
                iby121322RsParam.setUpdId("admin");
                iby121322RsParam.setActId("admin");
                iby121322RsParam.setUpdTime(currentDate);
                iby121322RsParam.setActTime(currentDate);
                iby121322RsParam.setUpdTime(currentDate);
                saveList.add(iby121322RsParam);
                int ins = iby121322Logic.save(iby121322RsParam);
            }
        }

        resultResponse.setResult(result);
        resultResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        resultResponse.setMessage("处理成功");

    }
}
