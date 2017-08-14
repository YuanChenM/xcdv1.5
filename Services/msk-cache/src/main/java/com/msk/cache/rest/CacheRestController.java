package com.msk.cache.rest;

import com.msk.cache.bean.ConfigConstCacheParam;
import com.msk.cache.bean.RestRequest;
import com.msk.cache.bean.RestResponse;
import com.msk.cache.service.ConfigConstCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Api(tags = {"缓存Restful API"},value = "Cache-API",description = "缓存相关操作的API",position = 0,authorizations={@Authorization(value = "江南")})
public class CacheRestController {
    @Autowired
    private ConfigConstCacheService configConstCacheService;

    @RequestMapping(value = "put/config/const/cache",method = RequestMethod.POST)
    @ApiOperation(value = "设置Config Const Cache",httpMethod = "POST",notes = "提供给内部使用设置Config Const Cache API")
    public @ResponseBody RestResponse<String> putConfigConstCache(@RequestBody RestRequest<ConfigConstCacheParam> requestBody){
        ConfigConstCacheParam param = requestBody.getParam();
        configConstCacheService.setCacheValue(param.getKey(),param.getValue());
        RestResponse<String> response = new RestResponse<>();
        response.setMessage("设置成功");
        response.setStatus("S");
        response.setReturnCode("200");
        response.setResult("Config Const Cache设置成功");
        return response;
    }

    @RequestMapping(value ="get/config/const/cache",method = RequestMethod.POST)
    @ApiOperation(value = "获得Config Const Cache",httpMethod = "POST",notes = "提供给内部使用获得Config Const Cache API")
    public @ResponseBody RestResponse<String> getConfigConstCache(@RequestBody RestRequest<String> requestBody){
        String key = requestBody.getParam();
        String value = this.configConstCacheService.getCacheValue(key);
        RestResponse<String> response = new RestResponse<>();
        response.setMessage("获得Cache成功");
        response.setStatus("S");
        response.setReturnCode("200");
        response.setResult(value);
        return response;
    }

    @RequestMapping(value ="remove/config/const/cache",method = RequestMethod.POST)
    @ApiOperation(value = "删除Config Const Cache",httpMethod = "POST",notes = "提供给内部使用删除Config Const Cache API")
    public @ResponseBody RestResponse<String> removeConfigConstCacheValue(@RequestBody RestRequest<String> requestBody){
        String key = requestBody.getParam();
        this.configConstCacheService.removeCacheValue(key);
        RestResponse<String> response = new RestResponse<>();
        response.setMessage("删除成功");
        response.setStatus("S");
        response.setReturnCode("200");
        response.setResult("Config Const Cache删除成功");
        return response;
    }
    @RequestMapping(value ="remove/all/config/const/cache",method = RequestMethod.POST)
    @ApiOperation(value = "删除所有Config Const Cache",httpMethod = "POST",notes = "提供给内部使用删除所有Config Const Cache API")
    public @ResponseBody RestResponse<String> removeAllConfigConstCache(@RequestBody RestRequest<Void> requestBody){
        this.configConstCacheService.removeAllCacheValue();
        RestResponse<String> response = new RestResponse<>();
        response.setMessage("删除所有成功");
        response.setStatus("S");
        response.setReturnCode("200");
        response.setResult("Config Const Cache 删除所有成功");
        return response;
    }





}
