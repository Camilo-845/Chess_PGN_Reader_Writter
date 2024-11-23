package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;



public class IntroApp extends javax.swing.JPanel {
    private Image imagen;

    // Constructor sin parámetros
public IntroApp() {
    ImageIcon icono = new ImageIcon(getClass().getResource("/Resources/supercell.gif"));
    imagen = icono.getImage();
    if (imagen == null) {
        System.out.println("Error: la imagen no se cargó correctamente");
    } else {
        System.out.println("La imagen se cargó correctamente");
    }
    setPreferredSize(new Dimension(200, 200));
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}
