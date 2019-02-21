package com.kryspinmusiol.demo.config;

import com.kryspinmusiol.demo.model.DonaldDuck;
import com.kryspinmusiol.demo.model.Duck;
import com.kryspinmusiol.ioc.annotation.Bean;
import com.kryspinmusiol.ioc.annotation.Component;
import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;
import com.kryspinmusiol.ioc.annotation.configuration.Conf;
import com.kryspinmusiol.orm.entitymanager.DBEntityManager;

import java.util.concurrent.Callable;

@Conf
@ComponentScan(baseDir = "com.kryspinmusiol.demo")
public class AnnotationConfig {

    @Bean
    public Object getObject() { return new Object(); }

    @Bean
    public DBEntityManager getEm() { return new DBEntityManager(); }

    @Bean
    public Duck getDD() { return new DonaldDuck(); }

    public Callable<String> getCallable() { return () -> "ok"; }

}
