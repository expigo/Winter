package com.kryspinmusiol.orm;

public interface EntityManager {

    <T> void persist(T t);

    <T> T find(Class<T> cls, Object primaryKey);
}
