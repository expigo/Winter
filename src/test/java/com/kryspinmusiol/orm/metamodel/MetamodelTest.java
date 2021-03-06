package com.kryspinmusiol.orm.metamodel;

import com.kryspinmusiol.orm.mappingabstractions.PrimaryKeyField;
import com.kryspinmusiol.orm.testfixtures.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetamodelTest implements TestHelper{

    private Metamodel<Person> metamodel;

    @BeforeEach
    @DisplayName("Given that we want Metamodel of Person class")
    void setUp() {
        // when calling factory for the Metamodel
        metamodel = (Metamodel<Person>) getMetamodel();
    }

    @Test
    @DisplayName("Then the type is truly of Person type")
    void shouldReturnModelOfProperType() throws NoSuchFieldException {
        assertEquals(Person.class, getParameterizedType());
    }

    private Class<?> getParameterizedType() throws NoSuchFieldException {
        final Field clsField = MetamodelTest.class.getDeclaredField("metamodel");
        ParameterizedType clsType = (ParameterizedType) clsField.getGenericType();
        return (Class<?>) clsType.getActualTypeArguments()[0];
    }


    @Test
    @DisplayName("Then return the correct field from entity marked as @PrimaryKey")
    void getPrimaryKeyFromEntity() throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // when asked for the entity Primary Key
        final PrimaryKeyField primaryKeyColumn = metamodel.getPrimaryKeyColumn();
        final Field actualField = primaryKeyColumn.getField();
        assertEquals(Person.class.getDeclaredField("id"), actualField);
    }

    @Test
    @DisplayName("Then return the list of all columns names")
    void getColumns() throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final List<String> columnNames = metamodel.getColumns()
                .stream().map(cf -> cf.getField().getName()).collect(Collectors.toList());
        assertEquals(getColumnNamesFromMockEntity() ,columnNames);
    }


    @Override
    public Metamodel<?> getMetamodel() {
        return Metamodel.of(Person.class);
    }
}