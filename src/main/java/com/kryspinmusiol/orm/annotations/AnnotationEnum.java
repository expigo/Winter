package com.kryspinmusiol.orm.annotations;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;


public enum AnnotationEnum {
    ENTITY(Entity.class),
    PRIMARY_KEY(PrimaryKey.class),
    COLUMN(Column.class);

    private final Class<? extends Annotation> annotationName;

    private static final List<AnnotationEnum> cachedAnnotations = Collections.unmodifiableList(List.of(AnnotationEnum.values()));

    AnnotationEnum(Class<? extends Annotation> columnClassName) {
        this.annotationName = columnClassName;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotationName;
    }


    public static AnnotationEnum getIfPresent(Class<? extends Annotation> klass, Supplier<? extends AnnotationEnum> defaultAction) {

        final AnnotationEnum annotationEnum = cachedAnnotations.stream()
                .filter(c -> c.getAnnotation().equals(klass))
                .findFirst().orElseGet(defaultAction);

        return annotationEnum;
    }
}
