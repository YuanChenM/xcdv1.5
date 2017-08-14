package com.msk.price.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.SP171192Bean;
import com.msk.price.bean.SP171192Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_shuai on 2016/5/20.
 */
public class SP171192Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171192Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    interface SqlId {
        String SQL_ID_FIND_WAY_DETAIL = "findWayDetail";
        String SQL_ID_GET_WAY_DETAIL_COUNT = "getWayDetailCount";
        String SQL_ID_DEL_WAY = "delWay";
        String SQL_ID_FIND_WAY = "findWay";
        String SQL_ID_DEL_MAIN_WAY = "delMainWay";

    }
    @Transactional(readOnly = true)
    public PageResult<SP171192Result> findWayDetail(BasePageParam basePageParam){
        int count = super.getCount(SqlId.SQL_ID_GET_WAY_DETAIL_COUNT,basePageParam);
        List<SP171192Result> sp171192ResultList = null;
        if(count != NumberConst.IntDef.INT_ZERO){
            sp171192ResultList = findList(SqlId.SQL_ID_FIND_WAY,basePageParam);
        }
        PageResult<SP171192Result> result = new PageResult<SP171192Result>();
        if(sp171192ResultList == null){
            List<SP171192Result> sp171192ResultList2 = new ArrayList<SP171192Result>();
            result.setData(sp171192ResultList2);
            result.setRecordsTotal(count);
        }else {
            result.setData(sp171192ResultList);
            result.setRecordsTotal(count);
        }

        return result;

    }
    @Transactional
    public void delWay(SP171192Bean sp171192Bean){
        super.modify(SqlId.SQL_ID_DEL_WAY,sp171192Bean);
        super.modify(SqlId.SQL_ID_DEL_MAIN_WAY,sp171192Bean);
    }

}
