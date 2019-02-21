package com.kryspinmusiol.demo;

import com.kryspinmusiol.ioc.container.Container;

public class ContainerDemo {
    public static void main(String[] args) {


        Container container = Container.create();
        container.getGraph();

        container = Container.create("config.json");
        container.getGraph();

        System.out.println("Done");

    }
}
