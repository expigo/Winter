package com.kryspinmusiol.orm.entitymanager;

import com.kryspinmusiol.demo.model.Duck;
import com.kryspinmusiol.ioc.annotation.Inject;

public class DBEntityManager implements EntityManager {

    @Inject
    private Duck duck;

    @Override
    public <T> void persist(T t) {

    }

    @Override
    public <T> T find(Class<T> cls, Object primaryKey) {
        return null;
    }
}
