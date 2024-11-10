package Model;

public class Reina extends Pieza{
    public Reina(Color color, Posicion posicion) {
        super(color, posicion);
        this.nomenclatura = "Q";
    }

    @Override
    public byte toByte() {
        if(color == Color.BLANCO){
            return 6;
        }else{
            return -6;
        }
    }
}
