package com.example.projectsale.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
public class AbstractDaoUtil<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void finalize() throws Throwable {
        entityManager.clear();
        super.finalize();
    }

    public T findById(Class<T> clazz, Object params) {
        return entityManager.find(clazz, params);
    }

    public List<T> findAll(Class<T> clazz, boolean existIsActive) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive) {
            sql.append(" WHERE isActive =1");
        }
        TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }

    public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive) {
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public T findOne(Class<T> clazz, String sql, Object... params) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public List<T> findMany(Class<T> clazz, String sql, int pageNumber, int pageSize, Object... params) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }

        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List findMany(String sql, Map<String, Object> params) {
        Query query = entityManager.createQuery(sql);
        params.forEach((key, value) -> query.setParameter(key, "%" + value + "%"));
        return query.getResultList();
    }

    public List<T> findMany(Class<T> clazz, String sql, Map<String, Object> params, int pageNumber, int pageSize) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        params.forEach(query::setParameter);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Page findMany(String sql, Map<String, Object> params, int pageNumber, int pageSize) {
        Query query = entityManager.createQuery(sql);
        params.forEach(query::setParameter);

        long totalCount = query.getResultList().size();

        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return new PageImpl<>(query.getResultList(), pageable, totalCount);
    }

    private long getTotalCount(String sql, Map<String, Object> params) {
        String countSql = "SELECT COUNT(*) " + sql.substring(sql.toLowerCase().indexOf("from"));
        Query countQuery = entityManager.createQuery(countSql);

        params.forEach(countQuery::setParameter);

        return (long) countQuery.getSingleResult();
    }

    public List findManyByNativeQuery(String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

    public T create(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            System.out.println("Create success");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot insert entity " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            System.out.println("Update success");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot insert entity " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public Boolean updateByCondition(Class<T> clazz, String sql, Object... params) {
        try {
            int result;
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(sql);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
            result = query.executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("Update success");
            return result > 0;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            System.out.println("Delete success");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot Delete entity " + entity.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> callStored(String namedStored, Map<String, Object> params) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(namedStored);
        params.forEach(query::setParameter);
        return query.getResultList();
    }

    public List<T> findNamedQuery(Class<T> clazz, String namedQuery, Map<String, Object> params, boolean isKeyword) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, clazz);
        params.forEach(query::setParameter);
        if (isKeyword) {
            params.forEach((key, value) -> query.setParameter(key, "%" + value + "%"));
        }
        return query.getResultList();
    }

    public List<T> findNamedQuery(Class<T> clazz, String namedQuery, Object... params) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

}