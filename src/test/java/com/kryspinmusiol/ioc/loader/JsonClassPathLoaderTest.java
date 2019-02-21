package com.kryspinmusiol.ioc.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonClassPathLoaderTest {

    private JsonClassPathLoader jsonClassPathLoader;

    @BeforeEach
    void setUp() {
        jsonClassPathLoader = new JsonClassPathLoader("config.json");

    }

    @Test
    void shouldReturnMapOfAllRegistrations() {
        jsonClassPathLoader.loadConfiguration();
    }
}