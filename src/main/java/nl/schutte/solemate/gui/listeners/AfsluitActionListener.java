/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.gui.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import nl.schutte.solemate.gui.UI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SoleMate - Description.
 */
public class AfsluitActionListener extends AbstractActionListener {

    private static final Logger log = LogManager.getLogger(AfsluitActionListener.class);

    public AfsluitActionListener(JMenuItem menuItem) {
        menuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info(e);
        System.exit(0);
    }
}
