package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestPageParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by duan_kai on 2016/9/7.
 */
public class InventoryViewBean extends BaseRestPageParam implements Serializable {

    private String pdCode;
    private String pdName;
    private String logicArea;
    private String platform;
    private String platformName;
    private String slType;
    private String slId;
    private String slName;
    private String ownerId;
    private String ownerCode;
    private String ownerName;
    private String ivType;
    private BigDecimal onhandQty;
    private BigDecimal allocatedQty;
    private BigDecimal availableQty;
    private boolean hasNullOwner = false;

    private List<Object> products;

    private List<Object> iso03Products;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getLogicArea() {
        return logicArea;
    }

    public void setLogicArea(String logicArea) {
        this.logicArea = logicArea;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getSlType() {
        return slType;
    }

    public void setSlType(String slType) {
        this.slType = slType;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIvType() {
        return ivType;
    }

    public void setIvType(String ivType) {
        this.ivType = ivType;
    }

    public BigDecimal getOnhandQty() {
        return onhandQty;
    }

    public void setOnhandQty(BigDecimal onhandQty) {
        this.onhandQty = onhandQty;
    }

    public BigDecimal getAllocatedQty() {
        return allocatedQty;
    }

    public void setAllocatedQty(BigDecimal allocatedQty) {
        this.allocatedQty = allocatedQty;
    }

    public BigDecimal getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(BigDecimal availableQty) {
        this.availableQty = availableQty;
    }

    public boolean isHasNullOwner() {
        return hasNullOwner;
    }

    public void setHasNullOwner(boolean hasNullOwner) {
        this.hasNullOwner = hasNullOwner;
    }

    public List<Object> getProducts() {
        return products;
    }

    public void setProducts(List<Object> products) {
        this.products = products;
    }

    public List<Object> getIso03Products() {
        return iso03Products;
    }

    public void setIso03Products(List<Object> iso03Products) {
        this.iso03Products = iso03Products;
    }

}
