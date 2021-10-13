import java.util.ArrayList;
import java.util.Arrays;

public class DFS_iter {

    private Graph graphe;
    private int temps;
    private int tIter;
    private Edge[] predecesseur;
    private Integer[] temps_sortie;
    private Integer[] temps_entree;
    private Integer[] temps_iter;
    private boolean[] parcouru;

    public Integer[] getTemps_sortie(){return temps_sortie;}
    public Integer[] getTemps_entree(){return temps_entree;}
    public Integer[] getTemps_iter(){return temps_iter;}
    public Edge[] getPredecesseur(){return predecesseur;}

    public Integer[] getOrdreSortie(){
        //ordre sortie est une liste contenant les sommets dans l'ordre
        Integer[] ordreSortie = graphe.sommets();
        //récupère la liste des temps de sortie des sommets rangés dans l'ordre des indices des littéraux
        Integer[] sorties = temps_sortie.clone();
        //trie la liste des temps de sortie et effectue-les même changement sur les sommets pour obtenir l'ordre de sortie
        quickSort_sortie(ordreSortie,sorties,0,graphe.order()-1);
        return ordreSortie;
    }

    private void quickSort_sortie(Integer[] sommets,Integer[] sorties, int begin, int end){
        if (begin < end) {
            int partitionIndex = partition_sortie(sommets,sorties ,begin, end);

            quickSort_sortie(sommets,sorties, begin, partitionIndex-1);
            quickSort_sortie(sommets,sorties, partitionIndex+1, end);
        }
    }
    private int partition_sortie(Integer[] sommets,Integer[] sorties, int begin, int end) {
        int pivot = sorties[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (sorties[j] <= pivot) {
                i++;

                int swapTemp = sorties[i];
                sorties[i] = sorties[j];
                sorties[j] = swapTemp;

                swapTemp = sommets[i];
                sommets[i] = sommets[j];
                sommets[j] = swapTemp;
            }
        }

        int swapTemp = sommets[i+1];
        sommets[i+1] = sommets[end];
        sommets[end] = swapTemp;

        swapTemp = sorties[i+1];
        sorties[i+1] = sorties[end];
        sorties[end] = swapTemp;

        return i+1;
    }

    public DFS_iter(Graph graph, Integer[] ordreSommet){
        //parcours de graphe itératif en profondeur classique
        temps = 0;
        tIter = 0;
        graphe = graph;
        predecesseur = new Edge[graphe.order()];
        temps_sortie = new Integer[graphe.order()];
        temps_entree = new Integer[graphe.order()];
        temps_iter = new Integer[graphe.order()];
        parcouru = new boolean[graph.order()];

        for (int sommet:ordreSommet){
            if (!parcouru[sommet]){
                temps_entree[sommet] = temps++;
                temps_iter[sommet] = tIter;
                parcouru[sommet] = true;
                for (Edge arc:graphe.arcsSortant(sommet)){
                    explore(arc);
                }
                tIter++;
                temps_sortie[sommet] = temps++;
            }
        }
    }

    private  void explore(Edge arc){
        int sommet = arc.dst();
        if (!parcouru[sommet]){
            temps_entree[sommet]=temps++;
            temps_iter[sommet]= tIter;
            parcouru[sommet] = true;
            predecesseur[sommet] = arc;
            for (Edge a:graphe.arcsSortant(sommet)){
                explore(a);
            }
            temps_sortie[sommet]=temps++;
        }
    }
}
