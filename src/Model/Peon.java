package Model;

import java.util.Objects;

public class Peon extends Pieza{
    public Peon(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "P";
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public boolean esMovimientoValido(Posicion nuevaPosicion, boolean captura) {
        int filaInicial;
        if(posicion.columna != nuevaPosicion.columna){
            if(!captura) return false;
            if (nuevaPosicion.toXY()[1] > posicion.toXY()[1] + 1 || nuevaPosicion.toXY()[1] < posicion.toXY()[1] - 1){
                return false;
            }
            if((nuevaPosicion.toXY()[0] - posicion.toXY()[0]) > 1){
                return false;
            }
        }

        if(color == Color.BLANCO){ //Piezas blancas
            filaInicial = 2;
            if((posicion.toXY()[0] - nuevaPosicion.toXY()[0]) > 1){
                if(posicion.fila != filaInicial){
                    return false;
                }
            }
            if(posicion.columna == nuevaPosicion.columna && captura){
                return false;
            }
        }else { // Piezas negras
            filaInicial = 7;
            if ((nuevaPosicion.toXY()[0] - posicion.toXY()[0]) < -1) {
                if (posicion.fila != filaInicial) {
                    return false;
                }
            }
            if (posicion.columna == nuevaPosicion.columna && captura) {
                return false;
            }
        }
        return true;
    }
}
