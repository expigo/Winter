package com.kryspinmusiol.orm.metamodel;


import com.kryspinmusiol.orm.mappingabstractions.ColumnField;
import com.kryspinmusiol.orm.testfixtures.model.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TestHelper {

    Metamodel<?>  getMetamodel();

    default List<String> getColumnNamesFromMockEntity() throws NoSuchFieldException {

            return Stream.of(
                    new ColumnField(Person.class.getDeclaredField("name")),
                    new ColumnField(Person.class.getDeclaredField("age"))
            ).map(columnField -> columnField.getField().getName()).collect(Collectors.toList());

    }


}
