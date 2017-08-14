package com.msk.so.logic;

import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CapitalPoolConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SoCpAccountBook;
import com.msk.core.entity.SoCpRunning;
import com.msk.so.bean.SOCp153141Bean;
import com.msk.so.bean.SOCp153142Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SO153142Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153142Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SO153141Logic so153141Logic;

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    private TransactionLogic transLogic;

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        String SQL_ID_UPDATE_SOCPACCOUNTBOOK = "updateSoCpAccountBook";
    }

    /**
     * 处理减免金额验证
     *
     * @param so153142Param
     */
    @Transactional
    public void updateAmount(SOCp153142Param so153142Param) throws Exception {
        // check 支付方式
        Integer paidType = so153142Param.getPaidType();
        if (StringUtil.isEmpty(StringUtil.toSafeString(paidType))) {
            throw new BusinessException("支付方式不能为空");
        }
        // check 日期
        Date operateDate = null;
        String operateTime = so153142Param.getOperateTime();
        if (!StringUtil.isNullOrEmpty(operateTime)) {
            operateDate = DateTimeUtil.parseDate(operateTime, DateTimeUtil.FORMAT_DATE_YYYYMMDD);
            if (null == operateDate) {
                throw new BusinessException("发生日期格式不正确");
            }
        } else {
            throw new BusinessException("发生日期不能为空");
        }
        // check 期初金额
        String periodAmount = so153142Param.getPeriodAmount();
        if (!StringUtil.isEmpty(periodAmount)) {
            BigDecimal periodAmountB = StringUtil.toBigDecimal(periodAmount);
            if (null != periodAmountB && periodAmountB.compareTo(BigDecimal.ZERO) != 0) {
                dealReliefAmount(periodAmountB, operateDate, so153142Param);
            } else {
                throw new BusinessException("期初金额格式不正确");
            }
        } else {
            throw new BusinessException("期初金额不能为空");
        }
    }

    /**
     * 处理减免金额
     *
     * @param periodAmountB
     * @param so153142Param
     */
    @Transactional
    private void dealReliefAmount(BigDecimal periodAmountB, Date operateDate, SOCp153142Param so153142Param)
        throws Exception {
        // 卖家交易详细信息
        SOCp153141Bean soCp153141Bean = this.so153141Logic.findSoCpAccountBook(so153142Param.getAccountBookId());
        if (null != soCp153141Bean && soCp153141Bean.getVer().equals(so153142Param.getVer())) {
            // 变更 so_cp_account_book
            SoCpAccountBook soCpSellerBill = new SoCpAccountBook();
            soCpSellerBill.setAccountBookId(StringUtil.toLong(so153142Param.getAccountBookId()));
            // 减免金额
            if (null != soCp153141Bean.getBalance()) {
                soCpSellerBill.setBalance(soCp153141Bean.getBalance().add(periodAmountB));
            } else {
                soCpSellerBill.setBalance(periodAmountB);
            }
            soCpSellerBill.setUpdId(so153142Param.getEmplId());
            so153141Logic.modify(SqlId.SQL_ID_UPDATE_SOCPACCOUNTBOOK, soCpSellerBill);

            // 新增 so_cp_running
            SoCpRunning soCpRunning = new SoCpRunning();
            Long runningId = commonLogic.maxId("SO_CP_RUNNING", "RUNNING_ID");
            Date now = DateTimeUtil.getCustomerDate();
            soCpRunning.setRunningId(runningId);
            soCpRunning.setAmountType(CapitalPoolConst.AmountType.INITIAL);
            soCpRunning.setPaidType(so153142Param.getPaidType());
            soCpRunning.setBillId(StringUtil.toLong(so153142Param.getAccountBookId()));
            soCpRunning.setPaidAmount(periodAmountB);
            soCpRunning.setPaidTime(operateDate);
            soCpRunning.setPaidSeq(so153142Param.getPaidSeq());
            soCpRunning.setRemark(so153142Param.getRemark());
            soCpRunning.setCrtId(so153142Param.getEmplId());
            soCpRunning.setCrtTime(now);
            soCpRunning.setUpdId(so153142Param.getEmplId());
            soCpRunning.setUpdTime(now);
            soCpRunning.setActId(so153142Param.getEmplId());
            soCpRunning.setActTime(now);
            transLogic.saveRunning(soCpRunning);
        } else {
            throw new BusinessException("数据已被修改，请重新刷新页面");
        }
    }
}
