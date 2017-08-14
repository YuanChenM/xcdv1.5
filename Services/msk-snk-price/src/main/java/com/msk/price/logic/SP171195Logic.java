package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.SP171195Bean;
import com.msk.price.bean.SP171195Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by sun_jiaju
 */
@Service
public class SP171195Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SP171195Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    /**
     *  数据处理
     *
     * @return
     */
    @Transactional
    public int dataResolve(SP171195Param param) {
        String thisMonth = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH,
                DateTimeUtil.getCustomerDate());
        String forecastYm = DateTimeUtil.monthAddN(thisMonth, 1);
        Date nowDate = DateTimeUtil.getCustomerDate();
        param.setForecastYm(forecastYm);
        param.setUpdId(param.getActId());
        param.setUpdTime(nowDate);
        //预测月数据逻辑删除
        super.modify(SqlId.SQLID_UPDATE_FORECAST_CONDITION, param);

        //插入需求预测条件系数
        return this.insertSpForecastCondition(forecastYm, param);
    }

    /**
     * 插入需求预测条件系数管理表
     * @param forecastYm
     * @param param
     */
    @Transactional
    private int insertSpForecastCondition(String forecastYm, BaseParam param)
    {
        long maxRatioId = commonLogic.maxId("SP_FORECAST_CONDITION", "CONDITION_RATIO_ID");
        Date nowDate = DateTimeUtil.getCustomerDate();
        SP171195Bean insertEntity = new SP171195Bean();
        insertEntity.setRatioId(maxRatioId);
        insertEntity.setForecastYm(forecastYm);
        insertEntity.setLgcsCode(param.getFilterMap().get("lgcsCode").toString());
        insertEntity.setSecurityRatio(new BigDecimal(param.getFilterMap().get("securityRatio").toString()));
        insertEntity.setSellForecastRatio(new BigDecimal(param.getFilterMap().get("sellForecastRatio").toString()));
        insertEntity.setCrtId(param.getActId());
        insertEntity.setCrtTime(nowDate);
        insertEntity.setUpdId(param.getActId());
        insertEntity.setUpdTime(nowDate);
        insertEntity.setActId(param.getActId());
        insertEntity.setActTime(nowDate);
        return super.save(insertEntity);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQLID_UPDATE_FORECAST_CONDITION = "updateForecastCondition";
    }
}
