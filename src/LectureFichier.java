import java.io.*;
import java.util.ArrayList;

public class LectureFichier {

    public LectureFichier(String nom) throws IOException {
        Lecture(nom);
    }

    private ArrayList<String[]> Lecture(String nom) throws IOException {
        String nomFichier = "formule/";
        nomFichier = nomFichier.concat(nom);
        nomFichier = nomFichier.concat(".txt");

        File file = new File(nomFichier);

        BufferedReader b = new BufferedReader(new FileReader(file));

        String readLine;

        ArrayList<String[]> Clauses = new ArrayList<>();

        while ((readLine = b.readLine()) != null) {
            if (readLine.charAt(0) == 'c'){break;}
            else{
                Clauses.add(readLine.split(" "));
            }
        }
        return Clauses;
    }
}
