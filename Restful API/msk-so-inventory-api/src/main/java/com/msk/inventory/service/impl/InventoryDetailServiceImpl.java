package com.msk.inventory.service.impl;

import com.msk.comm.exception.BusinessException;
import com.msk.common.base.mybatis.spring.BaseService;
import com.msk.common.constant.business.InventoryCodeMasterDef;
import com.msk.common.utils.DateTimeUtil;
import com.msk.inventory.bean.IvmInventoryDetailBean;
import com.msk.inventory.service.ICargoIdentityService;
import com.msk.inventory.service.IInventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfan on 16/8/15.
 */
@Service
public class InventoryDetailServiceImpl extends BaseService implements IInventoryDetailService {

    @Autowired
    private ICargoIdentityService cargoIdentityService;

    @Transactional
    public List<IvmInventoryDetailBean> selectInventoryDetailList(IvmInventoryDetailBean sqlBean){
        List<IvmInventoryDetailBean> inventoryDetailList = new ArrayList<IvmInventoryDetailBean>();
        inventoryDetailList=this.findList("selectIvDetail",sqlBean);
        return  inventoryDetailList;
    }

    @Transactional
    public int selectInventoryDetailCount(IvmInventoryDetailBean sqlBean){
        int pageCount= this.getCount("selectIvDetailCount",sqlBean);
        return  pageCount;
    }

    @Transactional
    public IvmInventoryDetailBean getOneSortInventoryDetail(IvmInventoryDetailBean sqlBean) {
        IvmInventoryDetailBean inventoryDetailBean = new IvmInventoryDetailBean();
        inventoryDetailBean = this.findOne("getOneSortInventoryDetail", sqlBean);
        return inventoryDetailBean;
    }

    @Transactional
    public BigDecimal getSumQtyFromIvDetail(IvmInventoryDetailBean sqlBean) {
        BigDecimal bigDecimal = this.findOne("getSumQtyFromIvDetail", sqlBean);
        return bigDecimal;
    }

    @Transactional
    public void insertOneInventoryDetail(IvmInventoryDetailBean sqlBean) {
        this.save("insertOneIvDetail", sqlBean);
    }

    @Transactional
    public void receiveByInbound(IvmInventoryDetailBean sqlBean) {
        if(!(sqlBean.getRecvDate()==null|| sqlBean.getRecvTime()==null)){
        this.modify("receiveByInbound", sqlBean);
        }else{
            throw new BusinessException("收货日期和收货时间不能为空！");
    }
    }

    @Transactional
    private void receiveByLoad(IvmInventoryDetailBean sqlBean) {
        this.modify("receiveByLoad", sqlBean);
    }

    @Transactional
    private void notReceiveByLoad(IvmInventoryDetailBean sqlBean) {
        this.modify("notReceiveByLoad", sqlBean);
    }

    @Transactional
    public void receiveByInboundDetailAndQty(long inboundId, long inboundDetailId, BigDecimal qty) {
        IvmInventoryDetailBean tempCondition = new IvmInventoryDetailBean();
        tempCondition.setInboundId(inboundId);
        tempCondition.setInboundDetailId(inboundDetailId);

        //确定库存并收货
        IvmInventoryDetailBean findTheIv = getOneSortInventoryDetail(tempCondition);
        receiveByLoad(findTheIv);

        BigDecimal notReceivedQty = findTheIv.getQty().subtract(qty);
        if(notReceivedQty.compareTo(BigDecimal.ZERO) > 0){
        findTheIv.setQty(notReceivedQty);
        splitIvDetail(findTheIv);

        notReceiveByLoad(findTheIv);
    }
    }

    @Transactional
    public void putawayByInbound(IvmInventoryDetailBean sqlBean) {
        this.modify("putawayByInbound", sqlBean);
    }

    @Transactional
    public void delIvDetailByInboundId(IvmInventoryDetailBean sqlBean) {
        this.remove("delIvDetailByInboundId", sqlBean);
    }

    @Transactional
    public void updateIvDetailFlag(IvmInventoryDetailBean sqlBean) {
        this.modify("updateIvDetailFlag", sqlBean);
    }
    
    /**
     *  库存维护
     * 
     * @param requestQty 所需数量
     */
    @Transactional
    public void updateIvFlagByCondition(IvmInventoryDetailBean condition, BigDecimal requestQty, String newFlag,
        String reason, String operator) {
        BigDecimal bigDecimal = getSumQtyFromIvDetail(condition);//根据批次号获取产品数量总和

        if(requestQty.compareTo(bigDecimal)>0){
            throw new BusinessException("所输数量("+requestQty+")大于库存数量总和("+bigDecimal+")");
        }

        IvmInventoryDetailBean splitCondition = null;
        IvmInventoryDetailBean updateCondition = null;
        IvmInventoryDetailBean sortInventoryDetailBean = null;
        while(requestQty.compareTo(BigDecimal.ZERO) > 0){
            //根据产品批次号获取库存数据
            sortInventoryDetailBean = getOneSortInventoryDetail(condition);

            if(requestQty.compareTo(sortInventoryDetailBean.getQty()) >= 0){
                //根据通过货品身份和身份序列修改对应库存货物标识
                updateCondition.setLoadNo(sortInventoryDetailBean.getLoadNo());
                updateCondition.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                updateCondition.setIvFlag(newFlag);
                updateCondition.setFlagCTime(DateTimeUtil.getCustomerDate());
                updateCondition.setFlagCReason(reason);
                updateIvDetailFlag(updateCondition);

                requestQty = requestQty.subtract(sortInventoryDetailBean.getQty());
            }else{
                splitCondition = new IvmInventoryDetailBean();
                splitCondition.setIvFlag(sortInventoryDetailBean.getIvFlag());
                splitCondition.setLoadNo(sortInventoryDetailBean.getLoadNo());
                splitCondition.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                splitCondition.setCrtId(operator);
                splitCondition.setCrtTime(DateTimeUtil.getCustomerDate());
                splitCondition.setQty(requestQty);

                //根据参数进行切数据
                splitIvDetail(splitCondition);

                //根据通过货品身份和身份序列修改对应库存货物标识
                updateCondition.setLoadNo(sortInventoryDetailBean.getLoadNo());
                updateCondition.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                updateCondition.setIvFlag(newFlag);
                updateCondition.setFlagCTime(DateTimeUtil.getCustomerDate());
                updateCondition.setFlagCReason(reason);
                updateIvDetailFlag(updateCondition);

                requestQty = BigDecimal.ZERO;
            }
        }
    }

    @Transactional
    public void splitIvDetail(IvmInventoryDetailBean sqlBean) {
        this.modify("splitIvDetail", sqlBean);
    }

    @Transactional
    public void outboundIvDetail(IvmInventoryDetailBean sqlBean) {
        this.modify("outboundIvDetail", sqlBean);
    }

    /**
     *  货品出库业务处理
     * 
     * @param requestQty 所需数量
     */
    @Transactional
    public void outboundInventoryByQty(IvmInventoryDetailBean condition, BigDecimal requestQty,
        IvmInventoryDetailBean outboundInfo) {
        condition.setIvStatus(String.valueOf(InventoryCodeMasterDef.IvStatus.IS_PUTAWAY));
        condition.setIvFlag(InventoryCodeMasterDef.GoodType.GT_GOOD+"");

        //待定,由于使用仓库系统库存作为出库依据,取消此验证,待完全使用自有库存管理和数据提供时再启用
        /*
         * BigDecimal bigDecimal = getSumQtyFromIvDetail(condition);// 根据批次号获取产品数量总和
         * if (requestQty.compareTo(bigDecimal) > 0) {
         * throw new BusinessException("出库数量大于库存数量总和: 库存" + bigDecimal + " 需要出库" + requestQty);
         * }
         */

        IvmInventoryDetailBean splitCondition = null;
        IvmInventoryDetailBean sortInventoryDetailBean = null;
        while(requestQty.compareTo(BigDecimal.ZERO) > 0){
            //根据产品批次号获取库存数据
            sortInventoryDetailBean = getOneSortInventoryDetail(condition);
            // sortInventoryDetailBean为空,动态插入一个数据来补货出库,入库信息自定义
            if (sortInventoryDetailBean == null) {
                sortInventoryDetailBean = new IvmInventoryDetailBean();

                sortInventoryDetailBean.setSkuCode(condition.getSkuCode());
                sortInventoryDetailBean.setPucharseBatch(condition.getPucharseBatch());
                sortInventoryDetailBean.setInnerBatch(condition.getInnerBatch());
                sortInventoryDetailBean.setLogicArea(condition.getLogicArea());
                sortInventoryDetailBean.setPlatform(condition.getPlatform());

                String synchroNo = cargoIdentityService.getLoadNo(DateTimeUtil.getCustomerDate());
                sortInventoryDetailBean.setLoadNo(synchroNo);
                sortInventoryDetailBean.setLoadSeq(1);
                sortInventoryDetailBean.setPmCode(condition.getPdCode()+condition.getSkuCode());
                sortInventoryDetailBean.setWhCode(condition.getWhCode());
                sortInventoryDetailBean.setInboundId(Long.parseLong("0"));
                sortInventoryDetailBean.setInboundDetailId(Long.parseLong("0"));
                sortInventoryDetailBean.setInboundNo(outboundInfo.getOutboundNo());
                sortInventoryDetailBean.setInboundType(InventoryCodeMasterDef.InboundType.IT_ADJUST+"");//调整入库
                sortInventoryDetailBean.setBuyPrice(BigDecimal.valueOf(0));
                sortInventoryDetailBean.setQty(condition.getQty());
                sortInventoryDetailBean.setUom("箱");
                sortInventoryDetailBean.setIvFlag(InventoryCodeMasterDef.GoodType.GT_GOOD + "");
                sortInventoryDetailBean.setIvStatus(String.valueOf(InventoryCodeMasterDef.IvStatus.IS_IN_TRANSIT));
                sortInventoryDetailBean.setDelFlg("0");
                sortInventoryDetailBean.setCrtId(outboundInfo.getUpdId());
                sortInventoryDetailBean.setCrtTime(outboundInfo.getUpdTime());
                sortInventoryDetailBean.setSupplierCode(condition.getSupplierName());
                sortInventoryDetailBean.setSlType(InventoryCodeMasterDef.SlType.ST_PLATFORM+"");
                sortInventoryDetailBean.setSlId("0000000");
                sortInventoryDetailBean.setOwnerCode(condition.getOwnerCode());
                sortInventoryDetailBean.setRecvDate(outboundInfo.getDispatchedTime());
                sortInventoryDetailBean.setRecvTime(outboundInfo.getDispatchedTime());

                insertOneInventoryDetail(sortInventoryDetailBean);
            //待定,由于使用仓库系统库存作为出库依据,添加此功能,待完全使用自有库存管理和数据提供时再取消
            }
            if(requestQty.compareTo(sortInventoryDetailBean.getQty()) >= 0){
                //根据通过货品身份和身份序列修改对应库存货物标识
                outboundInfo.setLoadNo(sortInventoryDetailBean.getLoadNo());
                outboundInfo.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                outboundInfo.setVer(sortInventoryDetailBean.getVer());
                outboundIvDetail(outboundInfo);

                requestQty = requestQty.subtract(sortInventoryDetailBean.getQty());
            }else{
                splitCondition = new IvmInventoryDetailBean();
                splitCondition.setLoadNo(sortInventoryDetailBean.getLoadNo());
                splitCondition.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                splitCondition.setCrtId(outboundInfo.getCrtId());
                splitCondition.setCrtTime(DateTimeUtil.getCustomerDate());
                splitCondition.setQty(requestQty);
                splitCondition.setVer(sortInventoryDetailBean.getVer());

                //根据参数进行切数据
                splitIvDetail(splitCondition);

                //根据通过货品身份和身份序列修改对应库存货物标识
                outboundInfo.setLoadNo(sortInventoryDetailBean.getLoadNo());
                outboundInfo.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                outboundInfo.setRequestQty(sortInventoryDetailBean.getRequestQty());
                outboundIvDetail(outboundInfo);

                requestQty = BigDecimal.ZERO;
            }
        }
    }

    @Transactional
    public void dispatchByOutbound(IvmInventoryDetailBean sqlBean) {
        this.modify("dispatchByOutbound", sqlBean);
    }

    @Transactional
    public void undoDispatchIvDetail(IvmInventoryDetailBean sqlBean) {
        this.modify("undoDispatchIvDetail", sqlBean);
    }

    @Transactional
    public void undoOutboundIvDetail(IvmInventoryDetailBean sqlBean) {
        this.modify("undoOutboundIvDetail", sqlBean);
    }

    @Transactional
    public void deliverByOutbound(IvmInventoryDetailBean sqlBean) {
        this.modify("deliverByOutbound", sqlBean);
    }

    @Transactional
    private void deliverByLoad(IvmInventoryDetailBean sqlBean) {
        this.modify("deliverByLoad", sqlBean);
    }

    @Transactional
    private void notDeliverByLoad(IvmInventoryDetailBean sqlBean) {
        this.modify("notDeliverByLoad", sqlBean);
    }

    @Transactional
    private void notDeliverByOutboundDetailAndStatus(IvmInventoryDetailBean sqlBean) {
        this.modify("notDeliverByOutboundDetailAndStatus", sqlBean);
    }

    @Transactional
    public void deliverByOutboundDetailAndQty(long outboundId, String outboundNo, long outboundDetailId, BigDecimal requestQty,
        String operator) {
        IvmInventoryDetailBean tempCondition = new IvmInventoryDetailBean();
        tempCondition.setOutboundId(outboundId);
        tempCondition.setOutboundNo(outboundNo);
        tempCondition.setOutboundDetailId(outboundDetailId);
        tempCondition.setIvStatus(String.valueOf(InventoryCodeMasterDef.IvStatus.IS_DISPATCHED));

        IvmInventoryDetailBean outboundInfo = new IvmInventoryDetailBean();
        IvmInventoryDetailBean sortInventoryDetailBean = null;
        while (requestQty.compareTo(BigDecimal.ZERO) > 0) {
            // 根据产品批次号获取库存数据
            sortInventoryDetailBean = getOneSortInventoryDetail(tempCondition);

            if (requestQty.compareTo(sortInventoryDetailBean.getQty()) >= 0) {
                // 根据通过货品身份和身份序列修改对应库存货物标识
                outboundInfo.setLoadNo(sortInventoryDetailBean.getLoadNo());
                outboundInfo.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                outboundInfo.setVer(sortInventoryDetailBean.getVer());
                deliverByLoad(outboundInfo);

                requestQty = requestQty.subtract(sortInventoryDetailBean.getQty());
            } else {
                outboundInfo.setLoadNo(sortInventoryDetailBean.getLoadNo());
                outboundInfo.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                outboundInfo.setCrtId(operator);
                outboundInfo.setCrtTime(DateTimeUtil.getCustomerDate());
                outboundInfo.setQty(requestQty);
                outboundInfo.setVer(sortInventoryDetailBean.getVer());

                // 根据参数进行切数据
                splitIvDetail(outboundInfo);

                // 根据通过货品身份和身份序列修改对应库存货物标识
                outboundInfo.setLoadNo(sortInventoryDetailBean.getLoadNo());
                outboundInfo.setLoadSeq(sortInventoryDetailBean.getLoadSeq());
                outboundInfo.setRequestQty(sortInventoryDetailBean.getRequestQty());
                deliverByLoad(outboundInfo);

                requestQty = BigDecimal.ZERO;
            }
        }

        this.notDeliverByOutboundDetailAndStatus(tempCondition);
    }
}
