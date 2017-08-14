package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/12.
 */
@ApiModel(value = "SSC11303RsPageResult", description = "合同接口的返回值")
public class SSC11303RsPageResult extends RsPageResult {
    @ApiModelProperty(value = "合同")
    private List<SSC11303RsBean> ssc11303RsBeans;

    public List<SSC11303RsBean> getSsc11303RsBeans() {
        return ssc11303RsBeans;
    }

    public void setSsc11303RsBeans(List<SSC11303RsBean> ssc11303RsBeans) {
        this.ssc11303RsBeans = ssc11303RsBeans;
    }

}