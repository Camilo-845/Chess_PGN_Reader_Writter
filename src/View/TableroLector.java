package View;

import Model.Tablero;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TableroLector extends JPanel {
    private Tablero tablero; // Matriz de piezas
    private Map<Integer, Image> piezasImagenes; // Mapa para cargar imágenes

    public TableroLector(Tablero tablero) {
        this.tablero = tablero;
        this.piezasImagenes = cargarImagenes();
        setPreferredSize(new Dimension(400, 400)); // Tamaño del tablero (8x8 casillas de 50x50 píxeles)
    }

    private Map<Integer, Image> cargarImagenes() {
        Map<Integer, Image> imagenes = new HashMap<>();
        
        // Ruta a la carpeta de imágenes de piezas
        String ruta = "/Resources/Pieces/";

        // Cargar imágenes según el valor de la pieza
        imagenes.put(1, new ImageIcon(getClass().getResource(ruta + "peon_blanco.png")).getImage());
        imagenes.put(-1, new ImageIcon(getClass().getResource(ruta + "peon_negro.png")).getImage());
        imagenes.put(2, new ImageIcon(getClass().getResource(ruta + "torre_blanco.png")).getImage());
        imagenes.put(-2, new ImageIcon(getClass().getResource(ruta + "torre_negro.png")).getImage());
        imagenes.put(3, new ImageIcon(getClass().getResource(ruta + "caballo_blanco.png")).getImage());
        imagenes.put(-3, new ImageIcon(getClass().getResource(ruta + "caballo_negro.png")).getImage());
        imagenes.put(4, new ImageIcon(getClass().getResource(ruta + "alfil_blanco.png")).getImage());
        imagenes.put(-4, new ImageIcon(getClass().getResource(ruta + "alfil_negro.png")).getImage());
        imagenes.put(5, new ImageIcon(getClass().getResource(ruta + "rey_blanco.png")).getImage());
        imagenes.put(-5, new ImageIcon(getClass().getResource(ruta + "rey_negro.png")).getImage());
        imagenes.put(6, new ImageIcon(getClass().getResource(ruta + "reina_blanco.png")).getImage());
        imagenes.put(-6, new ImageIcon(getClass().getResource(ruta + "reina_negro.png")).getImage());

        return imagenes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        byte[][] byteMatriz = tablero.toByteMatriz();
        int tileSize = getWidth() / 8; // Tamaño de cada casilla
        
        // Dibujar tablero y piezas
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                // Dibujar casilla del tablero
                if ((fila + col) % 2 == 0) {
                    g.setColor(new Color(64, 56, 48));
                } else {
                    g.setColor(new Color(255, 213, 179));
                }
                g.fillRect(col * tileSize, fila * tileSize, tileSize, tileSize);

                // Obtener valor de la pieza
                int pieza = byteMatriz[fila][col];

                // Dibujar la pieza si existe
                if (pieza != 0 && piezasImagenes.containsKey(pieza)) {
                    Image imagenPieza = piezasImagenes.get(pieza);
                    g.drawImage(imagenPieza, col * tileSize, fila * tileSize, tileSize, tileSize, this);
                }
            }
        }
    }
}

