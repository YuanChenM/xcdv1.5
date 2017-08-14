package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

import java.util.List;

/**
 * SO151510_发货单取消画面前台接口返回参数LIST
 * Created by wang_jianzhou on 2016/8/5.
 */
public class SO151510BeanList extends BaseEntity {

    private List<SO151510Bean> beanList;

    /** 控制页面编辑按钮是否显示*/
    private boolean btnFlag;

    public boolean isBtnFlag() {
        return btnFlag;
    }

    public void setBtnFlag(boolean btnFlag) {
        this.btnFlag = btnFlag;
    }

    public List<SO151510Bean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<SO151510Bean> beanList) {
        this.beanList = beanList;
    }
}
