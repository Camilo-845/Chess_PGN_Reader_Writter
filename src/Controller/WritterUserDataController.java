/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.WriterUserDataView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author camilosar
 */
public class WritterUserDataController implements ActionListener{
    private WriterUserDataView view;
    private WritterController mainController;

    public WritterUserDataController(WritterController controller, WriterUserDataView view) {
        this.view = view;
        this.mainController = controller;
        view.getBotonVolver().addActionListener(this);
        view.getBotonIniciar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBotonVolver()) {
            mainController.mainController.iniciarVistaMenu();
        }
        if(e.getSource() == view.getBotonIniciar()){
            mainController.iniciarVistaWritter();
        }
    }
}
