package com.msk.org.rest;

import java.util.List;

import com.msk.org.bean.param.LoginUserMenuParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.org.entity.OrgPage;
import com.msk.org.entity.OrgSystem;
import com.msk.org.logic.OrgPageLogic;
import com.msk.org.logic.OrgSysLogic;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api")
@Api(value = "Menu-Api",description = "菜单相关Rest接口")
public class MenuRestController {
    private static Logger logger = LoggerFactory.getLogger(MenuRestController.class);
    @Autowired
    private OrgSysLogic sysLogic;
    @Autowired
    private OrgPageLogic orgPageLogic;
    @RequestMapping(value = "/org/menu/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody RestResponse<List<OrgSystem>> searchMenuList(@RequestBody RestRequest<String> requestBody) {
        logger.debug("根据系统Code获得系统菜单");
        String sysCode = requestBody.getParam();
        List<OrgSystem> sysList = this.sysLogic.findSysListBySysCodeLike(sysCode);
        RestResponse<List<OrgSystem>> response = new RestResponse<List<OrgSystem>>();
        response.setResult(sysList);
        return response;
    }

    @RequestMapping(value = "/org/page/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<List<OrgPage>> searchPageList(@RequestBody RestRequest<String> requestBody){
        String systemCode = requestBody.getParam();
        RestResponse<List<OrgPage>> response = new RestResponse<List<OrgPage>>();
        List<OrgPage> pageList = this.orgPageLogic.findPageListBySysCode(systemCode);
        response.setResult(pageList);
        return response;
    }
    @RequestMapping(value = "/org/user/menu/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody RestResponse<List<OrgSystem>> searchUserMenuList(@RequestBody RestRequest<LoginUserMenuParam> requestBody){
        LoginUserMenuParam param = requestBody.getParam();
        List<OrgSystem> systemList = this.sysLogic.findUserMenuList(param);
        RestResponse<List<OrgSystem>> responseBody = new RestResponse<>();
        responseBody.setResult(systemList);
        return responseBody;
    }



}
