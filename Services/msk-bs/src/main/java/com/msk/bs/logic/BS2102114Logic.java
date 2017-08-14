package com.msk.bs.logic;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102114Bean;
import com.msk.bs.bean.BS2102114Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseStar;

/**
 * 定星管理
 * Created by ren_qiang on 2016/8/22.
 */
@Service
public class BS2102114Logic extends BaseLogic {

    private Logger logger = LoggerFactory.getLogger(BS2102114Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        String SQL_ID_FIND_HOUSE_SHOW_NAME = "findHouseShowName";
        String SQL_ID_MODIFY_STAR = "modifyStar";
        String SQL_ID_FIND_HOUSE_ACCOUNT = "findHouseAccount";
    }

    @Transactional
    public int update(BS2102114Param param){
        long gradeId =  commonLogic.maxId("SL_HOUSE_STAR", "GRADE_ID");
        param.setGradeId(gradeId);
        String validYearMonth = getTimeStr();
        int count = this.getCount(param);
        param.setValidYearMonth(validYearMonth);
        if(count>0){
            return  this.modify(SqlId.SQL_ID_MODIFY_STAR,param);
        }
        else{
            return this.save(param);
        }

    }

    @Transactional
    public BS2102114Bean findHouseShowName(BaseParam param){
        param.setFilter("validYearMonth",getTimeStr());
        BS2102114Bean bs2102114Bean = this.findOne(SqlId.SQL_ID_FIND_HOUSE_SHOW_NAME, param);
        if(bs2102114Bean == null){
            logger.info("查询不到当月的定级");
            logger.info("准备插入当月定级数据");
            //查询冻品管家信息
            SlHouseAccount houseAccount = this.findOne(SqlId.SQL_ID_FIND_HOUSE_ACCOUNT,param);
            if(houseAccount != null){
                BS2102114Param houseStar = new BS2102114Param();
                houseStar.setGradeId(commonLogic.maxId("SL_HOUSE_STAR","GRADE_ID"));
                houseStar.setSlCode(houseAccount.getSlCode());
                houseStar.setHouseCode(houseAccount.getHouseCode());
                houseStar.setStarCode(new BigDecimal(3));
                houseStar.setValidYearMonth(this.getTimeStr());
                houseStar.setEndTime(nextMonth());
                houseStar.setStatus("0");
                houseStar.setCrtId(param.getCrtId());
                houseStar.setCrtTime(new java.util.Date());
                houseStar.setUpdId(param.getUpdId());
                houseStar.setUpdTime(new java.util.Date());
                houseStar.setActId(param.getActId());
                houseStar.setActTime(new java.util.Date());
                int result = this.save(houseStar);
                logger.info("插入成功"+result+"条当月定级数据");
                bs2102114Bean = this.findOne(SqlId.SQL_ID_FIND_HOUSE_SHOW_NAME, param);
            }
        }
        return bs2102114Bean;
    }


    private String getTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String str = sdf.format(new java.util.Date());
        return  str;
    }

    private java.util.Date nextMonth(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        java.util.Date nextDate = cal.getTime();
        return nextDate;
    }
}
