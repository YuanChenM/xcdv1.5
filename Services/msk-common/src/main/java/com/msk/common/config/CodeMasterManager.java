package com.msk.common.config;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.StringConst;
import com.msk.common.bean.ConfigParam;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.springframework.util.CollectionUtils;

public final class CodeMasterManager {

    public static Map<String,String> findCodeMasterMap(String codeMasterType){
        RsRequest<ConfigParam> requestParam = new RsRequest<>();
        ConfigParam param = SystemServerManager.getCodeMasterParam(StringConst.EMPTY,codeMasterType);
        requestParam.setParam(param);
        String configCodeMasterLoadUrl = SystemServerManager.getConfigLoadCodeMaster();
        TypeReference reference = new TypeReference<RsResponse<HashMap<String,String>>>(){};
        RsResponse<HashMap<String,String>> response = RestClientUtil.post(configCodeMasterLoadUrl,requestParam,reference);
        HashMap<String,String> codeMasterMap = response.getResult();
       return sortMapByKey(codeMasterMap);
    }

    public static String  getCodeMasterName(String codeMasterType,String key){
        Map<String,String> codeMasterMap = findCodeMasterMap(codeMasterType);
        if(CollectionUtils.isEmpty(codeMasterMap)){
            return null;
        }
        return codeMasterMap.get(key);
    }


    private static Map<String, String> sortMapByKey(Map<String, String> codeMasterMap) {
        if (CollectionUtils.isEmpty(codeMasterMap)) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new MapKeyComparator());
        sortMap.putAll(codeMasterMap);
        return sortMap;
    }

    static class  MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
