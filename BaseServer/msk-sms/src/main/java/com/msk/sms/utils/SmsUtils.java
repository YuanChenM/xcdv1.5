package com.msk.sms.utils;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * *SmsUtils
 * *@author jiang_nan
 * *@version 1.0
 **/
public final class SmsUtils {
    private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
    public static void sendCaptchaMessage(String mobile,String message){
        logger.debug("手机号码："+mobile + "短信内容："+message);
        try{
            HttpClient httpclient = new HttpClient();
            PostMethod post = new PostMethod("http://sms.api.ums86.com:8899/sms/Api/Send.do");
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
            post.addParameter("SpCode", "230716");
            post.addParameter("LoginName", "SNK");
            post.addParameter("Password","shennongke123456");
            post.addParameter("MessageContent", message);
            post.addParameter("UserNumber", mobile);
            post.addParameter("SerialNumber", "");
            post.addParameter("ScheduleTime", "");
            post.addParameter("ExtendAccessNum", "");
            post.addParameter("f", "1");
            httpclient.executeMethod(post);
            String info = new String(post.getResponseBody(),"gbk");
            logger.debug(info);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 创建指定数量的随机字符串
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }
}
