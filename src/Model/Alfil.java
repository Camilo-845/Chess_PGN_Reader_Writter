package Model;

public class Alfil extends Pieza{
    public Alfil(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 4;
        }else{
            return -4;
        }
    }
}
