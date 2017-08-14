package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS2101138RsBean;
import com.msk.bs.bean.IBS2101139RsBean;
import com.msk.bs.logic.IBS2101133RsLogic;
import com.msk.core.entity.SlBsBuyer;
import com.msk.core.entity.SlHouseAccount;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101138RsParam;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by whc on 2016/10/18.
 */
@RestController
@Api(description = "管家和买家建立绑定关系RestController", tags = {"IBS2101134RsController"})
public class IBS2101134RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101134RsController.class);


    @Autowired
    private IBS2101133RsLogic ibs2101133RsLogic;

    @ApiOperation(value = "管家和买家建立绑定关系", notes = "管家和买家建立绑定关系接口")
    @RequestMapping(value = "/bs/hkBuyerReleationship/_save", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101133Validator")
    public RsResponse<T> saveReleationship(@RequestBody RsRequest<IBS2101138RsParam> requestParam) {
        RsResponse<T> rsResponse = new RsResponse<T>();
        IBS2101138RsParam param = requestParam.getParam();
        rsResponse = checkParam(rsResponse,param);
        if(SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            return rsResponse;
        }
        super.setCommonParam(param.getHkBuyers());
        if(StringUtil.isNullOrEmpty(param.getHkBuyers().getActId())){
            param.getHkBuyers().setActId(requestParam.getLoginId());
        }
        if(StringUtil.isNullOrEmpty(param.getHkBuyers().getCrtId())){
            param.getHkBuyers().setCrtId(requestParam.getLoginId());
        }
        if(StringUtil.isNullOrEmpty(param.getHkBuyers().getUpdId())){
            param.getHkBuyers().setUpdId(requestParam.getLoginId());
        }
        //绑定管家与买家的关系
        RsResponse rs = ibs2101133RsLogic.saveBuyer(param.getHkBuyers());
        return rs;
    }


    private RsResponse<T> checkParam(RsResponse<T> rsResponse,IBS2101138RsParam param){
        if(param == null || param.getHkBuyers() == null){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("参数不能为空");
            return rsResponse;
        }
        if(StringUtil.isNullOrEmpty(param.getHkBuyers().getSlCode())){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("买手ID不能为空");
            return rsResponse;
        }
        if(StringUtil.isNullOrEmpty(param.getHkBuyers().getHouseCode())){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("管家ID不能为空");
            return rsResponse;
        }
        if(CollectionUtils.isEmpty(param.getHkBuyers().getBuyerIds())){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("买家信息不能为空");
            return rsResponse;
        }
        if(!CollectionUtils.isEmpty(param.getHkBuyers().getBuyerIds())){
            int num = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(IBS2101138RsBean bean : param.getHkBuyers().getBuyerIds()){
                if(StringUtil.isNullOrEmpty(bean.getBuyerId())){
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的买家ID不能为空");
                    return rsResponse;
                }
                if(StringUtil.isNullOrEmpty(bean.getStartTime())){
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的买家ID为'"+bean.getBuyerId()+"'的开始日时不能为空");
                    return rsResponse;
                }
                try {
                    sdf.parse(bean.getStartTime());
                } catch (ParseException e) {
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的开始日时为'"+bean.getStartTime()+"'的格式不正确,格式为yyyy-MM-dd HH:mm:ss");
                    return rsResponse;
                }
                if(StringUtil.isNullOrEmpty(bean.getEndTime())){
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的买家ID为'"+bean.getBuyerId()+"'的结束日时不能为空");
                    return rsResponse;
                }
                try {
                    sdf.parse(bean.getEndTime());
                } catch (ParseException e) {
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的结束日时为'"+bean.getEndTime()+"'的格式不正确,格式为yyyy-MM-dd HH:mm:ss");
                    return rsResponse;
                }
                if(StringUtil.isNullOrEmpty(bean.getApplySide())){
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的买家ID为'"+bean.getBuyerId()+"'的认证方式不能为空");
                    return rsResponse;
                }
                if(StringUtil.isNullOrEmpty(bean.getApplyStatus())){
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("第"+num+"个的买家ID为'"+bean.getBuyerId()+"'的申请状态不能为空");
                    return rsResponse;
                }
                if(!StringUtil.isNullOrEmpty(bean.getApplyTime())){
                    try {
                        sdf.parse(bean.getApplyTime());
                    } catch (ParseException e) {
                        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                        rsResponse.setMessage("第"+num+"个的申请日时为'"+bean.getApplyTime()+"'的格式不正确,格式为yyyy-MM-dd HH:mm:ss");
                        return rsResponse;
                    }
                }
                num++;
            }
        }
        //判断管家是否存在
        SlHouseAccount slHouseAccount = new SlHouseAccount();
        slHouseAccount.setSlCode(param.getHkBuyers().getSlCode());
        slHouseAccount.setHouseCode(param.getHkBuyers().getHouseCode());
        int houseAccountCount = ibs2101133RsLogic.findHouseAccount(slHouseAccount);
        if(houseAccountCount <= 0){
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("冻品管家不存在");
            return rsResponse;
        }
        //判断买家是否已经绑定过
        List<SlBsBuyer> buyerList =ibs2101133RsLogic.findBuyerExist(param.getHkBuyers());
        if(!CollectionUtils.isEmpty(buyerList)){
            StringBuffer message = new StringBuffer();
            for(int i=0;i<buyerList.size();i++){
                if(i != buyerList.size() -1){
                    message.append(buyerList.get(i).getBuyerId()+",");
                }else {
                    message.append(buyerList.get(i).getBuyerId());
                }
            }
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage(message.toString()+".这"+buyerList.size()+"个买家ID已经绑定，不能重复绑定");
            return rsResponse;
        }
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        return rsResponse;
    }
}
