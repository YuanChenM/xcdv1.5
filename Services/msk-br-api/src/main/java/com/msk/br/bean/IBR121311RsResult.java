package com.msk.br.bean;

import com.msk.core.entity.BrHkGroup;

import java.util.Date;
import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/9.
 */
public class IBR121311RsResult  {
    private List<BrHkGroup> hkGroupList;

    private Date createTime;
    public List<BrHkGroup> getHkGroupList() {
        return hkGroupList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setHkGroupList(List<BrHkGroup> hkGroupList) {
        this.hkGroupList = hkGroupList;
    }
}
