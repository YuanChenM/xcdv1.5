package com.msk.bms.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msk.bms.inventory.bean.SO152502Bean;
import com.msk.bms.inventory.bean.SO152502ResultBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.inventory.bean.SO152501Bean;
import com.msk.bms.inventory.bean.SO152501ResultBean;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.utils.RestClientUtil;

/**
 * Created by wang_fan on 2016/10/11.
 */
@Component("SO152502ExportInvService")
public class SO152502ExportInvService extends ExcelAsyncPrintFasterService {
    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {

        List<Map<String, ?>> listMapParam = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();

        BasePageParam basePageParam = new BasePageParam();
        DbUtils.buildLikeCondition(basePageParam, "lgcsCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "salePlatform", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "lgcsName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.FRONT);

        RestRequest rsRequest = new RestRequest();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lgcsCode", "");
        map.put("warehouseCode", "");
        map.put("slCodeDis", "");
        map.put("lgcsName", "");
        map.put("warehouseName", "");
        map.put("slName", "");
        map.put("pdCode", "");
        map.put("salePlatform", "");
        map.put("pdName", "");
        basePageParam.setFilterMap(map);
        rsRequest.setParam(basePageParam);
        String url =  SystemServerManager.SoInventoryServerManager.getGetSellerInventoryList();
        RsResponse<SO152502ResultBean> rsResponse = RestClientUtil.post(url, rsRequest, new TypeReference<RsResponse<SO152502ResultBean>>() {
        });
        List<SO152502Bean> so152502BeanList = new ArrayList<SO152502Bean>();
        so152502BeanList =  rsResponse.getResult().getSo152502BeanList();

        paramMap.put("list", so152502BeanList);
        paramMap.put("sheetName", "卖家库存列表");
        listMapParam.add(paramMap);
        return listMapParam;
    }
}
