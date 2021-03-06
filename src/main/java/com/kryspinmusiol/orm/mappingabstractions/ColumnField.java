package com.kryspinmusiol.orm.mappingabstractions;

import com.kryspinmusiol.orm.annotations.Column;

import java.lang.reflect.Field;

public class ColumnField implements AbstractField {

    private final Field field;
    private Column column;

    public ColumnField(Field field) {
        this.field = field;
        this.column = field.getAnnotation(Column.class);
    }


    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public String getName() {
        return column.name() == "" ? field.getName() : column.name();
    }


}
