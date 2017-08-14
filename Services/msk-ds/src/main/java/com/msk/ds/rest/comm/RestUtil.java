package com.msk.ds.rest.comm;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlPdBrand;
import com.msk.core.entity.SlProduct;
import com.msk.core.entity.SlSeller;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.ds.bean.ISC1892I1Param;
import com.msk.ds.bean.ISC1892I1RsResult;
import com.msk.ds.bean.ISL231101RsResult;
import com.msk.ds.bean.ISO152405ParamBean;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.*;
import com.msk.stock.bean.StockResult;
import com.msk.stock.bean.StockRsParamList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by li_kai1
 */
public class RestUtil {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(RestUtil.class);

    /**
     * 获取产品信息
     * @param param
     * @return
     */
    public static PDInfoResult getProductInfo(PDInfoParam param) {
        logger.info("调用<获取产品信息>接口调用开始");
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdInfoSearchService();
//        RsResponse<PDInfoResult> pdInfoResult = RestClientUtil.post(url, request, new TypeReference<RsResponse<PDInfoResult>>() {});
//        PDInfoResult product = pdInfoResult.getResult();
        RsResponse<PDInfoResult> rs  = RestClientUtil.post(url, request, new TypeReference<RsResponse<PDInfoResult>>() {});
        PDInfoResult pdInfoResult = new PDInfoResult();
//        List<PDInfoResult> pdInfoResult = new ArrayList<PDInfoResult>();

        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw new BusinessException("调用<获取产品信息>接口失败");
        }
        if (rs.getResult() == null) {
            logger.info("产品编号:" + param.getPdCode() + "没有相关产品");
        }else {
            pdInfoResult = rs.getResult();
            logger.info("调用<获取产品信息>接口调用开始");
        }
        return pdInfoResult;
    }

    /**
     * 获取产品信息（Y） 批量查询
     * @param param
     * @return
     */
    public static List<PDInfoResult> getProductInfoList(PDInfoParam param) {
        logger.info("调用<获取产品信息>接口开始");
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdInfosSearchService();
        RsResponse<ProductBeanResult> rs  = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {});
        List<PDInfoResult> res = new ArrayList<PDInfoResult>();
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw  new BusinessException("调用<获取产品信息>接口失败");
        }
        if (rs.getResult() == null) {
            logger.info("产品编号没有相关产品");
        }else {
            res = rs.getResult().getResult();
            logger.info("<批量获取产品信息>接口调用成功");
        }
        return res;
    }

    /**
     * 查询卖家产品货号信息(N)
     * @param param
     * @return
     */
    public static SlPdArtnoBean getSlPdArtNo(SlPdArtnoBean param){
        logger.info("<查询卖家产品货号信息>接口调用开始");
        RsRequest<SlPdArtnoBean> request = new RsRequest<SlPdArtnoBean>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlPdArtnoSearchServices();
//        String url = "http://localhost:8083/api/sl/slInfo/slPdArtno/search";
        RsResponse<SlPdArtnoBean> stockInfo = RestClientUtil.post(url, request, new TypeReference<RsResponse<SlPdArtnoBean>>() {
                });
        SlPdArtnoBean slPdArtno = new SlPdArtnoBean();
        if(!SystemConst.RsStatus.SUCCESS.equals(stockInfo.getStatus())&&null == stockInfo){
            throw new BusinessException("<查询卖家产品货号信息>接口调用失败");
        }
        if (stockInfo.getResult() == null) {
            logger.info("没有相关的货号");
        }else{
            slPdArtno = stockInfo.getResult();
            logger.info("<查询卖家产品货号信息>接口调用成功");
        }
        return slPdArtno;
    }

    /**
     * 获取产品包装信息(Y)
     * @param param
     * @return
     */
    public static List<PDInfoResult> getPdNormsStd(PDInfoParam param){
        logger.info("<获取产品包装信息>接口调用开始");
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8085/api/findProductPackage";
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdPackageInfoSearchServices();

        RsResponse<ProductBeanResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {});
        List<PDInfoResult> list = new ArrayList<PDInfoResult>();
        if(!SystemConst.RsStatus.SUCCESS.equals(response.getStatus())){
            throw new BusinessException("<获取产品包装信息>接口调用失败");
        }
        if(response.getResult() == null) {
            logger.info("没有对应的产品包装信息");
        }else {
            logger.info("<获取产品包装信息>接口调用结束");
            ProductBeanResult productBeanResult = response.getResult();
            list = productBeanResult.getResult();
        }
        return list;

    }

    /**
     * 查询物流区信息接口(Y)
     * @return
     */
    public static List<LgcsAreaBean> getLogiticsAreaList(){
        logger.info("<查询物流区信息>接口调用开始");
        DistrictParam param = new DistrictParam();
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //物流区信息接口url
        String districturl = ConfigManager.getMskDistrictService()+ ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> lgcsAreaBeanList = RestClientUtil.post(districturl, request, new TypeReference<RsResponse<DistrictResult>>() {});
        List<LgcsAreaBean> districtList  = new ArrayList<LgcsAreaBean>();
        if(!SystemConst.RsStatus.SUCCESS.equals(lgcsAreaBeanList.getStatus())){
            throw new BusinessException("<查询物流区信息>接口调用失败");
        }
        if(lgcsAreaBeanList == null ){
            logger.info("没有对应的物流区信息");
        }else {
            districtList  = lgcsAreaBeanList.getResult().getLgcsAreaList();
            logger.info("<查询物流区信息>接口调用结束");
        }
        return districtList;
    }

    /**
     * 根据卖家Account查询供应商信息
     *
     * @param param param
     * @return 根据卖家Account查询供应商名称
     * @author zhangchi
     */
    public static ISL231193RsResult queryslEpData(ISL231193RsParam param) {
        logger.info("<根据卖家Account查询供应商信息>接口调用开始");
        RsRequest<ISL231193RsParam> request = new RsRequest<ISL231193RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerEpDataSearchServices();
        RsResponse <ISL231193RsResult> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<ISL231193RsResult>>(){});
        ISL231193RsResult isl231193RsResult = new ISL231193RsResult();
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw  new BusinessException("<根据卖家Account查询供应商信息>接口调用失败");
        }
        if (rs.getResult() == null){
            logger.info("没有获取到供应商");
        }else {
            isl231193RsResult = rs.getResult();
            logger.info("<根据卖家Account查询供应商信息>接口调用结束");
        }
        return isl231193RsResult;
    }

    /**
     * 批量查询卖家企业信息接口(N)
     * @param param
     * @return
     */
    public static List<ISL231193RsResult> slEpDataListSearch(ISL231193RsParam param){
        logger.info("<批量查询卖家企业信息接口>接口调用开始");
        RsRequest<ISL231193RsParam> request = new RsRequest<ISL231193RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8083/api/sl/slInfo/slEpDataList/search";
       String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlEpDataListSearchServices();
        RsResponse<ISL231193RsResult> rs =
                RestClientUtil.post(url,request,new TypeReference<RsResponse<ISL231193RsResult>>(){
        });
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw new BusinessException("<批量查询卖家企业信息接口>接口调用失败");
        }
        List<ISL231193RsResult> list = new ArrayList<ISL231193RsResult>();
        if(rs.getResult() == null){
            logger.info("没有对应卖家企业信息");
        }else {
            list = rs.getResult().getIsl231193RsList();
            logger.info("<批量查询卖家企业信息接口>接口调用结束");
        }
        return list;
    }

    /**
     * 根据卖家ID查询卖家一级分类(N)
     * @param param
     * @return
     */
    public static List<ISL231205Result> findSlOneClass(ISL231205RsParam param){
        logger.info("<根据卖家ID查询产品分类信息>接口调用开始");
        RsRequest<ISL231205RsParam> request = new RsRequest<ISL231205RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8083/api/sl/slInfo/SlOneClass/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerOneClassSearchServices();
        RsResponse<ISL231205Result[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISL231205Result[]>>() {});
        if(!SystemConst.RsStatus.SUCCESS.equals(response.getStatus())&&null==response){
            throw new BusinessException("<根据卖家ID查询产品分类信息>接口调用失败");
        }
        List<ISL231205Result> list = new ArrayList<ISL231205Result>();
        if(null != response.getResult() && response.getResult().length>0) {
            logger.info("<根据卖家ID查询产品分类信息>接口调用结束");
            list = Arrays.asList(response.getResult());
        }else {
            logger.info("没有对应卖家分类信息");
        }
        return list;
    }

    /**
     * 根据卖家产品查询产品生产商(N)
     * @param param
     * @return
     */
    public static List<ISL231206RsBean> findSlPropEpList(ISL231206RsParam param){
        logger.info("<根据卖家产品查询产品生产商>接口调用开始");
        RsRequest<ISL231206RsParam> request = new RsRequest<ISL231206RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8083/api/sl/slInfo/SlProp/search";
        String url = SystemServerManager.SellerServerManage.getSearchSlProp();
        RsResponse<ISL231206RsBean[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISL231206RsBean[]>>() {});
        if(!SystemConst.RsStatus.SUCCESS.equals(response.getStatus())&& null == response){
            throw new BusinessException("<根据卖家产品查询产品生产商>接口调用失败");
        }
        List<ISL231206RsBean> list = new ArrayList<ISL231206RsBean>();
        if(null != response.getResult() && response.getResult().length>0) {
            logger.info("<根据卖家产品查询产品生产商>接口调用结束");
            list = Arrays.asList(response.getResult());
        }else {
            logger.info("没有对应生产商信息");
        }
        return list;
    }

    /**
     * 获取卖家产品品牌信息(N)
     * @param param
     * @return
     */
    public static List<SlPdBrand> findSlBrand(SlProduct param){
        logger.info("<获取卖家产品品牌信息>接口调用开始");
        RsRequest<SlProduct> request = new RsRequest<SlProduct>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://localhost:8083/api/sl/slInfo/slPdBrand/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlPdBrandSearchServices();
        RsResponse<SlPdBrand[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SlPdBrand[]>>() {});
        if(!SystemConst.RsStatus.SUCCESS.equals(response.getStatus())&&null == response){
            throw new BusinessException("<获取卖家产品品牌信息>接口调用失败");
        }
        List<SlPdBrand> list = new ArrayList<SlPdBrand>();
        if(null != response.getResult() && response.getResult().length>0) {
            logger.info("<获取卖家产品品牌信息>接口调用结束");
            list = Arrays.asList(response.getResult());
        }else {
            logger.info("没有对应的卖家产品品牌信息");
        }
        return list;

    }

    /**
     * 根据卖家slCode获得生产商和品牌Id接口（N）
     * @param param
     * @return
     */
    public static List<ISL231204RsResult> findManuAndBrand(SlSeller param){
        logger.info("<根据卖家slCode获得生产商和品牌Id>接口调用开始");
        RsRequest<SlSeller> request = new RsRequest<SlSeller>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "HTTP://localhost:8083/api/sl/slInfo/getManufactureAndBrand";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerManufactureAndBrandSearchServices();
        RsResponse<ISL231204RsResult> rs = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISL231204RsResult>>() {});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null == rs){
            throw new BusinessException("<根据卖家slCode获得生产商和品牌Id>接口调用失败");
        }
        List<ISL231204RsResult> list = new ArrayList<ISL231204RsResult>();
        if (rs.getResult() == null){
            logger.info("没有对应的生产商和品牌信息");
        }else {
            list = rs.getResult().getIsl231201List();
            logger.info("<根据卖家slCode获得生产商和品牌Id>接口调用结束");
        }
        return list;
    }

    /**
     * 修改销售库存
     * @param param
     * @return
     */
    public static Boolean saveStockOfSupplierList(StockRsParamList param){
        logger.info("<修改销售库存>接口调用开始");
        RsRequest<StockRsParamList> request = new RsRequest<StockRsParamList>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8804/api/so/saveStockOfSupplierList";
        String url = ConfigManager.getMskStockService() + ConfigManager.getStockOfSupplierListSaveServices();
        RsResponse<StockResult> stockResult = RestClientUtil.post(url, request, new TypeReference<RsResponse<StockResult>>() {});
        if(!SystemConst.RsStatus.SUCCESS.equals(stockResult.getStatus())&&null==stockResult){
            throw new BusinessException("<修改销售库存>接口调用失败");
        }
        Boolean result = false;
        if(SystemConst.RsStatus.SUCCESS.equals(stockResult.getStatus())){
            result = true;
            logger.info("<修改销售库存>接口调用结束");
        }else {
            logger.info("修改库存失败");
        }
        return result;
    }

    /**
     * 查询产品等级List
     * @param param
     */
    public static List<PDInfoResult> getProductGrade(PDInfoParam param) {
        logger.info("<查询产品等级List>接口调用开始");
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdTypeNameInfoSearchService();
        RsResponse<ProductBeanResult> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<ProductBeanResult>>(){});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null == rs){
            throw new BusinessException("<查询产品等级List>接口调用失败");
        }
        ProductBeanResult productBeanResult = rs.getResult();
        List<PDInfoResult> pdInfoResults = new ArrayList<PDInfoResult>();
        if (productBeanResult == null) {
            logger.info("没有产品对应等级");
        }else {
            pdInfoResults = productBeanResult.getResult();
            logger.info("<查询产品等级List>接口调用结束");
        }
        return pdInfoResults;
    }

    /**
     * 获取有效产品的地区
     *
     */
    public static List<ISL231199RsBean> getAreaList() {
        logger.info("<获取有效产品的地区>接口调用开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://10.20.16.101:8080/api/sl/slInfo/slCodeName/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlCodeNameSearchServices();
        RsResponse<ISL231199RsBean> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<ISL231199RsBean>>(){});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw new BusinessException("<获取有效产品的地区>接口调用失败");
        }
        List<ISL231199RsBean> list = new ArrayList<ISL231199RsBean>();
        if (rs.getResult() == null){
            logger.info("没有对应的产品地区");
        }else {
            list = rs.getResult().getIsl231199RsBeanList();
            logger.info("<获取有效产品的地区>接口调用结束");        }
        return list;
    }

    /**
     * 获取有效产品的供应商信息
     *
     */
    public static List<ISL231199RsBean> getSuppName(ISL231199RsParam param) {
        logger.info("<获取有效产品的供应商信息>接口调用开始");
        RsRequest<ISL231199RsParam> request = new RsRequest<ISL231199RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://10.20.16.101:8080/api/sl/slInfo/slCodeShowName/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlCodeShowNameSearchServices();
        RsResponse<ISL231199RsBean> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<ISL231199RsBean>>(){});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus()) && null==rs){
            throw new BusinessException("<获取有效产品的供应商信息>接口调用失败");
        }
        List<ISL231199RsBean> list = new ArrayList<ISL231199RsBean>();
        if (rs.getResult() == null){
            logger.info("没有对应的产品的供应商信息");
        }else {
            list = rs.getResult().getIsl231199RsBeanList();
            logger.info("<获取有效产品的供应商信息>接口调用结束");
        }
        return list;
    }

    /**
     * 分页查询有效产品信息
     */
    public static ISL231199RsPageBean getLgcsSellerInfoSearch(ISL231199RsParam param) {
        logger.info("<分页查询有效产品信息>接口调用开始");
        RsRequest<ISL231199RsParam> request = new RsRequest<ISL231199RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://10.20.16.101:8080/api/sl/slInfo/slSellerProduct/search";
        String url = ConfigManager.getMskSellerService() + ConfigManager.getSellerSlSellerProductSearchServices();
        RsResponse<ISL231199RsPageBean> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<ISL231199RsPageBean>>(){});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw new BusinessException("<分页查询有效产品信息>接口调用失败");
        }
        ISL231199RsPageBean isl231199RsPageBean = new ISL231199RsPageBean();
        if (rs.getResult() == null) {
            logger.info("没有查询到有效产品信息");
        }else {
            isl231199RsPageBean = rs.getResult();
            logger.info("<分页查询有效产品信息>接口调用结束");
        }
        return isl231199RsPageBean;
    }

    /**
     *调用卖家接口获取生产商
     *@param slCodeList 供应商Code集合
     *@return 供应商编码为key，生产商名称为value
     */
    public static Map<String,String> getEPNameBySuppCode(List<String> slCodeList)  {
        logger.info("<获取生产商BySuppCode>接口调用开始");
        RsRequest<BasePageParam> requestParam = new RsRequest<BasePageParam>();
        //將供应商编码集合作为条件传入
        BasePageParam param = new BasePageParam();
        requestParam.setParam(param);
        requestParam.getParam().getFilterMap().put("list", slCodeList);
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        Map<String,String> epNameMap = new HashMap<String,String>();
        RsResponse<ISL231101RsResult> response = RestClientUtil.post(SystemServerManager.SellerServerManage.getQueryEPName(), requestParam, new TypeReference<RsResponse<ISL231101RsResult>>() {
        });
        if(!SystemConst.RsStatus.SUCCESS.equals(response.getStatus())&&null==response){
            throw new BusinessException("<获取生产商BySuppCode>接口调用失败");
        }
        if (response.getResult() != null || SystemConst.RsStatus.SUCCESS.equals(response.getStatus())){
            epNameMap = response.getResult().getEpNameMap();
            logger.info("<获取生产商BySuppCode>接口调用结束");
        }else {
            logger.info("没有获取到生产商信息");
        }

        return  epNameMap;
        }

    /**
     * 采供链扫码接口
     * @param isc1892I1Param
     * @return
     */
    public static ISC1892I1RsResult saveSscIntoBasic (ISC1892I1Param isc1892I1Param) {
        logger.info("<采供链扫码入库>接口调用开始");
        RsRequest<ISC1892I1Param> request = new RsRequest<ISC1892I1Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(isc1892I1Param);
        String url = SystemServerManager.SellerSupplyChainManage.getUpdatePreInto();
        RsResponse<ISC1892I1RsResult> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<ISC1892I1RsResult>>(){});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw new BusinessException("<采供链扫码入库>接口调用失败");
        }
        if (null == rs.getResult()) {
            logger.info("采供链扫码入库失败");
        }else {
            logger.info("<采供链扫码入库>接口调用结束");
        }
        return rs.getResult();
    }
    /**获取所有物流区信息
     * @return
     */
    public static List<LgcsAreaBean> getAllLgcsList(DistrictParam param){
        logger.info("<获取物流区信息>接口调用开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setParam(param);
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        String url = SystemServerManager.DistrictServerManage.getDistrictQueryLgcsArea();
        RsResponse<DistrictResult> rs = RestClientUtil.post(url,request,new TypeReference<RsResponse<DistrictResult>>(){});
        if(!SystemConst.RsStatus.SUCCESS.equals(rs.getStatus())&&null==rs){
            throw new BusinessException("<获取物流区信息>接口调用失败");
        }
        if(null==rs.getResult()){
            logger.info("<获取物流区信息>接口调用失败");
            return new ArrayList<LgcsAreaBean>();
        }else{
            logger.info("<获取物流区信息>接口调用成功");
        }
        return rs.getResult().getLgcsAreaList();
    }

    public static Boolean invModifyStock(ISO152405ParamBean param){
        logger.info("<修改销售库存>接口调用开始");
        RsRequest<ISO152405ParamBean> request = new RsRequest<ISO152405ParamBean>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //String url = "http://localhost:8804/api/so/saveStockOfSupplierList";
        String url = SystemServerManager.SoInventoryServerManager.getInboundInventory();
        RsResponse<StockResult> stockResult = RestClientUtil.post(url, request, new TypeReference<RsResponse<StockResult>>() {});
        if(!SystemConst.RsStatus.SUCCESS.equals(stockResult.getStatus())&&null==stockResult){
            throw new BusinessException("<修改销售库存>接口调用失败");
        }
        Boolean result = false;
        if (stockResult.getStatus().equals("S")){
            result = true;
            logger.info("<修改销售库存>接口调用结束");
        }else {
            logger.info("修改销售库存失败"+stockResult.getMessage());
        }
        return result;
    }


}
