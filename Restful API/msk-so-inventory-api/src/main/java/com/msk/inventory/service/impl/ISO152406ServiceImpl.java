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
 * Created by zheng_xu on 2016/8/25.
 */
@Service
public class ISO152406ServiceImpl extends BaseService implements IISO152406Service {

    @Autowired
    private IIvmAllocatedLogicService ivmAllocatedLogicService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Autowired
    private IInventoryByProdService inventoryByProdService;

    @Autowired
    private IInventoryViewService inventoryViewService;

    @Transactional
    public void getOrderIdOfOccupy(ISO152406ParamBean sqlBean, String allocatedBy, Date allocatedTime) throws Exception {

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
        for (int i = 0; i < sqlBean.getPdList().size(); i++) {
            ISO152406PdListParamBean sqlBeans = sqlBean.getPdList().get(i);
            if (sqlBeans.getPdCode() == null || sqlBeans.getPdCode().equals("")) {
                throw new BusinessException("产品编号不能为空");
            }
            if (sqlBeans.getInventoryStatus() == null || sqlBeans.getInventoryStatus().equals("")) {
                throw new BusinessException("库存类型不能为空");
            }
            // 参数封装
            IvmAllocatedLogicBean allocatedLogic = new IvmAllocatedLogicBean();
            allocatedLogic.setIvType(sqlBeans.getInventoryStatus());
            int r=sqlBeans.getOccupyQty().compareTo(BigDecimal.ZERO);
            if(r!=0){
                allocatedLogic.setAllocatedQty(sqlBeans.getOccupyQty());
            }else{
                throw new BusinessException("SKU（"+sqlBeans.getOccupyQty()+"）入库数量不能为0");
            }

            PartCodeUtil.parseCodeForPdCode(allocatedLogic, sqlBeans.getPdCode());
            allocatedLogic.setProductXml(PartCodeUtil.getPmExternalXml(allocatedLogic));
            allocatedLogic.setSkuCode("");
            allocatedLogic.setLogicArea(sqlBean.getLgcsCode());
            allocatedLogic.setPlatform(sqlBean.getPlantFormId());
            allocatedLogic.setSlType(sqlBean.getSlType());
            allocatedLogic.setSlId(sqlBean.getSlCode());
            allocatedLogic.setBeloneXml(BeloneCodeUtil.getBeloneXml(allocatedLogic));

            BigDecimal prodQty = BigDecimal.ZERO;
            if("1".equals(sqlBean.getSlType())){
                // prod表查询参数封装
                IvmInventoryByProdBean prodIvCondition = new IvmInventoryByProdBean();
                prodIvCondition.setSlType(sqlBean.getSlType());
                prodIvCondition.setSlId(sqlBean.getSlCode());
                prodIvCondition.setLogicCode(sqlBean.getLgcsCode());
                prodIvCondition.setPlatform(sqlBean.getPlantFormId());
                prodIvCondition.setPdCode(sqlBeans.getPdCode());
                prodIvCondition.setIvType(sqlBeans.getInventoryStatus());
                // 判断prod表对应条件查询的sum数量
                prodQty = inventoryByProdService.getSumQty(prodIvCondition);
                if (prodQty.compareTo(BigDecimal.ZERO) == 0) {
                    throw new BusinessException("品（" + sqlBeans.getPdCode() + "）无符合条件（" + sqlBean.getLgcsCode() + ","
                            + sqlBean.getPlantFormId() + "）的库存");
                }
            }else{
                InventoryViewBean viewCondition = new InventoryViewBean();
                viewCondition.setSlType(sqlBean.getSlType());
                viewCondition.setSlId(sqlBean.getSlCode());
                viewCondition.setLogicArea(sqlBean.getLgcsCode());
                viewCondition.setPlatform(sqlBean.getPlantFormId());
                viewCondition.setPdCode(sqlBeans.getPdCode());
                viewCondition.setIvType(sqlBeans.getInventoryStatus());
                viewCondition.setHasNullOwner(true);

                prodQty = inventoryViewService.getAvailableSumByCondition(viewCondition);
            if (prodQty.compareTo(BigDecimal.ZERO) == 0) {
                    throw new BusinessException("品（" + sqlBeans.getPdCode() + "）无符合条件（" + sqlBean.getLgcsCode() + ","
                            + sqlBean.getPlantFormId() + "）的库存");
                }
            }

                BigDecimal decreaseQty = allocatedLogic.getAllocatedQty();
                if (prodQty.compareTo(decreaseQty) >= 0) {
                    // 校验占用是否已存在，若存在即累加，若不存在即新增
                    List<IvmAllocatedLogicBean> allocatedLogicList = ivmAllocatedLogicService
                        .selectAllocatedLogicList(allocatedLogic);
                    if (allocatedLogicList.size() > 0) {
                        BigDecimal allocateQty = decreaseQty.add(allocatedLogicList.get(0).getAllocatedQty());
                        if (prodQty.compareTo(allocateQty) >= 0) {
                            IvmAllocatedLogicBean allocatedIv = allocatedLogicList.get(0);

                            IvmAllocatedLogicBean newALlocatedLogic = new IvmAllocatedLogicBean();
                            newALlocatedLogic.setPdCode(sqlBeans.getPdCode());
                            newALlocatedLogic.setLogicArea(sqlBean.getLgcsCode());
                            newALlocatedLogic.setPlatform(sqlBean.getPlantFormId());
                            newALlocatedLogic.setSlType(sqlBean.getSlType());
                            newALlocatedLogic.setSlId(sqlBean.getSlCode());
                            newALlocatedLogic.setAllocatedQty(sqlBeans.getOccupyQty());
                            newALlocatedLogic.setHasNullOwner(true);
                            ivmAllocatedLogicService.addAllocatedQty(newALlocatedLogic);

                        } else {
                        throw new BusinessException("占用后，占用数量（"+allocateQty+"）将会大于库存数量（"+prodQty+"）");
                        }
                    } else {
                        ivmAllocatedLogicService.insertAllocatedLogic(allocatedLogic);

                }
                // 日志参数封装
                IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();
                transactionLog.setCrtId(allocatedBy);
                transactionLog.setCrtTime(allocatedTime);
                        transactionLog.setBeloneXml(allocatedLogic.getBeloneXml());
                        transactionLog.setProductXml(allocatedLogic.getProductXml());
                        transactionLog.setQty(allocatedLogic.getAllocatedQty());
                    transactionLog.setTransactionType(InventoryCodeMasterDef.TranType.AL_SELLER+"");//卖家占用
                transactionLog.setUom("箱");
                transactionLog.setTransactionId(Long.valueOf(sqlBean.getOrderId()));
                transactionLog.setTransactionNo(sqlBean.getOrderCode());
                transactionLog.setTransactionDate(allocatedTime);
                        transactionLogService.insertOneTransactionLog(transactionLog);// 新增日志
                } else {
                throw new BusinessException("要占用数量（"+decreaseQty+"）大于库存数量（"+prodQty+"）");
                }
        }
    }
}
