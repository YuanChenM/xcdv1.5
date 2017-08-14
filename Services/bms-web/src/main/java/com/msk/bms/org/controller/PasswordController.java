package com.msk.bms.org.controller;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.org.bean.param.ModifyPasswordParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 修改密码
 */
@Controller
@RequestMapping("comm")
public class PasswordController extends BaseController{

    /**
     * 初期化页面
     * @return
     */
    @RequestMapping(value = "password",method = RequestMethod.POST)
    public String initPasswordPage(){
        return "/common/updatePassword";
    }

    /**
     * 更新密码
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<Boolean> update(String oldPassword, String newPassword){
        LoginUser loginUser = this.getLoginUser();
        // 调用密码修改接口参数
        ModifyPasswordParam param = new ModifyPasswordParam();
        param.setEmployId(loginUser.getEmplId());
        param.setEmployCode(loginUser.getEmplNo());
        param.setNewPassword(newPassword);
        param.setOldPassword(oldPassword);
        RsRequest<ModifyPasswordParam> requestBody = new RsRequest<>();
        requestBody.setParam(param);

        // 调用密码修改接口
        String url = SystemServerManager.OrgServerManager.getModifyPassword();
        RsResponse<Boolean> result = RestClientUtil.post(url, requestBody, new TypeReference<RsResponse<Boolean>>() {
        });

        return result;
    }
}
