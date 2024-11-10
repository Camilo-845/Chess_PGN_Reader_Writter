package Model;

import javax.swing.text.Position;
import java.util.ArrayList;

public class Tablero {
    public ArrayList<Pieza> piezas;
    public Tablero() {
        this.piezas = new ArrayList<>();
    }
    public Tablero(Tablero tablero) {
        this.piezas = new ArrayList<>();
        for(Pieza pieza : tablero.piezas) {
            this.piezas.add(pieza.copy());
        }
    }
    public void addPieza(Pieza p) {
        this.piezas.add(p);
    }
    public void removePieza(Pieza p) {
        this.piezas.remove(p);
    }

    /**
     * Retorna la pieza que esta ubicica en la posicion data
     * @param pos
     * @return Pieza || null
     */
    public Pieza getPieza(Posicion pos) {
        for (Pieza p : piezas) {
            if (p.posicion.fila == pos.fila && p.posicion.columna == pos.columna) {
                return p;
            }
        }
        return null;
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
        Posicion[] posiciones = ObtenerPosiciones_Inicial_Final(movimiento, color);
        if(posiciones[0] != null && posiciones[1] != null){
            Pieza piezaInicial = getPieza(posiciones[0]);
            Pieza piezaFinal = getPieza(posiciones[1]);
            if(piezaFinal != null){
                removePieza(piezaFinal);
            }

            if(piezaInicial != null){
                piezaInicial.posicion = posiciones[1];
            }
        }

    }
    private Posicion[] ObtenerPosiciones_Inicial_Final(String movimiento, Pieza.Color color){
        Posicion[] posiciones= new Posicion[2];
        boolean captura = movimiento.contains("x");
        movimiento = movimiento.replaceAll("[#!+x]", "");//Quitamos las x de capturas

        //String sin especificacion de pieza para verificar si el movmimiento no esta sobrepuesto
        String movimientoSinPieza = movimiento.replaceAll("[A-NP-Z]", "");
        boolean sobrepuesto = movimientoSinPieza.length() > 2;

        Posicion nuevaPosicion = null;
        Posicion posicionSobrepuesta = null;

        Posicion pieza_a_mover = null;
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

            for(Pieza pieza: this.piezas){
                if (!pieza.color.equals(color))continue;
                if( pieza.nomenclatura.contains(movimiento.charAt(0)+"") || movimiento.matches("^[a-z].*")){
                    if((posicionSobrepuesta == null) && pieza.esMovimientoValido(nuevaPosicion,captura)){
                        pieza_a_mover = pieza.posicion;
                        break;
                    }else if (posicionSobrepuesta != null){
                        boolean filtroColumna = posicionSobrepuesta.columna == null;
                        boolean filtroFila = posicionSobrepuesta.fila == null;
                        if(!filtroColumna){
                            if(pieza.posicion.toXY()[1] == nuevaPosicion.toXY()[1]){
                                if(pieza.esMovimientoValido(nuevaPosicion,captura)){
                                    pieza_a_mover = pieza.posicion;
                                    break;
                                }
                            };
                        }else if(filtroFila){
                            if(pieza.posicion.fila == nuevaPosicion.fila){
                                if(pieza.esMovimientoValido(nuevaPosicion,captura)){
                                    pieza_a_mover = pieza.posicion;
                                    break;
                                }
                            };
                        }
                    }
                }
            }

        }else{
            int filaCambio = (color == Pieza.Color.BLANCO)?1:8;
            Posicion[] posTorre = new Posicion[2];
            Posicion[] posRey = {new Posicion(filaCambio,"e"),new Posicion(filaCambio,"g")};
            if(movimiento.equals("O-O")){
                posTorre[0] =new Posicion(filaCambio,"h");
                posTorre[1] =new Posicion(filaCambio,"f");
            }else{
                posTorre[0] =new Posicion(filaCambio,"a");
                posTorre[1] =new Posicion(filaCambio,"d");
            }
            realizarMovimientoEnrroque(posRey, posTorre);
        }
        posiciones[0] =pieza_a_mover;
        posiciones[1] = nuevaPosicion;

        return posiciones;
    }

    private void realizarMovimientoEnrroque(Posicion[] posRey, Posicion[] posTorre){
        Pieza rey = getPieza(posRey[0]);
        Pieza torre = getPieza(posTorre[0]);
        rey.posicion = posRey[1];
        torre.posicion = posTorre[1];
    }
}

