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
 * The condition column that's type is Integer Array.
 * </p>
 * 
 * @author zhou_ke
 */
public class IntegersColumn extends BaseColumn {
    private Integer[] column;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public IntegersColumn() {
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
    public IntegersColumn(int size) {
        super();
        this.column = new Integer[size];
        this.operator = BaseOperatorEnum.IN;
    }

    // Mantis#83131 modified by liu_bing start
    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * 
     * @param values prepared conditions.
     */
    public IntegersColumn(Integer[] values) {
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
    public IntegersColumn(List<Integer> values) {
        this(values.toArray(new Integer[values.size()]));
    }

    // Mantis#83131 modified by liu_bing end

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author ma_b
     */
    public Integer[] getColumn() {
        return this.column;
    }

    /**
     * <p>
     * set the column.
     * </p>
     * 
     * @param column column
     * 
     * @author ma_b
     */
    public void setColumn(Integer[] column) {
        this.column = column;
    }
}
