package com.msk.org.rest;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.logic.OrgRoleAuthLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RoleAuthRestController {

    @Autowired
    private OrgRoleAuthLogic orgRoleAuthLogic;

    @RequestMapping(value = "/department/role/_search",method = RequestMethod.POST)
    public @ResponseBody RestResponse<List<OrgRoleAuth>> searchDepartmentRole(@RequestBody RestRequest<String> requestBody){
        RestResponse<List<OrgRoleAuth>> responseBody = new RestResponse<>();
        List<OrgRoleAuth> roleAuthList = orgRoleAuthLogic.searchDepartmentRole(requestBody.getParam());
        responseBody.setResult(roleAuthList);
        return responseBody;
    }

}
