package com.msk.batch.cp.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.cp.bean.BCP153101Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * BCP153101Logic
 *
 * @author Qiu_wenting
 * @version 1.0
 */
@Service
public class BCP153101Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BCP153101Logic.class);
    /**
     * CommonLogic
     */
    @Autowired
    private CommonLogic commonLogic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    public interface SqlId {
        static final String UPDATE_PAYMENT_PERIOD = "updatePaymentPeriod";
        static final String UPDATE_SEL_CHARGING = "updateSelCharging";
        static final String UPDATE_REFUND = "updateRefund";
        static final String INSERT_SELLER_BILL = "insertSellerBill";
        static final String SELECT_SELLER_CHARGING = "selectSellerCharging";
        static final String SELECT_REFUND_ID = "selectRefundId";
        static final String SELECT_CHARGING_ID = "selectChargingId";
        static final String SELECT_SO_CP_SELLER_BILL = "selectSoCpSellerBill";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 数据处理
     *
     * @return
     */
    @Transactional
    public void dataResolve(BaseParam param) {

        // 生成卖家计费单
        List<BCP153101Bean> sellerBills = super.findList(SqlId.SELECT_SELLER_CHARGING, param);

        List<BCP153101Bean> slBillPeriod = new ArrayList<BCP153101Bean>();
        if (CollectionUtils.isNotEmpty(sellerBills)) {
            slBillPeriod.addAll(sellerBills);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateCode = sdf.format(new Date());
        String idFormat = "000000";
        //取出 SELLER_BILL_NO 最大值
        BCP153101Bean beanParam = new BCP153101Bean();
        String sellerBillNoP = "SNO" + dateCode;
        beanParam.setSellerBillNo(sellerBillNoP + "%");
        BCP153101Bean beanResult = this.findOne(SqlId.SELECT_SO_CP_SELLER_BILL, beanParam);
        int num = NumberConst.IntDef.INT_ZERO;
        if (null != beanResult) {
            String sellerBillNoR = beanResult.getSellerBillNo();
            sellerBillNoR = sellerBillNoR.replace(sellerBillNoP, "");
            num = StringUtil.toInteger(sellerBillNoR) + NumberConst.IntDef.INT_ONE ;
        } else {
            num = NumberConst.IntDef.INT_ONE;
        }

        HashMap<String, BCP153101Bean> baseMap = new HashMap<>();
        if (sellerBills != null && sellerBills.size() > 0) {
            // Add for Bug#横展开06 at 2016/09/06 by li_huiqian Start
            Date now = DateTimeUtil.getCustomerDate();
            // Add for Bug#横展开06 at 2016/09/06 by li_huiqian End
            for (int i = 0; i < sellerBills.size(); i++) {

                // Add for Bug#横展开06 at 2016/09/06 by li_huiqian Start
                sellerBills.get(i).setCrtTime(now);
                sellerBills.get(i).setUpdTime(now);
                sellerBills.get(i).setActTime(now);
                // Add for Bug#横展开06 at 2016/09/06 by li_huiqian End

                if ((sellerBills.get(i).getReceiveable() == null
                        || sellerBills.get(i).getReceiveable().compareTo(BigDecimal.ZERO) == 0)
                        && (sellerBills.get(i).getRefundable() == null
                        || sellerBills.get(i).getRefundable().compareTo(BigDecimal.ZERO) == 0)) {

                    sellerBills.remove(sellerBills.get(i));
                    i--;
                } else {
                    // 获取第一个主键
                    Long sellerBillId = commonLogic.maxId("so_cp_seller_bill", "SELLER_BILL_ID");
                    sellerBills.get(i).setSellerBillId(sellerBillId);
                    String sellerBillNo = "SNO" + dateCode
                            + idFormat.substring(0, idFormat.length() - String.valueOf(num).length()) + num;
                    sellerBills.get(i).setSellerBillNo(sellerBillNo);
                    num++;
                    baseMap.put(sellerBills.get(i).getBusinessMainId(), sellerBills.get(i));
                }
            }
        }

        if (sellerBills != null && sellerBills.size() > 0) {
            // 批量插入买家计费单
            this.getBaseDao().batchInsert(SqlId.INSERT_SELLER_BILL, sellerBills);

            // 检索关联的计费项
            List<BCP153101Bean> selChargings = super.findList(SqlId.SELECT_CHARGING_ID, param);
            for (BCP153101Bean selCharging : selChargings) {
                if (baseMap.get(selCharging.getBusinessMainId()) != null) {
                    selCharging.setSellerBillId(baseMap.get(selCharging.getBusinessMainId()).getSellerBillId());
                }
                // Add for Bug#横展开06 at 2016/09/06 by li_huiqian Start
                selCharging.setUpdTime(DateTimeUtil.getCustomerDate());
                // Add for Bug#横展开06 at 2016/09/06 by li_huiqian End
            }

            if (selChargings != null && selChargings.size() > 0) {
                // 批量更新计费项
                this.getBaseDao().getSqlSession().update(SqlId.UPDATE_SEL_CHARGING, selChargings);
            }

            // 检索关联的退款单
            List<BCP153101Bean> selRefunds = super.findList(SqlId.SELECT_REFUND_ID, param);
            for (BCP153101Bean selRefund : selRefunds) {
                selRefund.setSellerBillId(baseMap.get(selRefund.getBusinessMainId()).getSellerBillId());
                // Add for Bug#横展开06 at 2016/09/06 by li_huiqian Start
                selRefund.setUpdTime(DateTimeUtil.getCustomerDate());
                // Add for Bug#横展开06 at 2016/09/06 by li_huiqian End
            }

            if (selRefunds != null && selRefunds.size() > 0) {
                // 批量更新退款单
                this.getBaseDao().getSqlSession().update(SqlId.UPDATE_REFUND, selRefunds);
            }

        }

        // 批量更新买家账期表 最新账期结束日
        if (CollectionUtils.isNotEmpty(slBillPeriod)) {
            this.getBaseDao().getSqlSession().update(SqlId.UPDATE_PAYMENT_PERIOD, slBillPeriod);
        }

    }
}
