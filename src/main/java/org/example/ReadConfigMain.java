package org.example;

import java.io.IOException;

public class ReadConfigMain {
    static GetPropertyValues properties = new GetPropertyValues();

    public static String getPropertiesConfig() throws IOException {
        return properties.getPropValues();
    }
}
