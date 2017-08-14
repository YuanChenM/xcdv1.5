/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表comm_id_manage对应的CommIdManage。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class CommIdManage extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** TABLE_NAME */
    private String tableName;
    /** AUTO_INCREMENT_VALUE */
    private Long autoIncrementValue;
    /**
     * <p>默认构造函数。</p>
     */
    public CommIdManage() {

    }

    /**
     * <p>ID。</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID。</p>
     *
     * @param id ID。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>TABLE_NAME。</p>
     *
     * @return the TABLE_NAME
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * <p>TABLE_NAME。</p>
     *
     * @param tableName TABLE_NAME。
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * <p>AUTO_INCREMENT_VALUE。</p>
     *
     * @return the AUTO_INCREMENT_VALUE
     */
    public Long getAutoIncrementValue() {
        return autoIncrementValue;
    }

    /**
     * <p>AUTO_INCREMENT_VALUE。</p>
     *
     * @param autoIncrementValue AUTO_INCREMENT_VALUE。
     */
    public void setAutoIncrementValue(Long autoIncrementValue) {
        this.autoIncrementValue = autoIncrementValue;
    }

}
