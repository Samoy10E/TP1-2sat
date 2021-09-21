import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label> {

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;
    private boolean estSatifiable;

    public void setEstSatifiable(boolean estSatifiable){this.estSatifiable=estSatifiable;}
    public boolean getEstSatisfiable(){return estSatifiable;}

    public static Graph reverse(Graph graph){
        Graph newGraph = new Graph(graph.order());
        for (int sommet=0;sommet< graph.order();sommet++){
            Edge[] arcs = graph.arcsSortant(sommet);
            for (Edge arc:arcs){
                newGraph.addArc(arc.dst(), arc.src(), arc.getLabel());
            }
        }
        return newGraph;
    }

    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public Edge[] arcsSortant(int sommet){

        int taille = incidency.get(sommet).size();
        Edge[] Larcs = new Edge[taille];
        for (int i=0;i<taille;i++){
            Larcs[i] = incidency.get(sommet).get(i);
        }
        return Larcs;
    }

    public Integer[] sommets() {
        Integer[] s = new Integer[cardinal];
        for (int i =0;i<cardinal;i++){s[i] = i;}
        return s;
    }

    public String toString() {
        String result = "";
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("Sommets : \n");
        for (int i = 0; i<cardinal;i++) {
            result = result.concat(TraitementGraphe.convIndexToVar(i,cardinal) + " ");
        }
        result = result.concat("\nArcs : \n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.toString(cardinal));
            }
        }
        return result;

    }


}
