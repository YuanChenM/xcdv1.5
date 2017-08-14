/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

import com.msk.common.data.jpa.condition.BaseOperatorEnum;

import java.math.BigDecimal;
import java.util.List;



/**
 * 
 * <p>
 * The condition column that's type is BigDecimal Array.
 * </p>
 * 
 * @author zhou_ke
 */
public class BigDecimalsColumn extends BaseColumn {

    private BigDecimal[] column;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public BigDecimalsColumn() {
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
    public BigDecimalsColumn(int size) {
        super();
        this.column = new BigDecimal[size];
        this.operator = BaseOperatorEnum.IN;
    }

    //Mantis#83131 modified by liu_bing start
    /**
     * <p>
     * The constructor for easy uss.
     * </p>
     * @param values prepared conditions.
     */
    public BigDecimalsColumn(BigDecimal[] values){
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
    public BigDecimalsColumn(List<BigDecimal> values){
        this(values.toArray(new BigDecimal[values.size()]));
    }
    //Mantis#83131 modified by liu_bing end

    /**
     * 
     * <p>
     * Get the column condition.
     * </p>
     * 
     * @return the column condition
     * 
     * @author zhou_ke
     */
    public BigDecimal[] getColumn() {
        return this.column;
    }

    /**
     * 
     * <p>
     * Set the column condition.
     * </p>
     * 
     * @param column the column condition
     * 
     * @author zhou_ke
     */
    public void setColumn(BigDecimal[] column) {
        this.column = column;
    }
}
