package Model;

public class Peon extends Pieza{
    public Peon(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "P";
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
