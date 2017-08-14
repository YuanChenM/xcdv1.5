package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.BR121402Param;
import com.msk.br.bean.BR121402RsPageResult;
import com.msk.br.logic.BR121402Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by zhao_chen on 2016/07/18.
 */
@RestController
public class IBR121402RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121402RsController.class);

    @Autowired
    private BR121402Logic br121402Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 配置管理一览页面接口
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/br/findBrSetting", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121402RsPageResult> findBrSetting(@RequestBody RsRequest<BR121402Param> param) {
        logger.info("设置列表接口");
        //参数未处理
        RsResponse<BR121402RsPageResult> response = new RsResponse<>();
        BR121402RsPageResult br121402RsPageResult = new BR121402RsPageResult();
        int count = this.br121402Logic.getPageCount(param.getParam());
        List<BrSetting> settingList = null;
        settingList = this.br121402Logic.findPageList(param.getParam(), BrSetting.class);
        br121402RsPageResult.setTotalCount(count);
        br121402RsPageResult.setBrSettings(settingList);
        response.setResult(br121402RsPageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }

    /**
     * 配置管理页面编辑保存接口
     *
     * @param br121402Param
     * @return
     */
    @RequestMapping(value = "/br/modifySetting", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121402RsPageResult> modifySetting(@RequestBody RsRequest<BR121402Param> br121402Param) {
        logger.info("配置管理画面编辑保存开始");
        logger.info("验证数据范围开始");
        br121402Param.getParam().getFilterMap().put("settingValue", br121402Param.getParam().getSettingStartValue());
        int regResult = br121402Logic.regRange(br121402Param.getParam());
        RsResponse<BR121402RsPageResult> result = new RsResponse<BR121402RsPageResult>();
        BR121402RsPageResult been = new BR121402RsPageResult();
        if (regResult > NumberConst.IntDef.INT_ZERO) {
            been.setRegResult(1);
        }
        br121402Param.getParam().getFilterMap().put("settingValue", br121402Param.getParam().getSettingEndValue());
        int regResult2 = br121402Logic.regRange(br121402Param.getParam());
        if (regResult2 > NumberConst.IntDef.INT_ZERO) {
            been.setRegResult(1);
        }

        int updateLine = br121402Logic.modify(br121402Param.getParam());
        if (updateLine > NumberConst.IntDef.INT_ZERO) {
            been.setUpdateLine(1);
            been.setRegResult(0);
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("处理成功");
            result.setResult(been);
            return result;
        }
        result.setResult(been);
        result.setStatus(SystemConst.RsStatus.FAIL);
        result.setMessage("编辑产品失败");
        return result;
    }


    /**
     * 配置管理页面数据删除接口
     *
     * @param br121402Param
     * @return
     */
    @RequestMapping(value = "/br/deleteSettingDate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteSettingDate(@RequestBody RsRequest<Long> br121402Param) {
        logger.info("删除配置数据接口开始调用");
        //参数可能有问题
        RsResponse<Integer> result = new RsResponse<Integer>();
        BR121402Param param =new BR121402Param();
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId(br121402Param.getLoginId());
        param.setUpdTime(currentDate);

        int deleteLine = br121402Logic.deleteSettingDate(br121402Param.getParam(),param);
        if (deleteLine > NumberConst.IntDef.INT_ZERO) {
            result.setStatus(SystemConst.RsStatus.SUCCESS);
            result.setMessage("处理成功");
            result.setResult(deleteLine);
            return result;
        }
        result.setStatus(SystemConst.RsStatus.FAIL);
        result.setMessage("删除失败");
        return result;

    }
/*
    public void setCommnFiled( ){
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.getParam().setCrtId(param.getLoginId());
        param.getParam().setUpdId(param.getLoginId());
        param.getParam().setActId(param.getLoginId());

        param.getParam().setUpdTime(currentDate);
        param.getParam().setCrtTime(currentDate);
        param.getParam().setActTime(currentDate);

    }*/


    /**
     * 配置管理画面新增数据接口
     *
     * @param br121402Param
     * @return
     */
    @RequestMapping(value = "/br/insertDemandPublishDetail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BR121402RsPageResult> insertDemandPublishDetail(@RequestBody RsRequest<BR121402Param> br121402Param) {
        logger.info("配置管理画面新增数据开始");
        RsResponse<BR121402RsPageResult> result = new RsResponse<BR121402RsPageResult>();
        if (br121402Param.getParam() != null) {
            Date currentDate = DateTimeUtil.getCustomerDate();
            br121402Param.getParam().setCrtId(br121402Param.getLoginId());
            br121402Param.getParam().setUpdId(br121402Param.getLoginId());
            br121402Param.getParam().setActId(br121402Param.getLoginId());
            br121402Param.getParam().setUpdTime(currentDate);
            br121402Param.getParam().setCrtTime(currentDate);
            br121402Param.getParam().setActTime(currentDate);


        BR121402RsPageResult been = new BR121402RsPageResult();
        int isExitResult = br121402Logic.isExist(br121402Param.getParam());
        if (isExitResult == NumberConst.IntDef.INT_ZERO) {
           /* RsResponse<BR121402RsPageResult> result = new RsResponse<BR121402RsPageResult>();*/
            long id = commonLogic.maxId("BR_SETTING", "ID");
            br121402Param.getParam().setId(id);
            int insertLine = br121402Logic.save(br121402Param.getParam());
            if (insertLine > NumberConst.IntDef.INT_ZERO) {
                //新增受影响行数大于1
                been.setInsertLine(1);
                //不存在数据
                been.setIsExitResult(0);
                result.setStatus(SystemConst.RsStatus.SUCCESS);
                result.setMessage("处理成功");
                result.setResult(been);
                return result;
            } else {
                //不存在数据
                been.setIsExitResult(0);
                //新增受影响行数=0
                been.setInsertLine(0);
                result.setResult(been);
                result.setStatus(SystemConst.RsStatus.FAIL);
                result.setMessage("编辑产品失败");
                return result;
            }
        } else {
            //根据setname找id
            BrSetting brSetting = br121402Logic.findOne(br121402Param.getParam());
            if (brSetting != null) {
                br121402Param.getParam().setId(brSetting.getId());
            }
            br121402Param.getParam().getFilterMap().put("settingValue", br121402Param.getParam().getSettingStartValue());
            int regResult = br121402Logic.regRange(br121402Param.getParam());
           /* RsResponse<BR121402RsPageResult> result = new RsResponse<BR121402RsPageResult>();*/
            if (regResult > NumberConst.IntDef.INT_ZERO) {
                //结果处理
                been.setRegResult(1);
            }
            br121402Param.getParam().getFilterMap().put("settingValue", br121402Param.getParam().getSettingEndValue());
            int regResult2 = br121402Logic.regRange(br121402Param.getParam());
            if (regResult2 > NumberConst.IntDef.INT_ZERO) {
                //结果处理 numberError
                been.setRegResult(1);
            }
            int updateLine = br121402Logic.modify(br121402Param.getParam());
            if (updateLine > NumberConst.IntDef.INT_ZERO) {
                been.setUpdateLine(1);
                been.setIsExitResult(1);
                been.setRegResult(0);
                result.setStatus(SystemConst.RsStatus.SUCCESS);
                result.setMessage("处理成功");
                result.setResult(been);
                return result;
            }
            been.setIsExitResult(1);
            result.setResult(been);
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("编辑产品失败");
            return result;
        }
    }else{
            result.setStatus(SystemConst.RsStatus.FAIL);
            result.setMessage("编辑产品失败");
            return result;
        }


    }




}


