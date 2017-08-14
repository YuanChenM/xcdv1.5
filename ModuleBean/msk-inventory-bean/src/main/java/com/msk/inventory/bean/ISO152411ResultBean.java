package com.msk.inventory.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zheng_xu on 2016/8/22.
 */
public class ISO152411ResultBean {
    private String sellerCode;
    private String districtCode;
    private List<Products> products;

    public String getSellerCode() {
        return sellerCode;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public class Products{
        private String pdCode;
        private java.math.BigDecimal stockCnt;
        private java.math.BigDecimal onhandQty;
        private java.math.BigDecimal allocatedQty;

        public String getPdCode() {
            return pdCode;
        }

        public void setPdCode(String pdCode) {
            this.pdCode = pdCode;
        }

        public BigDecimal getStockCnt() {
            return stockCnt;
        }

        public void setStockCnt(BigDecimal stockCnt) {
            this.stockCnt = stockCnt;
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
    }
}
