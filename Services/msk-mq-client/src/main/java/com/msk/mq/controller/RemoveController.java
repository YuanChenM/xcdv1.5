package com.msk.mq.controller;

import com.msk.mq.logic.BaseLogic;
import com.msk.mq.subject.AmqpSubject;

import com.msk.mq.bean.ObserverBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mao_yejun on 2016/6/30.
 */
@Controller
public class RemoveController {
    @Autowired
    private AmqpSubject amqpSubject;
    @Autowired
    BaseLogic baseLogic;

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody String register(ObserverBean observerBean) {

        amqpSubject.removeObserver(observerBean);

        return "{\"flag\":\"success\"}";
    }
}
