package com.msk.buyers.controller.comm;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.*;
import com.msk.buyers.logic.*;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;
/**
 * 新增买家
 *
 * @author zhang_jian4
 */
@Controller
@RequestMapping("by/BYCommon")
public class BYCommonController extends BaseUploadController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BYCommonController.class);
    @Autowired
    private BYMailAddresseeInfoLogic mailAddresseeInfoLogic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BYMailSendHistoryLogic mailSendHistoryLogic;

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public void SendEmail(IBY121202RsParam oldParam, IBY121202RsParam newParam) {
        String oldWechat = oldParam.getBuyerSingleWechat() == null ? "" : oldParam.getBuyerSingleWechat();
        String oldQq = oldParam.getBuyerQq() == null ? "" : oldParam.getBuyerQq();
        String newWechat = newParam.getBuyerSingleWechat() == null ? "" : newParam.getBuyerSingleWechat();
        String newQq = newParam.getBuyerQq() == null ? "" : newParam.getBuyerQq();

        if(!oldWechat.equals(newWechat) || !oldQq.equals(newQq)){
            List<BYMailAddresseeInfoBean> beanList = mailAddresseeInfoLogic.getMailAddressList(new BaseParam());
            if(!CollectionUtils.isEmpty(beanList)){
                BYMailParam mailParam = new BYMailParam();
                int count = beanList.size();
                int toCount = 0;
                int ccCount = 0;
                for (int i = 0; i< count; i++){
                    if(beanList.get(i).getSendType().equals("0")){
                        toCount++;
                    }else {
                        ccCount++;
                    }
                }
                //接收邮件
                String[] to = null;
                //抄送邮件
                String[] cc = null;
                if(toCount>0){
                    to = new String[toCount];
                }
                if (ccCount>0){
                    cc = new String[ccCount];
                }
                BYMailAddresseeInfoBean bean = new BYMailAddresseeInfoBean();
                ccCount = 0;
                toCount = 0;
                for (int i = 0; i< count; i++){
                    bean = beanList.get(i);
                    if(bean.getSendType().equals("0")){
                        to[toCount++] = bean.getAddresseeEmail();
                    }else {
                        cc[ccCount++] = bean.getAddresseeEmail();
                    }
                }
                if(to != null){
                    mailParam.setMailTo(to);
                }
                if (cc != null){
                    mailParam.setMailCC(cc);
                }


                // 主题
                String sub = "买家QQ号、微信号变更通知";
                mailParam.setSubject(sub);

                //内容
                String content = "买家编码：" + oldParam.getBuyerCode() + "\n"
                        + "买家名称：" + oldParam.getBuyerName() + "\n"
                        + "买家手机号：" + oldParam.getBuyerTel() + "\n"
                        + "买家qq号："+newQq + "\n"
                        + "买家微信号："+newWechat;
                mailParam.setContent(content);

                RsRequest<BYMailParam> request = new RsRequest<>();
                request.setSiteCode("1");
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setParam(mailParam);

//                String url = "http://10.20.16.83:8095/base-mail/api/text/mail/simple/_send";
                String url = SystemServerManager.CommonServerManager.getMailSimpleSend();

                RsResponse<String> response = RestClientUtil.post(url,request,new TypeReference<RsResponse<String>>(){});
                // 保存内容到数据库履历表
                BYMailSendHistoryBean historyBean = new BYMailSendHistoryBean();
                Date currentDate = DateTimeUtil.getCustomerDate();
                Long maxId = commonLogic.maxId("BY_MAIL_SEND_HISTORY","MAIL_SEND_HISTORY_ID");
                historyBean.setMailSendHistoryId(maxId);
                historyBean.setBuyerId(newParam.getBuyerId());
                historyBean.setEmailTopic(sub);
                historyBean.setEmailContent(content);
                historyBean.setIsSuccess(response.getStatus().equals("S") ? "1" : "0");
                historyBean.setActId("admin");
                historyBean.setActTime(currentDate);
                historyBean.setCrtId("admin");
                historyBean.setCrtTime(currentDate);
                historyBean.setUpdId("admin");
                historyBean.setUpdTime(currentDate);
                mailSendHistoryLogic.save(historyBean);
            }
        }
    }
}
