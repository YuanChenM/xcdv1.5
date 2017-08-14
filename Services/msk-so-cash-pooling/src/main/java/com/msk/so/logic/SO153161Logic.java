package com.msk.so.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseLogic;
import com.msk.so.bean.SOCp153161Bean;
import com.msk.so.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yang_yang 
 */
@Service
public class SO153161Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SO153161Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId sql定义
     */
    interface SqlId {
        String SQL_ID_GET_TOTAL_INFO = "getTotalInfo";
    }

    /**
     * 退款一览
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SOCp153161Bean> findSO153161List(BasePageParam pageParam) {

        logger.info("退款一览");

        // 验证日期
        boolean orderFlag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("orderTimeStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("orderTimeEnd")));
        boolean refundFlag = CheckUtil.checkDate(StringUtil.toSafeString(pageParam.getFilterMap().get("refundTimeStart"))
                , StringUtil.toSafeString(pageParam.getFilterMap().get("refundTimeEnd")));
        if(!orderFlag || !refundFlag){
            throw new BusinessException("日期选择不合理");
        }


        DbUtils.buildLikeCondition(pageParam, "refundCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "transCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(pageParam, "remark", DbUtils.LikeMode.PARTIAL);

        String transType = StringUtil.toSafeString(pageParam.getFilterMap().get("transType"));
        if (!StringUtil.isNullOrEmpty(transType)) {
            String[] transTypes = transType.split(",");
            pageParam.getFilterMap().put("transTypes", transTypes);
        }
        String refundType = StringUtil.toSafeString(pageParam.getFilterMap().get("refundType"));
        if (!StringUtil.isNullOrEmpty(refundType)) {
            String[] refundTypes = refundType.split(",");
            pageParam.getFilterMap().put("refundTypes", refundTypes);
        }
        String reShipFlg = StringUtil.toSafeString(pageParam.getFilterMap().get("reShipFlg"));
        if (!StringUtil.isNullOrEmpty(reShipFlg)) {
            String[] reShipFlgs = reShipFlg.split(",");
            pageParam.getFilterMap().put("reShipFlgs", reShipFlgs);
        }

        PageResult<SOCp153161Bean> result = this.findPage(pageParam, SOCp153161Bean.class);

        //获取订单信息
        if (result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            /* 计算当前页合计 */
            List<SOCp153161Bean> list = result.getData();
            // 当前页支付金额合计
            BigDecimal currentRefund = BigDecimal.ZERO;
            for (int index = 0; index < list.size(); index ++) {
                // 数据库中为NOT NULL
                BigDecimal refundAmount = list.get(index).getRefundAmount();
                currentRefund = currentRefund.add(refundAmount);
            }
            result.getData().get(0).setCurrentRefund(currentRefund);

            /* 计算总合计 */
            SOCp153161Bean totalBean = this.getTotalInfo(pageParam);
            result.getData().get(0).setTotalRefund(totalBean.getTotalRefund());
        }

        return result;
    }

    @Transactional(readOnly = true)
    public SOCp153161Bean getTotalInfo(BasePageParam pageParam) {
        return super.findOne(SqlId.SQL_ID_GET_TOTAL_INFO, pageParam);
    }
}
