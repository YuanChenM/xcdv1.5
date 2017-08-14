package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.PartCodeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.inventory.bean.IvmInfoDefBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.bean.IvmPartsMasterBean;
import com.msk.inventory.bean.IvmTransactionLogBean;
import com.msk.inventory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wang_fan on 2016/8/17.
 */
@Service
public class InboundServiceImpl extends BaseService implements IInboundService {

    @Autowired
    private IPartsMasterService partsMasterService;

    @Autowired
    private ICargoIdentityService cargoIdentityService;

    @Autowired
    private IInventoryDetailService inventoryDetailService;

    @Autowired
    private IInventoryByProdService inventoryByProdService;

    @Autowired
    private IInfoDefService infoDefService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Transactional
    public void receiveInboundData(List<IvmInventoryDetailBean> ivDetailList) throws Exception {
        if (ivDetailList == null || ivDetailList.size() == 0) {
            throw new BusinessException("无效的入库明细！");
        }

        IvmInventoryDetailBean tempIvD = null;
        tempIvD = ivDetailList.get(0);
        IvmInventoryDetailBean countCondition = new IvmInventoryDetailBean();
        countCondition.setInboundId(tempIvD.getInboundId());
        countCondition.setInboundNo(tempIvD.getInboundNo());
        if(inventoryDetailService.selectInventoryDetailCount(countCondition) > 0){
            throw new BusinessException("重复的入库作业（"+tempIvD.getInboundNo()+"）");
        }

        List<IvmInfoDefBean> infoDefList = new ArrayList<IvmInfoDefBean>();
        IvmInfoDefBean tempInfo = null;
        for (int i = 0; i < ivDetailList.size(); i++) {
            tempIvD = ivDetailList.get(i);
            this.doInboundForDetail(tempIvD);

            if(!StringUtil.isEmpty(tempIvD.getPlatform())){
            tempInfo = new IvmInfoDefBean();
            tempInfo.setCodeType(IvmInfoDefBean.TYPE_PLATFORM);
            tempInfo.setCodeValue(tempIvD.getPlatform());
            tempInfo.setCodeDesc(tempIvD.getPlatformName());
            infoDefList.add(tempInfo);
            }

            if(!StringUtil.isEmpty(tempIvD.getOwnerCode())) {
            tempInfo = new IvmInfoDefBean();
            tempInfo.setCodeType(IvmInfoDefBean.TYPE_OWNER);
            tempInfo.setCodeValue(tempIvD.getOwnerCode());
            tempInfo.setCodeDesc(tempIvD.getOwnerName());
            infoDefList.add(tempInfo);
            }

            if(!StringUtil.isEmpty(tempIvD.getSlId())) {
            tempInfo = new IvmInfoDefBean();
            tempInfo.setCodeType(IvmInfoDefBean.TYPE_SELLER);
            tempInfo.setCodeValue(tempIvD.getSlId());
            tempInfo.setCodeDesc(tempIvD.getSlName());
            infoDefList.add(tempInfo);
            }

            if(!StringUtil.isEmpty(tempIvD.getSupplierCode())) {
            tempInfo = new IvmInfoDefBean();
            tempInfo.setCodeType(IvmInfoDefBean.TYPE_SUPPLIER);
            tempInfo.setCodeValue(tempIvD.getSupplierCode());
            tempInfo.setCodeDesc(tempIvD.getSupplierName());
            infoDefList.add(tempInfo);
            }

            if(!StringUtil.isEmpty(tempIvD.getPdCode())) {
            tempInfo = new IvmInfoDefBean();
            tempInfo.setCodeType(IvmInfoDefBean.TYPE_PRODUCT);
            tempInfo.setCodeValue(tempIvD.getPdCode());
            tempInfo.setCodeDesc(tempIvD.getPdName());
            infoDefList.add(tempInfo);
        }
        }

        inventoryDetailService.receiveByInbound(ivDetailList.get(0));

        inventoryDetailService.putawayByInbound(ivDetailList.get(0));

        inventoryByProdService.touchIvProdByInbound(ivDetailList.get(0));

        infoDefService.synInfoDefList(infoDefList);
    }

    @Transactional
    public void doInboundForDetail(IvmInventoryDetailBean ivDetailBean) throws Exception {
        if (ivDetailBean.getInboundType().equals(InventoryCodeMasterDef.InboundType.IT_PUCHARSE+ "")) {
           int i=ivDetailBean.getBuyPrice().compareTo(BigDecimal.ZERO);
           //单价必须>0
           if(i==1){
                ivDetailBean.setIvFlag(InventoryCodeMasterDef.GoodType.GT_GOOD + "");// 货品类型：良品
            }else{
                throw new BusinessException("采购入库，单价为0");
           }
        } else if (ivDetailBean.getInboundType().equals(InventoryCodeMasterDef.InboundType.IT_ADJUST+ "")) {
            ivDetailBean.setIvFlag(InventoryCodeMasterDef.GoodType.GT_GOOD + "");// 货品类型：良品
        } else if (ivDetailBean.getInboundType().equals(InventoryCodeMasterDef.InboundType.IT_RETURN+ "")) {
           int i=ivDetailBean.getBuyPrice().compareTo(BigDecimal.ZERO);
           //单价必须>0
           if(i==1){
                ivDetailBean.setIvFlag(InventoryCodeMasterDef.GoodType.GT_PROBLEM + "");// 货品类型：问题品
           }else{
                throw new BusinessException("退货入库，单价为0");
           }
        } else if (ivDetailBean.getInboundType().equals(InventoryCodeMasterDef.InboundType.IT_TRYOUT+ "")) {
            ivDetailBean.setIvFlag(InventoryCodeMasterDef.GoodType.GT_PROBLEM+"");// 货品类型：良品
       }else{
           throw new BusinessException("入库类型("+ivDetailBean.getInboundType()+")有误！");
       }
        insertInboundDetail(ivDetailBean);
    }

    /**
     *  货品明细入库
     */
    @Transactional
    private void insertInboundDetail(IvmInventoryDetailBean ivDetailBean) throws Exception {
        IvmPartsMasterBean partsMasterBean = new IvmPartsMasterBean();
        partsMasterBean.setPdCode(ivDetailBean.getPdCode());
        partsMasterBean.setSkuCode(ivDetailBean.getSkuCode());
        boolean isExistPartsMaster =  partsMasterService.isExistPartsMaster(partsMasterBean);

        try {
            //当没有pdCode和skuCode，插入一条货品信息数据
            if(!isExistPartsMaster){
                partsMasterBean.setPmName(ivDetailBean.getPmName());
                partsMasterBean.setPmDesc(ivDetailBean.getPmDesc());
                partsMasterBean.setCrtId(ivDetailBean.getCrtId());
                partsMasterBean.setCrtTime(ivDetailBean.getCrtTime());
                partsMasterService.insertOneParts(partsMasterBean);
            }
            //2，入库明细表
            ivDetailBean.setPmCode(partsMasterBean.getPmCode());
            String loadNo=cargoIdentityService.getLoadNo(DateTimeUtil.getCustomerDate());
            ivDetailBean.setLoadNo(loadNo);
            ivDetailBean.setLoadSeq(1);
            inventoryDetailService.insertOneInventoryDetail(ivDetailBean);

            IvmTransactionLogBean transactionLog = getTransactionBean(ivDetailBean);// 获取库存日志参数

            transactionLogService.insertOneTransactionLog(transactionLog);// 库存日志表插入数据
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("货品明细入库执行失败！");
        }
    }

    /**
     * 根据入库信息，获取插入库存日志参数
     *
     * @param ivDetailBean
     * @return
     */
    public IvmTransactionLogBean getTransactionBean(IvmInventoryDetailBean ivDetailBean) throws Exception{
        IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();// 库存日志对象
        PartCodeUtil.parseCodeForPdCode(transactionLog, ivDetailBean.getPdCode());
        PartCodeUtil.parseCodeForSkuCode(transactionLog, ivDetailBean.getSkuCode());
        transactionLog.setProductXml(PartCodeUtil.getPmExternalXml(transactionLog));
        transactionLog.setWhId(ivDetailBean.getWhId());
        transactionLog.setWhCode(ivDetailBean.getWhCode());
        transactionLog.setOwnerId(ivDetailBean.getOwnerId());
        transactionLog.setOwnerCode(ivDetailBean.getOwnerCode());
        transactionLog.setBeloneXml(ivDetailBean.getBeloneXml());
        transactionLog.setSupplierId(ivDetailBean.getSupplierId());
        transactionLog.setSupplierCode(ivDetailBean.getSupplierCode());

        transactionLog.setTransactionId(ivDetailBean.getInboundId());// 业务ID
        transactionLog.setTransactionNo(ivDetailBean.getInboundNo());// 业务单号
        // transactionLog.setTransactionDesc();//业务描述
        transactionLog.setTransactionType(ivDetailBean.getInboundType());
        transactionLog.setPucharseBatch(ivDetailBean.getPucharseBatch());
        transactionLog.setInnerBatch(ivDetailBean.getInnerBatch());
        transactionLog.setPrice(ivDetailBean.getBuyPrice());
        transactionLog.setQty(ivDetailBean.getQty());
        transactionLog.setUom(ivDetailBean.getUom());
        //Modif for Bug#2613号 at 2016/09/06 by wangfan2 Start
        transactionLog.setTransactionDate(ivDetailBean.getCrtTime());
        //Modif for Bug#2613号 at 2016/09/06 by wangfan2 End
        transactionLog.setCrtId(ivDetailBean.getCrtId());
        transactionLog.setCrtTime(ivDetailBean.getCrtTime());
        transactionLog.setUpdId(ivDetailBean.getUpdId());
        // transactionLog.setUpdTime(ivDetailBean.getUpdTime());
        transactionLog.setActId(ivDetailBean.getActId());
        // transactionLog.setActTime(ivDetailBean.getActTime());
        return transactionLog;
    }

}
