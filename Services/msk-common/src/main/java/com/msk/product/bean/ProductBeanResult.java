package com.msk.product.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.core.entity.PdNormsStd;
import com.msk.core.entity.PdPriceprdLogiarea;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang_chunyan on 2016/5/9.
 */
@ApiModel(value = "ProductBeanResult", description = "result")
public class ProductBeanResult extends RsPageResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "result")
    private List<PDInfoResult> result;

    @ApiModelProperty(value = "productList")
    private List<StdResult> productList;

    @ApiModelProperty(value = "treeMatList")
    private List<PdClassestreeMat> treeMatList;

    @ApiModelProperty(value = "pdInfo")
    private Map<String,List<PDInfoResult>> pdInfo;

    @ApiModelProperty(value = "pdNormsStds")
    private List<PdNormsStd> pdNormsStds;

    @ApiModelProperty(value = "logiares")
    private List<PdPriceprdLogiarea> logiares;

    public List<PDInfoResult> getResult() {
        return result;
    }

    public void setResult(List<PDInfoResult> result) {
        this.result = result;
    }

    public List<StdResult> getProductList() {
        return productList;
    }

    public void setProductList(List<StdResult> productList) {
        this.productList = productList;
    }

    public Map<String, List<PDInfoResult>> getPdInfo() {
        return pdInfo;
    }

    public void setPdInfo(Map<String, List<PDInfoResult>> pdInfo) {
        this.pdInfo = pdInfo;
    }

    public List<PdNormsStd> getPdNormsStds() {
        return pdNormsStds;
    }

    public void setPdNormsStds(List<PdNormsStd> pdNormsStds) {
        this.pdNormsStds = pdNormsStds;
    }

    public List<PdPriceprdLogiarea> getLogiares() {
        return logiares;
    }

    public void setLogiares(List<PdPriceprdLogiarea> logiares) {
        this.logiares = logiares;
    }

    public List<PdClassestreeMat> getTreeMatList() {
        return treeMatList;
    }

    public void setTreeMatList(List<PdClassestreeMat> treeMatList) {
        this.treeMatList = treeMatList;
    }
}
