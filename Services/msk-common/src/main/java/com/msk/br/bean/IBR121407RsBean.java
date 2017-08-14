package com.msk.br.bean;

import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOClaMachiningInfo;

import java.util.List;

/**
 * IBR121407RsBean.
 *
 * @author yuan_zhifei
 */
public class IBR121407RsBean extends BrOBuyerInfo {
    //批发市场
    private String marketName;
    //买家名及主码
    private String buyerNameCode;
    //买家营业执照地址
    private String manageAddr;
    //老板名及联系方式
    private String bossNameTel;
    //微信号
    private String bossWechat;
    //QQ
    private String bossQq;
    private String fileName;
    /*待补充*/
    //收货人及联系方式
    private String recNameTel;
    //正常收货时间段
    private String habitRecTime;
    //最早收货时间
    private String earliestRecTime;
    //最晚收货时间
    private String latestRecTime;
    /*配送地址(虚拟地址)*/
    private String deliveryAddr;
    //配送参照地址
    private String referenceAddr;
    //支付方式
    private String paymentType;
    private List<BrOClaMachiningInfo> brOClaMachiningInfoList;
    //判断成功条件
    private String  dataCount;
    //判断文件状态
    private String fileStatusFlag;
    private String fileId;

    public String getDataCount() {
        return dataCount;
    }

    public String getFileStatusFlag() {
        return fileStatusFlag;
    }

    public void setFileStatusFlag(String fileStatusFlag) {
        this.fileStatusFlag = fileStatusFlag;
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

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getBuyerNameCode() {
        return buyerNameCode;
    }

    public void setBuyerNameCode(String buyerNameCode) {
        this.buyerNameCode = buyerNameCode;
    }

    public String getManageAddr() {
        return manageAddr;
    }

    public void setManageAddr(String manageAddr) {
        this.manageAddr = manageAddr;
    }

    public String getBossNameTel() {
        return bossNameTel;
    }

    public void setBossNameTel(String bossNameTel) {
        this.bossNameTel = bossNameTel;
    }

    public String getBossWechat() {
        return bossWechat;
    }

    public void setBossWechat(String bossWechat) {
        this.bossWechat = bossWechat;
    }

    public String getBossQq() {
        return bossQq;
    }

    public void setBossQq(String bossQq) {
        this.bossQq = bossQq;
    }

    public List<BrOClaMachiningInfo> getBrOClaMachiningInfoList() {
        return brOClaMachiningInfoList;
    }

    public void setBrOClaMachiningInfoList(List<BrOClaMachiningInfo> brOClaMachiningInfoList) {
        this.brOClaMachiningInfoList = brOClaMachiningInfoList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRecNameTel() {
        return recNameTel;
    }

    public void setRecNameTel(String recNameTel) {
        this.recNameTel = recNameTel;
    }

    @Override
    public String getHabitRecTime() {
        return habitRecTime;
    }

    @Override
    public void setHabitRecTime(String habitRecTime) {
        this.habitRecTime = habitRecTime;
    }

    @Override
    public String getEarliestRecTime() {
        return earliestRecTime;
    }

    @Override
    public void setEarliestRecTime(String earliestRecTime) {
        this.earliestRecTime = earliestRecTime;
    }

    @Override
    public String getLatestRecTime() {
        return latestRecTime;
    }

    @Override
    public void setLatestRecTime(String latestRecTime) {
        this.latestRecTime = latestRecTime;
    }

    @Override
    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    @Override
    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    @Override
    public String getReferenceAddr() {
        return referenceAddr;
    }

    @Override
    public void setReferenceAddr(String referenceAddr) {
        this.referenceAddr = referenceAddr;
    }

    @Override
    public String getPaymentType() {
        return paymentType;
    }

    @Override
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
