package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerBasicInfo;

import java.util.List;

/**
 * BY121324Bean.
 *
 * @author zhou_yajun
 */
public class BY121324Bean extends ByBuyerBasicInfo {

    private String telNo;
    private String accountName;
    private String accountPass;
    private String employeeType;
    private String employeeTypeName;
    private String employeeName;
    private String employeeTel;
    private String employeeQq;
    private String employeeWechat;
    /** 0：否，1：是 */
    private String contactPerson;
    /** 0：否，1：是 */
    private String purchase;
    private String terminalMarketName;
    private String marketingToosName;
    private String telMarketingTime;
    private String buyerStoreAddrAndNo;
    private String marketingsStatusName;
    private String classCode;
    private String className;
    private String machingCodeU;
    private String machingNameU;
    private String buyerPool;
    //买家雇员信息
    private List<BY121324Bean> employeeList;
    //买家商城账号信息
    private List<BY121324Bean> mallAccountList;

    /**
     * Getter method for property <tt>telNo</tt>.
     *
     * @return property value of telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * Setter method for property <tt>telNo</tt>.
     *
     * @param telNo value to be assigned to property telNo
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * Getter method for property <tt>accountName</tt>.
     *
     * @return property value of accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Setter method for property <tt>accountName</tt>.
     *
     * @param accountName value to be assigned to property accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Getter method for property <tt>accountPass</tt>.
     *
     * @return property value of accountPass
     */
    public String getAccountPass() {
        return accountPass;
    }

    /**
     * Setter method for property <tt>accountPass</tt>.
     *
     * @param accountPass value to be assigned to property accountPass
     */
    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    /**
     * Getter method for property <tt>employeeType</tt>.
     *
     * @return property value of employeeType
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * Setter method for property <tt>employeeType</tt>.
     *
     * @param employeeType value to be assigned to property employeeType
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * Getter method for property <tt>employeeTypeName</tt>.
     *
     * @return property value of employeeTypeName
     */
    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    /**
     * Setter method for property <tt>employeeTypeName</tt>.
     *
     * @param employeeTypeName value to be assigned to property employeeTypeName
     */
    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

    /**
     * Getter method for property <tt>employeeName</tt>.
     *
     * @return property value of employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Setter method for property <tt>employeeName</tt>.
     *
     * @param employeeName value to be assigned to property employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Getter method for property <tt>employeeTel</tt>.
     *
     * @return property value of employeeTel
     */
    public String getEmployeeTel() {
        return employeeTel;
    }

    /**
     * Setter method for property <tt>employeeTel</tt>.
     *
     * @param employeeTel value to be assigned to property employeeTel
     */
    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
    }

    /**
     * Getter method for property <tt>employeeQq</tt>.
     *
     * @return property value of employeeQq
     */
    public String getEmployeeQq() {
        return employeeQq;
    }

    /**
     * Setter method for property <tt>employeeQq</tt>.
     *
     * @param employeeQq value to be assigned to property employeeQq
     */
    public void setEmployeeQq(String employeeQq) {
        this.employeeQq = employeeQq;
    }

    /**
     * Getter method for property <tt>employeeWechat</tt>.
     *
     * @return property value of employeeWechat
     */
    public String getEmployeeWechat() {
        return employeeWechat;
    }

    /**
     * Setter method for property <tt>employeeWechat</tt>.
     *
     * @param employeeWechat value to be assigned to property employeeWechat
     */
    public void setEmployeeWechat(String employeeWechat) {
        this.employeeWechat = employeeWechat;
    }

    /**
     * Getter method for property <tt>terminalMarketName</tt>.
     *
     * @return property value of terminalMarketName
     */
    public String getTerminalMarketName() {
        return terminalMarketName;
    }

    /**
     * Setter method for property <tt>terminalMarketName</tt>.
     *
     * @param terminalMarketName value to be assigned to property terminalMarketName
     */
    public void setTerminalMarketName(String terminalMarketName) {
        this.terminalMarketName = terminalMarketName;
    }

    /**
     * Getter method for property <tt>marketingToosName</tt>.
     *
     * @return property value of marketingToosName
     */
    public String getMarketingToosName() {
        return marketingToosName;
    }

    /**
     * Setter method for property <tt>marketingToosName</tt>.
     *
     * @param marketingToosName value to be assigned to property marketingToosName
     */
    public void setMarketingToosName(String marketingToosName) {
        this.marketingToosName = marketingToosName;
    }

    /**
     * Getter method for property <tt>telMarketingTime</tt>.
     *
     * @return property value of telMarketingTime
     */
    public String getTelMarketingTime() {
        return telMarketingTime;
    }

    /**
     * Setter method for property <tt>telMarketingTime</tt>.
     *
     * @param telMarketingTime value to be assigned to property telMarketingTime
     */
    public void setTelMarketingTime(String telMarketingTime) {
        this.telMarketingTime = telMarketingTime;
    }

    /**
     * Getter method for property <tt>employeeList</tt>.
     *
     * @return property value of employeeList
     */
    public List<BY121324Bean> getEmployeeList() {
        return employeeList;
    }

    /**
     * Setter method for property <tt>employeeList</tt>.
     *
     * @param employeeList value to be assigned to property employeeList
     */
    public void setEmployeeList(List<BY121324Bean> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * Getter method for property <tt>mallAccountList</tt>.
     *
     * @return property value of mallAccountList
     */
    public List<BY121324Bean> getMallAccountList() {
        return mallAccountList;
    }

    /**
     * Setter method for property <tt>mallAccountList</tt>.
     *
     * @param mallAccountList value to be assigned to property mallAccountList
     */
    public void setMallAccountList(List<BY121324Bean> mallAccountList) {
        this.mallAccountList = mallAccountList;
    }

    /**
     * Getter method for property <tt>contactPerson</tt>.
     *
     * @return property value of contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * Setter method for property <tt>contactPerson</tt>.
     *
     * @param contactPerson value to be assigned to property contactPerson
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * Getter method for property <tt>purchase</tt>.
     *
     * @return property value of purchase
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * Setter method for property <tt>purchase</tt>.
     *
     * @param purchase value to be assigned to property purchase
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    /**
     * Getter method for property <tt>buyerStoreAddrAndNo</tt>.
     *
     * @return property value of buyerStoreAddrAndNo
     */
    public String getBuyerStoreAddrAndNo() {
        return buyerStoreAddrAndNo;
    }

    /**
     * Setter method for property <tt>buyerStoreAddrAndNo</tt>.
     *
     * @param buyerStoreAddrAndNo value to be assigned to property buyerStoreAddrAndNo
     */
    public void setBuyerStoreAddrAndNo(String buyerStoreAddrAndNo) {
        this.buyerStoreAddrAndNo = buyerStoreAddrAndNo;
    }

    /**
     * Getter method for property <tt>marketingsStatusName</tt>.
     *
     * @return property value of marketingsStatusName
     */
    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    /**
     * Setter method for property <tt>marketingsStatusName</tt>.
     *
     * @param marketingsStatusName value to be assigned to property marketingsStatusName
     */
    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    /**
     * Getter method for property <tt>classCode</tt>.
     *
     * @return property value of classCode
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Setter method for property <tt>classCode</tt>.
     *
     * @param classCode value to be assigned to property classCode
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * Getter method for property <tt>className</tt>.
     *
     * @return property value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter method for property <tt>className</tt>.
     *
     * @param className value to be assigned to property className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter method for property <tt>machingCodeU</tt>.
     *
     * @return property value of machingCodeU
     */
    public String getMachingCodeU() {
        return machingCodeU;
    }

    /**
     * Setter method for property <tt>machingCodeU</tt>.
     *
     * @param machingCodeU value to be assigned to property machingCodeU
     */
    public void setMachingCodeU(String machingCodeU) {
        this.machingCodeU = machingCodeU;
    }

    /**
     * Getter method for property <tt>machingNameU</tt>.
     *
     * @return property value of machingNameU
     */
    public String getMachingNameU() {
        return machingNameU;
    }

    /**
     * Setter method for property <tt>machingNameU</tt>.
     *
     * @param machingNameU value to be assigned to property machingNameU
     */
    public void setMachingNameU(String machingNameU) {
        this.machingNameU = machingNameU;
    }

    /**
     * Getter method for property <tt>buyerPool</tt>.
     *
     * @return property value of buyerPool
     */
    public String getBuyerPool() {
        return buyerPool;
    }

    /**
     * Setter method for property <tt>buyerPool</tt>.
     *
     * @param buyerPool value to be assigned to property buyerPool
     */
    public void setBuyerPool(String buyerPool) {
        this.buyerPool = buyerPool;
    }
}
