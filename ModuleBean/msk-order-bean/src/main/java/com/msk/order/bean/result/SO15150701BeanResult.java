package com.msk.order.bean.result;


import com.msk.order.entity.SoReturnDetail;


import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * ISO151506_退货单详情
 * Created by wang_shuai on 2016/8/3.
 */
public class SO15150701BeanResult extends SoReturnDetail {
    //产品单价
    private BigDecimal pdPrice;
    /** 退货单明细ID */
    private Long returnDetailId;
    /** 退货单编码 */
    private String returnCode;

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public Long getReturnDetailId() {
        return returnDetailId;
    }

    public void setReturnDetailId(Long returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
