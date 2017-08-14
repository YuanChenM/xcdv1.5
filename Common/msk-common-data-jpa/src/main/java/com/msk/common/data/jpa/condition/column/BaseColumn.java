/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition.column;


import com.msk.common.data.jpa.condition.BaseOperatorEnum;

/**
 * 
 * <p>
 * The base class of condition column.
 * </p>
 * 
 * @author zhou_ke
 */
public abstract class BaseColumn {

    /** the default size */
    protected final static int DEFAULT_SIZE = 2;

    String parentTable;
    BaseOperatorEnum operator;
    String columnName;

    // Mantis#83131 by liu_bing start
    private BaseColumn queryColumn;

    // Mantis#83131 by liu_bing end

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public BaseColumn() {
        parentTable = "";
        operator = BaseOperatorEnum.EQUAL;
        columnName = "";
    }

    /**
     * <p>
     * get the parentTable.
     * </p>
     * 
     * @return parentTable
     * 
     * @author zhou_ke
     */
    public String getParentTable() {
        return this.parentTable;
    }

    /**
     * <p>
     * set the parentTable.
     * </p>
     * 
     * @param parentTable parentTable
     * 
     * @author zhou_ke
     */
    public void setParentTable(String parentTable) {
        this.parentTable = parentTable;
    }

    /**
     * <p>
     * get the operator.
     * </p>
     * 
     * @return operator
     * 
     * @author zhou_ke
     */
    public BaseOperatorEnum getOperator() {
        return this.operator;
    }

    /**
     * <p>
     * set the operator.
     * </p>
     * 
     * @param operator operator
     * 
     * @author zhou_ke
     */
    public void setOperator(BaseOperatorEnum operator) {
        this.operator = operator;
    }

    /**
     * <p>
     * get the columnName.
     * </p>
     * 
     * @return columnName
     * 
     * @author zhou_ke
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * <p>
     * set the columnName.
     * </p>
     * 
     * @param columnName columnName
     * 
     * @author zhou_ke
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    // Mantis#83131 by liu_bing start
    /**
     * Getter for queryColumn.
     * 
     * @return The instance queryColumn.
     */
    public BaseColumn getQueryColumn() {
        return queryColumn;
    }

    /**
     * Setter for queryColumn.
     * 
     * @param queryColumn The parameter for queryColumn.
     */
    public void setQueryColumn(BaseColumn queryColumn) {
        this.queryColumn = queryColumn;
    }

    /**
     * <p>
     * Set query column, also set parent information into query column.
     * </p>
     * 
     * @param queryColumn The query column.
     */
    public void setQueryColumnSameParent(BaseColumn queryColumn) {
        this.queryColumn = queryColumn;
        if (this.queryColumn != null) {
            this.queryColumn.setParentTable(this.getParentTable());
            this.queryColumn.setColumnName(this.getColumnName());
        }
    }

    /**
     * <p>
     * Return value object.
     * </p>
     * 
     * @return The value.
     */
    public abstract Object getColumn();

    /**
     * Check if column doesn't contain any value.
     * 
     * @return isNullOrEmpty
     */
    public boolean isNullOrEmpty() {
        if (getColumn() == null) {
            return true;
        }
        if (getColumn().getClass().isArray()) {
            Object[] objArray = (Object[]) getColumn();
            if (objArray.length == 0) {
                return true;
            }
            for (Object obj : objArray) {
                if (obj != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Mantis#83131 by liu_bing end

}
