package com.kryspinmusiol.orm.metamodel;

import com.kryspinmusiol.modelexample.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import static org.junit.jupiter.api.Assertions.*;

class MetamodelTest {

    private Metamodel<Person> metamodel;

    @BeforeEach
    @DisplayName("Given that we want Metamodel of Person class")
    void setUp() {
        // when calling factory for the Metamodel
        metamodel = Metamodel.of(Person.class);
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
    void getPrimaryKeyFromEntity() {
        // when asked for the entity Primary Key

    }


}