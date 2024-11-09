package Model;

public class Torre extends Pieza{
    public Torre(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 2;
        }else{
            return -2;
        }
    }
}
