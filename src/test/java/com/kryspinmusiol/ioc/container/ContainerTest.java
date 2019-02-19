package com.kryspinmusiol.ioc.container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    @DisplayName("Given the annotation container")
    void setUp() {
        container = new Container();
    }

}