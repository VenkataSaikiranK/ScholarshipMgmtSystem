package com.dal.group7.config;

import java.io.IOException;
import java.util.Properties;

import static com.dal.group7.constants.ApplicationConstants.*;

public class ApplicationConfiguration {
    private Properties properties;

    public ApplicationConfiguration() {
        loadProperties();
    }

    public String getDbUrl() {
       return properties.getProperty(DB_URL)
                .concat(JOIN_USER)
                .concat(properties.getProperty(DB_USER))
                .concat(JOIN_PASSWORD)
                .concat(properties.getProperty(DB_PASSWORD));
    }

    private void loadProperties() {
        try(var inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
