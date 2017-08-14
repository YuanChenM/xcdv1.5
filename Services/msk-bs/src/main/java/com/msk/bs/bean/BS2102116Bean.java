package com.msk.bs.bean;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.utils.CommRestUtil;
import com.msk.core.entity.SlHouseWork;

/**
 * Created by wang_haichun on 2016/8/22.
 */
public class BS2102116Bean extends SlHouseWork{
    private String workTime;

    //Modif for 工作时间转义 at 2016/09/20 by ni_shaotang Start
    public String getWorkTime() {
        if(StringUtil.isNullOrEmpty(this.workTime)){
            return CommRestUtil.setDateToString(super.getWorkStart(),super.getWorkEnd());
        }else {
            return workTime;
        }
    }
//Modif for 工作时间转义 at 2016/09/20 by ni_shaotang End

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}
