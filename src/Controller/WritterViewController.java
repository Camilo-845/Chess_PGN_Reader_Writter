package Controller;

import Model.MovimientoListener;
import Model.PGN;
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
        view.getBotonAnterior1().addActionListener(this);
        view.setTableroEscritor(tablero);
        view.getTableroEscritor().setMovimientoListener(this);
        this.partida = new Partida();
        this.jugada = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getVolver()) {
            mainController.mainController.iniciarVistaMenu();
        }
        if (e.getSource() == view.getBotonAnterior1()) {
            String data = partida.PartidaToPGN();
            new PGN().saveGame(data);
        }
    }

    @Override
    public void onMovimientoRealizado(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        // Procesar el movimiento en el modelo y actualizar la vista si es necesario
        System.out.println("Movimiento desde " + filaOrigen + "," + colOrigen + " hasta " + filaDestino + "," + colDestino);
        Pieza pieza = tablero.getPieza(new Posicion(filaOrigen, colOrigen));
        System.out.println("Pieza = "+ pieza.nomenclatura);
        Posicion posicionFinal = new Posicion(filaDestino, colDestino);
        
        if(comprobarMovimiento(pieza, posicionFinal)){
            partida.agregarMovimiento(escribirMovimiento(pieza, posicionFinal));
            jugada++;
            this.tablero =  partida.obtenerTablero(jugada);
            actualizarTablero(tablero);
        };
    }
    
   
    
    private boolean comprobarMovimiento(Pieza pieza, Posicion posicionFinal){
        boolean captura = tablero.getPieza(posicionFinal) != null;
        if(this.jugada%2 == 0){
            if(pieza.getColor()==Pieza.Color.BLANCO){
                if(pieza.esMovimientoValido(posicionFinal, captura));{
                    return true;
                }
            }
        }else{
            if(pieza.getColor()==Pieza.Color.NEGRO){
                if(pieza.esMovimientoValido(posicionFinal, captura));{
                    return true;
                }
            }
        }
        return false;
    }
    
     public String escribirMovimiento(Pieza pieza, Posicion posicionFinal){
        Pieza piezaCapturada = tablero.getPieza(posicionFinal);
        boolean captura = piezaCapturada != null;
        
        String movimiento = ""+pieza.nomenclatura + posicionFinal.toString();
        if(captura){
            movimiento = pieza.nomenclatura+"x"+ piezaCapturada.posicion.toString();
        }
        
        return movimiento;
    }
     
     private void actualizarTablero(Tablero tablero1){
        view.setTableroEscritor(tablero1);
         System.out.println(tablero.toString());
    }
}
