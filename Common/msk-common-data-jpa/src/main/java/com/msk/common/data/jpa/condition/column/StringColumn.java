/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

/**
 * 
 * <p>
 * The condition column that's type is String.
 * </p>
 * 
 * @author zhou_ke
 */
public class StringColumn extends BaseColumn {
    private String column;

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author zhou_ke
     */
    public String getColumn() {
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
    public void setColumn(String column) {
        this.column = column;
    }
}
