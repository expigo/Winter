package com.kryspinmusiol.orm.mappingabstractions.factories;

import com.kryspinmusiol.orm.mappingabstractions.AbstractField;

import java.lang.reflect.Field;

public interface AbstractFieldFactory {
    AbstractField makeColumn(Field field);
}
