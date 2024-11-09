package Model;

public class Rey extends Pieza{
    public Rey(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 5;
        }else{
            return -5;
        }
    }
}
