/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

import com.msk.common.data.jpa.condition.BaseOperatorEnum;

import java.util.List;



/**
 * 
 * <p>
 * The condition column that's type is Long Array.
 * </p>
 * 
 * @author zhou_ke
 */
public class LongsColumn extends BaseColumn {
    private Long[] column;

    //by liu_bing for support NULL in SQL:IN operator,start
    private boolean includeNull;
    //by liu_bing for support NULL in SQL:IN operator,end

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public LongsColumn() {
        this(DEFAULT_SIZE);
    }

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * @param size the size
     * 
     * @author zhou_ke
     */
    public LongsColumn(int size) {
        super();
        this.column = new Long[size];
        this.operator = BaseOperatorEnum.IN;
    }

    // Mantis#83131 by liu_bing start
    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * 
     * @param values prepared conditions.
     */
    public LongsColumn(Long[] values) {
        super();
        this.column = values;
        this.operator = BaseOperatorEnum.IN;
    }

    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * 
     * @param values prepared conditions.
     */
    public LongsColumn(List<Long> values) {
        this(values.toArray(new Long[values.size()]));
    }

    //by liu_bing for support NULL in SQL:IN operator,start
    public LongsColumn(List<Long> values, boolean includeNull) {
        this(values.toArray(new Long[values.size()]));
        this.includeNull=includeNull;
    }
    //by liu_bing for support NULL in SQL:IN operator,end

    // Mantis#83131 by liu_bing end

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author zhou_ke
     */
    public Long[] getColumn() {
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
    public void setColumn(Long[] column) {
        this.column = column;
    }

    /**
     * <p>
     * Check if all of object in this column is null.
     * </p>
     * 
     * @return true if all of column is null.
     * @author liu_bing
     */
    public boolean isNull() {
        if (column == null) {
            return true;
        }
        for (Long obj : column) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }


    /**
     * Sets new includeNull.
     *
     * @param includeNull New value of includeNull.
     */
    public void setIncludeNull(boolean includeNull) {
        this.includeNull = includeNull;
    }

    /**
     * Gets includeNull.
     *
     * @return Value of includeNull.
     */
    public boolean isIncludeNull() {
        return includeNull;
    }
}
