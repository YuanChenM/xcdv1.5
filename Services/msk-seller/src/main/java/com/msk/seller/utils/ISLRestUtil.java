package com.msk.seller.utils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.IBS2101115Param;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.*;

import com.msk.product.bean.*;
import com.msk.seller.bean.SL241130Param;

import java.util.List;

/**
 * Created by on 2016/6/14.
 */
public class ISLRestUtil {

    /**
     * 产品等级&卫生质量标准（八张档案卡）
     * Created by zhang_chi on 2016/6/20.
     */
    public static RsResponse<ProductStdResult> getPdProductStd(List<StdItem> stdItemList, String standardId,
                                                               Integer stdType) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setStdParams(stdItemList);
        pdInfoParam.setType(stdType);
        pdInfoParam.setStandardId(standardId);
        request.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductStdServices();
        RsResponse<ProductStdResult> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<ProductStdResult>>() {
                });
        if (null == response.getMessage() || response.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            response = new RsResponse<ProductStdResult>();
        }
        return response;
    }

    /**
     * 根据产品分类code查询产品分类原料
     * Created by liu_yan2 on 2016/6/17.
     *
     * @param param
     * @return
     */
    public static RsResponse<ProductBeanResult> getPdClassesTreeMatInfo(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductClassesTreeMatServices();
        RsResponse<ProductBeanResult> productInfoResponse = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<ProductBeanResult>>() {
                });
        if (null == productInfoResponse.getMessage() || productInfoResponse.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            productInfoResponse = new RsResponse<ProductBeanResult>();
        }
        return productInfoResponse;
    }

    /**
     * 根据批量产品标准ID和包装规格编码查询产品包装规格信息
     * Created by xuwei on 2016/6/24.
     *
     * @param param
     * @return
     */
    public static RsResponse<ProductBeanResult> findpdNormsInfos(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getPdPackageInfoSearchServices();
        RsResponse<ProductBeanResult> productInfoResponse = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<ProductBeanResult>>() {
                });
        if (null == productInfoResponse.getMessage() || productInfoResponse.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            productInfoResponse = new RsResponse<ProductBeanResult>();
        }
        return productInfoResponse;
    }

    /**
     * 查询产品标准列表
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdStandard[]> findProductStandard(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductStandardSearchServices();
        RsResponse<PdStandard[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdStandard[]>>() {
                });
        if (null == response.getMessage() || response.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            response = new RsResponse<PdStandard[]>();
        }
        return response;
    }

    /**
     * 调用接口 查询二级分类信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdMachining[]> findPdMachining(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductMachiningInfo();
        RsResponse<PdMachining[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdMachining[]>>() {
                });
        if (null == response.getMessage() || response.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            response = new RsResponse<PdMachining[]>();
        }
        return response;
    }

    /**
     * 调用接口 查询品种信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdBreed[]> findPdBreed(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductBreedInfo();
        RsResponse<PdBreed[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdBreed[]>>() {
                });
        if (null == response.getMessage() || response.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            response = new RsResponse<PdBreed[]>();
        }
        return response;
    }

    /**
     * 调用接口 查询一级分类信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdClasses[]> findPdClasses(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductClassesInfo();
        RsResponse<PdClasses[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdClasses[]>>() {
                });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<PdClasses[]>();
        }
        return response;
    }

    /**
     * 调用接口 查询特征信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdFeature[]> findPdFeature(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductFeatureInfo();
        RsResponse<PdFeature[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdFeature[]>>() {
                });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<PdFeature[]>();
        }
        return response;
    }

    /**
     * 调用接口 查询净重信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdWeight[]> findPdWeight(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductWeightInfo();
        RsResponse<PdWeight[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdWeight[]>>() {
                });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<PdWeight[]>();
        }
        return response;
    }

    /**
     * 调用接口 查询产品包装信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdNormsStd[]> findPdNormsStd(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductNormsInfo();
        RsResponse<PdNormsStd[]> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<PdNormsStd[]>>() {
                });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<PdNormsStd[]>();
        }
        return response;
    }

    /**
     * 调用接口 批量保存产品加工质量标准表
     * Created by liu_yan2 on 2016/6/20.
     */
    public static Integer batchInsertMctProvider(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductSaveMctProviderServices();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<Integer>();
            response.setResult(0);
        }
        return response.getResult();
    }

    /**
     * 卖家申请新品
     * Created by zhang_chi on 2016/6/28.
     */
    public static Integer savePdTcProviderPackage(List<SL241130Param> paramList) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setPdTcProviderPackages(paramList);
        request.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getSaveProviderPackageServices();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<Integer>();
            response.setResult(0);
        }
        return response.getResult();
    }

    /**
     * 调用接口 批量保存产品加工质量标准表 (论证中供应商习惯标准）
     * Created by liu_yan2 on 2016/6/29.
     */
    public static Integer batchInsertTncProvider(PDInfoParam param) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getSaveTncDiscussProviderServices();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<Integer>();
            response.setResult(0);
        }
        return response.getResult();
    }

    /**
     * 调用接口 同步卖家模块买手信息
     * create by likai1 2016/8/3
     */
    public static Boolean syncBuyerInfo(IBS2101115Param param) {
        RsRequest<IBS2101115Param> request = new RsRequest<IBS2101115Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = SystemServerManager.BsServerManage.getSyncBuyerInfo();
        RsResponse<String> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<String>>() {
        });
        boolean result = false;
        if (!StringUtil.isNullOrEmpty(response.getStatus())
                && response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            result = true;
        }
        return result;
    }

    /**
     * 获取产品相关标准信息质量标准
     * 档案卡类型 原种种源标准指标 -- 1 原种种源标准指标 -- 2 原种饲养标准指标 -- 3 产品加工技术标准指标 -- 4 产品加工质量标准指标 -- 5 产品通用质量标准指标 -- 6 产品安全标准指标 -- 7
     * 储存运输标准指标 -- 8
     * Created by zhang_chi on 2016/6/20.
     * 方法重写
     */
    public static RsResponse<ProductStdResult> getPdProductStd(PDInfoParam pdInfoParam) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(pdInfoParam);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductStdServices();
        RsResponse<ProductStdResult> response = RestClientUtil.post(url, request,
                new TypeReference<RsResponse<ProductStdResult>>() {
                });
        if (response.getStatus().equals(SystemConst.RsStatus.FAIL) || null == response.getMessage()) {
            response = new RsResponse<ProductStdResult>();
        }
        return response;
    }
}
