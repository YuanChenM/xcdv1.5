package com.msk.common.data.jpa.condition;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

import javax.persistence.LockModeType;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.In;

import com.msk.common.data.jpa.condition.column.*;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.msk.common.constant.NumberConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;


/**
 * CommonSpecification.
 * 
 * @param <T>
 * @author ma_b
 */
public class CommonSpecification<T> implements Specification<T> {

    /** PARAMETERS key */
    public static final String PARAMETERS = "parameters";
    /** JOIN_TABLES key */
    public static final String JOIN_TABLES = "joinTables";
    /** FETCH_TABLES key */
    public static final String FETCH_TABLES = "fetchTables";
    /** the sort key */
    public static final String SORT = "sort";
    /** PK key */
    private static final String PK = "id";
    /** split char */
    private static final String SPLIT = "\\.";
    /** the hour in to condition */
    private static final int TO_HOUR = 23;
    /** the minute in to condition */
    private static final int TO_MINUTE = 59;
    /** the second in to condition */
    private static final int TO_SECOND = 59;

    // Add for bug#0135480 at 2013/12/09 by lijie4 Start.
    /** the millisecond in to condition */
    private static final int TO_MILLI = 999;
    // Add for bug#0135480 at 2013/12/09 by lijie4 End.
    public static final char CHAR_VERTICAL = '|';
    public static final String VERTICAL = "|";
    public static final String PRE = "%";
    public static final String UNDERLINE = "_";
    public static final char CHAR_STAR = '*';
    public static final char CHAR_PRE = '%';
    private static Logger logger = LoggerFactory.getLogger(CommonSpecification.class);

    private Object condition;

    private Map<String, Object> parmeters;

    private Map<String, Join<?, ?>> joins;

    private Map<String, Fetch<?, ?>> fetchs;

    private Map<String, SubQueryColumn> subQuerys;

    private boolean isFetch;

    private boolean isDistinct;

    private LockModeType lockModeType;

    private int paramCount;

    /**
     * The Constructors Method.
     * 
     * @param queryArgument queryArgument
     * 
     */
    public CommonSpecification(Object queryArgument) {
        condition = queryArgument;
        parmeters = new HashMap<String, Object>();
        isFetch = true;
        isDistinct = true;
    }

    /**
     * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
     * {@link Root} and {@link CriteriaQuery}.
     * 
     * @param root query root
     * @param query query condition
     * @param builder the criteria builder
     * @return the predicate of query
     * @see Specification#toPredicate(Root,
     *      CriteriaQuery, CriteriaBuilder)
     *
     * @author ma_b
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        parmeters = new HashMap<String, Object>();
        joins = new HashMap<String, Join<?, ?>>();
        fetchs = new HashMap<String, Fetch<?, ?>>();
        subQuerys = new HashMap<String, SubQueryColumn>();
        paramCount = 0;
        query.where(unionWhere(root, query, builder));

        List<Order> orderList = pingOrder(root, builder);
        if (orderList != null && orderList.size() > 0) {
            query.orderBy(orderList);
        }
        return query.getGroupRestriction();
    }

    /**
     * Creates a WHERE clause for a sub query of the referenced entity in form of a {@link Predicate} for the given
     * {@link Root} and {@link CriteriaQuery}.
     *
     * @param root sub query root
     * @param query sub query condition
     * @param builder the criteria builder
     * @return the predicate of query
     * @see Specification#toPredicate(Root,
     *      CriteriaQuery, CriteriaBuilder)
     *
     * @author ma_b
     */
    public Predicate toPredicate(Root<T> root, Subquery<?> query, CriteriaBuilder builder) {
        parmeters = new HashMap<String, Object>();
        joins = new HashMap<String, Join<?, ?>>();
        fetchs = new HashMap<String, Fetch<?, ?>>();
        subQuerys = new HashMap<String, SubQueryColumn>();
        paramCount = 0;
        query.where(unionWhere(root, query, builder));

        return query.getGroupRestriction();
    }

    /**
     * Creates a UNION WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
     * {@link Root} and {@link CriteriaQuery}.
     *
     * @param root query root
     * @param query query condition
     * @param builder the criteria builder
     * @return the predicate of query
     *
     */
    private Predicate[] unionWhere(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // List<Predicate> pdList = null;
        logger.debug("union where start : ");

        Predicate[] pdArray = (Predicate[])addAll(pingWhere(root, builder, condition), pingSubQuery(root, query, builder));

        return pdArray;
    }
    public static Object[] clone(Object[] array) {
        return array == null?null:(Object[])((Object[])array.clone());
    }

    public static Object[] addAll(Object[] array1, Object[] array2) {
        if(array1 == null) {
            return clone(array2);
        } else if(array2 == null) {
            return clone(array1);
        } else {
            Object[] joinedArray = (Object[])((Object[])Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length));
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);

            try {
                System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
                return joinedArray;
            } catch (ArrayStoreException var6) {
                Class type1 = array1.getClass().getComponentType();
                Class type2 = array2.getClass().getComponentType();
                if(!type1.isAssignableFrom(type2)) {
                    throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName());
                } else {
                    throw var6;
                }
            }
        }
    }

    /**
     * Creates a UNION WHERE clause for a sub query of the referenced entity in form of a {@link Predicate} for the
     * given {@link Root} and {@link CriteriaQuery}.
     *
     * @param root sub query root
     * @param query sub query condition
     * @param builder the criteria builder
     * @return the predicate of query
     *
     */
    private Predicate[] unionWhere(Root<T> root, Subquery<?> query, CriteriaBuilder builder) {
        // List<Predicate> pdList = null;
        logger.debug("union where start : ");

        Predicate[] pdArray = (Predicate[])addAll(pingWhere(root, builder, condition), pingSubQuery(root, query, builder));

        return pdArray;
    }

    /**
     * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
     * {@link Root} and {@link CriteriaQuery}.
     *
     * @param root query root
     * @param builder the criteria builder
     * @param condition query condition object
     * @return the predicate of query
     *
     */
    private Predicate[] pingWhere(Root<?> root, CriteriaBuilder builder, Object condition) {
        Object conditionObj = condition;
        if (conditionObj == null) {
            return new Predicate[0];
        }

        if (conditionObj instanceof Filter) {
            conditionObj = ((Filter<?>) conditionObj).convert();
        }

        logger.debug("ping where start : ");

        List<Predicate> pdList = new ArrayList<Predicate>();
        Predicate predicate = null;

        fetchTable(root, conditionObj);
        joinTable(root, conditionObj);

        if (conditionObj instanceof BaseCondition) {
            Method[] methods = conditionObj.getClass().getDeclaredMethods();

            for (int i = 0; i < methods.length; i++) {

                String fieldName = methods[i].getName();

                Object value = null;

                try {
                    if (fieldName.startsWith("get")) {
                        value = methods[i].invoke(conditionObj);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    // Modify for bug#0164691 at 2014/08/25 by zhangtao1 start.
                    if (fieldName.startsWith("get")) {
                        fieldName = fieldName.substring(NumberConstant.IntDef.INT_THREE);
                    }
                    // Modify for bug#0164691 at 2014/08/25 by zhangtao1 end.
                    String firsWord = fieldName.substring(0, 1).toLowerCase();
                    fieldName = firsWord + fieldName.substring(1, fieldName.length());
                    if (value instanceof BaseColumn) {
                        BaseColumn tempColumn = (BaseColumn) value;
                        if (!StringUtil.isEmpty(tempColumn.getColumnName())) {
                            fieldName = tempColumn.getColumnName();
                        }
                        //Mantis#83131 modified by liu_bing start
                        if(tempColumn.getQueryColumn()!=null){
                            value=tempColumn.getQueryColumn();
                        }
                        //Mantis#83131 modified by liu_bing end
                    }
                    predicate = pingQuery(value, fieldName, root, builder, true);
                    if (predicate != null) {
                        pdList.add(predicate);
                    }
                }
            }
        } else if (conditionObj instanceof HashMap<?, ?>) {
            Object objParams = ((HashMap<?, ?>) conditionObj).get(PARAMETERS);
            if (objParams instanceof List<?>) {
                List<?> paramList = (List<?>) objParams;
                if (paramList != null && paramList.size() > 0) {
                    for (Object param : paramList) {
                        if (param instanceof Object[]) {
                            Object[] paramArray = (Object[]) param;
                            if (paramArray.length > 1) {
                                predicate = pingQuery(paramArray[1], paramArray[0].toString(), root, builder, false);
                                if (predicate != null) {
                                    pdList.add(predicate);
                                }
                            }
                        }
                    }
                }
            }
        }

        Predicate[] pdArray = new Predicate[pdList.size()];
        pdList.toArray(pdArray);
        return pdArray;
    }

    /**
     * Creates a WHERE clause for a sub query of the referenced entity in form of a {@link Predicate} for the given
     * {@link Root} and {@link CriteriaQuery}.
     *
     * @param root sub query root
     * @param query the query object
     * @param builder the criteria builder
     * @return the predicate of sub query
     */
    @SuppressWarnings("unchecked")
    private Predicate[] pingSubQuery(Root<T> root, AbstractQuery<?> query, CriteriaBuilder builder) {
        if (subQuerys.size() == 0) {
            return null;
        }
        logger.debug("ping sub query start : ");

        List<Predicate> pdList = new ArrayList<Predicate>();
        Predicate predicate = null;
        Set<Map.Entry<String, SubQueryColumn>> subQuerySet = subQuerys.entrySet();

        for (Map.Entry<String, SubQueryColumn> subQueryObj : subQuerySet) {

            SubQueryColumn tempSubQuery = subQueryObj.getValue();

            Subquery<Object> subQuery = query.subquery(Object.class);
            try {
                Root<?> subRoot = subQuery.from(Class.forName(tempSubQuery.getSubQueryTable()));
                String subQuerySelect = "";
                if (tempSubQuery.getSubQuerySelect().isEmpty()) {
                    subQuerySelect = subQueryObj.getKey();
                } else {
                    subQuerySelect = tempSubQuery.getSubQuerySelect();
                }
                Expression<?> tempSubPath = getColumnPath(subQuerySelect, subRoot);
                subQuery.select((Expression<Object>) tempSubPath);
                subQuery.where(pingWhere(subRoot, builder, tempSubQuery.getSubQueryWhere()));

                Path<?> tempPath = getColumnPath(subQueryObj.getKey(), tempSubQuery.getParentTable(), root);

                if (tempSubQuery.getOperator().equals(BaseOperatorEnum.IN)) {
                    predicate = builder.in(tempPath).value(subQuery);
                    if (predicate != null) {
                        pdList.add(predicate);
                    }
                } else if (tempSubQuery.getOperator().equals(BaseOperatorEnum.NOTIN)) {
                    predicate = builder.not(builder.in(tempPath).value(subQuery));
                    if (predicate != null) {
                        pdList.add(predicate);
                    }
                } else {
                    predicate = builder.equal(tempPath, subQuery);
                    if (predicate != null) {
                        pdList.add(predicate);
                    }
                }
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            }
        }

        Predicate[] pdArray = new Predicate[pdList.size()];
        pdList.toArray(pdArray);
        return pdArray;
    }

    /**
     * Set JOIN TABLE condition.
     *
     * @param root query root
     * @param conditionObj conditionObj
     *
     */
    private void joinTable(Root<?> root, Object conditionObj) {
        List<?> joinTables = null;
        if (conditionObj instanceof BaseCondition) {
            joinTables = ((BaseCondition<?>) conditionObj).getJoinTables();
            // try {
            // Method joinMethod = conditionObj.getClass().getMethod("getJoinTables");
            // try {
            // joinTables = (List<BaseJoin>) joinMethod.invoke(conditionObj);
            // } catch (IllegalArgumentException e) {
            // e.printStackTrace();
            // } catch (IllegalAccessException e) {
            // e.printStackTrace();
            // } catch (InvocationTargetException e) {
            // e.printStackTrace();
            // }
            // } catch (SecurityException e1) {
            // e1.printStackTrace();
            // } catch (NoSuchMethodException e1) {
            // e1.printStackTrace();
            // }
        } else if (conditionObj instanceof HashMap) {
            // HashMap<String, Object> condMap = (HashMap<String, Object>) conditionObj;
            // joinTables = (List<BaseJoin>) condMap.get(JOIN_TABLES);
            Object objJoin = ((HashMap<?, ?>) conditionObj).get(JOIN_TABLES);
            if (objJoin != null && objJoin instanceof List<?>) {
                joinTables = (List<?>) objJoin;
            }
        }

        if (joinTables != null && joinTables.size() > 0) {
            for (Object joinTable : joinTables) {
                if (joinTable instanceof BaseJoin) {
                    pineJoin((BaseJoin) joinTable, root);
                }
            }
        }
    }

    /**
     * Set FETCH TABLE condition.
     *
     * @param root query root
     * @param conditionObj conditionObj
     *
     */
    private void fetchTable(Root<?> root, Object conditionObj) {
        List<?> fetchTables = null;

        if (conditionObj instanceof BaseCondition) {
            fetchTables = ((BaseCondition<?>) conditionObj).getFetchTables();
            // try {
            // Method fetchMethod = conditionObj.getClass().getMethod("getFetchTables");
            // try {
            // fetchTables = (List<BaseJoin>) fetchMethod.invoke(conditionObj);
            // } catch (IllegalArgumentException e) {
            // e.printStackTrace();
            // } catch (IllegalAccessException e) {
            // e.printStackTrace();
            // } catch (InvocationTargetException e) {
            // e.printStackTrace();
            // }
            // } catch (SecurityException e1) {
            // e1.printStackTrace();
            // } catch (NoSuchMethodException e1) {
            // e1.printStackTrace();
            // }
        } else if (conditionObj instanceof HashMap<?, ?>) {
            // HashMap<String, Object> condMap = (HashMap<String, Object>) conditionObj;
            Object objFetch = ((HashMap<?, ?>) conditionObj).get(FETCH_TABLES);
            if (objFetch != null && objFetch instanceof List<?>) {
                fetchTables = (List<?>) objFetch;
            }
        }

        if (fetchTables != null && fetchTables.size() > 0) {
            for (Object fetchTable : fetchTables) {
                if (fetchTable instanceof BaseJoin) {
                    if (isFetch) {
                        pineFetch((BaseJoin) fetchTable, root);
                    } else {
                        pineJoin((BaseJoin) fetchTable, root);
                    }
                }
            }
        }
    }

    /**
     * Set FETCH TABLE condition.
     *
     * @param fetchTable fetchTable
     * @param root query root
     */
    private void pineFetch(BaseJoin fetchTable, Root<?> root) {
        logger.debug("ping fetch : " + fetchTable.getTableName());
        String[] tables = fetchTable.getTableName().split(SPLIT);
        String parentTable = "";
        for (String table : tables) {
            if (!fetchs.containsKey(table)) {
                if (parentTable.equals("")) {
                    Fetch<?, ?> fetch = root.fetch(table, fetchTable.getJoinType());
                    Join<?, ?> join = (Join<?, ?>) fetch;
                    fetchs.put(table, fetch);
                    joins.put(table, join);
                } else {
                    Fetch<?, ?> fetch = fetchs.get(parentTable).fetch(table, fetchTable.getJoinType());
                    Join<?, ?> join = (Join<?, ?>) fetch;
                    fetchs.put(table, fetch);
                    joins.put(table, join);
                }
            }
            parentTable = table;
        }
    }

    /**
     * Set JOIN TABLE condition.
     *
     * @param joinTable joinTable
     * @param root query root
     * @return String
     */
    private String pineJoin(BaseJoin joinTable, Root<?> root) {
        logger.debug("ping join : " + joinTable.getTableName());
        String[] tables = joinTable.getTableName().split(SPLIT);
        String parentTable = "";
        for (String table : tables) {
            if (!joins.containsKey(table)) {
                if (parentTable.equals("")) {
                    Join<?, ?> join = root.join(table, joinTable.getJoinType());
                    joins.put(table, join);
                } else {
                    Join<?, ?> join = joins.get(parentTable).join(table, joinTable.getJoinType());
                    joins.put(table, join);
                }
            }
            parentTable = table;
        }
        return tables[tables.length - 1];
    }

    /**
     * Create query condition.
     *
     * @param columnValue the column value
     * @param fieldName the field name
     * @param root query root
     * @param builder the criteria builder
     * @param isScreenCondition if screen condition then true, otherwise is false
     * @return the predicate of query
     */
    private Predicate pingQuery(Object columnValue, String fieldName, Root<?> root, CriteriaBuilder builder,
            boolean isScreenCondition) {
        Predicate tempPredicate = null;
        if (columnValue != null) {
            logger.debug("ping query filter is: " + fieldName);
            if (columnValue instanceof BigDecimalColumn) {
                BigDecimalColumn column = (BigDecimalColumn) columnValue;
                tempPredicate = pingBigDecimalColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                        builder, column.getOperator());
            } else if (columnValue instanceof BigDecimalsColumn) {
                BigDecimalsColumn column = (BigDecimalsColumn) columnValue;
                tempPredicate = pingBigDecimalsColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                        builder, column.getOperator());
            } else if (columnValue instanceof DateColumn) {
                DateColumn column = (DateColumn) columnValue;
                tempPredicate = pingDateColumn(column.getColumn(), fieldName, column.getParentTable(), root, builder,
                        column.getOperator());
            } else if (columnValue instanceof DatesColumn) {
                DatesColumn column = (DatesColumn) columnValue;
                tempPredicate = pingDatesColumn(column.getColumn(), fieldName, column.getParentTable(), root, builder);
            } else if (columnValue instanceof IntegerColumn) {
                IntegerColumn column = (IntegerColumn) columnValue;
                tempPredicate = pingIntegerColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                        builder, column.getOperator());
            } else if (columnValue instanceof IntegersColumn) {
                IntegersColumn column = (IntegersColumn) columnValue;
                tempPredicate = pingIntegersColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                        builder, column.getOperator());
            } else if (columnValue instanceof LongColumn) {
                LongColumn column = (LongColumn) columnValue;
                tempPredicate = pingLongColumn(column.getColumn(), fieldName, column.getParentTable(), root, builder,
                        column.getOperator());
            } else if (columnValue instanceof LongsColumn) {
                LongsColumn column = (LongsColumn) columnValue;
                tempPredicate = pingLongsColumn(column.getColumn(), fieldName, column.getParentTable(), root, builder,
                        column.getOperator(), column.isIncludeNull());
            } else if (columnValue instanceof StringColumn) {
                StringColumn column = (StringColumn) columnValue;
                if (column.getOperator().equals(BaseOperatorEnum.IN)
                        || column.getOperator().equals(BaseOperatorEnum.NOTIN)
                        || column.getOperator().equals(BaseOperatorEnum.BETWEEN)) {
                    String[] columns = column.getColumn().split(",");
                    tempPredicate = pingStringsColumn(columns, fieldName, column.getParentTable(), root, builder,
                            column.getOperator());
                } else if (column.getOperator().equals(BaseOperatorEnum.LIKE_IGNORE_CASE)) {
                    tempPredicate = pingStringColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                            builder, column.getOperator(), true);
                } else {
                    tempPredicate = pingStringColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                            builder, column.getOperator(), isScreenCondition);
                }
            } else if (columnValue instanceof StringsColumn) {
                StringsColumn column = (StringsColumn) columnValue;
                tempPredicate = pingStringsColumn(column.getColumn(), fieldName, column.getParentTable(), root,
                        builder, column.getOperator());
            } else if (columnValue instanceof BigDecimal) {
                tempPredicate = pingBigDecimalColumn((BigDecimal) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.EQUAL);
            } else if (columnValue instanceof BigDecimal[]) {
                tempPredicate = pingBigDecimalsColumn((BigDecimal[]) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.IN);
            } else if (columnValue instanceof Date) {
                tempPredicate = pingDateColumn((Date) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.EQUAL);
            } else if (columnValue instanceof Date[]) {
                tempPredicate = pingDatesColumn((Date[]) columnValue, fieldName, null, root, builder);
            } else if (columnValue instanceof Integer) {
                tempPredicate = pingIntegerColumn((Integer) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.EQUAL);
            } else if (columnValue instanceof Integer[]) {
                tempPredicate = pingIntegersColumn((Integer[]) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.IN);
            } else if (columnValue instanceof Long) {
                tempPredicate = pingLongColumn((Long) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.EQUAL);
            } else if (columnValue instanceof Long[]) {
                tempPredicate = pingLongsColumn((Long[]) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.IN, false);
            } else if (columnValue instanceof String) {
                tempPredicate = pingStringColumn((String) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.EQUAL, isScreenCondition);
            } else if (columnValue instanceof String[]) {
                tempPredicate = pingStringsColumn((String[]) columnValue, fieldName, null, root, builder,
                        BaseOperatorEnum.IN);
            } else if (columnValue instanceof SubQueryColumn) {
                subQuerys.put(fieldName, (SubQueryColumn) columnValue);
            } else if (columnValue instanceof NullColumn) {
                NullColumn column = (NullColumn) columnValue;
                tempPredicate = pingNullColumn(fieldName, column.getParentTable(), root, builder);
            }
            paramCount++;
        }
        return tempPredicate;
    }

    /**
     * Create condition for BigDecimal column.
     *
     * @param column the column value
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    private Predicate pingBigDecimalColumn(BigDecimal column, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (column != null) {
            Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
            ParameterExpression<BigDecimal> tempPE = builder.parameter(BigDecimal.class, fieldName + paramCount);
            tempPredicate = builder.equal(tempPath, tempPE);
            parmeters.put(fieldName + paramCount, column);
            if (operator.equals(BaseOperatorEnum.NOTEQUAL)) {
                tempPredicate = builder.not(tempPredicate);
            }
        }
        return tempPredicate;
    }

    /**
     * Create condition for BigDecimal column array.
     *
     * @param columns the column value array
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    @SuppressWarnings("unchecked")
    private Predicate pingBigDecimalsColumn(BigDecimal[] columns, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (columns != null && columns.length > 0) {
            if (operator.equals(BaseOperatorEnum.BETWEEN)) {
                Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
                ParameterExpression<BigDecimal> tempPE1 = builder.parameter(BigDecimal.class, fieldName + paramCount
                        + 0);
                if (columns.length >= NumberConstant.IntDef.INT_TWO) {
                    if (columns[0] != null && columns[1] != null) {
                        ParameterExpression<BigDecimal> tempPE2 = builder.parameter(BigDecimal.class, fieldName
                                + paramCount + 1);
                        tempPredicate = builder.between((Expression<BigDecimal>) tempPath, tempPE1, tempPE2);
                        parmeters.put(fieldName + paramCount + 0, columns[0]);
                        parmeters.put(fieldName + paramCount + 1, columns[1]);
                    } else if (columns[0] != null && !"".equals(columns[0])) {
                        tempPredicate = builder.greaterThanOrEqualTo((Expression<BigDecimal>) tempPath, tempPE1);
                        parmeters.put(fieldName + paramCount + 0, columns[0]);
                    } else if (columns[1] != null && !"".equals(columns[1])) {
                        tempPredicate = builder.lessThanOrEqualTo((Expression<BigDecimal>) tempPath, tempPE1);
                        parmeters.put(fieldName + paramCount + 0, columns[1]);
                    }
                }
            } else {
                tempPredicate= pingInArrayColumn(BigDecimal.class, columns, fieldName, parentTable,
                        root, builder, operator, 0, false);
            }
        }
        return tempPredicate;
    }

    /**
     * Create condition for Date column.
     *
     * @param column the column value
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    @SuppressWarnings("unchecked")
    private Predicate pingDateColumn(Date column, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (column != null) {
            Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
            ParameterExpression<Date> tempPE = builder.parameter(Date.class, fieldName + paramCount);
            if (operator.equals(BaseOperatorEnum.GREATERTHAN)) {
                tempPredicate = builder.greaterThan((Expression<Date>) tempPath, tempPE);
            } else if (operator.equals(BaseOperatorEnum.LESSTHAN)) {
                tempPredicate = builder.lessThan((Expression<Date>) tempPath, tempPE);
            } else {
                tempPredicate = builder.equal(tempPath, tempPE);
            }
            parmeters.put(fieldName + paramCount, column);
            if (operator.equals(BaseOperatorEnum.NOTEQUAL)) {
                tempPredicate = builder.not(tempPredicate);
            }
        }
        return tempPredicate;
    }

    /**
     * Create condition for Date column array.
     *
     * @param columns the column value array
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @return the predicate of query
     */
    @SuppressWarnings("unchecked")
    private Predicate pingDatesColumn(Date[] columns, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder) {
        Predicate tempPredicate = null;
        if (columns != null && columns.length > 0) {
            if (columns.length >= NumberConstant.IntDef.INT_TWO && columns[0] == null && columns[1] == null) {
                return tempPredicate;
            }

            Date[] dateCondition = new Date[NumberConstant.IntDef.INT_TWO];
            if (columns[0] != null) {
                // the from condition is 00:00:00 in this date with owner time zone
                dateCondition[0] = columns[0];
            }
            if (columns.length > 1 && columns[1] != null) {
                // Modify for bug#0135480 at 2013/12/09 by lijie4 Start.
                // the to condition is 23:59:59.999 in this date with owner time zone
                dateCondition[1] = DateTimeUtil.add(columns[1], 0, 0, 0, TO_HOUR, TO_MINUTE, TO_SECOND, TO_MILLI);
                // Modify for bug#0135480 at 2013/12/09 by lijie4 End.
            }

            Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
            ParameterExpression<Date> tempPE1 = builder.parameter(Date.class, fieldName + paramCount + 0);
            if (dateCondition.length >= NumberConstant.IntDef.INT_TWO) {
                if (dateCondition[0] != null && dateCondition[1] != null) {
                    ParameterExpression<Date> tempPE2 = builder.parameter(Date.class, fieldName + paramCount + 1);
                    tempPredicate = builder.between((Expression<Date>) tempPath, tempPE1, tempPE2);
                    parmeters.put(fieldName + paramCount + 0, dateCondition[0]);
                    parmeters.put(fieldName + paramCount + 1, dateCondition[1]);
                } else if (dateCondition[0] != null) {
                    tempPredicate = builder.greaterThanOrEqualTo((Expression<Date>) tempPath, tempPE1);
                    parmeters.put(fieldName + paramCount + 0, dateCondition[0]);
                } else if (dateCondition[1] != null) {
                    tempPredicate = builder.lessThanOrEqualTo((Expression<Date>) tempPath, tempPE1);
                    parmeters.put(fieldName + paramCount + 0, dateCondition[1]);
                }
            }
        }
        return tempPredicate;
    }

    /**
     * Create condition for Integer column.
     *
     * @param column the column value
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    @SuppressWarnings("unchecked")
    private Predicate pingIntegerColumn(Integer column, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (column != null) {
            Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
            ParameterExpression<Integer> tempPE = builder.parameter(Integer.class, fieldName + paramCount);
            if (operator.equals(BaseOperatorEnum.NOTEQUAL)) {
                tempPredicate = builder.notEqual(tempPath, tempPE);
            } else if (operator.equals(BaseOperatorEnum.GREATERTHAN)) {
                tempPredicate = builder.greaterThan((Expression<Integer>) tempPath, tempPE);
            } else if (operator.equals(BaseOperatorEnum.LESSTHAN)) {
                tempPredicate = builder.lessThan((Expression<Integer>) tempPath, tempPE);
            } else {
                tempPredicate = builder.equal(tempPath, tempPE);
            }
            parmeters.put(fieldName + paramCount, column);
        }
        return tempPredicate;
    }

    /**
     * Create condition for Integer column array.
     *
     * @param columns the column value array
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    @SuppressWarnings("unchecked")
    private Predicate pingIntegersColumn(Integer[] columns, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (columns != null && columns.length > 0) {
            if (operator.equals(BaseOperatorEnum.BETWEEN)) {
                Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
                ParameterExpression<Integer> tempPE1 = builder.parameter(Integer.class, fieldName + paramCount + 0);
                if (columns.length >= NumberConstant.IntDef.INT_TWO) {
                    if (columns[0] != null && columns[1] != null) {
                        ParameterExpression<Integer> tempPE2 = builder.parameter(Integer.class, fieldName + paramCount
                                + 1);
                        tempPredicate = builder.between((Expression<Integer>) tempPath, tempPE1, tempPE2);
                        parmeters.put(fieldName + paramCount + 0, columns[0]);
                        parmeters.put(fieldName + paramCount + 1, columns[1]);
                    } else if (columns[0] != null && !"".equals(columns[0])) {
                        tempPredicate = builder.greaterThanOrEqualTo((Expression<Integer>) tempPath, tempPE1);
                        parmeters.put(fieldName + paramCount + 0, columns[0]);
                    } else if (columns[1] != null && !"".equals(columns[1])) {
                        tempPredicate = builder.lessThanOrEqualTo((Expression<Integer>) tempPath, tempPE1);
                        parmeters.put(fieldName + paramCount + 0, columns[1]);
                    }
                }
            } else {
                tempPredicate= pingInArrayColumn(Integer.class, columns, fieldName, parentTable,
                        root, builder, operator, 0, false);
            }
        }
        return tempPredicate;
    }

    /**
     * Create condition for Long column.
     *
     * @param column the column value
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     *
     */
    private Predicate pingLongColumn(Long column, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (column != null) {
            Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
            ParameterExpression<Long> tempPE = builder.parameter(Long.class, fieldName + paramCount);
            if (operator.equals(BaseOperatorEnum.NOTEQUAL)) {
                tempPredicate = builder.notEqual(tempPath, tempPE);
            } else if (operator.equals(BaseOperatorEnum.GREATERTHAN)) {
                tempPredicate = builder.greaterThan((Expression<Long>) tempPath, tempPE);
            } else if (operator.equals(BaseOperatorEnum.LESSTHAN)) {
                tempPredicate = builder.lessThan((Expression<Long>) tempPath, tempPE);
            } else {
                tempPredicate = builder.equal(tempPath, tempPE);
            }
            parmeters.put(fieldName + paramCount, column);
        }
        return tempPredicate;
    }

    /**
     * Create condition for Long column array.
     *
     * @param columns the column value array
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    private Predicate pingLongsColumn(Long[] columns, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator, boolean includeNull) {
        Predicate tempPredicate = null;
        tempPredicate= pingInArrayColumn(Long.class, columns, fieldName, parentTable,
                root, builder, operator, 0, includeNull);
        return tempPredicate;
    }

    /**
     * Create condition for String column.
     *
     * @param column the column value
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @param ignoreCaseFlag if flag is true, will query insensitively
     * @return the predicate of query
     */
    private Predicate pingStringColumn(String column, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator, boolean ignoreCaseFlag) {
        Predicate tempPredicate = null;
        if (column != null && !column.isEmpty()) {
            Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
            ParameterExpression<String> tempPE = builder.parameter(String.class, fieldName + paramCount);
            if (operator.equals(BaseOperatorEnum.NOTEQUAL)) {
                // !=
                tempPredicate = builder.notEqual(tempPath, tempPE);
                parmeters.put(fieldName + paramCount, column);
            } else if (operator.equals(BaseOperatorEnum.GREATERTHAN)) {
                // >
                tempPredicate = builder.greaterThan((Expression<String>) tempPath, tempPE);
                parmeters.put(fieldName + paramCount, column);
            } else if (operator.equals(BaseOperatorEnum.LESSTHAN)) {
                // <
                tempPredicate = builder.lessThan((Expression<String>) tempPath, tempPE);
                parmeters.put(fieldName + paramCount, column);
            } else {
                Expression<String> conditionPath = (Expression<String>) tempPath;
                String conditionValue = column;
                if (ignoreCaseFlag) {
                    logger.debug("ignoreCaseFlag=" + ignoreCaseFlag);
                    // if it is filter condition that input from screen, ignore case to search
                    conditionPath = builder.upper(conditionPath);
                    conditionValue = column.toUpperCase();
                }

                if (operator.equals(BaseOperatorEnum.LIKE) || operator.equals(BaseOperatorEnum.LIKE_IGNORE_CASE)
                        || column.indexOf(CHAR_STAR) != -1) {
                    logger.debug("execute LIKE");
                    // if has * in condition value, then do like search
                    // like
                    tempPredicate = new LikeNvarcharPredicate((CriteriaBuilderImpl) builder, conditionPath, tempPE,
                            CHAR_VERTICAL);
                    parmeters.put(fieldName + paramCount,changeToFuzzyCondition(conditionValue));
                } else {
                    // =
                    tempPredicate = builder.equal(conditionPath, tempPE);
                    parmeters.put(fieldName + paramCount, conditionValue);
                }
            }
        }
        return tempPredicate;
    }

    public static String changeToFuzzyCondition(String condition) {
        String changedCond = condition;
        if (!StringUtil.isEmpty(condition)) {
            if (condition.indexOf(VERTICAL) != -1) {
                changedCond = changedCond.replace(VERTICAL, VERTICAL + VERTICAL);
            }
            if (condition.indexOf(PRE) != -1) {
                changedCond = changedCond.replace(PRE, VERTICAL + PRE);
            }
            if (condition.indexOf(UNDERLINE) != -1) {
                changedCond = changedCond.replace(UNDERLINE,VERTICAL +UNDERLINE);
            }
            changedCond = changedCond.replace(CHAR_STAR, CHAR_PRE);
        }
        return changedCond;
    }

    /**
     * Create condition for String column array.
     *
     * @param columns the column value array
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @param operator operator
     * @return the predicate of query
     */
    @SuppressWarnings("unchecked")
    private Predicate pingStringsColumn(String[] columns, String fieldName, String parentTable, Root<?> root,
            CriteriaBuilder builder, BaseOperatorEnum operator) {
        Predicate tempPredicate = null;
        if (columns != null && columns.length > 0) {
            if (operator.equals(BaseOperatorEnum.BETWEEN)) {
                Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
                ParameterExpression<String> tempPE1 = builder.parameter(String.class, fieldName + paramCount + 0);
                if (columns.length >= NumberConstant.IntDef.INT_TWO) {
                    if (columns[0] != null && !"".equals(columns[0]) && columns[1] != null && !"".equals(columns[1])) {
                        ParameterExpression<String> tempPE2 = builder.parameter(String.class, fieldName + paramCount
                                + 1);
                        tempPredicate = builder.between((Expression<String>) tempPath, tempPE1, tempPE2);
                        parmeters.put(fieldName + paramCount + 0, columns[0]);
                        parmeters.put(fieldName + paramCount + 1, columns[1]);
                    } else if (columns[0] != null && !"".equals(columns[0])) {
                        tempPredicate = builder.greaterThanOrEqualTo((Expression<String>) tempPath, tempPE1);
                        parmeters.put(fieldName + paramCount + 0, columns[0]);
                    } else if (columns[1] != null && !"".equals(columns[1])) {
                        tempPredicate = builder.lessThanOrEqualTo((Expression<String>) tempPath, tempPE1);
                        parmeters.put(fieldName + paramCount + 0, columns[1]);
                    }
                }
            } else {
                tempPredicate= pingInArrayColumn(String.class, columns, fieldName, parentTable,
                        root, builder, operator, 0, false);
//                Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
//                ParameterExpression<String> tempPE;
//                In<String> in = builder.in((Expression<String>) tempPath);
//                for (int i = 0; i < columns.length; i++) {
//                    String tempParam = columns[i];
//                    tempPE = builder.parameter(String.class, fieldName + paramCount + i);
//                    in = in.value(tempPE);
//                    parmeters.put(fieldName + paramCount + i, tempParam);
//                }
//                tempPredicate = in;
//                if (operator.equals(BaseOperatorEnum.NOTIN)) {
//                    tempPredicate = builder.not(tempPredicate);
//                }
            }
        }
        return tempPredicate;
    }

    /**
     * Create condition for IS NULL column.
     *
     * @param fieldName the field name
     * @param parentTable the parent table name
     * @param root query root
     * @param builder the criteria builder
     * @return the predicate of query
     */
    private Predicate pingNullColumn(String fieldName, String parentTable, Root<?> root, CriteriaBuilder builder) {
        Predicate tempPredicate = null;
        Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
        tempPredicate = builder.isNull(tempPath);
        return tempPredicate;
    }

    /**
     * Create Order condition for query.
     *
     * @param root query root
     * @param builder the criteria builder
     * @return the order condition list
     *
     */
    private List<Order> pingOrder(Root<T> root, CriteriaBuilder builder) {
        Sort sort = null;
        if (condition instanceof BaseCondition<?>) {
            sort = ((BaseCondition<?>) condition).getSort();
            // try {
            // Method sortMethod = condition.getClass().getMethod("getSort");
            // try {
            // sort = (Sort) sortMethod.invoke(condition);
            // } catch (IllegalArgumentException e) {
            // e.printStackTrace();
            // } catch (IllegalAccessException e) {
            // e.printStackTrace();
            // } catch (InvocationTargetException e) {
            // e.printStackTrace();
            // }
            // } catch (SecurityException e) {
            // e.printStackTrace();
            // } catch (NoSuchMethodException e) {
            // e.printStackTrace();
            // }
        } else if (condition instanceof HashMap<?, ?>) {
            // HashMap<String, Object> condMap = (HashMap<String, Object>) condition;
            // sort = (Sort) condMap.get(SORT);
            Object objSort = ((HashMap<?, ?>) condition).get(SORT);
            if (objSort instanceof Sort) {
                sort = (Sort) objSort;
            }
        }

        if (sort != null && sort.iterator() != null && sort.iterator().hasNext()) {
            logger.debug("ping order start : ");
            return toOrders(sort, root, builder);
        }
        return null;
    }

    /**
     * Convert Sort condition to the Order condition list for query.
     *
     * @param sort the Sort condition
     * @param root query root
     * @param builder the criteria builder
     * @return List<javax.persistence.criteria.Order>
     */
    public List<Order> toOrders(Sort sort, Root<T> root, CriteriaBuilder builder) {
        List<Order> orders = new ArrayList<Order>();

        if (sort == null) {
            return orders;
        }
        logger.debug("to orders start : ");
        for (Sort.Order order : sort) {
            orders.add(toJpaOrder(order, root, builder));
        }

        return orders;
    }

    /**
     * Convert Sort.Order condition to the JPA Order condition for query.
     *
     * @param order the Sort.Order condition
     * @param root query root
     * @param builder the criteria builder
     * @return JPA Order condition
     */
    private Order toJpaOrder(Sort.Order order, Root<T> root,
            CriteriaBuilder builder) {
        logger.debug("to jpa order : " + order.getProperty());
        Expression<?> expression = getColumnPath(order.getProperty(), root);
        Order jpaOrder = null;
        if (order.isAscending()) {
            jpaOrder = builder.asc(expression);
        } else {
            jpaOrder = builder.desc(expression);
        }
        return jpaOrder;
    }

    /**
     * Get the column field path by property name.
     * 
     * @param property the property name of column
     * @param root query root
     * @return the Expression of this column
     */
    private Expression<?> getColumnPath(String property, Root<?> root) {
        int position = property.lastIndexOf(".");

        if (position == -1) {
            return getColumnPath(property, "", root);
        }
        String parentTables = property.substring(0, position);
        String fieldName = property.substring(position + 1);

        return getColumnPath(fieldName, parentTables, root);
    }

    /**
     * Get the column field path by property name.
     * 
     * @param fieldName the field name
     * @param parentTables the parent tables
     * @param root query root
     * @return the Expression of this column
     */
    private Path<?> getColumnPath(String fieldName, String parentTables, Root<?> root) {
        logger.debug("getColumnPath: " + fieldName);
        if (parentTables == null || parentTables.equals("")) {
            return root.get(fieldName);
        }
        Path<?> tempPath;
        if (PK.equals(parentTables)) {
            tempPath = root.get(parentTables).get(fieldName);
        } else {
            String tmpTable = pineJoin(new BaseJoin(parentTables), root);
            tempPath = joins.get(tmpTable).get(fieldName);
        }
        return tempPath;
    }

    /**
     * Get the query parameter map of query.
     * 
     * @return Map<String,Object> the query parameter map of query
     */
    public Map<String, Object> getParmeter() {
        return parmeters;
    }

    /**
     * If it is fetch query then return true.
     * 
     * @return fetch flag
     * 
     */
    public boolean isFetch() {
        return this.isFetch;
    }

    /**
     * Set fetch flag of query. If it is fetch query then set true.
     * 
     * @param isFetch fetch flag
     * 
     */
    public void setFetch(boolean isFetch) {
        this.isFetch = isFetch;
    }

    /**
     * If it is distinct query then return true.
     * 
     * @return distinct flag
     * 
     */
    public boolean isDistinct() {
        return this.isDistinct;
    }

    /**
     * Set distinct flag of query. If it is distinct query then set true.
     * 
     * @param isDistinct distinct flag
     * 
     */
    public void setDistinct(boolean isDistinct) {
        this.isDistinct = isDistinct;
    }

    /**
     * Get the lock mode type of query.
     * 
     * @return the lock mode type of query
     */
    public LockModeType getLockModeType() {
        return this.lockModeType;
    }

    /**
     * Set the lock mode type of query.
     * 
     * @param lockModeType the lock mode type of query
     */
    public void setLockModeType(LockModeType lockModeType) {
        this.lockModeType = lockModeType;

    }

    /**
     * Maximum size in "IN" clause, this limitation is for Oracle.
     */
    private final static int MAX_IN_LENGTH=1000;

    /**
     * Get a sub array.
     * @param arrayType Class of array element
     * @param originalArray Original array
     * @param start start index
     * @param length length of sub array
     * @param <CT> Type of array element
     * @return sub array
     */
    @SuppressWarnings("unchecked")
    private <CT> CT[] getSubArray(Class<CT> arrayType, CT[] originalArray, int start, int length){
        CT[] resultArray = (CT[]) Array.newInstance(arrayType, length);
        for(int index=0; index<length; index++){
            resultArray[index]=originalArray[start+index];
        }
        return resultArray;
    }

    /**
     * Build predicate for IN clause.
     * And auto split parameters more than 1000 into multi clause, for oracle.
     * @param columnType Class of column
     * @param columns value for IN clause
     * @param fieldName field name
     * @param parentTable parent table of column
     * @param root root predicate
     * @param builder predicate builder
     * @param operator  operator of clause, always equals IN here.
     * @param paramStartIndex index of parameter name
     * @param <CT> Type of column
     * @return predicate that include sql clause.
     */
    @SuppressWarnings("unchecked")
    private <CT extends Comparable<? super CT>>
            Predicate pingInArrayColumn(Class<CT> columnType, CT[] columns, String fieldName,
                                             String parentTable, Root<?> root,
                                             CriteriaBuilder builder, BaseOperatorEnum operator,
                                             int paramStartIndex, boolean includeNull
                                             ) {
        if(columns==null || columns.length==0) return null;

        if(columns.length>MAX_IN_LENGTH){
            logger.debug("START PING BIG IN ARRAY, column type="+
                    columnType.getSimpleName()+
                    ", size="+columns.length);
            List<CT[]> subColumnsList=new ArrayList<CT[]>();
            int index=0;
            while(index<columns.length){
                int length=columns.length-index;
                if(length>MAX_IN_LENGTH){
                    length=MAX_IN_LENGTH;
                }
                CT[] subArray=getSubArray(columnType, columns, index, length);
                subColumnsList.add(subArray);
                index+=length;
            }
            Predicate[] inPredicates=new Predicate[subColumnsList.size()];
            for(int i=0; i<subColumnsList.size(); i++){
                CT[] subColumns=subColumnsList.get(i);
                inPredicates[i]=pingInArrayColumn(columnType, subColumns, fieldName, parentTable,
                        root, builder, operator, i, includeNull);
            }
            if(operator.equals(BaseOperatorEnum.NOTIN)){
                return builder.and(inPredicates);
            }
            else{
                return builder.or(inPredicates);
            }
        }

        Predicate tempPredicate;

        Path<?> tempPath = getColumnPath(fieldName, parentTable, root);
        ParameterExpression<CT> tempPE;
        In<CT> in = builder.in((Expression<CT>) tempPath);
        StringBuilder paramNameBuilder=new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            paramNameBuilder.append(fieldName).append("_");
            paramNameBuilder.append(paramCount).append("_");
            paramNameBuilder.append(paramStartIndex).append("_");
            paramNameBuilder.append(i);
            CT tempParam = columns[i];
            tempPE = builder.parameter(columnType, paramNameBuilder.toString());
            in = in.value(tempPE);
            parmeters.put(paramNameBuilder.toString(), tempParam);
            paramNameBuilder.delete(0, paramNameBuilder.length());
        }
        tempPredicate = in;

        //by liu_bing for support NULL in SQL:IN operator,start
        if(includeNull){
            tempPredicate=builder.or(tempPredicate, builder.isNull(tempPath));
        }
        //by liu_bing for support NULL in SQL:IN operator,end
        if (operator.equals(BaseOperatorEnum.NOTIN)) {
            tempPredicate = builder.not(tempPredicate);
        }
        return tempPredicate;
    }

}
