package com.msk.batch.order.commUtils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.order.bean.*;
import com.msk.batch.order.logic.BSO151402Logic;
import com.msk.cashPooling.bean.BaseCpResult;
import com.msk.cashPooling.bean.CpRunningParam;
import com.msk.cashPooling.bean.CpTransactionParam;
import com.msk.cashPooling.bean.ISO153103Param;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.bean.Void;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.common.utils.AsyncPostCallBack;
import com.msk.common.utils.RestClientUtil;
import com.msk.common.utils.StringUtil;
import com.msk.core.entity.SoCpSelCharging;
import com.msk.core.entity.SoSalesRanking;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.seller.bean.SlPdArtnoBean;
import com.msk.seller.bean.SlProductBean;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParamList;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhu_kai1 on 2016/5/27.
 */
public class BatchCommRestUtils {

    private static Logger logger = LoggerFactory.getLogger(BatchCommRestUtils.class);

    /**
     * 根据产品信息查出所有库存信息
     *
     * @param orderResults
     * @return
     */
    public static BSO151402StockResult getStockSpInfo(List<BSO151402OrderResult> orderResults, BSO151402Param bso151402Param) {
        BSO151402StockParam param = new BSO151402StockParam();

        if (CollectionUtils.isEmpty(orderResults)) {
            return null;
        }
        RsRequest<BSO151402StockParam> request = new RsRequest<>();

        if(!StringUtil.isEmpty(bso151402Param.getSalePlatform())){
            param.setSalePlatform(bso151402Param.getSalePlatform());
        }
        param.setLgcsCode(bso151402Param.getDistrictCode());
        param.setSlCode(bso151402Param.getSlCode());

        List<BSO151402StockProductInfo> products = new ArrayList<>();

        for (BSO151402OrderResult orderResult : orderResults) {
            BSO151402StockProductInfo productInfo = new BSO151402StockProductInfo();
            productInfo.setPdCode(orderResult.getPdCode());
            products.add(productInfo);
        }
        param.setProducts(products);

        request.setParam(param);

        String url = SystemServerManager.SoInventoryServerManager.getFindSpProductIvList();
        RsResponse<BSO151402StockResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<BSO151402StockResult>>() {
        });
        return response.getResult();
    }

    /**
     * 断货的时候调用新增卖家产品状态履历
     *
     * @param productParam
     */
    public static void dealSellerProductHis(BSO151402SellerProductParam productParam) {
        RsRequest<BSO151402SellerProductParam> request = new RsRequest<>();
        request.setParam(productParam);
//        String url = RestRequest.changeUrl("/sl/slInfo/slProduct/statusChange",true);
        String url = SystemServerManager.SellerServerManage.getStatusChange();
        RsResponse<Void> result = RestClientUtil.post(url, request, new TypeReference<RsResponse<Void>>() {
        });
    }

    /**
     * 查询所有有过库存的供应商列表
     *
     * @param param
     * @return
     */
    public static List<SoSalesRanking> getAllStockInfo(BSO151401Param param) {
        RestRequest<BSO151401Param> request = new RestRequest<>();
        request.setParam(param);
        String url = SystemServerManager.SoInventoryServerManager.getOwnersInHistory();// /inv/ownersInHistory    ownersInHistory
        RsResponse<BSO151401Result> result = RestClientUtil.post(url, request, new TypeReference<RsResponse<BSO151401Result>>() {
        });
        if(null == result || null == result.getResult() || CollectionUtils.isEmpty(result.getResult().getSellers())){
            return null;
        }
        return result.getResult().getSellers();
    }

    /**
     * 查询新增卖家对应产品信息 当前价盘周期的
     *
     * @param param
     * @return
     */
    public static List<SoSalesRanking> getNewSellerInfo(BSO151401Param param) {
        RestRequest<BSO151401Param> request = new RestRequest<>();
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getQuerySlSellerProduct();
        RsResponse<BSO151401Result> result = RestClientUtil.post(url, request, new TypeReference<RsResponse<BSO151401Result>>() {
        });
        if(null == result || null == result.getResult() || CollectionUtils.isEmpty(result.getResult().getSellers())){
            return null;
        }

        Iterator<SoSalesRanking> it = result.getResult().getSellers().iterator();
        while(it.hasNext()){
            SoSalesRanking soSalesRanking = it.next();
            if(StringUtil.isEmpty(soSalesRanking.getPdCode())){
                it.remove();
            }
        }
        return result.getResult().getSellers();
    }


    /**
     * 得到所有卖家的分销资格
     *
     * @param param
     * @return
     */
    public static List<SoSalesRanking> getAllDistQua(BSO151401Param param) {
        RestRequest<BSO151401Param> request = new RestRequest<>();
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getQuerySlSellerDisQua();
        RsResponse<BSO151401Result> result = RestClientUtil.post(url, request, new TypeReference<RsResponse<BSO151401Result>>() {
        });
        if (null == result || null == result.getResult() || CollectionUtils.isEmpty(result.getResult().getSellers())) {
            return new ArrayList<SoSalesRanking>();
        }
        return result.getResult().getSellers();
    }


    /**
     * 批量查询SKU
     * @param productList 产品集合
     * @param salesPlatform 供应平台
     * @param districtCode 物流区
     */
    public static SlPdArtnoBean getSlPdArtNoList(List<BSO151403DetailResult> productList, String salesPlatform, String districtCode){

        PDInfoParam pdParam = new PDInfoParam();
        //实例化根据产品查询SKU的Bean
        SlPdArtnoBean pdArtnoBean = new SlPdArtnoBean();
        List<PDInfoParam> pdInfoParamList = new ArrayList<PDInfoParam>();
        //订单明供货明细里的pdCode集合
        List<String> pdCodes = new ArrayList<String>();
        for(BSO151403DetailResult productCode : productList){
            pdCodes.add(productCode.getPdCode());
        }
        pdArtnoBean.setSalesPlatform(salesPlatform);
        pdArtnoBean.setSaleRegionCode(districtCode);
        pdParam.setPdCodes(pdCodes);
        //由于产品接口需求，需要传GradeFlg才可以批量根据PdCode拆分
        pdParam.setGradeFlag("hasGradeCode");
        // 调用产品接口，拆分PdCode
        logger.info("产品拆分开始");
        ProductBeanResult productBean = getProductCodeNameList(pdParam).getResult();
        logger.info("产品拆分结束");
        if(null == productBean){
            return null;
        }
        List<PDInfoResult> slPdArtnoBeenList = new ArrayList<PDInfoResult>();
        Map<String ,String> classesCodeMap = new HashMap<String ,String>();
        Map<String ,String> breedCodeMap = new HashMap<String ,String>();
        Map<String ,String> featureCodeMap = new HashMap<String ,String>();
        Map<String ,String> machiningCodeMap = new HashMap<String ,String>();
        Map<String ,String> weightCodeMap = new HashMap<String ,String>();
        Map<String ,String> gradeCodeMap = new HashMap<String ,String>();
        if(!CollectionUtils.isEmpty(productBean.getResult())){
            slPdArtnoBeenList = productBean.getResult();
            for(PDInfoResult pdInfoResult : slPdArtnoBeenList){
                classesCodeMap.put(pdInfoResult.getPdCode(),pdInfoResult.getClassesCode());
                breedCodeMap.put(pdInfoResult.getPdCode(),pdInfoResult.getBreedCode());
                featureCodeMap.put(pdInfoResult.getPdCode(),pdInfoResult.getFeatureCode());
                machiningCodeMap.put(pdInfoResult.getPdCode(),pdInfoResult.getMachiningCode());
                weightCodeMap.put(pdInfoResult.getPdCode(),pdInfoResult.getWeightCode());
                gradeCodeMap.put(pdInfoResult.getPdCode(),pdInfoResult.getGradeCode());
            }
        }

        List<BSO151403DetailResult> resultToParams = new ArrayList<BSO151403DetailResult>();
        for(BSO151403DetailResult bso151403DetailResult : productList){
            bso151403DetailResult.setClassesCode(classesCodeMap.get(bso151403DetailResult.getPdCode()));
            bso151403DetailResult.setBreedCode(breedCodeMap.get(bso151403DetailResult.getPdCode()));
            bso151403DetailResult.setMachiningCode(machiningCodeMap.get(bso151403DetailResult.getPdCode()));
            bso151403DetailResult.setFeatureCode(featureCodeMap.get(bso151403DetailResult.getPdCode()));
            bso151403DetailResult.setWeightCode(weightCodeMap.get(bso151403DetailResult.getPdCode()));
            bso151403DetailResult.setGradeCode(gradeCodeMap.get(bso151403DetailResult.getPdCode()));
            resultToParams.add(bso151403DetailResult);
        }

        List<SlPdArtnoBean> slPdArtnoBeanList = new ArrayList<SlPdArtnoBean>();
        for(BSO151403DetailResult bso151403DetailResult : resultToParams){
            //根据产品拆分得到的编码，循环塞值
            SlPdArtnoBean slPdArtnoBean = new SlPdArtnoBean();
            slPdArtnoBean.setClassesCode(bso151403DetailResult.getClassesCode());
            slPdArtnoBean.setFeatureCode(bso151403DetailResult.getFeatureCode());
            slPdArtnoBean.setBreedCode(bso151403DetailResult.getBreedCode());
            slPdArtnoBean.setMachiningCode(bso151403DetailResult.getMachiningCode());
            slPdArtnoBean.setWeightCode(bso151403DetailResult.getWeightCode());
            slPdArtnoBean.setGradeCode(bso151403DetailResult.getGradeCode());
            slPdArtnoBean.setSlCode(bso151403DetailResult.getSupplierCode());
            slPdArtnoBeanList.add(slPdArtnoBean);
        }
        pdArtnoBean.setProducts(slPdArtnoBeanList);
        // 调用卖家信息，获取卖家货号信息
        logger.info("根据产品编码查询SKU开始");
        SlPdArtnoBean slPdArtno = getSlPdArtNos(pdArtnoBean).getResult();
        logger.info("根据产品编码查询SKU结束");
        return slPdArtno;

    }

    /**
     * 调用产品接口，拆分pdCode
     *
     * @param param
     * @return stockInfo
     * @author wang_jianzhou
     */
    public static RsResponse<ProductBeanResult> getProductCodeNameList(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.PdServerManager.getFindProductInfos();//"http://localhost:8080/msk-product/api/product/findProductInfos";
        RsResponse<ProductBeanResult> stockInfo =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
                });
        return stockInfo;
    }

    /**
     * 批量查询卖家产品货号信息
     *
     * @param params
     * @return
     * @author wang_jianzhou
     */
    public static RsResponse<SlPdArtnoBean> getSlPdArtNos(SlPdArtnoBean params) {
        RsRequest<SlPdArtnoBean> request = new RsRequest<SlPdArtnoBean>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(params);
        String url = SystemServerManager.SellerServerManage.getSkuCodeSearch();//"http://localhost:8080/msk-seller/api/sl/slInfo/skuCode/search";
        RsResponse<SlPdArtnoBean> stockInfo =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<SlPdArtnoBean>>() {
                });
        return stockInfo;
    }

    /**
     * 获得配送订单编号
     *
     * @return 配送订单编号
     */
    public static String getDeliveryCode() {
        //当前时间
        Date currentDate = DateTimeUtil.getCustomerDate();
        SimpleDateFormat formatStyle = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String deliveryCode = formatStyle.format(currentDate);
        return deliveryCode;
    }
}
