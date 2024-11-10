package Model;

public class Posicion {
    public enum Columna{A,B,C,D,E,F,G,H};
    public Integer fila;
    public Columna columna;

    public Posicion(Integer fila, Columna columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Posicion(Integer fila, String columna) {
        this.fila = fila;
        this.columna = stringToColumna(columna);
    }
    /**
     * Funcion que se encarga de pasar filas y columnas a una posicion en matriz
     * @return posiciones en X y Y
     */
    public int[] toXY(){
        int[] pos = new int[2];
        pos[0] = 8 - this.fila;
        pos[1] = this.columna.ordinal();
        return pos;
    }

    public static Columna stringToColumna(String columna){
        return switch (columna) {
            case "a" -> Columna.A;
            case "b" -> Columna.B;
            case "c" -> Columna.C;
            case "d" -> Columna.D;
            case "e" -> Columna.E;
            case "f" -> Columna.F;
            case "g" -> Columna.G;
            case "h" -> Columna.H;
            default -> {
                System.out.println("Error columna no valida");
                yield null;
            }
        };
    }
}
