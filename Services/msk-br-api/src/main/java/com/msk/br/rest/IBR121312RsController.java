package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121312RsParam;
import com.msk.br.logic.IBR121312Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrBuyerMarketingStatusHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by tao_zhifa on 2016/9/21.
 */
@RestController
public class IBR121312RsController extends BaseRsController{

    @Autowired
    private IBR121312Logic ibr121312Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 修改或更新履历表
     * @param ibr121312RsParam
     * @return
     */
    @RequestMapping(value = "/br/buyerMarketingStatusHistory/update",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<Integer> updateBuyerMarketingStatusHistory(@RequestBody RsRequest<IBR121312RsParam> ibr121312RsParam) {
        Date currentDate = DateTimeUtil.getCustomerDate();
        RsResponse<Integer> rs = new RsResponse<>();
        int result = 0;
        int count = 0;
        if (ibr121312RsParam != null) {
            IBR121312RsParam param = ibr121312RsParam.getParam();
            param.setCrtId(ibr121312RsParam.getLoginId());
            param.setUpdId(ibr121312RsParam.getLoginId());
            param.setActId(ibr121312RsParam.getLoginId());
            param.setUpdTime(currentDate);
            param.setActTime(currentDate);
            param.setCrtTime(currentDate);
            param.setModifyTime(currentDate);

            count = ibr121312Logic.getCount(param);

            if (count == NumberConst.IntDef.INT_ZERO) {
                Long maxId = commonLogic.maxId("br_buyer_marketing_status_history", "HISTORY_ID");
                param.setCurrentStatusFlg("1");
                param.setHistoryId(maxId);
                result = ibr121312Logic.saveBrBuyerMarketingStatusHistory(param);
            } else {
                int check = ibr121312Logic.checkCount(param);
                if (check == NumberConst.IntDef.INT_ZERO) {
                    BrBuyerMarketingStatusHistory brBuyerMarketingStatusHistory = ibr121312Logic.findBrBuyerMarketingStatusHistory(param);
                    param.setOldStatusClass(brBuyerMarketingStatusHistory.getNewStatusClass());
                    param.setOldStatusClassName(brBuyerMarketingStatusHistory.getNewStatusClassName());
                    param.setOldStatusBreed(brBuyerMarketingStatusHistory.getNewStatusBreed());
                    param.setOldStatusBreedName(brBuyerMarketingStatusHistory.getNewStatusBreedName());
                    if (brBuyerMarketingStatusHistory.getNewExceptionStatus() != null) {
                        param.setOldExceptionStatus(brBuyerMarketingStatusHistory.getNewExceptionStatus());
                    }
                    if (brBuyerMarketingStatusHistory.getNewExceptionStatusName() != null) {
                        param.setOldExceptionStatusName(brBuyerMarketingStatusHistory.getNewExceptionStatusName());
                    }
                    param.setCurrentStatusFlg("0");
                    result = ibr121312Logic.updateBrBuyerMarketingStatusHistory(param);
                    if (result != NumberConst.IntDef.INT_ZERO) {
                        param.setCurrentStatusFlg("1");
                        Long maxId = commonLogic.maxId("br_buyer_marketing_status_history", "HISTORY_ID");
                        param.setHistoryId(maxId);
                        result = ibr121312Logic.saveBrBuyerMarketingStatusHistory(param);
                    }
                } else {
                    result = 1;
                }
            }
        }
        if (result != NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("履历表保存成功");
            rs.setResult(NumberConst.IntDef.INT_ONE);
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("履历表保存失败");
            rs.setResult(NumberConst.IntDef.INT_ZERO);
        }
        return rs;

    }
}
