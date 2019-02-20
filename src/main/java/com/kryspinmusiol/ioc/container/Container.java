package com.kryspinmusiol.ioc.container;

import com.kryspinmusiol.ioc.annotation.configuration.ComponentScan;
import com.kryspinmusiol.ioc.loader.AnnotationBasedLoader;
import com.kryspinmusiol.ioc.loader.Loader;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Optional;

public class Container {

    private final Loader configurationLoader;


    private Container(Loader loader) {
        configurationLoader = loader;
    }



    public static Container create(){
        return new Container(getAppropriateLoader());
    }

    private static Loader getAppropriateLoader() {
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
            return null; // todo: JsonClassPathLoader
        }

    }


    private static String getCallerRootPackage() {

        
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Container.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
    }



}

