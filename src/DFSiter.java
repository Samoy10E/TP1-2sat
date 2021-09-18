import java.util.Dictionary;
import java.util.Hashtable;

public class DFSiter {

    private Graph graph;
    Dictionary<Integer,Edge> predecesseur = new Hashtable<Integer,Edge>();
    Integer[] temps_entree;
    Integer[] temps_sortie;
    Integer[] temps_iter;

    public DFSiter(Graph graph){
        graph = graph;
        temps_entree = new Integer[graph.order()*2];
        temps_sortie = new Integer[graph.order()*2];
        temps_iter = new Integer[graph.order()*2];
    }

    public void dsfIter(Graph<String> graph){


    }

    private void explore(Edge edge){
        Integer sommet = edge.dst();

    }


}
