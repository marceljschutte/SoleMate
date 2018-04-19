/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import nl.schutte.solemate.gui.UI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * SoleMate - Description.
 */
@ApplicationScoped
public class App {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Main.class);

    public void onBoot(@Observes BootEvent e, UI ui) {
        log.info("starting cdi-event: boot");
        ui.showMainWindow();
        log.info("finished cdi-event: boot");
    }
}
