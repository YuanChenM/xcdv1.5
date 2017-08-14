package com.msk.common.cache;


import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.exception.Restful404Exception;
import com.msk.common.utils.RestClientUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class ConfigConstCacheManager {
    private static Map<String,String> CONFIG_CONST_CACHE_MAP = new HashMap<>();

    private static Map<String,String> CACHE_SERVER_URL = new HashMap<>();

    public static String getCacheServerUrl(String key){
        return CACHE_SERVER_URL.get(key);
    }
    public static void setCacheServerUrl(String key,String value){
        CACHE_SERVER_URL.put(key,value);
    }
    public static String getConfigConstCache(String key){
        RsRequest<String> requestBody = new RsRequest<>();
        requestBody.setParam(key);
        String configConstValue = null;
        try {
            String getConfigConstCacheUrl = SystemServerManager.CacheServerManager.getGetConfigConstCache();
            RsResponse<String> response = RestClientUtil.post(getConfigConstCacheUrl,requestBody,new TypeReference<RsResponse<String>>(){});
            configConstValue = response.getResult();
        }catch (Restful404Exception ex){
            configConstValue = CONFIG_CONST_CACHE_MAP.get(key);
        }catch (Exception ex){
            configConstValue = null;
        }
        if(StringUtil.isEmpty(configConstValue)){
           return null;
        }
        return configConstValue;
    }

    public static void putConfigConstCache(String key,String value){
        ConfigConstCacheParam param = new ConfigConstCacheParam();
        param.setKey(key);
        param.setValue(value);
        RsRequest<ConfigConstCacheParam> requestBody = new RsRequest<>();
        requestBody.setParam(param);
        try {
            String putConfigConstCacheUrl = SystemServerManager.CacheServerManager.getPutConfigConstCache();
            RestClientUtil.post(putConfigConstCacheUrl,requestBody,new TypeReference<RsResponse<String>>(){});
        }catch (Restful404Exception ex){
           CONFIG_CONST_CACHE_MAP.put(key,value);
        }catch (Exception ex){
            CONFIG_CONST_CACHE_MAP.put(key,value);
        }
    }

    public static class ConfigConstCacheParam implements Serializable {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
