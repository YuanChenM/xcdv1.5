package com.msk.ds.logic;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by air on 2016/4/28.
 */
@Service
public class SC182212Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String SQL_ID_REMOVE ="removeMore";
    }
    @Transactional
    public void deleteInfo(Long lotId){
        BaseParam baseParam = new BaseParam();
        String slotId = lotId.toString();
        baseParam.setFilter("lotId",slotId);
        super.remove(baseParam);
    }
    @Transactional
    public void deleteInfo2(Long lotId){
        BaseParam baseParam = new BaseParam();
        String slotId = lotId.toString();
        baseParam.setFilter("lotId",slotId);
        try {
            super.remove(baseParam);
            super.remove(SqlId.SQL_ID_REMOVE,baseParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
