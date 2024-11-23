package Controller;

import View.MainMenuView;
import com.sun.tools.javac.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControladorMenuPrincipal implements ActionListener {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private MainMenuView view;
    private MainViewController mainController;

    public ControladorMenuPrincipal(MainViewController controller, MainMenuView view) {
        this.view = view;
        this.mainController = controller;
        view.getBotonLector().addActionListener(this);
        view.getBootonEscritor().addActionListener(this);
        view.getBotonSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBotonSalir()) {
            System.exit(0);
        }
        if (e.getSource() == view.getBotonLector()) {
            mainController.iniciarPantallaCarga();
            Runnable task = () -> {
                mainController.iniciarVistaLector();
            };
            scheduler.schedule(task, 3, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
        if (e.getSource() == view.getBootonEscritor()) {
            mainController.iniciarPantallaCarga();
            Runnable task = () -> {
                mainController.iniciarVistaWritter();
            };
            scheduler.schedule(task, 3, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
    }
}
