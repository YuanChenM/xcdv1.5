package com.msk.mq.controller;


import com.msk.mq.observer.AmqpObserver;
import com.msk.mq.subject.AmqpSubject;
import com.msk.mq.bean.ObserverBean;
import com.msk.mq.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mao_yejun on 2016/6/30.
 */
@Controller
public class RegisterController {
    @Autowired
    private AmqpSubject amqpSubject;
    @Autowired
    Validator validator;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public
    @ResponseBody
    String register(ObserverBean observerBean) {
        boolean rs = validator.registerValidator(observerBean);
        if(rs) {
            new AmqpObserver(amqpSubject, observerBean);
            return "{\"flag\":\"success\"}";
        }
        return "{\"flag\":\"false\"}";
    }
}
