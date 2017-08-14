package com.msk.bs.bean;

import com.hoperun.core.utils.StringUtil;
import com.msk.bs.utils.CommRestUtil;
import com.msk.core.entity.SlHouseEducation;
/**
 * Created by wang_haichun on 2016/8/22.
 */
public class BS2102117Bean extends SlHouseEducation{
    private String eduTime;


    //Modif for 教育时间转义 at 2016/09/20 by ni_shaotang Start
    public String getEduTime() {
        if(StringUtil.isNullOrEmpty(this.eduTime)){
            return CommRestUtil.setDateToString(super.getEduStart(), super.getEduEnd());
        }else {
            return eduTime;
        }
    }
    //Modif for 教育时间转义 at 2016/09/20 by ni_shaotang end

    public void setEduTime(String eduTime) {
        this.eduTime = eduTime;
    }
}
