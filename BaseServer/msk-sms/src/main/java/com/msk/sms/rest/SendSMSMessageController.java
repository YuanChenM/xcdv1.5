package com.msk.sms.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.msk.sms.utils.SmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * *SendSMSMessageController
 * *@author jiang_nan
 * *@version 1.0
 **/
public class SendSMSMessageController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedInputStream buf = null;
        PrintWriter out = null;
        int contentLen = request.getContentLength();
        byte content[] = new byte[contentLen];
        try {
            buf = new BufferedInputStream(request.getInputStream());
            buf.read(content, 0, content.length);
            String jsonParam = new String(content,0,contentLen,"UTF-8");
            JSONObject jsonObject = JSON.parseObject(jsonParam);
            String captcha = SmsUtils.createRandom(Boolean.TRUE, 6);
            logger.debug("验证码："+captcha);
            String message = "尊敬的用户您好您本次验证码为"+captcha+"请在10分钟内使用完了工作人员不会索要号码请勿泄露号码";
            logger.debug("发送的内容："+message);
            JSONObject jsonMobile = jsonObject.getJSONObject("param");
            String mobile = (String) jsonMobile.get("mobile");
            logger.debug("手机号码："+mobile);
            SmsUtils.sendCaptchaMessage(mobile,message);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject resultCaptchaJson = new JSONObject();
            resultCaptchaJson.put("captcha",captcha);
            JSONObject resultJson = new JSONObject();
            resultJson.put("result",resultCaptchaJson);
            resultJson.put("message","验证码短信发送成功");
            resultJson.put("status",0);
            out = response.getWriter();
            out.write(resultJson.toJSONString());
        }catch (Exception e) {
            out = response.getWriter();
            JSONObject resultJson = new JSONObject();
            resultJson.put("message","验证码短信发送失败");
            resultJson.put("status",1);
            out.write(resultJson.toJSONString());
            throw new IOException("Parse data error!",e);
        }finally{
            if(buf!=null){
                buf.close();
            }
            if (out != null) {
                out.close();
            }
        }



    }
}
