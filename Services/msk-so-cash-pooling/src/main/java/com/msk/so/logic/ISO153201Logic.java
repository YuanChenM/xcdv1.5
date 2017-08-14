package com.msk.so.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.so.bean.CpResult;
import com.msk.so.bean.ISO153201Param;
import com.msk.so.bean.SO153101Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang_yang on 2016/4/29.
 */
@Service
public class ISO153201Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISO153201Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        static final String SQL_ID_COUNT = "getPageCount";
    }

    /**
     * 查询买家结算列表
     * @return
     */
    @Transactional(readOnly = true)
    public CpResult findSO153201List(RsRequest<ISO153201Param> request) {

        logger.info("查询买家结算列表");
        ISO153201Param rsParam = request.getParam();

        CpResult cpResult = new CpResult();

        if(rsParam.getPageCount() != 0 && rsParam.getPageNo() != 0 ){
            rsParam.setPaging(true);
        }

        String supplyPlatform = StringUtil.toSafeString(rsParam.getSupplyPlatform());
        if (!StringUtil.isNullOrEmpty(supplyPlatform)) {
            String[] supplyPlatforms = supplyPlatform.split(",");
            rsParam.setSupplyPlatforms(supplyPlatforms);
        }

        String transFlg = StringUtil.toSafeString(rsParam.getTransFlg());
        if (!StringUtil.isNullOrEmpty(transFlg)) {
            String[] transFlgs = transFlg.split(",");
            rsParam.setTransFlgs(transFlgs);
        }

        String paymentType = StringUtil.toSafeString(rsParam.getPaymentType());
        if (!StringUtil.isNullOrEmpty(paymentType)) {
            String[] paymentTypes = paymentType.split(",");
            rsParam.setPaymentTypes(paymentTypes);
        }

        String transType = StringUtil.toSafeString(rsParam.getTransType());
        if (!StringUtil.isNullOrEmpty(transType)) {
            String[] transTypes = transType.split(",");
            rsParam.setTransTypes(transTypes);
        }

        String settlementStatus = StringUtil.toSafeString(rsParam.getSettlementStatus());
        if (!StringUtil.isNullOrEmpty(settlementStatus)) {
            String[] settlementStatuss = settlementStatus.split(",");
            rsParam.setSettlementStatuss(settlementStatuss);
        }
        
        cpResult.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, rsParam));
        cpResult.setPageNo(rsParam.getPageNo());

        List<SO153101Bean> results = new ArrayList<SO153101Bean>();

        if (cpResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(rsParam);
            cpResult.setTotalPage(cpResult.getTotalCount(), rsParam.getPageCount());
        }
        cpResult.setSellerBills(results);


        return cpResult;
    }


}
