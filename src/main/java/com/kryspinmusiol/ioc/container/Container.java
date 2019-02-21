package com.kryspinmusiol.ioc.container;

import com.kryspinmusiol.ioc.annotation.Inject;
import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;
import com.kryspinmusiol.ioc.exception.ContainerException;
import com.kryspinmusiol.ioc.loader.AnnotationBasedLoader;
import com.kryspinmusiol.ioc.loader.JsonClassPathLoader;
import com.kryspinmusiol.ioc.loader.Loader;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class Container {


    private final Loader configurationLoader;
    private Map<Class<?>, Supplier<?>> objectGraph;


    private Container(Loader loader) {
        configurationLoader = loader;
        objectGraph = loader.loadConfiguration();
    }


    public static Container create() {
        return new Container(getAnnotationLoader());
    }

    public static Container create(String configurationPath) {
        return new Container(new JsonClassPathLoader(configurationPath));
    }

    public <T> T resolve(Class<T> cls) {

        try {
            final T t = cls.getConstructor().newInstance();
            final Field[] declaredFields = cls.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Inject.class)) {
                    final Class<?> injectedFieldType = declaredField.getType();
                    final Supplier<?> supplier = objectGraph.get(injectedFieldType);

                    final Object objectToInject = supplier.get();
                    declaredField.setAccessible(true);
                    declaredField.set(t, objectToInject);
                }
            }

            return t;
        } catch (Exception e) {
            throw new ContainerException("Cannot resolve dependency for class: " + cls, e.getCause());
        }

    }


    private static Loader getAnnotationLoader() {
        final String callerClassName = getCallerRootPackage();
        final String[] packages = callerClassName.split("\\.");
        final String callerRootPackage = packages[0] + "." + packages[1];
        System.out.println("callerPackage = " + callerRootPackage);

        Reflections reflections = new Reflections(callerRootPackage);

        final Optional<ComponentScan> componentScanOpt = reflections.getTypesAnnotatedWith(ComponentScan.class).stream()
                .map(c -> c.getAnnotation(ComponentScan.class))
                .findFirst();

        if (componentScanOpt.isPresent()) {
            final String baseDir = componentScanOpt.get().baseDir();
            return new AnnotationBasedLoader(baseDir);
        } else {
            throw new ContainerException("There was a problem with annotation configuration");
        }

    }


    private static String getCallerRootPackage() {


        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Container.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }
        return null;
    }


    public void getGraph() {
        System.out.println(configurationLoader.loadConfiguration());
    }


}

