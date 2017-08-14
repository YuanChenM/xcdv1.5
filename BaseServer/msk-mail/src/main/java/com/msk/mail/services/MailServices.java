package com.msk.mail.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.msk.mail.bean.MailContext;
import com.msk.mail.bean.MailTextContent;

@Service
public class MailServices {
    @Autowired
    private JavaMailSender mailSender;
    @Resource
    private Environment environment;

    public void sendTextMail(MailContext<MailTextContent> mailContext){
        String mailFrom = environment.getProperty("spring.mail.from");
        MailTextContent textContent = mailContext.getMailContent();
        SimpleMailMessage mailMessage = this.createSimpleMailMessage(mailFrom,textContent);
        String [] to = mailContext.getTo();
        String [] cc = mailContext.getCc();
        this.setMailAddressee(mailMessage,to,cc);
        this.mailSender.send(mailMessage);

    }
    private void setMailAddressee(SimpleMailMessage mailMessages,String [] to,String [] cc){
        mailMessages.setTo(to);
        String appendMailCc = environment.getProperty("append.mail.cc");
        String[] mailCcAddresseeList = this.getMailCcAddressee(cc,appendMailCc);

        if(mailCcAddresseeList == null || mailCcAddresseeList.length==0){
            return;
        }
        mailMessages.setCc(mailCcAddresseeList);
    }

    private String[] getMailCcAddressee(String [] cc,String appendMailCc){
        if(StringUtils.isEmpty(appendMailCc)){
            return cc;
        }
        String [] appendMailCcList = appendMailCc.split(",");
        if(cc == null||cc.length==0){
            return appendMailCcList;
        }


        List<String> mailCcAddresseeList = new ArrayList<>();
        Collections.addAll(mailCcAddresseeList, cc);
        Collections.addAll(mailCcAddresseeList,appendMailCcList);
        return mailCcAddresseeList.toArray(new String[mailCcAddresseeList.size()]);
    }

    private SimpleMailMessage createSimpleMailMessage(String mailFrom,MailTextContent textContent){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setSubject(textContent.getSubject());
        mailMessage.setText(textContent.getText());
        return mailMessage;
    }




}
