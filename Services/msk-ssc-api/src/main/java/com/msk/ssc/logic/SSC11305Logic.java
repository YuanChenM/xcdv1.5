package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.business.constant.SscConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscContractBasic;
import com.msk.core.entity.SscContractPrDetail;
import com.msk.core.entity.SscDeliveryOrderPd;
import com.msk.core.entity.SscDeliveryPlanBasic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 发货订单一览Logic
 *
 */
@Service
public class SSC11305Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SSC11305Logic.class);

    @Autowired
    private CommonLogic commonLogic;



    /**
     * SQL Map 中SQL ID定义
     *
     * @author yang_yang
     */
    private interface SqlId {
        String SQL_SAVE_ORDER_BASIC = "saveSscDeliveryOrderBasic";
        String SQL_ID_FIND_CONTRACT_BASIC = "findSsccContractBasic";//合同基本信息的查看
        String SQL_ID_GET_MAX_DELIVERY_CODE = "getMaxDeliveryCode";//获取deliveryCode
        String SQL_ID_FIND_CONTRACT_PD = "findContractPd";//查询合同产品表数据
        String SQL_ID_CHECK_DELIVERY_ORDER = "checkDeliveryOrder";//查询该批次是否已经生成发货单
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 发货订单数据插入
     *
     * @param param
     */
    @Transactional
    public Long insertDelivery(SSC11305RsParam param) {
        //查询合同基础表数据
        SscContractBasic basic=new SscContractBasic();
        basic.setContractId(param.getContractId());
        SscContractBasic contractBasic=super.findOne(SqlId.SQL_ID_FIND_CONTRACT_BASIC, basic);
        //查询合同产品明细表数据
        SSC11305RsParam ssc11305RsParam = new SSC11305RsParam();
        ssc11305RsParam.setContractId(param.getContractId());
        ssc11305RsParam.setDeliveryBatch(param.getDeliveryBatch());
        List<SSC11305DeliveryPdParam> contractPdList = this.findList(SqlId.SQL_ID_FIND_CONTRACT_PD, ssc11305RsParam);
        //检查预付款比例
        for (SSC11305DeliveryPdParam pd : contractPdList) {
            if (null == pd.getDownPayment()) {
                return 0L;
            }
        }
        //判断基础表和明细表是否都有数据
        Long maxDeliverId=null;
        if(contractBasic!=null && contractPdList.size()!= NumberConst.IntDef.INT_ZERO){
            //获取deliveryCode   FH201607110001
            //插入基础表的参数组装
            String deliveryCode = "FH" + DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.getCustomerDate());
            param.setDeliveryCode(deliveryCode);
            SSC11305RsBean rsBean = super.findOne(SqlId.SQL_ID_GET_MAX_DELIVERY_CODE,param);
            if(rsBean != null && !StringUtil.isNullOrEmpty(rsBean.getDeliveryCode())){
                deliveryCode = "FH" + DecimalUtil.add(DecimalUtil.getBigDecimal(rsBean.getDeliveryCode()), BigDecimal.ONE);
            }else{
                deliveryCode += "0001";
            }
            SSC11305OrderBasicBean bean=new SSC11305OrderBasicBean();
            bean.setDeliveryCode(deliveryCode);
            bean.setContractId(contractBasic.getContractId());
            bean.setContractCode(contractBasic.getContractCode());
            bean.setContractRelationType(String.valueOf(SscConstant.RelationType.NORMAL));
            bean.setEtaStr(param.getEtaStr());
            bean.setDeliveryStatus(param.getDeliveryStatus());
            bean.setSupplierId(contractBasic.getSupplierId());
            bean.setSupplierName(contractBasic.getSupplierName());
            bean.setSupplierCode(contractBasic.getSupplierCode());
            bean.setPurchaserCode(contractBasic.getPurchaserCode());
            bean.setPurchaserId(contractBasic.getPurchaserId());
            bean.setPurchaserName(contractBasic.getPurchaserName());
            bean.setDeliveryBatch(Integer.valueOf(param.getDeliveryBatch()));
            bean.setCrtId(param.getCrtId());
            bean.setCrtTime(DateTimeUtil.getCustomerDate());
            bean.setUpdId(param.getUpdId());
            bean.setUpdTime(DateTimeUtil.getCustomerDate());
             maxDeliverId = commonLogic.maxId("ssc_delivery_order_basic", "DELIVERY_ID");
            bean.setDeliveryId(maxDeliverId);
            //插入明细表的参数组装
            List<SSC11305DeliveryPdParam> deliveryPdParamList = new ArrayList<SSC11305DeliveryPdParam>();
            for (SSC11305DeliveryPdParam ssc11305DeliveryPdParam : contractPdList){
                ssc11305DeliveryPdParam.setDeliveryCode(deliveryCode);
                ssc11305DeliveryPdParam.setDeliveryId(maxDeliverId);
                ssc11305DeliveryPdParam.setCrtId(param.getCrtId());
                ssc11305DeliveryPdParam.setCrtTime(DateTimeUtil.getCustomerDate());
                ssc11305DeliveryPdParam.setUpdId(param.getUpdId());
                ssc11305DeliveryPdParam.setUpdTime(DateTimeUtil.getCustomerDate());
              Long   maxId = commonLogic.maxId("ssc_delivery_order_pd", "DETAIL_ID");
                ssc11305DeliveryPdParam.setDetailId(maxId);
                deliveryPdParamList.add(ssc11305DeliveryPdParam);
            }
            //插入发货订单基础表和发货订单产品明细表
            super.save(SqlId.SQL_SAVE_ORDER_BASIC,bean);
            this.batchSave(deliveryPdParamList);


        }
        return maxDeliverId;
    }



    /**
     *  查询该批次发货订单是否已经生成
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Integer checkDeliveryOrder(SSC11305RsParam param){
        return (Integer)super.findObject(SqlId.SQL_ID_CHECK_DELIVERY_ORDER, param);
    }

}


