package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.ssc.controller.common.ISSCContractRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDeliveryConfirmOrderRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by wang_shuai on 2016/7/4.
 */
@Controller
@RequestMapping("SSC11314")
public class SSC11314Controller extends BaseController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(SSC11314Controller.class);

    private final String FROM_CONFIRM_DELIVERY_PAGE = "1"; //三方确认单页面
    private final String FROM_PRODUCE_PLAN_PAGE = "2";//生产商计划管理页面

    /**
     * 页面初始化
     * @param model
     * @return
     */
    @RequestMapping(value="init",method = RequestMethod.POST)
    public String init(Model model){
        logger.info("发货确认单一览页面初始化");
        //从redis获取订单发货确认单状态
        Map<String, String> deliveryConfirmStatus = CodeMasterManager.findCodeMasterMap("DeliveryConfirmStatus");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(deliveryConfirmStatus);
        List deliveryConfirmStatusList = new ArrayList(treeMap.entrySet());

        model.addAttribute("deliveryConfirmStatusList", deliveryConfirmStatusList);

        return "ssc/SSC11314";
    }

    /**
     * 发货确认单页面初始化查询数据
     * @param ssc11314RsParam
     * @return
     */
    @RequestMapping(value="search",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SSC11314RsResult> search(SSC11314RsParam ssc11314RsParam){
        logger.info("查询发货单确认信息");

        ssc11314RsParam.setDeliveryConfirmCode(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("deliveryConfirmCode")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setContractCode(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("contractCode")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setDeliveryCode(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("deliveryCode")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setContractName(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("contractName")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setPurchaserName(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("purchaserName")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setSupplierName(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("supplierName")).trim(), DbUtils.LikeMode.PARTIAL));

        ssc11314RsParam.setLgcsAreaName(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("lgcsAreaName")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setArriveWarehouse(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("arriveWarehouse")).trim(), DbUtils.LikeMode.PARTIAL));
        ssc11314RsParam.setArriveWarehouseAddr(DbUtils.buildLikeCondition(String.valueOf(ssc11314RsParam.getFilterMap().get("arriveWarehouseAddr")).trim(), DbUtils.LikeMode.PARTIAL));

        ssc11314RsParam.setDeliveryBatch(StringUtil.toSafeString(ssc11314RsParam.getFilterMap().get("deliveryBatch")).trim());
        String deliveryConfirmStatus = StringUtil.toSafeString(ssc11314RsParam.getFilterMap().get("deliveryConfirmStatus"));
        String[] delConfirmStatuses = null;
        if(!StringUtil.isNullOrEmpty(deliveryConfirmStatus)) {
            delConfirmStatuses = deliveryConfirmStatus.split(",");
            ssc11314RsParam.setDelConfirmStatuses(delConfirmStatuses);
        }

        String delFlg = SystemConst.DelFlg.ENABLE;
        if (!StringUtil.isNullOrEmpty(deliveryConfirmStatus)) {
            delFlg = null;
        }
        ssc11314RsParam.setDelFlg(delFlg);

        return ISSCDeliveryConfirmOrderRestUtil.findDeliveryConfirmInfoList(ssc11314RsParam);

    }

    /**
     * 选择弹出框初始化
     * @param model
     * @return
     */
    @RequestMapping(value = "chooseInit", method = RequestMethod.POST)
    public String chooseInit(Model model) {
        logger.info("选择发货订单弹出框初始化");
        return "ssc/SSC1131401";
    }
    /**
     * 查询弹出框合同数据
     * @return
     */
    @RequestMapping(value = "searchChooseContract/{type}", method = RequestMethod.POST)
    @ResponseBody
    public SSC11314ChooseResult searchChooseContract(@PathVariable(value = "type") String type) {
        logger.info("查询弹出框合同数据");
        SSC11314ChooseResult chooseContract = new SSC11314ChooseResult();
        SSC11314RsParam ssc11314RsParam = new SSC11314RsParam();
        List<Integer> contractStatusList = new ArrayList<>();
        if (FROM_CONFIRM_DELIVERY_PAGE.equals(type)) {
            contractStatusList.add(SscConstant.SscOrderStatus.EFFECTIVE);
        } else if (FROM_PRODUCE_PLAN_PAGE.equals(type)){
            contractStatusList.add(SscConstant.SscOrderStatus.EFFECTIVE);
            contractStatusList.add(SscConstant.SscOrderStatus.PARTIAL_COMP);
            contractStatusList.add(SscConstant.SscOrderStatus.COMPLETE);
            contractStatusList.add(SscConstant.SscOrderStatus.PENDING_VERIF);
            contractStatusList.add(SscConstant.SscOrderStatus.FINISHED);
        }
        ssc11314RsParam.setContractStatusList(contractStatusList);
        RsResponse<SSC11314RsPageResult> rsResponse= ISSCContractRestUtil.findChooseContract(ssc11314RsParam);
        List<String> contracts = new ArrayList<String>();
        List<SSC11314RsResult> chooseContractList = new ArrayList<SSC11314RsResult>();
        if(rsResponse==null || SystemConst.RsStatus.FAIL.equals(rsResponse.getStatus())){// 失败
            System.out.println("无合同数据");
            chooseContract.setContractFlag(SystemConst.RsStatus.FAIL);
        }else{
            chooseContractList = rsResponse.getResult().getDeliveryConfirmPageResult();
            for (SSC11314RsResult rsResult:chooseContractList){
                String contractCode = "";
                String contractName = "";
                if(rsResult != null){
                    if(rsResult.getContractCode() != null){
                        contractCode = rsResult.getContractCode();
                    }else {
                        contractCode = "";
                    }
                    if(rsResult.getContractName() != null){
                        contractName = rsResult.getContractName();
                    }else {
                        contractName = "";
                    }
                    String contract = contractCode+"("+contractName+")";
                    contracts.add(contract);
                }

            }
            chooseContract.setContractList(contracts);
        }


        return chooseContract;
    }

    /**
     * 查询弹出框发货单数据
     * @param chooseParam
     * @return
     */
    @RequestMapping(value = "searchChooseDelivery",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, SSC11314RsResult> searchChooseDelivery(SSC11314ChooseParam chooseParam){
        logger.info("根据合同编号查询对应的发货单号");

        SSC11314RsParam ssc11314RsParam = new SSC11314RsParam();
        ssc11314RsParam.setContractCode(chooseParam.getContractCode());
        //只查询发货订单状态为4：双方已确认
        ssc11314RsParam.setDeliveryStatus(SscConstant.DeliveryOrderStatus.CONFIRM);

        RsResponse<SSC11314RsPageResult> rsResponse = ISSCDeliveryConfirmOrderRestUtil.findChooseDelivery(ssc11314RsParam);

        Map<String, SSC11314RsResult> deliveryMap = new LinkedHashMap<>();
        if (SystemConst.RsStatus.SUCCESS.equals(rsResponse.getStatus())) {
            SSC11314RsPageResult pageResult = rsResponse.getResult();
            List<SSC11314RsResult> ssc11314RsResults = pageResult.getDeliveryConfirmPageResult();

            for(SSC11314RsResult bean: ssc11314RsResults) {
                deliveryMap.put(bean.getDeliveryCode(), bean);
            }
        }
        return deliveryMap;
    }

    /**
     * 新建发货确认单(根据合同编号和发货单号,从发货单表和发货单产品表查询数据插入发货确认单表和发货确认单产品明细表)
     * @return
     */
    @RequestMapping(value = "insertDeliveryConfirm",method = RequestMethod.POST)
    @ResponseBody
    public String insertDeliveryConfirm(SSC11314RsParam ssc11314RsParam){
        logger.info("新建发货确认单");
        String deliveryConfirmId = "";
        this.setCommonParam(ssc11314RsParam);
        ssc11314RsParam.setCrtTime(DateTimeUtil.getCustomerDate());
        ssc11314RsParam.setUpdTime(DateTimeUtil.getCustomerDate());

        RsResponse<SSC11314RsPageResult> rsResponse = ISSCDeliveryConfirmOrderRestUtil.insertDeliveryConfirm(ssc11314RsParam);

        if(rsResponse != null && rsResponse.getResult() != null){
            SSC11314RsPageResult result = rsResponse.getResult();
            deliveryConfirmId = result.getDeliveryConfirmId();
        }

        return deliveryConfirmId;
    }

    /**
     * 删除发货确认单
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String delete(SSC11314RsParam ssc11314RsParam){
        logger.info("删除发货确认单");

        this.setCommonParam(ssc11314RsParam);
        ssc11314RsParam.setUpdTime(DateTimeUtil.getCustomerDate());
        ssc11314RsParam.setDeliveryConfirmStatus(String.valueOf(SscConstant.DeliveryConfirmStatus.CANCEL));
        ISSCDeliveryConfirmOrderRestUtil.deleteConfirmBasic(ssc11314RsParam);
        return "ssc/SSC11314";
    }

}
