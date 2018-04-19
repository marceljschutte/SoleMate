/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.gui;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bulenkov.darcula.DarculaLaf;

@ApplicationScoped
public class UI {
    private static final Logger log = LogManager.getLogger(UI.class);

    private JFrame mainWindow;
    private JTabbedPane tabbedPane;

    @PostConstruct
    private void setup() {
        log.info("starting constructing: UI");
        try {
            SwingUtilities.invokeAndWait(() -> {
                log.info("starting constructing: main window");
                try {
                    BasicLookAndFeel darcula = new DarculaLaf();
                    UIManager.setLookAndFeel(darcula);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException("Error", e);  //To change body of catch statement use File | Settings | File Templates.
                }

                mainWindow = new JFrame("Window title");
                mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                mainWindow.setSize(800, 800);

                tabbedPane = new JTabbedPane(JTabbedPane.TOP);
                tabbedPane.addTab("Dashboard", makePanel("This is tab 1"));
                tabbedPane.addTab("Tables", makePanel("This is tab 2"));
                mainWindow.add(tabbedPane);

                log.info("finished constructing: main window");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("finished constructing: UI");
    }

    public void showMainWindow() {
        exec("show main window", () -> mainWindow.setVisible(true));
    }

    private static JPanel makePanel(String text) {
        JPanel p = new JPanel();
        p.add(new Label(text));
        p.setLayout(new GridLayout(1, 1));
        return p;
    }

    private void exec(final String name, final Runnable func) {
        log.info("starting swing-invoke: " + name);
        SwingUtilities.invokeLater(() -> {
            log.info("starting swing-event: " + name);
            func.run();
            log.info("finished swing-event: " + name);
        });
        log.info("finished swing-invoke: " + name);
    }
}
