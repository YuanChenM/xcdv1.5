package com.msk.bs.bean;

import com.msk.core.entity.SlHouseManage;

/**
 * Created by wang_haichun on 2016/8/4.
 */
public class BS2102107Bean extends SlHouseManage {

    private String houseCategoryName;
    private String houseReclassifyName;
    private String isChangeBuyersText;
    private String grade;
    private String star;

    public String getHouseCategoryName() {
        return houseCategoryName;
    }

    public void setHouseCategoryName(String houseCategoryName) {
        this.houseCategoryName = houseCategoryName;
    }

    public String getHouseReclassifyName() {
        return houseReclassifyName;
    }

    public void setHouseReclassifyName(String houseReclassifyName) {
        this.houseReclassifyName = houseReclassifyName;
    }

    public String getIsChangeBuyersText() {
        return isChangeBuyersText;
    }

    public void setIsChangeBuyersText(String isChangeBuyersText) {
        this.isChangeBuyersText = isChangeBuyersText;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
