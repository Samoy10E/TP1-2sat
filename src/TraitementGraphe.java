import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TraitementGraphe {

    public static ArrayList<String> LectureFichier(String nom) throws IOException {
        String nomFichier = "formule/";
        nomFichier = nomFichier.concat(nom);
        nomFichier = nomFichier.concat(".txt");

        File file = new File(nomFichier);

        BufferedReader b = new BufferedReader(new FileReader(file));

        String readLine;

        ArrayList<String> Clauses = new ArrayList<>();

        while ((readLine = b.readLine()) != null) {
            Clauses.add(readLine);
        }
        return Clauses;
    }

    public static Graph CreationGraph(ArrayList<String> Clauses){
        int i=0;
        while (i<Clauses.size() && !Clauses.get(i).contains("p ")){i++;}

        String[] param = Clauses.get(i).split(" ");
        int Nvar = Integer.parseInt(param[2]);
        int Nclause = Integer.parseInt(param[3]);
        Clauses.remove(i);

       Graph graph = new Graph(Nvar*2);

       int source,dest;
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
               String[] mots = ligne.split(" ");
               source = convVarToIndex(Integer.parseInt(mots[0]),Nvar*2);
               dest = convVarToIndex(-Integer.parseInt(mots[1]),Nvar*2);
               graph.addArc(source,dest,"");

               dest = convVarToIndex(-Integer.parseInt(mots[0]),Nvar*2);
               source = convVarToIndex(Integer.parseInt(mots[1]),Nvar*2);
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
