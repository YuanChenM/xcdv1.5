/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

/**
 * 
 * <p>
 * The condition column that's type is Integer.
 * </p>
 * 
 * @author zhou_ke
 */
public class IntegerColumn extends BaseColumn {
    private Integer column;

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author zhou_ke
     */
    public Integer getColumn() {
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
    public void setColumn(Integer column) {
        this.column = column;
    }
}
