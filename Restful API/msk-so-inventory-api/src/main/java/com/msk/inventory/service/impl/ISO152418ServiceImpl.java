package com.msk.inventory.service.impl;

import java.util.List;

import com.msk.common.constant.business.InventoryCodeMasterDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.PartCodeUtil;
import com.msk.inventory.bean.ISO152418InvParamBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.bean.IvmTransactionLogBean;
import com.msk.inventory.service.*;

/**
 * Created by wang_fan2 on 2016/9/18.
 */
@Service
public class ISO152418ServiceImpl extends BaseService implements IISO152418Service {

    @Autowired
    private IInventoryDetailService inventoryDetailService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Autowired
    private ISlOnhandLogicService slOnhandLogicService;

    @Autowired
    private IIvmAllocatedLogicService ivAllocatedLogicService;

    @Override
    public void undoDispatch(ISO152418InvParamBean paramBean) {
        if(paramBean.getOutboundId() == null || paramBean.getOutboundId().equals("")){
            throw new BusinessException("原出库作业单id不能为空");
        }
        if(paramBean.getOutboundNo() == null || paramBean.getOutboundNo().equals("")){
            throw new BusinessException("原出库作业单号不能为空");
        }
        if(paramBean.getCancelTime() == null || paramBean.getCancelTime().equals("")){
            throw new BusinessException("取消申请时日不能为空");
        }
        // 清空
        IvmInventoryDetailBean ivmInventoryDetailBean = new IvmInventoryDetailBean();
        ivmInventoryDetailBean.setOutboundId(paramBean.getOutboundId());
        ivmInventoryDetailBean.setOutboundNo(paramBean.getOutboundNo());
        ivmInventoryDetailBean.setUpdId("1");
        ivmInventoryDetailBean.setUpdTime(paramBean.getCancelTime());
        inventoryDetailService.undoOutboundIvDetail(ivmInventoryDetailBean);

        // 查询库存信息
        List<IvmInventoryDetailBean> ivDetailBeanList = inventoryDetailService
            .selectInventoryDetailList(ivmInventoryDetailBean);

        if (ivDetailBeanList.size() > 0) {
            IvmInventoryDetailBean tempOutInfo = ivDetailBeanList.get(0);

            IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();// 库存日志对象
            try {
                PartCodeUtil.parseCodeForPdCode(transactionLog, tempOutInfo.getPdCode());
                PartCodeUtil.parseCodeForSkuCode(transactionLog, tempOutInfo.getSkuCode());
                transactionLog.setProductXml(PartCodeUtil.getPmExternalXml(transactionLog));
                transactionLog.setWhId(tempOutInfo.getWhId());
                transactionLog.setWhCode(tempOutInfo.getWhCode());
                transactionLog.setOwnerId(tempOutInfo.getOwnerId());
                transactionLog.setOwnerCode(tempOutInfo.getOwnerCode());
                transactionLog.setBeloneXml(tempOutInfo.getBeloneXml());
                transactionLog.setSupplierId(tempOutInfo.getSupplierId());
                transactionLog.setSupplierCode(tempOutInfo.getSupplierCode());
                transactionLog.setTransactionId(tempOutInfo.getOutboundId());
                transactionLog.setTransactionNo(tempOutInfo.getOutboundNo());
                transactionLog.setTransactionType(InventoryCodeMasterDef.TranType.OT_CANCEL+"");
                transactionLog.setPucharseBatch(tempOutInfo.getPucharseBatch());
                transactionLog.setPrice(tempOutInfo.getSellPrice());
                transactionLog.setQty(tempOutInfo.getQty());
                transactionLog.setUom(tempOutInfo.getUom());
                transactionLog.setTransactionDate(tempOutInfo.getCrtTime());
                transactionLog.setCrtId(tempOutInfo.getCrtId());
                transactionLog.setCrtTime(DateTimeUtil.getCustomerDate());
                transactionLog.setUpdId(tempOutInfo.getUpdId());
                transactionLog.setActId(tempOutInfo.getActId());

                transactionLogService.insertOneTransactionLog(transactionLog);// 库存日志表插入数据

                // 取消囤货
                if ("2".equals(tempOutInfo.getSlType())) {
                    slOnhandLogicService.calOnhandQtyForUndoDispatch(tempOutInfo);
                }

                // 取消占用
                ivAllocatedLogicService.calAllocatedQtyForUndoDispatch(tempOutInfo);
            } catch (Exception e) {
                throw new BusinessException("库存出库取消失败！");
            }
        } else {
            throw new BusinessException("库存出库取消失败！");
        }

    }
}
