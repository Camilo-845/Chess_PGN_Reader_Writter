package Model;

import java.util.Objects;

public class Torre extends Pieza{
    public Torre(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "R";
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 2;
        }else{
            return -2;
        }
    }

    @Override
    public boolean esMovimientoValido(Posicion nuevaPosicion, boolean captura) {
        int diferenciaFilas = Math.abs(nuevaPosicion.fila - posicion.fila);
        int diferenciaColumnas = Math.abs(nuevaPosicion.toXY()[1] - posicion.toXY()[1]);

        // La torre se mueve en línea recta (vertical u horizontal)
        return (diferenciaFilas == 0 && diferenciaColumnas != 0) ||
                (diferenciaFilas != 0 && diferenciaColumnas == 0);
    }

    @Override
    public Pieza copy() {
        return new Torre(this.color,new Posicion(this.posicion.fila, this.posicion.columna));
    }
}
