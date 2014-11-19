package com.training.dao;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pragati on 15.11.14.
 */
public class AbstractDAO {

    EntityManager entityManager;

    public AbstractDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    protected <T extends Object> void insert(T entity){
        entityManager.persist(entity);
    }

    protected <T extends Object> void merge(T entity){
        entityManager.merge(entity);
    }

    protected <T extends Object> void delete(T entity){
        entityManager.remove(entity);
    }

    protected <T extends Object> void insertAndFlush(T entity){
        entityManager.persist(entity);
        entityManager.flush();
    }

    protected <T extends Object> void update(T entity){
        entityManager.merge(entity);
    }

    protected <T extends Object> T findByPrimaryKey(Class<T> className, Object primaryKey){
        Map<String, Object> loadingProp = new HashMap<String, Object>();
        loadingProp.put("javax.persistence.cache.storeMode", "REFRESH");
        //loadingProp.put("eclipselink.read-only", "true");
        return entityManager.find(className, primaryKey,loadingProp);
    }
}
