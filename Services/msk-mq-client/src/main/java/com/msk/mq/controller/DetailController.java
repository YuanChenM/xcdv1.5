package com.msk.mq.controller;

import com.msk.mq.config.AmqpConfiguration;
import com.msk.mq.bean.BaseParam;
import com.msk.mq.bean.ObserverBean;
import com.msk.mq.bean.PageResult;
import com.msk.mq.logic.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mao_yejun on 2016/7/7.
 */
@Controller
public class DetailController {
    @Autowired
    private BaseLogic baseLogic;

    /**
     * 进入Main页面
     *
     * @return index页面
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String main(BaseParam baseParam, Model model) {
        PageResult<ObserverBean> pageResult = baseLogic.getDetail(baseParam.getQueueName());
        pageResult.setQueueName(baseParam.getQueueName());
        model.addAttribute("pageResult", pageResult);
        return "detail";
    }
}
