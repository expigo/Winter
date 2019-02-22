package com.kryspinmusiol.orm.mappingabstractions;

import com.kryspinmusiol.orm.annotations.PrimaryKey;

import java.lang.reflect.Field;

public class PrimaryKeyField implements AbstractField {

    private final Field field;
    private final PrimaryKey primaryKey;
    private String name;

    public PrimaryKeyField(Field field) {
        this.field = field;
        this.primaryKey = this.field.getAnnotation(PrimaryKey.class);
    }

    @Override
    public Class<?> getType() {
        return this.field.getType();
    }

    @Override
    public Field getField() {
        return this.field;
    }

    @Override
    public String getName() {
        return primaryKey.name();
    }
}

