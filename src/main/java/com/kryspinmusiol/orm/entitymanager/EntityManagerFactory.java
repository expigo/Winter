package com.kryspinmusiol.orm.entitymanager;

import com.kryspinmusiol.ioc.container.Container;

public class EntityManagerFactory {

    /**
     * Container is used to resolve the dependency for DB Connection residing in EntityManager class
     */
    private final Container dependencyContainer;

    private EntityManagerFactory() {
        dependencyContainer = Container.create();
    }
    private EntityManagerFactory(String config) {
        dependencyContainer = Container.create(config);
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        return new EntityManagerFactory();
    }

    public static EntityManagerFactory createEntityManagerFactory(String persistencePath) {
        return new EntityManagerFactory(persistencePath);
    }

    public EntityManager createEntityManager() {
        return (EntityManager) dependencyContainer.resolve(EntityManager.class);
    }

    public EntityManager getEntityManager(Class<?> cls) {
        return (EntityManager) dependencyContainer.resolve(cls);
    }

}
