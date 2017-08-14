package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by liu_yan2 on 2016/9/6.
 */
public class ISSCBidRestUtil {

    /**logger*/
    private static Logger logger = getLogger(ISSCBidRestUtil.class);

    /** true：启用本地路径，false：停用 */
    private static final boolean LOCALHOST = false;

    /** 调试方便，接口路径可以修改 */
    private static final String LOCALHOST_URL = "http://localhost:8080/msk-ssc-api";

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";

    /**
     * 调用接口 查询中标基本信息列表
     * @param param
     * @return
     */
    public static SSC11301RsPageResult findSscBidBasicInfoList(SSC11301RsParam param) {
        RsRequest<SSC11301RsParam> request = new RsRequest<SSC11301RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getfindSscBidBasicInfoList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findSscBidBasicInfoList";
        }
        RsResponse<SSC11301RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11301RsPageResult>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 调用接口 保存或者修改中标基本信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11301RsPageResult> insertBidBasicInfo(SSC11301RsParam param) {
        RsRequest<SSC11301RsParam> request = new RsRequest<SSC11301RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getInsertBidBasicInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/insertBidBasicInfo";
        }
        RsResponse<SSC11301RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11301RsPageResult>>() {
        });
        return response;
    }

    /**
     * 建议优化 修改中标状态
     * @param param
     * @return
     */
    public static Integer modifyBidStatus(SSC11301RsBean param) {
        RsRequest<SSC11301RsBean> request = new RsRequest<SSC11301RsBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getModifyBidStatus();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyBidStatus";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 建议优化 删除中标确认书
     * @param param
     * @return
     */
    public static RsResponse<Integer> deleteBidBase(SSC11301RsParam param) {
        RsRequest<SSC11301RsParam> request = new RsRequest<SSC11301RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getDeleteBidBase();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deleteBidBase";
        }
        return RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {});
    }

    /**
     * 建议优化 根据中标ID查询合同
     * @param param
     * @return
     */
    public static RsResponse<Integer> getCheckIsContract(SSC11301RsParam param) {
        RsRequest<SSC11301RsParam> request = new RsRequest<SSC11301RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getCheckIsContract();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/checkIsContract";
        }
        return  RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {});
    }

    /**
     * 调用接口 根据中标ID获取中标产品列表
     * @param param
     * @return
     */
    public static SSC11302RsPageResult findBidProductDetail(SSC11302Param param) {
        RsRequest<SSC11302Param> request = new RsRequest<SSC11302Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindBidProductDetail();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findBidProductDetail";
        }
        RsResponse<SSC11302RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11302RsPageResult>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 调用接口 插入中标产品详情
     * @param param
     * @return
     */
    public static Integer insertBidProductDetail(SSC11302RsBeen param) {
        RsRequest<SSC11302RsBeen> request = new RsRequest<SSC11302RsBeen>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getInsertBidProductDetailt();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/insertBidProductDetail";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 调用接口 根据detailId删除中标产品
     * @param param
     * @return
     */
    public static Integer deleteBidProduct(SSC11302Param param) {
        RsRequest<SSC11302Param> request = new RsRequest<SSC11302Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getDeleteProduct();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deleteProduct";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 调用接口 修改中标产品
     * @param param
     * @return
     */
    public static Integer modifyBidProduct(SSC11302RsBeen param) {
        RsRequest<SSC11302RsBeen> request = new RsRequest<SSC11302RsBeen>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getModifyProduct();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyProduct";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        }else {
            throw new BusinessException(response.getMessage());
        }
    }


    /**
     * 调用接口 查询未关联合同的中标信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11301RsPageResult> findNoRelatedBidBase(SSC11301Param param) {
        RsRequest<SSC11301Param> request = new RsRequest<SSC11301Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url =  SystemServerManager.SellerSupplyChainManage.getFindNoRelatedBidBase();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findNoRelatedBidBase";
        }
        RsResponse<SSC11301RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11301RsPageResult>>() {
        });
        return response;
    }

    /**
     * 建议删除和上面接口重复 查询未关联合同的中标信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11301Bean> checkBidBaseExist(SSC11301Param param) {
        RsRequest<SSC11301Param> request = new RsRequest<>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getCheckBidBaseExist();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/checkBidBaseExist";
        }
        RsResponse<SSC11301Bean> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11301Bean>>() {
        });
        return response;
    }
}
