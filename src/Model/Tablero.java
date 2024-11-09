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
}
