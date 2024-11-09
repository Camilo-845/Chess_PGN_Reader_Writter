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

/**
 * Clase Encargada de la lectura y escrigura de archivos .pgn
 */
public class PGN {

    private String regexRondas = "(\\d+\\.[\\n ]*(([a-zA-Z]+\\d[#+]*)|(\\w-\\w(-\\w)*))[\\n ]+(([a-zA-Z]+\\d[#+]*)|(\\w-\\w(-\\w)*))(\\s*(1-0|0-1|1\\/2-1\\/2)?)?)";

    /**
     * Funcion encargada de devolver cada una de las jugadas
     * @param path
     * @return Arreglo con cada una de las jugadas
     */
    public ArrayList<String> getMoves(String path){
        try{
            ArrayList<String> moves = new ArrayList<>();
            Scanner scanner = new Scanner(new File(path));
            StringBuilder mainStringBuilder = new StringBuilder();

            //Leer todo el documento y agregarlo al string
            while(scanner.hasNextLine()){
                mainStringBuilder.append(" " + scanner.nextLine());
            }
            String mainString = mainStringBuilder.toString();

            //Usa Regex para agrupar coincidencias(Movimientos)

            Pattern pattern = Pattern.compile(regexRondas);
            Matcher matcher = pattern.matcher(mainString);
            while (matcher.find()) {
                String fullmove = matcher.group();
                String[] fullMoveArr = fullmove.split(" ");
                moves.add(fullMoveArr[1]);
                if(fullMoveArr.length > 2){
                    moves.add(fullMoveArr[2]);
                }
            }
            return moves;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
