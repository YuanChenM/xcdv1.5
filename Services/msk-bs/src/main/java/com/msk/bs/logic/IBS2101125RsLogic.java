package com.msk.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101125RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by ren_qiang on 2016/8/23.
 */
@Service
public class IBS2101125RsLogic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(IBS2101119RsLogic.class);

    interface sqlId{
        String SQL_ID_INSERT_HOUSE_PRAISE_REC = "insert";
    }

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional
    public Integer setHousePraise(IBS2101125RsParam param){
        long praiseId =  commonLogic.maxId("SL_HOUSE_BYPRAISE_REC", "PRAISE_ID");
        param.setPraiseId(praiseId);
        param.setActTime(new Date());
        int count =this.getCount(param);
        Integer result;
        if(count >0){
            param.setUpdTime(new Date());
            result = this.modify(param);
        }
        else{
            param.setCrtTime(new Date());
            result = this.save(param);
        }
       return result;
    }


}
