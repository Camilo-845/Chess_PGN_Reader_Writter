package Controller;

import Model.Partida;
import Model.Tablero;
import View.ReaderView;
import View.TableroLector;

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
        this.tablero = Partida.getDefaultTablero();

        view.getBotonSeleccionarArchivo().addActionListener(this);
        view.getBotonSiguiente().addActionListener(this);
        view.getBotonAnterior().addActionListener(this);
        view.getBotonVolver().addActionListener(this);
        view.setTableroLector(tablero);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBotonSeleccionarArchivo()) {
            JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("PGN Files", "pgn");
            fileChooser.setFileFilter(filtro);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(view.getBotonSeleccionarArchivo());
            String selection = fileChooser.getSelectedFile().getPath();
            System.out.println("Selection: " + selection);
            generarPartida(selection);
            actualizarTablero(tablero);

        }
        if (e.getSource() == view.getBotonSiguiente()) {
            if (partida != null) {
                if(jugada + 1 < partida.numeroMovimientos){
                    jugada ++;
                    tablero = partida.obtenerTablero(jugada);
                    actualizarTablero(tablero);
                }
            }
        }
        if (e.getSource() == view.getBotonAnterior()) {
            if (partida != null) {
                if(jugada -1 >= 0){
                    jugada --;
                    tablero = partida.obtenerTablero(jugada);
                    actualizarTablero(tablero);
                }
            }
        }
        if (e.getSource() == view.getBotonVolver()) {
            mainController.iniciarVistaMenu();
        }
    }

    private void generarPartida(String path){
        this.partida = new Partida(path);
        view.getPGN_moves().setText(partida.movimientos.toString());
        jugada = 0;
        tablero = partida.tableroInicial;
    }
    
    private void actualizarTablero(Tablero tablero1){
        view.setTableroLector(tablero1);
    }
}
