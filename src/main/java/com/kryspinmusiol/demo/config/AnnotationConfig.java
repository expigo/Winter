package com.kryspinmusiol.demo.config;

import com.kryspinmusiol.ioc.annotation.Bean;
import com.kryspinmusiol.ioc.annotation.Component;
import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;
import com.kryspinmusiol.ioc.annotation.configuration.Conf;

import java.util.concurrent.Callable;

@Conf
@ComponentScan(baseDir = "com.kryspinmusiol.demo")
public class AnnotationConfig {

    @Bean
    public Object getObject() { return new Object(); }

    public Callable<String> getCallable() { return () -> "ok"; }

}
