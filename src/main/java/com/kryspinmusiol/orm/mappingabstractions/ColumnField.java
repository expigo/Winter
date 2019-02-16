package com.kryspinmusiol.orm.mappingabstractions;

import com.kryspinmusiol.orm.annotations.Column;

import java.lang.reflect.Field;

public class ColumnField {
    private Field field;
    private Column column;

    public ColumnField(Field field) {
        this.field = field;
        this.column = field.getAnnotation(Column.class);
    }

    public Class<?> getType() {
        return field.getType();
    }

    public Field getField() {
        return field;
    }
}
