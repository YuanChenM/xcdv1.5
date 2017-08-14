package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlHouseEducation;
import com.msk.core.entity.SlHouseIntroduce;
import com.msk.core.entity.SlHouseTraining;
import com.msk.core.entity.SlHouseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ni_shaotang on 2016/9/18.
 */
@Service
public class IBA2141203RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        // 查询买手slcode
        static String SQL_ID_SAVE_HOUSE_WORK="saveWork";
        static String SQL_ID_SAVE_HOUSE_EDUCATION="saveEdu";
        static String SQL_ID_SAVE_HOUSE_TRAINING="saveTrain";
    }
    @Autowired
    private CommonLogic commonLogic;
    /**
     * 查询冻品管家自我介绍信息
     *
     * @param houseCode
     * @return
     */
    @Transactional(readOnly = true)
    public SlHouseIntroduce queryHouseIntroduce(String houseCode) {
        BaseParam param = new BaseParam();
        SlHouseIntroduce houseIntroduce = null;
        param.getFilterMap().put("houseCode", houseCode);
        List<SlHouseIntroduce> list = this.findList(param);
        if (null != list && list.size() > 0) {
            houseIntroduce = list.get(0);
        }
        return houseIntroduce;
    }

    /**
     * 更新冻品管家自我介绍
     *
     * @param houseIntroduce
     * @return
     */
    @Transactional
    public int modifyIntroduce(SlHouseIntroduce houseIntroduce) {
        SlHouseIntroduce slHouseIntroduce = this.queryHouseIntroduce(houseIntroduce.getHouseCode());
        if (null != slHouseIntroduce) {
            return this.modify(houseIntroduce);
        } else {
            Long maxId = commonLogic.maxId("SL_HOUSE_INTRODUCE","INT_ID");
            houseIntroduce.setIntId(maxId);
            return this.save(houseIntroduce);
        }
    }

    /**
     * 保存冻品管家工作经历
     * @param slHouseWork
     * @return
     */
    @Transactional
    public int saveSlHouseWork(SlHouseWork slHouseWork){
        Long maxId = commonLogic.maxId("SL_HOUSE_WORK","WORK_ID");
        slHouseWork.setWorkId(maxId);
        return this.save(SqlId.SQL_ID_SAVE_HOUSE_WORK,slHouseWork);
    }
    /**
     * 保存冻品管家教育经历
     * @param slHouseEducation
     * @return
     */
    @Transactional
    public int saveSlHouseEducation(SlHouseEducation slHouseEducation){
        Long maxId = commonLogic.maxId("SL_HOUSE_EDUCATION","EDU_ID");
        slHouseEducation.setEduId(maxId);
        return this.save(SqlId.SQL_ID_SAVE_HOUSE_EDUCATION,slHouseEducation);
    }
    /**
     * 保存冻品管家培训经历
     * @param slHouseTraining
     * @return
     */
    @Transactional
    public int saveSlHousTraining(SlHouseTraining slHouseTraining){
        Long maxId = commonLogic.maxId("sl_house_training","TRAIN_ID");
        slHouseTraining.setTrainId(maxId);
        return this.save(SqlId.SQL_ID_SAVE_HOUSE_TRAINING,slHouseTraining);
    }
}
