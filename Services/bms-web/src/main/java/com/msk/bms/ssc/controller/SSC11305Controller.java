package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCDeliveryConfirmOrderRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDeliveryOrderRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 发货订单一览Controller
 *
 * @author Peng_Hao
 */
@Controller
@RequestMapping("SSC11305")
public class SSC11305Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(SSC11305Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(@RequestParam(value = "contractCode", required = false) String contractCode,
                       @RequestParam(value = "contractName", required = false) String contractName,
                       @RequestParam(value = "purchaserName", required = false) String purchaserName,
                       @RequestParam(value = "supplierName", required = false) String supplierName, Model model) {
        logger.debug("发货订单一览初始化");
        if (!StringUtil.isNullOrEmpty(contractCode)) {
            model.addAttribute("contractCode", contractCode);
            model.addAttribute("contractName", contractName);
            model.addAttribute("supplierName", supplierName);
            model.addAttribute("purchaserName", purchaserName);
        }

        //从redis获取发货订单类型
        Map<String, String> deliveryOrderStatusMap = CodeMasterManager.findCodeMasterMap("DeliveryOrderStatus");
        List deliveryOrderStatusList = new ArrayList(deliveryOrderStatusMap.entrySet());

        model.addAttribute("deliveryOrderStatusList", deliveryOrderStatusList);

        //关联合同类型
        Map<String, String> relationTypeMap = CodeMasterManager.findCodeMasterMap("RelationType");
        List relationTypeList = new ArrayList(relationTypeMap.entrySet());

        model.addAttribute("relationTypeList", relationTypeList);

        return "ssc/SSC11305";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11305RsBean> search(SSC11305RsParam ssc11305RsParam, @RequestParam(value = "contractCode", required = false) String contractCode) {
        PageResult<SSC11305RsBean> result = new PageResult();
        String delFlg = SystemConst.DelFlg.ENABLE;
        String contractName = DbUtils.buildLikeCondition(String.valueOf(ssc11305RsParam.getFilterMap().get("contractName")).trim(), DbUtils.LikeMode.PARTIAL);
        String supplierName = DbUtils.buildLikeCondition(String.valueOf(ssc11305RsParam.getFilterMap().get("supplierName")).trim(), DbUtils.LikeMode.PARTIAL);
        String purchaserName = DbUtils.buildLikeCondition(String.valueOf(ssc11305RsParam.getFilterMap().get("purchaserName")).trim(), DbUtils.LikeMode.PARTIAL);
        String deliveryCode = DbUtils.buildLikeCondition(String.valueOf(ssc11305RsParam.getFilterMap().get("deliveryCode")).trim(), DbUtils.LikeMode.PARTIAL);
        String lgcsAreaName = DbUtils.buildLikeCondition(String.valueOf(ssc11305RsParam.getFilterMap().get("lgcsAreaName")).trim(), DbUtils.LikeMode.PARTIAL);
        String deliveryBatch = StringUtil.toString(ssc11305RsParam.getFilterMap().get("deliveryBatch")).trim();
        String etaStr = StringUtil.toString(ssc11305RsParam.getFilterMap().get("etaStr"));
        ssc11305RsParam.setDelFlg(delFlg);
        ssc11305RsParam.setContractName(contractName);
        ssc11305RsParam.setSupplierName(supplierName);
        ssc11305RsParam.setPurchaserName(purchaserName);
        ssc11305RsParam.setDeliveryCode(deliveryCode);
        ssc11305RsParam.setLgcsAreaName(lgcsAreaName);
        ssc11305RsParam.setDeliveryBatch(deliveryBatch);

        String deliveryStatus = StringUtil.toString(ssc11305RsParam.getFilterMap().get("deliveryStatus"));
        if (!StringUtil.isNullOrEmpty(deliveryStatus)) {
            delFlg = null;
        }

        String[] statusArr = null;
        if (!StringUtil.isNullOrEmpty(deliveryStatus)) {
            statusArr = deliveryStatus.split(",");
            ssc11305RsParam.setStatusArr(statusArr);
        }
        ssc11305RsParam.setEtaStr(etaStr);
        if (!StringUtil.isNullOrEmpty(contractCode)) {
            ssc11305RsParam.setContractCode(contractCode);
        } else {
            contractCode = DbUtils.buildLikeCondition(String.valueOf(ssc11305RsParam.getFilterMap().get("contractCode")).trim(), DbUtils.LikeMode.PARTIAL);
            ssc11305RsParam.setContractCode(contractCode);
        }
        ssc11305RsParam.setDelFlg(delFlg);

        String contractRelationType = StringUtil.toString(ssc11305RsParam.getFilterMap().get("contractRelationType"));
        if (!StringUtil.isNullOrEmpty(contractRelationType)) {
            ssc11305RsParam.setRelationTypeArr(contractRelationType.split(","));
        }

        if(!StringUtil.isNullOrEmpty(ssc11305RsParam.getIsPaymentRequest())){
            ssc11305RsParam.setPaymentType(SscConstant.SscPaymentType.PROGRESS_PAYMENT);
        }

        RsResponse<SSC11305RsPageResult> rsResponse = ISSCDeliveryOrderRestUtil.findOrderBasicList(ssc11305RsParam);

        List<SSC11305RsBean> sSC11305RsBeanList = new ArrayList<>();
        if (rsResponse == null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            result.setData(sSC11305RsBeanList);
            result.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
        } else {
            SSC11305RsPageResult sSC11305RsPageResult = rsResponse.getResult();
            sSC11305RsBeanList = sSC11305RsPageResult.getSSC11305RsBeanPageResult();
            result.setData(sSC11305RsBeanList);
            result.setRecordsTotal(rsResponse.getResult().getTotalCount());
        }
        return result;
    }

    /**
     * 通过deliveryCode查询发货确认单
     *
     * @param ssc11314RsParam
     * @return
     */
    @RequestMapping(value = "findDeliveryConfirmInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11314RsResult> findDeliveryConfirmInfo(SSC11314RsParam ssc11314RsParam) {
        logger.info("查询发货确认单信息");
        PageResult<SSC11314RsResult> result = new PageResult<>();
        if (!StringUtil.isNullOrEmpty(ssc11314RsParam.getDeliveryCode())) {
            ssc11314RsParam.setDelFlg(SystemConst.DelFlg.ENABLE);
            result = ISSCDeliveryConfirmOrderRestUtil.findDeliveryConfirmInfoList(ssc11314RsParam);
        }
        return result;
    }

    /**
     * 根据ID删除发货订单
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(SSC11306RsParam ssc11306RsParam) {

        super.setCommonParam(ssc11306RsParam);
        ssc11306RsParam.setDelFlg(SystemConst.DelFlg.DISABLE);
        ssc11306RsParam.setDeliveryStatus(String.valueOf(SscConstant.DeliveryOrderStatus.DELETE));
        ssc11306RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11306RsBean> rsResponse = ISSCDeliveryOrderRestUtil.modifyOrderBasic(ssc11306RsParam);

        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){
            throw new BusinessException(rsResponse.getMessage());
        }

        return "ssc/SSC11305";
    }

    /**
     * 初始化选择发货订单页面
     *
     * @param model
     * @return 页面
     */
    @RequestMapping(value = "chooseDeliveryOrder",method = RequestMethod.POST)
    public String chooseDeliveryOrder(Model model, SSC11305RsParam ssc11305RsParam) {
        logger.debug("选择发货订单页面初始化");
        model.addAttribute("ssc11305RsParam",ssc11305RsParam);
        return "ssc/SSC1130503";
    }

}
