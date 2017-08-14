package com.msk.bs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102116Param;
import com.msk.bs.bean.BS2102117Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseEducation;
import com.msk.core.entity.SlHouseEducationHis;

/**
 * Created by wang_haichun on 2016/8/22.
 */
@Service
public class BS2102116Logic extends BaseLogic {
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
        String SQL_ID_FIND_HOUSE_EDU_BY_ID = "findEduById";
        String SQL_ID_UPDATE_HOUSE_EDU_BY_ID="updateEduByID";
    }

    @Transactional(readOnly = true)
    public PageResult<BS2102117Bean> findEdu(BS2102116Param bs2102116Param){
        //查询工作经历
        PageResult<BS2102117Bean> pageResult = this.findPage(bs2102116Param,BS2102117Bean.class);
        return pageResult;
    }

    @Transactional
    public int delEdu(BS2102117Bean bs2102117Bean){
        int result = this.modify(bs2102117Bean);
        List<SlHouseEducationHis> eduHisList = new ArrayList<>();
        SlHouseEducationHis slHouseEducationHis =setEduHis(bs2102117Bean);
        eduHisList.add(slHouseEducationHis);
        result += bs2102113Logic.saveEduHis(eduHisList);
        return result;
    }

    @Transactional(readOnly = true)
    public BS2102117Bean findEduById(Long eduId){
        BS2102117Bean bs2102117Bean = new BS2102117Bean();
        bs2102117Bean.setEduId(eduId);
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_EDU_BY_ID,bs2102117Bean);
    }

    @Transactional
    public int modifyEdu(SlHouseEducation slHouseEducation){
        return this.modify(SqlId.SQL_ID_UPDATE_HOUSE_EDU_BY_ID,slHouseEducation);
    }


    private SlHouseEducationHis setEduHis(BS2102117Bean bs2102117Bean){
        SlHouseEducationHis slHouseEducationHis = new SlHouseEducationHis();
        slHouseEducationHis.setEduHisId(commonLogic.maxId("SL_HOUSE_EDUCATION_HIS","EDU_HIS_ID"));
        slHouseEducationHis.setEduId(bs2102117Bean.getEduId());
        slHouseEducationHis.setSlCode(bs2102117Bean.getSlCode());
        slHouseEducationHis.setHouseCode(bs2102117Bean.getHouseCode());
        slHouseEducationHis.setEduStart(bs2102117Bean.getEduStart());
        slHouseEducationHis.setEduEnd(bs2102117Bean.getEduEnd());
        slHouseEducationHis.setEduComp(bs2102117Bean.getEduComp());
        slHouseEducationHis.setEduRecord(bs2102117Bean.getEduRecord());
        slHouseEducationHis.setEduDegree(bs2102117Bean.getEduDegree());
        slHouseEducationHis.setActFlg("2");
        slHouseEducationHis.setDelFlg("0");
        slHouseEducationHis.setCrtId(bs2102117Bean.getCrtId());
        slHouseEducationHis.setCrtTime(new Date());
        slHouseEducationHis.setUpdId(bs2102117Bean.getUpdId());
        slHouseEducationHis.setUpdTime(new Date());
        slHouseEducationHis.setActTime(new Date());
        slHouseEducationHis.setActId(bs2102117Bean.getActId());
        slHouseEducationHis.setVer(0);
        return slHouseEducationHis;
    }

}
