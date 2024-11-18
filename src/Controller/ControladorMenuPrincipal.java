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
        view.BootonEscritor.addActionListener(this);
        view.BotonLector.addActionListener(this);
        view.BotonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.BotonSalir) {
            System.exit(0);
        }
        if (e.getSource() == view.BotonLector) {
            mainController.iniciarVistaLector();
        }
        if (e.getSource() == view.BootonEscritor) {
            mainController.iniciarVistaWritter();
        }
    }
}
