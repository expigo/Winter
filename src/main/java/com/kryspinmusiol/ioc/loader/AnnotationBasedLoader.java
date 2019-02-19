package com.kryspinmusiol.ioc.loader;

import com.kryspinmusiol.ioc.annotation.configuration.Conf;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class AnnotationBasedLoader implements Loader {
    @Override
    public Set<Class<?>> getServices() {
        Reflections reflections = new Reflections("com");

        final Set<Class<?>> typesAnnotatedWithConf = reflections.getTypesAnnotatedWith(Conf.class);

        return typesAnnotatedWithConf;
    }

    @Override
    public Map<Class<?>, Supplier<?>> assembleObjectGraph(Set<Class<?>> services) {
        return null;
    }
}
