package com.kryspinmusiol.orm.mappingabstractions;

import com.kryspinmusiol.orm.annotations.PrimaryKey;

import java.lang.reflect.Field;

public class PrimaryKeyColumn {

    private final Field field;
    private PrimaryKey primaryKey;

    public PrimaryKeyColumn(Field field) {
        this.field = field;
        this.primaryKey = this.field.getAnnotation(PrimaryKey.class);
    }

    public Class<?> getType() {
        return field.getType();
    }

    public Field getField() {
        return field;
    }
}
