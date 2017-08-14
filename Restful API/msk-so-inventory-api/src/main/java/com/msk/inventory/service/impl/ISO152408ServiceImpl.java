package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.BeloneCodeUtil;
import com.msk.common.utils.PartCodeUtil;
import com.msk.inventory.bean.ISO152408ParamBean;
import com.msk.inventory.bean.ISO152408PdListParamBean;
import com.msk.inventory.bean.IvmAllocatedLogicBean;
import com.msk.inventory.bean.IvmTransactionLogBean;
import com.msk.inventory.service.IISO152408Service;
import com.msk.inventory.service.IIvmAllocatedLogicService;
import com.msk.inventory.service.ITransactionLogService;
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
public class ISO152408ServiceImpl extends BaseService implements IISO152408Service {

    @Autowired
    private IIvmAllocatedLogicService ivmAllocatedLogicService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Transactional
    public void updateDecreaseQty(ISO152408ParamBean sqlBean, String allocatedBy, Date allocatedTime) throws Exception {
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
            IvmAllocatedLogicBean allocatedLogic = new IvmAllocatedLogicBean();
            ISO152408PdListParamBean sqlBeans = sqlBean.getPdList().get(i);

            if (sqlBeans.getPdCode() == null || sqlBeans.getPdCode().equals("")) {
                throw new BusinessException("产品编号不能为空");
            }
            if (sqlBeans.getInventoryStatus() == null || sqlBeans.getInventoryStatus().equals("")) {
                throw new BusinessException("库存类型不能为空");
            }

            allocatedLogic.setIvType(sqlBeans.getInventoryStatus());
            allocatedLogic.setAllocatedQty(sqlBeans.getDecreaseQty());
            PartCodeUtil.parseCodeForPdCode(allocatedLogic, sqlBeans.getPdCode());
            allocatedLogic.setProductXml(PartCodeUtil.getPmExternalXml(allocatedLogic));
            allocatedLogic.setSkuCode("");
            allocatedLogic.setLogicArea(sqlBean.getLgcsCode());
            allocatedLogic.setPlatform(sqlBean.getPlantFormId());
            allocatedLogic.setSlType(sqlBean.getSlType());
            allocatedLogic.setSlId(sqlBean.getSlCode());
            allocatedLogic.setBeloneXml(BeloneCodeUtil.getBeloneXml(allocatedLogic));
            // 先根据参数查询
            List<IvmAllocatedLogicBean> allocatedLogicList = ivmAllocatedLogicService
                .selectAllocatedLogicList(allocatedLogic);

            if (allocatedLogicList.size() > 0) {// 有数据则更改数量
                BigDecimal allocatedQty = allocatedLogicList.get(0).getAllocatedQty();
                BigDecimal decreaseQty = allocatedLogic.getAllocatedQty();
                if (allocatedQty.compareTo(decreaseQty) >= 0) {
                    IvmAllocatedLogicBean newALlocatedLogic = new IvmAllocatedLogicBean();
                    newALlocatedLogic.setPdCode(sqlBeans.getPdCode());
                    newALlocatedLogic.setLogicArea(sqlBean.getLgcsCode());
                    newALlocatedLogic.setPlatform(sqlBean.getPlantFormId());
                    newALlocatedLogic.setSlType(sqlBean.getSlType());
                    newALlocatedLogic.setSlId(sqlBean.getSlCode());
                    newALlocatedLogic.setAllocatedQty(allocatedLogic.getAllocatedQty());
                    newALlocatedLogic.setHasNullOwner(true);
                    ivmAllocatedLogicService.cutAllocatedQty(newALlocatedLogic);

                    IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();// 库存日志表对象
                    transactionLog.setCrtId(allocatedBy);
                    transactionLog.setCrtTime(allocatedTime);
                    transactionLog.setBeloneXml(allocatedLogic.getBeloneXml());
                    transactionLog.setProductXml(allocatedLogic.getProductXml());
                    transactionLog.setQty(sqlBeans.getDecreaseQty());
                    transactionLog.setUom("箱");
                    transactionLog.setTransactionId(Long.valueOf(sqlBean.getOrderId()));
                    transactionLog.setTransactionNo(sqlBean.getOrderCode());
                    transactionLog.setTransactionType(InventoryCodeMasterDef.TranType.AL_SUB_SELLER+"");//卖家占用减少
                    transactionLog.setTransactionDate(allocatedTime);
                    transactionLogService.insertOneTransactionLog(transactionLog);// 新增日志

                } else {
                    throw new BusinessException("产品（" + sqlBeans.getPdCode() + "）在卖家（" + sqlBean.getSlCode()
                            + "）中的占用库存（" + allocatedQty + "）小于需要减少库存(" + decreaseQty + ")");
                }
            } else {
                throw new BusinessException(
                    "未找到该产品（" + sqlBeans.getPdCode() + "）在卖家（" + sqlBean.getSlCode() + "）中的占用数据");
            }
        }
    }
}
