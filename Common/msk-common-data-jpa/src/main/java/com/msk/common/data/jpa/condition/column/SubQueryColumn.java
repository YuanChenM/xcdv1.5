/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;

/**
 * 
 * <p>
 * The condition column for sub query.
 * </p>
 * 
 * @author zhou_ke
 */
public class SubQueryColumn extends BaseColumn {
    String subQueryTable;
    String subQuerySelect;
    Object subQueryWhere;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public SubQueryColumn() {
        subQueryTable = "";
        subQuerySelect = "";
        subQueryWhere = "";
    }

    //Mantis#83131 by liu_bing start
    @Override
    public Object getColumn() {
        return null;
    }

    @Override
    public boolean isNullOrEmpty() {
        return false;
    }
    //Mantis#83131 by liu_bing end

    /**
     * <p>
     * get the subQueryTable.
     * </p>
     * 
     * @return subQueryTable
     * 
     * @author zhou_ke
     */
    public String getSubQueryTable() {
        return this.subQueryTable;
    }

    /**
     * <p>
     * set the subQueryTable.
     * </p>
     * 
     * @param subQueryTable subQueryTable
     * 
     * @author zhou_ke
     */
    public void setSubQueryTable(String subQueryTable) {
        this.subQueryTable = subQueryTable;
    }

    /**
     * <p>
     * get the subQuerySelect.
     * </p>
     * 
     * @return subQuerySelect
     * 
     * @author zhou_ke
     */
    public String getSubQuerySelect() {
        return this.subQuerySelect;
    }

    /**
     * <p>
     * set the subQuerySelect.
     * </p>
     * 
     * @param subQuerySelect subQuerySelect
     * 
     * @author zhou_ke
     */
    public void setSubQuerySelect(String subQuerySelect) {
        this.subQuerySelect = subQuerySelect;
    }

    /**
     * <p>
     * get the subQueryWhere.
     * </p>
     * 
     * @return subQueryWhere
     * 
     * @author zhou_ke
     */
    public Object getSubQueryWhere() {
        return this.subQueryWhere;
    }

    /**
     * <p>
     * set the subQueryWhere.
     * </p>
     * 
     * @param subQueryWhere subQueryWhere
     * 
     * @author zhou_ke
     */
    public void setSubQueryWhere(Object subQueryWhere) {
        this.subQueryWhere = subQueryWhere;
    }

}
