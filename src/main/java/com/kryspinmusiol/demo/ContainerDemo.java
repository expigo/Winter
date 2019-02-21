package com.kryspinmusiol.demo;

import com.kryspinmusiol.ioc.container.Container;
import com.kryspinmusiol.orm.entitymanager.DBEntityManager;
import com.kryspinmusiol.orm.entitymanager.EntityManager;
import com.kryspinmusiol.orm.entitymanager.EntityManagerFactory;

public class ContainerDemo {
    public static void main(String[] args) {


        Container container = Container.create();
        container.getGraph();

        container = Container.create("config.json");
        container.getGraph();



        EntityManagerFactory emf = EntityManagerFactory.createEntityManagerFactory();
        final EntityManager entityManager = emf.getEntityManager(DBEntityManager.class);

        System.out.println("Done");
    }
}
