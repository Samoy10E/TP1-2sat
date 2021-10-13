import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pb2sat {

    public static boolean solve(String nom) throws IOException {
        ArrayList<String> file = TraitementGrapheImplication.LectureFichier(nom);
        Graph<String> graph = TraitementGrapheImplication.CreationGraph(file);
        ArrayList<Integer>[] composanteFortementConnexe = kosaraju(graph);
        boolean result = estSatisfiable(composanteFortementConnexe);
        return result;
    }

    public static ArrayList<Integer>[] kosaraju(Graph graph){
        //vérifie la satifiabilité du graphe
        DFS_iter dfs1 = new DFS_iter(graph,graph.sommets());
        Integer[] liste_sortie = dfs1.getOrdreSortie();
        Collections.reverse(Arrays.asList(liste_sortie));

        Graph graphRev = Graph.reverse(graph);

        DFS_iter dfs2 = new DFS_iter(graphRev,liste_sortie);

        ArrayList<Integer>[] composanteFortementConnexe = TraitementGrapheImplication.composanteConnexe(dfs2.getTemps_iter());
        return composanteFortementConnexe;
    }

    public static boolean estSatisfiable(ArrayList<Integer>[] composanteFortementConnexe){
        int cardinal = 0;
        for (ArrayList<Integer> arr:composanteFortementConnexe){
            cardinal += arr.size();
        }

        for (ArrayList<Integer> composanteConnexe:composanteFortementConnexe){
            if (!Pb2sat.verificationLitOppose(composanteConnexe,cardinal)){
                return false;
            }
        }
        return true;
    }
    public static boolean verificationLitOppose(ArrayList<Integer> composanteConnexe,int ordre){
        for (int i=0; i< composanteConnexe.size();i++){
            composanteConnexe.set(i,TraitementGrapheImplication.convIndexToVar(composanteConnexe.get(i),ordre));
        }
        boolean estPos = true;
        System.out.println(composanteConnexe);

        /*La liste des sommets est triés, donc 2 sommets incompatibles se trouvent
        * forcément de part et autre du sommet "0"
        * Nous plaçons donc 2 curseurs aux extrémités et nous les faisons converger
        * vers le centre, nous parcourons donc une seule fois la liste
        */
        int i = 0;
        int j = composanteConnexe.size()-1;
        while (composanteConnexe.get(i) <= 0 && composanteConnexe.get(j)>=0){
            while(composanteConnexe.get(j)>-composanteConnexe.get(i)){
                j--;
            }
            if (composanteConnexe.get(i)+composanteConnexe.get(j)==0){
                estPos = false;
                break;
            }
            i++;
        }
        return estPos;
    }
}
