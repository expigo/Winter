package com.kryspinmusiol.orm.metamodel;

import com.sun.source.tree.ParameterizedTypeTree;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class Metamodel<T> {

    private Class<T> cls;

    private Metamodel(Class<T> cls) {
        this.cls = cls;
    }

    public static <T> Metamodel of(Class<T> cls) {
        Objects.requireNonNull(cls);
        return new Metamodel(cls);
    }
}
