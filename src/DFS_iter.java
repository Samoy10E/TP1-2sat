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
        Integer[] ordreSortie = graphe.sommets();
        Integer[] sorties = temps_sortie.clone();
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

    public ArrayList<Integer>[] composanteConnexe(){
        int maxIter=0;
        for (int i=0;i< graphe.order();i++){
            if (maxIter<temps_iter[i]){
                maxIter = temps_iter[i];
            }
        }
        ArrayList<Integer>[] compCon = new ArrayList[maxIter+1];
        for (int i=0;i<maxIter+1;i++){
            compCon[i] = new ArrayList<Integer>(1);
        }

        for (int i=0;i< graphe.order();i++){
            compCon[temps_iter[i]].add(i);
        }
        return compCon;
    }

    public static boolean estPossible(ArrayList<Integer> composanteConnexe,int ordre){
        for (int i=0; i< composanteConnexe.size();i++){
            composanteConnexe.set(i,TraitementGraphe.convIndexToVar(composanteConnexe.get(i),ordre));
        }
        boolean estPos = true;
        for (int i=0;i< composanteConnexe.size();i++){
            for (int j=i+1;j<composanteConnexe.size();j++){
                if (composanteConnexe.get(i)+composanteConnexe.get(j)==0){
                    estPos = false;
                    break;
                }
            }
        }
        return estPos;
    }
}
