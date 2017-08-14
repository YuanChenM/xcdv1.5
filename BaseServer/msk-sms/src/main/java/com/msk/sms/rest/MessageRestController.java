package com.msk.sms.rest;

import com.msk.sms.bean.MessageParam;
import com.msk.sms.bean.RestRequest;
import com.msk.sms.bean.RestResponse;
import com.msk.sms.utils.SmsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 短信发送Rest Controller
 * @author jiang_nan
 */
@RestController
@RequestMapping("api")
@Api(value = "Message-Api", description = "短信发送相关APi")
public class MessageRestController {
    /**
     * 发送短信验证码
     * @param request 请求参数
     */
    @RequestMapping(value = "/ol/send/captcha",method = RequestMethod.POST)
    @ApiOperation(notes = "发送短信验证码", httpMethod = "POST", value = "发送短信验证码API")

    public @ResponseBody RestResponse<String> sendCaptchaMessage(@ApiParam(required = true, value = "发送短信相关信息") @RequestBody RestRequest<MessageParam> request){
        MessageParam param = request.getParam();
        String mobile = param.getMobile();
        String captcha = SmsUtils.createRandom(Boolean.TRUE, 6);
        String messageTemplate = "尊敬的用户您好您本次验证码为%s请在10分钟内使用完了工作人员不会索要号码请勿泄露号码";
        String message = String.format(messageTemplate, captcha);
        return null;
    }
}
