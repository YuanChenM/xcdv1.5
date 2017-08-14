package com.msk.buyers.bean;


import java.util.List;
import java.util.Map;

/**
 * BY121311Bean.
 *
 */
public class BY121311Bean
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**买家分类*/
    private String superiorName;
    /**物流区*/
    private String lgcsAreaCode;
    /**地区*/
    private String cityCode;
    /**买家分类*/
    private String districtCode;
    /**批发市场*/
    private String terminalName;
    /**菜场*/
    private String foodName;
    /**批发市场顺序码*/
    private String cityMarketSeq;
    /**菜场顺序码*/
    private String districtMarketSeq;
    /**买家顺序码*/
    private String buyerSeq;
    /**上线状态码*/
    private String saleStatus;
    /**产品分类*/
    private Map<String,String> pdName;
    /**校正码*/
    private String identifyCode;

    public BY121311Bean(){

    }

    public String getSuperiorName()
    {
        return superiorName;
    }

    public void setSuperiorName(String superiorName)
    {
        this.superiorName = superiorName;
    }

    public String getLgcsAreaCode()
    {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode)
    {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getCityCode()
    {
        return cityCode;
    }

    public void setCityCode(String cityCode)
    {
        this.cityCode = cityCode;
    }

    public String getDistrictCode()
    {
        return districtCode;
    }

    public void setDistrictCode(String districtCode)
    {
        this.districtCode = districtCode;
    }

    public String getCityMarketSeq()
    {
        return cityMarketSeq;
    }

    public void setCityMarketSeq(String cityMarketSeq)
    {
        this.cityMarketSeq = cityMarketSeq;
    }

    public String getDistrictMarketSeq()
    {
        return districtMarketSeq;
    }

    public void setDistrictMarketSeq(String districtMarketSeq)
    {
        this.districtMarketSeq = districtMarketSeq;
    }

    public String getBuyerSeq()
    {
        return buyerSeq;
    }

    public void setBuyerSeq(String buyerSeq)
    {
        this.buyerSeq = buyerSeq;
    }

    public String getSaleStatus()
    {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus)
    {
        this.saleStatus = saleStatus;
    }

    public String getIdentifyCode()
    {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode)
    {
        this.identifyCode = identifyCode;
    }

    public Map<String, String> getPdName()
    {
        return pdName;
    }

    public void setPdName(Map<String, String> pdName)
    {
        this.pdName = pdName;
    }

    public String getTerminalName()
    {
        return terminalName;
    }

    public void setTerminalName(String terminalName)
    {
        this.terminalName = terminalName;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public void setFoodName(String foodName)
    {
        this.foodName = foodName;
    }
}
