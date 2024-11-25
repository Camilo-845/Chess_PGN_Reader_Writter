package View;

import Model.MovimientoListener;
import Model.Tablero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class TableroEscritor extends JPanel {
    private Tablero tablero;
    private Map<Integer, Image> piezasImagenes;
    private int piezaSeleccionada = 0;
    private int mouseX, mouseY, origenX, origenY;
    private boolean arrastrando = false;
    
    private MovimientoListener listener;

    public TableroEscritor() {
    }

    // Establecer el listener de movimientos
    public void setMovimientoListener(MovimientoListener listener) {
        this.listener = listener;
        System.out.println("Estoy escuchando: " + this.listener);
    }

    // Notificar al listener sobre el movimiento realizado
    private void notificarMovimiento(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        System.out.println("Movimiento mandado: " + this.listener);
        if (listener != null) {
            listener.onMovimientoRealizado(filaOrigen, colOrigen, filaDestino, colDestino);
        } else {
            System.out.println("El listener es null cuando intentamos notificar el movimiento");
        }
    }

    // Constructor con parámetros para el tablero y el listener
    public TableroEscritor(Tablero tablero, MovimientoListener listener) {
        this.tablero = tablero;
        this.piezasImagenes = cargarImagenes();
        setPreferredSize(new Dimension(400, 400));

        // Establecer el listener
        setMovimientoListener(listener);

        // Manejador de eventos de ratón
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Calcular la casilla de la posición del ratón
                int col = e.getX() / (getWidth() / 8);
                int fila = e.getY() / (getHeight() / 8);

                piezaSeleccionada = tablero.toByteMatriz()[fila][col];
                if (piezaSeleccionada != 0) {
                    arrastrando = true;
                    mouseX = e.getX();
                    mouseY = e.getY();
                    origenX = col;
                    origenY = fila;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (arrastrando) {
                    // Calcular la casilla de destino
                    int colDestino = e.getX() / (getWidth() / 8);
                    int filaDestino = e.getY() / (getHeight() / 8);

                    // Actualizar la matriz de piezas en el tablero
                    tablero.toByteMatriz()[origenY][origenX] = 0;
                    tablero.toByteMatriz()[filaDestino][colDestino] = (byte) piezaSeleccionada;

                    // Detener el arrastre
                    arrastrando = false;
                    piezaSeleccionada = 0;
                    repaint();

                    // Notificar el movimiento al listener
                    System.out.println("Movimiento");
                    notificarMovimiento(origenY, origenX, filaDestino, colDestino); // Pasar origenY, origenX
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (arrastrando) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    repaint();
                }
            }
        });
    }

    // Establecer el tablero de ajedrez (puede ser llamado para actualizar el tablero)
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
        repaint();
    }

    // Cargar las imágenes de las piezas de ajedrez
    private Map<Integer, Image> cargarImagenes() {
        Map<Integer, Image> imagenes = new HashMap<>();
        String ruta = "/Resources/Pieces/";
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

    // Pintar el tablero y las piezas en el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        byte[][] byteMatriz = tablero.toByteMatriz();
        int tileSize = getWidth() / 8;

        // Dibujar casillas del tablero y piezas
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                g.setColor((fila + col) % 2 == 0 ? new Color(64, 56, 48) : new Color(255, 213, 179));
                g.fillRect(col * tileSize, fila * tileSize, tileSize, tileSize);

                int pieza = byteMatriz[fila][col];
                if (pieza != 0 && piezasImagenes.containsKey(pieza) && !(arrastrando && fila == origenY && col == origenX)) {
                    g.drawImage(piezasImagenes.get(pieza), col * tileSize, fila * tileSize, tileSize, tileSize, this);
                }
            }
        }

        // Dibujar la pieza arrastrada
        if (arrastrando && piezasImagenes.containsKey(piezaSeleccionada)) {
            g.drawImage(piezasImagenes.get(piezaSeleccionada), mouseX - tileSize / 2, mouseY - tileSize / 2, tileSize, tileSize, this);
        }
    }
}
