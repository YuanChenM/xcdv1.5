package com.msk.org.rest;

import com.msk.org.entity.OrgSystemModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.msk.common.bean.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class SystemModuleRestController {
    @Autowired
    private com.msk.org.logic.OrgSysModuleLogic systemModuleLogic;

    @RequestMapping(value = "system/module/_search",method = RequestMethod.POST)
    public RestResponse<List<OrgSystemModule>> searchSystemModule(@RequestBody RestRequest<String> requestBody){
        String loginUserType = requestBody.getParam();
        List<OrgSystemModule> systemModelList = this.systemModuleLogic.findSystemModelByLoginUserType(loginUserType);
        RestResponse<List<OrgSystemModule>> response = new RestResponse<>();
        response.setResult(systemModelList);
        return response;
    }
}
