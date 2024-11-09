package Model;

public class Caballo extends Pieza{
    public Caballo(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 3;
        }else{
            return -3;
        }
    }
}
