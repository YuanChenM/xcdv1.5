package com.msk.price.logic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.price.bean.SP171192Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by wang_shuai on 2016/5/23.
 */
public class SP17119201Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SP171192Logic.class);
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    interface SqlId {
        String SQL_ID_FIND_MAX_CODE = "findMaxCode";
        String SQL_ID_SAVE_WAY = "saveWay";
        String SQL_ID_UPDATE_WAY = "updateWay";
        String SQL_ID_SAVE_MAIN_WAY = "saveMainWay";
        String SQL_ID_UPDATE_MAIN_WAY = "updateMainWay";
        String SQL_CHECK_WAYINFO = "checkWayInfo";
    }

    @Transactional(readOnly = true)
    public String findMaxCode(){
//        SP171192Bean sp171192Bean = new SP171192Bean();
//        SP171192Bean resultBean = new SP171192Bean();
//        resultBean = super.findOne(SqlId.SQL_ID_FIND_MAX_CODE,sp171192Bean);
        Long wayCode = commonLogic.maxId("SP_WAYG_DETAIL","WAY_CODE");
        return wayCode.toString();
    }
    @Transactional
    public int save(String paramList,BaseParam baseParam){
        Map<String, SP171192Bean> map = JSON.parseObject(paramList, new TypeReference<Map<String, SP171192Bean>>() {
        });
        String wayCode = this.findMaxCode();
        SP171192Bean sp171192BeanMain = null;
        for(SP171192Bean sp171192Bean:map.values()){
            sp171192Bean.setCrtId(baseParam.getCrtId());
            sp171192Bean.setCrtTime(DateTimeUtil.getCustomerDate());
            sp171192Bean.setUpdId(baseParam.getUpdId());
            sp171192Bean.setUpdTime(DateTimeUtil.getCustomerDate());
            sp171192Bean.setActId(baseParam.getActId());
            sp171192Bean.setActTime(DateTimeUtil.getCustomerDate());
            sp171192Bean.setWayId(this.commonLogic.maxId("SP_WAY", "WAY_CODE"));
            sp171192Bean.setWayCode(wayCode);
            super.save(SqlId.SQL_ID_SAVE_WAY, sp171192Bean);
            sp171192BeanMain = sp171192Bean;
        }
        sp171192BeanMain.setWayCode(wayCode);
      return  super.save(SqlId.SQL_ID_SAVE_MAIN_WAY, sp171192BeanMain);
    }
    @Transactional
    public void update(String paramList,BaseParam baseParam){
        Map<String, SP171192Bean> map = JSON.parseObject(paramList, new TypeReference<Map<String, SP171192Bean>>() {
        });
        SP171192Bean sp171192Bean2 = null;
        for(SP171192Bean sp171192Bean:map.values()){
            sp171192Bean.setUpdId(baseParam.getUpdId());
            sp171192Bean.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_UPDATE_WAY,sp171192Bean);
            sp171192Bean2 = sp171192Bean;
        }
        super.modify(SqlId.SQL_ID_UPDATE_MAIN_WAY,sp171192Bean2);

    }

    @Transactional(readOnly = false)
    public int checkWayInfo(BaseParam baseParam){
        return super.getCount(SqlId.SQL_CHECK_WAYINFO,baseParam);
    }
}
