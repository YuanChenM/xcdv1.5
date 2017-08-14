package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.seller.bean.ISL231205RsParam;
import com.msk.seller.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liu_yan2 on 2016/5/27.
 */
@Service
public class ISL231205RsLogic extends BaseLogic {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231205RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        static final String SQL_ID_GET_SL_ONECLASS = "getSlOneClass";;
    }

    /**
     * 根据卖家ID查询卖家一级分类
     * @return 卖家一级分类信息
     * @author liu_yan2
     */
    @Transactional(readOnly = true)
    public List<SlProductBean> querySlOneClass(ISL231205RsParam param) {
        logger.debug("根据卖家ID查询卖家一级分类");
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("slCode",param.getSlCode());
        baseParam.setFilter("pdClassesCode",param.getPdClassesCode());
        baseParam.setFilter("machiningCode",param.getMachiningCode());
        baseParam.setFilter("pdBreedCode",param.getPdBreedCode());
        baseParam.setFilter("pdFeatureCode",param.getPdFeatureCode());
        List<SlProductBean> resultList = super.findList(SqlId.SQL_ID_GET_SL_ONECLASS, baseParam);
        return resultList;
    }
}
