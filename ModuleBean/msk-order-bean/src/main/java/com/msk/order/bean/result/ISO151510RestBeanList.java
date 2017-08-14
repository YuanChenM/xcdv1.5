package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151510_发货单取消画面后台接口返回参数LIST
 * Created by wang_jianzhou on 2016/8/5.
 */
public class ISO151510RestBeanList extends BaseResult{

    private List<ISO151510RestBean> beanList;

    /** 控制页面编辑按钮是否显示*/
    private boolean btnFlag;

    public boolean isBtnFlag() {
        return btnFlag;
    }

    public void setBtnFlag(boolean btnFlag) {
        this.btnFlag = btnFlag;
    }

    public List<ISO151510RestBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<ISO151510RestBean> beanList) {
        this.beanList = beanList;
    }
}
