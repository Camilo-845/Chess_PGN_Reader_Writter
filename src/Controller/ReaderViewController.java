package Controller;

import Model.Partida;
import Model.Tablero;
import View.ReaderView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ReaderViewController implements ActionListener {
    private final ReaderView view;
    private MainViewController mainController;

    private Partida partida;
    private Tablero tablero;
    private int jugada;


    public ReaderViewController(MainViewController controller, ReaderView view) {
        this.view = view;
        this.mainController = controller;
        this.jugada = 0;
        this.tablero = null;

        view.BotonSeleccionarArchivo.addActionListener(this);
        view.BotonSiguiente.addActionListener(this);
        view.BotonAnterior.addActionListener(this);
        view.BotonVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.BotonSeleccionarArchivo) {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("PGN Files", "pgn");
            fileChooser.setFileFilter(filtro);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(view.BotonSeleccionarArchivo);
            String selection = fileChooser.getSelectedFile().getPath();
            System.out.println("Selection: " + selection);
            generarPartida(selection);

        }
        if (e.getSource() == view.BotonSiguiente) {
            if (partida != null) {
                if(jugada + 1 < partida.numeroMovimientos){
                    jugada ++;
                    tablero = partida.obtenerTablero(jugada);
                }
            }
        }
        if (e.getSource() == view.BotonAnterior) {
            if (partida != null) {
                if(jugada -1 >= 0){
                    jugada --;
                    tablero = partida.obtenerTablero(jugada);
                }
            }
        }
        if (e.getSource() == view.BotonVolver) {
            mainController.iniciarVistaMenu();
        }
    }

    private void generarPartida(String path){
        this.partida = new Partida(path);
        view.PGN_moves.setText(partida.movimientos.toString());
        jugada = 0;
        tablero = partida.tableroInicial;
    }
}
