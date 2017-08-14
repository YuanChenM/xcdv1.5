package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.DecimalUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/6/30
 */
@Service
public class SSC1130401Logic extends BaseLogic {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(SSC1130401Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;


    @Transactional(readOnly = false)
    public Long createContracts(SscContractBasic sscContractBasic) {
        String userId = sscContractBasic.getCrtId();
        Date now = DateTimeUtil.getCustomerDate();

        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("bidId", sscContractBasic.getBidId());
        SscContractBasic contractBasic = (SscContractBasic) super.findObject(SqlId.SQL_ID_FIND_BID_BASCI, baseParam);
        List<SscContractPrDetail> contractPrDetails = super.findList(SqlId.SQL_ID_FIND_BID_DETAIL, baseParam);
        Long contractId = null;
        if (contractBasic != null) {
            contractBasic.setContractCode(sscContractBasic.getContractCode());
            contractId = commonLogic.maxId("ssc_contract_basic", "CONTRACT_ID");
            contractBasic.setContractId(contractId);
            contractBasic.setContractStatus(sscContractBasic.getContractStatus());// 新建

            contractBasic.setBidRelationType(sscContractBasic.getBidRelationType());

            BigDecimal totalPayment = BigDecimal.ZERO;
            for (SscContractPrDetail contractPrDetail : contractPrDetails) {
                totalPayment = DecimalUtil.add(totalPayment, contractPrDetail.getProductValue());
            }
            contractBasic.setContractAmount(totalPayment);

            contractBasic.setCrtId(userId);
            contractBasic.setCrtTime(now);
            super.save(SqlId.SQL_ID_SAVE_CONTRACT_BASIC, contractBasic);
        }
        if (!CollectionUtils.isEmpty(contractPrDetails)) {
            for (SscContractPrDetail prDetail : contractPrDetails) {
                Long detailID = commonLogic.maxId("ssc_contract_pr_detail", "DETAIL_ID");
                prDetail.setDetailId(detailID);
                prDetail.setContractId(contractId);
                prDetail.setCrtId(userId);
                prDetail.setCrtTime(now);
                super.save(SqlId.SQL_ID_SAVE_CONTRACT_ORDER, prDetail);
            }
        }
        return contractId;
    }


    /**
     * @param sscBidBasicInfo
     * @return
     */
    @Transactional(readOnly = true)
    public Long checkBid(SscBidBasicInfo sscBidBasicInfo) {
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("bid", sscBidBasicInfo.getBidId());
        Long count = (Long) super.findObject(SqlId.SQL_ID_FIND_CONTRACT_BASCI, baseParam);
        return count;
    }

    interface SqlId {
        String SQL_ID_FIND_BID_BASCI = "findBidBasci";
        String SQL_ID_FIND_BID_DETAIL = "findBidDetail";
        String SQL_ID_SAVE_CONTRACT_BASIC = "saveContractBasic";
        String SQL_ID_SAVE_CONTRACT_ORDER = "saveContractOrder";
        String SQL_ID_FIND_CONTRACT_BASCI = "findContractBasciByBid";
    }
}
