package com.msk.bs.controller;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS210110301Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by ren_qiang on 2016/8/16.
 */
@Controller
@RequestMapping(value = "BS2101199")
public class BS2101199Controller {
    @RequestMapping(value = "init",
        method = RequestMethod.POST)
    public String init(IBS210110301Bean bean, Model model,
                       @RequestParam(value = "flagNum",required = false) String flagNum) throws UnsupportedEncodingException {

        if(bean != null && !StringUtil.isNullOrEmpty(bean.getSlContact())){
            bean.setSlContact(URLDecoder.decode(bean.getSlContact(),"UTF-8"));
        }
        if(bean != null && !StringUtil.isNullOrEmpty(bean.getSlAccount())){
            //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 start-->
            bean.setSlAccount(URLDecoder.decode(bean.getSlAccount(),"UTF-8"));
        }
        model.addAttribute("bean", bean);
        model.addAttribute("flagNum", flagNum);
        return "/bs/BS2101199";
    }
}
