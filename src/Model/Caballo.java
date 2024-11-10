package Model;

public class Caballo extends Pieza{
    public Caballo(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "N";
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 3;
        }else{
            return -3;
        }
    }

    @Override
    public boolean esMovimientoValido(Posicion nuevaPosicion, boolean captura) {
        int fila = posicion.toXY()[0];
        int columna = posicion.toXY()[1];
        int[][] posiblesPosiciones = {{fila+2,columna+1},{fila+2,columna-1},{fila-2,columna+1},{fila-2,columna-1},
                {fila+1,columna+2},{fila-1,columna+2},{fila+1,columna-2},{fila-1,columna-2}};
        for (int[] posPrueba : posiblesPosiciones) {
            if (posPrueba[0] >= 0 && posPrueba[0] < 8 && posPrueba[1] >= 0 && posPrueba[1] < 8) {
                if (posPrueba[0] == nuevaPosicion.toXY()[0] && posPrueba[1] == nuevaPosicion.toXY()[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Pieza copy() {
        return new Caballo(this.color,new Posicion(this.posicion.fila, this.posicion.columna));
    }
}
