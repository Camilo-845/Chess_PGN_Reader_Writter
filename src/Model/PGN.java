package Model;

//Clase encargada de la lectura y escritura de pgn

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Encargada de la lectura y escrigura de archivos .pgn
 */
public class PGN {

    private String regexRondas = "(\\d+\\.[\\n ]*(([A-Z]*\\d*[a-zA-Z]+\\d[#+]*)|(\\w-\\w(-\\w)*))[\\n ]+(([A-Z]*\\d*[a-zA-Z]+\\d[#+]*)|(\\w-\\w(-\\w)*))(\\s*(1-0|0-1|1\\/2-1\\/2)?)?)";

    /**
     * Funcion encargada de devolver cada una de las jugadas
     * @param path
     * @return Arreglo con cada una de las jugadas
     */
    public ArrayList<String> getMoves(String path) {
        try {
            ArrayList<String> moves = new ArrayList<>();
            Scanner scanner = new Scanner(new File(path));
            StringBuilder mainStringBuilder = new StringBuilder();

            // Leer todo el documento y agregarlo al string
            while (scanner.hasNextLine()) {
                mainStringBuilder.append(" " + scanner.nextLine());
            }
            String mainString = mainStringBuilder.toString();

            // Usa Regex para agrupar coincidencias (Movimientos)
            Pattern pattern = Pattern.compile(regexRondas);
            Matcher matcher = pattern.matcher(mainString);
            while (matcher.find()) {
                String fullmove = matcher.group();
                String[] fullMoveArr = fullmove.split(" ");
                moves.add(fullMoveArr[1]);
                if (fullMoveArr.length > 2) {
                    moves.add(fullMoveArr[2]);
                }
            }

            // Filtrar movimientos que coincidan con el patrón \d-\d
            Pattern filtroPattern = Pattern.compile("\\d-\\d");
            moves.removeIf(move -> filtroPattern.matcher(move).find());

            return moves;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();  // Retorna una lista vacía en caso de error
        }
    }
    
    
    public void saveGame(String data){
    // Crear un cuadro de diálogo para seleccionar el archivo de destino
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar archivo");
    
    // Filtrar solo archivos con extensión .pgn
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PGN Files", "pgn"));
    
    // Mostrar el cuadro de diálogo de guardar y obtener la respuesta del usuario
    int seleccion = fileChooser.showSaveDialog(null);

    // Si el usuario selecciona "Guardar"
    if (seleccion == JFileChooser.APPROVE_OPTION) {
        // Obtener el archivo seleccionado por el usuario
        File archivoSeleccionado = fileChooser.getSelectedFile();
        
        // Verificar si el archivo seleccionado no tiene la extensión .pgn
        if (!archivoSeleccionado.getName().endsWith(".pgn")) {
            archivoSeleccionado = new File(archivoSeleccionado.getAbsolutePath() + ".pgn");
        }

        // Ahora, escribe algo en el archivo
        try (FileWriter writer = new FileWriter(archivoSeleccionado)) {
            // Aquí puedes escribir en el archivo
            writer.write(data);
            System.out.println("Archivo guardado en: " + archivoSeleccionado.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("El usuario canceló la selección del archivo.");
    }
}
}
