package com.msk.mail.rest;

import com.msk.mail.validation.MailValidation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.msk.mail.bean.MailContext;
import com.msk.mail.bean.MailTextContent;
import com.msk.mail.bean.RestRequest;
import com.msk.mail.bean.RestResponse;
import com.msk.mail.services.MailServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Api(basePath="api/v1", value = "Mail-API",description = "邮件相关Rest接口",tags = {"邮件Restful接口定义"})
public class MailRestController {
    @Autowired
    private MailServices mailServices;
    @RequestMapping(value = "text/mail/_send",method = RequestMethod.POST)
    @ApiOperation(value = "发送文本信息邮件")
    public @ResponseBody RestResponse<String> sendTextMail(@ApiParam(required = true,value = "邮件相关参数,数据格式为JSON格式") @RequestBody RestRequest<MailContext<MailTextContent>> requestBody){
        MailContext<MailTextContent> mailContext = requestBody.getParam();
        MailValidation validation = new MailValidation();
        validation.validation(mailContext);

        RestResponse<String> response = new RestResponse<>();
        String returnCode = "200";
        String status = "S";
        String message = "邮件已经发送";
        String result = "邮件发送成功";
        if(validation.hasErrors()){
            returnCode = "500";
            status = "F";
            message = "邮件发送失败";
            result = validation.getAllErrorMessage();
        }else{
            this.mailServices.sendTextMail(mailContext);
        }
        response.setReturnCode(returnCode);
        response.setStatus(status);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }




}
