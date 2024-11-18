package View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public Dimension viewSize;



    public MainView(String tittle, Dimension size) {
        setTitle(tittle);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.viewSize= size;
        this.setResizable(false);
        this.setPreferredSize(size);
        this.pack();
    }

    public void changeView(JPanel newView) {
        // Remover todos los componentes del content pane
        getContentPane().removeAll();
        this.add(newView);
        // Revalidar y repintar para refrescar la ventana
        getContentPane().getComponent(0);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public JPanel getCurrentView() {
        return (JPanel) this.getContentPane().getComponent(0);
    }

    public void iniciar(){
        setVisible(true);
    }

    public void parar(){
        setVisible(false);
    }
}
