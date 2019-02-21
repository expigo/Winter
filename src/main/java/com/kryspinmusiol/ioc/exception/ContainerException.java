package com.kryspinmusiol.ioc.exception;

import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;

@ComponentScan(baseDir = "com")
public class ContainerException extends RuntimeException {
    public ContainerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerException(String message) {
        super(message);
    }
}
