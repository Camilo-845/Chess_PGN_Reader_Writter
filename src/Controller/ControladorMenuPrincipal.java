package Controller;

import View.MainMenuView;
import com.sun.tools.javac.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenuPrincipal implements ActionListener {
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
            mainController.iniciarVistaLector();
        }
        if (e.getSource() == view.getBootonEscritor()) {
            mainController.iniciarVistaWritter();
        }
    }
}
