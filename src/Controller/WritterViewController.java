package Controller;

import Model.Partida;
import Model.Tablero;
import View.WritterView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WritterViewController implements ActionListener {
    private WritterView view;
    private MainViewController mainController;

    private Partida partida;
    private Tablero tablero;
    private int jugada;


    public WritterViewController(MainViewController controller, WritterView view) {
        this.view = view;
        this.mainController = controller;
        view.BotonVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.BotonVolver) {
            mainController.iniciarVistaMenu();
        }
    }

}
