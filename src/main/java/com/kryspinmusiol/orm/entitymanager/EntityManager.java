package com.kryspinmusiol.orm.entitymanager;

public interface EntityManager {

    <T> void persist(T t);

    <T> T find(Class<T> cls, Object primaryKey);
}
