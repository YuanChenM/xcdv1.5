/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition;

import javax.persistence.criteria.JoinType;

/**
 * 
 * <p>
 * Database query join define.
 * </p>
 * 
 * @author zhou_ke
 */
public class BaseJoin {

    /** table name */
    String tableName;
    /** join type */
    JoinType joinType;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * 
     * @author zhou_ke
     */
    public BaseJoin() {
        this("");
    }

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * @param tableName the table name
     * 
     * @author zhou_ke
     */
    public BaseJoin(String tableName) {
        this.tableName = tableName;
        this.joinType = JoinType.INNER;
    }

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     * @param tableName the table name
     * @param joinType the join type
     * 
     * @author zhou_ke
     */
    public BaseJoin(String tableName, JoinType joinType) {
        this.tableName = tableName;
        this.joinType = joinType;
    }

    /**
     * <p>
     * get the table name.
     * </p>
     * 
     * @return table name
     * 
     * @author zhou_ke
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * <p>
     * set the table name.
     * </p>
     * 
     * @param tableName table name
     * 
     * @author zhou_ke
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * <p>
     * get the join type.
     * </p>
     * 
     * @return join type
     * 
     * @author zhou_ke
     */
    public JoinType getJoinType() {
        return this.joinType;
    }

    /**
     * <p>
     * set the join type.
     * </p>
     * 
     * @param joinType join type
     * 
     * @author zhou_ke
     */
    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

}
