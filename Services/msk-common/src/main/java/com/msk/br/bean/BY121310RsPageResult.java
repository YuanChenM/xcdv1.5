package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrOClaMachiningInfo;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/12.
 */
public class BY121310RsPageResult extends RsPageResult {

    private List<BrOClaMachiningInfo> brOClaMachiningInfoList;

    public List<BrOClaMachiningInfo> getBrOClaMachiningInfoList() {
        return brOClaMachiningInfoList;
    }

    public void setBrOClaMachiningInfoList(List<BrOClaMachiningInfo> brOClaMachiningInfoList) {
        this.brOClaMachiningInfoList = brOClaMachiningInfoList;
    }
}
