package Model;

public class Alfil extends Pieza{
    public Alfil(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "B";
    }

    @Override
    public Pieza copy() {
        return new Alfil(this.color,new Posicion(this.posicion.fila, this.posicion.columna));
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 4;
        }else{
            return -4;
        }
    }

    @Override
    public boolean esMovimientoValido(Posicion nuevaPosicion, boolean captura) {
        int diferenciaFilas = Math.abs(nuevaPosicion.fila - posicion.fila);
        int diferenciaColumnas = Math.abs(nuevaPosicion.toXY()[1] - posicion.toXY()[1]);

        // El alfil se mueve en diagonal
        return diferenciaFilas == diferenciaColumnas;
    }
}
