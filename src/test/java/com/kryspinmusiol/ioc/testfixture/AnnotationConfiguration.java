package com.kryspinmusiol.ioc.testfixture;

import com.kryspinmusiol.ioc.annotation.Bean;
import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;
import com.kryspinmusiol.ioc.annotation.configuration.Conf;
import com.kryspinmusiol.ioc.testfixture.mock.Badger;
import com.kryspinmusiol.ioc.testfixture.mock.Bulb;
import com.kryspinmusiol.ioc.testfixture.mock.Car;

@ComponentScan(baseDir = "com.kryspinmusiol.ioc.testfixture")
@Conf
public class AnnotationConfiguration {

    @Bean
    public Car getCar() { return new Car(); }

    @Bean
    public Badger getBadger() { return new Badger(); }

    public Bulb getBulb() { return new Bulb(); }

}
