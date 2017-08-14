package com.msk.common.data.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseJdbcDao<T> {

    /**
     * 
     * <p>
     * do query by a SQL.
     * </p>
     * 
     * @param sql query SQL
     * @param parameters query parameters
     * @param resultClass the result class type
     * @return the query result
     */
    List<T> queryForList(String sql, List<?> parameters, Class<T> resultClass);

    /**
     * 
     * <p>
     * do query by a SQL with paging condition.
     * </p>
     * 
     * @param sql query SQL
     * @param countSql count query SQL
     * @param parameters query parameters
     * @param resultClass the result class type
     * @param pageable the paging condition
     * @return the query result
     */
    Page<T> queryForList(String sql, String countSql, List<?> parameters, Class<T> resultClass, Pageable pageable);

    Page<T> queryForList(String sql,String countSql,List<?> parameters,Pageable pageable);
    /**
     * 
     * <p>
     * do count query by a SQL.
     * </p>
     * 
     * @param sql query SQL
     * @param parameters query parameters
     * @return the query result
     */
    long count(String sql, List<?> parameters);

    /**
     * 
     * <p>
     * do update by a SQL.
     * </p>
     * 
     * @param sql query SQL
     * @param parameters query parameters
     * @return updated record count
     */
    int executeUpdate(String sql, List<?> parameters);

    /**
     * 
     * <p>
     * do update by a SQL, the result is a map.
     * </p>
     * 
     * @param sql query SQL
     * @param parameters query parameters
     * @return the query result
     */
    Map<String, Object> queryForMap(String sql, List<?> parameters);
}
