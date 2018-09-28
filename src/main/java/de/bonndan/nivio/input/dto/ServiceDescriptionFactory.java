package de.bonndan.nivio.input.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bonndan.nivio.input.ReadingException;
import de.bonndan.nivio.landscape.LandscapeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDescriptionFactory {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDescriptionFactory.class);
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public static List<ServiceDescription> fromYaml(String yml) {

        List<ServiceDescription> services = new ArrayList<>();

        Source source = null;
        try {
            source = mapper.readValue(yml, Source.class);
        } catch (IOException e) {
            logger.error("Failed to read yml", e);
        }
        if (source == null) {
            logger.warn("Got null out of yml string " + yml);
            return services;
        }
        source.ingress.forEach(serviceDescription -> {
            serviceDescription.setType(LandscapeItem.TYPE_INGRESS);
            services.add(serviceDescription);
        });
        source.services.forEach(serviceDescription -> {
            serviceDescription.setType(LandscapeItem.TYPE_APPLICATION);
            services.add(serviceDescription);
        });
        source.infrastructure.forEach(serviceDescription -> {
            serviceDescription.setType(LandscapeItem.TYPE_INFRASTRUCTURE);
            services.add(serviceDescription);
        });

        return services;

    }
}
