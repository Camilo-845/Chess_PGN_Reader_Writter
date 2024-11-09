package Model;

public abstract class Pieza {
    public enum Color {NEGRO, BLANCO}
    protected Color color;

    public Pieza(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
