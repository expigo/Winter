package com.kryspinmusiol.orm.mappingabstractions.factories;

import com.kryspinmusiol.orm.mappingabstractions.AbstractField;
import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyField;

import java.lang.reflect.Field;

public class PrimaryKeyFactory implements AbstractFieldFactory {
    @Override
    public AbstractField makeColumn(Field  field) {
        return new PrimaryKeyField(field);
    }
}
