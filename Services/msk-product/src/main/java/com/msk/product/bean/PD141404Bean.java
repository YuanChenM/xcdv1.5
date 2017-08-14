package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by Administrator on 2016/4/12. xhy
 */
public class PD141404Bean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String onlineId;

    private String slPdId;

    private String slCodeDis;

    private String slId;

    private String standardId;

    private String showDate;

    private String resultGrade;

    private String pdLot;

    private String classestreeCode;

    private String resultInfo;


    private String showTable;
    //产品原种档案卡数据
    private String[] orgCheckBox;

    private String[] orgStdItemId;

    private String[] orgResultInfo;

    private String[] orgStdItemName;

    //产品饲养标准
    private String[] fedCheckBox;

    private String[] fedStdItemId;

    private String[] fedResultInfo;

    private String[] fedStdItemName;

    //产品加工技术标准
    private String[] mctCheckBox;

    private String[] mctStdItemId;

    private String[] mctResultInfo;

    private String[] mctStdItemName;

    //产品加工质量技术标准
    private String[] tncCheckBox;

    private String[] tncStdItemId;

    private String[] tncResultInfo;

    private String[] tncStdItemName;

    //产品加工质量技术标准
    private String[] gnqCheckBox;

    private String[] gnqStdItemId;

    private String[] gnqResultInfo;

    private String[] gnqStdItemName;

    //产品安全标准
    private String[] sftCheckBox;

    private String[] sftStdItemId;

    private String[] sftResultInfo;

    private String[] sftStdItemName;

    //产品安全标准
    private String[] tspCheckBox;

    private String[] tspStdItemId;

    private String[] tspResultInfo;

    private String[] tspStdItemName;

    //包装技术标准数据

    private String resultInnerweightFlg;

    private String resultInnererrorFlg;

    private String resultInnercountFlg;

    private String resultInnersizeFlg;

    private String resultInnermatFlg;

    private String resultOutspecFlg;

    private String resultOutweightFlg;

    private String resultOutsizeFlg;

    private String resultOutmatFlg;

    private String normsCode;

    private String normsName;

    private String normsCodeOld;


    /**
     * Getter method for property <tt>showTable</tt>.
     *
     * @return property value of showTable
     */
    public String getShowTable() {
        return showTable;
    }

    /**
     * Setter method for property <tt>showTable</tt>.
     *
     * @param showTable value to be assigned to property showTable
     */
    public void setShowTable(String showTable) {
        this.showTable = showTable;
    }

    /**
     * Getter method for property <tt>classestreeCode</tt>.
     *
     * @return property value of classestreeCode
     */
    public String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * Setter method for property <tt>classestreeCode</tt>.
     *
     * @param classestreeCode value to be assigned to property classestreeCode
     */
    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

    /**
     * Getter method for property <tt>normsCodeOld</tt>.
     *
     * @return property value of normsCodeOld
     */
    public String getNormsCodeOld() {
        return normsCodeOld;
    }

    /**
     * Setter method for property <tt>normsCodeOld</tt>.
     *
     * @param normsCodeOld value to be assigned to property normsCodeOld
     */
    public void setNormsCodeOld(String normsCodeOld) {
        this.normsCodeOld = normsCodeOld;
    }

    /**
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>normsName</tt>.
     *
     * @return property value of normsName
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * Setter method for property <tt>normsName</tt>.
     *
     * @param normsName value to be assigned to property normsName
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * Getter method for property <tt>resultInnerweightFlg</tt>.
     *
     * @return property value of resultInnerweightFlg
     */
    public String getResultInnerweightFlg() {
        return resultInnerweightFlg;
    }

    /**
     * Setter method for property <tt>resultInnerweightFlg</tt>.
     *
     * @param resultInnerweightFlg value to be assigned to property resultInnerweightFlg
     */
    public void setResultInnerweightFlg(String resultInnerweightFlg) {
        this.resultInnerweightFlg = resultInnerweightFlg;
    }

    /**
     * Getter method for property <tt>resultInnererrorFlg</tt>.
     *
     * @return property value of resultInnererrorFlg
     */
    public String getResultInnererrorFlg() {
        return resultInnererrorFlg;
    }

    /**
     * Setter method for property <tt>resultInnererrorFlg</tt>.
     *
     * @param resultInnererrorFlg value to be assigned to property resultInnererrorFlg
     */
    public void setResultInnererrorFlg(String resultInnererrorFlg) {
        this.resultInnererrorFlg = resultInnererrorFlg;
    }

    /**
     * Getter method for property <tt>resultInnercountFlg</tt>.
     *
     * @return property value of resultInnercountFlg
     */
    public String getResultInnercountFlg() {
        return resultInnercountFlg;
    }

    /**
     * Setter method for property <tt>resultInnercountFlg</tt>.
     *
     * @param resultInnercountFlg value to be assigned to property resultInnercountFlg
     */
    public void setResultInnercountFlg(String resultInnercountFlg) {
        this.resultInnercountFlg = resultInnercountFlg;
    }

    /**
     * Getter method for property <tt>resultInnersizeFlg</tt>.
     *
     * @return property value of resultInnersizeFlg
     */
    public String getResultInnersizeFlg() {
        return resultInnersizeFlg;
    }

    /**
     * Setter method for property <tt>resultInnersizeFlg</tt>.
     *
     * @param resultInnersizeFlg value to be assigned to property resultInnersizeFlg
     */
    public void setResultInnersizeFlg(String resultInnersizeFlg) {
        this.resultInnersizeFlg = resultInnersizeFlg;
    }

    /**
     * Getter method for property <tt>resultInnermatFlg</tt>.
     *
     * @return property value of resultInnermatFlg
     */
    public String getResultInnermatFlg() {
        return resultInnermatFlg;
    }

    /**
     * Setter method for property <tt>resultInnermatFlg</tt>.
     *
     * @param resultInnermatFlg value to be assigned to property resultInnermatFlg
     */
    public void setResultInnermatFlg(String resultInnermatFlg) {
        this.resultInnermatFlg = resultInnermatFlg;
    }

    /**
     * Getter method for property <tt>resultOutspecFlg</tt>.
     *
     * @return property value of resultOutspecFlg
     */
    public String getResultOutspecFlg() {
        return resultOutspecFlg;
    }

    /**
     * Setter method for property <tt>resultOutspecFlg</tt>.
     *
     * @param resultOutspecFlg value to be assigned to property resultOutspecFlg
     */
    public void setResultOutspecFlg(String resultOutspecFlg) {
        this.resultOutspecFlg = resultOutspecFlg;
    }

    /**
     * Getter method for property <tt>resultOutweightFlg</tt>.
     *
     * @return property value of resultOutweightFlg
     */
    public String getResultOutweightFlg() {
        return resultOutweightFlg;
    }

    /**
     * Setter method for property <tt>resultOutweightFlg</tt>.
     *
     * @param resultOutweightFlg value to be assigned to property resultOutweightFlg
     */
    public void setResultOutweightFlg(String resultOutweightFlg) {
        this.resultOutweightFlg = resultOutweightFlg;
    }

    /**
     * Getter method for property <tt>resultOutsizeFlg</tt>.
     *
     * @return property value of resultOutsizeFlg
     */
    public String getResultOutsizeFlg() {
        return resultOutsizeFlg;
    }

    /**
     * Setter method for property <tt>resultOutsizeFlg</tt>.
     *
     * @param resultOutsizeFlg value to be assigned to property resultOutsizeFlg
     */
    public void setResultOutsizeFlg(String resultOutsizeFlg) {
        this.resultOutsizeFlg = resultOutsizeFlg;
    }

    /**
     * Getter method for property <tt>resultOutmatFlg</tt>.
     *
     * @return property value of resultOutmatFlg
     */
    public String getResultOutmatFlg() {
        return resultOutmatFlg;
    }

    /**
     * Setter method for property <tt>resultOutmatFlg</tt>.
     *
     * @param resultOutmatFlg value to be assigned to property resultOutmatFlg
     */
    public void setResultOutmatFlg(String resultOutmatFlg) {
        this.resultOutmatFlg = resultOutmatFlg;
    }

    /**
     * Getter method for property <tt>pdLot</tt>.
     *
     * @return property value of pdLot
     */
    public String getPdLot() {
        return pdLot;
    }

    /**
     * Setter method for property <tt>pdLot</tt>.
     *
     * @param pdLot value to be assigned to property pdLot
     */
    public void setPdLot(String pdLot) {
        this.pdLot = pdLot;
    }

    /**
     * Getter method for property <tt>resultInfo</tt>.
     *
     * @return property value of resultInfo
     */
    public String getResultInfo() {
        return resultInfo;
    }

    /**
     * Setter method for property <tt>resultInfo</tt>.
     *
     * @param resultInfo value to be assigned to property resultInfo
     */
    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    /**
     * Getter method for property <tt>resultGrade</tt>.
     *
     * @return property value of resultGrade
     */
    public String getResultGrade() {
        return resultGrade;
    }

    /**
     * Setter method for property <tt>resultGrade</tt>.
     *
     * @param resultGrade value to be assigned to property resultGrade
     */
    public void setResultGrade(String resultGrade) {
        this.resultGrade = resultGrade;
    }

    /**
     * Getter method for property <tt>onlineId</tt>.
     *
     * @return property value of onlineId
     */
    public String getOnlineId() {
        return onlineId;
    }

    /**
     * Setter method for property <tt>onlineId</tt>.
     *
     * @param onlineId value to be assigned to property onlineId
     */
    public void setOnlineId(String onlineId) {
        this.onlineId = onlineId;
    }

    /**
     * Getter method for property <tt>tspCheckBox</tt>.
     *
     * @return property value of tspCheckBox
     */
    public String[] getTspCheckBox() {
        return tspCheckBox;
    }

    /**
     * Setter method for property <tt>tspCheckBox</tt>.
     *
     * @param tspCheckBox value to be assigned to property tspCheckBox
     */
    public void setTspCheckBox(String[] tspCheckBox) {
        this.tspCheckBox = tspCheckBox;
    }

    /**
     * Getter method for property <tt>tspStdItemId</tt>.
     *
     * @return property value of tspStdItemId
     */
    public String[] getTspStdItemId() {
        return tspStdItemId;
    }

    /**
     * Setter method for property <tt>tspStdItemId</tt>.
     *
     * @param tspStdItemId value to be assigned to property tspStdItemId
     */
    public void setTspStdItemId(String[] tspStdItemId) {
        this.tspStdItemId = tspStdItemId;
    }

    /**
     * Getter method for property <tt>tspResultInfo</tt>.
     *
     * @return property value of tspResultInfo
     */
    public String[] getTspResultInfo() {
        return tspResultInfo;
    }

    /**
     * Setter method for property <tt>tspResultInfo</tt>.
     *
     * @param tspResultInfo value to be assigned to property tspResultInfo
     */
    public void setTspResultInfo(String[] tspResultInfo) {
        this.tspResultInfo = tspResultInfo;
    }

    /**
     * Getter method for property <tt>tspStdItemName</tt>.
     *
     * @return property value of tspStdItemName
     */
    public String[] getTspStdItemName() {
        return tspStdItemName;
    }

    /**
     * Setter method for property <tt>tspStdItemName</tt>.
     *
     * @param tspStdItemName value to be assigned to property tspStdItemName
     */
    public void setTspStdItemName(String[] tspStdItemName) {
        this.tspStdItemName = tspStdItemName;
    }

    /**
     * Getter method for property <tt>sftCheckBox</tt>.
     *
     * @return property value of sftCheckBox
     */
    public String[] getSftCheckBox() {
        return sftCheckBox;
    }

    /**
     * Setter method for property <tt>sftCheckBox</tt>.
     *
     * @param sftCheckBox value to be assigned to property sftCheckBox
     */
    public void setSftCheckBox(String[] sftCheckBox) {
        this.sftCheckBox = sftCheckBox;
    }

    /**
     * Getter method for property <tt>sftStdItemId</tt>.
     *
     * @return property value of sftStdItemId
     */
    public String[] getSftStdItemId() {
        return sftStdItemId;
    }

    /**
     * Setter method for property <tt>sftStdItemId</tt>.
     *
     * @param sftStdItemId value to be assigned to property sftStdItemId
     */
    public void setSftStdItemId(String[] sftStdItemId) {
        this.sftStdItemId = sftStdItemId;
    }

    /**
     * Getter method for property <tt>sftResultInfo</tt>.
     *
     * @return property value of sftResultInfo
     */
    public String[] getSftResultInfo() {
        return sftResultInfo;
    }

    /**
     * Setter method for property <tt>sftResultInfo</tt>.
     *
     * @param sftResultInfo value to be assigned to property sftResultInfo
     */
    public void setSftResultInfo(String[] sftResultInfo) {
        this.sftResultInfo = sftResultInfo;
    }

    /**
     * Getter method for property <tt>sftStdItemName</tt>.
     *
     * @return property value of sftStdItemName
     */
    public String[] getSftStdItemName() {
        return sftStdItemName;
    }

    /**
     * Setter method for property <tt>sftStdItemName</tt>.
     *
     * @param sftStdItemName value to be assigned to property sftStdItemName
     */
    public void setSftStdItemName(String[] sftStdItemName) {
        this.sftStdItemName = sftStdItemName;
    }

    /**
     * Getter method for property <tt>gnqCheckBox</tt>.
     *
     * @return property value of gnqCheckBox
     */
    public String[] getGnqCheckBox() {
        return gnqCheckBox;
    }

    /**
     * Setter method for property <tt>gnqCheckBox</tt>.
     *
     * @param gnqCheckBox value to be assigned to property gnqCheckBox
     */
    public void setGnqCheckBox(String[] gnqCheckBox) {
        this.gnqCheckBox = gnqCheckBox;
    }

    /**
     * Getter method for property <tt>gnqStdItemId</tt>.
     *
     * @return property value of gnqStdItemId
     */
    public String[] getGnqStdItemId() {
        return gnqStdItemId;
    }

    /**
     * Setter method for property <tt>gnqStdItemId</tt>.
     *
     * @param gnqStdItemId value to be assigned to property gnqStdItemId
     */
    public void setGnqStdItemId(String[] gnqStdItemId) {
        this.gnqStdItemId = gnqStdItemId;
    }

    /**
     * Getter method for property <tt>gnqResultInfo</tt>.
     *
     * @return property value of gnqResultInfo
     */
    public String[] getGnqResultInfo() {
        return gnqResultInfo;
    }

    /**
     * Setter method for property <tt>gnqResultInfo</tt>.
     *
     * @param gnqResultInfo value to be assigned to property gnqResultInfo
     */
    public void setGnqResultInfo(String[] gnqResultInfo) {
        this.gnqResultInfo = gnqResultInfo;
    }

    /**
     * Getter method for property <tt>gnqStdItemName</tt>.
     *
     * @return property value of gnqStdItemName
     */
    public String[] getGnqStdItemName() {
        return gnqStdItemName;
    }

    /**
     * Setter method for property <tt>gnqStdItemName</tt>.
     *
     * @param gnqStdItemName value to be assigned to property gnqStdItemName
     */
    public void setGnqStdItemName(String[] gnqStdItemName) {
        this.gnqStdItemName = gnqStdItemName;
    }

    /**
     * Getter method for property <tt>tncCheckBox</tt>.
     *
     * @return property value of tncCheckBox
     */
    public String[] getTncCheckBox() {
        return tncCheckBox;
    }

    /**
     * Setter method for property <tt>tncCheckBox</tt>.
     *
     * @param tncCheckBox value to be assigned to property tncCheckBox
     */
    public void setTncCheckBox(String[] tncCheckBox) {
        this.tncCheckBox = tncCheckBox;
    }

    /**
     * Getter method for property <tt>tncStdItemId</tt>.
     *
     * @return property value of tncStdItemId
     */
    public String[] getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * Setter method for property <tt>tncStdItemId</tt>.
     *
     * @param tncStdItemId value to be assigned to property tncStdItemId
     */
    public void setTncStdItemId(String[] tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * Getter method for property <tt>tncResultInfo</tt>.
     *
     * @return property value of tncResultInfo
     */
    public String[] getTncResultInfo() {
        return tncResultInfo;
    }

    /**
     * Setter method for property <tt>tncResultInfo</tt>.
     *
     * @param tncResultInfo value to be assigned to property tncResultInfo
     */
    public void setTncResultInfo(String[] tncResultInfo) {
        this.tncResultInfo = tncResultInfo;
    }

    /**
     * Getter method for property <tt>tncStdItemName</tt>.
     *
     * @return property value of tncStdItemName
     */
    public String[] getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * Setter method for property <tt>tncStdItemName</tt>.
     *
     * @param tncStdItemName value to be assigned to property tncStdItemName
     */
    public void setTncStdItemName(String[] tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
    }

    /**
     * Getter method for property <tt>mctCheckBox</tt>.
     *
     * @return property value of mctCheckBox
     */
    public String[] getMctCheckBox() {
        return mctCheckBox;
    }

    /**
     * Setter method for property <tt>mctCheckBox</tt>.
     *
     * @param mctCheckBox value to be assigned to property mctCheckBox
     */
    public void setMctCheckBox(String[] mctCheckBox) {
        this.mctCheckBox = mctCheckBox;
    }

    /**
     * Getter method for property <tt>mctStdItemId</tt>.
     *
     * @return property value of mctStdItemId
     */
    public String[] getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * Setter method for property <tt>mctStdItemId</tt>.
     *
     * @param mctStdItemId value to be assigned to property mctStdItemId
     */
    public void setMctStdItemId(String[] mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * Getter method for property <tt>mctResultInfo</tt>.
     *
     * @return property value of mctResultInfo
     */
    public String[] getMctResultInfo() {
        return mctResultInfo;
    }

    /**
     * Setter method for property <tt>mctResultInfo</tt>.
     *
     * @param mctResultInfo value to be assigned to property mctResultInfo
     */
    public void setMctResultInfo(String[] mctResultInfo) {
        this.mctResultInfo = mctResultInfo;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String[] getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String[] mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
    }

    /**
     * Getter method for property <tt>fedCheckBox</tt>.
     *
     * @return property value of fedCheckBox
     */
    public String[] getFedCheckBox() {
        return fedCheckBox;
    }

    /**
     * Setter method for property <tt>fedCheckBox</tt>.
     *
     * @param fedCheckBox value to be assigned to property fedCheckBox
     */
    public void setFedCheckBox(String[] fedCheckBox) {
        this.fedCheckBox = fedCheckBox;
    }

    /**
     * Getter method for property <tt>fedStdItemId</tt>.
     *
     * @return property value of fedStdItemId
     */
    public String[] getFedStdItemId() {
        return fedStdItemId;
    }

    /**
     * Setter method for property <tt>fedStdItemId</tt>.
     *
     * @param fedStdItemId value to be assigned to property fedStdItemId
     */
    public void setFedStdItemId(String[] fedStdItemId) {
        this.fedStdItemId = fedStdItemId;
    }

    /**
     * Getter method for property <tt>fedResultInfo</tt>.
     *
     * @return property value of fedResultInfo
     */
    public String[] getFedResultInfo() {
        return fedResultInfo;
    }

    /**
     * Setter method for property <tt>fedResultInfo</tt>.
     *
     * @param fedResultInfo value to be assigned to property fedResultInfo
     */
    public void setFedResultInfo(String[] fedResultInfo) {
        this.fedResultInfo = fedResultInfo;
    }

    /**
     * Getter method for property <tt>fedStdItemName</tt>.
     *
     * @return property value of fedStdItemName
     */
    public String[] getFedStdItemName() {
        return fedStdItemName;
    }

    /**
     * Setter method for property <tt>fedStdItemName</tt>.
     *
     * @param fedStdItemName value to be assigned to property fedStdItemName
     */
    public void setFedStdItemName(String[] fedStdItemName) {
        this.fedStdItemName = fedStdItemName;
    }

    /**
     * Getter method for property <tt>showDate</tt>.
     *
     * @return property value of showDate
     */
    public String getShowDate() {
        return showDate;
    }

    /**
     * Setter method for property <tt>showDate</tt>.
     *
     * @param showDate value to be assigned to property showDate
     */
    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    /**
     * Getter method for property <tt>slPdId</tt>.
     *
     * @return property value of slPdId
     */
    public String getSlPdId() {
        return slPdId;
    }

    /**
     * Setter method for property <tt>slPdId</tt>.
     *
     * @param slPdId value to be assigned to property slPdId
     */
    public void setSlPdId(String slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * Getter method for property <tt>slCodeDis</tt>.
     *
     * @return property value of slCodeDis
     */
    public String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * Setter method for property <tt>slCodeDis</tt>.
     *
     * @param slCodeDis value to be assigned to property slCodeDis
     */
    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * Getter method for property <tt>slId</tt>.
     *
     * @return property value of slId
     */
    public String getSlId() {
        return slId;
    }

    /**
     * Setter method for property <tt>slId</tt>.
     *
     * @param slId value to be assigned to property slId
     */
    public void setSlId(String slId) {
        this.slId = slId;
    }

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public String getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>orgStdItemName</tt>.
     *
     * @return property value of orgStdItemName
     */
    public String[] getOrgStdItemName() {
        return orgStdItemName;
    }

    /**
     * Setter method for property <tt>orgStdItemName</tt>.
     *
     * @param orgStdItemName value to be assigned to property orgStdItemName
     */
    public void setOrgStdItemName(String[] orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
    }

    /**
     * Getter method for property <tt>orgCheckBox</tt>.
     *
     * @return property value of orgCheckBox
     */
    public String[] getOrgCheckBox() {
        return orgCheckBox;
    }

    /**
     * Setter method for property <tt>orgCheckBox</tt>.
     *
     * @param orgCheckBox value to be assigned to property orgCheckBox
     */
    public void setOrgCheckBox(String[] orgCheckBox) {
        this.orgCheckBox = orgCheckBox;
    }

    /**
     * Getter method for property <tt>orgStdItemId</tt>.
     *
     * @return property value of orgStdItemId
     */
    public String[] getOrgStdItemId() {
        return orgStdItemId;
    }

    /**
     * Setter method for property <tt>orgStdItemId</tt>.
     *
     * @param orgStdItemId value to be assigned to property orgStdItemId
     */
    public void setOrgStdItemId(String[] orgStdItemId) {
        this.orgStdItemId = orgStdItemId;
    }

    /**
     * Getter method for property <tt>orgResultInfo</tt>.
     *
     * @return property value of orgResultInfo
     */
    public String[] getOrgResultInfo() {
        return orgResultInfo;
    }

    /**
     * Setter method for property <tt>orgResultInfo</tt>.
     *
     * @param orgResultInfo value to be assigned to property orgResultInfo
     */
    public void setOrgResultInfo(String[] orgResultInfo) {
        this.orgResultInfo = orgResultInfo;
    }
}
