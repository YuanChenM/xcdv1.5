package com.msk.price.utils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.order.bean.ISO151434OrderRsResult;
import com.msk.order.bean.ISO151434RsParam;
import com.msk.price.bean.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.stock.bean.Stock;
import com.msk.stock.bean.StockResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhu_kai1 on 2016/6/8.
 */
public class CommRestUtil {

    private static Logger logger = LoggerFactory.getLogger(CommRestUtil.class);

    /**
     * 获取物流下拉框信息
     *
     * @return districtList
     */
    public static List<LgcsAreaBean> getLogiticsAreaList() {
        logger.info("调取物流区接口信息");
        DistrictParam param = new DistrictParam();
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String districturl = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(districturl, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        List<LgcsAreaBean> districtList = lgcsAreaBeanList.getResult().getLgcsAreaList();
        logger.info("调取物流区接口信息结束");
        return districtList;
    }

    /**
     * 获取库存信息
     * 根据pdTypCode查询卖家库存
     *
     * @param sp171101RsParam
     * @param sp171101Beans
     * @return
     */
    public static List<Stock> getStockInfo(SP171101RsParam sp171101RsParam, List<SP171101Bean> sp171101Beans) {
        sp171101RsParam.setPdList(sp171101Beans);
        RsRequest<SP171101RsParam> request = new RsRequest<SP171101RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(sp171101RsParam);
        String stockurl = ConfigManager.getMskStockService() + ConfigManager.getStockByPdTypeCodeListService();
        RsResponse<SP171101Bean> stockCntObject = RestClientUtil.post(stockurl, request, new TypeReference<RsResponse<SP171101Bean>>() {
        });
        logger.info("调取库存接口信息结束");
        List<Stock> stockCntList = new ArrayList<>();
        if (stockCntObject.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            stockCntList = stockCntObject.getResult().getPdStockList();
        }
        return stockCntList;
    }

    /**
     * 查询上半旬供应商销售排名
     *
     * @param iso151434RsParam
     * @return
     */
    public static RsResponse<ISO151434OrderRsResult> halfMonthOrder(ISO151434RsParam iso151434RsParam) {
        RsRequest<ISO151434RsParam> request = new RsRequest<ISO151434RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(iso151434RsParam);
        /*String url = "http://localhost:8081/msk-order/api/so/order/halfmonthcount";*/
        String url = ConfigManager.getMskOrderService() + ConfigManager.getOrderHalfMonthService();
        RsResponse<ISO151434OrderRsResult> seller = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISO151434OrderRsResult>>() {
        });
        return seller;
    }

    /**
     * 获取卖家接口产品信息
     *
     * @param param
     * @return List<DemandParam>
     */
    public static List<DemandParam> getDemandResponse(Map<String, Object> param) {
        //设置请求参数
        RsRequest<Map<String, Object>> requestParam = new RsRequest<>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        requestParam.setParam(param);

        String sellServicesUrl = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlPdCodeSearchServices();

        //请求接口
        RsResponse<DemandParam[]> response = RestClientUtil.post(sellServicesUrl, requestParam, new TypeReference<RsResponse<DemandParam[]>>() {
        });

        if (null != response.getResult() && response.getResult().length > 0) {
            return Arrays.asList(response.getResult());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 根据卖家商品信息获取对应商品的供应商现有库存量
     *
     * @param pdList
     * @return RsResponse<StockResult>
     */
    public static RsResponse<StockResult> getStockBySellerList(List<Map<String, Object>> pdList) {
        //设置请求参数
        RsRequest<Map<String, Object>> requestParam = new RsRequest<>();
        //param 参数
        Map<String, Object> param = new HashMap<>();
        param.put("pdList", pdList);

        //request param
        requestParam.setParam(param);

        //假数据
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");

        //url
        String url = ConfigManager.getMskStockService() + ConfigManager.getStockBySupplierListServices();
        //请求接口
        RsResponse<StockResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<StockResult>>() {
        });
        StockResult stockResult = new StockResult();
        if (null != response.getStatus() && response.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            response.setResult(stockResult);
        }
        return response;
    }

    /**
     * 获取产品信息
     *
     * @param param
     * @return
     */
    public static RsResponse<PDInfoResult> getProductInfo(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String disServicesUrl = ConfigManager.getMskProductService() + ConfigManager.getPdTypeNameInfoSearchService();//"http://localhost:8081/api/findProductInfo"
        RsResponse<PDInfoResult> productInfo =
                RestClientUtil.post(disServicesUrl, request, new TypeReference<RsResponse<PDInfoResult>>() {
                });
        return productInfo;
    }

    /**
     * 查询产品一级分类的name
     *
     * @return
     */
    public static List<PDInfoResult> getPdList(PDInfoParam pdInfoParam) {
        logger.info("获取产品所有一级信息");
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(pdInfoParam);
        //url
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdTypeNameInfoSearchService();
        //请求接口
        List<PDInfoResult> pdInfoResultList = new ArrayList<>();
        RsResponse<ProductBeanResult> pdList = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        if (pdList != null && null != pdList.getResult()) {
            pdInfoResultList = pdList.getResult().getResult();
        }
        logger.info("获取产品所有一级信息结束");
        return pdInfoResultList;
    }


    /**
     * 获取产品二级分类的名称
     *
     * @param pdInfoParam
     * @return
     */
    public static List<PDInfoResult> getMachingList(PDInfoParam pdInfoParam) {

        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(pdInfoParam);
        //url
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductBatchNameServices();
        //请求接口
        RsResponse<ProductBeanResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        List<PDInfoResult> pdInfoResultList = new ArrayList<>();
        if (response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            Map<String, List<PDInfoResult>> resultMap = response.getResult().getPdInfo();
            pdInfoResultList = resultMap.get("machining");
        }
        return pdInfoResultList;
    }

    /**
     * 调用卖家接口，根据SL Code 获取卖家名下商品的信息
     *
     * @return
     */
    public static List<MskSellerServiceParam> getPdResponse(MskSlInfoServiceParam slInfo) {

        //获取SLCode
        String slCode = slInfo.getSlCode();


        //设置请求参数
        RsRequest<Map<String, Object>> requestParam = new RsRequest<>();

        //假数据
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");

        Map<String, Object> param = new HashMap<>();
        param.put("slCode", slCode);
        requestParam.setParam(param);

        //url
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSnkPriceSlPdCodeSearchService();

        //请求接口
        RsResponse<MskSellerServiceParam[]> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<MskSellerServiceParam[]>>() {
        });

        if (null != response.getResult() && response.getResult().length > 0) {
            return Arrays.asList(response.getResult());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 获取卖家相关信息
     *
     * @return
     */
    public static MskSlInfoServiceParam getSlInfo(String slAccount) {
        //设置接口参数
        RsRequest<Map<String, Object>> requestParam = new RsRequest<>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        Map<String, Object> param = new HashMap<>();
        param.put("slAccount", slAccount);
        requestParam.setParam(param);
        //url
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerEpDataSearchServices();
        //"http://localhost:8082/msk-seller/api/sl/slInfo/slEpData/search";
        //调用接口
        RsResponse<MskSlInfoServiceParam> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<MskSlInfoServiceParam>>() {
        });
        if (null == response.getResult()) {
            return new MskSlInfoServiceParam();
        }
        return response.getResult();
    }

    //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang Start
    /**
     * 订单通道信息（4级）
     */
    public static enum SellWayName {
        WAY_CODE_1("普通订单", "1"), WAY_CODE_2("大额订单", "2"), WAY_CODE_3("大宗订单", "3"), WAY_CODE_4("超级大宗订单", "4");
        // 成员变量
        private String name;
        private String code;

        private SellWayName(String name, String code) {
            this.name = name;
            this.code = code;
        }

        // 普通方法
        public static String getName(String code) {
            for (SellWayName c : SellWayName.values()) {
                if (c.getCode().equals(code)) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang End
}
