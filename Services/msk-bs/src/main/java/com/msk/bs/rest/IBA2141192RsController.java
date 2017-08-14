package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBA2141192Param;
import com.msk.bs.bean.IBS2101104SlHouseAccount;
import com.msk.bs.logic.*;
import com.msk.bs.utils.CodeImage;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlHouseAccount;
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

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ni_shaotang on 2016/10/27.
 */
@RestController
@Api(description = "买手app修改密码", tags = {"IBA2141192RsController"})
public class IBA2141192RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101104RsController.class);

    @Autowired
    private IBS2101104RsLogic bS2101104RsLogic;
    @Autowired
    private IBA2141192RsLogic ibs2101192RsLogic;

    @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
    @RequestMapping(value = "/ba/checkImageCode", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    protected void checkImageCode(String code, HttpServletResponse httpServletResponse) throws IOException {
        logger.debug("生成图片验证码");
        CodeImage c = new CodeImage();

        BufferedImage bi = null;
        if (StringUtil.isNullOrEmpty(code) || code.length() != 4) {
            bi = c.getBufferedImage();
        } else {
            bi = c.getBufferedImage(code);
        }
        System.out.println(c.getText());
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = httpServletResponse.getOutputStream();
        ImageIO.write(bi, "jpeg", sos);
        sos.close();
    }

    @ApiOperation(value = "获取管家短信验证码", notes = "根据管家手机号发送短信验证码")
    @RequestMapping(value = "/bs/house/verification/code/_query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> checkHouseMessCode(@RequestBody RsRequest<String> request) {
        logger.debug("获取管家短信验证码");
        RsResponse<String> response = new RsResponse<String>();
        if (null != request && !StringUtil.isNullOrEmpty(request.getParam())) {
            IBS2101104SlHouseAccount ibs2101104SlHouseAccount = new IBS2101104SlHouseAccount();
            ibs2101104SlHouseAccount.setHouseTel(request.getParam());
            SlHouseAccount houseAccountTe = bS2101104RsLogic.findHouseAccountByTelIsExist(ibs2101104SlHouseAccount);
            if (null != houseAccountTe) {

                Random random = new Random();
                int n = random.nextInt(899999);
                n = n + 100000;
                System.out.println(n);
                response.setMessage("短信发送成功");
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setResult(n + "");
            } else {
                response.setMessage("该管家手机号码不存在");
                response.setStatus(SystemConst.RsStatus.FAIL);
            }
        } else {
            response.setMessage("查询失败,参数不全");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }

    @ApiOperation(value = "获取买手短信验证码", notes = "根据买手手机号发送短信验证码")
    @RequestMapping(value = "/bs/buyer/verification/code/_query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> checkBuyerMessCode(@RequestBody RsRequest<String> request) {
        logger.debug("获取买手短信验证码");
        RsResponse<String> response = new RsResponse<String>();
        if (null != request && !StringUtil.isNullOrEmpty(request.getParam())) {
            IBS2101104SlHouseAccount ibs2101104SlHouseAccount = new IBS2101104SlHouseAccount();
            ibs2101104SlHouseAccount.setHouseTel(request.getParam());
            IBA2141192Param baseParam = new IBA2141192Param();
            baseParam.getFilterMap().put("slTel", request.getParam());
            int num = ibs2101192RsLogic.findBuyerAccount(baseParam);
            if (num > 0) {
                Random random = new Random();
                int n = random.nextInt(899999);
                n = n + 100000;
                System.out.println(n);
                response.setMessage("短信发送成功");
                response.setStatus(SystemConst.RsStatus.SUCCESS);
                response.setResult(n + "");
            } else {
                response.setMessage("该买手手机号码不存在");
                response.setStatus(SystemConst.RsStatus.FAIL);
            }
        } else {
            response.setMessage("查询失败,参数不全");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }

    @ApiOperation(value = "修改冻品管家密码", notes = "根据管家手机号修改管家密码")
    @RequestMapping(value = "/bs/house/password/_update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBA2141192Validator")
    public RsResponse<String> updateHousePassword(@RequestBody RsRequest<IBA2141192Param> request) {
        logger.debug("修改冻品管家密码");
        RsResponse<String> response = new RsResponse<String>();
        IBA2141192Param param = request.getParam();
        param.setUpdId("买手APP");
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        if (null != param) {
            int num = ibs2101192RsLogic.findHouseAccount(param);
            if (num > 0) {
                int updateNum = ibs2101192RsLogic.updateHousePwd(param);
                if (updateNum > 0) {
                    response.setMessage("修改成功");
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                } else {
                    response.setMessage("修改失败");
                    response.setStatus(SystemConst.RsStatus.FAIL);
                }
            } else {
                response.setMessage("该管家手机号码不存在");
                response.setStatus(SystemConst.RsStatus.FAIL);
            }
        }
        return response;
    }

    /**
     * 修改买手密码
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "修改买手密码", notes = "根据买手手机号修改买手密码")
    @RequestMapping(value = "/bs/buyer/password/_update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBA2141192Validator")
    public RsResponse<String> updateBuyersPassword(@RequestBody RsRequest<IBA2141192Param> request) {
        logger.debug("修改买手密码");
        RsResponse<String> response = new RsResponse<String>();
        IBA2141192Param param = request.getParam();
        param.setUpdId("买手APP");
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        if (null != param) {
            int num = ibs2101192RsLogic.findBuyerAccount(param);
            if (num > 0) {
                int updateNum = ibs2101192RsLogic.updateBsPwd(param);
                if (updateNum > 0) {
                    response.setMessage("修改成功");
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                } else {
                    response.setMessage("修改失败");
                    response.setStatus(SystemConst.RsStatus.FAIL);
                }
            } else {
                response.setMessage("该买手不存在");
                response.setStatus(SystemConst.RsStatus.FAIL);
            }
        }
        return response;
    }
}
