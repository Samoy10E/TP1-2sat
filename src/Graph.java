import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label> {

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;
    private boolean estSatisfiableDonne;//contient le résultat attendu et non pas le résultat du programme

    public void setEstSatisfiableDonne(boolean estSatisfiableDonne){this.estSatisfiableDonne=estSatisfiableDonne;}
    public boolean getEstSatisfiableDonne(){return estSatisfiableDonne;}

    public Graph(int size) {
        //initialisation
        cardinal = size;
        incidency = new ArrayList<>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<>());
        }
    }

    public int order() {
        return cardinal;
    }

    public Integer[] sommets() {
        Integer[] s = new Integer[cardinal];
        for (int i =0;i<cardinal;i++){s[i] = i;}
        return s;
    }

    public static Graph reverse(Graph graph){
        //fonction rendant un graphe dont le sens des arrètes est inversé par rapport à l'original
        Graph newGraph = new Graph(graph.order());
        for (int sommet=0;sommet< graph.order();sommet++){
            Edge[] arcs = graph.arcsSortant(sommet);
            for (Edge arc:arcs){
                newGraph.addArc(arc.dst(), arc.src(), arc.getLabel());
            }
        }
        return newGraph;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public Edge[] arcsSortant(int sommet){
        //retourne la liste des arcs sortant du sommet
        int taille = incidency.get(sommet).size();
        Edge[] Larcs = new Edge[taille];
        for (int i=0;i<taille;i++){
            Larcs[i] = incidency.get(sommet).get(i);
        }
        return Larcs;
    }

    public String toString() {
        String result = "";
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("Sommets : \n");
        for (int i = 0; i<cardinal;i++) {
            result = result.concat(TraitementGrapheImplication.convIndexToVar(i,cardinal) + " ");
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
