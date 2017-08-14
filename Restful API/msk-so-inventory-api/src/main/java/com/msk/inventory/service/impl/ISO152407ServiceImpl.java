package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.BeloneCodeUtil;
import com.msk.common.utils.PartCodeUtil;
import com.msk.inventory.bean.*;
import com.msk.inventory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfan on 16/8/23.
 */
@Service
public class ISO152407ServiceImpl extends BaseService implements IISO152407Service {

    @Autowired
    private IIvmAllocatedLogicService ivmAllocatedLogicService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Autowired
    private IInventoryByProdService inventoryByProdService;

    @Autowired
    private IInventoryViewService inventoryViewService;

    @Transactional
    public void occupyOrder(ISO152407ParamBean sqlBean, String allocatedBy, Date allocatedTime) throws Exception {
        if (sqlBean.getLgcsCode() == null || sqlBean.getLgcsCode().equals("")) {
            throw new BusinessException("物流区编码不能为空");
        }
        if (sqlBean.getPlantFormId() == null || sqlBean.getPlantFormId().equals("")) {
            throw new BusinessException("平台编码不能为空");
        }
        if (sqlBean.getSlType() == null || sqlBean.getSlType().equals("")) {
            throw new BusinessException("卖家类型不能为空");
        }
        if (sqlBean.getSlCode() == null || sqlBean.getSlCode().equals("")) {
            throw new BusinessException("卖家编码不能为空");
        }
        if (sqlBean.getAllocateType() == null || sqlBean.getAllocateType().equals("")) {
            throw new BusinessException("占用类型不能为空");
        }
        for (int i = 0; i < sqlBean.getPdList().size(); i++) {
            IvmAllocatedLogicBean allocatedLogic = new IvmAllocatedLogicBean();
            // 先根据所属和查询卖家占用情况,查看DB中的数据是否存在
            ISO152407PdParamBean sqlBeans = sqlBean.getPdList().get(i);
            if (sqlBeans.getInventoryStatus() == null || sqlBeans.getInventoryStatus().equals("")) {
                throw new BusinessException("库存类型不能为空");
            }
            if (sqlBeans.getSupplierCode() == null || sqlBeans.getSupplierCode().equals("")) {
                throw new BusinessException("供应商编码不能为空");
            }

            allocatedLogic.setIvType(sqlBeans.getInventoryStatus());
            int r = sqlBeans.getOccupyQty().compareTo(BigDecimal.ZERO);
            if (r != 0) {
                allocatedLogic.setAllocatedQty(sqlBeans.getOccupyQty());
            } else {
                throw new BusinessException("SKU（" + sqlBeans.getOccupyQty() + "）入库数量不能为0");
            }
            if (sqlBeans.getPdCode() != null && sqlBeans.getPdCode() != "") {
                allocatedLogic.setPdCode(sqlBeans.getPdCode());
            } else {
                throw new BusinessException("pdCode为必填项");
            }

            //allocatedLogic.setSkuCode("");
            allocatedLogic.setLogicArea(sqlBean.getLgcsCode());
            allocatedLogic.setPlatform(sqlBean.getPlantFormId());
            allocatedLogic.setSlType(sqlBean.getSlType());
            allocatedLogic.setSlId(sqlBean.getSlCode());
            allocatedLogic.setHasNullOwner(true);
            allocatedLogic.setBeloneXml(BeloneCodeUtil.getBeloneXml(allocatedLogic));
            PartCodeUtil.parseCodeForPdCode(allocatedLogic, allocatedLogic.getPdCode());
            allocatedLogic.setProductXml(PartCodeUtil.getPmExternalXml(allocatedLogic));
            // 校验前准备，获取各种占用数据和库存数据
            // 卖家占用
            BigDecimal slAllocatedQty = BigDecimal.ZERO;
            List<IvmAllocatedLogicBean> allocatedLogicList = ivmAllocatedLogicService
                .selectAllocatedLogicList(allocatedLogic);
            if (allocatedLogicList.size() == 1) {
                slAllocatedQty = allocatedLogicList.get(0).getAllocatedQty();
            }

            // 供应商占用
            BigDecimal spAllocatedQty = BigDecimal.ZERO;
            allocatedLogic.setOwnerCode(sqlBeans.getSupplierCode());
            allocatedLogic.setHasNullOwner(false);
            //allocatedLogic.setSkuCode(sqlBeans.getSkuCode());
            List<IvmAllocatedLogicBean> allocatedSupplierLogicList = ivmAllocatedLogicService
                .selectAllocatedLogicList(allocatedLogic);
            if (allocatedSupplierLogicList.size() == 1) {
                spAllocatedQty = allocatedSupplierLogicList.get(0).getAllocatedQty();
            }

            // 卖家商品库存
            BigDecimal slProdQty = BigDecimal.ZERO;
            InventoryViewBean viewCondition = new InventoryViewBean();
            viewCondition.setSlType(sqlBean.getSlType());
            viewCondition.setSlId(sqlBean.getSlCode());
            viewCondition.setLogicArea(sqlBean.getLgcsCode());
            viewCondition.setPlatform(sqlBean.getPlantFormId());
            viewCondition.setPdCode(sqlBeans.getPdCode());
            viewCondition.setIvType(sqlBeans.getInventoryStatus());
            viewCondition.setHasNullOwner(true);
            slProdQty = inventoryViewService.getOnhandSumByCondition(viewCondition);
            if (slProdQty.compareTo(BigDecimal.ZERO) == 0 && sqlBean.getSlType().equals(InventoryCodeMasterDef.SlType.ST_BUYER)) {
                throw new BusinessException("品（" + sqlBeans.getPdCode() + "）无符合条件（" + sqlBean.getLgcsCode() + ","
                        + sqlBean.getPlantFormId() + "）的卖家库存");
            }

            // 供应商商品库存
            BigDecimal spProdQty = BigDecimal.ZERO;
            IvmInventoryByProdBean prodIvCondition = new IvmInventoryByProdBean();
            prodIvCondition.setSlType(sqlBean.getSlType());
            prodIvCondition.setSlId(sqlBean.getSlCode());
            prodIvCondition.setLogicCode(sqlBean.getLgcsCode());
            prodIvCondition.setPlatform(sqlBean.getPlantFormId());
            prodIvCondition.setPdCode(sqlBeans.getPdCode());
            prodIvCondition.setOwnerCode(sqlBeans.getSupplierCode());
            prodIvCondition.setIvType(sqlBeans.getInventoryStatus());
            spProdQty = inventoryByProdService.getSumQty(prodIvCondition);
            if (spProdQty.compareTo(BigDecimal.ZERO) == 0 && sqlBean.getSlType().equals(InventoryCodeMasterDef.SlType.ST_PLATFORM)) {
                throw new BusinessException("品（" + sqlBeans.getPdCode() + "）无符合条件（" + sqlBean.getLgcsCode() + ","
                        + sqlBean.getPlantFormId() + "）的供应商库存");
            }

            // 当前需要占用
            BigDecimal newAllocatedQty = allocatedLogic.getAllocatedQty();

            // 占用后数量与供应商库存比较
            BigDecimal futureSpAllocatedQty = spAllocatedQty.add(newAllocatedQty);
            if (spProdQty.compareTo(futureSpAllocatedQty) < 0 && sqlBean.getSlType().equals(InventoryCodeMasterDef.SlType.ST_PLATFORM)) {
                throw new BusinessException("供应商（" + sqlBeans.getSupplierCode() + "）的品（" + sqlBeans.getPdCode()
                        + "）库存不足，占用后占用数量将为" + futureSpAllocatedQty + "，库存为" + spProdQty);
            }
            if (slProdQty.compareTo(futureSpAllocatedQty) < 0 && sqlBean.getSlType().equals(InventoryCodeMasterDef.SlType.ST_BUYER)) {
                throw new BusinessException("卖家（" + sqlBean.getSlCode() + "）的品（" + sqlBeans.getPdCode()
                        + "）库存不足，占用后占用数量将为" + futureSpAllocatedQty + "，库存为" + slProdQty);
            }

            if ("2".equals(sqlBean.getAllocateType())) {

                // 检查纯卖家占用是否足够
                if (slAllocatedQty.compareTo(newAllocatedQty) < 0) {
                    throw new BusinessException("卖家（" + sqlBean.getSlCode() + "）占用的品（" + sqlBeans.getPdCode() + "）数量（"
                            + slAllocatedQty + "）不足，需要分配供应商占用的数量（" + newAllocatedQty + "）");
                }

                // 对应的该品的总占用数减少
                IvmAllocatedLogicBean cutALlocatedLogic = new IvmAllocatedLogicBean();
                cutALlocatedLogic.setPdCode(sqlBeans.getPdCode());
                cutALlocatedLogic.setLogicArea(sqlBean.getLgcsCode());
                cutALlocatedLogic.setPlatform(sqlBean.getPlantFormId());
                cutALlocatedLogic.setSlType(sqlBean.getSlType());
                cutALlocatedLogic.setSlId(sqlBean.getSlCode());
                cutALlocatedLogic.setAllocatedQty(sqlBeans.getOccupyQty());
                cutALlocatedLogic.setHasNullOwner(true);
                ivmAllocatedLogicService.cutAllocatedQty(cutALlocatedLogic);

                if (allocatedSupplierLogicList.size() == 0) {// 无供应商占用数据
                    // 新建供应商占用数据
                    //allocatedLogic.setSkuCode(sqlBeans.getSkuCode());
                    //PartCodeUtil.parseCodeForPdCode(allocatedLogic, allocatedLogic.getPdCode());
                    //PartCodeUtil.parseCodeForSkuCode(allocatedLogic, allocatedLogic.getSkuCode());
                    //allocatedLogic.setProductXml(PartCodeUtil.getPmExternalXml(allocatedLogic));
                    allocatedLogic.setOwnerCode(sqlBeans.getSupplierCode());
                    ivmAllocatedLogicService.insertAllocatedLogic(allocatedLogic);
                } else {// 有供应商占用数据
                    // 查询有ownerId数据的占用库存
                    allocatedLogic.setAllocatedQty(newAllocatedQty);
                    //allocatedLogic.setSkuCode(sqlBeans.getSkuCode());
                    allocatedLogic.setOwnerCode(sqlBeans.getSupplierCode());
                    ivmAllocatedLogicService.addAllocatedQty(allocatedLogic);
                }
            } else if ("1".equals(sqlBean.getAllocateType())) {// 无卖家占用信息，占用类型1的直接插入，否则报错

                // 由于是直接占用，所以要检查占用后的数量是不是会导致卖家占用超过库存
                BigDecimal futureSlAllocatedQty = slAllocatedQty.add(newAllocatedQty);
                if (slProdQty.compareTo(futureSlAllocatedQty) < 0) {
                    throw new BusinessException("卖家（" + sqlBean.getSlCode() + "）的品（" + sqlBeans.getPdCode()
                            + "）库存不足，占用后占用数量将为" + futureSlAllocatedQty + "，库存为" + slProdQty);
                }

                if (allocatedSupplierLogicList.size() > 0) {
                    ivmAllocatedLogicService.addAllocatedQty(allocatedLogic);
                } else {// 新增
                    //allocatedLogic.setSkuCode(sqlBeans.getSkuCode());
                    //PartCodeUtil.parseCodeForPdCode(allocatedLogic, allocatedLogic.getPdCode());
                    //PartCodeUtil.parseCodeForSkuCode(allocatedLogic, allocatedLogic.getSkuCode());
                    //allocatedLogic.setProductXml(PartCodeUtil.getPmExternalXml(allocatedLogic));
                    allocatedLogic.setOwnerCode(sqlBeans.getSupplierCode());
                    ivmAllocatedLogicService.insertAllocatedLogic(allocatedLogic);
                }
            } else {
                throw new BusinessException("占用类型错误：" + sqlBean.getAllocateType());
            }

            IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();
            transactionLog.setCrtId(allocatedBy);
            transactionLog.setCrtTime(allocatedTime);
            transactionLog.setBeloneXml(allocatedLogic.getBeloneXml());
            transactionLog.setProductXml(allocatedLogic.getProductXml());
            transactionLog.setQty(allocatedLogic.getAllocatedQty());
            transactionLog.setTransactionType(InventoryCodeMasterDef.TranType.AL_SUPPLIER+"");
            transactionLog.setUom("箱");
            transactionLog.setTransactionId(Long.valueOf(sqlBean.getOrderId()));
            transactionLog.setTransactionNo(sqlBean.getOrderCode());
            transactionLog.setTransactionDate(allocatedTime);
            transactionLog.setOwnerId(allocatedLogic.getOwnerId());
            transactionLog.setOwnerCode(allocatedLogic.getOwnerCode());
            transactionLog.setPucharseBatch(sqlBeans.getInboundBatch());
            transactionLogService.insertOneTransactionLog(transactionLog);
        }
    }
}
