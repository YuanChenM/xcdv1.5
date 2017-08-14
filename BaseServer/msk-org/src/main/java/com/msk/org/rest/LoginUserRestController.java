package com.msk.org.rest;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.org.bean.LoginUser;
import com.msk.org.bean.LoginUserType;
import com.msk.org.bean.param.LoginUserParam;
import com.msk.org.logic.BankLogic;
import com.msk.org.logic.LoginUserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginUserRestController {
    @Autowired
    private LoginUserLogic loginUserLogic;
    @Autowired
    private BankLogic bankLogic;
    @RequestMapping(value = "login/user/_get",method = RequestMethod.POST)
    public RestResponse<LoginUser> getLoginUser(@RequestBody RestRequest<LoginUserParam> requestBody){
        LoginUserParam loginUserParam = requestBody.getParam();
        String loginUserType = loginUserParam.getUserType();
        String userName = loginUserParam.getUserName();
        LoginUser loginUser = null;
        if(LoginUserType.getUserTypeEmploy().equals(loginUserType)){
            loginUser = this.loginUserLogic.getLoginEmployeeInfo(userName);
        }else if(LoginUserType.getUserTypeSeller().equals(loginUserType)){

        }else if(LoginUserType.getUserTypeBank().equals(loginUserType)){
            loginUser = this.bankLogic.getBankUserInfo(userName);
        }
        loginUser.setUserType(loginUserType);
        RestResponse<LoginUser> responseBody = new RestResponse<>();
        responseBody.setResult(loginUser);
        return responseBody;
    }
}
