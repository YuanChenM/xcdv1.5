package com.msk.so.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoCpSellerBill;
import com.msk.core.entity.SoCpSellerBillHis;
import com.msk.so.bean.SOCp153111Bean;
import com.msk.so.bean.SOCp153115Param;
import com.msk.so.utils.CheckUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SO153115Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153115Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SO153111Logic so153111Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_SAVE_SOCPSELLERBILLHIS = "saveSoCpSellerBillHis";
        String SQL_ID_UPDATE_SOCPSELLERBILL = "updateSoCpSellerBill";
    }

    /**
     * 处理减免金额验证
     *
     * @param so153115Param
     */
    @Transactional
    public void updateAmount(SOCp153115Param so153115Param) throws Exception {
        // check 费用类型
        Integer costsAdjusted = so153115Param.getRefundType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(costsAdjusted))) {
            throw new BusinessException("费用类型不能为空");
        }
        // check 日期
        Date operateDate = null;
        String operateTime = so153115Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, CheckUtil.FORMAT_YYYYMMDD_HHMMSS);
            if (null == operateDate) {
                throw new BusinessException("发生日期格式不正确");
            }
        } else {
            throw new BusinessException("发生日期不能为空");
        }
        // 处理全部取消金额
        String reliefAmount = so153115Param.getReliefAmount();
        if (!StringUtil.isEmpty(reliefAmount)) {
            BigDecimal reliefAmountB = StringUtil.toBigDecimal(reliefAmount);
            if (null != reliefAmountB && reliefAmountB.compareTo(BigDecimal.ZERO) != 0) {
                dealReliefAmount(reliefAmountB, operateDate, so153115Param);
            } else {
                throw new BusinessException("减免金额格式不正确");
            }
        } else {
            throw new BusinessException("减免金额不能为空");
        }
    }

    /**
     * 处理减免金额
     *
     * @param reliefAmountB
     * @param so153115Param
     */
    @Transactional
    private void dealReliefAmount(BigDecimal reliefAmountB, Date operateDate, SOCp153115Param so153115Param)
        throws Exception {
        // 卖家交易详细信息
        SOCp153111Bean soSellerBill = this.so153111Logic.findSellerById(so153115Param.getSellerBillId());
        if (null != soSellerBill && soSellerBill.getVer().equals(so153115Param.getVer())) {
            // 变更 so_cp_seller_bill
            SoCpSellerBill soCpSellerBill = new SoCpSellerBill();
            soCpSellerBill.setSellerBillId(soSellerBill.getSellerBillId());
            // 减免金额
            if (null != soSellerBill.getAjustAmount()) {
                soCpSellerBill.setAjustAmount(soSellerBill.getAjustAmount().add(reliefAmountB));
            } else {
                soCpSellerBill.setAjustAmount(reliefAmountB);
            }
            // 状态变更
            BigDecimal resultB = soSellerBill.getReceiveable().subtract(soSellerBill.getRefundable())
                .subtract(soSellerBill.getReceived()).add(soSellerBill.getRealRefund())
                .subtract(soCpSellerBill.getAjustAmount());
            Integer result = resultB.compareTo(BigDecimal.ZERO);
            // 状态变更
            if (result > NumberConst.IntDef.INT_ZERO) {
                soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_REFUND);
            } else if (result < NumberConst.IntDef.INT_ZERO) {
                soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.FOR_PAY);
            } else {
                soCpSellerBill.setSettlementStatus(CapitalPoolConst.SettlementStatus.BALANCED);
            }
            soCpSellerBill.setUpdId(so153115Param.getEmplId());
            soCpSellerBill.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_UPDATE_SOCPSELLERBILL, soCpSellerBill);

            // 新增 so_cp_seller_bill_his
            SoCpSellerBillHis soCpSellerBillHis = new SoCpSellerBillHis();
            BeanUtils.copyProperties(soCpSellerBillHis, soSellerBill);
            Long sellerBillHisId = commonLogic.maxId("SO_CP_SELLER_BILL_HIS", "SELLER_BILL_HIS_ID");
            soCpSellerBillHis.setSellerBillHisId(sellerBillHisId);
            soCpSellerBillHis.setAjustDate(operateDate);
            soCpSellerBillHis.setAjustAmount(reliefAmountB);
            soCpSellerBillHis.setAjustType(3);
            soCpSellerBillHis.setOperateId(so153115Param.getEmplId());
            soCpSellerBillHis.setOperateDate(DateTimeUtil.getCustomerDate());
            soCpSellerBillHis.setRemark(so153115Param.getRemark());
            super.save(SqlId.SQL_ID_SAVE_SOCPSELLERBILLHIS, soCpSellerBillHis);
        } else {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
    }
}
