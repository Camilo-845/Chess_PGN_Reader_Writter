package Model;

public class Reina extends Pieza{
    public Reina(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "Q";
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 6;
        }else{
            return -6;
        }
    }

    @Override
    public boolean esMovimientoValido(Posicion nuevaPosicion, boolean captura) {
        int diferenciaFilas = Math.abs(nuevaPosicion.fila - posicion.fila);
        int diferenciaColumnas = Math.abs(nuevaPosicion.toXY()[1] - posicion.toXY()[1]);

        // La reina se mueve en línea recta (vertical, horizontal o diagonal)
        return (diferenciaFilas == 0 && diferenciaColumnas != 0) || // Movimiento horizontal
                (diferenciaFilas != 0 && diferenciaColumnas == 0) || // Movimiento vertical
                (diferenciaFilas == diferenciaColumnas);            // Movimiento diagonal
    }
}
