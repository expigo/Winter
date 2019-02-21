package com.kryspinmusiol.ioc.loader;

import com.kryspinmusiol.ioc.annotation.Bean;
import com.kryspinmusiol.ioc.annotation.configuration.Conf;
import com.kryspinmusiol.ioc.exception.LoaderException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class AnnotationBasedLoader implements Loader {

    private final String source;

    public AnnotationBasedLoader(String source) {
        this.source = source;
    }

    @Override
    public Map<Class<?>, Supplier<?>> loadConfiguration() {
        Reflections reflections = new Reflections(source);
        final Set<Class<?>> typesAnnotatedWithConf = reflections.getTypesAnnotatedWith(Conf.class);
        final Set<Class<?>> services = new HashSet<>();
        return getClassSupplierMap(typesAnnotatedWithConf);

    }

    private Map<Class<?>, Supplier<?>> getClassSupplierMap(Set<Class<?>> typesAnnotatedWithConf) {
        Map<Class<?>, Supplier<?>> registry = new HashMap<>();

        for (Class<?> configClass : typesAnnotatedWithConf) {
            final Method[] declaredMethods = configClass.getDeclaredMethods();
            for (Method beanCandidate : declaredMethods) {
                if (beanCandidate.isAnnotationPresent(Bean.class)) {
                    final Class<?> returnType = beanCandidate.getReturnType();
                    Supplier<?> supplier = getComponentSupplier(configClass, beanCandidate);
                    registry.put(returnType, supplier);
                }
            }
        }
        return registry;
    }

    private Supplier<Object> getComponentSupplier(Class<?> configClass, Method beanCandidate) {
        return () -> {
            try {
                if (Modifier.isStatic(beanCandidate.getModifiers())) {
                    return beanCandidate.invoke(null);
                } else {
                    final Object o = configClass.getConstructor().newInstance();
                    return beanCandidate.invoke(o);
                }
            } catch (Exception e) {
                throw new LoaderException("Loader cannot load configuration", e.getCause());
            }
        };
    }


}
