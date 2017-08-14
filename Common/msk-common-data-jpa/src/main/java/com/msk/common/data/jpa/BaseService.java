/**
 * BaseService.java
 * 
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.msk.common.data.jpa.condition.BaseCondition;
import com.msk.common.data.jpa.condition.CommonSpecification;

public abstract class BaseService<T, ID extends Serializable> {

    public abstract BaseRepository<T, ID> getRepository();


    public long count() {
        return getRepository().count();
    }

    public long count(BaseCondition<T> condObj) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().count(spec);
    }

    public void delete(ID id) {
        getRepository().delete(id);
    }

    public void delete(T entity) {
        getRepository().delete(entity);
    }

    public void delete(Iterable<? extends T> entities) {
        getRepository().delete(entities);
    }

    public void deleteAll() {
        getRepository().deleteAll();
    }

    public void deleteInBatch(Iterable<T> entities) {
        getRepository().deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    public boolean exists(ID id) {
        return getRepository().equals(id);
    }


    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    public T findOne(BaseCondition<T> condObj) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findOne(spec);
    }

    public T findOneWithoutFlush(BaseCondition<T> condObj) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findOne(spec, FlushModeType.COMMIT);
    }


    public T findOneForce(ID id){
        T result = getRepository().findOne(id);
        if (result == null) {
            throw new RuntimeException("数据没有找到");
        }
        return result;
    }

    public T findOneForce(BaseCondition<T> condObj){
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);

        T result = getRepository().findOne(spec);
        if (result == null) {
            throw new RuntimeException("数据没有找到");
        }
        return result;
    }

    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    public Iterable<T> findAll(Iterable<ID> ids) {
        return getRepository().findAll(ids);
    }

    public Iterable<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public List<T> findAll(BaseCondition<T> condObj) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findAll(spec);
    }

    public Page<T> findAll(BaseCondition<T> condObj, Pageable pageable) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findAll(spec, pageable);
    }

    public Page<T> findAll(BaseCondition<T> condObj, Pageable pageable, boolean isFetch) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findAll(spec, pageable, isFetch);
    }

    public List<T> findAll(BaseCondition<T> condObj, Sort sort) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findAll(spec, sort);
    }

    public List<T> findAllWithoutFlush(BaseCondition<T> condObj) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findAll(spec, FlushModeType.COMMIT);
    }

    public List<T> findAllWithoutFlush(BaseCondition<T> condObj, Sort sort) {
        CommonSpecification<T> spec = new CommonSpecification<T>(condObj);
        return getRepository().findAll(spec, sort, FlushModeType.COMMIT);
    }

    public void flush() {
        getRepository().flush();
    }

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    public T saveAndFlush(T entity) {
        return getRepository().saveAndFlush(entity);
    }
}
