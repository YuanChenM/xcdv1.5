package com.msk.br.bean;

import com.msk.common.base.BaseBean;
import com.msk.district.bean.LgcsAreaBean;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/7/26.
 */
public class BR121406RsBean extends BaseBean{
    private String lgcsAreaCode;
    private String lgcsAreaName;
    private String cityCode;
    private String cityName;
    private String machiningName;
    private String machiningCode;
    private String fileId;
    private String classesCode;
    private String classesName;
    private List<LgcsAreaBean> lgcsAreaList;
    private List<BR121406RsBean> pageResult;
    private String fileStatus ;
    private String fileName;
    private String PdMachining[];
    private String productClassification;
    private String salesName;
    private String scientificName;
    private String localName;
    private String featureName;
    private String gradeName;
    private String pdMarketName;
    private String orderAmount;
    private String rankAmounts;
    private String orderQty;
    private String rankQtys;
    private String receiveQty;
    private String returnQty;
    private String pdPrice;
    //判断成功条件
    private String  dataCount;
    //判断文件状态
    private String fileStatusFlag;

    public String getDataCount() {
        return dataCount;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setDataCount(String dataCount) {
        this.dataCount = dataCount;
    }

    public String getFileStatusFlag() {
        return fileStatusFlag;
    }

    public void setFileStatusFlag(String fileStatusFlag) {
        this.fileStatusFlag = fileStatusFlag;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getProductClassification() {
        return productClassification;
    }

    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
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

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPdMarketName() {
        return pdMarketName;
    }

    public void setPdMarketName(String pdMarketName) {
        this.pdMarketName = pdMarketName;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getRankAmounts() {
        return rankAmounts;
    }

    public void setRankAmounts(String rankAmounts) {
        this.rankAmounts = rankAmounts;
    }

    public String getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }

    public String getRankQtys() {
        return rankQtys;
    }

    public void setRankQtys(String rankQtys) {
        this.rankQtys = rankQtys;
    }

    public String[] getPdMachining() {
        return PdMachining;
    }

    public void setPdMachining(String[] pdMachining) {
        PdMachining = pdMachining;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public List<BR121406RsBean> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List<BR121406RsBean> pageResult) {
        this.pageResult = pageResult;
    }

    public List<LgcsAreaBean> getLgcsAreaList() {
        return lgcsAreaList;
    }

    public void setLgcsAreaList(List<LgcsAreaBean> lgcsAreaList) {
        this.lgcsAreaList = lgcsAreaList;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(String receiveQty) {
        this.receiveQty = receiveQty;
    }

    public String getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(String returnQty) {
        this.returnQty = returnQty;
    }

    public String getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(String pdPrice) {
        this.pdPrice = pdPrice;
    }
}
