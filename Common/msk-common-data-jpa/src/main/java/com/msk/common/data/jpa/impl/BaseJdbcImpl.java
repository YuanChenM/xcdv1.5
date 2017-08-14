/**
 * BaseJdbcImpl.java
 * 
 * @screen core
 * @author li_hui,jiang_nan
 */
package com.msk.common.data.jpa.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.msk.common.data.jpa.BaseJdbcDao;
import com.msk.common.data.jpa.CustomPageRequest;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.ejb.HibernateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component("baseJdbcDao")
public class BaseJdbcImpl<T> implements BaseJdbcDao<T> {

    /** the index of as mark */
    private final static int AS_INDEX = 2;

    @Autowired
    protected EntityManager em;

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<T> queryForList(String sql, List<?> parameters, Class<T> resultClass) {
        return queryForList(sql, parameters, resultClass, false);
    }

    private Query createQuery(Class<T> resultClass,boolean isNative,String sql){
        Query query = null;
        if (resultClass == null) {
            if (isNative) {
                query = em.createNativeQuery(sql);
            } else {
                query = em.createQuery(sql);
            }
        } else {
            if (isNative) {
                query = em.createNativeQuery(sql, resultClass);
            } else {
                query = em.createQuery(sql, resultClass);
            }
        }
        return query;

    }

    public List<T> queryForList(String sql, List<?> parameters, Class<T> resultClass, boolean isNative) {
        List<T> result = null;
        Query query = this.createQuery(resultClass,isNative,sql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                query.setParameter(i, para);
            }
        }
        result = query.getResultList();
        return result;
    }

    @Override
    public Page<T> queryForList(String sql, String countSql, List<?> parameters, Class<T> resultClass, Pageable pageable) {
        return queryForList(sql, countSql, parameters, resultClass, pageable, false);
    }

    @Override
    public Page<T> queryForList(String sql, String countSql, List<?> parameters, Pageable pageable) {
        long total = count(countSql, parameters, Boolean.TRUE);
        int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());
        int targetPageNumber = pageable.getPageNumber();
        if (targetPageNumber >= totalPages) {
            targetPageNumber = totalPages - 1;
        }
        if (targetPageNumber < 0) {
            targetPageNumber = 0;
        }
        Pageable newPageable = new CustomPageRequest(targetPageNumber, pageable.getPageSize(), pageable.getSort());
        if (pageable instanceof CustomPageRequest) {
            CustomPageRequest customPageRequest = (CustomPageRequest) pageable;
            customPageRequest.setPage(targetPageNumber);
        }
        Query query = this.createQuery(null,true,sql);

        SQLQuery sqlQuery = (SQLQuery) ((HibernateQuery) query).getHibernateQuery();
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                query.setParameter(i, para);
            }
        }
        if (newPageable != null) {
            query.setFirstResult(newPageable.getOffset());
            query.setMaxResults(newPageable.getPageSize());
        }

        List<T> content = null;
        if (total > newPageable.getOffset()) {
            content = query.getResultList();
        } else {
            content = Collections.emptyList();
        }

        return new PageImpl<T>(content, newPageable, total);
    }

    public Page<T> queryForList(String sql, String countSql, List<?> parameters, Class<T> resultClass,
                                Pageable pageable, boolean isNative) {

        long total = count(countSql, parameters, isNative);

        int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());
        int targetPageNumber = pageable.getPageNumber();
        if (targetPageNumber >= totalPages) {
            targetPageNumber = totalPages - 1;
        }
        if (targetPageNumber < 0) {
            targetPageNumber = 0;
        }

        Pageable newPageable = new CustomPageRequest(targetPageNumber, pageable.getPageSize(), pageable.getSort());
        if (pageable instanceof CustomPageRequest) {
            CustomPageRequest customPageRequest = (CustomPageRequest) pageable;
            customPageRequest.setPage(targetPageNumber);
        }

        Query query = this.createQuery(resultClass,isNative,sql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                query.setParameter(i, para);
            }
        }
        if (newPageable != null) {
            query.setFirstResult(newPageable.getOffset());
            query.setMaxResults(newPageable.getPageSize());
        }

        List<T> content = null;
        if (total > newPageable.getOffset()) {
            content = query.getResultList();
        } else {
            content = Collections.emptyList();
        }

        return new PageImpl<T>(content, newPageable, total);

    }


    @Override
    public long count(String sql, List<?> parameters) {
        return count(sql, parameters, false);
    }


    public long count(String sql, List<?> parameters, boolean isNative) {
        Query query = null;
        Object count = 0;
        if (isNative) {
            query = em.createNativeQuery(sql);
        } else {
            query = em.createQuery(sql);
        }
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                query.setParameter(i, para);
            }
        }
        count = query.getSingleResult();
        if (count instanceof Long) {
            return ((Long) count).longValue();
        } else if(count instanceof BigInteger){
            return ((BigInteger) count).longValue();
        }else {
            return ((BigDecimal) count).longValue();
        }
    }

    @Override
    public int executeUpdate(String sql, List<?> parameters) {
        return executeUpdate(sql, parameters, false);
    }

    private int executeUpdate(String sql, List<?> parameters, boolean isNative) {
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction etr = em1.getTransaction();
        etr.begin();
        Query query = null;
        if (isNative) {
            query = em.createNativeQuery(sql);
        } else {
            query = em.createQuery(sql);
        }
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                query.setParameter(i, para);
            }
        }
        int ret = 0;
        try {
            ret = query.executeUpdate();
            etr.commit();
        } catch (Exception e) {
            etr.rollback();
            e.printStackTrace();
        }
        return ret;
    }


    public Map<String, Object> queryForMap(String sql, List<?> parameters) {
        Query query = em.createNativeQuery(sql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                query.setParameter(i + 1, para);
            }
        }
        List<?> list = query.getResultList();
        Map<String, Object> retMap = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            Object obj = list.get(0);
            Object[] data = (Object[]) obj;
            String[] key = this.getSqlFiled(sql);
            for (int i = 0; i < data.length; i++) {
                retMap.put(key[i], data[i]);
            }
        }
        return retMap;
    }


    protected List<Map<String, Object>> queryForMapList(String sql, List<?> parameters) {
        return queryForMapList(sql, parameters, null);
    }


    protected Page<Map<String, Object>> queryForMapPage(String sql, List<?> parameters, Pageable pageable,
            Map<String, org.hibernate.type.Type> scalarMap) {
        String countSql = "SELECT COUNT(1) FROM (" + sql + ")";
        return queryForMapPage(sql, countSql, parameters, pageable, scalarMap);
    }

    protected Page<Map<String, Object>> queryForMapPage(String sql, String countSql, List<?> parameters,
            Pageable pageable, Map<String, org.hibernate.type.Type> scalarMap) {
        long total = count(countSql, parameters, true);
        // Modify for bug#0143592 at 2014/01/21 by lijie4 Start.
        int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());
        int targetPageNumber = pageable.getPageNumber();
        if (targetPageNumber >= totalPages) {
            targetPageNumber = totalPages - 1;
        }
        if (targetPageNumber < 0) {
            targetPageNumber = 0;
        }

        Pageable newPageable = new CustomPageRequest(targetPageNumber, pageable.getPageSize(), pageable.getSort());
        if (pageable instanceof CustomPageRequest) {
            CustomPageRequest customPageRequest = (CustomPageRequest) pageable;
            customPageRequest.setPage(targetPageNumber);
        }

        List<Map<String, Object>> content = null;
        if (total > newPageable.getOffset()) {
            content = queryForMapListInPageLimit(sql, parameters, newPageable, scalarMap);
        } else {
            content = Collections.emptyList();
        }

        return new PageImpl<Map<String, Object>>(content, newPageable, total);
    }

    protected List<Map<String, Object>> queryForMapListInPageLimit(String sql, List<?> parameters, Pageable pageable,
            Map<String, org.hibernate.type.Type> scalarMap) {

        Query que = em.createNativeQuery(sql);
        SQLQuery query = (SQLQuery) ((HibernateQuery) que).getHibernateQuery();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        if (scalarMap != null) {
            for (Map.Entry<String, org.hibernate.type.Type> en : scalarMap.entrySet()) {
                query.addScalar(en.getKey(), en.getValue());
            }
        }

        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                que.setParameter(i, para);
            }
        }

        if (pageable != null) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }

        return (List<Map<String, Object>>) que.getResultList();
    }

    protected List<Map<String, Object>> queryForMapList(String sql, List<?> parameters,
            Map<String, org.hibernate.type.Type> scalarMap) {
        return queryForMapListInPageLimit(sql, parameters, null, scalarMap);
    }

    public List<T> queryForListNotCount(String sql, List<?> parameters, Pageable pageable, boolean isNative) {
        Query que = null;

        if (isNative) {
            que = em.createNativeQuery(sql);
            SQLQuery query = (SQLQuery) ((HibernateQuery) que).getHibernateQuery();
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        } else {
            que = em.createQuery(sql);
        }

        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);
                que.setParameter(i, para);
            }
        }
        if (pageable != null) {
            que.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            que.setMaxResults(pageable.getPageSize());
        }

        List<T> content = null;
        content = que.getResultList();

        return content;

    }


    private String[] getSqlFiled(String sql) {
        String[] fields = null;
        String sqlField = sql.substring("select ".length());
        sqlField = sqlField.substring(0, sqlField.toUpperCase().indexOf("FROM"));
        String[] splitSql = sqlField.split(",");
        fields = new String[splitSql.length];
        int i = 0;
        for (String field : splitSql) {
            String tmpField = field;
            int index = tmpField.indexOf(".");
            if (index != -1) {
                index = tmpField.toUpperCase().indexOf("AS");
                tmpField = tmpField.substring(index + AS_INDEX);
            }
            fields[i] = tmpField.trim();
            i++;
        }
        return fields;
    }
}
