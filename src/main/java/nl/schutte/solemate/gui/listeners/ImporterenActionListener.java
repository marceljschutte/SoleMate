/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.MethodHandles;

import javax.swing.JMenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SoleMate - Description.
 */
public class ImporterenActionListener implements ActionListener {

    private static final Logger log = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public ImporterenActionListener(JMenu menuItem) {
        menuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement method actionPerformed() --> void");
    }
}
