package com.agile.spirit.jba.infra.util;

import java.util.Properties;

import org.springframework.core.env.Environment;

public class PropertyHelper {

    /**
     * Add an environment property to a Properties map, thanks to its key.
     * 
     * @param env the environment containing the properties
     * @param properties the properties to
     * @param propertyKey the key of the property
     */
    public static void setProperty(final Environment env, final Properties properties, final String propertyKey) {
        String propertyValue = env.getProperty(propertyKey);
        if (propertyValue != null && !propertyValue.isEmpty()) {
            properties.put(propertyKey, propertyValue);
        }
    }
    
}
