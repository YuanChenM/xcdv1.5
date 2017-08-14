package com.msk.org.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.org.bean.param.ModifyPasswordParam;
import com.msk.org.entity.OrgEmploy;
import com.msk.org.logic.OrgEmployLogic;

@RestController
@RequestMapping("api")
public class EmployRestController {
    @Autowired
    private OrgEmployLogic employLogic;

    @RequestMapping(value = "employ/_save",method = RequestMethod.POST)
    public RestResponse<OrgEmploy> saveEmploy(@RequestBody RestRequest<OrgEmploy> requestBody){
        OrgEmploy param = requestBody.getParam();
        OrgEmploy result = this.employLogic.save(param);
        RestResponse<OrgEmploy> responseBody = new RestResponse<>();
        responseBody.setResult(result);
        return responseBody;
    }
    @RequestMapping(value = "employ/password/_modify",method = RequestMethod.POST)
    @Validation
    public RestResponse<Boolean> modifyPassword(@RequestBody RestRequest<ModifyPasswordParam> requestBody){

        return null;
    }

}
