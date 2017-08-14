package com.msk.batch.bs.bean;

import com.hoperun.core.bean.BaseParam;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;

public class BBS1101101Bean{
    private String houseCode;
    private String saleMonth;
    private String lgcsAreaCode;
    private String pdClassCode;
    private String sales;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getSaleMonth() {
        return saleMonth;
    }

    public void setSaleMonth(String saleMonth) {
        this.saleMonth = saleMonth;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getPdClassCode() {
        return pdClassCode;
    }

    public void setPdClassCode(String pdClassCode) {
        this.pdClassCode = pdClassCode;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
}
