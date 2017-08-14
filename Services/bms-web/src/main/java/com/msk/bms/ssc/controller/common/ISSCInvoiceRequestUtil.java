package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SscContractBasic;
import com.msk.ssc.bean.*;
import com.msk.sso.client.utils.UserSessionManger;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by liu_yan2 on 2016/8/30.
 */
public class ISSCInvoiceRequestUtil {




    /**logger*/
    private static Logger logger = getLogger(ISSCContractRestUtil.class);

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
     * 调用接口 查询发票列表
     * @param param
     * @return
     */
    public  static RsResponse<PageResult<SSC11323Bean>> searchInvoiceRequest(SSC11323Param param) {
        RsRequest<SSC11323Param> request = new RsRequest<SSC11323Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url;
        if(LOCALHOST){
                     url = LOCALHOST_URL+"/api/ssc/findSscinvoiceRequestList";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getFindSscinvoiceRequestList();
        }

        RsResponse<PageResult<SSC11323Bean>> bee = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11323Bean>>>() {
        });
        return bee;
    }
    /**
     * 删除发票信息
     * @param param
     * @return
     */
    public  static void delete(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url;
        if(LOCALHOST){
             url = LOCALHOST_URL+"/api/ssc/modifyInvoiceRequest";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getModifyInvoiceRequest();
        }

        RsResponse<SSC11324Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11324Bean>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }
    }

    /**
     * 查询发票详情
     *
     * @param
     * @return 页面
     */

    public  static RsResponse<SSC11324Bean> findInvoiceRequestDetail(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/findInvoiceRequestDetail";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getFindInvoiceRequestDetail();
        }
        RsResponse<SSC11324Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11324Bean>>() {
        });
        return rsResponse;
    }
    /**
     * 通过合同编号新建发票申请信息
     *
     * @param  param
     * @return 页面
     */
    public  static RsResponse<SSC11324Bean> contractToNewInvoiceRequestDetail(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/contractToNewInvoiceRequestDetail";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getContractToNewInvoiceRequestDetail();
        }

        RsResponse<SSC11324Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11324Bean>>() {
        });
        return rsResponse;
    }

    /**
     * 验证合同信息是否存在
     *
     * @param  param
     * @return 页面
     */
    public  static RsResponse<SSC11324Bean> contractFindInvoiceRequestDetailExist(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/contractFindInvoiceRequestDetailExist";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getContractFindInvoiceRequestDetailExist();
        }
        RsResponse<SSC11324Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11324Bean>>() {
        });
        return rsResponse;
    }

    /**
     * 新建发票申请
     *
     * @param param
     * @return 页面
     */
    public  static RsResponse<String> insertInvoiceRequest(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/insertInvoiceRequest";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getInsertInvoiceRequest();
        }

        RsResponse<String> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<String>>() {
        });
        return rsResponse;
    }

    /**
     * 修改发票内容
     *
     * @param param
     * @return 页面
     */
    public  static RsResponse<SSC11324Bean> modifyInvoiceRequestUp(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/modifyInvoiceRequestUp";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getModifyInvoiceRequestUp();
        }
        RsResponse<SSC11324Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11324Bean>>() {
        });
        return rsResponse;
    }
    /**
     * 查询存在的合同列表,用于新建发票信息
     *
     * @param
     * @return 页面
     */
    public  static RsResponse<PageResult<SSC11324Bean>> searchContractForInvoice(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/searchContractForInvoice";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getSearchContractForInvoice();
        }

        RsResponse<PageResult<SSC11324Bean>> rsResponse= RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11324Bean>>>() {
        });
        return rsResponse;
    }
    /**
     * 插入文件上传信息
     *
     * @param
     * @return 页面
     */
    public  static RsResponse<SSC11324Bean> saveInvoiceRequestFileInfo(SSC11324Param param) {
        RsRequest<SSC11324Param> request = new RsRequest<SSC11324Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url;
        if(LOCALHOST){
            url = LOCALHOST_URL+"/api/ssc/saveInvoiceRequestFileInfo";

        }else{
             url = SystemServerManager.SellerSupplyChainManage.getSaveInvoiceRequestFileInfo();
        }
        RsResponse<SSC11324Bean> rsResponse=RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11324Bean>>() {
        });
        return rsResponse;
    }

}
