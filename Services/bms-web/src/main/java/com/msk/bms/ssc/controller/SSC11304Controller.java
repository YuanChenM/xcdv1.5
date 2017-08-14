package com.msk.bms.ssc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;

import com.msk.bms.ssc.controller.common.*;
import com.msk.common.base.BaseController;
import com.msk.common.bean.LoginUser;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.core.entity.*;

import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.bean.ISLEnterpriseRsResult;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Created by tao_zhifa on 2016/6/28.
 */
@Controller
@RequestMapping("/SSC11304")
public class SSC11304Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SSC11304Controller.class);

    /**
     * 根据合同ID，初始化合同详细页面
     */
    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String show(SSC11304Param ssc11304Param, Model model) {
        if (null != ssc11304Param.getDeliveryId()) {
            model.addAttribute("navigation", "delivery");
            model.addAttribute("deliveryId", ssc11304Param.getDeliveryId());
        }
        else if (null != ssc11304Param.getDifferId()) {
            model.addAttribute("navigation", "differ");
            model.addAttribute("differId", ssc11304Param.getDifferId());
        }
        else if (null != ssc11304Param.getVerificationId()) {
            model.addAttribute("navigation", "verification");
            model.addAttribute("verificationId", ssc11304Param.getVerificationId());
        }
        else if (null != ssc11304Param.getPaymentId()) {
            model.addAttribute("navigation", "payment");
        }
        else if (null != ssc11304Param.getPaymentDetailId()) {
            model.addAttribute("navigation", "poolDetail");
            model.addAttribute("paymentId", ssc11304Param.getPaymentDetailId());
        }
        else if (null != ssc11304Param.getContractCode()) {
            model.addAttribute("navigation", "control");
            model.addAttribute("contractCode", ssc11304Param.getContractCode());
        }
        else if (null != ssc11304Param.getBidId()) {
            model.addAttribute("navigation", "bidBase");
        }
        else if (null != ssc11304Param.getContractId() &&
                null != ssc11304Param.getType()) {
            model.addAttribute("navigation", "requestDetail");
            model.addAttribute("paymentRequestId",ssc11304Param.getPaymentRequestId());
            model.addAttribute("requestType", ssc11304Param.getType());
        }
        else if (null != ssc11304Param.getPaymentRequestId()) {
            model.addAttribute("navigation", "request");
        }

        SscContractBasic sscContractBasic = new SscContractBasic();
        sscContractBasic.setContractId(ssc11304Param.getContractId());
        return this.init(sscContractBasic, model);
    }


    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SscContractBasic sscContractBasic, Model model) {
        logger.debug("合同详细页面初始化");
        if (sscContractBasic.getContractId() != null) {
            SSC11304Param ssc11304Param = new SSC11304Param();
            ssc11304Param.setContractId(sscContractBasic.getContractId());

            SscContractBasic dbsccContractBasic = ISSCContractRestUtil.findSscContractBasic(ssc11304Param);
            model.addAttribute("contractBasic", dbsccContractBasic);

            //调用合同商务接口
            SscBusinessTerms sscBusinessTerms = new SscBusinessTerms();
            sscBusinessTerms.setContractId(ssc11304Param.getContractId() + "");
            SscBusinessTerms dbContractBussiness = this.findSscContractBusiness(sscBusinessTerms);
            model.addAttribute("bs", dbContractBussiness);
        }

        List<Integer> sellerCodeList = new ArrayList<>();
        sellerCodeList.add(NumberConst.IntDef.INT_ONE);
        sellerCodeList.add(NumberConst.IntDef.INT_TWO);
        sellerCodeList.add(NumberConst.IntDef.INT_THREE);

        ISLSellerRsParam param=new ISLSellerRsParam();
        param.setSlMainClassList(sellerCodeList);
        List<ISLEnterpriseRsResult> sellers=  ISSCRestUtil.getSlEnterpriseList(param);
        model.addAttribute("sellers", sellers); //采购商
        return "ssc/SSC11304";
    }

    /**
     * 新增或更新合同基本信息
     */
    @RequestMapping(value = "/saveOrUpdateContract", method = RequestMethod.POST)
    public String saveOrUpdateContract(SSC11304ContractBean contractBean, Model model) {
        SscContractBasic contract = new SscContractBasic();
        String userId = super.getLoginUser().getEmplId();

        String contractActDateStr = contractBean.getContractActDateStr();
        if (!StringUtil.isEmpty(contractActDateStr)) {
            contract.setContractActDate(DateTimeUtil.parseDate(contractActDateStr, DateTimeUtil.FORMAT_DATE_YYYYMMDD));
        }

        Long contractId = contractBean.getContractId();
        if (null == contractId) {
            synchronized (this) {
                contract.setContractCode(this.buildMaxContractCode());
                contract.setContractName(contractBean.getContractName());
                contract.setPurchaserId(contractBean.getPurchaserId());
                contract.setPurchaserCode(contractBean.getPurchaserCode());
                contract.setPurchaserName(contractBean.getPurchaserName());
                contract.setSupplierId(contractBean.getSupplierId());
                contract.setSupplierCode(contractBean.getSupplierCode());
                contract.setSupplierName(contractBean.getSupplierName());
                contract.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.PENDING_AUDIT));
                contract.setCrtId(userId);
                contract.setBidProjectNo(contractBean.getBidProjectNo());
                contract.setBidRelationType(String.valueOf(SscConstant.RelationType.UNRELATE));
                contractId = ISSCContractRestUtil.saveContract(contract);
                contract.setContractId(contractId);
            }
        }
        else {
            contract.setContractId(contractBean.getContractId());
            contract.setContractName(contractBean.getContractName());
            contract.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.PENDING_AUDIT));
            contract.setUpdId(userId);
            contract.setVer(contractBean.getVer());
            ISSCContractRestUtil.updateContract(contract);
        }
        return this.init(contract, model);
    }

    /**
     * 新增或更新合同基本信息
     */
    @RequestMapping(value = "/saveOrUpdateContractBase", method = RequestMethod.POST)
    public String saveOrUpdateContractBase(SSC11304ContractBean contractBean, Model model) {
        SscContractBasic contract = new SscContractBasic();
        super.setCommonParam(contractBean);
        contract.setContractId(contractBean.getContractId());
        contract.setBidId(contractBean.getBidId());
        contract.setBidProjectNo(contractBean.getBidProjectNo());
        contract.setBidRelationType(String.valueOf(SscConstant.RelationType.RELATED));
        contractBean.setCrtTime(DateTimeUtil.getCustomerDate());
        ISSCContractRestUtil.updateContract(contract);
        return this.init(contract, model);
    }
    /**
     * 甲乙双方确认
     */
    @RequestMapping(value = "/auditContract", method = RequestMethod.POST)
    public String auditContract(SSC11304ContractBean contractBean, Model model) {
        int status = Integer.parseInt(contractBean.getContractStatus());

        SscContractBasic contract = new SscContractBasic();
        contract.setContractId(contractBean.getContractId());
        contract.setContractStatus(contractBean.getContractStatus());
        contract.setVer(contractBean.getVer());

        LoginUser loginUser = super.getLoginUser();
        String userId = loginUser.getEmplId();
        String userName = loginUser.getEmplName();
        Date now = DateTimeUtil.getCustomerDate();

        if (SscConstant.SscOrderStatus.PUR_AUDIT == status || NumberConst.IntDef.INT_N_TWO == status) {
            contract.setPurchaserConfirmId(userId);
            contract.setPurchaserConfirmName(userName);
            contract.setPurchaserConfirmTime(now);
        }
        else if (SscConstant.SscOrderStatus.SUPP_AUDIT == status || NumberConst.IntDef.INT_N_THREE == status) {
            contract.setSupplierConfirmId(userId);
            contract.setSupplierConfirmName(userName);
            contract.setSupplierConfirmTime(now);
        }

        if (NumberConst.IntDef.INT_N_TWO == status || NumberConst.IntDef.INT_N_THREE == status) {
            contract.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.EFFECTIVE));
            if (StringUtil.isEmpty(contractBean.getContractActDateStr())) {
                contract.setContractActDate(now);
            }
        }

        contract.setUpdId(userId);
        contract.setUpdTime(now);
        ISSCContractRestUtil.updateContract(contract);
        return this.init(contract, model);
    }

    /**
     * 保存合同的商务条款
     */
    @RequestMapping(value = "saveBusinessTerms", method = RequestMethod.POST)
    public String saveBusinessTerms(SscBusinessTerms sscBusinessTerms, Model model) {
        RsResponse<SscBusinessTerms> respResult = ISSCContractRestUtil.findSscContractBusiness(sscBusinessTerms);
        if (null != respResult.getResult()) {
            throw new BusinessException("当前合同的商务条款已经被别人添加了，请重新加载数据进行修改！");
        }

        sscBusinessTerms.setCrtId(super.getLoginUser().getEmplId());
        ISSCContractRestUtil.saveContractBussiness(sscBusinessTerms);
        SscContractBasic sscContractBasic = new SscContractBasic();
        sscContractBasic.setContractId(Long.parseLong(sscBusinessTerms.getContractId()));
        return this.init(sscContractBasic, model);
    }

    /**
     * 修改合同的商务条款
     */
    @RequestMapping(value = "updateBusinessTerms", method = RequestMethod.POST)
    public String updateBusinessTerms(SscBusinessTerms sscBusinessTerms, Model model) {
        sscBusinessTerms.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11304Result> respResult = ISSCContractRestUtil.updateContractBusiness(sscBusinessTerms);
        if (SystemConst.RsStatus.FAIL.equals(respResult.getStatus())) {
            throw new BusinessException(respResult.getMessage());
        }

        SscContractBasic sscContractBasic = new SscContractBasic();
        sscContractBasic.setContractId(Long.parseLong(sscBusinessTerms.getContractId()));
        return this.init(sscContractBasic, model);
    }


    /**
     * 查询合包材
     */
    @RequestMapping(value = "contractPackageSearch", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11304PackageBean> contractPackageSearch(SSC11304Param param) {
        logger.debug("查询合包材");
        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.findContractPackingList(param);
        List<SSC11304PackageBean> packingBeans = null;
        if (rsResponse == null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {// 失败
            packingBeans = new ArrayList<>();
        } else {// 成功
            SSC11304Result ssc11304Result = rsResponse.getResult();
            packingBeans = ssc11304Result.getPackingPageResult();
        }

        int totalCartons = NumberConst.IntDef.INT_ZERO;
        int totalInnerBags = NumberConst.IntDef.INT_ZERO;

        for (SSC11304PackageBean packingBean : packingBeans) {
            totalCartons += this.toInt(packingBean.getCartonUseNum());
            totalInnerBags += this.toInt(packingBean.getInnerBagUseNum());
        }

        //将合计值保存在list的第一个元素中
        if (!CollectionUtils.isEmpty(packingBeans)) {
            SSC11304PackageBean packingBean = packingBeans.get(0);
            packingBean.setTotalCartons(totalCartons);
            packingBean.setTotalInnerBags(totalInnerBags);
        }

        PageResult<SSC11304PackageBean> result = new PageResult();
        result.setData(packingBeans);
        return result;

    }


    /**
     * 将Integer转化为int
     */
    private int toInt(Integer i) {
        return (null == i) ? NumberConst.IntDef.INT_ZERO : i;
    }


    /**
     * 查询合同产品交货计划信息
     */
    @RequestMapping(value = "contractDeliveryPlanSearch", method = RequestMethod.POST)
    public @ResponseBody PageResult<SSC11304DeliveryPlanBean> contractDeliveryPlanSearch(SSC11304Param param) {
        logger.debug("查询合同产品交货计划信息");
        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.findDeliveryPlanList(param);

        List<SSC11304DeliveryPlanBean> pdBeans = null;
        if (rsResponse == null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {// 失败
            pdBeans = new ArrayList<>();
        } else {// 成功
            SSC11304Result ssc11304Result = rsResponse.getResult();
            pdBeans = ssc11304Result.getDpPageResult();

            //文字加样式
            if (!CollectionUtils.isEmpty(pdBeans)) {
                SSC11304Param ssc11304Param = new SSC11304Param();
                ssc11304Param.setContractId(param.getContractId());
                List<SSC11304ProductBean> ssc11304ProductBeans = ISSCContractRestUtil.findProductsByContractId(ssc11304Param);

                Map<String, String> codeMap = new HashMap<String, String>();
                for (SSC11304ProductBean bean : ssc11304ProductBeans) {
                    codeMap.put(bean.getPdCode(), bean.getDeliveryPlan());
                }

                BigDecimal thousand = new BigDecimal(1000);
                for (SSC11304DeliveryPlanBean bean : pdBeans) {
                    bean.setArriveQut(bean.getArriveQut().divide(thousand));    //千克转化为吨
                    String pdCode = bean.getPdCode();
                    String deliveryPlan = codeMap.get(pdCode);

                    if (deliveryPlan.equals("未分配")) {
                        bean.setPdName("<font color='red'>" + bean.getPdName() + "</font>");
                    } else if (deliveryPlan.equals("部分分配")) {
                        bean.setPdName("<font color='purple'>" + bean.getPdName() + "</font>");
                    } else if (deliveryPlan.equals("已分配完")) {
                        bean.setPdName("<font color='green'>" + bean.getPdName() + "</font>");
                    }
                }
            }
        }

        PageResult<SSC11304DeliveryPlanBean> result = new PageResult();
        result.setData(pdBeans);
        return result;

    }

    /**
     * 检查有无交货期计划，以及是否全部生成生成发货订单
     * 1无交货期计划 2全部生成发货订单
     */
    @RequestMapping(value = "checkDeliveryPlanNum", method = RequestMethod.POST)
    @ResponseBody
    public int checkDeliveryPlanNum(SSC11304Param param) {
        logger.debug("检查合同是否全部生成发货订单");
        RsResponse<SSC11304Result> ssc11304RsPageResultRsResponse = ISSCContractRestUtil.findDeliveryBatchList(param);
        if (SystemConst.RsStatus.FAIL.equals(ssc11304RsPageResultRsResponse.getStatus())) {
            return NumberConst.IntDef.INT_TWO;
        }
        return NumberConst.IntDef.INT_ZERO;
    }

    /**
     * 修改交货期计划
     */
    @RequestMapping(value = "updateDeliveryPlan", method = RequestMethod.POST)
    @ResponseBody
    public String updateDeliveryPlan(SSC11304DeliveryPlanBean planBean) throws Exception {
        if (!StringUtil.isEmpty(planBean.getArriveDateStr())) {
            String date = planBean.getArriveDateStr().replace("/", "-");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            planBean.setEta(formatter.parse(date));
        }
        if (planBean.getWeightVal() != null && planBean.getArriveBoxes() != null) {
            BigDecimal tempWeight = DecimalUtil.multiply(planBean.getWeightVal(), new BigDecimal((planBean.getArriveBoxes())));
            planBean.setArriveQut(tempWeight);
        }

        logger.debug("修改交货期计划");
        planBean.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.modifyDeliveryPlan(planBean);
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            throw new BusinessException(rsResponse.getMessage());
        }
        return rsResponse.getStatus();
    }


    /**
     * 跳转到新增跳转到交货期计划页面
     */
    @RequestMapping(value = "addDeliveryPlan", method = RequestMethod.POST)
    public String addDeliveryPlan(SSC11304Param param, Model model) {
        logger.debug("跳转到交货期计划");
        List<SSC11304ProductBean> pdBeans = ISSCContractRestUtil.findProductsByContractId(param);
        for (SSC11304ProductBean pdDetail : pdBeans) {
            int purchaseBoxes = pdDetail.getProductBox();
            int plannedBoxes = pdDetail.getPlannedBoxes();
            pdDetail.setPlannedBoxes(purchaseBoxes - plannedBoxes);
        }

        model.addAttribute("pdBeans", pdBeans);
        model.addAttribute("contractId", param.getContractId());// 合同编号
        return "ssc/SSC1130402";
    }

    /**
     * 调用接口获取产品包装信息
     */
    @RequestMapping(value = "findProductPackage", method = RequestMethod.POST)
    @ResponseBody
    public SSC11304PackageBean findProductPackage(PDInfoParam param) {
        logger.debug("跳转到合同包材页面");
        RsResponse<ProductBeanResult> rsResponse = ISSCRestUtil.findProductPackage(param);

        SSC11304PackageBean ssc11304PackageBean = new SSC11304PackageBean();
        if (SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())) {
            ProductBeanResult productBeanResult = rsResponse.getResult();
            List<PDInfoResult> pdInfoResults = productBeanResult.getResult();

            if (!CollectionUtils.isEmpty(pdInfoResults)) {
                PDInfoResult pdInfoResult = pdInfoResults.get(NumberConst.IntDef.INT_ZERO);
                ssc11304PackageBean.setCartonQuaSta(pdInfoResult.getNormsOutTexture());
                ssc11304PackageBean.setCartonSpecSta(pdInfoResult.getNormsOutSize());
                ssc11304PackageBean.setInnerBagQuaSta(pdInfoResult.getNormsTexture());
                ssc11304PackageBean.setInnerBagSpecSta(pdInfoResult.getNormsSize());
                ssc11304PackageBean.setCartonQua(pdInfoResult.getNormsOutTexture());
                ssc11304PackageBean.setCartonSpec(pdInfoResult.getNormsOutSize());
                ssc11304PackageBean.setInnerBagQua(pdInfoResult.getNormsTexture());
                ssc11304PackageBean.setInnerBagSpec(pdInfoResult.getNormsSize());
            }
        }
        return ssc11304PackageBean;
    }

    /**
     * 新增交货计划
     */
    @RequestMapping(value = "/deliveryPlan/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveDeliveryPlan(@RequestParam String jsonStr) throws ParseException {
        String loginId = this.getLoginUser().getEmplId();

        JSONArray jsonArr = JSON.parseArray(jsonStr);
        List<SSC11304DeliveryPlanBean> deliveryPlans = new ArrayList<SSC11304DeliveryPlanBean>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < jsonArr.size(); ++i) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            long contractId = jsonObj.getLongValue("contractId");
            String arriveDateStr = jsonObj.getString("expectArriveDate");
            String remark = jsonObj.getString("remark");
            String productCode = jsonObj.getString("productCode");
            String productName = jsonObj.getString("productName");
            int arriveBox = jsonObj.getIntValue("boxes");
            BigDecimal weightVal = jsonObj.getBigDecimal("weight");

            SSC11304DeliveryPlanBean deliveryPlan = new SSC11304DeliveryPlanBean();
            deliveryPlan.setContractId(contractId);
            deliveryPlan.setArriveDateStr(arriveDateStr);
            deliveryPlan.setEta(sdf.parse(arriveDateStr));
            deliveryPlan.setRemark(remark);
            deliveryPlan.setPdCode(productCode);
            deliveryPlan.setPdDesc(productName);
            deliveryPlan.setArriveBoxes(arriveBox);
            deliveryPlan.setWeightVal(weightVal);
            deliveryPlan.setArriveQut(new BigDecimal(arriveBox).multiply(weightVal));
            deliveryPlan.setCrtId(loginId);
            deliveryPlans.add(deliveryPlan);
        }

        SSC11304Param ssc11304Param = new SSC11304Param();
        ssc11304Param.setDeliveryPlans(deliveryPlans);
        return ISSCContractRestUtil.saveDeliveryPlan(ssc11304Param);
    }

    private void setDeliveryPlanBeanPar(SSC11304DeliveryPlanBean dp, String pdCodes, String productBox, String weightS) {
        List<String> productCodes = new ArrayList<>();
        List<Integer> arrivebBoxes = new ArrayList<>();
        List<BigDecimal> weightList = new ArrayList<>();
        String[] pdCodeArray = pdCodes.split(",");
        String[] boxesArray = productBox.split(",");// 正常情况下  pdids 的长度 和 boxes 长度相同
        String[] weightArray = weightS.split(",");
        for (int i = 0; i < pdCodeArray.length; i++) {
            productCodes.add(pdCodeArray[i]);
            arrivebBoxes.add(Integer.parseInt(boxesArray[i]));
            weightList.add(new BigDecimal(weightArray[i]));
        }
        dp.setProductCodes(productCodes);
        dp.setArriveQuts(arrivebBoxes);
        dp.setWeihtS(weightList);
    }


    @RequestMapping(value = "delContractPackgeM", method = RequestMethod.POST)
    @ResponseBody
    public String delContractPackgeM(SscPackageMaterialInfo packageMaterialInfo) {
        packageMaterialInfo.setUpdId(super.getLoginUser().getEmplId());
        RsResponse<SSC11304Result> response = ISSCContractRestUtil.delContractPackgeM(packageMaterialInfo);
        if (SystemConst.RsStatus.FAIL.equals(response.getStatus())) {
            throw new BusinessException(response.getMessage());
        }
        return response.getStatus();
    }

    /**
     * 生成发货订单
     */
    @RequestMapping(value = "creatDeliveryOrder", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<Long> creatDeliveryOrder(SSC11305RsParam param) {
        super.setCommonParam(param);
        param.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));
        param.setCrtTime(DateTimeUtil.getCustomerDate());
        param.setUpdTime(DateTimeUtil.getCustomerDate());
        RsResponse<Long> rsResponse = ISSCContractRestUtil.createDeliveryOrder(param);
        return rsResponse;
    }


    @RequestMapping(value = "getEffectBoxNum", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse<SSC11304Result> getEffectBoxNum(SSC11304DeliveryPlanBean dp, String pdCodes, String productBoxs, String arriveDateStr, String weightS) {
        if (!StringUtil.isEmpty(pdCodes) && !StringUtil.isEmpty(productBoxs) && !StringUtil.isEmpty(weightS)) {
            this.setDeliveryPlanBeanPar(dp, pdCodes, productBoxs, weightS);
        }
        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.checkEffectBoxNum(dp);

        // 验证两项  时间    箱数
        String message = null;
        if (rsResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            SSC11304Result result = rsResponse.getResult();
            List<SSC11304DeliveryPlanBean> dpList = result.getDpPageResult();
            for (SSC11304DeliveryPlanBean dbDp : dpList) {
                if (dbDp.getArriveBoxes() < 0) {
                    message += "产品名:" + dbDp.getPdName() + "  输入的箱数，应不大于" + dbDp.getEffctNum() + " <br> ;";
                } else {

                }
            }
            boolean evaFlag = false;
            if (StringUtil.isEmpty(result.getEvaDate()) || dp.getArriveDateStr().equals(result.getEvaDate())) {//  日期判断
                evaFlag = true;
            }
            boolean dpListFlag = message == null;
            if (!(dpListFlag && evaFlag)) {//当箱数  和时间都成功  才可以保存
                if (evaFlag) {
                    result.setEvaDate(null);
                }
                if (dpListFlag) {
                    result.setDpPageResult(null);
                }
                rsResponse.setMessage(message);
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            }

        }
        return rsResponse;
    }


    public SscBusinessTerms findSscContractBusiness(SscBusinessTerms param) {
        RsResponse<SscBusinessTerms> rsResponse = ISSCContractRestUtil.findSscContractBusiness(param);
        if (rsResponse.getStatus().equals(SystemConst.RsStatus.FAIL)) {
            return new SscBusinessTerms();
        }
        return rsResponse.getResult();
    }

    /**
     * 选择计划到货日期弹出框初始化
     */
    @RequestMapping(value = "chooseInit", method = RequestMethod.POST)
    public String chooseInit(Model model, SSC11304Param param) {
        logger.debug("选择计划到货日期弹出框初始化");

        PageResult<SSC11304DeliveryPlanBean> result = new PageResult<SSC11304DeliveryPlanBean>();
        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.findDeliveryBatchList(param);

        List<SSC11304DeliveryPlanBean> pdBeans = null;
        if (rsResponse == null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {// 失败
            pdBeans = new ArrayList<>();
        } else {// 成功
            SSC11304Result ssc11304Result = rsResponse.getResult();
            pdBeans = ssc11304Result.getDpPageResult();
        }
        result.setData(pdBeans);

        model.addAttribute("pdBeans", pdBeans);
        model.addAttribute("contractCode", param.getContractCode());
        model.addAttribute("contractId", param.getContractId());
        model.addAttribute("purchaserCode", param.getPurchaserCode());
        return "ssc/SSC1130405";
    }


    @RequestMapping(value = "createContracts", method = RequestMethod.POST)
    public synchronized String createContracts(Model model, SscContractBasic sscContractBasic) {
        sscContractBasic.setContractStatus(StringUtil.toString(SscConstant.SscOrderStatus.PENDING_AUDIT));
        sscContractBasic.setContractCode(this.buildMaxContractCode());
        sscContractBasic.setCrtId(super.getLoginUser().getEmplId());

        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.createContracts(sscContractBasic);
        sscContractBasic.setContractId(rsResponse.getResult().getContractId());
        return init(sscContractBasic, model);
    }


    @RequestMapping(value = "checkBid", method = RequestMethod.POST)
    @ResponseBody
    public String checkBid(SscBidBasicInfo sscBidBasicInfo) {
        RsResponse<SSC11304Result> rsResponse = ISSCContractRestUtil.checkBid(sscBidBasicInfo);
        return rsResponse.getStatus();
    }


    /**
     * 先从数据库查询   没有就新增    有就修改
     * 生成合同编号  HT201607130001
     */
    private synchronized String buildMaxContractCode() {
        String contractCode = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String tempContractCode = "HT" + formatter.format(new Date());
        BaseParam param = new BaseParam();
        RsResponse<String> rsResponse = ISSCContractRestUtil.findDBContractCode(param);
        String dbContractCode = rsResponse.getResult();
        if (!StringUtil.isEmpty(dbContractCode)) {//已经有了合同号
            if (dbContractCode.contains(tempContractCode)) {//  有前缀一样
                String lastF = dbContractCode.substring(dbContractCode.length() - 4); // 先截取后四位
                String numStr = (Integer.parseInt(lastF) + 1) + "";
                if (numStr.length() == 1) {
                    contractCode = tempContractCode + "000" + numStr;
                } else if (numStr.length() == 2) {
                    contractCode = tempContractCode + "00" + numStr;
                } else if (numStr.length() == 3) {
                    contractCode = tempContractCode + "0" + numStr;
                } else if (numStr.length() > 3) {
                    contractCode = tempContractCode + numStr;
                }
            } else {
                contractCode = tempContractCode + "0001";
            }
        } else {
            contractCode = tempContractCode + "0001";
        }
        return contractCode;
    }

    @RequestMapping(value = "/beforeConfirmation", method = RequestMethod.POST)
    @ResponseBody
    public String beforeConfirmation(SSC11304Param ssc11304Param) {
        List<SSC11304ProductBean> ssc11304ProductBeans = ISSCContractRestUtil.findProductsByContractId(ssc11304Param);
        for (SSC11304ProductBean ssc11304ProductBean : ssc11304ProductBeans) {
            if (null == ssc11304ProductBean.getDownPayment()) {
                return "no_first_percent";  //产品没有预付款比例
            }
        }
        return "not_missing";   //不缺
    }

}
