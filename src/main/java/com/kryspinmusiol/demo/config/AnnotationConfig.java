package com.kryspinmusiol.demo.config;

import com.kryspinmusiol.demo.model.DonaldDuck;
import com.kryspinmusiol.demo.model.Duck;
import com.kryspinmusiol.ioc.annotation.Bean;
import com.kryspinmusiol.ioc.annotation.Component;
import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;
import com.kryspinmusiol.ioc.annotation.configuration.Conf;
import com.kryspinmusiol.orm.entitymanager.DBEntityManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Callable;

@Conf
@ComponentScan(baseDir = "com.kryspinmusiol.demo")
public class AnnotationConfig {

    public Connection getH2Connection() throws SQLException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Properties dbConfig = new Properties();
        try {
            dbConfig.load(new FileInputStream(rootPath + "db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                dbConfig.getProperty("url"),
                dbConfig.getProperty("user"),
                dbConfig.getProperty("pass")
        );
    }

    @Bean
    public Object getObject() { return new Object(); }

    @Bean
    public DBEntityManager getEm() { return new DBEntityManager(); }

    @Bean
    public Duck getDD() { return new DonaldDuck(); }

    public Callable<String> getCallable() { return () -> "ok"; }

}
