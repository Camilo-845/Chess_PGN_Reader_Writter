package Model;

public class Posicion {
    public enum Columna{A,B,C,D,E,F,G,H};
    public Integer fila;
    public Columna columna;

    public Posicion(Integer fila, Columna columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public Posicion(Integer fila, int columna) {
        this.fila = 8-fila;
        this.columna = intToColumna(columna);
        System.out.println(this);
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
        pos[0] = 8 - this.fila; // Para la fila: 1 es la fila inferior, 8 la superior en un tablero estándar
        pos[1] = this.columna.ordinal(); // Usamos el índice ordinal de la columna (0 a 7)
        return pos;
    }
    
    public static Columna intToColumna(int columna){
        switch (columna) {
            case 0: return Columna.A;
            case 1: return Columna.B;
            case 2: return Columna.C;
            case 3: return Columna.D;
            case 4: return Columna.E;
            case 5: return Columna.F;
            case 6: return Columna.G;
            case 7: return Columna.H;
            default: 
                System.out.println("Error columna no válida");
                return null;
        }
    }
    public static int columnaToInt(Columna columna) {
    switch (columna) {
        case A: return 0;
        case B: return 1;
        case C: return 2;
        case D: return 3;
        case E: return 4;
        case F: return 5;
        case G: return 6;
        case H: return 7;
        default: 
            System.out.println("Error columna no válida");
            return -1; // Retornamos un valor inválido en caso de error
    }
}

    
    public static Columna stringToColumna(String columna){
        return switch (columna.toLowerCase()) { // Convertir a minúsculas para manejar entradas como "A" o "a"
            case "a" -> Columna.A;
            case "b" -> Columna.B;
            case "c" -> Columna.C;
            case "d" -> Columna.D;
            case "e" -> Columna.E;
            case "f" -> Columna.F;
            case "g" -> Columna.G;
            case "h" -> Columna.H;
            default -> {
                System.out.println("Error columna no válida");
                yield null;
            }
        };
    }

    @Override
    public String toString() {
        return ""+columna.toString().toLowerCase()+fila;
    }
    
    
}
