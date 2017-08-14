package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.bms.ssc.bean.ISC182209RsResult;
import com.msk.bms.ssc.bean.seller.*;
import com.msk.bms.ssc.bean.SSC11327RsPageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BaseEntity;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.ds.bean.ISC182207RsParam;
import com.msk.product.bean.*;
import com.msk.seller.bean.*;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 非本平台调用接口
 * Created by liu_yan2 on 2016/8/18.
 */
public class ISSCRestUtil {

    /**logger*/
    private static Logger logger = getLogger(ISSCRestUtil.class);

    /** true：启用本地路径，false：停用 */
    private static final boolean LOCALHOST = false;

    /** 调试方便，接口路径可以修改 */
    private static final String LOCALHOST_URL = "http://localhost:8080/msk-seller";

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";

    /**
     * 根据Slcode 获取甲方关联的生产商列表 或者 代理卖家/OEM卖家列表
     * @param param
     * @return
     */
    public static RsResponse<ISLEnterpriseRsPageResult> getProEnterpriseList(ISLEnterpriseRsParam param){
        RsRequest<ISLEnterpriseRsParam> request = new RsRequest<ISLEnterpriseRsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url=SystemServerManager.SellerServerManage.getGetSlEnterpriseBySlCode();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/sl/slInfo/slEnterpriseBySlCode/list";
        }
        RsResponse<ISLEnterpriseRsPageResult> response= RestClientUtil.post(url, request, new TypeReference<RsResponse<ISLEnterpriseRsPageResult>>() {
        });
        return response;
    }

    /**
     * 根据卖家类型获取卖家
     * @param param
     * @return
     */
    public static List<ISLEnterpriseRsResult> getSlEnterpriseList(ISLSellerRsParam param){
        RsRequest<ISLSellerRsParam> request = new RsRequest<ISLSellerRsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url= SystemServerManager.SellerServerManage.getSearchSlEnterprise();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/sl/slInfo/slEnterprise/list";
        }
        RsResponse<ISLEnterpriseRsResult> rsResponse= RestClientUtil.post(url, request, new TypeReference<RsResponse<ISLEnterpriseRsResult>>() {
        });
        ISLEnterpriseRsResult result=rsResponse.getResult();
        return  result.getSlEnterpriseList();
    }



    /**
     * 调用接口 获取甲方可卖，乙方可生产的共同产品
     * @param param
     * @return
     */
    public static List<SlProductRsBean> getSlProductList(ISLSellerRsParam param) {
        RsRequest<ISLSellerRsParam> request = new RsRequest<ISLSellerRsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getSearchSlProduct();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/sl/slInfo/slProduct/list";
        }
        RsResponse<ISLSProductRsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISLSProductRsResult>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult().getSlProductList();
        }
        return new ArrayList<SlProductRsBean>();
    }


    /**
     * 从产品接口获取8个档案卡
     * type 1.产品技术标准 2.原料种源信息 3.原种种源档案卡 4.饲养指标档案卡
     * 5.通用质量指标档案卡 6.存储运输指标档案卡 7.产品等级&卫生质量标准 8.产品标准质量档案卡
     * 调用接口 获取产品技术标准
     * @param param
     * @return
     *
     */
    public static  BaseEntity[]  getPdStandards(IPD141146RsParam param) {
        RsRequest<IPD141146RsParam> request = new RsRequest<IPD141146RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.PdServerManager.getPdStandardByCodes();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/pd/pd_std";
        }
        switch (param.getType()) {
            case NumberConst.IntDef.INT_ONE:
                RsResponse<IPD141121RsResult[]> mctResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141121RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(mctResponse.getStatus())) {
                    return mctResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_TWO:
                RsResponse<IPD141122RsResult[]> matResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141122RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(matResponse.getStatus())) {
                    return matResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_THREE:
                RsResponse<IPD141123RsResult[]> orgResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141123RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(orgResponse.getStatus())) {
                    return orgResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_FOUR:
                RsResponse<PD141147Bean[]> fedResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<PD141147Bean[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(fedResponse.getStatus())) {
                    return fedResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_FIVE:
                RsResponse<IPD141125RsResult[]> gnqResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141125RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(gnqResponse.getStatus())) {
                    return gnqResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_SIX:
                RsResponse<IPD141126RsResult[]> tspResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141126RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(tspResponse.getStatus())) {
                    return tspResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_SEVEN:
                RsResponse<IPD141127RsResult[]> sftResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141127RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(sftResponse.getStatus())) {
                    return sftResponse.getResult();
                }
                break;
            case NumberConst.IntDef.INT_EIGHT:
                RsResponse<IPD141107RsResult[]> tncResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<IPD141107RsResult[]>>() {});
                if (SystemConst.RsStatus.SUCCESS.equals(tncResponse.getStatus())) {
                    return tncResponse.getResult();
                }
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 调用产品接口 获取产品包装标准
     * @param param
     * @return
     */
    public static RsResponse<ProductBeanResult> findProductPackage(PDInfoParam param){
        RsRequest<PDInfoParam> request = new RsRequest<PDInfoParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url= SystemServerManager.PdServerManager.getFindProductPackage();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/v1//findProductPackage";
        }
        RsResponse<ProductBeanResult> response= RestClientUtil.post(url, request, new TypeReference<RsResponse<ProductBeanResult>>() {
        });
        return  response;
    }

    /**
     *
     * 调用接口 获取卖家产品信息与状态
     * @param param
     * @return
     *
     */
    static public RsResponse<ISC182209RsResult> searchSellerProductInfo( ISL231208Param param){
        RsRequest<ISL231208Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerServerManage.getQueryProductInfo();
        RsResponse<ISC182209RsResult> rs = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISC182209RsResult>>() {});

        return rs;
    }

    /**
     * 生成XML
     * @param isc182207RsParam
     * @return
     */
    public static RsResponse<SSC11317DepartureParam> createXML(ISC182207RsParam isc182207RsParam) {
        RsRequest<ISC182207RsParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(isc182207RsParam);
        String url = SystemServerManager.DsServerManage.getCreateXML();
        if(LOCALHOST) {
            //String url = "http://10.20.16.72:8085/api/sc/createXML";
            url = LOCALHOST_URL + "/api/sc/createXML";
        }
        RsResponse<SSC11317DepartureParam> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11317DepartureParam>>() {
        });
        return response;
    }

    /**
     * 调用卖家接口 查询企业信息列表
     * @param isl231207Param
     * @return
     */
    public static RsResponse<SSC11327RsPageResult> findEpPageList(ISL231207Param isl231207Param){
        RsRequest<ISL231207Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(isl231207Param);

        String url = SystemServerManager.SellerServerManage.getQueryEnterpriseInfo();

        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/sl/enterpriseInfo/search";
        }

        RsResponse<SSC11327RsPageResult> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11327RsPageResult>>() {
        });

        return rsResponse;
    }


    /**
     * 调用接口 根据企业账号查询企业详细
     * @param param
     * @return
     */
    public static ISL231181Result findEpDetail(ISL231181RsParam param) {
        RsRequest<ISL231181RsParam> request = new RsRequest<ISL231181RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url =  SystemServerManager.SellerServerManage.getSearchAllSLEpManager();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/v1/sl/slInfo/searchAll";
        }
        RsResponse<ISL231181Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISL231181Result>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 获取卖家产品档案卡接口
     * 加工质量标准指标
     * 加工技术标准指标
     * 原种种源标准指标
     * 原种饲养标准指标
     * 通用质量标准指标
     * 安全标准指标
     * 储存运输标准指标
     * 包装标准指标
     * @param param
     * @return
     */
    public static ISL231109RsResult getSlPdStandards(ISL231109RsParam param) {
        RsRequest<ISL231109RsParam> request = new RsRequest<ISL231109RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url =  SystemServerManager.SellerServerManage.getFindSlSellerStd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "msk-seller/api/sl/slInfo/slQlt/search";
        }
        RsResponse<ISL231109RsResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<ISL231109RsResult>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 调用物流区接口 获取物流区列表
     *
     * @return
     */
    public static List<LgcsAreaBean> getLgcsAreaList(DistrictParam districtParam) {
        RsRequest<DistrictParam> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(districtParam);
        String url = SystemServerManager.DistrictServerManage.getDistrictQueryLgcsArea();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "msk-district/api/district/query/lgcsArea";
        }
        RsResponse<DistrictResult> lgcsAreaResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {});
        List<LgcsAreaBean> lgcsAreaList = new ArrayList<>();
        if ("F".equals(lgcsAreaResponse.getStatus())) {
            throw new BusinessException("物流区:" + lgcsAreaResponse.getMessage());
        }else{
            lgcsAreaList = lgcsAreaResponse.getResult().getLgcsAreaList();
        }

        return lgcsAreaList;
    }

}
