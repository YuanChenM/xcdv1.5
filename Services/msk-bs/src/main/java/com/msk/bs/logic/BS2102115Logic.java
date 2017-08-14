package com.msk.bs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102116Bean;
import com.msk.bs.bean.BS2102116Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseWork;
import com.msk.core.entity.SlHouseWorkHis;

/**
 * Created by wang_haichun on 2016/8/22.
 */
@Service
public class BS2102115Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BS2102113Logic bs2102113Logic;


    interface SqlId {
        String SQL_ID_FIND_HOUSE_WORK_BY_ID = "findWorkById";
        String SQL_ID_UPDATE_HOUSE_WORK_BY_ID="updateWorkByID";
    }

    @Transactional(readOnly = true)
    public PageResult<BS2102116Bean> findWork(BS2102116Param bs2102116Param){
        //查询工作经历
        PageResult<BS2102116Bean> pageResult = this.findPage(bs2102116Param,BS2102116Bean.class);
        return pageResult;
    }


    @Transactional
    public int delWork(BS2102116Bean bs2102116Bean){
        int result = this.modify(bs2102116Bean);
        List<SlHouseWorkHis> workHisList = new ArrayList<>();
        SlHouseWorkHis slHouseWorkHis =setWorkHis(bs2102116Bean);
        workHisList.add(slHouseWorkHis);
        result += bs2102113Logic.saveWorkHis(workHisList);
        return result;
    }

    @Transactional(readOnly = true)
    public BS2102116Bean findWorkById(Long workId){
        BS2102116Bean bs2102116Bean = new BS2102116Bean();
        bs2102116Bean.setWorkId(workId);
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_WORK_BY_ID,bs2102116Bean);
    }

    @Transactional
    public int modifyWork(SlHouseWork slHouseWork){
        return this.modify(SqlId.SQL_ID_UPDATE_HOUSE_WORK_BY_ID,slHouseWork);
    }


    private SlHouseWorkHis setWorkHis(BS2102116Bean bs2102116Bean){
        //保存工作经历履历
        SlHouseWorkHis slHouseWorkHis = new SlHouseWorkHis();
        slHouseWorkHis.setWorkHisId(commonLogic.maxId("SL_HOUSE_WORK_HIS","WORK_HIS_ID"));
        slHouseWorkHis.setWorkId(bs2102116Bean.getWorkId());
        slHouseWorkHis.setSlCode(bs2102116Bean.getSlCode());
        slHouseWorkHis.setHouseCode(bs2102116Bean.getHouseCode());
        slHouseWorkHis.setWorkStart(bs2102116Bean.getWorkStart());
        slHouseWorkHis.setWorkEnd(bs2102116Bean.getWorkEnd());
        slHouseWorkHis.setWorkComp(bs2102116Bean.getWorkComp());
        slHouseWorkHis.setWorkPosition(bs2102116Bean.getWorkPosition());
        slHouseWorkHis.setWorkStation(bs2102116Bean.getWorkStation());
        slHouseWorkHis.setActFlg("2");
        slHouseWorkHis.setDelFlg("0");
        slHouseWorkHis.setCrtId(bs2102116Bean.getCrtId());
        slHouseWorkHis.setCrtTime(new Date());
        slHouseWorkHis.setUpdId(bs2102116Bean.getUpdId());
        slHouseWorkHis.setUpdTime(new Date());
        slHouseWorkHis.setActTime(new Date());
        slHouseWorkHis.setActId(bs2102116Bean.getActId());
        slHouseWorkHis.setVer(0);
        return slHouseWorkHis;
    }

}
