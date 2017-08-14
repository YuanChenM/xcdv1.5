/**
 * NullColumn.java
 * 
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

/**
 * <p>
 * The condition column that's type is null.
 * </p>
 * 
 * @author zhou_ke
 */
public class NullColumn extends BaseColumn {

    //Mantis#83131 by liu_bing start
    @Override
    public Object getColumn() {
        return null;
    }

    @Override
    public boolean isNullOrEmpty() {
        return true;
    }
    //Mantis#83131 by liu_bing end

}
