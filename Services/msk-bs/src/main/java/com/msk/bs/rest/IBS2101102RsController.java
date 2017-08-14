package com.msk.bs.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.bean.IBS2101102RsResult;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.logic.IBS2101102RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlHouseType;
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

import java.util.List;

/**
 * Created by cx on 2016/2/14.
 */
@RestController
@Api(description = "编辑买手信息RestController", tags = {"IBS2101102RsController"})
public class IBS2101102RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101102RsController.class);

    @Autowired
    private IBS2101102RsLogic IBS2101102RsLogic;

    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;

    /**
     * 编辑买手店卖家账户接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "买手信息", notes = "编辑买手信息接口")
    @RequestMapping(value = "/bs/slInfo/slAccount/newOrUpdate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101102Validator")
    public RsResponse<IBS2101102RsResult> editSLAccount(@RequestBody RsRequest<IBS2101102RsParam> param) {
        logger.debug("编辑买手店卖家账户接口");
        RsResponse<IBS2101102RsResult> rs = new RsResponse<IBS2101102RsResult>();
        param.getParam().setLoginId(param.getLoginId());
        if (StringUtil.isNullOrEmpty(param.getParam().getSlAccount().getSlShowName())) {
            param.getParam().getSlAccount().setSlShowName(param.getParam().getSlAccount().getSlContact());
        }
        int result = IBS2101102RsLogic.checkModifySlAccount(param.getParam().getSlAccount());
        if (result > 0) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手账号与其他买手账号或名称或手机相同！");
            return rs;
        }
        //买手账号名称唯一
        if (result == 0) {
            //该买手名称与其他买手账号或名称或手机相同
            result = IBS2101102RsLogic.checkModifySlContact(param.getParam().getSlAccount());
        }
        //买手账号手机唯一
        if (result == 0) {
            //该买手手机与其他买手账号或名称或手机相同
            result = IBS2101102RsLogic.checkModifySlTel(param.getParam().getSlAccount());
        } else if (result > 0) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手名称与其他买手账号或名称或手机相同！");
            return rs;
        }
        //校验买手名称是否和其他管家一致
        /*if (result == 0) {
            result = IBS2101102RsLogic.findHouseShowName(param.getParam().getSlAccount());
        } else if (result > 0) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手手机与其他买手账号或名称或手机相同！");
            return rs;
        }*/
        //校验买手手机是否和其他管家一致
        if (result == 0) {
            result = IBS2101102RsLogic.findHouseTel(param.getParam().getSlAccount());
        } else if (result > 0) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手手机与其他买手账号或名称或手机相同！");
            return rs;
        }
        if (result > 0) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手手机号已被其他冻品管家使用！");
            return rs;
        }
        IBS2101102RsLogic.editAccount(param);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("编辑买手店卖家账户成功");
        return rs;
    }

    /**
     * 检查买手账户是否存在接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "检查账户", notes = "检查买手账户是否存在接口")
    @RequestMapping(value = "/bs/checkBsInfoExist", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> checkBsInfoExist(@RequestBody RsRequest<IBS2101102RsParam> param) {
        logger.debug("检查买手账户是否存在接口");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        int count = IBS2101102RsLogic.checkBsInfoExist(param);
        if (count == 0) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("检查买手账户是否存在成功");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("买手账户已存在");
        return rs;
    }

    /**
     * 编辑买手店卖家账户接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "添加账户", notes = "添加买手店卖家账户接口")
    @RequestMapping(value = "/bs/newBsInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.bs.validator.IBS2101102Validator")
    public RsResponse<Integer> newBsInfo(@RequestBody RsRequest<IBS2101102RsParam> param) {
        logger.debug("添加买手店卖家账户接口");
        RsResponse<Integer> rs = new RsResponse<Integer>();

        int count = IBS2101102RsLogic.saveAccount(param);
        if (count > NumberConst.IntDef.INT_ZERO) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setResult(count);
            rs.setMessage("添加买手店卖家账户成功");
            return rs;
        } else if (count == -1) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手账号与其他买手账号或名称或手机相同！");
            return rs;
        } else if (count == -2) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手名称与其他买手账号或名称或手机相同！");
            return rs;
        } else if (count == -3) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手手机号已被其他冻品管家使用！");
            return rs;
        } /*else if (count == -4) {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("该买手名称或手机号已被其他冻品管家使用！");
            return rs;
        }*/
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("添加买手店卖家账户失败");
        return rs;
    }

    /**
     * 获取冻品管家一级分类信息
     *
     * @return rs
     */
    @ApiOperation(value = "买手分类", notes = "获取买手分类接口")
    @RequestMapping(value = "/bs/findBsTypeList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<SlHouseType>> houseTypeList(@RequestBody RsRequest<SlHouseType> param) {
        logger.debug("获取买手分类接口");
        RsResponse<List<SlHouseType>> rs = new RsResponse<List<SlHouseType>>();
        SlHouseType houseType = param.getParam();
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlBsType(houseType);
        rs.setResult(houseTypeList);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;

    }

}
