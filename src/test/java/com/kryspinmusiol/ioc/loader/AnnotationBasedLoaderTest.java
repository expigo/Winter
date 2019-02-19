package com.kryspinmusiol.ioc.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationBasedLoaderTest {

    private AnnotationBasedLoader loader;

    @BeforeEach
    @DisplayName("Given Annotation-based loader")
    void setUp() {
        loader = new AnnotationBasedLoader();
    }

    @Test
    @DisplayName("Should return all services")
    void getServices() {
        System.out.println(loader.getServices());
    }
}