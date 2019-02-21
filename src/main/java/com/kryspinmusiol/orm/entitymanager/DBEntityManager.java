package com.kryspinmusiol.orm.entitymanager;

public class DBEntityManager implements EntityManager {
    @Override
    public <T> void persist(T t) {

    }

    @Override
    public <T> T find(Class<T> cls, Object primaryKey) {
        return null;
    }
}
