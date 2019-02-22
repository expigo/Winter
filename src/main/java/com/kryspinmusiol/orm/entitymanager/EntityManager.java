package com.kryspinmusiol.orm.entitymanager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager {

    <T> void persist(T t) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException;

    <T> T find(Class<T> cls, Object primaryKey);
}
