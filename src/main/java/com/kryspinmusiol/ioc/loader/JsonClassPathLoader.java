package com.kryspinmusiol.ioc.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kryspinmusiol.ioc.exception.LoaderException;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class JsonClassPathLoader implements Loader {

    private final String source;

    public JsonClassPathLoader(String source) {
        this.source = source;
    }


    @Override
    public Map<Class<?>, Supplier<?>> loadConfiguration() {

        Map<Class<?>, Supplier<?>> registry = new HashMap<>();

        try {
            Path path = FileSystems.getDefault().getPath(source);
            String configFileContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            Set<Registration> parsedRegistrations = mapper.readValue(configFileContent, mapper.getTypeFactory().constructCollectionType(Set.class, Registration.class));
            for (Registration parsedRegistration : parsedRegistrations) {
                Class<?> cls = Class.forName(parsedRegistration.type);
                Supplier<?> supplier = () -> {
                    try {
                        return Class.forName(parsedRegistration.type).getConstructor().newInstance();
                    } catch(Exception e) {
                        throw new LoaderException("No such class declaration found!", e.getCause());
                    }
                };

                registry.put(cls, supplier);
            }
        } catch (Exception e) {
            throw new LoaderException("Cannot read configuration file", e.getCause());
        }


        return registry;

    }


    private static class Registration {
        public String mapTo;
        public String type;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Registration{");
            sb.append("mapTo='").append(mapTo).append('\'');
            sb.append(", type='").append(type).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
