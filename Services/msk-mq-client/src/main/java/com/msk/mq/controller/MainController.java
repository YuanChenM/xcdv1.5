package com.msk.mq.controller;


import com.msk.mq.config.AmqpConfiguration;
import com.msk.mq.bean.BaseParam;
import com.msk.mq.logic.BaseLogic;
import com.msk.mq.bean.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jackjiang on 16/7/1.
 */
@Controller
public class MainController {
    @Autowired
    AmqpConfiguration amqpConfiguration;
    @Autowired
    BaseLogic baseLogic;

    /**
     * 进入Main页面
     *
     * @return index页面
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(BaseParam param, Model model) {
        PageResult<BaseParam> pageResult = new PageResult<BaseParam>();
        List<BaseParam> baseParamList = baseLogic.getQueueList();
        pageResult.setData(baseParamList);
        model.addAttribute("pageResult", pageResult);
        return "index";
    }
}
