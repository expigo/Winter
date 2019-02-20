package com.kryspinmusiol.ioc.loader;

import com.kryspinmusiol.ioc.annotation.Bean;
import com.kryspinmusiol.ioc.annotation.configuration.Conf;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AnnotationBasedLoader implements Loader {

    private final String source;

    public AnnotationBasedLoader(String source) {
        this.source = source;
    }

    @Override
    public Set<Class<?>> getServices() {
        Reflections reflections = new Reflections(source);
        final Set<Class<?>> typesAnnotatedWithConf = reflections.getTypesAnnotatedWith(Conf.class);

        final Set<Class<?>> services = new HashSet<>();


        for (Class<?> configClass : typesAnnotatedWithConf) {
            final Method[] declaredMethods = configClass.getDeclaredMethods();

            for (Method beanCandidate : declaredMethods) {
                if (beanCandidate.isAnnotationPresent(Bean.class)) {
                    final Class<?> returnType = beanCandidate.getReturnType();
                    services.add(returnType);
                }
            }
        }

        return services;
    }

    @Override
    public Map<Class<?>, Supplier<?>> assembleObjectGraph(Set<Class<?>> services) {
        return null;
    }
}
