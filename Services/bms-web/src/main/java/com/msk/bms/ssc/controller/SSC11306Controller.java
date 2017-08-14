package com.msk.bms.ssc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.seller.bean.ISLEnterpriseRsResult;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.SlProductRsBean;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 发货订单明细Controller
 *
 * @author yang_yang
 */
@Controller
@RequestMapping("SSC11306")
public class SSC11306Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11306Controller.class);

    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String show(SSC11306RsParam ssc11306RsParam, Model model) {
        logger.debug("发货订单明细页面初始化");

        String navigation = null;
        //确认单详细跳转
        if (!StringUtil.isNullOrEmpty(ssc11306RsParam.getDeliveryConfirmCode())) {
            navigation = "confirm";
            model.addAttribute("deliveryConfirmCode", ssc11306RsParam.getDeliveryConfirmCode());
        }
        //差异单详细跳转
        if (ssc11306RsParam.getDifferId() != null && ssc11306RsParam.getDifferId() != 0) {
            navigation = "differ";
            model.addAttribute("differId", ssc11306RsParam.getDifferId());
        }
        //资金池一览跳转
        if (!StringUtil.isNullOrEmpty(ssc11306RsParam.getPaymentId())) {
            navigation = "payment";
        }
        if (ssc11306RsParam.getPaymentDetailId() != null && ssc11306RsParam.getPaymentDetailId() != 0) {
            navigation = "poolDetail";
            model.addAttribute("paymentId", ssc11306RsParam.getPaymentDetailId());
        }
        if (ssc11306RsParam.getPaymentRequestId() != null && ssc11306RsParam.getPaymentRequestId() != 0) {
            navigation = "request";
        }
        if (null != ssc11306RsParam.getVerificationId()) {
            navigation = "verification";
            model.addAttribute("verificationId", ssc11306RsParam.getVerificationId());
        }
        if (null != ssc11306RsParam.getPaymentRequestId()&& ssc11306RsParam.getPaymentRequestId()!=0) {
            navigation = "verification";
            model.addAttribute("verificationId", ssc11306RsParam.getVerificationId());
        }
        if (null != ssc11306RsParam.getPaymentRequestDetailId()) {
            navigation = "requestDetail";
        }


        model.addAttribute("navigation", navigation);
        return this.init(ssc11306RsParam, model);
    }

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SSC11306RsParam param,Model model) {
        logger.debug("发货订单明细页面初始化");
        SSC11306RsBean orderBasic = new SSC11306RsBean();

        //采购商 1 2 3
        List<Integer> sellerCodeList = new ArrayList<>();
        sellerCodeList.add(NumberConst.IntDef.INT_ONE);
        sellerCodeList.add(NumberConst.IntDef.INT_TWO);
        sellerCodeList.add(NumberConst.IntDef.INT_THREE);

        ISLSellerRsParam islSellerRsParam = new ISLSellerRsParam();
        islSellerRsParam.setSlMainClassList(sellerCodeList);
        List<ISLEnterpriseRsResult> purchaserList =  ISSCRestUtil.getSlEnterpriseList(islSellerRsParam);

        model.addAttribute("purchaserList", purchaserList);

        if(StringUtil.isNullOrEmpty(param.getDeliveryId())){
            //新增
            model.addAttribute("isAdd", true);
        }else{
            //修改
            SSC11306RsParam ssc11306RsParam = new SSC11306RsParam();
            ssc11306RsParam.setDeliveryId(param.getDeliveryId());

            orderBasic = ISSCDeliveryOrderRestUtil.findOrderBasic(ssc11306RsParam);
            model.addAttribute("deliveryId", param.getDeliveryId());
        }

        //获取发货订单产品信息
        param.setPaging(false);
        PageResult<SSC1130601RsBean> pdResult = ISSCDeliveryOrderRestUtil.searchOrderPd(param);

        //发货箱数合计
        BigDecimal sumProductBox = new BigDecimal(0);
        //发货数量合计
        BigDecimal sumProductQua = new BigDecimal(0);
        //结算标准价合计
        BigDecimal sumStandardPrice = new BigDecimal(0);
        //合计
        BigDecimal sumProductValue = new BigDecimal(0);

        List<SSC1130601RsBean> allList = pdResult.getData();
        for (SSC1130601RsBean bean : allList) {
            sumProductBox = DecimalUtil.add(sumProductBox,new BigDecimal(bean.getProductBox()));
            sumProductQua = DecimalUtil.add(sumProductQua,bean.getProductQua());
            sumStandardPrice = DecimalUtil.add(sumStandardPrice,bean.getSettkementStandardPrice());
            sumProductValue = DecimalUtil.add(sumProductValue,bean.getProductValue());
        }

        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> lgcsAreaList = ISSCRestUtil.getLgcsAreaList(districtParam);

        model.addAttribute("orderBasic", orderBasic);
        model.addAttribute("sumProductBox", sumProductBox);
        model.addAttribute("sumProductQua", sumProductQua);
        model.addAttribute("sumStandardPrice", sumStandardPrice);
        model.addAttribute("sumProductValue", sumProductValue);
        model.addAttribute("lgcsAreaList",lgcsAreaList);

        return "ssc/SSC11306";
    }

    /**
     * 分页查询数据
     *
     * @return 订单产品列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC1130601RsBean> search(SSC11306RsParam ssc11306RsParam) {

        ssc11306RsParam.setPaging(false);
        PageResult<SSC1130601RsBean> result = ISSCDeliveryOrderRestUtil.searchOrderPd(ssc11306RsParam);

        //不分页
        result.setRecordsTotal(NumberConst.IntDef.INT_ZERO);

        return result;
    }

    /**
     * 删除发货订单产品信息
     *
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(SSC1130601RsBean ssc1130601RsBean,Model model) {

        super.setCommonParam(ssc1130601RsBean);

        ssc1130601RsBean.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc1130601RsBean.setDelFlg(SystemConst.DelFlg.DISABLE);
        ssc1130601RsBean.setDetailId(Long.valueOf(ssc1130601RsBean.getDetailId()));

        RsResponse<SSC1130601RsBean> rsResponse = ISSCDeliveryOrderRestUtil.modifyOrderPd(ssc1130601RsBean);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }
        //删除产品修改发货订单状态为待确认
        SSC11306RsParam ssc11306RsParam = new SSC11306RsParam();
        this.setCommonParam(ssc11306RsParam);
        ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc11306RsParam.setDeliveryId(String.valueOf(ssc1130601RsBean.getDeliveryId()));

        SSC11306RsBean orderBasic = ISSCDeliveryOrderRestUtil.findOrderBasic(ssc11306RsParam);
        //本次运费及总金额
        this.getAmount(ssc11306RsParam,orderBasic);

        ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);

        return this.init(ssc11306RsParam, model);
    }

    /**
     * 修改发货订单产品信息
     *
     */
    @RequestMapping(value = "modifyOrderPd", method = RequestMethod.POST)
    @ResponseBody
    public String  modifyOrderPd(SSC11306RsParam ssc11306RsParam) {
        String jsonStr = ssc11306RsParam.getJsonStr();
        String staus = SystemConst.RsStatus.FAIL;
        if(!StringUtil.isNullOrEmpty(jsonStr)) {
            Map<String, SSC1130601RsBean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SSC1130601RsBean>>() {
            });

            if (map != null && map.size() != 0) {
                for(SSC1130601RsBean bean: map.values()){
                    bean.setUpdId(ssc11306RsParam.getUpdId());
                    bean.setUpdTime(DateTimeUtil.getCustomerDate());

                    RsResponse<SSC1130601RsBean> rsResponse = ISSCDeliveryOrderRestUtil.modifyOrderPd(bean);

                    staus = rsResponse.getStatus();

                    if (SystemConst.RsStatus.FAIL.equals(staus)){
                        throw new BusinessException(rsResponse.getMessage());
                    }
                }
            }
        }
        super.setCommonParam(ssc11306RsParam);
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        //修改后状态重置为待确认1
        ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));

        SSC11306RsBean orderBasic = ISSCDeliveryOrderRestUtil.findOrderBasic(ssc11306RsParam);
        //本次运费及总金额
        this.getAmount(ssc11306RsParam,orderBasic);

        ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);

        return staus;
    }

    /**
     * 修改发货订单基本信息
     *
     */
    @RequestMapping(value = "modifyOrderBasic", method = RequestMethod.POST)
    public String modifyOrderBasic(SSC11306RsParam ssc11306RsParam,Model model) {

        super.setCommonParam(ssc11306RsParam);
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        //获取登录信息
        LoginUser loginUser = super.getLoginUser();
        if(!StringUtil.isNullOrEmpty(ssc11306RsParam.getDeliveryStatus())){
            if(String.valueOf(SscConstant.DeliveryOrderStatus.PUR_AUDIT).equals(ssc11306RsParam.getDeliveryStatus())){
                ssc11306RsParam.setPurchaserAuditId(loginUser.getEmplId());
                ssc11306RsParam.setPurchaserAuditName(loginUser.getEmplName());
                ssc11306RsParam.setPurchaserAuditTime(DateTimeUtil.getCustomerDate());
            }
            if(String.valueOf(SscConstant.DeliveryOrderStatus.SUP_CONFIRM).equals(ssc11306RsParam.getDeliveryStatus())){
                ssc11306RsParam.setSupplierConfirmId(loginUser.getEmplId());
                ssc11306RsParam.setSupplierConfirmName(loginUser.getEmplName());
                ssc11306RsParam.setSupplierConfirmTime(DateTimeUtil.getCustomerDate());
            }
        }

        //获取发货订单基本信息
        String deliveryStatus = null;
        SSC11306RsBean orderBasic = ISSCDeliveryOrderRestUtil.findOrderBasic(ssc11306RsParam);
        if(orderBasic != null){
            deliveryStatus = orderBasic.getDeliveryStatus();
        }

        //发货订单状态设置
        if(StringUtil.isNullOrEmpty(ssc11306RsParam.getDeliveryStatus())){
            if(String.valueOf(SscConstant.DeliveryOrderStatus.NEW).equals(deliveryStatus)){
                ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.NEW));
            }else{
                ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));
            }
        }else{
            if(!StringUtil.isNullOrEmpty(deliveryStatus)){
                if(!String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM).equals(deliveryStatus) &&
                        !String.valueOf(SscConstant.DeliveryOrderStatus.CONFIRM).equals(deliveryStatus)){
                    ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.CONFIRM));
                }
            }
        }

        //获取本次运费及总金额
        this.getAmount(ssc11306RsParam,orderBasic);

        RsResponse<SSC11306RsBean> rsResponse = ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }

        return this.init(ssc11306RsParam, model);
    }

    /**
     * 获取发货订单运费及总金额
     * orderBasic 发货订单基本信息
     */
    private SSC11306RsParam getAmount(SSC11306RsParam ssc11306RsParam,SSC11306RsBean orderBasic) {
        //选择到岸价时，运费单价，每吨运费及本次运费置空
        if(String.valueOf(SscConstant.FreightSettleMethod.CIF).equals(ssc11306RsParam.getFreightSettleMethod())){
            ssc11306RsParam.setFreightUnit(BigDecimal.ZERO);
            ssc11306RsParam.setTransportUnit(BigDecimal.ZERO);
            ssc11306RsParam.setTransportCost(BigDecimal.ZERO);
        }else{
            //获取发货订单产品信息
            ssc11306RsParam.setPaging(false);
            PageResult<SSC1130601RsBean> pdResult = ISSCDeliveryOrderRestUtil.searchOrderPd(ssc11306RsParam);

            //发货箱数合计
            BigDecimal sumProductBox = new BigDecimal(0);
            //发货数量合计
            BigDecimal sumProductQua = new BigDecimal(0);
            //结算标准价合计
            BigDecimal sumStandardPrice = new BigDecimal(0);
            //合计
            BigDecimal sumProductValue = new BigDecimal(0);

            List<SSC1130601RsBean> allList = pdResult.getData();
            for (SSC1130601RsBean bean : allList) {
                sumProductBox = DecimalUtil.add(sumProductBox,new BigDecimal(bean.getProductBox()));
                sumProductQua = DecimalUtil.add(sumProductQua,bean.getProductQua());
                sumStandardPrice = DecimalUtil.add(sumStandardPrice,bean.getSettkementStandardPrice());
                sumProductValue = DecimalUtil.add(sumProductValue,bean.getProductValue());
            }

            //获取本次运费及总金额
            if(orderBasic != null){
                BigDecimal transportUnit = ssc11306RsParam.getTransportUnit();
                if(transportUnit == null){
                    transportUnit = orderBasic.getTransportUnit();
                }

                BigDecimal transportCost = DecimalUtil.multiply(DecimalUtil.divide(sumProductQua,new BigDecimal(NumberConst.IntDef.INT_THOUSAND)), transportUnit);
                //2位小数
                transportCost = transportCost.setScale(2,BigDecimal.ROUND_HALF_UP);

                BigDecimal amount = DecimalUtil.add(transportCost,sumProductValue);
                amount = amount.setScale(2,BigDecimal.ROUND_HALF_UP);

                if(orderBasic.getDeliveryId() != null){
                    ssc11306RsParam.setAmount(amount);
                    ssc11306RsParam.setTransportCost(transportCost);
                }
            }
        }

        return ssc11306RsParam;
    }

    /**
     * 发货订单明细-新增基本信息
     *
     */
    @RequestMapping(value = "saveOrderBasic", method = RequestMethod.POST)
    public String saveOrderBasic(SSC11306RsParam ssc11306RsParam,Model model) {

        super.setCommonParam(ssc11306RsParam);

        //关联合同类型：1：已关联 2：未关联
        if(!StringUtil.isNullOrEmpty(ssc11306RsParam.getContractId())){
            ssc11306RsParam.setContractRelationType(SscConstant.RelationType.RELATED);
        }else{
            ssc11306RsParam.setContractRelationType(SscConstant.RelationType.UNRELATE);
        }

        ssc11306RsParam.setCrtTime(DateTimeUtil.getCustomerDate());
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.NEW));

        RsResponse<SSC11306RsBean> result = ISSCDeliveryOrderRestUtil.saveOrderBasic(ssc11306RsParam);

        String deliveryId = "";
        if(result.getResult() != null){
            deliveryId = result.getResult().getDeliveryId().toString();
        }

        ssc11306RsParam.setDeliveryId(deliveryId);

        return this.init(ssc11306RsParam, model);
    }

    /**
     * 发货订单明细-新增产品信息
     *
     */
    @RequestMapping(value = "saveOrderPd", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrderPd(SSC1130601RsBean ssc1130601RsBean) {

        super.setCommonParam(ssc1130601RsBean);
        ssc1130601RsBean.setCrtTime(DateTimeUtil.getCustomerDate());
        ssc1130601RsBean.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11306RsBean> rsResponse = ISSCDeliveryOrderRestUtil.saveOrderPd(ssc1130601RsBean);

        String staus = rsResponse.getStatus();
        if(SystemConst.RsStatus.SUCCESS.equals(staus)){
            SSC11306RsParam ssc11306RsParam = new SSC11306RsParam();
            super.setCommonParam(ssc11306RsParam);
            ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());
            //状态
            ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));
            ssc11306RsParam.setDeliveryId(ssc1130601RsBean.getDeliveryId().toString());

            SSC11306RsBean orderBasic = ISSCDeliveryOrderRestUtil.findOrderBasic(ssc11306RsParam);
            //本次运费及总金额
            this.getAmount(ssc11306RsParam,orderBasic);

            ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);
        }

        return staus;
    }

    /**
     * 发货订单明细-修改
     *
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modify(SSC11306RsParam ssc11306RsParam,Model model) {

        super.setCommonParam(ssc11306RsParam);
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        //修改后状态重置为待确认1
        ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));

        ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);

        String jsonStr = ssc11306RsParam.getJsonStr();
        if(!StringUtil.isNullOrEmpty(jsonStr)){
            Map<String, SSC1130601RsBean> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, SSC1130601RsBean>>() {
            });

            if(map != null && map.size() != 0){
                for(SSC1130601RsBean bean: map.values()){
                    bean.setUpdId(ssc11306RsParam.getUpdId());
                    bean.setUpdTime(DateTimeUtil.getCustomerDate());

                    ISSCDeliveryOrderRestUtil.modifyOrderPd(bean);
                }
            }
        }

        return this.init(ssc11306RsParam, model);
    }

    /**
     * 发货订单明细-选择产品初始化
     * @return
     */
    @RequestMapping(value = "init2",method = RequestMethod.POST)
    public String initPd(SSC11306RsParam ssc11306RsParam,Model model){
        logger.debug("选择产品初始化");

        List<SlProductRsBean> productList = new ArrayList<>();

        String slCode = null;
        String deliveryId = null;
        String deliveryCode = null;
        Long supplierId = null;
        //从合同生成的发货订单有，获取生成的该发货订单的产品
        if(ssc11306RsParam != null && !StringUtil.isNullOrEmpty(ssc11306RsParam.getContractId())
                && SscConstant.RelationType.NORMAL == ssc11306RsParam.getContractRelationType()){

            ssc11306RsParam.setPaging(false);
            ssc11306RsParam.setDelFlg(SystemConst.DelFlg.DISABLE);
            deliveryId = ssc11306RsParam.getDeliveryId();
            deliveryCode = ssc11306RsParam.getDeliveryCode();

            PageResult<SSC1130601RsBean> result = ISSCDeliveryOrderRestUtil.searchOrderPd(ssc11306RsParam);

            if(result != null) {
                List<SSC1130601RsBean> allList = result.getData();

                for (SSC1130601RsBean bean : allList) {
                    SlProductRsBean productRsBean = new SlProductRsBean();
                    productRsBean.setPdCode(bean.getPdCode());
                    productRsBean.setPdDesc(bean.getPdDesc());
                    productRsBean.setPdClassesCode(bean.getClassesCode());
                    productRsBean.setPdClassesName(bean.getClassesName());
                    productRsBean.setMachiningCode(bean.getMachiningCode());
                    productRsBean.setMachiningName(bean.getMachiningName());
                    productRsBean.setPdBreedCode(bean.getBreedCode());
                    productRsBean.setPdBreedName(bean.getBreedName());
                    productRsBean.setPdFeatureCode(bean.getFeatureCode());
                    productRsBean.setPdFeatureName(bean.getFeatureName());
                    productRsBean.setWeightCode(bean.getWeightCode());
                    productRsBean.setWeightName(bean.getWeightName());
                    productRsBean.setSlTncGradeCode(Integer.valueOf(bean.getGradeCode()));
                    productRsBean.setSlTncGradeName(bean.getGradeName());
                    productRsBean.setNormsCode(bean.getNormsCode());
                    productRsBean.setNormsOut(bean.getNormsName());
                    productRsBean.setWeightVal(bean.getWeightVal());
                    productRsBean.setBrandName(bean.getBrandName());
                    productRsBean.setBrandId(bean.getBrandId().intValue());
                    productRsBean.setBrandEpId(bean.getBrandEpId().intValue());

                    productList.add(productRsBean);
                }
            }
        }else{
            //新建的发货订单无合同Id，从卖家获取产品
            //查询卖家产品代码、名称和等级
            ISLSellerRsParam sellerRsParam = new ISLSellerRsParam();

            if(ssc11306RsParam != null){
                slCode = ssc11306RsParam.getPurchaserCode();
                deliveryId = ssc11306RsParam.getDeliveryId();
                deliveryCode = ssc11306RsParam.getDeliveryCode();
                supplierId = ssc11306RsParam.getSupplierId();
            }
            sellerRsParam.setSlCode(slCode);
            sellerRsParam.setProdEpId(supplierId);
            productList = ISSCRestUtil.getSlProductList(sellerRsParam);
        }

        model.addAttribute("productList", productList);
        model.addAttribute("deliveryId", deliveryId);
        model.addAttribute("deliveryCode", deliveryCode);

        return "ssc/SSC1130601";
    }

    /**
     * 获取发货订单产品信息-判断是否重复添加
     */
    @RequestMapping(value = "findOrderPd", method = RequestMethod.POST)
    @ResponseBody
    public SSC1130601RsBean findOrderPd(SSC11306RsParam ssc11306RsParam) {
        return ISSCDeliveryOrderRestUtil.findOrderPd(ssc11306RsParam);
    }

    /**
     * 修改已存在的产品信息
     */
    @RequestMapping(value = "modifyOrderPdInfo", method = RequestMethod.POST)
    @ResponseBody
    public String modifyOrderPdInfo(SSC1130601RsBean ssc1130601RsBean) {

        super.setCommonParam(ssc1130601RsBean);

        ssc1130601RsBean.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC1130601RsBean> rsResponse = ISSCDeliveryOrderRestUtil.modifyOrderPd(ssc1130601RsBean);

        String staus = rsResponse.getStatus();
        if(SystemConst.RsStatus.SUCCESS.equals(staus)){
            //修改发货订单状态为待确认
            SSC11306RsParam ssc11306RsParam = new SSC11306RsParam();
            this.setCommonParam(ssc11306RsParam);
            ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.PENDING_CONFIRM));
            ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());
            ssc11306RsParam.setDeliveryId(String.valueOf(ssc1130601RsBean.getDeliveryId()));

            SSC11306RsBean orderBasic = ISSCDeliveryOrderRestUtil.findOrderBasic(ssc11306RsParam);
            //本次运费及总金额
            this.getAmount(ssc11306RsParam,orderBasic);

            RsResponse<SSC11306RsBean> rsRes = ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);
            staus = rsRes.getStatus();
        }else{
            throw new BusinessException(rsResponse.getMessage());
        }

        return staus;
    }

    /**
     * 选择合同
     */
    @RequestMapping(value = "chooseContract", method = RequestMethod.POST)
    public String chooseContract() {
        logger.info("选择合同页面初始化");
        return "ssc/SSC1130602";
    }

    /**
     * 关联合同
     */
    @RequestMapping(value = "relevanceContract", method = RequestMethod.POST)
    public String relevanceContract(SSC11306RsParam ssc11306RsParam, Model model) {
        logger.info("关联合同页面初始化");

        //获取登录信息
        LoginUser loginUser = super.getLoginUser();

        //关联发货订单
        //关联合同类型：1：已关联
        ssc11306RsParam.setContractRelationType(SscConstant.RelationType.RELATED);
        ssc11306RsParam.setUpdId(loginUser.getEmplId());
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11306RsBean> rsResponse = ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }

        //关联发货确认单
        SSC11315Param ssc11315Param = new SSC11315Param();

        ssc11315Param.setDeliveryId(ssc11306RsParam.getDeliveryId());
        ssc11315Param.setContractId(ssc11306RsParam.getContractId());
        ssc11315Param.setContractCode(ssc11306RsParam.getContractCode());
        ssc11315Param.setUpdId(loginUser.getEmplId());
        ssc11315Param.setUpdTime(DateTimeUtil.getCustomerDate());

        ISSCDeliveryConfirmOrderRestUtil.modifyDeliveryConfirm(ssc11315Param);

        //关联预入库通知单
        SSC11317RsParam ssc11317RsParam = new SSC11317RsParam();
        ssc11317RsParam.setDeliveryId(Long.valueOf(ssc11306RsParam.getDeliveryId()));

        List<SSC11317PreIntoBean> preIntoList = ISSCDeliveryPreInfoRestUtil.findPreIntoListByDeliveryId(ssc11317RsParam);

        for (SSC11317PreIntoBean bean : preIntoList) {
            //预入库通知单关联合同
            ssc11317RsParam.setContractId(Long.valueOf(ssc11306RsParam.getContractId()));
            ssc11317RsParam.setContractCode(ssc11306RsParam.getContractCode());
            ssc11317RsParam.setDeliveryPreIntoId(bean.getDeliveryPreIntoId());
            ssc11317RsParam.setUpdId(loginUser.getEmplId());
            ssc11317RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

            ISSCDeliveryPreInfoRestUtil.modifyDeliveryPreInto(ssc11317RsParam);
        }

        //关联差异单
        SSC11311Bean ssc11311Bean = new SSC11311Bean();

        ssc11311Bean.setUpdId(loginUser.getEmplId());
        ssc11311Bean.setUpdTime(DateTimeUtil.getCustomerDate());

        ssc11311Bean.setDeliveryId(Long.valueOf(ssc11306RsParam.getDeliveryId()));
        ssc11311Bean.setContractId(Long.valueOf(ssc11306RsParam.getContractId()));
        ssc11311Bean.setContractCode(ssc11306RsParam.getContractCode());

        ISSCDifferRestUtil.confirmDifferBasic(ssc11311Bean);

        //关联付款申请
        SSC11308RsBean ssc11308RsBean = new SSC11308RsBean();

        ssc11308RsBean.setDeliveryId(Long.valueOf(ssc11306RsParam.getDeliveryId()));
        ssc11308RsBean.setContractId(Long.valueOf(ssc11306RsParam.getContractId()));
        ssc11308RsBean.setContractName(ssc11306RsParam.getContractCode());
        ssc11308RsBean.setIsRelate(SystemConst.RsStatus.SUCCESS);

        ssc11308RsBean.setUpdId(loginUser.getEmplId());
        ssc11308RsBean.setUpdTime(DateTimeUtil.getCustomerDate());

        ISSCPaymentRestUtil.saveOrModifyPaymentRequest(ssc11308RsBean);

        return this.init(ssc11306RsParam, model);
    }

}
