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
}
