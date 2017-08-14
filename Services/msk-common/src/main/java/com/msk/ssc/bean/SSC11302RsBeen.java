package com.msk.ssc.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SscBidProductDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 *  param
 * <p/>
 * Created by zhao_chen on 2016/06/30.
 */
public class SSC11302RsBeen extends SscBidProductDetail {

    private String pdName;

    private String salesName;

    private String scientificName;

    private String localName;

    /** 发货箱数合计 */
    private BigDecimal sumProductBox;
    /** 发货数量合计 */
    private BigDecimal sumProductQua;
    /** 货值合计 */
    private BigDecimal sumProductValue;


    private List<SSC11302RsBeen> detailList;

    public List<SSC11302RsBeen> getDetailList() {
        return detailList;
    }

    public BigDecimal getSumProductBox() {
        return sumProductBox;
    }

    public void setSumProductBox(BigDecimal sumProductBox) {
        this.sumProductBox = sumProductBox;
    }

    public BigDecimal getSumProductQua() {
        return sumProductQua;
    }

    public void setSumProductQua(BigDecimal sumProductQua) {
        this.sumProductQua = sumProductQua;
    }

    public BigDecimal getSumProductValue() {
        return sumProductValue;
    }

    public void setSumProductValue(BigDecimal sumProductValue) {
        this.sumProductValue = sumProductValue;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public void setDetailList(List<SSC11302RsBeen> detailList) {
        this.detailList = detailList;
    }
}
