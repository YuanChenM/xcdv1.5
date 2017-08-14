package com.msk.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.msk.common.constant.NumberConstant;
import com.msk.common.constant.SystemConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.CommonSpecification;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.utils.*;
import com.msk.order.bean.param.ISO151422InvRestParam;
import com.msk.order.bean.param.ISO151422InvRestParamList;
import com.msk.order.bean.result.ISO151422InvRestResult;
import com.msk.order.entity.*;
import com.msk.order.repository.SoOrderRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ReturnOrderStatusService;
import com.msk.order.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msk.common.exception.BusinessException;
import com.msk.common.constant.business.OrderCodeMasterDef;
import com.msk.order.bean.param.ISO151422ProductRestParam;
import com.msk.order.bean.param.ISO151422RestParam;
import com.msk.order.bean.result.ISO151422RestResult;
import com.msk.order.repository.SoReturnDetailRepository;
import com.msk.order.repository.SoReturnRepository;
import com.msk.order.service.ISO151422Service;

/**
 * ISO151422_退货入库
 * Created by wang_jianzhou on 2016/8/17.
 */
@Service
public class ISO151422ServiceImpl extends BaseService implements ISO151422Service {

    @Autowired
    private SoReturnRepository soReturnRepository;

    @Autowired
    private SoReturnDetailRepository soReturnDetailRepository;

    @Autowired
    private ReturnOrderStatusService returnOrderStatusService;

    @Autowired
    private AsyncPostServiceImpl asyncPostService;

    @Autowired
    private SoOrderRepository soOrderRepository;

    @Autowired
    private BaseJdbcImpl baseJdbc;


    @Override
    public BaseRepository getRepository() {
        return null;
    }

    /**
     * 退货入库
     *
     * @param param
     */
    @Override
    @Transactional
    public ISO151422RestResult doInbound(ISO151422RestParam param) {

        Date inboundTime = DateTimeUtil.parseDate(param.getInboundTime(),"yyyy-MM-dd HH:mm:ss");
        if(null == inboundTime){
            throw new BusinessException("时间格式不正确");
        }

        Integer returnStatus = OrderCodeMasterDef.ReturnOrderStatus.STORAGE;
        param.setReturnStatus(returnStatus);
        //检查退货单状态是否已收货
        SoReturn soReturn = this.checkSoReturnStatus(param);
        SoOrder soOrder = this.findSalesPlatForm(soReturn);
        String crtTimeStr = DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", soReturn.getCrtTime());
        List<ISO151422ProductRestParam> productList = param.getProductList();
        for (ISO151422ProductRestParam product : productList) {
            product.setInboundQty(DecimalUtil.round(product.getInboundQty(),NumberConstant.IntDef.INT_TWO,BigDecimal.ROUND_HALF_UP));
            product.setReturnId(param.getReturnId());
            //检查退货单明细是否可退货
            this.checkSoReturnDetail(product);
        }

        //更新SO_RETURN
        soReturn = this.modifySoReturn(param,soReturn);
        //插入SO_RETURN_STATUS
        SoReturnStatus soReturnStatus = this.saveSoReturnStatus(param,soReturn);
        String warehouseCode = String.valueOf(NumberConstant.IntDef.INT_ZERO) + String.valueOf(NumberConstant.IntDef.INT_TWO) + String.valueOf(NumberConstant.IntDef.INT_ONE);
        String sql = this.getSql();
        List<ISO151422InvRestParam> iso151422InvRestParamList = new ArrayList<ISO151422InvRestParam>();

        for(ISO151422ProductRestParam product:productList){
            product.setDetailStatus(returnStatus);
            //更新SO_RETURN_DETAIL
            SoReturnDetail soReturnDetail = this.findAndModifySoReturnDetail(product.getDetailId(),product.getInboundQty(),param);
            //调用库存接口
            ISO151422InvRestParam iso151422InvRestParam = new ISO151422InvRestParam();
            ISO151422InvRestResult result = this.dealResult(sql,product);
            //平台ID 要求调用CodeMaster，目前api里引不到包暂时写一样的
            iso151422InvRestParam.setPlantFormId(soOrder.getSalePlatform());
            iso151422InvRestParam.setPlantformName(String.valueOf(NumberConstant.IntDef.INT_TEN) + soOrder.getSalePlatform());
            iso151422InvRestParam.setLgcsCode(soReturn.getDistrictCode());
            iso151422InvRestParam.setWarehouseCode(warehouseCode);
            iso151422InvRestParam.setFlowId(param.getReturnId());
            iso151422InvRestParam.setPurchaseBatch(result.getPurchaseBatch());
            iso151422InvRestParam.setSlCode(soReturn.getSellerCode());
            iso151422InvRestParam.setSlName(soReturn.getSellerName());
            iso151422InvRestParam.setSupplierCode(soReturnDetail.getSupplierCode());
            iso151422InvRestParam.setSupplierName(soReturnDetail.getSupplierName());
            iso151422InvRestParam.setPdCode(soReturnDetail.getPdCode());
            iso151422InvRestParam.setPdName(soReturnDetail.getPdName());
            iso151422InvRestParam.setSkuCode(product.getSkuCode());
            iso151422InvRestParam.setInboundQty(product.getInboundQty());
            iso151422InvRestParam.setInboundPrice(result.getPdPrice());
            //入库操作类型
            iso151422InvRestParam.setInvOptType(String.valueOf(NumberConstant.IntDef.INT_THIRTEEN));
            iso151422InvRestParam.setInventoryStatus(String.valueOf(NumberConstant.IntDef.INT_THIRTY_THREE));
            iso151422InvRestParam.setUom(soReturnDetail.getUnit());
            iso151422InvRestParam.setInboundId(param.getReturnId());
            iso151422InvRestParam.setInboundNo(soReturn.getReturnCode());
            iso151422InvRestParam.setInboundDetailId(soReturnDetail.getDetailId());
            iso151422InvRestParam.setRecvDate(soReturn.getInboundTime());
            iso151422InvRestParam.setRecvTime(soReturn.getInboundTime());
            iso151422InvRestParamList.add(iso151422InvRestParam);
        }

        ISO151422RestResult iso151422RestResult = new ISO151422RestResult();
        ISO151422InvRestParamList invRestParamList = new ISO151422InvRestParamList();
        invRestParamList.setUpdId(param.getUpdId());
        invRestParamList.setInvList(iso151422InvRestParamList);
        //异步调用库存入库接口
        this.asyncPostService.returnInboundBy151422Return(invRestParamList);
        iso151422RestResult.setReturnId(param.getReturnId());
        iso151422RestResult.setReturnStatus(returnStatus);
        iso151422RestResult.setCrtTime(crtTimeStr);
        iso151422RestResult.setReturnCode(soReturn.getReturnCode());
        iso151422RestResult.setVer(soReturn.getVer());
        return iso151422RestResult;
    }


    @Transactional
    public SoOrder findSalesPlatForm(SoReturn soReturn){
        Long orderId = soReturn.getOrderId();
        Filter<SoReturn> filter = new Filter<SoReturn>();
        filter.add("orderId",BaseOperatorEnum.EQUAL,orderId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoOrder> condition = new CommonSpecification<SoOrder>(
                filter);
        SoOrder soOrder = soOrderRepository.findOne(condition);
        return soOrder;
    }


    /**
     * 取XML中sql
     *
     * @return Map
     */
    @Transactional
    public String getSql(){
        String sqlId = "ISO151422.getPdPriceAndBatch";
        //调用共通方法取Sql
        String sql = SqlUtil.getSqlBySqlId(sqlId);
        return sql;
    }

    /**
     * 查询数据库
     *
     * @param sql sql语句
     */
    public List<Map<String, Object>> getResult(String sql,List<String> parameterList) {
        List<Map<String, Object>> mapList = baseJdbc.queryForListNotCount(sql,parameterList,null,true);
        List rows = mapList;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Object obj : rows) {
            Map<String, Object> row = (Map<String, Object>) obj;
            resultList.add(row);
        }
        return resultList;
    }

    /**
     * 查询产品单价，采购批次号，入库批次号
     *
     */
    public ISO151422InvRestResult dealResult(String sql, ISO151422ProductRestParam productParam){
        List<String> parameters = new ArrayList<String>();
        parameters.add(productParam.getDetailId().toString());
        parameters.add(productParam.getSkuCode());
        String conditon = " srd.DETAIL_ID = ?" + NumberConstant.IntDef.INT_ZERO + " AND sosd.SKU_CODE = ?" + NumberConstant.IntDef.INT_ONE ;
        sql += conditon;
        ISO151422InvRestResult newResult = new ISO151422InvRestResult();
        List<Map<String, Object>> result = getResult(sql,parameters);
        for (Map<String, Object> map : result) {
            ISO151422InvRestResult bean = new ISO151422InvRestResult();
            try {
                BeanUtils.populate(bean, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            newResult.setPdPrice(bean.getPdPrice());
            newResult.setPurchaseBatch(bean.getPurchaseBatch());
            newResult.setInnerBatch(bean.getInnerBatch());
        }
        return newResult;
    }


    /**
     * 更新退货表
     *
     * @param param 接口入参
     * @param soReturn 退货表数据
     */
    @Transactional
    public SoReturn modifySoReturn(ISO151422RestParam param, SoReturn soReturn){

        soReturn.setInboundManId(param.getInboundManId());
        soReturn.setInboundManName(param.getInboundManName());
        soReturn.setUpdId(param.getUpdId());
        soReturn.setUpdTime(DateTimeUtil.getCustomerDate());
        soReturn.setReturnStatus(param.getReturnStatus());
        soReturn.setVer(soReturn.getVer() + NumberConstant.IntDef.INT_ONE);
        soReturn.setInboundTime(DateTimeUtil.parseDate(param.getInboundTime(),"yyyy-MM-dd HH:mm:ss"));
        SoReturn newSoReturn = soReturnRepository.save(soReturn);
        return newSoReturn;
    }

    /**
     * 插入退货单状态表
     *
     * @param param 接口入参
     * @param soReturn 退货表数据
     */
    @Transactional
    public SoReturnStatus saveSoReturnStatus(ISO151422RestParam param, SoReturn soReturn){
        Long returnStatusId = super.maxId("so_order_status");
        SoReturnStatus soReturnStatus = new SoReturnStatus();
        soReturnStatus.setStatusId(returnStatusId);
        soReturnStatus.setReturnId(soReturn.getReturnId());
        soReturnStatus.setReturnCode(soReturn.getReturnCode());
        soReturnStatus.setReturnStatus(param.getReturnStatus());
        soReturnStatus.setCrtId(param.getUpdId());
        soReturnStatus.setActId(param.getUpdId());
        soReturnStatus.setVer(NumberConstant.IntDef.INT_ONE);
        soReturnStatus.setUpdId(param.getUpdId());
        soReturnStatus.setCrtTime(DateTimeUtil.getCustomerDate());
        soReturnStatus.setActTime(DateTimeUtil.getCustomerDate());
        soReturnStatus.setUpdTime(DateTimeUtil.getCustomerDate());
        soReturnStatus.setDelFlg(SystemConstant.DelFlg.ENABLE);
        returnOrderStatusService.saveReturnStatusByReturnOrder(soReturn,param.getUpdId());
        return soReturnStatus;
    }

    /**
     * 查询并更新退货明细表
     *
     * @param detailId 退货明细ID
     * @param inboundQty 退货数量
     */
    @Transactional
    public SoReturnDetail findAndModifySoReturnDetail(Long detailId,BigDecimal inboundQty,ISO151422RestParam param){
        Filter<SoReturnDetail> filter = new Filter<SoReturnDetail>();
        filter.add("detailId",BaseOperatorEnum.EQUAL,detailId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoReturnDetail> condition = new CommonSpecification<SoReturnDetail>(
                filter);
        SoReturnDetail soReturnDetail = soReturnDetailRepository.findOne(condition);
        soReturnDetail.setInboundQty(inboundQty.setScale(NumberConstant.IntDef.INT_TWO));
        soReturnDetail.setUpdTime(DateTimeUtil.getCustomerDate());
        soReturnDetail.setUpdId(param.getUpdId());
        soReturnDetail.setVer(soReturnDetail.getVer() + NumberConstant.IntDef.INT_ONE);
        soReturnDetail = soReturnDetailRepository.save(soReturnDetail);
        return soReturnDetail;
    }

    /**
     * 检查退货单状态
     *
     * @param param
     */
    @Transactional
    public SoReturn checkSoReturnStatus(ISO151422RestParam param) {

        Long returnId = param.getReturnId();
        Filter<SoReturn> filter = new Filter<SoReturn>();
        filter.add("returnId",BaseOperatorEnum.EQUAL,returnId);
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoReturn> condition = new CommonSpecification<SoReturn>(
                filter);
        SoReturn soReturn = soReturnRepository.findOne(condition);

        if (null == soReturn) {
            throw new BusinessException("退货单id " + param.getReturnId() + " 错误！");
        }

        // 状态列表  /** PROCESS 处理中*/  如果不是处理中就不能
        if (soReturn.getReturnStatus() != OrderCodeMasterDef.ReturnOrderStatus.PROCESS) {
            throw new BusinessException("抱歉！因为退货单状态不是处理中，所以不能进行入库操作");
        }

        return soReturn;
    }

    /**
     * 检查退货单明细是否可退货
     *
     * @param product
     */
    @Transactional
    public void checkSoReturnDetail(ISO151422ProductRestParam product) {
        Filter<SoReturn> filter = new Filter<SoReturn>();
        filter.add("returnId",BaseOperatorEnum.EQUAL,product.getReturnId());
        filter.add("detailId",BaseOperatorEnum.EQUAL,product.getDetailId());
        filter.add("skuCode",BaseOperatorEnum.EQUAL,product.getSkuCode());
        filter.add("inboundBatch",BaseOperatorEnum.EQUAL,product.getInboundBatch());
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        CommonSpecification<SoReturnDetail> condition = new CommonSpecification<SoReturnDetail>(
                filter);
        SoReturnDetail soReturnDetail = soReturnDetailRepository.findOne(condition);
        if (null != soReturnDetail) {
            BigDecimal lessVal = DecimalUtil.subtract(soReturnDetail.getReturnQty(), product.getInboundQty());
            if (lessVal.compareTo(BigDecimal.ZERO) < NumberConstant.IntDef.INT_ZERO) {
                throw new BusinessException("入库数量大于退货单明细申请数量,退货单明细id:" + product.getDetailId() +
                        " 退货量：" + soReturnDetail.getReturnQty() +
                        " 入库数量：" + product.getInboundQty() + " 入库批次：" + soReturnDetail.getInboundBatch());
            }
        } else {
            throw new BusinessException("查询对应不到的product");
        }
    }
}