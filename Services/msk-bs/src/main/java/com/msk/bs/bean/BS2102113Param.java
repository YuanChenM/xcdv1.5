package com.msk.bs.bean;

import com.msk.core.entity.*;

import java.util.List;

/**
 * Created by wang_haichun on 2016/8/19.
 */
public class BS2102113Param {
    private List<SlHouseWork> houseWorkList;
    private List<SlHouseEducation> houseEduList;
    private List<SlHouseTraining> houseTrainList;
    private SlHouseIntroduce houseIntroduce;

    public List<SlHouseWork> getHouseWorkList() {
        return houseWorkList;
    }

    public void setHouseWorkList(List<SlHouseWork> houseWorkList) {
        this.houseWorkList = houseWorkList;
    }

    public List<SlHouseEducation> getHouseEduList() {
        return houseEduList;
    }

    public void setHouseEduList(List<SlHouseEducation> houseEduList) {
        this.houseEduList = houseEduList;
    }

    public List<SlHouseTraining> getHouseTrainList() {
        return houseTrainList;
    }

    public void setHouseTrainList(List<SlHouseTraining> houseTrainList) {
        this.houseTrainList = houseTrainList;
    }

    public SlHouseIntroduce getHouseIntroduce() {
        return houseIntroduce;
    }

    public void setHouseIntroduce(SlHouseIntroduce houseIntroduce) {
        this.houseIntroduce = houseIntroduce;
    }
}
