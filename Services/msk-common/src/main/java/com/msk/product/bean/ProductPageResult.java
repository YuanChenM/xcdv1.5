package com.msk.product.bean;

import com.hoperun.core.utils.StringUtil;
import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/5/10.
 */
@ApiModel(value = "ProductPageResult", description = "result")
public class ProductPageResult extends RsPageResult implements Serializable{

    @ApiModelProperty(value = "pdInfo")
    private List<PDInfoResult> pdInfo = new ArrayList<PDInfoResult>();

    public List<PDInfoResult> getPdInfo() {
        return pdInfo;
    }

    public void setPdInfo(List<PDInfoResult> pdInfo) {
        this.pdInfo = pdInfo;
    }
}
