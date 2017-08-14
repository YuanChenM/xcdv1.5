package com.msk.buyers.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.IBY121202RsBean;
import com.msk.buyers.bean.IBY121202RsParam;
import com.msk.buyers.logic.IBY121202Logic;
import com.msk.buyers.logic.IBY121203Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOBuyerPdCla;
import com.msk.core.entity.ByBuyerPdCla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * IBY121203RsController.
 *
 * @author zhou_yajun
 */
@RestController
public class IBY121203RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121203RsController.class);

    @Autowired
    private IBY121203Logic iby121203Logic;

    @Autowired
    private IBY121202Logic iby121202Logic;

    /**
     * 买家经营产品类别更新接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/pdClassification/update",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Object> buyerPdClassificationUpdate(@RequestBody RsRequest<List<ByBuyerPdCla>> param){
        RsResponse<Object> rs = new RsResponse<>();
        if(!CollectionUtils.isEmpty(param.getParam())){
            for (int i = NumberConst.IntDef.INT_ZERO;i<param.getParam().size();i++){
                super.setCommonParam(param.getParam().get(i));
                Date currentDate = DateTimeUtil.getCustomerDate();
                param.getParam().get(i).setUpdTime(currentDate);
                param.getParam().get(i).setCrtTime(currentDate);
                param.getParam().get(i).setActTime(currentDate);

                param.getParam().get(i).setCrtId(param.getLoginId());
                param.getParam().get(i).setUpdId(param.getLoginId());
                param.getParam().get(i).setActId(param.getLoginId());

            }

       Integer returnNumber = iby121203Logic.buyerPdClassificationModify(param.getParam());

        if(returnNumber>0){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家经营产品类别更新成功");
            IBY121202RsBean iby121202RsBean = new IBY121202RsBean();
            List<BrOBuyerPdCla> brOBuyerPdClaList = new ArrayList<>();
            IBY121202RsParam iby121202RsParam = new IBY121202RsParam();
            iby121202RsParam.setBuyerId(param.getParam().get(0).getBuyerId());
            brOBuyerPdClaList = iby121202Logic.findBuyerPdCla(iby121202RsParam);
            iby121202RsBean.setBrOBuyerPdClaList(brOBuyerPdClaList);
            RsRequest<IBY121202RsBean> request = new RsRequest<>();
            request.setSiteCode("1");
            request.setAuth("MSK00001");
            request.setLoginId("msk01");
            request.setParam(iby121202RsBean);
//            String url = "http://localhost:8180/api/br/buyerReportInfo/_update";
            String url = SystemServerManager.BuyersReportServerManager.getUpdateBuyerReportInfo();
            RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
            });
        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家经营产品类别更新失败");
        }
       }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("参数为空，买家经营产品类别更新失败");
        }
        return rs;
    }
    /**
     * 买家经营产品类别查询接口
     *
     * @param param param
     * @return 结果
     * @author zhou_yajun
     */
    @RequestMapping(value = "/by/pdClassification/findList", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<ByBuyerPdCla>> buyerPdClassificationFind(@RequestBody RsRequest<ByBuyerPdCla> param){
        RsResponse<List<ByBuyerPdCla>> rs = new RsResponse<>();
        List<ByBuyerPdCla> pdClassificationList = iby121203Logic.buyerPdClassificationFind(param.getParam());
        if(CollectionUtils.isEmpty(pdClassificationList)){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买家产品类型信息不存在");
        }else{
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("买家产品类型获取成功");
            rs.setResult(pdClassificationList);
        }
        return rs;
    }
}
