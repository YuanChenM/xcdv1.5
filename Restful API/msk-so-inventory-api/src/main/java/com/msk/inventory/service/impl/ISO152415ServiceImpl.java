package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.BeloneCodeUtil;
import com.msk.common.utils.PartCodeUtil;
import com.msk.common.utils.StringUtil;
import com.msk.inventory.bean.*;
import com.msk.inventory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zheng_xu on 2016/9/7.
 */
@Service
public class ISO152415ServiceImpl extends BaseService implements IISO152415Service {

    @Autowired
    private IInventoryByProdService inventoryByProdService;

    @Autowired
    private ISlOnhandLogicService slOnhandLogicService;

    @Autowired
    private IInfoDefService infoDefService;

    @Autowired
    private IIvmAllocatedLogicService ivAllocatedLogicService;

    @Autowired
    private ITransactionLogService transactionLogService;

    @Autowired
    private IInventoryViewService inventoryViewService;

    @Transactional
    public void updateBuyerStockpile(ISO152415ParamBean sqlBean, String stockpileBy, Date stockpileTime)
        throws Exception {
        BigDecimal prodQty = BigDecimal.ZERO;//总库存
        IvmSlOnhandLogicBean onhandParam = null;
        for (int i = 0; i < sqlBean.getInvList().size(); i++) {
            ISO152415InvParamBean buyerParam = sqlBean.getInvList().get(i);

            if (buyerParam.getSalePlatform() == null || buyerParam.getSalePlatform().equals("")) {
                throw new BusinessException("供货平台不能为空");
            }
            if (buyerParam.getLogicCode() == null || buyerParam.getLogicCode().equals("")) {
                throw new BusinessException("物流区编号不能为空");
            }
            if (buyerParam.getInventoryStatus() == null || buyerParam.getInventoryStatus().equals("")) {
                throw new BusinessException("库存货物类型不能为空");
            }
            if (buyerParam.getOutSlCode() == null || buyerParam.getOutSlCode().equals("")) {
                throw new BusinessException("出库卖家编码（神农客平台）不能为空");
            }
            if (buyerParam.getOutSlType() == null || buyerParam.getOutSlType().equals("")) {
                throw new BusinessException("平台或买手不能为空");
            }
            if (buyerParam.getInSlCode() == null || buyerParam.getInSlCode().equals("")) {
                throw new BusinessException("入库卖家编码（囤货的买手）不能为空");
            }
            if (buyerParam.getInSlType() == null || buyerParam.getInSlType().equals("")) {
                throw new BusinessException("平台或买手不能为空");
            }
            if (buyerParam.getSupplierCode() == null || buyerParam.getSupplierCode().equals("")) {
                throw new BusinessException("供应商编码不能为空");
            }
            if (buyerParam.getPdCode() == null || buyerParam.getPdCode().equals("")) {
                throw new BusinessException("产品编号不能为空");
            }
            if (buyerParam.getOutboundQty() == null || buyerParam.getOutboundQty().equals("")) {
                throw new BusinessException("出库数量不能为空");
            }
            if (buyerParam.getOutboundPrice() == null || buyerParam.getOutboundPrice().equals("")) {
                throw new BusinessException("出库价格不能为空");
            }
            if (buyerParam.getInSlType().equals("1") && buyerParam.getOutSlType().equals("1")) {
                throw new BusinessException("平台无需囤货");
            }

            // 查视图可用数量（通过获取到的参数）
            InventoryViewBean inventoryViewBean = new InventoryViewBean();
            inventoryViewBean.setPdCode(buyerParam.getPdCode());
            inventoryViewBean.setLogicArea(buyerParam.getLogicCode());
            inventoryViewBean.setPlatform(buyerParam.getSalePlatform());
            inventoryViewBean.setSlType(buyerParam.getOutSlType());
            inventoryViewBean.setSlId(buyerParam.getOutSlCode());
            inventoryViewBean.setIvType(buyerParam.getInventoryStatus());
            inventoryViewBean.setHasNullOwner(true);
            BigDecimal availableQty = inventoryViewService.getAvailableSumByCondition(inventoryViewBean);
            // 判断 当前<可用数量
            if (availableQty.compareTo(BigDecimal.ZERO) == 0) {
                throw new BusinessException("品（" + buyerParam.getPdCode() + "）无符合条件（" + buyerParam.getLogicCode() + ","
                        + buyerParam.getSalePlatform() + "）的可用卖家库存");
            }
            if (availableQty.compareTo(buyerParam.getOutboundQty()) >= 0) {
                if (buyerParam.getOutSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_PLATFORM))
                        && buyerParam.getInSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_BUYER))) {
                    //减少占用
                    IvmAllocatedLogicBean cutAlloBean = new IvmAllocatedLogicBean();
                    cutAlloBean.setHasNullOwner(true);
                    cutAlloBean.setIvType(buyerParam.getInventoryStatus());
                    cutAlloBean.setLogicArea(buyerParam.getLogicCode());
                    cutAlloBean.setPlatform(buyerParam.getSalePlatform());
                    cutAlloBean.setSlType(buyerParam.getOutSlType());
                    cutAlloBean.setSlId(buyerParam.getOutSlCode());
                    cutAlloBean.setPdCode(buyerParam.getPdCode());
                    cutAlloBean.setAllocatedQty(buyerParam.getOutboundQty());
                    ivAllocatedLogicService.cutAllocatedQty(cutAlloBean);

                    // 囤货，新增或累加
            onhandParam = new IvmSlOnhandLogicBean();//针对表sl_onhandLogic操作所需参数
            onhandParam.setPlatform(buyerParam.getSalePlatform());
            onhandParam.setLogicArea(buyerParam.getLogicCode());
            PartCodeUtil.parseCodeForPdCode(onhandParam, buyerParam.getPdCode());
            onhandParam.setProductXml(PartCodeUtil.getPmExternalXml(onhandParam));
            onhandParam.setOwnerCode(buyerParam.getSupplierCode());
            onhandParam.setIvType(buyerParam.getInventoryStatus());
            onhandParam.setOnhandQty(buyerParam.getOutboundQty());
                    onhandParam.setSlType(buyerParam.getInSlType());
                    onhandParam.setSlId(buyerParam.getInSlCode());
                    onhandParam.setBeloneXml(BeloneCodeUtil.getBeloneXml(onhandParam));
                    // 根据参数查询sl_onhandLogic表
                    List<IvmSlOnhandLogicBean> onhandList = slOnhandLogicService.getSlOnhandLogicList(onhandParam);
                    if (onhandList.size() > 0) {
                        IvmSlOnhandLogicBean temp = onhandList.get(0);
                        IvmSlOnhandLogicBean setonhandBean = new IvmSlOnhandLogicBean();
                        setonhandBean.setPdCode(temp.getPdCode());
                        setonhandBean.setPlatform(onhandParam.getPlatform());
                        setonhandBean.setSlType(onhandParam.getSlType());
                        setonhandBean.setLogicArea(onhandParam.getLogicArea());
                        setonhandBean.setSlId(onhandParam.getSlId());
                        setonhandBean.setOwnerCode(temp.getOwnerCode());
                        setonhandBean.setIvType(temp.getIvType());
                        setonhandBean.setOnhandQty(buyerParam.getOutboundQty());
                        slOnhandLogicService.addOnhandQty(setonhandBean);
                    } else {
                        IvmSlOnhandLogicBean onhandLogic = new IvmSlOnhandLogicBean();// 囤货表对象
                        PartCodeUtil.parseCodeForPdCode(onhandLogic, buyerParam.getPdCode());
                        onhandLogic.setProductXml(PartCodeUtil.getPmExternalXml(onhandLogic));
                        onhandLogic.setPlatform(buyerParam.getSalePlatform());
                        onhandLogic.setLogicArea(buyerParam.getLogicCode());
                        onhandLogic.setSlType(buyerParam.getInSlType());
                        onhandLogic.setSlId(buyerParam.getInSlCode());
                        onhandLogic.setBeloneXml(BeloneCodeUtil.getBeloneXml(onhandLogic));
                        onhandLogic.setOwnerCode(buyerParam.getSupplierCode());
                        onhandLogic.setIvType(buyerParam.getInventoryStatus());
                        onhandLogic.setOnhandQty(buyerParam.getOutboundQty());
                        // 向囤货表onhandLogic插入数据
                        slOnhandLogicService.insertSlOnhandLogic(onhandLogic);
                    }
                } else if (buyerParam.getOutSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_BUYER))
                        && buyerParam.getInSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_PLATFORM))) {
                    // 释放
                    onhandParam = new IvmSlOnhandLogicBean();// 针对表sl_onhandLogic操作所需参数
                    onhandParam.setPdCode(buyerParam.getPdCode());
                    onhandParam.setIvType(buyerParam.getInventoryStatus());
                    onhandParam.setOnhandQty(buyerParam.getOutboundQty());
                    onhandParam.setPlatform(buyerParam.getSalePlatform());
                    onhandParam.setLogicArea(buyerParam.getLogicCode());
                    onhandParam.setSlType(buyerParam.getOutSlType());
                    onhandParam.setSlId(buyerParam.getOutSlCode());
                    // 根据参数查询sl_onhandLogic表，检查买手库存
                    List<IvmSlOnhandLogicBean> slOnhandLogicList = slOnhandLogicService.getSlOnhandLogicList(onhandParam);
                    if (slOnhandLogicList.size() == 0) {
                        throw new BusinessException("买手囤货，没有满足条件的库存，请再次确认卖家，物流区域，平台，库存类型和产品编码信息。");
                    }
                    BigDecimal slOnhandQty = slOnhandLogicList.get(0).getOnhandQty();// 查询到可用数量
                    if (slOnhandQty.compareTo(buyerParam.getOutboundQty()) >= 0) {
                        // 更改slonhandlogic表数量
                        slOnhandLogicService.cutOnhandQty(onhandParam);
                    } else {
                        throw new BusinessException(
                            "商品（" + buyerParam.getPdCode() + "）囤货数量（" + buyerParam.getOutboundQty() + "）大于卖家（"
                                    + buyerParam.getOutSlCode() + "）库存（" + slOnhandQty + "）");
                    }
                } else if (buyerParam.getOutSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_BUYER))
                        && buyerParam.getInSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_BUYER))) {
                    // 释放
                    onhandParam = new IvmSlOnhandLogicBean();// 针对表sl_onhandLogic操作所需参数
                    onhandParam.setPdCode(buyerParam.getPdCode());
                    onhandParam.setIvType(buyerParam.getInventoryStatus());
                    onhandParam.setOnhandQty(buyerParam.getOutboundQty());
                    onhandParam.setPlatform(buyerParam.getSalePlatform());
                    onhandParam.setLogicArea(buyerParam.getLogicCode());
                    onhandParam.setSlType(buyerParam.getOutSlType());
                    onhandParam.setSlId(buyerParam.getOutSlCode());
                    // 根据参数查询sl_onhandLogic表，检查买手库存
                    List<IvmSlOnhandLogicBean> slOnhandLogicList = slOnhandLogicService
                        .getSlOnhandLogicList(onhandParam);
                    if (slOnhandLogicList.size() == 0) {
                        throw new BusinessException("买手囤货，没有满足条件的库存，请再次确认卖家，物流区域，平台，库存类型和产品编码信息。");
                    }
                    BigDecimal slOnhandQty = slOnhandLogicList.get(0).getOnhandQty();// 查询到可用数量
                    if (slOnhandQty.compareTo(buyerParam.getOutboundQty()) >= 0) {
                        // 更改slonhandlogic表数量
                        slOnhandLogicService.cutOnhandQty(onhandParam);
                }else{
                        throw new BusinessException(
                            "商品（" + buyerParam.getPdCode() + "）囤货数量（" + buyerParam.getOutboundQty() + "）大于卖家（"
                                    + buyerParam.getOutSlCode() + "）库存（" + slOnhandQty + "）");
                    }
                    //减少占用
                    IvmAllocatedLogicBean cutAlloBean = new IvmAllocatedLogicBean();
                    cutAlloBean.setHasNullOwner(true);
                    cutAlloBean.setIvType(buyerParam.getInventoryStatus());
                    cutAlloBean.setLogicArea(buyerParam.getLogicCode());
                    cutAlloBean.setPlatform(buyerParam.getSalePlatform());
                    cutAlloBean.setSlType(buyerParam.getOutSlType());
                    cutAlloBean.setSlId(buyerParam.getOutSlCode());
                    cutAlloBean.setPdCode(buyerParam.getPdCode());
                    cutAlloBean.setAllocatedQty(buyerParam.getOutboundQty());
                    ivAllocatedLogicService.cutAllocatedQty(cutAlloBean);

                    // 囤货，新增或累加
                    onhandParam = new IvmSlOnhandLogicBean();// 针对表sl_onhandLogic操作所需参数
                    onhandParam.setPlatform(buyerParam.getSalePlatform());
                    onhandParam.setLogicArea(buyerParam.getLogicCode());
                    PartCodeUtil.parseCodeForPdCode(onhandParam, buyerParam.getPdCode());
                    onhandParam.setProductXml(PartCodeUtil.getPmExternalXml(onhandParam));
                    onhandParam.setOwnerCode(buyerParam.getSupplierCode());
                    onhandParam.setIvType(buyerParam.getInventoryStatus());
                    onhandParam.setOnhandQty(buyerParam.getOutboundQty());
                    onhandParam.setSlType(buyerParam.getInSlType());
                    onhandParam.setSlId(buyerParam.getInSlCode());
                    onhandParam.setBeloneXml(BeloneCodeUtil.getBeloneXml(onhandParam));
                    // 根据参数查询sl_onhandLogic表
                    List<IvmSlOnhandLogicBean> onhandList = slOnhandLogicService.getSlOnhandLogicList(onhandParam);
                        if (onhandList.size() > 0) {
                            IvmSlOnhandLogicBean temp = onhandList.get(0);
                                IvmSlOnhandLogicBean setonhandBean = new IvmSlOnhandLogicBean();
                                setonhandBean.setPdCode(temp.getPdCode());
                                setonhandBean.setPlatform(onhandParam.getPlatform());
                                setonhandBean.setSlType(onhandParam.getSlType());
                                setonhandBean.setLogicArea(onhandParam.getLogicArea());
                                setonhandBean.setSlId(onhandParam.getSlId());
                                setonhandBean.setOwnerCode(temp.getOwnerCode());
                                setonhandBean.setIvType(temp.getIvType());
                                setonhandBean.setOnhandQty(buyerParam.getOutboundQty());
                                slOnhandLogicService.addOnhandQty(setonhandBean);
                            } else {
                            IvmSlOnhandLogicBean onhandLogic = new IvmSlOnhandLogicBean();// 囤货表对象
                            PartCodeUtil.parseCodeForPdCode(onhandLogic, buyerParam.getPdCode());
                            onhandLogic.setProductXml(PartCodeUtil.getPmExternalXml(onhandLogic));
                            onhandLogic.setPlatform(buyerParam.getSalePlatform());
                            onhandLogic.setLogicArea(buyerParam.getLogicCode());
                            onhandLogic.setSlType(buyerParam.getInSlType());
                            onhandLogic.setSlId(buyerParam.getInSlCode());
                            onhandLogic.setBeloneXml(BeloneCodeUtil.getBeloneXml(onhandLogic));
                            onhandLogic.setOwnerCode(buyerParam.getSupplierCode());
                            onhandLogic.setIvType(buyerParam.getInventoryStatus());
                            onhandLogic.setOnhandQty(buyerParam.getOutboundQty());
                            // 向囤货表onhandLogic插入数据
                            slOnhandLogicService.insertSlOnhandLogic(onhandLogic);
                    }
                }else if (buyerParam.getOutSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_PLATFORM))
                        && buyerParam.getInSlType().equals(String.valueOf(InventoryCodeMasterDef.SlType.ST_PLATFORM))) {
                    throw new BusinessException("当前不支持平台间的囤货");
                }
                            // 同时记录卖家名称
                            List<IvmInfoDefBean> infoDefList = new ArrayList<IvmInfoDefBean>();
                            IvmInfoDefBean tempInfo = null;
                if (!StringUtil.isEmpty(buyerParam.getSalePlatform())) {
                                tempInfo = new IvmInfoDefBean();
                                tempInfo.setCodeType(IvmInfoDefBean.TYPE_PLATFORM);
                    tempInfo.setCodeValue(buyerParam.getSalePlatform());
                                infoDefList.add(tempInfo);
                            }
                if (!StringUtil.isEmpty(buyerParam.getInSlCode())) {
                                tempInfo = new IvmInfoDefBean();
                                tempInfo.setCodeType(IvmInfoDefBean.TYPE_SELLER);
                    tempInfo.setCodeValue(buyerParam.getInSlType());
                                tempInfo.setCodeDesc(buyerParam.getInSlName());
                                infoDefList.add(tempInfo);
                            }
                            // 向ivm_info_def表插入卖家名称
                            infoDefService.synInfoDefList(infoDefList);
                        // 向日志表插入一条数据
                IvmTransactionLogBean transactionLog = new IvmTransactionLogBean();
                transactionLog.setCrtId(stockpileBy);
                transactionLog.setCrtTime(stockpileTime);
                        transactionLog.setProductXml(onhandParam.getProductXml());
                        transactionLog.setBeloneXml(onhandParam.getBeloneXml());
                        transactionLog.setQty(buyerParam.getOutboundQty());
                transactionLog.setUom("箱");
                transactionLog.setTransactionId(Long.parseLong(buyerParam.getFlowId()));
                        transactionLog.setTransactionType(InventoryCodeMasterDef.TranType.AS_INVENTORY + "");// 买手囤货
                transactionLog.setTransactionDate(stockpileTime);
                        transactionLogService.insertOneTransactionLog(transactionLog);// 新增日志
                    } else {
                throw new BusinessException("商品（" + buyerParam.getPdCode() + "）可用库存（" + availableQty + "）不足，囤货数量（"
                        + buyerParam.getOutboundQty() + "）");
            }
        }
    }
}
