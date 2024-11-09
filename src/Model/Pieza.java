package Model;

public abstract class Pieza {
    public enum Color {NEGRO, BLANCO}
    protected Color color;
    public Posicion posicion;

    public Pieza(Color color, Posicion posicion) {
        this.color = color;
        this.posicion = posicion;

    }public Pieza(Color color, String columna, int fila) {
        this.color = color;
        this.posicion = new Posicion(fila,columna);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract byte toByte();
}
