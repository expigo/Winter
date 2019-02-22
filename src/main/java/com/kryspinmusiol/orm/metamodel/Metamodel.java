package com.kryspinmusiol.orm.metamodel;

import com.kryspinmusiol.orm.annotations.AnnotationEnum;
import com.kryspinmusiol.orm.annotations.Column;
import com.kryspinmusiol.orm.annotations.PrimaryKey;
import com.kryspinmusiol.orm.mappingabstractions.AbstractField;
import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyField;
import com.kryspinmusiol.orm.mappingabstractions.entitymetamodel.EntityModel;
import com.kryspinmusiol.orm.mappingabstractions.factories.EntityFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Metamodel<T> {

    private static final int NUMBER_OF_PRIMARY_KEYS = 1;


    private Class<T> cls;

    private Metamodel(Class<T> cls) {
        this.cls = cls;
    }

    public static <T> Metamodel of(Class<T> cls) {
        Objects.requireNonNull(cls);
        return new Metamodel(cls);
    }

    public PrimaryKeyField getPrimaryKeyColumn() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        final List<AbstractField> primaryKeyColumn = pickDesiredColumns(PrimaryKey.class);

        if (primaryKeyColumn.size() == 1) {
            final PrimaryKeyField abstractField = (PrimaryKeyField) primaryKeyColumn.get(0);
            return abstractField;
        } else {
            throw new IllegalArgumentException("Unexpected number of @PrimaryKey annotations (only one id permitted): " + cls.getSimpleName());
        }
    }


    public List<AbstractField> getColumns() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return pickDesiredColumns(Column.class);
    }


    public EntityModel getEntityModel() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return EntityModel.create(getPrimaryKeyColumn(), getColumns());
    }

    public String buildInsertStatement() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final List<AbstractField> columns = getColumns();
        final String preparedColumnNames = getFormattedColumnNames(columns);
        final String questionMarks = buildQuestionMarks(columns.size() + NUMBER_OF_PRIMARY_KEYS);

        return "insert into " + this.cls.getSimpleName() + "(" + preparedColumnNames + ") values (" + questionMarks + ")";
    }

    private String buildQuestionMarks(int noOfColumns) {
        return IntStream.range(0, noOfColumns).mapToObj(i -> "?").collect(Collectors.joining(", "));
    }

    private String getFormattedColumnNames(final List<AbstractField> columns) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final List<String> columnsNames = columns.stream().map(AbstractField::getName).collect(Collectors.toList());
        final String primaryKeyColumnName = getPrimaryKeyColumn().getName();

        columnsNames.add(0, primaryKeyColumnName);
        return String.join(", ", columnsNames);
    }

    private List<AbstractField> pickDesiredColumns(Class<? extends Annotation> klass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<AbstractField> columnFields = new ArrayList<>();
        final Class<? extends AbstractField> abstractField = getAssociatedAbstractionField(klass);
        final Field[] declaredFields = cls.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(klass)) {
                final AbstractField fieldToAdd = abstractField.getConstructor(Field.class).newInstance(field);
                columnFields.add(fieldToAdd);
            }
        }
        return Collections.unmodifiableList(columnFields);
    }

    private Class<? extends AbstractField> getAssociatedAbstractionField(Class<? extends Annotation> klass) {
        final AnnotationEnum annotation = AnnotationEnum.getIfPresent(klass, () -> {
            throw new IllegalArgumentException("No field with such annotation found:" + klass);
        });
        return EntityFactory.getMappingColumnFactory(annotation);
    }


}
