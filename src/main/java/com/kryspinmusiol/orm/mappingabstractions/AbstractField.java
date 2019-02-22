package com.kryspinmusiol.orm.mappingabstractions;

import java.lang.reflect.Field;

public interface AbstractField {
    Class<?> getType();
    Field getField();
    String getName();

}
