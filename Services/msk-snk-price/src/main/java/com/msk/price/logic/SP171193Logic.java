package com.msk.price.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;

import com.msk.price.bean.SP171193Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by wang_shuai on 2016/5/17.
 */
public class SP171193Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171193Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    interface SqlId {
        String SQL_ID_GET_WAY_DETAIL_COUNT = "getWayDetailCount";
        String SQL_ID_FIND_WAY = "findWay";

    }
    @Transactional(readOnly = true)
    public PageResult<SP171193Result> findWayDetail(BasePageParam basePageParam){
        int count = super.getCount(SqlId.SQL_ID_GET_WAY_DETAIL_COUNT,basePageParam);
//        List<SP171193Bean> sp171193BeanList = null;
        List<SP171193Result> sp171193ResultList = null;
        if(count != NumberConst.IntDef.INT_ZERO){
            sp171193ResultList = findList(SqlId.SQL_ID_FIND_WAY,basePageParam);
        }
        PageResult<SP171193Result> result = new PageResult<SP171193Result>();
        if(sp171193ResultList == null){
            List<SP171193Result> sp171193ResultList2 = new ArrayList<SP171193Result>();
            result.setData(sp171193ResultList2);
            result.setRecordsTotal(count);
        }else {
            result.setData(sp171193ResultList);
            result.setRecordsTotal(count);
        }
        return result;

    }

}
