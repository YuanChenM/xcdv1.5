/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * The condition column that's type is big decimal.
 * </p>
 * 
 * @author zhou_ke
 */
public class BigDecimalColumn extends BaseColumn {
    private BigDecimal column;

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author zhou_ke
     */
    public BigDecimal getColumn() {
        return this.column;
    }

    /**
     * <p>
     * set the column.
     * </p>
     * 
     * @param column column
     * 
     * @author zhou_ke
     */
    public void setColumn(BigDecimal column) {
        this.column = column;
    }
}
