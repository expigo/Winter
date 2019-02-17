package com.kryspinmusiol.orm.annotations;

import java.lang.annotation.Annotation;
import java.util.EnumSet;
import java.util.Optional;

public enum AnnotationEnum {
    ENTITY(Entity.class.getSimpleName()),
    PRIMARY_KEY(PrimaryKey.class.getSimpleName()),
    COLUMN(Column.class.getSimpleName());

    private final String annotationName;

    AnnotationEnum(String columnClassName) {
        this.annotationName = columnClassName;
    }

    public  String getAnnotation() {
        return annotationName;
    }

    public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> clazz, String name) {
        return EnumSet.allOf(clazz).stream().filter(v -> v.name().equals(name))
                .findAny();
    }
}
