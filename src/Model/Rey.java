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
        int diferenciaColumnas = Math.abs(Posicion.columnaToInt(nuevaPosicion.columna) - Posicion.columnaToInt(posicion.columna));

        // El rey solo puede moverse una casilla en cualquier dirección (horizontal, vertical o diagonal)
        return diferenciaFilas <= 1 && diferenciaColumnas <= 1;
    }


    @Override
    public Pieza copy() {
        return new Rey(this.color,new Posicion(this.posicion.fila, this.posicion.columna));
    }
}
