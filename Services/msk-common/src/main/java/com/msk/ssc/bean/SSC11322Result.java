package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/9.
 */
@ApiModel(value = "SSC11322Result", description = "核销单明细接口的返回对象")
public class SSC11322Result extends RsPageResult {
    @ApiModelProperty(value = "差异单")
    private List<SSC11311Bean> sscDifferBasics;

    @ApiModelProperty(value = "核销单")
    private List<SSC11322Bean> verificationDetails;

    @ApiModelProperty(value = "货值")
    private List<SSC11322ProductValueBean> productValues;

    @ApiModelProperty(value = "运费")
    private List<SSC11322TransportCostBean> transportCosts;


    public List<SSC11311Bean> getSscDifferBasics() {
        return sscDifferBasics;
    }

    public void setSscDifferBasics(List<SSC11311Bean> sscDifferBasics) {
        this.sscDifferBasics = sscDifferBasics;
    }

    public List<SSC11322Bean> getVerificationDetails() {
        return verificationDetails;
    }

    public void setVerificationDetails(List<SSC11322Bean> verificationDetails) {
        this.verificationDetails = verificationDetails;
    }

    public List<SSC11322ProductValueBean> getProductValues() {
        return productValues;
    }

    public void setProductValues(List<SSC11322ProductValueBean> productValues) {
        this.productValues = productValues;
    }

    public List<SSC11322TransportCostBean> getTransportCosts() {
        return transportCosts;
    }

    public void setTransportCosts(List<SSC11322TransportCostBean> transportCosts) {
        this.transportCosts = transportCosts;
    }

}
