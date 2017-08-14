package com.msk.bms.ssc.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.SscBidBasicInfo;
import com.msk.core.entity.SscBusinessTerms;
import com.msk.core.entity.SscContractBasic;
import com.msk.core.entity.SscPackageMaterialInfo;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 合同相关接口
 * Created by liu_yan2 on 2016/9/6.
 */
public class ISSCContractRestUtil {

    /**logger*/
    private static Logger logger = getLogger(ISSCContractRestUtil.class);

    /** true：启用本地路径，false：停用 */
    private static final boolean LOCALHOST = false;

    /** 调试方便，接口路径可以修改 */
    private static final String LOCALHOST_URL = "http://localhost:8081/msk-ssc-api";

    /** 平台编号 */
    private static final String SITECODE = "1";
    /** 平台身份识别码 */
    private static final String AUTH = "MSK00001";
    /** 用户登录ID */
    private static final String LOGINID = "msk01";

    /**
     * 调用接口 获取合同基本信息
     * @param param
     * @return
     */
    public  static SscContractBasic findSscContractBasic(SSC11304Param param) {
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindSscBidBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findSscContractBasic";
        }
        RsResponse<SscContractBasic> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SscContractBasic>>() {
        });
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult();
        } else {
            throw new BusinessException(response.getMessage());
        }
    }


    /**
     * 根据合同状态查询合同编码
     * @param param
     * @return
     */
    public static RsResponse<SSC11314RsPageResult> findChooseContract(SSC11314RsParam param) {
        RsRequest<SSC11314RsParam> request = new RsRequest<SSC11314RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindChooseContract();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findChooseContract";
        }
        RsResponse<SSC11314RsPageResult> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11314RsPageResult>>() {
        });
        return response;
    }


    /**
     * 建议优化
     * 查询合同列表
     * @param ssc11303RsParam
     * @return
     */
    public  static  RsResponse<PageResult<SSC11303RsBean>> findContractList(SSC11303RsParam ssc11303RsParam){
        logger.debug("查询合同资料");
        RsRequest<SSC11303RsParam> request = new RsRequest<SSC11303RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(ssc11303RsParam);
        String url = SystemServerManager.SellerSupplyChainManage.getFindContractBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findContractBasic";
        }

        RsResponse<PageResult<SSC11303RsBean>> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<PageResult<SSC11303RsBean>>>() {});
        return response;
    }

    /**
     * 建议优化
     * 查询合同下的产品信息列表
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> findContractOrderPD(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindContractPdDetailList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findContractPdDetailList";
        }

        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 更新合同基本信息
     * @param param
     * @return
     */
    public static String updateContract(SscContractBasic param){
        RsRequest<SscContractBasic> request = new RsRequest<SscContractBasic>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getUpdateContractBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/updateContractBasic";
        }

        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getStatus();
        } else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 保存合同基本信息
     * @param param
     * @return
     */
    public static Long saveContract(SscContractBasic param){
        RsRequest<SscContractBasic> request = new RsRequest<SscContractBasic>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveContractBasci();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveContractBasci";
        }

        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        if (SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
            return response.getResult().getContractId();
        }
        else {
            throw new BusinessException(response.getMessage());
        }
    }

    /**
     * 查询合同的商务条款
     * @param param
     * @return
     */
    public static RsResponse<SscBusinessTerms> findSscContractBusiness(SscBusinessTerms param){
        RsRequest<SscBusinessTerms> request = new RsRequest<SscBusinessTerms>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindSscContractBusiness();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findSscContractBusiness";
        }
        RsResponse<SscBusinessTerms> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SscBusinessTerms>>() {});
        return response;
    }
    /**
     * 保存合同的商务条款
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> saveContractBussiness(SscBusinessTerms param){
        RsRequest<SscBusinessTerms> request = new RsRequest<SscBusinessTerms>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveContractBusiness();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveContractBusiness";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 更新合同的商务条款
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> updateContractBusiness(SscBusinessTerms param){
        RsRequest<SscBusinessTerms> request = new RsRequest<SscBusinessTerms>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getUpdateContractBusiness();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/updateContractBusiness";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 查询合同包材
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> findContractPackingList(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindContractPackingList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findContractPackingList";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 查询合同产品交货计划信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> findDeliveryPlanList(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryPlanList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryPlanList";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 更新合同产品交货计划信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> modifyDeliveryPlan(SSC11304DeliveryPlanBean param){
        RsRequest<SSC11304DeliveryPlanBean> request = new RsRequest<SSC11304DeliveryPlanBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyDeliveryPlan();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyDeliveryPlan";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 查询合同产品交货批次
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> findDeliveryBatchList(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindDeliveryBatchList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDeliveryBatchList";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 新增交货计划
     * @param param
     * @return
     */
    public static String saveDeliveryPlan(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveBatchDps();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deliveryPlan/saveBatch";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response.getStatus();
    }

    /**
     * 建议优化
     * 根据合同ID查询合同中的产品，及其交货计划
     * @param param
     * @return
     */
    public static List<SSC11304ProductBean> findProductsByContractId(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindContractPdDetailList();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findContractPdDetailList";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        if(SystemConst.RsStatus.SUCCESS.equals(response.getStatus())) {
           return response.getResult().getProducts();
        }
        return new ArrayList<>();
    }

    /**
     * 建议优化
     * 根据合同产品ID，删除产品、包装和交货计划，更新合同金额和状态
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> deleteProduct(SSC11304ProductBean param){
        RsRequest<SSC11304ProductBean> request = new RsRequest<SSC11304ProductBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getDelContractPd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/delContractPd";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 建议优化
     * 根据合同产品ID，更新产品，更新合同金额和状态
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> updateProduct(SSC11304ProductBean param){
        RsRequest<SSC11304ProductBean> request = new RsRequest<SSC11304ProductBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getUpdateContractOrder();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/updateContractOrder";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 建议优化
     * 根据合同产品ID，更新产品，更新合同金额和状态
     * @param param
     * @return
     */
    public static String saveProduct(SSC11304ProductBean param){
        RsRequest<SSC11304ProductBean> request = new RsRequest<SSC11304ProductBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveContractOrder();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveContractOrder";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response.getStatus();
    }


    /**
     * 根据合同ID，查询合同订单中的产品明细，排除已有包材信息的产品
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> findContractProducts(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindContractProducts();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findContractProducts";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 删除合同包材信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> delContractPackgeM(SscPackageMaterialInfo param){
        RsRequest<SscPackageMaterialInfo> request = new RsRequest<SscPackageMaterialInfo>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getDelContractPackgeM();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/delContractPackgeM";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }


    /**
     * 修改合同包材信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> updateContractPackgeM(SSC11304PackageBean param){
        RsRequest<SSC11304PackageBean> request = new RsRequest<SSC11304PackageBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getModifyContractPacking();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/modifyContractPacking";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 保存合同包材信息
     * @param param
     * @return
     */
    public static String saveContractPackageM(SSC11304PackageBean param){
        RsRequest<SSC11304PackageBean> request = new RsRequest<SSC11304PackageBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getSaveContractPackageM();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/saveContractPackageM";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response.getStatus();
    }

    /**
     * 根据合同编号查询合同产品详细信息
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> findPd(SSC11304Param param){
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPd();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPd";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 生成发货订单
     * @param param
     * @return
     */
    public static RsResponse<Long> createDeliveryOrder(SSC11305RsParam param){
        RsRequest<SSC11305RsParam> request = new RsRequest<SSC11305RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getCreateSscDeliveryOrderInfo();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/createSscDeliveryOrderInfo";
        }
        RsResponse<Long> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Long>>() {});
        return response;
    }

    /**
     * 根据合同编号 查询对应的 有效箱数
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> checkEffectBoxNum(SSC11304DeliveryPlanBean param){
        RsRequest<SSC11304DeliveryPlanBean> request = new RsRequest<SSC11304DeliveryPlanBean>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getCheckEffecBoxNum();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/checkEffecBoxNum";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {});
        return response;
    }

    /**
     * 中标生成合同
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> createContracts(SscContractBasic param) {
        RsRequest<SscContractBasic> request = new RsRequest<SscContractBasic>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);

        String url = SystemServerManager.SellerSupplyChainManage.getCreatContracts();
        if (LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/creatContracts";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {
        });
        return response;
    }

    /**
     * 建议优化
     * 通过中标id查询合同
     * @param param
     * @return
     */
    public static RsResponse<SSC11304Result> checkBid(SscBidBasicInfo param) {
        RsRequest<SscBidBasicInfo> request = new RsRequest<SscBidBasicInfo>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getCheckBid();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/checkBid";
        }
        RsResponse<SSC11304Result> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<SSC11304Result>>() {
        });
        return response;
    }

    /**
     * 建议优化
     * 查询最大的合同编号
     * @param param
     * @return
     */
    public static RsResponse<String> findDBContractCode(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getFindDBContractCode();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findDBContractCode";
        }
        RsResponse<String> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<String>>() {
        });
        return response;
    }

    /**
     * 建议优化
     * 更新合同的状态为待审核
     * @param param
     * @return
     */
    public static RsResponse<Integer> enableToModify(SSC11304Param param) {
        RsRequest<SSC11304Param> request = new RsRequest<SSC11304Param>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getEnableToModify();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/enableToModify";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (SystemConst.RsStatus.FAIL.equals(response.getStatus())) {
            throw new BusinessException(response.getMessage());
        }
        return response;
    }

    /**
     * 建议优化
     * 删除合同基本信息
     * @param param
     * @return
     */
    public static RsResponse<Integer> deleteContractBasic(SSC11303RsParam param) {
        RsRequest<SSC11303RsParam> request = new RsRequest<SSC11303RsParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(param);
        String url = SystemServerManager.SellerSupplyChainManage.getDeleteContractBasic();
        if(LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/deleteContractBasic";
        }
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {});
        return response;
    }

    /**
     * 检查 是否存在同一个车次 相同的pdCode
     */
    public static RsResponse<Long> findPack(BaseParam baseParam) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setAuth(AUTH);
        request.setLoginId(LOGINID);
        request.setSiteCode(SITECODE);
        request.setParam(baseParam);

        String url = SystemServerManager.SellerSupplyChainManage.getFindPack();
        if (LOCALHOST) {
            url = LOCALHOST_URL + "/api/ssc/findPack";
        }
        RsResponse<Long> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<Long>>() {});
        return rsResponse;
    }

}
