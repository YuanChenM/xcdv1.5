package com.msk.stock.bean;
import com.msk.common.bean.RsPageResult;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * IISO151430RsResult接口返回结果信息.
 *
 * @author zhang_qiang1
 */
public class ISO151430RsResult extends StockResult {

    private BigDecimal sumUsedStock;//商品总库存

    private BigDecimal  sumOrderStock;//订单取消的库存量



    private BigDecimal   sumReturnOrderStock;//退货入库的库存量





    public BigDecimal getSumOrderStock() {
        return sumOrderStock;
    }

    public void setSumOrderStock(BigDecimal sumOrderStock) {
        this.sumOrderStock = sumOrderStock;
    }

    public BigDecimal getSumReturnOrderStock() {
        return sumReturnOrderStock;
    }

    public void setSumReturnOrderStock(BigDecimal sumReturnOrderStock) {
        this.sumReturnOrderStock = sumReturnOrderStock;
    }



    public BigDecimal getSumUsedStock() {
        return sumUsedStock;
    }

    public void setSumUsedStock(BigDecimal sumUsedStock) {
        this.sumUsedStock = sumUsedStock;
    }
}
