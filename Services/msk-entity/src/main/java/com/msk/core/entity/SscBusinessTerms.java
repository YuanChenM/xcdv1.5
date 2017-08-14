/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_business_terms对应的SscBusinessTerms。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscBusinessTerms extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private java.lang.Long clauseId;
    /** 合同编号 */
    private java.lang.String contractId;
    /** 付款方式 */
    private java.lang.Integer paymentRatio;
    /** 运输责任方及运费支付方式 */
    private java.lang.String tranRes;
    /** 运费支付方式 */
    private java.lang.Integer tranFeeMethod;
    /** 干线运费标准 */
    private java.math.BigDecimal tranFeeStandard;
    /** 交货期延迟期限 */
    private java.lang.Integer delayDeadline;
    /** 甲方授权签字人 */
    private java.lang.String purchaserAuthSig;
    /** 乙方授权签字人 */
    private java.lang.String supplierAuthSig;
    /** 指定传真号码 */
    private java.lang.String faxNum;
    /** 甲方地址 */
    private java.lang.String purchaserAddr;
    /** 甲方对公邮箱 */
    private java.lang.String purchaserEmail;
    /** 行政流转人 */
    private java.lang.String adminTrans;
    /** 行政流转人QQ */
    private java.lang.String adminTransQq;
    /** 谈判管理人 */
    private java.lang.String negManager;
    /** 谈判管理人联系方式 */
    private java.lang.String negManagerPhonenum;
    /** 谈判管理人QQ */
    private java.lang.String negManagerQq;
    /** 品控管理人 */
    private java.lang.String qcManager;
    /** 品控管理人联系方式 */
    private java.lang.String qcManagerPhonenum;
    /** 品控管理人QQ */
    private java.lang.String qcManagerQq;
    /** 合同主体甲1 */
    private java.lang.String contractSubj1;
    /** 合同主体甲2 */
    private java.lang.String contractSubj2;
    /** 付款单位甲1 */
    private java.lang.String paymentUnit1;
    /** 付款单位甲2 */
    private java.lang.String paymentUnit2;
    /** 乙方公司QQ号 */
    private java.lang.String supplierQq;
    /** 乙方公司对外邮箱 */
    private java.lang.String supplierEmail;
    /** 乙方营销负责人 */
    private java.lang.String marketManager;
    /** 乙方营销负责人联系方式 */
    private java.lang.String marketManagerPhonenum;
    /** 乙方合同负责人 */
    private java.lang.String contractDirector;
    /** 乙方合同负责人联系方式 */
    private java.lang.String contractDirectorPhonenum;
    /** 乙方生产负责人 */
    private java.lang.String produceDirector;
    /** 乙方生产负责人联系方式 */
    private java.lang.String produceDirectorPhonenum;
    /** 乙方品控负责人 */
    private java.lang.String qcDirector;
    /** 乙方品控负责人联系方式 */
    private java.lang.String qcDirectorPhonenum;
    /** 乙方运输负责人 */
    private java.lang.String transDirector;
    /** 乙方运输负责人联系方式 */
    private java.lang.String transDirectorPhonenum;
    /** 交付地点 */
    private java.lang.String deliveryLocation;
    /** 合同核销期限 */
    private java.lang.Integer contractVerPeriod;
    /** 甲方核销负责人 */
    private java.lang.String purVerDirector;
    /** 甲方核销负责人联系方式 */
    private java.lang.String purVerDirectorNum;
    /** 乙方核销负责人 */
    private java.lang.String suppVerDirector;
    /** 乙方核销负责人联系方式 */
    private java.lang.String suppVerDirectorNum;
    /**
     * <p>默认构造函数。</p>
     */
    public SscBusinessTerms() {

    }

    /**
     * <p>ID。</p>
     *
     * @return the ID
     */
    public java.lang.Long getClauseId() {
        return clauseId;
    }

    /**
     * <p>ID。</p>
     *
     * @param clauseId ID。
     */
    public void setClauseId(java.lang.Long clauseId) {
        this.clauseId = clauseId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public java.lang.String getContractId() {
        return contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractId 合同编号。
     */
    public void setContractId(java.lang.String contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>付款方式。</p>
     *
     * @return the 付款方式
     */
    public java.lang.Integer getPaymentRatio() {
        return paymentRatio;
    }

    /**
     * <p>付款方式。</p>
     *
     * @param paymentRatio 付款方式。
     */
    public void setPaymentRatio(java.lang.Integer paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    /**
     * <p>运输责任方及运费支付方式。</p>
     *
     * @return the 运输责任方及运费支付方式
     */
    public java.lang.String getTranRes() {
        return tranRes;
    }

    /**
     * <p>运输责任方及运费支付方式。</p>
     *
     * @param tranRes 运输责任方及运费支付方式。
     */
    public void setTranRes(java.lang.String tranRes) {
        this.tranRes = tranRes;
    }

    /**
     * <p>运费支付方式。</p>
     *
     * @return the 运费支付方式
     */
    public java.lang.Integer getTranFeeMethod() {
        return tranFeeMethod;
    }

    /**
     * <p>运费支付方式。</p>
     *
     * @param tranFeeMethod 运费支付方式。
     */
    public void setTranFeeMethod(java.lang.Integer tranFeeMethod) {
        this.tranFeeMethod = tranFeeMethod;
    }

    /**
     * <p>干线运费标准。</p>
     *
     * @return the 干线运费标准
     */
    public java.math.BigDecimal getTranFeeStandard() {
        return tranFeeStandard;
    }

    /**
     * <p>干线运费标准。</p>
     *
     * @param tranFeeStandard 干线运费标准。
     */
    public void setTranFeeStandard(java.math.BigDecimal tranFeeStandard) {
        this.tranFeeStandard = tranFeeStandard;
    }

    /**
     * <p>交货期延迟期限。</p>
     *
     * @return the 交货期延迟期限
     */
    public java.lang.Integer getDelayDeadline() {
        return delayDeadline;
    }

    /**
     * <p>交货期延迟期限。</p>
     *
     * @param delayDeadline 交货期延迟期限。
     */
    public void setDelayDeadline(java.lang.Integer delayDeadline) {
        this.delayDeadline = delayDeadline;
    }

    /**
     * <p>甲方授权签字人。</p>
     *
     * @return the 甲方授权签字人
     */
    public java.lang.String getPurchaserAuthSig() {
        return purchaserAuthSig;
    }

    /**
     * <p>甲方授权签字人。</p>
     *
     * @param purchaserAuthSig 甲方授权签字人。
     */
    public void setPurchaserAuthSig(java.lang.String purchaserAuthSig) {
        this.purchaserAuthSig = purchaserAuthSig;
    }

    /**
     * <p>乙方授权签字人。</p>
     *
     * @return the 乙方授权签字人
     */
    public java.lang.String getSupplierAuthSig() {
        return supplierAuthSig;
    }

    /**
     * <p>乙方授权签字人。</p>
     *
     * @param supplierAuthSig 乙方授权签字人。
     */
    public void setSupplierAuthSig(java.lang.String supplierAuthSig) {
        this.supplierAuthSig = supplierAuthSig;
    }

    /**
     * <p>指定传真号码。</p>
     *
     * @return the 指定传真号码
     */
    public java.lang.String getFaxNum() {
        return faxNum;
    }

    /**
     * <p>指定传真号码。</p>
     *
     * @param faxNum 指定传真号码。
     */
    public void setFaxNum(java.lang.String faxNum) {
        this.faxNum = faxNum;
    }

    /**
     * <p>甲方地址。</p>
     *
     * @return the 甲方地址
     */
    public java.lang.String getPurchaserAddr() {
        return purchaserAddr;
    }

    /**
     * <p>甲方地址。</p>
     *
     * @param purchaserAddr 甲方地址。
     */
    public void setPurchaserAddr(java.lang.String purchaserAddr) {
        this.purchaserAddr = purchaserAddr;
    }

    /**
     * <p>甲方对公邮箱。</p>
     *
     * @return the 甲方对公邮箱
     */
    public java.lang.String getPurchaserEmail() {
        return purchaserEmail;
    }

    /**
     * <p>甲方对公邮箱。</p>
     *
     * @param purchaserEmail 甲方对公邮箱。
     */
    public void setPurchaserEmail(java.lang.String purchaserEmail) {
        this.purchaserEmail = purchaserEmail;
    }

    /**
     * <p>行政流转人。</p>
     *
     * @return the 行政流转人
     */
    public java.lang.String getAdminTrans() {
        return adminTrans;
    }

    /**
     * <p>行政流转人。</p>
     *
     * @param adminTrans 行政流转人。
     */
    public void setAdminTrans(java.lang.String adminTrans) {
        this.adminTrans = adminTrans;
    }

    /**
     * <p>行政流转人QQ。</p>
     *
     * @return the 行政流转人QQ
     */
    public java.lang.String getAdminTransQq() {
        return adminTransQq;
    }

    /**
     * <p>行政流转人QQ。</p>
     *
     * @param adminTransQq 行政流转人QQ。
     */
    public void setAdminTransQq(java.lang.String adminTransQq) {
        this.adminTransQq = adminTransQq;
    }

    /**
     * <p>谈判管理人。</p>
     *
     * @return the 谈判管理人
     */
    public java.lang.String getNegManager() {
        return negManager;
    }

    /**
     * <p>谈判管理人。</p>
     *
     * @param negManager 谈判管理人。
     */
    public void setNegManager(java.lang.String negManager) {
        this.negManager = negManager;
    }

    /**
     * <p>谈判管理人联系方式。</p>
     *
     * @return the 谈判管理人联系方式
     */
    public java.lang.String getNegManagerPhonenum() {
        return negManagerPhonenum;
    }

    /**
     * <p>谈判管理人联系方式。</p>
     *
     * @param negManagerPhonenum 谈判管理人联系方式。
     */
    public void setNegManagerPhonenum(java.lang.String negManagerPhonenum) {
        this.negManagerPhonenum = negManagerPhonenum;
    }

    /**
     * <p>谈判管理人QQ。</p>
     *
     * @return the 谈判管理人QQ
     */
    public java.lang.String getNegManagerQq() {
        return negManagerQq;
    }

    /**
     * <p>谈判管理人QQ。</p>
     *
     * @param negManagerQq 谈判管理人QQ。
     */
    public void setNegManagerQq(java.lang.String negManagerQq) {
        this.negManagerQq = negManagerQq;
    }

    /**
     * <p>品控管理人。</p>
     *
     * @return the 品控管理人
     */
    public java.lang.String getQcManager() {
        return qcManager;
    }

    /**
     * <p>品控管理人。</p>
     *
     * @param qcManager 品控管理人。
     */
    public void setQcManager(java.lang.String qcManager) {
        this.qcManager = qcManager;
    }

    /**
     * <p>品控管理人联系方式。</p>
     *
     * @return the 品控管理人联系方式
     */
    public java.lang.String getQcManagerPhonenum() {
        return qcManagerPhonenum;
    }

    /**
     * <p>品控管理人联系方式。</p>
     *
     * @param qcManagerPhonenum 品控管理人联系方式。
     */
    public void setQcManagerPhonenum(java.lang.String qcManagerPhonenum) {
        this.qcManagerPhonenum = qcManagerPhonenum;
    }

    /**
     * <p>品控管理人QQ。</p>
     *
     * @return the 品控管理人QQ
     */
    public java.lang.String getQcManagerQq() {
        return qcManagerQq;
    }

    /**
     * <p>品控管理人QQ。</p>
     *
     * @param qcManagerQq 品控管理人QQ。
     */
    public void setQcManagerQq(java.lang.String qcManagerQq) {
        this.qcManagerQq = qcManagerQq;
    }

    /**
     * <p>合同主体甲1。</p>
     *
     * @return the 合同主体甲1
     */
    public java.lang.String getContractSubj1() {
        return contractSubj1;
    }

    /**
     * <p>合同主体甲1。</p>
     *
     * @param contractSubj1 合同主体甲1。
     */
    public void setContractSubj1(java.lang.String contractSubj1) {
        this.contractSubj1 = contractSubj1;
    }

    /**
     * <p>合同主体甲2。</p>
     *
     * @return the 合同主体甲2
     */
    public java.lang.String getContractSubj2() {
        return contractSubj2;
    }

    /**
     * <p>合同主体甲2。</p>
     *
     * @param contractSubj2 合同主体甲2。
     */
    public void setContractSubj2(java.lang.String contractSubj2) {
        this.contractSubj2 = contractSubj2;
    }

    /**
     * <p>付款单位甲1。</p>
     *
     * @return the 付款单位甲1
     */
    public java.lang.String getPaymentUnit1() {
        return paymentUnit1;
    }

    /**
     * <p>付款单位甲1。</p>
     *
     * @param paymentUnit1 付款单位甲1。
     */
    public void setPaymentUnit1(java.lang.String paymentUnit1) {
        this.paymentUnit1 = paymentUnit1;
    }

    /**
     * <p>付款单位甲2。</p>
     *
     * @return the 付款单位甲2
     */
    public java.lang.String getPaymentUnit2() {
        return paymentUnit2;
    }

    /**
     * <p>付款单位甲2。</p>
     *
     * @param paymentUnit2 付款单位甲2。
     */
    public void setPaymentUnit2(java.lang.String paymentUnit2) {
        this.paymentUnit2 = paymentUnit2;
    }

    /**
     * <p>乙方公司QQ号。</p>
     *
     * @return the 乙方公司QQ号
     */
    public java.lang.String getSupplierQq() {
        return supplierQq;
    }

    /**
     * <p>乙方公司QQ号。</p>
     *
     * @param supplierQq 乙方公司QQ号。
     */
    public void setSupplierQq(java.lang.String supplierQq) {
        this.supplierQq = supplierQq;
    }

    /**
     * <p>乙方公司对外邮箱。</p>
     *
     * @return the 乙方公司对外邮箱
     */
    public java.lang.String getSupplierEmail() {
        return supplierEmail;
    }

    /**
     * <p>乙方公司对外邮箱。</p>
     *
     * @param supplierEmail 乙方公司对外邮箱。
     */
    public void setSupplierEmail(java.lang.String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    /**
     * <p>乙方营销负责人。</p>
     *
     * @return the 乙方营销负责人
     */
    public java.lang.String getMarketManager() {
        return marketManager;
    }

    /**
     * <p>乙方营销负责人。</p>
     *
     * @param marketManager 乙方营销负责人。
     */
    public void setMarketManager(java.lang.String marketManager) {
        this.marketManager = marketManager;
    }

    /**
     * <p>乙方营销负责人联系方式。</p>
     *
     * @return the 乙方营销负责人联系方式
     */
    public java.lang.String getMarketManagerPhonenum() {
        return marketManagerPhonenum;
    }

    /**
     * <p>乙方营销负责人联系方式。</p>
     *
     * @param marketManagerPhonenum 乙方营销负责人联系方式。
     */
    public void setMarketManagerPhonenum(java.lang.String marketManagerPhonenum) {
        this.marketManagerPhonenum = marketManagerPhonenum;
    }

    /**
     * <p>乙方合同负责人。</p>
     *
     * @return the 乙方合同负责人
     */
    public java.lang.String getContractDirector() {
        return contractDirector;
    }

    /**
     * <p>乙方合同负责人。</p>
     *
     * @param contractDirector 乙方合同负责人。
     */
    public void setContractDirector(java.lang.String contractDirector) {
        this.contractDirector = contractDirector;
    }

    /**
     * <p>乙方合同负责人联系方式。</p>
     *
     * @return the 乙方合同负责人联系方式
     */
    public java.lang.String getContractDirectorPhonenum() {
        return contractDirectorPhonenum;
    }

    /**
     * <p>乙方合同负责人联系方式。</p>
     *
     * @param contractDirectorPhonenum 乙方合同负责人联系方式。
     */
    public void setContractDirectorPhonenum(java.lang.String contractDirectorPhonenum) {
        this.contractDirectorPhonenum = contractDirectorPhonenum;
    }

    /**
     * <p>乙方生产负责人。</p>
     *
     * @return the 乙方生产负责人
     */
    public java.lang.String getProduceDirector() {
        return produceDirector;
    }

    /**
     * <p>乙方生产负责人。</p>
     *
     * @param produceDirector 乙方生产负责人。
     */
    public void setProduceDirector(java.lang.String produceDirector) {
        this.produceDirector = produceDirector;
    }

    /**
     * <p>乙方生产负责人联系方式。</p>
     *
     * @return the 乙方生产负责人联系方式
     */
    public java.lang.String getProduceDirectorPhonenum() {
        return produceDirectorPhonenum;
    }

    /**
     * <p>乙方生产负责人联系方式。</p>
     *
     * @param produceDirectorPhonenum 乙方生产负责人联系方式。
     */
    public void setProduceDirectorPhonenum(java.lang.String produceDirectorPhonenum) {
        this.produceDirectorPhonenum = produceDirectorPhonenum;
    }

    /**
     * <p>乙方品控负责人。</p>
     *
     * @return the 乙方品控负责人
     */
    public java.lang.String getQcDirector() {
        return qcDirector;
    }

    /**
     * <p>乙方品控负责人。</p>
     *
     * @param qcDirector 乙方品控负责人。
     */
    public void setQcDirector(java.lang.String qcDirector) {
        this.qcDirector = qcDirector;
    }

    /**
     * <p>乙方品控负责人联系方式。</p>
     *
     * @return the 乙方品控负责人联系方式
     */
    public java.lang.String getQcDirectorPhonenum() {
        return qcDirectorPhonenum;
    }

    /**
     * <p>乙方品控负责人联系方式。</p>
     *
     * @param qcDirectorPhonenum 乙方品控负责人联系方式。
     */
    public void setQcDirectorPhonenum(java.lang.String qcDirectorPhonenum) {
        this.qcDirectorPhonenum = qcDirectorPhonenum;
    }

    /**
     * <p>乙方运输负责人。</p>
     *
     * @return the 乙方运输负责人
     */
    public java.lang.String getTransDirector() {
        return transDirector;
    }

    /**
     * <p>乙方运输负责人。</p>
     *
     * @param transDirector 乙方运输负责人。
     */
    public void setTransDirector(java.lang.String transDirector) {
        this.transDirector = transDirector;
    }

    /**
     * <p>乙方运输负责人联系方式。</p>
     *
     * @return the 乙方运输负责人联系方式
     */
    public java.lang.String getTransDirectorPhonenum() {
        return transDirectorPhonenum;
    }

    /**
     * <p>乙方运输负责人联系方式。</p>
     *
     * @param transDirectorPhonenum 乙方运输负责人联系方式。
     */
    public void setTransDirectorPhonenum(java.lang.String transDirectorPhonenum) {
        this.transDirectorPhonenum = transDirectorPhonenum;
    }

    /**
     * <p>交付地点。</p>
     *
     * @return the 交付地点
     */
    public java.lang.String getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * <p>交付地点。</p>
     *
     * @param deliveryLocation 交付地点。
     */
    public void setDeliveryLocation(java.lang.String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    /**
     * <p>合同核销期限。</p>
     *
     * @return the 合同核销期限
     */
    public java.lang.Integer getContractVerPeriod() {
        return contractVerPeriod;
    }

    /**
     * <p>合同核销期限。</p>
     *
     * @param contractVerPeriod 合同核销期限。
     */
    public void setContractVerPeriod(java.lang.Integer contractVerPeriod) {
        this.contractVerPeriod = contractVerPeriod;
    }

    /**
     * <p>甲方核销负责人。</p>
     *
     * @return the 甲方核销负责人
     */
    public java.lang.String getPurVerDirector() {
        return purVerDirector;
    }

    /**
     * <p>甲方核销负责人。</p>
     *
     * @param purVerDirector 甲方核销负责人。
     */
    public void setPurVerDirector(java.lang.String purVerDirector) {
        this.purVerDirector = purVerDirector;
    }

    /**
     * <p>甲方核销负责人联系方式。</p>
     *
     * @return the 甲方核销负责人联系方式
     */
    public java.lang.String getPurVerDirectorNum() {
        return purVerDirectorNum;
    }

    /**
     * <p>甲方核销负责人联系方式。</p>
     *
     * @param purVerDirectorNum 甲方核销负责人联系方式。
     */
    public void setPurVerDirectorNum(java.lang.String purVerDirectorNum) {
        this.purVerDirectorNum = purVerDirectorNum;
    }

    /**
     * <p>乙方核销负责人。</p>
     *
     * @return the 乙方核销负责人
     */
    public java.lang.String getSuppVerDirector() {
        return suppVerDirector;
    }

    /**
     * <p>乙方核销负责人。</p>
     *
     * @param suppVerDirector 乙方核销负责人。
     */
    public void setSuppVerDirector(java.lang.String suppVerDirector) {
        this.suppVerDirector = suppVerDirector;
    }

    /**
     * <p>乙方核销负责人联系方式。</p>
     *
     * @return the 乙方核销负责人联系方式
     */
    public java.lang.String getSuppVerDirectorNum() {
        return suppVerDirectorNum;
    }

    /**
     * <p>乙方核销负责人联系方式。</p>
     *
     * @param suppVerDirectorNum 乙方核销负责人联系方式。
     */
    public void setSuppVerDirectorNum(java.lang.String suppVerDirectorNum) {
        this.suppVerDirectorNum = suppVerDirectorNum;
    }

}
