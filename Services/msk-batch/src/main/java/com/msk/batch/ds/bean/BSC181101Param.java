package com.msk.batch.ds.bean;

import com.hoperun.core.bean.BaseParam;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class BSC181101Param extends BaseParam implements Cloneable{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BSC181101Param.class);

    public static final long serialVersionUID = 1L;
    /**接收的json字符串 */
    public JSONObject jsonData;
    /** 分销月度:201602 */
    private String distMonth;
    /** 销往地的物流区编号，2位大区编号 */
    private String lgcsCode;
    /** 供应商编号 */
    private String suppCode;
    /** 产品编码 */
    private String pdCode;
    /** 品牌阵营（1:神农先生 2:神农客 3:神农人家） */
    private Integer brandType;
    /** 分销通道,1:超级大宗订单 2:大宗订单 3:大额订单 4:标准订单 */
    private Integer distType;
    /** KG数量 */
    private BigDecimal distNum;

    //分销月度
    private String demandMonth;

    /**
     * Get the jsonData.
     *
     * @return jsonData
     */
    public JSONObject getJsonData() {
        return this.jsonData;
    }

    /**
     * Set the jsonData.
     *
     * @param jsonData jsonData
     */
    public void setJsonData(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public Integer getBrandType() {
        return brandType;
    }

    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
    }

    public Integer getDistType() {
        return distType;
    }

    public void setDistType(Integer distType) {
        this.distType = distType;
    }

    public BigDecimal getDistNum() {
        return distNum;
    }

    public void setDistNum(BigDecimal distNum) {
        this.distNum = distNum;
    }

    public String getDemandMonth() {
        return demandMonth;
    }

    public void setDemandMonth(String demandMonth) {
        this.demandMonth = demandMonth;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
