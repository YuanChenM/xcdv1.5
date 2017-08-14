package com.msk.bms.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msk.bms.inventory.bean.SO152501Bean;
import com.msk.bms.inventory.bean.SO152501ResultBean;
import com.msk.common.bean.RestRequest;
import com.msk.common.controller.ExcelAsyncPrintController;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.OrderCodeMasterDef;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.service.ExcelAsyncPrintService;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.ISO151501Bean;

/**
 * Created by wang_fan on 2016/10/11.
 */
@Component("SO152501ExportInvService")
public class SO152501ExportInvService extends ExcelAsyncPrintFasterService {
    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object param) {

        List<Map<String, ?>> listMapParam = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();

        BasePageParam basePageParam = new BasePageParam();
        DbUtils.buildLikeCondition(basePageParam, "lgcsCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "lgcsName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "supplierCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "supplyPlayFrom", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "supplierName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.FRONT);

        RestRequest rsRequest = new RestRequest();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lgcsCode", "");
        map.put("warehouseCode", "");
        map.put("lgcsName", "");
        map.put("warehouseName", "");
        map.put("slName", "");
        map.put("supplierCode", "");
        map.put("pdCode", "");
        map.put("supplyPlayFrom", "");
        map.put("slCodeDis", "");
        map.put("pdName", "");
        map.put("supplierName", "");
        basePageParam.setFilterMap(map);
        rsRequest.setParam(basePageParam);
        String url = SystemServerManager.SoInventoryServerManager.getGetDistributionList();
        RsResponse<SO152501ResultBean> rsResponse = RestClientUtil.post(url, rsRequest,
            new TypeReference<RsResponse<SO152501ResultBean>>() {});
        List<SO152501Bean> so152501BeanList = new ArrayList<SO152501Bean>();
        so152501BeanList = rsResponse.getResult().getSo152501BeanList();

        paramMap.put("list", so152501BeanList);
        paramMap.put("sheetName", "供应商库存列表");
        listMapParam.add(paramMap);
        return listMapParam;
    }
}
