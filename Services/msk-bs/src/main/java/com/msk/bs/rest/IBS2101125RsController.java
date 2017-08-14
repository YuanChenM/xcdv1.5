package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bs.bean.IBS2101125RsParam;
import com.msk.bs.bean.IBS2101125RsResult;
import com.msk.bs.logic.IBS2101125RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计算点月赞数
 * Created by ren_qiang on 2016/8/18.
 */
@RestController
@Api(description = "修改每月点赞数RestController", tags = {"IBS2101125RsController"})
public class IBS2101125RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBS2101125RsController.class);

    @Autowired
    private IBS2101125RsLogic ibs2101125RsLogic;
    @ApiOperation(value = "每月点赞数", notes = "修改点赞数接口")
    @RequestMapping(value = "/bs/updatePraiseTotal", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101125RsResult> updatePraiseTotal(@RequestBody RsRequest<IBS2101125RsParam> request){
        logger.info("开始计算点赞信息");
        RsResponse<IBS2101125RsResult> rsResponse = new RsResponse<IBS2101125RsResult>();
        /**添加request非空判断 2016/10/13 任强 Start*/
        if(null != request){
            IBS2101125RsParam param = request.getParam();
            if(null != param){
                /**Add: 横展开添加共通设置 2016/09/23   BY  任强  Start */
                String loginId = request.getLoginId();
                param.setActId(loginId);
                param.setUpdId(loginId);
                param.setCrtId(loginId);
                String sdate = getDate();
                Date date =  DateTimeUtil.getCustomerDate();
                /**Add: 横展开添加共通设置 2016/09/23   BY  任强  End */
                String praiseYm = sdate.substring(0,6);
                param.setPraiseYm(praiseYm);
                param.setCrtTime(date);
                param.setUpdTime(date);
                param.setActTime(date);
                Integer result = ibs2101125RsLogic.setHousePraise(param);
                if(null != result && result != 0 ){
                    rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
                    rsResponse.setMessage("处理成功");
                }
                else{
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("开始计算点赞失败");
                }

            }
        }
        /**添加request非空判断 2016/10/13 任强 End*/
        logger.info("结束计算点赞信息");
        return  rsResponse;

    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String sdate = sdf.format(new Date());
        return sdate;
    }
}
