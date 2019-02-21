package com.kryspinmusiol.orm.mappingabstractions.entitymetamodel;

import com.kryspinmusiol.orm.mappingabstractions.AbstractField;
import com.kryspinmusiol.orm.mappingabstractions.ColumnField;
import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityModel {

    private final PrimaryKeyField primaryKeyField;
    private final List<AbstractField> columns;

    private EntityModel(PrimaryKeyField primaryKeyField, List<AbstractField> columns) {
        this.primaryKeyField = primaryKeyField;
        this.columns = columns;
    }

    public EntityModel(PrimaryKeyField primaryKeyField) {
        this.primaryKeyField = primaryKeyField;
        columns = new ArrayList<>();
    }

    public static EntityModel create(PrimaryKeyField pk, List<AbstractField> columns) {
        return new EntityModel(pk);
    }

    private static EntityModel create(PrimaryKeyField pk) {
        return new EntityModel(pk);
    }

    public void addColumnField(ColumnField columnField) {
        Objects.requireNonNull(columnField);
        columns.add(columnField);
    }
}
