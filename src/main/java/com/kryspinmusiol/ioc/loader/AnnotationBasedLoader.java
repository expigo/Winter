package com.kryspinmusiol.ioc.loader;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class AnnotationBasedLoader implements Loader {
    @Override
    public Set<Class<?>> getServices() {
        Reflections reflections = new Reflections();
        reflections.getTypesAnnotatedWith(Conf.class);

        return null;
    }

    @Override
    public Map<Class<?>, Supplier<?>> assembleObjectGraph(Set<Class<?>> services) {
        return null;
    }
}
