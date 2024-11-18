package View;

import javax.swing.*;
import java.awt.*;

public class ReaderView extends JFrame {
    public JPanel panel1;
    public JPanel ChessBoard;
    public JButton BotonSiguiente;
    public JButton BotonAnterior;
    public JButton BotonVolver;
    public JButton BotonSeleccionarArchivo;
    public JPanel PanelBotones;
    public JTextArea PGN_moves;
    public JPanel Tablero;
    public JPanel pgnDataPanel;

    public ReaderView() {
        PGN_moves.setLineWrap(true);
        PGN_moves.setBorder(BorderFactory.createLineBorder(Color.black));
        Tablero.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
