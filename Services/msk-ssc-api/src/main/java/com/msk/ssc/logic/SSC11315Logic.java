package com.msk.ssc.logic;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SscDeliveryPreInto;
import com.msk.core.entity.SscDeliveryPrePd;
import com.msk.ssc.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by sun_jiaju on 2016/7/5
 */
@Service
public class SSC11315Logic extends BaseLogic{

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(SSC11315Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SSC1131502Logic ssc1131502Logic;

    /**
     * 根据发货确认单编号查询发货确认单信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11315DeliveryConfirmRsBean findDeliveryConfirm(SSC11315Param param){
        SSC11315DeliveryConfirmRsBean result = super.findOne(SqlId.SQL_ID_FIND_DELIVERY_CONFRIM, param);
        return result;
    }

    /**
     * 根据发货确认单编号查询发货确认产品信息总计
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11315DeliveryConfirmDetailRsBean findDeliveryConfirmDetailTotal(SSC11315Param param){
        SSC11315DeliveryConfirmDetailRsBean result = super.findOne(SqlId.SQL_ID_FIND_DELIVERY_CONFRIM_DETAIL_TOTAL, param);
        return result;
    }

    /**
     * 更新发货确认信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int modifyDeliveryConfirm(SSC11315Param param){
        if(param.getDeliveryConfirmId() != null && param.getDeliveryConfirmId() != 0){
            super.versionValidator("SSC_DELIVERY_CONFIRM_BASIC", new String[]{"DELIVERY_CONFIRM_ID"}, new Object[]{param.getDeliveryConfirmId()}, param.getVer());
        }
        return super.modify(SqlId.SQL_ID_MODIFY_DELIVERY_CONFRIM, param);
    }

    /**
     * 更新发货确认产品信息
     *
     * @param param
     * @return
     */
    @Transactional
    public int modifyDeliveryConfirmDetail(SSC11315Param param){
        super.versionValidator("SSC_DELIVERY_CONFIRM_PR_DETAIL", new String[]{"DETAIL_ID"}, new Object[]{param.getDetailId()}, param.getVer());
        int count = super.modify(SqlId.SQL_ID_MODIFY_DELIVERY_CONFRIM_DETAIL, param);
        this.saveProductHistory(param);
        return count;
    }

    @Transactional
    private void saveProductHistory(SSC11315Param ssc11315Param) {
        SSC1131502Bean ssc1131502Bean = new SSC1131502Bean();
        ssc1131502Bean.setConfirmDetailId(ssc11315Param.getDetailId());
        ssc1131502Bean.setProductQua(ssc11315Param.getProductQua());
        ssc1131502Bean.setProductConfirmBox(ssc11315Param.getProductConfirmBox());
        ssc1131502Bean.setSettkementStandardPrice(ssc11315Param.getSettkementStandardPrice());
        ssc1131502Bean.setProductValue(ssc11315Param.getProductValue());
        ssc1131502Bean.setCrtId(ssc11315Param.getCrtId());
        ssc1131502Bean.setCrtName(ssc11315Param.getCrtName());
        ssc1131502Logic.saveProductHistory(ssc1131502Bean);
    }

    /**
     * 更新发货确认产品信息
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public SSC11315DeliveryConfirmRsBean findDeliveryPreInto(SSC11315Param param){
        return super.findOne(SqlId.SQL_ID_FIND_DELIVERY_PRE_INTO, param);
    }

    /**
     * 查询或插入发货预入库单、发货预入库产品信息
     *
     * @param param
     * @return
     */
    @Transactional
    public SSC11315DeliveryConfirmRsBean insertDeliveryPreInto(SSC11315Param param){



        SscDeliveryPreInto sscDeliveryPreInto = super.findOne(SqlId.SQL_ID_FIND_DELIVERY_CONFRIM_PRE, param);
        Long maxId = commonLogic.maxId("SSC_DELIVERY_PRE_INTO", "DELIVERY_PRE_INTO_ID");
        if (sscDeliveryPreInto != null) {
            // 插入SSC_DELIVERY_PRE_INTO
            sscDeliveryPreInto.setDeliveryPreIntoId(maxId);
            String codeStr = "99"+maxId.toString();
            Long preCode = Long.parseLong(codeStr);
            sscDeliveryPreInto.setDeliveryPreIntoCode(preCode);
            sscDeliveryPreInto.setDriverName(param.getDriverName());
            sscDeliveryPreInto.setDriverTel(param.getDriverTel());
            sscDeliveryPreInto.setLicPlateNumber(param.getLicPlateNumber());
            sscDeliveryPreInto.setVehicleType(param.getVehicleType());
            sscDeliveryPreInto.setDeliveryDate(DateTimeUtil.getCustomerDate());
            sscDeliveryPreInto.setCrtTime(param.getCrtTime());
            sscDeliveryPreInto.setUpdTime(param.getUpdTime());
            sscDeliveryPreInto.setCrtId(param.getCrtId());
            sscDeliveryPreInto.setUpdId(param.getUpdId());
            sscDeliveryPreInto.setActId(param.getActId());
            sscDeliveryPreInto.setActTime(param.getActTime());
            SSC11315Param param2 = new SSC11315Param();
            param2.setDeliveryCode(sscDeliveryPreInto.getDeliveryCode());
            param2.setDeliveryBatch(sscDeliveryPreInto.getDeliveryBatch().toString());
            Integer vehicleNumber = this.getMaxVehicleNumberByDeliveryCode(param2);
            vehicleNumber = vehicleNumber + 1;
            sscDeliveryPreInto.setVehicleNumber(vehicleNumber);

            super.save(sscDeliveryPreInto);
        } else {
            return null;
        }
        Map<String, Integer> products = new HashMap<>();
        for(int i=0; i<param.getPdCodes().length; i++) {
            products.put(param.getPdCodes()[i], Integer.parseInt(param.getProductPlanBoxes()[i]));
        }
        List<SscDeliveryPrePd> sscDeliveryPrePdList = super.findList(SqlId.SQL_ID_FIND_DELIVERY_CONFRIM_DETAIL_PRE, param);
        if (CollectionUtils.isNotEmpty(sscDeliveryPrePdList)) {
            // 插入SSC_DELIVERY_PRE_PD
            for (SscDeliveryPrePd sscDeliveryPrePd : sscDeliveryPrePdList){
                Long detailMaxId = commonLogic.maxId("SSC_DELIVERY_PRE_PD", "DETAIL_ID");
                sscDeliveryPrePd.setDetailId(detailMaxId);
                sscDeliveryPrePd.setDeliveryPreIntoId(maxId);
                String pdPreCodeStr = "99"+maxId.toString();
                Long pdPreCode = Long.parseLong(pdPreCodeStr);
                sscDeliveryPrePd.setDeliveryPreIntoCode(pdPreCode);
                int productPlanBox = products.get(sscDeliveryPrePd.getPdCode());
                sscDeliveryPrePd.setProductPlanBox(productPlanBox);
                sscDeliveryPrePd.setProductPlanWeight(sscDeliveryPrePd.getWeightVal().multiply(new BigDecimal(productPlanBox)));
                sscDeliveryPrePd.setCrtId(param.getCrtId());
                sscDeliveryPrePd.setCrtTime(param.getCrtTime());
                sscDeliveryPrePd.setUpdId(param.getUpdId());
                sscDeliveryPrePd.setUpdTime(param.getUpdTime());
            }
            super.batchSave(sscDeliveryPrePdList);
        }
        SSC11315DeliveryConfirmRsBean res = new SSC11315DeliveryConfirmRsBean();
        res.setDeliveryPreIntoId(sscDeliveryPreInto.getDeliveryPreIntoId());
        res.setDeliveryConfirmCode(sscDeliveryPreInto.getDeliveryConfirmCode());
        return res;
    }

    /**
     * check确认单产品是否全部装车，从而判断是否可以再生成预入库单
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SSC11315DeliveryConfirmDetailRsBean> checkPdPlanBox(SSC11315Param param){
        List<SSC11315DeliveryConfirmDetailRsBean> rs = new ArrayList<SSC11315DeliveryConfirmDetailRsBean>();
        rs=super.findList(SqlId.SQL_ID_CHECK_PD_PLAN_BOX, param);
        return rs;
    }

    /**
     *查询本次发货批次的最大车次
     * @param param
     * @return
     */
    public Integer getMaxVehicleNumberByDeliveryCode(SSC11315Param param){
        return super.getBaseDao().count(SqlId.SQL_ID_GET_MAX_VEHICLE_NUMBER_BY_CODE, param);
    }

    interface SqlId {
        String SQL_ID_FIND_DELIVERY_CONFRIM = "findDeliveryConfirm";//根据发货确认单编号查询发货确认单信息
        String SQL_ID_FIND_DELIVERY_CONFRIM_DETAIL_TOTAL = "findDeliveryConfirmDetailTotal";//根据发货确认单编号查询发货确认产品信息总计
        String SQL_ID_MODIFY_DELIVERY_CONFRIM = "modifyDeliveryConfirm";//更新发货确认信息
        String SQL_ID_MODIFY_DELIVERY_CONFRIM_DETAIL = "modifyDeliveryConfirmDetail";//更新发货确认产品信息
        String SQL_ID_FIND_DELIVERY_PRE_INTO = "findDeliveryPreInto";//查询发货预入库单
        String SQL_ID_FIND_DELIVERY_CONFRIM_PRE = "findDeliveryConfirmPre";//查询发货确认单信息(插入发货预入库单)
        String SQL_ID_FIND_DELIVERY_CONFRIM_DETAIL_PRE = "findDeliveryConfirmDetailPre";//查询发货确认产品信息（插入发货预入库产品信息）
        String SQL_ID_CHECK_PD_PLAN_BOX ="checkPdPlanBox"; //check产品是否全部装车
        String SQL_ID_GET_MAX_VEHICLE_NUMBER_BY_CODE ="getMaxVehicleNumberByDeliveryCode"; //check产品是否全部装车
    }
}
