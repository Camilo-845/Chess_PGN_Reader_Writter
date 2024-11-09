package Model;

public class Peon extends Pieza{
    public Peon(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 1;
        }else{
            return -1;
        }
    }
}
