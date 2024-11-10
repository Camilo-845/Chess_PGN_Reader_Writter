package Model;

public class Rey extends Pieza{
    public Rey(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "K";
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 5;
        }else{
            return -5;
        }
    }

    @Override
    public boolean esMovimientoValido(Posicion nuevaPosicion, boolean captura) {
        int diferenciaFilas = Math.abs(nuevaPosicion.fila - posicion.fila);
        int diferenciaColumnas = Math.abs(nuevaPosicion.toXY()[1] - posicion.toXY()[1]);

        // El rey solo puede moverse una casilla en cualquier dirección
        return diferenciaFilas <= 1 && diferenciaColumnas <= 1;
    }
}
