package com.msk.seller.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SlEpAgentAuthBean;
import com.msk.seller.logic.SL241103010Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL241103010")
public class SL241103010Controller extends BaseUploadController{

    @Autowired
    private SL241103010Logic sl241103010Logic;

    /**
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("save")
    public @ResponseBody int insert2(String jspSL241103010_slCode,SlEpAgentAuthBean slEpAgentAuthBean,HttpServletResponse response,HttpServletRequest request) throws IOException {
        //String slcode = StringUtil.toSafeString(request.getSession().getAttribute("jsp_slCode"));
        String slcode = StringUtil.toSafeString(jspSL241103010_slCode);
        int count=NumberConst.IntDef.INT_ZERO;
        //slEpAgentAuthBean.setCrtId(super.getLoginUser().getCrtId());
        if(StringUtil.isNullOrEmpty(slcode)){
            throw new BusinessException("请先创建账号信息!");
        }else{
            //Modified by xia_xiaojie on 2016/6/22. Modified start.
            //Modified end.
            slEpAgentAuthBean.setCrtId(getLoginUser().getEmplId());
            int num=sl241103010Logic.saveData(slcode,slEpAgentAuthBean);
            if(num>0){
                count= NumberConst.IntDef.INT_ONE;
            }
        }
        return count;
    }


    @RequestMapping(value="init",method = RequestMethod.POST)
    public String init() {

        return "sl/SL241103010";
    }
}
