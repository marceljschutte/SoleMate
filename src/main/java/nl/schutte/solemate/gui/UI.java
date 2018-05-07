/*
 * Copyright 2009-2018 PIANOo; TenderNed programma.
 */
package nl.schutte.solemate.gui;

import static nl.schutte.solemate.util.PropertyUtil.getProperty;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.time.Year;
import java.util.SortedMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicLookAndFeel;

import nl.schutte.solemate.gui.listeners.AboutActionListener;
import nl.schutte.solemate.gui.listeners.AfsluitActionListener;
import nl.schutte.solemate.gui.listeners.ExporterenActionListener;
import nl.schutte.solemate.gui.listeners.HelpItemActionListener;
import nl.schutte.solemate.gui.listeners.ImporterenActionListener;
import nl.schutte.solemate.model.Schoen;
import nl.schutte.solemate.service.TotalenService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bulenkov.darcula.DarculaLaf;

@ApplicationScoped
public class UI {
    private static final Logger log = LogManager.getLogger(UI.class);
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int HEIGHT_TOTALENPANEL = 190;

    @Inject
    private TotalenService totalenService;



    private JFrame mainWindow;

    private JTabbedPane tabPane;
    private JPanel wedstrijdPanel, trainingPanel, bootcampPanel, totalenPanel, totalenPerSchoenPanel, totalenPerJaarPanel;
    private JScrollPane totalenPerJaarScrollPanel;
    private JMenu menuBestand, menuBewerken, menuOverzichten, menuHelp;
    private JMenuBar menuBar;
    private JMenuItem importerenActie, exporterenActie, afsluitenActie, helpItemActie, aboutActie;
    private JButton alleJarenButton;

    private double screenWidth, screenHeight;


    @PostConstruct
    private void setup() {
        log.info("starting constructing: UI");
        try {
            SwingUtilities.invokeAndWait(() -> {
                log.info("starting constructing: main window");
                configureDarculaLookAndFeel();
                createMainWindow();
                log.info("finished constructing: main window");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("finished constructing: UI");
    }

    private void configureDarculaLookAndFeel() {
        try {
            BasicLookAndFeel darcula = new DarculaLaf();
            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Error", e);  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void createMainWindow() {
        mainWindow = new JFrame(String.format("%s versie %s", getProperty("nl.schutte.solemate.mainwindow.title"), getProperty("nl.schutte.solemate.version")));
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setResizable(false);
        mainWindow.setAlwaysOnTop(true);
        mainWindow.setLocation(createPositionPoint());
        createMenu();
        createTotalenPanel();
        createTabPanel();

    }

    private void createTotalenPanel() {
        totalenPanel = new JPanel();
        totalenPanel.setPreferredSize(new Dimension(750, HEIGHT_TOTALENPANEL+50));
        TitledBorder border = new TitledBorder(getProperty("nl.schutte.solemate.panel.title.totalen"));
        totalenPanel.setBorder(border);



        createTotalenPerSchoenPanel();
        createTotalenPerJaarPanel();

        mainWindow.add(totalenPanel, BorderLayout.NORTH);
    }

    private void createTotalenPerJaarPanel() {
        totalenPerJaarPanel = new JPanel();
        TitledBorder border = new TitledBorder(getProperty("nl.schutte.solemate.panel.title.totalen.perjaar"));
        totalenPerJaarPanel.setPreferredSize(new Dimension(375, HEIGHT_TOTALENPANEL));
        totalenPerJaarPanel.setBorder(border);
        alleJarenButton = new JButton(getProperty("nl.schutte.solemate.button.allejaren"));
        addTotalenPerJaar();

        totalenPerJaarPanel.add(alleJarenButton, BorderLayout.SOUTH);
        totalenPanel.add(totalenPerJaarPanel, BorderLayout.CENTER);
    }

    private void addTotalenPerJaar() {
        SortedMap<Year, Integer> temp = totalenService.getTotalenPerJaar().tailMap(Year.now().minusYears(4));

        temp.forEach((key, value) -> {
            addOutputFieldAfstandPerJaar(key, value);
        });
    }

    private void addOutputFieldAfstandPerJaar(Year key, Integer value){

        JLabel label = new JLabel(String.valueOf(key));
        JTextField field = new JTextField();
        field.setText(String.valueOf(value));
        field.setEditable(false);
        field.setPreferredSize(new Dimension(250, 20));
        field.setHorizontalAlignment(JTextField.TRAILING);
        label.setPreferredSize(new Dimension(100, 20));
        totalenPerJaarPanel.add(label);
        totalenPerJaarPanel.add(field);

    }

    private void createTotalenPerSchoenPanel() {
        totalenPerSchoenPanel = new JPanel();
        TitledBorder border = new TitledBorder(getProperty("nl.schutte.solemate.panel.title.totalen.perschoen"));
        totalenPerSchoenPanel.setPreferredSize(new Dimension(375, HEIGHT_TOTALENPANEL));
        totalenPerSchoenPanel.setBorder(border);

        addTotalenPerSchoen();

        totalenPanel.add(totalenPerSchoenPanel, BorderLayout.CENTER);
    }

    private void addTotalenPerSchoen() {
        SortedMap<Schoen, Integer> temp = totalenService.getTotalenPerSchoen();

        temp.forEach((Schoen key, Integer value) -> {
            if(key.getPeriode().getEindDatum() == null) {
                addOutputFieldAfstandPerSchoen(key, value);
            }
        });
    }

    private void addOutputFieldAfstandPerSchoen(Schoen key, Integer value){

        JLabel label = new JLabel(key.toString());
        JTextField field = new JTextField();
        field.setText(String.valueOf(value));
        field.setEditable(false);
        field.setPreferredSize(new Dimension(250, 20));
        field.setHorizontalAlignment(JTextField.TRAILING);
        label.setPreferredSize(new Dimension(100, 20));
        totalenPerSchoenPanel.add(label);
        totalenPerSchoenPanel.add(field);

    }

    private void createTabPanel() {

        tabPane = new JTabbedPane();
        tabPane.setPreferredSize(new Dimension(750, 625));
        tabPane.setMaximumSize(new Dimension(700, 500));

        createTrainingTab();
        createBootCampTab();
        createWedstrijdTab();


        mainWindow.add(tabPane, BorderLayout.CENTER);
    }

    private void createWedstrijdTab() {
        wedstrijdPanel = new JPanel();


        tabPane.add(getProperty("nl.schutte.solemate.tab.title.wedstrijd"), wedstrijdPanel);
    }

    private void createBootCampTab() {
        bootcampPanel = new JPanel();

        tabPane.add(getProperty("nl.schutte.solemate.tab.title.bootcamp"),bootcampPanel);
    }

    private void createTrainingTab() {
        trainingPanel = new JPanel();

        tabPane.add(getProperty("nl.schutte.solemate.tab.title.training"),trainingPanel);
    }

    private Point createPositionPoint() {
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        Double startX = (screenWidth / 2) - WIDTH/2;
        Double startY = (screenHeight/2) - HEIGHT/2;

        Point result = new Point(startX.intValue(), startY.intValue());
        return result;
    }

    private void createMenu() {
        menuBar = new JMenuBar();
        addHoofdMenuItems();
        addActionListeners();
        addMnemonics();
        mainWindow.setJMenuBar(menuBar);
    }

    private void addHoofdMenuItems() {
        createMenuBestand();
        createMenuBewerken();
        createMenuOverzichten();
        createMenuHelp();

        menuBar.add(menuBestand);
        menuBar.add(menuBewerken);
        menuBar.add(menuOverzichten);
        menuBar.add(menuHelp);
    }

    private void createMenuHelp() {
        menuHelp = new JMenu(getProperty("nl.schutte.solemate.menu.hoofd.help"));
        helpItemActie = new JMenuItem(getProperty("nl.schutte.solemate.menu.hoofd.help.helpitems"));
        aboutActie = new JMenuItem(getProperty("nl.schutte.solemate.menu.hoofd.help.about"));
        menuHelp.add(helpItemActie);
        menuHelp.addSeparator();
        menuHelp.add(aboutActie);
    }

    private void createMenuOverzichten() {
        menuOverzichten = new JMenu(getProperty("nl.schutte.solemate.menu.hoofd.overzichten"));
    }

    private void createMenuBewerken() {
        menuBewerken = new JMenu(getProperty("nl.schutte.solemate.menu.hoofd.bewerken"));
    }

    private void createMenuBestand() {
        menuBestand = new JMenu(getProperty("nl.schutte.solemate.menu.hoofd.bestand"));
        importerenActie = new JMenuItem(getProperty("nl.schutte.solemate.menu.hoofd.bestand.import"));
        exporterenActie = new JMenuItem(getProperty("nl.schutte.solemate.menu.hoofd.bestand.export"));
        afsluitenActie = new JMenuItem(getProperty("nl.schutte.solemate.menu.hoofd.bestand.afsluiten"));
        menuBestand.add(importerenActie);
        menuBestand.add(exporterenActie);
        menuBestand.addSeparator();
        menuBestand.add(afsluitenActie);
    }

    private void addActionListeners() {
        afsluitenActie.addActionListener(new AfsluitActionListener(menuBestand));
        importerenActie.addActionListener(new ImporterenActionListener(menuBestand));
        exporterenActie.addActionListener(new ExporterenActionListener(menuBestand));


        helpItemActie.addActionListener(new HelpItemActionListener(menuBestand));
        aboutActie.addActionListener(new AboutActionListener(menuBestand));
    }

    private void addMnemonics() {
        menuBestand.setMnemonic(KeyEvent.VK_B);
        menuBewerken.setMnemonic(KeyEvent.VK_W);
        menuOverzichten.setMnemonic(KeyEvent.VK_O);
        menuHelp.setMnemonic(KeyEvent.VK_H);
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
