package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bs.bean.IBS2101130RsParam;
import com.msk.bs.logic.IBS2101130RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 买家投诉
 * Created by ren_qiang on 2016/9/7.
 */
@RestController
@Api(description = "添加投诉信息RestController", tags = {"IBS2101130RsController"})
public class IBS2101130RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBS2101130RsController.class);

    @Autowired
    private IBS2101130RsLogic ibs2101130RsLogic;
    @ApiOperation(value = "投诉信息", notes = "添加投诉信息接口")
    @RequestMapping(value = "/bs/saveComplaintInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> saveComplaintInfo(@RequestBody RsRequest<IBS2101130RsParam> request){
        logger.info("开始保存投诉信息");
        RsResponse<String> rsResponse = new RsResponse<String>();
        if(null != request){
            IBS2101130RsParam param = request.getParam();
            /**添加request非空判断 2016/10/13 任强 Start*/
            if(null != param) {
                if(StringUtils.hasLength(param.getHouseCode())&&StringUtils.hasLength(param.getById())
                        && null != param.getComplainDate()){
                    /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
                    // super.setCommonParam(param);
                    String loginId = request.getLoginId();
                    param.setActId(loginId);
                    param.setUpdId(loginId);
                    param.setCrtId(loginId);
                    //String sdate = getDate();
                    Date date =  DateTimeUtil.getCustomerDate();
                    /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
                    param.setCrtTime(date);
                    param.setUpdTime(date);
                    param.setActTime(date);
                    Integer cot = ibs2101130RsLogic.saveComplaintInfo(param);
                    if(cot != null && cot>0){
                        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
                        rsResponse.setMessage("处理成功!");
                    }
                    else{
                        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                        rsResponse.setMessage("保存数据失败!");
                    }
                }
                else{
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("管家ID和买家ID以及沟通日期不能为空!");
                }
            }
            else{
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                rsResponse.setMessage("传入的参数不能为空!");
            }
        }
        /**添加request非空判断 2016/10/13 任强 End*/
        logger.info("结束保存投诉信息");
        return  rsResponse;
    }
/*    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String sdate = sdf.format(new Date());
        return sdate;
    }*/
}
