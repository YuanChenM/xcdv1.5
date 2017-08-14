package com.msk.order.util;

import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.AsyncPostCallBack;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.constant.CommCodeMasterConst;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.StringConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.exception.BusinessException;
import com.msk.common.utils.RestClientUtils;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.*;
import com.msk.order.bean.result.*;
import com.msk.order.bean.result.Void;
import com.msk.order.entity.SoOrder;
import com.msk.order.service.ISO151410Service;
import com.msk.order.service.ISO151413Service;
import com.msk.order.service.ISO151414Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/11.
 */
public class SoRestUtil {

    private static Logger logger = LoggerFactory.getLogger(SoRestUtil.class);

    private static boolean isDebugger = false;

    /**
     * 通过买家ID获得管家和买手信息
     *
     * @param buyerId
     * @return
     */
    public static ISO151414BsBuyerInfoResult getSlBuyers(String buyerId) {
        logger.info("通过买家ID获得管家和买手信息开始");
        RestRequest<ISO151414BsBuyerInfoParam> request = new RestRequest<ISO151414BsBuyerInfoParam>();
        ISO151414BsBuyerInfoParam param = new ISO151414BsBuyerInfoParam();
        List<String> buyerIdList = new ArrayList<>();
        buyerIdList.add(buyerId);
        param.setBuyerIdList(buyerIdList);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //http://10.20.16.83:8884/msk-bs/api/bs/searchBuyerId
        String searchBuyerIdUrl = SystemServerManager.BsServerManage.getSearchBuyerId();
        RestResponse<ISO151414BsBuyerInfoResult> buyerInfo =
                RestClientUtils.post(searchBuyerIdUrl, request, new TypeReference<RestResponse<ISO151414BsBuyerInfoResult>>() {
                });
        logger.info("通过买家ID获得管家和买手信息结束");
        return buyerInfo.getResult();
    }

    /**
     * 查询物流区信息
     *
     * @param ISO151414DistrictParam
     * @return
     */
    public static DistrictResult getLogisticsAreaList(ISO151414DistrictParam ISO151414DistrictParam) {
        logger.info("查询物流区信息开始");
        RestRequest<ISO151414DistrictParam> request = new RestRequest<ISO151414DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId(ISO151414DistrictParam.getActId());
        request.setSiteCode("1");
        request.setParam(ISO151414DistrictParam);
        //http://10.20.16.83:8884/msk-district/api/district/query/lgcsArea
        String lgcsAreaUrl = SystemServerManager.DistrictServerManage.getDistrictQueryLgcsArea();
        RestResponse<DistrictResult> logisticsAreaList =
                RestClientUtils.post(lgcsAreaUrl, request, new TypeReference<RestResponse<DistrictResult>>() {
                });
        logger.info("查询物流区信息结束");
        if (null == logisticsAreaList) {
            return null;
        }
        DistrictResult districtResult = logisticsAreaList.getResult();
        return districtResult;
    }

    /**
     * 获取产品列表
     *
     * @param param
     * @return
     */
    public static List<ISO151414ProductStandardInfo> getProductList(ProductInfoParam param) {
        logger.info("查询产品列表开始");
        param.setGradeFlag(NumberConstant.IntDef.INT_ONE + StringConstant.EMPTY);
        RestRequest<ProductInfoParam> request = new RestRequest<ProductInfoParam>();
        request.setParam(param);
//        String url = "http://10.20.16.83:8884/msk-product/api/product/findProductInfos";
        String findProductInfoUrl = SystemServerManager.PdServerManager.getFindProductInfos();
        RestResponse<ISO151414ProductInfoResult> response = RestClientUtils.post(findProductInfoUrl, request, new TypeReference<RestResponse<ISO151414ProductInfoResult>>() {
        });
        logger.info("查询产品列表结束");
        if (StringUtils.hasLength(response.getStatus()) && response.getStatus().equals(SystemConstant.RsStatus.SUCCESS)) {
            if (!CollectionUtils.isEmpty(response.getResult().getResult())) {
                return response.getResult().getResult();
            }
        }
        return null;
    }

    /**
     * 根据买家电话得到买家基本信息
     *
     * @param tel
     * @return
     */
    public static ISO151414ByBuyerInfoResult getBuyerInfo(String tel) {
        if (StringUtil.isEmpty(tel)) {
            throw new BusinessException("买家电话不能为空");
        }
        logger.info("根据买家电话得到买家基本信息开始");
        RestRequest<ISO151414ByBuyerInfoParam> request = new RestRequest<>();
        ISO151414ByBuyerInfoParam ISO151414ByBuyerInfoParam = new ISO151414ByBuyerInfoParam();
        ISO151414ByBuyerInfoParam.setTelNo(tel);
        request.setParam(ISO151414ByBuyerInfoParam);
//        String url = "http://10.20.16.83:8884/msk-buyers/api/by/basicInfo/queryByTel";
        String url = SystemServerManager.BuyersServerManage.getQueryBuyeryBasicInfoByTel();
        RestResponse<ISO151414ByBuyerInfoResult> response = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414ByBuyerInfoResult>>() {
        });
        logger.info("根据买家电话得到买家基本信息结束");
        return response.getResult();
    }

    /**
     * 查询卖家产品库存列表
     *
     * @param param
     * @return
     */
    public static ISO151414StockRestResult getStockSlInfo(ISO151414BaseOrderParam param) {
        logger.info("查询卖家产品库存列表开始");
        RestRequest<ISO151414SearchStockRestParam> request = new RestRequest<>();
        ISO151414SearchStockRestParam stockParam = new ISO151414SearchStockRestParam();
        if (param.getSalePlatform().equals(OrderCodeMasterDef.SalePlatform.YDP)) {
            stockParam.setSalePlatform(CommCodeMasterConst.SystemCode.SYSTEM_CODE_SNK);
        } else {
            stockParam.setSalePlatform(CommCodeMasterConst.SystemCode.SYSTEM_CODE_MSK);
        }

        if(param.isHaveBuyerFlag()){
            stockParam.setSlCode(param.getBuyerSaleCode());
        }else {
            stockParam.setSlCode(param.getSellerCode());
        }

        stockParam.setLgcsCode(param.getDistrictCode());
        List<ISO151414StockProductInfo> productInfoList = new ArrayList<>();

        for (ISO151414OrderDetailParam ISO151414OrderDetailParam : param.getProducts()) {
            ISO151414StockProductInfo iso151414StockProductInfo = new ISO151414StockProductInfo();
            BeanUtils.copyProperties(ISO151414OrderDetailParam, iso151414StockProductInfo);
            iso151414StockProductInfo.setPdName(null);
            productInfoList.add(iso151414StockProductInfo);
        }
        stockParam.setProducts(productInfoList);
        request.setParam(stockParam);
        request.setLoginId(param.getCrtId());
        String url = SystemServerManager.SoInventoryServerManager.getFindSlProductIvList();
        RestResponse<ISO151414StockRestResult> response = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414StockRestResult>>() {
        });
        logger.info("查询卖家产品库存列表结束");
        if (null == response || null == response.getResult()) {
            return null;
        }

        return response.getResult();
    }

    /**
     * 查询供应商或者买手产品库存列表
     *
     * @param param
     * @return
     */
    public static ISO151414StockRestResult getStockSpInfo(ISO151414BaseOrderParam param) {
        logger.info("查询供应商或者买手产品库存列表开始");
        RestRequest<ISO151414SearchStockRestParam> request = new RestRequest<>();
        ISO151414SearchStockRestParam stockParam = new ISO151414SearchStockRestParam();
        if (param.getSalePlatform().equals(OrderCodeMasterDef.SalePlatform.YDP)) {
            stockParam.setSalePlatform(CommCodeMasterConst.SystemCode.SYSTEM_CODE_SNK);
        } else {
            stockParam.setSalePlatform(CommCodeMasterConst.SystemCode.SYSTEM_CODE_MSK);
        }

        stockParam.setSlCode(param.getBuyerSaleCode());
        stockParam.setLgcsCode(param.getDistrictCode());
        List<ISO151414StockProductInfo> productInfoList = new ArrayList<>();

        for (ISO151414OrderDetailParam ISO151414OrderDetailParam : param.getProducts()) {
            ISO151414StockProductInfo iso151414StockProductInfo = new ISO151414StockProductInfo();
            BeanUtils.copyProperties(ISO151414OrderDetailParam, iso151414StockProductInfo);
            iso151414StockProductInfo.setPdName(null);
            productInfoList.add(iso151414StockProductInfo);
        }
        stockParam.setProducts(productInfoList);
        request.setParam(stockParam);
        request.setLoginId(param.getCrtId());

        String url = SystemServerManager.SoInventoryServerManager.getFindSpProductIvList();
        RestResponse<ISO151414StockRestResult> response = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414StockRestResult>>() {
        });
        logger.info("查询供应商或者买手产品库存列表结束");
        if (null == response || null == response.getResult()) {
            return null;
        }
        return response.getResult();
    }

    /**
     * 买家提交订单后，刷新买家和冻品管家关系有效期时间
     *
     * @param param
     */
    public static void updateBuyerValidDate(ISO151414BaseOrderParam param) {
        logger.info("买家提交订单后，刷新买家和冻品管家关系有效期时间开始");
        RestRequest<ISO151414BuyerValidDateRsParam> request = new RestRequest<>();
        ISO151414BuyerValidDateRsParam iso151414BuyerValidDateRsParam = new ISO151414BuyerValidDateRsParam();
        iso151414BuyerValidDateRsParam.setBuyerId(param.getBuyersId());
        iso151414BuyerValidDateRsParam.setApplyStatus(NumberConstant.IntDef.INT_TWO);
        iso151414BuyerValidDateRsParam.setOrderCrtTime(param.getCrtTime());
        iso151414BuyerValidDateRsParam.setDays(NumberConstant.IntDef.INT_SIX * NumberConstant.IntDef.INT_TEN);
        request.setLoginId(param.getCrtId());
        request.setParam(iso151414BuyerValidDateRsParam);
//        String url = RestClientUtil.changeUrl("/bs/updateBuyerValidDate",true);
        String url = SystemServerManager.BsServerManage.getUpdateBuyerValidDate();
        RestClientUtils.post(url, request, new TypeReference<RestResponse<Void>>() {
        });
        logger.info("买家提交订单后，刷新买家和冻品管家关系有效期时间结束");
    }

    /**
     * 调用买家查询(环标码)买家编码接口
     * 得到买家编码
     *
     * @param buyerInfoParam
     * @return
     */
    public static String getBuyerCodeByBuyerId(ISO151414ByBuyerInfoParam buyerInfoParam) {
        logger.info("调用买家查询(环标码)买家编码接口开始");
        RestRequest<ISO151414ByBuyerInfoParam> request = new RestRequest<>();
        request.setParam(buyerInfoParam);
//        String url = "http://10.20.16.83:8884/msk-buyers/api/by/buyerCodeWithRingCode/query";
        String url = SystemServerManager.BuyersServerManage.getQueryBuyerCodeWithRingCode();
        RestResponse<ISO151414ByBuyerInfoResult> result = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414ByBuyerInfoResult>>() {
        });
        logger.info("调用买家查询(环标码)买家编码接口结束");
        if (null == result || null == result.getResult()
                || StringUtil.isEmpty(result.getResult().getBuyerCode())) {
            return null;
        }

        return result.getResult().getBuyerCode();
    }

    /**
     * 调用买家基本信息查询接口得到批发市场或菜场名称
     *
     * @param buyerInfoParam
     * @return
     */
    public static String getMarketNameByBuyerId(ISO151414ByBuyerInfoParam buyerInfoParam) {
        logger.info("调用买家基本信息查询接口得到批发市场或菜场名称开始");
        RestRequest<ISO151414ByBuyerInfoParam> request = new RestRequest<>();
        request.setParam(buyerInfoParam);
//        String url = "http://10.20.16.83:8884/msk-buyers/api/by/buyerInfo/findDetail";
        String url = SystemServerManager.BuyersServerManage.getFindBuyerDetailInfo();
        RestResponse<ISO151414BuyerResultInfo> result = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414BuyerResultInfo>>() {
        });
        logger.info("调用买家基本信息查询接口得到批发市场或菜场名称结束");
        if (null == result || null == result.getResult()
                || null == result.getResult().getSuperiorIdName()) {
            return null;
        }

        return result.getResult().getSuperiorIdName();
    }

    /**
     * 断货的时候调用新增卖家产品状态履历
     *
     * @param productParam
     */
    public static void dealSellerProductHis(ISO151414SellerProductParam productParam) {
        logger.info("断货的时候调用新增卖家产品状态履历开始");
        RestRequest<ISO151414SellerProductParam> request = new RestRequest<>();
        request.setLoginId(productParam.getCrtId());
        request.setParam(productParam);
//        String url = RestRequest.changeUrl("/sl/slInfo/slProduct/statusChange",true);
        String url = SystemServerManager.SellerServerManage.getStatusChange();
        RestResponse<Void> result = RestClientUtils.post(url, request, new TypeReference<RestResponse<Void>>() {
        });
        logger.info("断货的时候调用新增卖家产品状态履历结束");
    }

    /**
     * 根据货号得到产品编码
     *
     * @param skuParam
     * @return
     */
    public static ISO151415RestPdInfoResult getPdCodeBySkuCode(ISO151415RestSkuParam skuParam) {
        logger.info("根据货号得到产品编码开始");
        List<ISO151415SlArtNoResult> slArtNoList = new ArrayList<>();
        //拆分skuCode并设置调用所需要的参数slCodeDis，slPdArtno
        for (ISO151415SlArtNoResult slArtNo : skuParam.getSlList()) {
            /** slCodeDis(1-7) */
            String slCodeDis = slArtNo.getSkuCode().substring(NumberConstant.IntDef.INT_ZERO, NumberConstant.IntDef.INT_SEVEN);
            /** slPdArtno(8-12) */
            String slPdArtno = slArtNo.getSkuCode().substring(NumberConstant.IntDef.INT_SEVEN, NumberConstant.IntDef.INT_TWELVE);
            slArtNo.setSlCodeDis(slCodeDis);
            slArtNo.setSlPdArtno(slPdArtno);
            slArtNoList.add(slArtNo);
        }
        ISO151415RestSkuParam iso151415RestSkuParam = new ISO151415RestSkuParam();
        iso151415RestSkuParam.setSalePlatform(skuParam.getSalePlatform());
        iso151415RestSkuParam.setSaleRegionCode(skuParam.getSaleRegionCode());
        iso151415RestSkuParam.setSlList(slArtNoList);

        //调用卖家模块的获得产品信息接口得到pdCode
        RestRequest<ISO151415RestSkuParam> request = new RestRequest<ISO151415RestSkuParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(iso151415RestSkuParam);
        String url = SystemServerManager.SellerServerManage.getGetPdCode();
//        String url = "http://localhost:8880/msk-seller/api/sl/slInfo/slProduct/getPdCode";
        RestResponse<ISO151415RestPdInfoResult> rsResponse = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151415RestPdInfoResult>>() {
        });
        logger.info("根据货号得到产品编码结束");
        ISO151415RestPdInfoResult rsPdInfo = new ISO151415RestPdInfoResult();
        List<ISO151415SlArtNoResult> slArtNos = new ArrayList<>();
        if (rsResponse.getResult().getProductList().size() != NumberConstant.IntDef.INT_ZERO) {
            for (ISO151415SlArtNoResult iso151415SlArtNoResult : rsResponse.getResult().getProductList()) {
                String pdCode = null;
                if (!StringUtils.isEmpty(iso151415SlArtNoResult.getClassesCode())
                        && !StringUtils.isEmpty(iso151415SlArtNoResult.getMachiningCode())
                        && !StringUtils.isEmpty(iso151415SlArtNoResult.getBreedCode())
                        && !StringUtils.isEmpty(iso151415SlArtNoResult.getFeatureCode())
                        && !StringUtils.isEmpty(iso151415SlArtNoResult.getWeightCode())
                        && !StringUtils.isEmpty(iso151415SlArtNoResult.getGradeCode())) {
                    pdCode = iso151415SlArtNoResult.getClassesCode() + iso151415SlArtNoResult.getMachiningCode() + iso151415SlArtNoResult.getBreedCode()
                            + iso151415SlArtNoResult.getFeatureCode() + iso151415SlArtNoResult.getWeightCode() + iso151415SlArtNoResult.getGradeCode();
                }
                iso151415SlArtNoResult.setPdCode(pdCode);
                String sku = null;
                //获得原sku
                for (ISO151415SlArtNoResult artNo : slArtNoList) {
                    if (iso151415SlArtNoResult.getSlCodeDis().equals(artNo.getSlCodeDis()) && iso151415SlArtNoResult.getSlPdArtno().equals(artNo.getSlPdArtno())) {
                        sku = artNo.getSkuCode();
                    }
                }
                iso151415SlArtNoResult.setSkuCode(sku);
                slArtNos.add(iso151415SlArtNoResult);
            }
        }
        rsPdInfo.setProductList(slArtNos);

        return rsPdInfo;
    }

    /**
     * 调用价盘接口得到价盘信息
     *
     * @param priceWayParam
     * @return
     */
    public static ISO151414PriceWayResult getPriceWayInfo(ISO151414PriceWayParam priceWayParam) {
        logger.info("调用价盘接口得到价盘信息开始");
        RestRequest<ISO151414PriceWayParam> request = new RestRequest<>();
        request.setParam(priceWayParam);
//        String url = RestRequest.changeUrl("/pd/getPriceWayList",true);
        String url = SystemServerManager.SnkPriceServerManage.getGetPriceWayList();
        RestResponse<ISO151414PriceWayResult> result = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414PriceWayResult>>() {
        });
        logger.info("调用价盘接口得到价盘信息结束");
        if (null == result || null == result.getResult()) {
            return null;
        }
        return result.getResult();
    }

    /**
     * 根据卖家编码批量查询卖家信息
     *
     * @param param
     * @return
     */
    public static List<ISO151403SellerRestResult> querySellerList(ISO151403SellerRestParam param) {
        logger.info("根据卖家编码批量查询卖家信息开始");
        RestRequest<ISO151403SellerRestParam> request = new RestRequest<ISO151403SellerRestParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getQuerySellerList();
        RestResponse<ISO151403SellerRestResult> response = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151403SellerRestResult>>() {
        });
        logger.info("根据卖家编码批量查询卖家信息结束");
        if (response != null && SystemConstant.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult().getIslSellerRsResultList();
        } else {
            return null;
        }
    }

    /**
     * 通过前台传过来的买家编码查询买家id
     *
     * @param buyerInfoParam
     * @return
     */
    public static String getBuyerIdByBuyerCode(ISO151414ByBuyerInfoParam buyerInfoParam) {
        logger.info("通过前台传过来的买家编码查询买家id开始");
        RestRequest<ISO151414ByBuyerInfoParam> request = new RestRequest<ISO151414ByBuyerInfoParam>();
        request.setParam(buyerInfoParam);
        String url = SystemServerManager.BuyersServerManage.getQueryBuyerIdByBuyerCode();
        RestResponse<ISO151414ByBuyerInfoResult> response = RestClientUtils.post(url, request, new TypeReference<RestResponse<ISO151414ByBuyerInfoResult>>() {
        });
        logger.info("通过前台传过来的买家编码查询买家id结束");
        if(null == response || null == response.getResult() || null == response.getResult().getIby121319RsBean()){
            return null;
        }
        return response.getResult().getIby121319RsBean().getBuyerId();
    }

    /**
     * 调用库存出库接口
     * @param param
     */
    public static void transferOutBound(ISO151415RestOutBoundParam param){
        logger.info("调用库存出库接口开始");
        RestRequest<ISO151415RestOutBoundParam> request = new RestRequest<>();
        request.setSiteCode("903");
        request.setAuth("WMS00001");
        request.setLoginId(param.getLoginId());
        request.setParam(param);
        String url = SystemServerManager.SoInventoryServerManager.getOutboundInventory();
        RestResponse<Void> result = RestClientUtils.post(url, request, new TypeReference<RestResponse<Void>>() {
        });
        logger.info("调用库存出库接口结束");
    }
}
