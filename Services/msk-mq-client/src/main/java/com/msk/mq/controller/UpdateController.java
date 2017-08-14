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
 * Created by mao_yejun on 2016/7/6.
 */
@Controller
public class UpdateController {
    @Autowired
    private AmqpSubject amqpSubject;
    @Autowired
    private BaseLogic baseLogic;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody String update(ObserverBean observerBean) {
        amqpSubject.updateObserver(observerBean);
        return "{\"flag\":\"success\"}";
    }
}
