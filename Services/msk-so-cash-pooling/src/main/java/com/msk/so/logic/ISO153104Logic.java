package com.msk.so.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.core.utils.ValidatorUtils;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.so.bean.CpResult;
import com.msk.so.bean.ISO153104Param;
import com.msk.so.bean.SOCp153111Bean;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang_yang on 2016/4/29.
 */
@Service
public class ISO153104Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(ISO153104Logic.class);

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
        static final String SQL_ID_GET_DELIVER1Y_CODE = "getDeliveryCode";
        static final String SQL_ID_GET_REFUND_CODE = "getRefundCode";
    }


    /**
     * 必须项check
     *
     * @param rsParam 退款参数
     */
    private void RequiredCheck(ISO153104Param rsParam) {
        // 创建必须项校验的对象
        HashMap<String, Object> checkObj = new HashMap<String, Object>();
        Integer queryFlag = rsParam.getQueryFlag();
        if (StringUtil.isEmpty(StringUtil.toSafeString(queryFlag))) {
            checkObj.put("查询类型输入项", null);
        }
        // 必须项校验
        ValidatorUtils.validatorRequired(checkObj);
    }


    /**
     * 查询卖家结算列表
     *
     * @return
     */
    @Transactional(readOnly = true)
    public CpResult findSO153202List(RsRequest<ISO153104Param> request) {
        logger.info("查询卖家结算列表");
        ISO153104Param rsParam = request.getParam();
        RequiredCheck(rsParam);
        CpResult cpResult = new CpResult();
        if (rsParam.getQueryFlag().equals(CapitalPoolConst.queryTypeFlag.QUERY_PAGE)) {
            cpResult = getSellerBills(rsParam);
        } else if (rsParam.getQueryFlag().equals(CapitalPoolConst.queryTypeFlag.QUERY_DETAIL)) {
            cpResult = getDeliveryRefundCode(rsParam);
        }
        return cpResult;
    }


    /**
     * 查询分页
     *
     * @param rsParam
     * @return
     */
    @Transactional(readOnly = true)
    private CpResult getSellerBills(ISO153104Param rsParam) {
        CpResult cpResult = new CpResult();
        // 是否设置分页
        if (rsParam.getPageCount() != 0 && rsParam.getPageNo() != 0) {
            rsParam.setPaging(true);
        }
        // 结算标志(以逗号分割)
        String settlementFlg = StringUtil.toSafeString(rsParam.getSettlementFlg());
        if (!StringUtil.isNullOrEmpty(settlementFlg)) {
            String[] settlementFlgs = settlementFlg.split(",");
            rsParam.setSettlementFlgs(settlementFlgs);
        }
        // 设置模糊检索
        if (!StringUtil.isEmpty(rsParam.getRemark())) {
            rsParam.setRemark("%" + rsParam.getRemark() + "%");
        }
        cpResult.setTotalCount(super.getCount(SqlId.SQL_ID_COUNT, rsParam));
        cpResult.setPageNo(rsParam.getPageNo());

        List<SOCp153111Bean> results = new ArrayList<SOCp153111Bean>();
        if (cpResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            results = super.findPageList(rsParam);
            cpResult.setTotalPage(cpResult.getTotalCount(), rsParam.getPageCount());
        }
        cpResult.setSellerBills(results);
        return cpResult;
    }


    /**
     * 查询配送单列表 +   退货单列表
     *
     * @param rsParam
     * @return
     */
    @Transactional(readOnly = true)
    private CpResult getDeliveryRefundCode(ISO153104Param rsParam) {
        CpResult cpResult = new CpResult();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilterObject("sellerBillId", rsParam.getSellerBillId());
        if (!StringUtil.isEmpty(StringUtil.toSafeString(rsParam.getSellerBillId()))) {
            // 查询配送单列表
            List<SOCp153111Bean> deliveryCodeList = super.findList(SqlId.SQL_ID_GET_DELIVER1Y_CODE, baseParam);
            if (CollectionUtils.isNotEmpty(deliveryCodeList)) {
                cpResult.setDeliveryList(deliveryCodeList);
            }
            // 退货单列表
            List<SOCp153111Bean> refundCodeList = super.findList(SqlId.SQL_ID_GET_REFUND_CODE, baseParam);
            if (CollectionUtils.isNotEmpty(refundCodeList)) {
                cpResult.setRefundList(refundCodeList);
            }
        }
        return cpResult;
    }


}
