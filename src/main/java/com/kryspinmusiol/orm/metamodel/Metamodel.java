package com.kryspinmusiol.orm.metamodel;

public class Metamodel<T> {

    private Class<T> cls;

    private Metamodel(Class<T> cls) {
        this.cls = cls;
    }

    public static Metamodel of(Class<?> cls) {
        return new Metamodel(cls);
    }



}
