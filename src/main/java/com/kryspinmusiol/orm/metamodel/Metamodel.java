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

public class Metamodel {

    private Class<?> cls;

    private Metamodel(Class<?> cls) {
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
        } );
        return EntityFactory.getMappingColumnFactory(annotation);
    }



}
