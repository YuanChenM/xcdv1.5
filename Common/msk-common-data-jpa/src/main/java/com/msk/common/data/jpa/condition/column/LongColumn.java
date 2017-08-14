/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

/**
 * 
 * <p>
 * The condition column that's type is Long.
 * </p>
 * 
 * @author zhou_ke
 */
public class LongColumn extends BaseColumn {
    private Long column;

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author zhou_ke
     */
    public Long getColumn() {
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
    public void setColumn(Long column) {
        this.column = column;
    }
}
