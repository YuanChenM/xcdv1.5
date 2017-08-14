package com.msk.buyers.utils;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.msk.bs.bean.IBS2101114Param;
import com.msk.bs.bean.IBS2101114Result;
import com.msk.buyers.bean.BY121322RsParam;
import com.msk.buyers.bean.BY121322RsResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.PdMachining;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductBeanResult;
import com.msk.product.bean.ProductStdResult;

/**
 * Created by zhu_kai1 on 2016/5/10.
 */
public class RestCommUtil {

    private static String STATE_FLAG = "F";

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
     * 查询指定城市中的区县
     *
     * @param districtParam
     * @return
     */
    public static RsResponse<DistrictResult> getDistrictList(DistrictParam districtParam) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId(districtParam.getActId());
        request.setSiteCode("1");
        request.setParam(districtParam);
        String url = SystemServerManager.DistrictServerManage.getDistrictQueryDistrict();
        RsResponse<DistrictResult> districtList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        if (STATE_FLAG.equals(districtList.getStatus())) {
            throw new BusinessException(districtList.getMessage());
        }
        return districtList;
    }

    /**
     * 根据lgcsAreaCode查询下面的城市
     *
     * @param districtParam
     * @return
     */
    public static RsResponse<DistrictResult> getCityList(DistrictParam districtParam) {
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(districtParam);
        // "http://localhost:8080/msk-district/api/district/query/city"
        String url = SystemServerManager.DistrictServerManage.getDistrictQueryCity();
        RsResponse<DistrictResult> districtList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        if ("F".equals(districtList.getStatus())) {
            throw new BusinessException(districtList.getMessage());
        }
        return districtList;
    }

    /**
     * 获取产品信息
     * 产品一级分类
     *
     * @return
     */
    public static RsResponse<ProductBeanResult> getPdClassesList(PDInfoParam pdInfoParam) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        if (pdInfoParam.getType() == null) {
            pdInfoParam.setType(1);
        }
        request.setParam(pdInfoParam);
        String url = SystemServerManager.PdServerManager.getPdTypeName();
        RsResponse<ProductBeanResult> pdClassesList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
                });
        if (STATE_FLAG.equals(pdClassesList.getStatus())) {
            throw new BusinessException(pdClassesList.getMessage());
        }
        return pdClassesList;
    }

    /**
     * 调用接口 查询二级分类信息
     *
     * @param param
     * @return
     */
    public static RsResponse<PdMachining[]> findPdMachining(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = SystemServerManager.PdServerManager.getFindPdMachining();
//        String url = "http://10.20.16.22:8081/api/product/findPdMachining";
        RsResponse<PdMachining[]> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdMachining[]>>() {
        });
        return responce;
    }

    /**
     * TYPE
     * 1：产品技术标准
     * 2：原料种源信息
     * 3：原种种源档案卡
     * 4：饲养指标档案卡
     * 5：通用质量指标档案卡
     * 6：存储运输指标档案卡
     * 7：产品等级&卫生质量标准
     * 8：产品标准质量档案卡
     *
     * @param pdInfoParam
     * @return 提供给买家调研
     */
    public static RsResponse<ProductBeanResult> getPdInfoList(PDInfoParam pdInfoParam) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        pdInfoParam.setType(2);
        request.setParam(pdInfoParam);
        // TODO 产品档案卡的url,后期可能修改，现在url未加入到redis中
        RsResponse<ProductBeanResult> pdClassesList =
                RestClientUtil.post("http://localhost:8080/msk-product/api/pd_std", request, new TypeReference<RsResponse<ProductBeanResult>>() {
                });
        return pdClassesList;
    }

    /**
     * 买家产品标准调研用
     * TYPE
     * 1：买家产品技术标准调研
     * 2：买家原种种源标准调研
     * 3：买家饲养指标标准调研
     * 4：买家通用质量指标标准调研
     * 5：买家存储运输指标标准调研
     * 6：买家产品等级&卫生质量标准调研
     * 7：家产品标准质量标准调研
     * 8：买家产品包装标准调研
     *
     * @param pdInfoParam
     * @return
     */
    public static RsResponse<ProductStdResult> findBuyerProductStd(PDInfoParam pdInfoParam) {
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(pdInfoParam);
        // TODO 产品档案卡的url,后期可能修改，现在url未加入到redis中
        RsResponse<ProductStdResult> pdClassesList =
                RestClientUtil.post("http://localhost:8081/msk-product/product/findBuyerProductStd", request, new TypeReference<RsResponse<ProductStdResult>>() {
                });
        return pdClassesList;
    }

    public static RsResponse<IBS2101114Result> searchHouseInfo(IBS2101114Param param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = SystemServerManager.BsServerManage.getSearchHouseInfo();
        RsResponse<IBS2101114Result> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<IBS2101114Result>>() {
        });
        return responce;
    }

    /**
     * 获取营销管控信息
     *
     * @param param
     * @return
     */
    public static RsResponse<BY121322RsResult> getSaleMarketControlInfo(BY121322RsParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = SystemServerManager.BsServerManage.getGetSaleByBuyerId();
        RsResponse<BY121322RsResult> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY121322RsResult>>() {
        });
        if (STATE_FLAG.equals(responce.getStatus())) {
            throw new BusinessException(responce.getMessage());
        }
        return responce;
    }

    /**
     * 获取销售管控信息
     *
     * @param param
     * @return
     */
    public static RsResponse<BY121322RsResult> getSaleMarketInfoForBuyerPool(BY121322RsParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        // String url = SystemServerManager.BsServerManage.getGetSaleByBuyerId();
//        String url = "http://localhost:8080/api/br/getSaleInfoBYBuyerId";
        String url = SystemServerManager.BuyersReportServerManager.getGetSaleInfoByBuyerId();
        RsResponse<BY121322RsResult> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY121322RsResult>>() {
        });
        if (STATE_FLAG.equals(responce.getStatus())) {
            throw new BusinessException(responce.getMessage());
        }
        return responce;
    }
}
