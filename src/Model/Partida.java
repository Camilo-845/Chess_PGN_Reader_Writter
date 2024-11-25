package Model;

import java.util.ArrayList;

public class Partida {
    public ArrayList<String> movimientos;
    public int numeroMovimientos;
    public Tablero tableroInicial;

    public Partida(String path) {
        movimientos = new PGN().getMoves(path);
        numeroMovimientos = movimientos.size();
        tableroInicial = getDefaultTablero();
    }
    
    public Partida(){
        tableroInicial = getDefaultTablero();
    }
    /**
     * Obtine el tablero en la ultima jugada
     * @return Tablero
     */
    public Tablero obtenerTablero(){
        return obtenerTablero(numeroMovimientos);
    }

    /**
     * Obtiene el tablero en una ronda especificada
     * @param ronda
     * @return Tablero
     */
    public Tablero obtenerTablero(int ronda){
        if(ronda > numeroMovimientos) {
            return null;
        }
        Tablero tableroConMovimientos = new Tablero(tableroInicial);
        Pieza.Color currentColor = Pieza.Color.BLANCO;
        for (int i = 0; i < ronda; i++) {
            currentColor = ( i % 2 == 0 ) ? Pieza.Color.BLANCO : Pieza.Color.NEGRO;
            tableroConMovimientos.realizarMovimiento(currentColor, movimientos.get(i));
        }
        return tableroConMovimientos;
    }

    /**
     * Genera un tablero con la posicion inicial de las piezas del ajedrez
     * @return
     */
    public static Tablero getDefaultTablero(){
        String[] columnaPiezas = {"a","b","c","d","e","f","g","h"};
        Tablero tablero = new Tablero();

        //Blancas
        for(int i = 0; i < 8; i++){
            tablero.addPieza(new Peon(Pieza.Color.BLANCO, new Posicion(2,columnaPiezas[i])));
        }
        tablero.addPieza(new Torre(Pieza.Color.BLANCO, new Posicion(1,"a")));
        tablero.addPieza(new Caballo(Pieza.Color.BLANCO, new Posicion(1,"b")));
        tablero.addPieza(new Alfil(Pieza.Color.BLANCO, new Posicion(1,"c")));
        tablero.addPieza(new Reina(Pieza.Color.BLANCO, new Posicion(1,"d")));
        tablero.addPieza(new Rey(Pieza.Color.BLANCO, new Posicion(1,"e")));
        tablero.addPieza(new Alfil(Pieza.Color.BLANCO, new Posicion(1,"f")));
        tablero.addPieza(new Caballo(Pieza.Color.BLANCO, new Posicion(1,"g")));
        tablero.addPieza(new Torre(Pieza.Color.BLANCO, new Posicion(1,"h")));

        // Negras
        for(int i = 0; i < 8; i++){
            tablero.addPieza(new Peon(Pieza.Color.NEGRO, new Posicion(7,columnaPiezas[i])));
        }
        tablero.addPieza(new Torre(Pieza.Color.NEGRO, new Posicion(8,"a")));
        tablero.addPieza(new Caballo(Pieza.Color.NEGRO, new Posicion(8,"b")));
        tablero.addPieza(new Alfil(Pieza.Color.NEGRO, new Posicion(8,"c")));
        tablero.addPieza(new Reina(Pieza.Color.NEGRO, new Posicion(8,"d")));
        tablero.addPieza(new Rey(Pieza.Color.NEGRO, new Posicion(8,"e")));
        tablero.addPieza(new Alfil(Pieza.Color.NEGRO, new Posicion(8,"f")));
        tablero.addPieza(new Caballo(Pieza.Color.NEGRO, new Posicion(8,"g")));
        tablero.addPieza(new Torre(Pieza.Color.NEGRO, new Posicion(8,"h")));

        return tablero;
    }
    
    public String PartidaToPGN(){
        return "";
    }
}
