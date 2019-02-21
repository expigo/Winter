package com.kryspinmusiol.ioc.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationBasedLoaderTest {

    private AnnotationBasedLoader loader;

    @BeforeEach
    @DisplayName("Given Annotation-based loader")
    void setUp() {
        loader = new AnnotationBasedLoader("com.kryspinmusiol.ioc.testfixture");
    }

    @Test
    @DisplayName("Should return all services")
    void getServices() {
        loader.loadConfiguration();

    }
}