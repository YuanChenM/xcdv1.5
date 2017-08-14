package com.msk.buyers.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.ByBuyerBasicInfo;

/**
 * IBY121202RsParam.
 *
 * @author zhou_yajun
 */
@JsonIgnoreProperties(value = { "crtTime","updTime"})
public class IBY121202RsParam extends ByBuyerBasicInfo {

    private static final long serialVersionUID = 1L;

    /** 总数量 */
    private int totalCount;
    /** 每页数量 */
    private int pageSize;
    /** 查询页数 */
    private int pageNo;
    /** 开始行数 */
    private int startPos;
    /** 是否分页 */
    private boolean paging;
    /** 销售全部品种 */
    private String sellerObject;
    /** 在线状态名称 */
    private String marketingsStatusName;
    /** 支付方式名称 */
    private String paymentTypeName;
    /** 手机号码 */
    private String telNo;
    /** 前台用手机号码 */
    private String buyerTel;
    /** 雇员电话*/
    private String employeeTel;
    private String registerSourceName;
    /** 菜场或批发市场名称*/
    private String superiorIdName;

    /** 菜场或批发市场ID*/
    private String marketId;
    /** 菜场或批发市场编码*/
    private String marketCode;

    /** 画面选中的销售产品 */
    private String[] buyerPdCla;
    /**画面选中的销售二级 */
    private String[] buyerPdMac;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>totalCount</tt>.
     *
     * @return property value of totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Setter method for property <tt>totalCount</tt>.
     *
     * @param totalCount value to be assigned to property totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     *
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     *
     * @param pageSize value to be assigned to property pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>pageNo</tt>.
     *
     * @return property value of pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Setter method for property <tt>pageNo</tt>.
     *
     * @param pageNo value to be assigned to property pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * Getter method for property <tt>startPos</tt>.
     *
     * @return property value of startPos
     */
    public int getStartPos() {
        return startPos;
    }

    /**
     * Setter method for property <tt>startPos</tt>.
     *
     * @param startPos value to be assigned to property startPos
     */
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * Getter method for property <tt>paging</tt>.
     *
     * @return property value of paging
     */
    public boolean getPaging() {
        return paging;
    }

    /**
     * Setter method for property <tt>paging</tt>.
     *
     * @param paging value to be assigned to property paging
     */
    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    /**
     * <p>销售全部品种。</p>
     *
     * @return the 销售全部品种
     */
    public String getSellerObject() {
        return sellerObject;
    }

    /**
     * <p>销售全部品种。</p>
     *
     * @param sellerObject 销售全部品种。
     */
    public void setSellerObject(String sellerObject) {
        this.sellerObject = sellerObject;
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

    public String getEmployeeTel() {
        return employeeTel;
    }

    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
    }

    /**
     * Getter method for property <tt>paymentTypeName</tt>.
     *
     * @return property value of paymentTypeName
     */
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    /**
     * Setter method for property <tt>paymentTypeName</tt>.
     *
     * @param paymentTypeName value to be assigned to property paymentTypeName
     */
    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    /**
     * Getter method for property <tt>registerSourceName</tt>.
     *
     * @return property value of registerSourceName
     */
    public String getRegisterSourceName() {
        return registerSourceName;
    }

    /**
     * Setter method for property <tt>registerSourceName</tt>.
     *
     * @param registerSourceName value to be assigned to property registerSourceName
     */
    public void setRegisterSourceName(String registerSourceName) {
        this.registerSourceName = registerSourceName;
    }

    /**
     * Getter method for property <tt>superiorIdName</tt>.
     *
     * @return property value of superiorIdName
     */
    public String getSuperiorIdName() {
        return superiorIdName;
    }

    /**
     * Setter method for property <tt>superiorIdName</tt>.
     *
     * @param superiorIdName value to be assigned to property superiorIdName
     */
    public void setSuperiorIdName(String superiorIdName) {
        this.superiorIdName = superiorIdName;
    }

    /**
     * Getter method for property <tt>marketId</tt>.
     *
     * @return property value of marketId
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * Setter method for property <tt>marketId</tt>.
     *
     * @param marketId value to be assigned to property marketId
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
    /**
     * Getter method for property <tt>buyerPdCla</tt>.
     *
     * @return property value of buyerPdCla
     */
    public String[] getBuyerPdCla() {
        return buyerPdCla;
    }
    /**
     * Setter method for property <tt>buyerPdCla</tt>.
     *
     * @param buyerPdCla value to be assigned to property buyerPdCla
     */
    public void setBuyerPdCla(String[] buyerPdCla) {
        this.buyerPdCla = buyerPdCla;
    }
    /**
     * Getter method for property <tt>buyerPdMac</tt>.
     *
     * @return property value of buyerPdMac
     */
    public String[] getBuyerPdMac() {
        return buyerPdMac;
    }
    /**
     * Setter method for property <tt>buyerPdMac</tt>.
     *
     * @param buyerPdMac value to be assigned to property buyerPdMac
     */
    public void setBuyerPdMac(String[] buyerPdMac) {
        this.buyerPdMac = buyerPdMac;
    }

    /**
     * Getter method for property <tt>buyerTel</tt>.
     *
     * @return property value of buyerTel
     */
    public String getBuyerTel() {
        return buyerTel;
    }

    /**
     * Setter method for property <tt>buyerTel</tt>.
     *
     * @param buyerTel value to be assigned to property buyerTel
     */
    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    /**
     * Getter method for property <tt>marketCode</tt>.
     *
     * @return property value of marketCode
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * Setter method for property <tt>marketCode</tt>.
     *
     * @param marketCode value to be assigned to property marketCode
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }
}