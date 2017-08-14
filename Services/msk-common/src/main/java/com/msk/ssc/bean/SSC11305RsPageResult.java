package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;
import java.util.List;

/**
 * 发货订单一览分页bean
 *
 * @author Peng_Hao
 */

public class SSC11305RsPageResult extends RsPageResult {

    private List<SSC11305RsBean> SSC11305RsBeanPageResult;

    public List<SSC11305RsBean> getSSC11305RsBeanPageResult() {
        return SSC11305RsBeanPageResult;
    }

    public void setSSC11305RsBeanPageResult(List<SSC11305RsBean> SSC11305RsBeanPageResult) {
        this.SSC11305RsBeanPageResult = SSC11305RsBeanPageResult;
    }
}
