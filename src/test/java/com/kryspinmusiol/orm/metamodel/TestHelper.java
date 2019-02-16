package com.kryspinmusiol.orm.metamodel;


import com.kryspinmusiol.orm.mappingabstractions.ColumnField;
import com.kryspinmusiol.orm.testfixtures.model.Person;

import java.util.Arrays;
import java.util.List;

public interface TestHelper {

    Metamodel<?>  getMetamodel();

    default List<ColumnField> getColumnsFromMockEntity() {
        return Arrays.asList(
                new ColumnField()
        );
    }


}
