package View;

import Model.Partida;
import Model.Tablero;

import java.util.HashMap;
import java.util.Scanner;

public class VistaTablero {
    public void imprimirTablero(byte[][] TableroObj) {
        char[][] tablero = mapearTablero(TableroObj);

        System.out.println("    a   b   c   d   e   f   g   h ");
        System.out.println("  +---+---+---+---+---+---+---+---+");

        for (int i = 0; i < tablero.length; i++) {
            System.out.print((8 - i) + " |");

            for (int j = 0; j < tablero[i].length; j++) {
                char pieza = tablero[i][j];
                if ((i + j) % 2 == 0) {
                    System.out.print(" " + pieza + " |");
                } else {
                    System.out.print("\033[48;5;235;97m " + pieza + " \033[0m|"); // Fondo oscuro y texto claro
                }
            }

            System.out.println(" " + (8 - i));
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }

        System.out.println("    a   b   c   d   e   f   g   h ");
    }



    public static char[][] mapearTablero(byte[][] tablero){
        char[][] mapear = new char[tablero.length][tablero[0].length];
        char[] arregloDeMapeoBlanco = {' ','♟','♜','♞','♝','♚','♛'};
        char[] arregloDeMapeoNegro = {' ','♙','♖','♘','♗','♔','♕'};
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j] >= 0){
                    mapear[i][j] = arregloDeMapeoBlanco[tablero[i][j]];
                }
                else{
                    mapear[i][j] = arregloDeMapeoNegro[-tablero[i][j]];
                }
            }
        }
        return mapear;
    }

    public static void clearConsoleCommand() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la consola");
            e.printStackTrace();
        }
    }

    public String capturarEntrada(String mensaje){
        Scanner scan = new Scanner(System.in);
        System.out.print(mensaje);
        return scan.nextLine();
    }

}
