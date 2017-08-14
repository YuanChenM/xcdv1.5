/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.condition;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * <p>
 * Query condition base class.
 * </p>
 * 
 * @param <T> the entity type
 * 
 * @author zhou_ke
 */
public abstract class BaseCondition<T> {

    List<BaseJoin> joinTables;
    List<BaseJoin> fetchTables;
    Sort sort;

    /**
     * Add the sort condition for query.
     * 
     * @param direction the direction {@link Direction}
     * @param fieldNames the sort field name
     * 
     * @author zhou_ke
     */
    public void addSort(Direction direction, String... fieldNames) {
        Direction queryDirection = direction;
        if (queryDirection == null) {
            queryDirection = Direction.ASC;
        }
        if (this.sort == null) {
            this.sort = new Sort(queryDirection, fieldNames);
        } else {
            this.sort = this.sort.and(new Sort(queryDirection, fieldNames));
        }
    }

    /**
     * <p>
     * get the joinTables.
     * </p>
     * 
     * @return joinTables
     * 
     * @author zhou_ke
     */
    public List<BaseJoin> getJoinTables() {
        return this.joinTables;
    }

    /**
     * <p>
     * set the joinTables.
     * </p>
     * 
     * @param joinTables joinTables
     * 
     * @author zhou_ke
     */
    public void setJoinTables(List<BaseJoin> joinTables) {
        this.joinTables = joinTables;
    }

    /**
     * <p>
     * get the fetchTables.
     * </p>
     * 
     * @return fetchTables
     * 
     * @author zhou_ke
     */
    public List<BaseJoin> getFetchTables() {
        return this.fetchTables;
    }

    /**
     * <p>
     * set the fetchTables.
     * </p>
     * 
     * @param fetchTables fetchTables
     * 
     * @author zhou_ke
     */
    public void setFetchTables(List<BaseJoin> fetchTables) {
        this.fetchTables = fetchTables;
    }

    /**
     * <p>
     * get the sort.
     * </p>
     * 
     * @return sort
     * 
     * @author zhou_ke
     */
    public Sort getSort() {
        return this.sort;
    }

    /**
     * <p>
     * set the sort.
     * </p>
     * 
     * @param sort sort
     * 
     * @author zhou_ke
     */
    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
