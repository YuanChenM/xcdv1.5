package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/7/8.
 */
public class SSC11316Result extends RsPageResult {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 预入库通知单详情的集合 */
    private List<SSC11316Bean> sscDeliveryPreIntos;

    public List<SSC11316Bean> getSscDeliveryPreIntos() {
        return sscDeliveryPreIntos;
    }

    public void setSscDeliveryPreIntos(List<SSC11316Bean> sscDeliveryPreIntos) {
        this.sscDeliveryPreIntos = sscDeliveryPreIntos;
    }

}
