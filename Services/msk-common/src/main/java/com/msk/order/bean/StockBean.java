package com.msk.order.bean;

import com.msk.common.base.BaseBean;
import com.msk.core.entity.SoStockSl;
import com.msk.core.entity.SoStockSupp;

import java.util.List;

/**
 * 库存Bean.
 * @author yuan_chen
 */
public class StockBean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
 
    private List<SoStockSupp> soSuppStockList;
    
    /** SoSlStockList */
    private List<SoStockSl> soSlStockList;

    /**
     * Get the soSuppStockList.
     *
     * @return soSuppStockList
     *
     * @author administator
     */
    public List<SoStockSupp> getSoSuppStockList() {
        return this.soSuppStockList;
    }

    /**
     * Set the soSuppStockList.
     *
     * @param soSuppStockList soSuppStockList
     *
     * @author administator
     */
    public void setSoSuppStockList(List<SoStockSupp> soSuppStockList) {
        this.soSuppStockList = soSuppStockList;
    }

    /**
     * Get the soSlStockList.
     *
     * @return soSlStockList
     *
     * @author administator
     */
    public List<SoStockSl> getSoSlStockList() {
        return this.soSlStockList;
    }

    /**
     * Set the soSlStockList.
     *
     * @param soSlStockList soSlStockList
     *
     * @author administator
     */
    public void setSoSlStockList(List<SoStockSl> soSlStockList) {
        this.soSlStockList = soSlStockList;
    }

}
