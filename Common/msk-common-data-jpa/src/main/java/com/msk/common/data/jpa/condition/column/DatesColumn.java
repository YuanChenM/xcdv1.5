/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

import com.msk.common.data.jpa.condition.BaseOperatorEnum;

import java.util.Date;
import java.util.List;



/**
 * 
 * <p>
 * The condition column that's type is Date Array.
 * </p>
 * 
 * @author zhou_ke
 */
public class DatesColumn extends BaseColumn {
    private Date[] column;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public DatesColumn() {
        super();
        this.column = new Date[DEFAULT_SIZE];
        this.operator = BaseOperatorEnum.BETWEEN;
    }

    //Mantis#83131 modified by liu_bing start
    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * @param values prepared conditions.
     */
    public DatesColumn(Date[] values){
        super();
        this.column=values;
        this.operator = BaseOperatorEnum.IN;
    }

    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * @param values prepared conditions.
     */
    public DatesColumn(List<Date> values){
        this(values.toArray(new Date[values.size()]));
    }
    //Mantis#83131 modified by liu_bing end

    /**
     * <p>
     * get the column.
     * </p>
     * 
     * @return column
     * 
     * @author ma_b
     */
    public Date[] getColumn() {
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
    public void setColumn(Date[] column) {
        this.column = column;
    }
}
