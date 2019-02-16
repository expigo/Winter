package com.kryspinmusiol.orm.metamodel;

import com.kryspinmusiol.orm.annotations.Column;
import com.kryspinmusiol.orm.annotations.PrimaryKey;
import com.kryspinmusiol.orm.mappingabstractions.ColumnField;
import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyColumn;
import com.sun.source.tree.ParameterizedTypeTree;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Metamodel<T> {

    private Class<T> cls;

    private Metamodel(Class<T> cls) {
        this.cls = cls;
    }

    public static <T> Metamodel of(Class<T> cls) {
        Objects.requireNonNull(cls);
        return new Metamodel(cls);
    }

    public PrimaryKeyColumn getPrimaryKeyColumn() {
        final Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {

            if (field.isAnnotationPresent(PrimaryKey.class)) {
                PrimaryKeyColumn primaryKeyColumn = new PrimaryKeyColumn(field);
                return primaryKeyColumn;
            }
        }

        throw new IllegalArgumentException("Entity requires Primary Key: " + cls.getSimpleName());
    }


    public List<ColumnField> getColumns() {
        List<ColumnField> columnFields = new ArrayList<>();
        final Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Column.class)) {
                columnFields.add(new ColumnField(field));
            }
        }
        return Collections.unmodifiableList(columnFields);
    }
}
