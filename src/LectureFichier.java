import java.io.*;
import java.util.ArrayList;

public class LectureFichier {

    public LectureFichier(String nom) throws IOException {
        Lecture(nom);
    }

    private void Lecture(String nom) throws IOException {
        String nomFichier = "formule/";
        nomFichier = nomFichier.concat(nom);
        nomFichier = nomFichier.concat(".txt");

        File file = new File(nomFichier);

        BufferedReader b = new BufferedReader(new FileReader(file));

        String readLine;

        ArrayList<String> list = new ArrayList<>();

        while ((readLine = b.readLine()) != null) {
            if (readLine.charAt(0) == 'c'){break;}
            else if (readLine.charAt(0) == 'p'){
                String[] mots = readLine.split(" ");
                int Nvar = Integer.parseInt(mots[2]);
                int Nclause = Integer.parseInt(mots[3]);
                System.out.println(Nvar);
                System.out.println(Nclause);
            }
        }
    }
}
