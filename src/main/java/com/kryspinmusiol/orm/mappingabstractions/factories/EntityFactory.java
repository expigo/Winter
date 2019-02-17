package com.kryspinmusiol.orm.mappingabstractions.factories;

import com.kryspinmusiol.orm.annotations.AnnotationEnum;
import com.kryspinmusiol.orm.annotations.PrimaryKey;
import com.kryspinmusiol.orm.mappingabstractions.AbstractField;
import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyField;

import java.lang.annotation.Annotation;

public abstract class EntityFactory {

    public static Class<? extends AbstractField> getMappingColumnFactory(AnnotationEnum annotationEnum) {
        switch (annotationEnum) {
            case PRIMARY_KEY:
                return PrimaryKeyField.class;
        }
        throw new IllegalArgumentException("No such annotation!" + annotationEnum.getAnnotation());
    }

}
