/**
 * Filter.java
 * 
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.criteria.JoinType;

import com.msk.common.data.jpa.condition.column.*;

/**
 * The class of query condition.
 * 
 * @param <T> the type of main table
 * @author zhou_ke
 */
public class Filter<T> extends BaseCondition<T> {

    /** condition parameter list */
    List<Object[]> paramList;

    /**
     * 
     * <p>
     * The Constructors Method.
     * </p>
     * 
     */
    public Filter() {
        paramList = new ArrayList<Object[]>();
        joinTables = new ArrayList<BaseJoin>();
        fetchTables = new ArrayList<BaseJoin>();
    }

    /**
     * Convert the condition to a Map.
     * 
     * @return a Map of condition
     */
    public HashMap<String, Object> convert() {
        HashMap<String, Object> condMap = new HashMap<String, Object>();
        condMap.put(CommonSpecification.PARAMETERS, paramList);
        condMap.put(CommonSpecification.FETCH_TABLES, fetchTables);
        condMap.put(CommonSpecification.JOIN_TABLES, joinTables);
        condMap.put(CommonSpecification.SORT, sort);
        return condMap;
    }

    /**
     * Add a query condition.
     * 
     * @param fieldName the field name
     * @param operator the operator {@link BaseOperatorEnum}
     * @param value the condition value
     */
    public void add(String fieldName, BaseOperatorEnum operator, Object value) {
        String queryField = fieldName;
        BaseOperatorEnum queryOperator = operator;
        if (queryOperator == null) {
            queryOperator = BaseOperatorEnum.EQUAL;
        }

        int position = queryField.lastIndexOf(".");
        String parentTables = "";
        if (position > 0) {
            parentTables = queryField.substring(0, position);
            queryField = queryField.substring(position + 1);
        }

        if ((operator == BaseOperatorEnum.LIKE_IGNORE_CASE || operator == BaseOperatorEnum.LIKE) && value != null
                && !(value instanceof String)) {
            throw new IllegalArgumentException("You must specify string value for LIKE operator!");
        }

        if (value instanceof BigDecimal) {
            BigDecimalColumn temp = new BigDecimalColumn();
            temp.setColumn((BigDecimal) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof BigDecimal[]) {
            BigDecimalsColumn temp = new BigDecimalsColumn();
            temp.setColumn((BigDecimal[]) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof Date) {
            DateColumn temp = new DateColumn();
            temp.setColumn((Date) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof Date[]) {
            DatesColumn temp = new DatesColumn();
            temp.setColumn((Date[]) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof Integer) {
            IntegerColumn temp = new IntegerColumn();
            temp.setColumn((Integer) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof Integer[]) {
            IntegersColumn temp = new IntegersColumn();
            temp.setColumn((Integer[]) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof Long) {
            LongColumn temp = new LongColumn();
            temp.setColumn((Long) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof Long[]) {
            LongsColumn temp = new LongsColumn();
            temp.setColumn((Long[]) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof String) {
            StringColumn temp = new StringColumn();
            temp.setColumn((String) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else if (value instanceof String[]) {
            StringsColumn temp = new StringsColumn();
            temp.setColumn((String[]) value);
            temp.setOperator(queryOperator);
            temp.setParentTable(parentTables);
            paramList.add(new Object[] { queryField, temp });
        } else {
            //Mantis#83131 by liu_bing end
            if(value!=null){
                throw new IllegalArgumentException("The value of the type is not found.");
            }
            //Mantis#83131 by liu_bing end
        }
    }

    //Mantis#83131 by zhou_ke start
    /**
     * Remove a query condition.
     * 
     * @param fieldName the field name
     * 
     * @author zhou_ke
     */
    public void remove(String fieldName) {
        Object key = null;
        for (Object[] param : paramList) {
            if (param[0].toString().equals(fieldName)) {
                key = param;
                break;
            }
        }
        paramList.remove(key);
    }
    //Mantis#83131 by zhou_ke end

    /**
     * Add a sub query condition.
     * 
     * @param fieldName the field name
     * @param operator the operator {@link BaseOperatorEnum}
     * @param subTable the sub query table
     * @param subSelect the sub query select
     * @param subFilter the sub query condition
     */
    public void addSubQuery(String fieldName, BaseOperatorEnum operator, Class<?> subTable, String subSelect,
            Filter<?> subFilter) {
        BaseOperatorEnum queryOperator = operator;
        if (queryOperator == null) {
            queryOperator = BaseOperatorEnum.EQUAL;
        }
        SubQueryColumn subQuery = new SubQueryColumn();
        Map<String, Object> subCondMap = subFilter.convert();
        subQuery.setOperator(queryOperator);
        subQuery.setSubQueryTable(subTable.getName());
        subQuery.setSubQuerySelect(subSelect);
        subQuery.setSubQueryWhere(subCondMap);
        paramList.add(new Object[] { fieldName, subQuery });
    }

    /**
     * 
     * <p>
     * All null column for is null condition.
     * </p>
     * 
     * @param fieldName the field name
     * 
     * @author ma_b
     */
    public void addNullColumn(String fieldName) {
        String queryField = fieldName;

        int position = queryField.lastIndexOf(".");
        String parentTables = "";
        if (position > 0) {
            parentTables = queryField.substring(0, position);
            queryField = queryField.substring(position + 1);
        }

        NullColumn temp = new NullColumn();
        temp.setParentTable(parentTables);
        paramList.add(new Object[] { queryField, temp });
    }

    /**
     * Set a fetch table.
     * 
     * @param tableName the fetch table's name
     * @param joinType the join type
     */
    public void fetchRelative(String tableName, JoinType joinType) {
        JoinType queryJoin = joinType;
        if (queryJoin == null) {
            queryJoin = JoinType.INNER;
        }
        fetchTables.add(new BaseJoin(tableName, queryJoin));
    }

    /**
     * Set a join table.
     * 
     * @param tableName the fetch table's name
     * @param joinType the join type
     */
    public void joinRelative(String tableName, JoinType joinType) {
        JoinType queryJoin = joinType;
        if (queryJoin == null) {
            queryJoin = JoinType.INNER;
        }
        joinTables.add(new BaseJoin(tableName, queryJoin));
    }

    // /**
    // * Add the sort condition for query.
    // *
    // * @param direction the direction {@link Direction}
    // * @param fieldNames the sort field name
    // */
    // public void addSort(Direction direction, String... fieldNames) {
    // Direction queryDirection = direction;
    // if (queryDirection == null) {
    // queryDirection = Direction.ASC;
    // }
    // if (sort == null) {
    // sort = new Sort(queryDirection, fieldNames);
    // } else {
    // sort.and(new Sort(queryDirection, fieldNames));
    // }
    // }

    /**
     * get condition parameter list.
     * 
     * @return condition parameter list
     */
    public List<Object[]> getParamList() {
        return this.paramList;
    }

    /**
     * set condition parameter list
     * 
     * @param paramList condition parameter list
     */
    public void setParamList(List<Object[]> paramList) {
        this.paramList = paramList;
    }
}
