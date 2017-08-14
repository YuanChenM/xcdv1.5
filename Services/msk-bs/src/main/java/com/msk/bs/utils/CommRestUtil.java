package com.msk.bs.utils;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBA2141107RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.IBR121307RsPageResult;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.bs.bean.*;
import com.msk.buyers.bean.*;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;
import com.msk.district.bean.*;
import com.msk.price.bean.*;
import com.msk.product.bean.*;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.ISLSellerRsResult;

/**
 * Created by yang_yang on 2016/6/20.
 */
public class CommRestUtil {

    private static Logger logger = LoggerFactory.getLogger(CommRestUtil.class);

    /**
     * 获取物流区信息
     *
     * @return districtList
     */
    public static List<LgcsAreaBean> getLogisticsAreaList(DistrictParam param) {
        logger.info("调取物流区接口信息开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> LogisticsAreaBeanList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        List<LgcsAreaBean> districtList = LogisticsAreaBeanList.getResult().getLgcsAreaList();
        logger.info("调取物流区接口信息结束");
        return districtList;
    }

    /**
     * 查询省份信息，并根据需要加载市区信息
     *
     * @return districtList
     */
    public static List<ProvinceBean> getProvinceList(DistrictParam param) {
        logger.info("查询省份信息，并根据需要加载市区信息开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryProvinceService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        List<ProvinceBean> districtList = lgcsAreaBeanList.getResult().getProvinceList();
        logger.info("查询省份信息，并根据需要加载市区信息结束");
        return districtList;
    }

    /**
     * 根据买家ID获取买家相关信息
     *
     * @return
     */
    public static BuyerRelationResult searchBuyerInfo(BuyerRelationParam param) {
        logger.info("获取买家相关接口信息开始");
        RsRequest<BuyerRelationParam> request = new RsRequest<BuyerRelationParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersInfoRelationInfoServices();
        RsResponse<BuyerRelationResult> buyerResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BuyerRelationResult>>() {
                }, SerializerFeature.PrettyFormat);
        BuyerRelationResult result = buyerResult.getResult();
        logger.info("获取买家相关接口信息结束");
        return result;
    }

    /**
     * 查询行政区划信息
     *
     * @return
     */
    public static List<DistrictBean> getRegion(DistrictParam param) {
        logger.info("查询行政区划接口信息开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryRegionService();
        RsResponse<DistrictResult> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        List<DistrictBean> list = new ArrayList<DistrictBean>();
        if (null != response.getResult()) {
            DistrictResult districtResult = response.getResult();
            list = districtResult.getDistrictList();
        }
        logger.info("查询行政区划接口信息结束");
        return list;
    }

    /**
     * 查询公共买家池买家信息
     *
     * @return
     */
    public static List<IBY121223Result> searchBuyer(IBY121223Param param) {
        logger.info("查询公共买家池买家信息开始");
        RsRequest<IBY121223Param> request = new RsRequest<IBY121223Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersSearchBuyerServices();
        RsResponse<IBY121223Result[]> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBY121223Result[]>>() {
                });
        List<IBY121223Result> list = new ArrayList<IBY121223Result>();
        if (null != response.getResult() && response.getResult().length > 0) {
            list = Arrays.asList(response.getResult());
        }
        logger.info("查询公共买家池买家信息结束");
        return list;
    }


    /**
     * 最新版查询公共买家池信息
     *
     * @param param
     * @return
     */
    public static PageResult<publicBuyerPoolRsBean> searchPubliBuyerPool(publicBuyerPoolRsParam param) {
        logger.info("查询公共买家池买家信息开始");
        RsRequest<publicBuyerPoolRsParam> request = new RsRequest<publicBuyerPoolRsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        /*String url ="http://10.20.16.125:8380/msk-br-api/api/br/publiBuyerPoolInformation/search";*/
        String url = SystemServerManager.BuyersReportServerManager.getSearchPubliBuyerPoolInformation();
        RsResponse<publicBuyerPoolRsPageResult> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<publicBuyerPoolRsPageResult>>() {
                });
        List<publicBuyerPoolRsBean> publicBuyerPoolList = new ArrayList<>();
        PageResult<publicBuyerPoolRsBean> pageResult = new PageResult<publicBuyerPoolRsBean>();
        if (null != response && response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            publicBuyerPoolList = response.getResult().getBuyerList();
            pageResult.setRecordsTotal(response.getResult().getTotalCount());
            pageResult.setData(publicBuyerPoolList);
        }
        logger.info("查询公共买家池买家信息结束");
        return pageResult;
    }

    /**ADD from 匹配伽然调用公共买家信息   zhukai start**/
    /**
     * 给伽然处调用的公共买家
     * @param param
     * @return
     */
    public static IBS2101138RsResult searchBuyerPool(publicBuyerPoolRsParam param) {
        logger.info("查询公共买家池买家信息开始");
        RsRequest<publicBuyerPoolRsParam> request = new RsRequest<publicBuyerPoolRsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        /*String url ="http://10.20.16.125:8180/msk-br-api/api/br/publiBuyerPoolInformation/search";*/
        String url = SystemServerManager.BuyersReportServerManager.getSearchPubliBuyerPoolInformation();
        RsResponse<IBS2101138RsResult> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101138RsResult>>() {
                });
        IBS2101138RsResult rsResult = new IBS2101138RsResult();
        if (null != response && response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            rsResult = response.getResult();
        }
        logger.info("查询公共买家池买家信息结束");
        return rsResult;
    }
    /**ADD from 匹配伽然调用公共买家信息   zhukai end**/


    //查询买家列表
    public static PageResult<BS2102126Bean>  searchBuyers(BS2102126Param param){
        logger.info("查询买家列表开始");
        RsRequest<BS2102126Param> request = new RsRequest<BS2102126Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url = "http://10.20.16.146:8080/msk-buyers/api/by/buyers/query";
        String url = SystemServerManager.BuyersServerManage.getQueryBuyers();
        List<BS2102126Bean> buyerList = new ArrayList<>();
        PageResult<BS2102126Bean> pageResult = new PageResult<BS2102126Bean>();
        RsResponse<BS2102126Result> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BS2102126Result>>() {
                });

        if (null != response && response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            buyerList = response.getResult().getBuyerList();
            pageResult.setRecordsTotal(response.getResult().getTotalCount());
            pageResult.setData(buyerList);
        }
        logger.info("查询买家列表结束");
        return pageResult;
    }


    /**
     * 查询买手店管家会员信息
     *
     * @return
     */
    public static List<IBY121223Result> searchBuyerShop(IBY121224Param param) {
        logger.info("查询买手店管家会员信息开始");
        RsRequest<IBY121224Param> request = new RsRequest<IBY121224Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersSearchBuyerShopServices();
        RsResponse<IBY121224Result> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBY121224Result>>() {
                });
        List<IBY121223Result> list = new ArrayList<IBY121223Result>();
        if (null != response.getResult()) {
            list = response.getResult().getBuyerShopList();
        }
        logger.info("查询买手店管家会员信息结束");
        return list;
    }

    /**modify 改善 #3519 查询买手冻品管家的买家信息接口，查询条件不起作用 zhukai start**/
    /**
     * 查询买手冻品管家的买家信息
     * @return
     */
    public static IBS2101107RsResult searchExclusive(IBY121225Param param) {
        logger.info("查询买手冻品管家的买家信息开始");
        RsRequest<IBY121225Param> request = new RsRequest<IBY121225Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersSearchBuyerExclusiveServices();
        //String url = "http://10.20.16.125:8480/msk-buyers/api/by/buyerInfo/searchExclusive";
        RsResponse<IBS2101107RsResult> response =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101107RsResult>>() {
                },SerializerFeature.PrettyFormat);
      /*  List<IBS2101107Bean> list = new ArrayList<IBS2101107Bean>();
        if (null != response.getResult()) {
            list = response.getResult().getSlBuyerList();
        }*/
        logger.info("查询买手冻品管家的买家信息结束");
        return response.getResult();
    }
    /**modify 改善 #3519 查询买手冻品管家的买家信息接口，查询条件不起作用 zhukai end**/

    /**
     * 获取产品一级分类列表
     *
     * @return
     */
    public static List<PdClasses> searchPdClass() {
        logger.info("查询产品一级分类列表开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductClassesInfo();
        RsResponse<PdClasses[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdClasses[]>>() {
        });
        List<PdClasses> list = new ArrayList<PdClasses>();
        if (null != response.getResult()) {
            PdClasses[] pdClasses = response.getResult();
            for (int i = 0; i < pdClasses.length; i++) {
                list.add(pdClasses[i]);
            }
        }
        logger.info("查询产品一级分类列表结束");
        return list;
    }

    /**
     * 获取产品二级分类列表
     *
     * @return
     */
    public static List<PdMachining> searchPdMachining(String classesCode) {
        logger.info("查询产品二级分类列表开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        param.getFilterMap().put("classesCode", classesCode);
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductMachiningInfo();
        RsResponse<PdMachining[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdMachining[]>>() {
        });
        List<PdMachining> list = new ArrayList<PdMachining>();
        if (null != response.getResult()) {
            PdMachining[] pdMachining = response.getResult();
            for (int i = 0; i < pdMachining.length; i++) {
                list.add(pdMachining[i]);
            }
        }
        logger.info("查询产品二级分类列表结束");
        return list;
    }

    /**
     * 获取产品品种列表
     *
     * @return
     */
    public static List<PdBreed> searchPdBreed(String classesCode, String machiningCode, String breedCode) {
        logger.info("查询产品品种列表开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        param.getFilterMap().put("classesCode", classesCode);
        param.getFilterMap().put("machiningCode", machiningCode);
        param.getFilterMap().put("breedCode", breedCode);
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductBreedInfo();
        RsResponse<PdBreed[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdBreed[]>>() {
        });
        List<PdBreed> list = new ArrayList<PdBreed>();
        if (null != response.getResult()) {
            PdBreed[] pdBreeds = response.getResult();
            for (int i = 0; i < pdBreeds.length; i++) {
                list.add(pdBreeds[i]);
            }
        }
        logger.info("查询产品品种列表结束");
        return list;
    }

    /**
     * 根据批量产品编码查询产品包装规格信息及产品信息
     *
     * @return
     */
    public static List<PDInfoResult> searchPdInfoList(PDInfoParam pdInfoParam) {
        logger.info("查询产品信息开始");
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdInfosSearchService();
        RsResponse<ProductBeanResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        List<PDInfoResult> pdInfoResultList = new ArrayList<>();
        if (null != response.getResult()) {
            pdInfoResultList = response.getResult().getResult();
        }
        logger.info("查询产品信息结束");
        return pdInfoResultList;
    }

    /**
     * 获取产品特征列表
     *
     * @return
     */
    public static List<PdFeature> searchPdFeature(PdFeature pdFeature) {
        logger.info("查询产品特征列表开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        param.getFilterMap().put("classesCode", pdFeature.getClassesCode());
        param.getFilterMap().put("machiningCode", pdFeature.getMachiningCode());
        param.getFilterMap().put("breedCode", pdFeature.getBreedCode());
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductFeatureInfo();
        RsResponse<PdFeature[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdFeature[]>>() {
        });
        List<PdFeature> list = new ArrayList<PdFeature>();
        if (null != response.getResult()) {
            PdFeature[] pdPdFeatures = response.getResult();
            for (int i = 0; i < pdPdFeatures.length; i++) {
                list.add(pdPdFeatures[i]);
            }
        }
        logger.info("查询产品特征列表结束");
        return list;
    }

    /**
     * 获取产品净重列表
     *
     * @return
     */
    public static List<PdWeight> searchPdWeight(PdWeight pdWeight) {
        logger.info("查询产品净重列表开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        param.getFilterMap().put("classesCode", pdWeight.getClassesCode());
        param.getFilterMap().put("machiningCode", pdWeight.getMachiningCode());
        param.getFilterMap().put("breedCode", pdWeight.getBreedCode());
        param.getFilterMap().put("featureCode", pdWeight.getFeatureCode());
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductWeightInfo();
        RsResponse<PdWeight[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdWeight[]>>() {
        });
        List<PdWeight> list = new ArrayList<PdWeight>();
        if (null != response.getResult()) {
            PdWeight[] PdWeights = response.getResult();
            for (int i = 0; i < PdWeights.length; i++) {
                list.add(PdWeights[i]);
            }
        }
        logger.info("查询产品净重列表结束");
        return list;
    }

    /**
     * 获取产品等级列表
     *
     * @return
     */
    public static List<PdGrade> searchPdGrade() {
        logger.info("查询产品等级列表开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        BaseParam param = new BaseParam();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.PdServerManager.getFindListGrades();
        RsResponse<PdGrade[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdGrade[]>>() {
        });
        List<PdGrade> list = new ArrayList<PdGrade>();
        if (null != response.getResult()) {
            PdGrade[] PdGrades = response.getResult();
            for (int i = 0; i < PdGrades.length; i++) {
                list.add(PdGrades[i]);
            }
        }
        logger.info("查询产品等级列表结束");
        return list;
    }

    /**
     * 获取查询价盘接口
     *
     * @return
     */
    public static List<ISP171183Bean> getGetPriceCycle(ISP171183Param param) {
        logger.info("查询产品价盘列表开始");
        RsRequest<ISP171183Param> request = new RsRequest<ISP171183Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SnkPriceServerManage.getGetPriceCycle();
        RsResponse<ISP171183Bean[]> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISP171183Bean[]>>() {
        });
        List<ISP171183Bean> list = new ArrayList<ISP171183Bean>();
        if (null != response.getResult()) {
            ISP171183Bean[] PdPrice = response.getResult();
            for (int i = 0; i < PdPrice.length; i++) {
                list.add(PdPrice[i]);
            }
        }
        logger.info("查询产品价盘列表结束");
        return list;
    }

    /**
     * 获取价盘通道接口
     *
     * @return
     */
    public static List<ISP171184Bean> getPdPriceWay(String pdCode) {
        logger.info("查询神农客价盘通道开始");
        RsRequest<ISP171184Param> request = new RsRequest<ISP171184Param>();
        ISP171184Param param = new ISP171184Param();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        param.setPdCode(pdCode);
        request.setParam(param);
        String url = SystemServerManager.SnkPriceServerManage.getGetPriceWay();
        RsResponse<ISP171184Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISP171184Result>>() {
        });
        List<ISP171184Bean> list = new ArrayList<ISP171184Bean>();
        if (null != response.getResult()) {
            ISP171184Result result = response.getResult();
            list = result.getSearchList();
        }
        logger.info("查询神农客价盘通道结束");
        return list;
    }

    /**
     * 查询物流区信息，并根据需要加载市区信息
     * 根据lgcsAreaCode查询对应的地区信息
     *
     * @param param
     * @return
     */
    public static List<CityBean> getCityList(DistrictParam param) {
        logger.info("调取物流区接口获取地区信息开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (lgcsAreaBeanList != null && !CollectionUtils.isEmpty(lgcsAreaBeanList.getResult().getLgcsAreaList())) {
            return lgcsAreaBeanList.getResult().getLgcsAreaList().get(0).getCityList();
        }
        logger.info("调取物流区接口获取地区信息结束");
        return new ArrayList<>();
    }


    /**
     * 根据省编码或物流区编码查询市区信息，并根据需要加载区县信息
     * 根据省份code获取对应的city
     *
     * @param param
     * @return
     */
    public static List<CityBean> getProvinceCityList(DistrictParam param) {
        logger.info("调取省份接口获取地区信息开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryCityService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (null != lgcsAreaBeanList.getResult()) {
            return lgcsAreaBeanList.getResult().getCityList();
        }
        logger.info("调取省份接口获取地区信息结束");
        return new ArrayList<>();
    }

    /**
     * 根据cityCode获取区县
     *
     * @param param
     * @return
     */
    public static List<DistrictBean> getDistrictList(DistrictParam param) {
        logger.info("调取物流区接口获取区县信息开始");
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryDistrictService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });

        if (null != lgcsAreaBeanList.getResult()) {
            return lgcsAreaBeanList.getResult().getDistrictList();
        }
        logger.info("调取物流区接口获取区县信息结束");
        return new ArrayList<>();
    }

    /**
     * 获取冻品管家组信息
     *
     * @param param
     * @return
     */
    public static RsResponse<BR121305Result> queryHkGroupInfo(BR121305Param param) {
        logger.info("获取冻品管家组信息信息开始");
        RsRequest<BR121305Param> request = new RsRequest<BR121305Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = SystemServerManager.BuyersReportServerManager.getQueryHkGroupInfo();
        RsResponse<BR121305Result> rsResponse =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BR121305Result>>() {
                });

        if (rsResponse != null) {
            return rsResponse;
        }

        logger.info("调取物流区接口获取地区信息结束");
        return new RsResponse<BR121305Result>();
    }

    /**
     * 获取冻品管家组信息
     *
     * @param params
     * @return
     */
    public static Integer updatePdGroupName(List<BR121305Param> params) {
        logger.info("编辑冻品管家组名称开始");
        RsRequest<List<BR121305Param>> request = new RsRequest<List<BR121305Param>>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(params);

        String url = SystemServerManager.BuyersReportServerManager.getUpdateHkGroupName();
        RsResponse<Integer> rsResponse =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });

        if (rsResponse != null && rsResponse.getResult() != null) {
            return rsResponse.getResult();
        }
        logger.info("编辑冻品管家组名称开始");
        return 0;
    }

    /**
     * 新增冻品管家组
     * @param param
     * @return
     */
    public static Integer addHkGroup(BS2102123Param param){
        logger.info("新增冻品管家组开始");
        RsRequest<BS2102123Param> request = new RsRequest<BS2102123Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = SystemServerManager.BuyersReportServerManager.getAddHkGroup();
        RsResponse<Integer> rsResponse =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });
        return  rsResponse.getResult();
    }

    /**
     * 获取买手类型
     * @return
     */
    public static  List<BS2102124Bean> getBuyerType(){
        logger.info("查询买家类型开始");
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(new BaseParam());
        String url = SystemServerManager.BuyersServerManage.getFindBuyerTypesList();
        RsResponse<BS2102124Bean[]> rsResponse =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BS2102124Bean[]>>() {
                });
        List<BS2102124Bean> list =new ArrayList<BS2102124Bean>();
        //去重的list
        List<BS2102124Bean> qclist =new ArrayList<BS2102124Bean>();
        if(rsResponse != null && rsResponse.getResult() != null ){
            list = Arrays.asList(rsResponse.getResult());
            for(int i= 0;i<list.size()-1;i++){
                for(int j= 0;j<list.size();j++){
                    if(i != j){
                        if(list.get(i).getBuyerTypeName().equals(list.get(j).getBuyerTypeName())){
                            list.get(j).setBuyerTypeName("");
                        }
                    }
                }
            }
        }
        Iterator<BS2102124Bean> it = list.iterator();
        while (it.hasNext()){
            BS2102124Bean bean= it.next();
            if(StringUtils.hasLength(bean.getBuyerTypeName())){
                qclist.add(bean);
            }
        }
        return  qclist;
    }

    /**
     * 查询冻品管组的冻品管家信息
     *
     * @param param
     * @return
     */
    public static List<IBS121306RsBean> getHkListInHkGroup(BS2102105Param param) {
        logger.info("调取冻品管家组下的冻品管家信息开始");
        RsRequest<BS2102105Param> request = new RsRequest<BS2102105Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getQueryHkListInHkGroup();//.getDeleteSettingDate();// ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<IBS121306RsPageResult> rsResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS121306RsPageResult>>() {
                });

        if (null != rsResult.getResult() && !CollectionUtils.isEmpty(rsResult.getResult().getIbr121306RsBeanList())) {
            return rsResult.getResult().getIbr121306RsBeanList();
        }
        logger.info("调取冻品管家组下的冻品管家信息结束");
        return new ArrayList<>();
    }


    /**
     * 删除冻品管家信息
     *
     * @param param
     * @return
     */
    public static int deleteHouse(BS2102105Param param) {
        logger.info("删除冻品管家信息开始");
        RsRequest<BS2102105Param> request = new RsRequest<BS2102105Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getDeleteHkListInHkGroup();
        RsResponse<Integer> rsResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });

        if (rsResult != null)
            return rsResult.getResult();
        logger.info("删除冻品管家信息结束");
        return 0;
    }
/*
    *//**
     * 保存买手信息
     *
     * @return
     *//*
    public static RsResponse<ISLSellerRsResult> saveBuyerInfo(ISLSellerRsParam param) {
        RsRequest<ISLSellerRsParam> request = new RsRequest<ISLSellerRsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getDealSlSellerAccount();
        RsResponse<ISLSellerRsResult> count = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISLSellerRsResult>>() {
        });
        return count;
    }*/

    /**
     * 批量添加冻品管家信息
     *
     * @param param
     * @return
     */
    public static int getUpdateHkGroupInfos(BS2102105Param param) {
        logger.info("批量添加冻品管家信息开始");
        RsRequest<BS2102105Param> request = new RsRequest<BS2102105Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getUpdateHkGroupInfos();// ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<Integer> rsResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
                });

        if (rsResult.getStatus().equals(SystemConst.RsStatus.SUCCESS))
            return rsResult.getResult();
        logger.info("批量添加冻品管家信息结束");
        return 0;
    }

    /**
     * 查询产品一级分类或者根据产品一级分类编码查询产品二级分类信息
     *
     * @param param
     * @return
     */
    public static IBR121307RsPageResult getMachiningCodeOrClassesCode(IBR121307RsParam param) {
        logger.info("查询产品一级分类或者根据产品一级分类编码查询产品二级分类信息开始");
        RsRequest<IBR121307RsParam> request = new RsRequest<IBR121307RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
       /* String url = "http://localhost:8099/msk-br-api/api/br/brMPdClasses/findMachiningCodeU";*/
        String url = SystemServerManager.BuyersReportServerManager.getFindMachiningCodeU();
        RsResponse<IBR121307RsPageResult> resultRsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBR121307RsPageResult>>() {
        });
        if (resultRsResponse != null && resultRsResponse.getResult() != null) {
            return resultRsResponse.getResult();
        }
        return new IBR121307RsPageResult();
    }

    /**
     * 导出冻品管家组管控表查询冻品管组的冻品管家信息
     *
     * @param param
     * @return
     */
    public static List<IBS121306RsBean> getHkGroupForHkInfo(BR121305Param param) {
        logger.info("查询冻品管家组下的冻品管家信息开始");
        RsRequest<BR121305Param> request = new RsRequest<BR121305Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BuyersReportServerManager.getQueryHkGroupForHkInfo();//.getDeleteSettingDate();// ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<IBS121306RsPageResult> rsResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS121306RsPageResult>>() {
                });

        if (null != rsResult.getResult() && !CollectionUtils.isEmpty(rsResult.getResult().getIbr121306RsBeanList())) {
            return rsResult.getResult().getIbr121306RsBeanList();
        }
        logger.info("查询冻品管家组下的冻品管家信息结束");
        return new ArrayList<>();
    }


    /**
     * 根据买家ID查询对应的买家池
     *
     * @return
     */
    public static PageResult<BS2101200RsBean> getBuyerPoolListByBuyerId(BS2101200RsParam rsParam) {
        logger.info("查询买家ID查询对应的买家池开始");
        RsRequest<BS2101200RsParam> request = new RsRequest<BS2101200RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(rsParam);
        String url = SystemServerManager.BuyersReportServerManager.getQueryBrBuyerPoolByBuyerId();
        // String url ="http://10.20.16.60:8080/api/br/query/brBuyerPoolListByBuyerId";
        RsResponse<BS2101200Result> rsResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BS2101200Result>>() {
                });
        List<BS2101200RsBean> bs2101104RsBeanList = new ArrayList<>();
        if (rsResult != null) {
            bs2101104RsBeanList = rsResult.getResult().getBrBuyerPoolList();
        }
        PageResult<BS2101200RsBean> pageResult = new PageResult<BS2101200RsBean>();
        pageResult.setRecordsTotal(rsResult.getResult().getTotalCount());
        pageResult.setData(bs2101104RsBeanList);
        logger.info("查询买家ID查询对应的买家池结束");
        return pageResult;
    }


    /**
     * 根据产品列表信息获取价盘通道和价格列表
     *
     * @return
     */
    public static List<ISP171185Bean> getPriceWayListByPdCode(ISP171185Param rsParam) {
        logger.info("根据产品列表信息获取价盘通道和价格列表开始");
        RsRequest<ISP171185Param> request = new RsRequest<ISP171185Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(rsParam);
        String url = SystemServerManager.SnkPriceServerManage.getGetPriceWayList();
        // String url = "http://10.20.16.49:8181/msk-snk-price/api/pd/getPriceWayList";
        RsResponse<ISP171185Result> rsResult =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<ISP171185Result>>() {
                });
        List<ISP171185Bean> isp171185BeanList = new ArrayList<>();
        if (null != rsResult && null != rsResult.getResult() && rsResult.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            isp171185BeanList = rsResult.getResult().getProductList();
        }
        logger.info("根据产品列表信息获取价盘通道和价格列表结束");
        return isp171185BeanList;
    }

    /**
     * 获取买家所有相关的信息
     *
     * @param buyerRelationParam
     * @return
     */
    public static RsResponse<BuyerRelationResult> getBuyersInfoRelationInfo(BuyerRelationParam buyerRelationParam) {
        RsRequest<BuyerRelationParam> request = new RsRequest<BuyerRelationParam>();
        request.setAuth("MSK00001");
        request.setLoginId(buyerRelationParam.getActId());
        request.setSiteCode("1");
        request.setParam(buyerRelationParam);
        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersInfoRelationInfoServices();
        //String url ="http://10.20.16.49:8887/api/by/buyerInfo/relationInfo";
        RsResponse<BuyerRelationResult> buyerRelation =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BuyerRelationResult>>() {
                });
        return buyerRelation;
    }
//Add for Bug#2379 at 2016/09/06 by ni_shaotang Start
    /**
     * 获取物流区产品数据
     *
     * @return List
     * @throws Exception
     */
    public static List<PDInfoResult> getPDSup(PDInfoParam pdInfoParam){

        RsRequest<PDInfoParam> requestParam = new RsRequest<PDInfoParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        pdInfoParam.setGradeFlag("1");
        requestParam.setParam(pdInfoParam);
        //url

        String url = ConfigManager.getMskProductService() + ConfigManager.getProductGetPdSuppService();
        //请求接口
        RsResponse<ProductPageResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ProductPageResult>>() {
        });
        List<PDInfoResult> result = new ArrayList<PDInfoResult>();
        if (null != response.getResult() && "S".equals(response.getStatus())) {
            ProductPageResult pdResult = response.getResult();
            if (null != pdResult) {
                result = pdResult.getPdInfo();
            }
        }
        return result;
    }
    //Add for Bug#2379 at 2016/09/06 by ni_shaotang End

    //Add for 时间转义 at 2016/09/20 by ni_shaotang Start

    /**
     * 对开始时间和结束时间转义成字符显示
     * @param startDate
     * @param endDate
     * @return
     */
    public static String setDateToString(Date startDate,Date endDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer str = new StringBuffer();
        if(null != startDate){
            String start =sdf.format(startDate);
            str.append(start);
        }
        if(null != endDate){
            String end =sdf.format(endDate);
            str.append("至"+end);
        }
        if(null == startDate && null == endDate){
            str.append("");
        }
        return str.toString();
    }
    //Add for 时间转义 at 2016/09/20 by ni_shaotang End

    //Add for 添加库存接口 at 2016/09/20 by ni_shaotang start
    /**
     * 最新版查询公共买家池信息
     *
     * @param param
     * @return
     */
    public static RsResponse<IBA2141107RsResult> getFindProductStock(IPD141144RsParam param) {
        logger.info("查询产品库存开始");
        RsRequest<IPD141144RsParam> request  = new RsRequest<IPD141144RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoOrderServerManage.getFindPdStock();
        RsResponse<IBA2141107RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBA2141107RsResult>>() {});
        logger.info("查询产品库存结束");
        return response;
    }
    //Add for 时间转义 at 2016/09/20 by ni_shaotang start
    public static RsResponse<IBA2141122RsResult> findBssList(IBA2141122RsParam param){
        logger.info("查询买手销售订单开始");
        RsRequest<IBA2141122RsParam> request  = new RsRequest<IBA2141122RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoOrderApiServerManager.getQueryBssSdoList();
        RsResponse<IBA2141122RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBA2141122RsResult>>() {});
        logger.info("查询买手销售订单结束");
        return response;
    }
    //Add for 时间转义 at 2016/09/20 by ni_shaotang start
    public static RsResponse<IBA2141123RsResult> findBssDetail(IBA2141123RsParam param){
        logger.info("查询买手销售订单开始");
        RsRequest<IBA2141123RsParam> request  = new RsRequest<IBA2141123RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoOrderApiServerManager.getQueryBssSdoDetail();
        RsResponse<IBA2141123RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBA2141123RsResult>>() {});
        logger.info("查询买手销售订单结束");
        return response;
    }

    public static RsResponse<IPD141144RsResult> queryBssgSdoList(IPD141144RsParam param) {
        logger.info("查询买手囤货信息开始");
        RsRequest<IPD141144RsParam> request  = new RsRequest<IPD141144RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.SoOrderApiServerManager.getQueryBssgSdoList();
        RsResponse<IPD141144RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141144RsResult>>() {});
        logger.info("买手囤货信息结束");
        return response;
    }

    /**
     * 建立管家和买家关系接口
     * @param param
     * @return
     */
    public static RsResponse<IBS2101140RsResult> addBuyerHkRelationship(IBS2101140RsParam param){
        logger.info("建立管家和买家关系接口开始");
        RsRequest<IBS2101140RsParam> request  = new RsRequest<IBS2101140RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url ="http://localhost:8080/msk-br-api/api/br/bindingRelationship/update";
        String url = SystemServerManager.BuyersReportServerManager.getUpdateBindingRelationship();
        RsResponse<IBS2101140RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101140RsResult>>() {});
        logger.info("返回状态:"+response.getStatus()+"返回消息:"+response.getMessage());
        if(response.getResult() != null && !CollectionUtils.isEmpty(response.getResult().getBuyerIdList())){
            for(IBS2101140RsBean bean : response.getResult().getBuyerIdList()){
                logger.info("失败的buyerId："+bean.getBuyerId());
                if(!StringUtil.isNullOrEmpty(bean.getResult())){
                    switch (bean.getResult()){
                        case "1":
                            logger.info("买家不存在");
                            break;
                        case "2":
                            logger.info("该买家没有归属的买家池");
                            break;
                        case "3":
                            logger.info("更新买家买家池关系表失败");
                            break;
                        default:
                            logger.info("未知原因代码："+bean.getResult());
                    }
                }else {
                    logger.info("未知原因");
                }
            }
        }
        logger.info("建立管家和买家关系接口结束");
        return response;
    }


    /**
     * 解除管家和买家关系接口
     * @param param
     * @return
     */
    public static RsResponse<IBS2101140RsResult> updateBuyerHkRelationship(IBS2101140RsParam param){
        logger.info("解除管家和买家关系接口开始");
        RsRequest<IBS2101140RsParam> request  = new RsRequest<IBS2101140RsParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
//        String url ="http://localhost:8080/msk-br-api/api/br/unBindingRelationship/update";
        String url = SystemServerManager.BuyersReportServerManager.getUpdateUnBindingRelationship();
        RsResponse<IBS2101140RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101140RsResult>>() {});
        logger.info("返回状态:"+response.getStatus()+"返回消息:"+response.getMessage());
        if(response.getResult() != null && !CollectionUtils.isEmpty(response.getResult().getBuyerIdList())){
            for(IBS2101140RsBean bean : response.getResult().getBuyerIdList()){
                logger.info("失败的buyerId："+bean.getBuyerId());
            }
        }
        logger.info("解除管家和买家关系接口结束");
        return response;
    }
}