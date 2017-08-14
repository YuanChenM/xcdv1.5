package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

public class IPD1411211RsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**---------------------------------------pd_mct_std-----------------------------------------------*/

    /** 加工技术标准项目ID */
    private String mctStdItemId;

    private String mctStdItemName;
    /** 合格值 */
    private String mctStdVal1;
    /** 不合格值 */
    private String mctStdVal2;

    /**---------------------------------------pd_org_std-----------------------------------------------*/
    /** 产品标准ID */
    private Long standardId;
    /** 饲养标准项目ID  */
    private String orgStdItemId;
    /** 饲养标准项目名称  */
    private String orgStdItemName;
    /** 优良  */
    private String orgGoodVal;
    /** 一般 */
    private String orgNormalVal;
    /** 差 */
    private String orgBadVal;

    /**---------------------------------------pd_gnq_std-----------------------------------------------*/
    private String gnqStdClaId;

    private String gnqStdClaName;

    private List<IPD141125Bean> gnqStdSublist ;

    /**---------------------------------------pd_tsp_std-----------------------------------------------*/
    private String tspStdClaId;

    private String tspStdClaName;

    private List<IPD141126Bean> tspStdSublist ;


    /**---------------------------------------pd_sft_std-----------------------------------------------*/
    private String  sftStdClaId; // 分类质量标准指标id

    private String sftStdClaName; // 分类质量标准指标名称

    private List<IPD141127RsSftStdResult> sftStdSublist; // 具体质量指标列表

    /**---------------------------------------pd_mat-----------------------------------------------*/
    private String scientificName;

    private String localName;

    private String salesName;

    private String placeOrigin;

    private String placeCurrent;

    private String source;

    private String childType;

    private String feedType;

    private String feedPeriod;

    /**---------------------------------------pd_fed_std-----------------------------------------------*/
    private List<PD141147Bean> Resultlist;


    /**---------------------------------------pd_tnc_std-----------------------------------------------*/
    /**
     * 技术标准项目ID
     */
    private String tncStdItemId;

    private String tncStdItemName;
    /**
     * 技术标准项目值1
     */
    private String tncStdVal1;
    /**
     * 技术标准项目值2
     */
    private String tncStdVal2;
    /**
     * 技术标准项目值3
     */
    private String tncStdVal3;

    

    /**
     * Getter method for property <tt>mctStdItemId</tt>.
     *
     * @return property value of mctStdItemId
     */
    public String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * Setter method for property <tt>mctStdItemId</tt>.
     *
     * @param mctStdItemId value to be assigned to property mctStdItemId
     */
    public void setMctStdItemId(String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
    }

    /**
     * Getter method for property <tt>mctStdVal1</tt>.
     *
     * @return property value of mctStdVal1
     */
    public String getMctStdVal1() {
        return mctStdVal1;
    }

    /**
     * Setter method for property <tt>mctStdVal1</tt>.
     *
     * @param mctStdVal1 value to be assigned to property mctStdVal1
     */
    public void setMctStdVal1(String mctStdVal1) {
        this.mctStdVal1 = mctStdVal1;
    }

    /**
     * Getter method for property <tt>mctStdVal2</tt>.
     *
     * @return property value of mctStdVal2
     */
    public String getMctStdVal2() {
        return mctStdVal2;
    }

    /**
     * Setter method for property <tt>mctStdVal2</tt>.
     *
     * @param mctStdVal2 value to be assigned to property mctStdVal2
     */
    public void setMctStdVal2(String mctStdVal2) {
        this.mctStdVal2 = mctStdVal2;
    }

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>orgStdItemId</tt>.
     *
     * @return property value of orgStdItemId
     */
    public String getorgStdItemId() {
        return orgStdItemId;
    }

    /**
     * Setter method for property <tt>orgStdItemId</tt>.
     *
     * @param orgStdItemId value to be assigned to property orgStdItemId
     */
    public void setorgStdItemId(String orgStdItemId) {
        this.orgStdItemId = orgStdItemId;
    }

    /**
     * Getter method for property <tt>orgStdItemName</tt>.
     *
     * @return property value of orgStdItemName
     */
    public String getorgStdItemName() {
        return orgStdItemName;
    }

    /**
     * Setter method for property <tt>orgStdItemName</tt>.
     *
     * @param orgStdItemName value to be assigned to property orgStdItemName
     */
    public void setorgStdItemName(String orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
    }

    /**
     * Getter method for property <tt>orgGoodVal</tt>.
     *
     * @return property value of orgGoodVal
     */
    public String getorgGoodVal() {
        return orgGoodVal;
    }

    /**
     * Setter method for property <tt>orgGoodVal</tt>.
     *
     * @param orgGoodVal value to be assigned to property orgGoodVal
     */
    public void setorgGoodVal(String orgGoodVal) {
        this.orgGoodVal = orgGoodVal;
    }

    /**
     * Getter method for property <tt>orgNormalVal</tt>.
     *
     * @return property value of orgNormalVal
     */
    public String getorgNormalVal() {
        return orgNormalVal;
    }

    /**
     * Setter method for property <tt>orgNormalVal</tt>.
     *
     * @param orgNormalVal value to be assigned to property orgNormalVal
     */
    public void setorgNormalVal(String orgNormalVal) {
        this.orgNormalVal = orgNormalVal;
    }

    /**
     * Getter method for property <tt>orgBadVal</tt>.
     *
     * @return property value of orgBadVal
     */
    public String getorgBadVal() {
        return orgBadVal;
    }


    private String goodVal;

    private String normalVal;

    private String badVal;

    /**
     * Setter method for property <tt>orgBadVal</tt>.
     *
     * @param orgBadVal value to be assigned to property orgBadVal
     */
    public void setorgBadVal(String orgBadVal) {
        this.orgBadVal = orgBadVal;
    }

    public String getGoodVal() {
        return goodVal;
    }

    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    public String getNormalVal() {
        return normalVal;
    }

    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    public String getBadVal() {
        return badVal;
    }

    public void setBadVal(String badVal) {
        this.badVal = badVal;
    }

    public String getGnqStdClaId() {
        return gnqStdClaId;
    }

    public void setGnqStdClaId(String gnqStdClaId) {
        this.gnqStdClaId = gnqStdClaId;
    }

    public String getGnqStdClaName() {
        return gnqStdClaName;
    }

    public void setGnqStdClaName(String gnqStdClaName) {
        this.gnqStdClaName = gnqStdClaName;
    }

    public List<IPD141125Bean> getGnqStdSublist() {
        return gnqStdSublist;
    }

    public void setGnqStdSublist(List<IPD141125Bean> gnqStdSublist) {
        this.gnqStdSublist = gnqStdSublist;
    }

    public String getTspStdClaId() {
        return tspStdClaId;
    }

    public void setTspStdClaId(String tspStdClaId) {
        this.tspStdClaId = tspStdClaId;
    }

    public String getTspStdClaName() {
        return tspStdClaName;
    }

    public void setTspStdClaName(String tspStdClaName) {
        this.tspStdClaName = tspStdClaName;
    }

    public List<IPD141126Bean> getTspStdSublist() {
        return tspStdSublist;
    }

    public void setTspStdSublist(List<IPD141126Bean> tspStdSublist) {
        this.tspStdSublist = tspStdSublist;
    }

    public String getSftStdClaId() {
        return sftStdClaId;
    }

    /**
     * Setter method for property <tt>sftStdClaId</tt>.
     *
     * @param sftStdClaId value to be assigned to property sftStdClaId
     */
    public void setSftStdClaId(String sftStdClaId) {
        this.sftStdClaId = sftStdClaId;
    }

    /**
     * Getter method for property <tt>sftStdClaName</tt>.
     *
     * @return property value of sftStdClaName
     */
    public String getSftStdClaName() {
        return sftStdClaName;
    }

    /**
     * Setter method for property <tt>sftStdClaName</tt>.
     *
     * @param sftStdClaName value to be assigned to property sftStdClaName
     */
    public void setSftStdClaName(String sftStdClaName) {
        this.sftStdClaName = sftStdClaName;
    }

    /**
     * Getter method for property <tt>sftStdSublist</tt>.
     *
     * @return property value of sftStdSublist
     */
    public List<IPD141127RsSftStdResult> getSftStdSublist() {
        return sftStdSublist;
    }

    /**
     * Setter method for property <tt>sftStdSublist</tt>.
     *
     * @param sftStdSublist value to be assigned to property sftStdSublist
     */
    public void setSftStdSublist(List<IPD141127RsSftStdResult> sftStdSublist) {
        this.sftStdSublist = sftStdSublist;
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

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public String getPlaceCurrent() {
        return placeCurrent;
    }

    public void setPlaceCurrent(String placeCurrent) {
        this.placeCurrent = placeCurrent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedPeriod() {
        return feedPeriod;
    }

    public void setFeedPeriod(String feedPeriod) {
        this.feedPeriod = feedPeriod;
    }

    public List<PD141147Bean> getResultlist() {
        return Resultlist;
    }

    public void setResultlist(List<PD141147Bean> resultlist) {
        Resultlist = resultlist;
    }

    /**
     * Getter method for property <tt>tncStdItemId</tt>.
     *
     * @return property value of tncStdItemId
     */
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * Setter method for property <tt>tncStdItemId</tt>.
     *
     * @param tncStdItemId value to be assigned to property tncStdItemId
     */
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * Getter method for property <tt>tncStdItemName</tt>.
     *
     * @return property value of tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * Setter method for property <tt>tncStdItemName</tt>.
     *
     * @param tncStdItemName value to be assigned to property tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * Getter method for property <tt>tncStdVal1</tt>.
     *
     * @return property value of tncStdVal1
     */
    public String getTncStdVal1() {
        return tncStdVal1;
    }

    /**
     * Setter method for property <tt>tncStdVal1</tt>.
     *
     * @param tncStdVal1 value to be assigned to property tncStdVal1
     */
    public void setTncStdVal1(String tncStdVal1) {
        this.tncStdVal1 = tncStdVal1;
    }

    /**
     * Getter method for property <tt>tncStdVal2</tt>.
     *
     * @return property value of tncStdVal2
     */
    public String getTncStdVal2() {
        return tncStdVal2;
    }

    /**
     * Setter method for property <tt>tncStdVal2</tt>.
     *
     * @param tncStdVal2 value to be assigned to property tncStdVal2
     */
    public void setTncStdVal2(String tncStdVal2) {
        this.tncStdVal2 = tncStdVal2;
    }

    /**
     * Getter method for property <tt>tncStdVal3</tt>.
     *
     * @return property value of tncStdVal3
     */
    public String getTncStdVal3() {
        return tncStdVal3;
    }

    /**
     * Setter method for property <tt>tncStdVal3</tt>.
     *
     * @param tncStdVal3 value to be assigned to property tncStdVal3
     */
    public void setTncStdVal3(String tncStdVal3) {
        this.tncStdVal3 = tncStdVal3;
    }
}