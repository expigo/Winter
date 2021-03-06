package com.kryspinmusiol.orm.mappingabstractions.factories;

import com.kryspinmusiol.orm.annotations.AnnotationEnum;
import com.kryspinmusiol.orm.mappingabstractions.AbstractField;
import com.kryspinmusiol.orm.mappingabstractions.ColumnField;
import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyField;

public abstract class EntityFactory {


    public static Class<? extends AbstractField> getMappingColumnFactory(AnnotationEnum annotationEnum) {
        switch (annotationEnum) {
            case PRIMARY_KEY:
                return PrimaryKeyField.class;
            case COLUMN:
                return ColumnField.class;
        }
        throw new IllegalArgumentException("No such annotation!" + annotationEnum.getAnnotation());
    }

}
