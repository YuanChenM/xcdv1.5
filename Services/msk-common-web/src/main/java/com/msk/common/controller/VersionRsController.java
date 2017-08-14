package com.msk.common.controller;

import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取版本信息接口
 * @author zhou_yajun
 * @version 1.0
 **/
@RestController
public class VersionRsController extends BaseRsController{

    /**
     * 获取系统版本号
     *
     * @return
     */
    @RequestMapping(value="/getSysVersion",method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<Map<String,Object>> getSysEnvVersion(){
        String projectVersion = ConfigManager.getProjectVersion();
        String svnVersion = ConfigManager.getSvnVersion();
        String svnSubVersion = ConfigManager.getSvnSubVersion();
        String buildTime = ConfigManager.getBuildTime();
        Map<String,Object> sysVersionMap = new HashMap<>();
        sysVersionMap.put("projectVersion",projectVersion);
        sysVersionMap.put("svnVersion",svnVersion);
        sysVersionMap.put("svnSubVersion",svnSubVersion);
        sysVersionMap.put("buildTime",buildTime);
        RsResponse<Map<String,Object>> response = new RsResponse<Map<String,Object>>();
        response.setMessage("系统版本获取成功");
        response.setStatus("S");
        response.setResult(sysVersionMap);
        return response;
    }
}
