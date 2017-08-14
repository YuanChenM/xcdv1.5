package com.msk.seller.utils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.product.bean.*;
import com.msk.seller.bean.SL24112801Param;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhang_chi on 2016/6/14.
 */
public class SLControllerUtil {

    /**
     * 查询省份信息
     * Created by xia_xiaojie on 2016/6/15.
     */
    public static DistrictResult getProvinces(DistrictParam param) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryProvinceService();
//        String url = "http://localhost:8083/api/district/query/province";
        RsResponse<DistrictResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new DistrictResult();
        } else {
            return rsResponse.getResult();
        }
    }

    /**
     * 查询城市信息
     * Created by xia_xiaojie on 2016/6/15.
     */
    public static DistrictResult getCities(DistrictParam param) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryCityService();
//        String url = "http://localhost:8083/api/district/query/city";
        RsResponse<DistrictResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new DistrictResult();
        } else {
            return rsResponse.getResult();
        }
    }

    /**
     * 查询县区信息
     * Created by xia_xiaojie on 2016/6/15.
     */
    public static DistrictResult getDistricts(DistrictParam param) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskDistrictService() + ConfigManager.getDistrictQueryDistrictService();
//        String url = "http://localhost:8083/api/district/query/district";
        RsResponse<DistrictResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new DistrictResult();
        } else {
            return rsResponse.getResult();
        }
    }

    /**
     * 查询所有的物流区信息、指定物流区中的城市信息
     *
     * @return
     */
    public static RsResponse<DistrictResult> getLogisticsAreaList(DistrictParam districtParam) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId(districtParam.getActId());
        request.setSiteCode("1");
        request.setParam(districtParam);
        // "http://localhost:8080/msk-district/api/district/query/lgcsArea"
        String url = SystemServerManager.DistrictServerManage.getDistrictQueryLgcsArea();
        RsResponse<DistrictResult> logisticsAreaList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        if ("F".equals(logisticsAreaList.getStatus())) {
            throw new BusinessException("物流区:" + logisticsAreaList.getMessage());
        }
        return logisticsAreaList;
    }
    /**
     * 级联查询一级分类、二级分类、品种、特征、净重的代码和名称
     * Created by xia_xiaojie on 2016/6/17.
     */
    public static ProductBeanResult getProductBatchNames(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductBatchNameServices();
//        String url = "http://localhost:8082/api/pdBatchName";
        RsResponse<ProductBeanResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new ProductBeanResult();
        } else {
            return rsResponse.getResult();
        }
    }

    /**
     * 根据产品卖家/一级分类/二级分类/品种/特征/净重的代码或名称，批量查询产品包装信息。
     * Created by xia_xiaojie on 2016/6/20.
     */
    public static ProductPageResult getProviderPackages(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductProviderPackageServices();
//        String url = "http://localhost:8082/api/product/getProviderPackageInfo";
        RsResponse<ProductPageResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductPageResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new ProductPageResult();
        } else {
            return rsResponse.getResult();
        }
    }

    /**
     * 查询产品标准ID或代码
     * Created by xia_xiaojie on 2016/6/23.
     */
    public static List<IPD141105RsResult> getPdStdIdsOrCodes(IPD141105RsParam param) {
        RsRequest<IPD141105RsParam> request = new RsRequest<IPD141105RsParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductAllPdCodeSearchServices();
//        String url = "http://localhost:8082/api/product/findAllPdCode";
        RsResponse<IPD141105RsResult[]> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141105RsResult[]>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new ArrayList<IPD141105RsResult>();
        } else {
            IPD141105RsResult[] results = rsResponse.getResult();
            return (null == results) ? new ArrayList<IPD141105RsResult>() : Arrays.asList(results);
        }
    }

    /**
     * 查询产品包装
     * Created by xia_xiaojie on 2016/6/24.
     */
    public static ProductBeanResult getProductPackages(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdPackageInfoSearchServices();
        //   String url = "http://localhost:8080/msk-product/api/findProductPackage";
        RsResponse<ProductBeanResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            return new ProductBeanResult();
        } else {
            return rsResponse.getResult();
        }
    }

    /**
     * 获取分类信息
     * Created by zhang_chi on 2016/6/15.
     *
     * @param parentCode,treeLevel
     * @return
     */
    public static List<PDInfoResult> findClassTree(String parentCode, String treeLevel) {
        RsRequest<PDInfoParam> requestParam = new RsRequest<PDInfoParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setParentCode(parentCode);
        pdInfoParam.setTreeLevel(treeLevel);
        requestParam.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdClassesTreeInfoSearchServices();
//        String url = "http://localhost:8082/api/getPdClassesTreeInfo";
        RsResponse<ProductBeanResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(response.getStatus())) {
            return new ArrayList<PDInfoResult>();
        }else {
            ProductBeanResult productBeanResult = response.getResult();
            List<PDInfoResult> result = productBeanResult.getResult();
            if (!CollectionUtils.isEmpty(result)) {
                return result;
            } else {
                return new ArrayList<PDInfoResult>();
            }
        }
    }

    /**
     * 获取原料描述信息
     * Created by zhang_chi on 2016/6/16.
     *
     * @param classCode,delFlg
     * @return
     */
    public static PdClassestreeMat findClassData(String classCode, String delFlg) {
        RsRequest<PDInfoParam> requestParam = new RsRequest<PDInfoParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setClassesTreeCode(classCode);
        pdInfoParam.setDelFlg(delFlg);
        pdInfoParam.setType(NumberConst.IntDef.INT_TWO);
        requestParam.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductStdServices();
        // String url = "http://localhost:8080/msk-product/api/pdProductStd";
        RsResponse<ProductStdResult> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ProductStdResult>>() {
        });
        ProductStdResult productStdResult = response.getResult();
        if (null != productStdResult) {
            List<PdClassestreeMat> result = productStdResult.getMatStdList();
            if (!CollectionUtils.isEmpty(result)) {
                return result.get(0);
            } else {
                return new PdClassestreeMat();
            }
        } else {
            return new PdClassestreeMat();
        }
    }

    /**
     * 获取原料描述信息
     * Created by zhang_chi on 2016/6/24.
     *
     * @param pdInfoParam
     * @return
     */
    public static List<SL24112801Param> findPdClassestreeMat(PDInfoParam pdInfoParam) {
        RsRequest<PDInfoParam> requestParam = new RsRequest<PDInfoParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        requestParam.setParam(pdInfoParam);
        //String url = "http://localhost:8080/msk-product/api/product/findProviderList";
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductProviderListServices();
        RsResponse<SL24112801Param[]> response = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<SL24112801Param[]>>() {
        });
        if (null != response.getResult()) {
            List<SL24112801Param> list = Arrays.asList(response.getResult());
            return list;
        } else {
            return new ArrayList<SL24112801Param>();
        }
    }
}
