package Controller;

import Model.MovimientoListener;
import Model.Partida;
import Model.Pieza;
import Model.Posicion;
import Model.Tablero;
import View.WritterView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import View.TableroEscritor;

public class WritterViewController implements ActionListener, MovimientoListener {
    public WritterView view;
    private WritterController mainController;

    private Partida partida;
    private Tablero tablero;
    private int jugada;


    public WritterViewController(WritterController controller) {
        this.view = new WritterView(this);
        this.mainController = controller;
        this.tablero = Partida.getDefaultTablero();
        view.getVolver().addActionListener(this);
        view.setTableroEscritor(tablero);
        view.getTableroEscritor().setMovimientoListener(this);
        this.partida = new Partida("");
        this.jugada = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getVolver()) {
            mainController.mainController.iniciarVistaMenu();
        }
    }

    @Override
    public void onMovimientoRealizado(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        // Procesar el movimiento en el modelo y actualizar la vista si es necesario
        System.out.println("Movimiento desde " + filaOrigen + "," + colOrigen + " hasta " + filaDestino + "," + colDestino);
        Pieza pieza = tablero.getPieza(new Posicion(filaOrigen, colOrigen));
        System.out.println("Pieza = "+ pieza.nomenclatura);
        comprobarMovimiento(pieza, new Posicion(filaDestino, colDestino));
    }
    
    private boolean comprobarMovimiento(Pieza pieza, Posicion posicionFinal){
        if(this.jugada%2 == 0){
            if(pieza.getColor()==Pieza.Color.BLANCO){
                return true;
            }
        }else{
            if(pieza.getColor()==Pieza.Color.NEGRO){
                return true;
            }
        }
        return false;
    }
}
