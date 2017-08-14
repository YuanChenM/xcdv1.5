package com.msk.ds.logic;

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
public class SC182213Logic extends BaseLogic{

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public void deleteInfo(Long lotId,String serialId){
        BaseParam baseParam = new BaseParam();
        String slotId = lotId.toString();
        baseParam.setFilter("lotId",slotId);
        baseParam.setFilter("serialId",serialId);
        super.remove(baseParam);
    }

}
