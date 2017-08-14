package com.msk.product.utils;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.*;
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParamList;

import java.util.List;

/**
 * Created by yang_chunyan on 2016/6/16.
 */
public class RestUtil {

    /**
     * 获取物流信息
     *
     * @return
     */
    public static List<LgcsAreaBean> findDistrict(DistrictParam param) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String districtUrl = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(districtUrl, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        List<LgcsAreaBean> districtList = lgcsAreaBeanList.getResult().getLgcsAreaList();
        return districtList;
    }

    /**
     * 查询卖家库存
     *
     * @param param
     * @return
     */
    public static List<Stock> findSlStock(StockRsParamList param) {
        RsRequest<StockRsParamList> request = new RsRequest<StockRsParamList>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8081/msk-stock/api/so/queryStockBySeller/list";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getStockBysellerListService();
        RsResponse<StockResult> slStockList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<StockResult>>() {
                });
        List<Stock> stockList = slStockList.getResult().getPdStockList();
        return stockList;
    }

    /**
     * 查询供应商库存
     *
     * @param param
     * @return
     */
    public static List<Stock> findSPStock(StockRsParamList param) {
        RsRequest<StockRsParamList> request = new RsRequest<StockRsParamList>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8081/msk-stock/api/so/queryStockByPD/list";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getStockBySupplierListServices();
        RsResponse<StockResult> spStockList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<StockResult>>() {
                });
        List<Stock> stockList = spStockList.getResult().getPdStockList();
        return stockList;
    }

    /**
     * 查询产品编码
     *
     * @param param
     * @return
     */
    public static RsResponse<PDInfoResult> findPdCode(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
      //  String url = "http://localhost:8880/msk-product/api/findProductInfo";
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdInfoSearchService();
        RsResponse<PDInfoResult> pDInfoList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<PDInfoResult>>() {
                });
        return pDInfoList;
    }

    /**
     * 买家基本信息查询
     *
     * @param param
     * @return
     */
    public static RsResponse<ByBuyerBasicInfo> getBuyersInfo(IPD1411213Param param) {
        RsRequest<IPD1411213Param> request = new RsRequest<IPD1411213Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersServerManage.getFindBuyerDetailInfo();
        //String url = "http://localhost:8087/msk-buyers/api/by/buyerInfo/findDetail";
        RsResponse<ByBuyerBasicInfo> byBuyerBasicInfo =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<ByBuyerBasicInfo>>() {
                });
        return byBuyerBasicInfo;
    }

    /**
     * 买手基本信息查询
     *
     * @param param
     * @return
     */
    public static RsResponse<IPD1411213BsParam> getBsBasicInfo(IPD1411213Param param) {
        RsRequest<IPD1411213Param> request = new RsRequest<IPD1411213Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url =SystemServerManager.BsServerManage.getQueryShopAccount();
       // String url = "http://localhost:8080/msk-bs/api/bs/slInfo/slAccount/search";
        RsResponse<IPD1411213BsParam> bsBasicInfo =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD1411213BsParam>>() {
                });
        return bsBasicInfo;
    }

    /**
     * 管家基本信息查询
     *
     * @param param
     * @return
     */
    public static RsResponse<IPD1411213HouseParam> getHouseAccountInfo(IPD1411213Param param) {
        RsRequest<IPD1411213Param> request = new RsRequest<IPD1411213Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url =SystemServerManager.BsServerManage.getSearch();
      //  String url = "http://localhost:8080/msk-bs/api/bs/slInfo/houseAccount/search";
        RsResponse<IPD1411213HouseParam> houseAccountInfo =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD1411213HouseParam>>() {
                });
        return houseAccountInfo;
    }
}
