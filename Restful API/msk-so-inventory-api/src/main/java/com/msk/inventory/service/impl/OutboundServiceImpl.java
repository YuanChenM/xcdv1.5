package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.PartCodeUtil;
import com.msk.inventory.bean.InventoryViewBean;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.bean.IvmTransactionLogBean;
import com.msk.inventory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wang_fan on 2016/8/18.
 */
@Service
public class OutboundServiceImpl extends BaseService implements IOutboundService {

    @Autowired
    private IInventoryDetailService inventoryDetailService;

    @Autowired
    private IInventoryByProdService inventoryByProdService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Autowired
    private IInventoryViewService ivViewService;

    @Autowired
    private ISlOnhandLogicService slOnhandLogicService;

    @Autowired
    private IIvmAllocatedLogicService ivAllocatedLogicService;

    @Transactional
    public void pickInboundData(List<IvmInventoryDetailBean> ivDetailList, String outboundBy, Date outboundTime)
        throws Exception {
        if (ivDetailList == null || ivDetailList.size() == 0) {
            throw new BusinessException("无效的出库明细！");
        }

        IvmInventoryDetailBean tempIvD = null;
        tempIvD = ivDetailList.get(0);
        IvmInventoryDetailBean countCondition = new IvmInventoryDetailBean();
        countCondition.setOutboundId(tempIvD.getOutboundId());
        countCondition.setOutboundNo(tempIvD.getOutboundNo());
        if(inventoryDetailService.selectInventoryDetailCount(countCondition) > 0){
            throw new BusinessException("重复的出库作业（"+tempIvD.getOutboundNo()+"）");
        }

        IvmInventoryDetailBean tempOutInfo = null;
        IvmInventoryDetailBean tempCondition = null;
        for (int i = 0; i < ivDetailList.size(); i++) {
            tempOutInfo = ivDetailList.get(i);

            //此处需要添加一个卖家囤货库存的CHECK，可能需要出库接口中提供一个类型判断平台还是买手
            InventoryViewBean ivViewBean = new InventoryViewBean();
            ivViewBean.setPdCode(tempOutInfo.getPdCode());
            ivViewBean.setLogicArea(tempOutInfo.getLogicArea());
            ivViewBean.setPlatform(tempOutInfo.getPlatform());
            ivViewBean.setSlType(tempOutInfo.getSlType());
            ivViewBean.setSlId(tempOutInfo.getSlId());
            ivViewBean.setOwnerCode(tempOutInfo.getOwnerCode());
            ivViewBean.setIvType(tempOutInfo.getIvStatus());
            List<InventoryViewBean> ivViewBeanList = ivViewService.queryOwnerPdListByCondition(ivViewBean);
            if (ivViewBeanList.size() == 0) {
                throw new BusinessException("没有满足条件的库存，请再次确认卖家，物流区域，平台，库存类型和产品编码信息。");
            }
            BigDecimal onhandQty = ivViewBeanList.get(0).getOnhandQty();
            BigDecimal allocatedQty = ivViewBeanList.get(0).getAllocatedQty();
            BigDecimal requestQty = tempOutInfo.getQty();
            if (onhandQty.compareTo(requestQty) < 0) {
                if ("1".equals(tempOutInfo.getSlType())){
                    throw new BusinessException("品（"+tempOutInfo.getPdCode()+"）平台可用库存（"+onhandQty+"）不足,发货量（"+requestQty+"）");
                } else {
                    throw new BusinessException("品（"+tempOutInfo.getPdCode()+"）买手可用囤货库存（"+onhandQty+"）不足,发货量（"+requestQty+"）");
                }
            }
            if (allocatedQty.compareTo(requestQty) < 0) {
                if ("1".equals(tempOutInfo.getSlType())) {
                    throw new BusinessException("品（"+tempOutInfo.getPdCode()+"）平台占用库存（"+allocatedQty+"）不足,发货量（"+requestQty+"）");
                }else{
                    throw new BusinessException("品（"+tempOutInfo.getPdCode()+"）买手占用囤货库存（"+allocatedQty+"）不足,发货量（"+requestQty+"）");
                }
            }
            tempCondition = new IvmInventoryDetailBean();
            tempCondition.setPdCode(tempOutInfo.getPdCode());
            tempCondition.setSkuCode(tempOutInfo.getSkuCode());
            tempCondition.setPucharseBatch(tempOutInfo.getPucharseBatch());
            tempCondition.setLogicArea(tempOutInfo.getLogicArea());
            tempCondition.setPlatform(tempOutInfo.getPlatform());
            tempCondition.setWhCode(tempOutInfo.getWhCode());
            tempCondition.setQty(requestQty);
            tempCondition.setOwnerCode(tempOutInfo.getOwnerCode());

            tempOutInfo.setUpdId(outboundBy);
            tempOutInfo.setUpdTime(outboundTime);
            tempOutInfo.setDispatchedDate(tempOutInfo.getDispatchedDate());
            tempOutInfo.setDispatchedTime(tempOutInfo.getDispatchedTime());
            this.doOutboundForDetail(tempCondition,tempOutInfo.getQty(),tempOutInfo);

            IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();// 库存日志对象
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
            transactionLog.setTransactionDate(outboundTime);
            transactionLog.setTransactionType(tempOutInfo.getOutboundType());
            transactionLog.setPucharseBatch(tempOutInfo.getPucharseBatch());
            transactionLog.setInnerBatch(tempOutInfo.getInnerBatch());
            transactionLog.setPrice(tempOutInfo.getSellPrice());
            transactionLog.setQty(tempOutInfo.getQty());
            transactionLog.setUom("箱");
            transactionLog.setCrtId(outboundBy);
            transactionLog.setCrtTime(outboundTime);
            //transactionLog.setUpdId(tempOutInfo.getUpdId());
            //transactionLog.setUpdTime(tempOutInfo.getUpdTime());
            transactionLog.setActId(tempOutInfo.getActId());
            //transactionLog.setActTime(tempOutInfo.getActTime());

            transactionLogService.insertOneTransactionLog(transactionLog);// 库存日志表插入数据
        }

        inventoryDetailService.dispatchByOutbound(tempOutInfo);

        if("2".equals(tempOutInfo.getSlType())){
        slOnhandLogicService.calOnhandQtyForDispatch(tempOutInfo);
        }

        ivAllocatedLogicService.calAllocatedQtyForDispatch(tempOutInfo);

            inventoryByProdService.touchIvProdByOutbound(tempOutInfo);

    }

    @Transactional
    public void doOutboundForDetail(IvmInventoryDetailBean condition, BigDecimal outQty,
        IvmInventoryDetailBean outboundInfo) throws Exception {
        inventoryDetailService.outboundInventoryByQty(condition,outQty,outboundInfo);
    }

}
