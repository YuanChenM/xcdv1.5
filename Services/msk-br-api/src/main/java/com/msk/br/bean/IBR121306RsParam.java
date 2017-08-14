package com.msk.br.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.Date;
import java.util.List;


public class IBR121306RsParam extends BasePageParam {
    //ID
    private Long id;
    //冻品管家组ID
    private String hkGroupId;
    //买手店ID(冻品管家主键)
    private String slCode;
    //冻品管家编码(冻品管家主键)
    private String houseCode;
    //管家显示编码
    private String houseCodeDis;
    //登录手机号码
    private String houseTel;
    //管家显示名称
    private String houseShowName;
    //联系人姓名
    private String houseContact;
    //加入时间
    private Date joinTime;
    //离开时间
    private Date leaveTime;
    List<IBR121306RsParam> houseList;

    private List<Long> hkGroupIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHkGroupId() {
        return hkGroupId;
    }

    public void setHkGroupId(String hkGroupId) {
        this.hkGroupId = hkGroupId;
    }

    public List<IBR121306RsParam> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBR121306RsParam> houseList) {
        this.houseList = houseList;
    }

    public List<Long> getHkGroupIds() {
        return hkGroupIds;
    }

    public void setHkGroupIds(List<Long> hkGroupIds) {
        this.hkGroupIds = hkGroupIds;
    }

    public String getHouseCodeDis() {
        return houseCodeDis;
    }

    public void setHouseCodeDis(String houseCodeDis) {
        this.houseCodeDis = houseCodeDis;
    }

    public String getHouseTel() {
        return houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getHouseContact() {
        return houseContact;
    }

    public void setHouseContact(String houseContact) {
        this.houseContact = houseContact;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}
