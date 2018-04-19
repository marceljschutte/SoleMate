/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base class for Swing actions that fire a CDI event (asynchronously).
 *
 * @param <T> Type of the CDI event
 */
abstract public class AbstractCdiAction<T> extends AbstractAction {
    private static final Logger log = LogManager.getLogger(Main.class);

    @Inject
    private Event<T> event;
    @Inject
    private T command;

    protected AbstractCdiAction(final String name, final String actionCommand) {
        super(name);
        putValue(Action.ACTION_COMMAND_KEY, actionCommand);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        log.info("starting swing-action: $s", e.getActionCommand());
        event.fireAsync(command);
        log.info("finished swing-action: $s", e.getActionCommand());
    }
}
