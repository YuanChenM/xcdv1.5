/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

import java.util.Date;

/**
 * 
 * <p>
 * The condition column that's type is date.
 * </p>
 * 
 * @author zhou_ke
 */
public class DateColumn extends BaseColumn {
    private Date column;

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author zhou_ke
     */
    public Date getColumn() {
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
    public void setColumn(Date column) {
        this.column = column;
    }
}
