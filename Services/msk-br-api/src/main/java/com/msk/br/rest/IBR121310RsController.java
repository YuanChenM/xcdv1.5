package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121310Bean;
import com.msk.br.bean.IBR121310RsParam;
import com.msk.br.logic.IBR121310Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

/**
 * Created by tao_zhifa on 2016/8/26.
 */
@RestController
public class IBR121310RsController extends BaseRsController {

    @Autowired
    private IBR121310Logic ibr121310Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 更新买家买家池关系表
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/brBuyerPoolRelationship/updateMarketingPeriod", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<Integer> UpdateMarketingPeriod(@RequestBody RsRequest<IBR121310RsParam> param) {
        RsResponse<Integer> rs = new RsResponse<>();
        int result = 0;
        int count = 0;

        Date currentDate = DateTimeUtil.getCustomerDate();
        if(("01").equals(param.getParam().getSuperiorType())) {
            if(param.getParam().getClassCode().equals("01") || param.getParam().getClassCode().equals("02")){
                if (param.getParam().getMachiningCodeU().equals("2") || param.getParam().getMachiningCodeU().equals("3")) {
                    param.getParam().setMachiningCodeU("23");
                }
            }
            IBR121310Bean buyerPoolId = ibr121310Logic.findBuyerPoolId(param.getParam());
            if(buyerPoolId == null){
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家买家池关系表保存失败,买家池没有该条件信息");
                rs.setResult(NumberConst.IntDef.INT_ZERO);
                return rs;
            }
            String poolBuyerCode = param.getParam().getBuyerCode().substring(0, 15) + "-" + param.getParam().getClassCode();
            if ((param.getParam().getMachiningCodeU().equals("1") && param.getParam().getClassCode().equals("01"))
                    || (param.getParam().getMachiningCodeU().equals("1") && param.getParam().getClassCode().equals("02"))) {
                poolBuyerCode = poolBuyerCode + "1";
            } else if((param.getParam().getMachiningCodeU().equals("23") && param.getParam().getClassCode().equals("01"))
                    || (param.getParam().getMachiningCodeU().equals("23") && param.getParam().getClassCode().equals("02"))
                    || (param.getParam().getMachiningCodeU().equals("23") && param.getParam().getClassCode().equals("01"))
                    || (param.getParam().getMachiningCodeU().equals("23") && param.getParam().getClassCode().equals("02"))){
                poolBuyerCode = poolBuyerCode + "2";
            }else {
                poolBuyerCode = poolBuyerCode + param.getParam().getMachiningCodeU();
            }
            int buyerCodeLength = 0;
            buyerCodeLength   = param.getParam().getBuyerCode().length();
            if(buyerCodeLength != NumberConst.IntDef.INT_ZERO){
                poolBuyerCode = poolBuyerCode + param.getParam().getBuyerCode().substring(buyerCodeLength-3, buyerCodeLength);
            }else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家买家池关系表保存失败");
                rs.setResult(NumberConst.IntDef.INT_ZERO);
                return rs;
            }
            Long maxId = commonLogic.maxId("BR_BUYER_POOL_RELATIONSHIP", "ID");
            IBR121310RsParam baseParam = new IBR121310RsParam();
            baseParam.setId(maxId);
            baseParam.setBuyerId(param.getParam().getBuyerId());
            baseParam.setBuyerPoolId(buyerPoolId.getBuyerPoolId());
            baseParam.setPoolBuyerCode(poolBuyerCode);
            baseParam.setJoinTime(currentDate);
            baseParam.setLeaveTime(null);
            baseParam.setCrtTime(currentDate);
            baseParam.setCrtId(param.getLoginId());
            baseParam.setUpdId(param.getLoginId());
            baseParam.setActId(param.getLoginId());
            baseParam.setUpdTime(currentDate);
            baseParam.setActTime(currentDate);
            baseParam.setUpdTime(currentDate);
            baseParam.setLeaveTime(currentDate);
            ibr121310Logic.modifyDelFlgFlase(baseParam);
            count = ibr121310Logic.findExistenceCount(baseParam);
            if (count == NumberConst.IntDef.INT_ZERO) {
                result = ibr121310Logic.save(baseParam);
            }
            if (count == NumberConst.IntDef.INT_ONE) {
                result = ibr121310Logic.modifyDelFlgTrue(baseParam);
            }
            if (count > NumberConst.IntDef.INT_ONE) {
                ibr121310Logic.modifyDelFlgFlase(baseParam);
                result = ibr121310Logic.save(baseParam);
            }
            if(result != NumberConst.IntDef.INT_ZERO) {
                rs.setMessage("保存成功");
                rs.setResult(NumberConst.IntDef.INT_ONE);
                rs.setStatus(SystemConst.RsStatus.SUCCESS);
            }else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("买家买家池关系表保存失败");
                rs.setResult(NumberConst.IntDef.INT_ZERO);
            }
        }else {
            rs.setMessage("不是分销买家");
            rs.setResult( NumberConst.IntDef.INT_ZERO);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
        }

        return rs;
    }


}