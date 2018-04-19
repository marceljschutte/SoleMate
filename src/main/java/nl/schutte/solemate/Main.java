/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * SoleMate - Description.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        logger.info("starting main");
        SeContainer seContainer = SeContainerInitializer.newInstance().initialize();
        seContainer.getBeanManager().fireEvent(new BootEvent());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("starting shutdown");
            logger.info("finished shutdown");
        }));

        logger.info("finished main");
    }
}
