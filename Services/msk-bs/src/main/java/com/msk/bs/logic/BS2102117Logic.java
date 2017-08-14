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
import com.msk.bs.bean.BS2102118Bean;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseTraining;
import com.msk.core.entity.SlHouseTrainingHis;

/**
 * Created by wang_haichun on 2016/8/22.
 */
@Service
public class BS2102117Logic extends BaseLogic {
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
        String SQL_ID_FIND_HOUSE_TRAIN_BY_ID = "findTrainById";
        String SQL_ID_UPDATE_HOUSE_TRAIN_BY_ID="updateTrainByID";
    }

    @Transactional(readOnly = true)
    public PageResult<BS2102118Bean> findTrain(BS2102116Param bs2102116Param){
        //查询工作经历
        PageResult<BS2102118Bean> pageResult = this.findPage(bs2102116Param,BS2102118Bean.class);
        return pageResult;
    }

    @Transactional
    public int delTrain(BS2102118Bean bs2102118Bean){
        int result = this.modify(bs2102118Bean);
        List<SlHouseTrainingHis> trainHisList = new ArrayList<>();
        SlHouseTrainingHis slHouseTrainingHis =setTrainHis(bs2102118Bean);
        trainHisList.add(slHouseTrainingHis);
        result += bs2102113Logic.saveTrainHis(trainHisList);
        return result;
    }

    @Transactional(readOnly = true)
    public BS2102118Bean findTrainById(Long trainId){
        BS2102118Bean bs2102118Bean = new BS2102118Bean();
        bs2102118Bean.setTrainId(trainId);
        return this.findOne(SqlId.SQL_ID_FIND_HOUSE_TRAIN_BY_ID,bs2102118Bean);
    }

    @Transactional
    public int modifyTrain(SlHouseTraining slHouseTraining){
        return this.modify(SqlId.SQL_ID_UPDATE_HOUSE_TRAIN_BY_ID,slHouseTraining);
    }

    private SlHouseTrainingHis setTrainHis(BS2102118Bean bs2102118Bean){
        SlHouseTrainingHis slHouseTrainingHis = new SlHouseTrainingHis();
        slHouseTrainingHis.setTrainHisId(commonLogic.maxId("SL_HOUSE_TRAINING_HIS","TRAIN_HIS_ID"));
        slHouseTrainingHis.setTrainId(bs2102118Bean.getTrainId());
        slHouseTrainingHis.setSlCode(bs2102118Bean.getSlCode());
        slHouseTrainingHis.setHouseCode(bs2102118Bean.getHouseCode());
        slHouseTrainingHis.setTrainStart(bs2102118Bean.getTrainStart());
        slHouseTrainingHis.setTrainEnd(bs2102118Bean.getTrainEnd());
        slHouseTrainingHis.setTrainComp(bs2102118Bean.getTrainComp());
        slHouseTrainingHis.setTrainCertificate(bs2102118Bean.getTrainCertificate());
        slHouseTrainingHis.setActFlg("2");
        slHouseTrainingHis.setDelFlg("0");
        slHouseTrainingHis.setCrtId(bs2102118Bean.getCrtId());
        slHouseTrainingHis.setCrtTime(new Date());
        slHouseTrainingHis.setUpdId(bs2102118Bean.getUpdId());
        slHouseTrainingHis.setUpdTime(new Date());
        slHouseTrainingHis.setActTime(new Date());
        slHouseTrainingHis.setActId(bs2102118Bean.getActId());
        slHouseTrainingHis.setVer(0);
        return slHouseTrainingHis;
    }
}
