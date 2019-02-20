package com.kryspinmusiol.ioc.loader;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public interface Loader {

    default Map<Class<?>, Supplier<?>> getObjectGraph() {
        Set<Class<?>> services = getServices();
        return assembleObjectGraph(services);
    }

    Set<Class<?>> getServices();
    Map<Class<?>, Supplier<?>> assembleObjectGraph(Set<Class<?>> services);


}
