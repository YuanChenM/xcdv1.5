package com.msk.seller.bean;


import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by fjm on 2016/1/31.
 */
public class SL241103070Bean extends BaseEntity {
    private String eslCode;
    //成员ID
    private Integer ememberId;
    //是否负责人
    private String eleaderFlg;
    //姓名
    private String ememberName;
    //年龄
    private Integer ememberAge;
    //文化程度
    private String ememberEduc;
    //联系方式
    private String ememberTel;
    //删除标志
    private String edelFlg;


    /*public String getEmemberId() {
        return ememberId;
    }

    public void setEmemberId(String ememberId) {
        this.ememberId = ememberId;
    }*/


    public String getEmemberName() {
        return ememberName;
    }

    public void setEmemberName(String ememberName) {
        this.ememberName = ememberName;
    }

    public Integer getEmemberAge() {
        return ememberAge;
    }

    public void setEmemberAge(Integer ememberAge) {
        this.ememberAge = ememberAge;
    }

    public String getEmemberEduc() {
        return ememberEduc;
    }

    public void setEmemberEduc(String ememberEduc) {
        this.ememberEduc = ememberEduc;
    }

    public String getEmemberTel() {
        return ememberTel;
    }

    public void setEmemberTel(String ememberTel) {
        this.ememberTel = ememberTel;
    }

    public String getEdelFlg() {
        return edelFlg;
    }

    public void setEdelFlg(String edelFlg) {
        this.edelFlg = edelFlg;
    }

    public String getEleaderFlg() {
        return eleaderFlg;
    }

    public void setEleaderFlg(String eleaderFlg) {
        this.eleaderFlg = eleaderFlg;
    }

    public String getEslCode() {
        return eslCode;
    }

    public void setEslCode(String eslCode) {
        this.eslCode = eslCode;
    }

    public Integer getEmemberId() {
        return ememberId;
    }

    public void setEmemberId(Integer ememberId) {
        this.ememberId = ememberId;
    }
}
