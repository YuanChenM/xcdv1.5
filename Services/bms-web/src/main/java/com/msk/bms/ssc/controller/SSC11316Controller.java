package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCDeliveryPreInfoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 业务控制器：管理预入库通知单页面的业务
 * Created by xia_xiaojie on 2016/7/7.
 */
@Controller
@RequestMapping("/SSC11316")
public class SSC11316Controller extends BaseController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SSC11316Controller.class);

    /**
     * 预入库通知单页面初始化
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String init(Model model) {

        Map<String, String> ProductRecvStatusMap = CodeMasterManager.findCodeMasterMap("ProductRecvStatus");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(ProductRecvStatusMap);
        List ProductRecvStatus = new ArrayList(treeMap.entrySet());

        model.addAttribute("ProductRecvStatus", ProductRecvStatus);

        return "ssc/SSC11316";
    }

    /**
     * 预入库通知单列表查询
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageResult<SSC11316Bean> search(SSC11316Param ssc11316Param) {
        this.handleParameter(ssc11316Param);
        //查询预入库通知单列表
        RsResponse<SSC11316Result> respResult = ISSCDeliveryPreInfoRestUtil.findPreIntoBasicList(ssc11316Param);
        SSC11316Result ssc11316Result = respResult.getResult();

        PageResult<SSC11316Bean> pageResult = new PageResult<>();
        pageResult.setData(ssc11316Result.getSscDeliveryPreIntos());
        pageResult.setRecordsTotal(ssc11316Result.getTotalCount());

        return pageResult;
    }

    /**
     * 处理查询条件
     */
    private void handleParameter(SSC11316Param queryParam) {
        Map<String, Object> filterMap = queryParam.getFilterMap();

        //模糊查询，%field%
        Object contractCode = filterMap.get("contractCode");
        if (null != contractCode) {
            queryParam.setContractCode(DbUtils.buildLikeCondition(String.valueOf(contractCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object contractName = filterMap.get("contractName");
        if (null != contractName) {
            queryParam.setContractName(DbUtils.buildLikeCondition(String.valueOf(contractName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object purchaserName = filterMap.get("purchaserName");
        if (null != purchaserName) {
            queryParam.setPurchaserName(DbUtils.buildLikeCondition(String.valueOf(purchaserName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object supplierName = filterMap.get("supplierName");
        if (null != supplierName) {
            queryParam.setSupplierName(DbUtils.buildLikeCondition(String.valueOf(supplierName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object intoStoreCode = filterMap.get("intoStoreCode");
        if (null != intoStoreCode) {
            queryParam.setIntoStoreCode(DbUtils.buildLikeCondition(String.valueOf(intoStoreCode).trim(), DbUtils.LikeMode.PARTIAL));
        }
        Object deliveryCode = filterMap.get("deliveryCode");
        if (null != deliveryCode) {
            queryParam.setDeliveryCode(DbUtils.buildLikeCondition(String.valueOf(deliveryCode).trim(), DbUtils.LikeMode.PARTIAL));
        }
        Object deliveryConfirmCode = filterMap.get("deliveryConfirmCode");
        if (null != deliveryConfirmCode) {
            queryParam.setDeliveryConfirmCode(DbUtils.buildLikeCondition(String.valueOf(deliveryConfirmCode).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object arriveWarehouse = filterMap.get("arriveWarehouse");
        if (null != arriveWarehouse) {
            queryParam.setArriveWarehouse(DbUtils.buildLikeCondition(String.valueOf(arriveWarehouse).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object licPlateNumber = filterMap.get("licPlateNumber");
        if (null != licPlateNumber) {
            queryParam.setLicPlateNumber(DbUtils.buildLikeCondition(String.valueOf(licPlateNumber).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object driverTel = filterMap.get("driverTel");
        if (null != driverTel) {
            queryParam.setDriverTel(DbUtils.buildLikeCondition(String.valueOf(driverTel).trim(), DbUtils.LikeMode.PARTIAL));
        }

        Object lgcsAreaName = filterMap.get("lgcsAreaName");
        if (null != lgcsAreaName) {
            queryParam.setLgcsAreaName(DbUtils.buildLikeCondition(String.valueOf(lgcsAreaName).trim(), DbUtils.LikeMode.PARTIAL));
        }

        String productRecvStatus = StringUtil.toSafeString(filterMap.get("productRecvStatus"));
        if(StringUtils.hasText(productRecvStatus)) {
            String[] productRecvStatuses = productRecvStatus.split(",");
            queryParam.setProductRecvStatuses(Arrays.asList(productRecvStatuses));
        }

        queryParam.setExpectDeliveryDate(StringUtil.toSafeString(filterMap.get("expectDeliveryDate")));
        queryParam.setExpectArriveDate(StringUtil.toSafeString(filterMap.get("expectArriveDate")));

        queryParam.setDeliveryBatch(StringUtil.toSafeString(filterMap.get("deliveryBatch")).trim());
        queryParam.setVehicleNumber(StringUtil.toSafeString(filterMap.get("vehicleNumber")).trim());
    }

    /**
     * 查询预入库通知单
     */
    @RequestMapping(value = "findPreInto", method = RequestMethod.POST)
    @ResponseBody
    public SSC11316Param findPreinto(SSC11316Param ssc11316Param) {
        //1 没有生成预入库通知单  2 已发车  3  未发车
        String flag = "1";

        List<SSC11316Bean> list = ISSCDeliveryPreInfoRestUtil.findPreInto(ssc11316Param);

        SSC11316Param param = new SSC11316Param();

        if(!CollectionUtils.isEmpty(list)){
            //判断是否有发车
            for (SSC11316Bean bean : list) {
                if(SscConstant.ProductRecvStatus.TO_BE_SHIPPED != bean.getProductRecvStatus()){
                    flag = "2";
                }
            }
            //未发车则需回传预入库编号
            if(!"2".equals(flag)){
                flag = "3";
                List<String> codeList = new ArrayList<>();
                for (SSC11316Bean bean : list) {
                    codeList.add(String.valueOf(bean.getDeliveryPreIntoCode()));
                }
                param.setPreIntoCodeList(codeList);
            }
        }

        param.setFlag(flag);

        return param;
    }

    /**
     * 删除预入库通知单
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(SSC11316Param ssc11316Param) {

        this.setCommonParam(ssc11316Param);
        ssc11316Param.setProductRecvStatus(String.valueOf(SscConstant.ProductRecvStatus.EXCEPTION));
        ssc11316Param.setDelFlg(SystemConst.DelFlg.DISABLE);
        ssc11316Param.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11316Bean> rsResponse =ISSCDeliveryPreInfoRestUtil.deletePreInto(ssc11316Param);
        if (SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())) {
            throw new BusinessException(rsResponse.getMessage());
        }

        return rsResponse.getStatus();
    }

}
