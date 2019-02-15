package com.kryspinmusiol.orm.metamodel;

public class Metamodel<T> {

    private Class<T> cls;

    private Metamodel(Class<T> cls) {
        this.cls = cls;
    }

    public static <T> Metamodel of(Class<T> cls) {
        return new Metamodel(cls);
    }
}
