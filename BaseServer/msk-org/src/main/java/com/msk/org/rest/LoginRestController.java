package com.msk.org.rest;

import com.msk.org.bean.LoginUserType;
import com.msk.org.bean.param.LoginUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.org.bean.param.LoginParam;
import com.msk.org.entity.OrgEmploy;
import com.msk.org.logic.BankLogic;
import com.msk.org.logic.OrgEmployLogic;
import com.msk.org.logic.SellerLoginLogic;

@RestController
@RequestMapping(value = "api",method = RequestMethod.POST)
public class LoginRestController {
    @Autowired
    private OrgEmployLogic employLogic;
    @Autowired
    private SellerLoginLogic sellerLoginLogic;
    @Autowired
    private BankLogic bankLogic;

    @RequestMapping(value = "user/_login", method = RequestMethod.POST,produces={"application/json"})
    public @ResponseBody RestResponse<Boolean> login(@RequestBody RestRequest<LoginParam> requestBody) {
        LoginParam param = requestBody.getParam();
        String userType = param.getUserType();
        String userName = param.getUserName();
        String password = param.getPassword();
        RestResponse<Boolean> response = new RestResponse<>();
        if (StringUtils.isEmpty(userType)) {
            response.setResult(Boolean.FALSE);
        } else if (LoginUserType.getUserTypeBuyer().equals(userType)) {

        } else if (LoginUserType.getUserTypeSeller().equals(userType)) {
            response.setResult(this.sellerLoginLogic.sellerLogin(userName,password));
        } else if (LoginUserType.getUserTypeEmploy().equals(userType)) {
            response.setResult(this.employLogic.login(userName, password));
        }else if(LoginUserType.getUserTypeBank().equals(userType)){
            response.setResult(bankLogic.login(userName,password));
        } else {
            response.setResult(Boolean.FALSE);
        }
        if(response.getResult()){
            response.setStatus("S");
            response.setMessage("登录成功");
            response.setReturnCode("200");
        }else{
            response.setStatus("F");
            response.setMessage("登录失败,用户名密码错误");
            response.setReturnCode("500");
        }
        return response;
    }



    @RequestMapping(value = "employ/info/_get", method = RequestMethod.POST)
    public RestResponse<OrgEmploy> getLoginEmployInfo(@RequestBody RestRequest<LoginParam> requestBody) {
        LoginParam param = requestBody.getParam();
        String employCode = param.getUserName();
        String userType = param.getUserType();

        OrgEmploy employ = this.employLogic.findEmployByEmployCodeAndUserType(employCode,userType);
        RestResponse<OrgEmploy> response = new RestResponse<>();
        String message = "员工信息获得成功";
        String returnCode = "200";
        String status = "S";
        if (employ == null) {
            message = "员工信息获得失败";
            returnCode = "500";
            status = "F";
        } else {
            response.setResult(employ);
        }
        response.setMessage(message);
        response.setReturnCode(returnCode);
        response.setStatus(status);
        return response;
    }

}
