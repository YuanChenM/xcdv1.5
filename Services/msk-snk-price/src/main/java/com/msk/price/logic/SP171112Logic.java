package com.msk.price.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171112Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SP171112Logic.
 *
 * @author zhou_Ling
 */
@Service
public class SP171112Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171112Logic.class);

    /**
     * SqlId.
     *
     * @author zhouling
     */
    interface SqlId {
    }

    /**
     * 删除价盘
     * @param sp171112Bean
     * @return
     */
    @Transactional
    public int removePricePeriod(SP171112Bean sp171112Bean){
        sp171112Bean.setUpdTime(DateTimeUtil.getCustomerDate());
        return  this.remove(sp171112Bean);
    }

    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

}
