package com.msk.buyers.logic;


import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121411RsParam;
import com.msk.bs.bean.IBS2101114Bean;
import com.msk.bs.bean.IBS2101114Param;
import com.msk.bs.bean.IBS2101114Result;
import com.msk.buyers.bean.*;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.service.ExcelAsyncPrintFasterService;
import com.msk.common.utils.RestClientUtil;
import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("BY121414Report")

public class BY121414Logic extends ExcelAsyncPrintFasterService {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121414Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    private int Number = 100;

    @Override
    public List<Map<String, ?>> getDataSourceFaster(Object objectParam) {

        Map<String, Object> excelMap = new HashMap<>();
        BaseParam baseParam = new BaseParam();
        List<Map<String, ?>> listParam = new ArrayList<>();
        //返回出来的实体对象
        List<String> buyerIdList = new ArrayList<>();
        List<IBY121323Bean> basicInfoList = new ArrayList<>();
        //获取买家总数
        int count = NumberConst.IntDef.INT_ZERO;
        RsResponse<IBY121323RsResult> countObject = this.getBuyerInfo(Number, NumberConst.IntDef.INT_ONE);
        if (countObject != null) {
            if (countObject.getResult() != null) {
                count = countObject.getResult().getTotalCount();
            }
        }
        List<IBY121323Bean> byinfoList = new ArrayList<>();
        RsResponse<IBY121323RsResult> byinfObject = this.getBuyerInfo(count, NumberConst.IntDef.INT_ONE);
        if (byinfObject != null) {
            if (byinfObject.getResult() != null) {
                byinfoList = byinfObject.getResult().getBuyerList();
                if (!CollectionUtils.isEmpty(byinfoList)) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < byinfoList.size(); i++) {
                        buyerIdList.add(byinfoList.get(i).getBuyerId());
                    }
                }
            }
        }
        List<IBS2101114Bean> houseNameList = this.getHomeList(buyerIdList);
        //所属买家池
        List<BY121414Bean> poolList = this.getQueryBrBuyerPool(buyerIdList);

        if (!CollectionUtils.isEmpty(byinfoList)) {
            for (IBY121323Bean byInfo : byinfoList) {
                IBY121323Bean iby121323Bean = new IBY121323Bean();
                iby121323Bean.setAccountName(byInfo.getAccountName());
                iby121323Bean.setTelNo(byInfo.getTelNo());
                iby121323Bean.setBuyerCode(byInfo.getBuyerCode());
                iby121323Bean.setStoreNo(byInfo.getStoreNo());
                iby121323Bean.setBuyerName(byInfo.getBuyerName());
                iby121323Bean.setBossName(byInfo.getBossName());
                iby121323Bean.setSuperiorName(byInfo.getSuperiorName());
                iby121323Bean.setMarketingsStatusName(byInfo.getMarketingsStatusName());
                iby121323Bean.setLgcsAreaName(byInfo.getLgcsAreaName());
                iby121323Bean.setMarketName(byInfo.getMarketName());
                if (!CollectionUtils.isEmpty(houseNameList)) {
                    this.dealHouseDate(houseNameList, byInfo, iby121323Bean);
                }
                if (!CollectionUtils.isEmpty(poolList)) {
                    this.dealPoolDate(poolList, byInfo, iby121323Bean);
                }
                basicInfoList.add(iby121323Bean);
            }
        }
        excelMap.put("byInfoList", basicInfoList);
        excelMap.put("sheetName", "template");

        listParam.add(excelMap);
        return listParam;
    }

    //买家所属池数据处理
    public void dealPoolDate(List<BY121414Bean> poolList, IBY121323Bean byInfo, IBY121323Bean iby121323Bean) {
        String poolNames = "";
        if (!CollectionUtils.isEmpty(poolList)) {
            for (BY121414Bean pool : poolList) {
                if (byInfo.getBuyerId().equals(pool.getBuyerId())) {
                    String poolName = pool.getBuyerPoolName();
                    if (!StringUtil.isEmpty(poolName)) {
                        poolNames = poolNames + poolName + ",";
                    }

                }
            }
            if (!StringUtil.isEmpty(poolNames)) {
                poolNames = poolNames.substring(0, poolNames.length() - 1);
            }
            iby121323Bean.setBuyerPoolName(poolNames);

        }
    }

    //处理冻品管家信息
    public void dealHouseDate(List<IBS2101114Bean> houseNameList, IBY121323Bean byInfo, IBY121323Bean iby121323Bean) {
        String houseNames = "";
        if (!CollectionUtils.isEmpty(houseNameList)) {
            for (IBS2101114Bean home : houseNameList) {
                if (byInfo.getBuyerId().equals(home.getBuyerId()) && home.getFlag().equals("0")) {
                    String houseName = home.getHouseShowName();
                    if (!StringUtil.isEmpty(houseName)) {
                        if (houseName != "") {
                            houseNames = houseNames + houseName + ",";
                        }
                    }
                }
            }
            if (!StringUtil.isEmpty(houseNames)) {
                houseNames = houseNames.substring(NumberConst.IntDef.INT_ZERO, houseNames.length() - 1);
            }
            iby121323Bean.setHouseName(houseNames);
        }
    }

    //获取买家池归属
    public List<BY121414Bean> getQueryBrBuyerPool(List<String> buyerIdList) {
        IBR121411RsParam searchParam = new IBR121411RsParam();
        searchParam.getFilterMap().put("buyerIdList", buyerIdList);
        RsRequest<IBR121411RsParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("001");
        request.setSiteCode("001");
        request.setParam(searchParam);
//        String url = "http://localhost:8081/api/br/PoolsByBuyerIds/query";
        String url = SystemServerManager.BuyersReportServerManager.getQueryPoolsByBuyerIds();
        RsResponse<BY121414RsResult> pageResultList = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY121414RsResult>>() {
        }, SerializerFeature.PrettyFormat);
        List<BY121414Bean> brBuyerPoolList = new ArrayList<>();
        if (pageResultList.getResult() != null) {
            brBuyerPoolList = pageResultList.getResult().getPoolList();
        }
        return brBuyerPoolList;
    }

    //查询买家基本信息
    public RsResponse<IBY121323RsResult> getBuyerInfo(int page, int pageNo) {
        List<IBY121323Bean> byList = new ArrayList<>();
        IBY121323RsParam searchParam = new IBY121323RsParam();
        RsRequest<IBY121323RsParam> request = new RsRequest<>();
        searchParam.setPageNo(pageNo);
        searchParam.setPageCount(page);
        request.setAuth("MSK00001");
        request.setLoginId("001");
        request.setSiteCode("001");
        request.setParam(searchParam);
//        String url = "http://localhost:8080/msk-buyers/api/by/buyers/query";
        String url = SystemServerManager.BuyersServerManage.getQueryBuyers();
        RsResponse<IBY121323RsResult> byInfo = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBY121323RsResult>>() {
        }, SerializerFeature.PrettyFormat);
        return byInfo;
    }

    //调取冻品管家接口
    public static List<IBS2101114Bean> getHomeList(List<String> buyerIdList) {
        IBS2101114Param param = new IBS2101114Param();
        param.setBuyerIdList(buyerIdList);
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//        String url ="http://localhost:8082/msk-bs/api/bs/searchHouseInfo";
        String url = SystemServerManager.BsServerManage.getSearchHouseInfo();
        RsResponse<IBS2101114Result> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101114Result>>() {
        }, SerializerFeature.PrettyFormat);
        List<IBS2101114Bean> slBsBuyerList = null;
        if (responce.getResult() != null) {
            slBsBuyerList = responce.getResult().getSlBsBuyerList();
        }
        return slBsBuyerList;
    }
}
