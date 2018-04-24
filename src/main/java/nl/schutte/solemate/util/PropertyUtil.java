/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SoleMate - Description.
 */
public class PropertyUtil {

    static Properties properties;

    private PropertyUtil(){}

    private static void initialize() {

        try {
            properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("application.properties");
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Error", e);  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            initialize();
            //throw new IllegalStateException("Properties niet geladen");
        }
        return properties.getProperty(key) != null ? properties.getProperty(key) : "";
    }
}




