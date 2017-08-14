package com.msk.mail.validation;

import com.msk.mail.bean.BaseValidation;
import com.msk.mail.bean.MailContext;
import com.msk.mail.bean.MailTextContent;
import org.springframework.util.StringUtils;


public class MailValidation extends BaseValidation<MailContext<MailTextContent>>{
    @Override
    public void validation(MailContext<MailTextContent> entity) {
        String [] to = entity.getTo();

        if(to==null || to.length==0){
            this.addErrorMessage("发送人不能为空");
        }
        if(to!=null&&to.length>0){
            for (String toMail : to){
                boolean isMail = checkMail(toMail);
                if(!isMail){
                    this.addErrorMessage("邮件地址:"+toMail+"地址格式不正确");
                }
            }
        }
        MailTextContent content = entity.getMailContent();
        if(StringUtils.isEmpty(content.getSubject())){
            this.addErrorMessage("邮件标题不能为空");
        }
        if(StringUtils.isEmpty(content.getText())){
            this.addErrorMessage("邮件内容不能为空");
        }
    }
}
