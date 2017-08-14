package com.msk.bs.bean;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.utils.CommRestUtil;
import com.msk.core.entity.SlHouseTraining;

/**
 * Created by wang_haichun on 2016/8/22.
 */
public class BS2102118Bean extends SlHouseTraining{
    private String trainTime;

    //Modif for 培训时间转义 at 2016/09/20 by ni_shaotang Start
    public String getTrainTime() {
        if(StringUtil.isNullOrEmpty(this.trainTime)){
            return CommRestUtil.setDateToString(super.getTrainStart(), super.getTrainEnd());
        }else {
            return trainTime;
        }
    }
    //Modif for 培训时间转义 at 2016/09/20 by ni_shaotang End

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }
}
