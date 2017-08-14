package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SlHouseEducationHis;
import com.msk.core.entity.SlHouseTrainingHis;
import com.msk.core.entity.SlHouseWorkHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102116Param;
import com.msk.bs.bean.BS2102118Bean;
import com.msk.common.base.BaseLogic;

import java.util.List;

/**
 * Created by wang_haichun on 2016/8/22.
 */
@Service
public class BS2102118Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    interface SqlId {
        String SQL_ID_FIND_HOUSE_WORK_HIS = "findWorkHis";
        String SQL_ID_FIND_HOUSE_EDU_HIS = "findEduHis";
        String SQL_ID_FIND_HOUSE_TRAIN_HIS = "findTrainHis";
    }

    @Transactional(readOnly = true)
    public List<SlHouseWorkHis> findWorkHis(BaseParam baseParam){
        //查询工作经历履历
        List<SlHouseWorkHis> workHisList = this.findList(SqlId.SQL_ID_FIND_HOUSE_WORK_HIS,baseParam);
        return workHisList;
    }

    @Transactional(readOnly = true)
    public List<SlHouseEducationHis> findEduHis(BaseParam baseParam){
        //查询工作经历履历
        List<SlHouseEducationHis> workEduList = this.findList(SqlId.SQL_ID_FIND_HOUSE_EDU_HIS,baseParam);
        return workEduList;
    }

    @Transactional(readOnly = true)
    public List<SlHouseTrainingHis> findTrainHis(BaseParam baseParam){
        //查询工作经历履历

        List<SlHouseTrainingHis> trainHisList = this.findList(SqlId.SQL_ID_FIND_HOUSE_TRAIN_HIS,baseParam);
        return trainHisList;
    }


}
