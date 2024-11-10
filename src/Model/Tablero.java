package Model;

import java.util.ArrayList;

public class Tablero {
    public ArrayList<Pieza> piezas;
    public Tablero() {
        this.piezas = new ArrayList<>();
    }
    public Tablero(Tablero tablero) {
        this.piezas = new ArrayList<>(tablero.piezas);
    }
    public void addPieza(Pieza p) {
        this.piezas.add(p);
    }
    public void removePieza(Pieza p) {
        this.piezas.remove(p);
    }

    /**
     * Funcion que trasforma los datos del tablero a una matriz de bytes, que representan
     * todas las casillas del tablero de ajedrez
     * @return Byte[][]
     */
    public byte[][] toByteMatriz(){
        byte[][] matriz = new byte[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matriz[i][j] = 0;
            }
        }
        for(Pieza pieza: this.piezas){
            int[] posPieza = pieza.posicion.toXY();
            matriz[posPieza[0]][posPieza[1]] = pieza.toByte();
        }
        return matriz;
    }

    /**
     * Funcion encargada de hacer los movimientos dentro del tablero
     */
    public void realizarMovimiento(Pieza.Color color, String movimiento){
        Posicion[] posiciones = ObtenerPosiciones_Inicial_Final(movimiento);

        if(movimiento.matches("^[a-z].*") || movimiento.charAt(0) == 'P'){
            //peon
        }
        else{
            switch(movimiento.charAt(0)){
                case 'N':
                    // Caballo
                    break;
                case 'B':
                    // Alfil
                    break;
                case 'Q':
                    //Reina
                    break;
                case 'K':
                    //Rey
                    break;
                case 'R':
                    //Torre
                    break;
                case 'O':
                    if(movimiento.equals("O-O")){
                        //Enrroque corto
                    }else{
                        //Enrroque largo
                    }
            }
        }
    }
    private Posicion[] ObtenerPosiciones_Inicial_Final(String movimiento){
        Posicion[] posiciones= new Posicion[2];

        movimiento = movimiento.replaceAll("[#!+x]", "");//Quitamos las x de capturas

        //String sin especificacion de pieza para verificar si el movmimiento no esta sobrepuesto
        String movimientoSinPieza = movimiento.replaceAll("[A-NP-Z]", "");
        boolean sobrepuesto = movimientoSinPieza.length() > 2;

        Posicion nuevaPosicion = null;
        Posicion posicionSobrepuesta = null;
        if(movimiento.charAt(0) != 'O'){
            //Guardamos las posiciones del movimiento y si el el caso de la sobreposicion
            if(sobrepuesto){
                if((movimientoSinPieza.charAt(0)+"").matches("\\d")){
                    posicionSobrepuesta = new Posicion(Integer.parseInt(movimientoSinPieza.charAt(0)+""), (Posicion.Columna) null);
                }else{
                    posicionSobrepuesta = new Posicion((Integer) null, movimientoSinPieza.charAt(0)+"");
                }
                nuevaPosicion = new Posicion(Integer.parseInt(movimientoSinPieza.charAt(2)+""), movimientoSinPieza.charAt(1)+"");
            }else{
                nuevaPosicion = new Posicion(Integer.parseInt(movimientoSinPieza.charAt(1)+""), movimientoSinPieza.charAt(0)+"");
            }

            Pieza pieza_a_mover;
            for(Pieza pieza: this.piezas){
                if(pieza.esMovimientoValido(nuevaPosicion,true)){
                    pieza_a_mover = pieza;
                    break;
                };
            }
        }
        posiciones[0] = posicionSobrepuesta;
        posiciones[1] = nuevaPosicion;

        return posiciones;
    }
}

