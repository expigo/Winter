package com.kryspinmusiol.orm.mappingabstractions;

import com.kryspinmusiol.orm.annotations.PrimaryKey;

import java.lang.reflect.Field;

public class PrimaryKeyColumn {

    private final Field field;
    private final PrimaryKey primaryKey;

    public PrimaryKeyColumn(Field field, PrimaryKey primaryKey) {
        this.field = field;
        this.primaryKey = primaryKey;
    }

    public Class<?> getType() {
        return field.getType();
    }

    public Field getField() {
        return field;
    }
}
