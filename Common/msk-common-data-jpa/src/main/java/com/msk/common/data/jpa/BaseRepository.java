/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FlushModeType;

import com.msk.common.data.jpa.condition.CommonSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * 
 * <p>
 * The base class of DAO class.
 * </p>
 * 
 * @param <T> the entity type
 * @param <ID> the entity's PK type
 * 
 * @author zhou_ke
 */
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * Returns a single entity matching the given {@link CommonSpecification}.
     * 
     * @param spec the query condition
     * @return the query result
     */
    T findOne(CommonSpecification<T> spec);

    /**
     * Returns a single entity matching the given {@link CommonSpecification}.
     * 
     * @param spec the query condition
     * @param isFetch fetch flag
     * @return the query result
     */
    T findOne(CommonSpecification<T> spec, boolean isFetch);

    /**
     * Returns a single entity matching the given {@link CommonSpecification} with a flush mode.
     * 
     * @param spec the query condition
     * @param flushModeType the flush mode type {@line FlushModeType}
     * @return the query result
     */
    T findOne(CommonSpecification<T> spec, FlushModeType flushModeType);

    /**
     * Returns a single entity matching the given {@link CommonSpecification} with a flush mode.
     * 
     * @param spec the query condition
     * @param isFetch fetch flag
     * @param flushModeType the flush mode type {@line FlushModeType}
     * @return the query result
     */
    T findOne(CommonSpecification<T> spec, boolean isFetch, FlushModeType flushModeType);

    /**
     * Returns all entities matching the given {@link CommonSpecification}.
     * 
     * @param spec the query condition
     * @return the query result
     */
    List<T> findAll(CommonSpecification<T> spec);

    /**
     * Returns a {@link Page} of entities matching the given {@link CommonSpecification}.
     * 
     * @param spec the query condition
     * @param pageable the paging condition
     * @return the query result
     */
    Page<T> findAll(CommonSpecification<T> spec, Pageable pageable);

    /**
     * Returns a {@link Page} of entities matching the given {@link CommonSpecification}.
     * 
     * @param spec the query condition
     * @param pageable the paging condition
     * @param isFetch fetch flag
     * @return the query result
     */
    Page<T> findAll(CommonSpecification<T> spec, Pageable pageable, boolean isFetch);

    /**
     * Returns all entities matching the given {@link CommonSpecification} and {@link Sort}.
     * 
     * @param spec the query condition
     * @param sort the sort condition
     * @return the query result
     */
    List<T> findAll(CommonSpecification<T> spec, Sort sort);

    /**
     * Returns all entities matching the given {@link CommonSpecification} with a flush mode.
     * 
     * @param spec the query condition
     * @param flushModeType the flush mode type {@line FlushModeType}
     * @return the query result
     */
    List<T> findAll(CommonSpecification<T> spec, FlushModeType flushModeType);

    /**
     * Returns all entities matching the given {@link CommonSpecification} and {@link Sort} with a flush mode.
     * 
     * @param spec the query condition
     * @param sort the sort condition
     * @param flushModeType the flush mode type {@line FlushModeType}
     * @return the query result
     */
     List<T> findAll(CommonSpecification<T> spec, Sort sort, FlushModeType flushModeType);

    /**
     * Returns the number of instances that the given {@link CommonSpecification} will return.
     * 
     * @param spec the {@link CommonSpecification} to count instances for
     * @return the number of instances
     */
    long count(CommonSpecification<T> spec);

}
