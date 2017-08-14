package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;

import java.util.List;

/**
 * Created by wang_fan2 on 2016/9/9.
 */
public class ISO152411RsParamBean extends BaseParam{
    private List<ISO152411RsBean> userAccountList;

    public List<ISO152411RsBean> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<ISO152411RsBean> userAccountList) {
        this.userAccountList = userAccountList;
    }


}
