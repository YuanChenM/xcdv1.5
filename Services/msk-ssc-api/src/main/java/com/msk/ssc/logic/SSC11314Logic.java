package com.msk.ssc.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.SSC11314DeliveryConfirmParam;
import com.msk.ssc.bean.SSC11314DeliveryConfirmPdParam;
import com.msk.ssc.bean.SSC11314RsParam;
import com.msk.ssc.bean.SSC11314RsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/7/5.
 */
@Service
public class SSC11314Logic extends BaseLogic {
    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(SSC11314Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        String SQL_ID_FIND_CHOOSE_CONTRACT = "findChooseContract";//查询弹出框合同
        String SQL_ID_FIND_CHOOSE_DELIVERY = "findChooseDelivery";//查询弹出框发货单
        String SQL_ID_CHECK_DELIVERY_CONFIRM = "checkDeliveryConfirm";//验证发货确认表是否已经存在数据

        String SQL_ID_FIND_DELIVERY_BASIC_INFO = "findDeliveryBasicInfo";//查询发货单表
        String SQL_ID_FIND_DELIVERY_PD_INFO = "findDeliveryPdInfo";//查询发货单表
        String SQL_ID_INSERT_DELIVERY_CONFIRM_BASIC = "insertDeliveryConfirmBasic";//插入发货确认单
        String SQL_ID_DELETE_CONFIRM_BASIC = "deleteConfirmBasic";//删除发货确认单
    }

    /**
     * 查询弹出框合同
     * @param ssc11314RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11314RsResult> findChooseContract(SSC11314RsParam ssc11314RsParam){
        List<SSC11314RsResult> resultContractList = super.findList(SqlId.SQL_ID_FIND_CHOOSE_CONTRACT,ssc11314RsParam);
        return resultContractList;
    }

    /**
     * 查询弹出框发货单
     * @param ssc11314RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11314RsResult> findChooseDelivery(SSC11314RsParam ssc11314RsParam){
        List<SSC11314RsResult> resultDeliveryList = super.findList(SqlId.SQL_ID_FIND_CHOOSE_DELIVERY,ssc11314RsParam);
        return resultDeliveryList;
    }
    /**
     * 验证发货确认单是否已经存在
     * @param ssc11314RsParam
     * @return
     */
    @Transactional(readOnly = true)
    public String checkDeliveryConfirm(SSC11314RsParam ssc11314RsParam){
        String checkFlag = SystemConst.RsStatus.FAIL;
        List<SSC11314RsResult> resultDeliveryList = super.findList(SqlId.SQL_ID_CHECK_DELIVERY_CONFIRM,ssc11314RsParam);
        if(resultDeliveryList.size() != NumberConst.IntDef.INT_ZERO){
            checkFlag = SystemConst.RsStatus.SUCCESS;
        }
        return checkFlag;
    }

    /**
     * 发货单数据迁入发货确认单
     * @param ssc11314RsParam
     * @return
     */
    @Transactional
    public String insertDeliveryConfirm(SSC11314RsParam ssc11314RsParam){
        String deliveryConfirmId = null;
        SSC11314DeliveryConfirmParam resultDeliveryBasic = super.findOne(SqlId.SQL_ID_FIND_DELIVERY_BASIC_INFO, ssc11314RsParam);
        List<SSC11314DeliveryConfirmPdParam> resultDeliveryPdList = super.findList(SqlId.SQL_ID_FIND_DELIVERY_PD_INFO,ssc11314RsParam);
        List<SSC11314DeliveryConfirmPdParam> confirmPdParamList = new ArrayList<SSC11314DeliveryConfirmPdParam>();
        if(resultDeliveryBasic != null && resultDeliveryPdList.size() != NumberConst.IntDef.INT_ZERO){
            Long maxId = commonLogic.maxId("ssc_delivery_confirm_basic", "DELIVERY_CONFIRM_ID");
            deliveryConfirmId = String.valueOf(maxId);
            resultDeliveryBasic.setDeliveryConfirmId(maxId);
            resultDeliveryBasic.setDeliveryConfirmCode("9" + maxId.toString());
            for(SSC11314DeliveryConfirmPdParam ssc11314DeliveryConfirmPdParam : resultDeliveryPdList){
                ssc11314DeliveryConfirmPdParam.setDetailId(commonLogic.maxId("ssc_delivery_confirm_pr_detail", "DETAIL_ID"));
                ssc11314DeliveryConfirmPdParam.setDeliveryConfirmCode("9" + maxId.toString());
                ssc11314DeliveryConfirmPdParam.setDeliveryConfirmId(maxId.toString());
                ssc11314DeliveryConfirmPdParam.setCrtId(ssc11314RsParam.getCrtId());
                ssc11314DeliveryConfirmPdParam.setCrtTime(DateTimeUtil.getCustomerDate());
                ssc11314DeliveryConfirmPdParam.setUpdId(ssc11314RsParam.getUpdId());
                ssc11314DeliveryConfirmPdParam.setUpdTime(DateTimeUtil.getCustomerDate());
                /*//非到岸价时发货确认单结算标准价为不含运费结算标准价
                if(!"1".equals(resultDeliveryBasic.getFreightSettleMethod())){
                    ssc11314DeliveryConfirmPdParam.setSettkementStandardPrice(ssc11314DeliveryConfirmPdParam.getStandardPrice());
                }*/
                confirmPdParamList.add(ssc11314DeliveryConfirmPdParam);
            }
            resultDeliveryBasic.setCrtId(ssc11314RsParam.getCrtId());
            resultDeliveryBasic.setCrtTime(DateTimeUtil.getCustomerDate());
            resultDeliveryBasic.setUpdId(ssc11314RsParam.getUpdId());
            resultDeliveryBasic.setUpdTime(DateTimeUtil.getCustomerDate());
            super.save(SqlId.SQL_ID_INSERT_DELIVERY_CONFIRM_BASIC,resultDeliveryBasic);
            super.batchSave(confirmPdParamList);
        }
        return deliveryConfirmId;
    }

    /**
     * 删除发货确认单
     * @param
     * @return
     */
    @Transactional
    public String deleteConfirmBasic(SSC11314RsParam ssc11314RsParam){
        if(ssc11314RsParam.getDeliveryConfirmId() != null && ssc11314RsParam.getDeliveryConfirmId() != 0){
            super.versionValidator("SSC_DELIVERY_CONFIRM_BASIC", new String[]{"DELIVERY_CONFIRM_ID"}, new Object[]{ssc11314RsParam.getDeliveryConfirmId()}, ssc11314RsParam.getVer());
        }
        String flag = SystemConst.RsStatus.FAIL;
        int result = super.modify(SqlId.SQL_ID_DELETE_CONFIRM_BASIC,ssc11314RsParam);
        if(result > 0){
            flag = SystemConst.RsStatus.SUCCESS;
        }

        return flag;
    }

}
