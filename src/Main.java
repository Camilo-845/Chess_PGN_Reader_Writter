import Model.PGN;
import Model.Partida;
import View.VistaTablero;
public class Main {
    public static void main(String[] args) {
        VistaTablero vista = new VistaTablero();
        Partida partida = new Partida("TEST_FILES/file1.pgn");
        for(int i = 0; i<partida.numeroMovimientos; i++){
            vista.imprimirTablero(partida.obtenerTablero(i).toByteMatriz());
        }
    }
}
