import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TraitementGraphe {

    public static ArrayList<String> LectureFichier(String nom) throws IOException {
        //Création du chemin relatif "formule/nomFichier.txt"
        String nomFichier = "formule/";
        nomFichier = nomFichier.concat(nom);
        nomFichier = nomFichier.concat(".txt");

        File file = new File(nomFichier);

        BufferedReader b = new BufferedReader(new FileReader(file));

        String readLine;
        ArrayList<String> Clauses = new ArrayList<>();

        //Remplissage de l'ArrayList
        while ((readLine = b.readLine()) != null) {
            Clauses.add(readLine);
        }
        return Clauses;
    }

    public static Graph CreationGraph(ArrayList<String> Clauses){
        //On cherche la ligne de paramètre, on va supposer qu'il y en a qu'une
        int i=0;
        while (i<Clauses.size() && !Clauses.get(i).contains("p ")){i++;}

        //On récupère les informations
        String[] param = Clauses.get(i).split(" ");
        int Nvar = Integer.parseInt(param[2]);
        int Nclause = Integer.parseInt(param[3]);
        Clauses.remove(i);

        /*
        Initialisations du graphs, il y a 2 fois plus de sommet que de variable
        car il y aura x et -x, la variable et sa négation
         */
       Graph graph = new Graph(Nvar*2);

       int source,dest;
       String[] mots;
       for (i=0;i<Clauses.size();i++){
           String ligne = Clauses.get(i);
           if (ligne.contains("c ")){
               if (ligne.contains("Not satisfiable")){
                   graph.setEstSatifiable(false);
               }
               else if (ligne.contains("Satisfiable")){
                   graph.setEstSatifiable(true);
               }
           }
           else {
               //Je décompose la ligne en plusieurs mot, ex "x y 0"->["x","y","0"]
               mots = ligne.split(" ");

               // source = -x; destination = y
               source = convVarToIndex(-Integer.parseInt(mots[0]),Nvar*2);
               dest = convVarToIndex(Integer.parseInt(mots[1]),Nvar*2);
               graph.addArc(source,dest,"");

               //source = -y; destination = x
               source = convVarToIndex(-Integer.parseInt(mots[1]),Nvar*2);
               dest = convVarToIndex(Integer.parseInt(mots[0]),Nvar*2);
               graph.addArc(source,dest,"");
           }
       }
        return graph;
    }

    public static int convIndexToVar(int index,int cardinal){
        int newVar = index - cardinal/2;
        if (index>=cardinal/2){newVar++;}
        return newVar;
    }

    public  static int convVarToIndex(int var,int cardinal){
        int newIndex = var + cardinal/2;
        if (var>0){newIndex--;}
        return newIndex;
    }
}
