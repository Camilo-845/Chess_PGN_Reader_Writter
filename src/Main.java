import Controller.MainViewController;
import Model.PGN;
import Model.Partida;
import View.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView("Vista Principal", new Dimension(800,600));
        MainViewController controller = new MainViewController(view);
        controller.iniciar();
        view.setVisible(true);
        /*
        VistaTablero vista = new VistaTablero();
        Partida partida = new Partida("TEST_FILES/file1.pgn");
        for(int i = 0; i<partida.numeroMovimientos; i++){
            vista.imprimirTablero(partida.obtenerTablero(i).toByteMatriz());
        }*/



    }
}
