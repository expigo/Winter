package com.kryspinmusiol.ioc.exception;

import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;

@ComponentScan(baseDir = "com")
public class LoaderException extends RuntimeException {
    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
