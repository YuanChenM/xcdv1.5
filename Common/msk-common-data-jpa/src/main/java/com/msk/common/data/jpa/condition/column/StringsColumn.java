/**
 * @screen core
 * @author li_jie4
 */
package com.msk.common.data.jpa.condition.column;

import com.msk.common.data.jpa.condition.BaseOperatorEnum;

import java.util.List;



/**
 * 
 * <p>
 * The condition column that's type is String Array.
 * </p>
 * 
 * @author li_jie4
 */
public class StringsColumn extends BaseColumn {

    private String[] column;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author li_jie4
     */
    public StringsColumn() {
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
     * @author li_jie4
     */
    public StringsColumn(int size) {
        super();
        this.column = new String[size];
        this.operator = BaseOperatorEnum.BETWEEN;
    }

    // Mantis#83131 modified by liu_bing start
    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * 
     * @param values prepared conditions.
     */
    public StringsColumn(String[] values) {
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
    public StringsColumn(List<String> values) {
        this(values.toArray(new String[values.size()]));
    }

    // Mantis#83131 modified by liu_bing end

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author li_jie4
     */
    public String[] getColumn() {
        return this.column;
    }

    /**
     * <p>
     * set the column.
     * </p>
     * 
     * @param column column
     * 
     * @author li_jie4
     */
    public void setColumn(String[] column) {
        this.column = column;
    }
}
