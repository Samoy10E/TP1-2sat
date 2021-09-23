import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Kosaraju {

    public static boolean estSatifiable(Graph graph){
        DFS_iter dfs1 = new DFS_iter(graph,graph.sommets());
        Integer[] liste_sortie = dfs1.getOrdreSortie();
        Collections.reverse(Arrays.asList(liste_sortie));

        Graph graphRev = Graph.reverse(graph);

        DFS_iter dfs2 = new DFS_iter(graphRev,liste_sortie);

        ArrayList<Integer>[] composanteFortementConnexe = dfs2.composanteConnexe();

        for (ArrayList<Integer> arr:composanteFortementConnexe){
            if (!DFS_iter.estPossible(arr,graphRev.order())){
                return false;
            }
        }
        return true;
    }
}
