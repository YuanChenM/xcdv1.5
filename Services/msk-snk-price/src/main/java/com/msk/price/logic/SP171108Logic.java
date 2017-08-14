package com.msk.price.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171108Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by XU_WEI
 */
@Service
public class SP171108Logic extends BaseLogic {


    private static Logger logger = LoggerFactory.getLogger(SP171108Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 数量申报历史一览
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171108Bean> findSP171108BeansList(BasePageParam pageParam) {
        logger.info("数量申报历史一览");
        PageResult<SP171108Bean> result = this.findPage(pageParam, SP171108Bean.class);
        return result;
    }
}
