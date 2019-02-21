package com.kryspinmusiol.ioc.loader;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public interface Loader {

    Map<Class<?>, Supplier<?>> loadConfiguration();

}
