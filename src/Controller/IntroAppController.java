package Controller;

import Model.Partida;
import Model.Tablero;
import View.IntroApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroAppController implements ActionListener{
    private IntroApp view;
    private MainViewController mainController;


    public IntroAppController(MainViewController controller, IntroApp view) {
        this.view = view;
        this.mainController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
