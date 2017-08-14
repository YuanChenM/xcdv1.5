package com.msk.buyers.bean;

import com.msk.common.bean.RsPageParam;

/**
 * Created by guan_zhongheng on 2016/7/29.
 */
public class IBY121105Param extends RsPageParam {
    private String registerSource;

    private String crtTimeInfo;

    public String getCrtTimeInfo() {
        return crtTimeInfo;
    }

    public void setCrtTimeInfo(String crtTimeInfo) {
        this.crtTimeInfo = crtTimeInfo;
    }

    public String getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }
}
