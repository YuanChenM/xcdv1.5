/**
 * @screen core
 * @author zhou_ke
 */
package com.msk.common.data.jpa.impl;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.*;

import com.msk.common.constant.NumberConstant;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.CommonSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * <p>
 * The implement base class of DAO class.
 * </p>
 * 
 * @param <T> The entity type
 * @param <ID> The key's type of entity
 * 
 * @author zhou_ke
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements BaseRepository<T, ID> {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);
    private EntityManager entityManager;
    private JpaEntityInformation<T, ?> entityInformation;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
    }

    @Override
    public T findOne(CommonSpecification<T> spec) {
        return this.findOne(spec, true, FlushModeType.AUTO);
    }

    @Override
    public T findOne(CommonSpecification<T> spec, boolean isFetch) {
        return findOne(spec, isFetch, FlushModeType.AUTO);
    }

    @Override
    public T findOne(CommonSpecification<T> spec, FlushModeType flushModeType) {
        return this.findOne(spec, true, flushModeType);
    }

    @Override
    public T findOne(CommonSpecification<T> spec, boolean isFetch, FlushModeType flushModeType) {
        try {
            TypedQuery<T> query = getQuery(spec, (Sort) null, isFetch);
            query = setParam(query, spec.getParmeter());
            query.setFlushMode(flushModeType);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<T> findAll(CommonSpecification<T> spec) {
        return findAll(spec, (Sort) null);
    }

    @Override
    public Page<T> findAll(CommonSpecification<T> spec, Pageable pageable) {
        return findAll(spec, pageable, true);
    }

    @Override
    public Page<T> findAll(CommonSpecification<T> spec, Pageable pageable, boolean isFetch) {
        TypedQuery<T> query = getQuery(spec, pageable, isFetch);
        query = setParam(query, spec.getParmeter());

        Page<T> result = null;
        if (pageable == null) {
            result = new PageImpl<T>(query.getResultList());
        } else {
            result = readPage(query, pageable, spec);
        }
        return result;
    }

    private Page<T> readPage(TypedQuery<T> query, Pageable pageable, CommonSpecification<T> spec) {

        Long total = count(spec);
//        int totalPages=(int)Math.ceil((double)total/pageable.getPageSize());
//        int targetPageNumber=pageable.getPageNumber();
//        if(targetPageNumber>=totalPages){
//            targetPageNumber=totalPages-1;
//        }
//        if(targetPageNumber<0){
//            targetPageNumber=0;
//        }

//        Pageable newPageable=new CustomPageRequest(targetPageNumber, pageable.getPageSize(), pageable.getSort());
//        if(pageable instanceof CustomPageRequest){
//            CustomPageRequest customPageRequest=(CustomPageRequest) pageable;
//            customPageRequest.setPage(targetPageNumber);
//        }

        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<T> content;
        if (total > NumberConstant.IntDef.INT_ZERO) {
            content = query.getResultList();
        } else {
            content = Collections.<T> emptyList();
        }

        return new PageImpl<T>(content, pageable, total);
    }

    @Override
    public List<T> findAll(CommonSpecification<T> spec, Sort sort) {
        return findAll(spec, sort, FlushModeType.AUTO);
    }

    @Override
    public List<T> findAll(CommonSpecification<T> spec, FlushModeType flushModeType) {
        return findAll(spec, (Sort) null, flushModeType);
    }

    @Override
    public List<T> findAll(CommonSpecification<T> spec, Sort sort, FlushModeType flushModeType) {
        TypedQuery<T> query = getQuery(spec, sort, true);
        query = setParam(query, spec.getParmeter());
        query.setFlushMode(flushModeType);
        return query.getResultList();
    }

    @Override
    public long count(CommonSpecification<T> spec) {
        TypedQuery<Long> query = getCountQuery(spec);
        query = (TypedQuery<Long>) setParam((TypedQuery<T>) query, spec.getParmeter());
        return query.getSingleResult();
    }

    private TypedQuery<T> getQuery(CommonSpecification<T> spec, Sort sort, boolean isFetch) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getDomainClass());
        Root<T> root = applySpecificationToCriteria(spec, query, isFetch);
        if (spec.isDistinct()) {
            query.distinct(true);
        }
        query.select(root);
        if (sort != null) {
            List<Order> specOrderList = query.getOrderList();
            List<Order> pageOrder = spec.toOrders(sort, root, builder);
            // temp list
            List<Order> tempList = new ArrayList<Order>();
            // builder order
            for (int i = 0; i < specOrderList.size(); i++) {
                Order defaultOrder = specOrderList.get(i);
                // page order
                for (int j = 0; j < pageOrder.size(); j++) {
                    Order pageOrderTemp = pageOrder.get(j);
                    if (pageOrderTemp.getExpression().equals(defaultOrder.getExpression())) {
                        tempList.add(defaultOrder);
                        continue;
                    }
                }
            }
            specOrderList.removeAll(tempList);
            pageOrder.addAll(specOrderList);

            query.orderBy(pageOrder);
        }
        return applyLockMode(entityManager.createQuery(query), spec);

    }

    private TypedQuery<T> applyLockMode(TypedQuery<T> query, CommonSpecification<T> spec) {
        TypedQuery<T> result = null;

        LockModeType type = null;
        if (spec.getLockModeType() != null) {
            type = spec.getLockModeType();
        }

        if (type == null) {
            result = query;
        } else {
            result = query.setLockMode(type);
        }

        return result;
    }

    private <S> Root<T> applySpecificationToCriteria(CommonSpecification<T> spec, CriteriaQuery<S> query,
        boolean isFetch) {
        return this.applySpecification(spec, query, isFetch);
    }

    private <S> Root<T> applySpecificationToCriteria(CommonSpecification<T> spec, Subquery<S> query, boolean isFetch) {
        return this.applySpecification(spec, query, isFetch);
    }

    private <S> Root<T> applySpecification(CommonSpecification<T> spec, AbstractQuery<S> query, boolean isFetch) {
        Root<T> root = query.from(getDomainClass());
        if (spec == null) {
            return root;
        }

        spec.setFetch(isFetch);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Predicate predicate = null;
        if (query instanceof Subquery) {
            predicate = spec.toPredicate(root, (Subquery) query, builder);
        } else if (query instanceof CriteriaQuery) {
            predicate = spec.toPredicate(root, (CriteriaQuery) query, builder);

        }
        if (predicate != null) {
            query.where(predicate);
        }
        return root;
    }

    private TypedQuery<T> setParam(TypedQuery<T> query, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, Object>> parmeterSet = params.entrySet();
            logger.debug("query param list:");
            for (Map.Entry<String, Object> param : parmeterSet) {
                query.setParameter(param.getKey(), param.getValue());
                logger.debug("key : {}, value : {}", param.getKey(), param.getValue());
            }
        }
        return query;
    }

    private TypedQuery<T> getQuery(CommonSpecification<T> spec, Pageable pageable, boolean isFetch) {

        Sort sort = null;
        if (pageable != null) {
            sort = pageable.getSort();
        }
        return getQuery(spec, sort, isFetch);
    }

    public Class<T> getDomainClass() {
        return entityInformation.getJavaType();
    }

}
